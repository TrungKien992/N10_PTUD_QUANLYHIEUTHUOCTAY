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
     * Lấy danh sách thuốc hết hạn hoặc sắp hết hạn (<= 30 ngày)
     */
    public List<Thuoc> getDanhSachThuocHetHanVaSapHetHan() {
        List<Thuoc> dsThuoc = thuocDAO.getAllThuoc();
        LocalDate today = LocalDate.now();

        return dsThuoc.stream()
                .filter(t -> t.getHanSuDung() != null)
                .filter(t -> !t.getHanSuDung().isAfter(today.plusDays(30))) // hết hạn hoặc <=30 ngày
                .collect(Collectors.toList());
    }

    /**
     * Xuất danh sách ra Excel
     */
    public void exportToExcel(List<Thuoc> ds, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Thuốc hết hạn / sắp hết hạn");

            String[] headers = {
                "Mã Thuốc", "Tên Thuốc", "Số Lượng", "Giá Nhập", "Giá Bán", "Đơn Vị Tính",
                "Nhà Cung Cấp", "Hạn Sử Dụng", "Tên Kệ Thuốc", "Thành Phần", "Trạng Thái"
            };

            // Dòng tiêu đề
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            LocalDate today = LocalDate.now();
            int rowNum = 1;

            for (Thuoc t : ds) {
                Row row = sheet.createRow(rowNum++);

                long daysLeft = ChronoUnit.DAYS.between(today, t.getHanSuDung());
                String trangThai = daysLeft < 0 ? "ĐÃ HẾT HẠN" : "SẮP HẾT HẠN";

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
                row.createCell(10).setCellValue(trangThai);
            }

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

    /**
     * Tính số ngày còn lại trước khi hết hạn
     */
    public long tinhSoNgayConLai(LocalDate hanSuDung) {
        return ChronoUnit.DAYS.between(LocalDate.now(), hanSuDung);
    }

    /**
     * Tạo dataset cho biểu đồ — chỉ hiển thị thuốc còn hạn và sắp hết hạn (<= 30 ngày)
     */
    public DefaultCategoryDataset taoDatasetBieuDo() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<Thuoc> dsThuoc = thuocDAO.getAllThuoc();
        LocalDate today = LocalDate.now();

        for (Thuoc thuoc : dsThuoc) {
            if (thuoc.getHanSuDung() == null)
                continue;

            LocalDate hsd = thuoc.getHanSuDung();
            long daysLeft = ChronoUnit.DAYS.between(today, hsd);

            // CHỈ lấy thuốc còn hạn và sắp hết hạn (<= 30 ngày)
            if (daysLeft > 0 && daysLeft <= 30) {
                dataset.addValue(daysLeft, "Số ngày còn lại", thuoc.getTenThuoc());
            }
        }

        return dataset;
    }
}
