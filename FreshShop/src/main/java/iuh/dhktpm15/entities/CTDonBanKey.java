package iuh.dhktpm15.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CTDonBanKey implements Serializable {
    @Column(name = "idNongSan")
    private long idNongSan;

    @Column(name = "idDonBan")
    private long idDonBan;

    public CTDonBanKey(){}

    public CTDonBanKey(long idNongSan, long idDonBan){
        this.idDonBan = idDonBan;
        this.idNongSan = idNongSan;
    }

    public long getIdNongSan() {
        return idNongSan;
    }

    public void setIdNongSan(long idNongSan) {
        this.idNongSan = idNongSan;
    }

    public long getIdDonBan() {
        return idDonBan;
    }

    public void setIdDonBan(long idDonBan) {
        this.idDonBan = idDonBan;
    }
}
