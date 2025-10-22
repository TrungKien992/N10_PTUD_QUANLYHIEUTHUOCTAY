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

// Đổi tên class thành phieuDatHang_DAO
public class phieuDatHang_DAO {

    // Khởi tạo các DAO cần thiết
    private nhaCungCap_DAO nccDao = new nhaCungCap_DAO();
    private thuoc_DAO thuocDao = new thuoc_DAO();
    private nhanVien_DAO nvDao = new nhanVien_DAO(); // Sử dụng DAO bạn cung cấp

    // === HÀM LẤY DANH SÁCH PHIẾU (CHO BẢNG CHÍNH) ===
    public List<PhieuDatHang> getAllPhieuDatHang() {
        List<PhieuDatHang> dsPhieu = new ArrayList<>();
        // Câu SQL JOIN thêm NhanVien
        String sql = "SELECT pdh.MaPhieu, pdh.MaNCC, ncc.TenNCC, pdh.MaNhanVien, nv.TenNV, " +
                     "pdh.NgayDat, pdh.TongTien, pdh.TrangThai " +
                     "FROM PhieuDatHang pdh " +
                     "JOIN NhaCungCap ncc ON pdh.MaNCC = ncc.MaNhaCungCap " +
                     "JOIN NhanVien nv ON pdh.MaNhanVien = nv.MaNV"; // Sửa join key thành MaNV
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap(); // Tạo đối tượng NCC
                ncc.setMaNhaCungCap(rs.getString("MaNCC"));
                ncc.setTenNhaCungCap(rs.getString("TenNCC"));

                NhanVien nv = new NhanVien(); // Tạo đối tượng NV cơ bản
                nv.setMaNV(rs.getString("MaNhanVien")); // Lấy mã NV từ phiếu
                nv.setTenNV(rs.getString("TenNV")); // Lấy tên NV từ join

                PhieuDatHang pdh = new PhieuDatHang();
                pdh.setMaPhieu(rs.getString("MaPhieu"));
                pdh.setNhaCungCap(ncc);
                pdh.setNhanVien(nv); // Set nhân viên
                // Kiểm tra null cho ngày đặt trước khi chuyển đổi
                Date ngayDatSQL = rs.getDate("NgayDat");
                if (ngayDatSQL != null) {
                    pdh.setNgayDat(ngayDatSQL.toLocalDate());
                } else {
                    pdh.setNgayDat(null); // Hoặc một giá trị mặc định nếu cần
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

    // Lấy thông tin chung của 1 phiếu
    public PhieuDatHang getPhieuDatHangTheoMa(String maPhieu) {
        // Lấy MaNhanVien từ bảng PhieuDatHang
        String sql = "SELECT MaPhieu, MaNCC, MaNhanVien, NgayDat, TongTien, TrangThai, GhiChu " +
                     "FROM PhieuDatHang WHERE MaPhieu = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maNCC = rs.getString("MaNCC");
                String maNV = rs.getString("MaNhanVien"); // Lấy mã nhân viên

                // Dùng DAO tương ứng để lấy đối tượng đầy đủ
                NhaCungCap ncc = nccDao.getNhaCungCapTheoMa(maNCC);
                NhanVien nv = nvDao.getNhanVienTheoMa(maNV); // Gọi hàm từ nhanVien_DAO

                // Kiểm tra null phòng trường hợp dữ liệu không nhất quán
                if (ncc == null) {
                    System.err.println("Cảnh báo: Không tìm thấy Nhà cung cấp mã " + maNCC + " cho phiếu " + maPhieu);
                    ncc = new NhaCungCap(maNCC); // Tạo NCC chỉ có mã
                }
                 if (nv == null) {
                    System.err.println("Cảnh báo: Không tìm thấy Nhân viên mã " + maNV + " cho phiếu " + maPhieu);
                    nv = new NhanVien();
                    nv.setMaNV(maNV); // Gán mã để biết
                }
                
                // Kiểm tra null cho ngày đặt
                LocalDate ngayDat = null;
                Date ngayDatSQL = rs.getDate("NgayDat");
                 if (ngayDatSQL != null) {
                    ngayDat = ngayDatSQL.toLocalDate();
                 }

                PhieuDatHang pdh = new PhieuDatHang(
                    rs.getString("MaPhieu"),
                    ncc,
                    nv, // Đối tượng NhanVien đầy đủ
                    ngayDat, // LocalDate đã kiểm tra null
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

    // Lấy danh sách chi tiết (thuốc) của 1 phiếu
    public List<ChiTietPhieuDatHang> getChiTietTheoMaPhieu(String maPhieu) {
        List<ChiTietPhieuDatHang> dsChiTiet = new ArrayList<>();
        String sql = "SELECT ct.*, t.TenThuoc FROM ChiTietPhieuDatHang ct JOIN Thuoc t ON ct.MaThuoc = t.MaThuoc WHERE ct.MaPhieu = ?"; // JOIN để lấy tên thuốc
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Tạo đối tượng Thuoc cơ bản từ thông tin chi tiết
                Thuoc thuoc = new Thuoc();
                thuoc.setMaThuoc(rs.getString("MaThuoc"));
                thuoc.setTenThuoc(rs.getString("TenThuoc")); // Lấy tên từ JOIN

                ChiTietPhieuDatHang ct = new ChiTietPhieuDatHang();
                // Không cần set PhieuDatHang ở đây để tránh vòng lặp vô hạn khi đọc
                ct.setThuoc(thuoc);
                ct.setSoLuong(rs.getInt("SoLuong"));
                ct.setDonGia(rs.getDouble("DonGia"));
                // Tính lại thành tiền để đảm bảo đúng
//                ct.setThanhTien(ct.getSoLuong() * ct.getDonGia());
                dsChiTiet.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTiet;
    }


    // === HÀM CHO NÚT "HỦY PHIẾU" ===
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

    /**
     * Hàm tạo mã phiếu đặt hàng mới, ví dụ: PDH001
     */
    public String generateNewMaPhieu() {
        String prefix = "PDH";
        String sql = "SELECT TOP 1 MaPhieu FROM PhieuDatHang ORDER BY MaPhieu DESC";
        try (Connection con = ConnectDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String lastID = rs.getString("MaPhieu"); // VD: PDH007
                // Thêm try-catch để xử lý trường hợp không parse được số
                try {
                    int number = Integer.parseInt(lastID.replace(prefix, ""));
                    number++;
                    return String.format("%s%03d", prefix, number); // %03d cho 3 chữ số
                } catch (NumberFormatException e) {
                     System.err.println("Lỗi parse mã phiếu cuối cùng: " + lastID);
                     // Có thể trả về mã dựa trên thời gian hoặc lỗi
                     return prefix + System.currentTimeMillis() % 1000; // Ví dụ mã tạm thời
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prefix + "001"; // Nếu bảng rỗng
    }

    /**
     * Hàm lưu phiếu đặt hàng mới vào CSDL (cả thông tin chung và chi tiết)
     * Cần xử lý Transaction để đảm bảo tính toàn vẹn dữ liệu.
     */
    public boolean themPhieuDatHang(PhieuDatHang phieu) {
        // Kiểm tra đầu vào cơ bản
        if (phieu == null || phieu.getNhaCungCap() == null || phieu.getNhanVien() == null || phieu.getNgayDat() == null || phieu.getChiTietList() == null || phieu.getChiTietList().isEmpty()) {
             System.err.println("Lỗi thêm phiếu: Dữ liệu đầu vào không hợp lệ (null hoặc thiếu chi tiết).");
             return false;
        }

        Connection con = ConnectDB.getConnection();
        PreparedStatement psPhieu = null;
        PreparedStatement psChiTiet = null;
        String sqlPhieu = "INSERT INTO PhieuDatHang(MaPhieu, MaNCC, MaNhanVien, NgayDat, TongTien, TrangThai, GhiChu) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlChiTiet = "INSERT INTO ChiTietPhieuDatHang(MaPhieu, MaThuoc, SoLuong, DonGia, ThanhTien) " +
                            "VALUES (?, ?, ?, ?, ?)";

        try {
            con.setAutoCommit(false); // Bắt đầu Transaction

            // 1. Thêm thông tin chung của phiếu
            psPhieu = con.prepareStatement(sqlPhieu);
            psPhieu.setString(1, phieu.getMaPhieu());
            psPhieu.setString(2, phieu.getNhaCungCap().getMaNhaCungCap());
            psPhieu.setString(3, phieu.getNhanVien().getMaNV()); // Lưu mã nhân viên
            psPhieu.setDate(4, Date.valueOf(phieu.getNgayDat()));
            psPhieu.setDouble(5, phieu.getTongTien()); // Nên tính lại tổng tiền từ chi tiết trước khi lưu
            psPhieu.setString(6, phieu.getTrangThai());
            psPhieu.setString(7, phieu.getGhiChu());
            if (psPhieu.executeUpdate() == 0) {
                 throw new SQLException("Thêm phiếu đặt hàng thất bại.");
            }

            // 2. Thêm danh sách chi tiết
            psChiTiet = con.prepareStatement(sqlChiTiet);
            for (ChiTietPhieuDatHang ct : phieu.getChiTietList()) {
                 if (ct.getThuoc() == null) { // Kiểm tra thuốc trong chi tiết
                    throw new SQLException("Chi tiết phiếu chứa thuốc không hợp lệ (null).");
                 }
                psChiTiet.setString(1, phieu.getMaPhieu());
                psChiTiet.setString(2, ct.getThuoc().getMaThuoc());
                psChiTiet.setInt(3, ct.getSoLuong());
                psChiTiet.setDouble(4, ct.getDonGia());
                // Tính lại thành tiền để đảm bảo nhất quán
                double thanhTien = ct.getSoLuong() * ct.getDonGia();
                psChiTiet.setDouble(5, thanhTien);
                psChiTiet.addBatch(); // Thêm vào batch để thực thi cùng lúc
            }
            psChiTiet.executeBatch(); // Thực thi batch

            con.commit(); // Hoàn tất Transaction thành công
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (con != null) con.rollback(); // Rollback nếu có lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
             try {
                if (psPhieu != null) psPhieu.close();
                if (psChiTiet != null) psChiTiet.close();
                if (con != null) {
                    con.setAutoCommit(true); // Trả lại trạng thái AutoCommit
                    con.close();
                }
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
        }
    }

    // (Bạn cũng cần hàm cập nhật phiếu, tương tự hàm thêm nhưng dùng UPDATE và xóa chi tiết cũ trước khi thêm chi tiết mới)
    // Ví dụ hàm cập nhật (cần hoàn thiện):
    /*
    public boolean updatePhieuDatHang(PhieuDatHang phieu) {
        Connection con = ConnectDB.getConnection();
        // ... (khai báo PreparedStatement)
        String sqlUpdatePhieu = "UPDATE PhieuDatHang SET MaNCC=?, MaNhanVien=?, NgayDat=?, TongTien=?, TrangThai=?, GhiChu=? WHERE MaPhieu=?";
        String sqlDeleteChiTiet = "DELETE FROM ChiTietPhieuDatHang WHERE MaPhieu=?";
        String sqlInsertChiTiet = "INSERT INTO ChiTietPhieuDatHang(MaPhieu, MaThuoc, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?)";

        try {
            con.setAutoCommit(false);

            // 1. Xóa chi tiết cũ
            PreparedStatement psDelete = con.prepareStatement(sqlDeleteChiTiet);
            psDelete.setString(1, phieu.getMaPhieu());
            psDelete.executeUpdate();
            psDelete.close();

            // 2. Thêm chi tiết mới (giống hàm themPhieuDatHang)
            PreparedStatement psInsertChiTiet = con.prepareStatement(sqlInsertChiTiet);
            // ... (Vòng lặp thêm batch chi tiết)
            psInsertChiTiet.executeBatch();
            psInsertChiTiet.close();

            // 3. Cập nhật thông tin phiếu chính
            PreparedStatement psUpdatePhieu = con.prepareStatement(sqlUpdatePhieu);
            // ... (Set các tham số cho psUpdatePhieu)
            psUpdatePhieu.setString(7, phieu.getMaPhieu()); // Tham số WHERE
            if (psUpdatePhieu.executeUpdate() == 0) {
                 throw new SQLException("Cập nhật phiếu chính thất bại.");
            }
            psUpdatePhieu.close();

            con.commit();
            return true;

        } catch (SQLException e) {
            // ... (Rollback và xử lý lỗi)
            return false;
        } finally {
            // ... (Đóng connection, trả AutoCommit)
        }
    }
    */
}