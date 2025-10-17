package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Thuoc;

public class chiTietHoaDon_DAO {
    
    public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMaHD(String maHD) {
        ArrayList<ChiTietHoaDon> dsChiTiet = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietHoaDon WHERE maHD = ?";
        
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, maHD);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int soLuong = rs.getInt("soLuong");
                    String maThuoc = rs.getString("maThuoc");
                    
                    thuoc_DAO thuoc_dao = new thuoc_DAO();
                    Thuoc thuoc = thuoc_dao.getThuocTheoMa(maThuoc);
                    
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(maHD);
                    
                    ChiTietHoaDon cthd = new ChiTietHoaDon(hd, thuoc, soLuong);
                    dsChiTiet.add(cthd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTiet;
    }

    /**
     * Thêm một chi tiết hóa đơn vào CSDL.
     */
    public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
        String sql = "INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong) VALUES (?, ?, ?)";
        
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, cthd.getHoaDon().getMaHD());
            stmt.setString(2, cthd.getThuoc().getMaThuoc());
            stmt.setInt(3, cthd.getSoLuong());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}