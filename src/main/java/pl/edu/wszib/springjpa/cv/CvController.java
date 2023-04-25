package pl.edu.wszib.springjpa.cv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/moje-cv")
public class CvController {

    @Autowired
    private CvRepository repository;

    @Autowired
    private KompetencjaRepository kompetencjaRepository;

    @Autowired
    private PracaRepository pracaRepository;

    @GetMapping(value = "/obrazek/{id}", produces = "image/*")
    @ResponseBody
    public byte[] obrazek(@PathVariable Integer id) {
        return repository.findById(id)
                .get()
                .getZdjecie();
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cvs", repository.findAll());
        return "list-cv";
    }

    @GetMapping("/dodaj")
    public String dodaj(Model model) {
        CV nowe = new CV();
        model.addAttribute("noweCv", nowe);
        return "dodaj-cv";
    }

    @PostMapping("/dodaj")
    public String dodajPost(CV cv, @RequestParam("obraz") MultipartFile obraz) throws IOException {
        cv.setZdjecie(
                obraz.getBytes()
        );
        repository.save(cv);
        return "redirect:/moje-cv";
    }

    @GetMapping("/widok/{id}")
    public String widok(Model model, @PathVariable Integer id) {
        model.addAttribute("cv",
                repository.findById(id).get()
        );
        return "widok-cv";
    }

    @GetMapping("/edytuj/{id}")
    public String edytuj(Model model, @PathVariable Integer id) {
        model.addAttribute("cv",
                repository.findById(id).get()
        );
        Kompetencja nowaKompetencja = new Kompetencja();
        model.addAttribute("nowaKompetencja", nowaKompetencja);
        model.addAttribute("rodzaje", Praca.KompetencjaRodzaj.values());
        Praca nowaPraca = new Praca();
        model.addAttribute("nowaPraca", nowaPraca);
        return "edytuj-cv";
    }

    @PostMapping("/edytuj/{id}")
    public String edytujPost(@PathVariable Integer id, CV cv, @RequestParam("obraz") MultipartFile obraz) throws IOException {
        CV istniejacy = repository.findById(id).get();
        istniejacy.setDataUrodzenia(cv.getDataUrodzenia());
        istniejacy.setImie(cv.getImie());
        istniejacy.setNazwisko(cv.getNazwisko());

        if (!obraz.isEmpty()){
            istniejacy.setZdjecie(obraz.getBytes());
        }

        repository.save(istniejacy);
        return "redirect:/moje-cv/widok/" + id;
    }

    @PostMapping("/edytuj/{cvId}/kompetencja")
    public String dodajKompetencje( Kompetencja kompetencja, @PathVariable Integer cvId) {
        CV istnieje = repository.findById(cvId).get();
        kompetencja.setCv(istnieje);
        kompetencjaRepository.save(kompetencja);
        return "redirect:/moje-cv/edytuj/" + cvId;
    }

    @GetMapping("/edytuj/{cvId}/kompetencja/{id}/usun")
    public String usunKompetencje(@PathVariable Integer cvId, @PathVariable Integer id) {
        kompetencjaRepository.deleteById(id);
        return "redirect:/moje-cv/edytuj/" + cvId;
    }

    @PostMapping("/edytuj/{cvId}/praca")
    public String dodajPraca(Praca praca, @PathVariable Integer cvId) {
        CV istnieje = repository.findById(cvId).get();
        praca.setCv(istnieje);
        pracaRepository.save(praca);
        return "redirect:/moje-cv/edytuj/" + cvId;
    }

    @GetMapping("/edytuj/{cvId}/praca/{id}/usun")
    public String usunPraca(@PathVariable Integer cvId, @PathVariable Integer id) {
        pracaRepository.deleteById(id);
        return "redirect:/moje-cv/edytuj/" + cvId;
    }

}