package tugas1_sidok_1706043613.sidok.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="jadwal_jaga")
public class Jadwal_JagaModel implements Serializable, Comparable<DokterModel>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="hari", nullable=false)
    private String hari;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="id_poli", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoliModel poliModel;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="id_dokter", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DokterModel dokterModel;


    public Jadwal_JagaModel(Long id, String hari, PoliModel poliModel, DokterModel dokterModel) {
        this.id = id;
        this.hari = hari;
        this.poliModel = poliModel;
        this.dokterModel = dokterModel;
    }


    public Jadwal_JagaModel() {
	}


	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHari() {
        return this.hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public PoliModel getPoliModel() {
        return this.poliModel;
    }

    public void setPoliModel(PoliModel poliModel) {
        this.poliModel = poliModel;
    }

    public DokterModel getDokterModel() {
        return this.dokterModel;
    }

    public void setDokterModel(DokterModel dokterModel) {
        this.dokterModel = dokterModel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Jadwal_JagaModel)) {
            return false;
        }
        Jadwal_JagaModel jadwal_JagaModel = (Jadwal_JagaModel) o;
        return Objects.equals(id, jadwal_JagaModel.id) && Objects.equals(hari, jadwal_JagaModel.hari) && Objects.equals(poliModel, jadwal_JagaModel.poliModel) && Objects.equals(dokterModel, jadwal_JagaModel.dokterModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hari, poliModel, dokterModel);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", hari='" + hari + "'" +
            ", poliModel='" + poliModel + "'" +
            ", dokterModel='" + dokterModel + "'" +
            "}";
    }

    @Override
    public int compareTo(DokterModel o) {
        return this.id.compareTo(o.getId());
    }
}
