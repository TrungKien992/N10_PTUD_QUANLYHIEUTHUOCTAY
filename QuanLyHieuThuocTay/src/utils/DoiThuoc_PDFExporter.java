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

import entity.CTPhieuDoiTra;
import entity.ChiTietHoaDon;
import entity.PhieuDoiTra;
import dao.hoaDon_DAO;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DoiThuoc_PDFExporter {

    public static final String FONT_PATH = "fonts/times.ttf";
    private static final double TAX_RATE = 0.1;
    private static DecimalFormat df = new DecimalFormat("#,##0 VND");
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void exportPhieuDoiThuocToPdf(PhieuDoiTra pdt, ArrayList<CTPhieuDoiTra> dsCTPDT, String filePath) {
        PdfFont vietnameseFont = null;
        try {
            vietnameseFont = PdfFontFactory.createFont(FONT_PATH, PdfEncodings.IDENTITY_H);
        } catch (IOException e) {
            System.err.println("Lỗi tải font tiếng Việt: " + e.getMessage());
            try {
                vietnameseFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                System.err.println("Đang sử dụng font Helvetica dự phòng (Sẽ lỗi tiếng Việt).");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Không thể tải font chữ cần thiết để xuất PDF.\nLỗi chính: " + e.getMessage() + "\nLỗi phụ: " + ex.getMessage(), "Lỗi Font Nghiêm Trọng", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (vietnameseFont == null) {
            JOptionPane.showMessageDialog(null, "Không thể khởi tạo font chữ cần thiết để xuất PDF.", "Lỗi Font", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (pdt == null || dsCTPDT == null || dsCTPDT.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu phiếu đổi thuốc để xuất PDF.", "Lỗi Dữ Liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (pdt.getHoaDon() == null || pdt.getHoaDon().getMaHD() == null) {
            JOptionPane.showMessageDialog(null, "Phiếu đổi thuốc không có hóa đơn liên kết hợp lệ.", "Lỗi Dữ Liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        hoaDon_DAO hoaDonDAO = new hoaDon_DAO();
        ArrayList<ChiTietHoaDon> dsCTHD = hoaDonDAO.getChiTietHoaDonTheoMa(pdt.getHoaDon().getMaHD());
        if (dsCTHD == null || dsCTHD.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy chi tiết hóa đơn cho mã hóa đơn: " + pdt.getHoaDon().getMaHD(), "Lỗi Dữ Liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Map<String, Integer> soLuongCuMap = new HashMap<>();
        for (ChiTietHoaDon ctHD : dsCTHD) {
            if (ctHD.getThuoc() != null && ctHD.getThuoc().getMaThuoc() != null) {
                soLuongCuMap.put(ctHD.getThuoc().getMaThuoc(), ctHD.getSoLuong());
            }
        }

        try (PdfWriter writer = new PdfWriter(filePath);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf, PageSize.A4)) {

            document.setMargins(30, 30, 30, 30);

            document.add(new Paragraph("Hiệu Thuốc Tây Khánh Hưng")
                    .setFont(vietnameseFont).setFontSize(16).setBold()
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("123 Đường ABC, Quận XYZ, TP.HCM")
                    .setFont(vietnameseFont).setFontSize(12)
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("----------------------------------------------------------")
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("PHIẾU ĐỔI THUỐC")
                    .setFont(vietnameseFont).setFontSize(20).setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20).setMarginBottom(10));

            document.add(new Paragraph("Mã Phiếu Đổi: " + pdt.getMaPDT())
                    .setFont(vietnameseFont).setFontSize(12).setBold());
            document.add(new Paragraph("Mã Hóa Đơn: " + (pdt.getHoaDon() != null ? pdt.getHoaDon().getMaHD() : "N/A"))
                    .setFont(vietnameseFont).setFontSize(12));
            document.add(new Paragraph("Ngày Lập: " + pdt.getNgayDoiTra().format(dtf))
                    .setFont(vietnameseFont).setFontSize(12));
            document.add(new Paragraph("Nhân Viên: " + (pdt.getNhanVien() != null ? pdt.getNhanVien().getTenNV() : "N/A"))
                    .setFont(vietnameseFont).setFontSize(12));
            document.add(new Paragraph("Khách Hàng: " + (pdt.getHoaDon() != null && pdt.getHoaDon().getKhachHang() != null ? pdt.getHoaDon().getKhachHang().getTenKH() : "Khách vãng lai"))
                    .setFont(vietnameseFont).setFontSize(12));
            document.add(new Paragraph("Lý Do Đổi: " + pdt.getLyDo())
                    .setFont(vietnameseFont).setFontSize(12)
                    .setMarginBottom(15));

            Table table = new Table(UnitValue.createPercentArray(new float[]{1, 4, 2, 2, 2, 2, 3}));
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell(createCell("STT", true, vietnameseFont));
            table.addHeaderCell(createCell("Tên Thuốc", true, vietnameseFont));
            table.addHeaderCell(createCell("Đơn Giá", true, vietnameseFont));
            table.addHeaderCell(createCell("Đơn Vị Tính", true, vietnameseFont));
            table.addHeaderCell(createCell("Số Lượng", true, vietnameseFont));
            table.addHeaderCell(createCell("Thành Tiền", true, vietnameseFont));
            table.addHeaderCell(createCell("Số Lượng Đổi", true, vietnameseFont));

            int stt = 1;
            double tongThanhTienCu = 0;

            for (CTPhieuDoiTra ct : dsCTPDT) {
                if (ct.getThuoc() == null || ct.getThuoc().getMaThuoc() == null) continue;
                double giaBan = ct.getThuoc().getGiaBan() != 0 ? ct.getThuoc().getGiaBan() : 0.0;
                Integer soLuongCu = soLuongCuMap.getOrDefault(ct.getThuoc().getMaThuoc(), 0);
                double thanhTienCu = giaBan * soLuongCu * (1 + TAX_RATE);

                table.addCell(createCell(String.valueOf(stt++), false, vietnameseFont));
                table.addCell(createCell(ct.getThuoc().getTenThuoc() != null ? ct.getThuoc().getTenThuoc() : "Không xác định", false, vietnameseFont));
                table.addCell(createCell(df.format(giaBan), false, vietnameseFont));
                table.addCell(createCell(ct.getThuoc().getDonViTinh() != null ? ct.getThuoc().getDonViTinh() : "N/A", false, vietnameseFont));
                table.addCell(createCell(String.valueOf(soLuongCu), false, vietnameseFont));
                table.addCell(createCell(df.format(thanhTienCu), false, vietnameseFont));
                table.addCell(createCell(String.valueOf(ct.getSoLuong()), false, vietnameseFont));
                tongThanhTienCu += thanhTienCu;
            }

            document.add(table);

            Table summaryTable = new Table(UnitValue.createPercentArray(new float[]{3, 2}));
            summaryTable.setWidth(UnitValue.createPercentValue(100));
            summaryTable.setMarginTop(20);

            summaryTable.addCell(createSummaryCell("Tổng Thành Tiền:", TextAlignment.RIGHT, true, vietnameseFont));
            summaryTable.addCell(createSummaryCell(df.format(tongThanhTienCu), TextAlignment.LEFT, true, vietnameseFont));

            summaryTable.addCell(createSummaryCell("Phí Đổi:", TextAlignment.RIGHT, true, vietnameseFont));
            summaryTable.addCell(createSummaryCell("0 VND", TextAlignment.LEFT, true, vietnameseFont));

            document.add(summaryTable);

            File pdfFile = new File(filePath);
            if (pdfFile.exists()) {
                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(pdfFile);
                    } else {
                        JOptionPane.showMessageDialog(null, "Đã xuất PDF thành công tại:\n" + filePath);
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi mở file PDF: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tạo file PDF cho phiếu đổi " + pdt.getMaPDT() + ": " + e.getMessage(), "Lỗi Xuất PDF", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi không xác định khi xuất PDF: " + e.getMessage(), "Lỗi Xuất PDF", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private static Cell createCell(String content, boolean isHeader, PdfFont font) {
        if (content == null) content = "";
        Paragraph p = new Paragraph(content).setFont(font).setFontSize(isHeader ? 11 : 10);
        Cell cell = new Cell().add(p).setPadding(5);

        if (isHeader) {
            cell.setBold().setBackgroundColor(com.itextpdf.kernel.colors.ColorConstants.LIGHT_GRAY);
            cell.setTextAlignment(TextAlignment.CENTER);
        } else {
            if (content.matches("^-?\\d+$") || content.endsWith("VND")) {
                cell.setTextAlignment(TextAlignment.RIGHT);
            } else if (content.matches("^-?\\d+(\\.\\d+)?$")) {
                cell.setTextAlignment(TextAlignment.RIGHT);
            } else {
                cell.setTextAlignment(TextAlignment.LEFT);
            }
        }
        return cell;
    }

    private static Cell createSummaryCell(String content, TextAlignment alignment, boolean isBold, PdfFont font) {
        Paragraph p = new Paragraph(content).setFont(font).setFontSize(12).setTextAlignment(alignment);
        if (isBold) {
            p.setFontSize(14).setBold();
        }
        return new Cell().add(p).setBorder(null).setPadding(3);
    }
}
