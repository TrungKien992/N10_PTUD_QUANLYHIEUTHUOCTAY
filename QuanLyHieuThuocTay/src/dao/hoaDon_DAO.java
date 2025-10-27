package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.Thue;

public class hoaDon_DAO {

    // --- CÁC HÀM CŨ (getAllHoaDon, themHoaDon, generateNewMaHD, getHoaDonByMa,
    //               getAllHoaDonChiTietForTable, searchHoaDonChiTiet) ---
    // --- GIỮ NGUYÊN NHƯ FILE ĐẠI CA ĐÃ GỬI ---
    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        String sql = "SELECT *, tienKhachDua, tienThua FROM HoaDon";
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

                thue_DAO thue_dao = new thue_DAO();
                Thue thue = thue_dao.getThueByMa(rs.getString("maThue"));
                hd.setThue(thue);

                khuyenMai_DAO km_dao = new khuyenMai_DAO();
                KhuyenMai km = km_dao.getKhuyenMaiByMa(rs.getString("maKM"));
                hd.setKhuyenMai(km);

                hd.setTienKhachDua(rs.getDouble("tienKhachDua"));
                hd.setTienThua(rs.getDouble("tienThua"));

                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    public boolean themHoaDon(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (maHD, ngayLap, maKM, maThue, maNV, maKH, tienKhachDua, tienThua) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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

            stmt.setDouble(7, hd.getTienKhachDua());
            stmt.setDouble(8, hd.getTienThua());

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
                    try {
                        int num = Integer.parseInt(maxMaHD.substring(2)) + 1;
                        maHD = String.format("HD%03d", num);
                    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maHD;
    }

    public HoaDon getHoaDonByMa(String maHD) {
        HoaDon hd = null;
        String sql = "SELECT *, tienKhachDua, tienThua FROM HoaDon WHERE maHD = ?";
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

                    hd.setTienKhachDua(rs.getDouble("tienKhachDua"));
                    hd.setTienThua(rs.getDouble("tienThua"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hd;
    }

    public List<Object[]> getAllHoaDonChiTietForTable() {
        List<Object[]> ds = new ArrayList<>();
        String sql = "SELECT hd.maHD, kh.maKH, hd.ngayLap, kh.tenKH, kh.sdt, " +
                     "nv.tenNV, nv.sdt AS sdtNV, " +
                     "((SUM(ct.soLuong * t.giaBan) * (1 - ISNULL(km.giaTri, 0)/100.0)) * (1 + th.tiLe)) AS TongTien " + // Sử dụng 100.0
                     "FROM HoaDon hd " +
                     "LEFT JOIN KhachHang kh ON hd.maKH = kh.maKH " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
                     "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
                     "JOIN Thue th ON hd.maThue = th.maThue " +
                     "LEFT JOIN KhuyenMai km ON hd.maKM = km.maKM " +
                     "GROUP BY hd.maHD, kh.maKH, hd.ngayLap, kh.tenKH, kh.sdt, nv.tenNV, nv.sdt, th.tiLe, km.giaTri " +
                     "ORDER BY hd.ngayLap DESC, hd.maHD DESC";

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

    public List<Object[]> searchHoaDonChiTiet(String maHD, String tenKH, String sdtKH, String tenNV, String sdtNV, LocalDate ngayLap) {
        List<Object[]> ds = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder(
            "SELECT hd.maHD, kh.maKH, hd.ngayLap, kh.tenKH, kh.sdt, " +
            "nv.tenNV, nv.sdt AS sdtNV, " +
            "((SUM(ct.soLuong * t.giaBan) * (1 - ISNULL(km.giaTri, 0)/100.0)) * (1 + th.tiLe)) AS TongTien " + // Sử dụng 100.0
            "FROM HoaDon hd " +
            "LEFT JOIN KhachHang kh ON hd.maKH = kh.maKH " +
            "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
            "JOIN ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
            "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
            "JOIN Thue th ON hd.maThue = th.maThue " +
            "LEFT JOIN KhuyenMai km ON hd.maKM = km.maKM " +
            "WHERE hd.maHD LIKE ? " +
            "AND (kh.tenKH LIKE ? OR (hd.maKH IS NULL AND ? LIKE '%%')) " +
            "AND (kh.sdt LIKE ? OR (hd.maKH IS NULL AND ? LIKE '%%')) " +
            "AND nv.tenNV LIKE ? " +
            "AND nv.sdt LIKE ? "
        );

        List<Object> params = new ArrayList<>();
        params.add("%" + maHD + "%");
        params.add("%" + tenKH + "%");
        params.add("%" + tenKH + "%");
        params.add("%" + sdtKH + "%");
        params.add("%" + sdtKH + "%");
        params.add("%" + tenNV + "%");
        params.add("%" + sdtNV + "%");

        if (ngayLap != null) {
            sqlBuilder.append(" AND hd.ngayLap = ? ");
            params.add(Date.valueOf(ngayLap));
        }

        sqlBuilder.append("GROUP BY hd.maHD, kh.maKH, hd.ngayLap, kh.tenKH, kh.sdt, nv.tenNV, nv.sdt, th.tiLe, km.giaTri ")
                  .append("ORDER BY hd.ngayLap DESC, hd.maHD DESC");

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
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

    // --- CÁC HÀM THỐNG KÊ (ĐÃ CẬP NHẬT/BỔ SUNG) ---

    /**
     * Lấy danh sách hóa đơn chi tiết trong một ngày cụ thể (cho tab "Theo Ngày").
     * Có thể lọc theo nhân viên.
     */
    public List<Object[]> getHoaDonChiTietTrongNgay(LocalDate ngay, String maNV) {
        List<Object[]> ds = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder(
            "SELECT hd.maHD, kh.sdt, kh.tenKH, nv.maNV, nv.tenNV, " +
            "((SUM(ct.soLuong * t.giaBan) * (1 - ISNULL(km.giaTri, 0)/100.0)) * (1 + th.tiLe)) AS TongTien " + // Sử dụng 100.0
            "FROM HoaDon hd " +
            "LEFT JOIN KhachHang kh ON hd.maKH = kh.maKH " +
            "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
            "JOIN ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
            "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
            "JOIN Thue th ON hd.maThue = th.maThue " +
            "LEFT JOIN KhuyenMai km ON hd.maKM = km.maKM " +
            "WHERE hd.ngayLap = ? "
        );
        List<Object> params = new ArrayList<>();
        params.add(Date.valueOf(ngay));

        if (maNV != null && !maNV.isEmpty()) {
            sqlBuilder.append(" AND hd.maNV = ? ");
            params.add(maNV);
        }

        sqlBuilder.append("GROUP BY hd.maHD, kh.sdt, kh.tenKH, nv.maNV, nv.tenNV, th.tiLe, km.giaTri ")
                  .append("ORDER BY hd.maHD");

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new Object[] {
                        rs.getString("maHD"),
                        rs.getString("sdt") != null ? rs.getString("sdt") : "N/A",
                        rs.getString("tenKH") != null ? rs.getString("tenKH") : "Khách vãng lai",
                        rs.getString("maNV"),
                        rs.getString("tenNV"),
                        rs.getDouble("TongTien")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    // Overload không lọc NV
    public List<Object[]> getHoaDonChiTietTrongNgay(LocalDate ngay) {
        return getHoaDonChiTietTrongNgay(ngay, null);
    }


    /**
     * Thống kê tổng số hóa đơn và tổng tiền theo từng ngày trong tháng/năm.
     * Có thể lọc theo nhân viên.
     */
    public List<Object[]> getThongKeTheoNgayTrongThang(int thang, int nam, String maNV) {
        List<Object[]> dsThongKe = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder(
            "SELECT DAY(ngayLap) AS Ngay, COUNT(DISTINCT maHD) AS TongSoHD, SUM(TongTienNgay) AS TongTien " + // Use COUNT(DISTINCT maHD)
            "FROM (" +
                "SELECT hd.ngayLap, hd.maHD, hd.maNV, " + // Thêm maNV vào subquery
                "((SUM(ct.soLuong * t.giaBan) * (1 - ISNULL(km.giaTri, 0)/100.0)) * (1 + th.tiLe)) AS TongTienNgay " + // Sử dụng 100.0
                "FROM HoaDon hd " +
                "JOIN ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
                "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
                "JOIN Thue th ON hd.maThue = th.maThue " +
                "LEFT JOIN KhuyenMai km ON hd.maKM = km.maKM " +
                "WHERE MONTH(hd.ngayLap) = ? AND YEAR(hd.ngayLap) = ? "
        );
        List<Object> params = new ArrayList<>();
        params.add(thang);
        params.add(nam);

        if (maNV != null && !maNV.isEmpty()) {
            sqlBuilder.append(" AND hd.maNV = ? "); // Thêm lọc NV vào subquery
            params.add(maNV);
        }

        sqlBuilder.append("GROUP BY hd.ngayLap, hd.maHD, hd.maNV, th.tiLe, km.giaTri" + // Thêm maNV vào GROUP BY subquery
                     ") AS HoaDonDaTinhTong " +
                     "GROUP BY DAY(ngayLap) " +
                     "ORDER BY Ngay");

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    dsThongKe.add(new Object[]{
                        rs.getInt("Ngay"),
                        rs.getInt("TongSoHD"),
                        rs.getDouble("TongTien")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsThongKe;
    }
     // Overload không lọc NV
    public List<Object[]> getThongKeTheoNgayTrongThang(int thang, int nam) {
        return getThongKeTheoNgayTrongThang(thang, nam, null);
    }


     /**
     * Thống kê tổng số hóa đơn và tổng tiền theo từng tháng trong năm.
     * Có thể lọc theo nhân viên.
     */
    public List<Object[]> getThongKeTheoThangTrongNam(int nam, String maNV) {
        List<Object[]> dsThongKe = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder(
             "SELECT MONTH(ngayLap) AS Thang, COUNT(DISTINCT maHD) AS TongSoHD, SUM(TongTienThang) AS TongTien " + // Use COUNT(DISTINCT maHD)
             "FROM (" +
                 "SELECT hd.ngayLap, hd.maHD, hd.maNV, " + // Thêm maNV
                 "((SUM(ct.soLuong * t.giaBan) * (1 - ISNULL(km.giaTri, 0)/100.0)) * (1 + th.tiLe)) AS TongTienThang " + // Sử dụng 100.0
                 "FROM HoaDon hd " +
                 "JOIN ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
                 "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
                 "JOIN Thue th ON hd.maThue = th.maThue " +
                 "LEFT JOIN KhuyenMai km ON hd.maKM = km.maKM " +
                 "WHERE YEAR(hd.ngayLap) = ? "
        );
        List<Object> params = new ArrayList<>();
        params.add(nam);

        if (maNV != null && !maNV.isEmpty()) {
            sqlBuilder.append(" AND hd.maNV = ? "); // Thêm lọc NV
            params.add(maNV);
        }

        sqlBuilder.append("GROUP BY hd.ngayLap, hd.maHD, hd.maNV, th.tiLe, km.giaTri" + // Thêm maNV
                     ") AS HoaDonDaTinhTongNam " +
                     "GROUP BY MONTH(ngayLap) " +
                     "ORDER BY Thang");

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    dsThongKe.add(new Object[]{
                        rs.getInt("Thang"),
                        rs.getInt("TongSoHD"),
                        rs.getDouble("TongTien")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsThongKe;
    }
    // Overload không lọc NV
    public List<Object[]> getThongKeTheoThangTrongNam(int nam) {
        return getThongKeTheoThangTrongNam(nam, null);
    }


    /** Lấy tổng số hóa đơn và tổng tiền trong MỘT ngày. Có thể lọc theo nhân viên. */
    public Map<String, Object> getTongKetTrongNgay(LocalDate ngay, String maNV) {
        Map<String, Object> result = new HashMap<>();
        result.put("tongSoHD", 0);
        result.put("tongTien", 0.0);

        StringBuilder sqlBuilder = new StringBuilder(
            "SELECT COUNT(DISTINCT maHD) AS TongSoHD, SUM(TongTienNgay) AS TongTien " + // Use COUNT(DISTINCT maHD)
            "FROM (" +
                "SELECT hd.maHD, hd.maNV, " + // Thêm maNV
                "((SUM(ct.soLuong * t.giaBan) * (1 - ISNULL(km.giaTri, 0)/100.0)) * (1 + th.tiLe)) AS TongTienNgay " + // Sử dụng 100.0
                "FROM HoaDon hd " +
                "JOIN ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
                "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
                "JOIN Thue th ON hd.maThue = th.maThue " +
                "LEFT JOIN KhuyenMai km ON hd.maKM = km.maKM " +
                "WHERE hd.ngayLap = ? "
        );
        List<Object> params = new ArrayList<>();
        params.add(Date.valueOf(ngay));

        if (maNV != null && !maNV.isEmpty()) {
            sqlBuilder.append(" AND hd.maNV = ? "); // Thêm lọc NV
            params.add(maNV);
        }

        sqlBuilder.append("GROUP BY hd.maHD, hd.maNV, th.tiLe, km.giaTri" + // Thêm maNV
                     ") AS HoaDonDaTinhTong");

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result.put("tongSoHD", rs.getInt("TongSoHD"));
                    result.put("tongTien", rs.getDouble("TongTien"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    // Overload không lọc NV
    public Map<String, Object> getTongKetTrongNgay(LocalDate ngay) {
        return getTongKetTrongNgay(ngay, null);
    }


     /** Lấy tổng số hóa đơn và tổng tiền trong MỘT tháng/năm. Có thể lọc theo nhân viên. */
    public Map<String, Object> getTongKetTrongThang(int thang, int nam, String maNV) {
        Map<String, Object> result = new HashMap<>();
        result.put("tongSoHD", 0);
        result.put("tongTien", 0.0);

        StringBuilder sqlBuilder = new StringBuilder(
            "SELECT COUNT(DISTINCT maHD) AS TongSoHD, SUM(TongTienThang) AS TongTien " + // Use COUNT(DISTINCT maHD)
            "FROM (" +
                "SELECT hd.maHD, hd.maNV, " + // Thêm maNV
                "((SUM(ct.soLuong * t.giaBan) * (1 - ISNULL(km.giaTri, 0)/100.0)) * (1 + th.tiLe)) AS TongTienThang " + // Sử dụng 100.0
                "FROM HoaDon hd " +
                "JOIN ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
                "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
                "JOIN Thue th ON hd.maThue = th.maThue " +
                "LEFT JOIN KhuyenMai km ON hd.maKM = km.maKM " +
                "WHERE MONTH(hd.ngayLap) = ? AND YEAR(hd.ngayLap) = ? "
        );
        List<Object> params = new ArrayList<>();
        params.add(thang);
        params.add(nam);

        if (maNV != null && !maNV.isEmpty()) {
            sqlBuilder.append(" AND hd.maNV = ? "); // Thêm lọc NV
            params.add(maNV);
        }

        sqlBuilder.append("GROUP BY hd.maHD, hd.maNV, th.tiLe, km.giaTri" + // Thêm maNV
                     ") AS HoaDonDaTinhTong");

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result.put("tongSoHD", rs.getInt("TongSoHD"));
                    result.put("tongTien", rs.getDouble("TongTien"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    // Overload không lọc NV
    public Map<String, Object> getTongKetTrongThang(int thang, int nam) {
        return getTongKetTrongThang(thang, nam, null);
    }


     /** Lấy tổng số hóa đơn và tổng tiền trong MỘT năm. Có thể lọc theo nhân viên. */
    public Map<String, Object> getTongKetTrongNam(int nam, String maNV) {
        Map<String, Object> result = new HashMap<>();
        result.put("tongSoHD", 0);
        result.put("tongTien", 0.0);

        StringBuilder sqlBuilder = new StringBuilder(
             "SELECT COUNT(DISTINCT maHD) AS TongSoHD, SUM(TongTienNam) AS TongTien " + // Use COUNT(DISTINCT maHD)
             "FROM (" +
                 "SELECT hd.maHD, hd.maNV, " + // Thêm maNV
                 "((SUM(ct.soLuong * t.giaBan) * (1 - ISNULL(km.giaTri, 0)/100.0)) * (1 + th.tiLe)) AS TongTienNam " + // Sử dụng 100.0
                 "FROM HoaDon hd " +
                 "JOIN ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
                 "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
                 "JOIN Thue th ON hd.maThue = th.maThue " +
                 "LEFT JOIN KhuyenMai km ON hd.maKM = km.maKM " +
                 "WHERE YEAR(hd.ngayLap) = ? "
        );
        List<Object> params = new ArrayList<>();
        params.add(nam);

        if (maNV != null && !maNV.isEmpty()) {
            sqlBuilder.append(" AND hd.maNV = ? "); // Thêm lọc NV
            params.add(maNV);
        }

        sqlBuilder.append("GROUP BY hd.maHD, hd.maNV, th.tiLe, km.giaTri" + // Thêm maNV
                     ") AS HoaDonDaTinhTong");

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result.put("tongSoHD", rs.getInt("TongSoHD"));
                    result.put("tongTien", rs.getDouble("TongTien"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
     // Overload không lọc NV
     public Map<String, Object> getTongKetTrongNam(int nam) {
        return getTongKetTrongNam(nam, null);
    }

     // === THÊM MỚI: CÁC HÀM LẤY CHI TIẾT HÓA ĐƠN THEO THÁNG/NĂM ===
     // Cần thiết nếu muốn tính tổng trong Controller

     /** Lấy danh sách tất cả hóa đơn chi tiết trong tháng/năm */
     public List<Object[]> getHoaDonChiTietTrongThangNam(int thang, int nam) {
         return searchHoaDonChiTiet("", "", "", "", "", null, thang, nam); // Gọi hàm search mở rộng
     }

     /** Lấy danh sách tất cả hóa đơn chi tiết trong năm */
     public List<Object[]> getHoaDonChiTietTrongNam(int nam) {
         return searchHoaDonChiTiet("", "", "", "", "", null, 0, nam); // Gọi hàm search mở rộng
     }

     /**
      * Hàm tìm kiếm mở rộng, có thêm lọc theo tháng và năm.
      * Nếu thang = 0, chỉ lọc theo năm.
      */
     public List<Object[]> searchHoaDonChiTiet(String maHD, String tenKH, String sdtKH, String tenNV, String sdtNV, LocalDate ngayLap, int thang, int nam) {
        List<Object[]> ds = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder(
            "SELECT hd.maHD, kh.maKH, hd.ngayLap, kh.tenKH, kh.sdt, " +
            "nv.maNV, nv.tenNV, nv.sdt AS sdtNV, " + // Thêm maNV vào SELECT
            "((SUM(ct.soLuong * t.giaBan) * (1 - ISNULL(km.giaTri, 0)/100.0)) * (1 + th.tiLe)) AS TongTien " + // Sử dụng 100.0
            "FROM HoaDon hd " +
            "LEFT JOIN KhachHang kh ON hd.maKH = kh.maKH " +
            "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
            "JOIN ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
            "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
            "JOIN Thue th ON hd.maThue = th.maThue " +
            "LEFT JOIN KhuyenMai km ON hd.maKM = km.maKM " +
            "WHERE hd.maHD LIKE ? " +
            "AND (kh.tenKH LIKE ? OR (hd.maKH IS NULL AND ? LIKE '%%')) " +
            "AND (kh.sdt LIKE ? OR (hd.maKH IS NULL AND ? LIKE '%%')) " +
            "AND nv.tenNV LIKE ? " +
            "AND nv.sdt LIKE ? "
        );

        List<Object> params = new ArrayList<>();
        params.add("%" + maHD + "%");
        params.add("%" + tenKH + "%");
        params.add("%" + tenKH + "%");
        params.add("%" + sdtKH + "%");
        params.add("%" + sdtKH + "%");
        params.add("%" + tenNV + "%");
        params.add("%" + sdtNV + "%");

        if (ngayLap != null) {
            sqlBuilder.append(" AND hd.ngayLap = ? ");
            params.add(Date.valueOf(ngayLap));
        }
        if (thang > 0) {
             sqlBuilder.append(" AND MONTH(hd.ngayLap) = ? ");
             params.add(thang);
        }
        if (nam > 0) {
            sqlBuilder.append(" AND YEAR(hd.ngayLap) = ? ");
            params.add(nam);
        }


        sqlBuilder.append("GROUP BY hd.maHD, kh.maKH, hd.ngayLap, kh.tenKH, kh.sdt, nv.maNV, nv.tenNV, nv.sdt, th.tiLe, km.giaTri ") // Thêm maNV
                  .append("ORDER BY hd.ngayLap DESC, hd.maHD DESC");

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new Object[] {
                        rs.getString("maHD"),
                        rs.getString("maKH") != null ? rs.getString("maKH") : "N/A",
                        rs.getDate("ngayLap"),
                        rs.getString("tenKH") != null ? rs.getString("tenKH") : "Khách vãng lai",
                        rs.getString("sdt") != null ? rs.getString("sdt") : "N/A",
                        rs.getString("maNV"), // Thêm maNV vào kết quả
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