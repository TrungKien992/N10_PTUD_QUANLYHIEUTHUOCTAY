package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.Thue;

public class hoaDon_DAO {

    /**
     * Lấy tất cả hóa đơn từ cơ sở dữ liệu
     */
    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        
        // Cấu trúc try-with-resources sẽ tự động đóng Connection khi kết thúc
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("maHD"));
                hd.setNgayLap(rs.getDate("ngayLap").toLocalDate());

                // Lấy thông tin các đối tượng liên quan
                khachHang_DAO kh_dao = new khachHang_DAO();
                KhachHang kh = kh_dao.getKhachHangTheoMa(rs.getString("maKH"));
                hd.setKhachHang(kh);
                
                nhanVien_DAO nv_dao = new nhanVien_DAO();
                // Giả sử có hàm getNhanVienTheoMa trong nhanVien_DAO, nếu chưa có anh cần bổ sung
                NhanVien nv = nv_dao.getNhanVienTheoMa(rs.getString("maNV")); 
                hd.setNhanVien(nv);
                
                hd.setThue(new Thue(rs.getString("maThue")));
                hd.setKhuyenMai(new KhuyenMai(rs.getString("maKM")));

                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }
    
    /**
     * Thêm một hóa đơn mới vào CSDL.
     */
    public boolean themHoaDon(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (maHD, ngayLap, maKM, maThue, maNV, maKH) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, hd.getMaHD());
            stmt.setDate(2, Date.valueOf(hd.getNgayLap()));
            
            if (hd.getKhuyenMai() != null && hd.getKhuyenMai().getMaKM() != null) {
                stmt.setString(3, hd.getKhuyenMai().getMaKM());
            } else {
                stmt.setNull(3, java.sql.Types.VARCHAR);
            }
            
            stmt.setString(4, hd.getThue().getMaThue());
            stmt.setString(5, hd.getNhanVien().getMaNV());
            
            if (hd.getKhachHang() != null && hd.getKhachHang().getMaKH() != null) {
                stmt.setString(6, hd.getKhachHang().getMaKH());
            } else {
                stmt.setNull(6, java.sql.Types.VARCHAR);
            }
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Tự động tạo mã hóa đơn mới
     */
    public String generateNewMaHD() {
        String maHD = "HD001";
        String sql = "SELECT MAX(maHD) FROM HoaDon";
        
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                String maxMaHD = rs.getString(1);
                if (maxMaHD != null) {
                    int num = Integer.parseInt(maxMaHD.substring(2)) + 1;
                    maHD = String.format("HD%03d", num);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maHD;
    }
}