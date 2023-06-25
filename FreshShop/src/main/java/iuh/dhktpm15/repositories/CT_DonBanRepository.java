package iuh.dhktpm15.repositories;


import iuh.dhktpm15.entities.CTDonBanKey;
import iuh.dhktpm15.entities.CT_DonBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CT_DonBanRepository extends JpaRepository<CT_DonBan, CTDonBanKey> {

    @Query(value = "select * from CT_DonBan db where db.idDonBan = :idDonBan",nativeQuery = true)
    public List<CT_DonBan> findCT_DonBansByDonBan(@Param("idDonBan") long idDonBan);
}
