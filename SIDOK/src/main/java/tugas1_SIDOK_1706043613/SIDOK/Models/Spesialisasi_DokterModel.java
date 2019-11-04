package tugas1_sidok_1706043613.sidok.Models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="spesialisasi_dokter")
public class Spesialisasi_DokterModel implements Serializable, Comparable<DokterModel>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_dokter", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DokterModel dokterModel;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="id_spesialisasi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SpesialisasiModel spesialisasiModel;

    public Spesialisasi_DokterModel(Long id, DokterModel dokterModel, SpesialisasiModel spesialisasiModel) {
        this.id = id;
        this.dokterModel = dokterModel;
        this.spesialisasiModel = spesialisasiModel;
    }

    public Spesialisasi_DokterModel(){
        
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DokterModel getDokterModel() {
        return this.dokterModel;
    }

    public void setDokterModel(DokterModel dokterModel) {
        this.dokterModel = dokterModel;
    }

    public SpesialisasiModel getSpesialisasiModel() {
        return this.spesialisasiModel;
    }

    public void setSpesialisasiModel(SpesialisasiModel spesialisasiModel) {
        this.spesialisasiModel = spesialisasiModel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Spesialisasi_DokterModel)) {
            return false;
        }
        Spesialisasi_DokterModel spesialisasi_DokterModel = (Spesialisasi_DokterModel) o;
        return Objects.equals(id, spesialisasi_DokterModel.id) && Objects.equals(dokterModel, spesialisasi_DokterModel.dokterModel) && Objects.equals(spesialisasiModel, spesialisasi_DokterModel.spesialisasiModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dokterModel, spesialisasiModel);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dokterModel='" + getDokterModel() + "'" +
            ", spesialisasiModel='" + getSpesialisasiModel() + "'" +
            "}";
    }

    @Override
	public int compareTo(DokterModel o) {
		return this.id.compareTo(o.getId());
    }
}