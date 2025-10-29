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

    /**
     * Lấy danh sách thuốc bán chạy để hiển thị lên JTable.
     * @return List các mảng Object, mỗi mảng chứa dữ liệu cho một hàng của bảng.
     */
    public List<Object[]> getDanhSachThuocBanChay() {
        List<Object[]> danhSachHienThi = new ArrayList<>();

        // 1. Lấy thống kê số lượng bán từ DAO
        Map<String, Integer> thongKeSoLuong = cthdDAO.getThongKeThuocBanChay();

        // 2. Lặp qua thống kê và lấy thông tin chi tiết thuốc
        for (Map.Entry<String, Integer> entry : thongKeSoLuong.entrySet()) {
            String maThuoc = entry.getKey();
            int soLuongBan = entry.getValue();

            // Lấy thông tin chi tiết của thuốc
            Thuoc thuoc = thuocDAO.getThuocTheoMa(maThuoc);

            if (thuoc != null) {
                // Trích xuất thông tin cần thiết, xử lý null nếu có
                String tenThuoc = thuoc.getTenThuoc();
                double giaBan = thuoc.getGiaBan();
                String donViTinh = thuoc.getDonViTinh();
                String tenNCC = (thuoc.getNhaCungCap() != null) ? thuoc.getNhaCungCap().getTenNhaCungCap() : "N/A";
                // Chuyển LocalDate sang String hoặc giữ nguyên tùy cột bảng
                Object hanSuDung = thuoc.getHanSuDung(); // Giữ LocalDate hoặc .toString()
                String tenKe = (thuoc.getKeThuoc() != null) ? thuoc.getKeThuoc().getLoaiKe() : "N/A";

                // Tạo mảng Object theo đúng thứ tự cột của table_5
                Object[] rowData = {
                    maThuoc,
                    tenThuoc,
                    soLuongBan, // Thêm cột Số Lượng Bán
                    giaBan,
                    donViTinh,
                    tenNCC,
                    hanSuDung,
                    tenKe
                };
                danhSachHienThi.add(rowData);
            } else {
                // Xử lý trường hợp không tìm thấy thuốc (có thể ghi log)
                System.err.println("Không tìm thấy thông tin chi tiết cho thuốc: " + maThuoc);
            }
        }

        return danhSachHienThi;
    }
}