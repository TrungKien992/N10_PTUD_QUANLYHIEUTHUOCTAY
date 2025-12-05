package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.keThuoc_DAO;
import dao.nhaCungCap_DAO;
import dao.thuoc_DAO;
import entity.KeThuoc;
import entity.NhaCungCap;
import entity.Thuoc;
import gui.ChiTietThuoc_GUI;
import gui.TrangChu_GUI;

import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Thuoc_Controller {
    private thuoc_DAO dao;
    private TrangChu_GUI trangChuGUI;
    private File selectedFile = null;
    private String filePath = null;

    public Thuoc_Controller(TrangChu_GUI trangChuGUI) {
        this.trangChuGUI = trangChuGUI;
        dao = new thuoc_DAO();

        hienThiThuoc();
        hienThiThuocLenCapNhat();
        hienThiThuocLenThemThuoc();
        hienThiThuocLenThemThuocFile();
        loadComboboxData();


        ItemListener filterListener = e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                hienThiThuocTheoLoc();
            }
        };
        trangChuGUI.cb_tkt_kethuoc.addItemListener(filterListener);
        trangChuGUI.cb_tkt_tenthuoc.addItemListener(filterListener);
        trangChuGUI.cb_tkt_ncc.addItemListener(filterListener);

        // nút xem chi tiết thuốc
        trangChuGUI.btn_tkt_xemchitiet.addActionListener(e -> {
            int selectedRow = trangChuGUI.table_tkt.getSelectedRow();
            if (selectedRow >= 0) {
                String maThuoc = trangChuGUI.table_tkt.getValueAt(selectedRow, 0).toString();
                Thuoc t = dao.getThuocTheoMa(maThuoc);
                if (t != null) {
                    ChiTietThuoc_GUI chitiet = new ChiTietThuoc_GUI((JFrame) trangChuGUI);
                    chitiet.loadThuoc(t);
                    chitiet.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(trangChuGUI, "Vui lòng chọn thuốc để xem chi tiết");
            }
        });

        trangChuGUI.btn_tkt_Lammoi.addActionListener(e -> {
            trangChuGUI.cb_tkt_kethuoc.setSelectedIndex(0);
            trangChuGUI.cb_tkt_tenthuoc.setSelectedIndex(0);
            trangChuGUI.cb_tkt_ncc.setSelectedIndex(0);
            hienThiThuoc();
        });
        
        trangChuGUI.btn_ttLammoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                trangChuGUI.text_tttt.setText("");
                trangChuGUI.text_ttsl.setText("");
                trangChuGUI.text_ttgn.setText("");
                trangChuGUI.text_ttgb.setText("");
                trangChuGUI.textArea_tttp.setText("");

                trangChuGUI.cb_ttdvt.setSelectedIndex(0);
                trangChuGUI.cb_ttncc.setSelectedIndex(0);
                trangChuGUI.cb_tttkt.setSelectedIndex(0);

                trangChuGUI.date_tthsd.setDate(null);

                trangChuGUI.lb_Chuaanh_tt.setIcon(null);
                trangChuGUI.lb_Chuaanh_tt.setText("Chưa có ảnh");

                trangChuGUI.table_themthuoc.clearSelection();
            }
        });


        trangChuGUI.btn_cnt_tk_lammoi.addActionListener(e -> {
            trangChuGUI.text_cnt_tkmt.setText("");
            trangChuGUI.text_cnt_tktt.setText("");
            trangChuGUI.cb_cnt_tktkt.setSelectedIndex(0);
            hienThiThuocLenCapNhat();
        });

        ItemListener filterCntListener = e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                filterThuocCapNhat();
            }
        };
        trangChuGUI.cb_cnt_tktkt.addItemListener(filterCntListener);

        trangChuGUI.table_Capnhatthuoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = trangChuGUI.table_Capnhatthuoc.getSelectedRow();
                if (selectedRow >= 0) {
                    String maThuoc = trangChuGUI.table_Capnhatthuoc.getValueAt(selectedRow, 0).toString();
                    Thuoc t = dao.getThuocTheoMa(maThuoc);
                    if (t != null) {
                        trangChuGUI.text_cntmt.setText(t.getMaThuoc());
                        trangChuGUI.text_cnttt.setText(t.getTenThuoc());
                        trangChuGUI.text_cntsl.setText(String.valueOf(t.getSoLuong()));
                        trangChuGUI.text_cntgn.setText(String.valueOf(t.getGiaNhap()));
                        trangChuGUI.text_cntgb.setText(String.valueOf(t.getGiaBan()));
                        trangChuGUI.cb_cntdvt.setSelectedItem(t.getDonViTinh());
                        trangChuGUI.cb_cntncc.setSelectedItem(t.getNhaCungCap().getTenNhaCungCap());
                        if (t.getHanSuDung() != null) {
                            java.util.Date date = java.sql.Date.valueOf(t.getHanSuDung());
                            trangChuGUI.date_cnthsd.setDate(date);
                        } else {
                            trangChuGUI.date_cnthsd.setDate(null);
                        }
                        trangChuGUI.cb_cnt_tkt.setSelectedItem(t.getKeThuoc().getLoaiKe());
                        trangChuGUI.textArea_cnttp.setText(t.getThanhPhan());

                        if (t.getAnh() != null && !t.getAnh().isEmpty()) {
                            ImageIcon icon = new ImageIcon(t.getAnh());
                            Image img = icon.getImage().getScaledInstance(
                                    trangChuGUI.lb_Chuaanh.getWidth(),
                                    trangChuGUI.lb_Chuaanh.getHeight(),
                                    Image.SCALE_SMOOTH);
                            trangChuGUI.lb_Chuaanh.setIcon(new ImageIcon(img));
                            trangChuGUI.lb_Chuaanh.setText("");
                        } else {
                            trangChuGUI.lb_Chuaanh.setIcon(null);
                            trangChuGUI.lb_Chuaanh.setText("Chưa có ảnh");
                        }
                    }
                }
            }
        });
        
        trangChuGUI.btn_cntKhoiphuc.addActionListener(e -> {
            String maThuoc = trangChuGUI.text_cntmt.getText().trim();

            if (maThuoc.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập hoặc chọn mã thuốc cần khôi phục!");
                return;
            }

            Thuoc thuocGoc = dao.getThuocTheoMa(maThuoc);

            if (thuocGoc != null) {
                trangChuGUI.text_cntmt.setText(thuocGoc.getMaThuoc());
                trangChuGUI.text_cnttt.setText(thuocGoc.getTenThuoc());
                trangChuGUI.text_cntsl.setText(String.valueOf(thuocGoc.getSoLuong()));
                trangChuGUI.text_cntgn.setText(String.valueOf(thuocGoc.getGiaNhap()));
                trangChuGUI.text_cntgb.setText(String.valueOf(thuocGoc.getGiaBan()));

                if (thuocGoc.getHanSuDung() != null) {
                    java.util.Date date = java.sql.Date.valueOf(thuocGoc.getHanSuDung());
                    trangChuGUI.date_cnthsd.setDate(date);
                } else {
                    trangChuGUI.date_cnthsd.setDate(null);
                }

                trangChuGUI.textArea_cnttp.setText(thuocGoc.getThanhPhan());

                trangChuGUI.cb_cntdvt.setSelectedItem(thuocGoc.getDonViTinh());
                trangChuGUI.cb_cntncc.setSelectedItem(thuocGoc.getNhaCungCap().getTenNhaCungCap());
                trangChuGUI.cb_cnt_tkt.setSelectedItem(thuocGoc.getKeThuoc().getLoaiKe());

                // hiển thị ảnh
                if (thuocGoc.getAnh() != null && !thuocGoc.getAnh().isEmpty()) {
                    ImageIcon icon = new ImageIcon(thuocGoc.getAnh());
                    Image img = icon.getImage().getScaledInstance(trangChuGUI.lb_Chuaanh.getWidth(),
                            trangChuGUI.lb_Chuaanh.getHeight(),
                            Image.SCALE_SMOOTH);
                    trangChuGUI.lb_Chuaanh.setIcon(new ImageIcon(img));
                    trangChuGUI.lb_Chuaanh.setText("");
                } else {
                    trangChuGUI.lb_Chuaanh.setIcon(null);
                    trangChuGUI.lb_Chuaanh.setText("Chưa có ảnh");
                }

                
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy thuốc có mã " + maThuoc + " trong cơ sở dữ liệu!");
            }
        });



        trangChuGUI.btn_ttf_chonfile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn file Excel");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "File Excel (*.xls, *.xlsx)", "xls", "xlsx"));
            
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                filePath = selectedFile.getAbsolutePath();

                int tongSoThuoc = 0;
                try (FileInputStream fis = new FileInputStream(selectedFile)) {
                    Workbook workbook;
                    if (filePath.endsWith(".xlsx")) {
                        workbook = new XSSFWorkbook(fis);
                    } else {
                        workbook = new HSSFWorkbook(fis);
                    }

                    Sheet sheet = workbook.getSheetAt(0); // đọc sheet đầu tiên
                    for (int i = 1; i <= sheet.getLastRowNum(); i++) { // bỏ dòng tiêu đề (i = 1)
                        Row row = sheet.getRow(i);
                        if (row == null) continue; // bỏ dòng trống hoàn toàn

                        Cell cellTenThuoc = row.getCell(0); // cột A
                        if (cellTenThuoc != null && !cellTenThuoc.toString().trim().isEmpty()) {
                            tongSoThuoc++;
                        }
                    }
                    workbook.close();

                    trangChuGUI.lbl_ttfile_hienthitongsothuoc.setText(""+tongSoThuoc);

                    System.out.println("Đã chọn file: " + filePath);
                    System.out.println("Tổng số thuốc: " + tongSoThuoc);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi đọc file Excel!");
                }
            }
        });
        trangChuGUI.btn_ttf_lammoi.addActionListener(e -> {
            trangChuGUI.lbl_ttfile_hienthitongsothuoc.setText("0");
            selectedFile = null;
            filePath = null;

            System.out.println("Đã làm mới thông tin file để thêm thuốc mới.");
            JOptionPane.showMessageDialog(null, "Đã làm mới thông tin file. Bạn có thể chọn file mới để thêm thuốc.");
        });




        trangChuGUI.btn_cnt_ChonAnh.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn ảnh đại diện thuốc");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Hình ảnh (*.jpg, *.png, *.jpeg)", "jpg", "png", "jpeg"));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();

                ImageIcon icon = new ImageIcon(path);
                Image img = icon.getImage().getScaledInstance(
                        trangChuGUI.lb_Chuaanh.getWidth(),
                        trangChuGUI.lb_Chuaanh.getHeight(),
                        Image.SCALE_SMOOTH);
                trangChuGUI.lb_Chuaanh.setIcon(new ImageIcon(img));
                trangChuGUI.lb_Chuaanh.setText("");

                trangChuGUI.lb_Chuaanh.setToolTipText(path);
            }
        });
        
     // nút chọn ảnh thêm thuốc
        trangChuGUI.btn_ChonAnh_tt.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn ảnh đại diện thuốc");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Hình ảnh (*.jpg, *.png, *.jpeg)", "jpg", "png", "jpeg"));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();

                ImageIcon icon = new ImageIcon(path);
                Image img = icon.getImage().getScaledInstance(
                        trangChuGUI.lb_Chuaanh_tt.getWidth(),
                        trangChuGUI.lb_Chuaanh_tt.getHeight(),
                        Image.SCALE_SMOOTH);
                trangChuGUI.lb_Chuaanh_tt.setIcon(new ImageIcon(img));
                trangChuGUI.lb_Chuaanh_tt.setText("");

                // lưu đường dẫn ảnh vào label để lấy lại khi cập nhật
                trangChuGUI.lb_Chuaanh_tt.setToolTipText(path);
            }
        });

        trangChuGUI.btn_cntCapnhat.addActionListener(e -> {
            try {
                String maThuoc = trangChuGUI.text_cntmt.getText().trim();
                String tenThuoc = trangChuGUI.text_cnttt.getText().trim();
                int soLuong = Integer.parseInt(trangChuGUI.text_cntsl.getText().trim());
                double giaNhap = Double.parseDouble(trangChuGUI.text_cntgn.getText().trim());
                double giaBan = Double.parseDouble(trangChuGUI.text_cntgb.getText().trim());
                String donViTinh = trangChuGUI.cb_cntdvt.getSelectedItem().toString();

                java.util.Date hsdDate = trangChuGUI.date_cnthsd.getDate();
                LocalDate hanSuDung = null;
                if (hsdDate != null) {
                    hanSuDung = new java.sql.Date(hsdDate.getTime()).toLocalDate();
                } else {
                    JOptionPane.showMessageDialog(trangChuGUI, "Vui lòng chọn hạn sử dụng!");
                    return;
                }


                String thanhPhan = trangChuGUI.textArea_cnttp.getText().trim();
                String loaiKe = trangChuGUI.cb_cnt_tkt.getSelectedItem().toString();
                String tenNCC = trangChuGUI.cb_cntncc.getSelectedItem().toString();

                String maKe = null;
                for (KeThuoc k : dao.getAllThuoc().stream().map(Thuoc::getKeThuoc).distinct().toList()) {
                    if (k.getLoaiKe().equalsIgnoreCase(loaiKe)) {
                        maKe = k.getMaKe();
                        break;
                    }
                }

                if (maKe == null) {
                    JOptionPane.showMessageDialog(trangChuGUI,
                            "Không tìm thấy mã kệ tương ứng với loại kệ: " + loaiKe);
                    return;
                }

                String maNCC = dao.getMaNCCTheoTen(tenNCC);
                if (maNCC == null || maNCC.isEmpty()) {
                    JOptionPane.showMessageDialog(trangChuGUI,
                            "Không tìm thấy mã nhà cung cấp cho: " + tenNCC);
                    return;
                }

                Thuoc t = new Thuoc();
                t.setMaThuoc(maThuoc);
                t.setTenThuoc(tenThuoc);
                t.setSoLuong(soLuong);
                t.setGiaNhap(giaNhap);
                t.setGiaBan(giaBan);
                t.setDonViTinh(donViTinh);
                t.setHanSuDung(hanSuDung);
                t.setThanhPhan(thanhPhan);

                KeThuoc ke = new KeThuoc();
                ke.setMaKe(maKe);
                t.setKeThuoc(ke);

                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNhaCungCap(maNCC);
                t.setNhaCungCap(ncc);

                if (trangChuGUI.lb_Chuaanh.getToolTipText() != null) {
                    t.setAnh(trangChuGUI.lb_Chuaanh.getToolTipText());
                }

                boolean updated = dao.updateThuoc(t);
                if (updated) {
                    JOptionPane.showMessageDialog(trangChuGUI, "Cập nhật thuốc thành công!");
                    hienThiThuoc();
                    hienThiThuocLenCapNhat();
                    hienThiThuocLenThemThuoc();
                    hienThiThuocLenThemThuocFile();
                } else {
                    JOptionPane.showMessageDialog(trangChuGUI, "Cập nhật thất bại!");
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(trangChuGUI, "Nhập sai kiểu dữ liệu số!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(trangChuGUI, "Lỗi: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        trangChuGUI.text_cnt_tkmt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timKiemThuoc_TK();
            }
        });

        trangChuGUI.text_cnt_tktt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timKiemThuoc_TK();
            }
        });

        trangChuGUI.cb_cnt_tktkt.addActionListener(e -> timKiemThuoc_TK());
        
        trangChuGUI.btn_ttThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String maThuoc = trangChuGUI.text_ttmt.getText().trim();
                    String tenThuoc = trangChuGUI.text_tttt.getText().trim();
                    String donViTinh = trangChuGUI.cb_ttdvt.getSelectedItem().toString();
                    String tenNCC = trangChuGUI.cb_ttncc.getSelectedItem().toString();
                    String loaiKe = trangChuGUI.cb_tttkt.getSelectedItem().toString();
                    String thanhPhan = trangChuGUI.textArea_tttp.getText().trim();
                    String anh = (trangChuGUI.lb_Chuaanh_tt.getIcon() != null)
                            ? trangChuGUI.lb_Chuaanh_tt.getToolTipText() // giả sử bạn lưu đường dẫn ảnh vào tooltip
                            : null;


                    if (tenThuoc.isEmpty() || trangChuGUI.text_ttsl.getText().isEmpty() ||
                        trangChuGUI.text_ttgn.getText().isEmpty() || trangChuGUI.text_ttgb.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin thuốc!", "Thiếu dữ liệu", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    int soLuong = Integer.parseInt(trangChuGUI.text_ttsl.getText().trim());
                    double giaNhap = Double.parseDouble(trangChuGUI.text_ttgn.getText().trim());
                    double giaBan = Double.parseDouble(trangChuGUI.text_ttgb.getText().trim());

                    java.util.Date date = trangChuGUI.date_tthsd.getDate();
                    if (date == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn hạn sử dụng!", "Thiếu dữ liệu", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    java.sql.Date hanSuDung = new java.sql.Date(date.getTime());

                    String maNCC = dao.getMaNCCTheoTen(tenNCC);  
                    String maKe = dao.getMaKeTheoTen(loaiKe);     

                    if (maNCC == null || maKe == null) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy mã NCC hoặc mã Kệ tương ứng!", "Lỗi dữ liệu", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Thuoc t = new Thuoc();
                    t.setMaThuoc(maThuoc);
                    t.setTenThuoc(tenThuoc);
                    t.setSoLuong(soLuong);
                    t.setGiaNhap(giaNhap);
                    t.setGiaBan(giaBan);
                    t.setHanSuDung(hanSuDung.toLocalDate());
                    t.setThanhPhan(thanhPhan);
                    t.setDonViTinh(donViTinh);
                    t.setAnh(anh);

                    KeThuoc ke = new KeThuoc(maKe, loaiKe);
                    NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC);
                    t.setKeThuoc(ke);
                    t.setNhaCungCap(ncc);

                    boolean result = dao.themThuoc(t);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Thêm thuốc thành công!");
                        
                        hienThiThuoc();
                        hienThiThuocLenCapNhat();
                        hienThiThuocLenThemThuoc();
                        hienThiThuocLenThemThuocFile();
                        
                        trangChuGUI.text_ttmt.setText(dao.getNextMaThuoc());

                        trangChuGUI.text_tttt.setText("");
                        trangChuGUI.text_ttsl.setText("");
                        trangChuGUI.text_ttgn.setText("");
                        trangChuGUI.text_ttgb.setText("");
                        trangChuGUI.textArea_tttp.setText("");
                        trangChuGUI.cb_ttdvt.setSelectedIndex(0);
                        trangChuGUI.cb_ttncc.setSelectedIndex(0);
                        trangChuGUI.cb_tttkt.setSelectedIndex(0);
                        trangChuGUI.date_tthsd.setDate(null);
                        trangChuGUI.lb_Chuaanh_tt.setIcon(null);
                        trangChuGUI.lb_Chuaanh_tt.setText("Chưa có ảnh");
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thuốc thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số lượng, giá nhập và giá bán phải là số hợp lệ!", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thêm thuốc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        trangChuGUI.btn_ttf_them.addActionListener(e -> {
            if (selectedFile == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn file Excel trước khi thêm!");
                return;
            }

            try (FileInputStream fis = new FileInputStream(selectedFile)) {
                Workbook workbook;
                if (filePath.endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook(fis);
                } else {
                    workbook = new HSSFWorkbook(fis);
                }

                Sheet sheet = workbook.getSheetAt(0); // đọc sheet đầu tiên
                int soThuocThem = 0;

                thuoc_DAO thuocDAO = new thuoc_DAO();
                nhaCungCap_DAO nccDAO = new nhaCungCap_DAO();
                keThuoc_DAO keThuocDAO = new keThuoc_DAO();

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue;

                    String tenThuoc = getCellValue(row.getCell(0));
                    String soluong = getCellValue(row.getCell(1));
                    String giaNhap = getCellValue(row.getCell(2));
                    String giaBan = getCellValue(row.getCell(3));
                    String hanSD = getCellValue(row.getCell(4));
                    String thanhPhan = getCellValue(row.getCell(5));
                    String donViTinh = getCellValue(row.getCell(6));
                    String anh = getCellValue(row.getCell(7));
                    String tenKeThuoc = getCellValue(row.getCell(8));
                    String tenNCC = getCellValue(row.getCell(9));

                    if (tenThuoc.isEmpty()) continue; 
                    NhaCungCap ncc = nccDAO.getNhaCungCapTheoTen(tenNCC);
                    if (ncc == null) {
 
                        String maNCC = nccDAO.generateNewMaNCC();
                        ncc = new NhaCungCap(maNCC, tenNCC, "", "", "", true,"");
                        nccDAO.themNhaCungCap(ncc);
                    }

                    KeThuoc ke = keThuocDAO.getKeThuocTheoTen(tenKeThuoc);
                    if (ke == null) {
                        JOptionPane.showMessageDialog(null,
                            "Kệ thuốc '" + tenKeThuoc + "' không tồn tại. Vui lòng chọn lại!",
                            "Lỗi kệ thuốc",
                            JOptionPane.ERROR_MESSAGE);
                        return; 
                    }

                    String maThuoc = thuocDAO.getNextMaThuoc();

                    Thuoc thuoc = new Thuoc(
                        maThuoc,
                        tenThuoc,
                        Double.parseDouble(giaNhap),
                        Double.parseDouble(giaBan),
                        Integer.parseInt(soluong),
                        LocalDate.parse(hanSD),
                        thanhPhan,
                        donViTinh,
                        anh,
                        ke,
                        ncc
                    );
                    
                    NhaCungCap_Controller nccController = new NhaCungCap_Controller(trangChuGUI);
                    nccController.loadDataToTableThemNCC();

                    boolean ok = thuocDAO.themThuoc(thuoc);
                    if (ok) soThuocThem++;
                }

                workbook.close();
                hienThiThuoc();
                hienThiThuocLenCapNhat();
                hienThiThuocLenThemThuoc();
                hienThiThuocLenThemThuocFile();
                loadComboboxData();
                

                JOptionPane.showMessageDialog(null, "Đã thêm " + soThuocThem + " thuốc mới từ file Excel!");
                trangChuGUI.lbl_ttfile_hienthitongsothuoc.setText(String.valueOf(soThuocThem));

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm dữ liệu từ file Excel!");
            }
        });



    }
    
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }


    private void hienThiThuoc() {
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_tkt.getModel();
        model.setRowCount(0);
        List<Thuoc> dsThuoc = dao.getAllThuoc();
        for (Thuoc t : dsThuoc) {
            model.addRow(new Object[]{
                    t.getMaThuoc(), t.getTenThuoc(), t.getSoLuong(), t.getGiaNhap(), t.getGiaBan(),
                    t.getDonViTinh(), t.getNhaCungCap().getTenNhaCungCap(),
                    t.getHanSuDung(), t.getKeThuoc().getLoaiKe(), t.getAnh()
            });
        }
    }

    private void hienThiThuocTheoLoc() {
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_tkt.getModel();
        model.setRowCount(0);

        List<Thuoc> dsThuoc = dao.getAllThuoc();

        Object keObj = trangChuGUI.cb_tkt_kethuoc.getSelectedItem();
        Object tenObj = trangChuGUI.cb_tkt_tenthuoc.getSelectedItem();
        Object nccObj = trangChuGUI.cb_tkt_ncc.getSelectedItem();

        if (keObj == null || tenObj == null || nccObj == null) {
            return;
        }

        String keChon = keObj.toString();
        String tenChon = tenObj.toString();
        String nccChon = nccObj.toString();

        for (Thuoc t : dsThuoc) {
            boolean matchKe = keChon.equals("Tất cả") || t.getKeThuoc().getLoaiKe().equals(keChon);
            boolean matchTen = tenChon.equals("Tất cả") || t.getTenThuoc().equals(tenChon);
            boolean matchNCC = nccChon.equals("Tất cả") || t.getNhaCungCap().getTenNhaCungCap().equals(nccChon);

            if (matchKe && matchTen && matchNCC) {
                model.addRow(new Object[]{
                        t.getMaThuoc(), t.getTenThuoc(), t.getSoLuong(), t.getGiaNhap(),
                        t.getGiaBan(), t.getDonViTinh(), t.getNhaCungCap().getTenNhaCungCap(),
                        t.getHanSuDung(), t.getKeThuoc().getLoaiKe(), t.getAnh()
                });
            }
        }
    }

    private void hienThiThuocLenCapNhat() {
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_Capnhatthuoc.getModel();
        model.setRowCount(0);
        List<Thuoc> dsThuoc = dao.getAllThuoc();
        for (Thuoc t : dsThuoc) {
            model.addRow(new Object[]{
                    t.getMaThuoc(), t.getTenThuoc(), t.getSoLuong(), t.getGiaNhap(), t.getGiaBan(),
                    t.getDonViTinh(), t.getNhaCungCap().getTenNhaCungCap(),
                    t.getHanSuDung(), t.getKeThuoc().getLoaiKe(), t.getThanhPhan(), t.getAnh()
            });
        }
    }
    
    private void hienThiThuocLenThemThuoc() {
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_themthuoc.getModel();
        model.setRowCount(0);
        List<Thuoc> dsThuoc = dao.getAllThuoc();
        for (Thuoc t : dsThuoc) {
            model.addRow(new Object[]{
                    t.getMaThuoc(), t.getTenThuoc(), t.getSoLuong(), t.getGiaNhap(), t.getGiaBan(),
                    t.getDonViTinh(), t.getNhaCungCap().getTenNhaCungCap(),
                    t.getHanSuDung(), t.getKeThuoc().getLoaiKe(), t.getThanhPhan(), t.getAnh()
            });
        }
    }
    
    private void hienThiThuocLenThemThuocFile() {
        DefaultTableModel model = (DefaultTableModel) trangChuGUI.table_ttf.getModel();
        model.setRowCount(0);
        List<Thuoc> dsThuoc = dao.getAllThuoc();
        for (Thuoc t : dsThuoc) {
            model.addRow(new Object[]{
                    t.getMaThuoc(), t.getTenThuoc(), t.getSoLuong(), t.getGiaNhap(), t.getGiaBan(),
                    t.getDonViTinh(), t.getNhaCungCap().getTenNhaCungCap(),
                    t.getHanSuDung(), t.getKeThuoc().getLoaiKe(), t.getThanhPhan(), t.getAnh()
            });
        }
    }


    private void timKiemThuoc_TK() {
        String maTK = trangChuGUI.text_cnt_tkmt.getText().trim();
        String tenTK = trangChuGUI.text_cnt_tktt.getText().trim();
        Object selectedItem = trangChuGUI.cb_cnt_tktkt.getSelectedItem();
        String tenThuoc = selectedItem != null ? selectedItem.toString() : "";
        filterTable(trangChuGUI.table_Capnhatthuoc, maTK, tenTK, tenThuoc);
    }

    private void filterTable(JTable table, String maTK, String tenTK, String keTK) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<Thuoc> dsThuoc = dao.getAllThuoc();

        for (Thuoc t : dsThuoc) {
            boolean matchMa = maTK.isEmpty() || t.getMaThuoc().toLowerCase().contains(maTK.toLowerCase());
            boolean matchTen = tenTK.isEmpty() || t.getTenThuoc().toLowerCase().contains(tenTK.toLowerCase());
            boolean matchKe = keTK.equals("Tất cả") || t.getKeThuoc().getLoaiKe().equalsIgnoreCase(keTK);

            if (matchMa && matchTen && matchKe) {
                model.addRow(new Object[]{
                        t.getMaThuoc(), t.getTenThuoc(), t.getSoLuong(), t.getGiaNhap(), t.getGiaBan(),
                        t.getDonViTinh(), t.getNhaCungCap().getTenNhaCungCap(),
                        t.getHanSuDung(), t.getKeThuoc().getLoaiKe(), t.getThanhPhan(), t.getAnh()
                });
            }
        }
    }


    private void filterThuocCapNhat() {
        String maTK = trangChuGUI.text_cnt_tkmt.getText().trim();
        String tenTK = trangChuGUI.text_cnt_tktt.getText().trim();
        String keTK = trangChuGUI.cb_cnt_tktkt.getSelectedItem().toString().trim();
        filterTable(trangChuGUI.table_Capnhatthuoc, maTK, tenTK, keTK);
    }

    private void loadComboboxData() {
        trangChuGUI.cb_tkt_kethuoc.removeAllItems();
        trangChuGUI.cb_tkt_tenthuoc.removeAllItems();
        trangChuGUI.cb_tkt_ncc.removeAllItems();
        trangChuGUI.cb_cnt_tkt.removeAllItems();
        trangChuGUI.cb_cntncc.removeAllItems();
        trangChuGUI.cb_cntdvt.removeAllItems();
        trangChuGUI.cb_cnt_tktkt.removeAllItems();
        trangChuGUI.cb_ttdvt.removeAllItems();
        trangChuGUI.cb_ttncc.removeAllItems();
        trangChuGUI.cb_tttkt.removeAllItems();

        trangChuGUI.cb_tkt_kethuoc.addItem("Tất cả");
        trangChuGUI.cb_tkt_tenthuoc.addItem("Tất cả");
        trangChuGUI.cb_tkt_ncc.addItem("Tất cả");
        trangChuGUI.cb_cnt_tkt.addItem("Tất cả");
        trangChuGUI.cb_cnt_tktkt.addItem("Tất cả");

        List<Thuoc> dsThuoc = dao.getAllThuoc();
        for (Thuoc t : dsThuoc) {
            if (((DefaultComboBoxModel<String>) trangChuGUI.cb_tkt_kethuoc.getModel())
                    .getIndexOf(t.getKeThuoc().getLoaiKe()) == -1)
                trangChuGUI.cb_tkt_kethuoc.addItem(t.getKeThuoc().getLoaiKe());
            if (((DefaultComboBoxModel<String>) trangChuGUI.cb_tkt_tenthuoc.getModel())
                    .getIndexOf(t.getTenThuoc()) == -1)
                trangChuGUI.cb_tkt_tenthuoc.addItem(t.getTenThuoc());
            if (((DefaultComboBoxModel<String>) trangChuGUI.cb_tkt_ncc.getModel())
                    .getIndexOf(t.getNhaCungCap().getTenNhaCungCap()) == -1)
                trangChuGUI.cb_tkt_ncc.addItem(t.getNhaCungCap().getTenNhaCungCap());

            if (((DefaultComboBoxModel<String>) trangChuGUI.cb_cnt_tkt.getModel())
                    .getIndexOf(t.getKeThuoc().getLoaiKe()) == -1)
                trangChuGUI.cb_cnt_tkt.addItem(t.getKeThuoc().getLoaiKe());
            if (((DefaultComboBoxModel<String>) trangChuGUI.cb_cntncc.getModel())
                    .getIndexOf(t.getNhaCungCap().getTenNhaCungCap()) == -1)
                trangChuGUI.cb_cntncc.addItem(t.getNhaCungCap().getTenNhaCungCap());
            if (((DefaultComboBoxModel<String>) trangChuGUI.cb_cntdvt.getModel())
                    .getIndexOf(t.getDonViTinh()) == -1)
                trangChuGUI.cb_cntdvt.addItem(t.getDonViTinh());
            if (((DefaultComboBoxModel<String>) trangChuGUI.cb_cnt_tktkt.getModel())
                    .getIndexOf(t.getKeThuoc().getLoaiKe()) == -1)
                trangChuGUI.cb_cnt_tktkt.addItem(t.getKeThuoc().getLoaiKe());
            
            
            if (((DefaultComboBoxModel<String>) trangChuGUI.cb_tttkt.getModel())
                    .getIndexOf(t.getKeThuoc().getLoaiKe()) == -1)
                trangChuGUI.cb_tttkt.addItem(t.getKeThuoc().getLoaiKe());
            if (((DefaultComboBoxModel<String>) trangChuGUI.cb_ttncc.getModel())
                    .getIndexOf(t.getNhaCungCap().getTenNhaCungCap()) == -1)
                trangChuGUI.cb_ttncc.addItem(t.getNhaCungCap().getTenNhaCungCap());
            if (((DefaultComboBoxModel<String>) trangChuGUI.cb_ttdvt.getModel())
                    .getIndexOf(t.getDonViTinh()) == -1)
                trangChuGUI.cb_ttdvt.addItem(t.getDonViTinh());
            
        }
    }
}
