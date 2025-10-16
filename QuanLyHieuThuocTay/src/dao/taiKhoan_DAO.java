package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connectDB.ConnectDB;
import entity.TaiKhoan;

public class taiKhoan_DAO {

    public List<TaiKhoan> getAllTaiKhoan() {
        List<TaiKhoan> dsTaiKhoan = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Lấy kết nối chung đang hoạt động
            con = ConnectDB.getConnection();
            String sql = "SELECT maTK, tenTK, matKhau, quyenHan FROM TaiKhoan";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(
                    rs.getString("maTK"),
                    rs.getString("tenTK"),
                    rs.getString("matKhau"),
                    rs.getString("quyenHan")
                );
                dsTaiKhoan.add(tk);
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
        return dsTaiKhoan;
    }
}