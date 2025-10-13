package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

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
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

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
    private JTextField text_cntmt;
    private JTextField text_cnttt;
    private JTextField text_cntsl;
    private JTextField text_cntgn;
    private JTextField text_cntgb;
    private JTextField text_cnthsd;
    private JTextField text_cnt_tkmt;
    private JTextField text_cnt_tktt;
    private JTable table_Capnhatthuoc;
    private JTextField text_ttmt;
    private JTextField text_tttt;
    private JTextField text_ttsl;
    private JTextField text_ttgn;
    private JTextField text_ttgb;
    private JTextField text_tthsd;
    private JTable table_1;
    private JTable table_2;
    private JTable table_3;
    private JTable table_4;
    private JTable table_5;
    private JTextField text_tkhd_TenKH;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTable table_6;
    private JTextField textField_7;
    private JTable table_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTable table_8;
    private JTable table_10;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTextField textField_12;
    private JTextField textField_13;
    private JTable table_9;
    private JTable table_11;
    private JTable table_12;
    private JTextField txtMaNhanVien;
    private JTextField txtTenNhanVien;
    private JTextField txtSoDienThoai;
    private JComboBox<String> cmbGioiTinh;
    private JComboBox<String> cmbChucVu;
    private JComboBox<String> cmbTaiKhoan;
    private JDateChooser dateChooserNgaySinh;
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
    private JTextField textField_1;
    private JTextField txtHuyen_CNNV;
    private JTextField txtTenNV_TK_CNNV;
    private JTextField textField_2;
    private JTextField text_tkhd_Mahd;
    private JTable table_CNNV;

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
        mni_Themthuocthuong.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "Themthuocthuong");
        	}
        });
        mni_Themthuoc.add(mni_Themthuocthuong);

        JMenuItem mni_Themthuoctheofile = new JMenuItem("Thêm File");
        mni_Themthuoctheofile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "Themthuocfile");
    		}
        });
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
        mni_Capnhatthuoc.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "Capnhatthuoc");
        	}
        });
        mn_Thuoc.add(mni_Capnhatthuoc);
        mn_Thuoc.addSeparator();

        JMenu mni_Thongkethuoc = new JMenu("Thống Kê");
        mn_Thuoc.add(mni_Thongkethuoc);

        JMenuItem mntmNewItem_18 = new JMenuItem("Thuốc Sắp Hết Hạn");
        mntmNewItem_18.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "Thuocsaphethan");
        	}
        });
        mni_Thongkethuoc.add(mntmNewItem_18);

        JMenuItem mntmNewItem_19 = new JMenuItem("Thuốc Bán Chạy");
        mntmNewItem_19.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "Thuocbanchay");
        	}
        });
        mni_Thongkethuoc.add(mntmNewItem_19);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Thuốc Sắp Hết Hàng");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "Thuocsaphethang");
        	}
        });
        mni_Thongkethuoc.add(mntmNewMenuItem);
        
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
        mni_Thongkehoadon.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "ThongkeHD");
        	}
        });
        mn_Hoadon.add(mni_Thongkehoadon);
        
        // Menu Nhân Viên
        JMenu mn_Nhanvien = new JMenu("Nhân Viên");
        menuBar.add(mn_Nhanvien);
        JMenuItem mni_Themnv = new JMenuItem("Thêm");

        mni_Themnv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "themNV");
            }
        });

        mn_Nhanvien.add(mni_Themnv);
        mn_Nhanvien.addSeparator();

        JMenuItem mni_Capnhatnv = new JMenuItem("Cập Nhật");
        mni_Capnhatnv.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "capnhatnv");
        	}
        });
        mn_Nhanvien.add(mni_Capnhatnv);
        
        mn_Nhanvien.addSeparator();

        JMenuItem mni_Timkiemnv = new JMenuItem("Tìm Kiếm");
        mni_Timkiemnv.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout) maincontent.getLayout();
        	    cl.show(maincontent, "timkiemnv");
        	}
        });
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
        pn_tkhd.setLayout(null);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 0, 1584, 161);
        pn_tkhd.add(panel_3);
        panel_3.setLayout(null);
        
        JLabel lblNewLabel_5 = new JLabel("Tìm Kiếm Hóa Đơn");
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel_5.setBounds(618, 5, 338, 47);
        panel_3.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Tên Khách Hàng :");
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_6.setBounds(43, 63, 148, 30);
        panel_3.add(lblNewLabel_6);
        
        JLabel lblNewLabel_6_1 = new JLabel("Mã Hóa Đơn :");
        lblNewLabel_6_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_6_1.setBounds(43, 104, 148, 30);
        panel_3.add(lblNewLabel_6_1);
        
        JLabel lblNewLabel_6_2 = new JLabel("SĐT Khách Hàng :");
        lblNewLabel_6_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_6_2.setBounds(472, 63, 154, 30);
        panel_3.add(lblNewLabel_6_2);
        
        JLabel lblNewLabel_6_3 = new JLabel("Tên Nhân Viên :");
        lblNewLabel_6_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_6_3.setBounds(924, 63, 136, 30);
        panel_3.add(lblNewLabel_6_3);
        
        JLabel lblNewLabel_6_4 = new JLabel("SĐT Nhân Viên :");
        lblNewLabel_6_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_6_4.setBounds(472, 104, 154, 30);
        panel_3.add(lblNewLabel_6_4);
        
        JLabel lblNewLabel_6_5 = new JLabel("Ngày Lập HĐ :");
        lblNewLabel_6_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_6_5.setBounds(924, 104, 136, 30);
        panel_3.add(lblNewLabel_6_5);
        
        text_tkhd_TenKH = new JTextField();
        text_tkhd_TenKH.setBounds(194, 63, 268, 30);
        panel_3.add(text_tkhd_TenKH);
        text_tkhd_TenKH.setColumns(10);
        
        text_tkhd_Mahd = new JTextField();
        text_tkhd_Mahd.setColumns(10);
        text_tkhd_Mahd.setBounds(194, 104, 268, 30);
        panel_3.add(text_tkhd_Mahd);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(636, 63, 268, 30);
        panel_3.add(textField_3);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(636, 104, 268, 30);
        panel_3.add(textField_4);
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(1070, 63, 268, 30);
        panel_3.add(textField_5);
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(1070, 104, 268, 30);
        panel_3.add(textField_6);
        
        JButton btnNewButton_3 = new JButton("Làm Mới");
        btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_3.setBounds(1413, 63, 161, 30);
        panel_3.add(btnNewButton_3);
        
        JButton btnNewButton_3_1 = new JButton("Xem Chi Tiết");
        btnNewButton_3_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		XemchitietHD_GUI XemchitietHD =new XemchitietHD_GUI(QuanLyHieuThuocTay);
                
                // 2. Hiển thị cửa sổ đó lên
        		XemchitietHD.setVisible(true);
        	}
        });
        btnNewButton_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_3_1.setBounds(1413, 104, 161, 30);
        panel_3.add(btnNewButton_3_1);
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(10, 172, 1564, 767);
        pn_tkhd.add(scrollPane_3);
        
        table_6 = new JTable();
        table_6.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 H\u00F3a \u0110\u01A1n", "M\u00E3 Kh\u00E1ch H\u00E0ng", "Ng\u00E0y L\u1EADp HD", "T\u00EAn Kh\u00E1ch H\u00E0ng", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i KH", "T\u00EAn Nh\u00E2n Vi\u00EAn", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i NV", "T\u1ED5ng Ti\u1EC1n"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, String.class, String.class, String.class, String.class, String.class, Object.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        scrollPane_3.setViewportView(table_6);
        
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
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ChiTietThuoc_GUI Chitietphieudatthuoc = new ChiTietThuoc_GUI(QuanLyHieuThuocTay);
                
                // 2. Hiển thị cửa sổ đó lên
                Chitietphieudatthuoc.setVisible(true);
        	}
        });
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
        scrollPane_tkkh.setBounds(0, 2, 1584, 696);
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
        
        JPanel pn_Capnhatthuoc = new JPanel();
        maincontent.add(pn_Capnhatthuoc, "Capnhatthuoc");
        pn_Capnhatthuoc.setLayout(null);
        
        JPanel pn_Capnhatthuoc_nhaptt = new JPanel();
        pn_Capnhatthuoc_nhaptt.setBounds(0, 0, 1584, 429);
        pn_Capnhatthuoc.add(pn_Capnhatthuoc_nhaptt);
        pn_Capnhatthuoc_nhaptt.setLayout(null);
        
        JPanel pn_chuaanh = new JPanel();
        pn_chuaanh.setBorder(new LineBorder(new Color(0, 0, 0)));
        pn_chuaanh.setBounds(34, 51, 330, 300);
        pn_Capnhatthuoc_nhaptt.add(pn_chuaanh);
        pn_chuaanh.setLayout(null);
        
        JLabel lb_Chuaanh = new JLabel("New label");
        lb_Chuaanh.setBounds(0, 0, 330, 300);
        pn_chuaanh.add(lb_Chuaanh);
        
        JButton btn_ChonAnh = new JButton("Chọn Ảnh");
        btn_ChonAnh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_ChonAnh.setBounds(125, 363, 132, 40);
        pn_Capnhatthuoc_nhaptt.add(btn_ChonAnh);
        
        JLabel lbl_Capnhatthuoc = new JLabel("Cập Nhật Thuốc");
        lbl_Capnhatthuoc.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbl_Capnhatthuoc.setBounds(669, 11, 285, 59);
        pn_Capnhatthuoc_nhaptt.add(lbl_Capnhatthuoc);
        
        JLabel lbl_cntmt = new JLabel("Mã Thuốc :");
        lbl_cntmt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cntmt.setBounds(442, 75, 108, 30);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntmt);
        
        JLabel lbl_cnttt = new JLabel("Tên Thuốc :");
        lbl_cnttt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cnttt.setBounds(442, 116, 108, 30);
        pn_Capnhatthuoc_nhaptt.add(lbl_cnttt);
        
        JLabel lbl_cntsl = new JLabel("Số Lượng :");
        lbl_cntsl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cntsl.setBounds(442, 157, 108, 30);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntsl);
        
        JLabel lbl_cntgn = new JLabel("Giá Nhập :");
        lbl_cntgn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cntgn.setBounds(442, 198, 108, 30);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntgn);
        
        JLabel lbl_cntgb = new JLabel("Giá Bán :");
        lbl_cntgb.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cntgb.setBounds(442, 239, 108, 30);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntgb);
        
        JLabel lbl_cntdvt = new JLabel("Đơn Vị Tính :");
        lbl_cntdvt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cntdvt.setBounds(442, 280, 115, 30);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntdvt);
        
        text_cntmt = new JTextField();
        text_cntmt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_cntmt.setBounds(560, 75, 394, 30);
        pn_Capnhatthuoc_nhaptt.add(text_cntmt);
        text_cntmt.setColumns(10);
        
        text_cnttt = new JTextField();
        text_cnttt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_cnttt.setColumns(10);
        text_cnttt.setBounds(560, 116, 394, 30);
        pn_Capnhatthuoc_nhaptt.add(text_cnttt);
        
        text_cntsl = new JTextField();
        text_cntsl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_cntsl.setColumns(10);
        text_cntsl.setBounds(560, 157, 394, 30);
        pn_Capnhatthuoc_nhaptt.add(text_cntsl);
        
        text_cntgn = new JTextField();
        text_cntgn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_cntgn.setColumns(10);
        text_cntgn.setBounds(560, 198, 394, 30);
        pn_Capnhatthuoc_nhaptt.add(text_cntgn);
        
        text_cntgb = new JTextField();
        text_cntgb.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_cntgb.setColumns(10);
        text_cntgb.setBounds(560, 239, 394, 30);
        pn_Capnhatthuoc_nhaptt.add(text_cntgb);
        
        JLabel lbl_cntncc = new JLabel("Nhà Cung Cấp :");
        lbl_cntncc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cntncc.setBounds(1036, 75, 125, 30);
        pn_Capnhatthuoc_nhaptt.add(lbl_cntncc);
        
        JLabel lbl_cnthsd = new JLabel("Hạn Sử Dụng :");
        lbl_cnthsd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cnthsd.setBounds(1036, 116, 125, 30);
        pn_Capnhatthuoc_nhaptt.add(lbl_cnthsd);
        
        JLabel lbl_cnttkt = new JLabel("Tên Kệ Thuốc :");
        lbl_cnttkt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cnttkt.setBounds(1036, 157, 125, 30);
        pn_Capnhatthuoc_nhaptt.add(lbl_cnttkt);
        
        JLabel lbl_cnttp = new JLabel("Thành Phần :");
        lbl_cnttp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cnttp.setBounds(1036, 198, 108, 30);
        pn_Capnhatthuoc_nhaptt.add(lbl_cnttp);
        
        JComboBox cb_cntdvt = new JComboBox();
        cb_cntdvt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cb_cntdvt.setBounds(560, 280, 394, 30);
        pn_Capnhatthuoc_nhaptt.add(cb_cntdvt);
        
        JComboBox cb_cntncc = new JComboBox();
        cb_cntncc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cb_cntncc.setBounds(1168, 75, 394, 30);
        pn_Capnhatthuoc_nhaptt.add(cb_cntncc);
        
        JComboBox cb_cnttkt = new JComboBox();
        cb_cnttkt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cb_cnttkt.setBounds(1168, 157, 394, 30);
        pn_Capnhatthuoc_nhaptt.add(cb_cnttkt);
        
        text_cnthsd = new JTextField();
        text_cnthsd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_cnthsd.setColumns(10);
        text_cnthsd.setBounds(1168, 116, 394, 30);
        pn_Capnhatthuoc_nhaptt.add(text_cnthsd);
        
        JTextArea textArea_cnttp = new JTextArea();
        textArea_cnttp.setWrapStyleWord(true);
        textArea_cnttp.setLineWrap(true);
        textArea_cnttp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textArea_cnttp.setBounds(1036, 226, 526, 84);
        pn_Capnhatthuoc_nhaptt.add(textArea_cnttp);
        
        JButton btn_cntKhoiphuc = new JButton("Khôi Phục");
        btn_cntKhoiphuc.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btn_cntKhoiphuc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_cntKhoiphuc.setBounds(1244, 363, 132, 40);
        pn_Capnhatthuoc_nhaptt.add(btn_cntKhoiphuc);
        
        JButton btn_cntCapnhat = new JButton("Cập Nhật");
        btn_cntCapnhat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_cntCapnhat.setBounds(1424, 363, 138, 40);
        pn_Capnhatthuoc_nhaptt.add(btn_cntCapnhat);
        
        JPanel pn_Capnhatthuoc_tk = new JPanel();
        pn_Capnhatthuoc_tk.setBorder(new TitledBorder(null, "T\u00ECm Ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pn_Capnhatthuoc_tk.setBounds(10, 429, 1564, 80);
        pn_Capnhatthuoc.add(pn_Capnhatthuoc_tk);
        pn_Capnhatthuoc_tk.setLayout(null);
        
        JLabel lbl_cnt_tkmt = new JLabel("Mã Thuốc :");
        lbl_cnt_tkmt.setBounds(40, 29, 91, 24);
        lbl_cnt_tkmt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn_Capnhatthuoc_tk.add(lbl_cnt_tkmt);
        
        JLabel lbl_cnt_tktt = new JLabel("Tên Thuốc :");
        lbl_cnt_tktt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cnt_tktt.setBounds(349, 23, 108, 30);
        pn_Capnhatthuoc_tk.add(lbl_cnt_tktt);
        
        JLabel lbl_cnt_tktkt = new JLabel("Tên Kệ Thuốc :");
        lbl_cnt_tktkt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_cnt_tktkt.setBounds(779, 23, 125, 30);
        pn_Capnhatthuoc_tk.add(lbl_cnt_tktkt);
        
        text_cnt_tkmt = new JTextField();
        text_cnt_tkmt.setColumns(10);
        text_cnt_tkmt.setBounds(141, 23, 198, 30);
        pn_Capnhatthuoc_tk.add(text_cnt_tkmt);
        
        text_cnt_tktt = new JTextField();
        text_cnt_tktt.setColumns(10);
        text_cnt_tktt.setBounds(467, 23, 302, 30);
        pn_Capnhatthuoc_tk.add(text_cnt_tktt);
        
        JComboBox cb_cnt_tktkt = new JComboBox();
        cb_cnt_tktkt.setBounds(914, 23, 394, 30);
        pn_Capnhatthuoc_tk.add(cb_cnt_tktkt);
        
        JButton btn_cnt_tk_lammoi = new JButton("Làm Mới");
        btn_cnt_tk_lammoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_cnt_tk_lammoi.setBounds(1398, 20, 138, 40);
        pn_Capnhatthuoc_tk.add(btn_cnt_tk_lammoi);
        
        JPanel pn_capnhatthoc_table = new JPanel();
        pn_capnhatthoc_table.setBounds(10, 520, 1564, 419);
        pn_Capnhatthuoc.add(pn_capnhatthoc_table);
        pn_capnhatthoc_table.setLayout(null);
        
        JScrollPane scP_cnt_table = new JScrollPane();
        scP_cnt_table.setBounds(0, 0, 1564, 419);
        pn_capnhatthoc_table.add(scP_cnt_table);
        
        table_Capnhatthuoc = new JTable();
        table_Capnhatthuoc.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c", "Th\u00E0nh Ph\u1EA7n"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, Object.class, Double.class, Double.class, String.class, String.class, String.class, String.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
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
        
        JPanel pn_themthuoc1 = new JPanel();
        maincontent.add(pn_themthuoc1, "Themthuocthuong");
        pn_themthuoc1.setLayout(null);
        
        JPanel pn_Themthuoc = new JPanel();
        pn_Themthuoc.setLayout(null);
        pn_Themthuoc.setBounds(0, 0, 1584, 429);
        pn_themthuoc1.add(pn_Themthuoc);
        
        JPanel pn_chuaanh_1 = new JPanel();
        pn_chuaanh_1.setLayout(null);
        pn_chuaanh_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        pn_chuaanh_1.setBounds(34, 51, 330, 300);
        pn_Themthuoc.add(pn_chuaanh_1);
        
        JLabel lb_Chuaanh_1 = new JLabel("New label");
        lb_Chuaanh_1.setBounds(0, 0, 330, 300);
        pn_chuaanh_1.add(lb_Chuaanh_1);
        
        JButton btn_ChonAnh_1 = new JButton("Chọn Ảnh");
        btn_ChonAnh_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_ChonAnh_1.setBounds(125, 363, 132, 40);
        pn_Themthuoc.add(btn_ChonAnh_1);
        
        JLabel lbl_Themthuoc = new JLabel("THÊM THUỐC");
        lbl_Themthuoc.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbl_Themthuoc.setBounds(669, 11, 285, 47);
        pn_Themthuoc.add(lbl_Themthuoc);
        
        JLabel lbl_ttmt = new JLabel("Mã Thuốc :");
        lbl_ttmt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ttmt.setBounds(442, 75, 108, 30);
        pn_Themthuoc.add(lbl_ttmt);
        
        JLabel lbl_tttt = new JLabel("Tên Thuốc :");
        lbl_tttt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tttt.setBounds(442, 116, 108, 30);
        pn_Themthuoc.add(lbl_tttt);
        
        JLabel lbl_ttsl = new JLabel("Số Lượng :");
        lbl_ttsl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ttsl.setBounds(442, 157, 108, 30);
        pn_Themthuoc.add(lbl_ttsl);
        
        JLabel lbl_ttgn = new JLabel("Giá Nhập :");
        lbl_ttgn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ttgn.setBounds(442, 198, 108, 30);
        pn_Themthuoc.add(lbl_ttgn);
        
        JLabel lbl_ttgb = new JLabel("Giá Bán :");
        lbl_ttgb.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ttgb.setBounds(442, 239, 108, 30);
        pn_Themthuoc.add(lbl_ttgb);
        
        JLabel lbl_ttdvt = new JLabel("Đơn Vị Tính :");
        lbl_ttdvt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ttdvt.setBounds(442, 280, 115, 30);
        pn_Themthuoc.add(lbl_ttdvt);
        
        text_ttmt = new JTextField();
        text_ttmt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_ttmt.setColumns(10);
        text_ttmt.setBounds(560, 75, 394, 30);
        pn_Themthuoc.add(text_ttmt);
        
        text_tttt = new JTextField();
        text_tttt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_tttt.setColumns(10);
        text_tttt.setBounds(560, 116, 394, 30);
        pn_Themthuoc.add(text_tttt);
        
        text_ttsl = new JTextField();
        text_ttsl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_ttsl.setColumns(10);
        text_ttsl.setBounds(560, 157, 394, 30);
        pn_Themthuoc.add(text_ttsl);
        
        text_ttgn = new JTextField();
        text_ttgn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_ttgn.setColumns(10);
        text_ttgn.setBounds(560, 198, 394, 30);
        pn_Themthuoc.add(text_ttgn);
        
        text_ttgb = new JTextField();
        text_ttgb.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_ttgb.setColumns(10);
        text_ttgb.setBounds(560, 239, 394, 30);
        pn_Themthuoc.add(text_ttgb);
        
        JLabel lbl_ttncc = new JLabel("Nhà Cung Cấp :");
        lbl_ttncc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ttncc.setBounds(1036, 75, 125, 30);
        pn_Themthuoc.add(lbl_ttncc);
        
        JLabel lbl_tthsd = new JLabel("Hạn Sử Dụng :");
        lbl_tthsd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tthsd.setBounds(1036, 116, 125, 30);
        pn_Themthuoc.add(lbl_tthsd);
        
        JLabel lbl_tttkt = new JLabel("Tên Kệ Thuốc :");
        lbl_tttkt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tttkt.setBounds(1036, 157, 125, 30);
        pn_Themthuoc.add(lbl_tttkt);
        
        JLabel lbl_tttp = new JLabel("Thành Phần :");
        lbl_tttp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tttp.setBounds(1036, 198, 108, 30);
        pn_Themthuoc.add(lbl_tttp);
        
        JComboBox cb_ttdvt = new JComboBox();
        cb_ttdvt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cb_ttdvt.setBounds(560, 280, 394, 30);
        pn_Themthuoc.add(cb_ttdvt);
        
        JComboBox cb_ttncc = new JComboBox();
        cb_ttncc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cb_ttncc.setBounds(1168, 75, 394, 30);
        pn_Themthuoc.add(cb_ttncc);
        
        JComboBox cb_tttkt = new JComboBox();
        cb_tttkt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cb_tttkt.setBounds(1168, 157, 394, 30);
        pn_Themthuoc.add(cb_tttkt);
        
        text_tthsd = new JTextField();
        text_tthsd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        text_tthsd.setColumns(10);
        text_tthsd.setBounds(1168, 116, 394, 30);
        pn_Themthuoc.add(text_tthsd);
        
        JTextArea textArea_tttp = new JTextArea();
        textArea_tttp.setWrapStyleWord(true);
        textArea_tttp.setLineWrap(true);
        textArea_tttp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textArea_tttp.setBounds(1036, 226, 526, 84);
        pn_Themthuoc.add(textArea_tttp);
        
        JButton btn_ttLammoi = new JButton("Làm Mới");
        btn_ttLammoi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btn_ttLammoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_ttLammoi.setBounds(1134, 363, 132, 40);
        pn_Themthuoc.add(btn_ttLammoi);
        
        JButton btn_ttThem = new JButton("Thêm");
        btn_ttThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_ttThem.setBounds(1276, 363, 138, 40);
        pn_Themthuoc.add(btn_ttThem);
        
        JButton btn_ttThem_1 = new JButton("Lưu");
        btn_ttThem_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_ttThem_1.setBounds(1424, 363, 138, 40);
        pn_Themthuoc.add(btn_ttThem_1);
        
        JPanel pn_capnhatthoc_table_1 = new JPanel();
        pn_capnhatthoc_table_1.setLayout(null);
        pn_capnhatthoc_table_1.setBounds(0, 429, 1584, 510);
        pn_themthuoc1.add(pn_capnhatthoc_table_1);
        
        JScrollPane scP_cnt_table_1 = new JScrollPane();
        scP_cnt_table_1.setBounds(10, 0, 1564, 510);
        pn_capnhatthoc_table_1.add(scP_cnt_table_1);
        
        table_1 = new JTable();
        
        
        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c", "Th\u00E0nh Ph\u1EA7n"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, Object.class, Double.class, Double.class, String.class, String.class, String.class, String.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
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
        
        JPanel pn_themthuocfile = new JPanel();
        maincontent.add(pn_themthuocfile, "Themthuocfile");
        pn_themthuocfile.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 1584, 123);
        pn_themthuocfile.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("THÊM NHIỀU THUỐC");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel_2.setBounds(554, 11, 429, 47);
        panel_1.add(lblNewLabel_2);
        
        JButton btnNewButton_1 = new JButton("Chọn File");
        btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_1.setBounds(164, 74, 139, 38);
        panel_1.add(btnNewButton_1);
        
        JLabel lblNewLabel_3 = new JLabel("Tống số thuốc :");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(340, 81, 131, 24);
        panel_1.add(lblNewLabel_3);
        
        JLabel lblNewLabel_3_1 = new JLabel("0");
        lblNewLabel_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_3_1.setBounds(468, 81, 67, 24);
        panel_1.add(lblNewLabel_3_1);
        
        JButton btnNewButton_1_1 = new JButton("Làm Mới");
        btnNewButton_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_1_1.setBounds(1125, 74, 139, 38);
        panel_1.add(btnNewButton_1_1);
        
        JButton btnNewButton_1_2 = new JButton("Thêm");
        btnNewButton_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_1_2.setBounds(1274, 74, 139, 38);
        panel_1.add(btnNewButton_1_2);
        
        JButton btnNewButton_1_2_1 = new JButton("Lưu");
        btnNewButton_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_1_2_1.setBounds(1423, 74, 139, 38);
        panel_1.add(btnNewButton_1_2_1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(0, 122, 1584, 817);
        pn_themthuocfile.add(panel_2);
        panel_2.setLayout(null);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 0, 1564, 817);
        panel_2.add(scrollPane_1);
        
        table_2 = new JTable();
        table_2.setModel(new DefaultTableModel(
            	new Object[][] {
            		{null, null, null, null, null, null, null, null, null, null},
            	},
            	new String[] {
            		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c", "Th\u00E0nh Ph\u1EA7n"
            	}
            ) {
            	Class[] columnTypes = new Class[] {
            		String.class, String.class, Object.class, Double.class, Double.class, String.class, String.class, String.class, String.class, String.class
            	};
            	public Class getColumnClass(int columnIndex) {
            		return columnTypes[columnIndex];
            	}
            });
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
        scrollPane_1.setViewportView(table_2);
        
        JPanel pn_thuocsaphethan = new JPanel();
        maincontent.add(pn_thuocsaphethan, "Thuocsaphethan");
        pn_thuocsaphethan.setLayout(null);
        
        JLabel lblNewLabel_4 = new JLabel("Danh Sách Thuốc Sắp Hết Hạn");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel_4.setBounds(506, 11, 537, 40);
        pn_thuocsaphethan.add(lblNewLabel_4);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(10, 66, 1564, 811);
        pn_thuocsaphethan.add(scrollPane_2);
        
        table_3 = new JTable();
        table_3.setModel(new DefaultTableModel(
            	new Object[][] {
            		{null, null, null, null, null, null, null, null, null, null},
            	},
            	new String[] {
            		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c", "Th\u00E0nh Ph\u1EA7n"
            	}
            ) {
            	Class[] columnTypes = new Class[] {
            		String.class, String.class, Object.class, Double.class, Double.class, String.class, String.class, String.class, String.class, String.class
            	};
            	public Class getColumnClass(int columnIndex) {
            		return columnTypes[columnIndex];
            	}
            });
        table_3.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_3.getColumnModel().getColumn(1).setPreferredWidth(130);
        table_3.getColumnModel().getColumn(2).setPreferredWidth(15);
        table_3.getColumnModel().getColumn(3).setPreferredWidth(15);
        table_3.getColumnModel().getColumn(4).setPreferredWidth(15);
        table_3.getColumnModel().getColumn(5).setPreferredWidth(15);
        table_3.getColumnModel().getColumn(6).setPreferredWidth(100);
        table_3.getColumnModel().getColumn(7).setPreferredWidth(25);
        table_3.getColumnModel().getColumn(8).setPreferredWidth(100);
        table_3.getColumnModel().getColumn(9).setPreferredWidth(300);
        scrollPane_2.setViewportView(table_3);
        
        JButton btnNewButton_2 = new JButton("Xuất File");
        btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_2.setBounds(1422, 888, 152, 40);
        pn_thuocsaphethan.add(btnNewButton_2);
        
        JPanel pn_thuocbanchay = new JPanel();
        maincontent.add(pn_thuocbanchay, "Thuocbanchay");
        pn_thuocbanchay.setLayout(null);
        
        JLabel lblNewLabel_4_2 = new JLabel("Danh Sách Thuốc Bán Chạy");
        lblNewLabel_4_2.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel_4_2.setBounds(564, 0, 481, 57);
        pn_thuocbanchay.add(lblNewLabel_4_2);
        
        JScrollPane scrollPane_2_2 = new JScrollPane();
        scrollPane_2_2.setBounds(10, 55, 1564, 811);
        pn_thuocbanchay.add(scrollPane_2_2);
        
        table_5 = new JTable();
        table_5.setModel(new DefaultTableModel(
            	new Object[][] {
            		{null, null, null, null, null, null, null, null, null, null},
            	},
            	new String[] {
            		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c", "Th\u00E0nh Ph\u1EA7n"
            	}
            ) {
            	Class[] columnTypes = new Class[] {
            		String.class, String.class, Object.class, Double.class, Double.class, String.class, String.class, String.class, String.class, String.class
            	};
            	public Class getColumnClass(int columnIndex) {
            		return columnTypes[columnIndex];
            	}
            });
        table_5.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_5.getColumnModel().getColumn(1).setPreferredWidth(130);
        table_5.getColumnModel().getColumn(2).setPreferredWidth(15);
        table_5.getColumnModel().getColumn(3).setPreferredWidth(15);
        table_5.getColumnModel().getColumn(4).setPreferredWidth(15);
        table_5.getColumnModel().getColumn(5).setPreferredWidth(15);
        table_5.getColumnModel().getColumn(6).setPreferredWidth(100);
        table_5.getColumnModel().getColumn(7).setPreferredWidth(25);
        table_5.getColumnModel().getColumn(8).setPreferredWidth(100);
        table_5.getColumnModel().getColumn(9).setPreferredWidth(300);
        scrollPane_2_2.setViewportView(table_5);
        
        JButton btnNewButton_2_2 = new JButton("Xuất File");
        btnNewButton_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_2_2.setBounds(1422, 877, 152, 40);
        pn_thuocbanchay.add(btnNewButton_2_2);
        
        JPanel pn_thuocsaphethang = new JPanel();
        maincontent.add(pn_thuocsaphethang, "Thuocsaphethang");
        pn_thuocsaphethang.setLayout(null);
        
        JLabel lblNewLabel_4_1 = new JLabel("Danh Sách Thuốc Hết Hàng");
        lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel_4_1.setBounds(564, 0, 481, 57);
        pn_thuocsaphethang.add(lblNewLabel_4_1);
        
        JScrollPane scrollPane_2_1 = new JScrollPane();
        scrollPane_2_1.setBounds(10, 55, 1564, 811);
        pn_thuocsaphethang.add(scrollPane_2_1);
        
        table_4 = new JTable();
        table_4.setModel(new DefaultTableModel(
            	new Object[][] {
            		{null, null, null, null, null, null, null, null, null, null},
            	},
            	new String[] {
            		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c", "Th\u00E0nh Ph\u1EA7n"
            	}
            ) {
            	Class[] columnTypes = new Class[] {
            		String.class, String.class, Object.class, Double.class, Double.class, String.class, String.class, String.class, String.class, String.class
            	};
            	public Class getColumnClass(int columnIndex) {
            		return columnTypes[columnIndex];
            	}
            });
        table_4.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_4.getColumnModel().getColumn(1).setPreferredWidth(130);
        table_4.getColumnModel().getColumn(2).setPreferredWidth(15);
        table_4.getColumnModel().getColumn(3).setPreferredWidth(15);
        table_4.getColumnModel().getColumn(4).setPreferredWidth(15);
        table_4.getColumnModel().getColumn(5).setPreferredWidth(15);
        table_4.getColumnModel().getColumn(6).setPreferredWidth(100);
        table_4.getColumnModel().getColumn(7).setPreferredWidth(25);
        table_4.getColumnModel().getColumn(8).setPreferredWidth(100);
        table_4.getColumnModel().getColumn(9).setPreferredWidth(300);
        scrollPane_2_1.setViewportView(table_4);
        
        JButton btnNewButton_2_1 = new JButton("Xuất File");
        btnNewButton_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_2_1.setBounds(1422, 877, 152, 40);
        pn_thuocsaphethang.add(btnNewButton_2_1);
        
        JPanel pn_ThongkeHD = new JPanel();
        maincontent.add(pn_ThongkeHD, "ThongkeHD");
        pn_ThongkeHD.setLayout(null);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBounds(0, 0, 1584, 81);
        pn_ThongkeHD.add(panel_4);
        panel_4.setLayout(null);
        
        JLabel lblNewLabel_7 = new JLabel("Thống Kê Hóa Đơn");
        lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel_7.setBounds(623, 11, 337, 59);
        panel_4.add(lblNewLabel_7);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 80, 1584, 859);
        pn_ThongkeHD.add(tabbedPane);
        
        JPanel pn_tketheongay = new JPanel();
        tabbedPane.addTab("Theo Ngày", null, pn_tketheongay, null);
        pn_tketheongay.setLayout(null);
        
        JLabel lblNewLabel_8 = new JLabel("Ngày Thống Kê :");
        lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8.setBounds(30, 11, 143, 30);
        pn_tketheongay.add(lblNewLabel_8);
        
        JLabel lblNewLabel_8_1 = new JLabel("Tổng Số Hóa Đơn :");
        lblNewLabel_8_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1.setBounds(572, 11, 165, 30);
        pn_tketheongay.add(lblNewLabel_8_1);
        
        JLabel lblNewLabel_8_1_1 = new JLabel("Tổng Tiền Các Hóa Đơn :");
        lblNewLabel_8_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_1.setBounds(962, 11, 211, 30);
        pn_tketheongay.add(lblNewLabel_8_1_1);
        
        JLabel lblNewLabel_8_1_2 = new JLabel("1000");
        lblNewLabel_8_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_2.setBounds(747, 11, 143, 30);
        pn_tketheongay.add(lblNewLabel_8_1_2);
        
        JLabel lblNewLabel_8_1_3 = new JLabel("1000000000000000");
        lblNewLabel_8_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_3.setBounds(1183, 11, 165, 30);
        pn_tketheongay.add(lblNewLabel_8_1_3);
        
        JLabel lblNewLabel_8_1_4 = new JLabel("VND");
        lblNewLabel_8_1_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_4.setBounds(1358, 11, 50, 30);
        pn_tketheongay.add(lblNewLabel_8_1_4);
        
        textField_7 = new JTextField();
        textField_7.setBounds(183, 11, 223, 30);
        pn_tketheongay.add(textField_7);
        textField_7.setColumns(10);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBounds(10, 52, 480, 779);
        pn_tketheongay.add(panel_5);
        panel_5.setLayout(null);
        
        JPanel panel_6 = new JPanel();
        panel_6.setBackground(new Color(255, 255, 255));
        panel_6.setBounds(10, 11, 459, 230);
        panel_5.add(panel_6);
        panel_6.setLayout(null);
        
        JLabel lblNewLabel_8_2 = new JLabel("Thống Kê Theo :");
        lblNewLabel_8_2.setBounds(21, 11, 142, 30);
        lblNewLabel_8_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel_6.add(lblNewLabel_8_2);
        
        JComboBox comboBox_3 = new JComboBox();
        comboBox_3.setBounds(173, 11, 258, 30);
        panel_6.add(comboBox_3);
        
        JPanel panel_7 = new JPanel();
        panel_7.setBorder(new TitledBorder(null, "T\u00ECm Ki\u1EBFm Nh\u00E2n Vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_7.setBackground(new Color(255, 255, 255));
        panel_7.setBounds(10, 63, 437, 153);
        panel_6.add(panel_7);
        panel_7.setLayout(null);
        
        JLabel lblNewLabel_8_2_1 = new JLabel("Mã Nhân Viên :");
        lblNewLabel_8_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_2_1.setBounds(10, 33, 142, 30);
        panel_7.add(lblNewLabel_8_2_1);
        
        JLabel lblNewLabel_8_2_2 = new JLabel("Tên Nhân Viên :");
        lblNewLabel_8_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_2_2.setBounds(10, 85, 142, 30);
        panel_7.add(lblNewLabel_8_2_2);
        
        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(162, 33, 261, 30);
        panel_7.add(textField_8);
        
        textField_9 = new JTextField();
        textField_9.setColumns(10);
        textField_9.setBounds(162, 85, 261, 30);
        panel_7.add(textField_9);
        
        JScrollPane scrollPane_5 = new JScrollPane();
        scrollPane_5.setBounds(10, 263, 459, 505);
        panel_5.add(scrollPane_5);
        
        table_8 = new JTable();
        table_8.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null},
        	},
        	new String[] {
        		"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, Object.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        scrollPane_5.setViewportView(table_8);
        
        JScrollPane scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(500, 52, 1069, 715);
        pn_tketheongay.add(scrollPane_4);
        
        table_7 = new JTable();
        table_7.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 H\u00F3a \u0110\u01A1n", "S\u0110T Kh\u00E1ch H\u00E0ng", "T\u00EAn Kh\u00E1ch H\u00E0ng", "M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn", "T\u1ED5ng Ti\u1EC1n"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, String.class, String.class, String.class, Double.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        scrollPane_4.setViewportView(table_7);
        
        JButton btnNewButton_4 = new JButton("Xuất File");
        btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_4.setBounds(1426, 778, 143, 42);
        pn_tketheongay.add(btnNewButton_4);
        
        JPanel pn_tketheothang = new JPanel();
        tabbedPane.addTab("Theo Tháng", null, pn_tketheothang, null);
        pn_tketheothang.setLayout(null);
        
        JLabel lblNewLabel_8_3 = new JLabel("Tháng :");
        lblNewLabel_8_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_3.setBounds(20, 11, 69, 30);
        pn_tketheothang.add(lblNewLabel_8_3);
        
        JLabel lblNewLabel_8_1_5 = new JLabel("Tổng Số Hóa Đơn :");
        lblNewLabel_8_1_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_5.setBounds(504, 11, 165, 30);
        pn_tketheothang.add(lblNewLabel_8_1_5);
        
        JLabel lblNewLabel_8_1_1_1 = new JLabel("Tổng Tiền Các Hóa Đơn :");
        lblNewLabel_8_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_1_1.setBounds(504, 52, 211, 30);
        pn_tketheothang.add(lblNewLabel_8_1_1_1);
        
        JLabel lblNewLabel_8_1_2_1 = new JLabel("1000");
        lblNewLabel_8_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_2_1.setBounds(679, 11, 143, 30);
        pn_tketheothang.add(lblNewLabel_8_1_2_1);
        
        JLabel lblNewLabel_8_1_3_1 = new JLabel("10000000000");
        lblNewLabel_8_1_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_3_1.setBounds(725, 52, 121, 30);
        pn_tketheothang.add(lblNewLabel_8_1_3_1);
        
        JLabel lblNewLabel_8_1_4_1 = new JLabel("VND");
        lblNewLabel_8_1_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_4_1.setBounds(856, 52, 50, 30);
        pn_tketheothang.add(lblNewLabel_8_1_4_1);
        
        JButton btnNewButton_4_1 = new JButton("Xuất File");
        btnNewButton_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_4_1.setBounds(1416, 778, 143, 42);
        pn_tketheothang.add(btnNewButton_4_1);
        
        JLabel lblNewLabel_8_3_1 = new JLabel("Năm :");
        lblNewLabel_8_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_3_1.setBounds(244, 11, 55, 30);
        pn_tketheothang.add(lblNewLabel_8_3_1);
        
        JComboBox comboBox_3_1_1 = new JComboBox();
        comboBox_3_1_1.setBounds(309, 11, 185, 30);
        pn_tketheothang.add(comboBox_3_1_1);
        
        JComboBox comboBox_3_1_1_1 = new JComboBox();
        comboBox_3_1_1_1.setBounds(99, 11, 135, 30);
        pn_tketheothang.add(comboBox_3_1_1_1);
        
        JScrollPane scrollPane_6 = new JScrollPane();
        scrollPane_6.setBounds(500, 96, 436, 724);
        pn_tketheothang.add(scrollPane_6);
        
        table_10 = new JTable();
        table_10.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"Ng\u00E0y", "T\u1ED5ng S\u1ED1 H\u00F3a \u0110\u01A1n", "T\u1ED5ng Ti\u1EC1n C\u00E1c H\u00F3a \u0110\u01A1n"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, Object.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        scrollPane_6.setViewportView(table_10);
        
        JButton btnNewButton_4_1_1 = new JButton("Xem Chi tiết");
        btnNewButton_4_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_4_1_1.setBounds(1224, 778, 143, 42);
        pn_tketheothang.add(btnNewButton_4_1_1);
        
        JLabel lblNewLabel_9 = new JLabel("Biểu Đồ Thống Kê Doanh Thu");
        lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel_9.setBounds(995, 47, 323, 35);
        pn_tketheothang.add(lblNewLabel_9);
        
        JLabel lblNewLabel_9_1 = new JLabel("Tháng 12 Năm 2025");
        lblNewLabel_9_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel_9_1.setBounds(1324, 47, 222, 35);
        pn_tketheothang.add(lblNewLabel_9_1);
        
        JPanel panel_5_1 = new JPanel();
        panel_5_1.setLayout(null);
        panel_5_1.setBounds(10, 52, 480, 779);
        pn_tketheothang.add(panel_5_1);
        
        JPanel panel_6_1 = new JPanel();
        panel_6_1.setLayout(null);
        panel_6_1.setBackground(Color.WHITE);
        panel_6_1.setBounds(10, 11, 459, 230);
        panel_5_1.add(panel_6_1);
        
        JLabel lblNewLabel_8_2_3 = new JLabel("Thống Kê Theo :");
        lblNewLabel_8_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_2_3.setBounds(21, 11, 142, 30);
        panel_6_1.add(lblNewLabel_8_2_3);
        
        JComboBox comboBox_3_1 = new JComboBox();
        comboBox_3_1.setBounds(173, 11, 258, 30);
        panel_6_1.add(comboBox_3_1);
        
        JPanel panel_7_1 = new JPanel();
        panel_7_1.setLayout(null);
        panel_7_1.setBorder(new TitledBorder(null, "T\u00ECm Ki\u1EBFm Nh\u00E2n Vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_7_1.setBackground(Color.WHITE);
        panel_7_1.setBounds(10, 63, 437, 153);
        panel_6_1.add(panel_7_1);
        
        JLabel lblNewLabel_8_2_1_1 = new JLabel("Mã Nhân Viên :");
        lblNewLabel_8_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_2_1_1.setBounds(10, 33, 142, 30);
        panel_7_1.add(lblNewLabel_8_2_1_1);
        
        JLabel lblNewLabel_8_2_2_1 = new JLabel("Tên Nhân Viên :");
        lblNewLabel_8_2_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_2_2_1.setBounds(10, 85, 142, 30);
        panel_7_1.add(lblNewLabel_8_2_2_1);
        
        textField_10 = new JTextField();
        textField_10.setColumns(10);
        textField_10.setBounds(162, 33, 261, 30);
        panel_7_1.add(textField_10);
        
        textField_11 = new JTextField();
        textField_11.setColumns(10);
        textField_11.setBounds(162, 85, 261, 30);
        panel_7_1.add(textField_11);
        
        JScrollPane scrollPane_5_1 = new JScrollPane();
        scrollPane_5_1.setBounds(10, 263, 459, 505);
        panel_5_1.add(scrollPane_5_1);
        
        table_12 = new JTable();
        table_12.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null},
        	},
        	new String[] {
        		"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, Object.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        scrollPane_5_1.setViewportView(table_12);
        
        JPanel pn_tketheonam = new JPanel();
        tabbedPane.addTab("Theo Năm", null, pn_tketheonam, null);
        pn_tketheonam.setLayout(null);
        
        JLabel lblNewLabel_8_1_5_1 = new JLabel("Tổng Số Hóa Đơn :");
        lblNewLabel_8_1_5_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_5_1.setBounds(500, 11, 165, 30);
        pn_tketheonam.add(lblNewLabel_8_1_5_1);
        
        JLabel lblNewLabel_8_1_1_1_1 = new JLabel("Tổng Tiền Các Hóa Đơn :");
        lblNewLabel_8_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_1_1_1.setBounds(500, 52, 211, 30);
        pn_tketheonam.add(lblNewLabel_8_1_1_1_1);
        
        JLabel lblNewLabel_8_1_2_1_1 = new JLabel("1000");
        lblNewLabel_8_1_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_2_1_1.setBounds(675, 11, 143, 30);
        pn_tketheonam.add(lblNewLabel_8_1_2_1_1);
        
        JLabel lblNewLabel_8_1_3_1_1 = new JLabel("10000000000");
        lblNewLabel_8_1_3_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_3_1_1.setBounds(721, 52, 121, 30);
        pn_tketheonam.add(lblNewLabel_8_1_3_1_1);
        
        JLabel lblNewLabel_8_1_4_1_1 = new JLabel("VND");
        lblNewLabel_8_1_4_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_1_4_1_1.setBounds(852, 52, 50, 30);
        pn_tketheonam.add(lblNewLabel_8_1_4_1_1);
        
        JButton btnNewButton_4_1_2 = new JButton("Xuất File");
        btnNewButton_4_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_4_1_2.setBounds(1412, 778, 143, 42);
        pn_tketheonam.add(btnNewButton_4_1_2);
        
        JLabel lblNewLabel_8_3_1_1 = new JLabel("Năm Thống Kê :");
        lblNewLabel_8_3_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_3_1_1.setBounds(26, 11, 143, 30);
        pn_tketheonam.add(lblNewLabel_8_3_1_1);
        
        JComboBox comboBox_3_1_1_2 = new JComboBox();
        comboBox_3_1_1_2.setBounds(179, 13, 185, 30);
        pn_tketheonam.add(comboBox_3_1_1_2);
        
        JScrollPane scrollPane_6_1 = new JScrollPane();
        scrollPane_6_1.setBounds(500, 96, 436, 724);
        pn_tketheonam.add(scrollPane_6_1);
        
        table_11 = new JTable();
        table_11.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"Th\u00E1ng", "T\u1ED5ng S\u1ED1 H\u00F3a \u0110\u01A1n", "T\u1ED5ng Ti\u1EC1n C\u00E1c H\u00F3a \u0110\u01A1n"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        scrollPane_6_1.setViewportView(table_11);
        
        JButton btnNewButton_4_1_1_1 = new JButton("Xem Chi tiết");
        btnNewButton_4_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton_4_1_1_1.setBounds(1220, 778, 143, 42);
        pn_tketheonam.add(btnNewButton_4_1_1_1);
        
        JLabel lblNewLabel_9_2 = new JLabel("Biểu Đồ Thống Kê Doanh Thu");
        lblNewLabel_9_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel_9_2.setBounds(1059, 47, 323, 35);
        pn_tketheonam.add(lblNewLabel_9_2);
        
        JLabel lblNewLabel_9_1_1 = new JLabel("Năm 2025");
        lblNewLabel_9_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel_9_1_1.setBounds(1388, 47, 121, 35);
        pn_tketheonam.add(lblNewLabel_9_1_1);
        
        JPanel panel_5_1_1 = new JPanel();
        panel_5_1_1.setLayout(null);
        panel_5_1_1.setBounds(10, 52, 480, 779);
        pn_tketheonam.add(panel_5_1_1);
        
        JPanel panel_6_1_1 = new JPanel();
        panel_6_1_1.setLayout(null);
        panel_6_1_1.setBackground(Color.WHITE);
        panel_6_1_1.setBounds(10, 11, 459, 230);
        panel_5_1_1.add(panel_6_1_1);
        
        JLabel lblNewLabel_8_2_3_1 = new JLabel("Thống Kê Theo :");
        lblNewLabel_8_2_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_2_3_1.setBounds(21, 11, 142, 30);
        panel_6_1_1.add(lblNewLabel_8_2_3_1);
        
        JComboBox comboBox_3_1_2 = new JComboBox();
        comboBox_3_1_2.setBounds(173, 11, 258, 30);
        panel_6_1_1.add(comboBox_3_1_2);
        
        JPanel panel_7_1_1 = new JPanel();
        panel_7_1_1.setLayout(null);
        panel_7_1_1.setBorder(new TitledBorder(null, "T\u00ECm Ki\u1EBFm Nh\u00E2n Vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_7_1_1.setBackground(Color.WHITE);
        panel_7_1_1.setBounds(10, 63, 437, 153);
        panel_6_1_1.add(panel_7_1_1);
        
        JLabel lblNewLabel_8_2_1_1_1 = new JLabel("Mã Nhân Viên :");
        lblNewLabel_8_2_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_2_1_1_1.setBounds(10, 33, 142, 30);
        panel_7_1_1.add(lblNewLabel_8_2_1_1_1);
        
        JLabel lblNewLabel_8_2_2_1_1 = new JLabel("Tên Nhân Viên :");
        lblNewLabel_8_2_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_8_2_2_1_1.setBounds(10, 85, 142, 30);
        panel_7_1_1.add(lblNewLabel_8_2_2_1_1);
        
        textField_12 = new JTextField();
        textField_12.setColumns(10);
        textField_12.setBounds(162, 33, 261, 30);
        panel_7_1_1.add(textField_12);
        
        textField_13 = new JTextField();
        textField_13.setColumns(10);
        textField_13.setBounds(162, 85, 261, 30);
        panel_7_1_1.add(textField_13);
        
        JScrollPane scrollPane_5_1_1 = new JScrollPane();
        scrollPane_5_1_1.setBounds(10, 263, 459, 505);
        panel_5_1_1.add(scrollPane_5_1_1);
        
        table_9 = new JTable();
        table_9.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null},
        	},
        	new String[] {
        		"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        scrollPane_5_1_1.setViewportView(table_9);

        JPanel panel_ThemNV = new JPanel();
        maincontent.add(panel_ThemNV, "themNV");
        panel_ThemNV.setLayout(null);
        
        JLabel lblTitle_TNV = new JLabel("THÊM NHÂN VIÊN");
        lblTitle_TNV.setBounds(587, 34, 222, 30);
        lblTitle_TNV.setFont(new Font("Times New Roman", Font.BOLD, 25));
        panel_ThemNV.add(lblTitle_TNV);
        
        JPanel panelAnhNV_TNV = new JPanel();
        panelAnhNV_TNV.setLayout(null);
        panelAnhNV_TNV.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelAnhNV_TNV.setBounds(71, 134, 383, 578);
        panel_ThemNV.add(panelAnhNV_TNV);
        
        JPanel panelThongTinNV_TNV = new JPanel();
        panelThongTinNV_TNV.setLayout(null);
        panelThongTinNV_TNV.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, 
        TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 17), new Color(0, 0, 0)));
        panelThongTinNV_TNV.setBounds(587, 134, 886, 578);
        panel_ThemNV.add(panelThongTinNV_TNV);
        
        JLabel lblMaNV_TNV = new JLabel("Mã nhân viên:");
        lblMaNV_TNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        lblMaNV_TNV.setBounds(34, 63, 140, 32);
        panelThongTinNV_TNV.add(lblMaNV_TNV);
        
        txtMaNV_TNV = new JTextField();
        txtMaNV_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txtMaNV_TNV.setEnabled(false);
        txtMaNV_TNV.setEditable(false);
        txtMaNV_TNV.setColumns(10);
        txtMaNV_TNV.setBounds(184, 64, 259, 32);
        panelThongTinNV_TNV.add(txtMaNV_TNV);
        
        JLabel lblGioiTinh_TNV = new JLabel("Giới Tính:");
        lblGioiTinh_TNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        lblGioiTinh_TNV.setBounds(511, 66, 97, 26);
        panelThongTinNV_TNV.add(lblGioiTinh_TNV);
        
        JComboBox cboGIoiTinh_TNV = new JComboBox(new Object[]{});
        cboGIoiTinh_TNV.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
        cboGIoiTinh_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboGIoiTinh_TNV.setBounds(649, 67, 155, 30);
        panelThongTinNV_TNV.add(cboGIoiTinh_TNV);
        
        JLabel lblTenNV_TNV = new JLabel("Tên nhân viên:");
        lblTenNV_TNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        lblTenNV_TNV.setBounds(34, 144, 140, 32);
        panelThongTinNV_TNV.add(lblTenNV_TNV);
        
        txtTenNV_TNV = new JTextField();
        txtTenNV_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txtTenNV_TNV.setColumns(10);
        txtTenNV_TNV.setBounds(184, 147, 620, 32);
        panelThongTinNV_TNV.add(txtTenNV_TNV);
        
        JLabel lblSDT_TNV = new JLabel("Số điện thoại:");
        lblSDT_TNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        lblSDT_TNV.setBounds(34, 232, 140, 32);
        panelThongTinNV_TNV.add(lblSDT_TNV);
        
        txtSDT_TNV = new JTextField();
        txtSDT_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txtSDT_TNV.setColumns(10);
        txtSDT_TNV.setBounds(187, 235, 256, 32);
        panelThongTinNV_TNV.add(txtSDT_TNV);
        
        JLabel lblNgaySinh_TNV = new JLabel("Ngày sinh:");
        lblNgaySinh_TNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        lblNgaySinh_TNV.setBounds(511, 232, 103, 32);
        panelThongTinNV_TNV.add(lblNgaySinh_TNV);
        
        JLabel lblChucVu_TNV = new JLabel("Chức vụ:");
        lblChucVu_TNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        lblChucVu_TNV.setBounds(34, 307, 140, 32);
        panelThongTinNV_TNV.add(lblChucVu_TNV);
        
        JComboBox cboChucVu_TNV = new JComboBox();
        cboChucVu_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboChucVu_TNV.setBounds(184, 310, 192, 32);
        panelThongTinNV_TNV.add(cboChucVu_TNV);
        
        JLabel lblTaiKhoan_TNV = new JLabel("Tài khoản:");
        lblTaiKhoan_TNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        lblTaiKhoan_TNV.setBounds(505, 307, 103, 32);
        panelThongTinNV_TNV.add(lblTaiKhoan_TNV);
        
        JComboBox cboTaiKhoan_TNV = new JComboBox();
        cboTaiKhoan_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboTaiKhoan_TNV.setBounds(649, 311, 155, 30);
        panelThongTinNV_TNV.add(cboTaiKhoan_TNV);
        
        JDateChooser dateNgaySinh_TNV = new JDateChooser();
        dateNgaySinh_TNV.setBounds(649, 232, 155, 35);
        panelThongTinNV_TNV.add(dateNgaySinh_TNV);
        
        JPanel panelDiaChi_TNV = new JPanel();
        panelDiaChi_TNV.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\u0110\u1ECBa ch\u1EC9", TitledBorder.LEADING, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 14), new Color(0, 0, 0)));
        panelDiaChi_TNV.setBounds(10, 385, 855, 151);
        panelThongTinNV_TNV.add(panelDiaChi_TNV);
        panelDiaChi_TNV.setLayout(null);
        
        txtTinh_TNV = new JTextField();
        txtTinh_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txtTinh_TNV.setBounds(127, 44, 214, 33);
        panelDiaChi_TNV.add(txtTinh_TNV);
        txtTinh_TNV.setColumns(10);
        
        JLabel lblTinh_TNV = new JLabel("Tỉnh:");
        lblTinh_TNV.setBounds(24, 45, 86, 32);
        lblTinh_TNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        panelDiaChi_TNV.add(lblTinh_TNV);
        
        txtHuyen_TNV = new JTextField();
        txtHuyen_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txtHuyen_TNV.setColumns(10);
        txtHuyen_TNV.setBounds(600, 44, 214, 33);
        panelDiaChi_TNV.add(txtHuyen_TNV);
        
        JLabel lblHuyen_TNV = new JLabel("Huyện:");
        lblHuyen_TNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        lblHuyen_TNV.setBounds(497, 45, 86, 32);
        panelDiaChi_TNV.add(lblHuyen_TNV);
        
        JButton btnChonAnh_TNV = new JButton("Chọn ảnh");
        btnChonAnh_TNV.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnChonAnh_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        btnChonAnh_TNV.setBackground(UIManager.getColor("Button.shadow"));
        btnChonAnh_TNV.setBounds(197, 751, 107, 29);
        panel_ThemNV.add(btnChonAnh_TNV);
        
        JButton btnLamMoi_TNV = new JButton("Làm mới");
        btnLamMoi_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        btnLamMoi_TNV.setBackground(UIManager.getColor("Button.shadow"));
        btnLamMoi_TNV.setBounds(946, 751, 123, 28);
        panel_ThemNV.add(btnLamMoi_TNV);
        
        JButton btnThem_TNV = new JButton("Thêm");
        btnThem_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        btnThem_TNV.setBackground(UIManager.getColor("Button.shadow"));
        btnThem_TNV.setBounds(1142, 751, 86, 28);
        panel_ThemNV.add(btnThem_TNV);
        
        JPanel panel_TimKiemNV = new JPanel();
        maincontent.add(panel_TimKiemNV, "timkiemnv");
        panel_TimKiemNV.setLayout(null);
        
        JLabel lblTitle_TKNV = new JLabel("TÌM KIẾM NHÂN VIÊN");
        lblTitle_TKNV.setBounds(574, 31, 286, 43);
        lblTitle_TKNV.setFont(new Font("Times New Roman", Font.BOLD, 25));
        panel_TimKiemNV.add(lblTitle_TKNV);
        
        JLabel lblTenNV_TKNV = new JLabel("Tên nhân viên:");
        lblTenNV_TKNV.setBounds(53, 105, 163, 43);
        lblTenNV_TKNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        panel_TimKiemNV.add(lblTenNV_TKNV);
        
        txt_TenNV_TKNV = new JTextField();
        txt_TenNV_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txt_TenNV_TKNV.setBounds(240, 108, 448, 43);
        txt_TenNV_TKNV.setColumns(10);
        panel_TimKiemNV.add(txt_TenNV_TKNV);
        
        JLabel lblSDT_TKNV = new JLabel("Số điện thoại:");
        lblSDT_TKNV.setBounds(930, 105, 139, 43);
        lblSDT_TKNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        panel_TimKiemNV.add(lblSDT_TKNV);
        
        txtSDT_TKNV = new JTextField();
        txtSDT_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txtSDT_TKNV.setBounds(1079, 108, 370, 43);
        txtSDT_TKNV.setColumns(10);
        panel_TimKiemNV.add(txtSDT_TKNV);
        
        JLabel lblGioiTinh_TK = new JLabel("Giới tính:");
        lblGioiTinh_TK.setBounds(53, 171, 163, 43);
        lblGioiTinh_TK.setFont(new Font("Times New Roman", Font.BOLD, 21));
        panel_TimKiemNV.add(lblGioiTinh_TK);
        
        JComboBox cboGioiTinh_TKNV = new JComboBox();
        cboGioiTinh_TKNV.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
        cboGioiTinh_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboGioiTinh_TKNV.setBounds(240, 174, 163, 43);
        panel_TimKiemNV.add(cboGioiTinh_TKNV);
        
        JLabel lblVaiTro_TKNV = new JLabel("Vai trò:");
        lblVaiTro_TKNV.setBounds(930, 171, 139, 43);
        lblVaiTro_TKNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        panel_TimKiemNV.add(lblVaiTro_TKNV);
        
        JComboBox cboVaiTro_TKNV = new JComboBox();
        cboVaiTro_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboVaiTro_TKNV.setBounds(1079, 174, 370, 43);
        panel_TimKiemNV.add(cboVaiTro_TKNV);
        
        JButton btnXemCT_TKNV = new JButton("Xem chi tiết");
        btnXemCT_TKNV.setBounds(1079, 382, 125, 36);
        btnXemCT_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        btnXemCT_TKNV.setBackground(Color.WHITE);
        panel_TimKiemNV.add(btnXemCT_TKNV);
        
        JButton btnLamMoi_TKNV = new JButton("Làm mới");
        btnLamMoi_TKNV.setBounds(1297, 382, 125, 36);
        btnLamMoi_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        panel_TimKiemNV.add(btnLamMoi_TKNV);
        
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(625, 600, 17, 48);
        panel_TimKiemNV.add(scrollBar);
        
        JScrollPane scrollPane_TKNV = new JScrollPane();
        scrollPane_TKNV.setBounds(10, 429, 1564, 499);
        panel_TimKiemNV.add(scrollPane_TKNV);
        
        table_TKNV = new JTable();
        table_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        table_TKNV.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "Ng\u00E0y sinh", "Gi\u1EDBi t\u00EDnh", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9", "Ch\u1EE9c v\u1EE5", "\u1EA2nh nh\u00E2n vi\u00EAn", "T\u00E0i kho\u1EA3n"
        	}
        ));
        scrollPane_TKNV.setViewportView(table_TKNV);
        
        JPanel panelDiaChi_TKNV = new JPanel();
        panelDiaChi_TKNV.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\u0110\u1ECBa ch\u1EC9", TitledBorder.LEADING, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 14), new Color(0, 0, 0)));
        panelDiaChi_TKNV.setBounds(53, 241, 1396, 99);
        panel_TimKiemNV.add(panelDiaChi_TKNV);
        panelDiaChi_TKNV.setLayout(null);
        
        txtTinh_TKNV = new JTextField();
        txtTinh_TKNV.setBounds(186, 28, 360, 33);
        txtTinh_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txtTinh_TKNV.setColumns(10);
        panelDiaChi_TKNV.add(txtTinh_TKNV);
        
        JLabel lblTinh_TKNV = new JLabel("Tỉnh:");
        lblTinh_TKNV.setBounds(22, 28, 86, 32);
        lblTinh_TKNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        panelDiaChi_TKNV.add(lblTinh_TKNV);
        
        txtHuyen_TKNV = new JTextField();
        txtHuyen_TKNV.setBounds(1025, 28, 344, 33);
        txtHuyen_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txtHuyen_TKNV.setColumns(10);
        panelDiaChi_TKNV.add(txtHuyen_TKNV);
        
        JLabel lblHuyen_TKNV = new JLabel("Huyện:");
        lblHuyen_TKNV.setBounds(874, 29, 86, 32);
        lblHuyen_TKNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        panelDiaChi_TKNV.add(lblHuyen_TKNV);
        
        JPanel panel_CapNhatNV = new JPanel();
        maincontent.add(panel_CapNhatNV, "capnhatnv");
        panel_CapNhatNV.setLayout(null);
        
        JLabel lblTitle_CNNV = new JLabel("CẬP NHẬT NHÂN VIÊN");
        lblTitle_CNNV.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblTitle_CNNV.setBounds(570, 33, 277, 52);
        panel_CapNhatNV.add(lblTitle_CNNV);
        
        JPanel panelAnh_CNNV = new JPanel();
        panelAnh_CNNV.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelAnh_CNNV.setBounds(64, 115, 339, 328);
        panel_CapNhatNV.add(panelAnh_CNNV);
        panelAnh_CNNV.setLayout(null);
        
        JLabel lblMaNV_CNNV = new JLabel("Mã nhân viên:");
        lblMaNV_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblMaNV_CNNV.setBounds(498, 115, 152, 37);
        panel_CapNhatNV.add(lblMaNV_CNNV);
        
        JLabel lblTenNV_CNNV = new JLabel("Tên nhân viên:");
        lblTenNV_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblTenNV_CNNV.setBounds(498, 163, 152, 37);
        panel_CapNhatNV.add(lblTenNV_CNNV);
        
        JLabel lblSDT_CNNV = new JLabel("Số điện thoại:");
        lblSDT_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblSDT_CNNV.setBounds(498, 211, 152, 37);
        panel_CapNhatNV.add(lblSDT_CNNV);
        
        txtMaNV_CNNV = new JTextField();
        txtMaNV_CNNV.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        txtMaNV_CNNV.setEditable(false);
        txtMaNV_CNNV.setBounds(660, 115, 187, 37);
        panel_CapNhatNV.add(txtMaNV_CNNV);
        txtMaNV_CNNV.setColumns(10);
        
        txtTenNV_CNNV = new JTextField();
        txtTenNV_CNNV.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        txtTenNV_CNNV.setColumns(10);
        txtTenNV_CNNV.setBounds(660, 163, 379, 37);
        panel_CapNhatNV.add(txtTenNV_CNNV);
        
        txtSDT_CNNV = new JTextField();
        txtSDT_CNNV.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        txtSDT_CNNV.setColumns(10);
        txtSDT_CNNV.setBounds(660, 211, 379, 37);
        panel_CapNhatNV.add(txtSDT_CNNV);
        
        JLabel lblGioiTinh_CNNV = new JLabel("Giới tính:");
        lblGioiTinh_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblGioiTinh_CNNV.setBounds(890, 115, 105, 37);
        panel_CapNhatNV.add(lblGioiTinh_CNNV);
        
        JLabel lblNgaySinh_CNNV = new JLabel("Ngày sinh:");
        lblNgaySinh_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblNgaySinh_CNNV.setBounds(1209, 115, 114, 37);
        panel_CapNhatNV.add(lblNgaySinh_CNNV);
        
        JComboBox cboGIoiTinh_CNNV = new JComboBox();
        cboGIoiTinh_CNNV.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
        cboGIoiTinh_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboGIoiTinh_CNNV.setBounds(1044, 115, 105, 32);
        panel_CapNhatNV.add(cboGIoiTinh_CNNV);
        
        JDateChooser dateNgaySinh_CNNV = new JDateChooser();
        dateNgaySinh_CNNV.setDateFormatString("dd,MM, yyyy");
        dateNgaySinh_CNNV.setBounds(1333, 115, 152, 37);
        panel_CapNhatNV.add(dateNgaySinh_CNNV);
        
        JLabel lblChucVu_CNNV = new JLabel("Chức vụ:");
        lblChucVu_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblChucVu_CNNV.setBounds(1127, 163, 136, 37);
        panel_CapNhatNV.add(lblChucVu_CNNV);
        
        JComboBox cboChucVu_CNNV = new JComboBox();
        cboChucVu_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboChucVu_CNNV.setBounds(1260, 163, 225, 37);
        panel_CapNhatNV.add(cboChucVu_CNNV);
        
        JLabel lblTaiKhoan_CNNV = new JLabel("Tài khoản:");
        lblTaiKhoan_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblTaiKhoan_CNNV.setBounds(1127, 211, 136, 37);
        panel_CapNhatNV.add(lblTaiKhoan_CNNV);
        
        JComboBox cboTaiKhoan_CNNV = new JComboBox();
        cboTaiKhoan_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboTaiKhoan_CNNV.setBounds(1260, 211, 225, 37);
        panel_CapNhatNV.add(cboTaiKhoan_CNNV);
        
        JPanel panelDiaChi_CNNV = new JPanel();
        panelDiaChi_CNNV.setLayout(null);
        panelDiaChi_CNNV.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\u0110\u1ECBa ch\u1EC9", TitledBorder.LEADING, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 14), new Color(0, 0, 0)));
        panelDiaChi_CNNV.setBounds(498, 269, 987, 102);
        panel_CapNhatNV.add(panelDiaChi_CNNV);
        
        txtTinh_CNNV = new JTextField();
        txtTinh_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txtTinh_CNNV.setColumns(10);
        txtTinh_CNNV.setBounds(90, 28, 360, 33);
        panelDiaChi_CNNV.add(txtTinh_CNNV);
        
        JLabel lblTinh_CNNV = new JLabel("Tỉnh:");
        lblTinh_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblTinh_CNNV.setBounds(22, 28, 58, 32);
        panelDiaChi_CNNV.add(lblTinh_CNNV);
        
        
        JLabel lblHuyen_CNNV = new JLabel("Huyện:");
        lblHuyen_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblHuyen_CNNV.setBounds(527, 28, 86, 32);
        panelDiaChi_CNNV.add(lblHuyen_CNNV);
        
        txtHuyen_CNNV = new JTextField();
        txtHuyen_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txtHuyen_CNNV.setColumns(10);
        txtHuyen_CNNV.setBounds(606, 28, 360, 33);
        panelDiaChi_CNNV.add(txtHuyen_CNNV);
        
        JPanel panelTK_CNNV = new JPanel();
        panelTK_CNNV.setLayout(null);
        panelTK_CNNV.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 14), new Color(0, 0, 0)));
        panelTK_CNNV.setBounds(498, 396, 987, 102);
        panel_CapNhatNV.add(panelTK_CNNV);
        
        txtTenNV_TK_CNNV = new JTextField();
        txtTenNV_TK_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txtTenNV_TK_CNNV.setColumns(10);
        txtTenNV_TK_CNNV.setBounds(190, 28, 260, 33);
        panelTK_CNNV.add(txtTenNV_TK_CNNV);
        
        JLabel lblTenNV_TK_CNNV = new JLabel("Tên nhân viên:");
        lblTenNV_TK_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblTenNV_TK_CNNV.setBounds(22, 28, 132, 32);
        panelTK_CNNV.add(lblTenNV_TK_CNNV);
        
        JLabel lblChucVu_TK_CNNV = new JLabel("Chức vụ:");
        lblChucVu_TK_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblChucVu_TK_CNNV.setBounds(599, 28, 132, 32);
        panelTK_CNNV.add(lblChucVu_TK_CNNV);
        
        JComboBox cboChucVu_TK_CNNV = new JComboBox();
        cboChucVu_TK_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboChucVu_TK_CNNV.setBounds(741, 26, 225, 37);
        panelTK_CNNV.add(cboChucVu_TK_CNNV);
        
        JButton btnChonAnh_CNNV = new JButton("Chọn ảnh");
        btnChonAnh_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        btnChonAnh_CNNV.setBounds(171, 469, 119, 37);
        panel_CapNhatNV.add(btnChonAnh_CNNV);
        
        JButton btnLamMoi_CNNV = new JButton("Làm mới");
        btnLamMoi_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        btnLamMoi_CNNV.setBounds(890, 532, 119, 37);
        panel_CapNhatNV.add(btnLamMoi_CNNV);
        
        JButton btnKhoiPhuc_CNNV = new JButton("Khôi phục");
        btnKhoiPhuc_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        btnKhoiPhuc_CNNV.setBounds(1117, 532, 119, 37);
        panel_CapNhatNV.add(btnKhoiPhuc_CNNV);
        
        JButton btnCapNhat_CNNV = new JButton("Cập nhật");
        btnCapNhat_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        btnCapNhat_CNNV.setBounds(1333, 532, 119, 37);
        panel_CapNhatNV.add(btnCapNhat_CNNV);
        
        JScrollPane scrollPane_CNNV = new JScrollPane();
        scrollPane_CNNV.setBounds(10, 594, 1564, 334);
        panel_CapNhatNV.add(scrollPane_CNNV);
        
        table_CNNV = new JTable();
        table_CNNV.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "Ng\u00E0y sinh", "Gi\u1EDBi t\u00EDnh", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9", "Ch\u1EE9c v\u1EE5", "\u1EA2nh", "T\u00E0i kho\u1EA3n"
        	}
        ));
        table_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        scrollPane_CNNV.setViewportView(table_CNNV);
    }
}