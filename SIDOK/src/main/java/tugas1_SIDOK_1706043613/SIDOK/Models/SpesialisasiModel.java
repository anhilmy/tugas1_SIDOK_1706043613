package tugas1_sidok_1706043613.sidok.Models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="spesialisasi")
public class SpesialisasiModel implements Serializable, Comparable<DokterModel>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="nama", nullable=false)
    private String nama;

    @NotNull
    @Size(max=255)
    @Column(name="gelar", nullable = false )
    private String gelar;

    @OneToMany(mappedBy = "spesialisasiModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Spesialisasi_DokterModel> listSpDok;

    public SpesialisasiModel(Long id, String nama, String gelar, List<Spesialisasi_DokterModel> listSpDok) {
        this.id = id;
        this.nama = nama;
        this.gelar = gelar;
        this.listSpDok = listSpDok;
    }

    public SpesialisasiModel() {
    }
    
    
	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGelar() {
        return this.gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public List<Spesialisasi_DokterModel> getListSpDok() {
        return this.listSpDok;
    }

    public void setListSpDok(List<Spesialisasi_DokterModel> listSpDok) {
        this.listSpDok = listSpDok;
    }

    public void addListSpDok(Spesialisasi_DokterModel newSpDok){
        this.listSpDok.add(newSpDok);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SpesialisasiModel)) {
            return false;
        }
        SpesialisasiModel spesialisasiModel = (SpesialisasiModel) o;
        return Objects.equals(id, spesialisasiModel.id) && Objects.equals(nama, spesialisasiModel.nama) && Objects.equals(gelar, spesialisasiModel.gelar) && Objects.equals(listSpDok, spesialisasiModel.listSpDok);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nama, gelar);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nama='" + getNama() + "'" +
            ", gelar='" + getGelar() + "'" +
            "}";
    }

    @Override
	public int compareTo(DokterModel o) {
		return this.id.compareTo(o.getId());
    }
}