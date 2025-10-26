﻿USE master
GO

IF DB_ID('QLThuoc') IS NOT NULL
    DROP DATABASE QLThuoc;
GO

CREATE DATABASE QLThuoc;
GO

USE [QLThuoc];
GO

--------------------------------------------------
-- BẢNG TÀI KHOẢN
--------------------------------------------------
CREATE TABLE TaiKhoan (
    maTK VARCHAR(20) PRIMARY KEY,
    tenTK NVARCHAR(50) NOT NULL,
    matKhau VARCHAR(100) NOT NULL,
    quyenHan NVARCHAR(50)
);
GO

--------------------------------------------------
-- BẢNG CHỨC VỤ
--------------------------------------------------
CREATE TABLE ChucVu (
    maChucVu VARCHAR(20) PRIMARY KEY,
    tenChucVu NVARCHAR(50) NOT NULL,
    moTa NVARCHAR(255)
);
GO

--------------------------------------------------
-- BẢNG NHÂN VIÊN
--------------------------------------------------
CREATE TABLE NhanVien (
    maNV VARCHAR(20) PRIMARY KEY,
    tenNV NVARCHAR(100) NOT NULL,
    ngaySinh DATE NOT NULL,
    gioiTinh NVARCHAR(10),
    chucVu VARCHAR(20),
    sdt NVARCHAR(15),
    diaChi NVARCHAR(255),
    anh NVARCHAR(255),
    maTK VARCHAR(20) UNIQUE,
    FOREIGN KEY (maTK) REFERENCES TaiKhoan(maTK),
    FOREIGN KEY (chucVu) REFERENCES ChucVu(maChucVu)
);
GO

--------------------------------------------------
-- BẢNG KHÁCH HÀNG
--------------------------------------------------
CREATE TABLE KhachHang (
    maKH VARCHAR(20) PRIMARY KEY,
    tenKH NVARCHAR(100) NOT NULL,
    diaChi NVARCHAR(255),
    sdt NVARCHAR(15) UNIQUE
);
GO

--------------------------------------------------
-- BẢNG KHUYẾN MÃI
--------------------------------------------------
CREATE TABLE KhuyenMai (
    maKM VARCHAR(20) PRIMARY KEY,
    tenChuongTrinh NVARCHAR(100) NOT NULL,
    giaTri FLOAT NOT NULL,
    ngayBatDau DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
    soLuongToiDa INT,
    trangThai INT DEFAULT 1 CHECK (trangThai IN (0,1))
);
GO

--------------------------------------------------
-- BẢNG THUẾ
--------------------------------------------------
CREATE TABLE Thue (
    maThue VARCHAR(20) PRIMARY KEY,
    tiLe FLOAT NOT NULL CHECK (tiLe >= 0),
    moTa NVARCHAR(255),
    ngayApDung DATE NOT NULL
);
GO

--------------------------------------------------
-- BẢNG HÓA ĐƠN
--------------------------------------------------
CREATE TABLE HoaDon (
    maHD VARCHAR(20) PRIMARY KEY,
    ngayLap DATE NOT NULL,
    maKM VARCHAR(20),
    maThue VARCHAR(20) NOT NULL,
    maNV VARCHAR(20) NOT NULL,
    maKH VARCHAR(20),
    tienKhachDua FLOAT DEFAULT 0,
    tienThua FLOAT DEFAULT 0,
    FOREIGN KEY (maKM) REFERENCES KhuyenMai(maKM),
    FOREIGN KEY (maThue) REFERENCES Thue(maThue),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);
GO

--------------------------------------------------
-- BẢNG KỆ THUỐC
--------------------------------------------------
CREATE TABLE KeThuoc (
    maKe VARCHAR(20) PRIMARY KEY,
    viTri INT NOT NULL,
    loaiKe NVARCHAR(50)
);
GO

--------------------------------------------------
-- BẢNG NHÀ CUNG CẤP
--------------------------------------------------
CREATE TABLE NhaCungCap (
    maNhaCungCap VARCHAR(20) PRIMARY KEY,
    tenNhaCungCap NVARCHAR(100) NOT NULL,
    soDienThoai NVARCHAR(15),
    email VARCHAR(100),
    diaChi NVARCHAR(255),
    trangThai BIT DEFAULT 1,
    ghiChu NVARCHAR(255)
);
GO

--------------------------------------------------
-- BẢNG THUỐC
--------------------------------------------------
CREATE TABLE Thuoc (
    maThuoc VARCHAR(20) PRIMARY KEY,
    tenThuoc NVARCHAR(100) NOT NULL,
    soLuong INT NOT NULL CHECK (soLuong >= 0),
    giaNhap FLOAT NOT NULL CHECK (giaNhap >= 0),
    giaBan FLOAT NOT NULL CHECK (giaBan >= 0),
    hanSuDung DATE NOT NULL,
    thanhPhan NVARCHAR(255),
    donViTinh NVARCHAR(50),
    anh NVARCHAR(255),
    maKe VARCHAR(20),
    maNhaCungCap VARCHAR(20),
    FOREIGN KEY (maKe) REFERENCES KeThuoc(maKe),
    FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap(maNhaCungCap)
);
GO

--------------------------------------------------
-- BẢNG CHI TIẾT HÓA ĐƠN
--------------------------------------------------
CREATE TABLE ChiTietHoaDon (
    maHD VARCHAR(20),
    maThuoc VARCHAR(20),
    soLuong INT NOT NULL CHECK (soLuong > 0),
    PRIMARY KEY (maHD, maThuoc),
    FOREIGN KEY (maHD) REFERENCES HoaDon(maHD) ON DELETE CASCADE,
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc)
);
GO

--------------------------------------------------
-- PHIẾU ĐẶT HÀNG
--------------------------------------------------
CREATE TABLE PhieuDatHang (
    MaPhieu VARCHAR(20) PRIMARY KEY,
    MaNCC VARCHAR(20) NOT NULL,
    MaNhanVien VARCHAR(20) NOT NULL,
    NgayDat DATE NOT NULL DEFAULT GETDATE(),
    TongTien FLOAT NOT NULL DEFAULT 0 CHECK (TongTien >= 0),
    TrangThai NVARCHAR(50) NOT NULL DEFAULT N'Đã đặt hàng',
    GhiChu NVARCHAR(255),
    FOREIGN KEY (MaNCC) REFERENCES NhaCungCap(maNhaCungCap),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNV)
);
GO

--------------------------------------------------
-- CHI TIẾT PHIẾU ĐẶT HÀNG
--------------------------------------------------
CREATE TABLE ChiTietPhieuDatHang (
    MaPhieu VARCHAR(20),
    MaThuoc VARCHAR(20),
    SoLuong INT NOT NULL CHECK (SoLuong > 0),
    DonGia FLOAT NOT NULL CHECK (DonGia >= 0),
    ThanhTien AS (SoLuong * DonGia),
    PRIMARY KEY (MaPhieu, MaThuoc),
    FOREIGN KEY (MaPhieu) REFERENCES PhieuDatHang(MaPhieu) ON DELETE CASCADE,
    FOREIGN KEY (MaThuoc) REFERENCES Thuoc(MaThuoc)
);
GO

--------------------------------------------------
-- PHIẾU CHỜ THANH TOÁN
--------------------------------------------------
CREATE TABLE PhieuChoThanhToan (
    maPhieuCho VARCHAR(20) PRIMARY KEY,
    maKH VARCHAR(20) NULL,
    tenKH NVARCHAR(100) NOT NULL,
    sdtKH NVARCHAR(15) NOT NULL,
    maNVLap VARCHAR(20) NOT NULL,
    ngayLap DATE NOT NULL DEFAULT GETDATE(),
    tongTienHang FLOAT DEFAULT 0,
    thueVAT FLOAT DEFAULT 0,
    tongCong FLOAT DEFAULT 0,
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    FOREIGN KEY (maNVLap) REFERENCES NhanVien(maNV)
);
GO

--------------------------------------------------
-- CHI TIẾT PHIẾU CHỜ
--------------------------------------------------
CREATE TABLE ChiTietPhieuCho (
    maPhieuCho VARCHAR(20) NOT NULL,
    maThuoc VARCHAR(20) NOT NULL,
    soLuong INT NOT NULL CHECK (soLuong > 0),
    donGia FLOAT NOT NULL,
    thanhTien AS (soLuong * donGia),
    PRIMARY KEY (maPhieuCho, maThuoc),
    FOREIGN KEY (maPhieuCho) REFERENCES PhieuChoThanhToan(maPhieuCho) ON DELETE CASCADE,
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc)
);
GO

--------------------------------------------------
-- DỮ LIỆU MẪU
--------------------------------------------------

-- TAI KHOAN
INSERT INTO TaiKhoan VALUES
('TK01', 'admin', '123', N'Quản lý'),
('TK02', 'nvbh01', '123', N'Nhân viên bán hàng'),
('TK03', 'nvkho01', '123', N'Nhân viên kho');
GO

-- CHUC VU
INSERT INTO ChucVu VALUES
('CV01', N'Quản lý', N'Quản lý toàn bộ hệ thống'),
('CV02', N'Nhân viên bán hàng', N'Phụ trách bán thuốc và lập hóa đơn'),
('CV03', N'Nhân viên kho', N'Quản lý nhập - xuất thuốc trong kho');
GO

-- NHAN VIÊN
INSERT INTO NhanVien VALUES
('NV001', N'Nguyễn Văn An', '1990-05-15', N'Nam', 'CV01', '0912345678', N'Quận 1, TP.HCM', NULL, 'TK01'),
('NV002', N'Trần Thị Bích', '1995-08-10', N'Nữ', 'CV02', '0987654321', N'Quận 5, TP.HCM', NULL, 'TK02'),
('NV003', N'Lê Văn Cường', '1993-03-20', N'Nam', 'CV03', '0901234567', N'Gò Vấp, TP.HCM', NULL, 'TK03');
GO

-- KHÁCH HÀNG
INSERT INTO KhachHang VALUES
('KH0000001', N'Phạm Minh Dũng', N'Quận 3, TP.HCM', '0911222333'),
('KH0000002', N'Nguyễn Thu Trang', N'Quận 7, TP.HCM', '0988776655'),
('KH0000003', N'Đỗ Văn Phúc', N'TP. Thủ Đức', '0977998877');
GO

-- KHUYẾN MÃI
INSERT INTO KhuyenMai VALUES
('KM001', N'Giảm giá Black Friday', 15, '2025-11-20', '2025-11-30', 100, 1),
('KM002', N'Chào mừng Lễ 2/9', 10, '2025-09-01', '2025-09-03', 50, 0),
('KM003', N'Giảm giá cuối tuần (Tháng 10)', 5, '2025-10-20', '2025-10-27', NULL, 1),
('KM004', N'Tri ân khách hàng', 20, '2025-12-01', '2025-12-20', 200, 1);
GO

-- THUẾ
INSERT INTO Thue VALUES
('TH01', 0.05, N'Thuế GTGT 5%', '2025-01-01'),
('TH02', 0.1, N'Thuế GTGT 10%', '2025-01-01');
GO

-- KỆ THUỐC
INSERT INTO KeThuoc VALUES
('K01', 1, N'Kháng sinh'),
('K02', 2, N'Giảm đau - hạ sốt'),
('K03', 3, N'Vitamin & khoáng chất'),
('K04', 4, N'Da liễu'),
('K05', 5, N'Tim mạch');
GO

-- NHÀ CUNG CẤP
INSERT INTO NhaCungCap VALUES
('NCC01', N'Dược Hậu Giang', '02923891433', 'contact@dhgpharma.com.vn', N'Cần Thơ', 1, N'Đối tác lớn'),
('NCC02', N'Traphaco', '02436815032', 'info@traphaco.com.vn', N'Hà Nội', 1, N'Thuốc đông dược'),
('NCC03', N'Pymepharco', '02573829517', 'info@pymepharco.com', N'Phú Yên', 0, N'Ngừng hợp tác');
GO

-- THUỐC
INSERT INTO Thuoc VALUES
('T01', N'Paracetamol 500mg', 100, 1000, 1500, '2026-12-31', N'Paracetamol', N'Viên', NULL, 'K02', 'NCC01'),
('T02', N'Panadol Extra', 200, 1800, 2500, '2026-11-30', N'Paracetamol, Caffeine', N'Viên', NULL, 'K02', 'NCC01'),
('T03', N'Amoxicillin 500mg', 150, 1500, 2000, '2027-03-31', N'Amoxicillin', N'Viên', NULL, 'K01', 'NCC02'),
('T04', N'Augmentin 625mg', 50, 6000, 8000, '2027-05-31', N'Amoxicillin + Clavulanic Acid', N'Viên', NULL, 'K01', 'NCC02'),
('T05', N'Vitamin C 500mg', 300, 800, 1200, '2027-02-28', N'Ascorbic Acid', N'Viên', NULL, 'K03', 'NCC01'),
('T06', N'Kem nghệ Thorakao', 80, 15000, 20000, '2026-12-31', N'Curcumin', N'Tuýp', NULL, 'K04', 'NCC03'),
('T07', N'Aspirin 81mg', 120, 1000, 1500, '2027-08-31', N'Acetylsalicylic Acid', N'Viên', NULL, 'K05', 'NCC02');
GO

-- HÓA ĐƠN
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM)
VALUES ('HD001', '2025-10-16', 'TH02', 'NV002', 'KH0000001', 'KM001');
GO

-- CHI TIẾT HÓA ĐƠN
INSERT INTO ChiTietHoaDon VALUES
('HD001', 'T01', 20),
('HD001', 'T05', 10);
GO

-- PHIẾU ĐẶT HÀNG
INSERT INTO PhieuDatHang VALUES
('PDH001', 'NCC01', 'NV003', '2025-10-20', 0, N'Đã đặt hàng', N'Yêu cầu giao sớm'),
('PDH002', 'NCC02', 'NV001', '2025-10-15', 0, N'Đã nhập kho', NULL),
('PDH003', 'NCC01', 'NV003', '2025-10-22', 0, N'Đã hủy', N'Hủy do đặt nhầm');
GO

-- CHI TIẾT PHIẾU ĐẶT HÀNG
INSERT INTO ChiTietPhieuDatHang VALUES
('PDH001', 'T01', 50, 1000),
('PDH001', 'T02', 100, 1800),
('PDH001', 'T05', 200, 800),
('PDH002', 'T03', 100, 1500),
('PDH002', 'T04', 30, 6000),
('PDH002', 'T07', 50, 1000),
('PDH003', 'T01', 500, 1000);
GO

-- CẬP NHẬT TỔNG TIỀN
UPDATE PhieuDatHang SET TongTien = 390000 WHERE MaPhieu = 'PDH001';
UPDATE PhieuDatHang SET TongTien = 380000 WHERE MaPhieu = 'PDH002';
UPDATE PhieuDatHang SET TongTien = 500000 WHERE MaPhieu = 'PDH003';
GO
