package gui;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class ChiTietThuoc_GUI extends JDialog{


	/**
	 * Create the application.
	 */
	public ChiTietThuoc_GUI(JFrame parent) {
		super(parent, "Chi Tiết Thuốc", true);
		initialize();
		setLocationRelativeTo(parent);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 255, 255));
		panel.setBounds(0, 0, 914, 80);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chi Tiết Thuốc");
		lblNewLabel.setBounds(322, 22, 256, 47);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 80, 914, 481);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Mã Thuốc :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(453, 35, 97, 24);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tên Thuốc :");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(453, 70, 97, 24);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Số Lượng :");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(453, 105, 97, 24);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Giá Nhập :");
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_3.setBounds(453, 140, 97, 24);
		panel_1.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Giá Bán :");
		lblNewLabel_2_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_4.setBounds(453, 175, 97, 24);
		panel_1.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Đơn Vị Tính :");
		lblNewLabel_2_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_5.setBounds(453, 210, 115, 24);
		panel_1.add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("Nhà Cung Cấp :");
		lblNewLabel_2_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_6.setBounds(453, 245, 132, 24);
		panel_1.add(lblNewLabel_2_6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(35, 35, 330, 300);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 330, 300);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2_6_1 = new JLabel("Hạn Sử Dụng :");
		lblNewLabel_2_6_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_6_1.setBounds(453, 280, 132, 24);
		panel_1.add(lblNewLabel_2_6_1);
		
		JLabel lblNewLabel_2_6_2 = new JLabel("Tên Kệ Thuốc :");
		lblNewLabel_2_6_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_6_2.setBounds(453, 315, 132, 24);
		panel_1.add(lblNewLabel_2_6_2);
		
		JLabel lblNewLabel_2_6_3 = new JLabel("Thành Phần :");
		lblNewLabel_2_6_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_6_3.setBounds(35, 350, 132, 24);
		panel_1.add(lblNewLabel_2_6_3);
		
		JLabel lblNewLabel_2_7 = new JLabel("Hiện Tên Kệ Thuốc");
		lblNewLabel_2_7.setForeground(new Color(255, 0, 0));
		lblNewLabel_2_7.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel_2_7.setBounds(595, 315, 309, 24);
		panel_1.add(lblNewLabel_2_7);
		
		JLabel lblNewLabel_2_9 = new JLabel("Hiện Nhà Cung Cấp");
		lblNewLabel_2_9.setForeground(new Color(255, 0, 0));
		lblNewLabel_2_9.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel_2_9.setBounds(595, 245, 309, 24);
		panel_1.add(lblNewLabel_2_9);
		
		JLabel lblNewLabel_2_8 = new JLabel("Hiện Hạn Sử Dụng");
		lblNewLabel_2_8.setForeground(new Color(255, 0, 0));
		lblNewLabel_2_8.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel_2_8.setBounds(595, 280, 309, 24);
		panel_1.add(lblNewLabel_2_8);
		
		JLabel lblNewLabel_2_10 = new JLabel("Hiện Đơn Vị Tính");
		lblNewLabel_2_10.setForeground(new Color(255, 0, 0));
		lblNewLabel_2_10.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel_2_10.setBounds(595, 210, 309, 24);
		panel_1.add(lblNewLabel_2_10);
		
		JLabel lblNewLabel_2_11 = new JLabel("Hiện Giá Nhập");
		lblNewLabel_2_11.setForeground(new Color(255, 0, 0));
		lblNewLabel_2_11.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel_2_11.setBounds(595, 175, 309, 24);
		panel_1.add(lblNewLabel_2_11);
		
		JLabel lblNewLabel_2_12 = new JLabel("Hiện Giá Bán");
		lblNewLabel_2_12.setForeground(new Color(255, 0, 0));
		lblNewLabel_2_12.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel_2_12.setBounds(595, 140, 309, 24);
		panel_1.add(lblNewLabel_2_12);
		
		JLabel lblNewLabel_2_13 = new JLabel("Hiện Số Lượng");
		lblNewLabel_2_13.setForeground(new Color(255, 0, 0));
		lblNewLabel_2_13.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel_2_13.setBounds(595, 105, 309, 24);
		panel_1.add(lblNewLabel_2_13);
		
		JLabel lblNewLabel_2_14 = new JLabel("Hiện Tên Thuốc");
		lblNewLabel_2_14.setForeground(new Color(255, 0, 0));
		lblNewLabel_2_14.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel_2_14.setBounds(595, 70, 309, 24);
		panel_1.add(lblNewLabel_2_14);
		
		JLabel lblNewLabel_2_15 = new JLabel("Hiện Mã Thuốc");
		lblNewLabel_2_15.setForeground(new Color(255, 0, 0));
		lblNewLabel_2_15.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel_2_15.setBounds(595, 35, 309, 24);
		panel_1.add(lblNewLabel_2_15);
		
		JLabel lblNewLabel_3 = new JLabel("Hiện Thành Phần");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel_3.setBounds(35, 377, 869, 93);
		panel_1.add(lblNewLabel_3);
	}
	private void initialize() {
		setBounds(100, 100, 930, 600);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
}
