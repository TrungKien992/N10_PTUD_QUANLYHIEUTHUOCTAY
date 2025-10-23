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
    quyenHan NVARCHAR(50) 
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


GO
-- BẢNG PHIẾU ĐẶT HÀNG
CREATE TABLE PhieuDatHang (
    MaPhieu VARCHAR(20) PRIMARY KEY,       -- Mã phiếu (Ví dụ: PDH001)
    MaNCC VARCHAR(20) NOT NULL,          -- Khóa ngoại đến NhaCungCap
    MaNhanVien VARCHAR(20) NOT NULL,     -- Khóa ngoại đến NhanVien (người lập phiếu)
    NgayDat DATE NOT NULL DEFAULT GETDATE(), -- Ngày tạo phiếu (mặc định là ngày hiện tại)
    TongTien FLOAT NOT NULL DEFAULT 0 CHECK (TongTien >= 0), -- Tổng tiền của phiếu (nên tính từ chi tiết)
    TrangThai NVARCHAR(50) NOT NULL DEFAULT N'Đã đặt hàng', -- Trạng thái (Ví dụ: 'Đã đặt hàng', 'Đã nhập kho', 'Đã hủy')
    GhiChu NVARCHAR(255),                  -- Ghi chú thêm cho phiếu

    -- Khai báo khóa ngoại
    FOREIGN KEY (MaNCC) REFERENCES NhaCungCap(maNhaCungCap),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNV)
);
GO

GO
-- BẢNG CHI TIẾT PHIẾU ĐẶT HÀNG
CREATE TABLE ChiTietPhieuDatHang (
    MaPhieu VARCHAR(20),                   -- Khóa ngoại đến PhieuDatHang
    MaThuoc VARCHAR(20),                   -- Khóa ngoại đến Thuoc
    SoLuong INT NOT NULL CHECK (SoLuong > 0), -- Số lượng thuốc đặt
    DonGia FLOAT NOT NULL CHECK (DonGia >= 0), -- Đơn giá tại thời điểm đặt (có thể khác giá nhập/bán hiện tại)
    ThanhTien AS (SoLuong * DonGia),         -- Cột tính toán tự động (SQL Server)

    -- Khóa chính gồm Mã phiếu và Mã thuốc (đảm bảo mỗi thuốc chỉ xuất hiện 1 lần trong 1 phiếu)
    PRIMARY KEY (MaPhieu, MaThuoc), 

    -- Khai báo khóa ngoại
    FOREIGN KEY (MaPhieu) REFERENCES PhieuDatHang(MaPhieu) ON DELETE CASCADE, -- Nếu xóa phiếu thì xóa luôn chi tiết
    FOREIGN KEY (MaThuoc) REFERENCES Thuoc(MaThuoc)
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
INSERT INTO KhuyenMai (maKM, tenChuongTrinh, giaTri, ngayBatDau, ngayKetThuc, soLuongToiDa, trangThai)
VALUES
('KM001', N'Giảm giá Black Friday', 15.0, '2025-11-20', '2025-11-30', 100, 1),
('KM002', N'Chào mừng Lễ 2/9', 10.0, '2025-09-01', '2025-09-03', 50, 0),
('KM003', N'Giảm giá cuối tuần (Tháng 10)', 5.0, '2025-10-20', '2025-10-27', NULL, 1),
('KM004', N'Tri ân khách hàng', 20.0, '2025-12-01', '2025-12-20', 200, 1);
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




-- PHIEU DAT HANG
-- Phiếu 1: Do NV003 (Nhân viên kho) đặt hàng từ NCC01 (DHG), trạng thái 'Đã đặt hàng'
INSERT INTO PhieuDatHang (MaPhieu, MaNCC, MaNhanVien, NgayDat, TrangThai, GhiChu) VALUES
('PDH001', 'NCC01', 'NV003', '2025-10-20', N'Đã đặt hàng', N'Yêu cầu giao hàng sớm');
GO

-- Phiếu 2: Do NV001 (Quản lý) đặt hàng từ NCC02 (Traphaco), trạng thái 'Đã nhập kho'
INSERT INTO PhieuDatHang (MaPhieu, MaNCC, MaNhanVien, NgayDat, TrangThai, GhiChu) VALUES
('PDH002', 'NCC02', 'NV001', '2025-10-15', N'Đã nhập kho', NULL);
GO

-- Phiếu 3: Do NV003 đặt từ NCC01 nhưng đã hủy
INSERT INTO PhieuDatHang (MaPhieu, MaNCC, MaNhanVien, NgayDat, TrangThai, GhiChu) VALUES
('PDH003', 'NCC01', 'NV003', '2025-10-22', N'Đã hủy', N'Hủy do đặt nhầm số lượng');
GO


-- CHI TIET PHIEU DAT HANG

-- Chi tiết cho PDH001 (Từ NCC01)
INSERT INTO ChiTietPhieuDatHang (MaPhieu, MaThuoc, SoLuong, DonGia) VALUES
('PDH001', 'T01', 50, 1000), -- Paracetamol 500mg, giá nhập 1000
('PDH001', 'T02', 100, 1800), -- Panadol Extra, giá nhập 1800
('PDH001', 'T05', 200, 800);  -- Vitamin C 500mg, giá nhập 800
GO

-- Chi tiết cho PDH002 (Từ NCC02)
INSERT INTO ChiTietPhieuDatHang (MaPhieu, MaThuoc, SoLuong, DonGia) VALUES
('PDH002', 'T03', 100, 1500), -- Amoxicillin 500mg, giá nhập 1500
('PDH002', 'T04', 30, 6000),  -- Augmentin 625mg, giá nhập 6000
('PDH002', 'T07', 50, 1000);  -- Aspirin 81mg, giá nhập 1000
GO

-- Chi tiết cho PDH003 (Đã hủy, vẫn nên có chi tiết để biết đã đặt gì)
INSERT INTO ChiTietPhieuDatHang (MaPhieu, MaThuoc, SoLuong, DonGia) VALUES
('PDH003', 'T01', 500, 1000); -- Đặt nhầm 500 viên Paracetamol
GO


-- CẬP NHẬT TỔNG TIỀN CHO CÁC PHIẾU (SAU KHI ĐÃ INSERT CHI TIẾT)
-- Cách này thủ công, trong ứng dụng bạn nên tính tổng khi lưu phiếu

-- Tính tổng cho PDH001: (50*1000) + (100*1800) + (200*800) = 50000 + 180000 + 160000 = 390000
UPDATE PhieuDatHang SET TongTien = 390000 WHERE MaPhieu = 'PDH001';
GO

-- Tính tổng cho PDH002: (100*1500) + (30*6000) + (50*1000) = 150000 + 180000 + 50000 = 380000
UPDATE PhieuDatHang SET TongTien = 380000 WHERE MaPhieu = 'PDH002';
GO

-- Tính tổng cho PDH003: (500*1000) = 500000
UPDATE PhieuDatHang SET TongTien = 500000 WHERE MaPhieu = 'PDH003';
GO