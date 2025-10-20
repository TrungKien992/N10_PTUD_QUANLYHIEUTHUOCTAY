package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.thuoc_DAO;
import entity.KeThuoc;
import entity.NhaCungCap;
import entity.Thuoc;
import gui.ChiTietThuoc_GUI;
import gui.TrangChu_GUI;

import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Thuoc_Controller {
    private thuoc_DAO dao;
    private TrangChu_GUI trangChuGUI;

    public Thuoc_Controller(TrangChu_GUI trangChuGUI) {
        this.trangChuGUI = trangChuGUI;
        dao = new thuoc_DAO();

        // hiển thị dữ liệu ban đầu
        hienThiThuoc();
        hienThiThuocLenCapNhat();
        loadComboboxData();

        // ======= SỰ KIỆN TAB TÌM KIẾM =======

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

        // nút làm mới tab tìm kiếm
        trangChuGUI.btn_tkt_Lammoi.addActionListener(e -> {
            trangChuGUI.cb_tkt_kethuoc.setSelectedIndex(0);
            trangChuGUI.cb_tkt_tenthuoc.setSelectedIndex(0);
            trangChuGUI.cb_tkt_ncc.setSelectedIndex(0);
            hienThiThuoc();
        });

        // ======= SỰ KIỆN TAB CẬP NHẬT =======

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

        // click vào bảng cập nhật để hiển thị dữ liệu lên form (gồm ảnh)
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

                        // hiển thị ảnh (nếu có)
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

     // nút khôi phục
        trangChuGUI.btn_cntKhoiphuc.addActionListener(e -> {
            String maThuoc = trangChuGUI.text_cntmt.getText().trim();

            if (maThuoc.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập hoặc chọn mã thuốc cần khôi phục!");
                return;
            }

            // lấy lại thuốc gốc từ database
            Thuoc thuocGoc = dao.getThuocTheoMa(maThuoc);

            if (thuocGoc != null) {
                // hiển thị lại dữ liệu lên form
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


        // nút chọn ảnh
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

                // lưu đường dẫn ảnh vào label để lấy lại khi cập nhật
                trangChuGUI.lb_Chuaanh.setToolTipText(path);
            }
        });

        // nút cập nhật thuốc
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

                // thêm đường dẫn ảnh
                if (trangChuGUI.lb_Chuaanh.getToolTipText() != null) {
                    t.setAnh(trangChuGUI.lb_Chuaanh.getToolTipText());
                }

                boolean updated = dao.updateThuoc(t);
                if (updated) {
                    JOptionPane.showMessageDialog(trangChuGUI, "Cập nhật thuốc thành công!");
                    hienThiThuocLenCapNhat();
                    hienThiThuoc();
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

        // tìm kiếm trong tab cập nhật
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
    }

    // ======= HIỂN THỊ DỮ LIỆU =======

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
        for (Thuoc t : dsThuoc) {
            boolean matchKe = trangChuGUI.cb_tkt_kethuoc.getSelectedItem().equals("Tất cả")
                    || t.getKeThuoc().getLoaiKe().equals(trangChuGUI.cb_tkt_kethuoc.getSelectedItem());
            boolean matchTen = trangChuGUI.cb_tkt_tenthuoc.getSelectedItem().equals("Tất cả")
                    || t.getTenThuoc().equals(trangChuGUI.cb_tkt_tenthuoc.getSelectedItem());
            boolean matchNCC = trangChuGUI.cb_tkt_ncc.getSelectedItem().equals("Tất cả")
                    || t.getNhaCungCap().getTenNhaCungCap().equals(trangChuGUI.cb_tkt_ncc.getSelectedItem());
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

    // ======= BỘ LỌC TÌM KIẾM =======

    private void timKiemThuoc_TK() {
        String maTK = trangChuGUI.text_cnt_tkmt.getText().trim();
        String tenTK = trangChuGUI.text_cnt_tktt.getText().trim();
        String keTK = trangChuGUI.cb_cnt_tktkt.getSelectedItem().toString().trim();
        filterTable(trangChuGUI.table_Capnhatthuoc, maTK, tenTK, keTK);
    }

    private void filterTable(JTable table, String maTK, String tenTK, String keTK) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<Thuoc> dsThuoc = dao.getAllThuoc();

        for (Thuoc t : dsThuoc) {
            boolean matchMa = maTK.isEmpty() || t.getMaThuoc().toLowerCase().contains(maTK.toLowerCase());
            boolean matchTen = tenTK.isEmpty() || t.getTenThuoc().toLowerCase().contains(tenTK.toLowerCase());
            boolean matchKe = keTK.equals("Tất cả") || t.getKeThuoc().getLoaiKe().equalsIgnoreCase(keTK);

            // hiển thị chỉ khi tất cả điều kiện đều thỏa
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
        }
    }
}
