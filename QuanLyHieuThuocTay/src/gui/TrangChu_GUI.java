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

import javax.swing.BorderFactory;
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
    private JTable table_tkt;
    private JTextField txt_kh_MaKH;
    private JTextField txt_kh_SDT;
    private JTextField txt_kh_TenKH;
    private JTable table_tkkh;
    private JTextField txt_cnkh_MaKh;
    private JTextField txt_cnkh_SDt;
    private JTextField txt_cnkh_tenkh;
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
    private JTable table_tshh;
    private JTable table_tshhan;
    private JTable table_5;
    private JTextField text_tkhd_TenKH;
    private JTextField text_tkhd_sdtkh;
    private JTextField text_tkhd_sdtnv;
    private JTextField text_tkhd_tennv;
    private JTextField text_tkhd_ngaylaphd;
    private JTable table_tkhd;
    private JTextField text_tktn_ngay;
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
    private JTextField txt_kh_dc;
    private JTextField txt_cnkh_dc;

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
        
        JComboBox<String> cb_loctenthuoc = new JComboBox<String>();
        cb_loctenthuoc.setBounds(154, 109, 415, 28);
        pn_timkiemthuoc.add(cb_loctenthuoc);
        
        JButton btn_lammoitkthuoc = new JButton("Làm Mới");
        btn_lammoitkthuoc.setBounds(217, 153, 136, 31);
        btn_lammoitkthuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn_timkiemthuoc.add(btn_lammoitkthuoc);
        
        JComboBox<String> cb_lockethuoc = new JComboBox<String>();
        cb_lockethuoc.setModel(new DefaultComboBoxModel(new String[] {"sss"}));
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
        ));
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
        ));
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
        
        JPanel pn_Nhapthongtintk = new JPanel();
        pn_Nhapthongtintk.setBounds(0, 0, 1584, 161);
        pn_tkhd.add(pn_Nhapthongtintk);
        pn_Nhapthongtintk.setLayout(null);
        
        JLabel lbl_tkhd = new JLabel("Tìm Kiếm Hóa Đơn");
        lbl_tkhd.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbl_tkhd.setBounds(618, 5, 338, 47);
        pn_Nhapthongtintk.add(lbl_tkhd);
        
        JLabel lbl_tkhd_Tenkh = new JLabel("Tên Khách Hàng :");
        lbl_tkhd_Tenkh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tkhd_Tenkh.setBounds(43, 63, 148, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_Tenkh);
        
        JLabel lbl_tkhd_Mahd = new JLabel("Mã Hóa Đơn :");
        lbl_tkhd_Mahd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tkhd_Mahd.setBounds(43, 104, 148, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_Mahd);
        
        JLabel lbl_tkhd_SDTKH = new JLabel("SĐT Khách Hàng :");
        lbl_tkhd_SDTKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tkhd_SDTKH.setBounds(472, 63, 154, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_SDTKH);
        
        JLabel lbl_tkhd_tenNV = new JLabel("Tên Nhân Viên :");
        lbl_tkhd_tenNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tkhd_tenNV.setBounds(924, 63, 136, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_tenNV);
        
        JLabel lbl_tkhd_sdtnv = new JLabel("SĐT Nhân Viên :");
        lbl_tkhd_sdtnv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tkhd_sdtnv.setBounds(472, 104, 154, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_sdtnv);
        
        JLabel lbl_tkhd_Ngaylaphd = new JLabel("Ngày Lập HĐ :");
        lbl_tkhd_Ngaylaphd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tkhd_Ngaylaphd.setBounds(924, 104, 136, 30);
        pn_Nhapthongtintk.add(lbl_tkhd_Ngaylaphd);
        
        text_tkhd_TenKH = new JTextField();
        text_tkhd_TenKH.setBounds(194, 63, 268, 30);
        pn_Nhapthongtintk.add(text_tkhd_TenKH);
        text_tkhd_TenKH.setColumns(10);
        
        text_tkhd_Mahd = new JTextField();
        text_tkhd_Mahd.setColumns(10);
        text_tkhd_Mahd.setBounds(194, 104, 268, 30);
        pn_Nhapthongtintk.add(text_tkhd_Mahd);
        
        text_tkhd_sdtkh = new JTextField();
        text_tkhd_sdtkh.setColumns(10);
        text_tkhd_sdtkh.setBounds(636, 63, 268, 30);
        pn_Nhapthongtintk.add(text_tkhd_sdtkh);
        
        text_tkhd_sdtnv = new JTextField();
        text_tkhd_sdtnv.setColumns(10);
        text_tkhd_sdtnv.setBounds(636, 104, 268, 30);
        pn_Nhapthongtintk.add(text_tkhd_sdtnv);
        
        text_tkhd_tennv = new JTextField();
        text_tkhd_tennv.setColumns(10);
        text_tkhd_tennv.setBounds(1070, 63, 268, 30);
        pn_Nhapthongtintk.add(text_tkhd_tennv);
        
        text_tkhd_ngaylaphd = new JTextField();
        text_tkhd_ngaylaphd.setColumns(10);
        text_tkhd_ngaylaphd.setBounds(1070, 104, 268, 30);
        pn_Nhapthongtintk.add(text_tkhd_ngaylaphd);
        
        JButton btn_tkhd_lammoi = new JButton("Làm Mới");
        btn_tkhd_lammoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tkhd_lammoi.setBounds(1413, 63, 161, 30);
        pn_Nhapthongtintk.add(btn_tkhd_lammoi);
        
        JButton btn_tkhd_xemchitiet = new JButton("Xem Chi Tiết");
        btn_tkhd_xemchitiet.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		XemchitietHD_GUI XemchitietHD =new XemchitietHD_GUI(QuanLyHieuThuocTay);
                
                // 2. Hiển thị cửa sổ đó lên
        		XemchitietHD.setVisible(true);
        	}
        });
        btn_tkhd_xemchitiet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tkhd_xemchitiet.setBounds(1413, 104, 161, 30);
        pn_Nhapthongtintk.add(btn_tkhd_xemchitiet);
        
        JScrollPane scP_tabletkhd = new JScrollPane();
        scP_tabletkhd.setBounds(10, 172, 1564, 767);
        pn_tkhd.add(scP_tabletkhd);
        
        table_tkhd = new JTable();
        table_tkhd.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 H\u00F3a \u0110\u01A1n", "M\u00E3 Kh\u00E1ch H\u00E0ng", "Ng\u00E0y L\u1EADp HD", "T\u00EAn Kh\u00E1ch H\u00E0ng", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i KH", "T\u00EAn Nh\u00E2n Vi\u00EAn", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i NV", "T\u1ED5ng Ti\u1EC1n"
        	}
        ));
        scP_tabletkhd.setViewportView(table_tkhd);
        
        JPanel pn_Timkiemthuoc = new JPanel();
        maincontent.add(pn_Timkiemthuoc, "timkiemSP");
        pn_Timkiemthuoc.setLayout(null);
        
        JLabel lbl_tkthuoc = new JLabel("TÌM KIẾM THUỐC");
        lbl_tkthuoc.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl_tkthuoc.setBounds(638, 11, 260, 46);
        pn_Timkiemthuoc.add(lbl_tkthuoc);
        
        JLabel lbl_tkt_kethuoc = new JLabel("Kệ Thuốc :");
        lbl_tkt_kethuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tkt_kethuoc.setBounds(10, 62, 100, 30);
        pn_Timkiemthuoc.add(lbl_tkt_kethuoc);
        
        JLabel lbl_tkt_Tenthuoc = new JLabel("Tên Thuốc :");
        lbl_tkt_Tenthuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tkt_Tenthuoc.setBounds(10, 113, 100, 30);
        pn_Timkiemthuoc.add(lbl_tkt_Tenthuoc);
        
        JLabel lbl_tkt_ncc = new JLabel("Nhà Cung Cấp :");
        lbl_tkt_ncc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tkt_ncc.setBounds(838, 62, 135, 30);
        pn_Timkiemthuoc.add(lbl_tkt_ncc);
        
        JComboBox<String> cb_tkt_kethuoc = new JComboBox<String>();
        cb_tkt_kethuoc.setBounds(120, 62, 529, 30);
        pn_Timkiemthuoc.add(cb_tkt_kethuoc);
        
        JComboBox<String> cb_tkt_tenthuoc = new JComboBox<String>();
        cb_tkt_tenthuoc.setBounds(120, 115, 529, 30);
        pn_Timkiemthuoc.add(cb_tkt_tenthuoc);
        
        JComboBox<String> cb_tkt_ncc = new JComboBox<String>();
        cb_tkt_ncc.setBounds(983, 62, 540, 30);
        pn_Timkiemthuoc.add(cb_tkt_ncc);
        
        JButton btn_tkt_xemchitiet = new JButton("Xem Chi Tiết");
        btn_tkt_xemchitiet.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ChiTietThuoc_GUI Chitietphieudatthuoc = new ChiTietThuoc_GUI(QuanLyHieuThuocTay);
                
                // 2. Hiển thị cửa sổ đó lên
                Chitietphieudatthuoc.setVisible(true);
        	}
        });
        btn_tkt_xemchitiet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tkt_xemchitiet.setBounds(983, 113, 179, 36);
        pn_Timkiemthuoc.add(btn_tkt_xemchitiet);
        
        JButton btn_tkt_Lammoi = new JButton("Làm Mới");
        btn_tkt_Lammoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tkt_Lammoi.setBounds(1388, 110, 135, 36);
        pn_Timkiemthuoc.add(btn_tkt_Lammoi);
        
        JPanel pn_tkt_table = new JPanel();
        pn_tkt_table.setBounds(0, 167, 1584, 772);
        pn_Timkiemthuoc.add(pn_tkt_table);
        pn_tkt_table.setLayout(null);
        
        JScrollPane scP_tkt_table = new JScrollPane();
        scP_tkt_table.setBounds(10, 0, 1564, 772);
        pn_tkt_table.add(scP_tkt_table);
        
        table_tkt = new JTable();
        table_tkt.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c"
        	}
        ));
        table_tkt.getColumnModel().getColumn(2).setPreferredWidth(65);
        scP_tkt_table.setViewportView(table_tkt);
        
        JPanel pn_TKKhachHang = new JPanel();
        maincontent.add(pn_TKKhachHang, "timkiemkh");
        pn_TKKhachHang.setLayout(null);
        
        JPanel pnl_KH_north = new JPanel();
        pnl_KH_north.setBounds(0, 0, 1584, 177);
        pn_TKKhachHang.add(pnl_KH_north);
        pnl_KH_north.setLayout(null);
        
        JLabel lbl_kh_SDT = new JLabel("Số điện thoại : ");
        lbl_kh_SDT.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lbl_kh_SDT.setBounds(38, 130, 125, 24);
        pnl_KH_north.add(lbl_kh_SDT);
        
        JLabel lbl_kh_TimKiemKH = new JLabel("TÌM KIẾM KHÁCH HÀNG");
        lbl_kh_TimKiemKH.setBounds(561, 11, 454, 43);
        pnl_KH_north.add(lbl_kh_TimKiemKH);
        lbl_kh_TimKiemKH.setFont(new Font("Times New Roman", Font.BOLD, 36));
        
        JLabel lbl_kh_TenKH = new JLabel("Tên khách hàng : ");
        lbl_kh_TenKH.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lbl_kh_TenKH.setBounds(755, 65, 156, 24);
        pnl_KH_north.add(lbl_kh_TenKH);
        
        JLabel lbl_kh_MaKH = new JLabel("Mã khách hàng :");
        lbl_kh_MaKH.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lbl_kh_MaKH.setBounds(38, 64, 148, 24);
        pnl_KH_north.add(lbl_kh_MaKH);
        
        JLabel lbl_kh_Diachi = new JLabel("Địa chỉ :");
        lbl_kh_Diachi.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lbl_kh_Diachi.setBounds(759, 131, 79, 24);
        pnl_KH_north.add(lbl_kh_Diachi);
        
        txt_kh_MaKH = new JTextField();
        txt_kh_MaKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txt_kh_MaKH.setBounds(196, 64, 471, 32);
        pnl_KH_north.add(txt_kh_MaKH);
        txt_kh_MaKH.setColumns(10);
        
        txt_kh_SDT = new JTextField();
        txt_kh_SDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txt_kh_SDT.setColumns(10);
        txt_kh_SDT.setBounds(196, 127, 471, 32);
        pnl_KH_north.add(txt_kh_SDT);
        
        txt_kh_TenKH = new JTextField();
        txt_kh_TenKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txt_kh_TenKH.setColumns(10);
        txt_kh_TenKH.setBounds(919, 65, 471, 32);
        pnl_KH_north.add(txt_kh_TenKH);
        
        JButton btn_kh_Lammoi = new JButton("Làm mới");
        btn_kh_Lammoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_kh_Lammoi.setBounds(1424, 123, 108, 32);
        pnl_KH_north.add(btn_kh_Lammoi);
        
        txt_kh_dc = new JTextField();
        txt_kh_dc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txt_kh_dc.setColumns(10);
        txt_kh_dc.setBounds(919, 123, 471, 32);
        pnl_KH_north.add(txt_kh_dc);
        
        JPanel pnl_KH_south = new JPanel();
        pnl_KH_south.setBounds(0, 179, 1584, 760);
        pn_TKKhachHang.add(pnl_KH_south);
        pnl_KH_south.setLayout(null);
        
        JScrollPane scrollPane_tkkh = new JScrollPane();
        scrollPane_tkkh.setBounds(0, 0, 1584, 616);
        pnl_KH_south.add(scrollPane_tkkh);
        
        table_tkkh = new JTable();
        table_tkkh.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null},
        		{null, null, null, null},
        		{null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 kh\u00E1ch h\u00E0ng", "T\u00EAn kh\u00E1ch h\u00E0ng", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9"
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
        pnlNorth.setBounds(10, 11, 1564, 241);
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
        
        JLabel lblDC = new JLabel("Địa chỉ  :");
        lblDC.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblDC.setBounds(686, 146, 163, 25);
        pnlNorth.add(lblDC);
        
        txt_cnkh_MaKh = new JTextField();
        txt_cnkh_MaKh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txt_cnkh_MaKh.setBounds(354, 89, 301, 33);
        pnlNorth.add(txt_cnkh_MaKh);
        txt_cnkh_MaKh.setColumns(10);
        
        txt_cnkh_SDt = new JTextField();
        txt_cnkh_SDt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txt_cnkh_SDt.setColumns(10);
        txt_cnkh_SDt.setBounds(354, 144, 301, 33);
        pnlNorth.add(txt_cnkh_SDt);
        
        txt_cnkh_tenkh = new JTextField();
        txt_cnkh_tenkh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txt_cnkh_tenkh.setColumns(10);
        txt_cnkh_tenkh.setBounds(859, 89, 488, 33);
        pnlNorth.add(txt_cnkh_tenkh);
        
        JButton btn_cnkh_CapNhat = new JButton("Cập nhật");
        btn_cnkh_CapNhat.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btn_cnkh_CapNhat.setBounds(1011, 194, 118, 31);
        pnlNorth.add(btn_cnkh_CapNhat);
        
        JButton btn_cnkh_Khoiphuc = new JButton("Khôi phục");
        btn_cnkh_Khoiphuc.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btn_cnkh_Khoiphuc.setBounds(859, 194, 118, 31);
        pnlNorth.add(btn_cnkh_Khoiphuc);
        
        txt_cnkh_dc = new JTextField();
        txt_cnkh_dc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txt_cnkh_dc.setColumns(10);
        txt_cnkh_dc.setBounds(859, 138, 488, 33);
        pnlNorth.add(txt_cnkh_dc);
        
        JPanel pnlTimkiem = new JPanel();
        pnlTimkiem.setBounds(191, 263, 1191, 84);
        
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
        
        JScrollPane scrollPane_CapNhatKH = new JScrollPane();
        scrollPane_CapNhatKH.setBounds(10, 358, 1564, 570);
        pn_CapnhatKh.add(scrollPane_CapNhatKH);
        
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
        
        JButton btn_cnt_ChonAnh = new JButton("Chọn Ảnh");
        btn_cnt_ChonAnh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_cnt_ChonAnh.setBounds(125, 363, 132, 40);
        pn_Capnhatthuoc_nhaptt.add(btn_cnt_ChonAnh);
        
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
        
        JComboBox<String> cb_cntdvt = new JComboBox<String>();
        cb_cntdvt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cb_cntdvt.setBounds(560, 280, 394, 30);
        pn_Capnhatthuoc_nhaptt.add(cb_cntdvt);
        
        JComboBox<String> cb_cntncc = new JComboBox<String>();
        cb_cntncc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cb_cntncc.setBounds(1168, 75, 394, 30);
        pn_Capnhatthuoc_nhaptt.add(cb_cntncc);
        
        JComboBox<String> cb_cnttkt = new JComboBox<String>();
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
        
        JComboBox<String> cb_cnt_tktkt = new JComboBox<String>();
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
        ));
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
        
        JComboBox<String> cb_ttdvt = new JComboBox<String>();
        cb_ttdvt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cb_ttdvt.setBounds(560, 280, 394, 30);
        pn_Themthuoc.add(cb_ttdvt);
        
        JComboBox<String> cb_ttncc = new JComboBox<String>();
        cb_ttncc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cb_ttncc.setBounds(1168, 75, 394, 30);
        pn_Themthuoc.add(cb_ttncc);
        
        JComboBox<String> cb_tttkt = new JComboBox<String>();
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
        ));
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
        
        JPanel pn_ttf = new JPanel();
        pn_ttf.setBounds(0, 0, 1584, 123);
        pn_themthuocfile.add(pn_ttf);
        pn_ttf.setLayout(null);
        
        JLabel lbl_ttf_tieude = new JLabel("THÊM NHIỀU THUỐC");
        lbl_ttf_tieude.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbl_ttf_tieude.setBounds(554, 11, 429, 47);
        pn_ttf.add(lbl_ttf_tieude);
        
        JButton btn_ttf_chonfile = new JButton("Chọn File");
        btn_ttf_chonfile.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_ttf_chonfile.setBounds(164, 74, 139, 38);
        pn_ttf.add(btn_ttf_chonfile);
        
        JLabel lbl_ttf_tongsothuoc = new JLabel("Tống số thuốc :");
        lbl_ttf_tongsothuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ttf_tongsothuoc.setBounds(340, 81, 131, 24);
        pn_ttf.add(lbl_ttf_tongsothuoc);
        
        JLabel lbl_ttfile_hienthitongsothuoc = new JLabel("0");
        lbl_ttfile_hienthitongsothuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ttfile_hienthitongsothuoc.setBounds(468, 81, 67, 24);
        pn_ttf.add(lbl_ttfile_hienthitongsothuoc);
        
        JButton btn_ttf_lammoi = new JButton("Làm Mới");
        btn_ttf_lammoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_ttf_lammoi.setBounds(1125, 74, 139, 38);
        pn_ttf.add(btn_ttf_lammoi);
        
        JButton btn_ttf_them = new JButton("Thêm");
        btn_ttf_them.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_ttf_them.setBounds(1274, 74, 139, 38);
        pn_ttf.add(btn_ttf_them);
        
        JButton btn_ttf_luu = new JButton("Lưu");
        btn_ttf_luu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_ttf_luu.setBounds(1423, 74, 139, 38);
        pn_ttf.add(btn_ttf_luu);
        
        JPanel pn_ttf_table = new JPanel();
        pn_ttf_table.setBounds(0, 122, 1584, 817);
        pn_themthuocfile.add(pn_ttf_table);
        pn_ttf_table.setLayout(null);
        
        JScrollPane scP_ttf_table = new JScrollPane();
        scP_ttf_table.setBounds(10, 0, 1564, 817);
        pn_ttf_table.add(scP_ttf_table);
        
        table_2 = new JTable();
        table_2.setModel(new DefaultTableModel(
            	new Object[][] {
            		{null, null, null, null, null, null, null, null, null, null},
            	},
            	new String[] {
            		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c", "Th\u00E0nh Ph\u1EA7n"
            	}
            ));
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
        
        JPanel pn_thuocsaphethan = new JPanel();
        maincontent.add(pn_thuocsaphethan, "Thuocsaphethan");
        pn_thuocsaphethan.setLayout(null);
        
        JLabel lbl_tshh_tieude = new JLabel("Danh Sách Thuốc Sắp Hết Hạn");
        lbl_tshh_tieude.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbl_tshh_tieude.setBounds(506, 11, 537, 40);
        pn_thuocsaphethan.add(lbl_tshh_tieude);
        
        JScrollPane scP_tshh_table = new JScrollPane();
        scP_tshh_table.setBounds(10, 66, 1564, 811);
        pn_thuocsaphethan.add(scP_tshh_table);
        
        table_tshh = new JTable();
        table_tshh.setModel(new DefaultTableModel(
            	new Object[][] {
            		{null, null, null, null, null, null, null, null, null, null},
            	},
            	new String[] {
            		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c", "Th\u00E0nh Ph\u1EA7n"
            	}
            ));
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
        btn_tshh_xuatfile.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tshh_xuatfile.setBounds(1422, 888, 152, 40);
        pn_thuocsaphethan.add(btn_tshh_xuatfile);
        
        JPanel pn_thuocbanchay = new JPanel();
        maincontent.add(pn_thuocbanchay, "Thuocbanchay");
        pn_thuocbanchay.setLayout(null);
        
        JLabel lbl_tbc_tieude = new JLabel("Danh Sách Thuốc Bán Chạy");
        lbl_tbc_tieude.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbl_tbc_tieude.setBounds(564, 0, 481, 57);
        pn_thuocbanchay.add(lbl_tbc_tieude);
        
        JScrollPane scP_tbc_table = new JScrollPane();
        scP_tbc_table.setBounds(10, 55, 1564, 811);
        pn_thuocbanchay.add(scP_tbc_table);
        
        table_5 = new JTable();
        table_5.setModel(new DefaultTableModel(
            	new Object[][] {
            		{null, null, null, null, null, null, null, null, null, null},
            	},
            	new String[] {
            		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c", "Th\u00E0nh Ph\u1EA7n"
            	}
            ));
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
        scP_tbc_table.setViewportView(table_5);
        
        JButton btn_tbc_xuatfile = new JButton("Xuất File");
        btn_tbc_xuatfile.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tbc_xuatfile.setBounds(1422, 877, 152, 40);
        pn_thuocbanchay.add(btn_tbc_xuatfile);
        
        JPanel pn_thuocsaphethang = new JPanel();
        maincontent.add(pn_thuocsaphethang, "Thuocsaphethang");
        pn_thuocsaphethang.setLayout(null);
        
        JLabel lbl_tshhan_tieude = new JLabel("Danh Sách Thuốc Hết Hàng");
        lbl_tshhan_tieude.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbl_tshhan_tieude.setBounds(564, 0, 481, 57);
        pn_thuocsaphethang.add(lbl_tshhan_tieude);
        
        JScrollPane scP_tshhan_table = new JScrollPane();
        scP_tshhan_table.setBounds(10, 55, 1564, 811);
        pn_thuocsaphethang.add(scP_tshhan_table);
        
        table_tshhan = new JTable();
        table_tshhan.setModel(new DefaultTableModel(
            	new Object[][] {
            		{null, null, null, null, null, null, null, null, null, null},
            	},
            	new String[] {
            		"M\u00E3 Thu\u1ED1c", "T\u00EAn Thu\u1ED1c", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Nh\u00E0 Cung C\u1EA5p", "H\u1EA1n S\u1EED D\u1EE5ng", "T\u00EAn K\u1EC7 Thu\u1ED1c", "Th\u00E0nh Ph\u1EA7n"
            	}
            ));
        table_tshhan.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_tshhan.getColumnModel().getColumn(1).setPreferredWidth(130);
        table_tshhan.getColumnModel().getColumn(2).setPreferredWidth(15);
        table_tshhan.getColumnModel().getColumn(3).setPreferredWidth(15);
        table_tshhan.getColumnModel().getColumn(4).setPreferredWidth(15);
        table_tshhan.getColumnModel().getColumn(5).setPreferredWidth(15);
        table_tshhan.getColumnModel().getColumn(6).setPreferredWidth(100);
        table_tshhan.getColumnModel().getColumn(7).setPreferredWidth(25);
        table_tshhan.getColumnModel().getColumn(8).setPreferredWidth(100);
        table_tshhan.getColumnModel().getColumn(9).setPreferredWidth(300);
        scP_tshhan_table.setViewportView(table_tshhan);
        
        JButton btn_tshhan_xuatfile = new JButton("Xuất File");
        btn_tshhan_xuatfile.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tshhan_xuatfile.setBounds(1422, 877, 152, 40);
        pn_thuocsaphethang.add(btn_tshhan_xuatfile);
        
        JPanel pn_ThongkeHD = new JPanel();
        maincontent.add(pn_ThongkeHD, "ThongkeHD");
        pn_ThongkeHD.setLayout(null);
        
        JPanel pn_tkhd_tieude = new JPanel();
        pn_tkhd_tieude.setBounds(0, 0, 1584, 81);
        pn_ThongkeHD.add(pn_tkhd_tieude);
        pn_tkhd_tieude.setLayout(null);
        
        JLabel lbl_tkhd_tieude = new JLabel("Thống Kê Hóa Đơn");
        lbl_tkhd_tieude.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbl_tkhd_tieude.setBounds(623, 11, 337, 59);
        pn_tkhd_tieude.add(lbl_tkhd_tieude);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 80, 1584, 859);
        pn_ThongkeHD.add(tabbedPane);
        
        JPanel pn_tketheongay = new JPanel();
        tabbedPane.addTab("Theo Ngày", null, pn_tketheongay, null);
        pn_tketheongay.setLayout(null);
        
        JLabel lbl_Ngaythongke = new JLabel("Ngày Thống Kê :");
        lbl_Ngaythongke.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_Ngaythongke.setBounds(30, 11, 143, 30);
        pn_tketheongay.add(lbl_Ngaythongke);
        
        JLabel lbl_tktn_tongsohd = new JLabel("Tổng Số Hóa Đơn :");
        lbl_tktn_tongsohd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktn_tongsohd.setBounds(572, 11, 165, 30);
        pn_tketheongay.add(lbl_tktn_tongsohd);
        
        JLabel lbl_tktn_tongtiencachoadon = new JLabel("Tổng Tiền Các Hóa Đơn :");
        lbl_tktn_tongtiencachoadon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktn_tongtiencachoadon.setBounds(962, 11, 211, 30);
        pn_tketheongay.add(lbl_tktn_tongtiencachoadon);
        
        JLabel lbl_tktn_hientongsohd = new JLabel("1000");
        lbl_tktn_hientongsohd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktn_hientongsohd.setBounds(747, 11, 143, 30);
        pn_tketheongay.add(lbl_tktn_hientongsohd);
        
        JLabel lbl_tktn_hientongsotien = new JLabel("1000000000000000");
        lbl_tktn_hientongsotien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktn_hientongsotien.setBounds(1183, 11, 165, 30);
        pn_tketheongay.add(lbl_tktn_hientongsotien);
        
        JLabel lbl_tktn_vnd = new JLabel("VND");
        lbl_tktn_vnd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktn_vnd.setBounds(1358, 11, 50, 30);
        pn_tketheongay.add(lbl_tktn_vnd);
        
        text_tktn_ngay = new JTextField();
        text_tktn_ngay.setBounds(183, 11, 223, 30);
        pn_tketheongay.add(text_tktn_ngay);
        text_tktn_ngay.setColumns(10);
        
        JPanel pn_tktn_tktnv = new JPanel();
        pn_tktn_tktnv.setBounds(10, 52, 480, 779);
        pn_tketheongay.add(pn_tktn_tktnv);
        pn_tktn_tktnv.setLayout(null);
        
        JPanel pn_tktn_tktnv_boloc = new JPanel();
        pn_tktn_tktnv_boloc.setBackground(new Color(255, 255, 255));
        pn_tktn_tktnv_boloc.setBounds(10, 11, 459, 230);
        pn_tktn_tktnv.add(pn_tktn_tktnv_boloc);
        pn_tktn_tktnv_boloc.setLayout(null);
        
        JLabel lbl_tktn_tktnv_boloc = new JLabel("Thống Kê Theo :");
        lbl_tktn_tktnv_boloc.setBounds(21, 11, 142, 30);
        lbl_tktn_tktnv_boloc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn_tktn_tktnv_boloc.add(lbl_tktn_tktnv_boloc);
        
        JComboBox<String> cb_tktn_tktnv_boloc = new JComboBox<String>();
        cb_tktn_tktnv_boloc.setBounds(173, 11, 258, 30);
        pn_tktn_tktnv_boloc.add(cb_tktn_tktnv_boloc);
        
        JPanel pn_tktn_tktnv_boloc_pntk = new JPanel();
        pn_tktn_tktnv_boloc_pntk.setBorder(new TitledBorder(null, "T\u00ECm Ki\u1EBFm Nh\u00E2n Vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pn_tktn_tktnv_boloc_pntk.setBackground(new Color(255, 255, 255));
        pn_tktn_tktnv_boloc_pntk.setBounds(10, 63, 437, 153);
        pn_tktn_tktnv_boloc.add(pn_tktn_tktnv_boloc_pntk);
        pn_tktn_tktnv_boloc_pntk.setLayout(null);
        
        JLabel lbl_tktn_tktnv_boloc_manv = new JLabel("Mã Nhân Viên :");
        lbl_tktn_tktnv_boloc_manv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktn_tktnv_boloc_manv.setBounds(10, 33, 142, 30);
        pn_tktn_tktnv_boloc_pntk.add(lbl_tktn_tktnv_boloc_manv);
        
        JLabel lbl_tktn_tktnv_boloc_tennv = new JLabel("Tên Nhân Viên :");
        lbl_tktn_tktnv_boloc_tennv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktn_tktnv_boloc_tennv.setBounds(10, 85, 142, 30);
        pn_tktn_tktnv_boloc_pntk.add(lbl_tktn_tktnv_boloc_tennv);
        
        text_tktn_tktnv_boloc_manv = new JTextField();
        text_tktn_tktnv_boloc_manv.setColumns(10);
        text_tktn_tktnv_boloc_manv.setBounds(162, 33, 261, 30);
        pn_tktn_tktnv_boloc_pntk.add(text_tktn_tktnv_boloc_manv);
        
        text_tktn_tktnv_boloc_tennv = new JTextField();
        text_tktn_tktnv_boloc_tennv.setColumns(10);
        text_tktn_tktnv_boloc_tennv.setBounds(162, 85, 261, 30);
        pn_tktn_tktnv_boloc_pntk.add(text_tktn_tktnv_boloc_tennv);
        
        JScrollPane scrollPane_5 = new JScrollPane();
        scrollPane_5.setBounds(10, 263, 459, 505);
        pn_tktn_tktnv.add(scrollPane_5);
        
        table_8 = new JTable();
        table_8.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null},
        	},
        	new String[] {
        		"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn"
        	}
        ));
        scrollPane_5.setViewportView(table_8);
        
        JScrollPane scP_tktn_tktnv_table = new JScrollPane();
        scP_tktn_tktnv_table.setBounds(500, 52, 1069, 715);
        pn_tketheongay.add(scP_tktn_tktnv_table);
        
        table_tktn = new JTable();
        table_tktn.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null},
        	},
        	new String[] {
        		"M\u00E3 H\u00F3a \u0110\u01A1n", "S\u0110T Kh\u00E1ch H\u00E0ng", "T\u00EAn Kh\u00E1ch H\u00E0ng", "M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn", "T\u1ED5ng Ti\u1EC1n"
        	}
        ));
        scP_tktn_tktnv_table.setViewportView(table_tktn);
        
        JButton btn_tktn_Xuatfile = new JButton("Xuất File");
        btn_tktn_Xuatfile.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tktn_Xuatfile.setBounds(1426, 778, 143, 42);
        pn_tketheongay.add(btn_tktn_Xuatfile);
        
        JPanel pn_tketheothang = new JPanel();
        tabbedPane.addTab("Theo Tháng", null, pn_tketheothang, null);
        pn_tketheothang.setLayout(null);
        
        JLabel lbl_thang = new JLabel("Tháng :");
        lbl_thang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_thang.setBounds(20, 11, 69, 30);
        pn_tketheothang.add(lbl_thang);
        
        JLabel lbl_tktt_tongsohd = new JLabel("Tổng Số Hóa Đơn :");
        lbl_tktt_tongsohd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktt_tongsohd.setBounds(504, 11, 165, 30);
        pn_tketheothang.add(lbl_tktt_tongsohd);
        
        JLabel lbl_tktt_Tongtiencachd = new JLabel("Tổng Tiền Các Hóa Đơn :");
        lbl_tktt_Tongtiencachd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktt_Tongtiencachd.setBounds(504, 52, 211, 30);
        pn_tketheothang.add(lbl_tktt_Tongtiencachd);
        
        JLabel lbl_tktt_hiensohd = new JLabel("1000");
        lbl_tktt_hiensohd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktt_hiensohd.setBounds(679, 11, 143, 30);
        pn_tketheothang.add(lbl_tktt_hiensohd);
        
        JLabel lbl_tktt_hientongtienhd = new JLabel("10000000000");
        lbl_tktt_hientongtienhd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktt_hientongtienhd.setBounds(725, 52, 121, 30);
        pn_tketheothang.add(lbl_tktt_hientongtienhd);
        
        JLabel lbl_tktt_vnd = new JLabel("VND");
        lbl_tktt_vnd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktt_vnd.setBounds(856, 52, 50, 30);
        pn_tketheothang.add(lbl_tktt_vnd);
        
        JButton btn_tktt_xuatfile = new JButton("Xuất File");
        btn_tktt_xuatfile.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tktt_xuatfile.setBounds(1416, 778, 143, 42);
        pn_tketheothang.add(btn_tktt_xuatfile);
        
        JLabel lbl_nam = new JLabel("Năm :");
        lbl_nam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_nam.setBounds(244, 11, 55, 30);
        pn_tketheothang.add(lbl_nam);
        
        JComboBox<String> cb_nam = new JComboBox<String>();
        cb_nam.setBounds(309, 11, 185, 30);
        pn_tketheothang.add(cb_nam);
        
        JComboBox<String> cb_thang = new JComboBox<String>();
        cb_thang.setBounds(99, 11, 135, 30);
        pn_tketheothang.add(cb_thang);
        
        JScrollPane scP_tablehienhd = new JScrollPane();
        scP_tablehienhd.setBounds(500, 96, 436, 724);
        pn_tketheothang.add(scP_tablehienhd);
        
        table_hienhd_tktt = new JTable();
        table_hienhd_tktt.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"Ng\u00E0y", "T\u1ED5ng S\u1ED1 H\u00F3a \u0110\u01A1n", "T\u1ED5ng Ti\u1EC1n C\u00E1c H\u00F3a \u0110\u01A1n"
        	}  	
        ));
        scP_tablehienhd.setViewportView(table_hienhd_tktt);
        
        JButton btn_tktt_xemchitiet = new JButton("Xem Chi tiết");
        btn_tktt_xemchitiet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tktt_xemchitiet.setBounds(1224, 778, 143, 42);
        pn_tketheothang.add(btn_tktt_xemchitiet);
        
        JLabel lbl_tieudebd_tktt = new JLabel("Biểu Đồ Thống Kê Doanh Thu");
        lbl_tieudebd_tktt.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl_tieudebd_tktt.setBounds(995, 47, 323, 35);
        pn_tketheothang.add(lbl_tieudebd_tktt);
        
        JLabel lbl_hienthangvanam = new JLabel("Tháng 12 Năm 2025");
        lbl_hienthangvanam.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl_hienthangvanam.setBounds(1324, 47, 222, 35);
        pn_tketheothang.add(lbl_hienthangvanam);
        
        JPanel pn_tktt_tk = new JPanel();
        pn_tktt_tk.setLayout(null);
        pn_tktt_tk.setBounds(10, 52, 480, 779);
        pn_tketheothang.add(pn_tktt_tk);
        
        JPanel pn_tktt_tk_boloc = new JPanel();
        pn_tktt_tk_boloc.setLayout(null);
        pn_tktt_tk_boloc.setBackground(Color.WHITE);
        pn_tktt_tk_boloc.setBounds(10, 11, 459, 230);
        pn_tktt_tk.add(pn_tktt_tk_boloc);
        
        JLabel lbl_tktt_tk_boloc_thongktheonv = new JLabel("Thống Kê Theo :");
        lbl_tktt_tk_boloc_thongktheonv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktt_tk_boloc_thongktheonv.setBounds(21, 11, 142, 30);
        pn_tktt_tk_boloc.add(lbl_tktt_tk_boloc_thongktheonv);
        
        JComboBox<String> cb_tktt_tknv = new JComboBox<String>();
        cb_tktt_tknv.setBounds(173, 11, 258, 30);
        pn_tktt_tk_boloc.add(cb_tktt_tknv);
        
        JPanel pn_tktt_tk_boloc_tknv = new JPanel();
        pn_tktt_tk_boloc_tknv.setLayout(null);
        pn_tktt_tk_boloc_tknv.setBorder(new TitledBorder(null, "T\u00ECm Ki\u1EBFm Nh\u00E2n Vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pn_tktt_tk_boloc_tknv.setBackground(Color.WHITE);
        pn_tktt_tk_boloc_tknv.setBounds(10, 63, 437, 153);
        pn_tktt_tk_boloc.add(pn_tktt_tk_boloc_tknv);
        
        JLabel lbl_tktt_tk_boloc_manv = new JLabel("Mã Nhân Viên :");
        lbl_tktt_tk_boloc_manv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktt_tk_boloc_manv.setBounds(10, 33, 142, 30);
        pn_tktt_tk_boloc_tknv.add(lbl_tktt_tk_boloc_manv);
        
        JLabel lbl__tktt_tk_boloc_tennv = new JLabel("Tên Nhân Viên :");
        lbl__tktt_tk_boloc_tennv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl__tktt_tk_boloc_tennv.setBounds(10, 85, 142, 30);
        pn_tktt_tk_boloc_tknv.add(lbl__tktt_tk_boloc_tennv);
        
        text_tktt_tk_boloc_manv = new JTextField();
        text_tktt_tk_boloc_manv.setColumns(10);
        text_tktt_tk_boloc_manv.setBounds(162, 33, 261, 30);
        pn_tktt_tk_boloc_tknv.add(text_tktt_tk_boloc_manv);
        
        text_tktt_tk_boloc_tennv = new JTextField();
        text_tktt_tk_boloc_tennv.setColumns(10);
        text_tktt_tk_boloc_tennv.setBounds(162, 85, 261, 30);
        pn_tktt_tk_boloc_tknv.add(text_tktt_tk_boloc_tennv);
        
        JScrollPane scP_tktt = new JScrollPane();
        scP_tktt.setBounds(10, 263, 459, 505);
        pn_tktt_tk.add(scP_tktt);
        
        table_ttkt = new JTable();
        table_ttkt.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null},
        	},
        	new String[] {
        		"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn"
        	}
        ));
        scP_tktt.setViewportView(table_ttkt);
        
        JPanel pn_tketheonam = new JPanel();
        tabbedPane.addTab("Theo Năm", null, pn_tketheonam, null);
        pn_tketheonam.setLayout(null);
        
        JLabel lbl_tktnam_tongsohd = new JLabel("Tổng Số Hóa Đơn :");
        lbl_tktnam_tongsohd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktnam_tongsohd.setBounds(500, 11, 165, 30);
        pn_tketheonam.add(lbl_tktnam_tongsohd);
        
        JLabel lbl_tktnam_tongtiencachd = new JLabel("Tổng Tiền Các Hóa Đơn :");
        lbl_tktnam_tongtiencachd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktnam_tongtiencachd.setBounds(500, 52, 211, 30);
        pn_tketheonam.add(lbl_tktnam_tongtiencachd);
        
        JLabel lbl_tktn_hientshd = new JLabel("1000");
        lbl_tktn_hientshd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktn_hientshd.setBounds(675, 11, 143, 30);
        pn_tketheonam.add(lbl_tktn_hientshd);
        
        JLabel lbl_tktnam_hientongsotien = new JLabel("10000000000");
        lbl_tktnam_hientongsotien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktnam_hientongsotien.setBounds(721, 52, 121, 30);
        pn_tketheonam.add(lbl_tktnam_hientongsotien);
        
        JLabel lbl_tktnam_vnd = new JLabel("VND");
        lbl_tktnam_vnd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktnam_vnd.setBounds(852, 52, 50, 30);
        pn_tketheonam.add(lbl_tktnam_vnd);
        
        JButton btn_tktnam_xuatfile = new JButton("Xuất File");
        btn_tktnam_xuatfile.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tktnam_xuatfile.setBounds(1412, 778, 143, 42);
        pn_tketheonam.add(btn_tktnam_xuatfile);
        
        JLabel lbl_namthongke = new JLabel("Năm Thống Kê :");
        lbl_namthongke.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_namthongke.setBounds(26, 11, 143, 30);
        pn_tketheonam.add(lbl_namthongke);
        
        JComboBox<String> cb_loctheonam = new JComboBox<String>();
        cb_loctheonam.setBounds(179, 13, 185, 30);
        pn_tketheonam.add(cb_loctheonam);
        
        JScrollPane scP_table_hienhdtheothang = new JScrollPane();
        scP_table_hienhdtheothang.setBounds(500, 96, 436, 724);
        pn_tketheonam.add(scP_table_hienhdtheothang);
        
        table_11 = new JTable();
        table_11.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"Th\u00E1ng", "T\u1ED5ng S\u1ED1 H\u00F3a \u0110\u01A1n", "T\u1ED5ng Ti\u1EC1n C\u00E1c H\u00F3a \u0110\u01A1n"
        	}
        ));
        scP_table_hienhdtheothang.setViewportView(table_11);
        
        JButton btn_tktnam_xemchitiet = new JButton("Xem Chi tiết");
        btn_tktnam_xemchitiet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn_tktnam_xemchitiet.setBounds(1220, 778, 143, 42);
        pn_tketheonam.add(btn_tktnam_xemchitiet);
        
        JLabel lbl_tktnam_tieude = new JLabel("Biểu Đồ Thống Kê Doanh Thu");
        lbl_tktnam_tieude.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl_tktnam_tieude.setBounds(1059, 47, 323, 35);
        pn_tketheonam.add(lbl_tktnam_tieude);
        
        JLabel lbl_tktn_hiennam = new JLabel("Năm 2025");
        lbl_tktn_hiennam.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl_tktn_hiennam.setBounds(1388, 47, 121, 35);
        pn_tketheonam.add(lbl_tktn_hiennam);
        
        JPanel pn_tktnam_tk = new JPanel();
        pn_tktnam_tk.setLayout(null);
        pn_tktnam_tk.setBounds(10, 52, 480, 779);
        pn_tketheonam.add(pn_tktnam_tk);
        
        JPanel pn_tktnam_tk_boloc = new JPanel();
        pn_tktnam_tk_boloc.setLayout(null);
        pn_tktnam_tk_boloc.setBackground(Color.WHITE);
        pn_tktnam_tk_boloc.setBounds(10, 11, 459, 230);
        pn_tktnam_tk.add(pn_tktnam_tk_boloc);
        
        JLabel lbl_tktn_chonnamtk = new JLabel("Thống Kê Theo :");
        lbl_tktn_chonnamtk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktn_chonnamtk.setBounds(21, 11, 142, 30);
        pn_tktnam_tk_boloc.add(lbl_tktn_chonnamtk);
        
        JComboBox<String> cb_chonnamtk = new JComboBox<String>();
        cb_chonnamtk.setBounds(173, 11, 258, 30);
        pn_tktnam_tk_boloc.add(cb_chonnamtk);
        
        JPanel pn_tktnam_locnv = new JPanel();
        pn_tktnam_locnv.setLayout(null);
        pn_tktnam_locnv.setBorder(new TitledBorder(null, "T\u00ECm Ki\u1EBFm Nh\u00E2n Vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pn_tktnam_locnv.setBackground(Color.WHITE);
        pn_tktnam_locnv.setBounds(10, 63, 437, 153);
        pn_tktnam_tk_boloc.add(pn_tktnam_locnv);
        
        JLabel lbl_tktn_locnv_manv = new JLabel("Mã Nhân Viên :");
        lbl_tktn_locnv_manv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktn_locnv_manv.setBounds(10, 33, 142, 30);
        pn_tktnam_locnv.add(lbl_tktn_locnv_manv);
        
        JLabel lbl_tktnam_locnv_tennv = new JLabel("Tên Nhân Viên :");
        lbl_tktnam_locnv_tennv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_tktnam_locnv_tennv.setBounds(10, 85, 142, 30);
        pn_tktnam_locnv.add(lbl_tktnam_locnv_tennv);
        
        text_tktnam_locnv_manv = new JTextField();
        text_tktnam_locnv_manv.setColumns(10);
        text_tktnam_locnv_manv.setBounds(162, 33, 261, 30);
        pn_tktnam_locnv.add(text_tktnam_locnv_manv);
        
        text_tktnam_locnv_tennv = new JTextField();
        text_tktnam_locnv_tennv.setColumns(10);
        text_tktnam_locnv_tennv.setBounds(162, 85, 261, 30);
        pn_tktnam_locnv.add(text_tktnam_locnv_tennv);
        
        JScrollPane scPtknv_hientablenv = new JScrollPane();
        scPtknv_hientablenv.setBounds(10, 263, 459, 505);
        pn_tktnam_tk.add(scPtknv_hientablenv);
        
        table_tktn_hiennv = new JTable();
        table_tktn_hiennv.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null},
        	},
        	new String[] {
        		"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn"
        	}
        ));
        scPtknv_hientablenv.setViewportView(table_tktn_hiennv);

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
        
        JComboBox<String> cboGIoiTinh_TNV = new JComboBox(new Object[]{});
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
        
        JComboBox<String> cboChucVu_TNV = new JComboBox<String>();
        cboChucVu_TNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboChucVu_TNV.setBounds(184, 310, 192, 32);
        panelThongTinNV_TNV.add(cboChucVu_TNV);
        
        JLabel lblTaiKhoan_TNV = new JLabel("Tài khoản:");
        lblTaiKhoan_TNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        lblTaiKhoan_TNV.setBounds(505, 307, 103, 32);
        panelThongTinNV_TNV.add(lblTaiKhoan_TNV);
        
        JComboBox<String> cboTaiKhoan_TNV = new JComboBox<String>();
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
        
        JComboBox<String> cboGioiTinh_TKNV = new JComboBox<String>();
        cboGioiTinh_TKNV.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
        cboGioiTinh_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboGioiTinh_TKNV.setBounds(240, 174, 163, 43);
        panel_TimKiemNV.add(cboGioiTinh_TKNV);
        
        JLabel lblVaiTro_TKNV = new JLabel("Vai trò:");
        lblVaiTro_TKNV.setBounds(930, 171, 139, 43);
        lblVaiTro_TKNV.setFont(new Font("Times New Roman", Font.BOLD, 21));
        panel_TimKiemNV.add(lblVaiTro_TKNV);
        
        JComboBox<String> cboVaiTro_TKNV = new JComboBox<String>();
        cboVaiTro_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboVaiTro_TKNV.setBounds(1079, 174, 370, 43);
        panel_TimKiemNV.add(cboVaiTro_TKNV);
        
        JButton btnXemCT_TKNV = new JButton("Xem chi tiết");
        btnXemCT_TKNV.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		XemChiTietNV_GUI xemCTNV = new XemChiTietNV_GUI(QuanLyHieuThuocTay);
                
                // 2. Hiển thị cửa sổ đó lên
                xemCTNV.setVisible(true);
        	}
        });
        
        btnXemCT_TKNV.setBounds(1079, 382, 125, 36);
        btnXemCT_TKNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
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
        
        JComboBox<String> cboGIoiTinh_CNNV = new JComboBox<String>();
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
        
        JComboBox<String> cboChucVu_CNNV = new JComboBox<String>();
        cboChucVu_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cboChucVu_CNNV.setBounds(1260, 163, 225, 37);
        panel_CapNhatNV.add(cboChucVu_CNNV);
        
        JLabel lblTaiKhoan_CNNV = new JLabel("Tài khoản:");
        lblTaiKhoan_CNNV.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblTaiKhoan_CNNV.setBounds(1127, 211, 136, 37);
        panel_CapNhatNV.add(lblTaiKhoan_CNNV);
        
        JComboBox<String> cboTaiKhoan_CNNV = new JComboBox<String>();
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
        
        JComboBox<String> cboChucVu_TK_CNNV = new JComboBox<String>();
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
        btnKhoiPhuc_CNNV.setBounds(1117, 532, 152, 37);
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