package pl.edu.wszib.springjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ZwierzeRepository extends JpaRepository<Zwierze, Integer>, JpaSpecificationExecutor<Zwierze> {

    List<Zwierze> findAllByGatunek(String gatunek);

//    @Query("select z from Zwierze z where" +
//            "z.gatunek like: gatunek and z.nazwa = :nazwa and z.wiek> :wiek")
//    List<Zwierze> moja(@Param("gatunek") String gatunek, @Param("nazwa") String nazwa, @Param("wiek") Integer wiek);
}
