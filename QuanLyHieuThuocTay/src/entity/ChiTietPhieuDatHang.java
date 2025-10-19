package entity;

import java.util.Objects;

public class ChiTietPhieuDatHang {
    
    private Thuoc thuoc; // Liên kết tới Thuốc
    private int soLuong;
    private double donGia; // Đơn giá lúc đặt hàng

    public ChiTietPhieuDatHang() {
    }

    public ChiTietPhieuDatHang(Thuoc thuoc, int soLuong, double donGia) {
        this.thuoc = thuoc;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    
    /**
     * Tự động tính thành tiền cho chi tiết này
     */
    public double getThanhTien() {
        return this.soLuong * this.donGia;
    }

    // --- Getters and Setters ---
    
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

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    // Hashcode và equals dựa trên 'thuoc', 
    // vì trong 1 phiếu đặt hàng, mỗi loại thuốc chỉ nên xuất hiện 1 lần
    @Override
    public int hashCode() {
        return Objects.hash(thuoc);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChiTietPhieuDatHang other = (ChiTietPhieuDatHang) obj;
        return Objects.equals(thuoc, other.thuoc);
    }
}