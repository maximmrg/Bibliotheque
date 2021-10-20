package fr.miage.Bibliotheque.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data //create setters/getters, equals, toString method..
@AllArgsConstructor //Create constructors
@NoArgsConstructor //Create empty constructor
public class Emprunt implements Serializable {

    @Id
    @GeneratedValue
    private long idEmprunt;

    private Date dateEmprunt;

    @OneToOne
    private User user;

    @OneToOne
    private Exemplaire exemplaire;

    public Emprunt(Date dateEmprunt, User user, Exemplaire exemplaire) {
        this.dateEmprunt = dateEmprunt;
        this.user = user;
        this.exemplaire = exemplaire;
    }

    public long getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(long idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }
}
