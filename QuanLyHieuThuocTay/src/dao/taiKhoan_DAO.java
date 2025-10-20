package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Import Statement
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.TaiKhoan;

public class taiKhoan_DAO {

    // Lấy tất cả tài khoản
    public List<TaiKhoan> getAllTaiKhoan() {
        List<TaiKhoan> dsTaiKhoan = new ArrayList<>();
        String sql = "SELECT maTK, tenTK, matKhau, quyenHan FROM TaiKhoan";
        // Dùng try-with-resources cho cả Connection, PreparedStatement, ResultSet
        try (Connection con = ConnectDB.getConnection(); // Lấy connection mới
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(
                    rs.getString("maTK"),
                    rs.getString("tenTK"),
                    "********", // Không lấy mật khẩu thật
                    rs.getString("quyenHan")
                );
                dsTaiKhoan.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console
        }
        return dsTaiKhoan;
    }

    // Tự động tạo Mã Tài Khoản mới (ví dụ: TK001, TK002...)
    public String generateNewMaTK() {
        String newMaTK = "TK001"; // Mã mặc định nếu chưa có tài khoản nào
        String sql = "SELECT MAX(maTK) FROM TaiKhoan";
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement(); // Dùng Statement vì query đơn giản
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                String maxMaTK = rs.getString(1);
                if (maxMaTK != null && maxMaTK.matches("^TK\\d+$")) { // Kiểm tra định dạng TK...
                    try {
                        int num = Integer.parseInt(maxMaTK.substring(2)) + 1;
                        newMaTK = String.format("TK%03d", num); // Format thành TKxxx (hỗ trợ đến 999)
                    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                        System.err.println("Lỗi khi parse mã TK lớn nhất: " + maxMaTK + ". Sử dụng mã mặc định: " + newMaTK);
                        // Có thể log lỗi chi tiết hơn nếu cần
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newMaTK;
    }

    // Hàm THÊM tài khoản mới (dùng cho Admin, có thể set quyền)
    public boolean insertTaiKhoan(TaiKhoan tk) {
        // Kiểm tra tên TK đã tồn tại chưa
        if (getTaiKhoanByTenTK(tk.getTenTK()) != null) {
            System.err.println("Lỗi: Tên tài khoản '" + tk.getTenTK() + "' đã tồn tại!");
            return false;
        }

        String sql = "INSERT INTO TaiKhoan(maTK, tenTK, matKhau, quyenHan) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tk.getMaTK());
            ps.setString(2, tk.getTenTK());
            // !!! QUAN TRỌNG: Nên mã hóa mật khẩu trước khi lưu vào DB !!!
            // Ví dụ: ps.setString(3, maHoaMatKhau(tk.getMatKhau()));
            ps.setString(3, tk.getMatKhau());
            ps.setString(4, tk.getQuyenHan()); // Cho phép null hoặc giá trị quyền
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm ĐĂNG KÝ tài khoản mới (quyền hạn mặc định là NULL)
    public boolean registerTaiKhoan(TaiKhoan tk) {
         // Kiểm tra tên TK đã tồn tại chưa
        if (getTaiKhoanByTenTK(tk.getTenTK()) != null) {
            System.err.println("Lỗi: Tên tài khoản '" + tk.getTenTK() + "' đã tồn tại!");
            return false;
        }

        String sql = "INSERT INTO TaiKhoan(maTK, tenTK, matKhau, quyenHan) VALUES (?, ?, ?, NULL)"; // Quyền hạn là NULL
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tk.getMaTK()); // Mã TK nên được tạo bằng generateNewMaTK() trước khi gọi hàm này
            ps.setString(2, tk.getTenTK());
            // !!! QUAN TRỌNG: Nên mã hóa mật khẩu trước khi lưu vào DB !!!
            ps.setString(3, tk.getMatKhau());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm kiểm tra đăng nhập
    public TaiKhoan checkLogin(String tenTK, String matKhau) {
        TaiKhoan tk = null;
        String sql = "SELECT maTK, tenTK, matKhau, quyenHan FROM TaiKhoan WHERE tenTK = ? AND matKhau = ?";
        // !!! QUAN TRỌNG: Query này chỉ đúng nếu mật khẩu KHÔNG được mã hóa.
        // Nếu đã mã hóa, cần lấy mật khẩu hash từ DB rồi so sánh bằng thư viện mã hóa.
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenTK);
            ps.setString(2, matKhau); // So sánh mật khẩu rõ -> KHÔNG AN TOÀN

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tk = new TaiKhoan(
                        rs.getString("maTK"),
                        rs.getString("tenTK"),
                        "********", // Không trả về mật khẩu thật
                        rs.getString("quyenHan")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk;
    }

    // Hàm lấy tài khoản theo tên TK (kiểm tra tồn tại)
    public TaiKhoan getTaiKhoanByTenTK(String tenTK) {
        TaiKhoan tk = null;
        String sql = "SELECT maTK, tenTK, quyenHan FROM TaiKhoan WHERE tenTK = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenTK);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tk = new TaiKhoan(); // Chỉ cần tạo đối tượng để biết là tồn tại
                    tk.setMaTK(rs.getString("maTK"));
                    tk.setTenTK(rs.getString("tenTK"));
                    tk.setQuyenHan(rs.getString("quyenHan"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk; // Trả về null nếu không tìm thấy
    }

    // Hàm cập nhật quyền hạn cho tài khoản
    public boolean updateQuyenHan(String maTK, String quyenHan) {
        String sql = "UPDATE TaiKhoan SET quyenHan = ? WHERE maTK = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Nếu quyenHan là chuỗi rỗng hoặc "(Chưa cấp)", set là NULL trong DB
            if (quyenHan == null || quyenHan.trim().isEmpty() || quyenHan.equals("(Chưa cấp)")) {
                 ps.setNull(1, java.sql.Types.NVARCHAR); // Set NULL
            } else {
                 ps.setString(1, quyenHan);
            }
            ps.setString(2, maTK);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // (Optional: Hàm xóa tài khoản)
    public boolean deleteTaiKhoan(String maTK) {
        String sql = "DELETE FROM TaiKhoan WHERE maTK = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maTK);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // Bắt lỗi khóa ngoại nếu tài khoản đang được sử dụng bởi nhân viên
            if (e.getMessage().contains("The DELETE statement conflicted with the REFERENCE constraint")) {
                System.err.println("Lỗi: Không thể xóa tài khoản '" + maTK + "' vì đang được sử dụng bởi một nhân viên.");
            } else {
                e.printStackTrace();
            }
        }
        return false;
    }

    // (Optional: Hàm đổi mật khẩu)
    public boolean changePassword(String maTK, String newPassword) {
         String sql = "UPDATE TaiKhoan SET matKhau = ? WHERE maTK = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             // !!! QUAN TRỌNG: Nên mã hóa newPassword trước khi lưu !!!
             ps.setString(1, newPassword);
             ps.setString(2, maTK);
             return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

 // Trong class taiKhoan_DAO

    // Hàm THÊM tài khoản mới (dùng cho Admin, có thể set quyền) - ĐÃ SỬA
    public boolean addTaiKhoan(TaiKhoan tk) {
        // Kiểm tra mã TK đã tồn tại chưa (QUAN TRỌNG)
        if (getTaiKhoanByMaTK(tk.getMaTK()) != null) { // Thêm hàm getTaiKhoanByMaTK ở dưới
             System.err.println("Lỗi: Mã tài khoản '" + tk.getMaTK() + "' đã tồn tại!");
             JOptionPane.showMessageDialog(null, "Mã tài khoản '" + tk.getMaTK() + "' đã tồn tại!", "Lỗi Trùng Mã", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Kiểm tra tên TK đã tồn tại chưa
        if (getTaiKhoanByTenTK(tk.getTenTK()) != null) {
            System.err.println("Lỗi: Tên tài khoản '" + tk.getTenTK() + "' đã tồn tại!");
             JOptionPane.showMessageDialog(null, "Tên tài khoản '" + tk.getTenTK() + "' đã tồn tại!", "Lỗi Trùng Tên", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String sql = "INSERT INTO TaiKhoan(maTK, tenTK, matKhau, quyenHan) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tk.getMaTK()); // Lấy mã TK từ đối tượng tk (do người dùng nhập)
            ps.setString(2, tk.getTenTK());
            ps.setString(3, tk.getMatKhau()); // !!! Nên mã hóa
            // Cho phép admin set quyền (có thể null nếu ComboBox cho phép)
            if (tk.getQuyenHan() == null || tk.getQuyenHan().trim().isEmpty() || tk.getQuyenHan().equals("(Chưa cấp)")) {
                 ps.setNull(4, java.sql.Types.NVARCHAR);
            } else {
                 ps.setString(4, tk.getQuyenHan());
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm lấy tài khoản theo Mã TK (Bổ sung để kiểm tra trùng mã)
    public TaiKhoan getTaiKhoanByMaTK(String maTK) {
        TaiKhoan tk = null;
        String sql = "SELECT maTK, tenTK, quyenHan FROM TaiKhoan WHERE maTK = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maTK);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tk = new TaiKhoan();
                    tk.setMaTK(rs.getString("maTK"));
                    tk.setTenTK(rs.getString("tenTK"));
                    tk.setQuyenHan(rs.getString("quyenHan"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk; // Trả về null nếu không tìm thấy
    }


}