package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

public class ThemKH_GUI extends JDialog{

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the application.
	 */
	public ThemKH_GUI(JFrame parent) {
		super(parent, "Thêm Khách Hàng", true);
		initialize();
		setLocationRelativeTo(parent);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 930, 600);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 914, 99);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THÊM KHÁCH HÀNG");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(252, 22, 424, 53);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 99, 914, 462);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Khách Hàng :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(67, 60, 145, 30);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên Khách Hàng :");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(67, 150, 145, 30);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Số Điện Thoại :");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(67, 240, 145, 30);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Địa Chỉ :");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_4.setBounds(67, 330, 145, 30);
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_2 = new JLabel("KH0000001");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel_2.setBounds(243, 60, 182, 30);
		panel_1.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(222, 150, 682, 30);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(222, 240, 682, 30);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(222, 330, 682, 30);
		panel_1.add(textField_2);
		
		JButton btnNewButton = new JButton("Làm Mới");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(213, 386, 174, 54);
		panel_1.add(btnNewButton);
		
		JButton btnThm = new JButton("Thêm");
		btnThm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThm.setBounds(520, 386, 174, 54);
		panel_1.add(btnThm);
		
	}

}
