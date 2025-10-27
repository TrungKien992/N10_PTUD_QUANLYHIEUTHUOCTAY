package gui;

import java.awt.BorderLayout; // Import mới
import java.awt.Color; // Import mới
import java.awt.Font; // Import mới
import java.awt.event.ActionEvent; // Import mới
import java.awt.event.ActionListener; // Import mới
import java.text.DecimalFormat; // Import mới
import java.time.LocalDate; // Import mới
import java.util.List; // Import mới
import javax.swing.BorderFactory; // Import mới
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Import mới
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants; // Import mới
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader; // Import mới
import javax.swing.table.TableCellRenderer; // Import mới

import dao.hoaDon_DAO; // Import DAO

public class ThongkeHDNam_GUI extends JDialog {

    // --- Định nghĩa Style giống TrangChu_GUI ---
    private static final Color COLOR_BACKGROUND_PRIMARY = new Color(240, 242, 245);
    private static final Color COLOR_CARD_BACKGROUND = Color.WHITE;
    private static final Color COLOR_PRIMARY_BLUE = new Color(0, 123, 255);
    private static final Color COLOR_SUCCESS_GREEN = new Color(40, 167, 69);
    private static final Color COLOR_TEXT_DARK = new Color(33, 37, 41);
    private static final Color COLOR_TEXT_MUTED = new Color(108, 117, 125);
    private static final Color COLOR_BORDER_LIGHT = new Color(222, 226, 230);

    private static final Font FONT_TITLE_SECTION = new Font("Segoe UI", Font.BOLD, 26);
    private static final Font FONT_LABEL_BOLD = new Font("Segoe UI", Font.BOLD, 15);
    private static final Font FONT_TABLE_HEADER = new Font("Segoe UI", Font.BOLD, 15);
    private static final Font FONT_TABLE_CELL = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONT_SUMMARY_TOTAL = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font FONT_BUTTON_STANDARD = new Font("Segoe UI", Font.BOLD, 14); // Font nút
    // --- Kết thúc định nghĩa Style ---

    private JTable table;
    private JLabel lblTitle; // Label tiêu đề
    private JLabel lblTongSoHDValue; // Label giá trị tổng số HD
    private JLabel lblTongTienValue; // Label giá trị tổng tiền
    private JButton btnXemChiTiet; // Nút Xem Chi Tiết

    private hoaDon_DAO hoaDonDAO; // DAO
    private DecimalFormat df = new DecimalFormat("#,##0 VND"); // Format tiền
    private JFrame parentFrame; // Lưu frame cha để mở dialog con
    private int currentYear; // Lưu năm đang xem
    private String currentMaNV; // Lưu mã NV đang lọc

    public ThongkeHDNam_GUI(JFrame parent) {
        super(parent, "Chi Tiết Thống Kê Theo Tháng", true); // Tiêu đề Dialog
        this.parentFrame = parent; // Lưu frame cha
        hoaDonDAO = new hoaDon_DAO(); // Khởi tạo DAO
        initialize();
        setLocationRelativeTo(parent);
    }

    private void initialize() {
        setSize(800, 725); // Điều chỉnh kích thước nếu cần
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10)); // Dùng BorderLayout
        getContentPane().setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // --- Tiêu đề ---
        lblTitle = new JLabel(""); // Text mặc định
        lblTitle.setFont(FONT_TITLE_SECTION); // Font tiêu đề
        lblTitle.setForeground(COLOR_PRIMARY_BLUE); // Màu xanh
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        getContentPane().add(lblTitle, BorderLayout.NORTH); // Vị trí NORTH

        // --- Bảng Dữ Liệu ---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền
        getContentPane().add(scrollPane, BorderLayout.CENTER); // Vị trí CENTER

        table = new JTable() {
            @Override
            public java.awt.Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                java.awt.Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
        };
        // Áp dụng style chung cho bảng
        applyCommonTableStyling(table);
        table.setModel(new DefaultTableModel(
            new Object[][] {}, // Dữ liệu trống
            new String[] {
                "Ngày", "Tổng Số Hóa Đơn", "Tổng Tiền Các Hóa Đơn" // Đổi tên cột
            }
        ){
            // Chặn sửa
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        scrollPane.setViewportView(table);

        // --- Panel Tổng Kết và Nút (ở dưới) ---
        JPanel panelSouth = new JPanel(new BorderLayout(10, 5)); // Chứa cả tổng kết và nút
        panelSouth.setOpaque(false); // Trong suốt
        panelSouth.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, COLOR_BORDER_LIGHT),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        getContentPane().add(panelSouth, BorderLayout.SOUTH); // Vị trí SOUTH

        // -- Panel con cho tổng kết (FlowLayout) --
        JPanel panelSummary = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 5));
        panelSummary.setOpaque(false); // Trong suốt
        panelSouth.add(panelSummary, BorderLayout.CENTER); // Tổng kết ở giữa

        JLabel lblTongSoHD = new JLabel("Tổng Số Hóa Đơn:");
        lblTongSoHD.setFont(FONT_LABEL_BOLD);
        lblTongSoHD.setForeground(COLOR_TEXT_DARK);
        panelSummary.add(lblTongSoHD);

        lblTongSoHDValue = new JLabel("0");
        lblTongSoHDValue.setFont(FONT_LABEL_BOLD);
        lblTongSoHDValue.setForeground(COLOR_PRIMARY_BLUE);
        panelSummary.add(lblTongSoHDValue);

        JLabel lblTongTien = new JLabel("Tổng Tiền Các Hóa Đơn:");
        lblTongTien.setFont(FONT_LABEL_BOLD);
        lblTongTien.setForeground(COLOR_TEXT_DARK);
        panelSummary.add(lblTongTien);

        lblTongTienValue = new JLabel("0 VND");
        lblTongTienValue.setFont(FONT_SUMMARY_TOTAL);
        lblTongTienValue.setForeground(COLOR_SUCCESS_GREEN);
        panelSummary.add(lblTongTienValue);

        // -- Nút Xem Chi Tiết (ở bên phải) --
        btnXemChiTiet = new JButton("Xem Chi Tiết Ngày"); // Đổi tên nút
        btnXemChiTiet.setFont(FONT_BUTTON_STANDARD); // Font nút
        styleButton(btnXemChiTiet, COLOR_PRIMARY_BLUE); // Style nút
        panelSouth.add(btnXemChiTiet, BorderLayout.EAST); // Nút ở bên phải

        // --- Thêm ActionListener cho nút Xem Chi Tiết ---
        btnXemChiTiet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xemChiTietNgayTrongThang();
            }
        });
    }

    /**
     * Hàm này áp dụng style CHUNG cho một JTable đã được tạo
     */
    private void applyCommonTableStyling(JTable table) {
        table.setFont(FONT_TABLE_CELL);
        table.setRowHeight(30);
        table.setGridColor(COLOR_BORDER_LIGHT);
        table.setShowGrid(true);
        table.setSelectionBackground(COLOR_PRIMARY_BLUE);
        table.setSelectionForeground(Color.WHITE);

        JTableHeader header = table.getTableHeader();
        header.setFont(FONT_TABLE_HEADER);
        header.setBackground(new Color(230, 235, 240));
        header.setForeground(COLOR_TEXT_DARK);
        header.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        header.setReorderingAllowed(false);
        header.setResizingAllowed(true);
    }

     /**
     * Hàm để style các nút bấm
     */
    private void styleButton(JButton button, Color background) {
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Padding
    }

    /**
     * Hàm load dữ liệu thống kê theo ngày của một tháng (có thể lọc theo NV)
     * @param thang Tháng cần xem (1-12)
     * @param nam Năm cần xem
     * @param maNVSelected Mã nhân viên cần lọc (hoặc null/rỗng)
     */
    public void loadData(int thang, int nam, String maNVSelected) {
        this.currentYear = nam; // Lưu lại năm
        this.currentMaNV = maNVSelected; // Lưu lại mã NV

        // Cập nhật tiêu đề
        lblTitle.setText("Thống Kê Chi Tiết Tháng " + thang + "/" + nam);

        // Lấy dữ liệu từ DAO
        List<Object[]> dsThongKeNgay = hoaDonDAO.getThongKeTheoNgayTrongThang(thang, nam, maNVSelected);

        // Hiển thị lên bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa cũ
        int tongSoHDThang = 0;
        double tongTienThang = 0;

        for (Object[] row : dsThongKeNgay) {
            int soHDNgay = (int) row[1];
            double tongTienNgay = (double) row[2];
            row[2] = df.format(tongTienNgay); // Format tiền
            model.addRow(row);

            tongSoHDThang += soHDNgay;
            tongTienThang += tongTienNgay;
        }

        // Cập nhật label tổng kết
        lblTongSoHDValue.setText(String.valueOf(tongSoHDThang));
        lblTongTienValue.setText(df.format(tongTienThang));
    }

    /**
     * Xử lý sự kiện khi nhấn nút "Xem Chi Tiết Ngày"
     */
    private void xemChiTietNgayTrongThang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một ngày trong bảng để xem chi tiết!");
            return;
        }

        int ngay = (int) table.getValueAt(selectedRow, 0);
        String title = lblTitle.getText();
        String[] parts = title.split(" ");
        String thangNamPart = parts[parts.length - 1]; // "10/2025"
        String[] thangNam = thangNamPart.split("/");
        int thang = Integer.parseInt(thangNam[0]);
        int nam = this.currentYear;

        LocalDate selectedDate = LocalDate.of(nam, thang, ngay);

        // Mở dialog ThongkeHDThang_GUI
        ThongkeHDThang_GUI dialogChiTietNgay = new ThongkeHDThang_GUI(this.parentFrame);
        dialogChiTietNgay.loadData(selectedDate, this.currentMaNV); // Truyền ngày và mã NV đang lọc
        dialogChiTietNgay.setVisible(true);
    }
}