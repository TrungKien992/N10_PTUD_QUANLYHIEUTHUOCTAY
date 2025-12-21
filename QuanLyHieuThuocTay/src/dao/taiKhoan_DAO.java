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
        // Query phải lấy đủ cột, đặc biệt là trangThai
        String sql = "SELECT maTK, tenTK, matKhau, quyenHan, trangThai FROM TaiKhoan";
        
        try (Connection con = ConnectDB.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(
                    rs.getString("maTK"),
                    rs.getString("tenTK"),
                    "********", // Ở bảng danh sách vẫn nên ẩn password
                    rs.getString("quyenHan"),
                    rs.getBoolean("trangThai")
                );
                dsTaiKhoan.add(tk);
            }
        } catch (SQLException e) { e.printStackTrace(); }
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
                        newMaTK = String.format("TK%03d", num); // Format thành TKxx
                    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                        System.err.println("Lỗi khi parse mã TK lớn nhất: " + maxMaTK + ". Sử dụng mã mặc định: " + newMaTK);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newMaTK;
    }

 // Hàm sinh mã tài khoản mới nhất để tránh trùng khi lưu
    public String generateNewMaTK_ForAutoCreate() {
        String newMaTK = "TK001";
        String sql = "SELECT MAX(maTK) FROM TaiKhoan";
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String maxMaTK = rs.getString(1);
                if (maxMaTK != null && maxMaTK.startsWith("TK")) {
                    int num = Integer.parseInt(maxMaTK.substring(2)) + 1;
                    newMaTK = String.format("TK%03d", num);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newMaTK;
    }

    // Hàm kiểm tra đăng nhập
    public TaiKhoan checkLogin(String tenTK, String matKhau) {
        TaiKhoan tk = null;
        // 1. Cập nhật SQL: Thêm cột trangThai
        String sql = "SELECT maTK, tenTK, matKhau, quyenHan, trangThai FROM TaiKhoan WHERE tenTK = ? AND matKhau = ?";
        
        // !!! QUAN TRỌNG: Logic so sánh mật khẩu rõ (plain text) không an toàn, nên dùng hash (BCrypt/MD5) trong thực tế.
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenTK);
            ps.setString(2, matKhau); 

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // 2. Lấy trạng thái từ DB
                    boolean trangThai = rs.getBoolean("trangThai");

                    // 3. Khởi tạo với Constructor 5 tham số (Bổ sung tham số cuối)
                    tk = new TaiKhoan(
                        rs.getString("maTK"),
                        rs.getString("tenTK"),
                        "********", // Không trả về mật khẩu thật
                        rs.getString("quyenHan"),
                        trangThai   // Thêm tham số này để hết lỗi
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk;
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
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            // Sử dụng Soft Delete (Cập nhật trạng thái = 0) thay vì xóa vĩnh viễn
            String sql = "UPDATE TaiKhoan SET trangThai = 0 WHERE maTK = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maTK);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }


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

    public TaiKhoan getTaiKhoanByMaTK(String maTK) {
        TaiKhoan tk = null;
        // SỬA 1: Thêm cột trangThai vào câu SQL
        String sql = "SELECT maTK, tenTK, quyenHan, trangThai FROM TaiKhoan WHERE maTK = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maTK);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tk = new TaiKhoan();
                    tk.setMaTK(rs.getString("maTK"));
                    tk.setTenTK(rs.getString("tenTK"));
                    tk.setQuyenHan(rs.getString("quyenHan"));
                    // SỬA 2: Lấy dữ liệu trạng thái gán vào object
                    tk.setTrangThai(rs.getBoolean("trangThai"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk; 
    }
    public boolean create(TaiKhoan tk) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "INSERT INTO TaiKhoan(maTK, tenTK, matKhau, quyenHan, trangThai) VALUES(?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, tk.getMaTK());
            stmt.setString(2, tk.getTenTK());
            stmt.setString(3, tk.getMatKhau());
            stmt.setString(4, tk.getQuyenHan());
            stmt.setBoolean(5, tk.isTrangThai()); 

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
 // File: taiKhoan_DAO.java

    public boolean update(TaiKhoan tk) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "UPDATE TaiKhoan SET tenTK = ?, matKhau = ?, quyenHan = ?, trangThai = ? WHERE maTK = ?";
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, tk.getTenTK());
            stmt.setString(2, tk.getMatKhau());
            stmt.setString(3, tk.getQuyenHan());
            stmt.setBoolean(4, tk.isTrangThai());
            stmt.setString(5, tk.getMaTK());

            n = stmt.executeUpdate();
            
            if (n > 0) {
                syncTrangThaiNhanVien(tk.getMaTK(), tk.isTrangThai());
            }
            // -------------------------------------

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng resource để tránh leak
            try { if(stmt != null) stmt.close(); } catch (SQLException e) {}
        }
        return n > 0;
    }
    
    public boolean deleteTaiKhoanPermanently(String maTK) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "DELETE FROM TaiKhoan WHERE maTK = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maTK);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return n > 0;
    }
    public void syncTrangThaiNhanVien(String maTK, boolean trangThaiTK) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        try {
            String trangThaiNV = trangThaiTK ? "Còn làm việc" : "Nghỉ việc";
            String sql = "UPDATE NhanVien SET trangThai = ? WHERE maTK = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, trangThaiNV); // Hoặc setBoolean nếu DB để bit
            stmt.setString(2, maTK);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    public TaiKhoan getTaiKhoanByTenTK(String tenTK) {
        TaiKhoan tk = null;
        String sql = "SELECT * FROM TaiKhoan WHERE tenTK = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenTK);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tk = new TaiKhoan(rs.getString("maTK"), rs.getString("tenTK"), "", "", true);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return tk;
    }
    public TaiKhoan getTaiKhoanCanSua(String maTK) {
        TaiKhoan tk = null;
        String sql = "SELECT * FROM TaiKhoan WHERE maTK = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maTK);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tk = new TaiKhoan(
                        rs.getString("maTK"),
                        rs.getString("tenTK"),
                        rs.getString("matKhau"), // Lấy mật khẩu thật (plaintext)
                        rs.getString("quyenHan"),
                        rs.getBoolean("trangThai")
                    );
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return tk; 
    }
    public boolean khoaTaiKhoan(String maTK) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "UPDATE TaiKhoan SET trangThai = 0 WHERE maTK = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maTK);
            n = stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
        return n > 0;
    }
}