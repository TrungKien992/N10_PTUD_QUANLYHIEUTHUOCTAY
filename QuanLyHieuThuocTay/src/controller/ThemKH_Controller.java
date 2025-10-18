package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import dao.khachHang_DAO;
import entity.KhachHang;
import gui.ThemKH_GUI;
import gui.TrangChu_GUI; // ✅ thêm import này

public class ThemKH_Controller implements ActionListener {

	private ThemKH_GUI themKHGUI;
    private TrangChu_GUI trangChuGUI;
    private khachHang_DAO khDAO;

    public ThemKH_Controller(ThemKH_GUI themKHGUI, TrangChu_GUI trangChuGUI) {
        this.themKHGUI = themKHGUI;
        this.trangChuGUI = trangChuGUI;
        khDAO = new khachHang_DAO();

        themKHGUI.btnLamMoi.addActionListener(this);
        themKHGUI.btnThem.addActionListener(this);

        themKHGUI.txtMaKH.setText(khDAO.generateNewMaKH());
    }

    private boolean validData(String tenKH, String sdt, String diaChi) {
        if (tenKH.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin khách hàng!");
            return false;
        }

        String regexTen = "^[A-ZÀ-ỴĐ][a-zà-ỹđ]+( [A-ZÀ-ỴĐ][a-zà-ỹđ]+)*$";
        if (!tenKH.matches(regexTen)) {
            JOptionPane.showMessageDialog(null,
                    "Tên khách hàng không hợp lệ!\n- Viết hoa chữ cái đầu mỗi từ\n- Không chứa số hoặc ký tự đặc biệt.");
            return false;
        }

        if (!sdt.matches("^\\d{10}$")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải gồm đúng 10 chữ số!");
            return false;
        }

        String regexDiaChi = "^[a-zA-Z0-9À-Ỵà-ỹĐđ\\s,\\.]+$";
        if (!diaChi.matches(regexDiaChi)) {
            JOptionPane.showMessageDialog(null, "Địa chỉ không được chứa ký tự đặc biệt!");
            return false;
        }

        return true;
    }

    private void themKhachHang() {
        String maKH = themKHGUI.txtMaKH.getText().trim();
        String tenKH = themKHGUI.txtTenKH.getText().trim();
        String sdt = themKHGUI.txtSDT.getText().trim();
        String diaChi = themKHGUI.txtDiaChi.getText().trim();

        if (!validData(tenKH, sdt, diaChi)) return;

        if (khDAO.getKhachHangTheoSDT(sdt) != null) {
            JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc chắn muốn thêm khách hàng mới này không?",
                "Xác nhận thêm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            KhachHang kh = new KhachHang(maKH, tenKH, diaChi, sdt);
            boolean result = khDAO.themKhachHang(kh);

            if (result) {
                JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
                themKHGUI.txtMaKH.setText(khDAO.generateNewMaKH());
                lamMoiForm();

                // ✅ cập nhật lại bảng trong TrangChu_GUI
                if (trangChuGUI != null) {
                    trangChuGUI.loadDataTableKH();
                }

                // ✅ đóng dialog
                themKHGUI.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại! Vui lòng thử lại.");
            }
        }
    }

    private void lamMoiForm() {
        themKHGUI.txtTenKH.setText("");
        themKHGUI.txtSDT.setText("");
        themKHGUI.txtDiaChi.setText("");
        themKHGUI.txtTenKH.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(themKHGUI.btnLamMoi)) {
            lamMoiForm();
        } else if (o.equals(themKHGUI.btnThem)) {
            themKhachHang();
        }
    }
}
