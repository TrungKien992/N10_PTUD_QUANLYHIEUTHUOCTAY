package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List; // Import List

import javax.swing.*;
import javax.swing.border.Border; // Import Border
import javax.swing.border.EmptyBorder; // Import EmptyBorder
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser; // Import JDateChooser

import dao.chucVu_DAO; // Import DAO
import dao.nhanVien_DAO; // Import DAO
import entity.ChucVu; // Import Entity
import entity.NhanVien; // Import Entity
import entity.TaiKhoan; // Import Entity

public class ThemNhanVienSauKhiTaoTK_GUI extends JDialog {

    // ========== THÊM HẰNG SỐ MÀU/FONT ==========
    // (Copy từ TrangChu_GUI để class này độc lập)
	private static final Color COLOR_BACKGROUND_PRIMARY = new Color(240, 242, 245);
	private static final Color COLOR_CARD_BACKGROUND = Color.WHITE;
	private static final Color COLOR_PRIMARY_BLUE = new Color(0, 123, 255);
	private static final Color COLOR_SUCCESS_GREEN = new Color(40, 167, 69);
	private static final Color COLOR_DANGER_RED = new Color(220, 53, 69);
	private static final Color COLOR_TEXT_DARK = new Color(33, 37, 41);
	private static final Color COLOR_TEXT_MUTED = new Color(108, 117, 125);
	private static final Color COLOR_BORDER_LIGHT = new Color(222, 226, 230);
	private static final Font FONT_TITLE_SECTION = new Font("Segoe UI", Font.BOLD, 26);
	private static final Font FONT_LABEL_BOLD = new Font("Segoe UI", Font.BOLD, 15);
	private static final Font FONT_TEXT_FIELD = new Font("Segoe UI", Font.PLAIN, 15);
	private static final Font FONT_BUTTON_STANDARD = new Font("Segoe UI", Font.BOLD, 14);
	private static final Font FONT_DETAIL_ITALIC = new Font("Segoe UI", Font.ITALIC, 15);
	// ==========================================================

    private JTextField txtMaNV_Dialog;
    private JTextField txtTenNV_Dialog;
    private JDateChooser dateNgaySinh_Dialog;
    private JComboBox<String> cboGioiTinh_Dialog;
    private JTextField txtSDT_Dialog;
    private JTextField txtTinh_Dialog;
    private JTextField txtHuyen_Dialog;
    private JLabel lblAnhNV_Dialog;
    private String duongDanAnh_Dialog = null;
    private TaiKhoan taiKhoanMoi;
    private String maNVMoi;
    private nhanVien_DAO nvDAO_Dialog;
    private chucVu_DAO cvDAO_Dialog;

    public ThemNhanVienSauKhiTaoTK_GUI(JFrame parent, TaiKhoan tkMoi, String maNV) {
        super(parent, "Thêm Thông Tin Nhân Viên Tương Ứng", true);
        this.taiKhoanMoi = tkMoi;
        this.maNVMoi = maNV;
        this.nvDAO_Dialog = new nhanVien_DAO();
        this.cvDAO_Dialog = new chucVu_DAO();

        initialize(); // Gọi hàm khởi tạo giao diện
        setLocationRelativeTo(parent);
    }

    private void initialize() {
        setSize(900, 650);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(COLOR_BACKGROUND_PRIMARY);
        getContentPane().setLayout(null);

        JLabel lblTitleDialog = new JLabel("THÊM THÔNG TIN NHÂN VIÊN");
        lblTitleDialog.setFont(FONT_TITLE_SECTION);
        lblTitleDialog.setForeground(COLOR_PRIMARY_BLUE);
        lblTitleDialog.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitleDialog.setBounds(0, 15, 884, 40);
        getContentPane().add(lblTitleDialog);

        JPanel pnlAnhDialog = new JPanel(new BorderLayout());
        pnlAnhDialog.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        pnlAnhDialog.setBackground(COLOR_BACKGROUND_PRIMARY);
        pnlAnhDialog.setBounds(30, 70, 250, 300);
        getContentPane().add(pnlAnhDialog);

        lblAnhNV_Dialog = new JLabel("Chưa có ảnh", SwingConstants.CENTER);
        lblAnhNV_Dialog.setFont(FONT_DETAIL_ITALIC);
        lblAnhNV_Dialog.setForeground(COLOR_TEXT_MUTED);
        pnlAnhDialog.add(lblAnhNV_Dialog, BorderLayout.CENTER);

        JButton btnChonAnhDialog = new JButton("Chọn Ảnh");
        styleButton(btnChonAnhDialog, COLOR_TEXT_MUTED); // Dùng styleButton nội bộ
        btnChonAnhDialog.setFont(FONT_BUTTON_STANDARD);
        btnChonAnhDialog.setBounds(80, 380, 150, 35);
        getContentPane().add(btnChonAnhDialog);

         btnChonAnhDialog.addActionListener(e -> {
             JFileChooser fc = new JFileChooser();
             fc.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));
             if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                 File file = fc.getSelectedFile();
                 duongDanAnh_Dialog = file.getAbsolutePath();
                 try {
                     ImageIcon icon = new ImageIcon(duongDanAnh_Dialog);
                     // Kiểm tra ảnh hợp lệ trước khi scale
                     if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                         Image img = icon.getImage().getScaledInstance(lblAnhNV_Dialog.getWidth(), lblAnhNV_Dialog.getHeight(), Image.SCALE_SMOOTH);
                         lblAnhNV_Dialog.setIcon(new ImageIcon(img));
                         lblAnhNV_Dialog.setText(null);
                     } else {
                         throw new Exception("File ảnh không hợp lệ");
                     }
                 } catch(Exception ex) {
                     lblAnhNV_Dialog.setText("Lỗi ảnh");
                     lblAnhNV_Dialog.setIcon(null);
                     JOptionPane.showMessageDialog(this, "Không thể tải ảnh: " + ex.getMessage(), "Lỗi Ảnh", JOptionPane.ERROR_MESSAGE);
                     duongDanAnh_Dialog = null; // Reset nếu lỗi
                 }
             }
         });


        JPanel pnlInfoDialog = new JPanel(null); // Null layout
        pnlInfoDialog.setBackground(COLOR_CARD_BACKGROUND);
        pnlInfoDialog.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
             "Thông Tin Bắt Buộc", TitledBorder.LEADING, TitledBorder.TOP,
             FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        ));
        pnlInfoDialog.setBounds(300, 70, 570, 480);
        getContentPane().add(pnlInfoDialog);

         int labelX_d = 20;
         int inputX_d = 160;
         int inputW_d = 380;
         int startY_d = 20;
         int height_d = 30;
         int vGap_d = 15;

         JLabel lblMaNVDialog = new JLabel("Mã Nhân Viên:");
         lblMaNVDialog.setFont(FONT_LABEL_BOLD);
         lblMaNVDialog.setBounds(labelX_d, startY_d, 130, height_d);
         pnlInfoDialog.add(lblMaNVDialog);
         txtMaNV_Dialog = new JTextField(maNVMoi); // Hiển thị mã NV mới
         txtMaNV_Dialog.setFont(FONT_LABEL_BOLD);
         txtMaNV_Dialog.setEditable(false);
         txtMaNV_Dialog.setBackground(COLOR_CARD_BACKGROUND);
         txtMaNV_Dialog.setBorder(null);
         txtMaNV_Dialog.setForeground(COLOR_DANGER_RED);
         txtMaNV_Dialog.setBounds(inputX_d, startY_d, inputW_d, height_d);
         pnlInfoDialog.add(txtMaNV_Dialog);

         JLabel lblTenTKDialog = new JLabel("Tài Khoản:");
         lblTenTKDialog.setFont(FONT_LABEL_BOLD);
         lblTenTKDialog.setBounds(labelX_d, startY_d + height_d + vGap_d, 130, height_d);
         pnlInfoDialog.add(lblTenTKDialog);
         JTextField txtTenTKDialog = new JTextField(taiKhoanMoi.getTenTK()); // Hiển thị tên TK
         txtTenTKDialog.setFont(FONT_TEXT_FIELD);
         txtTenTKDialog.setEditable(false);
         txtTenTKDialog.setBackground(new Color(230,230,230));
         txtTenTKDialog.setBounds(inputX_d, startY_d + height_d + vGap_d, inputW_d, height_d);
         pnlInfoDialog.add(txtTenTKDialog);

        JLabel lblChucVuDialog = new JLabel("Chức Vụ:");
        lblChucVuDialog.setFont(FONT_LABEL_BOLD);
        lblChucVuDialog.setBounds(labelX_d, startY_d + 2*(height_d + vGap_d), 130, height_d);
        pnlInfoDialog.add(lblChucVuDialog);
        JTextField txtChucVuDialog = new JTextField(taiKhoanMoi.getQuyenHan() != null ? taiKhoanMoi.getQuyenHan() : "(Chưa cấp)"); // Hiển thị quyền
        txtChucVuDialog.setFont(FONT_TEXT_FIELD);
        txtChucVuDialog.setEditable(false);
        txtChucVuDialog.setBackground(new Color(230,230,230));
        txtChucVuDialog.setBounds(inputX_d, startY_d + 2*(height_d + vGap_d), inputW_d, height_d);
        pnlInfoDialog.add(txtChucVuDialog);

         JLabel lblTenNVDialog = new JLabel("Tên Nhân Viên (*):");
         lblTenNVDialog.setFont(FONT_LABEL_BOLD);
         lblTenNVDialog.setBounds(labelX_d, startY_d + 3*(height_d + vGap_d), 130, height_d);
         pnlInfoDialog.add(lblTenNVDialog);
         txtTenNV_Dialog = new JTextField();
         txtTenNV_Dialog.setFont(FONT_TEXT_FIELD);
         txtTenNV_Dialog.setBounds(inputX_d, startY_d + 3*(height_d + vGap_d), inputW_d, height_d);
         pnlInfoDialog.add(txtTenNV_Dialog);

        JLabel lblNgaySinhDialog = new JLabel("Ngày Sinh (*):");
        lblNgaySinhDialog.setFont(FONT_LABEL_BOLD);
        lblNgaySinhDialog.setBounds(labelX_d, startY_d + 4*(height_d + vGap_d), 130, height_d);
        pnlInfoDialog.add(lblNgaySinhDialog);
        dateNgaySinh_Dialog = new JDateChooser();
        dateNgaySinh_Dialog.setFont(FONT_TEXT_FIELD);
        dateNgaySinh_Dialog.setDateFormatString("dd/MM/yyyy");
        dateNgaySinh_Dialog.setBounds(inputX_d, startY_d + 4*(height_d + vGap_d), inputW_d, height_d);
        pnlInfoDialog.add(dateNgaySinh_Dialog);

         JLabel lblGioiTinhDialog = new JLabel("Giới Tính (*):");
         lblGioiTinhDialog.setFont(FONT_LABEL_BOLD);
         lblGioiTinhDialog.setBounds(labelX_d, startY_d + 5*(height_d + vGap_d), 130, height_d);
         pnlInfoDialog.add(lblGioiTinhDialog);
         cboGioiTinh_Dialog = new JComboBox<>(new String[]{"Nam", "Nữ"});
         cboGioiTinh_Dialog.setFont(FONT_TEXT_FIELD);
         cboGioiTinh_Dialog.setBounds(inputX_d, startY_d + 5*(height_d + vGap_d), inputW_d, height_d);
         pnlInfoDialog.add(cboGioiTinh_Dialog);

         JLabel lblSDTDialog = new JLabel("Số Điện Thoại (*):");
         lblSDTDialog.setFont(FONT_LABEL_BOLD);
         lblSDTDialog.setBounds(labelX_d, startY_d + 6*(height_d + vGap_d), 130, height_d);
         pnlInfoDialog.add(lblSDTDialog);
         txtSDT_Dialog = new JTextField();
         txtSDT_Dialog.setFont(FONT_TEXT_FIELD);
         txtSDT_Dialog.setBounds(inputX_d, startY_d + 6*(height_d + vGap_d), inputW_d, height_d);
         pnlInfoDialog.add(txtSDT_Dialog);

        JLabel lblTinhDialog = new JLabel("Tỉnh/TP (*):");
        lblTinhDialog.setFont(FONT_LABEL_BOLD);
        lblTinhDialog.setBounds(labelX_d, startY_d + 7*(height_d + vGap_d), 130, height_d);
        pnlInfoDialog.add(lblTinhDialog);
        txtTinh_Dialog = new JTextField();
        txtTinh_Dialog.setFont(FONT_TEXT_FIELD);
        txtTinh_Dialog.setBounds(inputX_d, startY_d + 7*(height_d + vGap_d), inputW_d, height_d);
        pnlInfoDialog.add(txtTinh_Dialog);

         JLabel lblHuyenDialog = new JLabel("Quận/Huyện (*):");
         lblHuyenDialog.setFont(FONT_LABEL_BOLD);
         lblHuyenDialog.setBounds(labelX_d, startY_d + 8*(height_d + vGap_d), 130, height_d);
         pnlInfoDialog.add(lblHuyenDialog);
         txtHuyen_Dialog = new JTextField();
         txtHuyen_Dialog.setFont(FONT_TEXT_FIELD);
         txtHuyen_Dialog.setBounds(inputX_d, startY_d + 8*(height_d + vGap_d), inputW_d, height_d);
         pnlInfoDialog.add(txtHuyen_Dialog);


        JButton btnLuuNV = new JButton("Lưu Thông Tin Nhân Viên");
        styleButton(btnLuuNV, COLOR_SUCCESS_GREEN); // Dùng styleButton nội bộ
        btnLuuNV.setFont(FONT_BUTTON_STANDARD);
        btnLuuNV.setBounds(350, 560, 250, 40);
        getContentPane().add(btnLuuNV);

        btnLuuNV.addActionListener(e -> {
             // Lấy dữ liệu từ dialog
            String tenNV = txtTenNV_Dialog.getText().trim();
            java.util.Date ngaySinh = dateNgaySinh_Dialog.getDate();
            String gioiTinh = cboGioiTinh_Dialog.getSelectedItem().toString();
            String sdt = txtSDT_Dialog.getText().trim();
            String tinh = txtTinh_Dialog.getText().trim();
            String huyen = txtHuyen_Dialog.getText().trim();
            String anh = duongDanAnh_Dialog; // Lấy đường dẫn ảnh

             // Kiểm tra nhập liệu
             if (tenNV.isEmpty() || ngaySinh == null || sdt.isEmpty() || tinh.isEmpty() || huyen.isEmpty() || anh == null) {
                  JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin bắt buộc (*) và chọn ảnh!", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                  return;
             }
             if (!sdt.matches("^0\\d{9}$")) {
                 JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                 return;
             }

             // Chuyển ngày
             LocalDate ngaySinhLocal = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

             // Tìm đối tượng ChucVu tương ứng với quyenHan của TaiKhoan
             ChucVu chucVuNV = cvDAO_Dialog.getChucVuByTen(taiKhoanMoi.getQuyenHan());
             if (chucVuNV == null && taiKhoanMoi.getQuyenHan() != null && !taiKhoanMoi.getQuyenHan().isEmpty()) {
                 // Nếu có quyền hạn nhưng không tìm thấy chức vụ -> báo lỗi
                 JOptionPane.showMessageDialog(this, "Không tìm thấy Chức vụ phù hợp ("+taiKhoanMoi.getQuyenHan()+")!", "Lỗi Dữ Liệu", JOptionPane.ERROR_MESSAGE);
                 return;
             } else if (chucVuNV == null) {
                 // Nếu tài khoản chưa có quyền hạn -> Gán chức vụ mặc định hoặc báo lỗi tùy logic
                 // Ví dụ: Gán chức vụ mặc định (cần có hàm getChucVuMacDinh() trong DAO)
                 // chucVuNV = cvDAO_Dialog.getChucVuMacDinh();
                 // Hoặc báo lỗi:
                  JOptionPane.showMessageDialog(this, "Tài khoản chưa được cấp quyền, không thể xác định chức vụ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                  return;
             }


             // Tạo đối tượng NhanVien
             NhanVien nvMoi = new NhanVien();
             nvMoi.setMaNV(maNVMoi);
             nvMoi.setTenNV(tenNV);
             nvMoi.setNgaySinh(ngaySinhLocal);
             nvMoi.setGioiTinh(gioiTinh);
             nvMoi.setSoDienThoai(sdt);
             nvMoi.setDiaChi(tinh + ", " + huyen);
             nvMoi.setAnh(anh);
             nvMoi.setTaiKhoan(taiKhoanMoi);
             nvMoi.setChucVu(chucVuNV);

             // Gọi DAO để thêm nhân viên
             if (nvDAO_Dialog.insertNhanVien(nvMoi)) {
                 JOptionPane.showMessageDialog(this, "Thêm thông tin nhân viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                 dispose(); // Đóng dialog sau khi thêm thành công
             } else {
                 JOptionPane.showMessageDialog(this, "Thêm thông tin nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
             }
        });
    } // Kết thúc initialize()

    // Hàm styleButton nội bộ (Copy từ TrangChu_GUI)
    private void styleButton(JButton button, Color background) {
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

} 