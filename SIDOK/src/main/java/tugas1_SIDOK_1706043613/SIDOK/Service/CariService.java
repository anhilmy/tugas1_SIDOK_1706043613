package tugas1_sidok_1706043613.sidok.Service;

import java.util.List;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import tugas1_sidok_1706043613.sidok.Models.PoliModel;
import tugas1_sidok_1706043613.sidok.Models.SpesialisasiModel;

public interface CariService {   
   
   List<DokterModel> cariDokterBySpesialisPoli(PoliModel poli, SpesialisasiModel spesialis);
   DokterModel cariDokterTersibuk(PoliModel poli);
}