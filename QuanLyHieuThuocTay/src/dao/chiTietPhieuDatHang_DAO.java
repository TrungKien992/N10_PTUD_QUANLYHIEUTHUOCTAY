package dao;

import entity.ChiTietPhieuDatHang;
import entity.PhieuDatHang;
import entity.Thuoc;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class chiTietPhieuDatHang_DAO {

    // Có thể cần DAO của Thuốc để lấy thông tin thuốc chi tiết
    private thuoc_DAO thuocDao = new thuoc_DAO(); 

    /**
     * Lấy danh sách tất cả chi tiết của một Phiếu Đặt Hàng cụ thể.
     * @param maPhieu Mã phiếu đặt hàng cần lấy chi tiết.
     * @return Danh sách các đối tượng ChiTietPhieuDatHang.
     */
    // Đã đổi tên: getChiTietTheoMaPhieu -> layChiTietTheoMaPhieu
    public List<ChiTietPhieuDatHang> layChiTietTheoMaPhieu(String maPhieu) {
        List<ChiTietPhieuDatHang> dsChiTiet = new ArrayList<>();
        
        // --- PHẦN ĐÃ SỬA ---
        // Sửa t.TenThuoc -> t.tenThuoc và t.MaThuoc -> t.maThuoc
        String sql = "SELECT ct.MaPhieu, ct.MaThuoc, t.tenThuoc, ct.SoLuong, ct.DonGia " + // Bỏ ct.ThanhTien
                     "FROM ChiTietPhieuDatHang ct " +
                     "JOIN Thuoc t ON ct.MaThuoc = t.maThuoc " + // Sửa tên cột
                     "WHERE ct.MaPhieu = ?";
        // --- HẾT PHẦN SỬA ---

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maPhieu);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhieuDatHang pdhRef = new PhieuDatHang();
                pdhRef.setMaPhieu(rs.getString("MaPhieu"));

                Thuoc thuoc = new Thuoc();
                thuoc.setMaThuoc(rs.getString("MaThuoc"));
                
                // --- PHẦN ĐÃ SỬA ---
                thuoc.setTenThuoc(rs.getString("tenThuoc")); // Sửa tên cột
                // --- HẾT PHẦN SỬA ---

                ChiTietPhieuDatHang ct = new ChiTietPhieuDatHang();
                ct.setPhieuDatHang(pdhRef);
                ct.setThuoc(thuoc);
                ct.setSoLuong(rs.getInt("SoLuong"));
                ct.setDonGia(rs.getDouble("DonGia"));
                // Entity đã tự tính thành tiền khi setSoLuong/DonGia

                dsChiTiet.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTiet;
    }

    // --- Các hàm Thêm/Sửa/Xóa (Thường được gọi bên trong Transaction của phieuDatHang_DAO) ---

    /**
     * Thêm một dòng chi tiết vào CSDL.
     * (Hàm này thường không được gọi trực tiếp mà qua hàm themPhieuDatHang của DAO chính).
     * @param ct Đối tượng ChiTietPhieuDatHang cần thêm.
     * @param con Connection đang trong Transaction (nếu có).
     * @return true nếu thêm thành công.
     */
    // Đã đổi tên: insertChiTiet -> themChiTiet
    public boolean themChiTiet(ChiTietPhieuDatHang ct, Connection con) throws SQLException {
         
         // --- PHẦN ĐÃ SỬA ---
         // Bỏ "ThanhTien" và "?" cuối cùng
         String sql = "INSERT INTO ChiTietPhieuDatHang(MaPhieu, MaThuoc, SoLuong, DonGia) " +
                      "VALUES (?, ?, ?, ?)";
         // --- HẾT PHẦN SỬA ---

         PreparedStatement ps = null; 
         try {
             ps = con.prepareStatement(sql);
             ps.setString(1, ct.getPhieuDatHang().getMaPhieu());
             ps.setString(2, ct.getThuoc().getMaThuoc());
             ps.setInt(3, ct.getSoLuong());
             ps.setDouble(4, ct.getDonGia());
             
             // --- PHẦN ĐÃ SỬA ---
             // Đã XÓA dòng: ps.setDouble(5, ct.tinhThanhTien());
             // --- HẾT PHẦN SỬA ---
             
             return ps.executeUpdate() > 0;
         } finally {
             if (ps != null) ps.close(); // Chỉ đóng PreparedStatement, không đóng Connection
         }
    }
    
    /**
     * Xóa tất cả chi tiết của một phiếu.
     * (Thường được gọi khi cập nhật phiếu, trước khi thêm chi tiết mới).
     * @param maPhieu Mã phiếu cần xóa chi tiết.
     * @param con Connection đang trong Transaction (nếu có).
     * @return true nếu xóa thành công (hoặc không có gì để xóa).
     */
    // Đã đổi tên: deleteChiTietTheoMaPhieu -> xoaChiTietTheoMaPhieu
    public boolean xoaChiTietTheoMaPhieu(String maPhieu, Connection con) throws SQLException {
        String sql = "DELETE FROM ChiTietPhieuDatHang WHERE MaPhieu = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, maPhieu);
            ps.executeUpdate(); // Thực thi xóa
            return true; // Giả định thành công (kể cả khi không có dòng nào bị xóa)
        } finally {
            if (ps != null) ps.close();
        }
    }
}