package entity;

import java.util.Objects;

public class ChiTietHoaDon {
    private HoaDon hoaDon;
    private Thuoc thuoc;
    private int soLuong;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(HoaDon hoaDon, Thuoc thuoc, int soLuong) {
        this.hoaDon = hoaDon;
        this.thuoc = thuoc;
        this.soLuong = soLuong;
    }

    // Getters and Setters
    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    // Phương thức tính thành tiền cho chi tiết này
    public double tinhThanhTien() {
        if(thuoc != null) {
            return thuoc.getGiaBan() * soLuong;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hoaDon, thuoc);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChiTietHoaDon other = (ChiTietHoaDon) obj;
        return Objects.equals(hoaDon, other.hoaDon) && Objects.equals(thuoc, other.thuoc);
    }
}