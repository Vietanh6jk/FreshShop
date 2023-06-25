package iuh.dhktpm15.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="NongSan")
public class NongSan {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tenNongSan")
    private String tenNongSan;

    @Column(name = "hinhAnh")
    private String hinhAnh;

    @Column(name = "giaBanHienTai")
    private float giaBanHienTai;

    @Column(name = "trongLuong")
    private float trongLuong;

    @Column(name = "moTa")
    private String moTa;

    @Column(name = "trangThai")
    private int trangThai;

    @ManyToOne
    @JoinColumn(name = "idLoai")
    private Loai loai;

    @OneToMany(mappedBy = "nongSan", cascade = CascadeType.ALL)
    private List<DonNhap> donNhap;

    @OneToMany(mappedBy = "nongSan")
    private List<CT_DonBan> ctDonBans;

    public NongSan() {

    }

    public NongSan(long id, String tenNongSan, String hinhAnh, float giaBanHienTai, float trongLuong, String moTa, int trangThai, Loai loai, List<DonNhap> donNhap, List<CT_DonBan> ctDonBans) {
        this.id = id;
        this.tenNongSan = tenNongSan;
        this.hinhAnh = hinhAnh;
        this.giaBanHienTai = giaBanHienTai;
        this.trongLuong = trongLuong;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.loai = loai;
        this.donNhap = donNhap;
        this.ctDonBans = ctDonBans;
    }

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

    public List<DonNhap> getCtDonNhaps() {
        return donNhap;
    }

    public void setCtDonNhaps(List<DonNhap> donNhap) {
        this.donNhap = donNhap;
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
