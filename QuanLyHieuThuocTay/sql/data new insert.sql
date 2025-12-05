USE [QLThuoc];
GO

--------------------------------------------------
-- BỔ SUNG NHÀ CUNG CẤP (Thêm 7)
--------------------------------------------------
INSERT INTO NhaCungCap (maNhaCungCap, tenNhaCungCap, soDienThoai, email, diaChi, trangThai, ghiChu) VALUES
('NCC04', N'Mekophar', '02838650357', 'info@mekophar.com', N'Quận 11, TP.HCM', 1, NULL),
('NCC05', N'Imexpharm', '02773822108', 'info@imexpharm.com', N'Đồng Tháp', 1, N'Chuyên kháng sinh'),
('NCC06', N'Sanofi Việt Nam', '02838298520', 'contact.vn@sanofi.com', N'Quận 1, TP.HCM', 1, N'Hàng nhập khẩu'),
('NCC07', N'OPV', '02513992900', 'info@opv.com.vn', N'Đồng Nai', 1, N'Chuyên vitamin'),
('NCC08', N'Domesco', '02773851270', 'vphcm@domesco.com', N'Đồng Tháp', 1, NULL),
('NCC09', N'Bidiphar', '02563846040', 'info@bidiphar.com', N'Bình Định', 1, NULL),
('NCC10', N'Pharmedic', '02838555137', 'info@pharmedic.com.vn', N'Quận 11, TP.HCM', 1, N'Thuốc sát khuẩn');
GO

--------------------------------------------------
-- BỔ SUNG KHUYẾN MÃI (Thêm 10, bao gồm hết hạn/hết số lượng)
--------------------------------------------------
INSERT INTO KhuyenMai (maKM, tenChuongTrinh, giaTri, ngayBatDau, ngayKetThuc, soLuongToiDa, trangThai) VALUES
('KM005', N'Mừng 30/4', 10, '2025-04-28', '2025-05-02', 100, 0), -- Đã hết hạn
('KM006', N'Quốc tế Thiếu nhi 1/6', 15, '2025-06-01', '2025-06-03', 200, 0), -- Đã hết hạn
('KM007', N'Hè rực rỡ', 5, '2025-07-01', '2025-07-15', NULL, 0), -- Đã hết hạn
('KM008', N'Back to School', 10, '2025-08-25', '2025-09-05', 100, 0), -- Đã hết hạn
('KM009', N'Giảm giá tháng 10 (cũ)', 5, '2025-10-01', '2025-10-15', 50, 0), -- Đã hết hạn
('KM010', N'Giảm giá đặc biệt (Hết SL)', 20, '2025-10-25', '2025-11-05', 30, 0), -- Giả lập hết số lượng (trangThai = 0)
('KM011', N'Giáng sinh an lành', 15, '2025-12-20', '2025-12-25', 500, 1),
('KM012', N'Chào năm mới 2026', 20, '2025-12-30', '2026-01-05', 200, 1),
('KM013', N'Tri ân tháng 11', 10, '2025-11-01', '2025-11-30', NULL, 1),
('KM014', N'Giảm giá thuốc bổ', 5, '2025-11-05', '2025-11-15', 100, 1);
GO

--------------------------------------------------
-- BỔ SUNG KHÁCH HÀNG (Thêm 47)
--------------------------------------------------
INSERT INTO KhachHang (maKH, tenKH, sdt, diaChi) VALUES
('KH0000004', N'Lê Thị Hoài An', '0905111222', N'Quận Bình Thạnh'),
('KH0000005', N'Trần Văn Bình', '0905333444', N'Quận 10'),
('KH0000006', N'Võ Minh Cường', '0905555666', N'Quận Phú Nhuận'),
('KH0000007', N'Đặng Ngọc Diệp', '0905777888', N'Quận Tân Bình'),
('KH0000008', N'Hoàng Văn Giang', '0905999000', N'Quận 12'),
('KH0000009', N'Phan Thanh Hùng', '0913111222', N'Quận Gò Vấp'),
('KH0000010', N'Bùi Thị Kim Liên', '0913333444', N'TP. Thủ Đức'),
('KH0000011', N'Dương Hoài Nam', '0913555666', N'Bình Chánh'),
('KH0000012', N'Ngô Gia Phúc', '0913777888', N'Quận 8'),
('KH0000013', N'Huỳnh Bảo Quyên', '0913999000', N'Quận 6'),
('KH0000014', N'Mai Anh Tuấn', '0981111222', N'Quận 11'),
('KH0000015', N'Lý Mỹ Uyên', '0981333444', N'Quận 5'),
('KH0000016', N'Nguyễn Đức Thịnh', '0981555666', N'Quận 4'),
('KH0000017', N'Vũ Thị Thùy Trang', '0981777888', N'Quận 7'),
('KH0000018', N'Trịnh Minh Vương', '0981999000', N'Quận 1'),
('KH0000019', N'Đinh Quốc Thái', '0971111222', N'Hóc Môn'),
('KH0000020', N'Tô Ngọc Hà', '0971333444', N'Củ Chi'),
('KH0000021', N'Lưu Trí Vĩ', '0971555666', N'Quận Bình Tân'),
('KH0000022', N'Đào Xuân Trường', '0971777888', N'Quận Tân Phú'),
('KH0000023', N'Phạm Gia Hân', '0971999000', N'Quận 1'),
('KH0000024', N'Nguyễn Hoàng Yến', '0961111222', N'Quận 3'),
('KH0000025', N'Trần Đức Bo', '0961333444', N'Quận 10'),
('KH0000026', N'Huỳnh Lập', '0961555666', N'Quận 5'),
('KH0000027', N'Lê Dương Bảo Lâm', '0961777888', N'Quận 6'),
('KH0000028', N'Ngô Kiến Huy', '0961999000', N'Quận Phú Nhuận'),
('KH0000029', N'Hồ Quang Hiếu', '0902111222', N'Quận Tân Bình'),
('KH0000030', N'Đông Nhi', '0902333444', N'Quận 1'),
('KH0000031', N'Ông Cao Thắng', '0902555666', N'Quận 1'),
('KH0000032', N'Bảo Thy', '0902777888', N'Quận 7'),
('KH0000033', N'Noo Phước Thịnh', '0902999000', N'Quận 3'),
('KH0000034', N'Sơn Tùng M-TP', '0903111222', N'Quận 7'),
('KH0000035', N'Hari Won', '0903333444', N'Quận 10'),
('KH0000036', N'Trấn Thành', '0903555666', N'Quận 10'),
('KH0000037', N'Trường Giang', '0903777888', N'Quận Phú Nhuận'),
('KH0000038', N'Nhã Phương', '0903999000', N'Quận Phú Nhuận'),
('KH0000039', N'Thu Trang', '0904111222', N'Quận Gò Vấp'),
('KH0000040', N'Tiến Luật', '0904333444', N'Quận Gò Vấp'),
('KH0000041', N'Việt Hương', '0904555666', N'Quận 10'),
('KH0000042', N'Hoài Linh', '0904777888', N'Quận Phú Nhuận'),
('KH0000043', N'Chí Tài', '0904999000', N'Quận 1'),
('KH0000044', N'Thúy Nga', '0906111222', N'Quận 5'),
('KH0000045', N'Tấn Beo', '0906333444', N'Quận 11'),
('KH0000046', N'Tấn Bo', '0906555666', N'Quận 11'),
('KH0000047', N'Minh Nhí', '0906777888', N'Quận 10'),
('KH0000048', N'Hồng Vân', '0906999000', N'Quận Phú Nhuận'),
('KH0000049', N'Đàm Vĩnh Hưng', '0907111222', N'Quận 1'),
('KH0000050', N'Mỹ Tâm', '0907333444', N'Quận Phú Nhuận');
GO

--------------------------------------------------
-- BỔ SUNG THUỐC (Thêm 50)
--------------------------------------------------
INSERT INTO Thuoc (maThuoc, tenThuoc, soLuong, giaNhap, giaBan, hanSuDung, thanhPhan, donViTinh, maKe, maNhaCungCap) VALUES
-- Hết hàng
('T08', N'Hapacol 650mg', 0, 1200, 1800, '2026-05-30', N'Paracetamol 650mg', N'Viên', 'K02', 'NCC01'),
('T09', N'Berberin 100mg', 0, 500, 800, '2027-01-15', N'Berberin', N'Viên', 'K01', 'NCC02'),

-- Hết hạn
('T10', N'Boganic', 50, 4000, 5500, '2025-01-20', N'Cao Actiso, Cao Rau đắng đất', N'Viên', 'K03', 'NCC02'),
('T11', N'Decolgen Forte', 30, 1000, 1500, '2025-09-10', N'Paracetamol, Phenylpropanolamine', N'Viên', 'K02', 'NCC07'),
('T12', N'Tiffy', 100, 900, 1300, '2024-12-25', N'Paracetamol, Chlorpheniramine', N'Viên', 'K02', 'NCC04'),

-- Thuốc thông thường (Thêm 45)
('T13', N'Strepsils Cool', 120, 2000, 2800, '2027-08-30', N'Amylmetacresol, Dichlorobenzyl Alcohol', N'Viên ngậm', 'K02', 'NCC06'),
('T14', N'Hoạt huyết dưỡng não', 250, 3000, 4000, '2028-02-10', N'Cao Đinh lăng, Cao Bạch quả', N'Viên', 'K05', 'NCC02'),
('T15', N'Calcium Corbiere', 80, 8000, 10000, '2027-11-11', N'Calcium', N'Ống', 'K03', 'NCC06'),
('T16', N'Enervon-C', 150, 2500, 3500, '2027-06-05', N'Vitamin C, B complex', N'Viên', 'K03', 'NCC07'),
('T17', N'Clarithromycin 500mg', 70, 7000, 9000, '2028-01-01', N'Clarithromycin', N'Viên', 'K01', 'NCC05'),
('T18', N'Acyclovir 200mg', 60, 1500, 2200, '2027-04-19', N'Acyclovir', N'Viên', 'K01', 'NCC08'),
('T19', N'Bepanthen', 90, 25000, 32000, '2027-10-30', N'Dexpanthenol', N'Tuýp', 'K04', 'NCC06'),
('T20', N'Nizoral Shampoo', 40, 45000, 55000, '2028-03-14', N'Ketoconazole', N'Chai', 'K04', 'NCC09'),
('T21', N'Betadin', 200, 18000, 25000, '2028-05-20', N'Povidone-iodine', N'Chai', 'K04', 'NCC10'),
('T22', N'Concor 5mg', 50, 4000, 5000, '2027-07-07', N'Bisoprolol', N'Viên', 'K05', 'NCC01'),
('T23', N'Phosphalugel', 130, 3500, 4500, '2027-09-09', N'Aluminium phosphate', N'Gói', 'K02', 'NCC06'),
('T24', N'Smecta', 100, 2800, 3800, '2028-04-01', N'Diosmectite', N'Gói', 'K02', 'NCC06'),
('T25', N'Cefixime 200mg', 80, 3000, 4200, '2028-06-15', N'Cefixime', N'Viên', 'K01', 'NCC05'),
('T26', N'Ciprofloxacin 500mg', 90, 2000, 2800, '2027-11-20', N'Ciprofloxacin', N'Viên', 'K01', 'NCC08'),
('T27', N'Azithromycin 500mg', 60, 8000, 10000, '2028-01-25', N'Azithromycin', N'Viên', 'K01', 'NCC09'),
('T28', N'Metronidazol 250mg', 110, 700, 1100, '2027-10-10', N'Metronidazol', N'Viên', 'K01', 'NCC04'),
('T29', N'Doxycyclin 100mg', 130, 900, 1400, '2028-02-14', N'Doxycyclin', N'Viên', 'K01', 'NCC03'),
('T30', N'Tetracyclin 500mg', 40, 1000, 1500, '2027-03-03', N'Tetracyclin', N'Viên', 'K01', 'NCC03'),
('T31', N'Erythromycin 500mg', 70, 2200, 3000, '2028-07-30', N'Erythromycin', N'Viên', 'K01', 'NCC05'),
('T32', N'Cephalexin 500mg', 150, 1800, 2500, '2028-08-08', N'Cephalexin', N'Viên', 'K01', 'NCC01'),
('T33', N'Levofloxacin 500mg', 50, 3500, 4800, '2027-12-12', N'Levofloxacin', N'Viên', 'K01', 'NCC08'),
('T34', N'Clindamycin 300mg', 60, 4000, 5200, '2028-05-15', N'Clindamycin', N'Viên', 'K01', 'NCC09'),
('T35', N'Ibuprofen 400mg', 200, 800, 1200, '2027-09-20', N'Ibuprofen', N'Viên', 'K02', 'NCC04'),
('T36', N'Diclofenac 50mg', 100, 1000, 1500, '2028-04-22', N'Diclofenac', N'Viên', 'K02', 'NCC07'),
('T37', N'Mefenamic Acid 500mg', 80, 1100, 1600, '2027-11-01', N'Mefenamic Acid', N'Viên', 'K02', 'NCC10'),
('T38', N'Naproxen 500mg', 70, 2000, 2800, '2028-01-18', N'Naproxen', N'Viên', 'K02', 'NCC06'),
('T39', N'Meloxicam 7.5mg', 90, 2500, 3500, '2028-06-25', N'Meloxicam', N'Viên', 'K02', 'NCC05'),
('T40', N'Celecoxib 200mg', 50, 5000, 6500, '2028-07-14', N'Celecoxib', N'Viên', 'K02', 'NCC01'),
('T41', N'Alaxan', 120, 1300, 1900, '2027-10-05', N'Paracetamol, Ibuprofen', N'Viên', 'K02', 'NCC07'),
('T42', N'Efferangan 500mg', 150, 1500, 2100, '2028-03-30', N'Paracetamol (dạng sủi)', N'Viên sủi', 'K02', 'NCC06'),
('T43', N'Tylenol 500mg', 100, 1200, 1700, '2027-12-25', N'Paracetamol', N'Viên', 'K02', 'NCC07'),
('T44', N'Siro ho Astex', 80, 20000, 26000, '2027-08-15', N'Húng chanh, Núc nác', N'Chai', 'K02', 'NCC02'),
('T45', N'Siro ho Prospan', 60, 50000, 65000, '2027-12-01', N'Cao lá thường xuân', N'Chai', 'K02', 'NCC06'),
('T46', N'Eugica', 200, 1500, 2200, '2028-02-28', N'Eucalyptol, Menthol', N'Viên nang mềm', 'K02', 'NCC01'),
('T47', N'Telfast 180mg', 70, 7000, 9000, '2028-05-19', N'Fexofenadine', N'Viên', 'K02', 'NCC06'),
('T48', N'Clorpheniramin 4mg', 300, 200, 400, '2027-11-30', N'Clorpheniramin', N'Viên', 'K02', 'NCC04'),
('T49', N'Cetirizin 10mg', 250, 300, 500, '2028-01-31', N'Cetirizine', N'Viên', 'K02', 'NCC08'),
('T50', N'Loratadin 10mg', 220, 400, 600, '2028-06-10', N'Loratadine', N'Viên', 'K02', 'NCC09'),
('T51', N'Vitamin B Complex', 180, 1000, 1500, '2027-09-15', N'B1, B6, B12', N'Viên', 'K03', 'NCC07'),
('T52', N'Becozyme', 100, 2000, 2800, '2028-04-05', N'Vitamin B, C', N'Viên', 'K03', 'NCC06'),
('T53', N'Redoxon', 90, 3000, 4000, '2027-10-22', N'Vitamin C (dạng sủi)', N'Viên sủi', 'K03', 'NCC06'),
('T54', N'Pharmaton', 60, 7000, 9000, '2028-03-18', N'Nhân sâm, Vitamin, Khoáng chất', N'Viên', 'K03', 'NCC06'),
('T55', N'Sắt (Ferrovit)', 130, 1200, 1800, '2028-07-20', N'Sắt, Acid Folic', N'Viên', 'K03', 'NCC10'),
('T56', N'Kẽm (ZinC)', 110, 1000, 1600, '2027-11-05', N'Kẽm Gluconat', N'Viên', 'K03', 'NCC04'),
('T57', N'Magie B6', 140, 2200, 3000, '2028-05-25', N'Magie, Vitamin B6', N'Viên', 'K03', 'NCC06');
GO


--------------------------------------------------
-- BỔ SUNG HÓA ĐƠN & CHI TIẾT HÓA ĐƠN
-- (ĐÃ SỬA LỖI: Thêm cột 'donGia' bị thiếu)
--------------------------------------------------

-- THÁNG 1/2025
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM) VALUES 
('HD002', '2025-01-10', 'TH01', 'NV002', 'KH0000004', NULL),
('HD003', '2025-01-15', 'TH02', 'NV002', 'KH0000005', NULL),
('HD004', '2025-01-25', 'TH01', 'NV003', 'KH0000010', NULL);
INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong, donGia) VALUES
('HD002', 'T01', 5, 1500), ('HD002', 'T14', 2, 4000),
('HD003', 'T03', 10, 2000), ('HD003', 'T05', 20, 1200),
('HD004', 'T15', 3, 10000), ('HD004', 'T16', 1, 3500);
GO

-- THÁNG 2/2025
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM) VALUES 
('HD005', '2025-02-05', 'TH01', 'NV002', 'KH0000011', NULL),
('HD006', '2025-02-18', 'TH02', 'NV002', NULL, NULL), -- Khách vãng lai
('HD007', '2025-02-28', 'TH01', 'NV002', 'KH0000020', NULL);
INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong, donGia) VALUES
('HD005', 'T19', 1, 32000),
('HD006', 'T02', 10, 2500), ('HD006', 'T23', 5, 4500), ('HD006', 'T24', 5, 3800),
('HD007', 'T22', 30, 5000), ('HD007', 'T07', 20, 1500);
GO

-- THÁNG 3/2025
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM) VALUES 
('HD008', '2025-03-10', 'TH02', 'NV002', 'KH0000025', NULL),
('HD009', '2025-03-20', 'TH01', 'NV003', 'KH0000030', NULL),
('HD010', '2025-03-30', 'TH01', 'NV002', 'KH0000035', NULL);
INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong, donGia) VALUES
('HD008', 'T18', 2, 2200), ('HD008', 'T20', 1, 55000),
('HD009', 'T35', 20, 1200), ('HD009', 'T36', 10, 1500),
('HD010', 'T40', 3, 6500), ('HD010', 'T41', 5, 1900), ('HD010', 'T42', 2, 2100);
GO

-- THÁNG 4/2025
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM) VALUES 
('HD011', '2025-04-05', 'TH02', 'NV002', 'KH0000040', NULL),
('HD012', '2025-04-15', 'TH01', 'NV002', 'KH0000045', NULL),
('HD013', '2025-04-29', 'TH02', 'NV002', 'KH0000001', 'KM005'); -- Dùng KM 30/4
INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong, donGia) VALUES
('HD011', 'T50', 10, 600), ('HD011', 'T51', 10, 1500), ('HD011', 'T52', 5, 2800),
('HD012', 'T55', 2, 1800), ('HD012', 'T56', 1, 1600), 
('HD013', 'T15', 4, 10000), ('HD013', 'T19', 1, 32000), ('HD013', 'T21', 1, 25000);
GO

-- THÁNG 5/2025
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM) VALUES 
('HD014', '2025-05-10', 'TH01', 'NV002', 'KH0000008', NULL),
('HD015', '2025-05-20', 'TH01', 'NV002', NULL, NULL),
('HD016', '2025-05-30', 'TH02', 'NV002', 'KH0000018', NULL);
INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong, donGia) VALUES
('HD014', 'T21', 15, 25000), ('HD014', 'T05', 20, 1200), -- ĐÃ SỬA: Gộp (5+10) cho T21
('HD015', 'T22', 4, 5000), -- ĐÃ SỬA: Gộp (2+2) cho T22
('HD016', 'T22', 75, 5000); -- ĐÃ SỬA: Gộp (30+30+15) cho T22
GO

-- THÁNG 6/2025
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM) VALUES 
('HD017', '2025-06-02', 'TH01', 'NV003', 'KH0000028', 'KM006'), -- Dùng KM 1/6
('HD018', '2025-06-15', 'TH02', 'NV002', 'KH0000038', NULL),
('HD019', '2025-06-25', 'TH01', 'NV002', 'KH0000048', NULL);
INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong, donGia) VALUES
('HD017', 'T44', 2, 26000), ('HD017', 'T45', 1, 65000), ('HD017', 'T16', 3, 3500),
('HD018', 'T23', 20, 4500), -- ĐÃ SỬA: Gộp (10+10) cho T23
('HD019', 'T46', 3, 2200); -- ĐÃ SỬA: Gộp (1+2) cho T46
GO

-- THÁNG 7/2025
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM) VALUES 
('HD020', '2025-07-05', 'TH02', 'NV002', 'KH0000002', 'KM007'), -- Dùng KM Hè
('HD021', '2025-07-15', 'TH01', 'NV002', 'KH0000012', NULL),
('HD022', '2025-07-25', 'TH01', 'NV002', 'KH0000022', NULL);
INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong, donGia) VALUES
('HD020', 'T19', 1, 32000), ('HD020', 'T21', 7, 25000), -- ĐÃ SỬA: Gộp (2+5) cho T21
('HD021', 'T01', 30, 1500), ('HD021', 'T02', 20, 2500),
('HD022', 'T03', 15, 2000), ('HD022', 'T04', 10, 8000);
GO

-- THÁNG 8/2025
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM) VALUES 
('HD023', '2025-08-01', 'TH01', 'NV003', 'KH0000032', NULL),
('HD024', '2025-08-10', 'TH02', 'NV002', 'KH0000042', NULL),
('HD025', '2025-08-20', 'TH01', 'NV002', 'KH0000050', NULL),
('HD026', '2025-08-28', 'TH02', 'NV002', 'KH0000003', 'KM008'); -- Dùng KM Back to school
INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong, donGia) VALUES
('HD023', 'T25', 10, 4200), ('HD023', 'T26', 10, 2800),
('HD024', 'T30', 20, 1500), ('HD024', 'T31', 10, 3000),
('HD025', 'T53', 2, 4000), ('HD025', 'T54', 1, 9000),
('HD026', 'T14', 5, 4000), ('HD026', 'T16', 5, 3500);
GO

-- THÁNG 9/2025
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM) VALUES 
('HD027', '2025-09-02', 'TH02', 'NV002', 'KH0000001', 'KM002'), -- Dùng KM 2/9 (Đã hết hạn, nhưng HĐ trong quá khứ)
('HD028', '2025-09-12', 'TH01', 'NV002', 'KH0000013', NULL),
('HD029', '2025-09-22', 'TH01', 'NV003', 'KH0000023', NULL),
('HD030', '2025-09-30', 'TH02', 'NV002', 'KH0000033', NULL);
INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong, donGia) VALUES
('HD027', 'T01', 10, 1500), ('HD027', 'T02', 10, 2500), ('HD027', 'T05', 30, 1200),
('HD028', 'T48', 20, 400), ('HD028', 'T49', 20, 500),
('HD029', 'T19', 3, 32000), -- ĐÃ SỬA: Gộp (1+2) cho T19
('HD030', 'T22', 60, 5000); -- ĐÃ SỬA: Gộp (30+30) cho T22
GO

-- THÁNG 10/2025
INSERT INTO HoaDon (maHD, ngayLap, maThue, maNV, maKH, maKM) VALUES 
('HD031', '2025-10-01', 'TH01', 'NV002', 'KH0000043', 'KM009'), -- Dùng KM tháng 10 (cũ)
('HD032', '2025-10-02', 'TH02', 'NV002', 'KH0000006', NULL),
('HD033', '2025-10-03', 'TH01', 'NV002', 'KH0000016', NULL),
('HD034', '2025-10-04', 'TH02', 'NV002', 'KH0000026', NULL),
('HD035', '2025-10-05', 'TH01', 'NV002', 'KH0000036', NULL),
('HD036', '2025-10-06', 'TH02', 'NV002', NULL, NULL),
('HD037', '2025-10-07', 'TH01', 'NV002', 'KH0000046', NULL),
('HD038', '2025-10-08', 'TH02', 'NV002', 'KH0000007', NULL),
('HD039', '2025-10-09', 'TH01', 'NV002', 'KH0000017', NULL),
('HD040', '2025-10-10', 'TH02', 'NV002', 'KH0000027', NULL),
('HD041', '2025-10-11', 'TH01', 'NV002', 'KH0000037', NULL),
('HD042', '2025-10-12', 'TH02', 'NV002', 'KH0000047', NULL),
('HD043', '2025-10-13', 'TH01', 'NV002', 'KH0000009', NULL),
('HD044', '2025-10-14', 'TH02', 'NV002', 'KH0000019', NULL),
('HD045', '2025-10-15', 'TH01', 'NV002', 'KH0000029', 'KM009'), -- Dùng KM tháng 10 (cũ)
('HD046', '2025-10-18', 'TH02', 'NV002', 'KH0000039', NULL),
('HD047', '2025-10-20', 'TH01', 'NV002', 'KH0000049', 'KM003'), -- Dùng KM cuối T10 (đang active)
('HD048', '2025-10-22', 'TH02', 'NV002', 'KH0000014', 'KM003'), -- Dùng KM cuối T10 (đang active)
('HD049', '2025-10-24', 'TH01', 'NV002', 'KH0000024', 'KM003'), -- Dùng KM cuối T10 (đang active)
('HD050', '2025-10-26', 'TH02', 'NV002', 'KH0000034', 'KM010'), -- Dùng KM đặc biệt (hết SL)
('HD051', '2025-10-27', 'TH01', 'NV002', 'KH0000044', NULL);
GO

-- CHI TIẾT HÓA ĐƠN THÁNG 10/2025
INSERT INTO ChiTietHoaDon (maHD, maThuoc, soLuong, donGia) VALUES
('HD031', 'T46', 3, 2200), -- ĐÃ SỬA: Gộp (1+2) cho T46
('HD032', 'T24', 15, 3800), -- ĐÃ SỬA: Gộp (10+5) cho T24
('HD033', 'T23', 20, 4500), 
('HD034', 'T22', 60, 5000), 
('HD035', 'T05', 20, 1200), ('HD035', 'T19', 1, 32000), -- ĐÃ SỬA: Gộp (10+10) cho T05
('HD036', 'T01', 50, 1500),
('HD037', 'T10', 2, 5500), ('HD037', 'T11', 4, 1500), ('HD037', 'T12', 3, 1300), 
('HD038', 'T46', 5, 2200), 
('HD039', 'T08', 1, 1800), 
('HD040', 'T09', 1, 800), 
('HD041', 'T21', 10, 25000), -- ĐÃ SỬA: Gộp (3+5+2) cho T21
('HD042', 'T32', 10, 2500), ('HD042', 'T33', 10, 4800), 
('HD043', 'T56', 2, 1600), ('HD043', 'T57', 3, 3000), 
('HD044', 'T22', 60, 5000), -- ĐÃ SỬA: Gộp (30+30) cho T22
('HD045', 'T23', 30, 4500), -- ĐÃ SỬA: Gộp (15+15) cho T23
('HD046', 'T05', 22, 1200), -- ĐÃ SỬA: Gộp (2+20) cho T05
('HD047', 'T01', 10, 1500), ('HD047', 'T02', 10, 2500), 
('HD048', 'T03', 20, 2000), 
('HD049', 'T04', 10, 8000), 
('HD050', 'T05', 50, 1200), 
('HD051', 'T06', 5, 20000), ('HD051', 'T07', 30, 1500);
GO

PRINT N'Bổ sung dữ liệu mẫu mở rộng thành công!';
GO
