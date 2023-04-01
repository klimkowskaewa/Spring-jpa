package pl.edu.wszib.springjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/praca")
public class PracaController {
    @Autowired
    private PracaRepository repository;

    @GetMapping
    public List<Praca> list(@RequestParam(required = false) String stanowisko) {
        if (stanowisko == null) {
            return repository.findAll();
        }
        return repository.findAllByStanowisko(stanowisko);
    }

    @GetMapping("/{id}")
    public Praca get (@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public Praca create(@RequestBody Praca praca) {
        praca.setId(null);
        return repository.save(praca);
    }

    @PutMapping
    public Praca update(@RequestBody Praca praca) {
        Praca istniejaca = get(praca.getId());
        istniejaca.setFirma(praca.getFirma());
        istniejaca.setStanowisko(praca.getStanowisko());
        return repository.save(istniejaca);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Integer id) {
        repository.deleteById(id);
    }

}