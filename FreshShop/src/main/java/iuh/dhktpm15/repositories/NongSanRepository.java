package iuh.dhktpm15.repositories;


import iuh.dhktpm15.entities.NongSan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NongSanRepository extends JpaRepository<NongSan, Long> {

    @Query(value = "select * from NongSan l where l.idLoai = :idLoai", nativeQuery = true)
    public List<NongSan> findNongSanByLoaiId(@Param("idLoai") long id);

    @Modifying
    @Transactional
    @Query(value = "update NongSan ns set ns.trangThai = :trangThai where ns.id = :idNongSan",nativeQuery = true)
    public void ngungKinhDoanh(
            @Param("idNongSan") long idNongSan,
            @Param("trangThai") int trangThai);

    @Modifying
    @Transactional
    @Query(value = "update NongSan ns set ns.trongLuong = :trongLuong where ns.id = :idNongSan", nativeQuery = true)
    public void updateTrongLuong(@Param("idNongSan") long idNongSan,
                                 @Param("trongLuong") float trongLuong);

    @Query(value = "select * from NongSan ns where ns.tenNongSan like :ten", nativeQuery = true)
    public List<NongSan> findNongSanByName(@Param("ten") String ten);
}
