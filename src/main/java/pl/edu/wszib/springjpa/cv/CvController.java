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

    @GetMapping(value = "/obrazek/{id}", produces = "image/*")
    @ResponseBody
    public byte[] obrazek(@PathVariable Integer id) {
        return repository.findById(id)
                .get()
                .getZdjecie();
    }

    @GetMapping
    public String list(Model model){
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

}