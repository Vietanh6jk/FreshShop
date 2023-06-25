package iuh.dhktpm15.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "DonBan")
public class DonBan {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ngayLap")
    private Date ngayLap;

    @Column(name = "diaChiGiao")
    private String diaChiGiao;


    @ManyToOne
    @JoinColumn(name = "nguoiDungId")
    private NguoiDung nguoiDung;

    @OneToMany(mappedBy = "donBan")
    private List<CT_DonBan> ctDonBans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getDiaChiGiao() {
        return diaChiGiao;
    }

    public void setDiaChiGiao(String diaChiGiao) {
        this.diaChiGiao = diaChiGiao;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public List<CT_DonBan> getCtDonBans() {
        return ctDonBans;
    }

    public void setCtDonBans(List<CT_DonBan> ctDonBans) {
        this.ctDonBans = ctDonBans;
    }
}
