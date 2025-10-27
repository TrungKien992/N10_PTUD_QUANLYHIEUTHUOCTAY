package dao;

import connectDB.ConnectDB;
import entity.CTPhieuDoiTra;
import entity.PhieuDoiTra;
import entity.Thuoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CTPhieuDoiTra_DAO {
    public List<CTPhieuDoiTra> getCTPhieuDoiTraTheoMa(String maPDT) {
        List<CTPhieuDoiTra> ds = new ArrayList<>();
        String sql = "SELECT * FROM CTPhieuDoiTra WHERE maPDT = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maPDT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTPhieuDoiTra ct = new CTPhieuDoiTra(
                        new PhieuDoiTra(rs.getString("maPDT")),
                        new Thuoc(rs.getString("maThuoc")),
                        rs.getInt("soLuong")
                );
                ds.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean themCTPhieuDoiTra(CTPhieuDoiTra ct) {
        String sql = "INSERT INTO CTPhieuDoiTra (maPDT, maThuoc, soLuong) VALUES (?,?,?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ct.getPhieuDoiTra().getMaPDT());
            ps.setString(2, ct.getThuoc().getMaThuoc());
            ps.setInt(3, ct.getSoLuong());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
