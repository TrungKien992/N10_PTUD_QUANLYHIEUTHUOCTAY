package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietPhieuDatHang;
import entity.Thuoc;

public class chiTietPhieuDatHang_DAO {

    private thuoc_DAO thuocDAO; // Giả sử bạn đã có thuoc_DAO

    public chiTietPhieuDatHang_DAO() {
        thuocDAO = new thuoc_DAO(); // Khởi tạo DAO liên quan
    }

    /**
     * Lấy danh sách chi tiết của 1 phiếu đặt hàng
     * (Chỉ được gọi bởi phieuDatHang_DAO)
     */
    public List<ChiTietPhieuDatHang> getChiTietTheoMaPhieu(String maPhieuDat) {
        List<ChiTietPhieuDatHang> dsChiTiet = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietPhieuDatHang WHERE maPhieuDat = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, maPhieuDat);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maThuoc = rs.getString("maThuoc");
                    int soLuong = rs.getInt("soLuong");
                    double donGia = rs.getDouble("donGia");

                    // Lấy đối tượng Thuoc đầy đủ
                    Thuoc thuoc = thuocDAO.getThuocTheoMa(maThuoc); 
                    
                    ChiTietPhieuDatHang ct = new ChiTietPhieuDatHang(thuoc, soLuong, donGia);
                    dsChiTiet.add(ct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTiet;
    }

    /**
     * Thêm 1 chi tiết vào CSDL
     * (Chỉ được gọi từ phieuDatHang_DAO và phải truyền vào 'con' của transaction)
     */
    public boolean themChiTiet(ChiTietPhieuDatHang ct, String maPhieuDat, Connection con) throws SQLException {
        String sql = "INSERT INTO ChiTietPhieuDatHang(maPhieuDat, maThuoc, soLuong, donGia) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maPhieuDat);
            ps.setString(2, ct.getThuoc().getMaThuoc());
            ps.setInt(3, ct.getSoLuong());
            ps.setDouble(4, ct.getDonGia());
            
            return ps.executeUpdate() > 0;
        }
        // Không catch SQLException ở đây để transaction ở ngoài bắt được
    }
}