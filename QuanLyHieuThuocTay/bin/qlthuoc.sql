USE master
GO

CREATE DATABASE QLThuoc
GO

USE [QLThuoc]
GO

CREATE TABLE TaiKhoan (
    maTK VARCHAR(20) PRIMARY KEY,
    tenTK NVARCHAR(50) NOT NULL,
    matKhau VARCHAR(100) NOT NULL,
    quyenHan NVARCHAR(50) NOT NULL
);
GO

CREATE TABLE ChucVu (
    maChucVu VARCHAR(20) PRIMARY KEY,
    tenChucVu NVARCHAR(50) NOT NULL,
    moTa NVARCHAR(255)
);
GO

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

-- BẢNG KHÁCH HÀNG (ĐÃ BỎ CỘT GIỚI TÍNH)
CREATE TABLE KhachHang (
    maKH VARCHAR(20) PRIMARY KEY,
    tenKH NVARCHAR(100) NOT NULL,
    diaChi NVARCHAR(255),
    sdt NVARCHAR(15) UNIQUE
);
GO

CREATE TABLE KhuyenMai (
    maKM VARCHAR(20) PRIMARY KEY,
    tenChuongTrinh NVARCHAR(100) NOT NULL,
    giaTri FLOAT NOT NULL,
    ngayBatDau DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
    dieuKienApDung NVARCHAR(255),
    phamViApDung NVARCHAR(255),
    soLuongToiDa INT,
    trangThai INT DEFAULT 1 CHECK (trangThai IN (0,1)) -- 1: Đang diễn ra, 0: Đã kết thúc
);
GO

CREATE TABLE Thue (
    maThue VARCHAR(20) PRIMARY KEY,
    tiLe FLOAT NOT NULL CHECK (tiLe >= 0),
    moTa NVARCHAR(255),
    ngayApDung DATE NOT NULL
);
GO

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
GO

CREATE TABLE KeThuoc (
    maKe VARCHAR(20) PRIMARY KEY,
    viTri INT NOT NULL,
    loaiKe NVARCHAR(50)
);
GO

-- BẢNG NHÀ CUNG CẤP (CẤU TRÚC ĐẦY ĐỦ THEO GIAO DIỆN)
CREATE TABLE NhaCungCap (
    maNhaCungCap VARCHAR(20) PRIMARY KEY,
    tenNhaCungCap NVARCHAR(100) NOT NULL,
    soDienThoai NVARCHAR(15),
    email VARCHAR(100),
    diaChi NVARCHAR(255),
    trangThai BIT DEFAULT 1, -- 1: Đang hợp tác, 0: Ngừng hợp tác
    ghiChu NVARCHAR(255)
);
GO

-- BẢNG THUỐC (CẤU TRÚC ĐẦY ĐỦ THEO GIAO DIỆN)
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
-- CHÈN DỮ LIỆU MẪU
--------------------------------------------------

-- TAI KHOAN
INSERT INTO TaiKhoan (maTK, tenTK, matKhau, quyenHan) VALUES
('TK01', 'admin', '123', N'Quản lý'),
('TK02', 'nvbh01', '123', N'Nhân viên bán hàng'),
('TK03', 'nvkho01', '123', N'Nhân viên kho');
GO

-- CHUC VU
INSERT INTO ChucVu (maChucVu, tenChucVu, moTa) VALUES
('CV01', N'Quản lý', N'Quản lý toàn bộ hệ thống'),
('CV02', N'Nhân viên bán hàng', N'Phụ trách bán thuốc và lập hóa đơn'),
('CV03', N'Nhân viên kho', N'Quản lý nhập - xuất thuốc trong kho');
GO

-- NHAN VIEN
INSERT INTO NhanVien (maNV, tenNV, ngaySinh, gioiTinh, chucVu, sdt, diaChi, anh, maTK) VALUES
('NV001', N'Nguyễn Văn An', '1990-05-15', N'Nam', 'CV01', '0912345678', N'Quận 1, TP. Hồ Chí Minh', N'/male-1.jpg', 'TK01'),
('NV002', N'Trần Thị Bích', '1995-08-10', N'Nữ', 'CV02', '0987654321', N'Quận 5, TP. Hồ Chí Minh', N'/male-1.jpg', 'TK02'),
('NV003', N'Lê Văn Cường', '1993-03-20', N'Nam', 'CV03', '0901234567', N'Quận Gò Vấp, TP. Hồ Chí Minh', N'/male-1.jpg', 'TK03');
GO
-- KHACH HANG (ĐÃ BỎ CỘT GIỚI TÍNH)
INSERT INTO KhachHang (maKH, tenKH, diaChi, sdt) VALUES
('KH0000001', N'Phạm Minh Dũng', N'Quận 3, TP. Hồ Chí Minh', '0911222333'),
('KH0000002', N'Nguyễn Thu Trang', N'Quận 7, TP. Hồ Chí Minh', '0988776655'),
('KH0000003', N'Đỗ Văn Phúc', N'TP. Thủ Đức', '0977998877');
GO

-- KHUYEN MAI
INSERT INTO KhuyenMai (maKM, tenChuongTrinh, giaTri, ngayBatDau, ngayKetThuc, trangThai) VALUES
('KM01', N'Giảm giá 10% mừng khai trương', 10, '2025-10-01', '2025-10-31', 1),
('KM02', N'Mua 2 tặng 1 cho nhóm Vitamin', 0, '2025-09-01', '2025-12-31', 1);
GO

-- THUE
INSERT INTO Thue (maThue, tiLe, moTa, ngayApDung) VALUES
('TH01', 0.05, N'Thuế GTGT 5%', '2025-01-01'),
('TH02', 0.1, N'Thuế GTGT 10%', '2025-01-01');
GO

-- KE THUOC
INSERT INTO KeThuoc (maKe, viTri, loaiKe) VALUES
('K01', 1, N'Kháng sinh'),
('K02', 2, N'Giảm đau - hạ sốt'),
('K03', 3, N'Vitamin & khoáng chất'),
('K04', 4, N'Da liễu'),
('K05', 5, N'Tim mạch');
GO

-- NHA CUNG CAP
INSERT INTO NhaCungCap (maNhaCungCap, tenNhaCungCap, soDienThoai, email, diaChi, trangThai, ghiChu) VALUES
('NCC01', N'Công ty Dược Hậu Giang', '02923891433', 'contact@dhgpharma.com.vn', N'288 Bis Nguyễn Văn Cừ, An Hòa, Ninh Kiều, Cần Thơ', 1, N'Đối tác lớn, uy tín'),
('NCC02', N'Công ty Cổ phần Traphaco', '02436815032', 'info@traphaco.com.vn', N'75 Yên Ninh, Ba Đình, Hà Nội', 1, N'Chuyên về thuốc đông dược'),
('NCC03', N'Công ty Cổ phần Pymepharco', '02573829517', 'info@pymepharco.com', N'166-170 Nguyễn Huệ, Tuy Hòa, Phú Yên', 0, N'Đã ngừng hợp tác');
GO

-- THUOC
INSERT INTO Thuoc (maThuoc, tenThuoc, soLuong, giaNhap, giaBan, hanSuDung, thanhPhan, donViTinh, anh, maKe, maNhaCungCap) VALUES
('T01', N'Paracetamol 500mg', 100, 1000, 1500, '2026-12-31', N'Paracetamol', N'Viên', NULL, 'K02', 'NCC01'),
('T02', N'Panadol Extra', 200, 1800, 2500, '2026-11-30', N'Paracetamol, Caffeine', N'Viên', NULL, 'K02', 'NCC01'),
('T03', N'Amoxicillin 500mg', 150, 1500, 2000, '2027-03-31', N'Amoxicillin', N'Viên', NULL, 'K01', 'NCC02'),
('T04', N'Augmentin 625mg', 50, 6000, 8000, '2027-05-31', N'Amoxicillin + Clavulanic Acid', N'Viên', NULL, 'K01', 'NCC02'),
('T05', N'Vitamin C 500mg', 300, 800, 1200, '2027-02-28', N'Ascorbic Acid', N'Viên', NULL, 'K03', 'NCC01'),
('T06', N'Kem nghệ Thorakao', 80, 15000, 20000, '2026-12-31', N'Curcumin, tinh dầu nghệ', N'Tuýp', NULL, 'K04', 'NCC03'),
('T07', N'Aspirin 81mg', 120, 1000, 1500, '2027-08-31', N'Acetylsalicylic Acid', N'Viên', NULL, 'K05', 'NCC02');
GO

-- HOA DON
INSERT INTO HoaDon(maHD, ngayLap, maThue, maNV, maKH, maKM) VALUES
('HD001', '2025-10-16', 'TH02', 'NV002', 'KH0000001', 'KM01');
GO

-- CHI TIET HOA DON
INSERT INTO ChiTietHoaDon(maHD, maThuoc, soLuong) VALUES
('HD001', 'T01', 20),
('HD001', 'T05', 10);
GO