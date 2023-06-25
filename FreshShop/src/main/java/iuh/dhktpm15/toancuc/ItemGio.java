package iuh.dhktpm15.toancuc;


import iuh.dhktpm15.entities.CT_DonBan;
import iuh.dhktpm15.entities.Loai;

import java.util.List;

public class ItemGio {

    private long id;


    private String tenNongSan;


    private String hinhAnh;


    private float giaBanHienTai;


    private float trongLuong;


    private String moTa;


    private int trangThai;


    private Loai loai;

    private float soLuong;

    private float tongGia;

    public float getTongGia() {
        return this.giaBanHienTai * this.soLuong;
    }

    public void setTongGia() {
        this.tongGia = this.giaBanHienTai * this.soLuong;
    }

    public float getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(float soLuong) {
        this.soLuong = soLuong;
    }



    private List<CT_DonBan> ctDonBans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenNongSan() {
        return tenNongSan;
    }

    public void setTenNongSan(String tenNongSan) {
        this.tenNongSan = tenNongSan;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public float getTrongLuong() {
        return trongLuong;
    }

    public void setTrongLuong(float trongLuong) {
        this.trongLuong = trongLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Loai getLoai() {
        return loai;
    }

    public void setLoai(Loai loai) {
        this.loai = loai;
    }



    public List<CT_DonBan> getCtDonBans() {
        return ctDonBans;
    }

    public void setCtDonBans(List<CT_DonBan> ctDonBans) {
        this.ctDonBans = ctDonBans;
    }

    public float getGiaBanHienTai() {
        return giaBanHienTai;
    }

    public void setGiaBanHienTai(float giaBanHienTai) {
        this.giaBanHienTai = giaBanHienTai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
