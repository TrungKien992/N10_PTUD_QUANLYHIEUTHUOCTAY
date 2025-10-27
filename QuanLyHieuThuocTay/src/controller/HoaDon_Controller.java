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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.ParseException; // THÊM MỚI
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel; 
import javax.swing.JButton; // THÊM MỚI
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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
import gui.ThongkeHDNam_GUI;
import gui.ThongkeHDThang_GUI;
import gui.TrangChu_GUI;
import gui.XemchitietHD_GUI;
import utils.PdfExporter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.BorderLayout; // Đảm bảo đã có
import java.awt.Color;      // Đảm bảo đã có
import java.awt.Font;       // Đảm bảo đã có
import java.text.DecimalFormat; // Đảm bảo đã có


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
    private TableRowSorter<DefaultTableModel> sorterNVNgay;
    private TableRowSorter<DefaultTableModel> sorterNVThang;
    private TableRowSorter<DefaultTableModel> sorterNVNam;
    private DoiTra_Controller doiTraController;
    public void setDoiTraController(DoiTra_Controller doiTraController) {
        this.doiTraController = doiTraController;
    }

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
        initializeThongKeComponents();
        addThongKeListeners();
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
            if (doiTraController != null) {
                doiTraController.lamMoiBangHoaDonTraThuoc();
            }
            if (doiTraController != null) {
                doiTraController.lamMoiBangHoaDonDoiThuoc();
            }

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
    private void initializeThongKeComponents() {
        // --- Tab Theo Ngày ---
        view.date_tktn_ngay.setDate(new Date());
        view.cb_tktn_tktnv_boloc.setModel(new DefaultComboBoxModel<>(new String[] {"Tất cả nhân viên", "Theo nhân viên cụ thể"}));
        loadNhanVienToTable(view.table_8);
        setupNhanVienFilter(view.text_tktn_tktnv_boloc_manv, view.text_tktn_tktnv_boloc_tennv, view.table_8);
        // Sửa: Truyền thẳng JTable
        setNhanVienFilterControlsVisible(view.pn_tktn_tktnv_boloc_pntk, view.table_8, false); // Tắt filter và xóa bảng NV ban đầu

        // --- Tab Theo Tháng ---
        view.month_tktt.setMonth(LocalDate.now().getMonthValue() - 1);
        view.year_tktt.setYear(LocalDate.now().getYear());
        view.cb_tktt_tknv.setModel(new DefaultComboBoxModel<>(new String[] {"Tất cả nhân viên", "Theo nhân viên cụ thể"}));
        loadNhanVienToTable(view.table_ttkt);
        setupNhanVienFilter(view.text_tktt_tk_boloc_manv, view.text_tktt_tk_boloc_tennv, view.table_ttkt);
        // Sửa: Truyền thẳng JTable
        setNhanVienFilterControlsVisible(view.pn_tktt_tk_boloc_tknv, view.table_ttkt, false);

        // --- Tab Theo Năm ---
        view.year_tktn.setYear(LocalDate.now().getYear());
        view.cb_chonnamtk.setModel(new DefaultComboBoxModel<>(new String[] {"Tất cả nhân viên", "Theo nhân viên cụ thể"}));
        loadNhanVienToTable(view.table_tktn_hiennv);
        setupNhanVienFilter(view.text_tktnam_locnv_manv, view.text_tktnam_locnv_tennv, view.table_tktn_hiennv);
        // Sửa: Truyền thẳng JTable
        setNhanVienFilterControlsVisible(view.pn_tktnam_locnv, view.table_tktn_hiennv, false);

        // --- Tải dữ liệu thống kê ban đầu ---
        updateThongKeTheoNgay();
        updateThongKeTheoThang();
        updateThongKeTheoNam();
    }

    /**
     * THÊM MỚI: Gán các listener cho các thành phần trong panel thống kê.
     */
    private void addThongKeListeners() {
        // --- Tab Theo Ngày ---
        view.date_tktn_ngay.addPropertyChangeListener("date", (PropertyChangeEvent evt) -> {
            updateThongKeTheoNgay();
        });
        view.table_8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (view.cb_tktn_tktnv_boloc.getSelectedIndex() == 1) {
                     updateThongKeTheoNgay();
                }
            }
        });
        view.cb_tktn_tktnv_boloc.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                boolean enableFilter = (view.cb_tktn_tktnv_boloc.getSelectedIndex() == 1);
                // Sửa: Truyền thẳng JTable thay vì JScrollPane
                setNhanVienFilterControlsVisible(view.pn_tktn_tktnv_boloc_pntk, view.table_8, enableFilter);
                if (!enableFilter) {
                    clearNhanVienSelection(view.table_8); // Chỉ bỏ chọn, không cần reset filter bảng HD nữa
                    updateThongKeTheoNgay(); // Tải lại cho tất cả
                } else {
                     ((DefaultTableModel) view.table_tktn.getModel()).setRowCount(0);
                     view.lbl_tktn_hientongsohd.setText("0");
                     view.lbl_tktn_hientongsotien.setText("0 VND");
                     view.table_8.clearSelection();
                }
            }
        });

        // --- Tab Theo Tháng ---
        PropertyChangeListener monthYearListener = (PropertyChangeEvent evt) -> {
            updateThongKeTheoThang();
        };
        view.month_tktt.addPropertyChangeListener("month", monthYearListener);
        view.year_tktt.addPropertyChangeListener("year", monthYearListener);
        view.table_ttkt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 if (view.cb_tktt_tknv.getSelectedIndex() == 1) {
                    updateThongKeTheoThang();
                 }
            }
        });
        view.btn_tktt_xemchitiet.addActionListener(e -> xemChiTietThongKeThang());
        view.cb_tktt_tknv.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                 boolean enableFilter = (view.cb_tktt_tknv.getSelectedIndex() == 1);
                 // Sửa: Truyền thẳng JTable
                 setNhanVienFilterControlsVisible(view.pn_tktt_tk_boloc_tknv, view.table_ttkt, enableFilter);
                 if (!enableFilter) {
                    clearNhanVienSelection(view.table_ttkt);
                    updateThongKeTheoThang();
                 } else {
                     ((DefaultTableModel) view.table_hienhd_tktt.getModel()).setRowCount(0);
                     view.lbl_tktt_hiensohd.setText("0");
                     view.lbl_tktt_hientongtienhd.setText("0 VND");
                     view.table_ttkt.clearSelection();
                 }
            }
        });

        // --- Tab Theo Năm ---
        view.year_tktn.addPropertyChangeListener("year", (PropertyChangeEvent evt) -> {
            updateThongKeTheoNam();
        });
        view.table_tktn_hiennv.addMouseListener(new MouseAdapter() {
             @Override
            public void mouseClicked(MouseEvent e) {
                 if (view.cb_chonnamtk.getSelectedIndex() == 1) {
                    updateThongKeTheoNam();
                 }
            }
        });
        view.btn_tktnam_xemchitiet.addActionListener(e -> xemChiTietThongKeNam());
        view.cb_chonnamtk.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                 boolean enableFilter = (view.cb_chonnamtk.getSelectedIndex() == 1);
                 // Sửa: Truyền thẳng JTable
                 setNhanVienFilterControlsVisible(view.pn_tktnam_locnv, view.table_tktn_hiennv, enableFilter);
                 if (!enableFilter) {
                    clearNhanVienSelection(view.table_tktn_hiennv);
                    updateThongKeTheoNam();
                 } else {
                     ((DefaultTableModel) view.table_11.getModel()).setRowCount(0);
                     view.lbl_tktn_hientshd.setText("0");
                     view.lbl_tktnam_hientongsotien.setText("0 VND");
                     view.table_tktn_hiennv.clearSelection();
                 }
            }
        });

        // --- Listener nút Xuất File (Placeholder) ---
        // ... (giữ nguyên) ...
    }

 // === CÁC HÀM XỬ LÝ CHO TAB "THỐNG KÊ THEO NGÀY" ===

    private void updateThongKeTheoNgay() {
        Date selectedDate = view.date_tktn_ngay.getDate();
        if (selectedDate == null) {
             // Xóa bảng và label nếu không có ngày nào được chọn
             ((DefaultTableModel) view.table_tktn.getModel()).setRowCount(0);
             view.lbl_tktn_hientongsohd.setText("0");
             view.lbl_tktn_hientongsotien.setText("0 VND");
            return;
        }
        LocalDate ngay = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Lấy mã NV chỉ khi combobox đang chọn "Theo nhân viên cụ thể"
        String maNVSelected = null;
        if (view.cb_tktn_tktnv_boloc.getSelectedIndex() == 1) { // Sử dụng cb_tktn_tktnv_boloc
             maNVSelected = getSelectedMaNV(view.table_8);
             // Nếu chọn "Theo NV" nhưng chưa chọn NV cụ thể thì không hiển thị gì
             if (maNVSelected == null) {
                 ((DefaultTableModel) view.table_tktn.getModel()).setRowCount(0);
                 view.lbl_tktn_hientongsohd.setText("0");
                 view.lbl_tktn_hientongsotien.setText("0 VND");
                 return; // Dừng lại
             }
        }

        // 1. Cập nhật bảng chi tiết (ĐÃ CÓ LỌC THEO NV TỪ DAO)
        DefaultTableModel modelNgay = (DefaultTableModel) view.table_tktn.getModel();
        modelNgay.setRowCount(0);
        List<Object[]> dsHDNgay = hoaDonDAO.getHoaDonChiTietTrongNgay(ngay, maNVSelected); // Truyền maNVSelected (có thể null)
        for (Object[] row : dsHDNgay) {
             // Kiểm tra kiểu dữ liệu trước khi format (phòng trường hợp DAO trả về String)
             if (row[5] instanceof Number) {
                 row[5] = df.format(((Number) row[5]).doubleValue());
             }
            modelNgay.addRow(row);
        }

        // 2. Cập nhật tổng kết (ĐÃ CÓ LỌC THEO NV TỪ DAO)
        Map<String, Object> tongKetNgay = hoaDonDAO.getTongKetTrongNgay(ngay, maNVSelected); // Truyền maNVSelected (có thể null)
        view.lbl_tktn_hientongsohd.setText(String.valueOf(tongKetNgay.getOrDefault("tongSoHD", 0)));
        view.lbl_tktn_hientongsotien.setText(df.format(tongKetNgay.getOrDefault("tongTien", 0.0)));
    }

    // === CÁC HÀM XỬ LÝ CHO TAB "THỐNG KÊ THEO THÁNG" ===

    private void updateThongKeTheoThang() {
        int thang = view.month_tktt.getMonth() + 1;
        int nam = view.year_tktt.getYear();
        view.lbl_hienthangvanam.setText(String.format("Tháng %d/%d", thang, nam));

        String maNVSelected = null;
        if (view.cb_tktt_tknv.getSelectedIndex() == 1) {
            maNVSelected = getSelectedMaNV(view.table_ttkt);
             if (maNVSelected == null) {
                 // Xóa bảng và biểu đồ nếu chưa chọn NV
                 ((DefaultTableModel) view.table_hienhd_tktt.getModel()).setRowCount(0);
                 view.lbl_tktt_hiensohd.setText("0");
                 view.lbl_tktt_hientongtienhd.setText("0 VND");
                 veBieuDoThang(new ArrayList<>()); // <<< Vẽ biểu đồ rỗng
                 return;
             }
        }

        // 1. Lấy dữ liệu gốc từ DAO
        List<Object[]> dsThongKeNgay = hoaDonDAO.getThongKeTheoNgayTrongThang(thang, nam, maNVSelected);

        // *** SỬA Ở ĐÂY: Vẽ biểu đồ TRƯỚC khi format ***
        // 3. Vẽ biểu đồ (với dữ liệu gốc)
        veBieuDoThang(dsThongKeNgay);

        // 2. Cập nhật bảng thống kê (format dữ liệu cho bảng)
        DefaultTableModel modelThang = (DefaultTableModel) view.table_hienhd_tktt.getModel();
        modelThang.setRowCount(0);
        for (Object[] row : dsThongKeNgay) {
             // Kiểm tra kiểu dữ liệu và format tiền NGAY LÚC THÊM VÀO BẢNG
             if (row[2] instanceof Number) {
                 row[2] = df.format(((Number) row[2]).doubleValue());
             }
            modelThang.addRow(row); // row[2] bây giờ là String đã format
        }

        // 4. Cập nhật tổng kết
        Map<String, Object> tongKetThang = hoaDonDAO.getTongKetTrongThang(thang, nam, maNVSelected);
        view.lbl_tktt_hiensohd.setText(String.valueOf(tongKetThang.getOrDefault("tongSoHD", 0)));
        view.lbl_tktt_hientongtienhd.setText(df.format(tongKetThang.getOrDefault("tongTien", 0.0)));
    }

    // === CÁC HÀM XỬ LÝ CHO TAB "THỐNG KÊ THEO NĂM" ===

    private void updateThongKeTheoNam() {
        int nam = view.year_tktn.getYear();
        view.lbl_tktn_hiennam.setText(String.valueOf(nam));

        String maNVSelected = null;
        if (view.cb_chonnamtk.getSelectedIndex() == 1) {
            maNVSelected = getSelectedMaNV(view.table_tktn_hiennv);
             if (maNVSelected == null) {
                 // Xóa bảng và biểu đồ nếu chưa chọn NV
                 ((DefaultTableModel) view.table_11.getModel()).setRowCount(0);
                 view.lbl_tktn_hientshd.setText("0");
                 view.lbl_tktnam_hientongsotien.setText("0 VND");
                 veBieuDoNam(new ArrayList<>()); // <<< Vẽ biểu đồ rỗng
                 return;
             }
        }

        // 1. Lấy dữ liệu gốc từ DAO
        List<Object[]> dsThongKeThang = hoaDonDAO.getThongKeTheoThangTrongNam(nam, maNVSelected);

        // *** SỬA Ở ĐÂY: Vẽ biểu đồ TRƯỚC khi format ***
        // 3. Vẽ biểu đồ (với dữ liệu gốc)
        veBieuDoNam(dsThongKeThang);

        // 2. Cập nhật bảng thống kê (format dữ liệu cho bảng)
        DefaultTableModel modelNam = (DefaultTableModel) view.table_11.getModel();
        modelNam.setRowCount(0);
        for (Object[] row : dsThongKeThang) {
            row[0] = "Tháng " + row[0]; // Format tháng
            // Kiểm tra kiểu dữ liệu và format tiền NGAY LÚC THÊM VÀO BẢNG
            if (row[2] instanceof Number) {
                row[2] = df.format(((Number) row[2]).doubleValue());
            }
            modelNam.addRow(row); // row[2] bây giờ là String đã format
        }

        // 4. Cập nhật tổng kết
        Map<String, Object> tongKetNam = hoaDonDAO.getTongKetTrongNam(nam, maNVSelected);
        view.lbl_tktn_hientshd.setText(String.valueOf(tongKetNam.getOrDefault("tongSoHD", 0)));
        view.lbl_tktnam_hientongsotien.setText(df.format(tongKetNam.getOrDefault("tongTien", 0.0)));
    }
    
    private void xemChiTietThongKeThang() {
        int selectedRow = view.table_hienhd_tktt.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Vui lòng chọn một ngày trong bảng để xem chi tiết!");
            return;
        }

        int ngay = (int) view.table_hienhd_tktt.getValueAt(selectedRow, 0);
        int thang = view.month_tktt.getMonth() + 1;
        int nam = view.year_tktt.getYear();
        LocalDate selectedDate = LocalDate.of(nam, thang, ngay);

        // Lấy mã NV đang được chọn (nếu có)
        String maNVSelected = getSelectedMaNV(view.table_ttkt);

        ThongkeHDThang_GUI dialog = new ThongkeHDThang_GUI(view.QuanLyHieuThuocTay);
        // Truyền ngày tháng năm và mã NV vào dialog để load dữ liệu
        dialog.loadData(selectedDate, maNVSelected); // Cần thêm hàm loadData(LocalDate, String) vào ThongkeHDThang_GUI
        dialog.setVisible(true);
    }

    /** THÊM MỚI: Mở dialog xem chi tiết hóa đơn của một tháng trong năm */
    private void xemChiTietThongKeNam() {
        int selectedRow = view.table_11.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view.QuanLyHieuThuocTay, "Vui lòng chọn một tháng trong bảng để xem chi tiết!");
            return;
        }

        // Lấy tháng từ chuỗi "Tháng x"
        String thangStr = view.table_11.getValueAt(selectedRow, 0).toString().replace("Tháng ", "");
        int thang = Integer.parseInt(thangStr);
        int nam = view.year_tktn.getYear();

        // Lấy mã NV đang được chọn (nếu có)
        String maNVSelected = getSelectedMaNV(view.table_tktn_hiennv);

        ThongkeHDNam_GUI dialog = new ThongkeHDNam_GUI(view.QuanLyHieuThuocTay);
        // Truyền tháng, năm và mã NV vào dialog để load dữ liệu
        dialog.loadData(thang, nam, maNVSelected); // Cần thêm hàm loadData(int, int, String) vào ThongkeHDNam_GUI
        dialog.setVisible(true);
    }

    // === CÁC HÀM HỖ TRỢ CHO LỌC NHÂN VIÊN TRONG THỐNG KÊ ===

    /** THÊM MỚI: Load danh sách nhân viên vào bảng lọc */
    private void loadNhanVienToTable(JTable tableNV) {
        DefaultTableModel modelNV = (DefaultTableModel) tableNV.getModel();
        modelNV.setRowCount(0);
        List<NhanVien> dsNV = nhanVienDAO.getAllNhanVien();
        for (NhanVien nv : dsNV) {
            modelNV.addRow(new Object[]{nv.getMaNV(), nv.getTenNV()});
        }
    }

    /** THÊM MỚI: Thiết lập bộ lọc cho bảng nhân viên (theo mã và tên) */
    private void setupNhanVienFilter(JTextField txtMaNV, JTextField txtTenNV, JTable tableNV) { // *** Bỏ tham số sorter ***
        DefaultTableModel modelNV = (DefaultTableModel) tableNV.getModel();
        TableRowSorter<DefaultTableModel> currentSorter; // Biến cục bộ tạm để listener truy cập

        // Xác định và khởi tạo sorter tương ứng cho bảng
        if (tableNV == view.table_8) {
            sorterNVNgay = new TableRowSorter<>(modelNV);
            tableNV.setRowSorter(sorterNVNgay);
            currentSorter = sorterNVNgay; // Gán sorter của tab Ngày
        } else if (tableNV == view.table_ttkt) {
            sorterNVThang = new TableRowSorter<>(modelNV);
            tableNV.setRowSorter(sorterNVThang);
            currentSorter = sorterNVThang; // Gán sorter của tab Tháng
        } else if (tableNV == view.table_tktn_hiennv) {
            sorterNVNam = new TableRowSorter<>(modelNV);
            tableNV.setRowSorter(sorterNVNam);
            currentSorter = sorterNVNam; // Gán sorter của tab Năm
        } else {
             System.err.println("Lỗi setupNhanVienFilter: Bảng không xác định.");
            return; // Thoát nếu bảng không khớp
        }

        // Biến currentSorter bây giờ là effectively final vì nó chỉ được gán giá trị một lần
        // trong mỗi nhánh if/else if và không bị thay đổi sau đó trước khi listener được tạo.

        DocumentListener filterListenerNV = new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { applyNhanVienFilter(); }
            @Override public void removeUpdate(DocumentEvent e) { applyNhanVienFilter(); }
            @Override public void changedUpdate(DocumentEvent e) { applyNhanVienFilter(); }

            private void applyNhanVienFilter() {
                List<RowFilter<Object, Object>> filters = new ArrayList<>();
                String maFilter = txtMaNV.getText().trim();
                String tenFilter = txtTenNV.getText().trim();

                if (!maFilter.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + maFilter, 0));
                }
                if (!tenFilter.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + tenFilter, 1));
                }

                RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
                // *** SỬA Ở ĐÂY: Dùng biến currentSorter (effectively final) ***
                currentSorter.setRowFilter(combinedFilter); // Dòng 1298 cũ giờ dùng currentSorter
            }
        };

        txtMaNV.getDocument().addDocumentListener(filterListenerNV);
        txtTenNV.getDocument().addDocumentListener(filterListenerNV);

        // *** Bỏ khối if/else gán sorter ở cuối vì đã gán ở trên rồi ***
        // if (tableNV == view.table_8) sorterNVNgay = sorter;
        // else if (tableNV == view.table_ttkt) sorterNVThang = sorter;
        // else if (tableNV == view.table_tktn_hiennv) sorterNVNam = sorter;
    }

    /** THÊM MỚI: Lấy mã NV từ dòng đang được chọn trong bảng lọc NV */
    private String getSelectedMaNV(JTable tableNV) {
        int selectedRow = tableNV.getSelectedRow();
        if (selectedRow != -1) {
            // Chuyển đổi view row index sang model row index (quan trọng khi có filter)
            int modelRow = tableNV.convertRowIndexToModel(selectedRow);
            return tableNV.getModel().getValueAt(modelRow, 0).toString(); // Cột 0 là Mã NV
        }
        return null; // Không có NV nào được chọn
    }

    /** THÊM MỚI: Bỏ chọn dòng trong bảng lọc NV */
    /** Bỏ chọn dòng trong bảng lọc NV */
    private void clearNhanVienSelection(JTable tableNV) {
        tableNV.clearSelection(); 
    }

    /**
     * THÊM MỚI: Lọc bảng hóa đơn chi tiết (tab Ngày) dựa trên nhân viên được chọn.
     * Áp dụng filter trực tiếp lên TableRowSorter của bảng hóa đơn.
     * @param tableNV Bảng lọc nhân viên
     * @param tableHD Bảng hóa đơn cần lọc
     * @param colMaNV Index cột Mã NV trong bảng HD
     * @param colTenNV Index cột Tên NV trong bảng HD (có thể dùng 1 trong 2)
     */
     private void filterHoaDonTableBySelectedNV(JTable tableNV, JTable tableHD, int colMaNV, int colTenNV) {
         String maNVSelected = getSelectedMaNV(tableNV);
         DefaultTableModel modelHD = (DefaultTableModel) tableHD.getModel();
         TableRowSorter<DefaultTableModel> sorterHD = new TableRowSorter<>(modelHD);
         tableHD.setRowSorter(sorterHD);

         if (maNVSelected != null) {
             // Lọc bảng HD theo mã NV đã chọn (không phân biệt hoa thường)
             sorterHD.setRowFilter(RowFilter.regexFilter("(?i)" + maNVSelected, colMaNV));
         } else {
             // Nếu không có NV nào được chọn, hiển thị tất cả
             sorterHD.setRowFilter(null);
         }
     }

     // === CÁC HÀM TÍNH TOÁN TỔNG KẾT THEO NV (TẠM THỜI) ===
     // Lưu ý: Các hàm này sẽ chậm nếu có nhiều hóa đơn. Nên tối ưu trong DAO.

     private Map<String, Object> tinhTongKetThangTheoNV(int thang, int nam, String maNV) {
         Map<String, Object> result = new HashMap<>();
         int tongSoHD = 0;
         double tongTien = 0.0;

         // Lấy tất cả HD chi tiết trong tháng (chưa tối ưu)
         List<Object[]> dsHDThang = hoaDonDAO.getHoaDonChiTietTrongThangNam(thang, nam); // Cần thêm hàm này vào DAO

         for (Object[] hd : dsHDThang) {
             String maNVTrongHD = (String) hd[3]; // Giả sử cột 3 là MaNV
             if (maNV.equals(maNVTrongHD)) {
                 tongSoHD++;
                 tongTien += (Double) hd[5]; // Giả sử cột 5 là Tổng tiền
             }
         }
         result.put("tongSoHD", tongSoHD);
         result.put("tongTien", tongTien);
         return result;
     }

     private Map<String, Object> tinhTongKetNamTheoNV(int nam, String maNV) {
         Map<String, Object> result = new HashMap<>();
         int tongSoHD = 0;
         double tongTien = 0.0;

         // Lấy tất cả HD chi tiết trong năm (chưa tối ưu)
          List<Object[]> dsHDNam = hoaDonDAO.getHoaDonChiTietTrongNam(nam); // Cần thêm hàm này vào DAO

         for (Object[] hd : dsHDNam) {
             String maNVTrongHD = (String) hd[3]; // Giả sử cột 3 là MaNV
             if (maNV.equals(maNVTrongHD)) {
                 tongSoHD++;
                 tongTien += (Double) hd[5]; // Giả sử cột 5 là Tổng tiền
             }
         }
         result.put("tongSoHD", tongSoHD);
         result.put("tongTien", tongTien);
         return result;
     }
     /**
      * Bật/tắt các controls lọc nhân viên VÀ xóa/tải lại bảng NV.
      * @param filterPanel Panel chứa JTextField tìm kiếm (ví dụ: pn_tktn_tktnv_boloc_pntk).
      * @param employeeTable Bảng hiển thị danh sách nhân viên (ví dụ: table_8).
      * @param enable Trạng thái true (bật) hoặc false (tắt).
      */
     private void setNhanVienFilterControlsVisible(JPanel filterPanel, JTable employeeTable, boolean enable) {
         // Bật/tắt các JTextField bên trong filterPanel
         if (filterPanel != null) {
             for (Component comp : filterPanel.getComponents()) {
                 if (comp instanceof JTextField) {
                     comp.setEnabled(enable);
                     if (!enable) {
                         ((JTextField) comp).setText(""); // Xóa text khi tắt
                     }
                 }
                 // Có thể xử lý thêm JComboBox bên trong nếu cần
             }
             // Đảm bảo panel lọc luôn hiển thị
              filterPanel.setVisible(true);
              if(filterPanel.getParent() != null) { // Đảm bảo panel cha của filterPanel cũng hiển thị
                   filterPanel.getParent().setVisible(true);
              }
         }

         // Xóa hoặc tải lại dữ liệu bảng nhân viên
         if (employeeTable != null) {
             DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
             if (!enable) {
                 model.setRowCount(0); // Xóa hết nhân viên khỏi bảng
                 employeeTable.clearSelection(); // Bỏ chọn
                 // Vô hiệu hóa bảng (tùy chọn)
                 // employeeTable.setEnabled(false);
             } else {
                 loadNhanVienToTable(employeeTable); // Tải lại danh sách NV
                 // Kích hoạt lại bảng (nếu đã vô hiệu hóa)
                 // employeeTable.setEnabled(true);
             }
              // Đảm bảo ScrollPane chứa bảng luôn hiển thị
              Component parent = employeeTable.getParent(); // Thường là JViewport
              if(parent != null && parent.getParent() instanceof JScrollPane){
                  ((JScrollPane)parent.getParent()).setVisible(true);
              }
         }
         // Revalidate và repaint để cập nhật giao diện
         if (filterPanel != null && filterPanel.getParent() != null) {
              filterPanel.getParent().revalidate();
              filterPanel.getParent().repaint();
         }
         if (employeeTable != null && employeeTable.getParent() != null && employeeTable.getParent().getParent() != null) { // table -> viewport -> scrollpane
              employeeTable.getParent().getParent().revalidate();
              employeeTable.getParent().getParent().repaint();
         }
     }
     /**
      * Vẽ biểu đồ cột doanh thu theo ngày trong tháng.
      * @param data Danh sách dữ liệu, mỗi Object[] chứa: [Ngày (int), TổngSốHD (int), TổngTiền (double)]
      */
     private void veBieuDoThang(List<Object[]> data) {
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         String series = "Doanh thu";

         // Thêm dữ liệu vào dataset
         if (data != null && !data.isEmpty()) {
             for (Object[] row : data) {
                 try {
                     String ngay = String.valueOf(row[0]); // Ngày là category (trục X)
                     double doanhThu = ((Number) row[2]).doubleValue(); // Doanh thu là value (trục Y)
                     dataset.addValue(doanhThu, series, ngay);
                 } catch (Exception e) { System.err.println("Lỗi dữ liệu biểu đồ tháng: " + e.getMessage()); }
             }
         } else {
              dataset.addValue(0, series, "1"); // Giá trị 0 nếu không có data
         }

         // Tạo biểu đồ
         JFreeChart barChart = ChartFactory.createBarChart(
                 "", "Ngày", "Doanh thu (VND)", dataset,
                 PlotOrientation.VERTICAL, false, true, false);

         // Tùy chỉnh (màu sắc, font)
         CategoryPlot plot = barChart.getCategoryPlot();
         plot.setBackgroundPaint(Color.WHITE);
         plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
         plot.setInsets(new RectangleInsets(10, 10, 10, 10)); // Padding

         CategoryAxis domainAxis = plot.getDomainAxis();
         domainAxis.setLabelFont(new Font("Segoe UI", Font.BOLD, 14));
         domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));

         NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
         rangeAxis.setLabelFont(new Font("Segoe UI", Font.BOLD, 14));
         rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
         rangeAxis.setNumberFormatOverride(new DecimalFormat("#,##0"));

         BarRenderer renderer = (BarRenderer) plot.getRenderer();
         renderer.setSeriesPaint(0, new Color(0, 123, 255)); // Màu xanh dương
         renderer.setDrawBarOutline(false);
         renderer.setItemMargin(0.2);

         // Hiển thị biểu đồ lên panel
         ChartPanel chartPanel = new ChartPanel(barChart);
         chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
         chartPanel.setBackground(Color.WHITE);

         view.pn_bieudo_thang.removeAll(); // Xóa biểu đồ cũ
         view.pn_bieudo_thang.add(chartPanel, BorderLayout.CENTER); // Thêm biểu đồ mới vào giữa (do dùng BorderLayout)
         view.pn_bieudo_thang.revalidate();
         view.pn_bieudo_thang.repaint();
     }
     
     /**
      * Vẽ biểu đồ cột doanh thu theo tháng trong năm.
      * @param data Danh sách dữ liệu, mỗi Object[] chứa: [Tháng (int/String), TổngSốHD (int), TổngTiền (double)]
      */
     private void veBieuDoNam(List<Object[]> data) {
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         String series = "Doanh thu";

         // Thêm dữ liệu vào dataset
         if (data != null && !data.isEmpty()) {
             for (Object[] row : data) {
                  try {
                      // Lấy tháng (có thể là số hoặc "Tháng x")
                      String thang;
                      if(row[0] instanceof String && ((String)row[0]).startsWith("Tháng ")){
                          thang = ((String)row[0]).replace("Tháng ", "");
                      } else {
                          thang = String.valueOf(row[0]);
                      }
                     double doanhThu = ((Number) row[2]).doubleValue();
                     dataset.addValue(doanhThu, series, "T" + thang); // Hiển thị T1, T2,... trên trục X
                  } catch (Exception e) { System.err.println("Lỗi dữ liệu biểu đồ năm: " + e.getMessage()); }
             }
         } else {
              dataset.addValue(0, series, "T1"); // Giá trị 0 nếu không có data
         }

         // Tạo biểu đồ
         JFreeChart barChart = ChartFactory.createBarChart(
                 "", "Tháng", "Doanh thu (VND)", dataset,
                 PlotOrientation.VERTICAL, false, true, false);

         // Tùy chỉnh (tương tự biểu đồ tháng, đổi màu cột)
         CategoryPlot plot = barChart.getCategoryPlot();
         plot.setBackgroundPaint(Color.WHITE);
         plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
         plot.setInsets(new RectangleInsets(10, 10, 10, 10));

         CategoryAxis domainAxis = plot.getDomainAxis();
         domainAxis.setLabelFont(new Font("Segoe UI", Font.BOLD, 14));
         domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));

         NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
         rangeAxis.setLabelFont(new Font("Segoe UI", Font.BOLD, 14));
         rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
         rangeAxis.setNumberFormatOverride(new DecimalFormat("#,##0"));

         BarRenderer renderer = (BarRenderer) plot.getRenderer();
         renderer.setSeriesPaint(0, new Color(40, 167, 69)); // Màu xanh lá
         renderer.setDrawBarOutline(false);
         renderer.setItemMargin(0.2);

         // Hiển thị biểu đồ lên panel
         ChartPanel chartPanel = new ChartPanel(barChart);
         chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
         chartPanel.setBackground(Color.WHITE);

         view.pn_bieudo_nam.removeAll(); // Xóa biểu đồ cũ
         view.pn_bieudo_nam.add(chartPanel, BorderLayout.CENTER); // Thêm biểu đồ mới
         view.pn_bieudo_nam.revalidate();
         view.pn_bieudo_nam.repaint();
     }
}
     