package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

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
		
		JLabel lblNewLabel = new JLabel("Chi Tiết Thuốc");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 80, 914, 481);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ảnh hiện chỗ này");
		lblNewLabel_1.setBackground(new Color(0, 128, 64));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(34, 23, 289, 271);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mã Thuốc");
		lblNewLabel_2.setBounds(450, 23, 65, 32);
		panel_1.add(lblNewLabel_2);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 930, 600);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
}
