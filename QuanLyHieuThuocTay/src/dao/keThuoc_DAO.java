package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KeThuoc;

public class keThuoc_DAO {
    
    /**
     * Lấy danh sách tất cả các kệ thuốc
     */
    public List<KeThuoc> getAllKeThuoc() {
        List<KeThuoc> dsKe = new ArrayList<>();
        String sql = "SELECT * FROM KeThuoc";
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while(rs.next()) {
                KeThuoc ke = new KeThuoc(
                    rs.getString("maKe"),
                    rs.getInt("viTri"),
                    rs.getString("loaiKe")
                );
                dsKe.add(ke);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKe;
    }
    
    public KeThuoc getKeThuocTheoTen(String tenKe) {
        String sql = "SELECT * FROM KeThuoc WHERE loaiKe = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenKe);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new KeThuoc(
                    rs.getString("maKe"),
                    rs.getInt("viTri"),
                    rs.getString("loaiKe")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String generateNewMaKe() {
        String prefix = "K";
        String sql = "SELECT TOP 1 maKe FROM KeThuoc ORDER BY maKe DESC";
        try (Connection con = ConnectDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String last = rs.getString("maKe");
                int num = Integer.parseInt(last.replace(prefix, ""));
                return String.format("%s%03d", prefix, num + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "K001";
    }

}