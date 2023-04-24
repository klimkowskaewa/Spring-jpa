package pl.edu.wszib.springjpa.zwierzeDomowe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gatunek")
public class GatunekController {

    @Autowired
    private GatunekRepository repository;

    @GetMapping("/{id}")
    public Gatunek get(@PathVariable Integer id){
        return repository.findById(id).get();
    }

    @PostMapping
    public Gatunek create(@RequestBody String nazwa){
        Gatunek nowy = new Gatunek();
        nowy.setNazwa(nazwa);
        return repository.save(nowy);
    }


}