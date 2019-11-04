package tugas1_sidok_1706043613.sidok.ImplService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import tugas1_sidok_1706043613.sidok.Models.SpesialisasiModel;
import tugas1_sidok_1706043613.sidok.Models.Spesialisasi_DokterModel;
import tugas1_sidok_1706043613.sidok.Repository.DokterDb;
import tugas1_sidok_1706043613.sidok.Repository.JadwalDb;
import tugas1_sidok_1706043613.sidok.Repository.PoliDb;
import tugas1_sidok_1706043613.sidok.Repository.SpDokterDb;
import tugas1_sidok_1706043613.sidok.Repository.SpesialiasasiDb;
import tugas1_sidok_1706043613.sidok.Service.SpesialisasiService;

@Service
@Transactional
public class ImplSpesialisasiService implements SpesialisasiService {

    @Autowired
    DokterDb dokterDb;

    @Autowired
    JadwalDb jadwalDb;

    @Autowired
    PoliDb poliDb;

    @Autowired
    SpesialiasasiDb spesialisasiDb;

    @Autowired
    SpDokterDb spDokterDb;

    @Override
    public List<SpesialisasiModel> findAllSpesialisasi() {
        return spesialisasiDb.findAll();
    }

    @Override
    public SpesialisasiModel addSpesialisasi(SpesialisasiModel spModel) {
        spesialisasiDb.save(spModel);
        return spModel;
    }

    @Override
    public List<SpesialisasiModel> findAllSpesialisasiByIdDokter(Long idDokter) {
        List<SpesialisasiModel> spByDokter = new ArrayList<>();
        List<Spesialisasi_DokterModel> dataFromSpDokter = spDokterDb.findByDokterModelId(idDokter);
        for (Spesialisasi_DokterModel spesialisasi_DokterModel : dataFromSpDokter) {
            spByDokter.add(spesialisasi_DokterModel.getSpesialisasiModel());
        }
        return spByDokter;
    }

    @Override
    public List<DokterModel> findAllDokterBySpesialisasiId(Long idSpesialisasi) {
        List<DokterModel> spByDokter = new ArrayList<>();
        List<Spesialisasi_DokterModel> dataFromSpDokter= spDokterDb.findBySpesialisasiModelId(idSpesialisasi);
        for (Spesialisasi_DokterModel spesialisasi_DokterModel : dataFromSpDokter) {
            spByDokter.add(spesialisasi_DokterModel.getDokterModel());
        }        
        return spByDokter;
    }

    @Override
    public SpesialisasiModel ubahSpesialisasi(SpesialisasiModel spesialisasi) {
        SpesialisasiModel target = spesialisasiDb.findById(spesialisasi.getId()).get();
        target.setGelar(spesialisasi.getGelar());
        target.setNama(spesialisasi.getNama());
        return target;
    }

    @Override
    public SpesialisasiModel findSpesialisasiById(Long idSpesialisasi) {
        return spesialisasiDb.findById(idSpesialisasi).get();
    }
}