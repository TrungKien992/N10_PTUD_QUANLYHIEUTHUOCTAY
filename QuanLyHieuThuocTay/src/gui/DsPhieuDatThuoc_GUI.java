package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DsPhieuDatThuoc_GUI extends JDialog{
	private JTextField textField;
	private JTable table;

	/**
	 * Create the application.
	 */
	public DsPhieuDatThuoc_GUI(JFrame parent) {
		super(parent, "Danh Sách Phiếu Đặt Thuốc", true);
		initialize();
		setLocationRelativeTo(parent);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 930, 600);
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 914, 99);
		getContentPane().add(panel);
		getContentPane().setLayout(null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH PHIẾU ĐẶT THUỐC CHỜ THANH TOÁN");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(79, 22, 767, 53);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 99, 914, 462);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Xóa");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(516, 386, 174, 54);
		panel_1.add(btnNewButton);
		
		JButton btnThm = new JButton("Thanh Toán");
		btnThm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThm.setBounds(700, 386, 174, 54);
		panel_1.add(btnThm);
		
		JButton btnXaTtC = new JButton("Xóa Tất Cả");
		btnXaTtC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXaTtC.setBounds(332, 386, 174, 54);
		panel_1.add(btnXaTtC);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 894, 323);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"M\u00E3 Kh\u00E1ch H\u00E0ng", "T\u00EAn Kh\u00E1ch H\u00E0ng", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("SĐT Khách Hàng :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 11, 163, 30);
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(165, 11, 280, 27);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Tìm");
		btnNewButton_1.setBounds(471, 11, 89, 30);
		panel_1.add(btnNewButton_1);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

}
