package dao;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.NhanVien;
import entity.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class nhanVien_DAO {

    // === BỔ SUNG QUAN TRỌNG: Lấy nhân viên theo mã ===
    /**
     * Lấy thông tin chi tiết của một nhân viên dựa vào mã nhân viên.
     * Rất cần thiết cho chức năng "Xem chi tiết" và tải dữ liệu cho các đối tượng khác.
     * @param maNV Mã nhân viên cần tìm.
     * @return một đối tượng NhanVien hoặc null nếu không tìm thấy.
     */
    public NhanVien getNhanVienTheoMa(String maNV) {
        String sql = "SELECT nv.*, cv.tenChucVu, tk.tenTK " +
                     "FROM NhanVien nv " +
                     "JOIN ChucVu cv ON nv.chucVu = cv.maChucVu " +
                     "JOIN TaiKhoan tk ON nv.maTK = tk.maTK " +
                     "WHERE nv.maNV = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, maNV);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapNhanVien(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // === Thêm nhân viên ===
    public boolean insertNhanVien(NhanVien nv) {
        String sql = "INSERT INTO NhanVien(maNV, tenNV, ngaySinh, gioiTinh, chucVu, sdt, diaChi, anh, maTK) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getTenNV());
            ps.setDate(3, java.sql.Date.valueOf(nv.getNgaySinh()));
            ps.setString(4, nv.getGioiTinh());
            ps.setString(5, nv.getChucVu().getMaChucVu());
            ps.setString(6, nv.getSoDienThoai());
            ps.setString(7, nv.getDiaChi());
            ps.setString(8, nv.getAnh());
            ps.setString(9, nv.getTaiKhoan().getMaTK());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // === Cập nhật nhân viên ===
    public boolean updateNhanVien(NhanVien nv) {
        String sql = "UPDATE NhanVien SET tenNV=?, ngaySinh=?, gioiTinh=?, chucVu=?, sdt=?, diaChi=?, anh=?, maTK=? WHERE maNV=?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nv.getTenNV());
            ps.setDate(2, java.sql.Date.valueOf(nv.getNgaySinh()));
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getChucVu().getMaChucVu());
            ps.setString(5, nv.getSoDienThoai());
            ps.setString(6, nv.getDiaChi());
            ps.setString(7, nv.getAnh());
            ps.setString(8, nv.getTaiKhoan().getMaTK());
            ps.setString(9, nv.getMaNV());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // === Xóa nhân viên ===
    public boolean deleteNhanVien(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE maNV=?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maNV);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // === Lấy danh sách tất cả nhân viên ===
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        // CẢI TIẾN: Dùng JOIN để lấy thêm Tên Chức Vụ và Tên Tài Khoản
        String sql = "SELECT nv.*, cv.tenChucVu, tk.tenTK " +
                     "FROM NhanVien nv " +
                     "JOIN ChucVu cv ON nv.chucVu = cv.maChucVu " +
                     "JOIN TaiKhoan tk ON nv.maTK = tk.maTK";
        try (Connection con = ConnectDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapNhanVien(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // === Tìm kiếm nhân viên (tên, sđt, giới tính, chức vụ, địa chỉ) ===
    public List<NhanVien> searchNhanVien(String tenNV, String sdt, String gioiTinh, String maChucVu, String diaChi) {
        List<NhanVien> list = new ArrayList<>();
        // CẢI TIẾN: Dùng JOIN và tìm kiếm theo LIKE
        String sql = "SELECT nv.*, cv.tenChucVu, tk.tenTK " +
                     "FROM NhanVien nv " +
                     "JOIN ChucVu cv ON nv.chucVu = cv.maChucVu " +
                     "JOIN TaiKhoan tk ON nv.maTK = tk.maTK " +
                     "WHERE nv.tenNV LIKE ? AND nv.sdt LIKE ? AND nv.gioiTinh LIKE ? AND nv.chucVu LIKE ? AND nv.diaChi LIKE ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + tenNV + "%");
            ps.setString(2, "%" + sdt + "%");
            ps.setString(3, "%" + gioiTinh + "%");
            ps.setString(4, "%" + maChucVu + "%");
            ps.setString(5, "%" + diaChi + "%");

            try (ResultSet rs = ps.executeQuery()) {
                 while (rs.next()) {
                    list.add(mapNhanVien(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // === Ánh xạ dữ liệu từ ResultSet sang đối tượng NhanVien ===
    private NhanVien mapNhanVien(ResultSet rs) throws SQLException {
        NhanVien nv = new NhanVien();
        nv.setMaNV(rs.getString("maNV"));
        nv.setTenNV(rs.getString("tenNV"));
        nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
        nv.setGioiTinh(rs.getString("gioiTinh"));
        nv.setSoDienThoai(rs.getString("sdt"));
        nv.setDiaChi(rs.getString("diaChi"));
        nv.setAnh(rs.getString("anh"));

        // CẢI TIẾN: Lấy đầy đủ thông tin cho ChucVu và TaiKhoan
        ChucVu cv = new ChucVu();
        cv.setMaChucVu(rs.getString("chucVu"));
        cv.setTenChucVu(rs.getString("tenChucVu")); // Lấy tên chức vụ từ JOIN
        nv.setChucVu(cv);

        TaiKhoan tk = new TaiKhoan();
        tk.setMaTK(rs.getString("maTK"));
        tk.setTenTK(rs.getString("tenTK")); // Lấy tên tài khoản từ JOIN
        nv.setTaiKhoan(tk);

        return nv;
    }
    
    // === Sinh mã nhân viên tự động, không trùng ===
    public String generateNewMaNV() {
        String prefix = "NV";
        String sql = "SELECT TOP 1 maNV FROM NhanVien ORDER BY maNV DESC";
        try (Connection con = ConnectDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String lastID = rs.getString("maNV"); // VD: NV007
                int number = Integer.parseInt(lastID.replace(prefix, ""));
                number++;
                return String.format("%s%03d", prefix, number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prefix + "001"; // nếu bảng đang rỗng
    }
}