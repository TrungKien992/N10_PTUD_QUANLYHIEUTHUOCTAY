package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhieuDatHang {
    private String maPhieuDat;
    private NhaCungCap nhaCungCap; // Liên kết tới Nhà Cung Cấp
    private NhanVien nhanVien; // Người lập phiếu (quan trọng)
    private LocalDate ngayDat;
    private String ghiChu;
    private String trangThai; // Ví dụ: "Chờ xác nhận", "Đã nhận hàng", "Đã hủy"
    
    // Một phiếu đặt hàng sẽ có nhiều chi tiết phiếu đặt
    private List<ChiTietPhieuDatHang> dsChiTiet;

    public PhieuDatHang() {
        // Khởi tạo danh sách chi tiết để tránh NullPointerException
        this.dsChiTiet = new ArrayList<>();
    }

    public PhieuDatHang(String maPhieuDat) {
        this.maPhieuDat = maPhieuDat;
        this.dsChiTiet = new ArrayList<>();
    }

    public PhieuDatHang(String maPhieuDat, NhaCungCap nhaCungCap, NhanVien nhanVien, LocalDate ngayDat, String ghiChu, String trangThai) {
        this.maPhieuDat = maPhieuDat;
        this.nhaCungCap = nhaCungCap;
        this.nhanVien = nhanVien;
        this.ngayDat = ngayDat;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
        this.dsChiTiet = new ArrayList<>();
    }
    
    // --- Phương thức tiện ích ---
    
    /**
     * Tự động tính tổng tiền của phiếu dựa trên danh sách chi tiết
     */
    public double getTongTien() {
        double tongTien = 0;
        for (ChiTietPhieuDatHang ct : dsChiTiet) {
            tongTien += ct.getThanhTien();
        }
        return tongTien;
    }
    
    /**
     * Thêm một chi tiết (một loại thuốc) vào phiếu
     */
    public void themChiTiet(ChiTietPhieuDatHang chiTiet) {
        this.dsChiTiet.add(chiTiet);
    }
    
    /**
     * Xóa một chi tiết (một loại thuốc) khỏi phiếu
     */
     public void xoaChiTiet(ChiTietPhieuDatHang chiTiet) {
         this.dsChiTiet.remove(chiTiet);
     }
    
    // --- Getters and Setters ---
    
    public String getMaPhieuDat() {
        return maPhieuDat;
    }

    public void setMaPhieuDat(String maPhieuDat) {
        this.maPhieuDat = maPhieuDat;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LocalDate getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDate ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<ChiTietPhieuDatHang> getDsChiTiet() {
        return dsChiTiet;
    }

    public void setDsChiTiet(List<ChiTietPhieuDatHang> dsChiTiet) {
        this.dsChiTiet = dsChiTiet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maPhieuDat);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PhieuDatHang other = (PhieuDatHang) obj;
        return Objects.equals(maPhieuDat, other.maPhieuDat);
    }
}