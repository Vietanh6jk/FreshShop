package iuh.dhktpm15.entities;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "DonNhap")
public class DonNhap {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ngayLap")
    private Date date;

    @Column(name = "diaChiGiao")
    private String diaChiGiao;

    @Column(name = "giaNhap")
    private float giaNhap;

    @Column(name = "soLuong")
    private float soLuong;

    @ManyToOne
    @JoinColumn(name = "idNongSan")
    private NongSan nongSan;

    public DonNhap(){
    }

    public DonNhap(Date date, String diaChiGiao, float giaNhap, float soLuong, NongSan nongSan) {
        this.date = date;
        this.diaChiGiao = diaChiGiao;
        this.giaNhap = giaNhap;
        this.soLuong = soLuong;
        this.nongSan = nongSan;

    }
}
