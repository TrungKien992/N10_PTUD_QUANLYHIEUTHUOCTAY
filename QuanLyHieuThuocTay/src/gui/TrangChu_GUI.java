package gui;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.beans.Beans;
import connectDB.ConnectDB;
import dao.chucVu_DAO;
import dao.khachHang_DAO;
import dao.nhanVien_DAO;
import dao.taiKhoan_DAO;
import entity.ChucVu;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import controller.KhachHang_Controller;
import controller.ThemKH_Controller;
import controller.Thuoc_Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;



public class TrangChu_GUI extends JFrame{

    private JFrame QuanLyHieuThuocTay;
    private JPanel maincontent;
    private JTextField text_Nhapmathuoc;
    private JTable table_timkiemthuoc;
    private JTextField text_Nhapsosdtkh;
    private JTable table_hdtam;
    private JTextField text_Nhaptiennhan;
    private JTextField text_Nhapsoluongthuoc;
    public JTable table_tkt;
    public JTextField txt_kh_MaKH;
    public JTextField txt_kh_SDT;
    public JTextField txt_kh_TenKH;
    public JTable table_tkkh;
    public JTextField txt_cnkh_MaKh;
    public JTextField txt_cnkh_SDt;
    public JTextField txt_cnkh_tenkh;
    public JTextField txtMKH_TK;
    public JTextField txtTenKH_TK;
    public JTable table_CapNhatKH;
    public JTextField text_cntmt;
    public JTextField text_cnttt;
    public JTextField text_cntsl;
    public JTextField text_cntgn;
    public JTextField text_cntgb;
    public JTextField text_cnthsd;
    public JTextField text_cnt_tkmt;
    public JTextField text_cnt_tktt;
    public JTable table_Capnhatthuoc;
    private JTextField text_ttmt;
    private JTextField text_tttt;
    private JTextField text_ttsl;
    private JTextField text_ttgn;
    private JTextField text_ttgb;
    private JTable table_1;
    private JTable table_2;
    private JTable table_tshh;
    private JTable table_tshhan;
    private JTable table_5;
    private JTextField text_tkhd_TenKH;
    private JTextField text_tkhd_sdtkh;
    private JTextField text_tkhd_sdtnv;
    private JTextField text_tkhd_tennv;
    private JDateChooser date_tkhd_ngaylaphd;
    private JTable table_tkhd;
    private JDateChooser date_tktn_ngay;
    private JTable table_tktn;
    private JTextField text_tktn_tktnv_boloc_manv;
    private JTextField text_tktn_tktnv_boloc_tennv;
    private JTable table_8;
    private JTable table_hienhd_tktt;
    private JTextField text_tktt_tk_boloc_manv;
    private JTextField text_tktt_tk_boloc_tennv;
    private JTextField text_tktnam_locnv_manv;
    private JTextField text_tktnam_locnv_tennv;
    private JTable table_tktn_hiennv;
    private JTable table_11;
    private JTable table_ttkt;
    private JTextField txtMaNV_TNV;
    private JTextField txtTenNV_TNV;
    private JTextField txtSDT_TNV;
    private JTextField txt_TenNV_TKNV;
    private JTextField txtSDT_TKNV;
    private JTable table_TKNV;
    private JTextField txtTinh_TNV;
    private JTextField txtHuyen_TNV;
    private JTextField txtTinh_TKNV;
    private JTextField txtHuyen_TKNV;
    private JTextField txtMaNV_CNNV;
    private JTextField txtTenNV_CNNV;
    private JTextField txtSDT_CNNV;
    private JTextField txtTinh_CNNV;
    private JTextField txtHuyen_CNNV;
    private JTextField txtTenNV_TK_CNNV;
    private JTextField text_tkhd_Mahd;
    private JTable table_CNNV;
    public JTextField txt_kh_dc;
    public JTextField txt_cnkh_dc;
    private JTable table_ThemNCC;
    private JTextField txtMaNCC_CNNCC;
    private JTextField txtTenNCC_CNNCC;
    private JTextField txtSDT_CNNCC;
    private JTextField txtEmail_CNNCC;
    private String duongDanAnh_TNV = null;
    private String duongDanAnh_CNNV= null;
    private JTextField txtTinh_TNCC;
    private JTextField txtHuyen_TNCC;
    private JTextField txtMaNCC_TNCC;
    private JTextField txtTenNCC_TNCC;
    private JTextField txtEmail_TNCC;
    private JTextField txtSDT_TNCC;
    private JTable table_CNNCC;
    private JTextField txtMaNCC_TKNCC;
    private JTextField txtTenNCC_TKNCC;
    private JTextField txtEmail_TKNCC;
    private JTextField txtSDT_TKNCC;
    private JTextField txtGhiChu_TKNCC;
    private JTable table_TKNCC;
	public JButton btnLammoi_CNKH;
	public JButton btn_kh_Lammoi;
	public JButton btn_cnkh_CapNhat;
	public JButton btn_cnkh_Khoiphuc;
	private JYearChooser year_tktt;
	private JMonthChooser month_tktt;
	private JYearChooser year_tktn;
	private JMenuItem currentSelectedItem = null;
	private JPanel currentVisibleSubmenu = null; // Panel con đang hiển thị
	private JButton selectedMainMenuButton = null; // Nút chính đang được chọn
	private CardLayout cl;
	public JComboBox<String> cb_tkt_kethuoc;
	public JComboBox<String> cb_tkt_tenthuoc;
	public JComboBox<String> cb_tkt_ncc;
	public JButton btn_tkt_xemchitiet;
	public JButton btn_tkt_Lammoi;
	public JButton btn_cnt_tk_lammoi;
	public JComboBox<String> cb_cnt_tktkt;
	public JComboBox<String> cb_cntdvt;
	public JComboBox<String> cb_cntncc;
	public JComboBox<String> cb_cnt_tkt;
	public JTextArea textArea_cnttp;
	public JButton btn_cntKhoiphuc;
	private JPanel pn_chuaanh;
	public JLabel lb_Chuaanh;
	public JButton btn_cntCapnhat;
	public JButton btn_cnt_ChonAnh;
	private JTextField txtTimKiem;
	private JTable table;
	private nhanVien_DAO nvDAO;
	public JDateChooser date_cnthsd;
	private TaiKhoan currentUser;
    private String currentUserName;
	private JButton btnTaikhoan;
	private JDateChooser loadDataQLTK;
	private JTable tableQLTK;
    

	// ========== BẢNG MÀU VÀ FONT CHỮ HIỆN ĐẠI ==========
	private static final Color COLOR_BACKGROUND_PRIMARY = new Color(240, 242, 245);
	private static final Color COLOR_CARD_BACKGROUND = Color.WHITE;
	private static final Color COLOR_PRIMARY_BLUE = new Color(0, 123, 255);
	private static final Color COLOR_SUCCESS_GREEN = new Color(40, 167, 69);
	private static final Color COLOR_DANGER_RED = new Color(220, 53, 69);
	private static final Color COLOR_TEXT_DARK = new Color(33, 37, 41);
	private static final Color COLOR_TEXT_MUTED = new Color(108, 117, 125);
	private static final Color COLOR_BORDER_LIGHT = new Color(222, 226, 230);

	// Fonts
	private static final Font FONT_TITLE_MAIN = new Font("Segoe UI", Font.BOLD, 38);
	private static final Font FONT_TITLE_SECTION = new Font("Segoe UI", Font.BOLD, 26);
	private static final Font FONT_LABEL_BOLD = new Font("Segoe UI", Font.BOLD, 15);
	private static final Font FONT_TEXT_FIELD = new Font("Segoe UI", Font.PLAIN, 15);
	private static final Font FONT_BUTTON_STANDARD = new Font("Segoe UI", Font.BOLD, 14);
	private static final Font FONT_TABLE_HEADER = new Font("Segoe UI", Font.BOLD, 15);
	private static final Font FONT_TABLE_CELL = new Font("Segoe UI", Font.PLAIN, 14);
	private static final Font FONT_SUMMARY_TOTAL = new Font("Segoe UI", Font.BOLD, 20);
	private static final Font FONT_DETAIL_ITALIC = new Font("Segoe UI", Font.ITALIC, 15);
    
	// ========== MÀU CHO SIDEBAR ==========
    private static final Color COLOR_SIDEBAR_BG_START = new Color(0, 80, 80); // Đậm hơn
    private static final Color COLOR_SIDEBAR_BG_END = new Color(0, 120, 120); // Nhạt hơn
    private static final Color COLOR_SIDEBAR_BUTTON_HOVER = new Color(0, 153, 153); // Màu hover
    private static final Color COLOR_SIDEBAR_BUTTON_SELECTED = new Color(0, 170, 170); // Màu khi nút được chọn
    private static final Color COLOR_SIDEBAR_TEXT = Color.WHITE;
	
	public static void main(String[] args) {
	    try {
	        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	            if ("Nimbus".equals(info.getName())) {
	                UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                new TrangChu_GUI();    
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}

    /**
     * Create the application.
     */
	// Constructor mặc định (có thể giữ lại để test)
    public TrangChu_GUI() {
         // Lấy thông tin từ biến static của Dangnhap_GUI
         this.currentUser = Dangnhap_GUI.taiKhoanLogin;
         this.currentUserName = Dangnhap_GUI.tenNhanVienLogin;
         // Kiểm tra nếu chưa đăng nhập thì quay lại màn hình đăng nhập
         if (this.currentUser == null) {
             // Đóng cửa sổ hiện tại (nếu có) và mở lại đăng nhập
             // Cần xử lý cẩn thận nếu đang chạy từ main của TrangChu_GUI
             // Tốt nhất là main chỉ nên chạy Dangnhap_GUI
             System.err.println("Chưa đăng nhập!");
             // Có thể đóng frame hiện tại và mở Dangnhap_GUI ở đây
             // Hoặc throw exception
             // Ví dụ đơn giản:
              if (QuanLyHieuThuocTay != null) { // Nếu frame đã được tạo (dù là rỗng)
                  QuanLyHieuThuocTay.dispose();
              }
              Dangnhap_GUI login = new Dangnhap_GUI();
              login.setVisible(true);
              return; // Dừng việc khởi tạo TrangChu_GUI
         }
        initialize();
    }

    // (Optional) Constructor nhận thông tin đăng nhập
    // public TrangChu_GUI(TaiKhoan user, String userName) {
    //     this.currentUser = user;
    //     this.currentUserName = userName;
    //     initialize();
    // }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        QuanLyHieuThuocTay = new JFrame();
        QuanLyHieuThuocTay.setTitle("Hệ Thống Quản Lý Hiệu Thuốc Tây");
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Admin\\eclipse-workspace\\QuanLyHieuThuoc\\image\\z7068801445103_7be0ebb233e8a4eceb10c3aceb500455.jpg");
        QuanLyHieuThuocTay.setIconImage(icon.getImage());
        QuanLyHieuThuocTay.setSize(1935,1040);
        QuanLyHieuThuocTay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        QuanLyHieuThuocTay.setLocationRelativeTo(null);

        JPanel sidebar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                GradientPaint gp = new GradientPaint(
                    0, 0, COLOR_SIDEBAR_BG_START, 
                    0, height, COLOR_SIDEBAR_BG_END); 
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS)); 
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5)); 

        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setOpaque(false); // Trong suốt để lấy nền gradient
        JLabel lblUserName = new JLabel(currentUserName != null ? currentUserName : "N/A", SwingConstants.CENTER); // Tên NV/TK
        lblUserName.setForeground(Color.WHITE);
        lblUserName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        logoPanel.add(lblUserName, BorderLayout.CENTER);

        JLabel lblUserRole = new JLabel(currentUser.getQuyenHan(), SwingConstants.CENTER); // Quyền hạn
        lblUserRole.setForeground(new Color(200, 220, 255)); // Màu nhạt hơn
        lblUserRole.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        logoPanel.add(lblUserRole, BorderLayout.SOUTH);
        logoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Giới hạn chiều cao
        logoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(logoPanel); 

        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));

        // --- Menu Hệ Thống ---
        JButton btnMenu_HT = createSidebarButton("Hệ thống", "/icons/system_16.png");
        JPanel systemSubmenuPanel = createSystemSubmenuPanel(); // Gọi hàm mới
        addAccordionListener(btnMenu_HT, systemSubmenuPanel, sidebar); // Thêm listener mới
        sidebar.add(btnMenu_HT);
        sidebar.add(systemSubmenuPanel); // Add panel con (đang ẩn)
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));

        // --- Menu Nhân Viên ---
        JButton btnMenu_NV = createSidebarButton("Nhân viên", "/icons/employee_16.png");
        JPanel employeeSubmenuPanel = createEmployeeSubmenuPanel(); // Gọi hàm mới
        addAccordionListener(btnMenu_NV, employeeSubmenuPanel, sidebar); // Thêm listener mới
        sidebar.add(btnMenu_NV);
        sidebar.add(employeeSubmenuPanel); // Add panel con (đang ẩn)
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));

        // --- Menu Khách Hàng ---
        JButton btnMenu_KH = createSidebarButton("Khách hàng", "/icons/customer_16.png");
        JPanel customerSubmenuPanel = createCustomerSubmenuPanel(); // Gọi hàm mới
        addAccordionListener(btnMenu_KH, customerSubmenuPanel, sidebar); // Thêm listener mới
        sidebar.add(btnMenu_KH);
        sidebar.add(customerSubmenuPanel); // Add panel con (đang ẩn)
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));

        // --- Menu Thuốc ---
        JButton btnMenu_Thuoc = createSidebarButton("Thuốc", "/icons/pill_16.png");
        JPanel medicineSubmenuPanel = createMedicineSubmenuPanel(); // Gọi hàm mới
        addAccordionListener(btnMenu_Thuoc, medicineSubmenuPanel, sidebar); // Thêm listener mới
        sidebar.add(btnMenu_Thuoc);
        sidebar.add(medicineSubmenuPanel); // Add panel con (đang ẩn)
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));

        // --- Menu Hóa Đơn ---
        JButton btnMenu_HD = createSidebarButton("Hoá đơn", "/icons/invoice_16.png");
        JPanel invoiceSubmenuPanel = createInvoiceSubmenuPanel(); // Gọi hàm mới
        addAccordionListener(btnMenu_HD, invoiceSubmenuPanel, sidebar); // Thêm listener mới
        sidebar.add(btnMenu_HD);
        sidebar.add(invoiceSubmenuPanel); // Add panel con (đang ẩn)
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));

        // --- Menu Nhà Cung Cấp ---
        JButton btnMenu_NCC = createSidebarButton("Nhà cung cấp", "/icons/supplier_16.png");
        JPanel supplierSubmenuPanel = createSupplierSubmenuPanel(); // Gọi hàm mới
        addAccordionListener(btnMenu_NCC, supplierSubmenuPanel, sidebar); // Thêm listener mới
        sidebar.add(btnMenu_NCC);
        sidebar.add(supplierSubmenuPanel); // Add panel con (đang ẩn)
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));

        // --- Menu Khuyến mãi ---
        JButton btnMenu_KM = createSidebarButton("Khuyến mãi", "/icons/discount_16.png");
        // (Tạm thời chưa có submenu)
        // addAccordionListener(btnMenu_KM, kmSubmenuPanel, sidebar);
        sidebar.add(btnMenu_KM);
        // sidebar.add(kmSubmenuPanel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));


        sidebar.add(Box.createVerticalGlue()); 

        // --- Nút Đăng xuất ---
     // Trong initialize(), phần tạo nút Đăng xuất ở sidebar
        JButton btnMenu_DX = createSidebarButton("Đăng xuất", "/icons/logout_16.png");
        addHoverEffect(btnMenu_DX);
        btnMenu_DX.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(QuanLyHieuThuocTay, // Hoặc this
             "Bạn có chắc muốn đăng xuất?", "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Đóng TrangChu_GUI
                if (QuanLyHieuThuocTay != null) {
                     QuanLyHieuThuocTay.dispose();
                } else {
                    // Nếu TrangChu_GUI extends JFrame, dùng dispose();
                    // dispose();
                }

                // Reset thông tin đăng nhập
                Dangnhap_GUI.taiKhoanLogin = null;
                Dangnhap_GUI.tenNhanVienLogin = null;

                // Mở lại cửa sổ Đăng nhập
                EventQueue.invokeLater(() -> {
                    Dangnhap_GUI login = new Dangnhap_GUI();
                    login.setVisible(true);
                });
            }
        });
        sidebar.add(btnMenu_DX);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));

        QuanLyHieuThuocTay.getContentPane().add(sidebar, BorderLayout.WEST);
        
        maincontent = new JPanel();
        QuanLyHieuThuocTay.getContentPane().add(maincontent, BorderLayout.CENTER);
        maincontent.setLayout(new CardLayout(0, 0));
        
        JPanel pn_Trangchu = new JPanel();
        maincontent.add(pn_Trangchu, "trangChu");
        
        JLabel welcomeLabel = new JLabel("CHÀO MỪNG ĐẾN VỚI HỆ THỐNG QUẢN LÝ HIỆU THUỐC");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        pn_Trangchu.add(welcomeLabel);
        maincontent.add(pn_Trangchu, "trangChu");
        CardLayout cl = (CardLayout) maincontent.getLayout();
        cl.show(maincontent, "trangChu");
        
        JPanel pn_Themhoadon = new JPanel();
        maincontent.add(pn_Themhoadon, "themHoaDon");
        pn_Themhoadon.setLayout(null);
        pn_Themhoadon.setBackground(COLOR_BACKGROUND_PRIMARY); // ĐÃ SỬA

        JPanel pn_Themhoadon_west = new JPanel();
        pn_Themhoadon_west.setBounds(0, 0, 600, 968);
        pn_Themhoadon_west.setBackground(COLOR_BACKGROUND_PRIMARY); // ĐÃ SỬA
        pn_Themhoadon.add(pn_Themhoadon_west);
        pn_Themhoadon_west.setLayout(null);

        JPanel pn_timkiemthuoc = new JPanel();
        pn_timkiemthuoc.setBounds(10, 11, 579, 195);
        // ĐÃ SỬA - TitledBorder hiện đại hơn
        pn_timkiemthuoc.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Tìm Kiếm",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            FONT_LABEL_BOLD,
            COLOR_PRIMARY_BLUE
        ));
        pn_timkiemthuoc.setBackground(COLOR_CARD_BACKGROUND); // ĐÃ SỬA
        pn_Themhoadon_west.add(pn_timkiemthuoc);
        pn_timkiemthuoc.setLayout(null);

        JLabel lbl_Mathuoc = new JLabel("Mã Thuốc :");
        lbl_Mathuoc.setBounds(30, 29, 115, 28);
        lbl_Mathuoc.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Mathuoc.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        pn_timkiemthuoc.add(lbl_Mathuoc);

        JLabel lbl_Kethuoc = new JLabel("Kệ Thuốc :");
        lbl_Kethuoc.setBounds(30, 68, 115, 28);
        lbl_Kethuoc.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Kethuoc.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        pn_timkiemthuoc.add(lbl_Kethuoc);

        JLabel lbl_Tenthuoc = new JLabel("Tên Thuốc :");
        lbl_Tenthuoc.setBounds(30, 107, 121, 28);
        lbl_Tenthuoc.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Tenthuoc.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        pn_timkiemthuoc.add(lbl_Tenthuoc);

        text_Nhapmathuoc = new JTextField();
        text_Nhapmathuoc.setBounds(154, 29, 415, 28);
        text_Nhapmathuoc.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        pn_timkiemthuoc.add(text_Nhapmathuoc);
        text_Nhapmathuoc.setColumns(10);

        JComboBox<String> cb_loctenthuoc = new JComboBox<String>();
        cb_loctenthuoc.setBounds(154, 109, 415, 28);
        cb_loctenthuoc.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        pn_timkiemthuoc.add(cb_loctenthuoc);

        JButton btn_lammoitkthuoc = new JButton("Làm Mới");
        btn_lammoitkthuoc.setBounds(217, 153, 136, 31);
        btn_lammoitkthuoc.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_lammoitkthuoc, COLOR_TEXT_MUTED); // ĐÃ SỬA
        pn_timkiemthuoc.add(btn_lammoitkthuoc);

        JComboBox<String> cb_lockethuoc = new JComboBox<String>();
        cb_lockethuoc.setModel(new DefaultComboBoxModel(new String[] {"sss"}));
        cb_lockethuoc.setBounds(153, 68, 415, 28);
        cb_lockethuoc.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        pn_timkiemthuoc.add(cb_lockethuoc);

        JScrollPane scP_timkiemthuoc = new JScrollPane();
        scP_timkiemthuoc.setBounds(10, 217, 579, 682);
        scP_timkiemthuoc.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // ĐÃ SỬA
        pn_Themhoadon_west.add(scP_timkiemthuoc);

     // TẠO BẢNG 1 VỚI RENDERER RIÊNG
        table_timkiemthuoc = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    
                }
                return c;
            }
        };
        // GỌI HÀM STYLE CHUNG
        applyCommonTableStyling(table_timkiemthuoc);
        table_timkiemthuoc.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null, null},
            },
            new String[] {
                "Mã Thuốc", "Kệ Thuốc", "Tên Thuốc", "Đơn Giá", "Số Lượng"
            }
        ));
        table_timkiemthuoc.getColumnModel().getColumn(0).setPreferredWidth(90);
        table_timkiemthuoc.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_timkiemthuoc.getColumnModel().getColumn(2).setPreferredWidth(200);
        scP_timkiemthuoc.setViewportView(table_timkiemthuoc);

        JButton btn_Xemchitiet = new JButton("Xem Chi Tiết");
        btn_Xemchitiet.setBounds(195, 910, 180, 47);
        btn_Xemchitiet.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_Xemchitiet, COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        pn_Themhoadon_west.add(btn_Xemchitiet);

        JPanel pn_Themhoadon_east = new JPanel();
        pn_Themhoadon_east.setBounds(701, 0, 1018, 968);
        pn_Themhoadon_east.setBackground(COLOR_BACKGROUND_PRIMARY); // ĐÃ SỬA
        pn_Themhoadon.add(pn_Themhoadon_east);
        pn_Themhoadon_east.setLayout(null);

        JLabel lbl_Laphoadon = new JLabel("LẬP HÓA ĐƠN");
        lbl_Laphoadon.setFont(FONT_TITLE_MAIN); // ĐÃ SỬA
        lbl_Laphoadon.setForeground(COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        lbl_Laphoadon.setBounds(10, 11, 296, 59);
        pn_Themhoadon_east.add(lbl_Laphoadon);

        JButton btn_xemphieudatthuoc = new JButton("Phiếu Đặt Thuốc");
        btn_xemphieudatthuoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DsPhieuDatThuoc_GUI DanhsachphieudatthuocDialog =new DsPhieuDatThuoc_GUI(QuanLyHieuThuocTay);
                DanhsachphieudatthuocDialog.setVisible(true);
            }
        });
        btn_xemphieudatthuoc.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_xemphieudatthuoc, COLOR_TEXT_MUTED); // ĐÃ SỬA
        btn_xemphieudatthuoc.setBounds(614, 64, 186, 43);
        pn_Themhoadon_east.add(btn_xemphieudatthuoc);

        JButton btn_Themkhachhangmoi = new JButton("Thêm Khách Hàng");
        btn_Themkhachhangmoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ThemKH_GUI themKhDialog = new ThemKH_GUI(QuanLyHieuThuocTay);
                themKhDialog.setVisible(true);
            }
        });
        btn_Themkhachhangmoi.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_Themkhachhangmoi, COLOR_TEXT_MUTED); // ĐÃ SỬA
        btn_Themkhachhangmoi.setBounds(810, 64, 198, 43);
        pn_Themhoadon_east.add(btn_Themkhachhangmoi);

        JPanel pn_Hoadonbanle = new JPanel();
        // ĐÃ SỬA - TitledBorder hiện đại hơn
        pn_Hoadonbanle.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Thông Tin Hóa Đơn",
            TitledBorder.LEADING,
            TitledBorder.TOP,
            FONT_LABEL_BOLD,
            COLOR_PRIMARY_BLUE
        ));
        pn_Hoadonbanle.setBackground(COLOR_CARD_BACKGROUND); // ĐÃ SỬA
        pn_Hoadonbanle.setBounds(10, 118, 998, 785);
        pn_Themhoadon_east.add(pn_Hoadonbanle);
        pn_Hoadonbanle.setLayout(null);

        JLabel lbl_Hoadonbanle = new JLabel("Hóa Đơn Bán Lẻ");
        lbl_Hoadonbanle.setFont(FONT_TITLE_SECTION); // ĐÃ SỬA
        lbl_Hoadonbanle.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_Hoadonbanle.setBounds(270, 11, 291, 47);
        pn_Hoadonbanle.add(lbl_Hoadonbanle);

        JLabel lbl_Ngaylaphoadon = new JLabel("Ngày Lập : ");
        lbl_Ngaylaphoadon.setForeground(COLOR_TEXT_MUTED); // ĐÃ SỬA
        lbl_Ngaylaphoadon.setFont(FONT_DETAIL_ITALIC); // ĐÃ SỬA
        lbl_Ngaylaphoadon.setBounds(720, 55, 100, 25);
        pn_Hoadonbanle.add(lbl_Ngaylaphoadon);

        JLabel lbl_hienngaylaphoadon = new JLabel("09/09/2025");
        lbl_hienngaylaphoadon.setForeground(COLOR_DANGER_RED); // ĐÃ SỬA
        lbl_hienngaylaphoadon.setFont(FONT_DETAIL_ITALIC); // ĐÃ SỬA
        lbl_hienngaylaphoadon.setBounds(830, 55, 100, 25);
        pn_Hoadonbanle.add(lbl_hienngaylaphoadon);

        JLabel lbl_Mahd = new JLabel("Mã Hóa Đơn :");
        lbl_Mahd.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Mahd.setBounds(43, 91, 114, 25);
        pn_Hoadonbanle.add(lbl_Mahd);

        JLabel lbl_Nhanvien = new JLabel("Nhân Viên :");
        lbl_Nhanvien.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Nhanvien.setBounds(43, 140, 114, 25);
        pn_Hoadonbanle.add(lbl_Nhanvien);

        JLabel lbl_Sodienthoai = new JLabel("SĐT Khách Hàng :");
        lbl_Sodienthoai.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Sodienthoai.setBounds(545, 91, 160, 25);
        pn_Hoadonbanle.add(lbl_Sodienthoai);

        JLabel lbl_Khachhang = new JLabel("Khách Hàng :");
        lbl_Khachhang.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Khachhang.setBounds(591, 140, 114, 25);
        pn_Hoadonbanle.add(lbl_Khachhang);

        text_Nhapsosdtkh = new JTextField();
        text_Nhapsosdtkh.setBounds(720, 84, 197, 43);
        text_Nhapsosdtkh.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        pn_Hoadonbanle.add(text_Nhapsosdtkh);
        text_Nhapsosdtkh.setColumns(10);

        JButton btn_timsdtkh = new JButton("Tìm");
        btn_timsdtkh.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_timsdtkh, COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        btn_timsdtkh.setBounds(923, 82, 65, 45);
        pn_Hoadonbanle.add(btn_timsdtkh);

        JButton btn_suaslthuoc = new JButton("Sửa Số Lượng");
        btn_suaslthuoc.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_suaslthuoc, COLOR_TEXT_MUTED); // ĐÃ SỬA
        btn_suaslthuoc.setBounds(729, 176, 160, 43);
        pn_Hoadonbanle.add(btn_suaslthuoc);

        JButton btn_xoathuockhoihd = new JButton("Xóa");
        btn_xoathuockhoihd.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_xoathuockhoihd, COLOR_DANGER_RED); // ĐÃ SỬA
        btn_xoathuockhoihd.setBounds(899, 176, 89, 43);
        pn_Hoadonbanle.add(btn_xoathuockhoihd);

        JLabel lbl_hienmahd = new JLabel("HD00000001");
        lbl_hienmahd.setForeground(COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        lbl_hienmahd.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_hienmahd.setBounds(167, 91, 209, 25);
        pn_Hoadonbanle.add(lbl_hienmahd);

        JLabel lbl_Hientennv = new JLabel("Nguyễn Trung Kiên");
        lbl_Hientennv.setForeground(COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        lbl_Hientennv.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Hientennv.setBounds(167, 142, 209, 25);
        pn_Hoadonbanle.add(lbl_Hientennv);

        JLabel lbl_Hientenkh = new JLabel("Nguyễn Ngô Đức Mạnh");
        lbl_Hientenkh.setForeground(COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        lbl_Hientenkh.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Hientenkh.setBounds(720, 140, 268, 25);
        pn_Hoadonbanle.add(lbl_Hientenkh);

        JScrollPane scP_Hdtam = new JScrollPane();
        scP_Hdtam.setBounds(10, 230, 978, 376);
        scP_Hdtam.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // ĐÃ SỬA
        pn_Hoadonbanle.add(scP_Hdtam);

     // TẠO BẢNG 2 VỚI RENDERER RIÊNG
        table_hdtam = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    
                }
                return c;
            }
        };
        // GỌI HÀM STYLE CHUNG
        applyCommonTableStyling(table_hdtam);
        table_hdtam.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null, null},
            },
            new String[] {
                "Mã Thuốc", "Tên Thuốc", "Số Lượng", "Giá Tiền", "Thành Tiền"
            }
        ));
        scP_Hdtam.setViewportView(table_hdtam);

        JLabel lbl_tongtienhang = new JLabel("Tổng Tiền Hàng :");
        lbl_tongtienhang.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_tongtienhang.setBounds(10, 617, 147, 25);
        pn_Hoadonbanle.add(lbl_tongtienhang);

        JLabel lbl_Tiennhan = new JLabel("Tiền Nhận :");
        lbl_Tiennhan.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Tiennhan.setBounds(10, 669, 100, 25);
        pn_Hoadonbanle.add(lbl_Tiennhan);

        JLabel lbl_Thue = new JLabel("Thuế (10% VAT) :");
        lbl_Thue.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Thue.setBounds(311, 617, 160, 25);
        pn_Hoadonbanle.add(lbl_Thue);

        JLabel lbl_Tongcong = new JLabel("Tổng Cộng :");
        lbl_Tongcong.setFont(FONT_SUMMARY_TOTAL); // ĐÃ SỬA
        lbl_Tongcong.setBounds(600, 617, 160, 25);
        pn_Hoadonbanle.add(lbl_Tongcong);

        JLabel lbl_Tienthua = new JLabel("Tiền Thừa :");
        lbl_Tienthua.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Tienthua.setBounds(434, 669, 95, 25);
        pn_Hoadonbanle.add(lbl_Tienthua);

        text_Nhaptiennhan = new JTextField();
        text_Nhaptiennhan.setBounds(120, 666, 246, 34);
        text_Nhaptiennhan.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        pn_Hoadonbanle.add(text_Nhaptiennhan);
        text_Nhaptiennhan.setColumns(10);

        JLabel lbl_Hientienhang = new JLabel("10000000");
        lbl_Hientienhang.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_Hientienhang.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Hientienhang.setBounds(167, 617, 134, 25);
        pn_Hoadonbanle.add(lbl_Hientienhang);

        JLabel lbl_Hienthue = new JLabel("10000000");
        lbl_Hienthue.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_Hienthue.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Hienthue.setBounds(481, 617, 109, 25);
        pn_Hoadonbanle.add(lbl_Hienthue);

        JLabel lbl_Hientongcong = new JLabel("10000000");
        lbl_Hientongcong.setForeground(COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        lbl_Hientongcong.setFont(FONT_SUMMARY_TOTAL); // ĐÃ SỬA
        lbl_Hientongcong.setBounds(853, 617, 135, 25);
        pn_Hoadonbanle.add(lbl_Hientongcong);

        JLabel lbl_Hientienthua = new JLabel("10000000");
        lbl_Hientienthua.setForeground(COLOR_SUCCESS_GREEN); // ĐÃ SỬA
        lbl_Hientienthua.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Hientienthua.setBounds(539, 669, 146, 25);
        pn_Hoadonbanle.add(lbl_Hientienthua);

        JButton btn_Huyhoadon = new JButton("Hủy Hóa Đơn");
        btn_Huyhoadon.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_Huyhoadon, COLOR_DANGER_RED); // ĐÃ SỬA
        btn_Huyhoadon.setBounds(10, 914, 180, 43);
        pn_Themhoadon_east.add(btn_Huyhoadon);

        JButton btn_Themthuocvaophieudat = new JButton("Thêm Vào Phiếu Đặt Thuốc");
        btn_Themthuocvaophieudat.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_Themthuocvaophieudat, COLOR_TEXT_MUTED); // ĐÃ SỬA
        btn_Themthuocvaophieudat.setBounds(257, 914, 290, 43);
        pn_Themhoadon_east.add(btn_Themthuocvaophieudat);

        JButton btn_Xuathoadon = new JButton("Xuất Hóa Đơn");
        btn_Xuathoadon.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_Xuathoadon, COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        btn_Xuathoadon.setBounds(610, 914, 177, 43);
        pn_Themhoadon_east.add(btn_Xuathoadon);

        JButton btn_Thanhtoanhoadon = new JButton("Thanh Toán");
        btn_Thanhtoanhoadon.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_Thanhtoanhoadon, COLOR_SUCCESS_GREEN); // ĐÃ SỬA
        btn_Thanhtoanhoadon.setBounds(822, 914, 186, 43);
        pn_Themhoadon_east.add(btn_Thanhtoanhoadon);

        text_Nhapsoluongthuoc = new JTextField();
        text_Nhapsoluongthuoc.setBounds(610, 567, 81, 37);
        text_Nhapsoluongthuoc.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        text_Nhapsoluongthuoc.setHorizontalAlignment(SwingConstants.CENTER); // ĐÃ SỬA
        pn_Themhoadon.add(text_Nhapsoluongthuoc);
        text_Nhapsoluongthuoc.setColumns(10);

        JButton btn_addthuocvaohoadon = new JButton(">>");
        btn_addthuocvaohoadon.setFont(new Font("Segoe UI", Font.BOLD, 30)); // ĐÃ SỬA
        styleButton(btn_addthuocvaohoadon, COLOR_SUCCESS_GREEN); // ĐÃ SỬA
        btn_addthuocvaohoadon.setBounds(610, 506, 81, 50);
        pn_Themhoadon.add(btn_addthuocvaohoadon);

        JLabel lbl_Nhapsoluongthuoc = new JLabel("<html><center>Nhập<br>Số<br>Lượng</center></html>");
        lbl_Nhapsoluongthuoc.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Nhapsoluongthuoc.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_Nhapsoluongthuoc.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_Nhapsoluongthuoc.setBounds(610, 615, 81, 84);
        pn_Themhoadon.add(lbl_Nhapsoluongthuoc);
        
     // ===== BẮT ĐẦU KHỐI CODE TÌM KIẾM HÓA ĐƠN ĐÃ SỬA =====
        JPanel pn_tkhd = new JPanel();
        maincontent.add(pn_tkhd, "timKiemHoaDon");
        pn_tkhd.setLayout(null);
        pn_tkhd.setBackground(COLOR_BACKGROUND_PRIMARY); // ĐÃ SỬA: Thêm nền

        JPanel pn_Nhapthongtintk = new JPanel();
        pn_Nhapthongtintk.setBackground(COLOR_CARD_BACKGROUND); // ĐÃ SỬA: Nền trắng
        // ĐÃ SỬA: Thêm TitledBorder hiện đại
        pn_Nhapthongtintk.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Bộ Lọc Tìm Kiếm",
            TitledBorder.LEADING,
            TitledBorder.TOP,
            FONT_LABEL_BOLD, // Font cho tiêu đề
            COLOR_PRIMARY_BLUE // Màu cho tiêu đề
        ));
        pn_Nhapthongtintk.setBounds(10, 11, 1699, 161); // ĐÃ SỬA: Điều chỉnh vị trí
        pn_tkhd.add(pn_Nhapthongtintk);
        pn_Nhapthongtintk.setLayout(null);
        
        JLabel lbl_tkhd = new JLabel("TÌM KIẾM HÓA ĐƠN");
        lbl_tkhd.setFont(FONT_TITLE_MAIN); // ĐÃ SỬA
        lbl_tkhd.setForeground(COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        lbl_tkhd.setHorizontalAlignment(SwingConstants.CENTER); // ĐÃ SỬA
        lbl_tkhd.setBounds(596, 11, 544, 41); // ĐÃ SỬA
        pn_Nhapthongtintk.add(lbl_tkhd);
        
        JLabel lbl_tkhd_Tenkh = new JLabel("Tên Khách Hàng :");
        lbl_tkhd_Tenkh.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_tkhd_Tenkh.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_tkhd_Tenkh.setBounds(43, 63, 148, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_Tenkh);
        
        JLabel lbl_tkhd_Mahd = new JLabel("Mã Hóa Đơn :");
        lbl_tkhd_Mahd.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_tkhd_Mahd.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_tkhd_Mahd.setBounds(43, 104, 148, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_Mahd);
        
        JLabel lbl_tkhd_SDTKH = new JLabel("SĐT Khách Hàng :");
        lbl_tkhd_SDTKH.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_tkhd_SDTKH.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_tkhd_SDTKH.setBounds(538, 63, 154, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_SDTKH);
        
        JLabel lbl_tkhd_tenNV = new JLabel("Tên Nhân Viên :");
        lbl_tkhd_tenNV.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_tkhd_tenNV.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_tkhd_tenNV.setBounds(1046, 63, 136, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_tenNV);
        
        JLabel lbl_tkhd_sdtnv = new JLabel("SĐT Nhân Viên :");
        lbl_tkhd_sdtnv.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_tkhd_sdtnv.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_tkhd_sdtnv.setBounds(538, 104, 154, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_sdtnv);
        
        JLabel lbl_tkhd_Ngaylaphd = new JLabel("Ngày Lập HĐ :");
        lbl_tkhd_Ngaylaphd.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_tkhd_Ngaylaphd.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_tkhd_Ngaylaphd.setBounds(1046, 104, 136, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_Ngaylaphd);
        
        text_tkhd_TenKH = new JTextField();
        text_tkhd_TenKH.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        text_tkhd_TenKH.setBounds(194, 63, 268, 30);
        pn_Nhapthongtintk.add(text_tkhd_TenKH);
        text_tkhd_TenKH.setColumns(10);
        
        text_tkhd_Mahd = new JTextField();
        text_tkhd_Mahd.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        text_tkhd_Mahd.setColumns(10);
        text_tkhd_Mahd.setBounds(194, 104, 268, 30);
        pn_Nhapthongtintk.add(text_tkhd_Mahd);
        
        text_tkhd_sdtkh = new JTextField();
        text_tkhd_sdtkh.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        text_tkhd_sdtkh.setColumns(10);
        text_tkhd_sdtkh.setBounds(702, 63, 268, 30);
        pn_Nhapthongtintk.add(text_tkhd_sdtkh);
        
        text_tkhd_sdtnv = new JTextField();
        text_tkhd_sdtnv.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        text_tkhd_sdtnv.setColumns(10);
        text_tkhd_sdtnv.setBounds(702, 104, 268, 30);
        pn_Nhapthongtintk.add(text_tkhd_sdtnv);
        
        text_tkhd_tennv = new JTextField();
        text_tkhd_tennv.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        text_tkhd_tennv.setColumns(10);
        text_tkhd_tennv.setBounds(1192, 63, 268, 30);
        pn_Nhapthongtintk.add(text_tkhd_tennv);
        
        // ĐÃ SỬA: Dùng JDateChooser thay vì JTextField
        date_tkhd_ngaylaphd = new JDateChooser();
        date_tkhd_ngaylaphd.setFont(FONT_TEXT_FIELD);
        date_tkhd_ngaylaphd.setDateFormatString("dd/MM/yyyy");
        date_tkhd_ngaylaphd.setBounds(1192, 104, 268, 30);
        pn_Nhapthongtintk.add(date_tkhd_ngaylaphd);
        
        JButton btn_tkhd_lammoi = new JButton("Làm Mới");
        btn_tkhd_lammoi.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_tkhd_lammoi, COLOR_TEXT_MUTED); // ĐÃ SỬA
        btn_tkhd_lammoi.setBounds(1535, 63, 141, 30); // ĐÃ SỬA
        pn_Nhapthongtintk.add(btn_tkhd_lammoi);
        
        JButton btn_tkhd_xemchitiet = new JButton("Xem Chi Tiết");
        btn_tkhd_xemchitiet.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		XemchitietHD_GUI XemchitietHD =new XemchitietHD_GUI(QuanLyHieuThuocTay); // Hoặc (this)
                XemchitietHD.setVisible(true);
        	}
        });
        btn_tkhd_xemchitiet.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_tkhd_xemchitiet, COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        btn_tkhd_xemchitiet.setBounds(1535, 104, 141, 30); // ĐÃ SỬA
        pn_Nhapthongtintk.add(btn_tkhd_xemchitiet);
        
        JScrollPane scP_tabletkhd = new JScrollPane();
        scP_tabletkhd.setBounds(10, 183, 1699, 796); // ĐÃ SỬA
        scP_tabletkhd.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // ĐÃ SỬA
        pn_tkhd.add(scP_tabletkhd);
        
        // ĐÃ SỬA: Áp dụng style cho JTable (giống hệt cách làm trước)
        table_tkhd = new JTable() {
            // ----- TẠO HIỆU ỨNG SỌC VẰN (ZEBRA-STRIPING) -----
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE); // Màu khi chọn dòng
                    
                }
                return c;
            }
        };
        
        // ----- TÙY CHỈNH CHUNG CHO BẢNG -----
        table_tkhd.setFont(FONT_TABLE_CELL);
        table_tkhd.setRowHeight(30);
        table_tkhd.setGridColor(COLOR_BORDER_LIGHT);
        table_tkhd.setShowGrid(true);
        table_tkhd.setSelectionBackground(COLOR_PRIMARY_BLUE);
        table_tkhd.setSelectionForeground(Color.WHITE);
        
        // ----- TÙY CHỈNH HEADER CỦA BẢNG -----
        JTableHeader header_tkhd = table_tkhd.getTableHeader();
        header_tkhd.setFont(FONT_TABLE_HEADER);
        header_tkhd.setBackground(new Color(230, 235, 240));
        header_tkhd.setForeground(COLOR_TEXT_DARK);
        header_tkhd.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        header_tkhd.setReorderingAllowed(false);
        header_tkhd.setResizingAllowed(true);

        table_tkhd.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"Mã Hóa Đơn", "Mã Khách Hàng", "Ngày Lập HD", "Tên Khách Hàng", "SĐT KH", "Tên Nhân Viên", "SĐT NV", "Tổng Tiền"
        	}
        ));
        scP_tabletkhd.setViewportView(table_tkhd);
        
        // ===== KẾT THÚC KHỐI CODE TÌM KIẾM HÓA ĐƠN ĐÃ SỬA =====
        
        
        
     // ===== BẮT ĐẦU KHỐI CODE TÌM KIẾM THUỐC ĐÃ SỬA =====
        JPanel pn_Timkiemthuoc = new JPanel();
        maincontent.add(pn_Timkiemthuoc, "timkiemSP");
        pn_Timkiemthuoc.setLayout(null);
        pn_Timkiemthuoc.setBackground(COLOR_BACKGROUND_PRIMARY); // ĐÃ SỬA: Nền chính

        // ĐÃ SỬA: Tạo panel riêng cho bộ lọc
        JPanel pn_tkt_filters = new JPanel();
        pn_tkt_filters.setBackground(COLOR_CARD_BACKGROUND);
        pn_tkt_filters.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Bộ Lọc Tìm Kiếm", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        ));
        pn_tkt_filters.setBounds(10, 68, 1564, 110); // ĐÃ SỬA: Vị trí và kích thước
        pn_Timkiemthuoc.add(pn_tkt_filters);
        pn_tkt_filters.setLayout(null);


        JLabel lbl_tkthuoc = new JLabel("TÌM KIẾM THUỐC");
        lbl_tkthuoc.setFont(FONT_TITLE_MAIN); // ĐÃ SỬA
        lbl_tkthuoc.setForeground(COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        lbl_tkthuoc.setHorizontalAlignment(SwingConstants.CENTER); // ĐÃ SỬA
        lbl_tkthuoc.setBounds(0, 11, 1584, 46); // ĐÃ SỬA: Căn giữa
        pn_Timkiemthuoc.add(lbl_tkthuoc); // Vẫn add vào panel chính

        JLabel lbl_tkt_kethuoc = new JLabel("Kệ Thuốc :");
        lbl_tkt_kethuoc.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_tkt_kethuoc.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_tkt_kethuoc.setBounds(20, 30, 100, 30); // ĐÃ SỬA: Vị trí trong pn_tkt_filters
        pn_tkt_filters.add(lbl_tkt_kethuoc); // ĐÃ SỬA: Add vào panel lọc

        JLabel lbl_tkt_Tenthuoc = new JLabel("Tên Thuốc :");
        lbl_tkt_Tenthuoc.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_tkt_Tenthuoc.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_tkt_Tenthuoc.setBounds(20, 71, 100, 30); // ĐÃ SỬA
        pn_tkt_filters.add(lbl_tkt_Tenthuoc); // ĐÃ SỬA

        JLabel lbl_tkt_ncc = new JLabel("Nhà Cung Cấp :");
        lbl_tkt_ncc.setFont(FONT_LABEL_BOLD); // ĐÃ SỬA
        lbl_tkt_ncc.setForeground(COLOR_TEXT_DARK); // ĐÃ SỬA
        lbl_tkt_ncc.setBounds(670, 30, 135, 30); // ĐÃ SỬA
        pn_tkt_filters.add(lbl_tkt_ncc); // ĐÃ SỬA

        cb_tkt_kethuoc = new JComboBox<String>();
        cb_tkt_kethuoc.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        cb_tkt_kethuoc.setBounds(130, 30, 529, 30); // ĐÃ SỬA
        pn_tkt_filters.add(cb_tkt_kethuoc); // ĐÃ SỬA

        cb_tkt_tenthuoc = new JComboBox<String>();
        cb_tkt_tenthuoc.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        cb_tkt_tenthuoc.setBounds(130, 71, 529, 30); // ĐÃ SỬA
        pn_tkt_filters.add(cb_tkt_tenthuoc); // ĐÃ SỬA

        cb_tkt_ncc = new JComboBox<String>();
        cb_tkt_ncc.setFont(FONT_TEXT_FIELD); // ĐÃ SỬA
        cb_tkt_ncc.setBounds(815, 30, 540, 30); // ĐÃ SỬA
        pn_tkt_filters.add(cb_tkt_ncc); // ĐÃ SỬA

        btn_tkt_xemchitiet = new JButton("Xem Chi Tiết");
        btn_tkt_xemchitiet.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ChiTietThuoc_GUI Chitietphieudatthuoc = new ChiTietThuoc_GUI(QuanLyHieuThuocTay);
        	}
        });
        btn_tkt_xemchitiet.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_tkt_xemchitiet, COLOR_PRIMARY_BLUE); // ĐÃ SỬA
        btn_tkt_xemchitiet.setBounds(1176, 70, 179, 36); // ĐÃ SỬA
        pn_tkt_filters.add(btn_tkt_xemchitiet); // ĐÃ SỬA

        btn_tkt_Lammoi = new JButton("Làm Mới");
        btn_tkt_Lammoi.setFont(FONT_BUTTON_STANDARD); // ĐÃ SỬA
        styleButton(btn_tkt_Lammoi, COLOR_TEXT_MUTED); // ĐÃ SỬA
        btn_tkt_Lammoi.setBounds(1388, 70, 135, 36); // ĐÃ SỬA
        pn_tkt_filters.add(btn_tkt_Lammoi); // ĐÃ SỬA

        // ĐÃ SỬA: Bỏ pn_tkt_table vì JScrollPane đã đủ
        JScrollPane scP_tkt_table = new JScrollPane();
        scP_tkt_table.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // ĐÃ SỬA
        scP_tkt_table.setBounds(10, 189, 1564, 739); // ĐÃ SỬA: Vị trí và kích thước
        pn_Timkiemthuoc.add(scP_tkt_table); // ĐÃ SỬA: Add thẳng vào panel chính

        // Áp dụng đúng cách tạo bảng table_tkt
        table_tkt = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    
                }
                return c;
            }
        };
        applyCommonTableStyling(table_tkt); // Áp dụng style chung
        table_tkt.setModel(new DefaultTableModel(
        	new Object[][] {}, // Bỏ dòng null
        	new String[] {
        		"Mã Thuốc", "Tên Thuốc", "Số Lượng", "Giá Nhập", "Giá Bán", "Đơn Vị Tính", "Nhà Cung Cấp", "Hạn Sử Dụng", "Tên Kệ Thuốc"
        	}
        ));
        table_tkt.getColumnModel().getColumn(2).setPreferredWidth(65);
        scP_tkt_table.setViewportView(table_tkt);

        // ===== KẾT THÚC KHỐI CODE TÌM KIẾM THUỐC ĐÃ SỬA =====
        
     // ===== BẮT ĐẦU KHỐI CODE TÌM KIẾM KHÁCH HÀNG ĐÃ SỬA (GIỮ NGUYÊN TÊN BIẾN) =====
        JPanel pn_TKKhachHang = new JPanel();
        maincontent.add(pn_TKKhachHang, "timkiemkh");
        pn_TKKhachHang.setLayout(null);
        pn_TKKhachHang.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        JLabel lblHeader_TKKH = new JLabel("TÌM KIẾM KHÁCH HÀNG");
        lblHeader_TKKH.setFont(FONT_TITLE_MAIN); // Font tiêu đề chính
        lblHeader_TKKH.setForeground(COLOR_PRIMARY_BLUE); // Màu tiêu đề
        lblHeader_TKKH.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lblHeader_TKKH.setBounds(0, 11, 1584, 46); // Vị trí tiêu đề
        pn_TKKhachHang.add(lblHeader_TKKH);

        // Panel chứa bộ lọc tìm kiếm
        JPanel pnl_KH_north = new JPanel();
        pnl_KH_north.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
        pnl_KH_north.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Bộ Lọc Tìm Kiếm", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        )); // Viền và tiêu đề cho bộ lọc
        pnl_KH_north.setBounds(10, 68, 1564, 177); // Vị trí panel lọc
        pn_TKKhachHang.add(pnl_KH_north);
        pnl_KH_north.setLayout(null);

        JLabel lbl_kh_MaKH = new JLabel("Mã khách hàng :");
        lbl_kh_MaKH.setFont(FONT_LABEL_BOLD); // Font label
        lbl_kh_MaKH.setForeground(COLOR_TEXT_DARK); // Màu chữ label
        lbl_kh_MaKH.setBounds(38, 40, 148, 30); // Vị trí label mã KH
        pnl_KH_north.add(lbl_kh_MaKH);

        txt_kh_MaKH = new JTextField();
        txt_kh_MaKH.setFont(FONT_TEXT_FIELD); // Font text field
        txt_kh_MaKH.setBounds(196, 40, 471, 32); // Vị trí text field mã KH
        pnl_KH_north.add(txt_kh_MaKH);
        txt_kh_MaKH.setColumns(10);

        JLabel lbl_kh_SDT = new JLabel("Số điện thoại :");
        lbl_kh_SDT.setFont(FONT_LABEL_BOLD); // Font label
        lbl_kh_SDT.setForeground(COLOR_TEXT_DARK); // Màu chữ label
        lbl_kh_SDT.setBounds(38, 88, 148, 30); // Vị trí label SĐT
        pnl_KH_north.add(lbl_kh_SDT);

        txt_kh_SDT = new JTextField();
        txt_kh_SDT.setFont(FONT_TEXT_FIELD); // Font text field
        txt_kh_SDT.setColumns(10);
        txt_kh_SDT.setBounds(196, 88, 471, 32); // Vị trí text field SĐT
        pnl_KH_north.add(txt_kh_SDT);

        JLabel lbl_kh_TenKH = new JLabel("Tên khách hàng :");
        lbl_kh_TenKH.setFont(FONT_LABEL_BOLD); // Font label
        lbl_kh_TenKH.setForeground(COLOR_TEXT_DARK); // Màu chữ label
        lbl_kh_TenKH.setBounds(755, 40, 156, 30); // Vị trí label tên KH
        pnl_KH_north.add(lbl_kh_TenKH);

        txt_kh_TenKH = new JTextField();
        txt_kh_TenKH.setFont(FONT_TEXT_FIELD); // Font text field
        txt_kh_TenKH.setColumns(10);
        txt_kh_TenKH.setBounds(919, 40, 471, 32); // Vị trí text field tên KH
        pnl_KH_north.add(txt_kh_TenKH);

        JLabel lbl_kh_Diachi = new JLabel("Địa chỉ :");
        lbl_kh_Diachi.setFont(FONT_LABEL_BOLD); // Font label
        lbl_kh_Diachi.setForeground(COLOR_TEXT_DARK); // Màu chữ label
        lbl_kh_Diachi.setBounds(759, 88, 152, 30); // Vị trí label địa chỉ
        pnl_KH_north.add(lbl_kh_Diachi);

        txt_kh_dc = new JTextField();
        txt_kh_dc.setFont(FONT_TEXT_FIELD); // Font text field
        txt_kh_dc.setColumns(10);
        txt_kh_dc.setBounds(919, 88, 471, 32); // Vị trí text field địa chỉ
        pnl_KH_north.add(txt_kh_dc);

        // Tạo nút Làm mới (GIỮ NGUYÊN TÊN BIẾN btn_kh_Lammoi)
        btn_kh_Lammoi = new JButton("Làm mới");
        btn_kh_Lammoi.setFont(FONT_BUTTON_STANDARD); // Font nút
        styleButton(btn_kh_Lammoi, COLOR_TEXT_MUTED); // Style nút phụ
        btn_kh_Lammoi.setBounds(1424, 134, 118, 32); // Vị trí nút làm mới
        pnl_KH_north.add(btn_kh_Lammoi);

        // ScrollPane chứa bảng kết quả
        JScrollPane scrollPane_tkkh = new JScrollPane();
        scrollPane_tkkh.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền ScrollPane
        scrollPane_tkkh.setBounds(10, 256, 1564, 672); // Vị trí ScrollPane
        pn_TKKhachHang.add(scrollPane_tkkh); // Add vào panel chính

        // Tạo và style bảng table_tkkh
        table_tkkh = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    // Hiệu ứng sọc vằn
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    // Màu khi chọn dòng
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    
                }
                return c;
            }
        };
        applyCommonTableStyling(table_tkkh); // Áp dụng style chung (font, header, row height...)
        table_tkkh.setModel(new DefaultTableModel(
        	new Object[][] {}, // Bỏ dữ liệu mẫu null
        	new String[] {
        		"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ"
        	}
        ));
        // Giữ lại setPreferredWidth nếu cần thiết
        table_tkkh.getColumnModel().getColumn(0).setPreferredWidth(99);
        table_tkkh.getColumnModel().getColumn(1).setPreferredWidth(92);

        scrollPane_tkkh.setViewportView(table_tkkh); // Đặt bảng vào ScrollPane

        // ===== KẾT THÚC KHỐI CODE TÌM KIẾM KHÁCH HÀNG ĐÃ SỬA =====
        
     // ===== BẮT ĐẦU KHỐI CODE CẬP NHẬT KHÁCH HÀNG ĐÃ SỬA =====
        JPanel pn_CapnhatKh = new JPanel();
        maincontent.add(pn_CapnhatKh, "capNhatKH");
        pn_CapnhatKh.setLayout(null);
        pn_CapnhatKh.setBackground(COLOR_BACKGROUND_PRIMARY);

        JLabel lblHeader_CNKH = new JLabel("CẬP NHẬT THÔNG TIN KHÁCH HÀNG");
        lblHeader_CNKH.setFont(FONT_TITLE_MAIN);
        lblHeader_CNKH.setForeground(COLOR_PRIMARY_BLUE);
        lblHeader_CNKH.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader_CNKH.setBounds(0, 11, 1584, 46);
        pn_CapnhatKh.add(lblHeader_CNKH);

        JPanel pnlNorth = new JPanel();
        pnlNorth.setBackground(COLOR_CARD_BACKGROUND);
        pnlNorth.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Thông Tin Cập Nhật", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        ));
        pnlNorth.setBounds(10, 68, 1564, 241);
        pn_CapnhatKh.add(pnlNorth);
        pnlNorth.setLayout(null);

        JLabel lblMaKh = new JLabel("Mã khách hàng :");
        lblMaKh.setFont(FONT_LABEL_BOLD);
        lblMaKh.setForeground(COLOR_TEXT_DARK);
        lblMaKh.setBounds(192, 40, 141, 30);
        pnlNorth.add(lblMaKh);

        txt_cnkh_MaKh = new JTextField();
        txt_cnkh_MaKh.setFont(FONT_TEXT_FIELD);
        txt_cnkh_MaKh.setBounds(354, 40, 301, 33);
        txt_cnkh_MaKh.setEditable(false); // Giữ nguyên trạng thái không sửa được
        txt_cnkh_MaKh.setBackground(new Color(230, 230, 230)); // Giữ màu nền xám
        pnlNorth.add(txt_cnkh_MaKh);
        txt_cnkh_MaKh.setColumns(10);

        JLabel lblSdt = new JLabel("Số điện thoại :");
        lblSdt.setFont(FONT_LABEL_BOLD);
        lblSdt.setForeground(COLOR_TEXT_DARK);
        lblSdt.setBounds(192, 90, 141, 30);
        pnlNorth.add(lblSdt);

        txt_cnkh_SDt = new JTextField();
        txt_cnkh_SDt.setFont(FONT_TEXT_FIELD);
        txt_cnkh_SDt.setColumns(10);
        txt_cnkh_SDt.setBounds(354, 90, 301, 33);
        pnlNorth.add(txt_cnkh_SDt);

        JLabel lblTenKh = new JLabel("Tên khách hàng :");
        lblTenKh.setFont(FONT_LABEL_BOLD);
        lblTenKh.setForeground(COLOR_TEXT_DARK);
        lblTenKh.setBounds(700, 40, 163, 30);
        pnlNorth.add(lblTenKh);

        txt_cnkh_tenkh = new JTextField();
        txt_cnkh_tenkh.setFont(FONT_TEXT_FIELD);
        txt_cnkh_tenkh.setColumns(10);
        txt_cnkh_tenkh.setBounds(870, 40, 550, 33);
        pnlNorth.add(txt_cnkh_tenkh);

        JLabel lblDC = new JLabel("Địa chỉ  :");
        lblDC.setFont(FONT_LABEL_BOLD);
        lblDC.setForeground(COLOR_TEXT_DARK);
        lblDC.setBounds(700, 90, 163, 30);
        pnlNorth.add(lblDC);

        txt_cnkh_dc = new JTextField();
        txt_cnkh_dc.setFont(FONT_TEXT_FIELD);
        txt_cnkh_dc.setColumns(10);
        txt_cnkh_dc.setBounds(870, 90, 550, 33);
        pnlNorth.add(txt_cnkh_dc);

        btn_cnkh_Khoiphuc = new JButton("Khôi phục");
        btn_cnkh_Khoiphuc.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_cnkh_Khoiphuc, COLOR_TEXT_MUTED);
        btn_cnkh_Khoiphuc.setBounds(1150, 180, 130, 40);
        pnlNorth.add(btn_cnkh_Khoiphuc);

        btn_cnkh_CapNhat = new JButton("Cập nhật");
        btn_cnkh_CapNhat.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_cnkh_CapNhat, COLOR_SUCCESS_GREEN);
        btn_cnkh_CapNhat.setBounds(1300, 180, 130, 40);
        pnlNorth.add(btn_cnkh_CapNhat);

        JPanel pnlTimkiem = new JPanel();
        pnlTimkiem.setBackground(COLOR_CARD_BACKGROUND);
        Font titleFont = FONT_LABEL_BOLD; // Dùng font chuẩn
        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
             "Tìm Kiếm Khách Hàng Cần Cập Nhật", TitledBorder.LEADING, TitledBorder.TOP,
             titleFont, COLOR_PRIMARY_BLUE
        );
        pnlTimkiem.setBorder(border);
        pnlTimkiem.setBounds(10, 320, 1564, 84);
        pn_CapnhatKh.add(pnlTimkiem);
        pnlTimkiem.setLayout(null);

        JLabel lblMaKh_TK = new JLabel("Mã khách hàng :");
        lblMaKh_TK.setBounds(40, 30, 140, 30);
        lblMaKh_TK.setFont(FONT_LABEL_BOLD);
        lblMaKh_TK.setForeground(COLOR_TEXT_DARK);
        pnlTimkiem.add(lblMaKh_TK);

        txtMKH_TK = new JTextField();
        txtMKH_TK.setFont(FONT_TEXT_FIELD);
        txtMKH_TK.setColumns(10);
        txtMKH_TK.setBounds(190, 30, 301, 33);
        pnlTimkiem.add(txtMKH_TK);

        JLabel lblTenKh_TK = new JLabel("Tên khách hàng :");
        lblTenKh_TK.setFont(FONT_LABEL_BOLD);
        lblTenKh_TK.setForeground(COLOR_TEXT_DARK);
        lblTenKh_TK.setBounds(540, 30, 163, 30);
        pnlTimkiem.add(lblTenKh_TK);

        txtTenKH_TK = new JTextField();
        txtTenKH_TK.setFont(FONT_TEXT_FIELD);
        txtTenKH_TK.setColumns(10);
        txtTenKH_TK.setBounds(710, 30, 471, 33);
        pnlTimkiem.add(txtTenKH_TK);

        btnLammoi_CNKH = new JButton("Làm mới TK");
        btnLammoi_CNKH.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnLammoi_CNKH, COLOR_TEXT_MUTED);
        btnLammoi_CNKH.setBounds(1424, 28, 130, 35);
        pnlTimkiem.add(btnLammoi_CNKH);

        JScrollPane scrollPane_CapNhatKH = new JScrollPane();
        scrollPane_CapNhatKH.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scrollPane_CapNhatKH.setBounds(10, 415, 1564, 513);
        pn_CapnhatKh.add(scrollPane_CapNhatKH);

        table_CapNhatKH = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                // JTable table = (JTable) c.getParent(); // Bỏ dòng này
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground()); // Dùng this
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    // Không set foreground trắng nữa
                }
                return c;
            }
        };
        applyCommonTableStyling(table_CapNhatKH);
        table_CapNhatKH.setModel(new DefaultTableModel(
        	new Object[][] {},
        	new String[] {
        		"Mã khách hàng", "Tên Khách hàng", "Số điện thoại", "Địa chỉ"
        	}
        ));
        scrollPane_CapNhatKH.setViewportView(table_CapNhatKH);

        // ===== KẾT THÚC KHỐI CODE CẬP NHẬT KHÁCH HÀNG ĐÃ SỬA =====
        
     // ===== BẮT ĐẦU KHỐI CODE CẬP NHẬT THUỐC ĐÃ SỬA =====
        JPanel pn_Capnhatthuoc = new JPanel();
        maincontent.add(pn_Capnhatthuoc, "Capnhatthuoc");
        pn_Capnhatthuoc.setLayout(null);
        pn_Capnhatthuoc.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        JLabel lbl_Capnhatthuoc = new JLabel("CẬP NHẬT THÔNG TIN THUỐC"); // Tiêu đề
        lbl_Capnhatthuoc.setFont(FONT_TITLE_MAIN);
        lbl_Capnhatthuoc.setForeground(COLOR_PRIMARY_BLUE);
        lbl_Capnhatthuoc.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Capnhatthuoc.setBounds(0, 11, 1584, 59);
        pn_Capnhatthuoc.add(lbl_Capnhatthuoc); // Add vào panel chính

        // Panel chứa form nhập thông tin cập nhật thuốc
        JPanel pn_Capnhatthuoc_nhaptt = new JPanel();
        pn_Capnhatthuoc_nhaptt.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
        pn_Capnhatthuoc_nhaptt.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Thông Tin Thuốc", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        )); // Viền và tiêu đề
        pn_Capnhatthuoc_nhaptt.setBounds(10, 80, 1564, 410); // Điều chỉnh vị trí, kích thước
        pn_Capnhatthuoc.add(pn_Capnhatthuoc_nhaptt);
        pn_Capnhatthuoc_nhaptt.setLayout(null);

        // Panel chứa ảnh
        JPanel pn_chuaanh = new JPanel();
        pn_chuaanh.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền nhạt
        pn_chuaanh.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền xám nhạt
        pn_chuaanh.setBounds(34, 40, 330, 300); // Điều chỉnh vị trí
        pn_Capnhatthuoc_nhaptt.add(pn_chuaanh);
        pn_chuaanh.setLayout(new BorderLayout(0, 0)); // Dùng BorderLayout

        lb_Chuaanh = new JLabel("Chưa có ảnh", SwingConstants.CENTER); // Text mặc định
        lb_Chuaanh.setFont(FONT_DETAIL_ITALIC); // Font italic
        lb_Chuaanh.setForeground(COLOR_TEXT_MUTED); // Màu nhạt
        pn_chuaanh.add(lb_Chuaanh, BorderLayout.CENTER);

        btn_cnt_ChonAnh = new JButton("Chọn Ảnh");
        btn_cnt_ChonAnh.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_cnt_ChonAnh, COLOR_TEXT_MUTED);
        btn_cnt_ChonAnh.setBounds(125, 350, 132, 40); // Điều chỉnh vị trí
        pn_Capnhatthuoc_nhaptt.add(btn_cnt_ChonAnh);

        // Các label và input fields
        int labelX = 400; // Vị trí X cột 1
        int inputX = 530; // Vị trí X cột 1
        int labelX2 = 1000; // Vị trí X cột 2
        int inputX2 = 1140; // Vị trí X cột 2
        int startY = 40; // Vị trí Y dòng đầu
        int height = 33; // Chiều cao component
        int vGap = 12; // Khoảng cách dọc

        JLabel lbl_cntmt = new JLabel("Mã Thuốc :");
        lbl_cntmt.setFont(FONT_LABEL_BOLD);
        lbl_cntmt.setForeground(COLOR_TEXT_DARK);
        lbl_cntmt.setBounds(labelX, startY, 120, height);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntmt);

        text_cntmt = new JTextField();
        text_cntmt.setFont(FONT_TEXT_FIELD);
        text_cntmt.setBounds(inputX, startY, 400, height);
        text_cntmt.setEditable(false); // Mã thường không sửa
        text_cntmt.setBackground(new Color(230, 230, 230)); // Nền xám
        pn_Capnhatthuoc_nhaptt.add(text_cntmt);
        text_cntmt.setColumns(10);

        JLabel lbl_cnttt = new JLabel("Tên Thuốc :");
        lbl_cnttt.setFont(FONT_LABEL_BOLD);
        lbl_cnttt.setForeground(COLOR_TEXT_DARK);
        lbl_cnttt.setBounds(labelX, startY + height + vGap, 120, height);
        pn_Capnhatthuoc_nhaptt.add(lbl_cnttt);

        text_cnttt = new JTextField();
        text_cnttt.setFont(FONT_TEXT_FIELD);
        text_cnttt.setColumns(10);
        text_cnttt.setBounds(inputX, startY + height + vGap, 400, height);
        pn_Capnhatthuoc_nhaptt.add(text_cnttt);

        JLabel lbl_cntsl = new JLabel("Số Lượng :");
        lbl_cntsl.setFont(FONT_LABEL_BOLD);
        lbl_cntsl.setForeground(COLOR_TEXT_DARK);
        lbl_cntsl.setBounds(labelX, startY + 2 * (height + vGap), 120, height);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntsl);

        text_cntsl = new JTextField();
        text_cntsl.setFont(FONT_TEXT_FIELD);
        text_cntsl.setColumns(10);
        text_cntsl.setBounds(inputX, startY + 2 * (height + vGap), 400, height);
        pn_Capnhatthuoc_nhaptt.add(text_cntsl);

        JLabel lbl_cntgn = new JLabel("Giá Nhập :");
        lbl_cntgn.setFont(FONT_LABEL_BOLD);
        lbl_cntgn.setForeground(COLOR_TEXT_DARK);
        lbl_cntgn.setBounds(labelX, startY + 3 * (height + vGap), 120, height);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntgn);

        text_cntgn = new JTextField();
        text_cntgn.setFont(FONT_TEXT_FIELD);
        text_cntgn.setColumns(10);
        text_cntgn.setBounds(inputX, startY + 3 * (height + vGap), 400, height);
        pn_Capnhatthuoc_nhaptt.add(text_cntgn);

        JLabel lbl_cntgb = new JLabel("Giá Bán :");
        lbl_cntgb.setFont(FONT_LABEL_BOLD);
        lbl_cntgb.setForeground(COLOR_TEXT_DARK);
        lbl_cntgb.setBounds(labelX, startY + 4 * (height + vGap), 120, height);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntgb);

        text_cntgb = new JTextField();
        text_cntgb.setFont(FONT_TEXT_FIELD);
        text_cntgb.setColumns(10);
        text_cntgb.setBounds(inputX, startY + 4 * (height + vGap), 400, height);
        pn_Capnhatthuoc_nhaptt.add(text_cntgb);

        JLabel lbl_cntdvt = new JLabel("Đơn Vị Tính :");
        lbl_cntdvt.setFont(FONT_LABEL_BOLD);
        lbl_cntdvt.setForeground(COLOR_TEXT_DARK);
        lbl_cntdvt.setBounds(labelX, startY + 5 * (height + vGap), 120, height);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntdvt);

        cb_cntdvt = new JComboBox<String>();
        cb_cntdvt.setFont(FONT_TEXT_FIELD);
        cb_cntdvt.setBounds(inputX, startY + 5 * (height + vGap), 400, height);
        pn_Capnhatthuoc_nhaptt.add(cb_cntdvt);

        JLabel lbl_cntncc = new JLabel("Nhà Cung Cấp :");
        lbl_cntncc.setFont(FONT_LABEL_BOLD);
        lbl_cntncc.setForeground(COLOR_TEXT_DARK);
        lbl_cntncc.setBounds(labelX2, startY, 130, height);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntncc);

        cb_cntncc = new JComboBox<String>();
        cb_cntncc.setFont(FONT_TEXT_FIELD);
        cb_cntncc.setBounds(inputX2, startY, 400, height);
        pn_Capnhatthuoc_nhaptt.add(cb_cntncc);

        JLabel lbl_cnthsd = new JLabel("Hạn Sử Dụng :");
        lbl_cnthsd.setFont(FONT_LABEL_BOLD);
        lbl_cnthsd.setForeground(COLOR_TEXT_DARK);
        lbl_cnthsd.setBounds(labelX2, startY + height + vGap, 130, height);
        pn_Capnhatthuoc_nhaptt.add(lbl_cnthsd);

        // Dùng JDateChooser cho HSD
        date_cnthsd = new JDateChooser();
        date_cnthsd.setFont(FONT_TEXT_FIELD);
        date_cnthsd.setDateFormatString("dd/MM/yyyy");
        date_cnthsd.setBounds(inputX2, startY + height + vGap, 400, height);
        pn_Capnhatthuoc_nhaptt.add(date_cnthsd);

        JLabel lbl_cnttkt = new JLabel("Tên Kệ Thuốc :");
        lbl_cnttkt.setFont(FONT_LABEL_BOLD);
        lbl_cnttkt.setForeground(COLOR_TEXT_DARK);
        lbl_cnttkt.setBounds(labelX2, startY + 2 * (height + vGap), 130, height);
        pn_Capnhatthuoc_nhaptt.add(lbl_cnttkt);

        cb_cnt_tkt = new JComboBox<String>();
        cb_cnt_tkt.setFont(FONT_TEXT_FIELD);
        cb_cnt_tkt.setBounds(inputX2, startY + 2 * (height + vGap), 400, height);
        pn_Capnhatthuoc_nhaptt.add(cb_cnt_tkt);

        JLabel lbl_cnttp = new JLabel("Thành Phần :");
        lbl_cnttp.setFont(FONT_LABEL_BOLD);
        lbl_cnttp.setForeground(COLOR_TEXT_DARK);
        lbl_cnttp.setBounds(labelX2, startY + 3 * (height + vGap), 130, height);
        pn_Capnhatthuoc_nhaptt.add(lbl_cnttp);

        // Dùng JScrollPane cho JTextArea
        JScrollPane scrollPane_cnttp = new JScrollPane();
        scrollPane_cnttp.setBounds(inputX2, startY + 3 * (height + vGap), 400, 110);
        pn_Capnhatthuoc_nhaptt.add(scrollPane_cnttp);

        textArea_cnttp = new JTextArea();
        textArea_cnttp.setWrapStyleWord(true);
        textArea_cnttp.setLineWrap(true);
        textArea_cnttp.setFont(FONT_TEXT_FIELD);
        scrollPane_cnttp.setViewportView(textArea_cnttp); // Add TextArea vào ScrollPane

        btn_cntKhoiphuc = new JButton("Khôi Phục");
        btn_cntKhoiphuc.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_cntKhoiphuc, COLOR_TEXT_MUTED);
        btn_cntKhoiphuc.setBounds(1260, 350, 132, 40); // Điều chỉnh vị trí
        pn_Capnhatthuoc_nhaptt.add(btn_cntKhoiphuc);

        btn_cntCapnhat  = new JButton("Cập Nhật");
        btn_cntCapnhat.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_cntCapnhat, COLOR_SUCCESS_GREEN);
        btn_cntCapnhat.setBounds(1412, 350, 132, 40); // Điều chỉnh vị trí
        pn_Capnhatthuoc_nhaptt.add(btn_cntCapnhat);

        // Panel chứa bộ lọc tìm kiếm thuốc cần cập nhật
        JPanel pn_Capnhatthuoc_tk = new JPanel();
        pn_Capnhatthuoc_tk.setBackground(COLOR_CARD_BACKGROUND);
        pn_Capnhatthuoc_tk.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Tìm Kiếm Thuốc Cần Cập Nhật", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        ));
        pn_Capnhatthuoc_tk.setBounds(10, 500, 1564, 80); // Điều chỉnh vị trí
        pn_Capnhatthuoc.add(pn_Capnhatthuoc_tk);
        pn_Capnhatthuoc_tk.setLayout(null);

        JLabel lbl_cnt_tkmt = new JLabel("Mã Thuốc :");
        lbl_cnt_tkmt.setBounds(40, 30, 91, 30); // Điều chỉnh
        lbl_cnt_tkmt.setFont(FONT_LABEL_BOLD);
        lbl_cnt_tkmt.setForeground(COLOR_TEXT_DARK);
        pn_Capnhatthuoc_tk.add(lbl_cnt_tkmt);

        text_cnt_tkmt = new JTextField();
        text_cnt_tkmt.setFont(FONT_TEXT_FIELD);
        text_cnt_tkmt.setColumns(10);
        text_cnt_tkmt.setBounds(141, 30, 198, 30); // Điều chỉnh
        pn_Capnhatthuoc_tk.add(text_cnt_tkmt);

        JLabel lbl_cnt_tktt = new JLabel("Tên Thuốc :");
        lbl_cnt_tktt.setFont(FONT_LABEL_BOLD);
        lbl_cnt_tktt.setForeground(COLOR_TEXT_DARK);
        lbl_cnt_tktt.setBounds(370, 30, 108, 30); // Điều chỉnh
        pn_Capnhatthuoc_tk.add(lbl_cnt_tktt);

        text_cnt_tktt = new JTextField();
        text_cnt_tktt.setFont(FONT_TEXT_FIELD);
        text_cnt_tktt.setColumns(10);
        text_cnt_tktt.setBounds(480, 30, 302, 30); // Điều chỉnh
        pn_Capnhatthuoc_tk.add(text_cnt_tktt);

        JLabel lbl_cnt_tktkt = new JLabel("Tên Kệ Thuốc :");
        lbl_cnt_tktkt.setFont(FONT_LABEL_BOLD);
        lbl_cnt_tktkt.setForeground(COLOR_TEXT_DARK);
        lbl_cnt_tktkt.setBounds(820, 30, 125, 30); // Điều chỉnh
        pn_Capnhatthuoc_tk.add(lbl_cnt_tktkt);

        cb_cnt_tktkt = new JComboBox<String>();
        cb_cnt_tktkt.setFont(FONT_TEXT_FIELD);
        cb_cnt_tktkt.setBounds(950, 30, 394, 30); // Điều chỉnh
        pn_Capnhatthuoc_tk.add(cb_cnt_tktkt);

        btn_cnt_tk_lammoi = new JButton("Làm Mới");
        btn_cnt_tk_lammoi.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_cnt_tk_lammoi, COLOR_TEXT_MUTED);
        btn_cnt_tk_lammoi.setBounds(1406, 25, 138, 40); // Điều chỉnh
        pn_Capnhatthuoc_tk.add(btn_cnt_tk_lammoi);

        // ScrollPane chứa bảng danh sách thuốc
        JScrollPane scP_cnt_table = new JScrollPane();
        scP_cnt_table.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scP_cnt_table.setBounds(10, 590, 1564, 338); // Điều chỉnh vị trí, kích thước
        pn_Capnhatthuoc.add(scP_cnt_table);

        // Tạo và style bảng table_Capnhatthuoc
        table_Capnhatthuoc = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground());
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
        };
        applyCommonTableStyling(table_Capnhatthuoc);
        table_Capnhatthuoc.setModel(new DefaultTableModel(
        	new Object[][] {},
        	new String[] {
        		"Mã Thuốc", "Tên Thuốc", "Số Lượng", "Giá Nhập", "Giá Bán", "Đơn Vị Tính", "Nhà Cung Cấp", "Hạn Sử Dụng", "Tên Kệ Thuốc", "Thành Phần"
        	}
        ));
        // Giữ lại các setPreferredWidth
        table_Capnhatthuoc.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_Capnhatthuoc.getColumnModel().getColumn(1).setPreferredWidth(130);
        table_Capnhatthuoc.getColumnModel().getColumn(2).setPreferredWidth(15);
        table_Capnhatthuoc.getColumnModel().getColumn(3).setPreferredWidth(15);
        table_Capnhatthuoc.getColumnModel().getColumn(4).setPreferredWidth(15);
        table_Capnhatthuoc.getColumnModel().getColumn(5).setPreferredWidth(15);
        table_Capnhatthuoc.getColumnModel().getColumn(6).setPreferredWidth(100);
        table_Capnhatthuoc.getColumnModel().getColumn(7).setPreferredWidth(25);
        table_Capnhatthuoc.getColumnModel().getColumn(8).setPreferredWidth(100);
        table_Capnhatthuoc.getColumnModel().getColumn(9).setPreferredWidth(300);
        scP_cnt_table.setViewportView(table_Capnhatthuoc);

        // ===== KẾT THÚC KHỐI CODE CẬP NHẬT THUỐC =====


        // ===== BẮT ĐẦU KHỐI CODE THÊM THUỐC (TỪNG LOẠI) ĐÃ SỬA =====
        JPanel pn_themthuoc1 = new JPanel(); // Panel chính cho tab này
        maincontent.add(pn_themthuoc1, "Themthuocthuong");
        pn_themthuoc1.setLayout(null);
        pn_themthuoc1.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        JLabel lbl_Themthuoc = new JLabel("THÊM THUỐC MỚI"); // Tiêu đề
        lbl_Themthuoc.setFont(FONT_TITLE_MAIN);
        lbl_Themthuoc.setForeground(COLOR_PRIMARY_BLUE);
        lbl_Themthuoc.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Themthuoc.setBounds(0, 11, 1584, 47);
        pn_themthuoc1.add(lbl_Themthuoc); // Add vào panel chính

        // Panel chứa form nhập thông tin thêm thuốc
        JPanel pn_Themthuoc = new JPanel(); // Giữ nguyên tên biến
        pn_Themthuoc.setBackground(COLOR_CARD_BACKGROUND);
        pn_Themthuoc.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Thông Tin Thuốc Mới", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        ));
        pn_Themthuoc.setLayout(null);
        pn_Themthuoc.setBounds(10, 68, 1564, 410); // Điều chỉnh
        pn_themthuoc1.add(pn_Themthuoc); // Add vào panel chính

        // Panel chứa ảnh (tương tự panel Cập Nhật)
        JPanel pn_chuaanh_1 = new JPanel();
        pn_chuaanh_1.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        pn_chuaanh_1.setBackground(COLOR_BACKGROUND_PRIMARY);
        pn_chuaanh_1.setBounds(34, 40, 330, 300);
        pn_Themthuoc.add(pn_chuaanh_1);
        pn_chuaanh_1.setLayout(new BorderLayout(0, 0));

        JLabel lb_Chuaanh_1 = new JLabel("Chưa có ảnh", SwingConstants.CENTER);
        lb_Chuaanh_1.setFont(FONT_DETAIL_ITALIC);
        lb_Chuaanh_1.setForeground(COLOR_TEXT_MUTED);
        pn_chuaanh_1.add(lb_Chuaanh_1, BorderLayout.CENTER);

        JButton btn_ChonAnh_1 = new JButton("Chọn Ảnh");
        btn_ChonAnh_1.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_ChonAnh_1, COLOR_TEXT_MUTED);
        btn_ChonAnh_1.setBounds(125, 350, 132, 40);
        pn_Themthuoc.add(btn_ChonAnh_1);

        // Các label và input fields (tương tự Cập Nhật, chỉ đổi tên biến)
        JLabel lbl_ttmt = new JLabel("Mã Thuốc :");
        lbl_ttmt.setFont(FONT_LABEL_BOLD);
        lbl_ttmt.setForeground(COLOR_TEXT_DARK);
        lbl_ttmt.setBounds(labelX, startY, 120, height);
        pn_Themthuoc.add(lbl_ttmt);

        text_ttmt = new JTextField();
        text_ttmt.setFont(FONT_TEXT_FIELD);
        text_ttmt.setColumns(10);
        text_ttmt.setBounds(inputX, startY, 400, height);
        text_ttmt.setEditable(false); // Mã thường tự sinh hoặc không cho nhập
        text_ttmt.setBackground(new Color(230, 230, 230));
        pn_Themthuoc.add(text_ttmt);

        JLabel lbl_tttt = new JLabel("Tên Thuốc :");
        lbl_tttt.setFont(FONT_LABEL_BOLD);
        lbl_tttt.setForeground(COLOR_TEXT_DARK);
        lbl_tttt.setBounds(labelX, startY + height + vGap, 120, height);
        pn_Themthuoc.add(lbl_tttt);

        text_tttt = new JTextField();
        text_tttt.setFont(FONT_TEXT_FIELD);
        text_tttt.setColumns(10);
        text_tttt.setBounds(inputX, startY + height + vGap, 400, height);
        pn_Themthuoc.add(text_tttt);

        JLabel lbl_ttsl = new JLabel("Số Lượng :");
        lbl_ttsl.setFont(FONT_LABEL_BOLD);
        lbl_ttsl.setForeground(COLOR_TEXT_DARK);
        lbl_ttsl.setBounds(labelX, startY + 2 * (height + vGap), 120, height);
        pn_Themthuoc.add(lbl_ttsl);

        text_ttsl = new JTextField();
        text_ttsl.setFont(FONT_TEXT_FIELD);
        text_ttsl.setColumns(10);
        text_ttsl.setBounds(inputX, startY + 2 * (height + vGap), 400, height);
        pn_Themthuoc.add(text_ttsl);

        JLabel lbl_ttgn = new JLabel("Giá Nhập :");
        lbl_ttgn.setFont(FONT_LABEL_BOLD);
        lbl_ttgn.setForeground(COLOR_TEXT_DARK);
        lbl_ttgn.setBounds(labelX, startY + 3 * (height + vGap), 120, height);
        pn_Themthuoc.add(lbl_ttgn);

        text_ttgn = new JTextField();
        text_ttgn.setFont(FONT_TEXT_FIELD);
        text_ttgn.setColumns(10);
        text_ttgn.setBounds(inputX, startY + 3 * (height + vGap), 400, height);
        pn_Themthuoc.add(text_ttgn);

        JLabel lbl_ttgb = new JLabel("Giá Bán :");
        lbl_ttgb.setFont(FONT_LABEL_BOLD);
        lbl_ttgb.setForeground(COLOR_TEXT_DARK);
        lbl_ttgb.setBounds(labelX, startY + 4 * (height + vGap), 120, height);
        pn_Themthuoc.add(lbl_ttgb);

        text_ttgb = new JTextField();
        text_ttgb.setFont(FONT_TEXT_FIELD);
        text_ttgb.setColumns(10);
        text_ttgb.setBounds(inputX, startY + 4 * (height + vGap), 400, height);
        pn_Themthuoc.add(text_ttgb);

        JLabel lbl_ttdvt = new JLabel("Đơn Vị Tính :");
        lbl_ttdvt.setFont(FONT_LABEL_BOLD);
        lbl_ttdvt.setForeground(COLOR_TEXT_DARK);
        lbl_ttdvt.setBounds(labelX, startY + 5 * (height + vGap), 120, height);
        pn_Themthuoc.add(lbl_ttdvt);

        JComboBox<String> cb_ttdvt = new JComboBox<String>();
        cb_ttdvt.setFont(FONT_TEXT_FIELD);
        cb_ttdvt.setBounds(inputX, startY + 5 * (height + vGap), 400, height);
        pn_Themthuoc.add(cb_ttdvt);

        JLabel lbl_ttncc = new JLabel("Nhà Cung Cấp :");
        lbl_ttncc.setFont(FONT_LABEL_BOLD);
        lbl_ttncc.setForeground(COLOR_TEXT_DARK);
        lbl_ttncc.setBounds(labelX2, startY, 130, height);
        pn_Themthuoc.add(lbl_ttncc);

        JComboBox<String> cb_ttncc = new JComboBox<String>();
        cb_ttncc.setFont(FONT_TEXT_FIELD);
        cb_ttncc.setBounds(inputX2, startY, 400, height);
        pn_Themthuoc.add(cb_ttncc);

        JLabel lbl_tthsd = new JLabel("Hạn Sử Dụng :");
        lbl_tthsd.setFont(FONT_LABEL_BOLD);
        lbl_tthsd.setForeground(COLOR_TEXT_DARK);
        lbl_tthsd.setBounds(labelX2, startY + height + vGap, 130, height);
        pn_Themthuoc.add(lbl_tthsd);

        // Dùng JDateChooser cho HSD
        JDateChooser date_tthsd = new JDateChooser(); // Tạo biến mới
        date_tthsd.setFont(FONT_TEXT_FIELD);
        date_tthsd.setDateFormatString("dd/MM/yyyy");
        date_tthsd.setBounds(inputX2, startY + height + vGap, 400, height);
        pn_Themthuoc.add(date_tthsd);
        // Đại Ca cần thay biến text_tthsd thành date_tthsd trong code logic

        JLabel lbl_tttkt = new JLabel("Tên Kệ Thuốc :");
        lbl_tttkt.setFont(FONT_LABEL_BOLD);
        lbl_tttkt.setForeground(COLOR_TEXT_DARK);
        lbl_tttkt.setBounds(labelX2, startY + 2 * (height + vGap), 130, height);
        pn_Themthuoc.add(lbl_tttkt);

        JComboBox<String> cb_tttkt = new JComboBox<String>();
        cb_tttkt.setFont(FONT_TEXT_FIELD);
        cb_tttkt.setBounds(inputX2, startY + 2 * (height + vGap), 400, height);
        pn_Themthuoc.add(cb_tttkt);

        JLabel lbl_tttp = new JLabel("Thành Phần :");
        lbl_tttp.setFont(FONT_LABEL_BOLD);
        lbl_tttp.setForeground(COLOR_TEXT_DARK);
        lbl_tttp.setBounds(labelX2, startY + 3 * (height + vGap), 130, height);
        pn_Themthuoc.add(lbl_tttp);

        JScrollPane scrollPane_tttp = new JScrollPane(); // ScrollPane cho thành phần
        scrollPane_tttp.setBounds(inputX2, startY + 3 * (height + vGap), 400, 110);
        pn_Themthuoc.add(scrollPane_tttp);

        JTextArea textArea_tttp = new JTextArea();
        textArea_tttp.setWrapStyleWord(true);
        textArea_tttp.setLineWrap(true);
        textArea_tttp.setFont(FONT_TEXT_FIELD);
        scrollPane_tttp.setViewportView(textArea_tttp); // Add vào ScrollPane

        JButton btn_ttLammoi = new JButton("Làm Mới");
        btn_ttLammoi.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_ttLammoi, COLOR_TEXT_MUTED);
        btn_ttLammoi.setBounds(1124, 350, 132, 40); // Điều chỉnh
        pn_Themthuoc.add(btn_ttLammoi);

        JButton btn_ttThem = new JButton("Thêm");
        btn_ttThem.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_ttThem, COLOR_PRIMARY_BLUE);
        btn_ttThem.setBounds(1276, 350, 138, 40); // Điều chỉnh
        pn_Themthuoc.add(btn_ttThem);

        JButton btn_ttLuu = new JButton("Lưu"); // Đổi tên biến
        btn_ttLuu.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_ttLuu, COLOR_SUCCESS_GREEN);
        btn_ttLuu.setBounds(1424, 350, 130, 40); // Điều chỉnh
        pn_Themthuoc.add(btn_ttLuu);

        // Bảng hiển thị danh sách thuốc vừa thêm (tạm thời)
        JScrollPane scP_cnt_table_1 = new JScrollPane();
        scP_cnt_table_1.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scP_cnt_table_1.setBounds(10, 489, 1564, 439); // Điều chỉnh
        pn_themthuoc1.add(scP_cnt_table_1); // Add vào panel chính

        // Style bảng table_1
        table_1 = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground());
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
        };
        applyCommonTableStyling(table_1);
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {},
        	new String[] {
        		"Mã Thuốc", "Tên Thuốc", "Số Lượng", "Giá Nhập", "Giá Bán", "Đơn Vị Tính", "Nhà Cung Cấp", "Hạn Sử Dụng", "Tên Kệ Thuốc", "Thành Phần"
        	}
        ));
        // Giữ lại setPreferredWidth
        table_1.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_1.getColumnModel().getColumn(1).setPreferredWidth(130);
        table_1.getColumnModel().getColumn(2).setPreferredWidth(15);
        table_1.getColumnModel().getColumn(3).setPreferredWidth(15);
        table_1.getColumnModel().getColumn(4).setPreferredWidth(15);
        table_1.getColumnModel().getColumn(5).setPreferredWidth(15);
        table_1.getColumnModel().getColumn(6).setPreferredWidth(100);
        table_1.getColumnModel().getColumn(7).setPreferredWidth(25);
        table_1.getColumnModel().getColumn(8).setPreferredWidth(100);
        table_1.getColumnModel().getColumn(9).setPreferredWidth(300);
        scP_cnt_table_1.setViewportView(table_1);

        // ===== KẾT THÚC KHỐI CODE THÊM THUỐC (TỪNG LOẠI) =====


        // ===== BẮT ĐẦU KHỐI CODE THÊM THUỐC (TỪ FILE) ĐÃ SỬA =====
        JPanel pn_themthuocfile = new JPanel();
        maincontent.add(pn_themthuocfile, "Themthuocfile");
        pn_themthuocfile.setLayout(null);
        pn_themthuocfile.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        // Panel chứa tiêu đề và các nút chức năng
        JPanel pn_ttf = new JPanel();
        pn_ttf.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
        pn_ttf.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền
        pn_ttf.setBounds(10, 11, 1564, 123); // Vị trí
        pn_themthuocfile.add(pn_ttf);
        pn_ttf.setLayout(null);

        JLabel lbl_ttf_tieude = new JLabel("THÊM DANH SÁCH THUỐC TỪ FILE EXCEL"); // Tiêu đề
        lbl_ttf_tieude.setFont(FONT_TITLE_MAIN);
        lbl_ttf_tieude.setForeground(COLOR_PRIMARY_BLUE);
        lbl_ttf_tieude.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ttf_tieude.setBounds(0, 11, 1564, 47); // Căn giữa
        pn_ttf.add(lbl_ttf_tieude);

        JButton btn_ttf_chonfile = new JButton("Chọn File");
        btn_ttf_chonfile.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_ttf_chonfile, COLOR_PRIMARY_BLUE); // Màu xanh
        btn_ttf_chonfile.setBounds(40, 74, 139, 38); // Vị trí
        pn_ttf.add(btn_ttf_chonfile);

        JLabel lbl_ttf_tongsothuoc = new JLabel("Tổng số thuốc :");
        lbl_ttf_tongsothuoc.setFont(FONT_LABEL_BOLD);
        lbl_ttf_tongsothuoc.setForeground(COLOR_TEXT_DARK);
        lbl_ttf_tongsothuoc.setBounds(200, 74, 131, 38); // Điều chỉnh
        pn_ttf.add(lbl_ttf_tongsothuoc);

        JLabel lbl_ttfile_hienthitongsothuoc = new JLabel("0");
        lbl_ttfile_hienthitongsothuoc.setFont(FONT_LABEL_BOLD);
        lbl_ttfile_hienthitongsothuoc.setForeground(COLOR_DANGER_RED); // Màu đỏ cho số lượng
        lbl_ttfile_hienthitongsothuoc.setBounds(340, 74, 100, 38); // Điều chỉnh
        pn_ttf.add(lbl_ttfile_hienthitongsothuoc);

        JButton btn_ttf_lammoi = new JButton("Làm Mới");
        btn_ttf_lammoi.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_ttf_lammoi, COLOR_TEXT_MUTED);
        btn_ttf_lammoi.setBounds(1125, 74, 139, 38);
        pn_ttf.add(btn_ttf_lammoi);

        JButton btn_ttf_them = new JButton("Thêm");
        btn_ttf_them.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_ttf_them, COLOR_PRIMARY_BLUE);
        btn_ttf_them.setBounds(1274, 74, 139, 38);
        pn_ttf.add(btn_ttf_them);

        JButton btn_ttf_luu = new JButton("Lưu");
        btn_ttf_luu.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_ttf_luu, COLOR_SUCCESS_GREEN);
        btn_ttf_luu.setBounds(1423, 74, 131, 38); // Điều chỉnh
        pn_ttf.add(btn_ttf_luu);

        // ScrollPane chứa bảng hiển thị thuốc từ file
        JScrollPane scP_ttf_table = new JScrollPane();
        scP_ttf_table.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scP_ttf_table.setBounds(10, 145, 1564, 783); // Điều chỉnh
        pn_themthuocfile.add(scP_ttf_table);

        // Style bảng table_2
        table_2 = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground());
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
        };
        applyCommonTableStyling(table_2);
        table_2.setModel(new DefaultTableModel(
            	new Object[][] {},
            	new String[] {
            		"Mã Thuốc", "Tên Thuốc", "Số Lượng", "Giá Nhập", "Giá Bán", "Đơn Vị Tính", "Nhà Cung Cấp", "Hạn Sử Dụng", "Tên Kệ Thuốc", "Thành Phần"
            	}
            ));
        // Giữ lại setPreferredWidth
        table_2.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_2.getColumnModel().getColumn(1).setPreferredWidth(130);
        table_2.getColumnModel().getColumn(2).setPreferredWidth(15);
        table_2.getColumnModel().getColumn(3).setPreferredWidth(15);
        table_2.getColumnModel().getColumn(4).setPreferredWidth(15);
        table_2.getColumnModel().getColumn(5).setPreferredWidth(15);
        table_2.getColumnModel().getColumn(6).setPreferredWidth(100);
        table_2.getColumnModel().getColumn(7).setPreferredWidth(25);
        table_2.getColumnModel().getColumn(8).setPreferredWidth(100);
        table_2.getColumnModel().getColumn(9).setPreferredWidth(300);
        scP_ttf_table.setViewportView(table_2);

        // ===== KẾT THÚC KHỐI CODE THÊM THUỐC (TỪ FILE) =====


        // ===== BẮT ĐẦU KHỐI CODE THUỐC SẮP HẾT HẠN ĐÃ SỬA =====
        JPanel pn_thuocsaphethan = new JPanel();
        maincontent.add(pn_thuocsaphethan, "Thuocsaphethan");
        pn_thuocsaphethan.setLayout(null);
        pn_thuocsaphethan.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        JLabel lbl_tshh_tieude = new JLabel("DANH SÁCH THUỐC SẮP HẾT HẠN"); // Tiêu đề
        lbl_tshh_tieude.setFont(FONT_TITLE_MAIN);
        lbl_tshh_tieude.setForeground(COLOR_DANGER_RED); // Màu đỏ cảnh báo
        lbl_tshh_tieude.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_tshh_tieude.setBounds(0, 11, 1584, 40); // Căn giữa
        pn_thuocsaphethan.add(lbl_tshh_tieude);

        JScrollPane scP_tshh_table = new JScrollPane();
        scP_tshh_table.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scP_tshh_table.setBounds(10, 66, 1564, 800); // Điều chỉnh
        pn_thuocsaphethan.add(scP_tshh_table);

        // Style bảng table_tshh
        table_tshh = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground());
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
        };
        applyCommonTableStyling(table_tshh);
        table_tshh.setModel(new DefaultTableModel(
            	new Object[][] {},
            	new String[] {
            		"Mã Thuốc", "Tên Thuốc", "Số Lượng", "Giá Nhập", "Giá Bán", "Đơn Vị Tính", "Nhà Cung Cấp", "Hạn Sử Dụng", "Tên Kệ Thuốc", "Thành Phần"
            	}
            ));
        // Giữ lại setPreferredWidth
        table_tshh.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_tshh.getColumnModel().getColumn(1).setPreferredWidth(130);
        table_tshh.getColumnModel().getColumn(2).setPreferredWidth(15);
        table_tshh.getColumnModel().getColumn(3).setPreferredWidth(15);
        table_tshh.getColumnModel().getColumn(4).setPreferredWidth(15);
        table_tshh.getColumnModel().getColumn(5).setPreferredWidth(15);
        table_tshh.getColumnModel().getColumn(6).setPreferredWidth(100);
        table_tshh.getColumnModel().getColumn(7).setPreferredWidth(25);
        table_tshh.getColumnModel().getColumn(8).setPreferredWidth(100);
        table_tshh.getColumnModel().getColumn(9).setPreferredWidth(300);
        scP_tshh_table.setViewportView(table_tshh);

        JButton btn_tshh_xuatfile = new JButton("Xuất File");
        btn_tshh_xuatfile.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_tshh_xuatfile, COLOR_SUCCESS_GREEN);
        btn_tshh_xuatfile.setBounds(1412, 877, 152, 40); // Điều chỉnh
        pn_thuocsaphethan.add(btn_tshh_xuatfile);

        // ===== KẾT THÚC KHỐI CODE THUỐC SẮP HẾT HẠN =====


        // ===== BẮT ĐẦU KHỐI CODE THUỐC BÁN CHẠY ĐÃ SỬA =====
        JPanel pn_thuocbanchay = new JPanel();
        maincontent.add(pn_thuocbanchay, "Thuocbanchay");
        pn_thuocbanchay.setLayout(null);
        pn_thuocbanchay.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        JLabel lbl_tbc_tieude = new JLabel("DANH SÁCH THUỐC BÁN CHẠY"); // Tiêu đề
        lbl_tbc_tieude.setFont(FONT_TITLE_MAIN);
        lbl_tbc_tieude.setForeground(COLOR_SUCCESS_GREEN); // Màu xanh lá
        lbl_tbc_tieude.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_tbc_tieude.setBounds(0, 11, 1584, 57); // Căn giữa
        pn_thuocbanchay.add(lbl_tbc_tieude);

        JScrollPane scP_tbc_table = new JScrollPane();
        scP_tbc_table.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scP_tbc_table.setBounds(10, 75, 1564, 791); // Điều chỉnh
        pn_thuocbanchay.add(scP_tbc_table);

        // Style bảng table_5 (nên đổi tên biến này cho rõ nghĩa, vd: table_thuocBanChay)
        table_5 = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground());
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
        };
        applyCommonTableStyling(table_5);
        table_5.setModel(new DefaultTableModel(
            	new Object[][] {},
            	new String[] { // Nên thêm cột Số Lượng Bán
            		"Mã Thuốc", "Tên Thuốc", "Số Lượng Bán", "Giá Bán", "Đơn Vị Tính", "Nhà Cung Cấp", "Hạn Sử Dụng", "Tên Kệ Thuốc"
            	}
            ));
        // Điều chỉnh lại setPreferredWidth cho phù hợp cột mới
        // table_5.getColumnModel().getColumn(0).setPreferredWidth(30); ...
        scP_tbc_table.setViewportView(table_5);

        JButton btn_tbc_xuatfile = new JButton("Xuất File");
        btn_tbc_xuatfile.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_tbc_xuatfile, COLOR_SUCCESS_GREEN);
        btn_tbc_xuatfile.setBounds(1412, 877, 152, 40); // Điều chỉnh
        pn_thuocbanchay.add(btn_tbc_xuatfile);

        // ===== KẾT THÚC KHỐI CODE THUỐC BÁN CHẠY =====


        // ===== BẮT ĐẦU KHỐI CODE THUỐC SẮP HẾT HÀNG ĐÃ SỬA =====
        JPanel pn_thuocsaphethang = new JPanel();
        maincontent.add(pn_thuocsaphethang, "Thuocsaphethang");
        pn_thuocsaphethang.setLayout(null);
        pn_thuocsaphethang.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        JLabel lbl_tshhan_tieude = new JLabel("DANH SÁCH THUỐC SẮP HẾT HÀNG"); // Tiêu đề
        lbl_tshhan_tieude.setFont(FONT_TITLE_MAIN);
        lbl_tshhan_tieude.setForeground(COLOR_DANGER_RED); // Màu đỏ cảnh báo
        lbl_tshhan_tieude.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_tshhan_tieude.setBounds(0, 11, 1584, 57); // Căn giữa
        pn_thuocsaphethang.add(lbl_tshhan_tieude);

        JScrollPane scP_tshhan_table = new JScrollPane();
        scP_tshhan_table.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scP_tshhan_table.setBounds(10, 75, 1564, 791); // Điều chỉnh
        pn_thuocsaphethang.add(scP_tshhan_table);

        // Style bảng table_tshhan
        table_tshhan = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground());
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
        };
        applyCommonTableStyling(table_tshhan);
        table_tshhan.setModel(new DefaultTableModel(
            	new Object[][] {},
            	new String[] { // Chỉ cần hiện các cột cần thiết
            		"Mã Thuốc", "Tên Thuốc", "Số Lượng Tồn", "Đơn Vị Tính", "Nhà Cung Cấp", "Tên Kệ Thuốc"
            	}
            ));
        // Điều chỉnh setPreferredWidth nếu cần
        // table_tshhan.getColumnModel().getColumn(0).setPreferredWidth(30); ...
        scP_tshhan_table.setViewportView(table_tshhan);

        JButton btn_tshhan_xuatfile = new JButton("Xuất File");
        btn_tshhan_xuatfile.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_tshhan_xuatfile, COLOR_SUCCESS_GREEN);
        btn_tshhan_xuatfile.setBounds(1412, 877, 152, 40); // Điều chỉnh
        pn_thuocsaphethang.add(btn_tshhan_xuatfile);

        // ===== KẾT THÚC KHỐI CODE THUỐC SẮP HẾT HÀNG =====
        
     // ===== BẮT ĐẦU KHỐI CODE THỐNG KÊ HÓA ĐƠN ĐÃ SỬA =====
        JPanel pn_ThongkeHD = new JPanel();
        maincontent.add(pn_ThongkeHD, "ThongkeHD");
        pn_ThongkeHD.setLayout(null);
        pn_ThongkeHD.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        JPanel pn_tkhd_tieude = new JPanel();
        pn_tkhd_tieude.setOpaque(false); // Trong suốt để lấy nền cha
        pn_tkhd_tieude.setBounds(0, 0, 1719, 81);
        pn_ThongkeHD.add(pn_tkhd_tieude);
        pn_tkhd_tieude.setLayout(null);
        
        JLabel lbl_tkhd_tieude = new JLabel("THỐNG KÊ DOANH THU HÓA ĐƠN"); // Chữ rõ hơn
        lbl_tkhd_tieude.setFont(FONT_TITLE_MAIN); // Font tiêu đề
        lbl_tkhd_tieude.setForeground(COLOR_PRIMARY_BLUE); // Màu tiêu đề
        lbl_tkhd_tieude.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lbl_tkhd_tieude.setBounds(0, 11, 1584, 59); // Căn giữa
        pn_tkhd_tieude.add(lbl_tkhd_tieude);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(FONT_LABEL_BOLD); // Font cho tab
        tabbedPane.setBounds(10, 80, 1709, 921); // Căn chỉnh
        pn_ThongkeHD.add(tabbedPane);
        
        // ========== TAB THỐNG KÊ THEO NGÀY ==========
        JPanel pn_tketheongay = new JPanel();
        pn_tketheongay.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền
        tabbedPane.addTab("Theo Ngày", null, pn_tketheongay, null);
        pn_tketheongay.setLayout(null);
        
        JLabel lbl_Ngaythongke = new JLabel("Chọn Ngày TK :");
        lbl_Ngaythongke.setFont(FONT_LABEL_BOLD);
        lbl_Ngaythongke.setForeground(COLOR_TEXT_DARK);
        lbl_Ngaythongke.setBounds(30, 11, 143, 30);
        pn_tketheongay.add(lbl_Ngaythongke);
        
        // Dùng JDateChooser
        date_tktn_ngay = new JDateChooser();
        date_tktn_ngay.setFont(FONT_TEXT_FIELD);
        date_tktn_ngay.setDateFormatString("dd/MM/yyyy");
        date_tktn_ngay.setBounds(183, 11, 223, 30);
        pn_tketheongay.add(date_tktn_ngay);
        
        JLabel lbl_tktn_tongsohd = new JLabel("Tổng Số Hóa Đơn :");
        lbl_tktn_tongsohd.setFont(FONT_LABEL_BOLD);
        lbl_tktn_tongsohd.setForeground(COLOR_TEXT_DARK);
        lbl_tktn_tongsohd.setBounds(572, 11, 165, 30);
        pn_tketheongay.add(lbl_tktn_tongsohd);
        
        JLabel lbl_tktn_hientongsohd = new JLabel("0"); // Giá trị mặc định
        lbl_tktn_hientongsohd.setFont(FONT_LABEL_BOLD);
        lbl_tktn_hientongsohd.setForeground(COLOR_PRIMARY_BLUE);
        lbl_tktn_hientongsohd.setBounds(747, 11, 143, 30);
        pn_tketheongay.add(lbl_tktn_hientongsohd);

        JLabel lbl_tktn_tongtiencachoadon = new JLabel("Tổng Doanh Thu :");
        lbl_tktn_tongtiencachoadon.setFont(FONT_LABEL_BOLD);
        lbl_tktn_tongtiencachoadon.setForeground(COLOR_TEXT_DARK);
        lbl_tktn_tongtiencachoadon.setBounds(962, 11, 160, 30);
        pn_tketheongay.add(lbl_tktn_tongtiencachoadon);
        
        JLabel lbl_tktn_hientongsotien = new JLabel("0 VND"); // Giá trị mặc định
        lbl_tktn_hientongsotien.setFont(FONT_SUMMARY_TOTAL);
        lbl_tktn_hientongsotien.setForeground(COLOR_SUCCESS_GREEN);
        lbl_tktn_hientongsotien.setBounds(1130, 11, 250, 30);
        pn_tketheongay.add(lbl_tktn_hientongsotien);
        
        // -- Panel bên trái (Lọc nhân viên) --
        JPanel pn_tktn_tktnv = new JPanel();
        pn_tktn_tktnv.setOpaque(false); // Trong suốt
        pn_tktn_tktnv.setBounds(10, 52, 480, 834); // Điều chỉnh
        pn_tketheongay.add(pn_tktn_tktnv);
        pn_tktn_tktnv.setLayout(null);
        
        JPanel pn_tktn_tktnv_boloc = new JPanel();
        pn_tktn_tktnv_boloc.setBackground(COLOR_CARD_BACKGROUND);
        pn_tktn_tktnv_boloc.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Lọc Theo Nhân Viên", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        ));
        pn_tktn_tktnv_boloc.setBounds(10, 11, 459, 230);
        pn_tktn_tktnv.add(pn_tktn_tktnv_boloc);
        pn_tktn_tktnv_boloc.setLayout(null);
        
        JLabel lbl_tktn_tktnv_boloc = new JLabel("Thống Kê Theo :");
        lbl_tktn_tktnv_boloc.setBounds(21, 25, 142, 30);
        lbl_tktn_tktnv_boloc.setFont(FONT_LABEL_BOLD);
        lbl_tktn_tktnv_boloc.setForeground(COLOR_TEXT_DARK);
        pn_tktn_tktnv_boloc.add(lbl_tktn_tktnv_boloc);
        
        JComboBox<String> cb_tktn_tktnv_boloc = new JComboBox<String>();
        cb_tktn_tktnv_boloc.setFont(FONT_TEXT_FIELD);
        cb_tktn_tktnv_boloc.setBounds(173, 25, 258, 30);
        pn_tktn_tktnv_boloc.add(cb_tktn_tktnv_boloc);
        
        JPanel pn_tktn_tktnv_boloc_pntk = new JPanel();
        pn_tktn_tktnv_boloc_pntk.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Tìm Kiếm Nhân Viên", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_TEXT_MUTED // Màu nhạt hơn
        ));
        pn_tktn_tktnv_boloc_pntk.setBackground(COLOR_CARD_BACKGROUND);
        pn_tktn_tktnv_boloc_pntk.setBounds(10, 66, 437, 153);
        pn_tktn_tktnv_boloc.add(pn_tktn_tktnv_boloc_pntk);
        pn_tktn_tktnv_boloc_pntk.setLayout(null);
        
        JLabel lbl_tktn_tktnv_boloc_manv = new JLabel("Mã Nhân Viên :");
        lbl_tktn_tktnv_boloc_manv.setFont(FONT_LABEL_BOLD);
        lbl_tktn_tktnv_boloc_manv.setForeground(COLOR_TEXT_DARK);
        lbl_tktn_tktnv_boloc_manv.setBounds(10, 33, 142, 30);
        pn_tktn_tktnv_boloc_pntk.add(lbl_tktn_tktnv_boloc_manv);
        
        JLabel lbl_tktn_tktnv_boloc_tennv = new JLabel("Tên Nhân Viên :");
        lbl_tktn_tktnv_boloc_tennv.setFont(FONT_LABEL_BOLD);
        lbl_tktn_tktnv_boloc_tennv.setForeground(COLOR_TEXT_DARK);
        lbl_tktn_tktnv_boloc_tennv.setBounds(10, 85, 142, 30);
        pn_tktn_tktnv_boloc_pntk.add(lbl_tktn_tktnv_boloc_tennv);
        
        text_tktn_tktnv_boloc_manv = new JTextField();
        text_tktn_tktnv_boloc_manv.setFont(FONT_TEXT_FIELD);
        text_tktn_tktnv_boloc_manv.setColumns(10);
        text_tktn_tktnv_boloc_manv.setBounds(162, 33, 261, 30);
        pn_tktn_tktnv_boloc_pntk.add(text_tktn_tktnv_boloc_manv);
        
        text_tktn_tktnv_boloc_tennv = new JTextField();
        text_tktn_tktnv_boloc_tennv.setFont(FONT_TEXT_FIELD);
        text_tktn_tktnv_boloc_tennv.setColumns(10);
        text_tktn_tktnv_boloc_tennv.setBounds(162, 85, 261, 30);
        pn_tktn_tktnv_boloc_pntk.add(text_tktn_tktnv_boloc_tennv);
        
        JScrollPane scrollPane_5 = new JScrollPane();
        scrollPane_5.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scrollPane_5.setBounds(10, 252, 459, 571);
        pn_tktn_tktnv.add(scrollPane_5);
        
        // Áp dụng đúng cách tạo bảng table_8
        table_8 = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    
                }
                return c;
            }
        };
        applyCommonTableStyling(table_8); // Áp dụng style chung
        table_8.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Mã Nhân Viên", "Tên Nhân Viên"}
        ));
        scrollPane_5.setViewportView(table_8);
        
        // -- Bảng bên phải (Danh sách hóa đơn) --
        JScrollPane scP_tktn_tktnv_table = new JScrollPane();
        scP_tktn_tktnv_table.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scP_tktn_tktnv_table.setBounds(500, 52, 1194, 770); // Căn chỉnh
        pn_tketheongay.add(scP_tktn_tktnv_table);
        
        // Áp dụng đúng cách tạo bảng table_tktn
        table_tktn = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    
                }
                return c;
            }
        };
        applyCommonTableStyling(table_tktn); // Áp dụng style chung
        table_tktn.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Mã Hóa Đơn", "SĐT Khách Hàng", "Tên Khách Hàng", "Mã Nhân Viên", "Tên Nhân Viên", "Tổng Tiền"}
        ));
        scP_tktn_tktnv_table.setViewportView(table_tktn);
        
        JButton btn_tktn_Xuatfile = new JButton("Xuất File");
        btn_tktn_Xuatfile.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_tktn_Xuatfile, COLOR_SUCCESS_GREEN);
        btn_tktn_Xuatfile.setBounds(1551, 833, 143, 42);
        pn_tketheongay.add(btn_tktn_Xuatfile);
        
        // ========== TAB THỐNG KÊ THEO THÁNG ==========
        JPanel pn_tketheothang = new JPanel();
        pn_tketheothang.setBackground(COLOR_BACKGROUND_PRIMARY);
        tabbedPane.addTab("Theo Tháng", null, pn_tketheothang, null);
        pn_tketheothang.setLayout(null);
        
        JLabel lbl_thang = new JLabel("Tháng :");
        lbl_thang.setFont(FONT_LABEL_BOLD);
        lbl_thang.setForeground(COLOR_TEXT_DARK);
        lbl_thang.setBounds(20, 11, 69, 30);
        pn_tketheothang.add(lbl_thang);

        // Dùng JMonthChooser
        month_tktt = new JMonthChooser();
        month_tktt.setFont(FONT_TEXT_FIELD);
        month_tktt.setBounds(99, 11, 135, 30);
        pn_tketheothang.add(month_tktt);

        JLabel lbl_nam = new JLabel("Năm :");
        lbl_nam.setFont(FONT_LABEL_BOLD);
        lbl_nam.setForeground(COLOR_TEXT_DARK);
        lbl_nam.setBounds(244, 11, 55, 30);
        pn_tketheothang.add(lbl_nam);

        // Dùng JYearChooser
        year_tktt = new JYearChooser();
        year_tktt.setFont(FONT_TEXT_FIELD);
        year_tktt.setBounds(309, 11, 100, 30); // Giảm chiều rộng
        pn_tketheothang.add(year_tktt);
        
        JLabel lbl_tktt_tongsohd = new JLabel("Tổng Số Hóa Đơn :");
        lbl_tktt_tongsohd.setFont(FONT_LABEL_BOLD);
        lbl_tktt_tongsohd.setForeground(COLOR_TEXT_DARK);
        lbl_tktt_tongsohd.setBounds(504, 11, 165, 30);
        pn_tketheothang.add(lbl_tktt_tongsohd);
        
        JLabel lbl_tktt_hiensohd = new JLabel("0");
        lbl_tktt_hiensohd.setFont(FONT_LABEL_BOLD);
        lbl_tktt_hiensohd.setForeground(COLOR_PRIMARY_BLUE);
        lbl_tktt_hiensohd.setBounds(679, 11, 143, 30);
        pn_tketheothang.add(lbl_tktt_hiensohd);

        JLabel lbl_tktt_Tongtiencachd = new JLabel("Tổng Doanh Thu :");
        lbl_tktt_Tongtiencachd.setFont(FONT_LABEL_BOLD);
        lbl_tktt_Tongtiencachd.setForeground(COLOR_TEXT_DARK);
        lbl_tktt_Tongtiencachd.setBounds(900, 11, 160, 30);
        pn_tketheothang.add(lbl_tktt_Tongtiencachd);
        
        JLabel lbl_tktt_hientongtienhd = new JLabel("0 VND");
        lbl_tktt_hientongtienhd.setFont(FONT_SUMMARY_TOTAL);
        lbl_tktt_hientongtienhd.setForeground(COLOR_SUCCESS_GREEN);
        lbl_tktt_hientongtienhd.setBounds(1070, 11, 250, 30);
        pn_tketheothang.add(lbl_tktt_hientongtienhd);
        
        JButton btn_tktt_xuatfile = new JButton("Xuất File");
        btn_tktt_xuatfile.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_tktt_xuatfile, COLOR_SUCCESS_GREEN);
        btn_tktt_xuatfile.setBounds(1551, 833, 143, 42);
        pn_tketheothang.add(btn_tktt_xuatfile);
        
        // -- Panel bên trái (Lọc nhân viên) --
        JPanel pn_tktt_tk = new JPanel(); // Giữ nguyên tên biến
        pn_tktt_tk.setOpaque(false);
        pn_tktt_tk.setLayout(null);
        pn_tktt_tk.setBounds(10, 52, 480, 834);
        pn_tketheothang.add(pn_tktt_tk);
        
        // (Code bên trong pn_tktt_tk giống hệt pn_tktn_tktnv, chỉ thay tên biến)
        JPanel pn_tktt_tk_boloc = new JPanel();
        pn_tktt_tk_boloc.setBackground(COLOR_CARD_BACKGROUND);
        pn_tktt_tk_boloc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true), "Lọc Theo Nhân Viên", TitledBorder.LEADING, TitledBorder.TOP, FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE));
        pn_tktt_tk_boloc.setBounds(10, 11, 459, 230);
        pn_tktt_tk.add(pn_tktt_tk_boloc);
        pn_tktt_tk_boloc.setLayout(null);
        
        JLabel lbl_tktt_tk_boloc_thongktheonv = new JLabel("Thống Kê Theo :");
        lbl_tktt_tk_boloc_thongktheonv.setFont(FONT_LABEL_BOLD);
        lbl_tktt_tk_boloc_thongktheonv.setForeground(COLOR_TEXT_DARK);
        lbl_tktt_tk_boloc_thongktheonv.setBounds(21, 25, 142, 30);
        pn_tktt_tk_boloc.add(lbl_tktt_tk_boloc_thongktheonv);
        
        JComboBox<String> cb_tktt_tknv = new JComboBox<String>();
        cb_tktt_tknv.setFont(FONT_TEXT_FIELD);
        cb_tktt_tknv.setBounds(173, 25, 258, 30);
        pn_tktt_tk_boloc.add(cb_tktt_tknv);
        
        JPanel pn_tktt_tk_boloc_tknv = new JPanel();
        pn_tktt_tk_boloc_tknv.setLayout(null);
        pn_tktt_tk_boloc_tknv.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true), "Tìm Kiếm Nhân Viên", TitledBorder.LEADING, TitledBorder.TOP, FONT_LABEL_BOLD, COLOR_TEXT_MUTED));
        pn_tktt_tk_boloc_tknv.setBackground(Color.WHITE);
        pn_tktt_tk_boloc_tknv.setBounds(10, 66, 437, 153);
        pn_tktt_tk_boloc.add(pn_tktt_tk_boloc_tknv);
        
        JLabel lbl_tktt_tk_boloc_manv = new JLabel("Mã Nhân Viên :");
        lbl_tktt_tk_boloc_manv.setFont(FONT_LABEL_BOLD);
        lbl_tktt_tk_boloc_manv.setForeground(COLOR_TEXT_DARK);
        lbl_tktt_tk_boloc_manv.setBounds(10, 33, 142, 30);
        pn_tktt_tk_boloc_tknv.add(lbl_tktt_tk_boloc_manv);
        
        JLabel lbl__tktt_tk_boloc_tennv = new JLabel("Tên Nhân Viên :");
        lbl__tktt_tk_boloc_tennv.setFont(FONT_LABEL_BOLD);
        lbl__tktt_tk_boloc_tennv.setForeground(COLOR_TEXT_DARK);
        lbl__tktt_tk_boloc_tennv.setBounds(10, 85, 142, 30);
        pn_tktt_tk_boloc_tknv.add(lbl__tktt_tk_boloc_tennv);
        
        text_tktt_tk_boloc_manv = new JTextField();
        text_tktt_tk_boloc_manv.setFont(FONT_TEXT_FIELD);
        text_tktt_tk_boloc_manv.setColumns(10);
        text_tktt_tk_boloc_manv.setBounds(162, 33, 261, 30);
        pn_tktt_tk_boloc_tknv.add(text_tktt_tk_boloc_manv);
        
        text_tktt_tk_boloc_tennv = new JTextField();
        text_tktt_tk_boloc_tennv.setFont(FONT_TEXT_FIELD);
        text_tktt_tk_boloc_tennv.setColumns(10);
        text_tktt_tk_boloc_tennv.setBounds(162, 85, 261, 30);
        pn_tktt_tk_boloc_tknv.add(text_tktt_tk_boloc_tennv);
        
        JScrollPane scP_tktt = new JScrollPane();
        scP_tktt.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scP_tktt.setBounds(10, 252, 459, 571);
        pn_tktt_tk.add(scP_tktt);
        
        // Áp dụng đúng cách tạo bảng table_ttkt
        table_ttkt = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
        };
        applyCommonTableStyling(table_ttkt); // Áp dụng style chung
        table_ttkt.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Mã Nhân Viên", "Tên Nhân Viên"}
        ));
        scP_tktt.setViewportView(table_ttkt);

        // -- Bảng giữa (Thống kê theo ngày trong tháng) --
        JScrollPane scP_tablehienhd = new JScrollPane();
        scP_tablehienhd.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scP_tablehienhd.setBounds(500, 52, 436, 823);
        pn_tketheothang.add(scP_tablehienhd);
        
        // Áp dụng đúng cách tạo bảng table_hienhd_tktt
        table_hienhd_tktt = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    
                }
                return c;
            }
        };
        applyCommonTableStyling(table_hienhd_tktt); // Áp dụng style chung
        table_hienhd_tktt.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Ngày", "Tổng Số Hóa Đơn", "Tổng Tiền Các Hóa Đơn"}      
        ));
        scP_tablehienhd.setViewportView(table_hienhd_tktt);
        
        JButton btn_tktt_xemchitiet = new JButton("Xem Chi tiết");
        btn_tktt_xemchitiet.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_tktt_xemchitiet, COLOR_PRIMARY_BLUE);
        btn_tktt_xemchitiet.setBounds(1398, 833, 143, 42);
        pn_tketheothang.add(btn_tktt_xemchitiet);
        
        // -- Khu vực biểu đồ (Đại Ca sẽ tự thêm component biểu đồ vào đây) --
        JPanel pn_bieudo_thang = new JPanel();
        pn_bieudo_thang.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        pn_bieudo_thang.setBackground(COLOR_CARD_BACKGROUND);
        pn_bieudo_thang.setBounds(946, 96, 748, 726);
        pn_tketheothang.add(pn_bieudo_thang);
        pn_bieudo_thang.setLayout(new BorderLayout(0, 0)); // Để chứa biểu đồ

        JLabel lbl_temp_bieudo_thang = new JLabel("Khu vực hiển thị biểu đồ theo Tháng"); // Tạm thời
        lbl_temp_bieudo_thang.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_temp_bieudo_thang.setFont(FONT_LABEL_BOLD);
        pn_bieudo_thang.add(lbl_temp_bieudo_thang, BorderLayout.CENTER);

        JLabel lbl_tieudebd_tktt = new JLabel("Biểu Đồ Doanh Thu Tháng:");
        lbl_tieudebd_tktt.setFont(FONT_TITLE_SECTION);
        lbl_tieudebd_tktt.setForeground(COLOR_TEXT_DARK);
        lbl_tieudebd_tktt.setBounds(946, 52, 350, 35);
        pn_tketheothang.add(lbl_tieudebd_tktt);
        
        JLabel lbl_hienthangvanam = new JLabel("..."); // Sẽ cập nhật sau
        lbl_hienthangvanam.setFont(FONT_TITLE_SECTION);
        lbl_hienthangvanam.setForeground(COLOR_PRIMARY_BLUE);
        lbl_hienthangvanam.setBounds(1300, 52, 254, 35);
        pn_tketheothang.add(lbl_hienthangvanam);
        
        // ========== TAB THỐNG KÊ THEO NĂM ==========
        JPanel pn_tketheonam = new JPanel();
        pn_tketheonam.setBackground(COLOR_BACKGROUND_PRIMARY);
        tabbedPane.addTab("Theo Năm", null, pn_tketheonam, null);
        pn_tketheonam.setLayout(null);
        
        JLabel lbl_namthongke = new JLabel("Chọn Năm TK :");
        lbl_namthongke.setFont(FONT_LABEL_BOLD);
        lbl_namthongke.setForeground(COLOR_TEXT_DARK);
        lbl_namthongke.setBounds(26, 11, 143, 30);
        pn_tketheonam.add(lbl_namthongke);

        // Dùng JYearChooser
        year_tktn = new JYearChooser();
        year_tktn.setFont(FONT_TEXT_FIELD);
        year_tktn.setBounds(179, 11, 100, 30); // Giảm chiều rộng
        pn_tketheonam.add(year_tktn);
        
        JLabel lbl_tktnam_tongsohd = new JLabel("Tổng Số Hóa Đơn :");
        lbl_tktnam_tongsohd.setFont(FONT_LABEL_BOLD);
        lbl_tktnam_tongsohd.setForeground(COLOR_TEXT_DARK);
        lbl_tktnam_tongsohd.setBounds(500, 11, 165, 30);
        pn_tketheonam.add(lbl_tktnam_tongsohd);
        
        JLabel lbl_tktn_hientshd = new JLabel("0");
        lbl_tktn_hientshd.setFont(FONT_LABEL_BOLD);
        lbl_tktn_hientshd.setForeground(COLOR_PRIMARY_BLUE);
        lbl_tktn_hientshd.setBounds(675, 11, 143, 30);
        pn_tketheonam.add(lbl_tktn_hientshd);
        
        JLabel lbl_tktnam_tongtiencachd = new JLabel("Tổng Doanh Thu :");
        lbl_tktnam_tongtiencachd.setFont(FONT_LABEL_BOLD);
        lbl_tktnam_tongtiencachd.setForeground(COLOR_TEXT_DARK);
        lbl_tktnam_tongtiencachd.setBounds(900, 11, 160, 30);
        pn_tketheonam.add(lbl_tktnam_tongtiencachd);
        
        JLabel lbl_tktnam_hientongsotien = new JLabel("0 VND");
        lbl_tktnam_hientongsotien.setFont(FONT_SUMMARY_TOTAL);
        lbl_tktnam_hientongsotien.setForeground(COLOR_SUCCESS_GREEN);
        lbl_tktnam_hientongsotien.setBounds(1070, 11, 250, 30);
        pn_tketheonam.add(lbl_tktnam_hientongsotien);
        
        JButton btn_tktnam_xuatfile = new JButton("Xuất File");
        btn_tktnam_xuatfile.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_tktnam_xuatfile, COLOR_SUCCESS_GREEN);
        btn_tktnam_xuatfile.setBounds(1551, 833, 143, 42);
        pn_tketheonam.add(btn_tktnam_xuatfile);

        // -- Panel bên trái (Lọc nhân viên) --
        JPanel pn_tktnam_tk = new JPanel(); // Giữ tên biến
        pn_tktnam_tk.setOpaque(false);
        pn_tktnam_tk.setLayout(null);
        pn_tktnam_tk.setBounds(10, 52, 480, 834);
        pn_tketheonam.add(pn_tktnam_tk);
        
        // (Code bên trong pn_tktnam_tk giống hệt pn_tktn_tktnv)
        JPanel pn_tktnam_tk_boloc = new JPanel();
        pn_tktnam_tk_boloc.setBackground(COLOR_CARD_BACKGROUND);
        pn_tktnam_tk_boloc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true), "Lọc Theo Nhân Viên", TitledBorder.LEADING, TitledBorder.TOP, FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE));
        pn_tktnam_tk_boloc.setBounds(10, 11, 459, 230);
        pn_tktnam_tk.add(pn_tktnam_tk_boloc);
        pn_tktnam_tk_boloc.setLayout(null);

        JLabel lbl_tktn_chonnamtk = new JLabel("Thống Kê Theo :");
        lbl_tktn_chonnamtk.setFont(FONT_LABEL_BOLD);
        lbl_tktn_chonnamtk.setForeground(COLOR_TEXT_DARK);
        lbl_tktn_chonnamtk.setBounds(21, 25, 142, 30);
        pn_tktnam_tk_boloc.add(lbl_tktn_chonnamtk);

        JComboBox<String> cb_chonnamtk = new JComboBox<String>();
        cb_chonnamtk.setFont(FONT_TEXT_FIELD);
        cb_chonnamtk.setBounds(173, 25, 258, 30);
        pn_tktnam_tk_boloc.add(cb_chonnamtk);

        JPanel pn_tktnam_locnv = new JPanel();
        pn_tktnam_locnv.setLayout(null);
        pn_tktnam_locnv.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true), "Tìm Kiếm Nhân Viên", TitledBorder.LEADING, TitledBorder.TOP, FONT_LABEL_BOLD, COLOR_TEXT_MUTED));
        pn_tktnam_locnv.setBackground(Color.WHITE);
        pn_tktnam_locnv.setBounds(10, 66, 437, 153);
        pn_tktnam_tk_boloc.add(pn_tktnam_locnv);

        JLabel lbl_tktn_locnv_manv = new JLabel("Mã Nhân Viên :");
        lbl_tktn_locnv_manv.setFont(FONT_LABEL_BOLD);
        lbl_tktn_locnv_manv.setForeground(COLOR_TEXT_DARK);
        lbl_tktn_locnv_manv.setBounds(10, 33, 142, 30);
        pn_tktnam_locnv.add(lbl_tktn_locnv_manv);

        JLabel lbl_tktnam_locnv_tennv = new JLabel("Tên Nhân Viên :");
        lbl_tktnam_locnv_tennv.setFont(FONT_LABEL_BOLD);
        lbl_tktnam_locnv_tennv.setForeground(COLOR_TEXT_DARK);
        lbl_tktnam_locnv_tennv.setBounds(10, 85, 142, 30);
        pn_tktnam_locnv.add(lbl_tktnam_locnv_tennv);

        text_tktnam_locnv_manv = new JTextField();
        text_tktnam_locnv_manv.setFont(FONT_TEXT_FIELD);
        text_tktnam_locnv_manv.setColumns(10);
        text_tktnam_locnv_manv.setBounds(162, 33, 261, 30);
        pn_tktnam_locnv.add(text_tktnam_locnv_manv);

        text_tktnam_locnv_tennv = new JTextField();
        text_tktnam_locnv_tennv.setFont(FONT_TEXT_FIELD);
        text_tktnam_locnv_tennv.setColumns(10);
        text_tktnam_locnv_tennv.setBounds(162, 85, 261, 30);
        pn_tktnam_locnv.add(text_tktnam_locnv_tennv);

        JScrollPane scPtknv_hientablenv = new JScrollPane();
        scPtknv_hientablenv.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scPtknv_hientablenv.setBounds(10, 252, 459, 571);
        pn_tktnam_tk.add(scPtknv_hientablenv);

        // Áp dụng đúng cách tạo bảng table_tktn_hiennv
        table_tktn_hiennv = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    
                }
                return c;
            }
        };
        applyCommonTableStyling(table_tktn_hiennv); // Áp dụng style chung
        table_tktn_hiennv.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Mã Nhân Viên", "Tên Nhân Viên"}
        ));
        scPtknv_hientablenv.setViewportView(table_tktn_hiennv);

        // -- Bảng giữa (Thống kê theo tháng trong năm) --
        JScrollPane scP_table_hienhdtheothang = new JScrollPane();
        scP_table_hienhdtheothang.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scP_table_hienhdtheothang.setBounds(500, 52, 436, 823);
        pn_tketheonam.add(scP_table_hienhdtheothang);
        
        // Áp dụng đúng cách tạo bảng table_11
        table_11 = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    
                }
                return c;
            }
        };
        applyCommonTableStyling(table_11); // Áp dụng style chung
        table_11.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Tháng", "Tổng Số Hóa Đơn", "Tổng Tiền Các Hóa Đơn"}
        ));
        scP_table_hienhdtheothang.setViewportView(table_11);
        
        JButton btn_tktnam_xemchitiet = new JButton("Xem Chi tiết");
        btn_tktnam_xemchitiet.setFont(FONT_BUTTON_STANDARD);
        styleButton(btn_tktnam_xemchitiet, COLOR_PRIMARY_BLUE);
        btn_tktnam_xemchitiet.setBounds(1398, 833, 143, 42);
        pn_tketheonam.add(btn_tktnam_xemchitiet);
        
        // -- Khu vực biểu đồ --
        JPanel pn_bieudo_nam = new JPanel();
        pn_bieudo_nam.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        pn_bieudo_nam.setBackground(COLOR_CARD_BACKGROUND);
        pn_bieudo_nam.setBounds(946, 96, 748, 726);
        pn_tketheonam.add(pn_bieudo_nam);
        pn_bieudo_nam.setLayout(new BorderLayout(0, 0)); // Để chứa biểu đồ

        JLabel lbl_temp_bieudo_nam = new JLabel("Khu vực hiển thị biểu đồ theo Năm"); // Tạm thời
        lbl_temp_bieudo_nam.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_temp_bieudo_nam.setFont(FONT_LABEL_BOLD);
        pn_bieudo_nam.add(lbl_temp_bieudo_nam, BorderLayout.CENTER);

        JLabel lbl_tktnam_tieude = new JLabel("Biểu Đồ Doanh Thu Năm:");
        lbl_tktnam_tieude.setFont(FONT_TITLE_SECTION);
        lbl_tktnam_tieude.setForeground(COLOR_TEXT_DARK);
        lbl_tktnam_tieude.setBounds(946, 52, 323, 35);
        pn_tketheonam.add(lbl_tktnam_tieude);
        
        JLabel lbl_tktn_hiennam = new JLabel("..."); // Sẽ cập nhật sau
        lbl_tktn_hiennam.setFont(FONT_TITLE_SECTION);
        lbl_tktn_hiennam.setForeground(COLOR_PRIMARY_BLUE);
        lbl_tktn_hiennam.setBounds(1280, 52, 121, 35);
        pn_tketheonam.add(lbl_tktn_hiennam);
        
        // ===== KẾT THÚC KHỐI CODE THỐNG KÊ HÓA ĐƠN ĐÃ SỬA =====

     // ===== BẮT ĐẦU KHỐI CODE THÊM NHÂN VIÊN ĐÃ SỬA (BỎ MÀU MENU BAR) =====
        JPanel panel_ThemNV = new JPanel();
        maincontent.add(panel_ThemNV, "themNV");
        panel_ThemNV.setLayout(null);
        panel_ThemNV.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        // Panel tiêu đề (Dùng màu gốc của Đại Ca)
        JPanel panel_title_ThemNV = new JPanel();
        panel_title_ThemNV.setBounds(0, 0, 1584, 67); // Điều chỉnh width cho vừa màn hình
        panel_title_ThemNV.setBackground(new Color(0, 102, 102)); // <<< DÙNG MÀU GỐC
        panel_ThemNV.add(panel_title_ThemNV);
        panel_title_ThemNV.setLayout(null);

        JLabel lblTitle_TNV = new JLabel("THÊM NHÂN VIÊN MỚI");
        lblTitle_TNV.setBounds(0, 11, 1584, 45); // Căn giữa
        panel_title_ThemNV.add(lblTitle_TNV);
        lblTitle_TNV.setFont(FONT_TITLE_MAIN); // Font tiêu đề
        lblTitle_TNV.setForeground(Color.WHITE); // Chữ trắng
        lblTitle_TNV.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa text


        // Panel ảnh
        JPanel panelAnhNV_TNV = new JPanel();
        panelAnhNV_TNV.setLayout(new BorderLayout(0, 0));
        panelAnhNV_TNV.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        panelAnhNV_TNV.setBackground(COLOR_BACKGROUND_PRIMARY);
        panelAnhNV_TNV.setBounds(40, 80, 383, 500);
        panel_ThemNV.add(panelAnhNV_TNV);

        JLabel lblAnhNV_TNV = new JLabel("Chưa có ảnh", SwingConstants.CENTER);
        lblAnhNV_TNV.setFont(FONT_DETAIL_ITALIC);
        lblAnhNV_TNV.setForeground(COLOR_TEXT_MUTED);
        panelAnhNV_TNV.add(lblAnhNV_TNV, BorderLayout.CENTER);

        // Nút chọn ảnh (Giữ nguyên code icon)
        JButton btnChonAnh_TNV = new JButton("Chọn ảnh");
        btnChonAnh_TNV.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnChonAnh_TNV, COLOR_TEXT_MUTED);
        btnChonAnh_TNV.setBounds(160, 590, 143, 40);
        // --- Code ActionListener và Icon của Đại Ca ---
        btnChonAnh_TNV.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn ảnh nhân viên");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                duongDanAnh_TNV = file.getAbsolutePath();
                lblAnhNV_TNV.setIcon(new ImageIcon(new ImageIcon(duongDanAnh_TNV).getImage()
                    .getScaledInstance(lblAnhNV_TNV.getWidth(), lblAnhNV_TNV.getHeight(), Image.SCALE_SMOOTH)));
                lblAnhNV_TNV.setText(null);
            }
        });
        java.net.URL imgChonAnh_TNV = getClass().getResource("/file-icon.png");
        if (imgChonAnh_TNV != null) {
            ImageIcon originalIcon = new ImageIcon(imgChonAnh_TNV);
            Image img = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
            btnChonAnh_TNV.setIcon(scaledIcon);
            btnChonAnh_TNV.setHorizontalAlignment(SwingConstants.LEFT);
            btnChonAnh_TNV.setIconTextGap(10);
        } else {
            System.err.println("Lỗi: Không tìm thấy ảnh tại /file-icon.png");
        }
        // --- Hết code ActionListener và Icon ---
        panel_ThemNV.add(btnChonAnh_TNV);


        // Panel thông tin nhân viên
        JPanel panelThongTinNV_TNV = new JPanel();
        panelThongTinNV_TNV.setLayout(null);
        panelThongTinNV_TNV.setBackground(COLOR_CARD_BACKGROUND);
        panelThongTinNV_TNV.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Thông tin nhân viên", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        ));
        panelThongTinNV_TNV.setBounds(450, 80, 1114, 550);
        panel_ThemNV.add(panelThongTinNV_TNV);

        // Định nghĩa vị trí và khoảng cách
        int labelX_nv = 40;
        int inputX_nv = 190;
        int labelX2_nv = 550;
        int inputX2_nv = 680;
        int startY_nv = 40;
        int height_nv = 33;
        int vGap_nv = 15;

        JLabel lblMaNV_TNV = new JLabel("Mã nhân viên:");
        lblMaNV_TNV.setFont(FONT_LABEL_BOLD);
        lblMaNV_TNV.setForeground(COLOR_TEXT_DARK);
        lblMaNV_TNV.setBounds(labelX_nv, startY_nv, 140, height_nv);
        panelThongTinNV_TNV.add(lblMaNV_TNV);

        txtMaNV_TNV = new JTextField();
        txtMaNV_TNV.setFont(FONT_LABEL_BOLD);
        txtMaNV_TNV.setForeground(COLOR_DANGER_RED);
        txtMaNV_TNV.setEditable(false);
        txtMaNV_TNV.setColumns(10);
        txtMaNV_TNV.setBounds(inputX_nv, startY_nv, 300, height_nv);
        txtMaNV_TNV.setBorder(null);
        txtMaNV_TNV.setBackground(COLOR_CARD_BACKGROUND);
        nhanVien_DAO nv_dao = new nhanVien_DAO();
        String maMoi = nv_dao.generateNewMaNV();
        txtMaNV_TNV.setText(maMoi);
        panelThongTinNV_TNV.add(txtMaNV_TNV);

        JLabel lblGioiTinh_TNV = new JLabel("Giới Tính:");
        lblGioiTinh_TNV.setFont(FONT_LABEL_BOLD);
        lblGioiTinh_TNV.setForeground(COLOR_TEXT_DARK);
        lblGioiTinh_TNV.setBounds(labelX2_nv, startY_nv, 120, height_nv);
        panelThongTinNV_TNV.add(lblGioiTinh_TNV);

        JComboBox<String> cboGIoiTinh_TNV = new JComboBox<String>();
        cboGIoiTinh_TNV.setModel(new DefaultComboBoxModel<String>(new String[] {"Nam", "Nữ"}));
        cboGIoiTinh_TNV.setFont(FONT_TEXT_FIELD);
        cboGIoiTinh_TNV.setBounds(inputX2_nv, startY_nv, 350, height_nv);
        panelThongTinNV_TNV.add(cboGIoiTinh_TNV);

        JLabel lblTenNV_TNV = new JLabel("Tên nhân viên:");
        lblTenNV_TNV.setFont(FONT_LABEL_BOLD);
        lblTenNV_TNV.setForeground(COLOR_TEXT_DARK);
        lblTenNV_TNV.setBounds(labelX_nv, startY_nv + height_nv + vGap_nv, 140, height_nv);
        panelThongTinNV_TNV.add(lblTenNV_TNV);

        txtTenNV_TNV = new JTextField();
        txtTenNV_TNV.setFont(FONT_TEXT_FIELD);
        txtTenNV_TNV.setColumns(10);
        txtTenNV_TNV.setBounds(inputX_nv, startY_nv + height_nv + vGap_nv, 840, height_nv);
        panelThongTinNV_TNV.add(txtTenNV_TNV);

        JLabel lblSDT_TNV = new JLabel("Số điện thoại:");
        lblSDT_TNV.setFont(FONT_LABEL_BOLD);
        lblSDT_TNV.setForeground(COLOR_TEXT_DARK);
        lblSDT_TNV.setBounds(labelX_nv, startY_nv + 2*(height_nv + vGap_nv), 140, height_nv);
        panelThongTinNV_TNV.add(lblSDT_TNV);

        txtSDT_TNV = new JTextField();
        txtSDT_TNV.setFont(FONT_TEXT_FIELD);
        txtSDT_TNV.setColumns(10);
        txtSDT_TNV.setBounds(inputX_nv, startY_nv + 2*(height_nv + vGap_nv), 300, height_nv);
        panelThongTinNV_TNV.add(txtSDT_TNV);

        JLabel lblNgaySinh_TNV = new JLabel("Ngày sinh:");
        lblNgaySinh_TNV.setFont(FONT_LABEL_BOLD);
        lblNgaySinh_TNV.setForeground(COLOR_TEXT_DARK);
        lblNgaySinh_TNV.setBounds(labelX2_nv, startY_nv + 2*(height_nv + vGap_nv), 120, height_nv);
        panelThongTinNV_TNV.add(lblNgaySinh_TNV);

        JDateChooser dateNgaySinh_TNV = new JDateChooser();
        dateNgaySinh_TNV.setFont(FONT_TEXT_FIELD);
        dateNgaySinh_TNV.setDateFormatString("dd/MM/yyyy");
        dateNgaySinh_TNV.setBounds(inputX2_nv, startY_nv + 2*(height_nv + vGap_nv), 350, height_nv);
        panelThongTinNV_TNV.add(dateNgaySinh_TNV);

        JLabel lblChucVu_TNV = new JLabel("Chức vụ:");
        lblChucVu_TNV.setFont(FONT_LABEL_BOLD);
        lblChucVu_TNV.setForeground(COLOR_TEXT_DARK);
        lblChucVu_TNV.setBounds(labelX_nv, startY_nv + 3*(height_nv + vGap_nv), 140, height_nv);
        panelThongTinNV_TNV.add(lblChucVu_TNV);

        JComboBox<ChucVu> cboChucVu_TNV = new JComboBox<ChucVu>();
        cboChucVu_TNV.setFont(FONT_TEXT_FIELD);
        cboChucVu_TNV.setBounds(inputX_nv, startY_nv + 3*(height_nv + vGap_nv), 300, height_nv);
        panelThongTinNV_TNV.add(cboChucVu_TNV);

        JLabel lblTaiKhoan_TNV = new JLabel("Tài khoản:");
        lblTaiKhoan_TNV.setFont(FONT_LABEL_BOLD);
        lblTaiKhoan_TNV.setForeground(COLOR_TEXT_DARK);
        lblTaiKhoan_TNV.setBounds(labelX2_nv, startY_nv + 3*(height_nv + vGap_nv), 120, height_nv);
        panelThongTinNV_TNV.add(lblTaiKhoan_TNV);

        JComboBox<TaiKhoan> cboTaiKhoan_TNV = new JComboBox<TaiKhoan>();
        cboTaiKhoan_TNV.setFont(FONT_TEXT_FIELD);
        cboTaiKhoan_TNV.setBounds(inputX2_nv, startY_nv + 3*(height_nv + vGap_nv), 350, height_nv);
        panelThongTinNV_TNV.add(cboTaiKhoan_TNV);

        chucVu_DAO cv_dao_TNV = new chucVu_DAO();
        List<ChucVu> dsChucVu = cv_dao_TNV.getAllChucVu();
        for (ChucVu cv : dsChucVu) {
        	cboChucVu_TNV.addItem(cv);
        }
        taiKhoan_DAO tk_dao_TNV = new taiKhoan_DAO();
        List<TaiKhoan> dsTaiKhoan = tk_dao_TNV.getAllTaiKhoan();
        for (TaiKhoan tk : dsTaiKhoan) {
            cboTaiKhoan_TNV.addItem(tk);
         }

        JPanel panelDiaChi_TNV = new JPanel();
        panelDiaChi_TNV.setBackground(COLOR_CARD_BACKGROUND);
        panelDiaChi_TNV.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
             "Địa chỉ", TitledBorder.LEADING, TitledBorder.TOP,
             FONT_LABEL_BOLD, COLOR_TEXT_MUTED
        ));
        panelDiaChi_TNV.setBounds(labelX_nv, startY_nv + 4*(height_nv + vGap_nv) + 10, 990, 100);
        panelThongTinNV_TNV.add(panelDiaChi_TNV);
        panelDiaChi_TNV.setLayout(null);

        JLabel lblTinh_TNV = new JLabel("Tỉnh/TP:");
        lblTinh_TNV.setBounds(20, 35, 86, height_nv);
        lblTinh_TNV.setFont(FONT_LABEL_BOLD);
        lblTinh_TNV.setForeground(COLOR_TEXT_DARK);
        panelDiaChi_TNV.add(lblTinh_TNV);

        txtTinh_TNV = new JTextField();
        txtTinh_TNV.setFont(FONT_TEXT_FIELD);
        txtTinh_TNV.setBounds(110, 35, 345, height_nv);
        panelDiaChi_TNV.add(txtTinh_TNV);
        txtTinh_TNV.setColumns(10);

        JLabel lblHuyen_TNV = new JLabel("Quận/Huyện:");
        lblHuyen_TNV.setFont(FONT_LABEL_BOLD);
        lblHuyen_TNV.setForeground(COLOR_TEXT_DARK);
        lblHuyen_TNV.setBounds(480, 35, 120, height_nv);
        panelDiaChi_TNV.add(lblHuyen_TNV);

        txtHuyen_TNV = new JTextField();
        txtHuyen_TNV.setFont(FONT_TEXT_FIELD);
        txtHuyen_TNV.setColumns(10);
        txtHuyen_TNV.setBounds(610, 35, 360, height_nv);
        panelDiaChi_TNV.add(txtHuyen_TNV);

        // Nút Làm mới (Giữ nguyên code icon)
        JButton btnLamMoi_TNV = new JButton("Làm mới");
        btnLamMoi_TNV.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnLamMoi_TNV, COLOR_TEXT_MUTED);
        btnLamMoi_TNV.setBounds(1200, 650, 143, 40);
        // --- Code ActionListener và Icon của Đại Ca ---
        btnLamMoi_TNV.addActionListener(e -> {
            txtTenNV_TNV.setText("");
            txtSDT_TNV.setText("");
            txtTinh_TNV.setText("");
            txtHuyen_TNV.setText("");
            cboGIoiTinh_TNV.setSelectedIndex(0);
            cboChucVu_TNV.setSelectedIndex(0);
            cboTaiKhoan_TNV.setSelectedIndex(0);
            dateNgaySinh_TNV.setDate(null);
            lblAnhNV_TNV.setIcon(null);
            lblAnhNV_TNV.setText("Chưa có ảnh");
            duongDanAnh_TNV = null;

            nhanVien_DAO dao = new nhanVien_DAO();
            txtMaNV_TNV.setText(dao.generateNewMaNV());
        });
        java.net.URL imgLamMoi_TNV = getClass().getResource("/icon-refresh.png");
        if (imgLamMoi_TNV != null) {
            ImageIcon originalIcon = new ImageIcon(imgLamMoi_TNV);
            Image img = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
            btnLamMoi_TNV.setIcon(scaledIcon);
            btnLamMoi_TNV.setHorizontalAlignment(SwingConstants.LEFT);
            btnLamMoi_TNV.setIconTextGap(10);
        } else {
            System.err.println("Lỗi: Không tìm thấy ảnh tại /icon-refresh.png");
        }
        // --- Hết code ActionListener và Icon ---
        panel_ThemNV.add(btnLamMoi_TNV);

        // Nút Thêm (Giữ nguyên code icon)
        JButton btnThem_TNV = new JButton("Thêm");
        btnThem_TNV.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnThem_TNV, COLOR_SUCCESS_GREEN);
        btnThem_TNV.setBounds(1370, 650, 133, 40);
        // --- Code ActionListener và Icon của Đại Ca ---
        btnThem_TNV.addActionListener(e -> {
            try {
                // ===== LẤY DỮ LIỆU TỪ FORM =====
                String ten = txtTenNV_TNV.getText().trim();
                String sdt = txtSDT_TNV.getText().trim();
                String gioiTinh = cboGIoiTinh_TNV.getSelectedItem().toString();
                java.util.Date ngaySinh = dateNgaySinh_TNV.getDate();

                ChucVu selectedCV = (ChucVu) cboChucVu_TNV.getSelectedItem();
                String maChucVu = selectedCV != null ? selectedCV.getMaChucVu() : "";

                TaiKhoan selectedTK = (TaiKhoan) cboTaiKhoan_TNV.getSelectedItem();
                String tenTaiKhoan = selectedTK != null ? selectedTK.getTenTK() : "";

                String tinh = txtTinh_TNV.getText().trim();
                String huyen = txtHuyen_TNV.getText().trim();
                String anh = duongDanAnh_TNV;

                if (ten.isEmpty() || sdt.isEmpty() || ngaySinh == null ||
                    maChucVu.isEmpty() || tenTaiKhoan.isEmpty() || anh == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin và chọn ảnh!");
                    return;
                }

                // ===== CHUYỂN NGÀY =====
                LocalDate ngaySinhLocal = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // ===== KHỞI TẠO DAO =====
                taiKhoan_DAO tkDao = new taiKhoan_DAO();
                nhanVien_DAO nvDAO = new nhanVien_DAO();

                // ===== TẠO MÃ TÀI KHOẢN MỚI =====
                String maTaiKhoanMoi = tkDao.generateNewMaTK();

                TaiKhoan tkMoi = new TaiKhoan();
                tkMoi.setMaTK(maTaiKhoanMoi);
                tkMoi.setTenTK(tenTaiKhoan);
                tkMoi.setMatKhau("123"); // mật khẩu mặc định
                tkMoi.setQuyenHan("Nhân viên");

                // ===== LƯU TÀI KHOẢN MỚI VÀO DB =====
                boolean tkThemThanhCong = tkDao.insertTaiKhoan(tkMoi);
                if (!tkThemThanhCong) {
                    JOptionPane.showMessageDialog(null, "Không thể thêm tài khoản mới!");
                    return;
                }

                // ===== TẠO MÃ NHÂN VIÊN MỚI =====
                String maNhanVienMoi = nvDAO.generateNewMaNV();

                // ===== TẠO NHÂN VIÊN =====
                NhanVien nv = new NhanVien();
                nv.setMaNV(maNhanVienMoi);
                nv.setTenNV(ten);
                nv.setSoDienThoai(sdt);
                nv.setGioiTinh(gioiTinh);
                nv.setNgaySinh(ngaySinhLocal);

                ChucVu cv = new ChucVu();
                cv.setMaChucVu(maChucVu);
                nv.setChucVu(cv);

                TaiKhoan tk = new TaiKhoan();
                tk.setMaTK(maTaiKhoanMoi);
                nv.setTaiKhoan(tk);

                nv.setDiaChi(tinh + ", " + huyen);
                nv.setAnh(anh);

                // ===== THÊM NHÂN VIÊN =====
                boolean kq = nvDAO.insertNhanVien(nv);
                if (kq) {
                    JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể thêm nhân viên (kiểm tra lại dữ liệu)!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
            }
        });


        java.net.URL imgThem_TNV = getClass().getResource("/icon-add.png");
        if (imgThem_TNV != null) {
            ImageIcon originalIcon = new ImageIcon(imgThem_TNV);
            Image img = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
            btnThem_TNV.setIcon(scaledIcon);
            btnThem_TNV.setHorizontalAlignment(SwingConstants.LEFT);
            btnThem_TNV.setIconTextGap(10);
        } else {
            System.err.println("Lỗi: Không tìm thấy ảnh tại /icon-add.png");
        }
        // --- Hết code ActionListener và Icon ---
        panel_ThemNV.add(btnThem_TNV);

        // ===== KẾT THÚC KHỐI CODE THÊM NHÂN VIÊN =====

     // ===== BẮT ĐẦU KHỐI CODE TÌM KIẾM NHÂN VIÊN ĐÃ SỬA =====
        JPanel panel_TimKiemNV = new JPanel();
        maincontent.add(panel_TimKiemNV, "timkiemnv");
        panel_TimKiemNV.setLayout(null);
        panel_TimKiemNV.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        // Panel tiêu đề
        JPanel panel_title_TKNV = new JPanel();
        panel_title_TKNV.setLayout(null);
        panel_title_TKNV.setBackground(new Color(0, 102, 102)); // Màu gốc
        panel_title_TKNV.setBounds(0, 0, 1584, 67); // Điều chỉnh width nếu cần
        panel_TimKiemNV.add(panel_title_TKNV);

        JLabel lblTitle_TKNV = new JLabel("TÌM KIẾM NHÂN VIÊN");
        lblTitle_TKNV.setForeground(Color.WHITE);
        lblTitle_TKNV.setFont(FONT_TITLE_MAIN); // Font tiêu đề
        lblTitle_TKNV.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lblTitle_TKNV.setBounds(0, 11, 1584, 45); // Căn giữa
        panel_title_TKNV.add(lblTitle_TKNV);

        // Panel bộ lọc
        JPanel pnlFilters_TKNV = new JPanel(); // Đặt tên mới cho dễ hiểu
        pnlFilters_TKNV.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
        pnlFilters_TKNV.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Bộ Lọc Tìm Kiếm", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        )); // Viền và tiêu đề
        pnlFilters_TKNV.setBounds(10, 80, 1564, 280); // Vị trí bộ lọc
        panel_TimKiemNV.add(pnlFilters_TKNV);
        pnlFilters_TKNV.setLayout(null);

        JLabel lblTenNV_TKNV = new JLabel("Tên nhân viên:");
        lblTenNV_TKNV.setFont(FONT_LABEL_BOLD); // Font label
        lblTenNV_TKNV.setForeground(COLOR_TEXT_DARK); // Màu chữ
        lblTenNV_TKNV.setBounds(40, 40, 163, 30); // Vị trí
        pnlFilters_TKNV.add(lblTenNV_TKNV);

        txt_TenNV_TKNV = new JTextField();
        txt_TenNV_TKNV.setFont(FONT_TEXT_FIELD); // Font text field
        txt_TenNV_TKNV.setBounds(210, 40, 500, 33); // Vị trí
        txt_TenNV_TKNV.setColumns(10);
        pnlFilters_TKNV.add(txt_TenNV_TKNV);

        JLabel lblSDT_TKNV = new JLabel("Số điện thoại:");
        lblSDT_TKNV.setFont(FONT_LABEL_BOLD); // Font label
        lblSDT_TKNV.setForeground(COLOR_TEXT_DARK); // Màu chữ
        lblSDT_TKNV.setBounds(800, 40, 139, 30); // Vị trí
        pnlFilters_TKNV.add(lblSDT_TKNV);

        txtSDT_TKNV = new JTextField();
        txtSDT_TKNV.setFont(FONT_TEXT_FIELD); // Font text field
        txtSDT_TKNV.setBounds(950, 40, 450, 33); // Vị trí
        txtSDT_TKNV.setColumns(10);
        pnlFilters_TKNV.add(txtSDT_TKNV);

        JLabel lblGioiTinh_TK = new JLabel("Giới tính:");
        lblGioiTinh_TK.setFont(FONT_LABEL_BOLD); // Font label
        lblGioiTinh_TK.setForeground(COLOR_TEXT_DARK); // Màu chữ
        lblGioiTinh_TK.setBounds(40, 90, 163, 30); // Vị trí
        pnlFilters_TKNV.add(lblGioiTinh_TK);

        JComboBox<String> cboGioiTinh_TKNV = new JComboBox<String>();
        cboGioiTinh_TKNV.setModel(new DefaultComboBoxModel<String>(new String[] {"Tất cả", "Nam", "Nữ"})); // Thêm "Tất cả"
        cboGioiTinh_TKNV.setFont(FONT_TEXT_FIELD); // Font text field
        cboGioiTinh_TKNV.setBounds(210, 90, 163, 33); // Vị trí
        pnlFilters_TKNV.add(cboGioiTinh_TKNV);

        JLabel lblVaiTro_TKNV = new JLabel("Chức vụ:"); // Đổi thành Chức vụ
        lblVaiTro_TKNV.setFont(FONT_LABEL_BOLD); // Font label
        lblVaiTro_TKNV.setForeground(COLOR_TEXT_DARK); // Màu chữ
        lblVaiTro_TKNV.setBounds(800, 90, 139, 30); // Vị trí
        pnlFilters_TKNV.add(lblVaiTro_TKNV);

        JComboBox<ChucVu> cboVaiTro_TKNV = new JComboBox<ChucVu>(); // Dùng ChucVu
        cboVaiTro_TKNV.setFont(FONT_TEXT_FIELD); // Font text field
        cboVaiTro_TKNV.setBounds(950, 90, 450, 33); // Vị trí
        // Load chức vụ vào combobox (Thêm lựa chọn null cho "Tất cả")
        cboVaiTro_TKNV.addItem(null);
        chucVu_DAO cv_dao_TKNV = new chucVu_DAO();
        List<ChucVu> dsChucVu_TKNV = cv_dao_TKNV.getAllChucVu();
        for (ChucVu cv : dsChucVu_TKNV) {
            cboVaiTro_TKNV.addItem(cv);
        }
        pnlFilters_TKNV.add(cboVaiTro_TKNV);


        // Panel địa chỉ trong bộ lọc
        JPanel panelDiaChi_TKNV = new JPanel();
        panelDiaChi_TKNV.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
        panelDiaChi_TKNV.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
             "Địa chỉ", TitledBorder.LEADING, TitledBorder.TOP,
             FONT_LABEL_BOLD, COLOR_TEXT_MUTED // Màu nhạt hơn
        ));
        panelDiaChi_TKNV.setBounds(20, 140, 1524, 80); // Vị trí
        pnlFilters_TKNV.add(panelDiaChi_TKNV);
        panelDiaChi_TKNV.setLayout(null);

        JLabel lblTinh_TKNV = new JLabel("Tỉnh/TP:");
        lblTinh_TKNV.setBounds(20, 30, 86, 30);
        lblTinh_TKNV.setFont(FONT_LABEL_BOLD); // Font label
        lblTinh_TKNV.setForeground(COLOR_TEXT_DARK); // Màu chữ
        panelDiaChi_TKNV.add(lblTinh_TKNV);

        txtTinh_TKNV = new JTextField();
        txtTinh_TKNV.setBounds(110, 30, 480, 33);
        txtTinh_TKNV.setFont(FONT_TEXT_FIELD); // Font text field
        txtTinh_TKNV.setColumns(10);
        panelDiaChi_TKNV.add(txtTinh_TKNV);

        JLabel lblHuyen_TKNV = new JLabel("Quận/Huyện:");
        lblHuyen_TKNV.setBounds(650, 30, 120, 30);
        lblHuyen_TKNV.setFont(FONT_LABEL_BOLD); // Font label
        lblHuyen_TKNV.setForeground(COLOR_TEXT_DARK); // Màu chữ
        panelDiaChi_TKNV.add(lblHuyen_TKNV);

        txtHuyen_TKNV = new JTextField();
        txtHuyen_TKNV.setBounds(780, 30, 500, 33);
        txtHuyen_TKNV.setFont(FONT_TEXT_FIELD); // Font text field
        txtHuyen_TKNV.setColumns(10);
        panelDiaChi_TKNV.add(txtHuyen_TKNV);

        // Nút trong bộ lọc
        JButton btnLamMoi_TKNV = new JButton("Làm mới");
        btnLamMoi_TKNV.setFont(FONT_BUTTON_STANDARD); // Font nút
        styleButton(btnLamMoi_TKNV, COLOR_TEXT_MUTED); // Style nút phụ
        btnLamMoi_TKNV.setBounds(1400, 230, 140, 40); // Vị trí nút
        pnlFilters_TKNV.add(btnLamMoi_TKNV);

        JButton btnXemCT_TKNV = new JButton("Xem chi tiết");
         btnXemCT_TKNV.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		XemChiTietNV_GUI xemCTNV = new XemChiTietNV_GUI(QuanLyHieuThuocTay); // Hoặc (this)
                xemCTNV.setVisible(true);
        	}
        });
        btnXemCT_TKNV.setFont(FONT_BUTTON_STANDARD); // Font nút
        styleButton(btnXemCT_TKNV, COLOR_PRIMARY_BLUE); // Style nút chính
        btnXemCT_TKNV.setBounds(1240, 230, 140, 40); // Vị trí nút
        pnlFilters_TKNV.add(btnXemCT_TKNV);

        // ScrollPane và Bảng kết quả
        JScrollPane scrollPane_TKNV = new JScrollPane();
        scrollPane_TKNV.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền
        scrollPane_TKNV.setBounds(10, 370, 1564, 558); // Vị trí bảng
        panel_TimKiemNV.add(scrollPane_TKNV);

        // Style bảng table_TKNV
        table_TKNV = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground()); // Dùng this
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    // Không set foreground trắng
                }
                return c;
            }
        };
        applyCommonTableStyling(table_TKNV); // Áp dụng style chung
        table_TKNV.setModel(new DefaultTableModel(
        	new Object[][] {}, // Bỏ dòng null
        	new String[] {
        		"Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ", "Chức vụ", "Ảnh nhân viên", "Tài khoản"
        	}
        ));
        scrollPane_TKNV.setViewportView(table_TKNV);

        // ===== KẾT THÚC KHỐI CODE TÌM KIẾM NHÂN VIÊN =====
        
     // ===== BẮT ĐẦU KHỐI CODE CẬP NHẬT NHÂN VIÊN ĐÃ SỬA =====
	        JPanel panel_CapNhatNV = new JPanel();
	        maincontent.add(panel_CapNhatNV, "capnhatnv");
	        panel_CapNhatNV.setLayout(null);
	        panel_CapNhatNV.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính
	        panel_CapNhatNV.addComponentListener(new ComponentAdapter() {
	            @Override
	            public void componentShown(ComponentEvent e) {
	                loadDataToTableNV(table_CNNV);
	            }
	        });
	
	        // Panel tiêu đề
	        JPanel panel_title_TKNV_1 = new JPanel(); // Giữ nguyên tên biến
	        panel_title_TKNV_1.setLayout(null);
	        panel_title_TKNV_1.setBackground(new Color(0, 102, 102)); // Màu gốc
	        panel_title_TKNV_1.setBounds(0, 0, 1584, 67); // Điều chỉnh width nếu cần
	        panel_CapNhatNV.add(panel_title_TKNV_1);
	
	        JLabel lblTitle_TKNV_1 = new JLabel("CẬP NHẬT THÔNG TIN NHÂN VIÊN"); // Đổi text tiêu đề
	        lblTitle_TKNV_1.setForeground(Color.WHITE);
	        lblTitle_TKNV_1.setFont(FONT_TITLE_MAIN); // Font tiêu đề
	        lblTitle_TKNV_1.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
	        lblTitle_TKNV_1.setBounds(0, 11, 1584, 45); // Căn giữa
	        panel_title_TKNV_1.add(lblTitle_TKNV_1);
	
	
	        // Panel ảnh
	        JPanel panelAnh_CNNV = new JPanel();
	        panelAnh_CNNV.setLayout(new BorderLayout(0, 0));
	        panelAnh_CNNV.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
	        panelAnh_CNNV.setBackground(COLOR_BACKGROUND_PRIMARY);
	        panelAnh_CNNV.setBounds(40, 80, 339, 328); // Vị trí
	        panel_CapNhatNV.add(panelAnh_CNNV);
	
	        JLabel lblAnhNV_CNNV = new JLabel("Chưa có ảnh", SwingConstants.CENTER);
	        lblAnhNV_CNNV.setFont(FONT_DETAIL_ITALIC);
	        lblAnhNV_CNNV.setForeground(COLOR_TEXT_MUTED);
	        panelAnh_CNNV.add(lblAnhNV_CNNV, BorderLayout.CENTER);
	
	     // Nút chọn ảnh (Giữ nguyên code icon)
	        JButton btnChonAnh_CNNV = new JButton("Chọn ảnh");
	        btnChonAnh_CNNV.setFont(FONT_BUTTON_STANDARD); // Chỉ set font
	        styleButton(btnChonAnh_CNNV, COLOR_TEXT_MUTED); // Chỉ set màu
	        btnChonAnh_CNNV.setBounds(140, 420, 152, 37); // Vị trí
	        // --- Code ActionListener GỐC của Đại Ca (KHÔNG try-catch) ---
	        btnChonAnh_CNNV.addActionListener(e -> {
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.setDialogTitle("Chọn ảnh nhân viên");
	            fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));
	            int result = fileChooser.showOpenDialog(null);
	            if (result == JFileChooser.APPROVE_OPTION) {
	                File file = fileChooser.getSelectedFile();
	                duongDanAnh_CNNV = file.getAbsolutePath();
	                // Code gốc load ảnh
	                lblAnhNV_CNNV.setIcon(new ImageIcon(new ImageIcon(duongDanAnh_CNNV).getImage()
	                    .getScaledInstance(lblAnhNV_CNNV.getWidth(), lblAnhNV_CNNV.getHeight(), Image.SCALE_SMOOTH)));
	                lblAnhNV_CNNV.setText(null); // Giữ lại dòng này để xóa chữ
	            }
	        });
	        // --- Code Icon GỐC của Đại Ca ---
	        java.net.URL imgURL = getClass().getResource("/file-icon.png");
	        if (imgURL != null) {
	            ImageIcon originalIcon = new ImageIcon(imgURL);
	            Image img = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
	            ImageIcon scaledIcon = new ImageIcon(img);
	            btnChonAnh_CNNV.setIcon(scaledIcon);
	            btnChonAnh_CNNV.setHorizontalAlignment(SwingConstants.LEFT);
	            btnChonAnh_CNNV.setIconTextGap(10);
	        } else {
	            System.err.println("Lỗi: Không tìm thấy ảnh tại /file-icon.png");
	        }
	        // --- Hết code Icon ---
	        panel_CapNhatNV.add(btnChonAnh_CNNV); // Add nút vào panel
	        // --- Hết code ActionListener và Icon ---
	        panel_CapNhatNV.add(btnChonAnh_CNNV);
	
	        // Panel thông tin cập nhật
	        JPanel pnlInfo_CNNV = new JPanel();
	        pnlInfo_CNNV.setBackground(COLOR_CARD_BACKGROUND);
	        pnlInfo_CNNV.setBorder(BorderFactory.createTitledBorder(
	            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
	            "Thông Tin Cập Nhật", TitledBorder.LEADING, TitledBorder.TOP,
	            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
	        ));
	        pnlInfo_CNNV.setBounds(400, 80, 1164, 320);
	        panel_CapNhatNV.add(pnlInfo_CNNV);
	        pnlInfo_CNNV.setLayout(null);
	
	        // Định nghĩa vị trí và khoảng cách
	        int labelX_cnnv = 30;
	        int inputX_cnnv = 170;
	        int labelX2_cnnv = 550;
	        int inputX2_cnnv = 680;
	        int startY_cnnv = 30;
	        int height_cnnv = 33;
	        int vGap_cnnv = 15;
	
	        JLabel lblMaNV_CNNV = new JLabel("Mã nhân viên:");
	        lblMaNV_CNNV.setFont(FONT_LABEL_BOLD);
	        lblMaNV_CNNV.setForeground(COLOR_TEXT_DARK);
	        lblMaNV_CNNV.setBounds(labelX_cnnv, startY_cnnv, 130, height_cnnv);
	        pnlInfo_CNNV.add(lblMaNV_CNNV);
	
	        txtMaNV_CNNV = new JTextField();
	        txtMaNV_CNNV.setFont(FONT_LABEL_BOLD);
	        txtMaNV_CNNV.setForeground(COLOR_DANGER_RED);
	        txtMaNV_CNNV.setBounds(inputX_cnnv, startY_cnnv, 280, height_cnnv);
	        txtMaNV_CNNV.setEditable(false);
	        txtMaNV_CNNV.setBackground(COLOR_CARD_BACKGROUND); // Nền giống panel
	        txtMaNV_CNNV.setBorder(null); // Bỏ viền
	        pnlInfo_CNNV.add(txtMaNV_CNNV);
	        txtMaNV_CNNV.setColumns(10);
	
	        JLabel lblGioiTinh_CNNV = new JLabel("Giới tính:");
	        lblGioiTinh_CNNV.setFont(FONT_LABEL_BOLD);
	        lblGioiTinh_CNNV.setForeground(COLOR_TEXT_DARK);
	        lblGioiTinh_CNNV.setBounds(labelX2_cnnv + 50, startY_cnnv, 120, height_cnnv);
	        pnlInfo_CNNV.add(lblGioiTinh_CNNV);
	
	        JComboBox<String> cboGIoiTinh_CNNV = new JComboBox<String>();
	        cboGIoiTinh_CNNV.setModel(new DefaultComboBoxModel<String>(new String[] {"Nam", "Nữ"}));
	        cboGIoiTinh_CNNV.setFont(FONT_TEXT_FIELD);
	        cboGIoiTinh_CNNV.setBounds(inputX2_cnnv + 50, startY_cnnv, 150, height_cnnv);
	        pnlInfo_CNNV.add(cboGIoiTinh_CNNV);
	
	
	        JLabel lblTenNV_CNNV = new JLabel("Tên nhân viên:");
	        lblTenNV_CNNV.setFont(FONT_LABEL_BOLD);
	        lblTenNV_CNNV.setForeground(COLOR_TEXT_DARK);
	        lblTenNV_CNNV.setBounds(labelX_cnnv, startY_cnnv + height_cnnv + vGap_cnnv, 130, height_cnnv);
	        pnlInfo_CNNV.add(lblTenNV_CNNV);
	
	        txtTenNV_CNNV = new JTextField();
	        txtTenNV_CNNV.setFont(FONT_TEXT_FIELD);
	        txtTenNV_CNNV.setColumns(10);
	        txtTenNV_CNNV.setBounds(inputX_cnnv, startY_cnnv + height_cnnv + vGap_cnnv, 700, height_cnnv);
	        pnlInfo_CNNV.add(txtTenNV_CNNV);
	
	        JLabel lblSDT_CNNV = new JLabel("Số điện thoại:");
	        lblSDT_CNNV.setFont(FONT_LABEL_BOLD);
	        lblSDT_CNNV.setForeground(COLOR_TEXT_DARK);
	        lblSDT_CNNV.setBounds(labelX_cnnv, startY_cnnv + 2*(height_cnnv + vGap_cnnv), 130, height_cnnv);
	        pnlInfo_CNNV.add(lblSDT_CNNV);
	
	        txtSDT_CNNV = new JTextField();
	        txtSDT_CNNV.setFont(FONT_TEXT_FIELD);
	        txtSDT_CNNV.setColumns(10);
	        txtSDT_CNNV.setBounds(inputX_cnnv, startY_cnnv + 2*(height_cnnv + vGap_cnnv), 280, height_cnnv);
	        pnlInfo_CNNV.add(txtSDT_CNNV);
	
	        JLabel lblNgaySinh_CNNV = new JLabel("Ngày sinh:");
	        lblNgaySinh_CNNV.setFont(FONT_LABEL_BOLD);
	        lblNgaySinh_CNNV.setForeground(COLOR_TEXT_DARK);
	        lblNgaySinh_CNNV.setBounds(labelX2_cnnv + 50, startY_cnnv + 2*(height_cnnv + vGap_cnnv), 120, height_cnnv);
	        pnlInfo_CNNV.add(lblNgaySinh_CNNV);
	
	        JDateChooser dateNgaySinh_CNNV = new JDateChooser();
	        dateNgaySinh_CNNV.setFont(FONT_TEXT_FIELD);
	        dateNgaySinh_CNNV.setDateFormatString("dd/MM/yyyy");
	        dateNgaySinh_CNNV.setBounds(inputX2_cnnv + 50, startY_cnnv + 2*(height_cnnv + vGap_cnnv), 200, height_cnnv);
	        pnlInfo_CNNV.add(dateNgaySinh_CNNV);
	
	
	        JLabel lblChucVu_CNNV = new JLabel("Chức vụ:");
	        lblChucVu_CNNV.setFont(FONT_LABEL_BOLD);
	        lblChucVu_CNNV.setForeground(COLOR_TEXT_DARK);
	        lblChucVu_CNNV.setBounds(labelX_cnnv, startY_cnnv + 3*(height_cnnv + vGap_cnnv), 130, height_cnnv);
	        pnlInfo_CNNV.add(lblChucVu_CNNV);
	
	        JComboBox<ChucVu> cboChucVu_CNNV = new JComboBox<ChucVu>();
	        cboChucVu_CNNV.setFont(FONT_TEXT_FIELD);
	        cboChucVu_CNNV.setBounds(inputX_cnnv, startY_cnnv + 3*(height_cnnv + vGap_cnnv), 280, height_cnnv);
	        pnlInfo_CNNV.add(cboChucVu_CNNV);
	
	        JLabel lblTaiKhoan_CNNV = new JLabel("Tài khoản:");
	        lblTaiKhoan_CNNV.setFont(FONT_LABEL_BOLD);
	        lblTaiKhoan_CNNV.setForeground(COLOR_TEXT_DARK);
	        lblTaiKhoan_CNNV.setBounds(labelX2_cnnv + 50, startY_cnnv + 3*(height_cnnv + vGap_cnnv), 120, height_cnnv);
	        pnlInfo_CNNV.add(lblTaiKhoan_CNNV);
	
	        JComboBox<TaiKhoan> cboTaiKhoan_CNNV = new JComboBox<TaiKhoan>();
	        cboTaiKhoan_CNNV.setFont(FONT_TEXT_FIELD);
	        cboTaiKhoan_CNNV.setBounds(inputX2_cnnv + 50, startY_cnnv + 3*(height_cnnv + vGap_cnnv), 350, height_cnnv);
	        pnlInfo_CNNV.add(cboTaiKhoan_CNNV);
	
	        chucVu_DAO cv_dao_CNNV = new chucVu_DAO();
	        List<ChucVu> dsChucVu_CNNV = cv_dao_CNNV.getAllChucVu();
	        for (ChucVu cv : dsChucVu_CNNV) {
	        	cboChucVu_CNNV.addItem(cv);
	        }
	        taiKhoan_DAO tk_dao_CNNV = new taiKhoan_DAO();
	        List<TaiKhoan> dsTaiKhoan_CNNV = tk_dao_CNNV.getAllTaiKhoan();
	        for (TaiKhoan tk : dsTaiKhoan_CNNV) {
	            cboTaiKhoan_CNNV.addItem(tk);
	         }
	
	        // Panel địa chỉ
	        JPanel panelDiaChi_CNNV = new JPanel();
	        panelDiaChi_CNNV.setBackground(COLOR_CARD_BACKGROUND);
	        panelDiaChi_CNNV.setLayout(null);
	        panelDiaChi_CNNV.setBorder(BorderFactory.createTitledBorder(
	            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
	             "Địa chỉ", TitledBorder.LEADING, TitledBorder.TOP,
	             FONT_LABEL_BOLD, COLOR_TEXT_MUTED
	        ));
	        panelDiaChi_CNNV.setBounds(labelX_cnnv, startY_cnnv + 4*(height_cnnv + vGap_cnnv) + 10, 1080, 85);
	        pnlInfo_CNNV.add(panelDiaChi_CNNV);
	
	
	        JLabel lblTinh_CNNV = new JLabel("Tỉnh/TP:");
	        lblTinh_CNNV.setFont(FONT_LABEL_BOLD);
	        lblTinh_CNNV.setForeground(COLOR_TEXT_DARK);
	        lblTinh_CNNV.setBounds(20, 30, 80, height_cnnv);
	        panelDiaChi_CNNV.add(lblTinh_CNNV);
	
	        txtTinh_CNNV = new JTextField();
	        txtTinh_CNNV.setFont(FONT_TEXT_FIELD);
	        txtTinh_CNNV.setColumns(10);
	        txtTinh_CNNV.setBounds(110, 30, 380, height_cnnv);
	        panelDiaChi_CNNV.add(txtTinh_CNNV);
	
	
	        JLabel lblHuyen_CNNV = new JLabel("Quận/Huyện:");
	        lblHuyen_CNNV.setFont(FONT_LABEL_BOLD);
	        lblHuyen_CNNV.setForeground(COLOR_TEXT_DARK);
	        lblHuyen_CNNV.setBounds(530, 30, 120, height_cnnv);
	        panelDiaChi_CNNV.add(lblHuyen_CNNV);
	
	        txtHuyen_CNNV = new JTextField();
	        txtHuyen_CNNV.setFont(FONT_TEXT_FIELD);
	        txtHuyen_CNNV.setColumns(10);
	        txtHuyen_CNNV.setBounds(660, 30, 400, height_cnnv);
	        panelDiaChi_CNNV.add(txtHuyen_CNNV);
	
	
	        // Panel tìm kiếm
	        JPanel panelTK_CNNV = new JPanel();
	        panelTK_CNNV.setBackground(COLOR_CARD_BACKGROUND);
	        panelTK_CNNV.setLayout(null);
	        panelTK_CNNV.setBorder(BorderFactory.createTitledBorder(
	            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
	             "Tìm Kiếm Nhân Viên Cần Cập Nhật", TitledBorder.LEADING, TitledBorder.TOP,
	             FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
	        ));
	        panelTK_CNNV.setBounds(400, 410, 1164, 80);
	        panel_CapNhatNV.add(panelTK_CNNV);
	
	
	     // Label và text field tìm kiếm
	        JLabel lblTenNV_TK_CNNV = new JLabel("Tên nhân viên:");
	        lblTenNV_TK_CNNV.setFont(FONT_LABEL_BOLD);
	        lblTenNV_TK_CNNV.setForeground(COLOR_TEXT_DARK);
	        lblTenNV_TK_CNNV.setBounds(30, 30, 132, 30);
	        panelTK_CNNV.add(lblTenNV_TK_CNNV);

	        txtTenNV_TK_CNNV = new JTextField();
	        txtTenNV_TK_CNNV.setFont(FONT_TEXT_FIELD);
	        txtTenNV_TK_CNNV.setColumns(10);
	        txtTenNV_TK_CNNV.setBounds(170, 30, 350, 33);
	        panelTK_CNNV.add(txtTenNV_TK_CNNV);

	        // Combobox chức vụ (phải tạo trước khi dùng trong listener)
	        JComboBox<ChucVu> cboChucVu_TK_CNNV = new JComboBox<ChucVu>();
	        cboChucVu_TK_CNNV.setFont(FONT_TEXT_FIELD);
	        cboChucVu_TK_CNNV.setBounds(670, 30, 300, 33);

	        // Thêm item "Tất cả" (null) để bỏ lọc theo chức vụ khi cần
	        cboChucVu_TK_CNNV.addItem(null);
	        chucVu_DAO cv_dao_tk_CNNV = new chucVu_DAO();
	        List<ChucVu> dsChucVu_tk_CNNV = cv_dao_tk_CNNV.getAllChucVu();
	        for (ChucVu cv : dsChucVu_tk_CNNV) {
	            cboChucVu_TK_CNNV.addItem(cv);
	        }
	        panelTK_CNNV.add(cboChucVu_TK_CNNV);

	        // --- BỐC DỮ LIỆU VÀ LỌC: đặt AFTER khi cả txt và cbo đã được tạo ---
	        // Lưu ý: đảm bảo table_CNNV đã được khởi tạo ở trên. Nếu table_CNNV được khai báo cục bộ phía dưới,
	        // bạn phải di chuyển đoạn này xuống sau khi table_CNNV được tạo.

	        // DocumentListener cho ô nhập tên (lọc realtime)
	        txtTenNV_TK_CNNV.getDocument().addDocumentListener(new DocumentListener() {
	            private void locDuLieu() {
	                String keyword = txtTenNV_TK_CNNV.getText().trim().toLowerCase();

	                // Lấy giá trị chức vụ hiện tại (ChucVu hoặc null)
	                ChucVu selectedCV = (ChucVu) cboChucVu_TK_CNNV.getSelectedItem();
	                String tenChucVu = (selectedCV != null) ? selectedCV.getTenChucVu().toLowerCase() : "";

	                nhanVien_DAO dao = new nhanVien_DAO();
	                List<NhanVien> dsNV = dao.getAllNhanVien();

	                DefaultTableModel model = (DefaultTableModel) table_CNNV.getModel();
	                model.setRowCount(0);

	                for (NhanVien nv : dsNV) {
	                    boolean matchTen = keyword.isEmpty() || (nv.getTenNV() != null && nv.getTenNV().toLowerCase().contains(keyword));
	                    boolean matchCV = tenChucVu.isEmpty() || (nv.getChucVu() != null && nv.getChucVu().getTenChucVu() != null
	                            && nv.getChucVu().getTenChucVu().toLowerCase().equalsIgnoreCase(tenChucVu));

	                    if (matchTen && matchCV) {
	                        model.addRow(new Object[]{
	                            nv.getMaNV(),
	                            nv.getTenNV(),
	                            nv.getNgaySinh(),
	                            nv.getGioiTinh(),
	                            nv.getChucVu() != null ? nv.getChucVu().getTenChucVu() : "",
	                            nv.getSoDienThoai(),
	                            nv.getDiaChi(),
	                            nv.getAnh(),
	                            nv.getTaiKhoan() != null ? nv.getTaiKhoan().getTenTK() : ""
	                        });
	                    }
	                }
	            }

	            @Override public void insertUpdate(DocumentEvent e) { locDuLieu(); }
	            @Override public void removeUpdate(DocumentEvent e) { locDuLieu(); }
	            @Override public void changedUpdate(DocumentEvent e) { locDuLieu(); }
	        });

	        // ActionListener cho combobox chức vụ (khi chọn sẽ lọc)
	        cboChucVu_TK_CNNV.addActionListener(e -> {
	            // Reuse same logic as above (để tránh trùng code bạn có thể tách thành private method nếu các biến là field)
	            String keyword = txtTenNV_TK_CNNV.getText().trim().toLowerCase();
	            ChucVu selectedCV = (ChucVu) cboChucVu_TK_CNNV.getSelectedItem();
	            String tenChucVu = (selectedCV != null) ? selectedCV.getTenChucVu().toLowerCase() : "";

	            nhanVien_DAO dao = new nhanVien_DAO();
	            List<NhanVien> dsNV = dao.getAllNhanVien();

	            DefaultTableModel model = (DefaultTableModel) table_CNNV.getModel();
	            model.setRowCount(0);

	            for (NhanVien nv : dsNV) {
	                boolean matchTen = keyword.isEmpty() || (nv.getTenNV() != null && nv.getTenNV().toLowerCase().contains(keyword));
	                boolean matchCV = tenChucVu.isEmpty() || (nv.getChucVu() != null && nv.getChucVu().getTenChucVu() != null
	                        && nv.getChucVu().getTenChucVu().toLowerCase().equalsIgnoreCase(tenChucVu));

	                if (matchTen && matchCV) {
	                    model.addRow(new Object[]{
	                        nv.getMaNV(),
	                        nv.getTenNV(),
	                        nv.getNgaySinh(),
	                        nv.getGioiTinh(),
	                        nv.getChucVu() != null ? nv.getChucVu().getTenChucVu() : "",
	                        nv.getSoDienThoai(),
	                        nv.getDiaChi(),
	                        nv.getAnh(),
	                        nv.getTaiKhoan() != null ? nv.getTaiKhoan().getTenTK() : ""
	                    });
	                }
	            }
	        });

	        panelTK_CNNV.add(cboChucVu_TK_CNNV);
	
	
	        // Các nút chức năng dưới form (Giữ nguyên code icon)
	        JButton btnLamMoi_CNNV = new JButton("Làm mới Form");
	        btnLamMoi_CNNV.setFont(FONT_BUTTON_STANDARD);
	        styleButton(btnLamMoi_CNNV, COLOR_TEXT_MUTED);
	        btnLamMoi_CNNV.setBounds(860, 500, 152, 40);
	        btnLamMoi_CNNV.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Bỏ chọn dòng trong bảng
	                table_CNNV.clearSelection();
	
	                // Xóa toàn bộ dữ liệu trong form nhập
	                txtMaNV_CNNV.setText("");
	                txtTenNV_CNNV.setText("");
	                txtSDT_CNNV.setText("");
	                txtTinh_CNNV.setText("");
	                txtHuyen_CNNV.setText("");
	                cboGIoiTinh_CNNV.setSelectedIndex(0); // Mặc định chọn "Nam"
	                cboChucVu_CNNV.setSelectedIndex(0);
	                cboTaiKhoan_CNNV.setSelectedIndex(0);
	                dateNgaySinh_CNNV.setDate(null);
	
	                // Reset ảnh nhân viên
	                duongDanAnh_CNNV = null;
	                lblAnhNV_CNNV.setText("Chưa có ảnh");
	                lblAnhNV_CNNV.setIcon(null);
	            }
	        });
	
	        // --- Code ActionListener và Icon của Đại Ca ---
	        java.net.URL imgLamMoi_CNNV = getClass().getResource("/icon-refresh.png");
	        if (imgLamMoi_CNNV != null) {
	            ImageIcon originalIcon = new ImageIcon(imgLamMoi_CNNV);
	            Image img = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
	            ImageIcon scaledIcon = new ImageIcon(img);
	            btnLamMoi_CNNV.setIcon(scaledIcon);
	            btnLamMoi_CNNV.setHorizontalAlignment(SwingConstants.LEFT);
	            btnLamMoi_CNNV.setIconTextGap(10);
	        } else {
	            System.err.println("Lỗi: Không tìm thấy ảnh tại /icon-refresh.png");
	        }
	        // --- Hết code ActionListener và Icon ---
	        panel_CapNhatNV.add(btnLamMoi_CNNV);
	
	        JButton btnXoa_CNNV = new JButton("Xóa");
	        btnXoa_CNNV.setFont(FONT_BUTTON_STANDARD);
	        styleButton(btnXoa_CNNV, COLOR_DANGER_RED); // Màu đỏ
	        btnXoa_CNNV.setBounds(1030, 500, 152, 40);
	        btnXoa_CNNV.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int selectedRow = table_CNNV.getSelectedRow();
	                if (selectedRow == -1) {
	                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	                    return;
	                }

	                String maNV = table_CNNV.getValueAt(selectedRow, 0).toString();
	                int confirm = JOptionPane.showConfirmDialog(null,
	                        "Bạn có chắc muốn xóa nhân viên có mã: " + maNV + " không?",
	                        "Xác nhận xóa",
	                        JOptionPane.YES_NO_OPTION);

	                if (confirm == JOptionPane.YES_OPTION) {
	                    nhanVien_DAO nv_dao = new nhanVien_DAO();
	                    boolean result = nv_dao.deleteNhanVien(maNV);
	                    if (result) {
	                        JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!");

	                        // ======= LÀM MỚI BẢNG =======
	                        loadDataToTableNV(table_CNNV);

	                        // ======= RESET FORM =======
	                        txtMaNV_CNNV.setText("");
	                        txtTenNV_CNNV.setText("");
	                        txtSDT_CNNV.setText("");
	                        dateNgaySinh_CNNV.setDate(null);
	                        cboGIoiTinh_CNNV.setSelectedIndex(0);
	                        cboChucVu_CNNV.setSelectedIndex(0);
	                        cboTaiKhoan_CNNV.setSelectedIndex(0);
	                        txtTinh_CNNV.setText("");
	                        txtHuyen_CNNV.setText("");

	                        // ======= XÓA HÌNH ẢNH =======
	                        lblAnhNV_CNNV.setIcon(null);
	                        lblAnhNV_CNNV.setText("Không có hình ảnh");

	                    } else {
	                        JOptionPane.showMessageDialog(null, 
	                            "Không thể xóa nhân viên! Kiểm tra lại dữ liệu hoặc ràng buộc khóa ngoại.", 
	                            "Lỗi", 
	                            JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	            }
	        });

	        panel_CapNhatNV.add(btnXoa_CNNV);
	
	        JButton btnKhoiPhuc_CNNV = new JButton("Khôi phục Form");
	        btnKhoiPhuc_CNNV.setFont(FONT_BUTTON_STANDARD);
	        styleButton(btnKhoiPhuc_CNNV, COLOR_TEXT_MUTED);
	        btnKhoiPhuc_CNNV.setBounds(1200, 500, 171, 40);
	        btnKhoiPhuc_CNNV.addActionListener(e -> {
	            int selectedRow = table_CNNV.getSelectedRow();
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên trong bảng để khôi phục dữ liệu!");
	                return;
	            }

	            try {
	                String maNV = table_CNNV.getValueAt(selectedRow, 0).toString(); // cột 0 là mã NV
	                NhanVien nv = nv_dao.getNhanVienTheoMa(maNV);

	                if (nv == null) {
	                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên trong cơ sở dữ liệu!");
	                    return;
	                }

	                // --- Gán dữ liệu lên form ---
	                txtMaNV_CNNV.setText(nv.getMaNV() != null ? nv.getMaNV() : "");
	                txtTenNV_CNNV.setText(nv.getTenNV() != null ? nv.getTenNV() : "");
	                txtSDT_CNNV.setText(nv.getSoDienThoai() != null ? nv.getSoDienThoai() : "");

	                // Giới tính (ComboBox<String>)
	                if (nv.getGioiTinh() != null) {
	                    cboGIoiTinh_CNNV.setSelectedItem(nv.getGioiTinh());
	                } else {
	                    cboGIoiTinh_CNNV.setSelectedIndex(0); // mặc định "Nam"
	                }

	                // Ngày sinh: NhanVien.getNgaySinh() trong DAO của bạn trả LocalDate
	                if (nv.getNgaySinh() != null) {
	                    java.time.LocalDate ld = nv.getNgaySinh();
	                    java.util.Date utilDate = java.util.Date.from(ld.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
	                    dateNgaySinh_CNNV.setDate(utilDate);
	                } else {
	                    dateNgaySinh_CNNV.setDate(null);
	                }

	                // Chức vụ: cboChucVu_CNNV chứa các object ChucVu -> chọn theo ma hoặc tên
	                for (int i = 0; i < cboChucVu_CNNV.getItemCount(); i++) {
	                    ChucVu item = cboChucVu_CNNV.getItemAt(i);
	                    if (item != null && nv.getChucVu() != null) {
	                        // so sánh theo mã chức vụ (an toàn hơn)
	                        if (item.getMaChucVu().equals(nv.getChucVu().getMaChucVu())) {
	                            cboChucVu_CNNV.setSelectedIndex(i);
	                            break;
	                        }
	                    }
	                }

	                // Tài khoản: cboTaiKhoan_CNNV chứa TaiKhoan objects -> chọn theo maTK hoặc tenTK
	                for (int i = 0; i < cboTaiKhoan_CNNV.getItemCount(); i++) {
	                    TaiKhoan item = cboTaiKhoan_CNNV.getItemAt(i);
	                    if (item != null && nv.getTaiKhoan() != null) {
	                        if (item.getMaTK().equals(nv.getTaiKhoan().getMaTK())) {
	                            cboTaiKhoan_CNNV.setSelectedIndex(i);
	                            break;
	                        }
	                    }
	                }

	                // Địa chỉ: tách tỉnh, huyện như bạn làm ở mouseClicked
	                String diaChi = nv.getDiaChi();
	                if (diaChi != null && diaChi.contains(",")) {
	                    String[] parts = diaChi.split(",", 2);
	                    txtTinh_CNNV.setText(parts[0].trim());
	                    txtHuyen_CNNV.setText(parts[1].trim());
	                } else if (diaChi != null) {
	                    txtTinh_CNNV.setText(diaChi.trim());
	                    txtHuyen_CNNV.setText("");
	                } else {
	                    txtTinh_CNNV.setText("");
	                    txtHuyen_CNNV.setText("");
	                }

	                // Ảnh: dùng trường anh trong entity (nv.getAnh())
	                duongDanAnh_CNNV = nv.getAnh(); // cập nhật biến lưu đường dẫn ảnh
	                if (duongDanAnh_CNNV != null && !duongDanAnh_CNNV.isEmpty()) {
	                    try {
	                        ImageIcon icon_kp = new ImageIcon(duongDanAnh_CNNV);
	                        if (icon_kp.getImageLoadStatus() == MediaTracker.COMPLETE) {
	                            Image img = icon_kp.getImage().getScaledInstance(
	                                lblAnhNV_CNNV.getWidth(), lblAnhNV_CNNV.getHeight(), Image.SCALE_SMOOTH
	                            );
	                            lblAnhNV_CNNV.setText(null);
	                            lblAnhNV_CNNV.setIcon(new ImageIcon(img));
	                        } else {
	                            throw new Exception("Ảnh không hợp lệ");
	                        }
	                    } catch (Exception ex) {
	                        lblAnhNV_CNNV.setText("Lỗi ảnh");
	                        lblAnhNV_CNNV.setIcon(null);
	                    }
	                } else {
	                    lblAnhNV_CNNV.setText("Chưa có ảnh");
	                    lblAnhNV_CNNV.setIcon(null);
	                }

	                JOptionPane.showMessageDialog(null, "Đã khôi phục dữ liệu gốc cho nhân viên " + nv.getTenNV());
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Lỗi khi khôi phục: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        });


	        panel_CapNhatNV.add(btnKhoiPhuc_CNNV);
	
	        JButton btnCapNhat_CNNV = new JButton("Cập nhật");
	        btnCapNhat_CNNV.setFont(FONT_BUTTON_STANDARD);
	        styleButton(btnCapNhat_CNNV, COLOR_SUCCESS_GREEN);
	        btnCapNhat_CNNV.setBounds(1390, 500, 150, 40);
	        panel_CapNhatNV.add(btnCapNhat_CNNV);
	        btnCapNhat_CNNV.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    int selectedRow = table_CNNV.getSelectedRow();
	                    if (selectedRow == -1) {
	                        JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần cập nhật!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	                        return;
	                    }

	                    // ===== LẤY MÃ NHÂN VIÊN TỪ BẢNG =====
	                    String maNV = table_CNNV.getValueAt(selectedRow, 0).toString();

	                    // ===== LẤY DỮ LIỆU TỪ FORM =====
	                    String tenNV = txtTenNV_CNNV.getText().trim();
	                    String sdt = txtSDT_CNNV.getText().trim();
	                    String gioiTinh = cboGIoiTinh_CNNV.getSelectedItem().toString();
	                    java.util.Date ngaySinh = dateNgaySinh_CNNV.getDate();

	                    ChucVu selectedCV = (ChucVu) cboChucVu_CNNV.getSelectedItem();
	                    String maChucVu = selectedCV != null ? selectedCV.getMaChucVu() : "";

	                    TaiKhoan selectedTK = (TaiKhoan) cboTaiKhoan_CNNV.getSelectedItem();
	                    String maTK = selectedTK != null ? selectedTK.getMaTK() : "";

	                    String tinh = txtTinh_CNNV.getText().trim();
	                    String huyen = txtHuyen_CNNV.getText().trim();
	                    String anh = duongDanAnh_CNNV;

	                    if (tenNV.isEmpty() || sdt.isEmpty() || ngaySinh == null || maChucVu.isEmpty() || maTK.isEmpty()) {
	                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
	                        return;
	                    }

	                    // ===== CHUYỂN NGÀY =====
	                    LocalDate ngaySinhLocal = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	                    // ===== TẠO ĐỐI TƯỢNG NHÂN VIÊN CẬP NHẬT =====
	                    NhanVien nv = new NhanVien();
	                    nv.setMaNV(maNV);
	                    nv.setTenNV(tenNV);
	                    nv.setSoDienThoai(sdt);
	                    nv.setGioiTinh(gioiTinh);
	                    nv.setNgaySinh(ngaySinhLocal);

	                    ChucVu cv = new ChucVu();
	                    cv.setMaChucVu(maChucVu);
	                    nv.setChucVu(cv);

	                    TaiKhoan tk = new TaiKhoan();
	                    tk.setMaTK(maTK);
	                    nv.setTaiKhoan(tk);

	                    nv.setDiaChi(tinh + ", " + huyen);
	                    nv.setAnh(anh);

	                    // ===== CẬP NHẬT DATABASE =====
	                    nhanVien_DAO nvDAO = new nhanVien_DAO();
	                    boolean result = nvDAO.updateNhanVien(nv);

	                    if (result) {
	                        JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công!");
	                        loadDataToTableNV(table_CNNV); // refresh lại bảng
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Không thể cập nhật nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    }

	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật: " + ex.getMessage());
	                }
	            }
	        });

	
	
	        // ScrollPane và Bảng
	        JScrollPane scrollPane_CNNV = new JScrollPane();
	        scrollPane_CNNV.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
	        scrollPane_CNNV.setBounds(10, 550, 1564, 378); // Điều chỉnh
	        panel_CapNhatNV.add(scrollPane_CNNV);
	
	        // Style bảng table_CNNV
	        table_CNNV = new JTable() {
	             @Override
	            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
	                Component c = super.prepareRenderer(renderer, row, column);
	                if (!isRowSelected(row)) {
	                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
	                    c.setForeground(this.getForeground()); // Dùng this
	                } else {
	                    c.setBackground(COLOR_PRIMARY_BLUE);
	                    // Không set foreground trắng
	                }
	                return c;
	            }
	        };
	        applyCommonTableStyling(table_CNNV);
	        table_CNNV.setModel(new DefaultTableModel(
	        	new Object[][] {},
	        	new String[] {
	        		"Mã NV", "Tên NV", "Ngày sinh", "Giới tính", "Chức vụ", "SĐT", "Địa chỉ", "Ảnh", "Tài khoản"
	        	}
	        ));
	        // Code MouseListener giữ nguyên
	         table_CNNV.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                 int selectedRow = table_CNNV.getSelectedRow();
	                if (selectedRow != -1) {
	                     String maNV = table_CNNV.getValueAt(selectedRow, 0).toString();
	                    String tenNV = table_CNNV.getValueAt(selectedRow, 1).toString();
	                    Object ngaySinhObj = table_CNNV.getValueAt(selectedRow, 2);
	                    String gioiTinh = table_CNNV.getValueAt(selectedRow, 3).toString();
	                    String chucVuStr = table_CNNV.getValueAt(selectedRow, 4).toString();
	                    String sdt = table_CNNV.getValueAt(selectedRow, 5).toString();
	                    String diaChi = table_CNNV.getValueAt(selectedRow, 6) != null ? table_CNNV.getValueAt(selectedRow, 6).toString() : "";
	                    String duongDanAnh = table_CNNV.getValueAt(selectedRow, 7) != null ? table_CNNV.getValueAt(selectedRow, 7).toString() : null;
	                    String taiKhoanStr = table_CNNV.getValueAt(selectedRow, 8).toString();
	
	                    txtMaNV_CNNV.setText(maNV);
	                    txtTenNV_CNNV.setText(tenNV);
	                    txtSDT_CNNV.setText(sdt);
	                    cboGIoiTinh_CNNV.setSelectedItem(gioiTinh);
	
	                    if (ngaySinhObj != null) {
	                         try {
	                            if (ngaySinhObj instanceof String) {
	                                 java.util.Date ngaySinh = new java.text.SimpleDateFormat("yyyy-MM-dd").parse((String)ngaySinhObj);
	                                dateNgaySinh_CNNV.setDate(ngaySinh);
	                            } else if (ngaySinhObj instanceof LocalDate) {
	                                java.util.Date ngaySinh = java.util.Date.from(((LocalDate)ngaySinhObj).atStartOfDay(ZoneId.systemDefault()).toInstant());
	                                dateNgaySinh_CNNV.setDate(ngaySinh);
	                            } else if (ngaySinhObj instanceof java.sql.Date) {
	                                dateNgaySinh_CNNV.setDate((java.sql.Date)ngaySinhObj);
	                            } else if (ngaySinhObj instanceof java.util.Date) {
	                                dateNgaySinh_CNNV.setDate((java.util.Date)ngaySinhObj);
	                            } else { dateNgaySinh_CNNV.setDate(null); }
	                        } catch (Exception ex) {
	                            dateNgaySinh_CNNV.setDate(null);
	                        }
	                    } else { dateNgaySinh_CNNV.setDate(null); }
	
	                    for (int i = 0; i < cboChucVu_CNNV.getItemCount(); i++) {
	                         ChucVu cvItem = cboChucVu_CNNV.getItemAt(i);
	                        if (cvItem != null && cvItem.getTenChucVu().equals(chucVuStr)) {
	                            cboChucVu_CNNV.setSelectedIndex(i);
	                            break;
	                        }
	                    }
	
	                    for (int i = 0; i < cboTaiKhoan_CNNV.getItemCount(); i++) {
	                        TaiKhoan tkItem = cboTaiKhoan_CNNV.getItemAt(i);
	                        if (tkItem != null && tkItem.getTenTK().equals(taiKhoanStr)) {
	                            cboTaiKhoan_CNNV.setSelectedIndex(i);
	                            break;
	                        }
	                    }
	
                    if (diaChi != null && diaChi.contains(",")) {
                        String[] parts = diaChi.split(",", 2);
                        txtTinh_CNNV.setText(parts[0].trim());
                        txtHuyen_CNNV.setText(parts[1].trim());
                    } else if (diaChi != null) {
                        txtTinh_CNNV.setText(diaChi.trim());
                        txtHuyen_CNNV.setText("");
                    } else {
                        txtTinh_CNNV.setText("");
                         txtHuyen_CNNV.setText("");
                    }

                    duongDanAnh_CNNV = duongDanAnh;

                    if (duongDanAnh != null && !duongDanAnh.isEmpty()) {
                        try {
                            File imageFile;

                            // ⚙️ Nếu đường dẫn bắt đầu bằng "/" (như /male-1.jpg) thì load từ thư mục gốc "img/"
                            if (duongDanAnh.startsWith("/")) {
                                String fileName = duongDanAnh.substring(1); // bỏ dấu '/'
                                imageFile = new File("img/" + fileName);
                            }
                            // ⚙️ Nếu có dấu ":" thì là đường dẫn tuyệt đối
                            else if (duongDanAnh.contains(":")) {
                                imageFile = new File(duongDanAnh);
                            }
                            // ⚙️ Còn lại thì load từ thư mục img/ theo tên file
                            else {
                                imageFile = new File("img/" + duongDanAnh);
                            }

                            if (imageFile.exists()) {
                                ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
                                Image img = icon.getImage().getScaledInstance(
                                        lblAnhNV_CNNV.getWidth(),
                                        lblAnhNV_CNNV.getHeight(),
                                        Image.SCALE_SMOOTH
                                );
                                lblAnhNV_CNNV.setText(null);
                                lblAnhNV_CNNV.setIcon(new ImageIcon(img));
                            } else {
                                System.out.println("❌ Không tìm thấy ảnh: " + imageFile.getAbsolutePath());
                                lblAnhNV_CNNV.setText("Không tìm thấy ảnh");
                                lblAnhNV_CNNV.setIcon(null);
                            }
                        } catch (Exception ex) {
                            lblAnhNV_CNNV.setText("Lỗi ảnh");
                            lblAnhNV_CNNV.setIcon(null);
                            ex.printStackTrace();
                        }
                    } else {
                        lblAnhNV_CNNV.setText("Chưa có ảnh");
                        lblAnhNV_CNNV.setIcon(null);
                    }


                }
            }
        });
        scrollPane_CNNV.setViewportView(table_CNNV);


        // ===== KẾT THÚC KHỐI CODE CẬP NHẬT NHÂN VIÊN =====
        
     // ===== BẮT ĐẦU KHỐI CODE THÊM NHÀ CUNG CẤP ĐÃ SỬA =====
        JPanel panel_themNCC = new JPanel();
        maincontent.add(panel_themNCC, "themNCC");
        panel_themNCC.setLayout(null);
        panel_themNCC.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        JLabel lblTitleThemNCC = new JLabel("THÊM NHÀ CUNG CẤP MỚI"); // Tiêu đề
        lblTitleThemNCC.setFont(FONT_TITLE_MAIN);
        lblTitleThemNCC.setForeground(COLOR_PRIMARY_BLUE);
        lblTitleThemNCC.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitleThemNCC.setBounds(0, 11, 1584, 45); // Vị trí tiêu đề
        panel_themNCC.add(lblTitleThemNCC);

        // Panel ảnh (Tương tự Thêm Nhân Viên)
        JPanel panelAnhNCC_TNCC = new JPanel();
        panelAnhNCC_TNCC.setLayout(new BorderLayout(0,0)); // Dùng BorderLayout
        panelAnhNCC_TNCC.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền nhạt
        panelAnhNCC_TNCC.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền xám nhạt
        panelAnhNCC_TNCC.setBounds(40, 80, 383, 400); // Điều chỉnh vị trí/kích thước
        panel_themNCC.add(panelAnhNCC_TNCC);
        // Thêm label placeholder cho ảnh vào đây nếu muốn
        JLabel lblAnhNCC_TNCC = new JLabel("Chưa có ảnh NCC", SwingConstants.CENTER);
        lblAnhNCC_TNCC.setFont(FONT_DETAIL_ITALIC);
        lblAnhNCC_TNCC.setForeground(COLOR_TEXT_MUTED);
        panelAnhNCC_TNCC.add(lblAnhNCC_TNCC, BorderLayout.CENTER);


        JButton btnChonAnh_TNCC = new JButton("Chọn ảnh");
        btnChonAnh_TNCC.setFont(FONT_BUTTON_STANDARD); // Font nút
        styleButton(btnChonAnh_TNCC, COLOR_TEXT_MUTED); // Style nút phụ
        btnChonAnh_TNCC.setBounds(160, 490, 143, 40); // Điều chỉnh vị trí
        panel_themNCC.add(btnChonAnh_TNCC);
        // Đại Ca có thể thêm ActionListener cho nút này sau

        // Panel thông tin NCC
        JPanel panel_ThongTinNCC = new JPanel();
        panel_ThongTinNCC.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
        panel_ThongTinNCC.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Thông tin nhà cung cấp", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        )); // Viền và tiêu đề
        panel_ThongTinNCC.setBounds(450, 80, 1114, 380); // Điều chỉnh vị trí/kích thước
        panel_themNCC.add(panel_ThongTinNCC);
        panel_ThongTinNCC.setLayout(null);

        // Định nghĩa lại vị trí và khoảng cách
        int labelX_tncc = 40;
        int inputX_tncc = 210;
        int labelX2_tncc = 580;
        int inputX2_tncc = 710;
        int startY_tncc = 40;
        int height_tncc = 33;
        int vGap_tncc = 15;

        JLabel lblMaNCC_TNCC = new JLabel("Mã nhà cung cấp:");
        lblMaNCC_TNCC.setFont(FONT_LABEL_BOLD); // Font label
        lblMaNCC_TNCC.setForeground(COLOR_TEXT_DARK); // Màu chữ
        lblMaNCC_TNCC.setBounds(labelX_tncc, startY_tncc, 160, height_tncc);
        panel_ThongTinNCC.add(lblMaNCC_TNCC);

        txtMaNCC_TNCC = new JTextField();
        txtMaNCC_TNCC.setFont(FONT_LABEL_BOLD); // Font đậm
        txtMaNCC_TNCC.setForeground(COLOR_DANGER_RED); // Màu đỏ
        txtMaNCC_TNCC.setEditable(false); // Mã thường tự sinh
        txtMaNCC_TNCC.setColumns(10);
        txtMaNCC_TNCC.setBounds(inputX_tncc, startY_tncc, 300, height_tncc); // Điều chỉnh width
        txtMaNCC_TNCC.setBorder(null); // Bỏ viền
        txtMaNCC_TNCC.setBackground(COLOR_CARD_BACKGROUND); // Nền giống panel
        // Cần code để tạo mã NCC mới ở đây
        // txtMaNCC_TNCC.setText(maMoiNCC);
        panel_ThongTinNCC.add(txtMaNCC_TNCC);

        JLabel lblTenNCC_TNCC = new JLabel("Tên nhà cung cấp:");
        lblTenNCC_TNCC.setFont(FONT_LABEL_BOLD);
        lblTenNCC_TNCC.setForeground(COLOR_TEXT_DARK);
        lblTenNCC_TNCC.setBounds(labelX_tncc, startY_tncc + height_tncc + vGap_tncc, 179, height_tncc);
        panel_ThongTinNCC.add(lblTenNCC_TNCC);

        txtTenNCC_TNCC = new JTextField();
        txtTenNCC_TNCC.setFont(FONT_TEXT_FIELD);
        txtTenNCC_TNCC.setColumns(10);
        txtTenNCC_TNCC.setBounds(inputX_tncc, startY_tncc + height_tncc + vGap_tncc, 800, height_tncc); // Kéo dài
        panel_ThongTinNCC.add(txtTenNCC_TNCC);

        JLabel lblSDTNCC_TNCC = new JLabel("Số điện thoại:");
        lblSDTNCC_TNCC.setFont(FONT_LABEL_BOLD);
        lblSDTNCC_TNCC.setForeground(COLOR_TEXT_DARK);
        lblSDTNCC_TNCC.setBounds(labelX_tncc, startY_tncc + 2*(height_tncc + vGap_tncc), 160, height_tncc);
        panel_ThongTinNCC.add(lblSDTNCC_TNCC);

        txtSDT_TNCC = new JTextField();
        txtSDT_TNCC.setFont(FONT_TEXT_FIELD);
        txtSDT_TNCC.setColumns(10);
        txtSDT_TNCC.setBounds(inputX_tncc, startY_tncc + 2*(height_tncc + vGap_tncc), 300, height_tncc);
        panel_ThongTinNCC.add(txtSDT_TNCC);

        JLabel lblEmail_TNCC = new JLabel("Email:");
        lblEmail_TNCC.setFont(FONT_LABEL_BOLD);
        lblEmail_TNCC.setForeground(COLOR_TEXT_DARK);
        lblEmail_TNCC.setBounds(labelX2_tncc, startY_tncc + 2*(height_tncc + vGap_tncc), 120, height_tncc); // Điều chỉnh
        panel_ThongTinNCC.add(lblEmail_TNCC);

        txtEmail_TNCC = new JTextField();
        txtEmail_TNCC.setFont(FONT_TEXT_FIELD);
        txtEmail_TNCC.setColumns(10);
        txtEmail_TNCC.setBounds(inputX2_tncc, startY_tncc + 2*(height_tncc + vGap_tncc), 300, height_tncc); // Điều chỉnh
        panel_ThongTinNCC.add(txtEmail_TNCC);


        JLabel lblTrangThai_TNCC = new JLabel("Trạng thái:");
        lblTrangThai_TNCC.setFont(FONT_LABEL_BOLD);
        lblTrangThai_TNCC.setForeground(COLOR_TEXT_DARK);
        lblTrangThai_TNCC.setBounds(labelX2_tncc, startY_tncc, 120, height_tncc); // Điều chỉnh
        panel_ThongTinNCC.add(lblTrangThai_TNCC);

        // Nên dùng JComboBox cho Trạng thái
        JComboBox<String> cboTrangThai_TNCC = new JComboBox<String>();
        cboTrangThai_TNCC.setFont(FONT_TEXT_FIELD);
        cboTrangThai_TNCC.setModel(new DefaultComboBoxModel<>(new String[] {"Đang hợp tác", "Ngừng hợp tác"})); // Ví dụ
        cboTrangThai_TNCC.setBounds(inputX2_tncc, startY_tncc, 300, height_tncc); // Điều chỉnh
        panel_ThongTinNCC.add(cboTrangThai_TNCC);
        // Bỏ JTextField txtTrangThai_TNCC cũ


        JLabel lblGhiChu_TNCC = new JLabel("Ghi chú:");
        lblGhiChu_TNCC.setFont(FONT_LABEL_BOLD);
        lblGhiChu_TNCC.setForeground(COLOR_TEXT_DARK);
        lblGhiChu_TNCC.setBounds(labelX_tncc, startY_tncc + 3*(height_tncc + vGap_tncc), 160, height_tncc);
        panel_ThongTinNCC.add(lblGhiChu_TNCC);

        // Dùng JScrollPane cho Ghi chú
        JScrollPane scrollPaneGhiChu = new JScrollPane();
        scrollPaneGhiChu.setBounds(inputX_tncc, startY_tncc + 3*(height_tncc + vGap_tncc), 800, 60); // Điều chỉnh
        panel_ThongTinNCC.add(scrollPaneGhiChu);

        JTextArea txtGhiChu_TNCC = new JTextArea(); // Đổi thành JTextArea
        txtGhiChu_TNCC.setFont(FONT_TEXT_FIELD);
        txtGhiChu_TNCC.setLineWrap(true); // Tự xuống dòng
        txtGhiChu_TNCC.setWrapStyleWord(true); // Xuống dòng theo từ
        scrollPaneGhiChu.setViewportView(txtGhiChu_TNCC);
        // Bỏ JTextField txtGhiChu_TNCC cũ


        // Panel địa chỉ
        JPanel panelDiaChi_TNCC = new JPanel();
        panelDiaChi_TNCC.setBackground(COLOR_CARD_BACKGROUND);
        panelDiaChi_TNCC.setLayout(null);
        panelDiaChi_TNCC.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
             "Địa chỉ", TitledBorder.LEADING, TitledBorder.TOP,
             FONT_LABEL_BOLD, COLOR_TEXT_MUTED
        ));
        panelDiaChi_TNCC.setBounds(labelX_tncc, startY_tncc + 4*(height_tncc + vGap_tncc) + 30, 990, 85); // Điều chỉnh
        panel_ThongTinNCC.add(panelDiaChi_TNCC);

        JLabel lblTinh_TNCC = new JLabel("Tỉnh/TP:");
        lblTinh_TNCC.setFont(FONT_LABEL_BOLD);
        lblTinh_TNCC.setForeground(COLOR_TEXT_DARK);
        lblTinh_TNCC.setBounds(20, 30, 80, height_tncc);
        panelDiaChi_TNCC.add(lblTinh_TNCC);

        txtTinh_TNCC = new JTextField();
        txtTinh_TNCC.setFont(FONT_TEXT_FIELD);
        txtTinh_TNCC.setColumns(10);
        txtTinh_TNCC.setBounds(110, 30, 360, height_tncc);
        panelDiaChi_TNCC.add(txtTinh_TNCC);

        JLabel lblHuyen_TNCC = new JLabel("Quận/Huyện:");
        lblHuyen_TNCC.setFont(FONT_LABEL_BOLD);
        lblHuyen_TNCC.setForeground(COLOR_TEXT_DARK);
        lblHuyen_TNCC.setBounds(500, 30, 120, height_tncc);
        panelDiaChi_TNCC.add(lblHuyen_TNCC);

        txtHuyen_TNCC = new JTextField();
        txtHuyen_TNCC.setFont(FONT_TEXT_FIELD);
        txtHuyen_TNCC.setColumns(10);
        txtHuyen_TNCC.setBounds(630, 30, 340, height_tncc);
        panelDiaChi_TNCC.add(txtHuyen_TNCC);

        // Nút chức năng (Thêm, Làm mới...)
        JButton btnLamMoi_TNCC = new JButton("Làm mới"); // Tạo nút mới
        btnLamMoi_TNCC.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnLamMoi_TNCC, COLOR_TEXT_MUTED);
        btnLamMoi_TNCC.setBounds(1250, 475, 140, 40); // Điều chỉnh
        panel_themNCC.add(btnLamMoi_TNCC);

        JButton btnThem_TNCC = new JButton("Thêm NCC"); // Tạo nút mới
        btnThem_TNCC.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnThem_TNCC, COLOR_SUCCESS_GREEN);
        btnThem_TNCC.setBounds(1410, 475, 140, 40); // Điều chỉnh
        panel_themNCC.add(btnThem_TNCC);


        // ScrollPane và Bảng hiển thị NCC đã thêm
        JScrollPane scrollPane_ThemNCC = new JScrollPane();
        scrollPane_ThemNCC.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scrollPane_ThemNCC.setBounds(10, 530, 1564, 398); // Điều chỉnh vị trí/kích thước
        panel_themNCC.add(scrollPane_ThemNCC);

        // Style bảng table_ThemNCC
        table_ThemNCC = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground());
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
        };
        applyCommonTableStyling(table_ThemNCC);
        table_ThemNCC.setModel(new DefaultTableModel(
        	new Object[][] {}, // Bỏ dòng null
        	new String[] {
        		"Mã NCC", "Tên NCC", "Số điện thoại", "Email", "Trạng thái", "Tỉnh/TP", "Quận/Huyện", "Ghi chú"
        	}
        ));
        scrollPane_ThemNCC.setViewportView(table_ThemNCC);

        // ===== KẾT THÚC KHỐI CODE THÊM NHÀ CUNG CẤP =====
        
     // ===== BẮT ĐẦU KHỐI CODE CẬP NHẬT NHÀ CUNG CẤP ĐÃ SỬA =====
        JPanel panel_capNhatNCC = new JPanel();
        maincontent.add(panel_capNhatNCC, "capNhatNCC");
        panel_capNhatNCC.setLayout(null);
        panel_capNhatNCC.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        JLabel lblTitle_CN_NCC = new JLabel("CẬP NHẬT THÔNG TIN NHÀ CUNG CẤP"); // Tiêu đề
        lblTitle_CN_NCC.setFont(FONT_TITLE_MAIN);
        lblTitle_CN_NCC.setForeground(COLOR_PRIMARY_BLUE);
        lblTitle_CN_NCC.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle_CN_NCC.setBounds(0, 11, 1584, 52); // Vị trí tiêu đề
        panel_capNhatNCC.add(lblTitle_CN_NCC);

        // Panel ảnh
        JPanel panelAnh_NCC = new JPanel(); // Giữ tên biến
        panelAnh_NCC.setLayout(new BorderLayout(0,0));
        panelAnh_NCC.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        panelAnh_NCC.setBackground(COLOR_BACKGROUND_PRIMARY);
        panelAnh_NCC.setBounds(40, 80, 339, 328); // Vị trí
        panel_capNhatNCC.add(panelAnh_NCC);
        // Thêm label placeholder
        JLabel lblAnh_CNNCC = new JLabel("Chưa có ảnh NCC", SwingConstants.CENTER);
        lblAnh_CNNCC.setFont(FONT_DETAIL_ITALIC);
        lblAnh_CNNCC.setForeground(COLOR_TEXT_MUTED);
        panelAnh_NCC.add(lblAnh_CNNCC, BorderLayout.CENTER);


        JButton btnChonAnh_CNNCC = new JButton("Chọn ảnh");
        btnChonAnh_CNNCC.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnChonAnh_CNNCC, COLOR_TEXT_MUTED);
        btnChonAnh_CNNCC.setBounds(140, 420, 152, 37); // Vị trí
        // Đại Ca thêm ActionListener sau
        panel_capNhatNCC.add(btnChonAnh_CNNCC);

        // Panel thông tin cập nhật
        JPanel pnlInfo_CNNCC = new JPanel(); // Đặt tên mới
        pnlInfo_CNNCC.setBackground(COLOR_CARD_BACKGROUND);
        pnlInfo_CNNCC.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Thông Tin Cập Nhật", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        ));
        pnlInfo_CNNCC.setBounds(400, 80, 1164, 377); // Vị trí
        panel_capNhatNCC.add(pnlInfo_CNNCC);
        pnlInfo_CNNCC.setLayout(null);

        // Định nghĩa vị trí và khoảng cách (tương tự panel Thêm NCC)
        int labelX_cncc = 40;
        int inputX_cncc = 210;
        int labelX2_cncc = 580;
        int inputX2_cncc = 710;
        int startY_cncc = 30;
        int height_cncc = 33;
        int vGap_cncc = 15;

        JLabel lblMaNCC_CNNCC = new JLabel("Mã nhà cung cấp:");
        lblMaNCC_CNNCC.setFont(FONT_LABEL_BOLD);
        lblMaNCC_CNNCC.setForeground(COLOR_TEXT_DARK);
        lblMaNCC_CNNCC.setBounds(labelX_cncc, startY_cncc, 160, height_cncc);
        pnlInfo_CNNCC.add(lblMaNCC_CNNCC);

        txtMaNCC_CNNCC = new JTextField();
        txtMaNCC_CNNCC.setFont(FONT_LABEL_BOLD);
        txtMaNCC_CNNCC.setForeground(COLOR_DANGER_RED);
        txtMaNCC_CNNCC.setEditable(false);
        txtMaNCC_CNNCC.setColumns(10);
        txtMaNCC_CNNCC.setBounds(inputX_cncc, startY_cncc, 300, height_cncc);
        txtMaNCC_CNNCC.setBorder(null);
        txtMaNCC_CNNCC.setBackground(COLOR_CARD_BACKGROUND);
        pnlInfo_CNNCC.add(txtMaNCC_CNNCC);

        JLabel lblTenNCC_CNNCC = new JLabel("Tên nhà cung cấp:");
        lblTenNCC_CNNCC.setFont(FONT_LABEL_BOLD);
        lblTenNCC_CNNCC.setForeground(COLOR_TEXT_DARK);
        lblTenNCC_CNNCC.setBounds(labelX_cncc, startY_cncc + height_cncc + vGap_cncc, 160, height_cncc);
        pnlInfo_CNNCC.add(lblTenNCC_CNNCC);

        txtTenNCC_CNNCC = new JTextField();
        txtTenNCC_CNNCC.setFont(FONT_TEXT_FIELD);
        txtTenNCC_CNNCC.setColumns(10);
        txtTenNCC_CNNCC.setBounds(inputX_cncc, startY_cncc + height_cncc + vGap_cncc, 800, height_cncc);
        pnlInfo_CNNCC.add(txtTenNCC_CNNCC);

        JLabel lblSDT_CNNCC = new JLabel("Số điện thoại:");
        lblSDT_CNNCC.setFont(FONT_LABEL_BOLD);
        lblSDT_CNNCC.setForeground(COLOR_TEXT_DARK);
        lblSDT_CNNCC.setBounds(labelX_cncc, startY_cncc + 2*(height_cncc + vGap_cncc), 160, height_cncc);
        pnlInfo_CNNCC.add(lblSDT_CNNCC);

        txtSDT_CNNCC = new JTextField();
        txtSDT_CNNCC.setFont(FONT_TEXT_FIELD);
        txtSDT_CNNCC.setColumns(10);
        txtSDT_CNNCC.setBounds(inputX_cncc, startY_cncc + 2*(height_cncc + vGap_cncc), 300, height_cncc);
        pnlInfo_CNNCC.add(txtSDT_CNNCC);

        JLabel lblEmail_CNNCC = new JLabel("Email:");
        lblEmail_CNNCC.setFont(FONT_LABEL_BOLD);
        lblEmail_CNNCC.setForeground(COLOR_TEXT_DARK);
        lblEmail_CNNCC.setBounds(labelX2_cncc, startY_cncc + 2*(height_cncc + vGap_cncc), 120, height_cncc);
        pnlInfo_CNNCC.add(lblEmail_CNNCC);

        txtEmail_CNNCC = new JTextField(); // Giữ lại TextField vì code gốc có dùng
        txtEmail_CNNCC.setFont(FONT_TEXT_FIELD);
        txtEmail_CNNCC.setColumns(10);
        txtEmail_CNNCC.setBounds(inputX2_cncc, startY_cncc + 2*(height_cncc + vGap_cncc), 300, height_cncc);
        pnlInfo_CNNCC.add(txtEmail_CNNCC);

        JLabel lblTrangThai_CNNCC = new JLabel("Trạng thái:");
        lblTrangThai_CNNCC.setFont(FONT_LABEL_BOLD);
        lblTrangThai_CNNCC.setForeground(COLOR_TEXT_DARK);
        lblTrangThai_CNNCC.setBounds(labelX2_cncc, startY_cncc, 120, height_cncc);
        pnlInfo_CNNCC.add(lblTrangThai_CNNCC);

        JComboBox<String> cboTrangThai_CNNCC = new JComboBox<String>(); // Dùng ComboBox
        cboTrangThai_CNNCC.setFont(FONT_TEXT_FIELD);
        cboTrangThai_CNNCC.setModel(new DefaultComboBoxModel<>(new String[] {"Đang hợp tác", "Ngừng hợp tác"}));
        cboTrangThai_CNNCC.setBounds(inputX2_cncc, startY_cncc, 300, height_cncc);
        pnlInfo_CNNCC.add(cboTrangThai_CNNCC);

        JLabel lblGhiChu_CNNCC = new JLabel("Ghi chú:");
        lblGhiChu_CNNCC.setFont(FONT_LABEL_BOLD);
        lblGhiChu_CNNCC.setForeground(COLOR_TEXT_DARK);
        lblGhiChu_CNNCC.setBounds(labelX_cncc, startY_cncc + 3*(height_cncc + vGap_cncc), 160, height_cncc);
        pnlInfo_CNNCC.add(lblGhiChu_CNNCC);

        // Dùng JScrollPane cho Ghi chú
        JScrollPane scrollPaneGhiChu_CNNCC = new JScrollPane();
        scrollPaneGhiChu_CNNCC.setBounds(inputX_cncc, startY_cncc + 3*(height_cncc + vGap_cncc), 800, 60);
        pnlInfo_CNNCC.add(scrollPaneGhiChu_CNNCC);

        JTextArea txtGhiChu_CNNCC = new JTextArea(); // Đổi thành JTextArea
        txtGhiChu_CNNCC.setFont(FONT_TEXT_FIELD);
        txtGhiChu_CNNCC.setLineWrap(true);
        txtGhiChu_CNNCC.setWrapStyleWord(true);
        scrollPaneGhiChu_CNNCC.setViewportView(txtGhiChu_CNNCC);
        // Bỏ JTextField txtGhiChu_CNNCC cũ

        // Panel địa chỉ
        JPanel panelDiaChi_CNNCC = new JPanel();
        panelDiaChi_CNNCC.setBackground(COLOR_CARD_BACKGROUND);
        panelDiaChi_CNNCC.setLayout(null);
        panelDiaChi_CNNCC.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
             "Địa chỉ", TitledBorder.LEADING, TitledBorder.TOP,
             FONT_LABEL_BOLD, COLOR_TEXT_MUTED
        ));
        panelDiaChi_CNNCC.setBounds(labelX_cncc, startY_cncc + 4*(height_cncc + vGap_cncc) + 30, 1080, 85);
        pnlInfo_CNNCC.add(panelDiaChi_CNNCC);

        JLabel lblTinh_CNNV_1 = new JLabel("Tỉnh/TP:"); // Đổi tên label cho rõ
        lblTinh_CNNV_1.setFont(FONT_LABEL_BOLD);
        lblTinh_CNNV_1.setForeground(COLOR_TEXT_DARK);
        lblTinh_CNNV_1.setBounds(20, 30, 80, height_cncc);
        panelDiaChi_CNNCC.add(lblTinh_CNNV_1);

        JTextField txtTinh_CNNCC = new JTextField(); // Đổi tên biến cho rõ
        txtTinh_CNNCC.setFont(FONT_TEXT_FIELD);
        txtTinh_CNNCC.setColumns(10);
        txtTinh_CNNCC.setBounds(110, 30, 380, height_cncc);
        panelDiaChi_CNNCC.add(txtTinh_CNNCC);

        JLabel lblHuyen_CNNV_1 = new JLabel("Quận/Huyện:"); // Đổi tên label
        lblHuyen_CNNV_1.setFont(FONT_LABEL_BOLD);
        lblHuyen_CNNV_1.setForeground(COLOR_TEXT_DARK);
        lblHuyen_CNNV_1.setBounds(530, 30, 120, height_cncc);
        panelDiaChi_CNNCC.add(lblHuyen_CNNV_1);

        JTextField txtHuyen_CNNCC = new JTextField(); // Đổi tên biến
        txtHuyen_CNNCC.setFont(FONT_TEXT_FIELD);
        txtHuyen_CNNCC.setColumns(10);
        txtHuyen_CNNCC.setBounds(660, 30, 400, height_cncc);
        panelDiaChi_CNNCC.add(txtHuyen_CNNCC);


        // Các nút chức năng
        JButton btnLamMoi_CNNCC = new JButton("Làm mới Form");
        btnLamMoi_CNNCC.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnLamMoi_CNNCC, COLOR_TEXT_MUTED);
        btnLamMoi_CNNCC.setBounds(880, 470, 150, 40); // Điều chỉnh
        panel_capNhatNCC.add(btnLamMoi_CNNCC);

        JButton btnKhoiPhuc_CNNCC = new JButton("Khôi phục");
        btnKhoiPhuc_CNNCC.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnKhoiPhuc_CNNCC, COLOR_TEXT_MUTED);
        btnKhoiPhuc_CNNCC.setBounds(1050, 470, 150, 40); // Điều chỉnh
        panel_capNhatNCC.add(btnKhoiPhuc_CNNCC);

        JButton btnCapNhat_CNNCC = new JButton("Cập nhật");
        btnCapNhat_CNNCC.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnCapNhat_CNNCC, COLOR_SUCCESS_GREEN);
        btnCapNhat_CNNCC.setBounds(1220, 470, 150, 40); // Điều chỉnh
        panel_capNhatNCC.add(btnCapNhat_CNNCC);


        // ScrollPane và Bảng
        JScrollPane scrollPane_CNNCC = new JScrollPane();
        scrollPane_CNNCC.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scrollPane_CNNCC.setBounds(10, 520, 1564, 408); // Điều chỉnh
        panel_capNhatNCC.add(scrollPane_CNNCC);

        // Style bảng table_CNNCC
        table_CNNCC = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground());
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
        };
        applyCommonTableStyling(table_CNNCC);
        table_CNNCC.setModel(new DefaultTableModel(
        	new Object[][] {},
        	new String[] {
        		"Mã NCC", "Tên NCC", "Số điện thoại", "Email", "Trạng thái", "Quận/Huyện", "Tỉnh/TP", "Ghi chú"
        	}
        ));
        scrollPane_CNNCC.setViewportView(table_CNNCC);

        // ===== KẾT THÚC KHỐI CODE CẬP NHẬT NHÀ CUNG CẤP =====
        
     // ===== BẮT ĐẦU KHỐI CODE TÌM KIẾM NHÀ CUNG CẤP ĐÃ SỬA =====
        JPanel panel_TimKiemNCC = new JPanel();
        maincontent.add(panel_TimKiemNCC, "timKiemNCC");
        panel_TimKiemNCC.setLayout(null);
        panel_TimKiemNCC.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính

        JLabel lblTitle_TKNCC = new JLabel("TÌM KIẾM NHÀ CUNG CẤP"); // Tiêu đề
        lblTitle_TKNCC.setFont(FONT_TITLE_MAIN);
        lblTitle_TKNCC.setForeground(COLOR_PRIMARY_BLUE);
        lblTitle_TKNCC.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle_TKNCC.setBounds(0, 11, 1584, 43); // Vị trí
        panel_TimKiemNCC.add(lblTitle_TKNCC);

        // Panel bộ lọc thông tin
        JPanel panel_ThongTinTKNCC = new JPanel();
        panel_ThongTinTKNCC.setLayout(null);
        panel_ThongTinTKNCC.setBackground(COLOR_CARD_BACKGROUND); // Nền trắng
        panel_ThongTinTKNCC.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
            "Bộ Lọc Tìm Kiếm", TitledBorder.LEADING, TitledBorder.TOP,
            FONT_LABEL_BOLD, COLOR_PRIMARY_BLUE
        )); // Viền và tiêu đề
        panel_ThongTinTKNCC.setBounds(10, 68, 1564, 350); // Vị trí
        panel_TimKiemNCC.add(panel_ThongTinTKNCC);

        // Định nghĩa vị trí và khoảng cách (tương tự Thêm NCC)
        int labelX_tkncc = 40;
        int inputX_tkncc = 210;
        int labelX2_tkncc = 580;
        int inputX2_tkncc = 710;
        int startY_tkncc = 40;
        int height_tkncc = 33;
        int vGap_tkncc = 15;

        JLabel lblMaNCC_TKNCC = new JLabel("Mã nhà cung cấp:");
        lblMaNCC_TKNCC.setFont(FONT_LABEL_BOLD);
        lblMaNCC_TKNCC.setForeground(COLOR_TEXT_DARK);
        lblMaNCC_TKNCC.setBounds(labelX_tkncc, startY_tkncc, 160, height_tkncc);
        panel_ThongTinTKNCC.add(lblMaNCC_TKNCC);

        txtMaNCC_TKNCC = new JTextField();
        txtMaNCC_TKNCC.setFont(FONT_TEXT_FIELD);
        txtMaNCC_TKNCC.setColumns(10);
        txtMaNCC_TKNCC.setBounds(inputX_tkncc, startY_tkncc, 300, height_tkncc);
        panel_ThongTinTKNCC.add(txtMaNCC_TKNCC);

        JLabel lblTenNCC_TKNCC = new JLabel("Tên nhà cung cấp:");
        lblTenNCC_TKNCC.setFont(FONT_LABEL_BOLD);
        lblTenNCC_TKNCC.setForeground(COLOR_TEXT_DARK);
        lblTenNCC_TKNCC.setBounds(labelX_tkncc, startY_tkncc + height_tkncc + vGap_tncc, 179, height_tkncc);
        panel_ThongTinTKNCC.add(lblTenNCC_TKNCC);

        txtTenNCC_TKNCC = new JTextField();
        txtTenNCC_TKNCC.setFont(FONT_TEXT_FIELD);
        txtTenNCC_TKNCC.setColumns(10);
        txtTenNCC_TKNCC.setBounds(inputX_tkncc, startY_tkncc + height_tkncc + vGap_tncc, 800, height_tkncc);
        panel_ThongTinTKNCC.add(txtTenNCC_TKNCC);

        JLabel lblSDTNCC_TKNCC = new JLabel("Số điện thoại:");
        lblSDTNCC_TKNCC.setFont(FONT_LABEL_BOLD);
        lblSDTNCC_TKNCC.setForeground(COLOR_TEXT_DARK);
        lblSDTNCC_TKNCC.setBounds(labelX_tkncc, startY_tkncc + 2*(height_tkncc + vGap_tncc), 160, height_tkncc);
        panel_ThongTinTKNCC.add(lblSDTNCC_TKNCC);

        txtSDT_TKNCC = new JTextField();
        txtSDT_TKNCC.setFont(FONT_TEXT_FIELD);
        txtSDT_TKNCC.setColumns(10);
        txtSDT_TKNCC.setBounds(inputX_tkncc, startY_tkncc + 2*(height_tkncc + vGap_tncc), 300, height_tkncc);
        panel_ThongTinTKNCC.add(txtSDT_TKNCC);

        JLabel lblEmail_TKNCC = new JLabel("Email:");
        lblEmail_TKNCC.setFont(FONT_LABEL_BOLD);
        lblEmail_TKNCC.setForeground(COLOR_TEXT_DARK);
        lblEmail_TKNCC.setBounds(labelX2_tkncc, startY_tkncc + 2*(height_tkncc + vGap_tncc), 120, height_tkncc);
        panel_ThongTinTKNCC.add(lblEmail_TKNCC);

        txtEmail_TKNCC = new JTextField();
        txtEmail_TKNCC.setFont(FONT_TEXT_FIELD);
        txtEmail_TKNCC.setColumns(10);
        txtEmail_TKNCC.setBounds(inputX2_tkncc, startY_tkncc + 2*(height_tkncc + vGap_tncc), 300, height_tkncc);
        panel_ThongTinTKNCC.add(txtEmail_TKNCC);

        JLabel lblTrangThai_TKNCC = new JLabel("Trạng thái:");
        lblTrangThai_TKNCC.setFont(FONT_LABEL_BOLD);
        lblTrangThai_TKNCC.setForeground(COLOR_TEXT_DARK);
        lblTrangThai_TKNCC.setBounds(labelX2_tkncc, startY_tkncc, 120, height_tkncc);
        panel_ThongTinTKNCC.add(lblTrangThai_TKNCC);

        JComboBox<String> cboTrangThai_TKNCC = new JComboBox<String>(); // Dùng ComboBox
        cboTrangThai_TKNCC.setFont(FONT_TEXT_FIELD);
        cboTrangThai_TKNCC.setModel(new DefaultComboBoxModel<>(new String[] {"Tất cả", "Đang hợp tác", "Ngừng hợp tác"})); // Thêm "Tất cả"
        cboTrangThai_TKNCC.setBounds(inputX2_tkncc, startY_tkncc, 300, height_tkncc);
        panel_ThongTinTKNCC.add(cboTrangThai_TKNCC);
        // Bỏ JTextField txtTrangThai_TKNCC cũ


        JLabel lblGhiChu_TKNCC = new JLabel("Ghi chú:");
        lblGhiChu_TKNCC.setFont(FONT_LABEL_BOLD);
        lblGhiChu_TKNCC.setForeground(COLOR_TEXT_DARK);
        lblGhiChu_TKNCC.setBounds(labelX_tkncc, startY_tkncc + 3*(height_tkncc + vGap_tncc), 160, height_tkncc);
        panel_ThongTinTKNCC.add(lblGhiChu_TKNCC);

        txtGhiChu_TKNCC = new JTextField(); // Giữ lại TextField nếu chỉ tìm kiếm
        txtGhiChu_TKNCC.setFont(FONT_TEXT_FIELD);
        txtGhiChu_TKNCC.setColumns(10);
        txtGhiChu_TKNCC.setBounds(inputX_tkncc, startY_tkncc + 3*(height_tkncc + vGap_tncc), 800, height_tkncc);
        panel_ThongTinTKNCC.add(txtGhiChu_TKNCC);


        // Panel địa chỉ
        JPanel panelDiaChi_TKNCC = new JPanel();
        panelDiaChi_TKNCC.setBackground(COLOR_CARD_BACKGROUND);
        panelDiaChi_TKNCC.setLayout(null);
        panelDiaChi_TKNCC.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDER_LIGHT, 1, true),
             "Địa chỉ", TitledBorder.LEADING, TitledBorder.TOP,
             FONT_LABEL_BOLD, COLOR_TEXT_MUTED
        ));
        panelDiaChi_TKNCC.setBounds(labelX_tkncc, startY_tkncc + 4*(height_tkncc + vGap_tncc) + 10, 1018, 85); // Điều chỉnh width
        panel_ThongTinTKNCC.add(panelDiaChi_TKNCC);

        JLabel lblTinh_TKNCC = new JLabel("Tỉnh/TP:");
        lblTinh_TKNCC.setFont(FONT_LABEL_BOLD);
        lblTinh_TKNCC.setForeground(COLOR_TEXT_DARK);
        lblTinh_TKNCC.setBounds(20, 30, 80, height_tkncc);
        panelDiaChi_TKNCC.add(lblTinh_TKNCC);

        JTextField txtTinh_TKNCC = new JTextField(); // Đổi tên biến cho rõ
        txtTinh_TKNCC.setFont(FONT_TEXT_FIELD);
        txtTinh_TKNCC.setColumns(10);
        txtTinh_TKNCC.setBounds(110, 30, 360, height_tkncc);
        panelDiaChi_TKNCC.add(txtTinh_TKNCC);

        JLabel lblHuyen_TKNCC = new JLabel("Quận/Huyện:");
        lblHuyen_TKNCC.setFont(FONT_LABEL_BOLD);
        lblHuyen_TKNCC.setForeground(COLOR_TEXT_DARK);
        lblHuyen_TKNCC.setBounds(500, 30, 120, height_tkncc);
        panelDiaChi_TKNCC.add(lblHuyen_TKNCC);

        JTextField txtHuyen_TKNCC = new JTextField(); // Đổi tên biến cho rõ
        txtHuyen_TKNCC.setFont(FONT_TEXT_FIELD);
        txtHuyen_TKNCC.setColumns(10);
        txtHuyen_TKNCC.setBounds(630, 30, 360, height_tkncc);
        panelDiaChi_TKNCC.add(txtHuyen_TKNCC);

        // Nút Làm mới cho bộ lọc
        JButton btnLamMoi_TKNCC = new JButton("Làm mới bộ lọc"); // Tạo nút mới
        btnLamMoi_TKNCC.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnLamMoi_TKNCC, COLOR_TEXT_MUTED);
        btnLamMoi_TKNCC.setBounds(1400, 240, 160, 40); // Điều chỉnh vị trí ngoài panel lọc
        panel_ThongTinTKNCC.add(btnLamMoi_TKNCC);


        // ScrollPane và Bảng kết quả tìm kiếm
        JScrollPane scrollPane_TKNCC = new JScrollPane();
        scrollPane_TKNCC.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        scrollPane_TKNCC.setBounds(10, 429, 1564, 561); // Điều chỉnh vị trí/kích thước
        panel_TimKiemNCC.add(scrollPane_TKNCC);

        // Style bảng table_TKNCC
        table_TKNCC = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground());
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                }
                return c;
            }
        };
        applyCommonTableStyling(table_TKNCC);
        table_TKNCC.setModel(new DefaultTableModel(
        	new Object[][] {}, // Bỏ dòng null
        	new String[] {
        		"Mã NCC", "Tên NCC", "Số điện thoại", "Email", "Trạng thái", "Quận/Huyện", "Tỉnh/TP", "Ghi chú"
        	}
        ));
        scrollPane_TKNCC.setViewportView(table_TKNCC);

        // ===== KẾT THÚC KHỐI CODE TÌM KIẾM NHÀ CUNG CẤP =====
        
        JPanel panel_QLPhieuDatHangNCC = new JPanel();
        maincontent.add(panel_QLPhieuDatHangNCC, "quanLyPhieuThuNCC");
        panel_QLPhieuDatHangNCC.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel_QLPhieuDatHangNCC.add(panel, BorderLayout.NORTH);
        
        JLabel lblTimKiemNCC = new JLabel("Tìm kiếm:");
        lblTimKiemNCC.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel.add(lblTimKiemNCC);
        
        txtTimKiem = new JTextField();
        txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(txtTimKiem);
        txtTimKiem.setColumns(10);
        
        JButton btnTim = new JButton("Tìm");
        btnTim.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel.add(btnTim);
        
        JScrollPane scrollPane = new JScrollPane();
        panel_QLPhieuDatHangNCC.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 phi\u1EBFu ", "M\u00E3 nh\u00E0 cung c\u1EA5p", "T\u00EAn nh\u00E0 cung c\u1EA5p", "Ng\u00E0y \u0111\u1EB7t", "T\u1ED5ng ti\u1EC1n ", "Tr\u1EA1ng th\u00E1i"
        	}
        ));
        scrollPane.setViewportView(table);
        
        JPanel panel_1 = new JPanel();
        panel_QLPhieuDatHangNCC.add(panel_1, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton("Tạo mới");
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel_1.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Xem/Sửa");
        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel_1.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Xóa");
        btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel_1.add(btnNewButton_2);
        
     // ===== PANEL QUẢN LÝ TÀI KHOẢN (ADMIN) =====
        JPanel pn_QuanLyTaiKhoan = new JPanel();
        maincontent.add(pn_QuanLyTaiKhoan, "quanLyTaiKhoan"); // Add panel vào CardLayout
        pn_QuanLyTaiKhoan.setLayout(new BorderLayout(10, 10)); // Dùng BorderLayout
        pn_QuanLyTaiKhoan.setBackground(COLOR_BACKGROUND_PRIMARY); // Nền chính
        pn_QuanLyTaiKhoan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        JLabel lblTitleQLTK = new JLabel("QUẢN LÝ TÀI KHOẢN HỆ THỐNG");
        lblTitleQLTK.setFont(FONT_TITLE_MAIN); // Font tiêu đề
        lblTitleQLTK.setForeground(COLOR_PRIMARY_BLUE); // Màu tiêu đề
        lblTitleQLTK.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        pn_QuanLyTaiKhoan.add(lblTitleQLTK, BorderLayout.NORTH); // Thêm tiêu đề vào vị trí NORTH

        JScrollPane scrollPaneQLTK = new JScrollPane();
        scrollPaneQLTK.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT)); // Viền
        pn_QuanLyTaiKhoan.add(scrollPaneQLTK, BorderLayout.CENTER); // Thêm bảng vào vị trí CENTER

        // Khởi tạo và style bảng tableQLTK (gán vào biến toàn cục)
        tableQLTK = new JTable() {
             @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BACKGROUND : COLOR_BACKGROUND_PRIMARY);
                    c.setForeground(this.getForeground()); // Dùng this
                } else {
                    c.setBackground(COLOR_PRIMARY_BLUE);
                    // Không set foreground trắng
                }
                return c;
            }
        };
        applyCommonTableStyling(tableQLTK); // Áp dụng style chung
        tableQLTK.setModel(new DefaultTableModel(
            new Object[][] {}, // Dữ liệu trống ban đầu
            new String[] {"Mã TK", "Tên Tài Khoản", "Quyền Hạn"} // Các cột hiển thị
        ));
        scrollPaneQLTK.setViewportView(tableQLTK); // Đặt bảng vào ScrollPane

        // Panel chức năng (Thêm, Xóa trắng form) đặt ở SOUTH
        JPanel pnlChucNangQLTK = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // FlowLayout căn trái
        pnlChucNangQLTK.setOpaque(false); // Trong suốt để lấy nền của pn_QuanLyTaiKhoan
        pn_QuanLyTaiKhoan.add(pnlChucNangQLTK, BorderLayout.SOUTH); // Thêm panel chức năng vào SOUTH

        JLabel lblMaTK_QL = new JLabel("Mã TK:");
        lblMaTK_QL.setFont(FONT_LABEL_BOLD);
        lblMaTK_QL.setForeground(COLOR_TEXT_DARK);
        pnlChucNangQLTK.add(lblMaTK_QL);

        JTextField txtMaTK_QL = new JTextField(10);
        txtMaTK_QL.setFont(FONT_TEXT_FIELD);
        pnlChucNangQLTK.add(txtMaTK_QL);

        JLabel lblTenTK_QL = new JLabel("Tên TK:");
        lblTenTK_QL.setFont(FONT_LABEL_BOLD);
        lblTenTK_QL.setForeground(COLOR_TEXT_DARK);
        pnlChucNangQLTK.add(lblTenTK_QL);

        JTextField txtTenTK_QL = new JTextField(15);
        txtTenTK_QL.setFont(FONT_TEXT_FIELD);
        pnlChucNangQLTK.add(txtTenTK_QL);

        JLabel lblMatKhau_QL = new JLabel("Mật Khẩu:");
        lblMatKhau_QL.setFont(FONT_LABEL_BOLD);
        lblMatKhau_QL.setForeground(COLOR_TEXT_DARK);
        pnlChucNangQLTK.add(lblMatKhau_QL);

        JPasswordField txtMatKhau_QL = new JPasswordField(15);
        txtMatKhau_QL.setFont(FONT_TEXT_FIELD);
        pnlChucNangQLTK.add(txtMatKhau_QL);

        JLabel lblQuyenHan_QL = new JLabel("Quyền Hạn:");
        lblQuyenHan_QL.setFont(FONT_LABEL_BOLD);
        lblQuyenHan_QL.setForeground(COLOR_TEXT_DARK);
        pnlChucNangQLTK.add(lblQuyenHan_QL);

        JComboBox<String> cboQuyenHan_QL = new JComboBox<>(new String[]{
            "Quản lý",
            "Nhân viên bán hàng",
            "Nhân viên kho",
            "(Chưa cấp)" // Lựa chọn cho NULL
        });
        cboQuyenHan_QL.setFont(FONT_TEXT_FIELD);
        pnlChucNangQLTK.add(cboQuyenHan_QL);

        JButton btnThemTK = new JButton("Thêm Tài Khoản");
        btnThemTK.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnThemTK, COLOR_SUCCESS_GREEN); // Style nút thêm
        pnlChucNangQLTK.add(btnThemTK);

        JButton btnXoaTrangQLTK = new JButton("Xóa Trắng Form");
        btnXoaTrangQLTK.setFont(FONT_BUTTON_STANDARD);
        styleButton(btnXoaTrangQLTK, COLOR_TEXT_MUTED); // Style nút xóa trắng
        pnlChucNangQLTK.add(btnXoaTrangQLTK);

        // --- Xử lý sự kiện cho panel QLTK ---
        taiKhoan_DAO tkDAO_QL = new taiKhoan_DAO(); // Khởi tạo DAO

        // Click vào bảng QLTK -> hiện thông tin lên form
        tableQLTK.addMouseListener(new MouseAdapter() {
             @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableQLTK.getSelectedRow();
                if (row != -1) {
                     txtMaTK_QL.setText(tableQLTK.getValueAt(row, 0).toString());
                     txtTenTK_QL.setText(tableQLTK.getValueAt(row, 1).toString());
                     txtMatKhau_QL.setText(""); // Luôn xóa field mật khẩu khi chọn
                     Object quyenObj = tableQLTK.getValueAt(row, 2);
                     // Hiển thị quyền hiện tại
                     cboQuyenHan_QL.setSelectedItem(quyenObj != null && !quyenObj.toString().equals("(Chưa cấp)") ? quyenObj.toString() : "(Chưa cấp)");
                     txtMaTK_QL.setEditable(false); // Không cho sửa mã khi đã chọn từ bảng
                     txtTenTK_QL.requestFocus(); // Focus vào Tên TK để dễ sửa
                }
            }
        });

        // Nút Xóa trắng form QLTK
        btnXoaTrangQLTK.addActionListener(e -> {
             txtMaTK_QL.setText("");
             txtTenTK_QL.setText("");
             txtMatKhau_QL.setText("");
             cboQuyenHan_QL.setSelectedIndex(cboQuyenHan_QL.getItemCount() - 1); // Chọn "(Chưa cấp)"
             tableQLTK.clearSelection(); // Bỏ chọn dòng trên bảng
             txtMaTK_QL.setEditable(true); // Cho phép nhập lại mã khi xóa trắng
             txtMaTK_QL.requestFocus(); // Focus vào ô mã TK
        });

        // Nút Thêm Tài Khoản (Admin)
        btnThemTK.addActionListener(e -> {
            String maTK = txtMaTK_QL.getText().trim();
            String tenTK = txtTenTK_QL.getText().trim();
            String matKhau = new String(txtMatKhau_QL.getPassword());
            String quyen = cboQuyenHan_QL.getSelectedItem().toString();

            // Kiểm tra nhập liệu
            if (maTK.isEmpty() || tenTK.isEmpty() || matKhau.isEmpty()) {
                JOptionPane.showMessageDialog(pn_QuanLyTaiKhoan, "Vui lòng nhập đầy đủ Mã TK, Tên TK và Mật khẩu!", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                txtMaTK_QL.requestFocus(); // Focus vào ô Mã TK nếu rỗng
                return;
            }
            // (Thêm kiểm tra định dạng maTK nếu muốn, ví dụ: phải bắt đầu bằng TK...)

            TaiKhoan tkMoi = new TaiKhoan();
            tkMoi.setMaTK(maTK); // Dùng mã TK người dùng nhập
            tkMoi.setTenTK(tenTK);
            tkMoi.setMatKhau(matKhau); // !!! Nhớ mã hóa mật khẩu ở DAO !!!
            tkMoi.setQuyenHan(quyen.equals("(Chưa cấp)") ? null : quyen); // Xử lý "(Chưa cấp)" thành NULL

            // Gọi hàm addTaiKhoan từ DAO (đã có kiểm tra trùng mã/tên bên trong)
            if (tkDAO_QL.addTaiKhoan(tkMoi)) {
                JOptionPane.showMessageDialog(pn_QuanLyTaiKhoan, "Thêm tài khoản '" + maTK + "' thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

                // Load lại bảng tài khoản (gọi trực tiếp)
                try {
                    DefaultTableModel model = (DefaultTableModel) tableQLTK.getModel();
                    model.setRowCount(0);
                    List<TaiKhoan> list = tkDAO_QL.getAllTaiKhoan();
                    for (TaiKhoan tk : list) {
                         model.addRow(new Object[]{tk.getMaTK(), tk.getTenTK(), tk.getQuyenHan() == null ? "(Chưa cấp)" : tk.getQuyenHan()});
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // In lỗi nếu load lại bảng thất bại
                }

                btnXoaTrangQLTK.doClick(); // Xóa trắng form sau khi thêm thành công

                // HIỆN DIALOG THÊM NHÂN VIÊN
                nhanVien_DAO nvDAO_moi = new nhanVien_DAO();
                String maNV_moi = nvDAO_moi.generateNewMaNV(); // Tạo mã NV mới
                ThemNhanVienSauKhiTaoTK_GUI themNVDialog = new ThemNhanVienSauKhiTaoTK_GUI(
                    QuanLyHieuThuocTay, // Frame cha
                    tkMoi,             // Tài khoản vừa tạo
                    maNV_moi           // Mã NV mới
                );
                themNVDialog.setVisible(true); // Hiển thị dialog

            }
            // else { // Không cần else vì DAO đã hiện JOptionPane lỗi trùng lặp }
        });

        // ===== KẾT THÚC PANEL QUẢN LÝ TÀI KHOẢN =====
        
        new KhachHang_Controller(this);
        Thuoc_Controller controller = new Thuoc_Controller(this);
        QuanLyHieuThuocTay.setVisible(true);
    }
    
    // Phương thức hỗ trợ tạo nút trong thanh sidebar
    private JButton createSidebarButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(FONT_LABEL_BOLD.deriveFont(16f));
        button.setForeground(COLOR_SIDEBAR_TEXT);
        button.setBackground(COLOR_SIDEBAR_BG_END);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setOpaque(false); // QUAN TRỌNG
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height + 10));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);

//        ImageIcon icon = loadIcon(iconPath);
//        if (icon != null) {
//            Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
//            button.setIcon(new ImageIcon(img));
//            button.setIconTextGap(15);
//        }
        return button;
    }

 // Phương thức hỗ trợ thêm hiệu ứng hover cho nút sidebar (CHỈ HOVER)
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button != selectedMainMenuButton) { // Chỉ hover nếu chưa selected
                    button.setOpaque(true);
                    button.setBackground(COLOR_SIDEBAR_BUTTON_HOVER);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != selectedMainMenuButton) { // Chỉ reset nếu chưa selected
                    button.setOpaque(false);
                    button.setBackground(COLOR_SIDEBAR_BG_END); // Màu nền cũ không quan trọng
                }
            }
        });
    }
 // === CÁC HÀM TẠO SUBMENU PANEL (DẠNG ACCORDION - CÁCH 2) ===

    // Submenu cho Hệ Thống (JPanel)
    private JPanel createSystemSubmenuPanel() {
        JPanel submenuPanel = new JPanel();
        submenuPanel.setLayout(new BoxLayout(submenuPanel, BoxLayout.Y_AXIS));
        submenuPanel.setBackground(COLOR_SIDEBAR_BG_END);
        submenuPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 5));
        submenuPanel.setVisible(false);
        submenuPanel.setOpaque(true);
        submenuPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnTrangChu;
        if (Beans.isDesignTime()) {
            btnTrangChu = new JButton("Trang Chủ (Design)");
        } else {
            btnTrangChu = createSubmenuButton("Trang Chủ");
            btnTrangChu.addActionListener(e -> {
               CardLayout cl = (CardLayout) maincontent.getLayout();
               cl.show(maincontent, "trangChu");
           });
        }
       submenuPanel.add(btnTrangChu);
        
       btnTaikhoan = createSubmenuButton("Tài Khoản");
       btnTaikhoan.addActionListener(e -> {
           // Kiểm tra quyền hạn trước khi hiển thị
           if (currentUser != null && "Quản lý".equalsIgnoreCase(currentUser.getQuyenHan())) {

               // 👇👇👇 LOAD DỮ LIỆU TRỰC TIẾP VÀO BẢNG 👇👇👇
               try {
                   DefaultTableModel model = (DefaultTableModel) tableQLTK.getModel();
                   model.setRowCount(0); // Xóa dữ liệu cũ
                   taiKhoan_DAO dao = new taiKhoan_DAO();
                   List<TaiKhoan> list = dao.getAllTaiKhoan();
                   if (list.isEmpty()) {
                        System.out.println("DAO: Không có tài khoản nào."); // Debug
                   }
                   for (TaiKhoan tk : list) {
                        model.addRow(new Object[]{
                           tk.getMaTK(),
                           tk.getTenTK(),
                           tk.getQuyenHan() == null ? "(Chưa cấp)" : tk.getQuyenHan()
                       });
                   }
                    System.out.println("Đã load " + model.getRowCount() + " tài khoản vào bảng."); // Debug
               } catch (Exception ex) {
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(TrangChu_GUI.this.QuanLyHieuThuocTay,
                    "Lỗi khi tải dữ liệu tài khoản:\n" + ex.getMessage(), "Lỗi dữ liệu", JOptionPane.ERROR_MESSAGE);
               }
               // 👆👆👆 HẾT PHẦN LOAD DỮ LIỆU 👆👆👆

               // Hiển thị panel
              CardLayout cl = (CardLayout) TrangChu_GUI.this.maincontent.getLayout();
              cl.show(TrangChu_GUI.this.maincontent, "quanLyTaiKhoan");

           } else {
               JOptionPane.showMessageDialog(TrangChu_GUI.this.QuanLyHieuThuocTay,
                "Chỉ có Quản lý mới được truy cập chức năng này!", "Truy cập bị từ chối", JOptionPane.WARNING_MESSAGE);
           }
       });
        submenuPanel.add(btnTaikhoan);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnTrogiup;
        if (Beans.isDesignTime()) {
            btnTrogiup = new JButton("Trợ Giúp (Design)");
        } else {
            btnTrogiup = createSubmenuButton("Trợ Giúp");
            // btnTrogiup.addActionListener(e -> { /* Code xử lý */ });
        }
        submenuPanel.add(btnTrogiup);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        
        submenuPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, submenuPanel.getPreferredSize().height));
        return submenuPanel;
    }

    // Submenu cho Nhân Viên (JPanel)
    private JPanel createEmployeeSubmenuPanel() {
        JPanel submenuPanel = new JPanel();
        submenuPanel.setLayout(new BoxLayout(submenuPanel, BoxLayout.Y_AXIS));
        submenuPanel.setBackground(COLOR_SIDEBAR_BG_END);
        submenuPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 5));
        submenuPanel.setVisible(false);
        submenuPanel.setOpaque(true);
        submenuPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnThemnv;
        if (Beans.isDesignTime()) {
            btnThemnv = new JButton("Thêm (Design)");
        } else {
            btnThemnv = createSubmenuButton("Thêm");
            btnThemnv.addActionListener(e -> {
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "themNV");
            });
        }
        submenuPanel.add(btnThemnv);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnCapnhatnv;
        if (Beans.isDesignTime()) {
            btnCapnhatnv = new JButton("Cập Nhật (Design)");
        } else {
            btnCapnhatnv = createSubmenuButton("Cập Nhật");
            btnCapnhatnv.addActionListener(e -> {
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "capnhatnv");
            });
        }
        submenuPanel.add(btnCapnhatnv);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnTimkiemnv;
        if (Beans.isDesignTime()) {
            btnTimkiemnv = new JButton("Tìm Kiếm (Design)");
        } else {
            btnTimkiemnv = createSubmenuButton("Tìm Kiếm");
            btnTimkiemnv.addActionListener(e -> {
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "timkiemnv");
            });
        }
        submenuPanel.add(btnTimkiemnv);

        submenuPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, submenuPanel.getPreferredSize().height));
        return submenuPanel;
    }

    // Submenu cho Khách Hàng (JPanel)
    private JPanel createCustomerSubmenuPanel() {
        JPanel submenuPanel = new JPanel();
        submenuPanel.setLayout(new BoxLayout(submenuPanel, BoxLayout.Y_AXIS));
        submenuPanel.setBackground(COLOR_SIDEBAR_BG_END);
        submenuPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 5));
        submenuPanel.setVisible(false);
        submenuPanel.setOpaque(true);
        submenuPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnThemkh;
        if (Beans.isDesignTime()) {
            btnThemkh = new JButton("Thêm (Design)");
        } else {
            btnThemkh = createSubmenuButton("Thêm");
            btnThemkh.addActionListener(e -> {
                ThemKH_GUI themKhDialog = new ThemKH_GUI(QuanLyHieuThuocTay); // Hoặc (this)
                new ThemKH_Controller(themKhDialog, this);
                themKhDialog.setVisible(true);
            });
        }
        submenuPanel.add(btnThemkh);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnCapnhatkh;
        if (Beans.isDesignTime()) {
            btnCapnhatkh = new JButton("Cập Nhật (Design)");
        } else {
            btnCapnhatkh = createSubmenuButton("Cập Nhật");
            btnCapnhatkh.addActionListener(e -> {
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "capNhatKH");
            });
        }
        submenuPanel.add(btnCapnhatkh);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnTimkiemkh;
        if (Beans.isDesignTime()) {
            btnTimkiemkh = new JButton("Tìm Kiếm (Design)");
        } else {
            btnTimkiemkh = createSubmenuButton("Tìm Kiếm");
            btnTimkiemkh.addActionListener(e -> {
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "timkiemkh");
            });
        }
        submenuPanel.add(btnTimkiemkh);

        submenuPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, submenuPanel.getPreferredSize().height));
        return submenuPanel;
    }

 // Submenu cho Thuốc (JPanel) - ĐÃ SỬA LỖI cl = null
    private JPanel createMedicineSubmenuPanel() {
        JPanel submenuPanel = new JPanel();
        submenuPanel.setLayout(new BoxLayout(submenuPanel, BoxLayout.Y_AXIS));
        submenuPanel.setBackground(COLOR_SIDEBAR_BG_END);
        submenuPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 5));
        submenuPanel.setVisible(false);
        submenuPanel.setOpaque(true);
        submenuPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnThemthuocthuong;
        if(Beans.isDesignTime()){ btnThemthuocthuong = new JButton("Thêm (Design)"); }
        else {
            btnThemthuocthuong = createSubmenuButton("Thêm");
            btnThemthuocthuong.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "Themthuocthuong");
            });
        }
        submenuPanel.add(btnThemthuocthuong);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnThemthuoctheofile;
        if(Beans.isDesignTime()){ btnThemthuoctheofile = new JButton("Thêm File (Design)"); }
        else {
            btnThemthuoctheofile = createSubmenuButton("Thêm File");
            btnThemthuoctheofile.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "Themthuocfile");
            });
        }
        submenuPanel.add(btnThemthuoctheofile);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnTimkiemthuoc;
        if(Beans.isDesignTime()){ btnTimkiemthuoc = new JButton("Tìm Kiếm (Design)"); }
        else {
            btnTimkiemthuoc = createSubmenuButton("Tìm Kiếm");
            btnTimkiemthuoc.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "timkiemSP");
            });
        }
        submenuPanel.add(btnTimkiemthuoc);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnCapnhatthuoc;
        if(Beans.isDesignTime()){ btnCapnhatthuoc = new JButton("Cập Nhật (Design)"); }
        else {
            btnCapnhatthuoc = createSubmenuButton("Cập Nhật");
            btnCapnhatthuoc.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "Capnhatthuoc");
            });
        }
        submenuPanel.add(btnCapnhatthuoc);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnThuocSapHetHan;
        if(Beans.isDesignTime()){ btnThuocSapHetHan = new JButton("Sắp Hết Hạn (Design)"); }
        else {
            btnThuocSapHetHan = createSubmenuButton("Thuốc Sắp Hết Hạn");
            btnThuocSapHetHan.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "Thuocsaphethan");
            });
        }
        submenuPanel.add(btnThuocSapHetHan);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnThuocBanChay;
        if(Beans.isDesignTime()){ btnThuocBanChay = new JButton("Bán Chạy (Design)"); }
        else {
            btnThuocBanChay = createSubmenuButton("Thuốc Bán Chạy");
            btnThuocBanChay.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "Thuocbanchay");
            });
        }
        submenuPanel.add(btnThuocBanChay);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnThuocSapHetHang;
        if(Beans.isDesignTime()){ btnThuocSapHetHang = new JButton("Sắp Hết Hàng (Design)"); }
        else {
            btnThuocSapHetHang = createSubmenuButton("Thuốc Sắp Hết Hàng");
            btnThuocSapHetHang.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "Thuocsaphethang");
            });
        }
        submenuPanel.add(btnThuocSapHetHang);

        submenuPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, submenuPanel.getPreferredSize().height));
        return submenuPanel;
    }

    // Submenu cho Hóa Đơn (JPanel) - ĐÃ SỬA LỖI cl = null
    private JPanel createInvoiceSubmenuPanel() {
        JPanel submenuPanel = new JPanel();
        submenuPanel.setLayout(new BoxLayout(submenuPanel, BoxLayout.Y_AXIS));
        submenuPanel.setBackground(COLOR_SIDEBAR_BG_END);
        submenuPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 5));
        submenuPanel.setVisible(false);
        submenuPanel.setOpaque(true);
        submenuPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnThemhoadon;
        if(Beans.isDesignTime()){ btnThemhoadon = new JButton("Thêm (Design)"); }
        else {
            btnThemhoadon = createSubmenuButton("Thêm");
            btnThemhoadon.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "themHoaDon");
            });
        }
        submenuPanel.add(btnThemhoadon);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnTimkiemhoadon;
        if(Beans.isDesignTime()){ btnTimkiemhoadon = new JButton("Tìm Kiếm (Design)"); }
        else {
            btnTimkiemhoadon = createSubmenuButton("Tìm Kiếm");
            btnTimkiemhoadon.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "timKiemHoaDon");
            });
        }
        submenuPanel.add(btnTimkiemhoadon);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnThongkehoadon;
        if(Beans.isDesignTime()){ btnThongkehoadon = new JButton("Thống Kê (Design)"); }
        else {
            btnThongkehoadon = createSubmenuButton("Thống Kê");
            btnThongkehoadon.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "ThongkeHD");
            });
        }
        submenuPanel.add(btnThongkehoadon);

        submenuPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, submenuPanel.getPreferredSize().height));
        return submenuPanel;
    }

     // Submenu cho Nhà Cung Cấp (JPanel) - ĐÃ SỬA LỖI cl = null
    private JPanel createSupplierSubmenuPanel() {
        JPanel submenuPanel = new JPanel();
        submenuPanel.setLayout(new BoxLayout(submenuPanel, BoxLayout.Y_AXIS));
        submenuPanel.setBackground(COLOR_SIDEBAR_BG_END);
        submenuPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 5));
        submenuPanel.setVisible(false);
        submenuPanel.setOpaque(true);
        submenuPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnThemncc;
        if(Beans.isDesignTime()){ btnThemncc = new JButton("Thêm (Design)"); }
        else {
            btnThemncc = createSubmenuButton("Thêm");
            btnThemncc.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "themNCC");
            });
        }
        submenuPanel.add(btnThemncc);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnCapnhatncc;
        if(Beans.isDesignTime()){ btnCapnhatncc = new JButton("Cập Nhật (Design)"); }
        else {
            btnCapnhatncc = createSubmenuButton("Cập Nhật");
            btnCapnhatncc.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "capNhatNCC");
            });
        }
        submenuPanel.add(btnCapnhatncc);
        submenuPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        JButton btnTimkiemncc;
        if(Beans.isDesignTime()){ btnTimkiemncc = new JButton("Tìm Kiếm (Design)"); }
        else {
            btnTimkiemncc = createSubmenuButton("Tìm Kiếm");
            btnTimkiemncc.addActionListener(e -> { // Lấy cl cục bộ
                CardLayout cl = (CardLayout) maincontent.getLayout();
                cl.show(maincontent, "timKiemNCC");
            });
        }
        submenuPanel.add(btnTimkiemncc);

        submenuPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, submenuPanel.getPreferredSize().height));
        return submenuPanel;
    }
    
    public void loadDataTableKH() {
        // gọi DAO để lấy danh sách khách hàng mới nhất
        khachHang_DAO khDAO = new khachHang_DAO();
        List<KhachHang> list = khDAO.getAllKhachHang();

        // xóa toàn bộ dữ liệu cũ trên JTable
        DefaultTableModel model = (DefaultTableModel) table_CapNhatKH.getModel();
        model.setRowCount(0);
        DefaultTableModel model_TK = (DefaultTableModel) table_tkkh.getModel();
        model.setRowCount(0);

        // thêm lại dữ liệu mới
        for (KhachHang kh : list) {
            model.addRow(new Object[]{
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getDiaChi(),
                kh.getSoDienThoai()
            });
        }
     // thêm lại dữ liệu mới
        for (KhachHang kh : list) {
        	model_TK.addRow(new Object[]{
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getDiaChi(),
                kh.getSoDienThoai()
            });
        }
    }
    
 // Hàm tạo nút cho submenu con
    private JButton createSubmenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(FONT_TEXT_FIELD.deriveFont(15f)); // Font nhỏ hơn nút chính
        button.setForeground(COLOR_SIDEBAR_TEXT);
        button.setBackground(COLOR_SIDEBAR_BG_END); // Màu nền
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setOpaque(false); // Trong suốt ban đầu
        button.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10)); // Padding
        button.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height + 5)); // Giới hạn chiều cao

        // Hiệu ứng hover đơn giản cho nút con
        button.addMouseListener(new MouseAdapter() {
             @Override
            public void mouseEntered(MouseEvent e) {
                button.setOpaque(true);
                button.setBackground(COLOR_SIDEBAR_BUTTON_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setOpaque(false);
                 button.setBackground(COLOR_SIDEBAR_BG_END); // Không quan trọng vì opaque = false
            }
        });

        return button;
    }
    
    private void loadDataToTableNV(JTable table) {
        // Lấy model của bảng được truyền vào
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa sạch dữ liệu cũ

        // Lấy danh sách nhân viên từ DAO
        nhanVien_DAO dao = new nhanVien_DAO();
        List<NhanVien> dsNV = dao.getAllNhanVien();

        // Thêm từng nhân viên vào bảng
        for (NhanVien nv : dsNV) {
            model.addRow(new Object[]{
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getNgaySinh(),
                nv.getGioiTinh(),
                nv.getChucVu().getTenChucVu(),
                nv.getSoDienThoai(),
                nv.getDiaChi(),
                nv.getAnh(),
                nv.getTaiKhoan().getTenTK()
            });
        }
    }
    private void loadImageToLabel(String path, JLabel lblAnh) {
        if (path != null && !path.trim().isEmpty()) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH);
            lblAnh.setIcon(new ImageIcon(img));
            lblAnh.setText("");
        } else {
            lblAnh.setIcon(null);
            lblAnh.setText("Chưa có ảnh");
        }
    }
    
    /**
     * Hàm để style các nút bấm cho đồng bộ
     * @param button Nút cần style
     * @param background Màu nền
     */
    private void styleButton(JButton button, Color background) {
        button.setBackground(background);
        button.setForeground(Color.WHITE); // Màu chữ trắng cho các nút có nền màu
        button.setFocusPainted(false); // Bỏ viền focus khi click
        // Đại Ca có thể bỏ dòng setBorder nếu muốn giữ kích thước gốc
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
    }

    /**
     * Hàm này áp dụng style CHUNG cho một JTable đã được tạo
     * @param table Bảng cần áp dụng style
     */
    private void applyCommonTableStyling(JTable table) {
        // ----- TÙY CHỈNH CHUNG CHO BẢNG -----
        table.setFont(FONT_TABLE_CELL);
        table.setRowHeight(30); // Chiều cao dòng
        table.setGridColor(COLOR_BORDER_LIGHT);
        table.setShowGrid(true); // Hiển thị đường kẻ
        table.setSelectionBackground(COLOR_PRIMARY_BLUE);
        table.setSelectionForeground(Color.WHITE);
        
        // ----- TÙY CHỈNH HEADER CỦA BẢNG -----
        JTableHeader header = table.getTableHeader();
        header.setFont(FONT_TABLE_HEADER);
        header.setBackground(new Color(230, 235, 240)); // Màu nền header
        header.setForeground(COLOR_TEXT_DARK);
        header.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
        header.setReorderingAllowed(false); // Không cho phép sắp xếp lại cột
        header.setResizingAllowed(true); // Cho phép thay đổi kích thước cột
    }
    
 // Hàm thêm listener cho hiệu ứng accordion (ĐÃ SỬA)
    private void addAccordionListener(JButton mainButton, JPanel submenuPanel, JPanel sidebarContainer) {
        // Áp dụng hiệu ứng hover cho nút chính này
        addHoverEffect(mainButton); // <<< GỌI Ở ĐÂY

        mainButton.addActionListener(e -> {
            // Kiểm tra xem có panel con nào đang mở không
            if (currentVisibleSubmenu != null && currentVisibleSubmenu != submenuPanel) {
                currentVisibleSubmenu.setVisible(false); // Ẩn panel con cũ
                // Reset style nút chính cũ (nếu muốn)
                 if (selectedMainMenuButton != null) {
                    selectedMainMenuButton.setOpaque(false);
                    selectedMainMenuButton.setBackground(COLOR_SIDEBAR_BG_END);
                 }
            }

            // Đảo trạng thái hiển thị của panel con được click
            boolean isVisible = !submenuPanel.isVisible();
            submenuPanel.setVisible(isVisible);

            // Cập nhật nút chính đang được chọn và style của nó
            if (isVisible) {
                selectedMainMenuButton = mainButton;
                selectedMainMenuButton.setOpaque(true); // Hiện màu nền selected
                selectedMainMenuButton.setBackground(COLOR_SIDEBAR_BUTTON_SELECTED);
                currentVisibleSubmenu = submenuPanel;
            } else {
                // Nếu đóng panel đang mở, bỏ chọn nút chính
                mainButton.setOpaque(false); // Trở lại trong suốt
                mainButton.setBackground(COLOR_SIDEBAR_BG_END);
                selectedMainMenuButton = null;
                currentVisibleSubmenu = null;
            }
            
            sidebarContainer.revalidate();
            sidebarContainer.repaint();
        });
    }
    
    private ImageIcon loadIcon(String path) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("Không tìm thấy icon: " + path);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi load icon: " + path + " - " + e.getMessage());
            return null;
        }
    }
    
}