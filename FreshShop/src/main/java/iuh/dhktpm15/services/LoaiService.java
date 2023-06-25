package iuh.dhktpm15.services;



import iuh.dhktpm15.entities.Loai;

import java.util.List;

public interface LoaiService {
    public List<Loai> findByAll();
    public Loai update(long idLoai);

    public Loai save(Loai loai);

    public Loai findById(long id);
}
