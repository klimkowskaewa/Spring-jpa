package pl.edu.wszib.springjpa.census;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CensusRepository extends JpaRepository <Persons, Integer> {
}
