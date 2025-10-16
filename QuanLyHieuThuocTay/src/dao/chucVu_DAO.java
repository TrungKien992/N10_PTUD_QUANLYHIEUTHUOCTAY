package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connectDB.ConnectDB;
import entity.ChucVu;

public class chucVu_DAO {

    public List<ChucVu> getAllChucVu() {
        List<ChucVu> dsChucVu = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Lấy kết nối chung đang hoạt động
            con = ConnectDB.getConnection();
            String sql = "SELECT maChucVu, tenChucVu, moTa FROM ChucVu";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ChucVu cv = new ChucVu(
                    rs.getString("maChucVu"),
                    rs.getString("tenChucVu"),
                    rs.getString("moTa")
                );
                dsChucVu.add(cv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Chỉ đóng PreparedStatement và ResultSet, KHÔNG đóng Connection
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return dsChucVu;
    }
}