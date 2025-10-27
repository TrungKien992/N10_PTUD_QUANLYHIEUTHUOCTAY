package dao;

import connectDB.ConnectDB;
import entity.CTPhieuDoiTra;
import entity.HoaDon;
import entity.NhanVien;
import entity.PhieuDoiTra;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhieuDoiTra_DAO {
    public List<PhieuDoiTra> getAllPhieuDoiTra() {
        List<PhieuDoiTra> ds = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM PhieuDoiTra")) {
            while (rs.next()) {
                PhieuDoiTra pdt = new PhieuDoiTra(
                        rs.getString("maPDT"),
                        rs.getDate("ngayDoiTra").toLocalDate(),
                        rs.getString("lyDo"),
                        rs.getString("loaiPhieu"),
                        rs.getDouble("tienHoanLai"),
                        new NhanVien(rs.getString("maNV")),
                        new HoaDon(rs.getString("maHD"))
                );
                ds.add(pdt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean themPhieuDoiTra(PhieuDoiTra pdt) {
        String sql = "INSERT INTO PhieuDoiTra (maPDT, ngayDoiTra, lyDo, loaiPhieu, tienHoanLai, maNV, maHD) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pdt.getMaPDT().trim()); // trim để tránh khoảng trắng
            ps.setDate(2, Date.valueOf(pdt.getNgayDoiTra()));
            ps.setString(3, pdt.getLyDo());
            ps.setString(4, pdt.getLoaiPhieu());
            ps.setDouble(5, pdt.getTienHoanLai());
            ps.setString(6, pdt.getNhanVien().getMaNV());
            ps.setString(7, pdt.getHoaDon().getMaHD());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean themCTPhieuDoiTra(CTPhieuDoiTra ct) {
        String sql = "INSERT INTO CTPhieuDoiTra (maPDT, maThuoc, soLuong) VALUES (?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ct.getPhieuDoiTra().getMaPDT());
            ps.setString(2, ct.getThuoc().getMaThuoc());
            ps.setInt(3, ct.getSoLuong());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String taoMaPDTMoi() {
        String sql = "SELECT MAX(maPDT) AS maxMa FROM PhieuDoiTra";
        try (Connection con = ConnectDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String maxMa = rs.getString("maxMa");
                if (maxMa != null && maxMa.startsWith("PDT")) {
                    int so = Integer.parseInt(maxMa.substring(3).trim());
                    return String.format("PDT%03d", so + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "PDT001";
    }


}
