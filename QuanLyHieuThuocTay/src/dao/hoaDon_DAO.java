package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat; // Import
import java.time.LocalDate; // Import
import java.util.ArrayList;
import java.util.List; // Import List

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.Thue;

public class hoaDon_DAO {
    
    // ... (Hàm getAllHoaDon(), themHoaDon(), generateNewMaHD() giữ nguyên) ...
    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("maHD"));
                hd.setNgayLap(rs.getDate("ngayLap").toLocalDate());
                
                khachHang_DAO kh_dao = new khachHang_DAO();
                KhachHang kh = kh_dao.getKhachHangTheoMa(rs.getString("maKH"));
                hd.setKhachHang(kh);
                
                nhanVien_DAO nv_dao = new nhanVien_DAO();
                NhanVien nv = nv_dao.getNhanVienTheoMa(rs.getString("maNV")); 
                hd.setNhanVien(nv);
                
                thue_DAO thue_dao = new thue_DAO(); // Tạo DAO
                Thue thue = thue_dao.getThueByMa(rs.getString("maThue")); // Lấy full
                hd.setThue(thue);
                
                khuyenMai_DAO km_dao = new khuyenMai_DAO(); // Tạo DAO
                KhuyenMai km = km_dao.getKhuyenMaiByMa(rs.getString("maKM")); // Lấy full
                hd.setKhuyenMai(km);

                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }
    
    public boolean themHoaDon(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (maHD, ngayLap, maKM, maThue, maNV, maKH) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, hd.getMaHD());
            stmt.setDate(2, Date.valueOf(hd.getNgayLap()));
            
            if (hd.getKhuyenMai() != null && hd.getKhuyenMai().getMaKM() != null) {
                stmt.setString(3, hd.getKhuyenMai().getMaKM());
            } else {
                stmt.setNull(3, java.sql.Types.VARCHAR);
            }
            
            stmt.setString(4, hd.getThue().getMaThue());
            stmt.setString(5, hd.getNhanVien().getMaNV());
            
            if (hd.getKhachHang() != null && hd.getKhachHang().getMaKH() != null) {
                stmt.setString(6, hd.getKhachHang().getMaKH());
            } else {
                stmt.setNull(6, java.sql.Types.VARCHAR);
            }
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String generateNewMaHD() {
        String maHD = "HD001";
        String sql = "SELECT MAX(maHD) FROM HoaDon";
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String maxMaHD = rs.getString(1);
                if (maxMaHD != null) {
                    try { // Thêm try-catch
                        int num = Integer.parseInt(maxMaHD.substring(2)) + 1;
                        maHD = String.format("HD%03d", num);
                    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                        e.printStackTrace(); // Lỗi nếu mã không đúng định dạng
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maHD;
    }
    
    // === BỔ SUNG HÀM LẤY HÓA ĐƠN THEO MÃ (REQ 6) ===
    public HoaDon getHoaDonByMa(String maHD) {
        HoaDon hd = null;
        String sql = "SELECT * FROM HoaDon WHERE maHD = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, maHD);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    hd = new HoaDon();
                    hd.setMaHD(rs.getString("maHD"));
                    hd.setNgayLap(rs.getDate("ngayLap").toLocalDate());

                    khachHang_DAO kh_dao = new khachHang_DAO();
                    KhachHang kh = kh_dao.getKhachHangTheoMa(rs.getString("maKH"));
                    hd.setKhachHang(kh);
                    
                    nhanVien_DAO nv_dao = new nhanVien_DAO();
                    NhanVien nv = nv_dao.getNhanVienTheoMa(rs.getString("maNV")); 
                    hd.setNhanVien(nv);
                    
                    thue_DAO thue_dao = new thue_DAO();
                    Thue thue = thue_dao.getThueByMa(rs.getString("maThue"));
                    hd.setThue(thue);
                    
                    khuyenMai_DAO km_dao = new khuyenMai_DAO();
                    KhuyenMai km = km_dao.getKhuyenMaiByMa(rs.getString("maKM"));
                    hd.setKhuyenMai(km);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hd;
    }

    // === BỔ SUNG HÀM LẤY DỮ LIỆU BẢNG TÌM KIẾM (REQ 5) ===
    public List<Object[]> getAllHoaDonChiTietForTable() {
        List<Object[]> ds = new ArrayList<>();
        String sql = "SELECT hd.maHD, kh.maKH, hd.ngayLap, kh.tenKH, kh.sdt, " +
                     "nv.tenNV, nv.sdt AS sdtNV, " +
                     // Tính tổng tiền = SUM(ct.soLuong * t.giaBan) * (1 + thue.tiLe) * (1 - km.giaTri/100)
                     "(SUM(ct.soLuong * t.giaBan) * (1 + th.tiLe) * (1 - ISNULL(km.giaTri, 0)/100)) AS TongTien " + 
                     "FROM HoaDon hd " +
                     "LEFT JOIN KhachHang kh ON hd.maKH = kh.maKH " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
                     "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
                     "JOIN Thue th ON hd.maThue = th.maThue " +
                     "LEFT JOIN KhuyenMai km ON hd.maKM = km.maKM " +
                     "GROUP BY hd.maHD, kh.maKH, hd.ngayLap, kh.tenKH, kh.sdt, nv.tenNV, nv.sdt, th.tiLe, km.giaTri " +
                     "ORDER BY hd.ngayLap DESC";

        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ds.add(new Object[] {
                    rs.getString("maHD"),
                    rs.getString("maKH") != null ? rs.getString("maKH") : "N/A",
                    rs.getDate("ngayLap"),
                    rs.getString("tenKH") != null ? rs.getString("tenKH") : "Khách vãng lai",
                    rs.getString("sdt") != null ? rs.getString("sdt") : "N/A",
                    rs.getString("tenNV"),
                    rs.getString("sdtNV"),
                    rs.getDouble("TongTien")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    // === BỔ SUNG HÀM TÌM KIẾM HÓA ĐƠN (REQ 6) ===
    public List<Object[]> searchHoaDonChiTiet(String maHD, String tenKH, String sdtKH, String tenNV, String sdtNV, LocalDate ngayLap) {
        List<Object[]> ds = new ArrayList<>();
        String sql = "SELECT hd.maHD, kh.maKH, hd.ngayLap, kh.tenKH, kh.sdt, " +
                     "nv.tenNV, nv.sdt AS sdtNV, " +
                     "(SUM(ct.soLuong * t.giaBan) * (1 + th.tiLe) * (1 - ISNULL(km.giaTri, 0)/100)) AS TongTien " +
                     "FROM HoaDon hd " +
                     "LEFT JOIN KhachHang kh ON hd.maKH = kh.maKH " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
                     "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
                     "JOIN Thue th ON hd.maThue = th.maThue " +
                     "LEFT JOIN KhuyenMai km ON hd.maKM = km.maKM " +
                     "WHERE hd.maHD LIKE ? " +
                     "AND (kh.tenKH LIKE ? OR (kh.tenKH IS NULL AND ? = '%%')) " + // Cho phép khách vãng lai
                     "AND (kh.sdt LIKE ? OR (kh.sdt IS NULL AND ? = '%%')) " +
                     "AND nv.tenNV LIKE ? " +
                     "AND nv.sdt LIKE ? ";
        
        if (ngayLap != null) {
            sql += " AND hd.ngayLap = ? ";
        }
        
        sql += "GROUP BY hd.maHD, kh.maKH, hd.ngayLap, kh.tenKH, kh.sdt, nv.tenNV, nv.sdt, th.tiLe, km.giaTri " +
               "ORDER BY hd.ngayLap DESC";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, "%" + maHD + "%");
            ps.setString(2, "%" + tenKH + "%");
            ps.setString(3, "%" + tenKH + "%"); // Lặp lại cho điều kiện IS NULL
            ps.setString(4, "%" + sdtKH + "%");
            ps.setString(5, "%" + sdtKH + "%"); // Lặp lại cho điều kiện IS NULL
            ps.setString(6, "%" + tenNV + "%");
            ps.setString(7, "%" + sdtNV + "%");
            if (ngayLap != null) {
                ps.setDate(8, Date.valueOf(ngayLap));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new Object[] {
                        rs.getString("maHD"),
                        rs.getString("maKH") != null ? rs.getString("maKH") : "N/A",
                        rs.getDate("ngayLap"),
                        rs.getString("tenKH") != null ? rs.getString("tenKH") : "Khách vãng lai",
                        rs.getString("sdt") != null ? rs.getString("sdt") : "N/A",
                        rs.getString("tenNV"),
                        rs.getString("sdtNV"),
                        rs.getDouble("TongTien")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
}