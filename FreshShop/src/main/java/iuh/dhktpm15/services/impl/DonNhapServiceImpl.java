package iuh.dhktpm15.services.impl;


import iuh.dhktpm15.entities.DonNhap;
import iuh.dhktpm15.repositories.DonNhapRepository;
import iuh.dhktpm15.services.DonNhapService;
import org.springframework.stereotype.Service;

@Service
public class DonNhapServiceImpl implements DonNhapService {
    private final DonNhapRepository donNhapRepository;

    public DonNhapServiceImpl(DonNhapRepository donNhapRepository) {
        this.donNhapRepository = donNhapRepository;
    }

    @Override
    public DonNhap save(DonNhap donNhap) {
        return donNhapRepository.save(donNhap);
    }
}
