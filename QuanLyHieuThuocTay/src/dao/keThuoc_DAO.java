package dao;

import java.sql.Connection;
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
}