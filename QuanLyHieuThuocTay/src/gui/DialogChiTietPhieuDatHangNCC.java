package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class DialogChiTietPhieuDatHangNCC extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogChiTietPhieuDatHangNCC dialog = new DialogChiTietPhieuDatHangNCC();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogChiTietPhieuDatHangNCC() {
		setBounds(100, 100, 808, 542);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Mã Phiếu:");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
			lblNewLabel.setBounds(78, 11, 157, 28);
			panel.add(lblNewLabel);
			
			JLabel lblNhCungCp = new JLabel("Nhà cung cấp:");
			lblNhCungCp.setFont(new Font("Times New Roman", Font.BOLD, 20));
			lblNhCungCp.setBounds(78, 50, 157, 28);
			panel.add(lblNhCungCp);
			
			JLabel lblNgyt = new JLabel("Ngày đăt:");
			lblNgyt.setFont(new Font("Times New Roman", Font.BOLD, 20));
			lblNgyt.setBounds(78, 89, 157, 28);
			panel.add(lblNgyt);
			
			textField = new JTextField();
			textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			textField.setBounds(245, 11, 474, 28);
			panel.add(textField);
			textField.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			textField_2.setColumns(10);
			textField_2.setBounds(245, 89, 474, 28);
			panel.add(textField_2);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(245, 52, 474, 28);
			panel.add(comboBox);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 128, 762, 152);
			panel.add(scrollPane);
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"M\u00E3 thu\u1ED1c", "T\u00EAn thu\u1ED1c", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1", "Th\u00E0nh ti\u1EC1n"
				}
			));
			scrollPane.setViewportView(table);
			
			JButton btnNewButton = new JButton("Thêm thuốc");
			btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btnNewButton.setBounds(245, 291, 139, 33);
			panel.add(btnNewButton);
			
			JButton btnXaThuc = new JButton("Xóa thuốc");
			btnXaThuc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnXaThuc.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btnXaThuc.setBounds(416, 291, 139, 33);
			panel.add(btnXaThuc);
			
			JLabel lblNewLabel_1 = new JLabel("Tổng tiền:");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
			lblNewLabel_1.setBounds(10, 337, 114, 28);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_1_1 = new JLabel("Ghi chú:");
			lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
			lblNewLabel_1_1.setBounds(10, 376, 114, 28);
			panel.add(lblNewLabel_1_1);
			
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			textField_1.setColumns(10);
			textField_1.setBounds(133, 335, 474, 28);
			panel.add(textField_1);
			
			JTextArea textArea = new JTextArea();
			textArea.setBounds(134, 380, 473, 55);
			panel.add(textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Lưu");
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Hủy");
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
