package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietPhieuCho;
import entity.PhieuChoThanhToan;
import entity.Thuoc;

public class ChiTietPhieuCho_DAO {

    // Thêm một chi tiết phiếu chờ
    public boolean themChiTietPhieuCho(ChiTietPhieuCho ctpc) {
        String query = "INSERT INTO ChiTietPhieuCho (maPhieuCho, maThuoc, soLuong, donGia) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, ctpc.getPhieuChoThanhToan().getMaPhieuCho());
            pstmt.setString(2, ctpc.getThuoc().getMaThuoc());
            pstmt.setInt(3, ctpc.getSoLuong());
            pstmt.setDouble(4, ctpc.getDonGia());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy tất cả chi tiết của một phiếu chờ
    public List<ChiTietPhieuCho> getChiTietTheoMaPhieuCho(String maPhieuCho) {
        List<ChiTietPhieuCho> dsChiTiet = new ArrayList<>();
        String query = "SELECT * FROM ChiTietPhieuCho WHERE maPhieuCho = ?";
        
        // DAO để lấy thông tin Thuoc
        thuoc_DAO thuocDAO = new thuoc_DAO(); 
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, maPhieuCho);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String maThuoc = rs.getString("maThuoc");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");

                // Lấy thông tin đầy đủ của thuốc
                Thuoc thuoc = thuocDAO.getThuocTheoMa(maThuoc);
                
                PhieuChoThanhToan pc = new PhieuChoThanhToan(maPhieuCho); // Chỉ cần mã phiếu
                
                ChiTietPhieuCho ctpc = new ChiTietPhieuCho(pc, thuoc, soLuong, donGia);
                dsChiTiet.add(ctpc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTiet;
    }
}