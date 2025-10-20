package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.nhanVien_DAO; // Import DAO
import dao.taiKhoan_DAO; // Import DAO
import entity.NhanVien; // Import Entity
import entity.TaiKhoan; // Import Entity

public class Dangnhap_GUI extends JFrame {

    private JPanel contentPane;
    private JTextField txtTenDangNhap;
    private JPasswordField txtMatKhau;
    private taiKhoan_DAO taiKhoanDAO; // DAO object
    private nhanVien_DAO nhanVienDAO; // DAO object

    // Biến static để lưu thông tin người đăng nhập (cách đơn giản)
    public static TaiKhoan taiKhoanLogin = null;
    public static String tenNhanVienLogin = null;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
         // Đặt Look and Feel Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dangnhap_GUI frame = new Dangnhap_GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Dangnhap_GUI() {
        taiKhoanDAO = new taiKhoan_DAO(); // Khởi tạo DAO
        nhanVienDAO = new nhanVien_DAO(); // Khởi tạo DAO

        setTitle("Đăng Nhập Hệ Thống");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350); // Kích thước cửa sổ
        setLocationRelativeTo(null); // Căn giữa màn hình
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15)); // Padding
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 15)); // BorderLayout với khoảng cách

        JLabel lblTitle = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 123, 255));
        contentPane.add(lblTitle, BorderLayout.NORTH);

        JPanel panelInput = new JPanel();
        contentPane.add(panelInput, BorderLayout.CENTER);
        GridBagLayout gbl_panelInput = new GridBagLayout();
        gbl_panelInput.columnWidths = new int[]{0, 0, 0};
        gbl_panelInput.rowHeights = new int[]{0, 0, 0, 0};
        gbl_panelInput.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panelInput.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelInput.setLayout(gbl_panelInput);

        JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
        lblTenDangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        GridBagConstraints gbc_lblTenDangNhap = new GridBagConstraints();
        gbc_lblTenDangNhap.anchor = GridBagConstraints.EAST;
        gbc_lblTenDangNhap.insets = new Insets(5, 5, 5, 5);
        gbc_lblTenDangNhap.gridx = 0;
        gbc_lblTenDangNhap.gridy = 0;
        panelInput.add(lblTenDangNhap, gbc_lblTenDangNhap);

        txtTenDangNhap = new JTextField();
        txtTenDangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        GridBagConstraints gbc_txtTenDangNhap = new GridBagConstraints();
        gbc_txtTenDangNhap.insets = new Insets(5, 0, 5, 5);
        gbc_txtTenDangNhap.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtTenDangNhap.gridx = 1;
        gbc_txtTenDangNhap.gridy = 0;
        panelInput.add(txtTenDangNhap, gbc_txtTenDangNhap);
        txtTenDangNhap.setColumns(10);

        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        GridBagConstraints gbc_lblMatKhau = new GridBagConstraints();
        gbc_lblMatKhau.anchor = GridBagConstraints.EAST;
        gbc_lblMatKhau.insets = new Insets(5, 5, 5, 5);
        gbc_lblMatKhau.gridx = 0;
        gbc_lblMatKhau.gridy = 1;
        panelInput.add(lblMatKhau, gbc_lblMatKhau);

        txtMatKhau = new JPasswordField();
        txtMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        GridBagConstraints gbc_txtMatKhau = new GridBagConstraints();
        gbc_txtMatKhau.insets = new Insets(5, 0, 5, 5);
        gbc_txtMatKhau.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtMatKhau.gridx = 1;
        gbc_txtMatKhau.gridy = 1;
        panelInput.add(txtMatKhau, gbc_txtMatKhau);

        JPanel panelButtons = new JPanel();
        contentPane.add(panelButtons, BorderLayout.SOUTH);
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5)); // Căn giữa nút

        JButton btnDangNhap = new JButton("Đăng Nhập");
        btnDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDangNhap.setBackground(new Color(40, 167, 69)); // Xanh lá
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setFocusPainted(false);
        btnDangNhap.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelButtons.add(btnDangNhap);
        
        // Đăng nhập
        btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tenTK = txtTenDangNhap.getText().trim();
                String matKhau = new String(txtMatKhau.getPassword()); // Lấy mật khẩu

                if (tenTK.isEmpty() || matKhau.isEmpty()) {
                    JOptionPane.showMessageDialog(Dangnhap_GUI.this, "Vui lòng nhập tên đăng nhập và mật khẩu!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                TaiKhoan tk = taiKhoanDAO.checkLogin(tenTK, matKhau);

                if (tk == null) {
                    JOptionPane.showMessageDialog(Dangnhap_GUI.this, "Tên đăng nhập hoặc mật khẩu không đúng!", "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
                } else if (tk.getQuyenHan() == null || tk.getQuyenHan().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(Dangnhap_GUI.this, "Tài khoản của bạn chưa được cấp quyền truy cập. Vui lòng liên hệ quản trị viên!", "Chưa được cấp quyền", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Đăng nhập thành công
                    JOptionPane.showMessageDialog(Dangnhap_GUI.this, "Đăng nhập thành công! Quyền: " + tk.getQuyenHan(), "Thành công", JOptionPane.INFORMATION_MESSAGE);

                    // Lưu thông tin người đăng nhập
                    taiKhoanLogin = tk;
                    tenNhanVienLogin = nhanVienDAO.getTenNhanVienByMaTK(tk.getMaTK()); // Lấy tên NV
                    if (tenNhanVienLogin == null) {
                        tenNhanVienLogin = tk.getTenTK(); // Nếu không có NV liên kết, dùng tên TK
                    }


                    // Mở TrangChu_GUI và đóng cửa sổ đăng nhập
                    EventQueue.invokeLater(() -> {
                         try {
                            TrangChu_GUI trangChu = new TrangChu_GUI(); // Khởi tạo TrangChu_GUI
                            // trangChu.QuanLyHieuThuocTay.setVisible(true); // Hiển thị TrangChu_GUI
                            dispose(); // Đóng cửa sổ đăng nhập
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            }
        });

        txtMatKhau.addActionListener(e -> btnDangNhap.doClick());
        txtTenDangNhap.addActionListener(e -> txtMatKhau.requestFocus()); // Chuyển focus khi Enter ở tên DN
    }
}