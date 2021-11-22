package fr.miage.Bibliotheque.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data //create setters/getters, equals, toString method..
@AllArgsConstructor //Create constructors
@NoArgsConstructor //Create empty constructor
public class Exemplaire implements Serializable {

    @Id
    @GeneratedValue
    private long idExemplaire;

    private String etat;
    private boolean estDispo = true;

    @ManyToOne
    private Oeuvre oeuvre;

    public Exemplaire(Oeuvre oeuvre) {
        this.etat = "Neuf";
        this.oeuvre = oeuvre;
    }

    public long getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(long idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public boolean isEstDispo() {
        return estDispo;
    }

    public void setEstDispo(boolean estDispo) {
        this.estDispo = estDispo;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }
}
