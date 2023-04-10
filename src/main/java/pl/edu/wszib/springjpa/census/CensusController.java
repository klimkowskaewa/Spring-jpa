package pl.edu.wszib.springjpa.census;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/census")
public class CensusController {

    @Autowired
    private CensusRepository censusRepository;

    @GetMapping
    public String list(Model model, @RequestParam(required = false) Integer editedId) {
        List<Persons> persons = censusRepository.findAll();
        model.addAttribute("persons", persons);
        model.addAttribute("editedId", editedId);

        if (editedId != null) {
            model.addAttribute("editPerson", censusRepository.findById(editedId).get());
        }
        return "census/list";
    }

    @GetMapping("/create")
    public String create (Model model){
        Persons newPersons = new Persons();
        model.addAttribute("newPersons", newPersons);
        return "census/create";
    }

    @PostMapping("/create")
    public String createAction(Persons newPersons){
        newPersons.setId(null);
        censusRepository.save(newPersons);
        return "redirect:/census";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id){
        censusRepository.deleteById(id);
        return "redirect:/census";
    }

    @PostMapping("edit/{id}")
    public String edit(Persons person, @PathVariable Integer id){
        Persons existing = censusRepository.findById(id).get();
        existing.setName(person.getName());
        existing.setSurname(person.getSurname());
        existing.setDateOfBirth(person.getDateOfBirth());
        censusRepository.save(existing);
        return "redirect:/census";
    }
}