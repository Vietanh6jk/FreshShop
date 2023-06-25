package iuh.dhktpm15.services.impl;

import iuh.dhktpm15.entities.CT_DonBan;
import iuh.dhktpm15.repositories.CT_DonBanRepository;
import iuh.dhktpm15.services.CT_DonBanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CT_DonBanServiceImpl implements CT_DonBanService {
    private final CT_DonBanRepository donBanRepository;

    public CT_DonBanServiceImpl(CT_DonBanRepository donBanRepository) {
        this.donBanRepository = donBanRepository;
    }

    @Override
    public CT_DonBan save(CT_DonBan ct_donBan){
        return donBanRepository.save(ct_donBan);
    }

    @Override
    public List<CT_DonBan> findByDonBan(long idDonBan){
        return donBanRepository.findCT_DonBansByDonBan(idDonBan);
    }
}
