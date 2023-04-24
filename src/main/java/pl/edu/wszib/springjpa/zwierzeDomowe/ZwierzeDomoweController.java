package pl.edu.wszib.springjpa.zwierzeDomowe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zwierze-domowe")
public class ZwierzeDomoweController {

    @Autowired
    private ZwierzeDomoweRepository repository;

    @GetMapping("/{id}")
    public ZwierzeDomowe get (@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public ZwierzeDomowe create(@RequestBody ZwierzeDomowe zwierzeDomowe){
        return repository.save(zwierzeDomowe);
    }

}