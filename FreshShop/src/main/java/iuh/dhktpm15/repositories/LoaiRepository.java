package iuh.dhktpm15.repositories;


import iuh.dhktpm15.entities.Loai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LoaiRepository extends JpaRepository<Loai,Long> {
    @Modifying
    @Transactional
    @Query(value = "update Loai l set l.tenLoai where l.id = :idLoai", nativeQuery = true)
    public Loai update(@Param("idLoai") long idLoai);
}
