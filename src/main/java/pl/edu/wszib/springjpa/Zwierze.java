package pl.edu.wszib.springjpa;

import javax.persistence.*;

@Entity
public class Zwierze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
  //  @Column(name = "new name")
  //  @Column(nullable = false)
    private String nazwa;
    private Integer wiek;
    private String gatunek;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getWiek() {
        return wiek;
    }

    public void setWiek(Integer wiek) {
        this.wiek = wiek;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }
}