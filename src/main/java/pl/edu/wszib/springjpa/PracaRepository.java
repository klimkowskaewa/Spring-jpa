package pl.edu.wszib.springjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PracaRepository extends JpaRepository<Praca, Integer> {

    List<Praca> findAllByStanowisko(String stanowisko);

}
