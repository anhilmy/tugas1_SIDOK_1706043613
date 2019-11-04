package tugas1_sidok_1706043613.sidok.ImplService;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import tugas1_sidok_1706043613.sidok.Models.Jadwal_JagaModel;
import tugas1_sidok_1706043613.sidok.Models.PoliModel;
import tugas1_sidok_1706043613.sidok.Models.SpesialisasiModel;
import tugas1_sidok_1706043613.sidok.Models.Spesialisasi_DokterModel;
import tugas1_sidok_1706043613.sidok.Repository.DokterDb;
import tugas1_sidok_1706043613.sidok.Repository.JadwalDb;
import tugas1_sidok_1706043613.sidok.Repository.PoliDb;
import tugas1_sidok_1706043613.sidok.Repository.SpDokterDb;
import tugas1_sidok_1706043613.sidok.Service.CariService;

@Service
@Transactional
public class ImplCariService implements CariService {

    @Autowired
    DokterDb dokterDb;

    @Autowired
    JadwalDb jadwalDb;

    @Autowired
    PoliDb poliDb;

    @Autowired
    SpDokterDb spDokterDb;

    @Override
    public List<DokterModel> cariDokterBySpesialisPoli(PoliModel poli, SpesialisasiModel spesialis) {
        List<Jadwal_JagaModel> poliDokter = jadwalDb.findByPoliModelId(poli.getId());
        List<Spesialisasi_DokterModel> spDokter = spDokterDb.findBySpesialisasiModelId(spesialis.getId());
        List<DokterModel> dataFromPoliModel = new ArrayList<DokterModel>();
        List<DokterModel> dataFromSpDokter = new ArrayList<DokterModel>();
        for (Jadwal_JagaModel jadwalJaga : poliDokter) {
            if (!dataFromPoliModel.contains(jadwalJaga.getDokterModel())) {
                dataFromPoliModel.add(jadwalJaga.getDokterModel());
            }

        }
        for (Spesialisasi_DokterModel spDokEach : spDokter) {
            if (!dataFromSpDokter.contains(spDokEach.getDokterModel())) {

                dataFromSpDokter.add(spDokEach.getDokterModel());
            }
        }
        dataFromPoliModel.retainAll(dataFromSpDokter);

        return dataFromPoliModel;
    }

    @Override
    public DokterModel cariDokterTersibuk(PoliModel poli) {
        // List<Object[]> listCandidate =
        // jadwalDb.findMostCountDokterByIdPoli(poli.getId());
        // BigInteger idDokterBusy = listCandidate.get(0)[1];
        return null;
    }
}