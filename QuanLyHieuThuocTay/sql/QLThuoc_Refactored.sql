USE master
GO

-- Xóa DB cũ nếu tồn tại để tạo mới (cẩn thận khi chạy trên DB thật)
IF DB_ID('QLThuoc') IS NOT NULL
BEGIN
    ALTER DATABASE QLThuoc SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE QLThuoc;
END
GO

CREATE DATABASE QLThuoc;
GO

USE [QLThuoc];
GO

--------------------------------------------------
-- BẢNG TÀI KHOẢN
-- Thêm trangThai (1=Hoạt động, 0=Vô hiệu hóa)
--------------------------------------------------
CREATE TABLE TaiKhoan (
    maTK VARCHAR(20) PRIMARY KEY,
    tenTK NVARCHAR(50) NOT NULL UNIQUE,
    matKhau VARCHAR(100) NOT NULL,
    quyenHan NVARCHAR(50),
    trangThai BIT DEFAULT 1 NOT NULL -- 1 = Active, 0 = Inactive
);
GO

--------------------------------------------------
-- BẢNG CHỨC VỤ
--------------------------------------------------
CREATE TABLE ChucVu (
    maChucVu VARCHAR(20) PRIMARY KEY,
    tenChucVu NVARCHAR(50) NOT NULL UNIQUE,
    moTa NVARCHAR(255)
);
GO

--------------------------------------------------
-- BẢNG NHÂN VIÊN
-- Đã có 'trangThai' (Soft Delete)
--------------------------------------------------
CREATE TABLE NhanVien (
    maNV VARCHAR(20) PRIMARY KEY,
    tenNV NVARCHAR(100) NOT NULL,
    ngaySinh DATE NOT NULL,
    gioiTinh NVARCHAR(10),
    chucVu VARCHAR(20),
    sdt NVARCHAR(15) UNIQUE,
    diaChi NVARCHAR(255),
    anh NVARCHAR(255),
    maTK VARCHAR(20) UNIQUE,
    trangThai NVARCHAR(20) DEFAULT N'Còn làm việc' CHECK (trangThai IN (N'Còn làm việc', N'Nghỉ việc')),
    FOREIGN KEY (maTK) REFERENCES TaiKhoan(maTK),
    FOREIGN KEY (chucVu) REFERENCES ChucVu(maChucVu)
);
GO


--------------------------------------------------
-- BẢNG KHÁCH HÀNG
-- Thêm trangThai (1=Còn giao dịch, 0=Ngừng giao dịch)
--------------------------------------------------
CREATE TABLE KhachHang (
    maKH VARCHAR(20) PRIMARY KEY,
    tenKH NVARCHAR(100) NOT NULL,
    sdt NVARCHAR(15) UNIQUE,
	diaChi NVARCHAR(255),
    trangThai BIT DEFAULT 1 NOT NULL -- 1 = Active, 0 = Inactive
);
GO

--------------------------------------------------
-- BẢNG KHUYẾN MÃI
-- Đã có 'trangThai' (Soft Delete)
--------------------------------------------------
CREATE TABLE KhuyenMai (
    maKM VARCHAR(20) PRIMARY KEY,
    tenChuongTrinh NVARCHAR(100) NOT NULL,
    giaTri FLOAT NOT NULL,
    ngayBatDau DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
    soLuongToiDa INT,
    trangThai INT DEFAULT 1 CHECK (trangThai IN (0,1)) -- 1 = Active, 0 = Inactive
);
GO

--------------------------------------------------
-- BẢNG THUẾ
-- Thêm trangThai (1=Còn hiệu lực, 0=Hết hiệu lực)
--------------------------------------------------
CREATE TABLE Thue (
    maThue VARCHAR(20) PRIMARY KEY,
    tiLe FLOAT NOT NULL CHECK (tiLe >= 0),
    moTa NVARCHAR(255),
    ngayApDung DATE NOT NULL,
    trangThai BIT DEFAULT 1 NOT NULL -- 1 = Active, 0 = Inactive
);
GO

--------------------------------------------------
-- BẢNG HÓA ĐƠN
-- Thêm trangThai (Hoàn thành, Đã hủy)
--------------------------------------------------
CREATE TABLE HoaDon (
    maHD VARCHAR(20) PRIMARY KEY,
    ngayLap DATE NOT NULL DEFAULT GETDATE(),
    maKM VARCHAR(20),
    maThue VARCHAR(20) NOT NULL,
    maNV VARCHAR(20) NOT NULL,
    maKH VARCHAR(20),
    tienKhachDua FLOAT DEFAULT 0,
    tienThua FLOAT DEFAULT 0,
    trangThai NVARCHAR(50) DEFAULT N'Hoàn thành' CHECK (trangThai IN (N'Hoàn thành', N'Đã hủy')),
    FOREIGN KEY (maKM) REFERENCES KhuyenMai(maKM),
    FOREIGN KEY (maThue) REFERENCES Thue(maThue),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV) ON DELETE NO ACTION,
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH) ON DELETE NO ACTION
);
GO

--------------------------------------------------
-- BẢNG KỆ THUỐC
-- Thêm trangThai (1=Sử dụng, 0=Không sử dụng)
--------------------------------------------------
CREATE TABLE KeThuoc (
    maKe VARCHAR(20) PRIMARY KEY,
    viTri INT NOT NULL,
    loaiKe NVARCHAR(50),
    trangThai BIT DEFAULT 1 NOT NULL -- 1 = Active, 0 = Inactive
);
GO

--------------------------------------------------
-- BẢNG NHÀ CUNG CẤP
-- Đã có 'trangThai' (Soft Delete)
--------------------------------------------------
CREATE TABLE NhaCungCap (
    maNhaCungCap VARCHAR(20) PRIMARY KEY,
    tenNhaCungCap NVARCHAR(100) NOT NULL,
    soDienThoai NVARCHAR(15),
    email VARCHAR(100),
    diaChi NVARCHAR(255),
    trangThai BIT DEFAULT 1, -- 1 = Hợp tác, 0 = Ngừng hợp tác
    ghiChu NVARCHAR(255)
);
GO

--------------------------------------------------
-- BẢNG THUỐC
-- Thêm trangThai (Đang kinh doanh, Ngừng kinh doanh)
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
    trangThai NVARCHAR(50) DEFAULT N'Đang kinh doanh' CHECK (trangThai IN (N'Đang kinh doanh', N'Ngừng kinh doanh')),
    FOREIGN KEY (maKe) REFERENCES KeThuoc(maKe),
    FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap(maNhaCungCap) ON DELETE NO ACTION
);
GO

--------------------------------------------------
-- BẢNG CHI TIẾT HÓA ĐƠN
-- Sửa FK maThuoc -> ON DELETE NO ACTION
--------------------------------------------------
CREATE TABLE ChiTietHoaDon (
    maHD VARCHAR(20),
    maThuoc VARCHAR(20),
    soLuong INT NOT NULL CHECK (soLuong > 0),
    donGia FLOAT NOT NULL, -- Thêm đơn giá (giá bán tại thời điểm mua)
    thanhTien AS (soLuong * donGia),
    PRIMARY KEY (maHD, maThuoc),
    FOREIGN KEY (maHD) REFERENCES HoaDon(maHD) ON DELETE CASCADE,
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc) ON DELETE NO ACTION
);
GO

--------------------------------------------------
-- PHIẾU ĐẶT HÀNG
-- Đã có 'TrangThai' (Lifecycle)
-- Sửa FK maThuoc -> ON DELETE NO ACTION
--------------------------------------------------
CREATE TABLE PhieuDatHang (
    MaPhieu VARCHAR(20) PRIMARY KEY,
    MaNCC VARCHAR(20) NOT NULL,
    MaNhanVien VARCHAR(20) NOT NULL,
    NgayDat DATE NOT NULL DEFAULT GETDATE(),
    TongTien FLOAT NOT NULL DEFAULT 0 CHECK (TongTien >= 0),
    TrangThai NVARCHAR(50) NOT NULL DEFAULT N'Đã đặt hàng' CHECK (TrangThai IN (N'Đã đặt hàng', N'Đã nhập kho', N'Đã hủy')),
    GhiChu NVARCHAR(255),
    FOREIGN KEY (MaNCC) REFERENCES NhaCungCap(maNhaCungCap) ON DELETE NO ACTION,
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNV) ON DELETE NO ACTION
);
GO

--------------------------------------------------
-- CHI TIẾT PHIẾU ĐẶT HÀNG
-- Sửa FK maThuoc -> ON DELETE NO ACTION
--------------------------------------------------
CREATE TABLE ChiTietPhieuDatHang (
    MaPhieu VARCHAR(20),
    MaThuoc VARCHAR(20),
    SoLuong INT NOT NULL CHECK (SoLuong > 0),
    DonGia FLOAT NOT NULL CHECK (DonGia >= 0),
    ThanhTien AS (SoLuong * DonGia),
    PRIMARY KEY (MaPhieu, MaThuoc),
    FOREIGN KEY (MaPhieu) REFERENCES PhieuDatHang(MaPhieu) ON DELETE CASCADE,
    FOREIGN KEY (MaThuoc) REFERENCES Thuoc(MaThuoc) ON DELETE NO ACTION
);
GO

--------------------------------------------------
-- PHIẾU CHỜ THANH TOÁN
-- Giữ nguyên, có thể XÓA CỨNG (Hard Delete)
-- Sửa FK maThuoc -> ON DELETE NO ACTION
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
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH) ON DELETE NO ACTION,
    FOREIGN KEY (maNVLap) REFERENCES NhanVien(maNV) ON DELETE NO ACTION
);
GO

--------------------------------------------------
-- CHI TIẾT PHIẾU CHỜ
-- Sửa FK maThuoc -> ON DELETE NO ACTION
--------------------------------------------------
CREATE TABLE ChiTietPhieuCho (
    maPhieuCho VARCHAR(20) NOT NULL,
    maThuoc VARCHAR(20) NOT NULL,
    soLuong INT NOT NULL CHECK (soLuong > 0),
    donGia FLOAT NOT NULL,
    thanhTien AS (soLuong * donGia),
    PRIMARY KEY (maPhieuCho, maThuoc),
    FOREIGN KEY (maPhieuCho) REFERENCES PhieuChoThanhToan(maPhieuCho) ON DELETE CASCADE,
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc) ON DELETE NO ACTION
);
GO

--------------------------------------------------
-- PHIẾU ĐỔI TRẢ
-- Thêm trangThai (Hoàn thành, Đã hủy)
--------------------------------------------------
CREATE TABLE PhieuDoiTra (
    maPDT CHAR(10) PRIMARY KEY,           
    ngayDoiTra DATE NOT NULL DEFAULT GETDATE(),        
    lyDo NVARCHAR(255),             
    loaiPhieu NVARCHAR(20) NOT NULL CHECK (loaiPhieu IN (N'Đổi thuốc', N'Trả thuốc')), 
    tienHoanLai DECIMAL(18,2) DEFAULT 0,
    maNV VARCHAR(20) NOT NULL,  
    maHD VARCHAR(20) NOT NULL,
    trangThai NVARCHAR(50) DEFAULT N'Hoàn thành' CHECK (trangThai IN (N'Hoàn thành', N'Đã hủy')),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV) ON DELETE NO ACTION,
    FOREIGN KEY (maHD) REFERENCES HoaDon(maHD) ON DELETE NO ACTION
);
GO

--------------------------------------------------
-- CHI TIẾT PHIẾU ĐỔI TRẢ
-- Sửa FK maPDT -> ON DELETE CASCADE
-- Sửa FK maThuoc -> ON DELETE NO ACTION
--------------------------------------------------
CREATE TABLE CTPhieuDoiTra (
    maPDT CHAR(10) NOT NULL,  
    maThuoc VARCHAR(20) NOT NULL,
    soLuong INT NOT NULL CHECK (soLuong > 0),
    PRIMARY KEY (maPDT, maThuoc),
    FOREIGN KEY (maPDT) REFERENCES PhieuDoiTra(maPDT) ON DELETE CASCADE,
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc) ON DELETE NO ACTION
);
GO

--------------------------------------------------
-- DỮ LIỆU MẪU (DATA)
-- (Giữ nguyên, các cột 'trangThai' mới sẽ tự nhận giá trị DEFAULT)
--------------------------------------------------

-- TAI KHOAN
INSERT INTO TaiKhoan (maTK, tenTK, matKhau, quyenHan) VALUES
('TK01', 'admin', '123', N'Quản lý'),
('TK02', 'nvbh01', '123', N'Nhân viên bán hàng'),
('TK03', 'nvbh02', '123', N'Nhân viên bán hàng');
GO
-- Vô hiệu hóa 1 tài khoản
UPDATE TaiKhoan SET trangThai = 0 WHERE maTK = 'TK03';
GO

-- CHUC VU
INSERT INTO ChucVu VALUES
('CV01', N'Quản lý', N'Quản lý toàn bộ hệ thống'),
('CV02', N'Nhân viên bán hàng', N'Phụ trách bán thuốc và lập hóa đơn');
GO

-- NHAN VIÊN
INSERT INTO NhanVien (maNV, tenNV, ngaySinh, gioiTinh, chucVu, sdt, diaChi, anh, maTK, trangThai) VALUES
('NV001', N'Nguyễn Văn An', '1990-05-15', N'Nam', 'CV01', '0912345678', N'Quận 1, TP.HCM', NULL, 'TK01', N'Còn làm việc'),
('NV002', N'Trần Thị Bích', '1995-08-10', N'Nữ', 'CV02', '0987654321', N'Quận 5, TP.HCM', NULL, 'TK02', N'Còn làm việc'),
('NV003', N'Lê Văn Cường', '1993-03-20', N'Nam', 'CV02', '0901234567', N'Gò Vấp, TP.HCM', NULL, 'TK03', N'Nghỉ việc');
GO


-- KHÁCH HÀNG
INSERT INTO KhachHang (maKH, tenKH, sdt, diaChi) VALUES
('KH0000001', N'Phạm Minh Dũng', '0911222333' ,N'Quận 3, TP.HCM'),
('KH0000002', N'Nguyễn Thu Trang', '0988776655', N'Quận 7, TP.HCM'),
('KH0000003', N'Đỗ Văn Phúc', '0977998877', N'TP. Thủ Đức');
GO

-- KHUYẾN MÃI
INSERT INTO KhuyenMai (maKM, tenChuongTrinh, giaTri, ngayBatDau, ngayKetThuc, soLuongToiDa, trangThai) VALUES
('KM001', N'Giảm giá Black Friday', 15, '2025-11-20', '2025-11-30', 100, 1),
('KM002', N'Chào mừng Lễ 2/9', 10, '2025-09-01', '2025-09-03', 50, 0), -- trangThai = 0 (Hết hiệu lực)
('KM003', N'Giảm giá cuối tuần (Tháng 10)', 5, '2025-10-20', '2025-10-27', NULL, 1),
('KM004', N'Tri ân khách hàng', 20, '2025-12-01', '2025-12-20', 200, 1);
GO

-- THUẾ
INSERT INTO Thue (maThue, tiLe, moTa, ngayApDung) VALUES
('TH01', 0.05, N'Thuế GTGT 5%', '2025-01-01'),
('TH02', 0.1, N'Thuế GTGT 10%', '2025-01-01');
GO

-- KỆ THUỐC
INSERT INTO KeThuoc (maKe, viTri, loaiKe) VALUES
('K01', 1, N'Kháng sinh'),
('K02', 2, N'Giảm đau - hạ sốt'),
('K03', 3, N'Vitamin & khoáng chất'),
('K04', 4, N'Da liễu'),
('K05', 5, N'Tim mạch');
GO

-- NHÀ CUNG CẤP
INSERT INTO NhaCungCap (maNhaCungCap, tenNhaCungCap, soDienThoai, email, diaChi, trangThai, ghiChu) VALUES
('NCC01', N'Dược Hậu Giang', '02923891433', 'contact@dhgpharma.com.vn', N'Cần Thơ', 1, N'Đối tác lớn'),
('NCC02', N'Traphaco', '02436815032', 'info@traphaco.com.vn', N'Hà Nội', 1, N'Thuốc đông dược'),
('NCC03', N'Pymepharco', '02573829517', 'info@pymepharco.com', N'Phú Yên', 0, N'Ngừng hợp tác'); -- trangThai = 0 (Soft Delete)
GO

-- THUỐC
INSERT INTO Thuoc (maThuoc, tenThuoc, soLuong, giaNhap, giaBan, hanSuDung, thanhPhan, donViTinh, anh, maKe, maNhaCungCap) VALUES
('T01', N'Paracetamol 500mg', 100, 1000, 1500, '2026-12-31', N'Paracetamol', N'Viên', NULL, 'K02', 'NCC01'),
('T02', N'Panadol Extra', 200, 1800, 2500, '2026-11-30', N'Paracetamol, Caffeine', N'Viên', NULL, 'K02', 'NCC01'),
('T03', N'Amoxicillin 500mg', 150, 1500, 2000, '2027-03-31', N'Amoxicillin', N'Viên', NULL, 'K01', 'NCC02'),
('T04', N'Augmentin 625mg', 50, 6000, 8000, '2027-05-31', N'Amoxicillin + Clavulanic Acid', N'Viên', NULL, 'K01', 'NCC02'),
('T05', N'Vitamin C 500mg', 300, 800, 1200, '2027-02-28', N'Ascorbic Acid', N'Viên', NULL, 'K03', 'NCC01'),
('T06', N'Kem nghệ Thorakao', 80, 15000, 20000, '2026-12-31', N'Curcumin', N'Tuýp', NULL, 'K04', 'NCC03'),
('T07', N'Aspirin 81mg', 120, 1000, 1500, '2027-08-31', N'Acetylsalicylic Acid', N'Viên', NULL, 'K05', 'NCC02');
GO
-- Ngừng kinh doanh 1 loại thuốc
UPDATE Thuoc SET trangThai = N'Ngừng kinh doanh' WHERE maThuoc = 'T06';
GO

-- HÓA ĐƠN
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM)
VALUES ('HD001', '2025-10-16', 'TH02', 'NV002', 'KH0000001', 'KM003');
GO

-- CHI TIẾT HÓA ĐƠN
-- Thêm donGia (giá bán tại thời điểm đó)
INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong, donGia) VALUES
('HD001', 'T01', 20, 1500),
('HD001', 'T05', 10, 1200);
GO

-- PHIẾU ĐẶT HÀNG
INSERT INTO PhieuDatHang (MaPhieu, MaNCC, MaNhanVien, NgayDat, TrangThai, GhiChu) VALUES
('PDH001', 'NCC01', 'NV003', '2025-10-20', N'Đã đặt hàng', N'Yêu cầu giao sớm'),
('PDH002', 'NCC02', 'NV001', '2025-10-15', N'Đã nhập kho', NULL),
('PDH003', 'NCC01', 'NV003', '2025-10-22', N'Đã hủy', N'Hủy do đặt nhầm');
GO

-- CHI TIẾT PHIẾU ĐẶT HÀNG
INSERT INTO ChiTietPhieuDatHang (MaPhieu, MaThuoc, SoLuong, DonGia) VALUES
('PDH001', 'T01', 50, 1000),
('PDH001', 'T02', 100, 1800),
('PDH001', 'T05', 200, 800),
('PDH002', 'T03', 100, 1500),
('PDH002', 'T04', 30, 6000),
('PDH002', 'T07', 50, 1000),
('PDH003', 'T01', 500, 1000);
GO

-- CẬP NHẬT TỔNG TIỀN (Vẫn cần trigger hoặc logic nghiệp vụ để tự động hóa)
UPDATE PhieuDatHang SET TongTien = (SELECT SUM(ThanhTien) FROM ChiTietPhieuDatHang WHERE MaPhieu = 'PDH001') WHERE MaPhieu = 'PDH001';
UPDATE PhieuDatHang SET TongTien = (SELECT SUM(ThanhTien) FROM ChiTietPhieuDatHang WHERE MaPhieu = 'PDH002') WHERE MaPhieu = 'PDH002';
UPDATE PhieuDatHang SET TongTien = (SELECT SUM(ThanhTien) FROM ChiTietPhieuDatHang WHERE MaPhieu = 'PDH003') WHERE MaPhieu = 'PDH003';
GO

-- PHIẾU ĐỔI TRẢ
INSERT INTO PhieuDoiTra (maPDT, ngayDoiTra, lyDo, loaiPhieu, tienHoanLai, maNV, maHD)
VALUES ('PDT001', '2025-10-20', N'Thuốc bị ẩm, khách trả lại', N'Trả thuốc', 7500, 'NV002', 'HD001');

INSERT INTO CTPhieuDoiTra (maPDT, maThuoc, soLuong)
VALUES ('PDT001', 'T01', 5);

INSERT INTO PhieuDoiTra (maPDT, ngayDoiTra, lyDo, loaiPhieu, tienHoanLai, maNV, maHD)
VALUES ('PDT002', '2025-10-22', N'Khách muốn đổi sang loại Vitamin khác', N'Đổi thuốc', 0, 'NV002', 'HD001');

INSERT INTO CTPhieuDoiTra (maPDT, maThuoc, soLuong)
VALUES ('PDT002', 'T05', 2);
GO

PRINT N'Tạo cơ sở dữ liệu và chèn dữ liệu mẫu thành công!';
GO
