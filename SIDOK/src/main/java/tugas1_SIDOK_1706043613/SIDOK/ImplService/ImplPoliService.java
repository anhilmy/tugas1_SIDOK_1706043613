package tugas1_sidok_1706043613.sidok.ImplService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import tugas1_sidok_1706043613.sidok.Models.Jadwal_JagaModel;
import tugas1_sidok_1706043613.sidok.Models.PoliModel;
import tugas1_sidok_1706043613.sidok.Repository.DokterDb;
import tugas1_sidok_1706043613.sidok.Repository.JadwalDb;
import tugas1_sidok_1706043613.sidok.Repository.PoliDb;
import tugas1_sidok_1706043613.sidok.Service.PoliService;

@Service
@Transactional
public class ImplPoliService implements PoliService {

    @Autowired
    DokterDb dokterDb;

    @Autowired
    JadwalDb jadwalDb;

    @Autowired
    PoliDb poliDb;

    @Override
    public List<PoliModel> findAllPoli() {
        List<PoliModel> allPoli = poliDb.findAll();
        return allPoli;
    }

    @Override
    public PoliModel addPoli(PoliModel poliModel) {
        poliDb.save(poliModel);
        return poliModel;
    }

    @Override
    public List<DokterModel> findAllDokterByIdPoli(Long idPoli) {
        List<Jadwal_JagaModel> listJadwal = jadwalDb.findByPoliModelId(idPoli);
        List<DokterModel> listDokter = new ArrayList<DokterModel>();
        for (Jadwal_JagaModel target : listJadwal) {
            if(!listDokter.contains(target.getDokterModel())){
               listDokter.add(target.getDokterModel());
            }
        }
        return listDokter;
    }

    @Override
    public PoliModel ubahPoli(PoliModel poliModel) {
        PoliModel targetPoli = poliDb.findById(poliModel.getId()).get();
        targetPoli.setNama(poliModel.getNama());
        targetPoli.setLokasi(poliModel.getLokasi());
        return targetPoli;
    }

    @Override
    public Optional<PoliModel> findByIdPoli(Long idPoli) {
        return poliDb.findById(idPoli);
    }

    @Override
    public List<PoliModel> findAll() {
        return poliDb.findAll();
    }

}