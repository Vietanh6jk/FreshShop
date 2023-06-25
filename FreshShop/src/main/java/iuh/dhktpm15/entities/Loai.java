package iuh.dhktpm15.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Loai")
public class Loai {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tenLoai")
    private String tenLoai;

    @OneToMany(mappedBy = "loai")
    private List<NongSan> nongSans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public List<NongSan> getNongSans() {
        return nongSans;
    }

    public void setNongSans(List<NongSan> nongSans) {
        this.nongSans = nongSans;
    }
}
