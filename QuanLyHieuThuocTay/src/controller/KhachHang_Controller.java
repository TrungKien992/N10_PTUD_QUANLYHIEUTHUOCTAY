package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import dao.khachHang_DAO;
import entity.KhachHang;
import gui.TrangChu_GUI;


public class KhachHang_Controller implements ActionListener {

    private TrangChu_GUI trangChuGUI;
    private khachHang_DAO khDAO;

    // biến tạm để lưu thông tin gốc của khách hàng đang chọn
    private KhachHang khachHangGoc = null;

    public KhachHang_Controller(TrangChu_GUI trangChuGUI) {
        this.trangChuGUI = trangChuGUI;
        khDAO = new khachHang_DAO();

        // hiển thị danh sách khách hàng khi khởi tạo controller
        hienThiDanhSachKhachHang();

        // gán sự kiện cho các nút
        trangChuGUI.btnLammoi_CNKH.addActionListener(this);
        trangChuGUI.btn_kh_Lammoi.addActionListener(this);
        trangChuGUI.btn_cnkh_CapNhat.addActionListener(this);
        trangChuGUI.btn_cnkh_Khoiphuc.addActionListener(this); // thêm nút khôi phục

        
        // gán sự kiện click cho bảng
        trangChuGUI.table_CapNhatKH.addMouseListener(new MouseAdapter() {
        	
            @Override
            public void mouseClicked(MouseEvent e) {
                hienThiThongTinLenTextField();
            } 
        });
        
        
        // sự kiện tìm kiếm theo mã hoặc tên khách hàng
        trangChuGUI.txtMKH_TK.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timKiemKhachHang();
            }
        });

        trangChuGUI.txtTenKH_TK.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timKiemKhachHang();
            }
        });

        trangChuGUI.txt_kh_MaKH.addKeyListener(new KeyAdapter() {
        	@Override
            public void keyReleased(KeyEvent e) {
                timKiemKhachHang_TK();
            }
		});
        
        trangChuGUI.txt_kh_TenKH.addKeyListener(new KeyAdapter() {
        	@Override
            public void keyReleased(KeyEvent e) {
        		timKiemKhachHang_TK();
            }
		});
        
        trangChuGUI.txt_kh_SDT.addKeyListener(new KeyAdapter() {
        	@Override
            public void keyReleased(KeyEvent e) {
        		timKiemKhachHang_TK();
            }
		});
        
        trangChuGUI.txt_kh_dc.addKeyListener(new KeyAdapter() {
        	@Override
            public void keyReleased(KeyEvent e) {
        		timKiemKhachHang_TK();
            }
		});
    }


    /**
     * hiển thị danh sách khách hàng lên JTable
     */
    public void hienThiDanhSachKhachHang() {
        List<KhachHang> dsKH = khDAO.getAllKhachHang();
        DefaultTableModel modelCN = (DefaultTableModel) trangChuGUI.table_CapNhatKH.getModel();
        DefaultTableModel modelTK = (DefaultTableModel) trangChuGUI.table_tkkh.getModel();

        modelCN.setRowCount(0);
        modelTK.setRowCount(0);
        for (KhachHang kh : dsKH) {
            // *** SỬA Ở ĐÂY: Đổi vị trí getDiaChi() và getSoDienThoai() ***
            Object[] row = { kh.getMaKH(), kh.getTenKH(), kh.getDiaChi(), kh.getSoDienThoai() };
            modelCN.addRow(row);
            modelTK.addRow(row);
        }
    }

    /**
     * hiển thị thông tin khách hàng lên textfield khi click vào bảng
     */
    private void hienThiThongTinLenTextField() {
        int row = trangChuGUI.table_CapNhatKH.getSelectedRow();
        if (row >= 0) {
            String maKH = trangChuGUI.table_CapNhatKH.getValueAt(row, 0).toString();
            String tenKH = trangChuGUI.table_CapNhatKH.getValueAt(row, 1).toString();
            // *** SỬA Ở ĐÂY: Lấy đúng cột và gán đúng ô text field ***
            String diaChi = trangChuGUI.table_CapNhatKH.getValueAt(row, 2).toString(); // Cột 2 là Địa chỉ
            String sdt = trangChuGUI.table_CapNhatKH.getValueAt(row, 3).toString();    // Cột 3 là SĐT

            trangChuGUI.txt_cnkh_MaKh.setText(maKH);
            trangChuGUI.txt_cnkh_tenkh.setText(tenKH);
            trangChuGUI.txt_cnkh_dc.setText(diaChi); // Gán địa chỉ vào ô địa chỉ
            trangChuGUI.txt_cnkh_SDt.setText(sdt);   // Gán SĐT vào ô SĐT

            // Lưu thông tin gốc (sửa thứ tự cho đúng constructor hoặc setter)
            khachHangGoc = new KhachHang(maKH, tenKH, sdt, diaChi); // Giả sử constructor là (ma, ten, sdt, diaChi)
            // Hoặc nếu constructor là (ma, ten, diaChi, sdt):
            // khachHangGoc = new KhachHang(maKH, tenKH, diaChi, sdt);
        }
    }

    /**
     * làm mới form (xóa nội dung textfield)
     */
    private void lamMoiForm() {
    	// xóa nội dung tìm kiếm
        trangChuGUI.txtMKH_TK.setText("");
        trangChuGUI.txtTenKH_TK.setText("");
        hienThiDanhSachKhachHang();
    }
    
    private void lammoitimkiemkhachhang() {
    	// xóa nội dung tìm kiếm
        trangChuGUI.txt_kh_MaKH.setText("");
        trangChuGUI.txt_kh_TenKH.setText("");
        trangChuGUI.txt_kh_SDT.setText("");
        trangChuGUI.txt_kh_dc.setText("");
        hienThiDanhSachKhachHang();
    }

    /**
     * khôi phục thông tin gốc của khách hàng đang chọn
     */
    private void khoiPhucThongTin() {
        if (khachHangGoc == null) {
            JOptionPane.showMessageDialog(null, "Không có thông tin để khôi phục!");
            return;
        }

        trangChuGUI.txt_cnkh_MaKh.setText(khachHangGoc.getMaKH());
        trangChuGUI.txt_cnkh_tenkh.setText(khachHangGoc.getTenKH());
        // *** SỬA Ở ĐÂY: Gán đúng giá trị vào đúng ô text field ***
        trangChuGUI.txt_cnkh_dc.setText(khachHangGoc.getDiaChi());
        trangChuGUI.txt_cnkh_SDt.setText(khachHangGoc.getSoDienThoai());
    }

    /**
     * kiểm tra hợp lệ dữ liệu khách hàng
     */
    private boolean validData(String tenKH, String sdt, String diaChi) {
        if (tenKH.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin khách hàng!");
            return false;
        }

        // họ tên tiếng Việt, có dấu cách, không ký tự số hoặc đặc biệt
        String regexTen = "^[A-ZÀ-ỴĐ][a-zà-ỹđ]+( [A-ZÀ-ỴĐ][a-zà-ỹđ]+)*$";
        if (!tenKH.matches(regexTen)) {
            JOptionPane.showMessageDialog(null,
                "Họ tên không hợp lệ!\n- Viết hoa chữ cái đầu mỗi từ\n- Không chứa số hoặc ký tự đặc biệt.");
            return false;
        }

        // số điện thoại gồm 10 chữ số
        if (!sdt.matches("^\\d{10}$")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải gồm đúng 10 chữ số!");
            return false;
        }

        // địa chỉ không có ký tự đặc biệt
        String regexDiaChi = "^[a-zA-Z0-9À-Ỵà-ỹĐđ\\s,\\.]+$";
        if (!diaChi.matches(regexDiaChi)) {
            JOptionPane.showMessageDialog(null, "Địa chỉ không được chứa ký tự đặc biệt!");
            return false;
        }

        return true;
    }
    
    /**
     * Tìm kiếm khách hàng theo mã hoặc tên và chọn các dòng tương ứng
     */
    private void timKiemKhachHang() {
        String maTK = trangChuGUI.txtMKH_TK.getText().trim().toLowerCase();
        String tenTK = trangChuGUI.txtTenKH_TK.getText().trim().toLowerCase();

        List<KhachHang> dsKH = khDAO.getAllKhachHang();
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_CapNhatKH.getModel();
        model.setRowCount(0); // xóa toàn bộ bảng

        for (KhachHang kh : dsKH) {
            String ma = kh.getMaKH().toLowerCase();
            String ten = kh.getTenKH().toLowerCase();

            // nếu trùng khớp điều kiện
            if ((maTK.isEmpty() || ma.contains(maTK)) &&
                (tenTK.isEmpty() || ten.contains(tenTK))) {
            	Object[] row = { kh.getMaKH(), kh.getTenKH(), kh.getDiaChi(), kh.getSoDienThoai() };
                model.addRow(row);
            }
        }

        // nếu cả hai ô trống, hiển thị lại toàn bộ danh sách
        if (maTK.isEmpty() && tenTK.isEmpty()) {
            hienThiDanhSachKhachHang();
        }
    }
    
    private void timKiemKhachHang_TK() {
        String maTK = trangChuGUI.txt_kh_MaKH.getText().trim().toLowerCase();
        String tenTK = trangChuGUI.txt_kh_TenKH.getText().trim().toLowerCase();
        String sdtTK = trangChuGUI.txt_kh_SDT.getText().trim().toLowerCase();
        String dcTK = trangChuGUI.txt_kh_dc.getText().trim().toLowerCase();

        List<KhachHang> dsKH = khDAO.getAllKhachHang();
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_tkkh.getModel();
        model.setRowCount(0); // xóa toàn bộ bảng

        for (KhachHang kh : dsKH) {
            String ma = kh.getMaKH().toLowerCase();
            String ten = kh.getTenKH().toLowerCase();
            String sdt = kh.getSoDienThoai().toLowerCase();
            String diachi = kh.getDiaChi().toLowerCase();

            if ((maTK.isEmpty() || ma.contains(maTK)) &&
                (tenTK.isEmpty() || ten.contains(tenTK)) &&
                (sdtTK.isEmpty() || sdt.contains(sdtTK)) &&
                (dcTK.isEmpty() || diachi.contains(dcTK))) {
            	Object[] row = { kh.getMaKH(), kh.getTenKH(), kh.getDiaChi(), kh.getSoDienThoai() };
                model.addRow(row);
            }
        }

        if (maTK.isEmpty() && tenTK.isEmpty() && sdtTK.isEmpty() && dcTK.isEmpty()) {
            hienThiDanhSachKhachHang();
        }
    }



    /**
     * cập nhật thông tin khách hàng
     */
    private void capNhatKhachHang() {
        String maKH = trangChuGUI.txt_cnkh_MaKh.getText().trim();
        String tenKH = trangChuGUI.txt_cnkh_tenkh.getText().trim();
        // *** SỬA Ở ĐÂY: Lấy đúng giá trị từ đúng ô text field ***
        String diaChi = trangChuGUI.txt_cnkh_dc.getText().trim();
        String sdt = trangChuGUI.txt_cnkh_SDt.getText().trim();


        if (!validData(tenKH, sdt, diaChi)) { // Đảm bảo validData kiểm tra đúng thứ tự
            return;
        }

        int row = trangChuGUI.table_CapNhatKH.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần cập nhật trong bảng!");
            return;
        }

        String oldTen = trangChuGUI.table_CapNhatKH.getValueAt(row, 1).toString();
        // *** SỬA Ở ĐÂY: Lấy đúng giá trị cũ từ đúng cột ***
        String oldDiaChi = trangChuGUI.table_CapNhatKH.getValueAt(row, 2).toString();
        String oldSDT = trangChuGUI.table_CapNhatKH.getValueAt(row, 3).toString();


        if (tenKH.equals(oldTen) && sdt.equals(oldSDT) && diaChi.equals(oldDiaChi)) {
            JOptionPane.showMessageDialog(null, "Không có thay đổi nào để cập nhật!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc chắn muốn cập nhật thông tin khách hàng này không?",
                "Xác nhận cập nhật", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // *** SỬA Ở ĐÂY: Đảm bảo thứ tự đúng với constructor hoặc dùng setter ***
            KhachHang kh = new KhachHang(maKH, tenKH, sdt, diaChi); // Giả sử constructor (ma, ten, sdt, diaChi)
            // Hoặc nếu constructor là (ma, ten, diaChi, sdt):
            // KhachHang kh = new KhachHang(maKH, tenKH, diaChi, sdt);
            boolean result = khDAO.updateKhachHang(kh);

            if (result) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                hienThiDanhSachKhachHang();
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật thất bại! Vui lòng thử lại.");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(trangChuGUI.btnLammoi_CNKH)) {
                lamMoiForm();
        } else if (o.equals(trangChuGUI.btn_cnkh_CapNhat)) {
                capNhatKhachHang();
        } else if (o.equals(trangChuGUI.btn_cnkh_Khoiphuc)) {
                khoiPhucThongTin();
        } else if(o.equals(trangChuGUI.btn_kh_Lammoi)) {
        	lammoitimkiemkhachhang();
        }

    }

}
