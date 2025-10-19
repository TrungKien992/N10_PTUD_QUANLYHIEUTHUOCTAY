package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class XemChiTietNV_GUI extends JDialog{

	private JTextField txtMaNV_XCTNV;
	private JTextField txtTenNV_XCTNV;
	private JTextField txtNgaySinh_XCTNV; // Nên dùng JLabel để hiển thị
	private JTextField txtSDT_XCTNV;
	private JTextField txtGioiTinh_XCTNV; // Nên dùng JLabel
	private JTextField txtChucVu_XCTNV; // Nên dùng JLabel
	private JTextField txtDiaChi_XCTNV;
	private JTextField txtTaiKhoan_XCTNV; // Nên dùng JLabel
	private JLabel lblAnhNV_XCTNV; // Dùng JLabel để hiển thị ảnh

	// ========== THÊM HẰNG SỐ MÀU/FONT ==========
	private static final Color COLOR_BACKGROUND_PRIMARY = new Color(240, 242, 245);
	private static final Color COLOR_CARD_BACKGROUND = Color.WHITE;
	private static final Color COLOR_PRIMARY_BLUE = new Color(0, 123, 255);
	private static final Color COLOR_TEXT_DARK = new Color(33, 37, 41);
	private static final Color COLOR_TEXT_MUTED = new Color(108, 117, 125);
	private static final Color COLOR_BORDER_LIGHT = new Color(222, 226, 230);
	private static final Font FONT_TITLE_SECTION = new Font("Segoe UI", Font.BOLD, 26);
	private static final Font FONT_LABEL_BOLD = new Font("Segoe UI", Font.BOLD, 15);
	private static final Font FONT_TEXT_FIELD = new Font("Segoe UI", Font.PLAIN, 15);
	private static final Font FONT_DETAIL_ITALIC = new Font("Segoe UI", Font.ITALIC, 15);
	// ==========================================================


	public XemChiTietNV_GUI(JFrame parent){
		super(parent, "Xem Chi Tiết Nhân Viên", true);
		initialize();
		setLocationRelativeTo(parent);
	}

	private void initialize() {
		setBounds(100, 100, 930, 600);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(COLOR_BACKGROUND_PRIMARY); // Nền dialog
		getContentPane().setLayout(null); // Giữ null layout
        setTitle("Xem Chi Tiết Nhân Viên");

		// --- Panel Ảnh ---
		JPanel panelAnhNV_XCTNV = new JPanel();
		panelAnhNV_XCTNV.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền
		panelAnhNV_XCTNV.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
		panelAnhNV_XCTNV.setBounds(40, 40, 320, 480); // Điều chỉnh vị trí/kích thước
		getContentPane().add(panelAnhNV_XCTNV);
		panelAnhNV_XCTNV.setLayout(new BorderLayout(0, 0)); // BorderLayout

		lblAnhNV_XCTNV = new JLabel("Chưa có ảnh", SwingConstants.CENTER); // Placeholder
		lblAnhNV_XCTNV.setFont(FONT_DETAIL_ITALIC);
		lblAnhNV_XCTNV.setForeground(COLOR_TEXT_MUTED);
		panelAnhNV_XCTNV.add(lblAnhNV_XCTNV, BorderLayout.CENTER);

		// --- Panel Thông tin ---
		JPanel panelTTNV_XCTNV = new JPanel();
		panelTTNV_XCTNV.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
		panelTTNV_XCTNV.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
             "Thông tin nhân viên", TitledBorder.LEADING, TitledBorder.TOP,
             FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        )); // Viền và tiêu đề
		panelTTNV_XCTNV.setBounds(380, 40, 510, 480); // Điều chỉnh vị trí/kích thước
		getContentPane().add(panelTTNV_XCTNV);
		panelTTNV_XCTNV.setLayout(null);

		// Định nghĩa vị trí/kích thước
		int labelX_xctnv = 20;
		int valueX_xctnv = 160;
		int valueWidth_xctnv = 320; // Tăng width
		int startY_xctnv = 30;
		int height_xctnv = 30; // Giảm height
		int vGap_xctnv = 10; // Giảm gap

		JLabel lblMaNV_XCTNV = new JLabel("Mã nhân viên:");
		lblMaNV_XCTNV.setFont(FONT_LABEL_BOLD); // Font
		lblMaNV_XCTNV.setForeground(COLOR_TEXT_DARK); // Màu
		lblMaNV_XCTNV.setBounds(labelX_xctnv, startY_xctnv, 131, height_xctnv);
		panelTTNV_XCTNV.add(lblMaNV_XCTNV);

		txtMaNV_XCTNV = new JTextField(); // Nên dùng JLabel để hiển thị
		txtMaNV_XCTNV.setFont(FONT_TEXT_FIELD); // Font
		txtMaNV_XCTNV.setEditable(false); // Không sửa
		txtMaNV_XCTNV.setBorder(null); // Bỏ viền
		txtMaNV_XCTNV.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
		txtMaNV_XCTNV.setBounds(valueX_xctnv, startY_xctnv, valueWidth_xctnv, height_xctnv);
		panelTTNV_XCTNV.add(txtMaNV_XCTNV);
		txtMaNV_XCTNV.setColumns(10);

		JLabel lblTenNV_XCTNV = new JLabel("Tên nhân viên:");
		lblTenNV_XCTNV.setFont(FONT_LABEL_BOLD);
		lblTenNV_XCTNV.setForeground(COLOR_TEXT_DARK);
		lblTenNV_XCTNV.setBounds(labelX_xctnv, startY_xctnv + height_xctnv + vGap_xctnv, 131, height_xctnv);
		panelTTNV_XCTNV.add(lblTenNV_XCTNV);

		txtTenNV_XCTNV = new JTextField();
		txtTenNV_XCTNV.setFont(FONT_TEXT_FIELD);
		txtTenNV_XCTNV.setEditable(false);
		txtTenNV_XCTNV.setBorder(null);
		txtTenNV_XCTNV.setBackground(COLOR_CARD_BACKGROUND);
		txtTenNV_XCTNV.setColumns(10);
		txtTenNV_XCTNV.setBounds(valueX_xctnv, startY_xctnv + height_xctnv + vGap_xctnv, valueWidth_xctnv, height_xctnv);
		panelTTNV_XCTNV.add(txtTenNV_XCTNV);

		JLabel lblNgaySinh_XCTNV = new JLabel("Ngày sinh:");
		lblNgaySinh_XCTNV.setFont(FONT_LABEL_BOLD);
		lblNgaySinh_XCTNV.setForeground(COLOR_TEXT_DARK);
		lblNgaySinh_XCTNV.setBounds(labelX_xctnv, startY_xctnv + 2*(height_xctnv + vGap_xctnv), 131, height_xctnv);
		panelTTNV_XCTNV.add(lblNgaySinh_XCTNV);

		txtNgaySinh_XCTNV = new JTextField(); // Nên dùng JLabel
		txtNgaySinh_XCTNV.setFont(FONT_TEXT_FIELD);
		txtNgaySinh_XCTNV.setEditable(false);
		txtNgaySinh_XCTNV.setBorder(null);
		txtNgaySinh_XCTNV.setBackground(COLOR_CARD_BACKGROUND);
		txtNgaySinh_XCTNV.setColumns(10);
		txtNgaySinh_XCTNV.setBounds(valueX_xctnv, startY_xctnv + 2*(height_xctnv + vGap_xctnv), valueWidth_xctnv, height_xctnv);
		panelTTNV_XCTNV.add(txtNgaySinh_XCTNV);

		JLabel lblSDT_XCTNV = new JLabel("Số điện thoại:");
		lblSDT_XCTNV.setFont(FONT_LABEL_BOLD);
		lblSDT_XCTNV.setForeground(COLOR_TEXT_DARK);
		lblSDT_XCTNV.setBounds(labelX_xctnv, startY_xctnv + 3*(height_xctnv + vGap_xctnv), 131, height_xctnv);
		panelTTNV_XCTNV.add(lblSDT_XCTNV);

		txtSDT_XCTNV = new JTextField();
		txtSDT_XCTNV.setFont(FONT_TEXT_FIELD);
		txtSDT_XCTNV.setEditable(false);
		txtSDT_XCTNV.setBorder(null);
		txtSDT_XCTNV.setBackground(COLOR_CARD_BACKGROUND);
		txtSDT_XCTNV.setColumns(10);
		txtSDT_XCTNV.setBounds(valueX_xctnv, startY_xctnv + 3*(height_xctnv + vGap_xctnv), valueWidth_xctnv, height_xctnv);
		panelTTNV_XCTNV.add(txtSDT_XCTNV);

		JLabel lblGioiTinh_XCTNV = new JLabel("Giới tính:");
		lblGioiTinh_XCTNV.setFont(FONT_LABEL_BOLD);
		lblGioiTinh_XCTNV.setForeground(COLOR_TEXT_DARK);
		lblGioiTinh_XCTNV.setBounds(labelX_xctnv, startY_xctnv + 4*(height_xctnv + vGap_xctnv), 131, height_xctnv);
		panelTTNV_XCTNV.add(lblGioiTinh_XCTNV);

		txtGioiTinh_XCTNV = new JTextField(); // Nên dùng JLabel
		txtGioiTinh_XCTNV.setFont(FONT_TEXT_FIELD);
		txtGioiTinh_XCTNV.setEditable(false);
		txtGioiTinh_XCTNV.setBorder(null);
		txtGioiTinh_XCTNV.setBackground(COLOR_CARD_BACKGROUND);
		txtGioiTinh_XCTNV.setColumns(10);
		txtGioiTinh_XCTNV.setBounds(valueX_xctnv, startY_xctnv + 4*(height_xctnv + vGap_xctnv), valueWidth_xctnv, height_xctnv);
		panelTTNV_XCTNV.add(txtGioiTinh_XCTNV);

		JLabel lblChucVu_XCTNV = new JLabel("Chức vụ:");
		lblChucVu_XCTNV.setFont(FONT_LABEL_BOLD);
		lblChucVu_XCTNV.setForeground(COLOR_TEXT_DARK);
		lblChucVu_XCTNV.setBounds(labelX_xctnv, startY_xctnv + 5*(height_xctnv + vGap_xctnv), 131, height_xctnv);
		panelTTNV_XCTNV.add(lblChucVu_XCTNV);

		txtChucVu_XCTNV = new JTextField(); // Nên dùng JLabel
		txtChucVu_XCTNV.setFont(FONT_TEXT_FIELD);
		txtChucVu_XCTNV.setEditable(false);
		txtChucVu_XCTNV.setBorder(null);
		txtChucVu_XCTNV.setBackground(COLOR_CARD_BACKGROUND);
		txtChucVu_XCTNV.setColumns(10);
		txtChucVu_XCTNV.setBounds(valueX_xctnv, startY_xctnv + 5*(height_xctnv + vGap_xctnv), valueWidth_xctnv, height_xctnv);
		panelTTNV_XCTNV.add(txtChucVu_XCTNV);

		JLabel lblDiaChi_XCTNV = new JLabel("Địa chỉ:");
		lblDiaChi_XCTNV.setFont(FONT_LABEL_BOLD);
		lblDiaChi_XCTNV.setForeground(COLOR_TEXT_DARK);
		lblDiaChi_XCTNV.setBounds(labelX_xctnv, startY_xctnv + 6*(height_xctnv + vGap_xctnv), 131, height_xctnv);
		panelTTNV_XCTNV.add(lblDiaChi_XCTNV);

		txtDiaChi_XCTNV = new JTextField();
		txtDiaChi_XCTNV.setFont(FONT_TEXT_FIELD);
		txtDiaChi_XCTNV.setEditable(false);
		txtDiaChi_XCTNV.setBorder(null);
		txtDiaChi_XCTNV.setBackground(COLOR_CARD_BACKGROUND);
		txtDiaChi_XCTNV.setColumns(10);
		txtDiaChi_XCTNV.setBounds(valueX_xctnv, startY_xctnv + 6*(height_xctnv + vGap_xctnv), valueWidth_xctnv, height_xctnv);
		panelTTNV_XCTNV.add(txtDiaChi_XCTNV);

		JLabel lblTaiKhoan_XCTNV = new JLabel("Tài khoản:");
		lblTaiKhoan_XCTNV.setFont(FONT_LABEL_BOLD);
		lblTaiKhoan_XCTNV.setForeground(COLOR_TEXT_DARK);
		lblTaiKhoan_XCTNV.setBounds(labelX_xctnv, startY_xctnv + 7*(height_xctnv + vGap_xctnv), 131, height_xctnv);
		panelTTNV_XCTNV.add(lblTaiKhoan_XCTNV);

		txtTaiKhoan_XCTNV = new JTextField(); // Nên dùng JLabel
		txtTaiKhoan_XCTNV.setFont(FONT_TEXT_FIELD);
		txtTaiKhoan_XCTNV.setEditable(false);
		txtTaiKhoan_XCTNV.setBorder(null);
		txtTaiKhoan_XCTNV.setBackground(COLOR_CARD_BACKGROUND);
		txtTaiKhoan_XCTNV.setColumns(10);
		txtTaiKhoan_XCTNV.setBounds(valueX_xctnv, startY_xctnv + 7*(height_xctnv + vGap_xctnv), valueWidth_xctnv, height_xctnv);
		panelTTNV_XCTNV.add(txtTaiKhoan_XCTNV);
	}
}