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
                    rs.getBoolean("trangThai")
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
                        rs.getBoolean("trangThai")
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
        String sql = "INSERT INTO NhaCungCap(maNhaCungCap, tenNhaCungCap, soDienThoai, email, diaChi, trangThai) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, ncc.getMaNhaCungCap());
            ps.setString(2, ncc.getTenNhaCungCap());
            ps.setString(3, ncc.getSoDienThoai());
            ps.setString(4, ncc.getEmail());
            ps.setString(5, ncc.getDiaChi());
            ps.setBoolean(6, ncc.isTrangThai());
            
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
        String sql = "UPDATE NhaCungCap SET tenNhaCungCap = ?, soDienThoai = ?, email = ?, diaChi = ?, trangThai = ? WHERE maNhaCungCap = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, ncc.getTenNhaCungCap());
            ps.setString(2, ncc.getSoDienThoai());
            ps.setString(3, ncc.getEmail());
            ps.setString(4, ncc.getDiaChi());
            ps.setBoolean(5, ncc.isTrangThai());
            ps.setString(6, ncc.getMaNhaCungCap());
            
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
                return String.format("%s%03d", prefix, number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prefix + "001"; // Nếu bảng đang rỗng
    }
}