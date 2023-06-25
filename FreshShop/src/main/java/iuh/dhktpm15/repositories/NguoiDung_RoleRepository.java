package iuh.dhktpm15.repositories;

import iuh.dhktpm15.entities.NguoiDung_Role;
import iuh.dhktpm15.entities.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NguoiDung_RoleRepository extends JpaRepository<NguoiDung_Role, UserRoleKey> {

    @Query(value = "select * from Nguoi_Role n where n.idNguoiDung = :idNguoiDung",nativeQuery = true)
    public List<NguoiDung_Role> findNguoiDung_RoleByNguoiDung(@Param("idNguoiDung") long id);
}
