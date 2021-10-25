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
    private String nomOeuvre;

    private int nbResa;
    private boolean estPerimee = false;

    public Oeuvre(String nomOeuvre) {
        this.nomOeuvre = nomOeuvre;
        this.nbResa=0;
        estPerimee=false;
    }

    public String getNomOeuvre() {
        return nomOeuvre;
    }

    public void setNomOeuvre(String nomOeuvre) {
        this.nomOeuvre = nomOeuvre;
    }

    public int getNbResa() {
        return nbResa;
    }

    public void setNbResa(int nbResa) {
        this.nbResa = nbResa;
    }

    public boolean isEstPerimee() {
        return estPerimee;
    }

    public void setEstPerimee(boolean estPerimee) {
        this.estPerimee = estPerimee;
    }

    public void nbResaPlus(){
        this.setNbResa(this.nbResa + 1);
    }

    public void nbResaMoins(){
        this.setNbResa(this.nbResa - 1);
    }
}
