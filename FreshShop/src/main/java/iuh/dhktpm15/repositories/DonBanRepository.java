package iuh.dhktpm15.repositories;

import iuh.dhktpm15.entities.DonBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonBanRepository extends JpaRepository<DonBan,Long> {

    @Query(value = "select * from DonBan db where db.nguoiDungId = :idKhach",nativeQuery = true)
    public List<DonBan> findDonBanByKhach(@Param("idKhach") long idKhach);
}
