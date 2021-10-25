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
public class Reservation implements Serializable {

    @Id
    @GeneratedValue
    private long idReservation;

    private Date dateReservation;
    private Boolean reservationEnCours = true;

    @OneToOne
    private Usager user;

    @OneToOne
    private Oeuvre oeuvre;

    public Reservation(Date dateReservation, Usager user, Oeuvre oeuvre) {
        this.dateReservation = dateReservation;
        this.user = user;
        this.oeuvre = oeuvre;
    }

    public long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(long idReservation) {
        this.idReservation = idReservation;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Boolean getReservationEnCours() {
        return reservationEnCours;
    }

    public void setReservationEnCours(Boolean reservationEnCours) {
        this.reservationEnCours = reservationEnCours;
    }

    public Usager getUser() {
        return user;
    }

    public void setUser(Usager user) {
        this.user = user;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }
}
