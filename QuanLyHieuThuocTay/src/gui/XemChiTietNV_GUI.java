package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;

public class XemChiTietNV_GUI extends JDialog{

	private JFrame frame;
	private JTextField txtMaNV_XCTNV;
	private JTextField txtTenNV_XCTNV;
	private JTextField txtNgaySinh_XCTNV;
	private JTextField txtSDT_XCTNV;
	private JTextField txtGioiTinh_XCTNV;
	private JTextField txtChucVu_XCTNV;
	private JTextField txtDiaChi_XCTNV;
	private JTextField txtTaiKhoan_XCTNV;

	public XemChiTietNV_GUI(JFrame parent){
		super(parent, "Xem Chi Tiết Nhân Viên", true);
		initialize();
		setLocationRelativeTo(parent);
		getContentPane().setLayout(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 930, 600);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
        setTitle("Xem Chi Tiết Nhân Viên");
		
		JPanel panelAnhNV_XCTNV = new JPanel();
		panelAnhNV_XCTNV.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAnhNV_XCTNV.setBounds(79, 60, 320, 445);
		getContentPane().add(panelAnhNV_XCTNV);
		panelAnhNV_XCTNV.setLayout(null);
		
		JPanel panelTTNV_XCTNV = new JPanel();
		panelTTNV_XCTNV.setBorder(new TitledBorder(null, "Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 14), null));
		panelTTNV_XCTNV.setBounds(443, 65, 443, 445);
		getContentPane().add(panelTTNV_XCTNV);
		panelTTNV_XCTNV.setLayout(null);
		
		JLabel lblMaNV_XCTNV = new JLabel("Mã nhân viên:");
		lblMaNV_XCTNV.setBounds(10, 28, 131, 39);
		panelTTNV_XCTNV.add(lblMaNV_XCTNV);
		lblMaNV_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		
		JLabel lblTenNV_XCTNV = new JLabel("Tên nhân viên:");
		lblTenNV_XCTNV.setBounds(10, 78, 131, 39);
		panelTTNV_XCTNV.add(lblTenNV_XCTNV);
		lblTenNV_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		
		JLabel lblNgaySinh_XCTNV = new JLabel("Ngày sinh:");
		lblNgaySinh_XCTNV.setBounds(10, 128, 131, 39);
		panelTTNV_XCTNV.add(lblNgaySinh_XCTNV);
		lblNgaySinh_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		
		JLabel lblSDT_XCTNV = new JLabel("Số điện thoại:");
		lblSDT_XCTNV.setBounds(10, 178, 131, 39);
		panelTTNV_XCTNV.add(lblSDT_XCTNV);
		lblSDT_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		
		JLabel lblGioiTinh_XCTNV = new JLabel("Giới tính:");
		lblGioiTinh_XCTNV.setBounds(10, 228, 131, 39);
		panelTTNV_XCTNV.add(lblGioiTinh_XCTNV);
		lblGioiTinh_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		
		JLabel lblChucVu_XCTNV = new JLabel("Chức vụ:");
		lblChucVu_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblChucVu_XCTNV.setBounds(10, 278, 131, 39);
		panelTTNV_XCTNV.add(lblChucVu_XCTNV);
		
		JLabel lblDiaChi_XCTNV = new JLabel("Địa chỉ:");
		lblDiaChi_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblDiaChi_XCTNV.setBounds(10, 328, 131, 39);
		panelTTNV_XCTNV.add(lblDiaChi_XCTNV);
		
		JLabel lblTaiKhoan_XCTNV = new JLabel("Tài khoản:");
		lblTaiKhoan_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblTaiKhoan_XCTNV.setBounds(10, 378, 131, 39);
		panelTTNV_XCTNV.add(lblTaiKhoan_XCTNV);
		
		txtMaNV_XCTNV = new JTextField();
		txtMaNV_XCTNV.setEnabled(false);
		txtMaNV_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtMaNV_XCTNV.setBounds(171, 28, 240, 39);
		panelTTNV_XCTNV.add(txtMaNV_XCTNV);
		txtMaNV_XCTNV.setColumns(10);
		
		txtTenNV_XCTNV = new JTextField();
		txtTenNV_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtTenNV_XCTNV.setEnabled(false);
		txtTenNV_XCTNV.setColumns(10);
		txtTenNV_XCTNV.setBounds(171, 78, 240, 39);
		panelTTNV_XCTNV.add(txtTenNV_XCTNV);
		
		txtNgaySinh_XCTNV = new JTextField();
		txtNgaySinh_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtNgaySinh_XCTNV.setEnabled(false);
		txtNgaySinh_XCTNV.setColumns(10);
		txtNgaySinh_XCTNV.setBounds(171, 128, 240, 39);
		panelTTNV_XCTNV.add(txtNgaySinh_XCTNV);
		
		txtSDT_XCTNV = new JTextField();
		txtSDT_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtSDT_XCTNV.setEnabled(false);
		txtSDT_XCTNV.setColumns(10);
		txtSDT_XCTNV.setBounds(171, 178, 240, 39);
		panelTTNV_XCTNV.add(txtSDT_XCTNV);
		
		txtGioiTinh_XCTNV = new JTextField();
		txtGioiTinh_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtGioiTinh_XCTNV.setEnabled(false);
		txtGioiTinh_XCTNV.setColumns(10);
		txtGioiTinh_XCTNV.setBounds(171, 228, 240, 39);
		panelTTNV_XCTNV.add(txtGioiTinh_XCTNV);
		
		txtChucVu_XCTNV = new JTextField();
		txtChucVu_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtChucVu_XCTNV.setEnabled(false);
		txtChucVu_XCTNV.setColumns(10);
		txtChucVu_XCTNV.setBounds(171, 278, 240, 39);
		panelTTNV_XCTNV.add(txtChucVu_XCTNV);
		
		txtDiaChi_XCTNV = new JTextField();
		txtDiaChi_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtDiaChi_XCTNV.setEnabled(false);
		txtDiaChi_XCTNV.setColumns(10);
		txtDiaChi_XCTNV.setBounds(171, 328, 240, 39);
		panelTTNV_XCTNV.add(txtDiaChi_XCTNV);
		
		txtTaiKhoan_XCTNV = new JTextField();
		txtTaiKhoan_XCTNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtTaiKhoan_XCTNV.setEnabled(false);
		txtTaiKhoan_XCTNV.setColumns(10);
		txtTaiKhoan_XCTNV.setBounds(171, 378, 240, 39);
		panelTTNV_XCTNV.add(txtTaiKhoan_XCTNV);
	}
}
