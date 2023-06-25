package iuh.dhktpm15.repositories;

import iuh.dhktpm15.entities.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface NguoiDungRepository extends JpaRepository<NguoiDung,Long> {
    @Query(value = "select * from NguoiDung n where n.sdt = :sdt", nativeQuery = true)
    public Optional<NguoiDung> findNguoiDungBySdt(@Param("sdt") String sdt);
}
