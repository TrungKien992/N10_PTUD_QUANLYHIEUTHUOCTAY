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
    
 // Trong class chucVu_DAO

    /**
     * Tìm kiếm và trả về đối tượng ChucVu dựa vào tên chức vụ.
     * @param tenCV Tên chức vụ cần tìm (ví dụ: "Quản lý").
     * @return Đối tượng ChucVu tương ứng hoặc null nếu không tìm thấy.
     */
    public ChucVu getChucVuByTen(String tenCV) {
        ChucVu cv = null;
        String sql = "SELECT maChucVu, tenChucVu, moTa FROM ChucVu WHERE tenChucVu = ?";
        // Dùng try-with-resources
        try (Connection con = ConnectDB.getConnection(); // Lấy connection mới
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenCV); // Đặt tham số tên chức vụ

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // Nếu tìm thấy
                    cv = new ChucVu(
                        rs.getString("maChucVu"),
                        rs.getString("tenChucVu"),
                        rs.getString("moTa")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console
        }
        return cv; // Trả về ChucVu hoặc null
    }

}