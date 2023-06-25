package iuh.dhktpm15.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hoTen")
    private String hoTen;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "nguoiDung")
    private List<DonBan> donBans;

    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    private List<NguoiDung_Role> nguoiDung_roles;



    public NguoiDung(String hoTen, String sdt, String password) {
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.password = password;

    }

    public NguoiDung(NguoiDung nguoiDung){
        this.hoTen = nguoiDung.getHoTen();
        this.sdt = nguoiDung.getSdt();
        this.password = nguoiDung.getPassword();
    }

    public NguoiDung() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<NguoiDung_Role> getNguoiDung_roles() {
        return nguoiDung_roles;
    }

    public void setNguoiDung_roles(List<NguoiDung_Role> nguoiDung_roles) {
        this.nguoiDung_roles = nguoiDung_roles;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "id=" + id +
                ", hoTen='" + hoTen + '\'' +
                ", sdt='" + sdt + '\'' +
                ", password='" + password + '\'' +

                '}';
    }
}
