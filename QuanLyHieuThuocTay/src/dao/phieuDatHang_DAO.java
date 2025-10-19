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
import entity.ChiTietPhieuDatHang;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.PhieuDatHang;

public class phieuDatHang_DAO {
    
    // Khai báo các DAO liên quan (bạn đã cung cấp)
    private nhaCungCap_DAO nccDAO;
    private nhanVien_DAO nvDAO;
    private chiTietPhieuDatHang_DAO ctpdhDAO;

    public phieuDatHang_DAO() {
        // Khởi tạo các DAO
        nccDAO = new nhaCungCap_DAO();
        nvDAO = new nhanVien_DAO(); 
        ctpdhDAO = new chiTietPhieuDatHang_DAO();
    }

    /**
     * Lấy danh sách tất cả phiếu đặt hàng (cho bảng chính)
     */
    public List<PhieuDatHang> getAllPhieuDatHang() {
        List<PhieuDatHang> dsPDH = new ArrayList<>();
        String sql = "SELECT * FROM PhieuDatHang"; 
                     
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String maPDH = rs.getString("maPhieuDat");
                LocalDate ngayDat = rs.getDate("ngayDat").toLocalDate();
                String trangThai = rs.getString("trangThai");
                String ghiChu = rs.getString("ghiChu");

                // Lấy đối tượng NhaCungCap đầy đủ
                NhaCungCap ncc = nccDAO.getNhaCungCapTheoMa(rs.getString("maNhaCungCap"));
                
                // Lấy đối tượng NhanVien đầy đủ
                NhanVien nv = nvDAO.getNhanVienTheoMa(rs.getString("maNhanVien"));

                // Dòng này sẽ hết "tô đỏ" vì constructor đã chính xác
                PhieuDatHang pdh = new PhieuDatHang(maPDH, ncc, nv, ngayDat, ghiChu, trangThai);
                
                // Lấy danh sách chi tiết từ CSDL
                List<ChiTietPhieuDatHang> dsCT = ctpdhDAO.getChiTietTheoMaPhieu(maPDH);
                
                // Gán danh sách chi tiết vào phiếu để hàm pdh.getTongTien() chạy được
                pdh.setDsChiTiet(dsCT); 

                dsPDH.add(pdh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPDH;
    }

    /**
     * Lấy 1 phiếu đặt hàng đầy đủ theo mã (cho dialog Xem/Sửa)
     */
    public PhieuDatHang getPhieuDatHangTheoMa(String maPhieu) {
        String sql = "SELECT * FROM PhieuDatHang WHERE maPhieuDat = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, maPhieu);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LocalDate ngayDat = rs.getDate("ngayDat").toLocalDate();
                    String trangThai = rs.getString("trangThai");
                    String ghiChu = rs.getString("ghiChu");

                    NhaCungCap ncc = nccDAO.getNhaCungCapTheoMa(rs.getString("maNhaCungCap"));
                    NhanVien nv = nvDAO.getNhanVienTheoMa(rs.getString("maNhanVien"));
                    
                    PhieuDatHang pdh = new PhieuDatHang(maPhieu, ncc, nv, ngayDat, ghiChu, trangThai);

                    // Lấy danh sách chi tiết và gán vào phiếu
                    List<ChiTietPhieuDatHang> dsCT = ctpdhDAO.getChiTietTheoMaPhieu(maPhieu);
                    pdh.setDsChiTiet(dsCT);
                    
                    return pdh;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Thêm mới 1 phiếu đặt hàng (Sử dụng TRANSACTION)
     */
    public boolean themPhieuDatHang(PhieuDatHang pdh) {
        Connection con = ConnectDB.getConnection();
        String sqlPhieu = "INSERT INTO PhieuDatHang(maPhieuDat, maNhaCungCap, maNhanVien, ngayDat, ghiChu, trangThai) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            // --- BẮT ĐẦU TRANSACTION ---
            con.setAutoCommit(false); 
            
            // 1. Thêm Phiếu Đặt Hàng (bảng cha)
            try (PreparedStatement psPhieu = con.prepareStatement(sqlPhieu)) {
                psPhieu.setString(1, pdh.getMaPhieuDat());
                psPhieu.setString(2, pdh.getNhaCungCap().getMaNhaCungCap());
                
                // --- ĐÃ SỬA ---
                // Sử dụng getMaNV() từ entity NhanVien bạn đã cung cấp
                psPhieu.setString(3, pdh.getNhanVien().getMaNV()); 
                // --- KẾT THÚC SỬA ---

                psPhieu.setDate(4, java.sql.Date.valueOf(pdh.getNgayDat()));
                psPhieu.setString(5, pdh.getGhiChu());
                psPhieu.setString(6, pdh.getTrangThai());
                
                if (psPhieu.executeUpdate() == 0) {
                    throw new SQLException("Thêm phiếu đặt hàng thất bại.");
                }
            }

            // 2. Thêm tất cả Chi Tiết Phiếu Đặt Hàng (bảng con)
            for (ChiTietPhieuDatHang ct : pdh.getDsChiTiet()) {
                if (!ctpdhDAO.themChiTiet(ct, pdh.getMaPhieuDat(), con)) {
                    throw new SQLException("Thêm chi tiết phiếu đặt hàng thất bại.");
                }
            }
            
            // --- KẾT THÚC TRANSACTION (THÀNH CÔNG) ---
            con.commit(); 
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // --- ROLLBACK (THẤT BẠI) ---
                con.rollback(); 
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } finally {
            try {
                con.setAutoCommit(true); // Trả lại trạng thái mặc định
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Xóa phiếu đặt hàng (Sử dụng TRANSACTION)
     */
    public boolean xoaPhieuDatHang(String maPhieu) {
        Connection con = ConnectDB.getConnection();
        String sqlChiTiet = "DELETE FROM ChiTietPhieuDatHang WHERE maPhieuDat = ?";
        String sqlPhieu = "DELETE FROM PhieuDatHang WHERE maPhieuDat = ?";

        try {
            // --- BẮT ĐẦU TRANSACTION ---
            con.setAutoCommit(false);

            // 1. Xóa tất cả Chi Tiết trước (vì khóa ngoại)
            try (PreparedStatement psChiTiet = con.prepareStatement(sqlChiTiet)) {
                psChiTiet.setString(1, maPhieu);
                psChiTiet.executeUpdate(); 
            }
            
            // 2. Xóa Phiếu Đặt Hàng
            try (PreparedStatement psPhieu = con.prepareStatement(sqlPhieu)) {
                psPhieu.setString(1, maPhieu);
                if (psPhieu.executeUpdate() == 0) {
                     throw new SQLException("Xóa phiếu đặt hàng thất bại.");
                }
            }
            
            // --- COMMIT (THÀNH CÔNG) ---
            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // --- ROLLBACK (THẤT BẠI) ---
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } finally {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Cập nhật phiếu đặt hàng (Sử dụng TRANSACTION)
     */
    public boolean capNhatPhieuDatHang(PhieuDatHang pdh) {
        Connection con = ConnectDB.getConnection();
        String sqlPhieu = "UPDATE PhieuDatHang SET maNhaCungCap = ?, maNhanVien = ?, ngayDat = ?, ghiChu = ?, trangThai = ? WHERE maPhieuDat = ?";
        String sqlXoaChiTiet = "DELETE FROM ChiTietPhieuDatHang WHERE maPhieuDat = ?";

        try {
            // --- BẮT ĐẦU TRANSACTION ---
            con.setAutoCommit(false);
            
            // 1. Cập nhật thông tin Phiếu Đặt Hàng
            try (PreparedStatement psPhieu = con.prepareStatement(sqlPhieu)) {
                psPhieu.setString(1, pdh.getNhaCungCap().getMaNhaCungCap());

                // --- ĐÃ SỬA ---
                // Sử dụng getMaNV() từ entity NhanVien bạn đã cung cấp
                psPhieu.setString(2, pdh.getNhanVien().getMaNV());
                // --- KẾT THÚC SỬA ---
                
                psPhieu.setDate(3, java.sql.Date.valueOf(pdh.getNgayDat()));
                psPhieu.setString(4, pdh.getGhiChu());
                psPhieu.setString(5, pdh.getTrangThai());
                psPhieu.setString(6, pdh.getMaPhieuDat()); // MÃ Ở CUỐI (WHERE)
                
                if (psPhieu.executeUpdate() == 0) {
                    throw new SQLException("Cập nhật phiếu đặt hàng thất bại.");
                }
            }

            // 2. Xóa hết Chi Tiết cũ
            try (PreparedStatement psXoaCT = con.prepareStatement(sqlXoaChiTiet)) {
                psXoaCT.setString(1, pdh.getMaPhieuDat());
                psXoaCT.executeUpdate();
            }

            // 3. Thêm lại Chi Tiết mới
            for (ChiTietPhieuDatHang ct : pdh.getDsChiTiet()) {
                if (!ctpdhDAO.themChiTiet(ct, pdh.getMaPhieuDat(), con)) {
                    throw new SQLException("Cập nhật chi tiết phiếu đặt hàng thất bại.");
                }
            }
            
            // --- COMMIT (THÀNH CÔNG) ---
            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // --- ROLLBACK (THẤT BẠI) ---
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } finally {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Tạo mã phiếu đặt hàng mới (ví dụ: PDH001)
     */
    public String generateNewMaPhieu() {
        String prefix = "PDH";
        String sql = "SELECT TOP 1 maPhieuDat FROM PhieuDatHang ORDER BY maPhieuDat DESC";
        try (Connection con = ConnectDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String lastID = rs.getString("maPhieuDat");
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