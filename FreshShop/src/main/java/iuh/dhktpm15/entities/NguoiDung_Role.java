package iuh.dhktpm15.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Nguoi_Role")
public class NguoiDung_Role {
    @EmbeddedId
    UserRoleKey userRoleKey;

    @ManyToOne
    @JoinColumn(name = "idNguoiDung")
    @MapsId("idNguoiDung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "idRole")
    @MapsId("idRole")
    private Roles roles;

    public UserRoleKey getUserRoleKey() {
        return userRoleKey;
    }

    public void setUserRoleKey(UserRoleKey userRoleKey) {
        this.userRoleKey = userRoleKey;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
