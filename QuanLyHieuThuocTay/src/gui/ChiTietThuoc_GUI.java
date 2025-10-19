package gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.io.File;
import entity.Thuoc;

public class ChiTietThuoc_GUI extends JDialog {

    // các JLabel hiển thị thông tin thuốc
    public JLabel lblMaThuoc, lblTenThuoc, lblSoLuong, lblGiaNhap, lblGiaBan,
            lblDonViTinh, lblNhaCungCap, lblHanSuDung, lblTenKeThuoc, lblThanhPhan, lblAnh;

    public ChiTietThuoc_GUI(JFrame parent) {
        super(parent, "Chi Tiết Thuốc", true);
        initialize();
        setLocationRelativeTo(parent);
        getContentPane().setLayout(null);

        // panel header
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(128, 255, 255));
        panelHeader.setBounds(0, 0, 930, 80);
        getContentPane().add(panelHeader);
        panelHeader.setLayout(null);

        JLabel lblTitle = new JLabel("Chi Tiết Thuốc");
        lblTitle.setBounds(322, 22, 256, 47);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
        panelHeader.add(lblTitle);

        // panel nội dung
        JPanel panelContent = new JPanel();
        panelContent.setBounds(0, 80, 930, 481);
        getContentPane().add(panelContent);
        panelContent.setLayout(null);

        // panel chứa ảnh thuốc
        JPanel panelAnh = new JPanel();
        panelAnh.setBorder(new LineBorder(Color.BLACK));
        panelAnh.setBounds(35, 35, 330, 300);
        panelContent.add(panelAnh);
        panelAnh.setLayout(null);

        lblAnh = new JLabel("Không có ảnh");
        lblAnh.setBounds(0, 0, 330, 300);
        lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
        lblAnh.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        panelAnh.add(lblAnh);

        // nhãn thông tin thuốc
        lblMaThuoc = createLabel(panelContent, "Mã Thuốc :", 453, 35);
        lblTenThuoc = createLabel(panelContent, "Tên Thuốc :", 453, 70);
        lblSoLuong = createLabel(panelContent, "Số Lượng :", 453, 105);
        lblGiaNhap = createLabel(panelContent, "Giá Nhập :", 453, 140);
        lblGiaBan = createLabel(panelContent, "Giá Bán :", 453, 175);
        lblDonViTinh = createLabel(panelContent, "Đơn Vị Tính :", 453, 210);
        lblNhaCungCap = createLabel(panelContent, "Nhà Cung Cấp :", 453, 245);
        lblHanSuDung = createLabel(panelContent, "Hạn Sử Dụng :", 453, 280);
        lblTenKeThuoc = createLabel(panelContent, "Tên Kệ Thuốc :", 453, 315);
        lblThanhPhan = createLabel(panelContent, "Thành Phần :", 35, 350);

        // định dạng chung
        Font valueFont = new Font("Times New Roman", Font.ITALIC, 20);
        lblMaThuoc.setFont(valueFont);
        lblTenThuoc.setFont(valueFont);
        lblSoLuong.setFont(valueFont);
        lblGiaNhap.setFont(valueFont);
        lblGiaBan.setFont(valueFont);
        lblDonViTinh.setFont(valueFont);
        lblNhaCungCap.setFont(valueFont);
        lblHanSuDung.setFont(valueFont);
        lblTenKeThuoc.setFont(valueFont);
        lblThanhPhan.setFont(valueFont);
    }

    private JLabel createLabel(JPanel parent, String text, int x, int y) {
        JLabel lblTitle = new JLabel(text);
        lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblTitle.setBounds(x, y, 150, 24);
        parent.add(lblTitle);

        JLabel lblValue = new JLabel("...");
        lblValue.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblValue.setForeground(Color.RED);
        lblValue.setBounds(x + 142, y, 300, 24);
        parent.add(lblValue);

        return lblValue;
    }

    private void initialize() {
        setBounds(100, 100, 930, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    // phương thức load dữ liệu từ entity Thuoc
    public void loadThuoc(Thuoc t) {
        lblMaThuoc.setText(t.getMaThuoc());
        lblTenThuoc.setText(t.getTenThuoc());
        lblSoLuong.setText(String.valueOf(t.getSoLuong()));
        lblGiaNhap.setText(String.valueOf(t.getGiaNhap()));
        lblGiaBan.setText(String.valueOf(t.getGiaBan()));
        lblDonViTinh.setText(t.getDonViTinh());
        lblNhaCungCap.setText(t.getNhaCungCap().getTenNhaCungCap());
        lblHanSuDung.setText(String.valueOf(t.getHanSuDung()));
        lblTenKeThuoc.setText(t.getKeThuoc().getLoaiKe());
        lblThanhPhan.setText(t.getThanhPhan());

        // hiển thị ảnh thực tế nếu có
        String anhPath = t.getAnh();
        if (anhPath != null && !anhPath.isEmpty()) {
            File file = new File(anhPath);
            if (file.exists()) {
                ImageIcon icon = new ImageIcon(anhPath);
                Image img = icon.getImage().getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH);
                lblAnh.setIcon(new ImageIcon(img));
                lblAnh.setText(null);
            } else {
                lblAnh.setIcon(null);
                lblAnh.setText("Ảnh không tồn tại");
            }
        } else {
            lblAnh.setIcon(null);
            lblAnh.setText("Không có ảnh");
        }
    }
}
