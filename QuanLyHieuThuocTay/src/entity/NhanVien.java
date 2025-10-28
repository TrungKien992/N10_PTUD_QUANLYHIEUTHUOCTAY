package entity;

import java.time.LocalDate;

public class NhanVien {
	private String maNV;
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	private String tenNV;
	private LocalDate ngaySinh;
	private String gioiTinh;
	private ChucVu chucVu;
	private String soDienThoai;
	private String diaChi;
	private String anh;
	private String trangThai;
	private TaiKhoan taiKhoan;
	
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public ChucVu getChucVu() {
		return chucVu;
	}
	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
	public NhanVien(String maNV, String tenNV, LocalDate ngaySinh, String gioiTinh, ChucVu chucVu,
			String soDienThoai, String diaChi, String anh, TaiKhoan taiKhoan) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.chucVu = chucVu;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.anh = anh;
		this.taiKhoan = taiKhoan;
	}
	
	public NhanVien() {
		super();
	}
	@Override
	public String toString() {
		return maNV + ";" + tenNV + ";" + ngaySinh + ";" + gioiTinh + ";" + chucVu + ";" + soDienThoai + ";" + diaChi + ";" + anh + ";" + taiKhoan ;
	}
	
	
	
}
