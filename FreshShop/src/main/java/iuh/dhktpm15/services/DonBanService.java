package iuh.dhktpm15.services;

import iuh.dhktpm15.entities.DonBan;

import java.util.List;

public interface DonBanService {
    DonBan save(DonBan donBan);

    public List<DonBan> findDonBanByKH(long idKhach);

    public List<DonBan> findAllDonBan();
}
