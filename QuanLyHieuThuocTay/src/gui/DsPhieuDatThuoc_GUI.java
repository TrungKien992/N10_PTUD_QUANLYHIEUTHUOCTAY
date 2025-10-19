package gui;

import java.awt.Color;
import java.awt.Component; // Import Component
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics; // Import Graphics
import java.awt.Graphics2D; // Import Graphics2D
import java.awt.GradientPaint; // Import GradientPaint

import javax.swing.*; // Import *
import javax.swing.border.Border; // Import Border
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.event.*; // Import *

public class DsPhieuDatThuoc_GUI extends JDialog{
	private JTextField textField;
	private JTable table;

	// ========== THÊM HẰNG SỐ MÀU/FONT VÀ HÀM TRỢ GIÚP ==========
	private static final Color COLOR_BACKGROUND_PRIMARY = new Color(240, 242, 245);
	private static final Color COLOR_CARD_BACKGROUND = Color.WHITE;
	private static final Color COLOR_PRIMARY_BLUE = new Color(0, 123, 255);
	private static final Color COLOR_SUCCESS_GREEN = new Color(40, 167, 69);
	private static final Color COLOR_DANGER_RED = new Color(220, 53, 69);
	private static final Color COLOR_TEXT_DARK = new Color(33, 37, 41);
	private static final Color COLOR_TEXT_MUTED = new Color(108, 117, 125);
	private static final Color COLOR_BORDER_LIGHT = new Color(222, 226, 230);
	private static final Font FONT_TITLE_MAIN = new Font("Segoe UI", Font.BOLD, 38);
	private static final Font FONT_TITLE_SECTION = new Font("Segoe UI", Font.BOLD, 26);
	private static final Font FONT_LABEL_BOLD = new Font("Segoe UI", Font.BOLD, 15);
	private static final Font FONT_TEXT_FIELD = new Font("Segoe UI", Font.PLAIN, 15);
	private static final Font FONT_BUTTON_STANDARD = new Font("Segoe UI", Font.BOLD, 14);
	private static final Font FONT_TABLE_HEADER = new Font("Segoe UI", Font.BOLD, 15);
	private static final Font FONT_TABLE_CELL = new Font("Segoe UI", Font.PLAIN, 14);

	private void styleButton(JButton button, Color background) {
		button.setBackground(background);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15)); // Tăng padding
	}

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
	// ==========================================================


	public DsPhieuDatThuoc_GUI(JFrame parent) {
		super(parent, "Danh Sách Phiếu Đặt Thuốc Chờ Thanh Toán", true); // Sửa tiêu đề
		initialize();
		setLocationRelativeTo(parent);
	}

	private void initialize() {
		setBounds(100, 100, 930, 600);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(COLOR_BACKGROUND_PRIMARY); // Nền dialog
		getContentPane().setLayout(null); // Giữ null layout

		// --- Panel Tiêu đề ---
		JPanel panel = new JPanel();
		panel.setBackground(COLOR_PRIMARY_BLUE); // Nền xanh
		panel.setBounds(0, 0, 914, 70); // Điều chỉnh height
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15)); // Căn giữa

		JLabel lblNewLabel = new JLabel("PHIẾU ĐẶT THUỐC CHỜ THANH TOÁN"); // Text tiêu đề
		lblNewLabel.setFont(FONT_TITLE_SECTION); // Font
		lblNewLabel.setForeground(Color.WHITE); // Màu chữ
		panel.add(lblNewLabel);

		// --- Panel Nội dung ---
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
		panel_1.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền
		panel_1.setBounds(10, 80, 894, 470); // Điều chỉnh vị trí/kích thước
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		// --- Bộ lọc tìm kiếm ---
		JLabel lblNewLabel_1 = new JLabel("SĐT Khách Hàng :");
		lblNewLabel_1.setFont(FONT_LABEL_BOLD); // Font
		lblNewLabel_1.setForeground(COLOR_TEXT_DARK); // Màu
		lblNewLabel_1.setBounds(20, 15, 163, 30); // Vị trí
		panel_1.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setFont(FONT_TEXT_FIELD); // Font
		textField.setBounds(190, 15, 280, 30); // Vị trí
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Tìm");
		btnNewButton_1.setFont(FONT_BUTTON_STANDARD); // Font
		styleButton(btnNewButton_1, COLOR_PRIMARY_BLUE); // Style
		btnNewButton_1.setBounds(485, 15, 89, 30); // Vị trí
		panel_1.add(btnNewButton_1);

		// --- Bảng danh sách ---
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền
		scrollPane.setBounds(10, 60, 874, 310); // Điều chỉnh vị trí/kích thước
		panel_1.add(scrollPane);

		table = new JTable() { // Style bảng
			 @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground());
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
		};
		applyCommonTableStyling(table); // Style chung
		table.setModel(new DefaultTableModel(
			new Object[][] {}, // Bỏ dòng null
			new String[] {"Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại"}
		));
		scrollPane.setViewportView(table);

		// --- Nút chức năng ---
		int buttonY_dspdt = 390; // Y nút
		int buttonW_dspdt = 174;
		int buttonH_dspdt = 45; // Height nút
		int buttonGap_dspdt = 20;

		JButton btnXaTtC = new JButton("Xóa Tất Cả");
		btnXaTtC.setFont(FONT_BUTTON_STANDARD); // Font
		styleButton(btnXaTtC, COLOR_DANGER_RED); // Style nút xóa
		btnXaTtC.setBounds(332, buttonY_dspdt, buttonW_dspdt, buttonH_dspdt);
		panel_1.add(btnXaTtC);

		JButton btnNewButton = new JButton("Xóa");
		btnNewButton.setFont(FONT_BUTTON_STANDARD); // Font
		styleButton(btnNewButton, COLOR_DANGER_RED); // Style nút xóa
		btnNewButton.setBounds(516, buttonY_dspdt, buttonW_dspdt, buttonH_dspdt);
		panel_1.add(btnNewButton);

		JButton btnThm = new JButton("Thanh Toán");
		btnThm.setFont(FONT_BUTTON_STANDARD); // Font
		styleButton(btnThm, COLOR_SUCCESS_GREEN); // Style nút chính
		btnThm.setBounds(700, buttonY_dspdt, buttonW_dspdt, buttonH_dspdt);
		panel_1.add(btnThm);
	}
}