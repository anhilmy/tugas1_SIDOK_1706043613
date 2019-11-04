package tugas1_sidok_1706043613.sidok.Service;

import java.util.List;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import tugas1_sidok_1706043613.sidok.Models.Jadwal_JagaModel;

public interface DokterService {
    DokterModel addDokter(DokterModel dokter);
    List<DokterModel> findAllDoctor();
    DokterModel findDoctor(String nik);
    DokterModel ubahDoktor(DokterModel dokter);
    DokterModel hapusDoktor(DokterModel dokter);

    //Jadwal JAGA
    Jadwal_JagaModel tambahJadwal(Jadwal_JagaModel jadwal);
	DokterModel findDoctorById(Long idDokter);
	DokterModel findDoctorByNip(String nip);
}