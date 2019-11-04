package tugas1_sidok_1706043613.sidok.Service;

import java.util.List;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import tugas1_sidok_1706043613.sidok.Models.SpesialisasiModel;

public interface SpesialisasiService {
    List<SpesialisasiModel> findAllSpesialisasi();
    SpesialisasiModel addSpesialisasi(SpesialisasiModel spModel);
    
    List<SpesialisasiModel> findAllSpesialisasiByIdDokter(Long idDokter);
    List<DokterModel> findAllDokterBySpesialisasiId(Long idSpesialisasi);
    
    SpesialisasiModel ubahSpesialisasi(SpesialisasiModel spesialisasi);
	SpesialisasiModel findSpesialisasiById(Long idSpesialisasi);

    
}