package controller;

import dao.PhieuDoiTra_DAO;
import dao.hoaDon_DAO;
import dao.khachHang_DAO;
import dao.khuyenMai_DAO;
import dao.nhanVien_DAO;
import entity.CTPhieuDoiTra;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.PhieuDoiTra;
import entity.Thuoc;
import gui.TrangChu_GUI;
import utils.DoiThuoc_PDFExporter;
import utils.TraThuoc_PDFExporter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class DoiTra_Controller implements ActionListener {

    private TrangChu_GUI trangChuGUI;
    private hoaDon_DAO hoaDonDAO;
    private khachHang_DAO khachHangDAO;
    private nhanVien_DAO nhanVienDAO;
    private khuyenMai_DAO khuyenMaiDAO;
    private PhieuDoiTra_DAO phieuDAO;
    private List<CTPhieuDoiTra> tempCTPhieuDoiTraList;
    private List<CTPhieuDoiTra> tempCTPhieuTraList;
    private static final double TAX_RATE = 0.1;
    private NhanVien currentNhanVien = null;
    private boolean daLuuPhieuDoiThuoc = false;
    private boolean daLuuPhieuTraThuoc = false;

    public DoiTra_Controller(TrangChu_GUI trangChuGUI) {
        this.trangChuGUI = trangChuGUI;
        hoaDonDAO = new hoaDon_DAO();
        khachHangDAO = new khachHang_DAO();
        nhanVienDAO = new nhanVien_DAO();
        khuyenMaiDAO = new khuyenMai_DAO();
        phieuDAO = new PhieuDoiTra_DAO();
        tempCTPhieuDoiTraList = new ArrayList<>();
        tempCTPhieuTraList = new ArrayList<>();

        // Initialize currentNhanVien
        if (trangChuGUI.currentUser != null) {
            this.currentNhanVien = nhanVienDAO.getNhanVienByMaTK(trangChuGUI.currentUser.getMaTK());
        }
        if (this.currentNhanVien == null) {
            this.currentNhanVien = new NhanVien();
            this.currentNhanVien.setMaNV("NV000");
            this.currentNhanVien.setTenNV("Admin"); // Always set a non-null tenNV
        }

        // Register action listeners
        this.trangChuGUI.btn_trathuoc_lammoi.addActionListener(this);
        this.trangChuGUI.btn_doithuoc_lammoi.addActionListener(this);
        this.trangChuGUI.btn_trathuoc_LM.addActionListener(this);
        trangChuGUI.table_trathuoc_thuoc.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.trangChuGUI.btn_trathuoc_XN.addActionListener(this);
        this.trangChuGUI.btn_trathuoc_luu.addActionListener(this);
        this.trangChuGUI.btn_doithuoc_LM.addActionListener(this);
        trangChuGUI.table_doithuoc_thuoc.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.trangChuGUI.btn_doithuoc_XN.addActionListener(this);
        this.trangChuGUI.btn_doithuoc_luu.addActionListener(this);
        this.trangChuGUI.btn_trathuoc_xuathd.addActionListener(this);
        this.trangChuGUI.btn_doithuoc_xuathd.addActionListener(this);

        // Key listeners for search
        this.trangChuGUI.txt_trathuoc_tkhd_maHD.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timKiemHoaDon();
            }
        });
        this.trangChuGUI.txt_trathuoc_tkhd_tenKH.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timKiemHoaDon();
            }
        });

        this.trangChuGUI.dateNgayLapHD_TKHD.getDateEditor().addPropertyChangeListener(evt -> {
            if ("date".equals(evt.getPropertyName())) {
                timKiemHoaDon();
            }
        });

        // Table mouse listener for return invoice selection
        trangChuGUI.table_trathuoc_tkhd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = trangChuGUI.table_trathuoc_tkhd.getSelectedRow();
                if (row != -1) {
                    String maHD = trangChuGUI.table_trathuoc_tkhd.getValueAt(row, 0).toString();
                    trangChuGUI.lbl_trathuoc_hienmahd.setText(maHD);
                    trangChuGUI.lbl_trathuoc_Hientenkh.setText(trangChuGUI.table_trathuoc_tkhd.getValueAt(row, 1).toString());
                    trangChuGUI.lbl_trathuoc_hiensdtkh.setText(trangChuGUI.table_trathuoc_tkhd.getValueAt(row, 2).toString());
                    trangChuGUI.lbl_trathuoc_Hientennv.setText(trangChuGUI.table_trathuoc_tkhd.getValueAt(row, 3).toString());
                    trangChuGUI.lbl_trathuoc_hienngaylaphoadon.setText(trangChuGUI.table_trathuoc_tkhd.getValueAt(row, 4).toString());
                    trangChuGUI.lbl_trathuoc_HienKmapdung.setText(trangChuGUI.table_trathuoc_tkhd.getValueAt(row, 5).toString());
                    double tongCong = Double.parseDouble(trangChuGUI.table_trathuoc_tkhd.getValueAt(row, 6).toString());
                    double tongTienThuoc = tongCong / (1 + TAX_RATE);
                    double thue = tongTienThuoc * TAX_RATE;
                    trangChuGUI.lbl_trathuoc_Hientienthuoc.setText(String.format("%.0f", tongTienThuoc));
                    trangChuGUI.lbl_trathuoc_Hienthue.setText(String.format("%.0f", thue));
                    trangChuGUI.lbl_trathuoc_Hientongcong.setText(String.format("%.0f", tongCong));

                    tempCTPhieuTraList.clear();
                    List<Thuoc> dsThuoc = hoaDonDAO.getDSThuocTheoHoaDon(maHD);
                    updateTraThuocTable();
                }
            }
        });

        // Key listeners for exchange invoice search
        this.trangChuGUI.txt_doithuoc_tkhd_mahd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timKiemHoaDonDoiThuoc();
            }
        });
        this.trangChuGUI.txt_doithuoc_tkhd_tenKh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timKiemHoaDonDoiThuoc();
            }
        });

        this.trangChuGUI.dateNgayLapHD_TKHD_doithuoc.getDateEditor().addPropertyChangeListener(evt -> {
            if ("date".equals(evt.getPropertyName())) {
                timKiemHoaDonDoiThuoc();
            }
        });

        // Table mouse listener for exchange invoice selection
        trangChuGUI.table_doithuoc_hd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = trangChuGUI.table_doithuoc_hd.getSelectedRow();
                if (row != -1) {
                    String maHD = trangChuGUI.table_doithuoc_hd.getValueAt(row, 0).toString();
                    trangChuGUI.lbl_doithuoc_hienmahd.setText(maHD);
                    trangChuGUI.lbl_doithuoc_Hientenkh.setText(trangChuGUI.table_doithuoc_hd.getValueAt(row, 1).toString());
                    trangChuGUI.lbl_doithuoc_hiensdtkh.setText(trangChuGUI.table_doithuoc_hd.getValueAt(row, 2).toString());
                    trangChuGUI.lbl_doithuoc_Hientennv.setText(trangChuGUI.table_doithuoc_hd.getValueAt(row, 3).toString());
                    trangChuGUI.lbl_doithuoc_hienngaylaphoadon.setText(trangChuGUI.table_doithuoc_hd.getValueAt(row, 4).toString());
                    trangChuGUI.lbl_doithuoc_HienKmapdung.setText(trangChuGUI.table_doithuoc_hd.getValueAt(row, 5).toString());
                    trangChuGUI.lbl_doithuoc_Hientongcong.setText(trangChuGUI.table_doithuoc_hd.getValueAt(row, 6).toString());

                    List<Thuoc> dsThuoc = hoaDonDAO.getDSThuocTheoHoaDon(maHD);
                    updateDoiThuocTable();
                }
            }
        });

        lamMoiBangHoaDonTraThuoc();
        lamMoiBangHoaDonDoiThuoc();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == trangChuGUI.btn_trathuoc_lammoi) {
            lamMoiBangHoaDonTraThuoc();
        }

        if (o == trangChuGUI.btn_trathuoc_XN) {
            int[] selectedRows = trangChuGUI.table_trathuoc_thuoc.getSelectedRows();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một loại thuốc để trả!");
                return;
            }

            String soLuongStr = trangChuGUI.txt_trathuoc_sldoi.getText().trim();
            if (soLuongStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng thuốc cần trả!");
                trangChuGUI.txt_trathuoc_sldoi.requestFocus();
                return;
            }

            int soLuong;
            try {
                soLuong = Integer.parseInt(soLuongStr);
                if (soLuong <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương!");
                trangChuGUI.txt_trathuoc_sldoi.requestFocus();
                return;
            }

            DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_trathuoc_thuoc.getModel();
            StringBuilder confirmationMessage = new StringBuilder();
            boolean valid = true;
            List<Thuoc> dsThuoc = hoaDonDAO.getDSThuocTheoHoaDon(trangChuGUI.lbl_trathuoc_hienmahd.getText().trim());

            for (int row : selectedRows) {
                try {
                    String maThuoc = model.getValueAt(row, 0).toString();
                    String tenThuoc = model.getValueAt(row, 1).toString();
                    int soLuongTrongHD = Integer.parseInt(model.getValueAt(row, 3).toString());

                    if (soLuong > soLuongTrongHD) {
                        JOptionPane.showMessageDialog(null, "Số lượng trả không được vượt quá số lượng trong hóa đơn cho thuốc: " + tenThuoc);
                        trangChuGUI.txt_trathuoc_sldoi.requestFocus();
                        valid = false;
                        break;
                    }

                    int totalSoLuongTra = tempCTPhieuTraList.stream()
                        .filter(ct -> ct.getThuoc().getMaThuoc().equals(maThuoc))
                        .mapToInt(CTPhieuDoiTra::getSoLuong)
                        .sum();
                    Optional<CTPhieuDoiTra> existingCT = tempCTPhieuTraList.stream()
                        .filter(ct -> ct.getThuoc().getMaThuoc().equals(maThuoc))
                        .findFirst();
                    if (existingCT.isPresent()) {
                        totalSoLuongTra -= existingCT.get().getSoLuong();
                    }
                    if (totalSoLuongTra + soLuong > soLuongTrongHD) {
                        JOptionPane.showMessageDialog(null, "Tổng số lượng trả vượt quá số lượng trong hóa đơn cho thuốc: " + tenThuoc);
                        valid = false;
                        break;
                    }

                    if (existingCT.isPresent()) {
                        existingCT.get().setSoLuong(soLuong);
                    } else {
                        // tìm thuốc trong danh sách dsThuoc (lấy từ hóa đơn)
                        Thuoc thuoc = dsThuoc.stream()
                            .filter(t -> t.getMaThuoc().equals(maThuoc))
                            .findFirst()
                            .orElse(null);

                        if (thuoc != null) {
                            CTPhieuDoiTra ct = new CTPhieuDoiTra(null, thuoc, soLuong);
                            tempCTPhieuTraList.add(ct);
                        } else {
                            System.err.println("Không tìm thấy thông tin thuốc: " + maThuoc);
                        }
                    }


                    confirmationMessage.append("Đã xác nhận trả ").append(soLuong).append(" sản phẩm: ").append(tenThuoc).append("\n");
                } catch (Exception ex) {
                    System.err.println("Lỗi xử lý thuốc: " + ex.getMessage());
                    valid = false;
                }
            }

            if (valid) {
                double tienHoanLai = tempCTPhieuTraList.stream()
                    .mapToDouble(ct -> {
                        String maThuoc = ct.getThuoc().getMaThuoc();
                        double giaBan = dsThuoc.stream()
                            .filter(t -> t.getMaThuoc().equals(maThuoc))
                            .findFirst()
                            .map(Thuoc::getGiaBan)
                            .orElse(0.0);
                        return giaBan * ct.getSoLuong() * (1 + TAX_RATE);
                    })
                    .sum();

                trangChuGUI.lbl_trathuoc_tiendchoan.setText(String.format("%,.0f VND", tienHoanLai));
                updateTraThuocTable();
                JOptionPane.showMessageDialog(null, confirmationMessage.toString());
                trangChuGUI.txt_trathuoc_sldoi.setText("");
            }
        }

        if (o == trangChuGUI.btn_trathuoc_LM) {
            tempCTPhieuTraList.clear();
            updateTraThuocTable();
            trangChuGUI.table_trathuoc_thuoc.clearSelection();
            trangChuGUI.table_trathuoc_thuoc.setEnabled(true);
            trangChuGUI.txt_trathuoc_lydo.setText("");
            trangChuGUI.lbl_trathuoc_tiendchoan.setText("");
            trangChuGUI.txt_trathuoc_sldoi.setText("");
            JOptionPane.showMessageDialog(null, "Đã làm mới bảng thuốc, lý do, số lượng và tiền hoàn.");
        }

        if (o == trangChuGUI.btn_trathuoc_luu) {
            if (trangChuGUI.currentUser == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng đăng nhập trước khi lưu phiếu trả!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (tempCTPhieuTraList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng xác nhận ít nhất một loại thuốc để trả!");
                return;
            }

            String maHD = trangChuGUI.lbl_trathuoc_hienmahd.getText().trim();
            if (maHD.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn trước!");
                return;
            }

            String lyDo = trangChuGUI.txt_trathuoc_lydo.getText().trim();
            if (lyDo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập lý do trả thuốc!");
                trangChuGUI.txt_trathuoc_lydo.requestFocus();
                return;
            }

            String maPDT = phieuDAO.taoMaPDTMoi();
            String loaiPhieu = "Trả thuốc";

            double tienHoanLai = 0;
            try {
                String textTien = trangChuGUI.lbl_trathuoc_tiendchoan.getText().replace("VND", "").replace(",", "").trim();
                tienHoanLai = Double.parseDouble(textTien);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi lấy số tiền hoàn!");
                return;
            }

            NhanVien nv = currentNhanVien;
            HoaDon hd = hoaDonDAO.getHoaDonByMa(maHD);
            if (hd == null) {
                JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ!");
                return;
            }

            PhieuDoiTra pdt = new PhieuDoiTra(maPDT, java.time.LocalDate.now(), lyDo, loaiPhieu, tienHoanLai, nv, hd);

            if (phieuDAO.themPhieuDoiTra(pdt)) {
                for (CTPhieuDoiTra ct : tempCTPhieuTraList) {
                    ct.setPhieuDoiTra(pdt);
                    phieuDAO.themCTPhieuDoiTra(ct);
                }
                JOptionPane.showMessageDialog(null, "Lưu phiếu trả thuốc thành công! Mã phiếu: " + maPDT);
                daLuuPhieuTraThuoc = true;
                // không xóa dữ liệu, giữ nguyên form
            } else {
                JOptionPane.showMessageDialog(null, "Không thể lưu phiếu trả thuốc!");
            }
        }

        // =============================
        // Xuất file PDF
        // =============================
        if (o == trangChuGUI.btn_trathuoc_xuathd) {
        	
        	if (!daLuuPhieuTraThuoc) {
                JOptionPane.showMessageDialog(null, "Vui lòng lưu phiếu trả thuốc trước khi xuất PDF!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        	
            if (trangChuGUI.currentUser == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng đăng nhập trước khi xuất PDF!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (tempCTPhieuTraList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng xác nhận ít nhất một loại thuốc để trả trước khi xuất PDF!");
                return;
            }

            String maHD = trangChuGUI.lbl_trathuoc_hienmahd.getText().trim();
            if (maHD.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn trước khi xuất PDF!");
                return;
            }

            String lyDo = trangChuGUI.txt_trathuoc_lydo.getText().trim();
            if (lyDo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập lý do trả thuốc trước khi xuất PDF!");
                trangChuGUI.txt_trathuoc_lydo.requestFocus();
                return;
            }

            String maPDT = phieuDAO.taoMaPDTMoi();
            String loaiPhieu = "Trả thuốc";

            double tienHoanLai = 0;
            try {
                String textTien = trangChuGUI.lbl_trathuoc_tiendchoan.getText().replace("VND", "").replace(",", "").trim();
                tienHoanLai = Double.parseDouble(textTien);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi lấy số tiền hoàn để xuất PDF!");
                return;
            }

            NhanVien nv = currentNhanVien;
            HoaDon hd = hoaDonDAO.getHoaDonByMa(maHD);
            if (hd == null) {
                JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ!");
                return;
            }

            PhieuDoiTra pdt = new PhieuDoiTra(maPDT, java.time.LocalDate.now(), lyDo, loaiPhieu, tienHoanLai, nv, hd);

            String filePath = "tra_thuoc_pdf/" + maPDT + ".pdf";
            File dir = new File("tra_thuoc_pdf");
            if (!dir.exists()) dir.mkdirs();

            try {
                TraThuoc_PDFExporter.exportPhieuTraThuocToPdf(pdt, new ArrayList<>(tempCTPhieuTraList), filePath);
                lamMoiBangHoaDonTraThuoc();
                tempCTPhieuDoiTraList.clear();
                updateTraThuocTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi xuất PDF: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (o == trangChuGUI.btn_doithuoc_lammoi) {
            lamMoiBangHoaDonDoiThuoc();
            tempCTPhieuDoiTraList.clear();
            updateDoiThuocTable();
        }

        if (o == trangChuGUI.btn_doithuoc_LM) {
            tempCTPhieuDoiTraList.clear();
            updateDoiThuocTable();
            trangChuGUI.table_doithuoc_thuoc.clearSelection();
            trangChuGUI.table_doithuoc_thuoc.setEnabled(true);
            trangChuGUI.txt_doithuoc_lydo.setText("");
            trangChuGUI.txt_doithuoc_sldoi.setText("");
            JOptionPane.showMessageDialog(null, "Đã làm mới bảng thuốc, lý do và số lượng.");
        }

        if (o == trangChuGUI.btn_doithuoc_XN) {
            int[] selectedRows = trangChuGUI.table_doithuoc_thuoc.getSelectedRows();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một loại thuốc để đổi!");
                return;
            }

            String soLuongStr = trangChuGUI.txt_doithuoc_sldoi.getText().trim();
            if (soLuongStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng thuốc cần đổi!");
                trangChuGUI.txt_doithuoc_sldoi.requestFocus();
                return;
            }

            int soLuong;
            try {
                soLuong = Integer.parseInt(soLuongStr);
                if (soLuong <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương!");
                trangChuGUI.txt_doithuoc_sldoi.requestFocus();
                return;
            }

            DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_doithuoc_thuoc.getModel();
            StringBuilder confirmationMessage = new StringBuilder();
            boolean valid = true;

            // Lấy danh sách thuốc trong hóa đơn để lấy giá và đơn vị
            List<Thuoc> dsThuoc = hoaDonDAO.getDSThuocTheoHoaDon(trangChuGUI.lbl_doithuoc_hienmahd.getText().trim());

            for (int row : selectedRows) {
                String maThuoc = model.getValueAt(row, 0).toString();
                String tenThuoc = model.getValueAt(row, 1).toString();
                int soLuongTrongHD = Integer.parseInt(model.getValueAt(row, 3).toString());

                if (soLuong > soLuongTrongHD) {
                    JOptionPane.showMessageDialog(null, "Số lượng đổi không được vượt quá số lượng trong hóa đơn cho thuốc: " + tenThuoc);
                    trangChuGUI.txt_doithuoc_sldoi.requestFocus();
                    valid = false;
                    break;
                }

                Optional<CTPhieuDoiTra> existingCT = tempCTPhieuDoiTraList.stream()
                    .filter(ct -> ct.getThuoc().getMaThuoc().equals(maThuoc))
                    .findFirst();

                if (existingCT.isPresent()) {
                    existingCT.get().setSoLuong(soLuong);
                } else {
                    Thuoc thuoc = dsThuoc.stream()
                        .filter(t -> t.getMaThuoc().equals(maThuoc))
                        .findFirst()
                        .orElse(null);
                    if (thuoc != null) {
                        CTPhieuDoiTra ct = new CTPhieuDoiTra(null, thuoc, soLuong);
                        tempCTPhieuDoiTraList.add(ct);
                    } else {
                        System.err.println("Không tìm thấy thông tin thuốc: " + maThuoc);
                    }
                }

                confirmationMessage.append("Đã xác nhận đổi ").append(soLuong).append(" sản phẩm: ").append(tenThuoc).append("\n");
            }

            if (valid) {
                updateDoiThuocTable();
                JOptionPane.showMessageDialog(null, confirmationMessage.toString());
                trangChuGUI.txt_doithuoc_sldoi.setText("");
            }
        }

        // =======================================
        // LƯU PHIẾU ĐỔI THUỐC
        // =======================================
        if (o == trangChuGUI.btn_doithuoc_luu) {
            if (trangChuGUI.currentUser == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng đăng nhập trước khi lưu phiếu đổi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (tempCTPhieuDoiTraList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng xác nhận ít nhất một loại thuốc để đổi!");
                return;
            }

            String maHD = trangChuGUI.lbl_doithuoc_hienmahd.getText().trim();
            if (maHD.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn trước!");
                return;
            }

            String lyDo = trangChuGUI.txt_doithuoc_lydo.getText().trim();
            if (lyDo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập lý do đổi thuốc!");
                trangChuGUI.txt_doithuoc_lydo.requestFocus();
                return;
            }

            String maPDT = phieuDAO.taoMaPDTMoi();
            String loaiPhieu = "Đổi thuốc";
            double tienHoanLai = 0; // đổi thuốc không hoàn tiền
            NhanVien nv = currentNhanVien;
            HoaDon hd = hoaDonDAO.getHoaDonByMa(maHD);

            if (hd == null) {
                JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ!");
                return;
            }

            PhieuDoiTra pdt = new PhieuDoiTra(maPDT, java.time.LocalDate.now(), lyDo, loaiPhieu, tienHoanLai, nv, hd);

            if (phieuDAO.themPhieuDoiTra(pdt)) {
                for (CTPhieuDoiTra ct : tempCTPhieuDoiTraList) {
                    ct.setPhieuDoiTra(pdt);
                    phieuDAO.themCTPhieuDoiTra(ct);
                }
                JOptionPane.showMessageDialog(null, "Lưu phiếu đổi thuốc thành công! Mã phiếu: " + maPDT);
                daLuuPhieuDoiThuoc = true;
            } else {
                JOptionPane.showMessageDialog(null, "Không thể lưu phiếu đổi thuốc!");
            }
        }

        if (o == trangChuGUI.btn_doithuoc_xuathd) {
            if (!daLuuPhieuDoiThuoc) {
                JOptionPane.showMessageDialog(null, "Vui lòng lưu phiếu đổi thuốc trước khi xuất PDF!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (trangChuGUI.currentUser == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng đăng nhập trước khi xuất PDF!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (tempCTPhieuDoiTraList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng xác nhận ít nhất một loại thuốc để đổi trước khi xuất PDF!");
                return;
            }

            String maHD = trangChuGUI.lbl_doithuoc_hienmahd.getText().trim();
            if (maHD.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn trước khi xuất PDF!");
                return;
            }

            String lyDo = trangChuGUI.txt_doithuoc_lydo.getText().trim();
            if (lyDo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập lý do đổi thuốc trước khi xuất PDF!");
                trangChuGUI.txt_doithuoc_lydo.requestFocus();
                return;
            }

            String maPDT = phieuDAO.taoMaPDTMoi();
            String loaiPhieu = "Đổi thuốc";
            double tienHoanLai = 0;
            NhanVien nv = currentNhanVien;
            HoaDon hd = hoaDonDAO.getHoaDonByMa(maHD);
            if (hd == null) {
                JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ!");
                return;
            }

            PhieuDoiTra pdt = new PhieuDoiTra(maPDT, java.time.LocalDate.now(), lyDo, loaiPhieu, tienHoanLai, nv, hd);

            String filePath = "doi_thuoc_pdf/" + maPDT + ".pdf";
            File dir = new File("doi_thuoc_pdf");
            if (!dir.exists()) dir.mkdirs();

            try {
                DoiThuoc_PDFExporter.exportPhieuDoiThuocToPdf(pdt, new ArrayList<>(tempCTPhieuDoiTraList), filePath);
                // reset lại sau khi xuất
                daLuuPhieuDoiThuoc = false;
                lamMoiBangHoaDonDoiThuoc();
                tempCTPhieuDoiTraList.clear();
                updateDoiThuocTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi xuất PDF: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTraThuocTable() {
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_trathuoc_thuoc.getModel();
        model.setRowCount(0);

        String maHD = trangChuGUI.lbl_trathuoc_hienmahd.getText().trim();
        if (!maHD.isEmpty()) {
            List<Thuoc> dsThuoc = hoaDonDAO.getDSThuocTheoHoaDon(maHD);
            List<Integer> rowsToSelect = new ArrayList<>();
            int rowIndex = 0;

            for (Thuoc t : dsThuoc) {
                int soLuongTra = tempCTPhieuTraList.stream()
                    .filter(ct -> ct.getThuoc().getMaThuoc().equals(t.getMaThuoc()))
                    .mapToInt(CTPhieuDoiTra::getSoLuong)
                    .sum();
                int soLuongConLai = t.getSoLuong() - soLuongTra;

                model.addRow(new Object[]{
                    t.getMaThuoc(),
                    t.getTenThuoc(),
                    t.getGiaBan(),
                    soLuongConLai,
                    t.getDonViTinh(),
                    t.getGiaBan() * soLuongConLai
                });

                if (soLuongTra > 0) {
                    rowsToSelect.add(rowIndex);
                }
                rowIndex++;
            }

            trangChuGUI.table_trathuoc_thuoc.clearSelection();
            for (int row : rowsToSelect) {
                trangChuGUI.table_trathuoc_thuoc.addRowSelectionInterval(row, row);
            }
            trangChuGUI.table_trathuoc_thuoc.setEnabled(true);

            // Update refund amount display only
            double tienHoanLai = tempCTPhieuTraList.stream()
                .mapToDouble(ct -> {
                    String maThuoc = ct.getThuoc().getMaThuoc();
                    double giaBan = dsThuoc.stream()
                        .filter(t -> t.getMaThuoc().equals(maThuoc))
                        .findFirst()
                        .map(Thuoc::getGiaBan)
                        .orElse(0.0);
                    return giaBan * ct.getSoLuong() * (1 + TAX_RATE);
                })
                .sum();
            trangChuGUI.lbl_trathuoc_tiendchoan.setText(String.format("%,.0f VND", tienHoanLai));
        }
    }

    private void updateDoiThuocTable() {
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_doithuoc_thuoc.getModel();
        model.setRowCount(0);

        String maHD = trangChuGUI.lbl_doithuoc_hienmahd.getText().trim();
        if (!maHD.isEmpty()) {
            List<Thuoc> dsThuoc = hoaDonDAO.getDSThuocTheoHoaDon(maHD);
            List<Integer> rowsToSelect = new ArrayList<>();
            int rowIndex = 0;

            for (Thuoc t : dsThuoc) {
                int soLuongDoi = tempCTPhieuDoiTraList.stream()
                    .filter(ct -> ct.getThuoc().getMaThuoc().equals(t.getMaThuoc()))
                    .mapToInt(CTPhieuDoiTra::getSoLuong)
                    .sum();
                int soLuongConLai = t.getSoLuong() - soLuongDoi;

                model.addRow(new Object[]{
                    t.getMaThuoc(),
                    t.getTenThuoc(),
                    t.getGiaBan(),
                    soLuongConLai,
                    t.getDonViTinh(),
                    t.getGiaBan() * soLuongConLai
                });

                if (soLuongDoi > 0) {
                    rowsToSelect.add(rowIndex);
                }
                rowIndex++;
            }

            trangChuGUI.table_doithuoc_thuoc.clearSelection();
            for (int row : rowsToSelect) {
                trangChuGUI.table_doithuoc_thuoc.addRowSelectionInterval(row, row);
            }
            trangChuGUI.table_doithuoc_thuoc.setEnabled(true);
        }
    }

    public void timKiemHoaDon() {
        String maHD = trangChuGUI.txt_trathuoc_tkhd_maHD.getText().trim();
        String tenKH = trangChuGUI.txt_trathuoc_tkhd_tenKH.getText().trim();
        Date ngayLap = trangChuGUI.dateNgayLapHD_TKHD.getDate();

        List<HoaDon> dsHD = hoaDonDAO.getAllHoaDon();
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_trathuoc_tkhd.getModel();
        model.setRowCount(0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (HoaDon hd : dsHD) {
            boolean match = true;

            if (!maHD.isEmpty() && !hd.getMaHD().toLowerCase().contains(maHD.toLowerCase())) {
                match = false;
            }

            KhachHang kh = null;
            if (hd.getKhachHang() != null && hd.getKhachHang().getMaKH() != null) {
                kh = khachHangDAO.getKhachHangTheoMa(hd.getKhachHang().getMaKH());
            }

            NhanVien nv = null;
            if (hd.getNhanVien() != null && hd.getNhanVien().getMaNV() != null) {
                nv = nhanVienDAO.getNhanVienTheoMa(hd.getNhanVien().getMaNV());
            }

            KhuyenMai km = null;
            if (hd.getKhuyenMai() != null && hd.getKhuyenMai().getMaKM() != null) {
                km = khuyenMaiDAO.getKhuyenMaiTheoMa(hd.getKhuyenMai().getMaKM());
            }

            if (!tenKH.isEmpty() && (kh == null || !kh.getTenKH().toLowerCase().contains(tenKH.toLowerCase()))) {
                match = false;
            }

            if (ngayLap != null && hd.getNgayLap() != null) {
                String ngayNhap = sdf.format(ngayLap);
                String ngayHD = sdf.format(convertToDate(hd.getNgayLap()));
                if (!ngayNhap.equals(ngayHD)) {
                    match = false;
                }
            }

            if (match) {
                double tongTien = tinhTongTien(hd);
                model.addRow(new Object[]{
                        hd.getMaHD(),
                        kh != null ? kh.getTenKH() : "Khách vãng lai",
                        kh != null ? kh.getSoDienThoai() : "",
                        nv != null ? nv.getTenNV() : "",
                        sdf.format(convertToDate(hd.getNgayLap())),
                        km != null ? km.getTenChuongTrinh() : "Không có",
                        tongTien
                });
            }
        }
    }

    public void timKiemHoaDonDoiThuoc() {
        String maHD = trangChuGUI.txt_doithuoc_tkhd_mahd.getText().trim();
        String tenKH = trangChuGUI.txt_doithuoc_tkhd_tenKh.getText().trim();
        Date ngayLap = trangChuGUI.dateNgayLapHD_TKHD_doithuoc.getDate();

        List<HoaDon> dsHD = hoaDonDAO.getAllHoaDon();
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_doithuoc_hd.getModel();
        model.setRowCount(0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (HoaDon hd : dsHD) {
            boolean match = true;

            if (!maHD.isEmpty() && !hd.getMaHD().toLowerCase().contains(maHD.toLowerCase())) {
                match = false;
            }

            KhachHang kh = null;
            if (hd.getKhachHang() != null && hd.getKhachHang().getMaKH() != null) {
                kh = khachHangDAO.getKhachHangTheoMa(hd.getKhachHang().getMaKH());
            }

            NhanVien nv = null;
            if (hd.getNhanVien() != null && hd.getNhanVien().getMaNV() != null) {
                nv = nhanVienDAO.getNhanVienTheoMa(hd.getNhanVien().getMaNV());
            }

            KhuyenMai km = null;
            if (hd.getKhuyenMai() != null && hd.getKhuyenMai().getMaKM() != null) {
                km = khuyenMaiDAO.getKhuyenMaiTheoMa(hd.getKhuyenMai().getMaKM());
            }

            if (!tenKH.isEmpty() && (kh == null || !kh.getTenKH().toLowerCase().contains(tenKH.toLowerCase()))) {
                match = false;
            }

            if (ngayLap != null && hd.getNgayLap() != null) {
                String ngayNhap = sdf.format(ngayLap);
                String ngayHD = sdf.format(convertToDate(hd.getNgayLap()));
                if (!ngayNhap.equals(ngayHD)) {
                    match = false;
                }
            }

            if (match) {
                double tongTien = tinhTongTien(hd);
                model.addRow(new Object[]{
                        hd.getMaHD(),
                        kh != null ? kh.getTenKH() : "Khách vãng lai",
                        kh != null ? kh.getSoDienThoai() : "",
                        nv != null ? nv.getTenNV() : "",
                        sdf.format(convertToDate(hd.getNgayLap())),
                        km != null ? km.getTenChuongTrinh() : "Không có",
                        tongTien
                });
            }
        }
    }

    private double tinhTongTien(HoaDon hd) {
        double tong = 0;
        List<ChiTietHoaDon> dsCT = hoaDonDAO.getChiTietHoaDonTheoMa(hd.getMaHD());
        for (ChiTietHoaDon ct : dsCT) {
            tong += ct.tinhThanhTien();
        }
        return tong;
    }

    public void lamMoiBangHoaDonTraThuoc() {
        trangChuGUI.txt_trathuoc_tkhd_maHD.setText("");
        trangChuGUI.txt_trathuoc_tkhd_tenKH.setText("");
        trangChuGUI.dateNgayLapHD_TKHD.setDate(null);

        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_trathuoc_tkhd.getModel();
        model.setRowCount(0);

        List<HoaDon> dsHD = hoaDonDAO.getAllHoaDon();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (HoaDon hd : dsHD) {
            KhachHang kh = khachHangDAO.getKhachHangTheoMa(hd.getKhachHang().getMaKH());
            NhanVien nv = nhanVienDAO.getNhanVienTheoMa(hd.getNhanVien().getMaNV());
            KhuyenMai km = null;
            if (hd.getKhuyenMai() != null) {
                km = khuyenMaiDAO.getKhuyenMaiTheoMa(hd.getKhuyenMai().getMaKM());
            }

            double tongTien = tinhTongTien(hd);

            Object ngayLapFormatted = "";
            Date ngay = convertToDate(hd.getNgayLap());
            if (ngay != null) {
                ngayLapFormatted = sdf.format(ngay);
            }

            model.addRow(new Object[]{
                    hd.getMaHD(),
                    kh != null ? kh.getTenKH() : "",
                    kh != null ? kh.getSoDienThoai() : "",
                    nv != null ? nv.getTenNV() : "",
                    ngayLapFormatted,
                    km != null ? km.getTenChuongTrinh() : "Không có",
                    tongTien
            });
        }
        tempCTPhieuTraList.clear();
        updateTraThuocTable();
        trangChuGUI.table_trathuoc_thuoc.clearSelection();
        trangChuGUI.table_trathuoc_thuoc.setEnabled(true);
        trangChuGUI.txt_trathuoc_lydo.setText("");
        trangChuGUI.txt_trathuoc_sldoi.setText("");
        trangChuGUI.lbl_trathuoc_tiendchoan.setText("");
        trangChuGUI.lbl_trathuoc_hienmahd.setText("");
        trangChuGUI.lbl_trathuoc_Hientenkh.setText("");
        trangChuGUI.lbl_trathuoc_hiensdtkh.setText("");
        trangChuGUI.lbl_trathuoc_Hientennv.setText("");
        trangChuGUI.lbl_trathuoc_hienngaylaphoadon.setText("");
        trangChuGUI.lbl_trathuoc_HienKmapdung.setText("");
        trangChuGUI.lbl_trathuoc_Hientienthuoc.setText("");
        trangChuGUI.lbl_trathuoc_Hienthue.setText("");
        trangChuGUI.lbl_trathuoc_Hientongcong.setText("");
    }

    public void lamMoiBangHoaDonDoiThuoc() {
        trangChuGUI.txt_doithuoc_tkhd_mahd.setText("");
        trangChuGUI.txt_doithuoc_tkhd_tenKh.setText("");
        trangChuGUI.dateNgayLapHD_TKHD_doithuoc.setDate(null);

        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_doithuoc_hd.getModel();
        model.setRowCount(0);

        List<HoaDon> dsHD = hoaDonDAO.getAllHoaDon();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (HoaDon hd : dsHD) {
            KhachHang kh = khachHangDAO.getKhachHangTheoMa(hd.getKhachHang().getMaKH());
            NhanVien nv = nhanVienDAO.getNhanVienTheoMa(hd.getNhanVien().getMaNV());
            KhuyenMai km = null;
            if (hd.getKhuyenMai() != null) {
                km = khuyenMaiDAO.getKhuyenMaiTheoMa(hd.getKhuyenMai().getMaKM());
            }

            double tongTien = tinhTongTien(hd);

            Object ngayLapFormatted = "";
            Date ngay = convertToDate(hd.getNgayLap());
            if (ngay != null) {
                ngayLapFormatted = sdf.format(ngay);
            }

            model.addRow(new Object[]{
                    hd.getMaHD(),
                    kh != null ? kh.getTenKH() : "",
                    kh != null ? kh.getSoDienThoai() : "",
                    nv != null ? nv.getTenNV() : "",
                    ngayLapFormatted,
                    km != null ? km.getTenChuongTrinh() : "Không có",
                    tongTien
            });
        }
        tempCTPhieuDoiTraList.clear();
        updateDoiThuocTable();
        trangChuGUI.table_doithuoc_thuoc.clearSelection();
        trangChuGUI.table_doithuoc_thuoc.setEnabled(true);
        trangChuGUI.txt_doithuoc_lydo.setText("");
        trangChuGUI.txt_doithuoc_sldoi.setText("");
        trangChuGUI.lbl_doithuoc_hienmahd.setText("");
        trangChuGUI.lbl_doithuoc_Hientenkh.setText("");
        trangChuGUI.lbl_doithuoc_hiensdtkh.setText("");
        trangChuGUI.lbl_doithuoc_Hientennv.setText("");
        trangChuGUI.lbl_doithuoc_hienngaylaphoadon.setText("");
        trangChuGUI.lbl_doithuoc_HienKmapdung.setText("");
        trangChuGUI.lbl_doithuoc_Hientienthuoc.setText("");
        trangChuGUI.lbl_doithuoc_Hienthue.setText("");
        trangChuGUI.lbl_doithuoc_Hientongcong.setText("");
    }

    private Date convertToDate(Object obj) {
        try {
            if (obj == null) return null;
            if (obj instanceof Date) return (Date) obj;
            if (obj instanceof java.sql.Timestamp)
                return new Date(((java.sql.Timestamp) obj).getTime());
            if (obj instanceof java.time.LocalDate)
                return java.sql.Date.valueOf((java.time.LocalDate) obj);
            if (obj instanceof java.time.LocalDateTime)
                return java.sql.Timestamp.valueOf((java.time.LocalDateTime) obj);
            if (obj instanceof String) {
                try {
                    return new SimpleDateFormat("yyyy-MM-dd").parse((String) obj);
                } catch (Exception e) {
                    return new SimpleDateFormat("dd/MM/yyyy").parse((String) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}