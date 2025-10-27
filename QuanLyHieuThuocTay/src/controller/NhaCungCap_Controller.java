package controller;

import dao.nhaCungCap_DAO;
import entity.NhaCungCap;
import gui.Dialog_ChiTietNCC; // Import dialog
import gui.TrangChu_GUI; 

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List; 
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
// Đã xóa import List thừa

public class NhaCungCap_Controller {

    private TrangChu_GUI trangChuGUI; // GUI chính
    private nhaCungCap_DAO dao;
    // Đã xóa biến private List<NhaCungCap> dsNhaCungCapFull;
    
    public NhaCungCap_Controller(TrangChu_GUI trangChuGUI) {
        this.trangChuGUI = trangChuGUI;
        this.dao = new nhaCungCap_DAO();

        // Tải dữ liệu ban đầu
        reloadAllTables();
        
        // Đăng ký sự kiện
        registerThemNhaCungCapEvents();
        registerCapNhatNhaCungCapEvents();
        registerTimKiemNhaCungCapEvents();
        
        // Tải mã NCC mới
        loadNewMaNccLenFormThem();
    }

    // =========================================================================
    // SECTION: TẢI DỮ LIỆU (DATA LOADING) - Phiên bản gốc (gọi DAO nhiều lần)
    // =========================================================================

    /** Tải dữ liệu lên bảng ở tab "Thêm" (gọi DAO) */
    public void loadDataToTableThemNCC() { 
        List<NhaCungCap> ds = dao.getAllNhaCungCap(); // Gọi DAO
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_ThemNCC.getModel();
        model.setRowCount(0); 
        for (NhaCungCap ncc : ds) {
            model.addRow(new Object[]{
                    ncc.getMaNhaCungCap(),
                    ncc.getTenNhaCungCap(),
                    ncc.getSoDienThoai(),
                    ncc.getEmail(),
                    ncc.getDiaChi(),
                    ncc.isTrangThai() ? "Đang hợp tác" : "Ngừng hợp tác",
                    ncc.getGhiChu()
            });
        }
    }
    
    /** Tải dữ liệu lên bảng ở tab "Cập nhật" (gọi DAO) */
    public void loadDataToTableCapNhatNCC() { 
        List<NhaCungCap> ds = dao.getAllNhaCungCap(); // Gọi DAO
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_CNNCC.getModel();
        model.setRowCount(0);
        for (NhaCungCap ncc : ds) {
            model.addRow(new Object[]{
                    ncc.getMaNhaCungCap(),
                    ncc.getTenNhaCungCap(),
                    ncc.getSoDienThoai(),
                    ncc.getEmail(),
                    ncc.getDiaChi(),
                    ncc.isTrangThai() ? "Đang hợp tác" : "Ngừng hợp tác", 
                    ncc.getGhiChu()
            });
        }
    }
    
    /** Tải dữ liệu lên bảng ở tab "Tìm kiếm" (gọi DAO) */
    public void loadDataToTableTimKiemNCC() { 
          List<NhaCungCap> ds = dao.getAllNhaCungCap(); // Gọi DAO
          DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_TKNCC.getModel();
          model.setRowCount(0); 
          for (NhaCungCap ncc : ds) {
              model.addRow(new Object[]{
                      ncc.getMaNhaCungCap(),
                      ncc.getTenNhaCungCap(),
                      ncc.getSoDienThoai(),
                      ncc.getEmail(),
                      ncc.getDiaChi(),
                      ncc.isTrangThai() ? "Đang hợp tác" : "Ngừng hợp tác", 
                      ncc.getGhiChu()
              });
          }
    }
    
    /** Hàm tải lại dữ liệu trên tất cả các bảng (phiên bản gốc) */
    private void reloadAllTables() {
        loadDataToTableThemNCC();
        loadDataToTableCapNhatNCC();
        loadDataToTableTimKiemNCC();
    }

    // =========================================================================
    // SECTION: TAB THÊM NHÀ CUNG CẤP
    // =========================================================================

    /** Đăng ký sự kiện cho các nút ở tab Thêm NCC */
    public void registerThemNhaCungCapEvents() {
        trangChuGUI.btnThem_TNCC.addActionListener(e -> themNhaCungCap());
        trangChuGUI.btnLamMoi_TNCC.addActionListener(e -> lamMoiFormThem());
    }

    /** Xử lý logic Thêm nhà cung cấp mới */
    private void themNhaCungCap() {
        try {
            // ... (code lấy dữ liệu và validate như cũ) ...
            String maNCC = trangChuGUI.txtMaNCC_TNCC.getText().trim();
            String tenNCC = trangChuGUI.txtTenNCC_TNCC.getText().trim();
            String sdt = trangChuGUI.txtSDT_TNCC.getText().trim();
            String email = trangChuGUI.txtEmail_TNCC.getText().trim();
            String diaChi = trangChuGUI.txtDC_TNCC.getText().trim();
            String ghiChu = trangChuGUI.txtGhiChu_TNCC.getText().trim();
            String trangThaiStr = trangChuGUI.cboTrangThai_TNCC.getSelectedItem().toString();
            boolean trangThai = trangThaiStr.equals("Đang hợp tác");

            if (tenNCC.isEmpty() || sdt.isEmpty() || email.isEmpty() || diaChi.isEmpty()) {
                JOptionPane.showMessageDialog(trangChuGUI, "Vui lòng nhập đầy đủ Tên, SĐT, Email và Địa chỉ.", "Thiếu dữ liệu", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!sdt.matches("^0[0-9]{9}$")) {
                JOptionPane.showMessageDialog(trangChuGUI, "Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số.", "Sai định dạng SĐT", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                JOptionPane.showMessageDialog(trangChuGUI, "Email không hợp lệ.", "Sai định dạng Email", JOptionPane.WARNING_MESSAGE);
                return;
            }

            NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, sdt, email, diaChi, trangThai, ghiChu);
            boolean result = dao.themNhaCungCap(ncc);

            if (result) {
                JOptionPane.showMessageDialog(trangChuGUI, "Thêm nhà cung cấp thành công!");
                reloadAllTables(); // Gọi reloadAllTables phiên bản gốc
                lamMoiFormThem();
            } else {
                JOptionPane.showMessageDialog(trangChuGUI, "Thêm nhà cung cấp thất bại! (Có thể trùng Mã NCC).", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(trangChuGUI, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Làm mới các trường nhập liệu trên tab Thêm NCC */
    private void lamMoiFormThem() {
        trangChuGUI.txtTenNCC_TNCC.setText("");
        trangChuGUI.txtSDT_TNCC.setText("");
        trangChuGUI.txtEmail_TNCC.setText("");
        trangChuGUI.txtDC_TNCC.setText("");
        trangChuGUI.txtGhiChu_TNCC.setText("");
        trangChuGUI.cboTrangThai_TNCC.setSelectedIndex(0); 
        loadNewMaNccLenFormThem();
    }
    
    /** Tải mã NCC mới nhất lên ô Mã NCC */
    private void loadNewMaNccLenFormThem() {
        trangChuGUI.txtMaNCC_TNCC.setText(dao.generateNewMaNCC());
    }

    // =========================================================================
    // SECTION: TAB CẬP NHẬT NHÀ CUNG CẤP
    // =========================================================================

    /** Đăng ký sự kiện cho các component trên tab Cập nhật NCC */
    private void registerCapNhatNhaCungCapEvents() {
        // Sự kiện click chuột vào bảng
        trangChuGUI.table_CNNCC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Giữ nguyên hàm cũ (gọi DAO)
                hienThiThongTinLenFormCapNhat(); 
            }
        });
        
        trangChuGUI.btnCapNhat_CNNCC.addActionListener(e -> capNhatNhaCungCap());
        trangChuGUI.btnKhoiPhuc_CNNCC.addActionListener(e -> khoiPhucFormCapNhat());
        trangChuGUI.btnLamMoi_CNNCC.addActionListener(e -> lamMoiFormCapNhat());
        trangChuGUI.btnXoa_CNNCC.addActionListener(e -> ngungHopTacNhaCungCap());
    }

    /**
     * Lấy dữ liệu từ hàng được chọn và hiển thị lên form (gọi DAO)
     */
    private void hienThiThongTinLenFormCapNhat() { 
        int selectedRow = trangChuGUI.table_CNNCC.getSelectedRow();
        if (selectedRow < 0) {
            return; 
        }

        String maNCC = trangChuGUI.table_CNNCC.getValueAt(selectedRow, 0).toString();
        NhaCungCap ncc = dao.getNhaCungCapTheoMa(maNCC); // Gọi DAO
        
        if (ncc != null) {
            trangChuGUI.txtMaNCC_CNNCC.setText(ncc.getMaNhaCungCap());
            trangChuGUI.txtTenNCC_CNNCC.setText(ncc.getTenNhaCungCap());
            trangChuGUI.txtSDT_CNNCC.setText(ncc.getSoDienThoai());
            trangChuGUI.txtEmail_CNNCC.setText(ncc.getEmail());
            trangChuGUI.txtDiaChi_CNNCC.setText(ncc.getDiaChi());
            trangChuGUI.txtGhiChu_CNNCC.setText(ncc.getGhiChu()); 
            trangChuGUI.cboTrangThai_CNNCC.setSelectedItem(
                ncc.isTrangThai() ? "Đang hợp tác" : "Ngừng hợp tác"
            );
        }
    }

    /** Xử lý logic Cập nhật nhà cung cấp */
    private void capNhatNhaCungCap() {
        try {
            // ... (code lấy dữ liệu và validate như cũ) ...
             String maNCC = trangChuGUI.txtMaNCC_CNNCC.getText().trim();
            if (maNCC.isEmpty()) {
                JOptionPane.showMessageDialog(trangChuGUI, "Vui lòng chọn một nhà cung cấp từ bảng để cập nhật.", "Chưa chọn NCC", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String tenNCC = trangChuGUI.txtTenNCC_CNNCC.getText().trim();
            String sdt = trangChuGUI.txtSDT_CNNCC.getText().trim();
            String email = trangChuGUI.txtEmail_CNNCC.getText().trim();
            String diaChi = trangChuGUI.txtDiaChi_CNNCC.getText().trim();
            String ghiChu = trangChuGUI.txtGhiChu_CNNCC.getText().trim();
            String trangThaiStr = trangChuGUI.cboTrangThai_CNNCC.getSelectedItem().toString();
            boolean trangThai = trangThaiStr.equals("Đang hợp tác");

            if (tenNCC.isEmpty() || sdt.isEmpty() || email.isEmpty() || diaChi.isEmpty()) {
                JOptionPane.showMessageDialog(trangChuGUI, "Vui lòng nhập đầy đủ Tên, SĐT, Email và Địa chỉ.", "Thiếu dữ liệu", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!sdt.matches("^0[0-9]{9}$")) {
                JOptionPane.showMessageDialog(trangChuGUI, "Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số.", "Sai định dạng SĐT", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                JOptionPane.showMessageDialog(trangChuGUI, "Email không hợp lệ.", "Sai định dạng Email", JOptionPane.WARNING_MESSAGE);
                return;
            }

            NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, sdt, email, diaChi, trangThai, ghiChu);
            
            boolean result = dao.updateNhaCungCap(ncc);

            if (result) {
                JOptionPane.showMessageDialog(trangChuGUI, "Cập nhật nhà cung cấp thành công!");
                reloadAllTables(); // Gọi reloadAllTables phiên bản gốc
                lamMoiFormCapNhat();
            } else {
                JOptionPane.showMessageDialog(trangChuGUI, "Cập nhật nhà cung cấp thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(trangChuGUI, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Khôi phục dữ liệu gốc của NCC đang chọn trên form (gọi DAO) */
    private void khoiPhucFormCapNhat() {
        String maNCC = trangChuGUI.txtMaNCC_CNNCC.getText().trim();
        if (maNCC.isEmpty()) {
            JOptionPane.showMessageDialog(trangChuGUI, "Chưa có nhà cung cấp nào được chọn để khôi phục.", "Chưa chọn NCC", JOptionPane.WARNING_MESSAGE);
            return;
        }

        NhaCungCap nccGoc = dao.getNhaCungCapTheoMa(maNCC); // Gọi DAO
        
        if (nccGoc != null) {
            trangChuGUI.txtTenNCC_CNNCC.setText(nccGoc.getTenNhaCungCap());
            trangChuGUI.txtSDT_CNNCC.setText(nccGoc.getSoDienThoai());
            trangChuGUI.txtEmail_CNNCC.setText(nccGoc.getEmail());
            trangChuGUI.txtDiaChi_CNNCC.setText(nccGoc.getDiaChi());
            trangChuGUI.txtGhiChu_CNNCC.setText(nccGoc.getGhiChu());
            trangChuGUI.cboTrangThai_CNNCC.setSelectedItem(
                nccGoc.isTrangThai() ? "Đang hợp tác" : "Ngừng hợp tác"
            );
            JOptionPane.showMessageDialog(trangChuGUI, "Đã khôi phục dữ liệu gốc cho " + maNCC);
        } else {
            JOptionPane.showMessageDialog(trangChuGUI, "Không tìm thấy dữ liệu gốc cho " + maNCC, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Làm mới (xóa trắng) các trường trên form Cập nhật */
    private void lamMoiFormCapNhat() {
        trangChuGUI.txtMaNCC_CNNCC.setText("");
        trangChuGUI.txtTenNCC_CNNCC.setText("");
        trangChuGUI.txtSDT_CNNCC.setText("");
        trangChuGUI.txtEmail_CNNCC.setText("");
        trangChuGUI.txtDiaChi_CNNCC.setText("");
        trangChuGUI.txtGhiChu_CNNCC.setText("");
        trangChuGUI.cboTrangThai_CNNCC.setSelectedIndex(0);
        trangChuGUI.table_CNNCC.clearSelection();
    }
    
    /** Xử lý nút "Xóa" (Ngừng hợp tác) (gọi DAO) */
    private void ngungHopTacNhaCungCap() {
        // ... (code lấy maNCC và tenNCC như cũ) ...
        String maNCC = trangChuGUI.txtMaNCC_CNNCC.getText().trim();
        String tenNCC = trangChuGUI.txtTenNCC_CNNCC.getText().trim();
        int selectedRow = trangChuGUI.table_CNNCC.getSelectedRow();
        if (selectedRow >= 0) {
            maNCC = trangChuGUI.table_CNNCC.getValueAt(selectedRow, 0).toString();
            tenNCC = trangChuGUI.table_CNNCC.getValueAt(selectedRow, 1).toString();
        } 
        if (maNCC.isEmpty()) {
             JOptionPane.showMessageDialog(trangChuGUI, "Vui lòng chọn nhà cung cấp cần ngừng hợp tác.", "Chưa chọn NCC", JOptionPane.WARNING_MESSAGE);
             return;
        }

        int confirm = JOptionPane.showConfirmDialog(trangChuGUI, 
            "Bạn có chắc muốn cập nhật trạng thái 'Ngừng hợp tác' cho:\n" + tenNCC + " (Mã: " + maNCC + ")?", 
            "Xác nhận ngừng hợp tác", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        NhaCungCap ncc = dao.getNhaCungCapTheoMa(maNCC); // Gọi DAO
        if (ncc != null) {
            ncc.setTrangThai(false); 
            
            boolean result = dao.updateNhaCungCap(ncc);
            if (result) {
                JOptionPane.showMessageDialog(trangChuGUI, "Đã cập nhật trạng thái 'Ngừng hợp tác' thành công.");
                reloadAllTables(); // Gọi reloadAllTables phiên bản gốc
                lamMoiFormCapNhat();
            } else {
                JOptionPane.showMessageDialog(trangChuGUI, "Cập nhật trạng thái thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(trangChuGUI, "Không tìm thấy nhà cung cấp " + maNCC, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // =========================================================================
    // SECTION: TAB TÌM KIẾM NHÀ CUNG CẤP
    // =========================================================================

    /** Đăng ký sự kiện cho các component trên tab Tìm kiếm NCC */
    private void registerTimKiemNhaCungCapEvents() {
        
        trangChuGUI.btnLamMoi_TKNCC.addActionListener(e -> lamMoiBoLocTimKiem());
        trangChuGUI.btnXemChiTiet_TKNCC.addActionListener(e -> xemChiTietNhaCungCap()); // <--- SẼ GỌI HÀM ĐÃ SỬA

        KeyAdapter filterKeyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timKiemNhaCungCap(); // Gọi hàm tìm kiếm phiên bản gốc (gọi DAO)
            }
        };
        
        trangChuGUI.txtMaNCC_TKNCC.addKeyListener(filterKeyListener);
        trangChuGUI.txtTenNCC_TKNCC.addKeyListener(filterKeyListener);
        trangChuGUI.txtSDT_TKNCC.addKeyListener(filterKeyListener);
        trangChuGUI.txtEmail_TKNCC.addKeyListener(filterKeyListener);
        trangChuGUI.txtDiaChi_TKNCC.addKeyListener(filterKeyListener);
        trangChuGUI.txtGhiChu_TKNCC.addKeyListener(filterKeyListener);
        
        trangChuGUI.cboTrangThai_TKNCC.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                timKiemNhaCungCap(); // Gọi hàm tìm kiếm phiên bản gốc (gọi DAO)
            }
        });
    }

    /**
     * Hàm lọc bảng (phiên bản gốc - gọi DAO)
     */
    private void timKiemNhaCungCap() { 
        String ma = trangChuGUI.txtMaNCC_TKNCC.getText().trim().toLowerCase();
        String ten = trangChuGUI.txtTenNCC_TKNCC.getText().trim().toLowerCase();
        String sdt = trangChuGUI.txtSDT_TKNCC.getText().trim().toLowerCase();
        String email = trangChuGUI.txtEmail_TKNCC.getText().trim().toLowerCase();
        String diaChi = trangChuGUI.txtDiaChi_TKNCC.getText().trim().toLowerCase();
        String ghiChu = trangChuGUI.txtGhiChu_TKNCC.getText().trim().toLowerCase();
        String trangThai = trangChuGUI.cboTrangThai_TKNCC.getSelectedItem().toString();

        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_TKNCC.getModel();
        model.setRowCount(0);
        
        List<NhaCungCap> ds = dao.getAllNhaCungCap(); // Gọi DAO

        for (NhaCungCap ncc : ds) { 
            
            // Sửa lỗi NullPointerException khi gọi toLowerCase()
            String dbTen = (ncc.getTenNhaCungCap() == null) ? "" : ncc.getTenNhaCungCap().toLowerCase();
            String dbSdt = (ncc.getSoDienThoai() == null) ? "" : ncc.getSoDienThoai().toLowerCase();
            String dbEmail = (ncc.getEmail() == null) ? "" : ncc.getEmail().toLowerCase();
            String dbDiaChi = (ncc.getDiaChi() == null) ? "" : ncc.getDiaChi().toLowerCase();
            String dbGhiChu = (ncc.getGhiChu() == null) ? "" : ncc.getGhiChu().toLowerCase();

            // ... (phần logic match... như cũ) ...
            boolean matchMa = ma.isEmpty() || ncc.getMaNhaCungCap().toLowerCase().contains(ma);
            boolean matchTen = ten.isEmpty() || dbTen.contains(ten);
            boolean matchSdt = sdt.isEmpty() || dbSdt.contains(sdt);
            boolean matchEmail = email.isEmpty() || dbEmail.contains(email);
            boolean matchDiaChi = diaChi.isEmpty() || dbDiaChi.contains(diaChi);
            boolean matchGhiChu = ghiChu.isEmpty() || dbGhiChu.contains(ghiChu);

            boolean matchTrangThai = false;
            if (trangThai.equals("Tất cả")) {
                matchTrangThai = true;
            } else if (trangThai.equals("Đang hợp tác")) {
                matchTrangThai = ncc.isTrangThai();
            } else if (trangThai.equals("Ngừng hợp tác")) {
                matchTrangThai = !ncc.isTrangThai();
            }

            if (matchMa && matchTen && matchSdt && matchEmail && matchDiaChi && matchGhiChu && matchTrangThai) {
                 model.addRow(new Object[]{
                    ncc.getMaNhaCungCap(),
                    ncc.getTenNhaCungCap(),
                    ncc.getSoDienThoai(),
                    ncc.getEmail(),
                    ncc.getDiaChi(),
                    ncc.isTrangThai() ? "Đang hợp tác" : "Ngừng hợp tác", 
                    ncc.getGhiChu()
                });
            }
        }
    }

    /**
     * [ĐÃ SỬA] Xử lý nút "Xem chi tiết" (gọi DAO)
     */
    private void xemChiTietNhaCungCap() {
    	int selectedRow = trangChuGUI.table_TKNCC.getSelectedRow();
    	if (selectedRow < 0) {
    		JOptionPane.showMessageDialog(trangChuGUI, "Vui lòng chọn một nhà cung cấp từ bảng để xem chi tiết.", "Chưa chọn NCC", JOptionPane.WARNING_MESSAGE);
    		return;
    	}

        // 1. Lấy mã NCC từ bảng
    	String maNCC = trangChuGUI.table_TKNCC.getValueAt(selectedRow, 0).toString();
    	
        // 2. Gọi DAO để lấy đối tượng NCC
    	NhaCungCap nccCanXem = dao.getNhaCungCapTheoMa(maNCC); 
     
    	if (nccCanXem != null) {
            // 3. Tạo dialog mới
    		Dialog_ChiTietNCC dialog = new Dialog_ChiTietNCC(trangChuGUI, nccCanXem);
            // 4. Hiển thị dialog
    		dialog.setVisible(true);
         
    	} else {
    		JOptionPane.showMessageDialog(trangChuGUI, "Không tìm thấy dữ liệu chi tiết cho " + maNCC, "Lỗi", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    /** Xử lý nút "Làm mới bộ lọc" (phiên bản gốc) */
    private void lamMoiBoLocTimKiem() {
        // Xóa trắng các ô lọc
        trangChuGUI.txtMaNCC_TKNCC.setText("");
        trangChuGUI.txtTenNCC_TKNCC.setText("");
        trangChuGUI.txtSDT_TKNCC.setText("");
        trangChuGUI.txtEmail_TKNCC.setText("");
        trangChuGUI.txtDiaChi_TKNCC.setText("");
        trangChuGUI.txtGhiChu_TKNCC.setText("");
        trangChuGUI.cboTrangThai_TKNCC.setSelectedIndex(0);
        
        // Tải lại toàn bộ dữ liệu (gọi DAO)
        loadDataToTableTimKiemNCC(); 
    }
    
    // Đã xóa hàm tiện ích timNCCTheoMaTuDanhSach() vì không cần nữa
}