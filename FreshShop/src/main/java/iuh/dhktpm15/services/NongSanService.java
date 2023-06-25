package iuh.dhktpm15.services;



import iuh.dhktpm15.entities.NongSan;

import java.util.List;

public interface NongSanService {
    public List<NongSan> findByAll();
    public NongSan findById(long id);
    public List<NongSan>  findByName(String name);

    public List<NongSan> findByLoaiId(long idLoai);

    public NongSan save(NongSan nongSan);

    public NongSan ngungKinhDoanh(NongSan nongSan);

    public NongSan updateTrongLuong(NongSan nongSan, float trongLuong);

}
