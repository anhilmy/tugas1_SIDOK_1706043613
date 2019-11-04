package tugas1_sidok_1706043613.sidok.ImplService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import tugas1_sidok_1706043613.sidok.Models.Jadwal_JagaModel;
import tugas1_sidok_1706043613.sidok.Models.SpesialisasiModel;
import tugas1_sidok_1706043613.sidok.Models.Spesialisasi_DokterModel;
import tugas1_sidok_1706043613.sidok.Repository.DokterDb;
import tugas1_sidok_1706043613.sidok.Repository.JadwalDb;
import tugas1_sidok_1706043613.sidok.Repository.SpDokterDb;
import tugas1_sidok_1706043613.sidok.Repository.SpesialiasasiDb;
import tugas1_sidok_1706043613.sidok.Service.DokterService;


@Service
@Transactional
public class ImplDokterService implements DokterService {

    @Autowired
    DokterDb dokterDb;

    @Autowired
    JadwalDb jadwalDb;

    @Autowired
    SpDokterDb spDokDb;

    @Autowired
    SpesialiasasiDb spDb;


    public String createNIK(DokterModel dokter){
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)  + 5);
        System.out.println(dokter.getTanggalLahir());
        DateFormat dateFormat = new SimpleDateFormat("ddMMYY");
        String birthDate = dateFormat.format(dokter.getTanggalLahir());
        String jk = Integer.toString(dokter.getJenisKelamin());
        String letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();
        String rand = (Character.toString(letter.charAt(r.nextInt(letter.length())))) + (Character.toString(letter.charAt(r.nextInt(letter.length()))));
        return year + birthDate + jk + rand;
    }

    @Override
    public DokterModel addDokter(DokterModel dokter) {
        String NIK = createNIK(dokter);
        dokter.setNik(NIK);
        // System.out.println(dokter);
        // System.out.println(dokter.getListSpesialisasi());
        SpesialisasiModel target;
        for (Spesialisasi_DokterModel spDok : dokter.getListSpesialisasi()) {
            target = spDb.findById(spDok.getSpesialisasiModel().getId()).get();
            target.addListSpDok(spDok);
            spDb.save(target);
        }

        
        dokterDb.save(dokter);
        // System.out.println("ADD DOKTER=================================================================================================================================");
        
        for (Spesialisasi_DokterModel spDok : dokter.getListSpesialisasi()) {
            spDok.setDokterModel(dokter);
            System.out.println(spDok);
            spDokDb.save(spDok);
        }

        

        return dokter;
    }

    @Override
    public List<DokterModel> findAllDoctor() {
        return dokterDb.findAll();
    }

    @Override
    public DokterModel findDoctor(String nik) {
        return dokterDb.findByNik(nik).get()  ;
    }

    @Override
    public DokterModel ubahDoktor(DokterModel dokter) {
        DokterModel target = dokterDb.findById(dokter.getId()).get();
        String newNik = createNIK(dokter);
        target.setJenisKelamin(dokter.getJenisKelamin());
        target.setNik(newNik);
        target.setNama(dokter.getNama());
        target.setTanggalLahir(dokter.getTanggalLahir());
        target.setTempatLahir(dokter.getTempatLahir());
        return target;
    }

    @Override
    public DokterModel hapusDoktor(DokterModel dokter) {
        dokterDb.delete(dokter);
        return dokter;
    }

    @Override
    public Jadwal_JagaModel tambahJadwal(Jadwal_JagaModel jadwal) {
        jadwalDb.save(jadwal);
        return jadwal;
    }

    @Override
    public DokterModel findDoctorById(Long idDokter) {
        return dokterDb.findById(idDokter).get();
    }

    @Override
    public DokterModel findDoctorByNip(String nip) {
        return dokterDb.findByNip(nip).get();
    }

}