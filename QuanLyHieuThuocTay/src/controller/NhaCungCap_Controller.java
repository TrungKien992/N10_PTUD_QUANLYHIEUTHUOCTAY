package controller;

import dao.nhaCungCap_DAO;
import entity.NhaCungCap;
import gui.TrangChu_GUI; // Hoặc GUI chứa các panel của bạn

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

public class NhaCungCap_Controller {

    private TrangChu_GUI trangChuGUI; // GUI chính
    private nhaCungCap_DAO dao;
    
    public NhaCungCap_Controller(TrangChu_GUI trangChuGUI) {
        this.trangChuGUI = trangChuGUI;
        this.dao = new nhaCungCap_DAO();

        // --- TẢI DỮ LIỆU BAN ĐẦU LÊN CẢ 3 BẢNG ---
        reloadAllTables();
        
        // --- ĐĂNG KÝ SỰ KIỆN ---
        registerThemNhaCungCapEvents();
        registerCapNhatNhaCungCapEvents();
        registerTimKiemNhaCungCapEvents();// <-- Đã bao gồm nút Xóa
        
        // Tải mã NCC mới đầu tiên khi khởi động
        loadNewMaNccLenFormThem();
    }

    // =================================================================
    // CÁC HÀM TẢI DỮ LIỆU LÊN BẢNG (Giữ nguyên)
    // =================================================================

    /** Tải dữ liệu lên bảng ở tab "Thêm" */
    public void loadDataToTableThemNCC() {
        List<NhaCungCap> ds = dao.getAllNhaCungCap();
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
    
    /** Tải dữ liệu lên bảng ở tab "Cập nhật" */
    public void loadDataToTableCapNhatNCC() {
        List<NhaCungCap> ds = dao.getAllNhaCungCap();
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
    
    public void loadDataToTableTimKiemNCC() {
    	  List<NhaCungCap> ds = dao.getAllNhaCungCap();
    	  
    	  // !!! CHÚ Ý: Đảm bảo "table_TimKiemNCC" là tên biến JTable của bạn
    	  DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_TKNCC.getModel();
    	  model.setRowCount(0); // Xóa dữ liệu cũ

    	  // 4. Duyệt danh sách và thêm từng dòng vào model
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
    
    /** Hàm tải lại dữ liệu trên tất cả các bảng */
    private void reloadAllTables() {
        loadDataToTableThemNCC();
        loadDataToTableCapNhatNCC();
        loadDataToTableTimKiemNCC();
    }

    // =================================================================
    // HÀM XỬ LÝ SỰ KIỆN TAB "THÊM NHÀ CUNG CẤP" (Giữ nguyên)
    // =================================================================

    /** Đăng ký sự kiện cho các nút ở tab Thêm NCC */
    public void registerThemNhaCungCapEvents() {
        trangChuGUI.btnThem_TNCC.addActionListener(e -> themNhaCungCap());
        trangChuGUI.btnLamMoi_TNCC.addActionListener(e -> lamMoiFormThem());
    }

    /** Xử lý logic Thêm nhà cung cấp mới */
    private void themNhaCungCap() {
        try {
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
                reloadAllTables();
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

    // =================================================================
    // HÀM XỬ LÝ SỰ KIỆN TAB "CẬP NHẬT NHÀ CUNG CẤP" (ĐÃ SỬA)
    // =================================================================

    /**
     * Đăng ký sự kiện cho các component trên tab Cập nhật NCC
     */
    private void registerCapNhatNhaCungCapEvents() {
        // Sự kiện click chuột vào bảng
        trangChuGUI.table_CNNCC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hienThiThongTinLenFormCapNhat();
            }
        });
        
        // Nút "Cập nhật"
        trangChuGUI.btnCapNhat_CNNCC.addActionListener(e -> capNhatNhaCungCap());
        
        // Nút "Khôi phục"
        trangChuGUI.btnKhoiPhuc_CNNCC.addActionListener(e -> khoiPhucFormCapNhat());
        
        // Nút "Làm mới Form"
        trangChuGUI.btnLamMoi_CNNCC.addActionListener(e -> lamMoiFormCapNhat());
        
        // --- PHẦN SỬA ĐỔI ---
        // Nút "Xóa" (Ngừng hợp tác)
        // Đã bỏ comment vì bạn đã thêm nút btnXoa_CNNCC vào GUI
        trangChuGUI.btnXoa_CNNCC.addActionListener(e -> ngungHopTacNhaCungCap());
        // --- HẾT PHẦN SỬA ĐỔI ---
    }

    /**
     * Lấy dữ liệu từ hàng được chọn trong table_CNNCC và hiển thị lên form
     */
    private void hienThiThongTinLenFormCapNhat() {
        int selectedRow = trangChuGUI.table_CNNCC.getSelectedRow();
        if (selectedRow < 0) {
            return; // Không có hàng nào được chọn
        }

        String maNCC = trangChuGUI.table_CNNCC.getValueAt(selectedRow, 0).toString();
        NhaCungCap ncc = dao.getNhaCungCapTheoMa(maNCC);
        
        if (ncc != null) {
            trangChuGUI.txtMaNCC_CNNCC.setText(ncc.getMaNhaCungCap());
            trangChuGUI.txtTenNCC_CNNCC.setText(ncc.getTenNhaCungCap());
            trangChuGUI.txtSDT_CNNCC.setText(ncc.getSoDienThoai());
            trangChuGUI.txtEmail_CNNCC.setText(ncc.getEmail());
            trangChuGUI.txtDiaChi_CNNCC.setText(ncc.getDiaChi());
            
            // Code GUI mới của bạn đã có JTextArea 'txtGhiChu_CNNCC'
            trangChuGUI.txtGhiChu_CNNCC.setText(ncc.getGhiChu()); 
            
            trangChuGUI.cboTrangThai_CNNCC.setSelectedItem(
                ncc.isTrangThai() ? "Đang hợp tác" : "Ngừng hợp tác"
            );
        }
    }

    /**
     * Xử lý logic Cập nhật nhà cung cấp
     */
    private void capNhatNhaCungCap() {
        try {
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
            
            // ‼ LƯU Ý: Đảm bảo bạn đã sửa lỗi vị trí tham số trong DAO.updateNhaCungCap
            boolean result = dao.updateNhaCungCap(ncc);

            if (result) {
                JOptionPane.showMessageDialog(trangChuGUI, "Cập nhật nhà cung cấp thành công!");
                reloadAllTables();
                lamMoiFormCapNhat();
            } else {
                JOptionPane.showMessageDialog(trangChuGUI, "Cập nhật nhà cung cấp thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(trangChuGUI, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Khôi phục dữ liệu gốc của NCC đang chọn trên form
     */
    private void khoiPhucFormCapNhat() {
        String maNCC = trangChuGUI.txtMaNCC_CNNCC.getText().trim();
        if (maNCC.isEmpty()) {
            JOptionPane.showMessageDialog(trangChuGUI, "Chưa có nhà cung cấp nào được chọn để khôi phục.", "Chưa chọn NCC", JOptionPane.WARNING_MESSAGE);
            return;
        }

        NhaCungCap nccGoc = dao.getNhaCungCapTheoMa(maNCC);
        
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

    /**
     * Làm mới (xóa trắng) các trường trên form Cập nhật
     */
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
    
    /**
     * Xử lý nút "Xóa" (Thực chất là cập nhật trạng thái -> Ngừng hợp tác)
     */
    private void ngungHopTacNhaCungCap() {
        int selectedRow = trangChuGUI.table_CNNCC.getSelectedRow();
        String maNCC_Form = trangChuGUI.txtMaNCC_CNNCC.getText().trim();
        
        String maNCC = "";
        String tenNCC = "";

        // Ưu tiên lấy từ hàng đang chọn trong bảng
        if (selectedRow >= 0) {
            maNCC = trangChuGUI.table_CNNCC.getValueAt(selectedRow, 0).toString();
            tenNCC = trangChuGUI.table_CNNCC.getValueAt(selectedRow, 1).toString();
        } 
        // Nếu không có hàng nào được chọn, thử lấy từ form
        else if (!maNCC_Form.isEmpty()) {
            maNCC = maNCC_Form;
            tenNCC = trangChuGUI.txtTenNCC_CNNCC.getText().trim(); // Lấy tên từ form
        } 
        // Nếu cả hai đều rỗng
        else {
            JOptionPane.showMessageDialog(trangChuGUI, "Vui lòng chọn nhà cung cấp cần ngừng hợp tác (từ bảng hoặc form).", "Chưa chọn NCC", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(trangChuGUI, 
            "Bạn có chắc muốn cập nhật trạng thái 'Ngừng hợp tác' cho:\n" + tenNCC + " (Mã: " + maNCC + ")?", 
            "Xác nhận ngừng hợp tác", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        NhaCungCap ncc = dao.getNhaCungCapTheoMa(maNCC);
        if (ncc != null) {
            ncc.setTrangThai(false); // Cập nhật thành "Ngừng hợp tác"
            
            boolean result = dao.updateNhaCungCap(ncc);
            if (result) {
                JOptionPane.showMessageDialog(trangChuGUI, "Đã cập nhật trạng thái 'Ngừng hợp tác' thành công.");
                reloadAllTables();
                lamMoiFormCapNhat();
            } else {
                JOptionPane.showMessageDialog(trangChuGUI, "Cập nhật trạng thái thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(trangChuGUI, "Không tìm thấy nhà cung cấp " + maNCC, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
 // =================================================================
    // HÀM XỬ LÝ SỰ KIỆN TAB "TÌM KIẾM NHÀ CUNG CẤP" (CODE MỚI)
    // =================================================================

    /**
     * Đăng ký sự kiện cho các component trên tab Tìm kiếm NCC
     */
    private void registerTimKiemNhaCungCapEvents() {
        
        // === 1. ĐĂNG KÝ SỰ KIỆN CHO CÁC NÚT ===
        
        // Nút "Làm mới bộ lọc"
        trangChuGUI.btnLamMoi_TKNCC.addActionListener(e -> lamMoiBoLocTimKiem());
        
        // Nút "Xem chi tiết"
        trangChuGUI.btnXemChiTiet_TKNCC.addActionListener(e -> xemChiTietNhaCungCap());

        // === 2. ĐĂNG KÝ SỰ KIỆN TỰ ĐỘNG LỌC ===
        
        // Tạo một KeyListener chung để lọc khi gõ
        KeyAdapter filterKeyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timKiemNhaCungCap();
            }
        };
        
        // Gán listener cho tất cả các ô text field
        trangChuGUI.txtMaNCC_TKNCC.addKeyListener(filterKeyListener);
        trangChuGUI.txtTenNCC_TKNCC.addKeyListener(filterKeyListener);
        trangChuGUI.txtSDT_TKNCC.addKeyListener(filterKeyListener);
        trangChuGUI.txtEmail_TKNCC.addKeyListener(filterKeyListener);
        trangChuGUI.txtDiaChi_TKNCC.addKeyListener(filterKeyListener);
        trangChuGUI.txtGhiChu_TKNCC.addKeyListener(filterKeyListener); // Đây là JTextArea
        
        // Gán ItemListener cho ComboBox
        trangChuGUI.cboTrangThai_TKNCC.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                timKiemNhaCungCap();
            }
        });
    }

    /**
     * Hàm lọc bảng table_TKNCC dựa trên các tiêu chí trong bộ lọc
     */
    private void timKiemNhaCungCap() {
        // Lấy tất cả giá trị từ bộ lọc, chuyển về chữ thường để tìm kiếm không phân biệt hoa/thường
        String ma = trangChuGUI.txtMaNCC_TKNCC.getText().trim().toLowerCase();
        String ten = trangChuGUI.txtTenNCC_TKNCC.getText().trim().toLowerCase();
        String sdt = trangChuGUI.txtSDT_TKNCC.getText().trim().toLowerCase();
        String email = trangChuGUI.txtEmail_TKNCC.getText().trim().toLowerCase();
        String diaChi = trangChuGUI.txtDiaChi_TKNCC.getText().trim().toLowerCase();
        String ghiChu = trangChuGUI.txtGhiChu_TKNCC.getText().trim().toLowerCase();
        String trangThai = trangChuGUI.cboTrangThai_TKNCC.getSelectedItem().toString();

        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_TKNCC.getModel();
        model.setRowCount(0);
        List<NhaCungCap> ds = dao.getAllNhaCungCap();

        for (NhaCungCap ncc : ds) {
            // Xử lý giá trị null từ database để tránh lỗi NullPointerException
            String dbTen = ncc.getTenNhaCungCap().toLowerCase();
            String dbSdt = ncc.getSoDienThoai().toLowerCase();
            String dbEmail = ncc.getEmail().toLowerCase();
            String dbDiaChi = ncc.getDiaChi().toLowerCase();
            String dbGhiChu = (ncc.getGhiChu() == null) ? "" : ncc.getGhiChu().toLowerCase();

            // 1. Kiểm tra tiêu chí text (dùng contains để tìm kiếm gần đúng)
            boolean matchMa = ma.isEmpty() || ncc.getMaNhaCungCap().toLowerCase().contains(ma);
            boolean matchTen = ten.isEmpty() || dbTen.contains(ten);
            boolean matchSdt = sdt.isEmpty() || dbSdt.contains(sdt);
            boolean matchEmail = email.isEmpty() || dbEmail.contains(email);
            boolean matchDiaChi = diaChi.isEmpty() || dbDiaChi.contains(diaChi);
            boolean matchGhiChu = ghiChu.isEmpty() || dbGhiChu.contains(ghiChu);

            // 2. Kiểm tra tiêu chí ComboBox (trạng thái)
            boolean matchTrangThai = false;
            if (trangThai.equals("Tất cả")) {
                matchTrangThai = true;
            } else if (trangThai.equals("Đang hợp tác")) {
                matchTrangThai = ncc.isTrangThai();
            } else if (trangThai.equals("Ngừng hợp tác")) {
                matchTrangThai = !ncc.isTrangThai();
            }

            // Nếu tất cả tiêu chí đều khớp, thêm vào bảng
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
     * Xử lý nút "Xem chi tiết"
     */
    private void xemChiTietNhaCungCap() {
        int selectedRow = trangChuGUI.table_TKNCC.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(trangChuGUI, "Vui lòng chọn một nhà cung cấp từ bảng để xem chi tiết.", "Chưa chọn NCC", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy mã từ bảng Tìm kiếm
        String maNCC = trangChuGUI.table_TKNCC.getValueAt(selectedRow, 0).toString();
        NhaCungCap ncc = dao.getNhaCungCapTheoMa(maNCC);
        
        if (ncc != null) {
            // 1. Điền thông tin NCC này vào form của tab "Cập nhật"
            trangChuGUI.txtMaNCC_CNNCC.setText(ncc.getMaNhaCungCap());
            trangChuGUI.txtTenNCC_CNNCC.setText(ncc.getTenNhaCungCap());
            trangChuGUI.txtSDT_CNNCC.setText(ncc.getSoDienThoai());
            trangChuGUI.txtEmail_CNNCC.setText(ncc.getEmail());
            trangChuGUI.txtDiaChi_CNNCC.setText(ncc.getDiaChi());
            trangChuGUI.txtGhiChu_CNNCC.setText(ncc.getGhiChu()); 
            trangChuGUI.cboTrangThai_CNNCC.setSelectedItem(
                ncc.isTrangThai() ? "Đang hợp tác" : "Ngừng hợp tác"
            );
            
            // 2. Tự động chuyển sang tab "Cập nhật"
            // Lấy CardLayout của panel 'maincontent' và 'show' card 'capNhatNCC'
            CardLayout cl = (CardLayout) (trangChuGUI.maincontent.getLayout());
            cl.show(trangChuGUI.maincontent, "capNhatNCC");
            
        } else {
            JOptionPane.showMessageDialog(trangChuGUI, "Không tìm thấy dữ liệu chi tiết cho " + maNCC, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Xử lý nút "Làm mới bộ lọc"
     */
    private void lamMoiBoLocTimKiem() {
        // Xóa trắng các ô lọc
        trangChuGUI.txtMaNCC_TKNCC.setText("");
        trangChuGUI.txtTenNCC_TKNCC.setText("");
        trangChuGUI.txtSDT_TKNCC.setText("");
        trangChuGUI.txtEmail_TKNCC.setText("");
        trangChuGUI.txtDiaChi_TKNCC.setText("");
        trangChuGUI.txtGhiChu_TKNCC.setText("");
        trangChuGUI.cboTrangThai_TKNCC.setSelectedIndex(0); // Set về "Tất cả"
        
        // Tải lại toàn bộ dữ liệu lên bảng tìm kiếm
        loadDataToTableTimKiemNCC();
    }
}