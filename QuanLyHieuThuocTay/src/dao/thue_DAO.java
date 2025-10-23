package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectDB.ConnectDB;
import entity.Thue; // Import entity Thue

public class thue_DAO {
    
    // Hàm lấy Thuế theo mã
    public Thue getThueByMa(String maThue) {
        Thue thue = null;
        String sql = "SELECT * FROM Thue WHERE maThue = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, maThue);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    thue = new Thue(); // Cần có constructor rỗng
                    thue.setMaThue(rs.getString("maThue"));
                    thue.setTiLe(rs.getDouble("tiLe"));
                    thue.setMoTa(rs.getString("moTa"));
                    thue.setNgayApDung(rs.getDate("ngayApDung").toLocalDate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thue;
    }
}