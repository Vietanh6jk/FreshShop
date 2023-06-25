package iuh.dhktpm15.services.impl;


import iuh.dhktpm15.entities.NongSan;
import iuh.dhktpm15.repositories.NongSanRepository;
import iuh.dhktpm15.services.NongSanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NongSanServiceImpl implements NongSanService {
    private final NongSanRepository nongSanRepository;
    public NongSanServiceImpl(NongSanRepository nongSanRepository){
        this.nongSanRepository = nongSanRepository;
    }

    public List<NongSan> findByAll(){
        return nongSanRepository.findAll();
    }

    @Override
    public NongSan findById(long id) {
        return nongSanRepository.findById(id).orElse(null);
    }

    public List<NongSan> findByName(String name){
        name = "%"+name+"%";
        return nongSanRepository.findNongSanByName(name);
    }
    @Override
    public List<NongSan> findByLoaiId(long idLoai){
        return nongSanRepository.findNongSanByLoaiId(idLoai);
    }

    @Override
    public NongSan save(NongSan nongSan) {
        return nongSanRepository.save(nongSan);
    }

    @Override
    public NongSan ngungKinhDoanh(NongSan nongSan){
        NongSan nongSan1 = nongSanRepository.findById(nongSan.getId()).orElse(null);
        if(nongSan1 != null){
            nongSanRepository.ngungKinhDoanh(nongSan.getId(),nongSan.getTrangThai());
            return nongSan;
        }
         return null;
    }

    @Override
    public NongSan updateTrongLuong(NongSan nongSan, float trongLuong) {
        nongSanRepository.updateTrongLuong(nongSan.getId(), trongLuong);
        return nongSan;
    }
}
