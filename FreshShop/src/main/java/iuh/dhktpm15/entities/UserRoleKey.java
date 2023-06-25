package iuh.dhktpm15.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

import java.io.Serializable;

@Embeddable
public class UserRoleKey implements Serializable {

    @Column(name = "idNguoiDung")
    private long idNguoiDung;


    @Column(name = "idRole")
    private long idRole;

    public UserRoleKey(long idNguoiDung, long idRole) {
        this.idNguoiDung = idNguoiDung;
        this.idRole = idRole;
    }

    public UserRoleKey() {
    }

    public long getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(long idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }
}
