package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException; // THÊM MỚI
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel; 
import javax.swing.JButton; // THÊM MỚI
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.io.File; // THÊM MỚI
import java.io.IOException;
import java.nio.file.Files; // THÊM MỚI
import java.nio.file.Paths; // THÊM MỚI

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dao.chiTietHoaDon_DAO;
import dao.hoaDon_DAO;
import dao.keThuoc_DAO;
import dao.khachHang_DAO;
import dao.nhanVien_DAO;
import dao.thue_DAO;
import dao.thuoc_DAO;
import dao.khuyenMai_DAO; 
// THÊM MỚI 2 DAO
import dao.PhieuChoThanhToan_DAO;
import dao.ChiTietPhieuCho_DAO;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KeThuoc;
import entity.KhachHang;
import entity.KhuyenMai; 
import entity.NhanVien;
import entity.Thue;
import entity.Thuoc;
// THÊM MỚI 2 ENTITY
import entity.PhieuChoThanhToan;
import entity.ChiTietPhieuCho;
import gui.ChiTietThuoc_GUI;
import gui.DsPhieuDatThuoc_GUI; 
import gui.ThemKH_GUI; 
import gui.TrangChu_GUI;
import gui.XemchitietHD_GUI;
import utils.PdfExporter;

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
    
    // THÊM MỚI 2 DAO
    private PhieuChoThanhToan_DAO phieuChoDAO;
    private ChiTietPhieuCho_DAO ctPhieuChoDAO;


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
        
        // THÊM MỚI
        phieuChoDAO = new PhieuChoThanhToan_DAO();
        ctPhieuChoDAO = new ChiTietPhieuCho_DAO();


        if (view.currentUser != null) {
            this.currentNhanVien = nhanVienDAO.getNhanVienByMaTK(view.currentUser.getMaTK());
        }
        if (this.currentNhanVien == null) {
             this.currentNhanVien = new NhanVien();
             this.currentNhanVien.setMaNV("NV000"); // Mã admin giả
             this.currentNhanVien.setTenNV(view.currentUserName != null ? view.currentUserName : "Admin");
        }

        loadKhuyenMaiToComboBox(); 
        clearHoaDonForm();
        loadThuocFilters_ThemHD();
        addThemHoaDonListeners();
        addTimKiemHoaDonListeners();
        loadTatCaHoaDon();
        loadCustomerPhonesToComboBox();
    }

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
        
    private void filterTimKiemThuocTable() {
        String maThuoc = view.txt_Nhapmathuoc.getText().trim();
        
        String tenThuoc = (view.cb_loctenthuoc.getSelectedItem() == null || "Tất cả".equals(view.cb_loctenthuoc.getSelectedItem())) 
                            ? "" : view.cb_loctenthuoc.getSelectedItem().toString();
        
        String loaiKe = (view.cb_lockethuoc.getSelectedItem() == null || "Tất cả".equals(view.cb_lockethuoc.getSelectedItem())) 
                            ? "" : view.cb_lockethuoc.getSelectedItem().toString();

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

    // (CHỈNH SỬA hàm này)
    private void addThemHoaDonListeners() {
        // (REQ 1) Nút Làm Mới (Tìm Thuốc)
        view.btn_lammoitkthuoc.addActionListener(e -> {
            view.txt_Nhapmathuoc.setText("");
            view.cb_lockethuoc.setSelectedIndex(0);
            view.cb_loctenthuoc.setSelectedIndex(0);
            filterTimKiemThuocTable(); // Gọi hàm lọc
        });

        // (REQ 1) Listener cho ComboBox
        ItemListener filterListener = e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                filterTimKiemThuocTable();
            }
        };
        view.cb_lockethuoc.addItemListener(filterListener);
        view.cb_loctenthuoc.addItemListener(filterListener);
        
        // (REQ 1) Listener cho Text Field Mã Thuốc
        view.txt_Nhapmathuoc.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { filterTimKiemThuocTable(); }
            @Override public void removeUpdate(DocumentEvent e) { filterTimKiemThuocTable(); }
            @Override public void changedUpdate(DocumentEvent e) { filterTimKiemThuocTable(); }
        });

        // --- Giữ nguyên các listener khác ---
        view.btn_addthuocvaohoadon.addActionListener(e -> addThuocToCart());
        view.cb_Nhapsosdtkh.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Chỉ gọi tìm kiếm khi một item được CHỌN (SELECTED) từ danh sách gợi ý
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Kiểm tra xem item được chọn có phải là String không (tránh lỗi)
                    if (e.getItem() instanceof String) {
                        // Gọi hàm tìm kiếm để cập nhật tên khách hàng
                        findKhachHangBySDT();
                    }
                }
            }
        });
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
        
        // --- CHỈNH SỬA LISTENER CHO NÚT "PHIẾU ĐẶT THUỐC" ---
        view.btn_xemphieudatthuoc.addActionListener(e -> { 
            moDialogDanhSachCho(); // Gọi hàm mới
        });
        
        // --- THÊM MỚI LISTENER CHO NÚT "THÊM VÀO PHIẾU ĐẶT THUỐC" ---
        view.btn_Themthuocvaophieudat.addActionListener(e -> {
            luuPhieuChoThanhToan(); // Gọi hàm mới
        });
        
        view.cb_Chonkhuyenmai.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateTongTien(); // Gọi hàm tính lại tổng tiền
                }
            }
        });
     // === BẮT ĐẦU CODE MỚI CHO NÚT XEM CHI TIẾT THUỐC ===
        view.btn_Xemchitiet.addActionListener(e -> {
            int selectedRow = view.table_timkiemthuoc.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay,
                        "Vui lòng chọn một thuốc từ bảng tìm kiếm để xem chi tiết!",
                        "Chưa chọn thuốc", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String maThuoc = view.table_timkiemthuoc.getValueAt(selectedRow, 0).toString();
            
            // Dùng thuocDAO đã có để lấy thông tin thuốc
            Thuoc thuoc = thuocDAO.getThuocTheoMa(maThuoc); 

            if (thuoc == null) {
                JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay,
                        "Không tìm thấy thông tin chi tiết cho mã thuốc: " + maThuoc,
                        "Lỗi dữ liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tạo và hiển thị Dialog chi tiết
            ChiTietThuoc_GUI chiTietDialog = new ChiTietThuoc_GUI(view.QuanLyHieuThuocTay); // Truyền JFrame cha
            chiTietDialog.loadThuoc(thuoc); // Gọi hàm load dữ liệu
            chiTietDialog.setVisible(true); // Hiển thị dialog
        });
        // === KẾT THÚC CODE MỚI CHO NÚT XEM CHI TIẾT THUỐC ===
    }

    // (CHỈNH SỬA hàm này)
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

        // 1. Kiểm tra thuốc đã có trong giỏ chưa
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

        // 2. Nếu chưa có, THÊM MỚI (Đã bỏ dòng null placeholder)
        Object[] rowData = {
            thuoc.getMaThuoc(),
            thuoc.getTenThuoc(),
            soLuongNhap,
            thuoc.getGiaBan(),
            soLuongNhap * thuoc.getGiaBan()
        };
        cartModel.addRow(rowData);
        updateTongTien();
        view.text_Nhapsoluongthuoc.setText("");
    }

    private void suaSoLuongCart() {
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

    // (CHỈNH SỬA hàm này)
    private void xoaThuocFromCart() {
        int selectedRow = view.table_hdtam.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Vui lòng chọn một thuốc trong hóa đơn để xóa!");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) view.table_hdtam.getModel();
        model.removeRow(selectedRow);
        
        // Đã bỏ phần thêm lại dòng null
        updateTongTien();
    }

    private void findKhachHangBySDT() {
        Component editorComponent = view.cb_Nhapsosdtkh.getEditor().getEditorComponent(); 

        String sdt = "";
        if (editorComponent instanceof JTextField) {
            sdt = ((JTextField) editorComponent).getText().trim();
        } else {
            Object selected = view.cb_Nhapsosdtkh.getSelectedItem(); 
            if (selected != null) {
                sdt = selected.toString().trim();
            }
        }
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
            List<KhachHang> dsKH = khachHangDAO.searchKhachHang("", "", sdt, ""); 
            if (dsKH.size() == 1) { 
                kh = dsKH.get(0);
                view.lbl_Hientenkh.setText(kh.getTenKH());
                 if (editorComponent instanceof JTextField) {
                     ((JTextField) editorComponent).setText(kh.getSoDienThoai());
                 } else {
                     view.cb_Nhapsosdtkh.setSelectedItem(kh.getSoDienThoai()); 
                 }
                this.currentKhachHang = kh;
            } else if (dsKH.size() > 1) { 
               
                view.lbl_Hientenkh.setText("Khách vãng lai");
                this.currentKhachHang = null;
            } else { 
                view.lbl_Hientenkh.setText("Khách vãng lai"); 
                this.currentKhachHang = null;
            }
        }
    }
    
    // THÊM MỚI: Hàm tiện ích để parse tiền từ String (VD: "100,000 VND" -> 100000.0)
    private double parseCurrency(String amountString) {
        try {
            // Loại bỏ chữ " VND" và dấu phẩy, dấu chấm
            String cleanString = amountString.replace("VND", "").replace(",", "").replace(".", "").trim();
            if (cleanString.isEmpty()) {
                return 0.0;
            }
            return Double.parseDouble(cleanString);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi parse tiền tệ: " + amountString);
            return 0.0;
        }
    }


    private void updateTongTien() {
        DefaultTableModel model = (DefaultTableModel) view.table_hdtam.getModel();
        double tongTienHang = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            if(model.getValueAt(i, 4) != null) { 
                 tongTienHang += (double) model.getValueAt(i, 4);
            }
        }
        
        Thue thueObj = thueDAO.getThueByMa("TH02");
        double tiLeThue = (thueObj != null) ? thueObj.getTiLe() : 0.10;
        
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
        view.lbl_Hienthue.setText(df.format(thue)); 
        view.lbl_Hientongcong.setText(df.format(tongCong));
        
        updateTienThua();
    }

    private void updateTienThua() {
         try {
            // CHỈNH SỬA: Dùng hàm parseCurrency
            double tongCong = parseCurrency(view.lbl_Hientongcong.getText());
            
            String tienNhanStr = view.text_Nhaptiennhan.getText().replaceAll("[^\\d]", "");
            if (tienNhanStr.isEmpty()) tienNhanStr = "0";
            
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
    
    // (CHỈNH SỬA hàm này)
    private void clearHoaDonForm() {
        view.lbl_hienmahd.setText(hoaDonDAO.generateNewMaHD());
        view.lbl_hienngaylaphoadon.setText(sdf.format(new Date()));
        if(currentNhanVien != null) {
             view.lbl_Hientennv.setText(currentNhanVien.getTenNV());
        }
        Component editorComponent = view.cb_Nhapsosdtkh.getEditor().getEditorComponent(); // Thay cb_Nhapsosdtkh
        if (editorComponent instanceof JTextField) {
            ((JTextField) editorComponent).setText("");
        } else {
            view.cb_Nhapsosdtkh.setSelectedItem(null); // Thay cb_Nhapsosdtkh
        }
        view.lbl_Hientenkh.setText("Khách vãng lai");
        this.currentKhachHang = null;
        DefaultTableModel model = (DefaultTableModel) view.table_hdtam.getModel();
        model.setRowCount(0); // Xóa sạch bảng
        // Đã bỏ dòng add new Object[]
        
        view.lbl_Hientienhang.setText("0 VND");
        view.lbl_Hienthue.setText("0 VND");
        view.lbl_Hientongcong.setText("0 VND");
        view.text_Nhaptiennhan.setText("");
        view.lbl_Hientienthua.setText("0 VND");
        view.cb_Chonkhuyenmai.setSelectedIndex(0); 
    }
    
    private void thanhToanHoaDon() {
        DefaultTableModel model = (DefaultTableModel) view.table_hdtam.getModel();
        if (model.getRowCount() == 0) {
             JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Hóa đơn đang trống, không thể thanh toán!", "Lỗi", JOptionPane.WARNING_MESSAGE);
             return;
        }

        String maHD = view.lbl_hienmahd.getText();
        LocalDate ngayLap = LocalDate.now();
        KhuyenMai selectedKM = (KhuyenMai) view.cb_Chonkhuyenmai.getSelectedItem();
        Thue thue = thueDAO.getThueByMa("TH02"); // Mặc định thuế 10%

        double tongCong, tienNhan, tienThua; // Thêm biến tiền thừa
        try {
            tongCong = parseCurrency(view.lbl_Hientongcong.getText());

            String tienNhanStr = view.text_Nhaptiennhan.getText().replaceAll("[^\\d]", "");
             // Kiểm tra tiền nhận có rỗng không
            if (tienNhanStr.isEmpty()) {
                 JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Vui lòng nhập số tiền khách đưa!", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                 view.text_Nhaptiennhan.requestFocus(); // Focus vào ô nhập tiền
                 return;
            }
            tienNhan = Double.parseDouble(tienNhanStr);

            if (tienNhan < tongCong) {
                 JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Chưa nhận đủ tiền!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                 return;
            }
            // Tính tiền thừa
            tienThua = tienNhan - tongCong;

        } catch (Exception e) {
             JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Số tiền nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
             return;
        }

        // Tạo đối tượng HoaDon
        HoaDon hd = new HoaDon(maHD, ngayLap, this.currentNhanVien, this.currentKhachHang, thue);
        if(selectedKM != null && selectedKM.getMaKM() != null) {
            hd.setKhuyenMai(selectedKM);
        }
        // === THÊM MỚI: Set tiền khách đưa và tiền thừa (Yêu cầu 1) ===
        hd.setTienKhachDua(tienNhan);
        hd.setTienThua(tienThua);
        // ==========================================================

        // Tạo danh sách ChiTietHoaDon
        ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<>(); // Dùng ArrayList để xuất PDF
        for (int i = 0; i < model.getRowCount(); i++) {
            String maThuoc = model.getValueAt(i, 0).toString();
            int soLuong = (int) model.getValueAt(i, 2);
            Thuoc thuoc = thuocDAO.getThuocTheoMa(maThuoc);
            // Quan trọng: Lấy lại thông tin Thuốc đầy đủ để PDF có tên thuốc
            if (thuoc == null) {
                 JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Lỗi: Không tìm thấy thông tin thuốc " + maThuoc, "Lỗi dữ liệu", JOptionPane.ERROR_MESSAGE);
                 return; // Dừng nếu có lỗi
            }
            ChiTietHoaDon ct = new ChiTietHoaDon(hd, thuoc, soLuong);
            dsCTHD.add(ct);
        }
        // Gán dsCTHD vào hóa đơn chính (để tiện cho PDF)
        hd.setDanhSachChiTiet(dsCTHD); // Có thể dùng List thay ArrayList nếu cần

        try {
            // Lưu hóa đơn chính (đã bao gồm tiền khách đưa, tiền thừa)
            if (!hoaDonDAO.themHoaDon(hd)) {
                throw new Exception("Lỗi khi lưu hóa đơn chính!");
            }

            // Lưu các chi tiết và cập nhật tồn kho
            for (ChiTietHoaDon ct : dsCTHD) {
                if (!chiTietHoaDonDAO.themChiTietHoaDon(ct)) {
                     throw new Exception("Lỗi khi lưu chi tiết thuốc " + ct.getThuoc().getMaThuoc());
                }
                if (!thuocDAO.updateSoLuongSauKhiBan(ct.getThuoc().getMaThuoc(), ct.getSoLuong())) {
                    throw new Exception("Lỗi khi cập nhật tồn kho cho " + ct.getThuoc().getMaThuoc());
                }
            }

            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Thanh toán thành công! Mã Hóa Đơn: " + maHD);

            // === THÊM MỚI: Kiểm tra checkbox và xuất PDF (Yêu cầu 3) ===
            if (view.chk_XuatHoaDon.isSelected()) {
                // Tạo thư mục nếu chưa tồn tại
                try {
                    Files.createDirectories(Paths.get("hoa_don_pdf")); 
                } catch (IOException ioException) {
                    System.err.println("Không thể tạo thư mục lưu PDF: " + ioException.getMessage());
                }

                String filePath = "hoa_don_pdf/" + hd.getMaHD() + ".pdf"; 
                PdfExporter.exportHoaDonToPdf(hd, dsCTHD, filePath);
            }

            clearHoaDonForm();
            filterTimKiemThuocTable(); // Cập nhật bảng tìm thuốc (số lượng tồn)
            loadTatCaHoaDon(); // Cập nhật bảng tìm hóa đơn

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Thanh toán thất bại: \n" + e.getMessage(), "Lỗi Database", JOptionPane.ERROR_MESSAGE);
            // Cân nhắc rollback transaction nếu có lỗi xảy ra ở đây
        }
    }
    
    // =================================================================
    // == PHẦN 1.5: LOGIC CHO PHIẾU CHỜ THANH TOÁN (THÊM MỚI) ==
    // =================================================================
	/**
     * THÊM MỚI: Lấy thông tin từ form, lưu vào 2 bảng PhieuChoThanhToan và ChiTietPhieuCho
     */
    private void luuPhieuChoThanhToan() {
        // 1. Kiểm tra điều kiện
        DefaultTableModel model = (DefaultTableModel) view.table_hdtam.getModel();
        if (model.getRowCount() == 0) {
             JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Hóa đơn đang trống, không thể lưu phiếu!", "Lỗi", JOptionPane.WARNING_MESSAGE);
             return;
        }
     // Lấy editor component của JComboBox
        Component editorComponent_luu = view.cb_Nhapsosdtkh.getEditor().getEditorComponent(); // Thay cb_Nhapsosdtkh
        String sdt = "";
        if (editorComponent_luu instanceof JTextField) {
            sdt = ((JTextField) editorComponent_luu).getText().trim();
        } else {
            Object selected = view.cb_Nhapsosdtkh.getSelectedItem(); // Thay cb_Nhapsosdtkh
            if (selected != null) {
                sdt = selected.toString().trim();
            }
        }
        // ... (phần còn lại của hàm)
        
        // 2. Lấy thông tin từ Form
        String maPhieuCho = phieuChoDAO.generateNewMaPhieuCho();
        String tenKH = view.lbl_Hientenkh.getText();
        LocalDate ngayLap = LocalDate.now();
        double tongTienHang = parseCurrency(view.lbl_Hientienhang.getText());
        double thueVAT = parseCurrency(view.lbl_Hienthue.getText());
        double tongCong = parseCurrency(view.lbl_Hientongcong.getText());
        
        // 3. Tạo đối tượng PhieuChoThanhToan
        PhieuChoThanhToan phieuCho = new PhieuChoThanhToan(
            maPhieuCho,
            this.currentKhachHang, // Có thể null nếu là khách vãng lai
            tenKH,
            sdt,
            this.currentNhanVien,
            ngayLap,
            tongTienHang,
            thueVAT,
            tongCong
        );

        // 4. Tạo danh sách ChiTietPhieuCho
        List<ChiTietPhieuCho> dsChiTiet = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String maThuoc = model.getValueAt(i, 0).toString();
            int soLuong = (int) model.getValueAt(i, 2);
            double donGia = (double) model.getValueAt(i, 3);
            
            Thuoc thuoc = new Thuoc(maThuoc); // Chỉ cần mã thuốc
            ChiTietPhieuCho ctpc = new ChiTietPhieuCho(phieuCho, thuoc, soLuong, donGia);
            dsChiTiet.add(ctpc);
        }

        // 5. Lưu vào Database (Transaction)
        try {
            // Thêm phiếu chờ cha
            if (!phieuChoDAO.themPhieuCho(phieuCho)) {
                throw new Exception("Lỗi khi lưu thông tin phiếu chờ!");
            }
            
            // Thêm các chi tiết
            for (ChiTietPhieuCho ct : dsChiTiet) {
                if (!ctPhieuChoDAO.themChiTietPhieuCho(ct)) {
                    throw new Exception("Lỗi khi lưu chi tiết thuốc " + ct.getThuoc().getMaThuoc());
                }
            }

            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Đã thêm vào danh sách chờ thành công!\nMã phiếu chờ: " + maPhieuCho);
            
            // 6. Xóa trắng form
            clearHoaDonForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Lưu phiếu chờ thất bại: \n" + e.getMessage(), "Lỗi Database", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * THÊM MỚI: Mở Dialog DsPhieuDatThuoc_GUI và gán sự kiện cho nó
     */
    private void moDialogDanhSachCho() {
        DsPhieuDatThuoc_GUI dialog = new DsPhieuDatThuoc_GUI(view.QuanLyHieuThuocTay);
        
        // Lấy component qua getter (An toàn hơn)
        JTable table = dialog.getTable();
        JButton btnThanhToan = dialog.getBtnThanhToan();
        JButton btnXoa = dialog.getBtnXoa();
        JButton btnXoaTatCa = dialog.getBtnXoaTatCa();
        JTextField txtTimKiem = dialog.getTextFieldSearch();
        JButton btnTimKiem = dialog.getBtnTimKiem();
        
        // 1. Load dữ liệu ban đầu
        loadDanhSachChoLenBang(table);

        // 2. Gán sự kiện cho nút "Thanh Toán" (Tải lại)
        btnThanhToan.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng chọn một phiếu để tải lại!");
                return;
            }
            // Lấy Mã Phiếu Chờ từ cột 0
            String maPhieuCho = table.getValueAt(selectedRow, 0).toString();
            
            // Tải phiếu chờ lên form chính
            taiPhieuChoLenForm(maPhieuCho);
            
            // Đóng dialog
            dialog.dispose();
        });
        
        // 3. Gán sự kiện cho nút "Xóa"
        btnXoa.addActionListener(e -> {
             int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng chọn một phiếu để xóa!");
                return;
            }
            
            String maPhieuCho = table.getValueAt(selectedRow, 0).toString();
            String tenKH = table.getValueAt(selectedRow, 2).toString();
            
            int confirm = JOptionPane.showConfirmDialog(dialog, 
                "Đại Ca Mạnh có chắc muốn xóa phiếu chờ:\n" + maPhieuCho + "\nCủa khách: " + tenKH + "?", 
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                if (phieuChoDAO.xoaPhieuCho(maPhieuCho)) {
                    JOptionPane.showMessageDialog(dialog, "Xóa thành công phiếu " + maPhieuCho + "!");
                    loadDanhSachChoLenBang(table); // Load lại bảng
                } else {
                    JOptionPane.showMessageDialog(dialog, "Xóa thất bại!");
                }
            }
        });
        
        // 4. Gán sự kiện cho nút "Xóa Tất Cả"
        btnXoaTatCa.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(dialog, "Đại Ca Mạnh có chắc muốn xóa TẤT CẢ phiếu chờ không?\nHành động này không thể hoàn tác.", "XÁC NHẬN HỦY DIỆT", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                 if (phieuChoDAO.xoaTatCaPhieuCho()) {
                    JOptionPane.showMessageDialog(dialog, "Đã xóa tất cả phiếu chờ!");
                    loadDanhSachChoLenBang(table); // Load lại bảng
                 } else {
                    JOptionPane.showMessageDialog(dialog, "Xóa thất bại!");
                 }
            }
        });
        
        // 5. Gán sự kiện cho Tìm kiếm (lọc theo SĐT)
        ActionListener timKiemAction = e -> {
            String sdtTim = txtTimKiem.getText().trim().toLowerCase();
            loadDanhSachChoLenBang(table, sdtTim);
        };
        btnTimKiem.addActionListener(timKiemAction);
        txtTimKiem.addActionListener(timKiemAction); // Cho phép nhấn Enter để tìm

        dialog.setVisible(true);
    }
    
    /**
     * THÊM MỚI: Tải danh sách chờ (đã lọc) lên bảng trong Dialog
     */
    private void loadDanhSachChoLenBang(JTable table, String sdtFilter) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<PhieuChoThanhToan> dsCho = phieuChoDAO.getDanhSachCho();
        
        for (PhieuChoThanhToan pc : dsCho) {
            String maKH = (pc.getKhachHang() != null) ? pc.getKhachHang().getMaKH() : "N/A";
            
            // Lọc theo SĐT
            if (sdtFilter == null || pc.getSdtKH().toLowerCase().contains(sdtFilter)) {
                 model.addRow(new Object[]{
                    pc.getMaPhieuCho(), // Cột 0: Mã Phiếu Chờ
                    maKH, // Cột 1: Mã KH
                    pc.getTenKH(), // Cột 2: Tên KH
                    pc.getSdtKH() // Cột 3: SĐT
                });
            }
        }
    }
    // Overload (tải tất cả)
    private void loadDanhSachChoLenBang(JTable table) {
        loadDanhSachChoLenBang(table, null);
    }
    
    /**
     * THÊM MỚI: Tải 1 phiếu chờ (theo MÃ PHIẾU CHỜ) lên lại màn hình chính và xóa phiếu đó
     */
    private void taiPhieuChoLenForm(String maPhieuCho) {
        // 1. Lấy phiếu chờ theo MÃ
        PhieuChoThanhToan pc = phieuChoDAO.getPhieuChoTheoMa(maPhieuCho);
        if (pc == null) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Không tìm thấy phiếu chờ: " + maPhieuCho, "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 2. Lấy chi tiết của phiếu chờ đó
        List<ChiTietPhieuCho> dsChiTiet = ctPhieuChoDAO.getChiTietTheoMaPhieuCho(pc.getMaPhieuCho());
        
        // 3. Xóa trắng form hiện tại
        clearHoaDonForm();
        
        // 4. Đổ dữ liệu phiếu chờ lên form
        Component editorComponent_tai = view.cb_Nhapsosdtkh.getEditor().getEditorComponent(); // Thay cb_Nhapsosdtkh
        if (editorComponent_tai instanceof JTextField) {
            ((JTextField) editorComponent_tai).setText(pc.getSdtKH());
        } else {
            view.cb_Nhapsosdtkh.setSelectedItem(pc.getSdtKH()); // Thay cb_Nhapsosdtkh
        }
        view.lbl_Hientenkh.setText(pc.getTenKH());
        if(pc.getKhachHang() != null) {
            // Tải lại đối tượng KhachHang đầy đủ
            this.currentKhachHang = khachHangDAO.getKhachHangTheoMa(pc.getKhachHang().getMaKH());
        } else {
            this.currentKhachHang = null;
        }
        
        // Đổ tiền 
        view.lbl_Hientienhang.setText(df.format(pc.getTongTienHang()));
        view.lbl_Hienthue.setText(df.format(pc.getThueVAT()));
        view.lbl_Hientongcong.setText(df.format(pc.getTongCong()));
        
        // 5. Đổ danh sách thuốc vào table_hdtam
        DefaultTableModel model = (DefaultTableModel) view.table_hdtam.getModel();
        model.setRowCount(0); // Xóa sạch
        
        for (ChiTietPhieuCho ct : dsChiTiet) {
            model.addRow(new Object[]{
                ct.getThuoc().getMaThuoc(),
                ct.getThuoc().getTenThuoc(),
                ct.getSoLuong(),
                ct.getDonGia(),
                ct.getThanhTien() // Lấy từ hàm getThanhTien()
            });
        }
        
        // 6. Xóa phiếu chờ này khỏi Database
        if (!phieuChoDAO.xoaPhieuCho(pc.getMaPhieuCho())) {
             JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Đã tải lại phiếu, nhưng XÓA phiếu chờ thất bại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }


    // =================================================================
    // == PHẦN 2: LOGIC CHO PANEL TÌM KIẾM HÓA ĐƠN (pn_tkhd) ==
    // =================================================================

    private void addTimKiemHoaDonListeners() {
        view.btn_tkhd_lammoi.addActionListener(e -> clearHoaDonFilter());
        view.btn_tkhd_xemchitiet.addActionListener(e -> xemChiTietHoaDonDaTim()); 

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

    private void xemChiTietHoaDonDaTim() {
        int selectedRow = view.table_tkhd.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Vui lòng chọn một hóa đơn từ bảng để xem chi tiết!");
            return;
        }
        String maHD = view.table_tkhd.getValueAt(selectedRow, 0).toString();
        
        XemchitietHD_GUI dialog = new XemchitietHD_GUI(view.QuanLyHieuThuocTay);
        dialog.loadData(maHD); 
        dialog.setVisible(true);
    }
    private void loadCustomerPhonesToComboBox() {
        List<KhachHang> dsKH = khachHangDAO.getAllKhachHang(); 

        DefaultComboBoxModel<String> phoneModel = new DefaultComboBoxModel<>();

        if (dsKH != null) {
            for (KhachHang kh : dsKH) {
                if (kh.getSoDienThoai() != null && !kh.getSoDienThoai().isEmpty()) {
                    phoneModel.addElement(kh.getSoDienThoai());
                }
            }
        }

        view.cb_Nhapsosdtkh.setModel(phoneModel);
        view.cb_Nhapsosdtkh.setSelectedItem(null);
        AutoCompleteDecorator.decorate(view.cb_Nhapsosdtkh);
    }
}