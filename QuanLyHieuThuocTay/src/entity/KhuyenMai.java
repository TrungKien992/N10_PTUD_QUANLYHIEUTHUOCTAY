package entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhuyenMai {
    private String maKM;
    private String tenChuongTrinh;
    private double giaTri; // Tỷ lệ % hoặc giá trị tiền mặt giảm giá
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String dieuKienApDung;
    private String phamViApDung;
    private int soLuongToiDa;
    private boolean trangThai; // true cho 1 (Đang diễn ra), false cho 0 (Đã kết thúc)

    public KhuyenMai() {
    }
    
    public KhuyenMai(String maKM) {
        this.maKM = maKM;
    }

    public KhuyenMai(String maKM, String tenChuongTrinh, double giaTri, LocalDate ngayBatDau, LocalDate ngayKetThuc,
            String dieuKienApDung, String phamViApDung, int soLuongToiDa, boolean trangThai) {
        this.maKM = maKM;
        this.tenChuongTrinh = tenChuongTrinh;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.dieuKienApDung = dieuKienApDung;
        this.phamViApDung = phamViApDung;
        this.soLuongToiDa = soLuongToiDa;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getTenChuongTrinh() {
        return tenChuongTrinh;
    }

    public void setTenChuongTrinh(String tenChuongTrinh) {
        this.tenChuongTrinh = tenChuongTrinh;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getDieuKienApDung() {
        return dieuKienApDung;
    }

    public void setDieuKienApDung(String dieuKienApDung) {
        this.dieuKienApDung = dieuKienApDung;
    }

    public String getPhamViApDung() {
        return phamViApDung;
    }

    public void setPhamViApDung(String phamViApDung) {
        this.phamViApDung = phamViApDung;
    }

    public int getSoLuongToiDa() {
        return soLuongToiDa;
    }

    public void setSoLuongToiDa(int soLuongToiDa) {
        this.soLuongToiDa = soLuongToiDa;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKM);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KhuyenMai other = (KhuyenMai) obj;
        return Objects.equals(maKM, other.maKM);
    }

    @Override
    public String toString() {
        return tenChuongTrinh;
    }
}