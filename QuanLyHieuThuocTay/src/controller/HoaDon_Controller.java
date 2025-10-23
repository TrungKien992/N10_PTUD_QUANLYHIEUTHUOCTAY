package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel; // Import
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import dao.chiTietHoaDon_DAO;
import dao.hoaDon_DAO;
import dao.keThuoc_DAO;
import dao.khachHang_DAO;
import dao.nhanVien_DAO;
import dao.thue_DAO;
import dao.thuoc_DAO;
import dao.khuyenMai_DAO; // Import
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KeThuoc;
import entity.KhachHang;
import entity.KhuyenMai; // Import
import entity.NhanVien;
import entity.Thue;
import entity.Thuoc;
import gui.DsPhieuDatThuoc_GUI; // Import
import gui.ThemKH_GUI; // Import
import gui.TrangChu_GUI;
import gui.XemchitietHD_GUI;

public class HoaDon_Controller {
	private keThuoc_DAO keThuocDAO;
    private TrangChu_GUI view;
    private khachHang_DAO khachHangDAO;
    private thuoc_DAO thuocDAO;
    private hoaDon_DAO hoaDonDAO;
    private chiTietHoaDon_DAO chiTietHoaDonDAO;
    private nhanVien_DAO nhanVienDAO;
    private thue_DAO thueDAO;
    private khuyenMai_DAO khuyenMaiDAO; 

    private KhachHang currentKhachHang = null;
    private NhanVien currentNhanVien = null;
    private DecimalFormat df = new DecimalFormat("#,##0 VND");
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public HoaDon_Controller(TrangChu_GUI view) {
        this.view = view;
        keThuocDAO = new keThuoc_DAO();
        khachHangDAO = new khachHang_DAO();
        thuocDAO = new thuoc_DAO();
        hoaDonDAO = new hoaDon_DAO();
        chiTietHoaDonDAO = new chiTietHoaDon_DAO();
        nhanVienDAO = new nhanVien_DAO();
        thueDAO = new thue_DAO();
        khuyenMaiDAO = new khuyenMai_DAO(); 

        if (view.currentUser != null) {
            this.currentNhanVien = nhanVienDAO.getNhanVienByMaTK(view.currentUser.getMaTK());
        }
        if (this.currentNhanVien == null) {
             this.currentNhanVien = new NhanVien();
             this.currentNhanVien.setMaNV("NV000"); // Mã admin giả
             this.currentNhanVien.setTenNV(view.currentUserName != null ? view.currentUserName : "Admin");
        }

        loadKhuyenMaiToComboBox(); // (Req 7)
        clearHoaDonForm();
        loadThuocFilters_ThemHD();
        addThemHoaDonListeners();
        addTimKiemHoaDonListeners();
        loadTatCaHoaDon();
    }

 // (Thêm vào HoaDon_Controller.java)

    // (REQ 1) Thêm hàm load CBB
    private void loadThuocFilters_ThemHD() {
        // 1. Load Kệ Thuốc
        DefaultComboBoxModel<String> keModel = new DefaultComboBoxModel<>();
        keModel.addElement("Tất cả"); // Mục mặc định
        List<KeThuoc> dsKe = keThuocDAO.getAllKeThuoc();
        for(KeThuoc ke : dsKe) {
            keModel.addElement(ke.getLoaiKe());
        }
        view.cb_lockethuoc.setModel(keModel);

        // 2. Load Tên Thuốc
        DefaultComboBoxModel<String> tenModel = new DefaultComboBoxModel<>();
        tenModel.addElement("Tất cả");
        List<String> dsTen = thuocDAO.getAllTenThuoc();
        for(String ten : dsTen) {
            tenModel.addElement(ten);
        }
        view.cb_loctenthuoc.setModel(tenModel);
        
        // 3. Tải dữ liệu ban đầu cho bảng (lọc theo "Tất cả")
        filterTimKiemThuocTable();
    }
        
    // (REQ 1) Thêm hàm lọc
    private void filterTimKiemThuocTable() {
        String maThuoc = view.txt_Nhapmathuoc.getText().trim();
        
        String tenThuoc = (view.cb_loctenthuoc.getSelectedItem() == null || "Tất cả".equals(view.cb_loctenthuoc.getSelectedItem())) 
                            ? "" : view.cb_loctenthuoc.getSelectedItem().toString();
        
        String loaiKe = (view.cb_lockethuoc.getSelectedItem() == null || "Tất cả".equals(view.cb_lockethuoc.getSelectedItem())) 
                            ? "" : view.cb_lockethuoc.getSelectedItem().toString();

        // Gọi hàm search mới trong thuoc_DAO
        List<Thuoc> dsThuoc = thuocDAO.searchThuoc(maThuoc, tenThuoc, loaiKe);
        
        DefaultTableModel model = (DefaultTableModel) view.table_timkiemthuoc.getModel();
        model.setRowCount(0);
        if(dsThuoc == null) return;
        for (Thuoc t : dsThuoc) {
            model.addRow(new Object[]{
                t.getMaThuoc(),
                t.getKeThuoc() != null ? t.getKeThuoc().getLoaiKe() : "N/A",
                t.getTenThuoc(),
                df.format(t.getGiaBan()),
                t.getSoLuong()
            });
        }
    }

	// === Tải ComboBox Khuyến Mãi (REQ 7) ===
    public void loadKhuyenMaiToComboBox() {
        DefaultComboBoxModel<KhuyenMai> model = new DefaultComboBoxModel<>();
        
        KhuyenMai kmMacDinh = new KhuyenMai();
        kmMacDinh.setMaKM(null);
        kmMacDinh.setTenChuongTrinh("Không áp dụng");
        model.addElement(kmMacDinh);
        
        List<KhuyenMai> dsKM = khuyenMaiDAO.getAllKhuyenMai();
        for (KhuyenMai km : dsKM) {
            if (km.getTrangThai() == 1 && km.getNgayKetThuc().isAfter(LocalDate.now())) {
                model.addElement(km);
            }
        }
        view.cb_Chonkhuyenmai.setModel(model);
    }

    // =================================================================
    // == PHẦN 1: LOGIC CHO PANEL THÊM HÓA ĐƠN (pn_Themhoadon) ==
    // =================================================================

 // (Thay thế hàm này trong HoaDon_Controller.java)
    private void addThemHoaDonListeners() {
        // (REQ 1) Nút Làm Mới (Tìm Thuốc) - Sửa lại
        view.btn_lammoitkthuoc.addActionListener(e -> {
            view.txt_Nhapmathuoc.setText("");
            view.cb_lockethuoc.setSelectedIndex(0);
            view.cb_loctenthuoc.setSelectedIndex(0);
            filterTimKiemThuocTable(); // Gọi hàm lọc
        });

        // (REQ 1) Thêm Listener cho ComboBox
        ItemListener filterListener = e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                filterTimKiemThuocTable();
            }
        };
        view.cb_lockethuoc.addItemListener(filterListener);
        view.cb_loctenthuoc.addItemListener(filterListener);
        
        // (REQ 1) Thêm Listener cho Text Field Mã Thuốc
        view.txt_Nhapmathuoc.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { filterTimKiemThuocTable(); }
            @Override public void removeUpdate(DocumentEvent e) { filterTimKiemThuocTable(); }
            @Override public void changedUpdate(DocumentEvent e) { filterTimKiemThuocTable(); }
        });

        // --- Giữ nguyên các listener khác ---
        view.btn_addthuocvaohoadon.addActionListener(e -> addThuocToCart());
        view.btn_timsdtkh.addActionListener(e -> findKhachHangBySDT());
        view.text_Nhapsosdtkh.addActionListener(e -> findKhachHangBySDT());
        view.btn_suaslthuoc.addActionListener(e -> suaSoLuongCart());
        view.btn_xoathuockhoihd.addActionListener(e -> xoaThuocFromCart());
        view.btn_Huyhoadon.addActionListener(e -> clearHoaDonForm());
        view.btn_Thanhtoanhoadon.addActionListener(e -> thanhToanHoaDon());

        view.text_Nhaptiennhan.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { updateTienThua(); }
            @Override public void removeUpdate(DocumentEvent e) { updateTienThua(); }
            @Override public void changedUpdate(DocumentEvent e) { updateTienThua(); }
        });
        
        view.btn_Themkhachhangmoi.addActionListener(e -> {
            ThemKH_GUI themKhDialog = new ThemKH_GUI(view.QuanLyHieuThuocTay);
            new ThemKH_Controller(themKhDialog, view);
            themKhDialog.setVisible(true);
        });
        
        view.btn_Themthuocvaophieudat.addActionListener(e -> {
            DsPhieuDatThuoc_GUI dialog = new DsPhieuDatThuoc_GUI(view.QuanLyHieuThuocTay);
            dialog.setVisible(true);
        });
        
        // (REQ 2 - Cập nhật KM)
        view.cb_Chonkhuyenmai.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateTongTien(); // Gọi hàm tính lại tổng tiền
                }
            }
        });
    }

    private void loadDataTimKiemThuoc() {
        filterTimKiemThuocTable();
   }

    // === Sửa lỗi Null Row (REQ 3 - Lỗi cuối) ===
    private void addThuocToCart() {
        int selectedRow = view.table_timkiemthuoc.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Vui lòng chọn một loại thuốc từ bảng tìm kiếm!");
            return;
        }

        String maThuoc = view.table_timkiemthuoc.getValueAt(selectedRow, 0).toString();
        Thuoc thuoc = thuocDAO.getThuocTheoMa(maThuoc); 
        if (thuoc == null) return;

        int soLuongNhap;
        try {
            soLuongNhap = Integer.parseInt(view.text_Nhapsoluongthuoc.getText());
            if (soLuongNhap <= 0) {
                JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Số lượng phải là số dương!");
                return;
            }
            if (soLuongNhap > thuoc.getSoLuong()) {
                JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Số lượng tồn kho không đủ (Chỉ còn " + thuoc.getSoLuong() + ")!");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Vui lòng nhập số lượng hợp lệ!");
            return;
        }

        DefaultTableModel cartModel = (DefaultTableModel) view.table_hdtam.getModel();

        // 1. Kiểm tra thuốc đã có trong giỏ chưa (kiểm tra null an toàn)
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            Object maThuocTrongGio = cartModel.getValueAt(i, 0);
            
            if (maThuocTrongGio != null && maThuocTrongGio.equals(maThuoc)) { 
                int soLuongHienTai = (int) cartModel.getValueAt(i, 2);
                int soLuongMoi = soLuongHienTai + soLuongNhap;
                if (soLuongMoi > thuoc.getSoLuong()) {
                     JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Tổng số lượng vượt quá tồn kho (Chỉ còn " + thuoc.getSoLuong() + ")!");
                     return;
                }
                cartModel.setValueAt(soLuongMoi, i, 2); 
                cartModel.setValueAt(soLuongMoi * thuoc.getGiaBan(), i, 4); 
                updateTongTien();
                view.text_Nhapsoluongthuoc.setText(""); 
                return;
            }
        }

        // 2. Nếu chưa có, kiểm tra xem có dòng null placeholder không
        if (cartModel.getRowCount() == 1 && cartModel.getValueAt(0, 0) == null) {
            // Có dòng null, GHI ĐÈ dòng này
            cartModel.setValueAt(thuoc.getMaThuoc(), 0, 0);
            cartModel.setValueAt(thuoc.getTenThuoc(), 0, 1);
            cartModel.setValueAt(soLuongNhap, 0, 2);
            cartModel.setValueAt(thuoc.getGiaBan(), 0, 3);
            cartModel.setValueAt(soLuongNhap * thuoc.getGiaBan(), 0, 4);
        } else {
            // Không có dòng null, THÊM MỚI
            Object[] rowData = {
                thuoc.getMaThuoc(),
                thuoc.getTenThuoc(),
                soLuongNhap,
                thuoc.getGiaBan(),
                soLuongNhap * thuoc.getGiaBan()
            };
            cartModel.addRow(rowData);
        }
        updateTongTien();
        view.text_Nhapsoluongthuoc.setText("");
    }

    private void suaSoLuongCart() {
        // ... (Giữ nguyên code suaSoLuongCart) ...
         int selectedRow = view.table_hdtam.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Vui lòng chọn một thuốc trong hóa đơn để sửa!");
            return;
        }
        String maThuoc = view.table_hdtam.getValueAt(selectedRow, 0).toString();
        Thuoc thuoc = thuocDAO.getThuocTheoMa(maThuoc);
        
        String soLuongMoiStr = JOptionPane.showInputDialog(view.QuanLyHieuThuocTay, "Nhập số lượng mới:", view.table_hdtam.getValueAt(selectedRow, 2));
        if (soLuongMoiStr == null) return;

        try {
            int soLuongMoi = Integer.parseInt(soLuongMoiStr);
            if (soLuongMoi <= 0) {
                 JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Số lượng phải là số dương!");
                 return;
            }
            if (soLuongMoi > thuoc.getSoLuong()) {
                JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Số lượng tồn kho không đủ (Chỉ còn " + thuoc.getSoLuong() + ")!");
                return;
            }
            DefaultTableModel model = (DefaultTableModel) view.table_hdtam.getModel();
            double giaBan = (double) model.getValueAt(selectedRow, 3);
            model.setValueAt(soLuongMoi, selectedRow, 2);
            model.setValueAt(soLuongMoi * giaBan, selectedRow, 4);
            updateTongTien();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Số lượng nhập không hợp lệ!");
        }
    }

    private void xoaThuocFromCart() {
        // ... (Giữ nguyên code xoaThuocFromCart) ...
        int selectedRow = view.table_hdtam.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Vui lòng chọn một thuốc trong hóa đơn để xóa!");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) view.table_hdtam.getModel();
        model.removeRow(selectedRow);
        
        // Nếu xóa hết, thêm lại dòng null
        if (model.getRowCount() == 0) {
             model.addRow(new Object[]{null, null, null, null, null});
        }
        updateTongTien();
    }

    // === Sửa findKhachHangBySDT (REQ 2) ===
    private void findKhachHangBySDT() {
        String sdt = view.text_Nhapsosdtkh.getText().trim();
        if (sdt.isEmpty()) {
            view.lbl_Hientenkh.setText("Khách vãng lai");
            this.currentKhachHang = null;
            return;
        }
        KhachHang kh = khachHangDAO.getKhachHangTheoSDT(sdt);
        if (kh != null) {
            view.lbl_Hientenkh.setText(kh.getTenKH());
            this.currentKhachHang = kh;
        } else {
            // (Req 2): Hiển thị tên khách hàng CÓ SỐ PHÙ HỢP
            // Sửa: Tìm kiếm theo LIKE
            List<KhachHang> dsKH = khachHangDAO.searchKhachHang("", "", sdt, "");
            if (dsKH.size() == 1) {
                // Nếu chỉ có 1 kết quả, chọn luôn
                kh = dsKH.get(0);
                view.lbl_Hientenkh.setText(kh.getTenKH());
                view.text_Nhapsosdtkh.setText(kh.getSoDienThoai()); // Cập nhật SĐT đúng
                this.currentKhachHang = kh;
            } else if (dsKH.size() > 1) {
                // Nếu có nhiều, có thể hiện JDialog chọn
                JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Tìm thấy nhiều SĐT khớp, vui lòng nhập chính xác hơn.");
                view.lbl_Hientenkh.setText("Khách vãng lai");
                this.currentKhachHang = null;
            } else {
                JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Không tìm thấy khách hàng! Có thể thêm khách hàng mới.");
                view.lbl_Hientenkh.setText("Khách vãng lai");
                this.currentKhachHang = null;
            }
        }
    }

    // === Sửa updateTongTien (Xử lý tiền tệ) ===
    private void updateTongTien() {
        DefaultTableModel model = (DefaultTableModel) view.table_hdtam.getModel();
        double tongTienHang = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            if(model.getValueAt(i, 4) != null) { // Kiểm tra null
                 tongTienHang += (double) model.getValueAt(i, 4);
            }
        }
        
        // Giả sử lấy thuế 10%
        Thue thueObj = thueDAO.getThueByMa("TH02");
        double tiLeThue = (thueObj != null) ? thueObj.getTiLe() : 0.10; // Mặc định 10%
        
        // Lấy khuyến mãi
        KhuyenMai selectedKM = (KhuyenMai) view.cb_Chonkhuyenmai.getSelectedItem();
        double tiLeKM = 0.0;
        if(selectedKM != null && selectedKM.getMaKM() != null) {
            tiLeKM = selectedKM.getGiaTri() / 100.0;
        }

        double tienKM = tongTienHang * tiLeKM;
        double tongSauKM = tongTienHang - tienKM;
        double thue = tongSauKM * tiLeThue;
        double tongCong = tongSauKM + thue;

        view.lbl_Hientienhang.setText(df.format(tongTienHang));
        view.lbl_Hienthue.setText(df.format(thue)); // Hiển thị tiền thuế
        view.lbl_Hientongcong.setText(df.format(tongCong));
        
        updateTienThua();
    }

    private void updateTienThua() {
         try {
            // Lấy text từ JFormattedTextField hoặc JLabel, loại bỏ " VND" và dấu chấm
            String tongCongStr = view.lbl_Hientongcong.getText().replaceAll("[^\\d]", "");
            if (tongCongStr.isEmpty()) tongCongStr = "0";
            
            String tienNhanStr = view.text_Nhaptiennhan.getText().replaceAll("[^\\d]", "");
            if (tienNhanStr.isEmpty()) tienNhanStr = "0";

            double tongCong = Double.parseDouble(tongCongStr);
            double tienNhan = Double.parseDouble(tienNhanStr);
            
            if (tienNhan >= tongCong) {
                double tienThua = tienNhan - tongCong;
                view.lbl_Hientienthua.setText(df.format(tienThua));
            } else {
                view.lbl_Hientienthua.setText("Chưa đủ tiền!");
            }
        } catch (Exception e) {
            view.lbl_Hientienthua.setText("0 VND");
        }
    }
    
    private void clearHoaDonForm() {
        view.lbl_hienmahd.setText(hoaDonDAO.generateNewMaHD());
        view.lbl_hienngaylaphoadon.setText(sdf.format(new Date()));
        if(currentNhanVien != null) {
             view.lbl_Hientennv.setText(currentNhanVien.getTenNV());
        }
        view.text_Nhapsosdtkh.setText("");
        view.lbl_Hientenkh.setText("Khách vãng lai");
        this.currentKhachHang = null;
        DefaultTableModel model = (DefaultTableModel) view.table_hdtam.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{null, null, null, null, null}); 
        view.lbl_Hientienhang.setText("0 VND");
        view.lbl_Hienthue.setText("0 VND");
        view.lbl_Hientongcong.setText("0 VND");
        view.text_Nhaptiennhan.setText("");
        view.lbl_Hientienthua.setText("0 VND");
        view.cb_Chonkhuyenmai.setSelectedIndex(0); // Về "Không áp dụng"
    }
    
    // === Sửa thanhToanHoaDon (Req 5) ===
    private void thanhToanHoaDon() {
        DefaultTableModel model = (DefaultTableModel) view.table_hdtam.getModel();
        if (model.getRowCount() == 0 || model.getValueAt(0, 0) == null) {
             JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Hóa đơn đang trống, không thể thanh toán!", "Lỗi", JOptionPane.WARNING_MESSAGE);
             return;
        }

        String maHD = view.lbl_hienmahd.getText();
        LocalDate ngayLap = LocalDate.now();
        KhuyenMai selectedKM = (KhuyenMai) view.cb_Chonkhuyenmai.getSelectedItem();
        Thue thue = thueDAO.getThueByMa("TH02"); // Giả sử VAT 10%

        double tongCong, tienNhan;
        try {
            String tongCongStr = view.lbl_Hientongcong.getText().replaceAll("[^\\d]", "");
            tongCong = Double.parseDouble(tongCongStr);
            String tienNhanStr = view.text_Nhaptiennhan.getText().replaceAll("[^\\d]", "");
            tienNhan = Double.parseDouble(tienNhanStr);
            
            if (tienNhan < tongCong) {
                 JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Chưa nhận đủ tiền!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                 return;
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Số tiền nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
             return;
        }
        
        HoaDon hd = new HoaDon(maHD, ngayLap, this.currentNhanVien, this.currentKhachHang, thue);
        if(selectedKM != null && selectedKM.getMaKM() != null) {
            hd.setKhuyenMai(selectedKM);
        }

        List<ChiTietHoaDon> dsCTHD = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String maThuoc = model.getValueAt(i, 0).toString();
            int soLuong = (int) model.getValueAt(i, 2);
            Thuoc thuoc = thuocDAO.getThuocTheoMa(maThuoc);
            ChiTietHoaDon ct = new ChiTietHoaDon(hd, thuoc, soLuong);
            dsCTHD.add(ct);
        }

        try {
            if (!hoaDonDAO.themHoaDon(hd)) {
                throw new Exception("Lỗi khi lưu hóa đơn chính!");
            }
            
            for (ChiTietHoaDon ct : dsCTHD) {
                if (!chiTietHoaDonDAO.themChiTietHoaDon(ct)) {
                     throw new Exception("Lỗi khi lưu chi tiết thuốc " + ct.getThuoc().getMaThuoc());
                }
                if (!thuocDAO.updateSoLuongSauKhiBan(ct.getThuoc().getMaThuoc(), ct.getSoLuong())) {
                    throw new Exception("Lỗi khi cập nhật tồn kho cho " + ct.getThuoc().getMaThuoc());
                }
            }

            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Thanh toán thành công! Mã Hóa Đơn: " + maHD);
            
            clearHoaDonForm();
            loadDataTimKiemThuoc();
            loadTatCaHoaDon(); // (REQ 5) - Cập nhật bảng tìm kiếm

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Thanh toán thất bại: \n" + e.getMessage(), "Lỗi Database", JOptionPane.ERROR_MESSAGE);
        }
    }

    // =================================================================
    // == PHẦN 2: LOGIC CHO PANEL TÌM KIẾM HÓA ĐƠN (pn_tkhd) ==
    // =================================================================

    // === Sửa addTimKiemHoaDonListeners (REQ 6) ===
    private void addTimKiemHoaDonListeners() {
        view.btn_tkhd_lammoi.addActionListener(e -> clearHoaDonFilter());
        view.btn_tkhd_xemchitiet.addActionListener(e -> xemChiTietHoaDonDaTim()); // (REQ 6)

        DocumentListener filterListener = new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { timKiemHoaDon(); }
            @Override public void removeUpdate(DocumentEvent e) { timKiemHoaDon(); }
            @Override public void changedUpdate(DocumentEvent e) { timKiemHoaDon(); }
        };
        view.text_tkhd_Mahd.getDocument().addDocumentListener(filterListener);
        view.text_tkhd_TenKH.getDocument().addDocumentListener(filterListener);
        view.text_tkhd_sdtkh.getDocument().addDocumentListener(filterListener);
        view.text_tkhd_tennv.getDocument().addDocumentListener(filterListener);
        view.text_tkhd_sdtnv.getDocument().addDocumentListener(filterListener);
        view.date_tkhd_ngaylaphd.getDateEditor().addPropertyChangeListener("date", e -> timKiemHoaDon());
    }

    private void clearHoaDonFilter() {
        view.text_tkhd_Mahd.setText("");
        view.text_tkhd_TenKH.setText("");
        view.text_tkhd_sdtkh.setText("");
        view.text_tkhd_tennv.setText("");
        view.text_tkhd_sdtnv.setText("");
        view.date_tkhd_ngaylaphd.setDate(null);
        loadTatCaHoaDon();
    }

    // === Sửa loadTatCaHoaDon (Định dạng tiền) ===
    private void loadTatCaHoaDon() {
        DefaultTableModel model = (DefaultTableModel) view.table_tkhd.getModel();
        model.setRowCount(0);
        List<Object[]> dsHD = hoaDonDAO.getAllHoaDonChiTietForTable(); 
        if(dsHD == null) return;
        for (Object[] row : dsHD) {
             row[7] = df.format(row[7]); // Định dạng tiền
             model.addRow(row);
        }
    }

    // === Sửa timKiemHoaDon (Định dạng tiền) ===
    private void timKiemHoaDon() {
        String maHD = view.text_tkhd_Mahd.getText().trim();
        String tenKH = view.text_tkhd_TenKH.getText().trim();
        String sdtKH = view.text_tkhd_sdtkh.getText().trim();
        String tenNV = view.text_tkhd_tennv.getText().trim();
        String sdtNV = view.text_tkhd_sdtnv.getText().trim();
        Date ngayLap = view.date_tkhd_ngaylaphd.getDate();
        LocalDate ngayLapLocal = (ngayLap != null) ? ngayLap.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;

        DefaultTableModel model = (DefaultTableModel) view.table_tkhd.getModel();
        model.setRowCount(0);
        List<Object[]> dsHD = hoaDonDAO.searchHoaDonChiTiet(maHD, tenKH, sdtKH, tenNV, sdtNV, ngayLapLocal); 
        if(dsHD == null) return;
        for (Object[] row : dsHD) {
             row[7] = df.format(row[7]); // Định dạng tiền
            model.addRow(row);
        }
    }

    // === Hàm xem chi tiết HĐ (REQ 6) ===
    private void xemChiTietHoaDonDaTim() {
        int selectedRow = view.table_tkhd.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Vui lòng chọn một hóa đơn từ bảng để xem chi tiết!");
            return;
        }
        String maHD = view.table_tkhd.getValueAt(selectedRow, 0).toString();
        
        XemchitietHD_GUI dialog = new XemchitietHD_GUI(view.QuanLyHieuThuocTay);
        dialog.loadData(maHD); // Gọi hàm loadData đã tạo trong XemchitietHD_GUI
        dialog.setVisible(true);
    }
}