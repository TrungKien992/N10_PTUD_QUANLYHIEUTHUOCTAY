use master
go


--create database
create database QLThuoc
go


use [QLThuoc]
go

--tao bang
CREATE TABLE TaiKhoan (
    maTK VARCHAR(20) PRIMARY KEY,
    tenTK VARCHAR(50) NOT NULL,
    matKhau VARCHAR(100) NOT NULL,
    quyenHan VARCHAR(50) NOT NULL
);

go
CREATE TABLE ChucVu (
    maChucVu VARCHAR(20) PRIMARY KEY,
    tenChucVu NVARCHAR(50) NOT NULL,
    moTa NVARCHAR(255)
);

CREATE TABLE NhanVien (
    maNV VARCHAR(20) PRIMARY KEY,
    tenNV VARCHAR(100) NOT NULL,
    ngaySinh DATE NOT NULL,
    gioiTinh VARCHAR(10),
    chucVu VARCHAR(20),
    sdt NVARCHAR(15),
	diaChi NVARCHAR(50),
	anh NVARCHAR(30),
    maTK VARCHAR(20) UNIQUE,   -- mỗi NV có 1 tài khoản duy nhất
    FOREIGN KEY (maTK) REFERENCES TaiKhoan(maTK),
	FOREIGN KEY (chucVu) REFERENCES ChucVu(maChucVu)
);
go

CREATE TABLE KhachHang (
    maKH VARCHAR(20) PRIMARY KEY,
    tenKH VARCHAR(100) NOT NULL,
    diaChi VARCHAR(255),
    sdt NVARCHAR(15) UNIQUE
);
go

CREATE TABLE KhuyenMai (
    maKM VARCHAR(20) PRIMARY KEY,
    tenChuongTrinh VARCHAR(100) NOT NULL,
    giaTri FLOAT NOT NULL,          
    ngayBatDau DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
    dieuKienApDung VARCHAR(255),
    phamViApDung VARCHAR(255),
    soLuongToiDa INT,
    trangThai INT DEFAULT 1 CHECK (trangThai IN (0,1)) --1: Dang dien ra, 0: Da ket thuc
);
go

CREATE TABLE Thue (
    maThue VARCHAR(20) PRIMARY KEY,
    tiLe float NOT NULL CHECK (tiLe >= 0),
    moTa VARCHAR(255),
    ngayApDung DATE NOT NULL
);
go

CREATE TABLE HoaDon (
    maHD VARCHAR(20) PRIMARY KEY,
    ngayLap DATE NOT NULL,
    maKM VARCHAR(20),
    maThue VARCHAR(20) NOT NULL,
    maNV VARCHAR(20) NOT NULL,
    maKH VARCHAR(20),
    FOREIGN KEY (maKM) REFERENCES KhuyenMai(maKM),
    FOREIGN KEY (maThue) REFERENCES Thue(maThue),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);
go

CREATE TABLE KeThuoc (
    maKe VARCHAR(20) PRIMARY KEY,
    viTri INT NOT NULL,
    loaiKe VARCHAR(50)
);
go

CREATE TABLE Thuoc (
    maThuoc VARCHAR(20) PRIMARY KEY,
    tenThuoc VARCHAR(100) NOT NULL,
    giaBan float NOT NULL CHECK (giaBan >= 0),
    hanSuDung DATE NOT NULL,
    thanhPhan VARCHAR(255),
    donViTinh VARCHAR(50),
    maKe VARCHAR(20),
    FOREIGN KEY (maKe) REFERENCES KeThuoc(maKe)
);
go

CREATE TABLE NhaCungCap (
    maNhaCungCap VARCHAR(20) PRIMARY KEY,
    tenNhaCungCap VARCHAR(100) NOT NULL
);
go

CREATE TABLE PhieuDatHang (
    maPhieuDatHang VARCHAR(20) PRIMARY KEY,
    maNhaCC VARCHAR(20) NOT NULL,                
    maNV VARCHAR(20) NOT NULL,           
    ngayGiaoHang DATE NOT NULL,
    FOREIGN KEY (maNhaCC) REFERENCES NhaCungCap(maNhaCungCap),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
);
go


CREATE TABLE CtPhieuDatHang(
	soLuong int,
	maPDH VARCHAR(20) not null,
	maThuoc VARCHAR(20)  not null, 
	FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc),
	FOREIGN KEY (maPDH) REFERENCES PhieuDatHang(maPhieuDatHang)
);
go

CREATE TABLE PhieuDatThuoc (
    maPhieu VARCHAR(20) PRIMARY KEY,
    maNV VARCHAR(20) NOT NULL,
    maKH VARCHAR(20),
    ngayDat DATE NOT NULL,
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);
go

CREATE TABLE ChiTietPhieuDatThuoc (
    maPhieu VARCHAR(20),
    maThuoc VARCHAR(20),
    soLuong INT NOT NULL CHECK (soLuong > 0),
    PRIMARY KEY (maPhieu, maThuoc),
    FOREIGN KEY (maPhieu) REFERENCES PhieuDatThuoc(maPhieu) ON DELETE CASCADE,
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc)
);
go

CREATE TABLE ChiTietHoaDon (
    maHD VARCHAR(20),
    maThuoc VARCHAR(20),
    soLuong INT NOT NULL CHECK (soLuong > 0),
    PRIMARY KEY (maHD, maThuoc),
    FOREIGN KEY (maHD) REFERENCES HoaDon(maHD) ON DELETE CASCADE,
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc)
);
go

CREATE TABLE PhieuTraThuoc (
    maPhieuTra VARCHAR(20) PRIMARY KEY,
    maHD VARCHAR(20) NOT NULL,
    maNV VARCHAR(20) NOT NULL,
    ngayTra DATE NOT NULL,
    lyDo NVARCHAR(255),
    tongTienHoan FLOAT CHECK (tongTienHoan >= 0),
    FOREIGN KEY (maHD) REFERENCES HoaDon(maHD),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
);
go

CREATE TABLE CtPhieuTraThuoc (
    maPhieuTra VARCHAR(20),
    maThuoc VARCHAR(20),
    soLuong INT NOT NULL CHECK (soLuong > 0),
    donGia FLOAT NOT NULL CHECK (donGia >= 0),
    PRIMARY KEY (maPhieuTra, maThuoc),
    FOREIGN KEY (maPhieuTra) REFERENCES PhieuTraThuoc(maPhieuTra) ON DELETE CASCADE,
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc)
);
go

CREATE TABLE PhieuDoiThuoc (
    maPhieuDoi VARCHAR(20) PRIMARY KEY,
    maHD VARCHAR(20) NOT NULL,
    maNV VARCHAR(20) NOT NULL,
    ngayDoi DATE NOT NULL,
    lyDo NVARCHAR(255),
    FOREIGN KEY (maHD) REFERENCES HoaDon(maHD),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
);
go

CREATE TABLE CtPhieuDoiThuoc (
    maPhieuDoi VARCHAR(20),
    maThuocCu VARCHAR(20) NOT NULL,
    maThuocMoi VARCHAR(20) NOT NULL,
    soLuong INT NOT NULL CHECK (soLuong > 0),
    donGiaCu FLOAT NOT NULL CHECK (donGiaCu >= 0),
    donGiaMoi FLOAT NOT NULL CHECK (donGiaMoi >= 0),
    PRIMARY KEY (maPhieuDoi, maThuocCu, maThuocMoi),
    FOREIGN KEY (maPhieuDoi) REFERENCES PhieuDoiThuoc(maPhieuDoi) ON DELETE CASCADE,
    FOREIGN KEY (maThuocCu) REFERENCES Thuoc(maThuoc),
    FOREIGN KEY (maThuocMoi) REFERENCES Thuoc(maThuoc)
);
go

--Insert data

-- TAI KHOAN
INSERT INTO TaiKhoan VALUES
('TK01', 'admin', '123456', 'Admin'),
('TK02', N'Nhân viên bán hàng', '123456', 'Nhân viên bán hàng'),
('TK03', N'Nhân viên quản lý', '123456', 'Nhân viên kho');

-- CHUC VU
INSERT INTO ChucVu VALUES
('CV01', N'Quản lý', N'Quản lý toàn bộ hệ thống'),
('CV02', N'Nhân viên bán hàng', N'Phụ trách bán thuốc và lập hóa đơn'),
('CV03', N'Nhân viên kho', N'Quản lý nhập - xuất thuốc trong kho');

-- NHAN VIEN
INSERT INTO NhanVien VALUES
('NV001', N'Nguyễn Văn A', '1990-05-15', N'Nam', 'CV01', '0912345678', N'Hà Nội', N'a', 'TK01'),
('NV002', N'Trần Thị B', '1995-08-10', N'Nữ', 'CV02', '0987654321', N'Hải Phòng', N'a', 'TK02'),
('NV003', N'Lê Văn C', '1993-03-20', N'Nam', 'CV03', '0901234567', N'Đà Nẵng', N'a', 'TK03');

-- KHACH HANG
INSERT INTO KhachHang VALUES
('KH01', N'Phạm Minh D', N'Hà Nội', '0911222333'),
('KH02', N'Nguyễn Thu E', N'Hồ Chí Minh', '0988776655'),
('KH03', N'Đỗ Văn F', N'Hải Dương', '0977998877');

-- KHUYEN MAI
INSERT INTO KhuyenMai VALUES
('KM01', N'Giảm giá 10%', 10, '2025-10-01', '2025-10-31', N'Hóa đơn > 100k', N'Toàn quốc', 1000, 1),
('KM02', N'Mua 2 tặng 1', 0, '2025-09-01', '2025-12-31', N'Áp dụng nhóm thuốc vitamin', N'Toàn quốc', 500, 1);

-- THUE
INSERT INTO Thue VALUES
('TH01', 0.05, N'Thuế GTGT 5%', '2025-01-01'),
('TH02', 0.1, N'Thuế GTGT 10%', '2025-01-01');

-- KE THUOC
INSERT INTO KeThuoc VALUES
('K01', 1, N'Kháng sinh'),
('K02', 2, N'Giảm đau - hạ sốt'),
('K03', 3, N'Vitamin & khoáng chất'),
('K04', 4, N'Da liễu'),
('K05', 5, N'Tim mạch');

-- NHA CUNG CAP
INSERT INTO NhaCungCap VALUES
('NCC01', N'Công ty Dược Trung ương 1'),
('NCC02', N'Công ty Dược Hậu Giang'),
('NCC03', N'Công ty Dược Mekophar');

-- 36 LOẠI THUỐC
INSERT INTO Thuoc VALUES
('T01', N'Paracetamol 500mg', 1500, '2026-12-31', N'Paracetamol', N'Viên', 'K02'),
('T02', N'Panadol Extra', 2500, '2026-11-30', N'Paracetamol, Caffeine', N'Viên', 'K02'),
('T03', N'Acetaminophen Syrup', 30000, '2026-10-30', N'Paracetamol 160mg/5ml', N'Chai', 'K02'),
('T04', N'Amoxicillin 500mg', 2000, '2027-03-31', N'Amoxicillin', N'Viên', 'K01'),
('T05', N'Augmentin 625mg', 8000, '2027-05-31', N'Amoxicillin + Clavulanic Acid', N'Viên', 'K01'),
('T06', N'Cephalexin 500mg', 2500, '2027-06-30', N'Cephalexin', N'Viên', 'K01'),
('T07', N'Ciprofloxacin 500mg', 3000, '2027-07-31', N'Ciprofloxacin', N'Viên', 'K01'),
('T08', N'Azithromycin 250mg', 7000, '2027-08-31', N'Azithromycin', N'Viên', 'K01'),
('T09', N'Doxycycline 100mg', 2500, '2027-09-30', N'Doxycycline', N'Viên', 'K01'),
('T10', N'Erythromycin 250mg', 2200, '2026-09-30', N'Erythromycin', N'Viên', 'K01'),
('T11', N'Vitamin C 500mg', 1200, '2027-02-28', N'Ascorbic Acid', N'Viên', 'K03'),
('T12', N'Vitamin B1-B6-B12', 1500, '2027-02-28', N'Three B vitamins', N'Viên', 'K03'),
('T13', N'Calcium D3', 5000, '2027-01-31', N'Calcium Carbonate + Vitamin D3', N'Viên', 'K03'),
('T14', N'Zinc 15mg', 2500, '2027-03-31', N'Zinc Gluconate', N'Viên', 'K03'),
('T15', N'Multivitamin tổng hợp', 3500, '2027-04-30', N'Nhiều vitamin và khoáng chất', N'Viên', 'K03'),
('T16', N'Acid Folic 5mg', 1800, '2027-05-31', N'Folic Acid', N'Viên', 'K03'),
('T17', N'Vitamin E 400IU', 3200, '2027-06-30', N'Tocopherol', N'Viên', 'K03'),
('T18', N'Kem nghệ', 20000, '2026-12-31', N'Curcumin, tinh dầu nghệ', N'Tuýp', 'K04'),
('T19', N'Kem trị mụn Acnes', 35000, '2027-01-31', N'Sulfur, Resorcinol', N'Tuýp', 'K04'),
('T20', N'Kem dưỡng ẩm Nivea', 45000, '2027-03-31', N'Glycerin, Vitamin E', N'Hộp', 'K04'),
('T21', N'Kem chống nắng Sunplay', 60000, '2027-05-31', N'Titanium dioxide, SPF50', N'Chai', 'K04'),
('T22', N'Thuốc mỡ Tetax', 18000, '2026-11-30', N'Tetracycline', N'Tuýp', 'K04'),
('T23', N'Aspirin 81mg', 1500, '2027-08-31', N'Acetylsalicylic Acid', N'Viên', 'K05'),
('T24', N'Atorvastatin 10mg', 4000, '2027-09-30', N'Atorvastatin Calcium', N'Viên', 'K05'),
('T25', N'Simvastatin 10mg', 3500, '2027-10-31', N'Simvastatin', N'Viên', 'K05'),
('T26', N'Bisoprolol 5mg', 3000, '2027-06-30', N'Bisoprolol Fumarate', N'Viên', 'K05'),
('T27', N'Enalapril 5mg', 2500, '2027-05-31', N'Enalapril Maleate', N'Viên', 'K05'),
('T28', N'Amlodipine 5mg', 2800, '2027-04-30', N'Amlodipine Besylate', N'Viên', 'K05'),
('T29', N'Furosemide 40mg', 2000, '2027-03-31', N'Furosemide', N'Viên', 'K05'),
('T30', N'Losartan 50mg', 3500, '2027-02-28', N'Losartan Potassium', N'Viên', 'K05'),
('T31', N'Clopidogrel 75mg', 4200, '2027-01-31', N'Clopidogrel bisulfate', N'Viên', 'K05'),
('T32', N'Metoprolol 50mg', 3600, '2027-08-31', N'Metoprolol Tartrate', N'Viên', 'K05'),
('T33', N'Pantoprazole 40mg', 3500, '2027-03-31', N'Pantoprazole Sodium', N'Viên', 'K02'),
('T34', N'Omeprazole 20mg', 3000, '2027-05-31', N'Omeprazole', N'Viên', 'K02'),
('T35', N'Loperamide 2mg', 2500, '2027-06-30', N'Loperamide Hydrochloride', N'Viên', 'K02'),
('T36', N'Smecta', 15000, '2026-12-31', N'Diosmectite 3g', N'Gói', 'K02');
go
