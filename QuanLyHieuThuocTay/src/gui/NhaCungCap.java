package gui;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class NhaCungCap extends JFrame {
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private JTable tblNhaCungCap;
	private JTextField txtTiemKiem;
	public NhaCungCap() {
		getContentPane().setBackground(new Color(204, 204, 204));
		getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 12));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 255));
		panel.setForeground(new Color(0, 0, 51));
		panel.setBounds(10, 11, 1316, 51);
		getContentPane().add(panel);
		
		JLabel lblTitle = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(lblTitle);
		
		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBackground(new Color(0, 153, 204));
		pnlThongTin.setBorder(new TitledBorder(null, "Th\u00F4ng tin nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTin.setBounds(10, 84, 292, 188);
		getContentPane().add(pnlThongTin);
		pnlThongTin.setLayout(null);
		
		JLabel lblMaNCC = new JLabel("Mã nhà cung cấp:");
		lblMaNCC.setBackground(new Color(0, 153, 204));
		lblMaNCC.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblMaNCC.setBounds(10, 30, 101, 14);
		pnlThongTin.add(lblMaNCC);
		
		JLabel lblTenNCC = new JLabel("Tên nhà cung cấp:");
		lblTenNCC.setBackground(new Color(0, 153, 204));
		lblTenNCC.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTenNCC.setBounds(10, 55, 101, 14);
		pnlThongTin.add(lblTenNCC);
		
		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setBackground(new Color(0, 153, 204));
		lblSDT.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblSDT.setBounds(10, 80, 101, 14);
		pnlThongTin.add(lblSDT);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBackground(new Color(0, 153, 204));
		lblDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDiaChi.setBounds(10, 105, 101, 14);
		pnlThongTin.add(lblDiaChi);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBackground(new Color(0, 153, 204));
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblEmail.setBounds(10, 130, 101, 14);
		pnlThongTin.add(lblEmail);
		
		txtMaNCC = new JTextField();
		txtMaNCC.setBounds(121, 27, 161, 20);
		pnlThongTin.add(txtMaNCC);
		txtMaNCC.setColumns(10);
		
		txtTenNCC = new JTextField();
		txtTenNCC.setColumns(10);
		txtTenNCC.setBounds(121, 52, 161, 20);
		pnlThongTin.add(txtTenNCC);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(121, 77, 161, 20);
		pnlThongTin.add(txtSDT);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(121, 102, 161, 20);
		pnlThongTin.add(txtDiaChi);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(121, 127, 161, 20);
		pnlThongTin.add(txtEmail);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setBackground(new Color(0, 153, 204));
		lblTrangThai.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTrangThai.setBounds(10, 155, 101, 14);
		pnlThongTin.add(lblTrangThai);
		
		JComboBox cbTrangThai = new JComboBox();
		cbTrangThai.setBounds(121, 151, 161, 22);
		pnlThongTin.add(cbTrangThai);
		
		JPanel pnlDS_NCC = new JPanel();
		pnlDS_NCC.setBackground(new Color(204, 204, 204));
		pnlDS_NCC.setBorder(new TitledBorder(null, "Danh s\u00E1ch nh\u00E0 cung c\u1EA5p:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDS_NCC.setBounds(331, 84, 995, 591);
		getContentPane().add(pnlDS_NCC);
		pnlDS_NCC.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_DSNCC = new JScrollPane();
		pnlDS_NCC.add(scrollPane_DSNCC, BorderLayout.CENTER);
		
		tblNhaCungCap = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Mã NCC");
		model.addColumn("Tên NCC");
		model.addColumn("Số điện thoại");
		model.addColumn("Địa chỉ");
		model.addColumn("Email");
		model.addColumn("Trạng thái");

		tblNhaCungCap.setModel(model);
		tblNhaCungCap.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane_DSNCC.setViewportView(tblNhaCungCap);
		
		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBackground(new Color(0, 153, 204));
		pnlChucNang.setForeground(new Color(204, 0, 51));
		pnlChucNang.setBorder(new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChucNang.setBounds(10, 318, 292, 357);
		getContentPane().add(pnlChucNang);
		pnlChucNang.setLayout(null);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThem.setBounds(41, 21, 213, 23);
		pnlChucNang.add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSua.setBounds(41, 55, 213, 23);
		pnlChucNang.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoa.setBounds(41, 89, 213, 23);
		pnlChucNang.add(btnXoa);
		
		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoaTrang.setBounds(41, 123, 213, 23);
		pnlChucNang.add(btnXoaTrang);
		
		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimKiem.setBounds(10, 187, 272, 56);
		pnlChucNang.add(pnlTimKiem);
		pnlTimKiem.setLayout(null);
		
		txtTiemKiem = new JTextField();
		txtTiemKiem.setBounds(10, 21, 252, 20);
		pnlTimKiem.add(txtTiemKiem);
		txtTiemKiem.setColumns(10);
		
		JPanel pnlTimKiem_1 = new JPanel();
		pnlTimKiem_1.setLayout(null);
		pnlTimKiem_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm ki\u1EBFm theo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTimKiem_1.setBounds(10, 254, 272, 56);
		pnlChucNang.add(pnlTimKiem_1);
		
		JComboBox cbTimKiem = new JComboBox();
		cbTimKiem.setBounds(10, 23, 252, 22);
		pnlTimKiem_1.add(cbTimKiem);
	}
}
