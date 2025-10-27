package gui;

import java.awt.BorderLayout; // Import mới
import java.awt.Color; // Import mới
import java.awt.Font; // Import mới
import java.text.DecimalFormat; // Import mới
import java.text.SimpleDateFormat; // Import mới
import java.time.LocalDate; // Import mới
import java.time.format.DateTimeFormatter; // Import mới
import java.util.List; // Import mới

import javax.swing.BorderFactory; // Import mới
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants; // Import mới
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader; // Import mới
import javax.swing.table.TableCellRenderer; // Import mới

import dao.hoaDon_DAO; // Import DAO

public class ThongkeHDThang_GUI extends JDialog {

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
    // --- Kết thúc định nghĩa Style ---

    private JTable table;
    private JLabel lblTitle; // Đổi tên biến tiêu đề
    private JLabel lblTongSoHDValue; // Label hiển thị giá trị tổng số HD
    private JLabel lblTongTienValue; // Label hiển thị giá trị tổng tiền

    private hoaDon_DAO hoaDonDAO; // DAO để lấy dữ liệu
    private DecimalFormat df = new DecimalFormat("#,##0 VND"); // Format tiền
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Format ngày

    public ThongkeHDThang_GUI(JFrame parent) {
        super(parent, "Chi Tiết Hóa Đơn Theo Ngày", true); // Thêm tiêu đề Dialog
        hoaDonDAO = new hoaDon_DAO(); // Khởi tạo DAO
        initialize();
        setLocationRelativeTo(parent);
    }

    private void initialize() {
        setSize(1280, 710); // Kích thước gốc
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10)); // Dùng BorderLayout
        getContentPane().setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // --- Tiêu đề ---
        lblTitle = new JLabel("Bảng Thống Kê Hóa Đơn Ngày ..."); // Text mặc định
        lblTitle.setFont(FONT_TITLE_SECTION); // Font tiêu đề
        lblTitle.setForeground(COLOR_PRIMARY_BLUE); // Màu xanh
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        getContentPane().add(lblTitle, BorderLayout.NORTH); // Vị trí NORTH

        // --- Bảng Dữ Liệu ---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền
        getContentPane().add(scrollPane, BorderLayout.CENTER); // Vị trí CENTER

        table = new JTable() {
             // Thêm hiệu ứng sọc vằn
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
            new Object[][] {}, // Dữ liệu trống ban đầu
            new String[] {
                "Mã Hóa Đơn", "SĐT Khách Hàng", "Tên Khách Hàng", "Mã Nhân Viên", "Tên Nhân Viên", "Tổng Tiền"
            }
        ) {
            // Chặn sửa trực tiếp trên bảng
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        scrollPane.setViewportView(table);

        // --- Panel Tổng Kết (ở dưới) ---
        JPanel panelSummary = new JPanel();
        panelSummary.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
        panelSummary.setBorder(BorderFactory.createCompoundBorder( // Viền trên + padding
            BorderFactory.createMatteBorder(1, 0, 0, 0, COLOR_BORDER_LIGHT),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        getContentPane().add(panelSummary, BorderLayout.SOUTH); // Vị trí SOUTH
        panelSummary.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 5)); // FlowLayout cách nhau

        JLabel lblTongSoHD = new JLabel("Tổng Số Hóa Đơn:");
        lblTongSoHD.setFont(FONT_LABEL_BOLD); // Font đậm
        lblTongSoHD.setForeground(COLOR_TEXT_DARK); // Màu chữ
        panelSummary.add(lblTongSoHD);

        lblTongSoHDValue = new JLabel("0"); // Label giá trị
        lblTongSoHDValue.setFont(FONT_LABEL_BOLD); // Font đậm
        lblTongSoHDValue.setForeground(COLOR_PRIMARY_BLUE); // Màu xanh
        panelSummary.add(lblTongSoHDValue);

        JLabel lblTongTien = new JLabel("Tổng Tiền Các Hóa Đơn:");
        lblTongTien.setFont(FONT_LABEL_BOLD); // Font đậm
        lblTongTien.setForeground(COLOR_TEXT_DARK); // Màu chữ
        panelSummary.add(lblTongTien);

        lblTongTienValue = new JLabel("0 VND"); // Label giá trị
        lblTongTienValue.setFont(FONT_SUMMARY_TOTAL); // Font to, đậm
        lblTongTienValue.setForeground(COLOR_SUCCESS_GREEN); // Màu xanh lá
        panelSummary.add(lblTongTienValue);
    }

    /**
     * Hàm này áp dụng style CHUNG cho một JTable đã được tạo
     * @param table Bảng cần áp dụng style
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
     * Hàm load dữ liệu hóa đơn chi tiết của một ngày (có thể lọc theo NV)
     * @param selectedDate Ngày cần xem
     * @param maNVSelected Mã nhân viên cần lọc (hoặc null/rỗng nếu không lọc)
     */
    public void loadData(LocalDate selectedDate, String maNVSelected) {
        if (selectedDate == null) return;

        // Cập nhật tiêu đề
        lblTitle.setText("Chi Tiết Hóa Đơn Ngày " + selectedDate.format(dateFormatter));

        // Lấy dữ liệu từ DAO
        List<Object[]> dsHD = hoaDonDAO.getHoaDonChiTietTrongNgay(selectedDate, maNVSelected);

        // Hiển thị lên bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ
        double tongTienNgay = 0;

        for (Object[] row : dsHD) {
            double tongTienHD = (double) row[5];
            row[5] = df.format(tongTienHD); // Format tiền cho cột tổng tiền
            model.addRow(row);
            tongTienNgay += tongTienHD; // Cộng dồn tổng tiền
        }

        // Cập nhật label tổng kết
        lblTongSoHDValue.setText(String.valueOf(dsHD.size()));
        lblTongTienValue.setText(df.format(tongTienNgay));
    }
}