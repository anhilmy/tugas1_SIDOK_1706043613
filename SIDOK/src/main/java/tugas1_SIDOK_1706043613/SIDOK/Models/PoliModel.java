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

import tugas1_sidok_1706043613.sidok.Models.Jadwal_JagaModel;

@Entity
@Table(name="poli")
public class PoliModel implements Serializable, Comparable<DokterModel>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="nama", nullable=false)
    private String nama;

    @NotNull
    @Size(max=255)
    @Column(name="lokasi", nullable=false)
    private String lokasi;

    @OneToMany(mappedBy = "poliModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Jadwal_JagaModel> listJadwal;


    public PoliModel(Long id, String nama, String lokasi, List<Jadwal_JagaModel> listJadwal) {
        this.id = id;
        this.nama = nama;
        this.lokasi = lokasi;
        this.listJadwal = listJadwal;
    }

    public PoliModel(){
        
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

    public String getLokasi() {
        return this.lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public List<Jadwal_JagaModel> getListJadwal() {
        return this.listJadwal;
    }

    public void setListJadwal(List<Jadwal_JagaModel> listJadwal) {
        this.listJadwal = listJadwal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PoliModel)) {
            return false;
        }
        PoliModel poliModel = (PoliModel) o;
        return Objects.equals(id, poliModel.id) && Objects.equals(nama, poliModel.nama) && Objects.equals(lokasi, poliModel.lokasi) && Objects.equals(listJadwal, poliModel.listJadwal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nama, lokasi, listJadwal);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nama='" + getNama() + "'" +
            ", lokasi='" + getLokasi() + "'" +
            ", listJadwal='" + getListJadwal() + "'" +
            "}";
    }


	@Override
	public int compareTo(DokterModel o) {
		return this.id.compareTo(o.getId());
    }
}
