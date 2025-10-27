//package controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//import javax.swing.JFileChooser;
//import javax.swing.JOptionPane;
//import javax.swing.filechooser.FileNameExtensionFilter;
//import javax.swing.table.DefaultTableModel;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import dao.thuoc_DAO;
//import entity.Thuoc;
//import gui.TrangChu_GUI;
//
///**
// * Controller này quản lý logic cho tab "Thuốc Sắp Hết Hàng"
// * (Tải dữ liệu lên bảng và xử lý xuất file Excel)
// * Đã cập nhật kiểm tra null cho NCC và Kệ Thuốc.
// */
//public class ThuocSapHetHang_Controller implements ActionListener {
//
//    private TrangChu_GUI trangChuGUI;
//    private thuoc_DAO thuoc_DAO;
//    
//    // Định nghĩa ngưỡng sắp hết hàng (ví dụ: <= 20 là sắp hết)
//    // Bạn có thể thay đổi số này tùy theo quy định của nhà thuốc
//    private static final int NGUONG_SAP_HET = 20; 
//
//    public ThuocSapHetHang_Controller(TrangChu_GUI trangChuGUI) {
//        this.trangChuGUI = trangChuGUI;
//        this.thuoc_DAO = new thuoc_DAO();
//
//        // 1. Tải dữ liệu lên bảng khi khởi tạo
//        hienThiThuocSapHetHang();
//
//        // 2. Gán sự kiện cho nút "Xuất File"
//        trangChuGUI.btn_tshhan_xuatfile.addActionListener(this);
//    }
//
//    /**
//     * Tải danh sách các thuốc có số lượng tồn <= NGUONG_SAP_HET lên bảng
//     * ĐÃ SỬA: Bổ sung kiểm tra null
//     */
//    public void hienThiThuocSapHetHang() {
//        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_tshhan.getModel();
//        model.setRowCount(0); // Xóa dữ liệu cũ
//
//        List<Thuoc> dsThuoc = thuoc_DAO.getAllThuoc();
//
//        for (Thuoc t : dsThuoc) {
//            // Kiểm tra điều kiện sắp hết hàng
//            if (t.getSoLuong() <= NGUONG_SAP_HET) {
//                
//                // --- BỔ SUNG KIỂM TRA NULL ---
//                String tenNCC = "N/A"; // Giá trị mặc định nếu null
//                if (t.getNhaCungCap() != null) {
//                    tenNCC = t.getNhaCungCap().getTenNhaCungCap();
//                }
//                
//                String tenKe = "N/A"; // Giá trị mặc định nếu null
//                if (t.getKeThuoc() != null) {
//                    tenKe = t.getKeThuoc().getLoaiKe();
//                }
//                // --- KẾT THÚC BỔ SUNG ---
//
//                model.addRow(new Object[] {
//                    t.getMaThuoc(),
//                    t.getTenThuoc(),
//                    t.getSoLuong(),
//                    t.getDonViTinh(),
//                    tenNCC, // Dùng biến an toàn
//                    tenKe   // Dùng biến an toàn
//                });
//            }
//        }
//    }
//
//    /**
//     * Xử lý sự kiện khi nhấn nút (chỉ có nút Xuất File)
//     */
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object o = e.getSource();
//
//        if (o.equals(trangChuGUI.btn_tshhan_xuatfile)) {
//            xuLyXuatFile();
//        }
//    }
//
//    /**
//     * Logic xử lý khi nhấn nút "Xuất File"
//     */
//    private void xuLyXuatFile() {
//        // Kiểm tra xem bảng có dữ liệu không
//        if (trangChuGUI.table_tshhan.getRowCount() == 0) {
//            JOptionPane.showMessageDialog(trangChuGUI, "Không có dữ liệu để xuất file.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
//
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Lưu file Excel");
//        // Đặt tên file mặc định
//        fileChooser.setSelectedFile(new File("DanhSachThuocSapHetHang.xlsx")); 
//        // Lọc chỉ file .xlsx
//        fileChooser.setFileFilter(new FileNameExtensionFilter("File Excel (*.xlsx)", "xlsx"));
//
//        int userSelection = fileChooser.showSaveDialog(trangChuGUI);
//
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File fileToSave = fileChooser.getSelectedFile();
//            
//            // Đảm bảo file có đuôi .xlsx
//            String filePath = fileToSave.getAbsolutePath();
//            if (!filePath.endsWith(".xlsx")) {
//                fileToSave = new File(filePath + ".xlsx");
//            }
//
//            xuatFileExcel(fileToSave);
//        }
//    }
//
//    /**
//     * Ghi dữ liệu từ table_tshhan ra file Excel
//     * @param file File để lưu
//     */
//    private void xuatFileExcel(File file) {
//        // Sử dụng XSSFWorkbook cho file .xlsx
//        try (Workbook workbook = new XSSFWorkbook(); 
//             FileOutputStream fos = new FileOutputStream(file)) {
//
//            Sheet sheet = workbook.createSheet("ThuocSapHetHang");
//            DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_tshhan.getModel();
//
//            // 1. Tạo hàng tiêu đề (Header)
//            Row headerRow = sheet.createRow(0);
//            for (int i = 0; i < model.getColumnCount(); i++) {
//                Cell cell = headerRow.createCell(i);
//                cell.setCellValue(model.getColumnName(i));
//                // (Bạn có thể thêm style cho header ở đây nếu muốn)
//            }
//
//            // 2. Thêm dữ liệu (Data rows)
//            for (int i = 0; i < model.getRowCount(); i++) {
//                Row dataRow = sheet.createRow(i + 1); // +1 vì hàng 0 là header
//                for (int j = 0; j < model.getColumnCount(); j++) {
//                    Object value = model.getValueAt(i, j);
//                    Cell cell = dataRow.createCell(j);
//
//                    // Xử lý kiểu dữ liệu (để số lượng lưu vào Excel là SỐ)
//                    if (value instanceof Number) {
//                        cell.setCellValue(((Number) value).doubleValue());
//                    } else {
//                        cell.setCellValue(value != null ? value.toString() : "");
//                    }
//                }
//            }
//
//            // (Tùy chọn: Tự động điều chỉnh độ rộng cột)
//            for (int i = 0; i < model.getColumnCount(); i++) {
//                sheet.autoSizeColumn(i);
//            }
//
//            // 3. Ghi file
//            workbook.write(fos);
//            
//            JOptionPane.showMessageDialog(trangChuGUI, "Xuất file thành công!\nĐã lưu tại: " + file.getAbsolutePath(), "Thành công", JOptionPane.INFORMATION_MESSAGE);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(trangChuGUI, "Có lỗi xảy ra khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//}


package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.nhaCungCap_DAO; // Cần import DAO này
import dao.thuoc_DAO;
import entity.NhaCungCap; // Cần import Entity này
import entity.Thuoc;
import gui.ChiTietThuoc_GUI; // Cần import GUI Chi Tiết
import gui.TrangChu_GUI;

/**
 * Controller này quản lý logic cho tab "Thuốc Sắp Hết Hàng"
 * ĐÃ NÂNG CẤP:
 * - Thêm bộ lọc theo ngưỡng (JSpinner)
 * - Thêm tìm kiếm (JTextField)
 * - Thêm nút Làm Mới
 * - Thêm Menu chuột phải (Xem chi tiết, Xem NCC)
 */
public class ThuocSapHetHang_Controller implements ActionListener {

    private TrangChu_GUI trangChuGUI;
    private thuoc_DAO thuoc_DAO;
    private nhaCungCap_DAO ncc_DAO; // Thêm DAO nhà cung cấp

    // Danh sách này dùng để lưu trữ dữ liệu gốc, giúp lọc nhanh không cần gọi CSDL
    private List<Thuoc> dsThuocGoc; 

    public ThuocSapHetHang_Controller(TrangChu_GUI trangChuGUI) {
        this.trangChuGUI = trangChuGUI;
        this.thuoc_DAO = new thuoc_DAO();
        this.ncc_DAO = new nhaCungCap_DAO(); // Khởi tạo DAO
        this.dsThuocGoc = new ArrayList<>(); // Khởi tạo danh sách

        // 1. Tải dữ liệu gốc từ CSDL và hiển thị lên bảng
        taiLaiDuLieuGocVaHienThi();

        // 2. Gán sự kiện cho tất cả các nút
        trangChuGUI.btn_tshhan_xuatfile.addActionListener(this);
        trangChuGUI.btn_tshhan_loc.addActionListener(this);
        trangChuGUI.btn_tshhan_lammoi.addActionListener(this);
        
        // Gán sự kiện cho menu chuột phải
        trangChuGUI.item_tshhan_xemchitiet.addActionListener(this);
        trangChuGUI.item_tshhan_xemncc.addActionListener(this);

        // 3. Gán sự kiện cho ô tìm kiếm (lọc ngay khi gõ)
        trangChuGUI.text_tshhan_timkiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Mỗi khi gõ phím, gọi hàm lọc và hiển thị lại
                locVaHienThiLenBang();
            }
        });
    }

    /**
     * Lấy dữ liệu mới nhất từ CSDL, lưu vào dsThuocGoc và hiển thị lên bảng
     */
    private void taiLaiDuLieuGocVaHienThi() {
        // Lấy dữ liệu mới nhất
        this.dsThuocGoc = thuoc_DAO.getAllThuoc();
        // Hiển thị (với bộ lọc hiện tại)
        locVaHienThiLenBang();
    }
    
    /**
     * Hàm chính: Lọc danh sách dsThuocGoc dựa trên các tiêu chí
     * và cập nhật dữ liệu lên table_tshhan
     */
    private void locVaHienThiLenBang() {
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_tshhan.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        // Lấy các giá trị từ bộ lọc
        int nguongTon = (Integer) trangChuGUI.spinner_tshhan_nguong.getValue();
        String tuKhoa = trangChuGUI.text_tshhan_timkiem.getText().trim().toLowerCase();

        for (Thuoc t : dsThuocGoc) { // Lọc trên danh sách đã tải
            
            // 1. Kiểm tra điều kiện ngưỡng
            boolean matchNguong = t.getSoLuong() <= nguongTon;
            
            // 2. Kiểm tra điều kiện tìm kiếm (theo mã hoặc tên)
            boolean matchTimKiem = tuKhoa.isEmpty() || 
                                   t.getMaThuoc().toLowerCase().contains(tuKhoa) ||
                                   t.getTenThuoc().toLowerCase().contains(tuKhoa);

            // Nếu thỏa mãn cả 2 điều kiện
            if (matchNguong && matchTimKiem) {
                
                String tenNCC = (t.getNhaCungCap() != null) ? t.getNhaCungCap().getTenNhaCungCap() : "N/A";
                String tenKe = (t.getKeThuoc() != null) ? t.getKeThuoc().getLoaiKe() : "N/A";

                model.addRow(new Object[] {
                    t.getMaThuoc(),
                    t.getTenThuoc(),
                    t.getSoLuong(),
                    t.getDonViTinh(),
                    tenNCC,
                    tenKe
                });
            }
        }
    }

    /**
     * Xử lý sự kiện khi nhấn nút
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(trangChuGUI.btn_tshhan_xuatfile)) {
            xuLyXuatFile();
        } 
        else if (o.equals(trangChuGUI.btn_tshhan_loc)) {
            // Khi nhấn "Lọc", chỉ cần gọi lại hàm lọc và hiển thị
            locVaHienThiLenBang();
        } 
        else if (o.equals(trangChuGUI.btn_tshhan_lammoi)) {
            xuLyLamMoi();
        }
        else if (o.equals(trangChuGUI.item_tshhan_xemchitiet)) {
            xuLyXemChiTiet();
        }
        else if (o.equals(trangChuGUI.item_tshhan_xemncc)) {
            xuLyXemNCC();
        }
    }

    /**
     * Xử lý nút Làm Mới
     */
    private void xuLyLamMoi() {
        // 1. Reset các bộ lọc về mặc định
        trangChuGUI.text_tshhan_timkiem.setText("");
        trangChuGUI.spinner_tshhan_nguong.setValue(20); // Set về giá trị mặc định
        
        // 2. Tải lại dữ liệu mới từ CSDL và hiển thị
        taiLaiDuLieuGocVaHienThi();
    }

    /**
     * Xử lý menu chuột phải "Xem chi tiết thuốc"
     */
    private void xuLyXemChiTiet() {
        int viewRow = trangChuGUI.table_tshhan.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(trangChuGUI, "Vui lòng chọn một thuốc để xem chi tiết.");
            return;
        }
        
        // Chuyển đổi index của view sang model (quan trọng vì bảng có sắp xếp)
        int modelRow = trangChuGUI.table_tshhan.convertRowIndexToModel(viewRow);
        String maThuoc = (String) trangChuGUI.table_tshhan.getModel().getValueAt(modelRow, 0);

        Thuoc t = thuoc_DAO.getThuocTheoMa(maThuoc);
        if (t != null) {
            // Lấy JFrame cha một cách an toàn
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(trangChuGUI);
            ChiTietThuoc_GUI chitiet = new ChiTietThuoc_GUI(parentFrame);
            chitiet.loadThuoc(t);
            chitiet.setVisible(true);
        }
    }
    
    /**
     * Xử lý menu chuột phải "Xem thông tin nhà cung cấp"
     */
    private void xuLyXemNCC() {
        int viewRow = trangChuGUI.table_tshhan.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(trangChuGUI, "Vui lòng chọn một thuốc để xem nhà cung cấp.");
            return;
        }

        int modelRow = trangChuGUI.table_tshhan.convertRowIndexToModel(viewRow);
        String maThuoc = (String) trangChuGUI.table_tshhan.getModel().getValueAt(modelRow, 0);

        // Lấy Thuoc từ CSDL để đảm bảo có maNCC
        Thuoc t = thuoc_DAO.getThuocTheoMa(maThuoc); 
        if (t == null || t.getNhaCungCap() == null) {
            JOptionPane.showMessageDialog(trangChuGUI, "Không tìm thấy thông tin nhà cung cấp cho thuốc này.");
            return;
        }

        // Dùng maNCC để lấy đầy đủ thông tin NCC
        String maNCC = t.getNhaCungCap().getMaNhaCungCap();
        NhaCungCap ncc = ncc_DAO.getNhaCungCapTheoMa(maNCC);

        if (ncc == null) {
            JOptionPane.showMessageDialog(trangChuGUI, "Không tìm thấy thông tin chi tiết cho NCC có mã: " + maNCC);
            return;
        }

        // Hiển thị thông tin NCC bằng JOptionPane
        String message = String.format(
            "<html><b>THÔNG TIN NHÀ CUNG CẤP</b><br>" +
            "<hr>" +
            "<b>Tên:</b> %s<br>" +
            "<b>SĐT:</b> %s<br>" +
            "<b>Email:</b> %s<br>" +
            "<b>Địa chỉ:</b> %s</html>",
            ncc.getTenNhaCungCap(),
            ncc.getSoDienThoai().isEmpty() ? "Chưa cập nhật" : ncc.getSoDienThoai(),
            ncc.getEmail().isEmpty() ? "Chưa cập nhật" : ncc.getEmail(),
            ncc.getDiaChi().isEmpty() ? "Chưa cập nhật" : ncc.getDiaChi()
        );
        
        JOptionPane.showMessageDialog(trangChuGUI, message, "Thông tin Nhà Cung Cấp", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Logic xử lý khi nhấn nút "Xuất File"
     * (Không thay đổi, hoạt động tốt vì nó xuất từ model của bảng)
     */
    private void xuLyXuatFile() {
        if (trangChuGUI.table_tshhan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(trangChuGUI, "Không có dữ liệu để xuất file.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu file Excel");
        fileChooser.setSelectedFile(new File("DanhSachThuocSapHetHang.xlsx")); 
        fileChooser.setFileFilter(new FileNameExtensionFilter("File Excel (*.xlsx)", "xlsx"));

        int userSelection = fileChooser.showSaveDialog(trangChuGUI);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.endsWith(".xlsx")) {
                fileToSave = new File(filePath + ".xlsx");
            }

            xuatFileExcel(fileToSave);
        }
    }

    /**
     * Ghi dữ liệu từ table_tshhan ra file Excel
     * (Không thay đổi)
     * @param file File để lưu
     */
    private void xuatFileExcel(File file) {
        try (Workbook workbook = new XSSFWorkbook(); 
             FileOutputStream fos = new FileOutputStream(file)) {

            Sheet sheet = workbook.createSheet("ThuocSapHetHang");
            DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_tshhan.getModel();

            // 1. Tạo hàng tiêu đề
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(model.getColumnName(i));
            }

            // 2. Thêm dữ liệu
            for (int i = 0; i < model.getRowCount(); i++) {
                Row dataRow = sheet.createRow(i + 1); 
                for (int j = 0; j < model.getColumnCount(); j++) {
                    // Lấy giá trị từ model (đã được lọc)
                    Object value = model.getValueAt(i, j); 
                    Cell cell = dataRow.createCell(j);

                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            // 3. Tự động điều chỉnh độ rộng cột
            for (int i = 0; i < model.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }

            // 4. Ghi file
            workbook.write(fos);
            
            JOptionPane.showMessageDialog(trangChuGUI, "Xuất file thành công!\nĐã lưu tại: " + file.getAbsolutePath(), "Thành công", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(trangChuGUI, "Có lỗi xảy ra khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
