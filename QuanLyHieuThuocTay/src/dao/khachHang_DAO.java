package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;

public class khachHang_DAO {

    /**
     * Lấy danh sách tất cả khách hàng TẤT CẢ TRẠNG THÁI từ CSDL
     */
	public List<KhachHang> getAllKhachHang() {
	    // Cập nhật câu truy vấn để lấy cả trường trangThai
	    List<KhachHang> dsKH = new ArrayList<>();
	    String sql = "SELECT maKH, tenKH, diaChi, sdt, trangThai FROM KhachHang";
	    try (Connection con = ConnectDB.getConnection();
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            KhachHang kh = new KhachHang(
	                rs.getString("maKH"),
	                rs.getString("tenKH"),
	                rs.getString("sdt"),      
	                rs.getString("diaChi"),
	                rs.getBoolean("trangThai") // THÊM TRẠNG THÁI
	            );
	            dsKH.add(kh);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsKH;
	}
    
    /**
     * BỔ SUNG: Lấy danh sách khách hàng CHỈ CÓ TRẠNG THÁI ACTIVE (trangThai = 1)
     */
	public List<KhachHang> getAllActiveKhachHang() {
	    List<KhachHang> dsKH = new ArrayList<>();
	    String sql = "SELECT maKH, tenKH, diaChi, sdt, trangThai FROM KhachHang WHERE trangThai = 1"; // Chỉ lấy active
	    try (Connection con = ConnectDB.getConnection();
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            KhachHang kh = new KhachHang(
	                rs.getString("maKH"),
	                rs.getString("tenKH"),
	                rs.getString("sdt"),
	                rs.getString("diaChi"),
	                rs.getBoolean("trangThai")
	            );
	            dsKH.add(kh);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsKH;
	}


    /**
     * Lấy thông tin khách hàng dựa vào mã (Cập nhật để lấy trangThai)
     */
	public KhachHang getKhachHangTheoMa(String maKH) {
	    String sql = "SELECT maKH, tenKH, diaChi, sdt, trangThai FROM KhachHang WHERE maKH = ?";
	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, maKH);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return new KhachHang(
	                    rs.getString("maKH"),
	                    rs.getString("tenKH"),
	                    rs.getString("sdt"),      
	                    rs.getString("diaChi"),    
	                    rs.getBoolean("trangThai") // THÊM TRẠNG THÁI
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
    
    /**
     * Lấy thông tin khách hàng dựa vào số điện thoại (Cập nhật để lấy trangThai)
     */
	public KhachHang getKhachHangTheoSDT(String sdt) {
	    String sql = "SELECT maKH, tenKH, diaChi, sdt, trangThai FROM KhachHang WHERE sdt = ?";
	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, sdt);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                 return new KhachHang(
	                    rs.getString("maKH"),
	                    rs.getString("tenKH"),
	                    rs.getString("sdt"),      
	                    rs.getString("diaChi"),    
	                    rs.getBoolean("trangThai") // THÊM TRẠNG THÁI
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

    /**
     * Thêm một khách hàng mới vào CSDL (Cập nhật để thêm trangThai)
     */
    public boolean themKhachHang(KhachHang kh) {
        // Thêm trường trangThai vào câu truy vấn
        String sql = "INSERT INTO KhachHang(maKH, tenKH, diaChi, sdt, trangThai) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getTenKH());
            ps.setString(3, kh.getDiaChi());
            ps.setString(4, kh.getSoDienThoai());
            ps.setBoolean(5, kh.isTrangThai()); // THÊM TRẠNG THÁI
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Cập nhật thông tin một khách hàng
     */
    public boolean updateKhachHang(KhachHang kh) {
        // Cập nhật câu SQL để có thể cập nhật trangThai
        String sql = "UPDATE KhachHang SET tenKH = ?, diaChi = ?, sdt = ?, trangThai = ? WHERE maKH = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, kh.getTenKH());
            ps.setString(2, kh.getDiaChi());
            ps.setString(3, kh.getSoDienThoai());
            ps.setBoolean(4, kh.isTrangThai()); // THÊM TRẠNG THÁI
            ps.setString(5, kh.getMaKH());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * BỔ SUNG: Xóa khách hàng (Set trangThai = 0)
     */
    public boolean deleteKhachHang(String maKH) {
        // Thực chất là Set trangThai = 0 (Ngừng giao dịch)
        String sql = "UPDATE KhachHang SET trangThai = 0 WHERE maKH = ?"; 
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maKH);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Tự động sinh mã khách hàng mới (Giữ nguyên)
     */
    public String generateNewMaKH() {
        String prefix = "KH";
        String sql = "SELECT TOP 1 maKH FROM KhachHang ORDER BY maKH DESC";
        try (Connection con = ConnectDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String lastID = rs.getString("maKH");
                int number = Integer.parseInt(lastID.replace(prefix, ""));
                number++;
                return String.format("%s%07d", prefix, number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prefix + "0000001"; // Nếu bảng đang rỗng
    }
    
    /**
     * TÌM KIẾM KHÁCH HÀNG (SỬ DỤNG PHƯƠNG THỨC NÀY)
     */
    public List<KhachHang> searchKhachHang(String maKH, String tenKH, String sdt, String diaChi) {
        List<KhachHang> dsKH = new ArrayList<>();
        // Chỉ tìm kiếm KH đang Active (trangThai = 1) và lấy cả trường trangThai
        String sql = "SELECT maKH, tenKH, diaChi, sdt, trangThai FROM KhachHang WHERE trangThai = 1 AND maKH LIKE ? AND tenKH LIKE ? AND sdt LIKE ? AND diaChi LIKE ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + maKH + "%");
            ps.setString(2, "%" + tenKH + "%");
            ps.setString(3, "%" + sdt + "%");
            ps.setString(4, "%" + diaChi + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // SỬ DỤNG CONSTRUCTOR 5 THAM SỐ (ma, ten, sdt, diaChi, trangThai)
                    KhachHang kh = new KhachHang(
                        rs.getString("maKH"),
                        rs.getString("tenKH"),
                        rs.getString("sdt"),
                        rs.getString("diaChi"),
                        rs.getBoolean("trangThai") 
                    );
                    dsKH.add(kh);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKH;
    }
}