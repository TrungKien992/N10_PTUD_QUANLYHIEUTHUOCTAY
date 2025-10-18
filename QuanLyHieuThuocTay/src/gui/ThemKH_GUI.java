package gui;

import javax.swing.*;

import controller.KhachHang_Controller;
import controller.ThemKH_Controller;

import java.awt.*;

public class ThemKH_GUI extends JDialog {

    // khai báo public để controller có thể truy cập
    public JTextField txtMaKH;
    public JTextField txtTenKH;
    public JTextField txtSDT;
    public JTextField txtDiaChi;
    public JButton btnLamMoi;
    public JButton btnThem;

    /**
     * Create the dialog.
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

        // tiêu đề
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(0, 0, 914, 99);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("THÊM KHÁCH HÀNG");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel.setBounds(252, 22, 424, 53);
        panel.add(lblNewLabel);

        // phần nhập dữ liệu
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 99, 914, 462);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblMaKH = new JLabel("Mã Khách Hàng :");
        lblMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblMaKH.setBounds(67, 60, 145, 30);
        panel_1.add(lblMaKH);

        JLabel lblTenKH = new JLabel("Tên Khách Hàng :");
        lblTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblTenKH.setBounds(67, 150, 145, 30);
        panel_1.add(lblTenKH);

        JLabel lblSDT = new JLabel("Số Điện Thoại :");
        lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblSDT.setBounds(67, 240, 145, 30);
        panel_1.add(lblSDT);

        JLabel lblDiaChi = new JLabel("Địa Chỉ :");
        lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblDiaChi.setBounds(67, 330, 145, 30);
        panel_1.add(lblDiaChi);

        // txt mã khách hàng - chỉ hiển thị, không cho nhập
        txtMaKH = new JTextField();
        txtMaKH.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        txtMaKH.setEditable(false); // không cho nhập
        txtMaKH.setBounds(222, 60, 682, 30);
        panel_1.add(txtMaKH);

        // txt tên khách hàng
        txtTenKH = new JTextField();
        txtTenKH.setBounds(222, 150, 682, 30);
        txtTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        panel_1.add(txtTenKH);

        // txt số điện thoại
        txtSDT = new JTextField();
        txtSDT.setBounds(222, 240, 682, 30);
        txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        panel_1.add(txtSDT);

        // txt địa chỉ
        txtDiaChi = new JTextField();
        txtDiaChi.setBounds(222, 330, 682, 30);
        txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        panel_1.add(txtDiaChi);

        // nút làm mới
        btnLamMoi = new JButton("Làm Mới");
        btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnLamMoi.setBounds(213, 386, 174, 54);
        panel_1.add(btnLamMoi);

        // nút thêm
        btnThem = new JButton("Thêm");
        btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnThem.setBounds(520, 386, 174, 54);
        panel_1.add(btnThem);

    }
}
