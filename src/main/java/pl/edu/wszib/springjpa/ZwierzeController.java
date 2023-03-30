package pl.edu.wszib.springjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zwierze")
public class ZwierzeController {

    @Autowired
    private ZwierzeRepository repository;

    @PostMapping
    public Zwierze create(@RequestBody Zwierze zwierze) {
        return repository.save(zwierze);
    }

}
