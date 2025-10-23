package gui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat; // Import
import java.text.SimpleDateFormat; // Import
import java.time.ZoneId; // Import
import java.util.ArrayList; // Import
import java.util.Date; // Import

// Import DAO và Entity cần thiết
import dao.chiTietHoaDon_DAO;
import dao.hoaDon_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;

public class XemchitietHD_GUI extends JDialog {
	private JTable table;

	// (Các hằng số màu/font và hàm helper styleButton, applyCommonTableStyling giữ nguyên)
	// ... (Copy/Paste hằng số màu + 2 hàm style từ file cũ) ...
	// ========== THÊM HẰNG SỐ MÀU/FONT VÀ HÀM TRỢ GIÚP ==========
	private static final Color COLOR_BACKGROUND_PRIMARY = new Color(240, 242, 245);
	private static final Color COLOR_CARD_BACKGROUND = Color.WHITE;
	private static final Color COLOR_PRIMARY_BLUE = new Color(0, 123, 255);
	private static final Color COLOR_SUCCESS_GREEN = new Color(40, 167, 69);
	private static final Color COLOR_DANGER_RED = new Color(220, 53, 69);
	private static final Color COLOR_TEXT_DARK = new Color(33, 37, 41);
	private static final Color COLOR_TEXT_MUTED = new Color(108, 117, 125);
	private static final Color COLOR_BORDER_LIGHT = new Color(222, 226, 230);
	private static final Font FONT_TITLE_MAIN = new Font("Segoe UI", Font.BOLD, 38);
	private static final Font FONT_TITLE_SECTION = new Font("Segoe UI", Font.BOLD, 26);
	private static final Font FONT_LABEL_BOLD = new Font("Segoe UI", Font.BOLD, 15);
	private static final Font FONT_TEXT_FIELD = new Font("Segoe UI", Font.PLAIN, 15);
	private static final Font FONT_BUTTON_STANDARD = new Font("Segoe UI", Font.BOLD, 14);
	private static final Font FONT_TABLE_HEADER = new Font("Segoe UI", Font.BOLD, 15);
	private static final Font FONT_TABLE_CELL = new Font("Segoe UI", Font.PLAIN, 14);
	private static final Font FONT_SUMMARY_TOTAL = new Font("Segoe UI", Font.BOLD, 20);
	private static final Font FONT_DETAIL_ITALIC = new Font("Segoe UI", Font.ITALIC, 15);

	private void styleButton(JButton button, Color background) {
		button.setBackground(background);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	}

	private void applyCommonTableStyling(JTable table) {
		table.setFont(FONT_TABLE_CELL);
		table.setRowHeight(30);
		table.setGridColor(COLOR_BORDER_LIGHT);
		table.setShowGrid(true);
		table.setSelectionBackground(COLOR_PRIMARY_BLUE);
		table.setSelectionForeground(Color.WHITE);
		JTableHeader header = table.getTableHeader();
		header.setFont(FONT_TABLE_HEADER);
		header.setBackground(new Color(230, 235, 240));
		header.setForeground(COLOR_TEXT_DARK);
		header.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
		header.setReorderingAllowed(false);
		header.setResizingAllowed(true);
	}
	// ==========================================================

	// Khai báo các JLabel để hiển thị dữ liệu
	private JLabel lblHd, lblNguynTrungKin, lblNguynNgc, lblNgyLp_1;
	private JLabel lblNgyLp_2_3, lblNgyLp_2_4, lblNgyLp_2_2, lblNgyLp_2, lblNgyLp_2_1;
    private JLabel lblHienKhuyenMai; // Thêm label khuyến mãi

	public XemchitietHD_GUI(JFrame parent) {
		super(parent, "Chi Tiết Hóa Đơn", true);
		initialize();
		setLocationRelativeTo(parent);
	}

	private void initialize() {
		setBounds(100, 100, 1000, 930);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(COLOR_BACKGROUND_PRIMARY);
		getContentPane().setLayout(null);

		// --- Thông tin cửa hàng ---
		JLabel lblNewLabel = new JLabel("Tên Đơn Vị :");
		lblNewLabel.setFont(FONT_LABEL_BOLD);
		lblNewLabel.setForeground(COLOR_TEXT_DARK);
		lblNewLabel.setBounds(20, 11, 114, 30);
		getContentPane().add(lblNewLabel);
		// ... (Các label thông tin cửa hàng) ...
        JLabel lblHiuThucTy = new JLabel("Hiệu Thuốc Tây Khánh Hưng");
		lblHiuThucTy.setFont(FONT_LABEL_BOLD);
		lblHiuThucTy.setForeground(COLOR_PRIMARY_BLUE);
		lblHiuThucTy.setBounds(144, 11, 300, 30);
		getContentPane().add(lblHiuThucTy);
		JLabel lblaCh = new JLabel("Địa Chỉ :");
		lblaCh.setFont(FONT_LABEL_BOLD);
		lblaCh.setForeground(COLOR_TEXT_DARK);
		lblaCh.setBounds(20, 52, 114, 30);
		getContentPane().add(lblaCh);
		JLabel lblAbc = new JLabel("123 Đường ABC, Quận XYZ, TP.HCM");
		lblAbc.setFont(FONT_TEXT_FIELD);
		lblAbc.setForeground(COLOR_TEXT_MUTED);
		lblAbc.setBounds(144, 52, 830, 30);
		getContentPane().add(lblAbc);


		// --- Tiêu đề hóa đơn ---
		JLabel lblNewLabel_1 = new JLabel("Hóa Đơn Bán Hàng");
		lblNewLabel_1.setFont(FONT_TITLE_SECTION);
		lblNewLabel_1.setForeground(COLOR_TEXT_DARK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 90, 984, 47);
		getContentPane().add(lblNewLabel_1);

		// --- Thông tin hóa đơn ---
		JLabel lblMHan = new JLabel("Mã Hóa Đơn :");
		lblMHan.setFont(FONT_LABEL_BOLD);
		lblMHan.setForeground(COLOR_TEXT_DARK);
		lblMHan.setBounds(20, 140, 148, 30);
		getContentPane().add(lblMHan);

		lblHd = new JLabel("..."); // Chỉ khởi tạo
		lblHd.setForeground(COLOR_DANGER_RED);
		lblHd.setFont(FONT_LABEL_BOLD);
		lblHd.setBounds(178, 140, 200, 30);
		getContentPane().add(lblHd);

		JLabel lblTnNhnVin = new JLabel("Tên Nhân Viên :");
		lblTnNhnVin.setFont(FONT_LABEL_BOLD);
		lblTnNhnVin.setForeground(COLOR_TEXT_DARK);
		lblTnNhnVin.setBounds(20, 181, 148, 30);
		getContentPane().add(lblTnNhnVin);

		lblNguynTrungKin = new JLabel("..."); // Chỉ khởi tạo
		lblNguynTrungKin.setFont(FONT_TEXT_FIELD);
		lblNguynTrungKin.setForeground(COLOR_TEXT_DARK);
		lblNguynTrungKin.setBounds(178, 181, 400, 30);
		getContentPane().add(lblNguynTrungKin);

		JLabel lblTnKhchHng = new JLabel("Tên Khách Hàng :");
		lblTnKhchHng.setFont(FONT_LABEL_BOLD);
		lblTnKhchHng.setForeground(COLOR_TEXT_DARK);
		lblTnKhchHng.setBounds(20, 222, 148, 30);
		getContentPane().add(lblTnKhchHng);

		lblNguynNgc = new JLabel("..."); // Chỉ khởi tạo
		lblNguynNgc.setFont(FONT_TEXT_FIELD);
		lblNguynNgc.setForeground(COLOR_TEXT_DARK);
		lblNguynNgc.setBounds(178, 222, 400, 30);
		getContentPane().add(lblNguynNgc);

		JLabel lblNgyLp = new JLabel("Ngày Lập :");
		lblNgyLp.setFont(FONT_LABEL_BOLD);
		lblNgyLp.setForeground(COLOR_TEXT_DARK);
		lblNgyLp.setBounds(693, 181, 102, 30);
		getContentPane().add(lblNgyLp);

		lblNgyLp_1 = new JLabel("..."); // Chỉ khởi tạo
		lblNgyLp_1.setFont(FONT_TEXT_FIELD);
		lblNgyLp_1.setForeground(COLOR_TEXT_DARK);
		lblNgyLp_1.setBounds(805, 181, 150, 30);
		getContentPane().add(lblNgyLp_1);

		// --- Bảng chi tiết ---
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_LIGHT));
		scrollPane.setBounds(10, 270, 964, 430);
		getContentPane().add(scrollPane);

		table = new JTable() {
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
		applyCommonTableStyling(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"STT", "Tên Thuốc", "Số Lượng", "Giá Tiền", "Thành Tiền"}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		scrollPane.setViewportView(table);

		// --- Thông tin thanh toán ---
		int summaryY = 715;
		int summaryLabelX = 20;
		int summaryValueX = 178;
		int summaryLabelX2 = 668;
		int summaryValueX2 = 833;
		int summaryHeight = 30;
		int summaryVGap = 10;

		JLabel lblTinKhcha = new JLabel("Tiền Khách Đưa :");
		lblTinKhcha.setFont(FONT_LABEL_BOLD);
		lblTinKhcha.setForeground(COLOR_TEXT_DARK);
		lblTinKhcha.setBounds(summaryLabelX, summaryY, 148, summaryHeight);
		getContentPane().add(lblTinKhcha);

		lblNgyLp_2_3 = new JLabel("0 VND"); // Tiền Khách Đưa
		lblNgyLp_2_3.setForeground(COLOR_PRIMARY_BLUE);
		lblNgyLp_2_3.setFont(FONT_LABEL_BOLD);
		lblNgyLp_2_3.setBounds(summaryValueX, summaryY, 200, summaryHeight);
		getContentPane().add(lblNgyLp_2_3);

		JLabel lblTinTha = new JLabel("Tiền Thừa :");
		lblTinTha.setFont(FONT_LABEL_BOLD);
		lblTinTha.setForeground(COLOR_TEXT_DARK);
		lblTinTha.setBounds(20, 840, 148, summaryHeight); // Sửa Y
		getContentPane().add(lblTinTha);

		lblNgyLp_2_4 = new JLabel("0 VND"); // Tiền Thừa
		lblNgyLp_2_4.setForeground(COLOR_SUCCESS_GREEN);
		lblNgyLp_2_4.setFont(FONT_LABEL_BOLD);
		lblNgyLp_2_4.setBounds(178, 840, 200, summaryHeight); // Sửa Y
		getContentPane().add(lblNgyLp_2_4);

		JLabel lblTngThnhTin = new JLabel("Tổng Tiền Hàng :");
		lblTngThnhTin.setFont(FONT_LABEL_BOLD);
		lblTngThnhTin.setForeground(COLOR_TEXT_DARK);
		lblTngThnhTin.setBounds(summaryLabelX2, summaryY, 155, summaryHeight);
		getContentPane().add(lblTngThnhTin);

		lblNgyLp_2_2 = new JLabel("0 VND"); // Tổng Tiền Hàng
		lblNgyLp_2_2.setForeground(COLOR_TEXT_DARK);
		lblNgyLp_2_2.setFont(FONT_LABEL_BOLD);
		lblNgyLp_2_2.setBounds(summaryValueX2, summaryY, 141, summaryHeight);
		getContentPane().add(lblNgyLp_2_2);

		JLabel lblThuVat = new JLabel("Thuế (10% VAT) :");
		lblThuVat.setFont(FONT_LABEL_BOLD);
		lblThuVat.setForeground(COLOR_TEXT_DARK);
		lblThuVat.setBounds(summaryLabelX2, summaryY + summaryHeight + summaryVGap, 155, summaryHeight);
		getContentPane().add(lblThuVat);

		lblNgyLp_2 = new JLabel("0 VND"); // Thuế
		lblNgyLp_2.setForeground(COLOR_TEXT_DARK);
		lblNgyLp_2.setFont(FONT_LABEL_BOLD);
		lblNgyLp_2.setBounds(summaryValueX2, summaryY + summaryHeight + summaryVGap, 141, summaryHeight);
		getContentPane().add(lblNgyLp_2);

		JLabel lblKhuynMi = new JLabel("Khuyến Mãi :");
		lblKhuynMi.setForeground(COLOR_TEXT_DARK);
		lblKhuynMi.setFont(FONT_LABEL_BOLD);
		lblKhuynMi.setBounds(summaryLabelX2, summaryY + 2*(summaryHeight + summaryVGap), 155, summaryHeight); // Sửa vị trí
		getContentPane().add(lblKhuynMi);

		lblHienKhuyenMai = new JLabel("..."); // Label cho Khuyến mãi
		lblHienKhuyenMai.setForeground(COLOR_TEXT_DARK);
		lblHienKhuyenMai.setFont(FONT_LABEL_BOLD);
		lblHienKhuyenMai.setBounds(summaryValueX2, summaryY + 2*(summaryHeight + summaryVGap), 141, summaryHeight); // Sửa vị trí
		getContentPane().add(lblHienKhuyenMai);

		JLabel lblTngCng = new JLabel("Tổng Cộng :");
		lblTngCng.setFont(FONT_SUMMARY_TOTAL); // Font tổng cộng
		lblTngCng.setForeground(COLOR_TEXT_DARK);
		lblTngCng.setBounds(summaryLabelX, summaryY + 2*(summaryHeight + summaryVGap) + 10, 148, summaryHeight); // Sửa Y
		getContentPane().add(lblTngCng);

		lblNgyLp_2_1 = new JLabel("0 VND"); // Tổng Cộng
		lblNgyLp_2_1.setForeground(COLOR_DANGER_RED);
		lblNgyLp_2_1.setFont(FONT_SUMMARY_TOTAL); // Font tổng cộng
		lblNgyLp_2_1.setBounds(summaryValueX, summaryY + 2*(summaryHeight + summaryVGap) + 10, 250, summaryHeight); // Sửa Y
		getContentPane().add(lblNgyLp_2_1);

		// --- Nút Xuất Hóa Đơn ---
		JButton btnNewButton = new JButton("Xuất Hóa Đơn");
		btnNewButton.setFont(FONT_BUTTON_STANDARD);
		styleButton(btnNewButton, COLOR_SUCCESS_GREEN);
		btnNewButton.setBounds(800, 833, 174, 47);
		getContentPane().add(btnNewButton);
	}

    // === BỔ SUNG HÀM LOAD DATA (REQ 6) ===
    public void loadData(String maHD) {
        hoaDon_DAO hdDAO = new hoaDon_DAO();
        chiTietHoaDon_DAO cthdDAO = new chiTietHoaDon_DAO();
        DecimalFormat df = new DecimalFormat("#,##0 VND");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // 1. Lấy thông tin Hóa Đơn chính
        HoaDon hd = hdDAO.getHoaDonByMa(maHD); 
        if (hd == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn " + maHD, "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Hiển thị thông tin Hóa Đơn
        lblHd.setText(hd.getMaHD());
        lblNgyLp_1.setText(sdf.format(Date.from(hd.getNgayLap().atStartOfDay(ZoneId.systemDefault()).toInstant())));
        
        if (hd.getNhanVien() != null) {
            lblNguynTrungKin.setText(hd.getNhanVien().getTenNV());
        } else {
            lblNguynTrungKin.setText("N/A");
        }
        
        if (hd.getKhachHang() != null) {
            lblNguynNgc.setText(hd.getKhachHang().getTenKH());
        } else {
            lblNguynNgc.setText("Khách vãng lai");
        }

        // 3. Lấy và hiển thị Chi Tiết Hóa Đơn
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        ArrayList<ChiTietHoaDon> dsCTHD = cthdDAO.getChiTietHoaDonTheoMaHD(maHD);
        
        double tongTienHang = 0;
        int stt = 1;
        for (ChiTietHoaDon ct : dsCTHD) {
            double thanhTien = ct.tinhThanhTien(); // Dùng hàm tính của Entity
            model.addRow(new Object[]{
                stt++,
                ct.getThuoc().getTenThuoc(),
                ct.getSoLuong(),
                df.format(ct.getThuoc().getGiaBan()),
                df.format(thanhTien)
            });
            tongTienHang += thanhTien;
        }

        // 4. Tính toán và hiển thị Tổng tiền
        double thue = 0;
        if(hd.getThue() != null) {
            thue = tongTienHang * hd.getThue().getTiLe(); // Lấy tỉ lệ thuế
        }

        double khuyenMaiGiam = 0.0;
        if (hd.getKhuyenMai() != null) {
             khuyenMaiGiam = tongTienHang * (hd.getKhuyenMai().getGiaTri() / 100);
             lblHienKhuyenMai.setText(String.format("-%.0f%% (%s)", hd.getKhuyenMai().getGiaTri(), df.format(khuyenMaiGiam)));
        } else {
            lblHienKhuyenMai.setText("Không áp dụng");
        }

        double tongCong = (tongTienHang - khuyenMaiGiam) + thue;

        lblNgyLp_2_2.setText(df.format(tongTienHang)); // Tổng tiền hàng
        lblNgyLp_2.setText(df.format(thue)); // Thuế
        lblNgyLp_2_1.setText(df.format(tongCong)); // Tổng cộng

        // (Phần Tiền khách đưa và Tiền thừa là N/A)
        lblNgyLp_2_3.setText("N/A"); // Tiền khách đưa
        lblNgyLp_2_4.setText("N/A"); // Tiền thừa
    }
}