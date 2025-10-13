package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class XemchitietHD_GUI extends JDialog {
	private JTable table;

	public XemchitietHD_GUI(JFrame parent) {
		super(parent, "Chi Tiết Hóa Đơn", true);
		setFont(new Font("Times New Roman", Font.PLAIN, 20));
		setLocationRelativeTo(parent);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên Đơn Vị : ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(20, 11, 114, 30);
		getContentPane().add(lblNewLabel);
		
		JLabel lblaCh = new JLabel("Địa Chỉ : ");
		lblaCh.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblaCh.setBounds(20, 52, 114, 30);
		getContentPane().add(lblaCh);
		
		JLabel lblHiuThucTy = new JLabel("Hiệu Thuốc Tây Khánh  Hưng");
		lblHiuThucTy.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblHiuThucTy.setBounds(144, 11, 263, 30);
		getContentPane().add(lblHiuThucTy);
		
		JLabel lblAbc = new JLabel("Abc ");
		lblAbc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAbc.setBounds(144, 52, 830, 30);
		getContentPane().add(lblAbc);
		
		JLabel lblNewLabel_1 = new JLabel("Hóa Đơn Bán Hàng");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel_1.setBounds(335, 82, 339, 47);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblMHan = new JLabel("Mã Hóa Đơn : ");
		lblMHan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMHan.setBounds(20, 140, 148, 30);
		getContentPane().add(lblMHan);
		
		JLabel lblTnNhnVin = new JLabel("Tên Nhân Viên : ");
		lblTnNhnVin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnNhnVin.setBounds(20, 181, 148, 30);
		getContentPane().add(lblTnNhnVin);
		
		JLabel lblTnKhchHng = new JLabel("Tên Khách Hàng : ");
		lblTnKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnKhchHng.setBounds(20, 222, 148, 30);
		getContentPane().add(lblTnKhchHng);
		
		JLabel lblNgyLp = new JLabel("Ngày Lập : ");
		lblNgyLp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgyLp.setBounds(693, 181, 102, 30);
		getContentPane().add(lblNgyLp);
		
		JLabel lblNguynNgc = new JLabel("Nguyễn Ngô Đức Mạnh");
		lblNguynNgc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguynNgc.setBounds(178, 222, 496, 30);
		getContentPane().add(lblNguynNgc);
		
		JLabel lblNguynTrungKin = new JLabel("Nguyễn Trung Kiên");
		lblNguynTrungKin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguynTrungKin.setBounds(178, 181, 496, 30);
		getContentPane().add(lblNguynTrungKin);
		
		JLabel lblHd = new JLabel("HD000001");
		lblHd.setForeground(new Color(255, 0, 0));
		lblHd.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblHd.setBounds(178, 140, 148, 30);
		getContentPane().add(lblHd);
		
		JLabel lblNgyLp_1 = new JLabel("12/10/2025");
		lblNgyLp_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgyLp_1.setBounds(805, 181, 114, 30);
		getContentPane().add(lblNgyLp_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 260, 964, 443);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"STT", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Ti\u1EC1n", "Th\u00E0nh Ti\u1EC1n"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblTinKhcha = new JLabel("Tiền Khách Đưa : ");
		lblTinKhcha.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTinKhcha.setBounds(20, 712, 148, 30);
		getContentPane().add(lblTinKhcha);
		
		JLabel lblTinTha = new JLabel("Tiền Thừa : ");
		lblTinTha.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTinTha.setBounds(20, 753, 148, 30);
		getContentPane().add(lblTinTha);
		
		JLabel lblTngThnhTin = new JLabel("Tổng Tiền Hàng : ");
		lblTngThnhTin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTngThnhTin.setBounds(668, 714, 155, 30);
		getContentPane().add(lblTngThnhTin);
		
		JLabel lblThuVat = new JLabel("Thuế (10% VAT) : ");
		lblThuVat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblThuVat.setBounds(668, 753, 155, 30);
		getContentPane().add(lblThuVat);
		
		JLabel lblTngCng = new JLabel("Tổng Cộng : ");
		lblTngCng.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTngCng.setBounds(20, 794, 148, 30);
		getContentPane().add(lblTngCng);
		
		JLabel lblNgyLp_2 = new JLabel("100000000");
		lblNgyLp_2.setForeground(new Color(255, 0, 0));
		lblNgyLp_2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNgyLp_2.setBounds(833, 753, 141, 30);
		getContentPane().add(lblNgyLp_2);
		
		JLabel lblNgyLp_2_1 = new JLabel("100000000");
		lblNgyLp_2_1.setForeground(new Color(255, 0, 0));
		lblNgyLp_2_1.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNgyLp_2_1.setBounds(178, 794, 141, 30);
		getContentPane().add(lblNgyLp_2_1);
		
		JLabel lblNgyLp_2_2 = new JLabel("100000000");
		lblNgyLp_2_2.setForeground(new Color(255, 0, 0));
		lblNgyLp_2_2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNgyLp_2_2.setBounds(833, 712, 141, 30);
		getContentPane().add(lblNgyLp_2_2);
		
		JLabel lblNgyLp_2_3 = new JLabel("100000000");
		lblNgyLp_2_3.setForeground(new Color(255, 0, 0));
		lblNgyLp_2_3.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNgyLp_2_3.setBounds(178, 714, 141, 30);
		getContentPane().add(lblNgyLp_2_3);
		
		JLabel lblNgyLp_2_4 = new JLabel("100000000");
		lblNgyLp_2_4.setForeground(new Color(255, 0, 0));
		lblNgyLp_2_4.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNgyLp_2_4.setBounds(178, 753, 141, 30);
		getContentPane().add(lblNgyLp_2_4);
		
		JButton btnNewButton = new JButton("Xuất Hóa Đơn");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(771, 786, 148, 47);
		getContentPane().add(btnNewButton);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 1000, 900);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
}
