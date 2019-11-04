package tugas1_sidok_1706043613.sidok.Service;

import java.util.List;
import java.util.Optional;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import tugas1_sidok_1706043613.sidok.Models.PoliModel;

public interface PoliService {
    List<PoliModel> findAllPoli();
    PoliModel addPoli(PoliModel poliModel);

    //SemuaDokterPadaPoli
    List<DokterModel> findAllDokterByIdPoli(Long idPoli);
    
    PoliModel ubahPoli(PoliModel poliModel);
	Optional<PoliModel> findByIdPoli(Long idPoli);
	List<PoliModel> findAll();

    
}