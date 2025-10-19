package gui;

import java.awt.EventQueue;
import javax.swing.*; // Import * cho tiện
import javax.swing.border.Border; // Import Border
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*; // Import * cho tiện
import java.awt.event.*; // Import * cho tiện

public class XemchitietHD_GUI extends JDialog {
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
	private static final Font FONT_SUMMARY_TOTAL = new Font("Segoe UI", Font.BOLD, 20);
	private static final Font FONT_DETAIL_ITALIC = new Font("Segoe UI", Font.ITALIC, 15);

	private void styleButton(JButton button, Color background) {
		button.setBackground(background);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
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

	public XemchitietHD_GUI(JFrame parent) {
		super(parent, "Chi Tiết Hóa Đơn", true);
		// Xóa setFont ở đây
		setLocationRelativeTo(parent);
		// Đặt size và layout ở initialize
		initialize(); // Gọi initialize ở cuối constructor
	}

	private void initialize() {
		setBounds(100, 100, 1000, 900);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(COLOR_BACKGROUND_PRIMARY); // Nền dialog
		getContentPane().setLayout(null); // Giữ null layout

		// --- Thông tin cửa hàng ---
		JLabel lblNewLabel = new JLabel("Tên Đơn Vị :");
		lblNewLabel.setFont(FONT_LABEL_BOLD); // Font
		lblNewLabel.setForeground(COLOR_TEXT_DARK); // Màu chữ
		lblNewLabel.setBounds(20, 11, 114, 30);
		getContentPane().add(lblNewLabel);

		JLabel lblHiuThucTy = new JLabel("Hiệu Thuốc Tây Khánh Hưng");
		lblHiuThucTy.setFont(FONT_LABEL_BOLD); // Font
		lblHiuThucTy.setForeground(COLOR_PRIMARY_BLUE); // Màu nhấn
		lblHiuThucTy.setBounds(144, 11, 300, 30); // Tăng width
		getContentPane().add(lblHiuThucTy);

		JLabel lblaCh = new JLabel("Địa Chỉ :");
		lblaCh.setFont(FONT_LABEL_BOLD);
		lblaCh.setForeground(COLOR_TEXT_DARK);
		lblaCh.setBounds(20, 52, 114, 30);
		getContentPane().add(lblaCh);

		JLabel lblAbc = new JLabel("123 Đường ABC, Quận XYZ, TP.HCM"); // Ví dụ
		lblAbc.setFont(FONT_TEXT_FIELD); // Font thường
		lblAbc.setForeground(COLOR_TEXT_MUTED); // Màu nhạt
		lblAbc.setBounds(144, 52, 830, 30);
		getContentPane().add(lblAbc);

		// --- Tiêu đề hóa đơn ---
		JLabel lblNewLabel_1 = new JLabel("Hóa Đơn Bán Hàng");
		lblNewLabel_1.setFont(FONT_TITLE_SECTION); // Font tiêu đề section
		lblNewLabel_1.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
		lblNewLabel_1.setBounds(0, 90, 984, 47); // Căn giữa theo width
		getContentPane().add(lblNewLabel_1);

		// --- Thông tin hóa đơn ---
		JLabel lblMHan = new JLabel("Mã Hóa Đơn :");
		lblMHan.setFont(FONT_LABEL_BOLD);
		lblMHan.setForeground(COLOR_TEXT_DARK);
		lblMHan.setBounds(20, 140, 148, 30);
		getContentPane().add(lblMHan);

		JLabel lblHd = new JLabel("HD000001"); // Dữ liệu mẫu
		lblHd.setForeground(COLOR_DANGER_RED); // Màu đỏ
		lblHd.setFont(FONT_LABEL_BOLD); // Font đậm
		lblHd.setBounds(178, 140, 200, 30); // Tăng width
		getContentPane().add(lblHd);

		JLabel lblTnNhnVin = new JLabel("Tên Nhân Viên :");
		lblTnNhnVin.setFont(FONT_LABEL_BOLD);
		lblTnNhnVin.setForeground(COLOR_TEXT_DARK);
		lblTnNhnVin.setBounds(20, 181, 148, 30);
		getContentPane().add(lblTnNhnVin);

		JLabel lblNguynTrungKin = new JLabel("Nguyễn Trung Kiên"); // Dữ liệu mẫu
		lblNguynTrungKin.setFont(FONT_TEXT_FIELD); // Font thường
		lblNguynTrungKin.setForeground(COLOR_TEXT_DARK);
		lblNguynTrungKin.setBounds(178, 181, 400, 30); // Tăng width
		getContentPane().add(lblNguynTrungKin);

		JLabel lblTnKhchHng = new JLabel("Tên Khách Hàng :");
		lblTnKhchHng.setFont(FONT_LABEL_BOLD);
		lblTnKhchHng.setForeground(COLOR_TEXT_DARK);
		lblTnKhchHng.setBounds(20, 222, 148, 30);
		getContentPane().add(lblTnKhchHng);

		JLabel lblNguynNgc = new JLabel("Nguyễn Ngô Đức Mạnh"); // Dữ liệu mẫu
		lblNguynNgc.setFont(FONT_TEXT_FIELD);
		lblNguynNgc.setForeground(COLOR_TEXT_DARK);
		lblNguynNgc.setBounds(178, 222, 400, 30); // Tăng width
		getContentPane().add(lblNguynNgc);

		JLabel lblNgyLp = new JLabel("Ngày Lập :");
		lblNgyLp.setFont(FONT_LABEL_BOLD);
		lblNgyLp.setForeground(COLOR_TEXT_DARK);
		lblNgyLp.setBounds(693, 181, 102, 30);
		getContentPane().add(lblNgyLp);

		JLabel lblNgyLp_1 = new JLabel("19/10/2025"); // Dữ liệu mẫu
		lblNgyLp_1.setFont(FONT_TEXT_FIELD);
		lblNgyLp_1.setForeground(COLOR_TEXT_DARK);
		lblNgyLp_1.setBounds(805, 181, 150, 30); // Tăng width
		getContentPane().add(lblNgyLp_1);

		// --- Bảng chi tiết ---
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền
		scrollPane.setBounds(10, 270, 964, 430); // Điều chỉnh vị trí/kích thước
		getContentPane().add(scrollPane);

		table = new JTable() { // Style bảng
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (!isRowSelected(row)) {
					c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
					c.setForeground(this.getForeground()); // Dùng this
				} else {
					c.setBackground(COLOR_PRIMARY_BLUE);
					// Không set foreground trắng
				}
				return c;
			}
		};
		applyCommonTableStyling(table); // Áp dụng style chung
		table.setModel(new DefaultTableModel(
			new Object[][] {}, // Bỏ dòng null
			new String[] {"STT", "Tên Thuốc", "Số Lượng", "Giá Tiền", "Thành Tiền"}
		));
		// Căn chỉnh cột nếu cần
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		scrollPane.setViewportView(table);

		// --- Thông tin thanh toán ---
		int summaryY = 715; // Y bắt đầu của phần tổng kết
		int summaryLabelX = 20;
		int summaryValueX = 178;
		int summaryLabelX2 = 668;
		int summaryValueX2 = 833;
		int summaryHeight = 30;
		int summaryVGap = 10;

		JLabel lblTinKhcha = new JLabel("Tiền Khách Đưa :");
		lblTinKhcha.setFont(FONT_LABEL_BOLD);
		lblTinKhcha.setForeground(COLOR_TEXT_DARK);
		lblTinKhcha.setBounds(summaryLabelX, summaryY, 148, summaryHeight);
		getContentPane().add(lblTinKhcha);

		JLabel lblNgyLp_2_3 = new JLabel("11.000.000 VND"); // Dữ liệu mẫu
		lblNgyLp_2_3.setForeground(COLOR_PRIMARY_BLUE); // Màu xanh
		lblNgyLp_2_3.setFont(FONT_LABEL_BOLD); // Font đậm
		lblNgyLp_2_3.setBounds(summaryValueX, summaryY, 200, summaryHeight); // Tăng width
		getContentPane().add(lblNgyLp_2_3);

		JLabel lblTinTha = new JLabel("Tiền Thừa :");
		lblTinTha.setFont(FONT_LABEL_BOLD);
		lblTinTha.setForeground(COLOR_TEXT_DARK);
		lblTinTha.setBounds(summaryLabelX, summaryY + summaryHeight + summaryVGap, 148, summaryHeight);
		getContentPane().add(lblTinTha);

		JLabel lblNgyLp_2_4 = new JLabel("0 VND"); // Dữ liệu mẫu
		lblNgyLp_2_4.setForeground(COLOR_SUCCESS_GREEN); // Màu xanh lá
		lblNgyLp_2_4.setFont(FONT_LABEL_BOLD);
		lblNgyLp_2_4.setBounds(summaryValueX, summaryY + summaryHeight + summaryVGap, 200, summaryHeight);
		getContentPane().add(lblNgyLp_2_4);

		JLabel lblTngThnhTin = new JLabel("Tổng Tiền Hàng :");
		lblTngThnhTin.setFont(FONT_LABEL_BOLD);
		lblTngThnhTin.setForeground(COLOR_TEXT_DARK);
		lblTngThnhTin.setBounds(summaryLabelX2, summaryY, 155, summaryHeight);
		getContentPane().add(lblTngThnhTin);

		JLabel lblNgyLp_2_2 = new JLabel("10.000.000 VND"); // Dữ liệu mẫu
		lblNgyLp_2_2.setForeground(COLOR_TEXT_DARK);
		lblNgyLp_2_2.setFont(FONT_LABEL_BOLD);
		lblNgyLp_2_2.setBounds(summaryValueX2, summaryY, 141, summaryHeight);
		getContentPane().add(lblNgyLp_2_2);

		JLabel lblThuVat = new JLabel("Thuế (10% VAT) :");
		lblThuVat.setFont(FONT_LABEL_BOLD);
		lblThuVat.setForeground(COLOR_TEXT_DARK);
		lblThuVat.setBounds(summaryLabelX2, summaryY + summaryHeight + summaryVGap, 155, summaryHeight);
		getContentPane().add(lblThuVat);

		JLabel lblNgyLp_2 = new JLabel("1.000.000 VND"); // Dữ liệu mẫu
		lblNgyLp_2.setForeground(COLOR_TEXT_DARK);
		lblNgyLp_2.setFont(FONT_LABEL_BOLD);
		lblNgyLp_2.setBounds(summaryValueX2, summaryY + summaryHeight + summaryVGap, 141, summaryHeight);
		getContentPane().add(lblNgyLp_2);

		JLabel lblTngCng = new JLabel("Tổng Cộng :");
		lblTngCng.setFont(FONT_SUMMARY_TOTAL); // Font tổng cộng
		lblTngCng.setForeground(COLOR_TEXT_DARK);
		lblTngCng.setBounds(summaryLabelX, summaryY + 2*(summaryHeight + summaryVGap) + 10, 148, summaryHeight); // Xuống dòng
		getContentPane().add(lblTngCng);

		JLabel lblNgyLp_2_1 = new JLabel("11.000.000 VND"); // Dữ liệu mẫu
		lblNgyLp_2_1.setForeground(COLOR_DANGER_RED); // Màu đỏ
		lblNgyLp_2_1.setFont(FONT_SUMMARY_TOTAL); // Font tổng cộng
		lblNgyLp_2_1.setBounds(summaryValueX, summaryY + 2*(summaryHeight + summaryVGap) + 10, 250, summaryHeight); // Xuống dòng
		getContentPane().add(lblNgyLp_2_1);

		// --- Nút Xuất Hóa Đơn ---
		JButton btnNewButton = new JButton("Xuất Hóa Đơn");
		btnNewButton.setFont(FONT_BUTTON_STANDARD); // Font nút
		styleButton(btnNewButton, COLOR_SUCCESS_GREEN); // Style nút
		btnNewButton.setBounds(800, 794, 174, 47); // Điều chỉnh vị trí
		getContentPane().add(btnNewButton);
	}
}