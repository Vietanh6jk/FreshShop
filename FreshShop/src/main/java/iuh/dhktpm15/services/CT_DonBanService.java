package iuh.dhktpm15.services;

import iuh.dhktpm15.entities.CT_DonBan;

import java.util.List;

public interface CT_DonBanService {

    CT_DonBan save(CT_DonBan ct_donBan);

    List<CT_DonBan> findByDonBan(long idDonBan);
}
