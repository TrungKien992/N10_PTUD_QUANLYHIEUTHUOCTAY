package utils;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.io.font.PdfEncodings;
import java.time.format.DateTimeFormatter; // Giữ lại cái này

import entity.ChiTietHoaDon;
import entity.HoaDon;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
// import java.text.SimpleDateFormat; // Không cần nếu dùng DateTimeFormatter
import java.util.ArrayList;
// import java.util.Date; // Không cần nếu dùng DateTimeFormatter

public class PdfExporter {

    // ĐƯỜNG DẪN ĐẾN FONT TIẾNG VIỆT
    public static final String FONT_PATH = "fonts/times.ttf"; // Đảm bảo file này tồn tại
    // private static PdfFont vietnameseFont = null; // <-- XÓA BIẾN STATIC

    // Giữ lại df và dtf là static vì chúng an toàn và tiện lợi
    private static DecimalFormat df = new DecimalFormat("#,##0 VND");
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Dùng DateTimeFormatter

    // XÓA HÀM loadFont()
    // private static void loadFont() { ... }

    // Hàm xuất PDF
    public static void exportHoaDonToPdf(HoaDon hd, ArrayList<ChiTietHoaDon> dsCTHD, String filePath) {
        // KHAI BÁO VÀ LOAD FONT CỤC BỘ
        PdfFont vietnameseFont = null;
        try {
            vietnameseFont = PdfFontFactory.createFont(FONT_PATH, PdfEncodings.IDENTITY_H);
        } catch (IOException e) {
            System.err.println("Lỗi tải font tiếng Việt: " + e.getMessage());
            try {
                vietnameseFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                System.err.println("Đang sử dụng font Helvetica dự phòng (Sẽ lỗi tiếng Việt).");
            } catch (IOException ex) {
                System.err.println("Không thể tải font dự phòng: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Không thể tải font chữ cần thiết để xuất PDF.\nLỗi chính: " + e.getMessage() + "\nLỗi phụ: " + ex.getMessage(), "Lỗi Font Nghiêm Trọng", JOptionPane.ERROR_MESSAGE);
                return; // Thoát
            }
        }

        // Kiểm tra font (vẫn cần thiết)
        if (vietnameseFont == null) {
            JOptionPane.showMessageDialog(null, "Không thể khởi tạo font chữ cần thiết để xuất PDF.", "Lỗi Font", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (hd == null || dsCTHD == null || dsCTHD.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu hóa đơn để xuất PDF.", "Lỗi Dữ Liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (PdfWriter writer = new PdfWriter(filePath);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf, PageSize.A4)) {

            document.setMargins(30, 30, 30, 30);

            // --- 1. Thông tin cửa hàng ---
            document.add(new Paragraph("Hiệu Thuốc Tây Khánh Hưng")
                    .setFont(vietnameseFont).setFontSize(16).setBold()
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("123 Đường ABC, Quận XYZ, TP.HCM")
                    .setFont(vietnameseFont).setFontSize(12)
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("----------------------------------------------------------")
                    .setTextAlignment(TextAlignment.CENTER));

            // --- 2. Tiêu đề Hóa đơn ---
            document.add(new Paragraph("HÓA ĐƠN BÁN HÀNG")
                    .setFont(vietnameseFont).setFontSize(20).setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20).setMarginBottom(10));

            // --- 3. Thông tin Hóa đơn (Mã, Ngày, NV, KH) ---
            // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Đã đưa lên static
            document.add(new Paragraph("Mã Hóa Đơn: " + hd.getMaHD())
                    .setFont(vietnameseFont).setFontSize(12).setBold());
            // Dùng dtf static
            document.add(new Paragraph("Ngày Lập: " + hd.getNgayLap().format(dtf))
                    .setFont(vietnameseFont).setFontSize(12));
            document.add(new Paragraph("Nhân Viên: " + (hd.getNhanVien() != null ? hd.getNhanVien().getTenNV() : "N/A"))
                    .setFont(vietnameseFont).setFontSize(12));
            document.add(new Paragraph("Khách Hàng: " + (hd.getKhachHang() != null ? hd.getKhachHang().getTenKH() : "Khách vãng lai"))
                    .setFont(vietnameseFont).setFontSize(12)
                    .setMarginBottom(15));

            // --- 4. Bảng Chi Tiết Hóa Đơn ---
            Table table = new Table(UnitValue.createPercentArray(new float[]{1, 4, 2, 2, 3}));
            table.setWidth(UnitValue.createPercentValue(100));

            // Thêm header - TRUYỀN FONT VÀO
            table.addHeaderCell(createCell("STT", true, vietnameseFont));
            table.addHeaderCell(createCell("Tên Thuốc", true, vietnameseFont));
            table.addHeaderCell(createCell("Số Lượng", true, vietnameseFont));
            table.addHeaderCell(createCell("Đơn Giá", true, vietnameseFont));
            table.addHeaderCell(createCell("Thành Tiền", true, vietnameseFont));

            // Thêm data
            int stt = 1;
            double tongTienHang = 0;
            for (ChiTietHoaDon ct : dsCTHD) {
                if (ct.getThuoc() == null) continue;
                double thanhTien = ct.tinhThanhTien();
                // TRUYỀN FONT VÀO
                table.addCell(createCell(String.valueOf(stt++), false, vietnameseFont));
                table.addCell(createCell(ct.getThuoc().getTenThuoc(), false, vietnameseFont));
                table.addCell(createCell(String.valueOf(ct.getSoLuong()), false, vietnameseFont));
                table.addCell(createCell(df.format(ct.getThuoc().getGiaBan()), false, vietnameseFont));
                table.addCell(createCell(df.format(thanhTien), false, vietnameseFont));
                tongTienHang += thanhTien;
            }
            document.add(table);

            // --- 5. Thông tin Thanh toán ---
            double thueValue = 0;
            double thueTiLe = 0;
             if (hd.getThue() != null) {
                thueTiLe = hd.getThue().getTiLe();
             } else {
                 // Nên có giá trị mặc định nếu getThue() là null
                 thueTiLe = 0.1; // Ví dụ: 10%
                 System.err.println("Cảnh báo: Hóa đơn " + hd.getMaHD() + " không có thông tin thuế, tạm tính 10%.");
             }

            double khuyenMaiGiam = 0.0;
            String kmText = "Không áp dụng";
            if (hd.getKhuyenMai() != null) {
                khuyenMaiGiam = tongTienHang * (hd.getKhuyenMai().getGiaTri() / 100.0); // Chia 100.0 để đảm bảo là double
                kmText = String.format("-%.0f%% (%s)", hd.getKhuyenMai().getGiaTri(), df.format(khuyenMaiGiam));
            }
            double tongSauKM = tongTienHang - khuyenMaiGiam;
            thueValue = tongSauKM * thueTiLe;
            double tongCong = tongSauKM + thueValue;

            Table summaryTable = new Table(UnitValue.createPercentArray(new float[]{3, 2, 3, 2}));
            summaryTable.setWidth(UnitValue.createPercentValue(100));
            summaryTable.setMarginTop(20);

            // Thêm các dòng tổng kết - TRUYỀN FONT VÀO
            summaryTable.addCell(createSummaryCell("Tổng Tiền Hàng:", TextAlignment.RIGHT, vietnameseFont));
            summaryTable.addCell(createSummaryCell(df.format(tongTienHang), TextAlignment.LEFT, vietnameseFont));
            summaryTable.addCell(createSummaryCell(String.format("Thuế (%.0f%% VAT):", thueTiLe * 100), TextAlignment.RIGHT, vietnameseFont));
            summaryTable.addCell(createSummaryCell(df.format(thueValue), TextAlignment.LEFT, vietnameseFont));
            summaryTable.addCell(createSummaryCell("Khuyến Mãi:", TextAlignment.RIGHT, vietnameseFont));
            summaryTable.addCell(createSummaryCell(kmText, TextAlignment.LEFT, vietnameseFont));
            summaryTable.addCell(createSummaryCell("Tổng Cộng:", TextAlignment.RIGHT, true, vietnameseFont)); // Hàm 4 tham số
            summaryTable.addCell(createSummaryCell(df.format(tongCong), TextAlignment.LEFT, true, vietnameseFont)); // Hàm 4 tham số
            summaryTable.addCell(createSummaryCell("Tiền Khách Đưa:", TextAlignment.RIGHT, vietnameseFont));
            summaryTable.addCell(createSummaryCell(df.format(hd.getTienKhachDua()), TextAlignment.LEFT, vietnameseFont));
            summaryTable.addCell(createSummaryCell("Tiền Thừa:", TextAlignment.RIGHT, vietnameseFont));
            summaryTable.addCell(createSummaryCell(df.format(hd.getTienThua()), TextAlignment.LEFT, vietnameseFont));

            document.add(summaryTable);

            // try-with-resources tự động đóng document

            // Mở file PDF sau khi tạo
            try {
                File pdfFile = new File(filePath);
                if (pdfFile.exists()) {
                    if (Desktop.isDesktopSupported()) {
                         Desktop.getDesktop().open(pdfFile);
                    } else {
                        System.out.println("Không thể tự động mở file PDF trên hệ thống này.");
                         JOptionPane.showMessageDialog(null, "Đã xuất PDF thành công tại:\n" + filePath + "\nVui lòng mở file thủ công.", "Xuất PDF Thành Công", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                     JOptionPane.showMessageDialog(null, "Đã xuất PDF thành công nhưng không tìm thấy file để mở.", "Xuất PDF Thành Công", JOptionPane.WARNING_MESSAGE);
                }
            } catch (IOException e) {
                 JOptionPane.showMessageDialog(null, "Lỗi khi mở file PDF: " + e.getMessage(), "Lỗi Mở File", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException e) { // Bắt lỗi IO chung khi tạo PdfWriter, PdfDocument...
             JOptionPane.showMessageDialog(null, "Lỗi khi tạo file PDF: " + e.getMessage(), "Lỗi Xuất PDF", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) { // Bắt các lỗi khác có thể xảy ra (như lỗi của iText)
             JOptionPane.showMessageDialog(null, "Lỗi không xác định khi xuất PDF: " + e.getMessage(), "Lỗi Xuất PDF", JOptionPane.ERROR_MESSAGE);
             e.printStackTrace(); // In stack trace để debug
        }
    }

    // Helper tạo Cell cho bảng - THÊM THAM SỐ FONT
    private static Cell createCell(String content, boolean isHeader, PdfFont font) {
        Paragraph p = new Paragraph(content).setFont(font).setFontSize(isHeader ? 11 : 10);
        Cell cell = new Cell().add(p).setPadding(5);
        if (isHeader) {
            cell.setBold().setBackgroundColor(com.itextpdf.kernel.colors.ColorConstants.LIGHT_GRAY);
            cell.setTextAlignment(TextAlignment.CENTER);
        } else {
            // Căn lề
            if (content.matches("^-?\\d+$") || content.endsWith("VND")) { // Chỉ căn phải số nguyên hoặc tiền
                cell.setTextAlignment(TextAlignment.RIGHT);
            } else if (content.matches("^-?\\d+(\\.\\d+)?$")){ // Căn phải số thực
                 cell.setTextAlignment(TextAlignment.RIGHT);
            }
             else {
                cell.setTextAlignment(TextAlignment.LEFT);
            }
        }
        return cell;
    }

    // Helper tạo Cell cho bảng tổng kết (không viền) - THÊM THAM SỐ FONT
    private static Cell createSummaryCell(String content, TextAlignment alignment, PdfFont font) {
        return createSummaryCell(content, alignment, false, font); // Gọi hàm 4 tham số
    }

    // THÊM THAM SỐ FONT
    private static Cell createSummaryCell(String content, TextAlignment alignment, boolean isBold, PdfFont font) {
        Paragraph p = new Paragraph(content).setFont(font).setFontSize(12).setTextAlignment(alignment);
        if (isBold) {
            p.setFontSize(14).setBold();
        }
        return new Cell().add(p).setBorder(null).setPadding(3);
    }
}