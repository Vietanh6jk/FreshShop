package iuh.dhktpm15.services;



import iuh.dhktpm15.entities.NguoiDung;

import java.util.Optional;

public interface NguoiDungService {
    public NguoiDung save(NguoiDung nguoiDung);
    public Optional<NguoiDung> findBySdt(String sdt);
}
