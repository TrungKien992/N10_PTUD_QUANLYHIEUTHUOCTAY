package dao;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.NhanVien;
import entity.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class nhanVien_DAO {

    // === Lấy nhân viên theo mã ===
    public NhanVien getNhanVienTheoMa(String maNV) {
        String sql = "SELECT nv.*, cv.tenChucVu, tk.tenTK " +
                     "FROM NhanVien nv " +
                     "JOIN ChucVu cv ON nv.chucVu = cv.maChucVu " +
                     "JOIN TaiKhoan tk ON nv.maTK = tk.maTK " +
                     "WHERE nv.maNV = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, maNV);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapNhanVien(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertNhanVien(NhanVien nv) {
        if (nv.getMaNV() == null || nv.getMaNV().isEmpty()) {
            nv.setMaNV(generateNewMaNV()); // tự động sinh mã nếu chưa có
        }

        // Nếu trạng thái chưa được set thì tự động gán là "Còn làm việc"
        if (nv.getTrangThai() == null || nv.getTrangThai().trim().isEmpty()) {
            nv.setTrangThai("Còn làm việc");
        }

        String sql = "INSERT INTO NhanVien(maNV, tenNV, ngaySinh, gioiTinh, chucVu, sdt, diaChi, anh, maTK, trangThai) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getTenNV());
            ps.setDate(3, java.sql.Date.valueOf(nv.getNgaySinh()));
            ps.setString(4, nv.getGioiTinh());
            ps.setString(5, nv.getChucVu().getMaChucVu());
            ps.setString(6, nv.getSoDienThoai());
            ps.setString(7, nv.getDiaChi());
            ps.setString(8, nv.getAnh());
            ps.setString(9, nv.getTaiKhoan().getMaTK());
            ps.setString(10, nv.getTrangThai()); // 👈 thêm trạng thái

            System.out.println(">> Thêm nhân viên: " + nv.getMaNV() + " - " + nv.getTrangThai());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // === Cập nhật nhân viên ===
    public boolean updateNhanVien(NhanVien nv) {
        String sql = "UPDATE NhanVien SET tenNV=?, ngaySinh=?, gioiTinh=?, chucVu=?, sdt=?, diaChi=?, anh=?, maTK=?, trangThai=? WHERE maNV=?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nv.getTenNV());
            ps.setDate(2, java.sql.Date.valueOf(nv.getNgaySinh()));
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getChucVu().getMaChucVu());
            ps.setString(5, nv.getSoDienThoai());
            ps.setString(6, nv.getDiaChi());
            ps.setString(7, nv.getAnh());
            ps.setString(8, nv.getTaiKhoan().getMaTK());
            ps.setString(9, nv.getTrangThai());
            ps.setString(10, nv.getMaNV());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

 // Hàm hỗ trợ lấy ChucVu bằng tên (Dùng riêng cho việc đọc dữ liệu từ JTable)

 // === XÓA MỀM NHÂN VIÊN VÀ KHÓA TÀI KHOẢN ĐỒNG THỜI ===
    public boolean deleteNhanVien(String maNV) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            // 1. Lấy mã tài khoản của nhân viên này trước
            String maTK = null;
            String sqlGetTK = "SELECT maTK FROM NhanVien WHERE maNV = ?";
            PreparedStatement psGet = con.prepareStatement(sqlGetTK);
            psGet.setString(1, maNV);
            ResultSet rs = psGet.executeQuery();
            if(rs.next()) {
                maTK = rs.getString("maTK");
            }

            // 2. Cập nhật trạng thái nhân viên thành "Nghỉ việc"
            String sql = "UPDATE NhanVien SET trangThai = ? WHERE maNV = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "Nghỉ việc");
            stmt.setString(2, maNV);
            n = stmt.executeUpdate();

            // 3. ĐỒNG BỘ: Nếu xóa NV thành công và có tài khoản -> Khóa luôn tài khoản
            if (n > 0 && maTK != null) {
                taiKhoan_DAO tkDAO = new taiKhoan_DAO();
                // Gọi hàm khóa tài khoản (set trangThai = 0) bên DAO tài khoản
                if (tkDAO.khoaTaiKhoan(maTK)) {
                    System.out.println(">> Đã khóa tài khoản " + maTK + " theo nhân viên " + maNV);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng resource nếu cần thiết
            try { if(stmt != null) stmt.close(); } catch (SQLException e) {}
        }
        return n > 0;
    }


    // === Lấy danh sách tất cả nhân viên đang còn làm việc ===
 // Trong file nhanVien_DAO.java
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        // Sửa câu SQL: Bỏ đoạn "WHERE nv.trangThai = N'Còn làm việc'"
        String sql = "SELECT nv.*, cv.tenChucVu, tk.tenTK " +
                     "FROM NhanVien nv " +
                     "JOIN ChucVu cv ON nv.chucVu = cv.maChucVu " +
                     "JOIN TaiKhoan tk ON nv.maTK = tk.maTK"; 
                     
        try (Connection con = ConnectDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapNhanVien(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // === Tìm kiếm nhân viên ===
    public List<NhanVien> searchNhanVien(String tenNV, String sdt, String gioiTinh, String maChucVu, String diaChi) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT nv.*, cv.tenChucVu, tk.tenTK " +
                     "FROM NhanVien nv " +
                     "JOIN ChucVu cv ON nv.chucVu = cv.maChucVu " +
                     "JOIN TaiKhoan tk ON nv.maTK = tk.maTK " +
                     "WHERE nv.tenNV LIKE ? AND nv.sdt LIKE ? AND nv.gioiTinh LIKE ? AND nv.chucVu LIKE ? AND nv.diaChi LIKE ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + tenNV + "%");
            ps.setString(2, "%" + sdt + "%");
            ps.setString(3, "%" + gioiTinh + "%");
            ps.setString(4, "%" + maChucVu + "%");
            ps.setString(5, "%" + diaChi + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapNhanVien(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // === Ánh xạ ResultSet → NhanVien ===
    private NhanVien mapNhanVien(ResultSet rs) throws SQLException {
        NhanVien nv = new NhanVien();
        nv.setMaNV(rs.getString("maNV"));
        nv.setTenNV(rs.getString("tenNV"));
        nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
        nv.setGioiTinh(rs.getString("gioiTinh"));
        nv.setSoDienThoai(rs.getString("sdt"));
        nv.setDiaChi(rs.getString("diaChi"));
        nv.setAnh(rs.getString("anh"));
        nv.setTrangThai(rs.getString("trangThai")); // 👈 đọc từ DB

        ChucVu cv = new ChucVu();
        cv.setMaChucVu(rs.getString("chucVu"));
        cv.setTenChucVu(rs.getString("tenChucVu"));
        nv.setChucVu(cv);

        TaiKhoan tk = new TaiKhoan();
        tk.setMaTK(rs.getString("maTK"));
        tk.setTenTK(rs.getString("tenTK"));
        nv.setTaiKhoan(tk);

        return nv;
    }

    // === Sinh mã nhân viên tự động ===
    public String generateNewMaNV() {
        String prefix = "NV";
        String sql = "SELECT TOP 1 maNV FROM NhanVien ORDER BY maNV DESC";
        try (Connection con = ConnectDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String lastID = rs.getString("maNV");
                int number = Integer.parseInt(lastID.replace(prefix, ""));
                number++;
                return String.format("%s%03d", prefix, number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prefix + "001";
    }
    
 // File: dao/nhanVien_DAO.java

 // Thay thế toàn bộ hàm cũ bằng hàm này
    public String generateNewMaNV_FromTable(JTable table, List<NhanVien> tempListNV) {
        int maxNum = 0;
        Set<String> existingMaNV = new HashSet<>();

        // ✅ 1. Lấy toàn bộ mã nhân viên từ database
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT maNV FROM NhanVien")) {

            while (rs.next()) {
                String ma = rs.getString("maNV");
                if (ma != null && ma.startsWith("NV")) {
                    existingMaNV.add(ma);
                    
                    // --- QUAN TRỌNG: Lọc bỏ mã rác (mã dài quá 7 ký tự) ---
                    if (ma.length() > 7) continue; 
                    // -----------------------------------------------------

                    try {
                        int num = Integer.parseInt(ma.substring(2));
                        if (num > maxNum) maxNum = num;
                    } catch (NumberFormatException e) {
                        // Bỏ qua nếu không parse được
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ 2. Lấy mã NV trong JTable (để tránh trùng với dòng đang hiển thị chưa lưu)
        if (table != null && table.getRowCount() > 0) {
            var model = table.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                Object value = model.getValueAt(i, 0);
                if (value != null) {
                    String ma = value.toString().trim();
                    if (ma.startsWith("NV")) {
                        existingMaNV.add(ma);
                        
                        // --- QUAN TRỌNG: Lọc bỏ mã rác trên bảng ---
                        if (ma.length() > 7) continue; 
                        
                        try {
                            int num = Integer.parseInt(ma.substring(2));
                            if (num > maxNum) maxNum = num;
                        } catch (NumberFormatException ignore) {}
                    }
                }
            }
        }

        // ✅ 3. Lấy mã NV trong danh sách tạm tempListNV
        if (tempListNV != null && !tempListNV.isEmpty()) {
            for (NhanVien nv : tempListNV) {
                if (nv.getMaNV() != null && nv.getMaNV().startsWith("NV")) {
                    existingMaNV.add(nv.getMaNV());
                    
                    // --- QUAN TRỌNG: Lọc bỏ mã rác trong list tạm ---
                    if (nv.getMaNV().length() > 7) continue;

                    try {
                        int num = Integer.parseInt(nv.getMaNV().substring(2));
                        if (num > maxNum) maxNum = num;
                    } catch (NumberFormatException ignore) {}
                }
            }
        }

        // ✅ 4. Sinh mã mới chuẩn (NV001, NV002...)
        String newMa;
        do {
            maxNum++;
            newMa = String.format("NV%03d", maxNum);
        } while (existingMaNV.contains(newMa));

        return newMa;
    }

    // === Lấy đường dẫn ảnh theo mã NV ===
    public String layDuongDanAnhTheoMa(String maNV) {
        String duongDanAnh = null;
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT anh FROM NhanVien WHERE maNV = ?")) {
            stmt.setString(1, maNV);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) duongDanAnh = rs.getString("anh");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duongDanAnh;
    }

    // === Lấy tên nhân viên theo mã tài khoản ===
    public String getTenNhanVienByMaTK(String maTK) {
        String tenNV = null;
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT tenNV FROM NhanVien WHERE maTK = ?")) {
            pstmt.setString(1, maTK);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) tenNV = rs.getString("tenNV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenNV;
    }

    // === Lấy nhân viên theo mã tài khoản ===
    public NhanVien getNhanVienByMaTK(String maTK) {
        String sql = "SELECT nv.*, cv.tenChucVu, tk.tenTK " +
                     "FROM NhanVien nv " +
                     "JOIN ChucVu cv ON nv.chucVu = cv.maChucVu " +
                     "JOIN TaiKhoan tk ON nv.maTK = tk.maTK " +
                     "WHERE nv.maTK = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maTK);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapNhanVien(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean checkSdtTonTai(String sdt) {
        boolean exist = false;
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE sdt = ?";
        try (java.sql.Connection con = connectDB.ConnectDB.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, sdt);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Nếu đếm được > 0 tức là đã có người dùng số này
                    exist = rs.getInt(1) > 0;
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }
    
	public boolean create(NhanVien nv) {
		// TODO Auto-generated method stub
		return false;
	}

}
