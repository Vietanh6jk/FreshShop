package iuh.dhktpm15.services.impl;


import iuh.dhktpm15.entities.Loai;
import iuh.dhktpm15.repositories.LoaiRepository;
import iuh.dhktpm15.services.LoaiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiServiceImpl implements LoaiService {
    private final LoaiRepository loaiRepository;
    public LoaiServiceImpl (LoaiRepository loaiRepository){
        this.loaiRepository = loaiRepository;
    }

    @Override
    public List<Loai> findByAll() {
        return loaiRepository.findAll();
    }

    @Override
    public Loai update(long idLoai) {
        return loaiRepository.update(idLoai);
    }

    @Override
    public Loai save(Loai loai) {
        return loaiRepository.save(loai);
    }

    @Override
    public Loai findById(long id) {
        return loaiRepository.findById(id).orElse(null);
    }
}
