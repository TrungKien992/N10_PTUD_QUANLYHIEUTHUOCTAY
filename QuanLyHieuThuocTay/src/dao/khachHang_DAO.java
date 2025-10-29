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
     * Lấy danh sách tất cả khách hàng từ CSDL
     */
	public List<KhachHang> getAllKhachHang() {
	    List<KhachHang> dsKH = new ArrayList<>();
	    String sql = "SELECT * FROM KhachHang";
	    try (Connection con = ConnectDB.getConnection();
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            // *** SỬA Ở ĐÂY: Đổi vị trí rs.getString("sdt") và rs.getString("diaChi") ***
	            KhachHang kh = new KhachHang(
	                rs.getString("maKH"),
	                rs.getString("tenKH"),
	                rs.getString("sdt"),      // Lấy sdt trước
	                rs.getString("diaChi")    // Lấy diaChi sau
	            );
	            dsKH.add(kh);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsKH;
	}

    /**
     * Lấy thông tin khách hàng dựa vào mã
     */
	public KhachHang getKhachHangTheoMa(String maKH) {
	    String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, maKH);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                // *** SỬA Ở ĐÂY: Đổi vị trí rs.getString("sdt") và rs.getString("diaChi") ***
	                return new KhachHang(
	                    rs.getString("maKH"),
	                    rs.getString("tenKH"),
	                    rs.getString("sdt"),      // Lấy sdt trước
	                    rs.getString("diaChi")    // Lấy diaChi sau
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
    
    /**
     * Lấy thông tin khách hàng dựa vào số điện thoại
     */
	public KhachHang getKhachHangTheoSDT(String sdt) {
	    String sql = "SELECT * FROM KhachHang WHERE sdt = ?";
	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, sdt);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                 // *** SỬA Ở ĐÂY: Đổi vị trí rs.getString("sdt") và rs.getString("diaChi") ***
	                 return new KhachHang(
	                    rs.getString("maKH"),
	                    rs.getString("tenKH"),
	                    rs.getString("sdt"),      // Lấy sdt trước
	                    rs.getString("diaChi")    // Lấy diaChi sau
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

    /**
     * Thêm một khách hàng mới vào CSDL
     */
    public boolean themKhachHang(KhachHang kh) {
        String sql = "INSERT INTO KhachHang(maKH, tenKH, diaChi, sdt) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getTenKH());
            ps.setString(3, kh.getDiaChi());
            ps.setString(4, kh.getSoDienThoai());
            
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
        // SQL: SET tenKH = ?, diaChi = ?, sdt = ? WHERE maKH = ?
        String sql = "UPDATE KhachHang SET tenKH = ?, diaChi = ?, sdt = ? WHERE maKH = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, kh.getTenKH());
            // Lấy đúng giá trị từ object KH (sau khi đã sửa các hàm get/search)
            ps.setString(2, kh.getDiaChi()); // tham số thứ 2 là diaChi
            ps.setString(3, kh.getSoDienThoai()); // tham số thứ 3 là sdt
            ps.setString(4, kh.getMaKH());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Tự động sinh mã khách hàng mới
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
 // === BỔ SUNG HÀM TÌM KIẾM KHÁCH HÀNG (REQ 2) ===
    public List<KhachHang> searchKhachHang(String maKH, String tenKH, String sdt, String diaChi) {
        List<KhachHang> dsKH = new ArrayList<>();
        // SQL query giữ nguyên
        String sql = "SELECT * FROM KhachHang WHERE maKH LIKE ? AND tenKH LIKE ? AND sdt LIKE ? AND diaChi LIKE ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + maKH + "%");
            ps.setString(2, "%" + tenKH + "%");
            ps.setString(3, "%" + sdt + "%");
            ps.setString(4, "%" + diaChi + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                     // *** SỬA Ở ĐÂY: Đổi vị trí rs.getString("sdt") và rs.getString("diaChi") ***
                    KhachHang kh = new KhachHang(
                        rs.getString("maKH"),
                        rs.getString("tenKH"),
                        rs.getString("sdt"),      // Lấy sdt trước
                        rs.getString("diaChi")    // Lấy diaChi sau
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