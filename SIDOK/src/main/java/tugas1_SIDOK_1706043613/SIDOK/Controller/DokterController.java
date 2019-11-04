package tugas1_sidok_1706043613.sidok.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import tugas1_sidok_1706043613.sidok.Models.Jadwal_JagaModel;
import tugas1_sidok_1706043613.sidok.Models.PoliModel;
import tugas1_sidok_1706043613.sidok.Models.SpesialisasiModel;
import tugas1_sidok_1706043613.sidok.Models.Spesialisasi_DokterModel;
import tugas1_sidok_1706043613.sidok.Service.DokterService;
import tugas1_sidok_1706043613.sidok.Service.PoliService;
import tugas1_sidok_1706043613.sidok.Service.SpesialisasiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DokterController {

    @Autowired
    DokterService dokterService;

    @Autowired
    SpesialisasiService spService;

    @Autowired
    PoliService PoliService;

    // Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("title", "Home");
        List<DokterModel> allDoct = dokterService.findAllDoctor();
        model.addAttribute("DokterList", allDoct);
        return "home";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.GET)
    public String formAddDokter(Model model) {
        model.addAttribute("title", "Tambah Dokter");
        DokterModel dokter = new DokterModel();
        List<SpesialisasiModel> listSpesialisasi = spService.findAllSpesialisasi();

        dokter.setListSpesialisasi(new ArrayList<>());

        Spesialisasi_DokterModel spDok = new Spesialisasi_DokterModel();
        spDok.setDokterModel(dokter);
        dokter.getListSpesialisasi().add(spDok);
        // System.out.println(spDok);

        model.addAttribute("dokter", dokter);
        model.addAttribute("namaSpesialisasi", listSpesialisasi);
        return "form-add-dokter";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST, params = "addRow")
    public String AddDokterAddRow(@ModelAttribute DokterModel dokter, BindingResult bindingResult, Model model) {
        // System.out.println(
                // "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++BREAK================================================");
        Spesialisasi_DokterModel spDok = new Spesialisasi_DokterModel();
        
        spDok.setDokterModel(dokter);
        dokter.getListSpesialisasi().add(spDok);
        // System.out.println(spDok);
        model.addAttribute("title", "Tambah Dokter");
        // dokter.getListSpesialisasi().add(new Spesialisasi_DokterModel());
        model.addAttribute("dokter", dokter);

        // System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++BREAK================================================");
        // System.out.println(dokter.getListSpesialisasi());

        List<SpesialisasiModel> listSpesialisasi = spService.findAllSpesialisasi();
        model.addAttribute("namaSpesialisasi", listSpesialisasi);
        return "form-add-dokter";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST, params = "removeRow")
    public String AddDokterRemoveRow(@ModelAttribute DokterModel dokter, BindingResult bindingResult, Model model) {
        model.addAttribute("title", "Tambah Dokter");
        dokter.getListSpesialisasi().remove(0);
        model.addAttribute("dokter", dokter);

        List<SpesialisasiModel> listSpesialisasi = spService.findAllSpesialisasi();
        model.addAttribute("namaSpesialisasi", listSpesialisasi);
        return "form-add-dokter";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST, params = "submit")
    public String AddDokterSubmit(@ModelAttribute DokterModel dokter, BindingResult bindingResult, Model model) {
        model.addAttribute("title", "Tambah Dokter");
        // for (Spesialisasi_DokterModel spDokter : dokter.getListSpesialisasi()) {
        // spDokter.setDokterModel(dokter);
        // }
        dokterService.addDokter(dokter);
        model.addAttribute("dokter", dokter);

        return "add-dokter-finish";
    }

    @RequestMapping(value = "/dokter", method = RequestMethod.GET)
    public String viewDokter(@RequestParam String nik, Model model) {
        model.addAttribute("title", "View Dokter");
        DokterModel targetFind = dokterService.findDoctor(nik);
        model.addAttribute("dokter", targetFind);
        return "view-dokter";
    }

    @RequestMapping(value = "/dokter/update/{id}", method = RequestMethod.GET)
    public String updateDokterForm(@PathVariable("id") Long idDokter, Model model) {
        model.addAttribute("title", "Update Dokter");
        DokterModel targetUpdate = dokterService.findDoctorById(idDokter);
        model.addAttribute("dokter", targetUpdate);
        return "form-update-dokter";
    }

    @RequestMapping(value = "/dokter/update/{id}", method = RequestMethod.POST)
    public String updateDokterSubmit(@ModelAttribute DokterModel dokter, @PathVariable("id") Long idDokter, Model model,
            RedirectAttributes redirectAttributes) {
        // System.out.println("PRINT CONTROLLER");
        DokterModel updateDokter = dokterService.ubahDoktor(dokter);
        model.addAttribute("dokter", updateDokter);
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/dokter?nik=" + updateDokter.getNik();
    }

    @RequestMapping(value = "/dokter/delete/{id}", method = RequestMethod.POST)
    public String deleteDokter(@PathVariable("id") Long idDokter, Model model) {
        model.addAttribute("title", "Hapus Dokter");
        DokterModel deleted = dokterService.hapusDoktor(dokterService.findDoctorById(idDokter));
        model.addAttribute("dokter", deleted);
        return "delete-dokter";
    }

    @RequestMapping(value = "/jadwal/tambah/{nip}", method = RequestMethod.GET)
    public String addJadwalForm(@PathVariable("nip") String nip, Model model) {
        model.addAttribute("title", "Tambah Jadwal");
        DokterModel targetDokter = dokterService.findDoctorByNip(nip);
        List<PoliModel> listPoli = PoliService.findAll();
        Jadwal_JagaModel newJadwal = new Jadwal_JagaModel();
        newJadwal.setDokterModel(targetDokter);
        model.addAttribute("dokter", targetDokter);
        model.addAttribute("jadwal", newJadwal);
        model.addAttribute("listPoli", listPoli);
        List<String> hari = new ArrayList<>() { 
            { 
                add("Senin"); 
                add("Selasa"); 
                add("Rabu");
                add("Kamis");
                add("Jumat");
                add("Sabtu");
                add("Minggu"); 
            } 
        };
        model.addAttribute("listHari", hari);
        return "form-tambah-jadwal";
    }

    @RequestMapping(value = "/jadwal/tambah/{nip}", method = RequestMethod.POST)
    public String addJadwalSubmit(
        @PathVariable("nip") String nip,
        @ModelAttribute Jadwal_JagaModel jadwal, 
        RedirectAttributes redirectAttributes,
        Model model) {
            model.addAttribute("title", "Tambah Jadwal");
            DokterModel targetDokter = dokterService.findDoctorByNip(nip);
            jadwal.setDokterModel(targetDokter);
            Jadwal_JagaModel newJadwal = dokterService.tambahJadwal(jadwal);
            model.addAttribute("jadwal", newJadwal);
            redirectAttributes.addFlashAttribute("message", "Sukes menambahkan jadwal jaga " + targetDokter.getNama() + " di " + jadwal.getPoliModel().getNama() + " pada hari " + jadwal.getHari());
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/jadwal/tambah/" + nip;
    }
    

}
