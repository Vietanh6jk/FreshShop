package iuh.dhktpm15.services.impl;


import iuh.dhktpm15.entities.NguoiDung;
import iuh.dhktpm15.repositories.NguoiDungRepository;
import iuh.dhktpm15.services.NguoiDungService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NguoiDungServiceIpml implements NguoiDungService {
    private final NguoiDungRepository nguoiDungRepository;
    public NguoiDungServiceIpml(NguoiDungRepository nguoiDungRepository){
        this.nguoiDungRepository = nguoiDungRepository;
    }
    @Override
    public NguoiDung save(NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

    @Override
    public Optional<NguoiDung> findBySdt(String sdt) {

        return nguoiDungRepository.findNguoiDungBySdt(sdt);
    }
}
