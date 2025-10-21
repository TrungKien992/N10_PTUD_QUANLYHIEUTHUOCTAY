package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhaCungCap;

public class nhaCungCap_DAO {

    /**
     * Lấy danh sách tất cả các nhà cung cấp
     */
    public List<NhaCungCap> getAllNhaCungCap() {
        List<NhaCungCap> dsNCC = new ArrayList<>();
        // Giả định bảng NhaCungCap đã được cập nhật đầy đủ các cột
        String sql = "SELECT * FROM NhaCungCap";
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap(
                        rs.getString("maNhaCungCap"),
                        rs.getString("tenNhaCungCap"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        rs.getString("diaChi"),
                        rs.getBoolean("trangThai"),
                        rs.getString("ghiChu")
                );
                dsNCC.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNCC;
    }

    /**
     * Lấy thông tin nhà cung cấp theo mã
     */
    public NhaCungCap getNhaCungCapTheoMa(String maNCC) {
        String sql = "SELECT * FROM NhaCungCap WHERE maNhaCungCap = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maNCC);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhaCungCap(
                            rs.getString("maNhaCungCap"),
                            rs.getString("tenNhaCungCap"),
                            rs.getString("soDienThoai"),
                            rs.getString("email"),
                            rs.getString("diaChi"),
                            rs.getBoolean("trangThai"),
                            rs.getString("ghiChu")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lấy thông tin nhà cung cấp theo tên
     */
    public NhaCungCap getNhaCungCapTheoTen(String tenNCC) {
        String sql = "SELECT * FROM NhaCungCap WHERE tenNhaCungCap = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenNCC);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhaCungCap(
                            rs.getString("maNhaCungCap"),
                            rs.getString("tenNhaCungCap"),
                            rs.getString("soDienThoai"),
                            rs.getString("email"),
                            rs.getString("diaChi"),
                            rs.getBoolean("trangThai"),
                            rs.getString("ghiChu")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Thêm một nhà cung cấp mới
     */
    public boolean themNhaCungCap(NhaCungCap ncc) {
        String sql = "INSERT INTO NhaCungCap(maNhaCungCap, tenNhaCungCap, soDienThoai, email, diaChi, trangThai, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ncc.getMaNhaCungCap());
            ps.setString(2, ncc.getTenNhaCungCap());
            ps.setString(3, ncc.getSoDienThoai());
            ps.setString(4, ncc.getEmail());
            ps.setString(5, ncc.getDiaChi());
            ps.setBoolean(6, ncc.isTrangThai());
            ps.setString(7, ncc.getGhiChu());


            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Cập nhật thông tin nhà cung cấp
     */
    public boolean updateNhaCungCap(NhaCungCap ncc) {
    	// Câu lệnh SQL yêu cầu 7 tham số: 6 cho SET và 1 cho WHERE
        String sql = "UPDATE NhaCungCap SET tenNhaCungCap = ?, soDienThoai = ?, email = ?, diaChi = ?, trangThai = ?, ghiChu = ? WHERE maNhaCungCap = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ncc.getTenNhaCungCap());
            ps.setString(2, ncc.getSoDienThoai());
            ps.setString(3, ncc.getEmail());
            ps.setString(4, ncc.getDiaChi());
            ps.setBoolean(5, ncc.isTrangThai());
            
            // --- PHẦN ĐÃ SỬA ---
            // Tham số thứ 6 là 'ghiChu'
            ps.setString(6, ncc.getGhiChu());
            // Tham số thứ 7 là 'maNhaCungCap' cho mệnh đề WHERE
            ps.setString(7, ncc.getMaNhaCungCap());
            // --- HẾT PHẦN SỬA ---
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Tạo mã nhà cung cấp mới tự động
     */
    public String generateNewMaNCC() {
        String prefix = "NCC";
        String sql = "SELECT TOP 1 maNhaCungCap FROM NhaCungCap ORDER BY maNhaCungCap DESC";
        try (Connection con = ConnectDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String lastID = rs.getString("maNhaCungCap");
                int number = Integer.parseInt(lastID.replace(prefix, ""));
                number++;
                // Sử dụng %03d nếu bạn muốn có 3 chữ số (ví dụ: NCC001), %02d cho 2 chữ số (NCC01)
                return String.format("%s%02d", prefix, number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prefix + "01"; // Nếu bảng đang rỗng
    }
}