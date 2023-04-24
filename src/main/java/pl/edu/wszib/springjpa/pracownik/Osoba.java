package pl.edu.wszib.springjpa.pracownik;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Osoba {

    @Id
    @GeneratedValue
    private Integer id;
    private String imie;
    private String nazwisko;
    @ManyToMany
    @JoinTable
    private List<Adres> adres;

    @OneToOne(mappedBy = "osoba")
    private Pracownik pracownik;

    public List<Adres> getAdres() {
        return adres;
    }

    public void setAdres(List<Adres> adres) {
        this.adres = adres;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}