package fr.miage.Bibliotheque.Controller;

import fr.miage.Bibliotheque.Component.OeuvreRepository;
import fr.miage.Bibliotheque.Component.ReservationRepository;
import fr.miage.Bibliotheque.Component.UserRepository;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import fr.miage.Bibliotheque.Entity.Reservation;
import fr.miage.Bibliotheque.Entity.User;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping(value = "/reservations")
@ExposesResourceFor(Reservation.class)
public class ReservationController {

    private final ReservationRepository resR;
    private final OeuvreRepository oR;
    private final UserRepository uR;

    public ReservationController(ReservationRepository resR, OeuvreRepository oR, UserRepository uR) {
        this.resR = resR;
        this.oR = oR;
        this.uR = uR;
    }

    @GetMapping
    public String getAllReservations(Model model){
        model.addAttribute("reservation", new Reservation());

        Iterable<Reservation> allReservations = resR.findAll();
        model.addAttribute("reservations", allReservations);

        return "reservations";
    }

    @PostMapping("/create")
    public String createReservation(@RequestParam(value = "nomUser") String nomUser, @RequestParam(value = "nomOeuvre") String nomOeuvre, Model model) {
        User user = uR.findByNom(nomUser);
        Oeuvre oeuvre = oR.findByNom(nomOeuvre).stream().findFirst().orElse(null);

        if(user != null && oeuvre != null){
            oeuvre.nbResaPlus();
            Reservation reservation = new Reservation(new Date(), user, oeuvre);
            resR.save(reservation);
            oR.save(oeuvre);
        }

        Iterable<Reservation> allReservations = resR.findAll();
        model.addAttribute("reservations", allReservations);

        return "reservations";
    }

    @PostMapping("/annuler")
    public String annulerResa(@RequestParam(value = "idResa") Long idResa, Model model){
        Reservation reservation = resR.getById(idResa);

        reservation.setEnCours(false);
        resR.save(reservation);

        Oeuvre oeuvre = reservation.getOeuvre();
        oeuvre.nbResaMoins();
        oR.save(oeuvre);

        Iterable<Reservation> allReservations = resR.findAll();
        model.addAttribute("reservations", allReservations);

        return "reservations";
    }
}
