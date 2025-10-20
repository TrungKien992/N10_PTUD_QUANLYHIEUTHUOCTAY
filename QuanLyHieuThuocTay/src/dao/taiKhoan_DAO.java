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
    public String generateNewMaTK() {
        String newMa = "TK01";
        String sql = "SELECT TOP 1 maTK FROM TaiKhoan ORDER BY maTK DESC";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String lastMa = rs.getString("maTK");
                int number = Integer.parseInt(lastMa.replaceAll("[^0-9]", "")) + 1;
                newMa = String.format("TK%02d", number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newMa;
    }
    public boolean insertTaiKhoan(TaiKhoan tk) {
        String sql = "INSERT INTO TaiKhoan(maTK, tenTK, matKhau, quyenHan) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tk.getMaTK());
            ps.setString(2, tk.getTenTK());
            ps.setString(3, tk.getMatKhau());
            ps.setString(4, tk.getQuyenHan());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}