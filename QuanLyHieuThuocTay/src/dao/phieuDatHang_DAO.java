package dao;

import entity.PhieuDatHang;
import entity.ChiTietPhieuDatHang;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.Thuoc;
import connectDB.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class phieuDatHang_DAO {

    // Khởi tạo các DAO cần thiết
    private nhaCungCap_DAO nccDao = new nhaCungCap_DAO();
    private thuoc_DAO thuocDao = new thuoc_DAO();
    private nhanVien_DAO nvDao = new nhanVien_DAO(); 

    // === HÀM LẤY DANH SÁCH PHIẾU (CHO BẢNG CHÍNH) ===
    // Đã đổi tên: getAllPhieuDatHang -> layTatCaPhieuDatHang
    public List<PhieuDatHang> layTatCaPhieuDatHang() {
        List<PhieuDatHang> dsPhieu = new ArrayList<>();
        
        // Câu SQL đã được tối ưu (bỏ JOIN NhanVien) và sửa tên cột
        String sql = "SELECT pdh.MaPhieu, pdh.MaNCC, ncc.tenNhaCungCap, " +
                     "pdh.NgayDat, pdh.TongTien, pdh.TrangThai " +
                     "FROM PhieuDatHang pdh " +
                     "JOIN NhaCungCap ncc ON pdh.MaNCC = ncc.maNhaCungCap"; 

        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap(); 
                ncc.setMaNhaCungCap(rs.getString("MaNCC"));
                ncc.setTenNhaCungCap(rs.getString("tenNhaCungCap")); 

                PhieuDatHang pdh = new PhieuDatHang();
                pdh.setMaPhieu(rs.getString("MaPhieu"));
                pdh.setNhaCungCap(ncc);
                
                Date ngayDatSQL = rs.getDate("NgayDat");
                if (ngayDatSQL != null) {
                    pdh.setNgayDat(ngayDatSQL.toLocalDate());
                } else {
                    pdh.setNgayDat(null); 
                }
                pdh.setTongTien(rs.getDouble("TongTien"));
                pdh.setTrangThai(rs.getString("TrangThai"));
                dsPhieu.add(pdh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhieu;
    }


    // === HÀM CHO NÚT "XEM/SỬA" ===

    // Đã đổi tên: getPhieuDatHangTheoMa -> layPhieuDatHangTheoMa
    public PhieuDatHang layPhieuDatHangTheoMa(String maPhieu) {
        String sql = "SELECT MaPhieu, MaNCC, MaNhanVien, NgayDat, TongTien, TrangThai, GhiChu " +
                     "FROM PhieuDatHang WHERE MaPhieu = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maNCC = rs.getString("MaNCC");
                String maNV = rs.getString("MaNhanVien"); 

                // Chú ý: Các hàm của DAO khác vẫn giữ nguyên tên
                NhaCungCap ncc = nccDao.getNhaCungCapTheoMa(maNCC);
                NhanVien nv = nvDao.getNhanVienTheoMa(maNV); 

                if (ncc == null) {
                    System.err.println("Cảnh báo: Không tìm thấy Nhà cung cấp mã " + maNCC + " cho phiếu " + maPhieu);
                    ncc = new NhaCungCap(maNCC); 
                }
                 if (nv == null) {
                    System.err.println("Cảnh báo: Không tìm thấy Nhân viên mã " + maNV + " cho phiếu " + maPhieu);
                    nv = new NhanVien();
                    nv.setMaNV(maNV); 
                 }
                
                LocalDate ngayDat = null;
                Date ngayDatSQL = rs.getDate("NgayDat");
                 if (ngayDatSQL != null) {
                    ngayDat = ngayDatSQL.toLocalDate();
                 }

                PhieuDatHang pdh = new PhieuDatHang(
                    rs.getString("MaPhieu"),
                    ncc,
                    nv,
                    ngayDat, 
                    rs.getDouble("TongTien"),
                    rs.getString("TrangThai"),
                    rs.getString("GhiChu")
                );
                return pdh;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Đã đổi tên: getChiTietTheoMaPhieu -> layChiTietTheoMaPhieu
    public List<ChiTietPhieuDatHang> layChiTietTheoMaPhieu(String maPhieu) {
        List<ChiTietPhieuDatHang> dsChiTiet = new ArrayList<>();
        
        // Đã sửa tên cột (tenThuoc, maThuoc)
        String sql = "SELECT ct.*, t.tenThuoc FROM ChiTietPhieuDatHang ct JOIN Thuoc t ON ct.MaThuoc = t.maThuoc WHERE ct.MaPhieu = ?"; 
        
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Thuoc thuoc = new Thuoc();
                thuoc.setMaThuoc(rs.getString("MaThuoc"));
                thuoc.setTenThuoc(rs.getString("tenThuoc")); 

                ChiTietPhieuDatHang ct = new ChiTietPhieuDatHang();
                ct.setThuoc(thuoc);
                ct.setSoLuong(rs.getInt("SoLuong"));
                ct.setDonGia(rs.getDouble("DonGia"));
                dsChiTiet.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTiet;
    }


    // === HÀM CHO NÚT "HỦY PHIẾU" ===
    // (Giữ nguyên tên, đã rõ nghĩa)
    public boolean huyPhieuDatHang(String maPhieu) {
        String sql = "UPDATE PhieuDatHang SET TrangThai = N'Đã hủy' WHERE MaPhieu = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maPhieu);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // === CÁC HÀM CẦN THÊM ===

    // Đã đổi tên: generateNewMaPhieu -> taoMaPhieuMoi
    public String taoMaPhieuMoi() {
        String prefix = "PDH";
        String sql = "SELECT TOP 1 MaPhieu FROM PhieuDatHang ORDER BY MaPhieu DESC";
        try (Connection con = ConnectDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String lastID = rs.getString("MaPhieu"); 
                try {
                    int number = Integer.parseInt(lastID.replace(prefix, ""));
                    number++;
                    return String.format("%s%03d", prefix, number); 
                } catch (NumberFormatException e) {
                     System.err.println("Lỗi parse mã phiếu cuối cùng: " + lastID);
                     return prefix + System.currentTimeMillis() % 1000; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prefix + "001"; // Nếu bảng rỗng
    }

    // (Giữ nguyên tên, đã rõ nghĩa)
    public boolean themPhieuDatHang(PhieuDatHang phieu) {
        if (phieu == null || phieu.getNhaCungCap() == null || phieu.getNhanVien() == null || phieu.getNgayDat() == null || phieu.getChiTietList() == null || phieu.getChiTietList().isEmpty()) {
             System.err.println("Lỗi thêm phiếu: Dữ liệu đầu vào không hợp lệ (null hoặc thiếu chi tiết).");
             return false;
        }

        Connection con = ConnectDB.getConnection();
        PreparedStatement psPhieu = null;
        PreparedStatement psChiTiet = null;
        String sqlPhieu = "INSERT INTO PhieuDatHang(MaPhieu, MaNCC, MaNhanVien, NgayDat, TongTien, TrangThai, GhiChu) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        // Câu SQL đúng (đã sửa lỗi ThanhTien)
        String sqlChiTiet = "INSERT INTO ChiTietPhieuDatHang(MaPhieu, MaThuoc, SoLuong, DonGia) " +
                           "VALUES (?, ?, ?, ?)";
        
        try {
            con.setAutoCommit(false); 

            // 1. Thêm thông tin chung của phiếu
            psPhieu = con.prepareStatement(sqlPhieu);
            psPhieu.setString(1, phieu.getMaPhieu());
            psPhieu.setString(2, phieu.getNhaCungCap().getMaNhaCungCap());
            psPhieu.setString(3, phieu.getNhanVien().getMaNV()); 
            psPhieu.setDate(4, Date.valueOf(phieu.getNgayDat()));
            psPhieu.setDouble(5, phieu.getTongTien()); 
            psPhieu.setString(6, phieu.getTrangThai());
            psPhieu.setString(7, phieu.getGhiChu());
            if (psPhieu.executeUpdate() == 0) {
                 throw new SQLException("Thêm phiếu đặt hàng thất bại.");
            }

            // 2. Thêm danh sách chi tiết
            psChiTiet = con.prepareStatement(sqlChiTiet);
            for (ChiTietPhieuDatHang ct : phieu.getChiTietList()) {
                 if (ct.getThuoc() == null) { 
                     throw new SQLException("Chi tiết phiếu chứa thuốc không hợp lệ (null).");
                 }
                psChiTiet.setString(1, phieu.getMaPhieu());
                psChiTiet.setString(2, ct.getThuoc().getMaThuoc());
                psChiTiet.setInt(3, ct.getSoLuong());
                psChiTiet.setDouble(4, ct.getDonGia());
                psChiTiet.addBatch(); 
            }
            psChiTiet.executeBatch(); 

            con.commit(); 
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (con != null) con.rollback(); 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
             try {
                if (psPhieu != null) psPhieu.close();
                if (psChiTiet != null) psChiTiet.close();
                if (con != null) {
                    con.setAutoCommit(true); 
                    con.close();
                }
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
        }
    }

    // Đã đổi tên: updatePhieuDatHang -> capNhatPhieuDatHang
    public boolean capNhatPhieuDatHang(PhieuDatHang phieu) {
        if (phieu == null || phieu.getMaPhieu() == null || phieu.getChiTietList() == null || phieu.getChiTietList().isEmpty()) {
            System.err.println("Lỗi cập nhật phiếu: Dữ liệu đầu vào không hợp lệ.");
            return false;
        }

        Connection con = ConnectDB.getConnection();
        PreparedStatement psUpdatePhieu = null;
        PreparedStatement psDeleteChiTiet = null;
        PreparedStatement psInsertChiTiet = null;

        String sqlUpdatePhieu = "UPDATE PhieuDatHang SET MaNCC=?, MaNhanVien=?, NgayDat=?, TongTien=?, TrangThai=?, GhiChu=? WHERE MaPhieu=?";
        String sqlDeleteChiTiet = "DELETE FROM ChiTietPhieuDatHang WHERE MaPhieu=?";
        
        // Câu SQL đúng (đã sửa lỗi ThanhTien)
        String sqlInsertChiTiet = "INSERT INTO ChiTietPhieuDatHang(MaPhieu, MaThuoc, SoLuong, DonGia) VALUES (?, ?, ?, ?)";

        try {
            con.setAutoCommit(false); // Bắt đầu Transaction

            // 1. Xóa tất cả chi tiết cũ
            psDeleteChiTiet = con.prepareStatement(sqlDeleteChiTiet);
            psDeleteChiTiet.setString(1, phieu.getMaPhieu());
            psDeleteChiTiet.executeUpdate();

            // 2. Thêm lại danh sách chi tiết mới
            psInsertChiTiet = con.prepareStatement(sqlInsertChiTiet);
            double tongTienMoi = 0; 
            
            for (ChiTietPhieuDatHang ct : phieu.getChiTietList()) {
                if (ct.getThuoc() == null) {
                    throw new SQLException("Chi tiết phiếu (cập nhật) chứa thuốc không hợp lệ (null).");
                }
                psInsertChiTiet.setString(1, phieu.getMaPhieu());
                psInsertChiTiet.setString(2, ct.getThuoc().getMaThuoc());
                psInsertChiTiet.setInt(3, ct.getSoLuong());
                psInsertChiTiet.setDouble(4, ct.getDonGia());
                
                tongTienMoi += ct.tinhThanhTien(); 
                
                psInsertChiTiet.addBatch();
            }
            psInsertChiTiet.executeBatch(); 

            // 3. Cập nhật phiếu chính
            psUpdatePhieu = con.prepareStatement(sqlUpdatePhieu);
            psUpdatePhieu.setString(1, phieu.getNhaCungCap().getMaNhaCungCap());
            psUpdatePhieu.setString(2, phieu.getNhanVien().getMaNV());
            psUpdatePhieu.setDate(3, Date.valueOf(phieu.getNgayDat()));
            psUpdatePhieu.setDouble(4, tongTienMoi); // Dùng tổng tiền mới
            psUpdatePhieu.setString(5, phieu.getTrangThai());
            psUpdatePhieu.setString(6, phieu.getGhiChu());
            psUpdatePhieu.setString(7, phieu.getMaPhieu()); // Tham số WHERE
            
            if (psUpdatePhieu.executeUpdate() == 0) {
                throw new SQLException("Cập nhật phiếu chính thất bại, không tìm thấy mã phiếu: " + phieu.getMaPhieu());
            }

            con.commit(); 
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (con != null) con.rollback(); 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (psDeleteChiTiet != null) psDeleteChiTiet.close();
                if (psInsertChiTiet != null) psInsertChiTiet.close();
                if (psUpdatePhieu != null) psUpdatePhieu.close();
                if (con != null) {
                    con.setAutoCommit(true); 
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}