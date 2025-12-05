package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.chiTietHoaDon_DAO;
import dao.thuoc_DAO;
import entity.Thuoc;

public class ThuocBanChay_Controller {

    private chiTietHoaDon_DAO cthdDAO;
    private thuoc_DAO thuocDAO;

    public ThuocBanChay_Controller() {
        this.cthdDAO = new chiTietHoaDon_DAO();
        this.thuocDAO = new thuoc_DAO();
    }

    
    public List<Object[]> getDanhSachThuocBanChay() {
        List<Object[]> danhSachHienThi = new ArrayList<>();

     
        Map<String, Integer> thongKeSoLuong = cthdDAO.getThongKeThuocBanChay();

     
        for (Map.Entry<String, Integer> entry : thongKeSoLuong.entrySet()) {
            String maThuoc = entry.getKey();
            int soLuongBan = entry.getValue();

         
            Thuoc thuoc = thuocDAO.getThuocTheoMa(maThuoc);

            if (thuoc != null) {
                
                String tenThuoc = thuoc.getTenThuoc();
                double giaBan = thuoc.getGiaBan();
                String donViTinh = thuoc.getDonViTinh();
                String tenNCC = (thuoc.getNhaCungCap() != null) ? thuoc.getNhaCungCap().getTenNhaCungCap() : "N/A";
               
                Object hanSuDung = thuoc.getHanSuDung(); 
                String tenKe = (thuoc.getKeThuoc() != null) ? thuoc.getKeThuoc().getLoaiKe() : "N/A";

                Object[] rowData = {
                    maThuoc,
                    tenThuoc,
                    soLuongBan, 
                    giaBan,
                    donViTinh,
                    tenNCC,
                    hanSuDung,
                    tenKe
                };
                danhSachHienThi.add(rowData);
            } else {
            
                System.err.println("Không tìm thấy thông tin chi tiết cho thuốc: " + maThuoc);
            }
        }

        return danhSachHienThi;
    }
}