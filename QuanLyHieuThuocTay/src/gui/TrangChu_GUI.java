package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;

public class TrangChu_GUI {

    private JFrame QuanLyHieuThuocTay;
    private JPanel maincontent;
    private JTextField text_Nhapmathuoc;
    private JTable table_timkiemthuoc;
    private JTextField text_Nhapsosdtkh;
    private JTable table_hdtam;
    private JTextField text_Nhaptiennhan;
    private JTextField text_Nhapsoluongthuoc;
    private JTable table;
    private JTextField txtMaKH;
    private JTextField txtSDT;
    private JTextField txtTenKH;
    private JTable table_tkkh;
    private JTextField txtMaKh;
    private JTextField txtSDt;
    private JTextField textField;
    private JTextField txtMKH_TK;
    private JTextField txtTenKH_TK;
    private JTable table_CapNhatKH;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TrangChu_GUI window = new TrangChu_GUI();
                    window.QuanLyHieuThuocTay.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public TrangChu_GUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        QuanLyHieuThuocTay = new JFrame();
        QuanLyHieuThuocTay.setTitle("Hệ Thống Quản Lý Hiệu Thuốc Tây");
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Admin\\eclipse-workspace\\QuanLyHieuThuoc\\image\\z7068801445103_7be0ebb233e8a4eceb10c3aceb500455.jpg");
        QuanLyHieuThuocTay.setIconImage(icon.getImage());
        QuanLyHieuThuocTay.setSize(1600, 1000);
        QuanLyHieuThuocTay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        QuanLyHieuThuocTay.setLocationRelativeTo(null); 

        JMenuBar menuBar = new JMenuBar();
        QuanLyHieuThuocTay.setJMenuBar(menuBar);
        
        //Menu Hệ Thống
        JMenu mn_Hethong = new JMenu("Hệ Thống");
        menuBar.add(mn_Hethong);

        JMenuItem mni_Taikhoan = new JMenuItem("Tài Khoản");
        mn_Hethong.add(mni_Taikhoan);
        mn_Hethong.addSeparator(); 

        JMenuItem mni_Trogiup = new JMenuItem("Trợ Giúp");
        mn_Hethong.add(mni_Trogiup);
        
        mn_Hethong.addSeparator(); 

        JMenuItem mni_Dangxuat = new JMenuItem("Đăng Xuất");
        mn_Hethong.add(mni_Dangxuat);

        //Menu Thuốc
        JMenu mn_Thuoc = new JMenu("Thuốc");
        menuBar.add(mn_Thuoc);

        JMenu mni_Themthuoc = new JMenu("Thêm");
        mn_Thuoc.add(mni_Themthuoc);

        JMenuItem mni_Themthuocthuong = new JMenuItem("Thêm");
        mni_Themthuoc.add(mni_Themthuocthuong);

        JMenuItem mni_Themthuoctheofile = new JMenuItem("Thêm File");
        mni_Themthuoc.add(mni_Themthuoctheofile);
        mn_Thuoc.addSeparator();

        JMenuItem mni_Timkiemthuoc = new JMenuItem("Tìm Kiếm");
        mni_Timkiemthuoc.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "timkiemSP");
        	}
        });
        
        mn_Thuoc.add(mni_Timkiemthuoc);
        mn_Thuoc.addSeparator();

        JMenuItem mni_Capnhatthuoc = new JMenuItem("Cập Nhật");
        mn_Thuoc.add(mni_Capnhatthuoc);
        mn_Thuoc.addSeparator();

        JMenu mni_Thongkethuoc = new JMenu("Thống Kê");
        mn_Thuoc.add(mni_Thongkethuoc);

        JMenuItem mntmNewItem_18 = new JMenuItem("New Item");
        mni_Thongkethuoc.add(mntmNewItem_18);

        JMenuItem mntmNewItem_19 = new JMenuItem("New Item");
        mni_Thongkethuoc.add(mntmNewItem_19);

        JMenuItem mntmNewItem_20 = new JMenuItem("New Item");
        mni_Thongkethuoc.add(mntmNewItem_20);
        
        //Menu Hóa đơn
        JMenu mn_Hoadon = new JMenu("Hóa Đơn");
        menuBar.add(mn_Hoadon);

        JMenuItem mni_Themhoadon = new JMenuItem("Thêm");
        mni_Themhoadon.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "themHoaDon");
        	}
        });
        mn_Hoadon.add(mni_Themhoadon);
        
        mn_Hoadon.addSeparator();

        JMenuItem mni_Timkiemhoadon = new JMenuItem("Tìm Kiếm");
        mni_Timkiemhoadon.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "timKiemHoaDon");
        	}
        });
        mn_Hoadon.add(mni_Timkiemhoadon);
        
        mn_Hoadon.addSeparator();

        JMenuItem mni_Thongkehoadon = new JMenuItem("Thống Kê");
        mn_Hoadon.add(mni_Thongkehoadon);
        
        // Menu Nhân Viên
        JMenu mn_Nhanvien = new JMenu("Nhân Viên");
        menuBar.add(mn_Nhanvien);

        JMenuItem mni_Themnv = new JMenuItem("Thêm");
        mn_Nhanvien.add(mni_Themnv);
        mn_Nhanvien.addSeparator();

        JMenuItem mni_Capnhatnv = new JMenuItem("Cập Nhật");
        mn_Nhanvien.add(mni_Capnhatnv);
        
        mn_Nhanvien.addSeparator();

        JMenuItem mni_Timkiemnv = new JMenuItem("Tìm Kiếm");
        mn_Nhanvien.add(mni_Timkiemnv);
        
        // Menu Nhà Cung Cấp
        JMenu mn_Nhacungcap = new JMenu("Nhà Cung Cấp");
        menuBar.add(mn_Nhacungcap);

        JMenuItem mni_Themncc = new JMenuItem("Thêm");
        mn_Nhacungcap.add(mni_Themncc);
        mn_Nhacungcap.addSeparator();

        JMenuItem mni_Capnhatncc = new JMenuItem("Cập Nhật");
        mn_Nhacungcap.add(mni_Capnhatncc);

        mn_Nhacungcap.addSeparator();

        JMenuItem mni_Timkiemncc = new JMenuItem("Tìm Kiếm");
        mn_Nhacungcap.add(mni_Timkiemncc);

        // Menu Khách Hàng
        JMenu mn_Khachhang = new JMenu("Khách Hàng");
        menuBar.add(mn_Khachhang);

        JMenuItem mni_Themkh = new JMenuItem("Thêm");
        mni_Themkh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// 1. Tạo một đối tượng của cửa sổ ThemKH_GUI
                //    Truyền "QuanLyHieuThuocTay" (JFrame cha) vào làm tham số
                ThemKH_GUI themKhDialog = new ThemKH_GUI(QuanLyHieuThuocTay);
                
                // 2. Hiển thị cửa sổ đó lên
                themKhDialog.setVisible(true);
        	}
        });
        mn_Khachhang.add(mni_Themkh);
        mn_Khachhang.addSeparator();

        JMenuItem mni_Capnhatkh = new JMenuItem("Cập Nhật");
        mni_Capnhatkh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "capNhatKH");
        	}
        });
        mn_Khachhang.add(mni_Capnhatkh);
        
        mn_Khachhang.addSeparator();

        JMenuItem mni_Timkiemkh = new JMenuItem("Tìm Kiếm");
        mni_Timkiemkh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "timkiemkh");
        	}
        });
        mn_Khachhang.add(mni_Timkiemkh);
        
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
        
        JPanel pn_Themhoadon_west = new JPanel();
        pn_Themhoadon_west.setBounds(0, 0, 600, 939);
        pn_Themhoadon.add(pn_Themhoadon_west);
        pn_Themhoadon_west.setLayout(null);
        
        JPanel pn_timkiemthuoc = new JPanel();
        pn_timkiemthuoc.setBounds(10, 11, 579, 195);
        pn_timkiemthuoc.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm Ki\u1EBFm", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pn_Themhoadon_west.add(pn_timkiemthuoc);
        pn_timkiemthuoc.setLayout(null);
        
        JLabel lbl_Mathuoc = new JLabel("Mã Thuốc :");
        lbl_Mathuoc.setBounds(30, 29, 115, 28);
        lbl_Mathuoc.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pn_timkiemthuoc.add(lbl_Mathuoc);
        
        JLabel lbl_Kethuoc = new JLabel("Kệ Thuốc :");
        lbl_Kethuoc.setBounds(30, 68, 115, 28);
        lbl_Kethuoc.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pn_timkiemthuoc.add(lbl_Kethuoc);
        
        JLabel lbl_Tenthuoc = new JLabel("Tên Thuốc :");
        lbl_Tenthuoc.setBounds(30, 107, 121, 28);
        lbl_Tenthuoc.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pn_timkiemthuoc.add(lbl_Tenthuoc);
        
        text_Nhapmathuoc = new JTextField();
        text_Nhapmathuoc.setBounds(154, 29, 415, 28);
        pn_timkiemthuoc.add(text_Nhapmathuoc);
        text_Nhapmathuoc.setColumns(10);
        
        JComboBox cb_loctenthuoc = new JComboBox();
        cb_loctenthuoc.setBounds(154, 109, 415, 28);
        pn_timkiemthuoc.add(cb_loctenthuoc);
        
        JButton btn_lammoitkthuoc = new JButton("Làm Mới");
        btn_lammoitkthuoc.setBounds(217, 153, 136, 31);
        btn_lammoitkthuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn_timkiemthuoc.add(btn_lammoitkthuoc);
        
        JComboBox cb_lockethuoc = new JComboBox();
        cb_lockethuoc.setBounds(153, 68, 415, 28);
        pn_timkiemthuoc.add(cb_lockethuoc);
        
        JScrollPane scP_timkiemthuoc = new JScrollPane();
        scP_timkiemthuoc.setBounds(10, 217, 579, 641);
        pn_Themhoadon_west.add(scP_timkiemthuoc);
        
        table_timkiemthuoc = new JTable();
        table_timkiemthuoc.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 Thu\u1ED1c", "K\u1EC7 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "\u0110\u01A1n Gi\u00E1", "S\u1ED1 L\u01B0\u1EE3ng"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, String.class, Double.class, Integer.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        table_timkiemthuoc.getColumnModel().getColumn(0).setPreferredWidth(90);
        table_timkiemthuoc.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_timkiemthuoc.getColumnModel().getColumn(2).setPreferredWidth(200);
        scP_timkiemthuoc.setViewportView(table_timkiemthuoc);
        
        JButton btn_Xemchitiet = new JButton("Xem Chi Tiết");
        btn_Xemchitiet.setBounds(195, 873, 180, 47);
        pn_Themhoadon_west.add(btn_Xemchitiet);
        btn_Xemchitiet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        
        JPanel pn_Themhoadon_east = new JPanel();
        pn_Themhoadon_east.setBounds(701, 0, 883, 939);
        pn_Themhoadon.add(pn_Themhoadon_east);
        pn_Themhoadon_east.setLayout(null);
        
        JLabel lbl_Laphoadon = new JLabel("LẬP HÓA ĐƠN");
        lbl_Laphoadon.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbl_Laphoadon.setBounds(10, 11, 296, 59);
        pn_Themhoadon_east.add(lbl_Laphoadon);
        
        JButton btn_xemphieudatthuoc = new JButton("Phiếu Đặt Thuốc");
        btn_xemphieudatthuoc.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DsPhieuDatThuoc_GUI DanhsachphieudatthuocDialog =new DsPhieuDatThuoc_GUI(QuanLyHieuThuocTay);
                
                // 2. Hiển thị cửa sổ đó lên
        		DanhsachphieudatthuocDialog.setVisible(true);
        	}
        });
        btn_xemphieudatthuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_xemphieudatthuoc.setBounds(479, 64, 186, 43);
        pn_Themhoadon_east.add(btn_xemphieudatthuoc);
        
        JButton btn_Themkhachhangmoi = new JButton("Thêm Khách Hàng");
        btn_Themkhachhangmoi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// 1. Tạo một đối tượng của cửa sổ ThemKH_GUI
                //    Truyền "QuanLyHieuThuocTay" (JFrame cha) vào làm tham số
                ThemKH_GUI themKhDialog = new ThemKH_GUI(QuanLyHieuThuocTay);
                
                // 2. Hiển thị cửa sổ đó lên
                themKhDialog.setVisible(true);
        	}
        });
        btn_Themkhachhangmoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_Themkhachhangmoi.setBounds(675, 64, 198, 43);
        pn_Themhoadon_east.add(btn_Themkhachhangmoi);
        
        JPanel pn_Hoadonbanle = new JPanel();
        pn_Hoadonbanle.setBorder(new TitledBorder(null, "Th\u00F4ng Tin H\u00F3a \u0110\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pn_Hoadonbanle.setBounds(10, 118, 863, 744);
        pn_Themhoadon_east.add(pn_Hoadonbanle);
        pn_Hoadonbanle.setLayout(null);
        
        JLabel lbl_Hoadonbanle = new JLabel("Hóa Đơn Bán Lẻ");
        lbl_Hoadonbanle.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbl_Hoadonbanle.setBounds(270, 11, 291, 47);
        pn_Hoadonbanle.add(lbl_Hoadonbanle);
        
        JLabel lbl_Ngaylaphoadon = new JLabel("Ngày Lập : ");
        lbl_Ngaylaphoadon.setForeground(new Color(255, 0, 0));
        lbl_Ngaylaphoadon.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lbl_Ngaylaphoadon.setBounds(585, 55, 100, 25);
        pn_Hoadonbanle.add(lbl_Ngaylaphoadon);
        
        JLabel lbl_hienngaylaphoadon = new JLabel("09/09/2025");
        lbl_hienngaylaphoadon.setForeground(new Color(255, 0, 0));
        lbl_hienngaylaphoadon.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lbl_hienngaylaphoadon.setBounds(695, 55, 100, 25);
        pn_Hoadonbanle.add(lbl_hienngaylaphoadon);
        
        JLabel lbl_Mahd = new JLabel("Mã Hóa Đơn :");
        lbl_Mahd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Mahd.setBounds(43, 91, 114, 25);
        pn_Hoadonbanle.add(lbl_Mahd);
        
        JLabel lbl_Nhanvien = new JLabel("Nhân Viên :");
        lbl_Nhanvien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Nhanvien.setBounds(43, 140, 114, 25);
        pn_Hoadonbanle.add(lbl_Nhanvien);
        
        JLabel lbl_Sodienthoai = new JLabel("SĐT Khách Hàng :");
        lbl_Sodienthoai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Sodienthoai.setBounds(410, 91, 160, 25);
        pn_Hoadonbanle.add(lbl_Sodienthoai);
        
        JLabel lbl_Khachhang = new JLabel("Khách Hàng :");
        lbl_Khachhang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Khachhang.setBounds(456, 140, 114, 25);
        pn_Hoadonbanle.add(lbl_Khachhang);
        
        text_Nhapsosdtkh = new JTextField();
        text_Nhapsosdtkh.setBounds(585, 84, 197, 43);
        pn_Hoadonbanle.add(text_Nhapsosdtkh);
        text_Nhapsosdtkh.setColumns(10);
        
        JButton btn_timsdtkh = new JButton("Tìm");
        btn_timsdtkh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_timsdtkh.setBounds(788, 82, 65, 45);
        pn_Hoadonbanle.add(btn_timsdtkh);
        
        JButton btn_suaslthuoc = new JButton("Sửa Số Lượng");
        btn_suaslthuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_suaslthuoc.setBounds(594, 176, 160, 43);
        pn_Hoadonbanle.add(btn_suaslthuoc);
        
        JButton btn_xoathuockhoihd = new JButton("Xóa");
        btn_xoathuockhoihd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_xoathuockhoihd.setBounds(764, 176, 89, 43);
        pn_Hoadonbanle.add(btn_xoathuockhoihd);
        
        JLabel lbl_hienmahd = new JLabel("HD00000001");
        lbl_hienmahd.setForeground(new Color(255, 0, 0));
        lbl_hienmahd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_hienmahd.setBounds(167, 91, 209, 25);
        pn_Hoadonbanle.add(lbl_hienmahd);
        
        JLabel lbl_Hientennv = new JLabel("Nguyễn Trung Kiên");
        lbl_Hientennv.setForeground(new Color(255, 0, 0));
        lbl_Hientennv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Hientennv.setBounds(167, 142, 209, 25);
        pn_Hoadonbanle.add(lbl_Hientennv);
        
        JLabel lbl_Hientenkh = new JLabel("Nguyễn Ngô Đức Mạnh");
        lbl_Hientenkh.setForeground(new Color(255, 0, 0));
        lbl_Hientenkh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Hientenkh.setBounds(585, 140, 268, 25);
        pn_Hoadonbanle.add(lbl_Hientenkh);
        
        JScrollPane scP_Hdtam = new JScrollPane();
        scP_Hdtam.setBounds(10, 230, 843, 376);
        pn_Hoadonbanle.add(scP_Hdtam);
        
        table_hdtam = new JTable();
        table_hdtam.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Ti\u1EC1n", "Th\u00E0nh Ti\u1EC1n"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, Object.class, Object.class, Object.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        scP_Hdtam.setViewportView(table_hdtam);
        
        JLabel lbl_tongtienhang = new JLabel("Tổng Tiền Hàng :");
        lbl_tongtienhang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tongtienhang.setBounds(10, 617, 147, 25);
        pn_Hoadonbanle.add(lbl_tongtienhang);
        
        JLabel lbl_Tiennhan = new JLabel("Tiền Nhận :");
        lbl_Tiennhan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Tiennhan.setBounds(10, 669, 100, 25);
        pn_Hoadonbanle.add(lbl_Tiennhan);
        
        JLabel lbl_Thue = new JLabel("Thuế (10% VAT) :");
        lbl_Thue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Thue.setBounds(311, 617, 160, 25);
        pn_Hoadonbanle.add(lbl_Thue);
        
        JLabel lbl_Tongcong = new JLabel("Tổng Cộng :");
        lbl_Tongcong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Tongcong.setBounds(600, 617, 108, 25);
        pn_Hoadonbanle.add(lbl_Tongcong);
        
        JLabel lbl_Tienthua = new JLabel("Tiền Thừa :");
        lbl_Tienthua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Tienthua.setBounds(434, 669, 95, 25);
        pn_Hoadonbanle.add(lbl_Tienthua);
        
        text_Nhaptiennhan = new JTextField();
        text_Nhaptiennhan.setBounds(120, 666, 246, 34);
        pn_Hoadonbanle.add(text_Nhaptiennhan);
        text_Nhaptiennhan.setColumns(10);
        
        JLabel lbl_Hientienhang = new JLabel("10000000");
        lbl_Hientienhang.setForeground(new Color(255, 0, 0));
        lbl_Hientienhang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Hientienhang.setBounds(167, 617, 134, 25);
        pn_Hoadonbanle.add(lbl_Hientienhang);
        
        JLabel lbl_Hienthue = new JLabel("10000000");
        lbl_Hienthue.setForeground(new Color(255, 0, 0));
        lbl_Hienthue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Hienthue.setBounds(481, 617, 109, 25);
        pn_Hoadonbanle.add(lbl_Hienthue);
        
        JLabel lbl_Hientongcong = new JLabel("10000000");
        lbl_Hientongcong.setForeground(new Color(255, 0, 0));
        lbl_Hientongcong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Hientongcong.setBounds(718, 617, 135, 25);
        pn_Hoadonbanle.add(lbl_Hientongcong);
        
        JLabel lbl_Hientienthua = new JLabel("10000000");
        lbl_Hientienthua.setForeground(new Color(255, 0, 0));
        lbl_Hientienthua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Hientienthua.setBounds(539, 669, 146, 25);
        pn_Hoadonbanle.add(lbl_Hientienthua);
        
        JButton btn_Huyhoadon = new JButton("Hủy Hóa Đơn");
        btn_Huyhoadon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_Huyhoadon.setBounds(10, 873, 180, 43);
        pn_Themhoadon_east.add(btn_Huyhoadon);
        
        JButton btn_Themthuocvaophieudat = new JButton("Thêm Vào Phiếu Đặt Thuốc");
        btn_Themthuocvaophieudat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_Themthuocvaophieudat.setBounds(200, 873, 290, 43);
        pn_Themhoadon_east.add(btn_Themthuocvaophieudat);
        
        JButton btn_Xuathoadon = new JButton("Xuất Hóa Đơn");
        btn_Xuathoadon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_Xuathoadon.setBounds(500, 873, 177, 43);
        pn_Themhoadon_east.add(btn_Xuathoadon);
        
        JButton btn_Thanhtoanhoadon = new JButton("Thanh Toán");
        btn_Thanhtoanhoadon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_Thanhtoanhoadon.setBounds(687, 873, 186, 43);
        pn_Themhoadon_east.add(btn_Thanhtoanhoadon);
        
        text_Nhapsoluongthuoc = new JTextField();
        text_Nhapsoluongthuoc.setBounds(610, 567, 81, 37);
        pn_Themhoadon.add(text_Nhapsoluongthuoc);
        text_Nhapsoluongthuoc.setColumns(10);
        
        JButton btn_addthuocvaohoadon = new JButton(">>");
        btn_addthuocvaohoadon.setFont(new Font("Times New Roman", Font.BOLD, 40));
        btn_addthuocvaohoadon.setBounds(610, 506, 81, 50);
        pn_Themhoadon.add(btn_addthuocvaohoadon);
        
        JLabel lbl_Nhapsoluongthuoc = new JLabel("<html>Nhập<br>Số<br>Lượng</html>");
        lbl_Nhapsoluongthuoc.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Nhapsoluongthuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Nhapsoluongthuoc.setBounds(610, 615, 81, 84);
        pn_Themhoadon.add(lbl_Nhapsoluongthuoc);
        
        JPanel pn_tkhd = new JPanel();
        maincontent.add(pn_tkhd, "timKiemHoaDon");
        
        JPanel pn_Timkiemsp = new JPanel();
        maincontent.add(pn_Timkiemsp, "timkiemSP");
        pn_Timkiemsp.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("TÌM KIẾM THUỐC");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel.setBounds(638, 11, 260, 46);
        pn_Timkiemsp.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Kệ Thuốc :");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(10, 62, 135, 30);
        pn_Timkiemsp.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Tên Thuốc :");
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(10, 113, 135, 30);
        pn_Timkiemsp.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("Nhà Cung Cấp :");
        lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(838, 62, 135, 30);
        pn_Timkiemsp.add(lblNewLabel_1_2);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(111, 62, 529, 30);
        pn_Timkiemsp.add(comboBox);
        
        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setBounds(111, 115, 529, 30);
        pn_Timkiemsp.add(comboBox_1);
        
        JComboBox comboBox_2 = new JComboBox();
        comboBox_2.setBounds(983, 62, 540, 30);
        pn_Timkiemsp.add(comboBox_2);
        
        JButton btnNewButton = new JButton("Xem Chi Tiết");
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton.setBounds(983, 113, 179, 36);
        pn_Timkiemsp.add(btnNewButton);
        
        JButton btnLmMi = new JButton("Làm Mới");
        btnLmMi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnLmMi.setBounds(1388, 110, 135, 36);
        pn_Timkiemsp.add(btnLmMi);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 167, 1584, 772);
        pn_Timkiemsp.add(panel);
        panel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 0, 1564, 772);
        panel.add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        table.getColumnModel().getColumn(2).setPreferredWidth(65);
        scrollPane.setViewportView(table);
        
        JPanel pn_TKKhachHang = new JPanel();
        maincontent.add(pn_TKKhachHang, "timkiemkh");
        pn_TKKhachHang.setLayout(null);
        
        JPanel pnl_KH_north = new JPanel();
        pnl_KH_north.setBounds(0, 0, 1584, 246);
        pn_TKKhachHang.add(pnl_KH_north);
        pnl_KH_north.setLayout(null);
        
        JLabel lblSDT = new JLabel("Số điện thoại : ");
        lblSDT.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblSDT.setBounds(38, 130, 125, 24);
        pnl_KH_north.add(lblSDT);
        
        JLabel lblTimKiemKH = new JLabel("TÌM KIẾM KHÁCH HÀNG");
        lblTimKiemKH.setBounds(561, 11, 454, 43);
        pnl_KH_north.add(lblTimKiemKH);
        lblTimKiemKH.setFont(new Font("Times New Roman", Font.BOLD, 36));
        
        JLabel lblGioiTinh = new JLabel("Giới tính :");
        lblGioiTinh.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblGioiTinh.setBounds(1122, 64, 96, 24);
        pnl_KH_north.add(lblGioiTinh);
        
        JLabel lblTenKH = new JLabel("Tên khách hàng : ");
        lblTenKH.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTenKH.setBounds(561, 64, 156, 24);
        pnl_KH_north.add(lblTenKH);
        
        JLabel lblMaKH = new JLabel("Mã khách hàng :");
        lblMaKH.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblMaKH.setBounds(38, 64, 148, 24);
        pnl_KH_north.add(lblMaKH);
        
        JLabel lblDiachi = new JLabel("Địa chỉ :");
        lblDiachi.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblDiachi.setBounds(565, 130, 79, 24);
        pnl_KH_north.add(lblDiachi);
        
        txtMaKH = new JTextField();
        txtMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtMaKH.setBounds(196, 64, 330, 32);
        pnl_KH_north.add(txtMaKH);
        txtMaKH.setColumns(10);
        
        txtSDT = new JTextField();
        txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtSDT.setColumns(10);
        txtSDT.setBounds(196, 127, 330, 32);
        pnl_KH_north.add(txtSDT);
        
        txtTenKH = new JTextField();
        txtTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtTenKH.setColumns(10);
        txtTenKH.setBounds(725, 64, 354, 32);
        pnl_KH_north.add(txtTenKH);
        
        JComboBox cboGioiTinh = new JComboBox();
        cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
        cboGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cboGioiTinh.setBounds(1228, 64, 96, 32);
        pnl_KH_north.add(cboGioiTinh);
        
        JComboBox cboTinh = new JComboBox();
        cboTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cboTinh.setModel(new DefaultComboBoxModel(new String[] {"Tỉnh/Thành phố"}));
        cboTinh.setBounds(660, 127, 180, 32);
        pnl_KH_north.add(cboTinh);
        
        JComboBox cboHuyen = new JComboBox();
        cboHuyen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cboHuyen.setModel(new DefaultComboBoxModel(new String[] {"Quận/Huyện"}));
        cboHuyen.setBounds(899, 128, 180, 32);
        pnl_KH_north.add(cboHuyen);
        
        JComboBox cboXa = new JComboBox();
        cboXa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cboXa.setModel(new DefaultComboBoxModel(new String[] {"Xã/Phường"}));
        cboXa.setBounds(1144, 128, 180, 32);
        pnl_KH_north.add(cboXa);
        
        JButton btnLammoi = new JButton("Làm mới");
        btnLammoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnLammoi.setBounds(1144, 184, 108, 32);
        pnl_KH_north.add(btnLammoi);
        
        JPanel pnl_KH_south = new JPanel();
        pnl_KH_south.setBounds(0, 244, 1584, 695);
        pn_TKKhachHang.add(pnl_KH_south);
        pnl_KH_south.setLayout(null);
        
        JScrollPane scrollPane_tkkh = new JScrollPane();
        scrollPane_tkkh.setBounds(0, 0, 1584, 696);
        pnl_KH_south.add(scrollPane_tkkh);
        
        table_tkkh = new JTable();
        table_tkkh.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 kh\u00E1ch h\u00E0ng", "T\u00EAn kh\u00E1ch h\u00E0ng", "Gi\u1EDBi t\u00EDnh", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9"
        	}
        ));
        table_tkkh.getColumnModel().getColumn(0).setPreferredWidth(99);
        table_tkkh.getColumnModel().getColumn(1).setPreferredWidth(92);
        table_tkkh.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        table_tkkh.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20));
        table_tkkh.setRowHeight(30);

        scrollPane_tkkh.setViewportView(table_tkkh);
        
        JPanel pn_CapnhatKh = new JPanel();
        maincontent.add(pn_CapnhatKh, "capNhatKH");
        pn_CapnhatKh.setLayout(null);
        
        JPanel pnlNorth = new JPanel();
        pnlNorth.setBounds(10, 11, 1564, 297);
        pn_CapnhatKh.add(pnlNorth);
        pnlNorth.setLayout(null);
        
        JLabel lblTitle = new JLabel("CẬP NHẬT KHÁCH HÀNG");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 36));
        lblTitle.setBounds(548, 11, 452, 43);
        pnlNorth.add(lblTitle);
        
        JLabel lblMaKh = new JLabel("Mã khách hàng :");
        lblMaKh.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblMaKh.setBounds(192, 91, 141, 25);
        pnlNorth.add(lblMaKh);
        
        JLabel lblTenKh = new JLabel("Tên khách hàng :");
        lblTenKh.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTenKh.setBounds(686, 91, 163, 25);
        pnlNorth.add(lblTenKh);
        
        JLabel lblSdt = new JLabel("Số điện thoại :");
        lblSdt.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblSdt.setBounds(192, 146, 141, 25);
        pnlNorth.add(lblSdt);
        
        JLabel lblGT = new JLabel("Giới tính :");
        lblGT.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblGT.setBounds(686, 152, 94, 25);
        pnlNorth.add(lblGT);
        
        JLabel lblDC = new JLabel("Địa chỉ  :");
        lblDC.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblDC.setBounds(192, 203, 163, 25);
        pnlNorth.add(lblDC);
        
        txtMaKh = new JTextField();
        txtMaKh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtMaKh.setBounds(354, 89, 301, 33);
        pnlNorth.add(txtMaKh);
        txtMaKh.setColumns(10);
        
        txtSDt = new JTextField();
        txtSDt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtSDt.setColumns(10);
        txtSDt.setBounds(354, 144, 301, 33);
        pnlNorth.add(txtSDt);
        
        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textField.setColumns(10);
        textField.setBounds(859, 89, 488, 33);
        pnlNorth.add(textField);
        
        JComboBox cboGT = new JComboBox();
        cboGT.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
        cboGT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cboGT.setBounds(859, 148, 80, 32);
        pnlNorth.add(cboGT);
        
        JComboBox cboDC_tinh = new JComboBox();
        cboDC_tinh.setModel(new DefaultComboBoxModel(new String[] {"Tỉnh / Thành phố"}));
        cboDC_tinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cboDC_tinh.setBounds(354, 199, 235, 32);
        pnlNorth.add(cboDC_tinh);
        
        JComboBox cboDC_huyen = new JComboBox();
        cboDC_huyen.setModel(new DefaultComboBoxModel(new String[] {"Quận/Huyện"}));
        cboDC_huyen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cboDC_huyen.setBounds(623, 199, 235, 32);
        pnlNorth.add(cboDC_huyen);
        
        JComboBox cboDC_xa = new JComboBox();
        cboDC_xa.setModel(new DefaultComboBoxModel(new String[] {"Xã/Phường"}));
        cboDC_xa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cboDC_xa.setBounds(885, 199, 235, 32);
        pnlNorth.add(cboDC_xa);
        
        JButton btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnCapNhat.setBounds(1011, 255, 118, 31);
        pnlNorth.add(btnCapNhat);
        
        JButton btnKhoiphuc = new JButton("Khôi phục");
        btnKhoiphuc.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnKhoiphuc.setBounds(859, 255, 118, 31);
        pnlNorth.add(btnKhoiphuc);
        
        JPanel pnlTimkiem = new JPanel();
        pnlTimkiem.setBounds(191, 307, 1191, 84);
        
        Font titleFont = new Font("Tahoma", Font.BOLD, 20);
        TitledBorder border = BorderFactory.createTitledBorder("Tìm kiếm");
        border.setTitleFont(titleFont);
        pnlTimkiem.setBorder(border);
        
        pn_CapnhatKh.add(pnlTimkiem);
        pnlTimkiem.setLayout(null);
        
        JLabel lblMaKh_TK = new JLabel("Mã khách hàng :");
        lblMaKh_TK.setBounds(10, 24, 140, 24);
        lblMaKh_TK.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pnlTimkiem.add(lblMaKh_TK);
        
        txtMKH_TK = new JTextField();
        txtMKH_TK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtMKH_TK.setColumns(10);
        txtMKH_TK.setBounds(174, 22, 301, 33);
        pnlTimkiem.add(txtMKH_TK);
        
        JLabel lblTenKh_TK = new JLabel("Tên khách hàng :");
        lblTenKh_TK.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTenKh_TK.setBounds(514, 24, 163, 25);
        pnlTimkiem.add(lblTenKh_TK);
        
        txtTenKH_TK = new JTextField();
        txtTenKH_TK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtTenKH_TK.setColumns(10);
        txtTenKH_TK.setBounds(687, 22, 301, 33);
        pnlTimkiem.add(txtTenKH_TK);
        
        JButton btnLammoi_CNKH = new JButton("Làm mới");
        btnLammoi_CNKH.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnLammoi_CNKH.setBounds(1039, 24, 118, 31);
        pnlTimkiem.add(btnLammoi_CNKH);
        
        JPanel pnlSouth = new JPanel();
        pnlSouth.setBounds(10, 394, 1564, 534);
        pn_CapnhatKh.add(pnlSouth);
        pnlSouth.setLayout(null);
        
        JScrollPane scrollPane_CapNhatKH = new JScrollPane();
        scrollPane_CapNhatKH.setBounds(0, 0, 1564, 534);
        pnlSouth.add(scrollPane_CapNhatKH);
        
        table_CapNhatKH = new JTable();
        table_CapNhatKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        table_CapNhatKH.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20));
        table_CapNhatKH.setRowHeight(30);
        table_CapNhatKH.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 kh\u00E1ch h\u00E0ng", "T\u00EAn Kh\u00E1ch h\u00E0ng", "Gi\u1EDBi t\u00EDnh", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9"
        	}
        ));
        scrollPane_CapNhatKH.setViewportView(table_CapNhatKH);
        
        

    }
}