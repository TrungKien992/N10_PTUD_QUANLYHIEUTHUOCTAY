package controller;

import dao.nhaCungCap_DAO;
import entity.NhaCungCap;
import gui.TrangChu_GUI; // hoặc gui.NhaCungCap_GUI nếu bạn đặt ở form khác

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NhaCungCap_Controller {

    private TrangChu_GUI trangChuGUI;  // hoặc NhaCungCap_GUI
    private nhaCungCap_DAO dao;

    public NhaCungCap_Controller(TrangChu_GUI trangChuGUI) {
        this.trangChuGUI = trangChuGUI;
        this.dao = new nhaCungCap_DAO();

        loadDataToTableThemNCC();
    }

    /**
     * Hàm lấy dữ liệu nhà cung cấp và hiển thị lên bảng table_ThemNCC
     */
    public void loadDataToTableThemNCC() {
        List<NhaCungCap> ds = dao.getAllNhaCungCap();
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_ThemNCC.getModel();
        model.setRowCount(0); // xóa dữ liệu cũ

        for (NhaCungCap ncc : ds) {
            model.addRow(new Object[]{
                    ncc.getMaNhaCungCap(),
                    ncc.getTenNhaCungCap(),
                    ncc.getSoDienThoai(),
                    ncc.getEmail(),
                    ncc.getDiaChi(),
                    ncc.isTrangThai() ? "Hoạt động" : "Ngừng",
                    ncc.getGhiChu()
            });
        }
    }
}
