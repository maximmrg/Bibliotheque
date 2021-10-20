package fr.miage.Bibliotheque.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data //create setters/getters, equals, toString method..
@AllArgsConstructor //Create constructors
@NoArgsConstructor //Create empty constructor
public class Oeuvre implements Serializable {

    @Id
    @GeneratedValue
    private long idOeuvre;

    private String nom;

    public Oeuvre(String nom) {
        this.nom = nom;
    }

    public long getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(long idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
