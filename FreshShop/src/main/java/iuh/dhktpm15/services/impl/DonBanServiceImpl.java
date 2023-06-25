package iuh.dhktpm15.services.impl;

import iuh.dhktpm15.entities.DonBan;
import iuh.dhktpm15.repositories.DonBanRepository;
import iuh.dhktpm15.services.DonBanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonBanServiceImpl implements DonBanService {
    private final DonBanRepository donBanRepository;

    public DonBanServiceImpl(DonBanRepository donBanRepository) {
        this.donBanRepository = donBanRepository;
    }

    @Override
    public DonBan save(DonBan donBan){
        return donBanRepository.save(donBan);
    }

    @Override
    public List<DonBan> findDonBanByKH(long idKhach) {
        return donBanRepository.findDonBanByKhach(idKhach);
    }

    @Override
    public List<DonBan> findAllDonBan() {
        return donBanRepository.findAll();
    }
}
