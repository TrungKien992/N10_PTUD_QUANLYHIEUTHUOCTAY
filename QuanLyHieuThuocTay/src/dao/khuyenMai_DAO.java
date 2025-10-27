package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import connectDB.ConnectDB;
import entity.KhuyenMai;

public class khuyenMai_DAO {

    public List<KhuyenMai> getAllKhuyenMai() {
        List<KhuyenMai> dsKM = new ArrayList<>();
        String sql = "SELECT * FROM KhuyenMai";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                dsKM.add(mapRowToKhuyenMai(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKM;
    }

    public KhuyenMai getKhuyenMaiByMa(String maKM) {
        String sql = "SELECT * FROM KhuyenMai WHERE maKM = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maKM);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToKhuyenMai(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean addKhuyenMai(KhuyenMai km) {
        String sql = "INSERT INTO KhuyenMai (maKM, tenChuongTrinh, giaTri, ngayBatDau, ngayKetThuc, soLuongToiDa, trangThai) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, km.getMaKM());
            ps.setString(2, km.getTenChuongTrinh());
            ps.setDouble(3, km.getGiaTri());
            ps.setDate(4, Date.valueOf(km.getNgayBatDau()));
            ps.setDate(5, Date.valueOf(km.getNgayKetThuc()));
            if (km.getSoLuongToiDa() > 0) {
                 ps.setInt(6, km.getSoLuongToiDa());
            } else {
                 ps.setNull(6, java.sql.Types.INTEGER);
            }
            ps.setInt(7, km.getTrangThai());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateKhuyenMai(KhuyenMai km) {
        String sql = "UPDATE KhuyenMai SET tenChuongTrinh = ?, giaTri = ?, ngayBatDau = ?, ngayKetThuc = ?, " +
                     "soLuongToiDa = ?, trangThai = ? WHERE maKM = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, km.getTenChuongTrinh());
            ps.setDouble(2, km.getGiaTri());
            ps.setDate(3, Date.valueOf(km.getNgayBatDau()));
            ps.setDate(4, Date.valueOf(km.getNgayKetThuc()));
            if (km.getSoLuongToiDa() > 0) {
                 ps.setInt(5, km.getSoLuongToiDa());
            } else {
                 ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setInt(6, km.getTrangThai());
            ps.setString(7, km.getMaKM());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteKhuyenMai(String maKM) {
        String sql = "DELETE FROM KhuyenMai WHERE maKM = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maKM);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private KhuyenMai mapRowToKhuyenMai(ResultSet rs) throws SQLException {
        KhuyenMai km = new KhuyenMai();
        km.setMaKM(rs.getString("maKM"));
        km.setTenChuongTrinh(rs.getString("tenChuongTrinh"));
        km.setGiaTri(rs.getDouble("giaTri"));
        km.setNgayBatDau(rs.getDate("ngayBatDau").toLocalDate());
        km.setNgayKetThuc(rs.getDate("ngayKetThuc").toLocalDate());
        km.setSoLuongToiDa(rs.getInt("soLuongToiDa"));
        km.setTrangThai(rs.getInt("trangThai")); // Sửa thành getInt
        return km;
    }
// (Thêm vào khuyenMai_DAO.java)
    
    public List<KhuyenMai> searchKhuyenMai(String maTen, LocalDate tuNgay, LocalDate denNgay, int trangThai) {
        List<KhuyenMai> dsKM = new ArrayList<>();
        String sql = "SELECT * FROM KhuyenMai WHERE (maKM LIKE ? OR tenChuongTrinh LIKE ?) ";
        
        if (tuNgay != null) {
            sql += " AND ngayBatDau >= ? ";
        }
        if (denNgay != null) {
            sql += " AND ngayKetThuc <= ? ";
        }
        if (trangThai != -1) { 
            sql += " AND trangThai = ? ";
        }

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            int paramIndex = 1;
            ps.setString(paramIndex++, "%" + maTen + "%");
            ps.setString(paramIndex++, "%" + maTen + "%");

            if (tuNgay != null) {
                ps.setDate(paramIndex++, Date.valueOf(tuNgay));
            }
            if (denNgay != null) {
                ps.setDate(paramIndex++, Date.valueOf(denNgay));
            }
            if (trangThai != -1) {
                ps.setInt(paramIndex++, trangThai);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    dsKM.add(mapRowToKhuyenMai(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKM;
    }
 // (Thêm vào cuối file khuyenMai_DAO.java)

    /**
     * Tự động tạo mã Khuyến Mãi mới (VD: KM005 -> KM006)
     * @return String mã KM mới
     */
    public String generateNewMaKM() {
        String newMaKM = "KM001"; // Mã mặc định nếu bảng trống
        // Lưu ý: "SELECT TOP 1" là cú pháp của SQL Server. 
        // Nếu Đại Ca dùng MySQL, hãy đổi thành "SELECT ... LIMIT 1"
        String query = "SELECT TOP 1 MaKM FROM KhuyenMai ORDER BY MaKM DESC";
        
        // Sử dụng cách kết nối giống như các hàm khác trong file của Đại Ca
        try (Connection con = ConnectDB.getConnection(); 
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (rs.next()) {
                String lastMaKM = rs.getString("MaKM");
                try {
                    // Tách phần số (VD: "KM005" -> 5)
                    int lastNum = Integer.parseInt(lastMaKM.substring(2));
                    int newNum = lastNum + 1;
                    // Format lại (VD: 6 -> "KM006")
                    newMaKM = String.format("KM%03d", newNum);
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    System.err.println("Lỗi khi phân tích mã KM cuối cùng: " + lastMaKM);
                    // (Trong trường hợp mã không theo định dạng, ta vẫn trả về KM001)
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // (Xử lý lỗi kết nối DB)
        }
        return newMaKM;
    }
    public KhuyenMai getKhuyenMaiTheoMa(String maKM) {
        String sql = "SELECT * FROM KhuyenMai WHERE maKM = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maKM);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToKhuyenMai(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}