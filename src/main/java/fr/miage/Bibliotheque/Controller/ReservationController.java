package fr.miage.Bibliotheque.Controller;

import fr.miage.Bibliotheque.Component.OeuvreRepository;
import fr.miage.Bibliotheque.Component.ReservationRepository;
import fr.miage.Bibliotheque.Component.UsagerRepository;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import fr.miage.Bibliotheque.Entity.Reservation;
import fr.miage.Bibliotheque.Entity.Usager;
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
    private final UsagerRepository uR;

    public ReservationController(ReservationRepository resR, OeuvreRepository oR, UsagerRepository uR) {
        this.resR = resR;
        this.oR = oR;
        this.uR = uR;
    }

    public void init(Model model){
        Iterable<Reservation> allReservations = resR.getAllByEnCoursTrue();
        model.addAttribute("reservationsEnCours", allReservations);

        Iterable<Oeuvre> allOeuvres = oR.findAllByEstPerimeeFalse();
        model.addAttribute("oeuvres", allOeuvres);

        Iterable<Usager> allUsagers = uR.findAll();
        model.addAttribute("usagers", allUsagers);
    }

    @GetMapping
    public String getAllReservations(Model model){
        model.addAttribute("reservation", new Reservation());

        init(model);

        return "reservations";
    }

    @PostMapping("/create")
    public String reserver(@RequestParam(value = "nomUsager") String nomUsager, @RequestParam(value = "nomOeuvre") String nomOeuvre, Model model) {
        Usager Usager = uR.findByNom(nomUsager);
        Oeuvre oeuvre = oR.findByNom(nomOeuvre).stream().findFirst().orElse(null);

        if(Usager != null && oeuvre != null){
            oeuvre.nbResaPlus();
            Reservation reservation = new Reservation(new Date(), Usager, oeuvre);
            resR.save(reservation);
            oR.save(oeuvre);
        }

        init(model);

        return "reservations";
    }

    @PostMapping("/annuler")
    public String modifierReservation(@RequestParam(value = "idResa") Long idResa, Model model){
        Reservation reservation = resR.getById(idResa);

        reservation.setReservationEnCours(false);
        resR.save(reservation);

        Oeuvre oeuvre = reservation.getOeuvre();
        oeuvre.nbResaMoins();
        oR.save(oeuvre);

        init(model);

        return "reservations";
    }
}
