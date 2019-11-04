package tugas1_sidok_1706043613.sidok.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="dokter")
public class DokterModel implements Serializable, Comparable<DokterModel>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="nama", nullable=false)
    private String nama;

    @NotNull
    @Size(max=255)
    @Column(name="nip", nullable=false)
    private String nip;

    @NotNull
    @Size(max=255)
    @Column(name="nik", nullable=false)
    private String nik;

    @NotNull
    @DateTimeFormat(pattern = "MM/dd/YYYY")
    @Column(name="tanggal_lahir", nullable=false)
    private Date tanggalLahir;

    @NotNull
    @Size(max=255)
    @Column(name="tempat_lahir", nullable=false)
    private String tempatLahir;

    @NotNull
    @Column(name="jenis_kelamin", nullable=false)
    private int jenisKelamin;

    @OneToMany(mappedBy = "dokterModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Jadwal_JagaModel> listJadwal;

    @OneToMany(mappedBy = "dokterModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Spesialisasi_DokterModel> listSpesialisasi;

    public DokterModel(Long id, String nama, String nip, String nik, Date tanggalLahir, String tempatLahir, int jenisKelamin, List<Jadwal_JagaModel> listJadwal, List<Spesialisasi_DokterModel> listSpesialisasi) {
        this.id = id;
        this.nama = nama;
        this.nip = nip;
        this.nik = nik;
        this.tanggalLahir = tanggalLahir;
        this.tempatLahir = tempatLahir;
        this.jenisKelamin = jenisKelamin;
        this.listJadwal = listJadwal;
        this.listSpesialisasi = listSpesialisasi;
    }

    public DokterModel() {
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

    public String getNip() {
        return this.nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNik() {
        return this.nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Date getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return this.tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public int getJenisKelamin() {
        return this.jenisKelamin;
    }

    public void setJenisKelamin(int jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public List<Jadwal_JagaModel> getListJadwal() {
        return this.listJadwal;
    }

    public void setListJadwal(List<Jadwal_JagaModel> listJadwal) {
        this.listJadwal = listJadwal;
    }

    public List<Spesialisasi_DokterModel> getListSpesialisasi() {
        return this.listSpesialisasi;
    }

    public void setListSpesialisasi(List<Spesialisasi_DokterModel> listSpesialisasi) {
        this.listSpesialisasi = listSpesialisasi;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DokterModel)) {
            return false;
        }
        DokterModel dokterModel = (DokterModel) o;
        return Objects.equals(id, dokterModel.id) && Objects.equals(nama, dokterModel.nama) && Objects.equals(nip, dokterModel.nip) && Objects.equals(nik, dokterModel.nik) && Objects.equals(tanggalLahir, dokterModel.tanggalLahir) && Objects.equals(tempatLahir, dokterModel.tempatLahir) && jenisKelamin == dokterModel.jenisKelamin && Objects.equals(listJadwal, dokterModel.listJadwal) && Objects.equals(listSpesialisasi, dokterModel.listSpesialisasi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nama, nip, nik, tanggalLahir, tempatLahir, jenisKelamin);

    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nama='" + getNama() + "'" +
            ", nip='" + getNip() + "'" +
            ", nik='" + getNik() + "'" +
            ", tanggalLahir='" + getTanggalLahir() + "'" +
            ", tempatLahir='" + getTempatLahir() + "'" +
            ", jenisKelamin='" + getJenisKelamin() + "'" +
            "}";
    }
    

	@Override
	public int compareTo(DokterModel o) {
		return this.id.compareTo(o.id);
    }
}