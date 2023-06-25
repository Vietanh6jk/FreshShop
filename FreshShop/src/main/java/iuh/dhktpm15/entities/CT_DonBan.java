package iuh.dhktpm15.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CT_DonBan")
public class CT_DonBan {

    @EmbeddedId
    private CTDonBanKey key;

    @ManyToOne
    @JoinColumn(name = "idNongSan")
    @MapsId("idNongSan")
    private NongSan nongSan;

    @ManyToOne
    @JoinColumn(name = "idDonBan")
    @MapsId("idDonBan")
    private DonBan donBan;

    @Column(name = "giaBan")
    private float giaNhap;

    @Column(name = "soLuong")
    private float soLuong;

    public CTDonBanKey getKey() {
        return key;
    }

    public void setKey(CTDonBanKey key) {
        this.key = key;
    }

    public NongSan getNongSan() {
        return nongSan;
    }

    public void setNongSan(NongSan nongSan) {
        this.nongSan = nongSan;
    }

    public DonBan getDonBan() {
        return donBan;
    }

    public void setDonBan(DonBan donBan) {
        this.donBan = donBan;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public float getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(float soLuong) {
        this.soLuong = soLuong;
    }
}
