package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KeThuoc;
import entity.NhaCungCap;
import entity.Thuoc;

public class thuoc_DAO {
    
    /**
     * Lấy thông tin thuốc theo mã
     */
    public Thuoc getThuocTheoMa(String maThuoc) {
        // Giả định bảng Thuoc đã có cột maNhaCungCap
        String sql = "SELECT t.*, k.loaiKe, ncc.tenNhaCungCap FROM Thuoc t " +
                     "JOIN KeThuoc k ON t.maKe = k.maKe " +
                     "JOIN NhaCungCap ncc ON t.maNhaCungCap = ncc.maNhaCungCap " +
                     "WHERE t.maThuoc = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, maThuoc);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToThuoc(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lấy danh sách tất cả các loại thuốc
     */
    public List<Thuoc> getAllThuoc() {
        List<Thuoc> dsThuoc = new ArrayList<>();
        String sql = "SELECT t.*, k.loaiKe, ncc.tenNhaCungCap FROM Thuoc t " +
                     "JOIN KeThuoc k ON t.maKe = k.maKe " +
                     "JOIN NhaCungCap ncc ON t.maNhaCungCap = ncc.maNhaCungCap";
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                dsThuoc.add(mapRowToThuoc(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsThuoc;
    }
    
    /**
     * Thêm một loại thuốc mới
     */
    public boolean themThuoc(Thuoc t) {
        String sql = "INSERT INTO Thuoc(maThuoc, tenThuoc, giaNhap, giaBan, soLuong, hanSuDung, thanhPhan, donViTinh, anh, maKe, maNhaCungCap) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, t.getMaThuoc());
            ps.setString(2, t.getTenThuoc());
            ps.setDouble(3, t.getGiaNhap());
            ps.setDouble(4, t.getGiaBan());
            ps.setInt(5, t.getSoLuong());
            ps.setDate(6, Date.valueOf(t.getHanSuDung()));
            ps.setString(7, t.getThanhPhan());
            ps.setString(8, t.getDonViTinh());
            ps.setString(9, t.getAnh());
            ps.setString(10, t.getKeThuoc().getMaKe());
            ps.setString(11, t.getNhaCungCap().getMaNhaCungCap());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Cập nhật thông tin thuốc
     */
    public boolean updateThuoc(Thuoc t) {
        String sql = "UPDATE Thuoc SET tenThuoc = ?, giaNhap = ?, giaBan = ?, soLuong = ?, hanSuDung = ?, " +
                     "thanhPhan = ?, donViTinh = ?, anh = ?, maKe = ?, maNhaCungCap = ? WHERE maThuoc = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, t.getTenThuoc());
            ps.setDouble(2, t.getGiaNhap());
            ps.setDouble(3, t.getGiaBan());
            ps.setInt(4, t.getSoLuong());
            ps.setDate(5, Date.valueOf(t.getHanSuDung()));
            ps.setString(6, t.getThanhPhan());
            ps.setString(7, t.getDonViTinh());
            ps.setString(8, t.getAnh());
            ps.setString(9, t.getKeThuoc().getMaKe());
            ps.setString(10, t.getNhaCungCap().getMaNhaCungCap());
            ps.setString(11, t.getMaThuoc());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Hàm helper để map một dòng từ ResultSet thành đối tượng Thuoc
     */
    private Thuoc mapRowToThuoc(ResultSet rs) throws SQLException {
        Thuoc t = new Thuoc();
        t.setMaThuoc(rs.getString("maThuoc"));
        t.setTenThuoc(rs.getString("tenThuoc"));
        t.setGiaNhap(rs.getDouble("giaNhap"));
        t.setGiaBan(rs.getDouble("giaBan"));
        t.setSoLuong(rs.getInt("soLuong"));
        t.setHanSuDung(rs.getDate("hanSuDung").toLocalDate());
        t.setThanhPhan(rs.getString("thanhPhan"));
        t.setDonViTinh(rs.getString("donViTinh"));
        t.setAnh(rs.getString("anh"));
        
        KeThuoc ke = new KeThuoc();
        ke.setMaKe(rs.getString("maKe"));
        ke.setLoaiKe(rs.getString("loaiKe")); // Lấy từ join
        t.setKeThuoc(ke);
        
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNhaCungCap(rs.getString("maNhaCungCap"));
        ncc.setTenNhaCungCap(rs.getString("tenNhaCungCap")); // Lấy từ join
        t.setNhaCungCap(ncc);
        
        return t;
    }
    /**
     * Lấy mã nhà cung cấp theo tên nhà cung cấp
     * Dùng khi combobox chỉ chứa tên NCC
     */
    public String getMaNCCTheoTen(String tenNCC) {
        String ma = null;
        String sql = "SELECT maNhaCungCap FROM NhaCungCap WHERE tenNhaCungCap = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenNCC);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ma = rs.getString("maNhaCungCap");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ma;
    }
}