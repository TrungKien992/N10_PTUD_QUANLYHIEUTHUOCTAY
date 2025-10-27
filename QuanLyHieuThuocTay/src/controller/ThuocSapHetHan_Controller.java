package controller;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import dao.thuoc_DAO;
import entity.Thuoc;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.data.category.DefaultCategoryDataset;
import java.time.temporal.ChronoUnit;

public class ThuocSapHetHan_Controller {
    
    private thuoc_DAO thuocDAO;

    public ThuocSapHetHan_Controller() {
        thuocDAO = new thuoc_DAO();
    }

    /**
     * Lấy danh sách thuốc sắp hết hạn (<= 30 ngày)
     */
    public List<Thuoc> getDanhSachThuocSapHetHan() {
        List<Thuoc> dsThuoc = thuocDAO.getAllThuoc(); // lấy toàn bộ thuốc
        LocalDate today = LocalDate.now();

        return dsThuoc.stream()
                .filter(t -> t.getHanSuDung() != null)
                .filter(t -> !t.getHanSuDung().isBefore(today)) // chưa hết hạn
                .filter(t -> t.getHanSuDung().isBefore(today.plusDays(30))) // còn <= 30 ngày
                .collect(Collectors.toList());
    }
    public void exportToExcel(List<Thuoc> ds, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Thuốc sắp hết hạn");

            String[] headers = {
                "Mã Thuốc", "Tên Thuốc", "Số Lượng", "Giá Nhập", "Giá Bán", "Đơn Vị Tính",
                "Nhà Cung Cấp", "Hạn Sử Dụng", "Tên Kệ Thuốc", "Thành Phần"
            };

            // Dòng tiêu đề
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Ghi dữ liệu
            int rowNum = 1;
            for (Thuoc t : ds) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(t.getMaThuoc());
                row.createCell(1).setCellValue(t.getTenThuoc());
                row.createCell(2).setCellValue(t.getSoLuong());
                row.createCell(3).setCellValue(t.getGiaNhap());
                row.createCell(4).setCellValue(t.getGiaBan());
                row.createCell(5).setCellValue(t.getDonViTinh());
                row.createCell(6).setCellValue(t.getNhaCungCap() != null ? t.getNhaCungCap().getTenNhaCungCap() : "");
                row.createCell(7).setCellValue(t.getHanSuDung() != null ? t.getHanSuDung().toString() : "");
                row.createCell(8).setCellValue(t.getKeThuoc() != null ? t.getKeThuoc().getLoaiKe() : "");
                row.createCell(9).setCellValue(t.getThanhPhan());
            }

            // Căn chỉnh độ rộng cột
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream out = new FileOutputStream(filePath)) {
                workbook.write(out);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất file Excel: " + e.getMessage());
        }
    }
    public long tinhSoNgayConLai(LocalDate hanSuDung) {
        return ChronoUnit.DAYS.between(LocalDate.now(), hanSuDung);
    }
    public DefaultCategoryDataset taoDatasetBieuDo() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Thuoc> ds = getDanhSachThuocSapHetHan();
        for (Thuoc t : ds) {
            if (t.getHanSuDung() != null) {
                long ngayConLai = tinhSoNgayConLai(t.getHanSuDung());
                dataset.addValue(ngayConLai, "Số ngày còn lại", t.getTenThuoc());
            }
        }
        return dataset;
    }
}
