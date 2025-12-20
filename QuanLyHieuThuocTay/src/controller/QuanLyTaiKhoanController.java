package controller;

import dao.taiKhoan_DAO;
import entity.TaiKhoan;
import gui.TaiKhoan_GUI;
import gui.ThemNhanVienSauKhiTaoTK_GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

public class QuanLyTaiKhoanController {
    private JPanel view; 
    private JTable table;
    private taiKhoan_DAO dao;

    public QuanLyTaiKhoanController(JPanel view, JTable table) {
        this.view = view;
        this.table = table;
        this.dao = new taiKhoan_DAO();
        loadData(); 
    }

    public void loadData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        List<TaiKhoan> list = dao.getAllTaiKhoan(); // Hàm này đã sửa SQL ở DAO
        for (TaiKhoan tk : list) {
            String trangThaiText = tk.isTrangThai() ? "Hoạt động" : "Ngừng hoạt động";
            model.addRow(new Object[]{
                tk.getMaTK(), 
                tk.getTenTK(), 
                tk.getQuyenHan(),
                trangThaiText 
            });
        }
    }

    // YÊU CẦU 1: Cập nhật tài khoản hiện Pass thật
    public void capNhatTaiKhoan(JFrame parentFrame) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn tài khoản cần sửa!");
            return;
        }
        String maTK = table.getValueAt(row, 0).toString();
        
        // QUAN TRỌNG: Gọi hàm mới getTaiKhoanCanSua để lấy Pass thật
        TaiKhoan tkCu = dao.getTaiKhoanCanSua(maTK); 

        if (tkCu != null) {
            TaiKhoan_GUI dialog = new TaiKhoan_GUI(parentFrame, "Cập Nhật Tài Khoản", tkCu, null);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                TaiKhoan tkMoi = dialog.getTaiKhoan();
                tkMoi.setMaTK(tkCu.getMaTK()); 
                
                if (dao.update(tkMoi)) {
                    JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(view, "Cập nhật thất bại!");
                }
            }
        }
    }

    // ... (Giữ nguyên hàm themTaiKhoan và generateNewNV của phiên bản trước) ...
    public void themTaiKhoan(JFrame parentFrame) {
         // Copy y nguyên logic Transaction em gửi ở câu trả lời trước vào đây
         // ...
         String maMoi = dao.generateNewMaTK();
         TaiKhoan_GUI dialog = new TaiKhoan_GUI(parentFrame, "Thêm Tài Khoản Mới", null, maMoi);
         dialog.setVisible(true);

         if (dialog.isConfirmed()) {
             TaiKhoan tkMoi = dialog.getTaiKhoan();
             if (dao.create(tkMoi)) {
                 try {
                     // Tạo mã NV giả lập hoặc lấy từ DB
                     String maNVMoi = new dao.nhanVien_DAO().generateNewMaNV(); 
                     ThemNhanVienSauKhiTaoTK_GUI nvDialog = new ThemNhanVienSauKhiTaoTK_GUI(parentFrame, tkMoi, maNVMoi);
                     nvDialog.setVisible(true);
                     
                     if (nvDialog.isSuccess()) {
                         loadData();
                         JOptionPane.showMessageDialog(view, "Thêm thành công!");
                     } else {
                         dao.deleteTaiKhoanPermanently(tkMoi.getMaTK());
                         loadData();
                         JOptionPane.showMessageDialog(view, "Đã hủy thao tác, xóa tài khoản tạm.", "Hủy", JOptionPane.WARNING_MESSAGE);
                     }
                 } catch (Exception e) { e.printStackTrace(); }
             } else {
                 JOptionPane.showMessageDialog(view, "Lỗi tạo tài khoản (Trùng tên?)");
             }
         }
    }
}