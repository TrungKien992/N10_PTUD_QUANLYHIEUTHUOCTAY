package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuChoThanhToan;

public class PhieuChoThanhToan_DAO {

    // Hàm tạo mã phiếu chờ mới
    public String generateNewMaPhieuCho() {
        String maPhieuCho = "PC00001"; // Mã bắt đầu
        String query = "SELECT TOP 1 maPhieuCho FROM PhieuChoThanhToan ORDER BY maPhieuCho DESC";
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                String lastMa = rs.getString("maPhieuCho");
                if (lastMa != null && lastMa.startsWith("PC")) {
                    int num = Integer.parseInt(lastMa.substring(2)) + 1;
                    maPhieuCho = String.format("PC%05d", num);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maPhieuCho;
    }

    // Thêm một phiếu chờ (chưa bao gồm chi tiết)
    public boolean themPhieuCho(PhieuChoThanhToan phieuCho) {
        String query = "INSERT INTO PhieuChoThanhToan (maPhieuCho, maKH, tenKH, sdtKH, maNVLap, ngayLap, tongTienHang, thueVAT, tongCong) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, phieuCho.getMaPhieuCho());
            
            // Xử lý maKH (có thể null)
            if (phieuCho.getKhachHang() != null) {
                pstmt.setString(2, phieuCho.getKhachHang().getMaKH());
            } else {
                pstmt.setNull(2, java.sql.Types.VARCHAR);
            }
            
            pstmt.setString(3, phieuCho.getTenKH());
            pstmt.setString(4, phieuCho.getSdtKH());
            pstmt.setString(5, phieuCho.getNhanVienLap().getMaNV());
            pstmt.setDate(6, java.sql.Date.valueOf(phieuCho.getNgayLap()));
            pstmt.setDouble(7, phieuCho.getTongTienHang());
            pstmt.setDouble(8, phieuCho.getThueVAT());
            pstmt.setDouble(9, phieuCho.getTongCong());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa một phiếu chờ (ChiTietPhieuCho sẽ tự xóa theo nhờ ON DELETE CASCADE)
    public boolean xoaPhieuCho(String maPhieuCho) {
        String query = "DELETE FROM PhieuChoThanhToan WHERE maPhieuCho = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, maPhieuCho);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Xóa tất cả phiếu chờ
    public boolean xoaTatCaPhieuCho() {
        String query = "DELETE FROM PhieuChoThanhToan";
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(query) >= 0; // Trả về true ngay cả khi 0 dòng bị xóa
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 // Lấy danh sách phiếu chờ (chỉ thông tin cơ bản để hiển thị)
    // SỬA LẠI: Lấy tất cả các phiếu chờ, không lọc theo SĐT nữa
    public List<PhieuChoThanhToan> getDanhSachCho() {
        List<PhieuChoThanhToan> dsPhieuCho = new ArrayList<>();
        String query = "SELECT maPhieuCho, sdtKH, tenKH, maKH, ngayLap " + // Thêm ngayLap để sắp xếp nếu cần
                       "FROM PhieuChoThanhToan " +
                       "ORDER BY ngayLap DESC, maPhieuCho DESC"; // Sắp xếp mới nhất lên đầu

        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                PhieuChoThanhToan pc = new PhieuChoThanhToan();
                pc.setMaPhieuCho(rs.getString("maPhieuCho"));
                pc.setSdtKH(rs.getString("sdtKH"));
                pc.setTenKH(rs.getString("tenKH"));

                String maKH = rs.getString("maKH");
                if(maKH != null) {
                    pc.setKhachHang(new KhachHang(maKH)); // Chỉ cần mã KH
                }

                dsPhieuCho.add(pc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhieuCho;
    }

    // Lấy 1 phiếu chờ đầy đủ thông tin (theo mã phiếu chờ)
    public PhieuChoThanhToan getPhieuChoTheoMa(String maPhieuCho) {
        PhieuChoThanhToan phieuCho = null;
        String query = "SELECT * FROM PhieuChoThanhToan WHERE maPhieuCho = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, maPhieuCho);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                phieuCho = new PhieuChoThanhToan();
                phieuCho.setMaPhieuCho(rs.getString("maPhieuCho"));
                phieuCho.setTenKH(rs.getString("tenKH"));
                phieuCho.setSdtKH(rs.getString("sdtKH"));
                phieuCho.setNgayLap(rs.getDate("ngayLap").toLocalDate());
                phieuCho.setTongTienHang(rs.getDouble("tongTienHang"));
                phieuCho.setThueVAT(rs.getDouble("thueVAT"));
                phieuCho.setTongCong(rs.getDouble("tongCong"));
                
                String maKH = rs.getString("maKH");
                if (maKH != null) {
                    phieuCho.setKhachHang(new KhachHang(maKH)); // Sẽ lấy full sau nếu cần
                }
                
                String maNV = rs.getString("maNVLap");
                phieuCho.setNhanVienLap(new NhanVien(maNV)); // Sẽ lấy full sau nếu cần
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phieuCho;
    }
}