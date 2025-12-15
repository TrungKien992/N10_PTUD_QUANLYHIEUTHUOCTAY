package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KeThuoc;
import entity.NhaCungCap;
import entity.Thuoc;

public class thuoc_DAO {
	
	// Hằng số cho trạng thái
    private static final String TRANG_THAI_KINH_DOANH = "Đang kinh doanh";
    private static final String TRANG_THAI_NGUNG_KINH_DOANH = "Ngừng kinh doanh";
    
    /**
     * Lấy thông tin thuốc theo mã (Lấy cả trạng thái)
     */
    public Thuoc getThuocTheoMa(String maThuoc) {
        String sql = "SELECT t.*, k.loaiKe, ncc.tenNhaCungCap FROM Thuoc t " +
                     "JOIN KeThuoc k ON t.maKe = k.maKe " +
                     "JOIN NhaCungCap ncc ON t.maNhaCungCap = ncc.maNhaCungCap " +
                     "WHERE t.maThuoc = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, maThuoc);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToThuoc(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lấy danh sách tất cả các loại thuốc (Tất cả trạng thái)
     */
    public List<Thuoc> getAllThuoc() {
        List<Thuoc> dsThuoc = new ArrayList<>();
        // Cập nhật câu SQL để lấy trangThai
        String sql = "SELECT t.*, k.loaiKe, ncc.tenNhaCungCap FROM Thuoc t " +
                     "JOIN KeThuoc k ON t.maKe = k.maKe " +
                     "JOIN NhaCungCap ncc ON t.maNhaCungCap = ncc.maNhaCungCap";
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                dsThuoc.add(mapRowToThuoc(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsThuoc;
    }
    
    /**
     * Thêm một loại thuốc mới (Cập nhật: Thêm cột trangThai)
     */
    /**
     * Thêm một loại thuốc mới (Cập nhật: Thêm cột trangThai và logic COMMIT)
     */
    public boolean themThuoc(Thuoc t) {
        String sql = "INSERT INTO Thuoc(maThuoc, tenThuoc, giaNhap, giaBan, soLuong, hanSuDung, thanhPhan, donViTinh, anh, maKe, maNhaCungCap, trangThai) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // 12 tham số
        
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            con = ConnectDB.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, t.getMaThuoc());
            ps.setString(2, t.getTenThuoc());
            ps.setDouble(3, t.getGiaNhap());
            ps.setDouble(4, t.getGiaBan());
            ps.setInt(5, t.getSoLuong());
            ps.setDate(6, Date.valueOf(t.getHanSuDung()));
            ps.setString(7, t.getThanhPhan());
            ps.setString(8, t.getDonViTinh());
            ps.setString(9, t.getAnh());
            ps.setString(10, t.getKeThuoc().getMaKe());
            ps.setString(11, t.getNhaCungCap().getMaNhaCungCap());
            ps.setString(12, t.getTrangThai() != null ? t.getTrangThai() : TRANG_THAI_KINH_DOANH);
            
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                result = true;
                
                // === BỔ SUNG LOGIC COMMIT ===
                // Nếu kết nối không ở chế độ Auto-Commit, phải tự gọi commit()
                if (!con.getAutoCommit()) {
                    con.commit();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
            // Nếu có lỗi SQL và không ở chế độ Auto-Commit, phải gọi rollback
            try {
                if (con != null && !con.getAutoCommit()) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // Đóng tài nguyên
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Cập nhật thông tin thuốc (Cập nhật: Thêm cột trangThai)
     */
    public boolean updateThuoc(Thuoc t) {
        String sql = "UPDATE Thuoc SET tenThuoc = ?, giaNhap = ?, giaBan = ?, soLuong = ?, hanSuDung = ?, " +
                     "thanhPhan = ?, donViTinh = ?, anh = ?, maKe = ?, maNhaCungCap = ?, trangThai = ? WHERE maThuoc = ?"; // Thêm trangThai
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, t.getTenThuoc());
            ps.setDouble(2, t.getGiaNhap());
            ps.setDouble(3, t.getGiaBan());
            ps.setInt(4, t.getSoLuong());
            ps.setDate(5, Date.valueOf(t.getHanSuDung()));
            ps.setString(6, t.getThanhPhan());
            ps.setString(7, t.getDonViTinh());
            ps.setString(8, t.getAnh());
            ps.setString(9, t.getKeThuoc().getMaKe());
            ps.setString(10, t.getNhaCungCap().getMaNhaCungCap());
            ps.setString(11, t.getTrangThai()); // CẬP NHẬT TRẠNG THÁI
            ps.setString(12, t.getMaThuoc());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Hàm helper để map một dòng từ ResultSet thành đối tượng Thuoc
     */
    private Thuoc mapRowToThuoc(ResultSet rs) throws SQLException {
        Thuoc t = new Thuoc();
        t.setMaThuoc(rs.getString("maThuoc"));
        t.setTenThuoc(rs.getString("tenThuoc"));
        t.setGiaNhap(rs.getDouble("giaNhap"));
        t.setGiaBan(rs.getDouble("giaBan"));
        t.setSoLuong(rs.getInt("soLuong"));
        t.setHanSuDung(rs.getDate("hanSuDung").toLocalDate());
        t.setThanhPhan(rs.getString("thanhPhan"));
        t.setDonViTinh(rs.getString("donViTinh"));
        t.setAnh(rs.getString("anh"));
        t.setTrangThai(rs.getString("trangThai")); // THÊM TRẠNG THÁI
        
        KeThuoc ke = new KeThuoc();
        ke.setMaKe(rs.getString("maKe"));
        ke.setLoaiKe(rs.getString("loaiKe"));
        t.setKeThuoc(ke);
        
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNhaCungCap(rs.getString("maNhaCungCap"));
        ncc.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
        t.setNhaCungCap(ncc);
        
        return t;
    }
    /**
     * Lấy mã nhà cung cấp theo tên nhà cung cấp
     * Dùng khi combobox chỉ chứa tên NCC
     */
    public String getMaNCCTheoTen(String tenNCC) {
        String ma = null;
        String sql = "SELECT maNhaCungCap FROM NhaCungCap WHERE tenNhaCungCap = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenNCC);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ma = rs.getString("maNhaCungCap");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ma;
    }
    
    public String getNextMaThuoc() {
        String nextMa = "T01";
        String sql = "SELECT MAX(maThuoc) FROM Thuoc";
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                String lastMa = rs.getString(1);
                if (lastMa != null && lastMa.startsWith("T")) {
                    int number = Integer.parseInt(lastMa.substring(1)); // bỏ chữ T
                    number++;
                    nextMa = String.format("T%02d", number); // định dạng T01, T02,...
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextMa;
    }

 // trong thuoc_DAO.java
    public String getMaKeTheoTen(String tenKe) {
        String maKe = null;
        String sql = "SELECT MaKe FROM KeThuoc WHERE LoaiKe = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenKe);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maKe = rs.getString("MaKe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maKe;
    }
    
 // === Cập nhật số lượng thuốc sau khi bán (Thêm vào thuoc_DAO.java) ===
    public boolean updateSoLuongSauKhiBan(String maThuoc, int soLuongBan) {
        // Lấy số lượng tồn kho hiện tại
        Thuoc thuoc = getThuocTheoMa(maThuoc);
        if (thuoc == null) {
            System.err.println("Lỗi: Không tìm thấy thuốc " + maThuoc + " để cập nhật số lượng.");
            return false;
        }
        
        int soLuongMoi = thuoc.getSoLuong() - soLuongBan;
        if (soLuongMoi < 0) {
            System.err.println("Lỗi: Số lượng bán vượt quá tồn kho (Logic Controller bị sai).");
            return false; // Không cho phép số lượng âm
        }

        String sql = "UPDATE Thuoc SET soLuong = ? WHERE maThuoc = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, soLuongMoi);
            ps.setString(2, maThuoc);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // === BỔ SUNG HÀM LẤY TÊN THUỐC (REQ 1) ===
    public List<String> getAllTenThuoc() {
         List<String> dsTen = new ArrayList<>();
        String sql = "SELECT DISTINCT tenThuoc FROM Thuoc ORDER BY tenThuoc";
         try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                dsTen.add(rs.getString("tenThuoc"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return dsTen;
    }
    
// (Thêm vào thuoc_DAO.java)
    
    /**
     * HÀM NÀY DÙNG CHO BÁN HÀNG (TRONG CONTROLLER HÓA ĐƠN)
     *
     * - Lọc thuốc hết hạn (hanSuDung > GETDATE())
     * - Lọc thuốc hết hàng (soLuong > 0)
     * - Sắp xếp theo số lượng (thấp -> cao)
     * - Sắp xếp theo HSD (gần -> xa)
     */
 // === HÀM TÌM KIẾM CHO BÁN HÀNG (Sửa để chỉ tìm thuốc active) ===
    public List<Thuoc> searchThuoc(String maThuoc, String tenThuoc, String loaiKe) {
        List<Thuoc> dsThuoc = new ArrayList<>();
        
        StringBuilder sqlBuilder = new StringBuilder(
            "SELECT t.*, k.loaiKe, ncc.tenNhaCungCap FROM Thuoc t " +
            "LEFT JOIN KeThuoc k ON t.maKe = k.maKe " +
            "LEFT JOIN NhaCungCap ncc ON t.maNhaCungCap = ncc.maNhaCungCap " +
            "WHERE t.maThuoc LIKE ? " +
            "AND t.tenThuoc LIKE ? " +
            "AND t.trangThai = N'" + TRANG_THAI_KINH_DOANH + "'" // CHỈ TÌM THUỐC ĐANG KINH DOANH
        );
        
        List<Object> params = new ArrayList<>();
        params.add("%" + maThuoc + "%");
        params.add("%" + tenThuoc + "%");

        if (loaiKe != null && !loaiKe.isEmpty() && !"Tất cả".equals(loaiKe)) {
            sqlBuilder.append(" AND k.loaiKe = ? ");
            params.add(loaiKe);
        }
        
        sqlBuilder.append(" AND t.hanSuDung > GETDATE() ");
        sqlBuilder.append(" AND t.soLuong > 0 ");

        sqlBuilder.append(" ORDER BY t.soLuong ASC, t.hanSuDung ASC ");
        
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    dsThuoc.add(mapRowToThuoc(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsThuoc;
    }
    
    /**
     * HÀM NÀY DÙNG CHO TAB QUẢN LÝ THUỐC (Sửa để chỉ tìm thuốc active)
     */
    public List<Thuoc> searchThuocForQuanLy(String maThuoc, String tenThuoc, String loaiKe) {
        List<Thuoc> dsThuoc = new ArrayList<>();
        
        StringBuilder sqlBuilder = new StringBuilder(
            "SELECT t.*, k.loaiKe, ncc.tenNhaCungCap FROM Thuoc t " +
            "LEFT JOIN KeThuoc k ON t.maKe = k.maKe " +
            "LEFT JOIN NhaCungCap ncc ON t.maNhaCungCap = ncc.maNhaCungCap " +
            "WHERE t.maThuoc LIKE ? " +
            "AND t.tenThuoc LIKE ? " +
            "AND t.trangThai = N'" + TRANG_THAI_KINH_DOANH + "'" // CHỈ TÌM THUỐC ĐANG KINH DOANH
        );
        
        List<Object> params = new ArrayList<>();
        params.add("%" + maThuoc + "%");
        params.add("%" + tenThuoc + "%");

        if (loaiKe != null && !loaiKe.isEmpty() && !"Tất cả".equals(loaiKe)) {
            sqlBuilder.append(" AND k.loaiKe = ? ");
            params.add(loaiKe);
        }
        
        sqlBuilder.append(" ORDER BY t.tenThuoc ASC ");

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    dsThuoc.add(mapRowToThuoc(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsThuoc;
    }
    
    /**
     * BỔ SUNG: Lấy danh sách thuốc đang hoạt động (trangThai = 'Đang kinh doanh')
     */
    public List<Thuoc> getAllActiveThuoc() {
        List<Thuoc> dsThuoc = new ArrayList<>();
        // Lấy thuốc đang kinh doanh
        String sql = "SELECT t.*, k.loaiKe, ncc.tenNhaCungCap FROM Thuoc t " +
                     "JOIN KeThuoc k ON t.maKe = k.maKe " +
                     "JOIN NhaCungCap ncc ON t.maNhaCungCap = ncc.maNhaCungCap " +
                     "WHERE t.trangThai = N'" + TRANG_THAI_KINH_DOANH + "'";
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                dsThuoc.add(mapRowToThuoc(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsThuoc;
    }
    
    /**
     * Cập nhật trạng thái của thuốc (thực hiện chức năng xóa mềm)
     */
    public boolean updateTrangThai(String maThuoc, String newTrangThai) {
        String sql = "UPDATE Thuoc SET trangThai = ? WHERE maThuoc = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newTrangThai);
            ps.setString(2, maThuoc);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}