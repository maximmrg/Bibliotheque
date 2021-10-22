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
    private int nbResa;

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

    public int getNbResa() {
        return nbResa;
    }

    public void setNbResa(int nbResa) {
        this.nbResa = nbResa;
    }

    public void nbResaPlus(){
        this.setNbResa(this.nbResa + 1);
    }

    public void nbResaMoins(){
        this.setNbResa(this.nbResa - 1);
    }
}
