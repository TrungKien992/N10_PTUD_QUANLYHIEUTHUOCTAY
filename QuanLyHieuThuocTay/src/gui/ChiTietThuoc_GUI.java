package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class ChiTietThuoc_GUI extends JDialog{

	// ========== THÊM HẰNG SỐ MÀU/FONT ==========
	private static final Color COLOR_BACKGROUND_PRIMARY = new Color(240, 242, 245);
	private static final Color COLOR_CARD_BACKGROUND = Color.WHITE;
	private static final Color COLOR_PRIMARY_BLUE = new Color(0, 123, 255);
	private static final Color COLOR_DANGER_RED = new Color(220, 53, 69);
	private static final Color COLOR_TEXT_DARK = new Color(33, 37, 41);
	private static final Color COLOR_TEXT_MUTED = new Color(108, 117, 125);
	private static final Color COLOR_BORDER_LIGHT = new Color(222, 226, 230);
	private static final Font FONT_TITLE_SECTION = new Font("Segoe UI", Font.BOLD, 26);
	private static final Font FONT_LABEL_BOLD = new Font("Segoe UI", Font.BOLD, 15);
	private static final Font FONT_TEXT_FIELD = new Font("Segoe UI", Font.PLAIN, 15); // Dùng cho giá trị hiển thị
	private static final Font FONT_DETAIL_ITALIC = new Font("Segoe UI", Font.ITALIC, 15);
	// ==========================================================

	/**
	 * Create the application.
	 */
	public ChiTietThuoc_GUI(JFrame parent) {
		super(parent, "Chi Tiết Thuốc", true);
		initialize();
		setLocationRelativeTo(parent);
	}

	/**
	 * Initialize the contents of the frame.
	 */
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

		JLabel lblNewLabel = new JLabel("Chi Tiết Thuốc");
		lblNewLabel.setFont(FONT_TITLE_SECTION); // Font
		lblNewLabel.setForeground(Color.WHITE); // Chữ trắng
		panel.add(lblNewLabel);

		// --- Panel Nội dung ---
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
		panel_1.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền
		panel_1.setBounds(10, 80, 894, 470); // Điều chỉnh vị trí/kích thước
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		// --- Panel Ảnh ---
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền
		panel_2.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền xám
		panel_2.setBounds(35, 35, 330, 300);
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0)); // BorderLayout

		JLabel lblNewLabel_1 = new JLabel("Chưa có ảnh", SwingConstants.CENTER); // Placeholder
		lblNewLabel_1.setFont(FONT_DETAIL_ITALIC);
		lblNewLabel_1.setForeground(COLOR_TEXT_MUTED);
		panel_2.add(lblNewLabel_1, BorderLayout.CENTER);

		// --- Thông tin chi tiết ---
		int labelX_ctt = 400; // X cột label
		int valueX_ctt = 550; // X cột giá trị
		int valueWidth_ctt = 320; // Width giá trị
		int startY_ctt = 35; // Y dòng đầu
		int height_ctt = 28; // Height dòng
		int vGap_ctt = 8; // Gap dọc

		JLabel lblNewLabel_2 = new JLabel("Mã Thuốc :");
		lblNewLabel_2.setFont(FONT_LABEL_BOLD); // Font
		lblNewLabel_2.setForeground(COLOR_TEXT_DARK); // Màu
		lblNewLabel_2.setBounds(labelX_ctt, startY_ctt, 132, height_ctt); // Điều chỉnh width
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_15 = new JLabel("HIENMATHUOC"); // Giá trị mẫu
		lblNewLabel_2_15.setForeground(COLOR_DANGER_RED); // Màu đỏ
		lblNewLabel_2_15.setFont(FONT_TEXT_FIELD); // Font giá trị
		lblNewLabel_2_15.setBounds(valueX_ctt, startY_ctt, valueWidth_ctt, height_ctt);
		panel_1.add(lblNewLabel_2_15);

		JLabel lblNewLabel_2_1 = new JLabel("Tên Thuốc :");
		lblNewLabel_2_1.setFont(FONT_LABEL_BOLD);
		lblNewLabel_2_1.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_1.setBounds(labelX_ctt, startY_ctt + height_ctt + vGap_ctt, 132, height_ctt);
		panel_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_14 = new JLabel("HIENTENTHUOC");
		lblNewLabel_2_14.setForeground(COLOR_TEXT_DARK); // Màu thường
		lblNewLabel_2_14.setFont(FONT_TEXT_FIELD);
		lblNewLabel_2_14.setBounds(valueX_ctt, startY_ctt + height_ctt + vGap_ctt, valueWidth_ctt, height_ctt);
		panel_1.add(lblNewLabel_2_14);

		JLabel lblNewLabel_2_2 = new JLabel("Số Lượng :");
		lblNewLabel_2_2.setFont(FONT_LABEL_BOLD);
		lblNewLabel_2_2.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_2.setBounds(labelX_ctt, startY_ctt + 2*(height_ctt + vGap_ctt), 132, height_ctt);
		panel_1.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_13 = new JLabel("HIENSOLUONG");
		lblNewLabel_2_13.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_13.setFont(FONT_TEXT_FIELD);
		lblNewLabel_2_13.setBounds(valueX_ctt, startY_ctt + 2*(height_ctt + vGap_ctt), valueWidth_ctt, height_ctt);
		panel_1.add(lblNewLabel_2_13);

		JLabel lblNewLabel_2_3 = new JLabel("Giá Nhập :");
		lblNewLabel_2_3.setFont(FONT_LABEL_BOLD);
		lblNewLabel_2_3.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_3.setBounds(labelX_ctt, startY_ctt + 3*(height_ctt + vGap_ctt), 132, height_ctt);
		panel_1.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_12 = new JLabel("HIENGIANHAP"); // Đổi tên cho đúng
		lblNewLabel_2_12.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_12.setFont(FONT_TEXT_FIELD);
		lblNewLabel_2_12.setBounds(valueX_ctt, startY_ctt + 3*(height_ctt + vGap_ctt), valueWidth_ctt, height_ctt);
		panel_1.add(lblNewLabel_2_12);

		JLabel lblNewLabel_2_4 = new JLabel("Giá Bán :");
		lblNewLabel_2_4.setFont(FONT_LABEL_BOLD);
		lblNewLabel_2_4.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_4.setBounds(labelX_ctt, startY_ctt + 4*(height_ctt + vGap_ctt), 132, height_ctt);
		panel_1.add(lblNewLabel_2_4);

		JLabel lblNewLabel_2_11 = new JLabel("HIENGIABAN"); // Đổi tên cho đúng
		lblNewLabel_2_11.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_11.setFont(FONT_TEXT_FIELD);
		lblNewLabel_2_11.setBounds(valueX_ctt, startY_ctt + 4*(height_ctt + vGap_ctt), valueWidth_ctt, height_ctt);
		panel_1.add(lblNewLabel_2_11);

		JLabel lblNewLabel_2_5 = new JLabel("Đơn Vị Tính :");
		lblNewLabel_2_5.setFont(FONT_LABEL_BOLD);
		lblNewLabel_2_5.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_5.setBounds(labelX_ctt, startY_ctt + 5*(height_ctt + vGap_ctt), 132, height_ctt);
		panel_1.add(lblNewLabel_2_5);

		JLabel lblNewLabel_2_10 = new JLabel("HIENDONVITINH");
		lblNewLabel_2_10.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_10.setFont(FONT_TEXT_FIELD);
		lblNewLabel_2_10.setBounds(valueX_ctt, startY_ctt + 5*(height_ctt + vGap_ctt), valueWidth_ctt, height_ctt);
		panel_1.add(lblNewLabel_2_10);

		JLabel lblNewLabel_2_6 = new JLabel("Nhà Cung Cấp :");
		lblNewLabel_2_6.setFont(FONT_LABEL_BOLD);
		lblNewLabel_2_6.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_6.setBounds(labelX_ctt, startY_ctt + 6*(height_ctt + vGap_ctt), 132, height_ctt);
		panel_1.add(lblNewLabel_2_6);

		JLabel lblNewLabel_2_9 = new JLabel("HIENNHACUNGCAP");
		lblNewLabel_2_9.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_9.setFont(FONT_TEXT_FIELD);
		lblNewLabel_2_9.setBounds(valueX_ctt, startY_ctt + 6*(height_ctt + vGap_ctt), valueWidth_ctt, height_ctt);
		panel_1.add(lblNewLabel_2_9);

		JLabel lblNewLabel_2_6_1 = new JLabel("Hạn Sử Dụng :");
		lblNewLabel_2_6_1.setFont(FONT_LABEL_BOLD);
		lblNewLabel_2_6_1.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_6_1.setBounds(labelX_ctt, startY_ctt + 7*(height_ctt + vGap_ctt), 132, height_ctt);
		panel_1.add(lblNewLabel_2_6_1);

		JLabel lblNewLabel_2_8 = new JLabel("HIENHANSUDUNG");
		lblNewLabel_2_8.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_8.setFont(FONT_TEXT_FIELD);
		lblNewLabel_2_8.setBounds(valueX_ctt, startY_ctt + 7*(height_ctt + vGap_ctt), valueWidth_ctt, height_ctt);
		panel_1.add(lblNewLabel_2_8);

		JLabel lblNewLabel_2_6_2 = new JLabel("Tên Kệ Thuốc :");
		lblNewLabel_2_6_2.setFont(FONT_LABEL_BOLD);
		lblNewLabel_2_6_2.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_6_2.setBounds(labelX_ctt, startY_ctt + 8*(height_ctt + vGap_ctt), 132, height_ctt);
		panel_1.add(lblNewLabel_2_6_2);

		JLabel lblNewLabel_2_7 = new JLabel("HIENTENKETHUOC");
		lblNewLabel_2_7.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_7.setFont(FONT_TEXT_FIELD);
		lblNewLabel_2_7.setBounds(valueX_ctt, startY_ctt + 8*(height_ctt + vGap_ctt), valueWidth_ctt, height_ctt);
		panel_1.add(lblNewLabel_2_7);

		JLabel lblNewLabel_2_6_3 = new JLabel("Thành Phần :");
		lblNewLabel_2_6_3.setFont(FONT_LABEL_BOLD);
		lblNewLabel_2_6_3.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_2_6_3.setBounds(35, 350, 132, 24); // Vị trí label thành phần
		panel_1.add(lblNewLabel_2_6_3);

		// Dùng JScrollPane cho Thành phần
		JScrollPane scrollPaneThanhPhan = new JScrollPane();
		scrollPaneThanhPhan.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
		scrollPaneThanhPhan.setBounds(35, 380, 850, 75); // Vị trí scroll pane
		panel_1.add(scrollPaneThanhPhan);

		JTextArea lblNewLabel_3 = new JTextArea("HIENTHANHPHAN..."); // Đổi thành JTextArea
		lblNewLabel_3.setEditable(false); // Không cho sửa
		lblNewLabel_3.setLineWrap(true);
		lblNewLabel_3.setWrapStyleWord(true);
		lblNewLabel_3.setFont(FONT_TEXT_FIELD); // Font
		lblNewLabel_3.setForeground(COLOR_TEXT_DARK); // Màu
		lblNewLabel_3.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền xám nhạt
		lblNewLabel_3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Padding
		scrollPaneThanhPhan.setViewportView(lblNewLabel_3); // Add vào scroll pane
	}
}