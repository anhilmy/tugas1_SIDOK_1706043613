package tugas1_sidok_1706043613.sidok.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import tugas1_sidok_1706043613.sidok.Models.PoliModel;
import tugas1_sidok_1706043613.sidok.Service.DokterService;
import tugas1_sidok_1706043613.sidok.Service.PoliService;
import tugas1_sidok_1706043613.sidok.Service.SpesialisasiService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PoliController {

    @Autowired
    DokterService dokterService;

    @Autowired
    SpesialisasiService spService;

    @Autowired
    PoliService poliService;
    // Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping(value="/poli", method=RequestMethod.GET)
    public String viewAllPoli(
        Model model    
    ) {
        
        model.addAttribute("title", "Lihat Poli");
        List<PoliModel> listPoli = poliService.findAllPoli();
        model.addAttribute("listPoli", listPoli);

        return "poli-home";
    }

    @RequestMapping(value="/poli/tambah", method=RequestMethod.GET)
    public String tambahPoliForm(
        Model model
    ){
        model.addAttribute("title", "Tambah Poli");
        PoliModel newPoli = new PoliModel();
        model.addAttribute("poli", newPoli);
        return "form-tambah-poli";
    }

    @RequestMapping(value="/poli/tambah", method=RequestMethod.POST)
    public String tambahPoliSubmit(
        @ModelAttribute PoliModel poli,
        RedirectAttributes redirectAttributes,
        Model model
    ){
        PoliModel newPoli = poliService.addPoli(poli);
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/poli/view/" + newPoli.getId();
    }

    @RequestMapping(value="poli/view/{idPoli}", method=RequestMethod.GET)
    public String viewPoli(
        @PathVariable("idPoli") Long idPoli,
        Model model
        
    ){
        model.addAttribute("title", "Lihat Poli");
        PoliModel viewedPoli = poliService.findByIdPoli(idPoli).get();
        model.addAttribute("poli", viewedPoli);
        List<DokterModel> listDokter = poliService.findAllDokterByIdPoli(idPoli);
        model.addAttribute("listDokter", listDokter);
        // System.out.println(listDokter.get(0).getListSpesialisasi());
        // System.out.println("BREAK===============================================================================================================");

        return "view-poli";
    }

}
