package iuh.dhktpm15.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private List<NguoiDung_Role> nguoiDung_roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", name='" + name + '\'' +

                '}';
    }
}
