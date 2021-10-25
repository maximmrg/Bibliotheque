package fr.miage.Bibliotheque.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data //create setters/getters, equals, toString method..
@AllArgsConstructor //Create constructors
@NoArgsConstructor //Create empty constructor
public class Emprunt implements Serializable {

    @Id
    private long idEmprunt;

    private Date dateEmprunt;

    private boolean enCours;


    @OneToOne
    private Usager usager;

    @OneToOne
    private Exemplaire exemplaire;

    public Emprunt(Date dateEmprunt, Usager usager, Exemplaire exemplaire) {
        this.dateEmprunt = dateEmprunt;
        this.usager = usager;
        this.exemplaire = exemplaire;
        this.enCours = true;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public boolean isEnCours() {
        return enCours;
    }

    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }


}

