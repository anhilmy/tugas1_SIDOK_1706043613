package tugas1_sidok_1706043613.sidok.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import tugas1_sidok_1706043613.sidok.Models.PoliModel;
import tugas1_sidok_1706043613.sidok.Models.SpesialisasiModel;
import tugas1_sidok_1706043613.sidok.Service.CariService;
import tugas1_sidok_1706043613.sidok.Service.DokterService;
import tugas1_sidok_1706043613.sidok.Service.PoliService;
import tugas1_sidok_1706043613.sidok.Service.SpesialisasiService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class CariController {

    @Autowired
    DokterService dokterService;

    @Autowired
    SpesialisasiService spService;

    @Autowired
    PoliService poliService;

    @Autowired
    CariService cariService;

    @RequestMapping(value = "/cari", method=RequestMethod.GET)
    public String cariForm(
        Model model
    ){
        model.addAttribute("title", "Cari Dokter");
        List<SpesialisasiModel> listSpesialisasi = spService.findAllSpesialisasi();
        List<PoliModel> listPoli = poliService.findAll();
        model.addAttribute("listSp", listSpesialisasi);
        model.addAttribute("listPoli", listPoli);
        return "cari-home";
    }

    @RequestMapping(value = "/cari", method=RequestMethod.GET, params="submit")
    public String cariSubmit(
        @RequestParam Long idSpesialisasi,
        @RequestParam Long idPoli,
        Model model
    ){
        model.addAttribute("title", "Cari Dokter");
        List<SpesialisasiModel> listSpesialisasi = spService.findAllSpesialisasi();
        List<PoliModel> listPoli = poliService.findAll();
        model.addAttribute("listSp", listSpesialisasi);
        model.addAttribute("listPoli", listPoli);
        PoliModel poli = poliService.findByIdPoli(idPoli).get();
        SpesialisasiModel sp = spService.findSpesialisasiById(idSpesialisasi);
        List<DokterModel> listDokter = cariService.cariDokterBySpesialisPoli(poli, sp);
        model.addAttribute("listDokter", listDokter);
        return "cari-home";
    }

    // Logger logger = LoggerFactory.getLogger(LoggingController.class);

}
