package fr.miage.Bibliotheque.Controller;

import fr.miage.Bibliotheque.Component.EmpruntRepository;
import fr.miage.Bibliotheque.Component.ExemplaireRepository;
import fr.miage.Bibliotheque.Component.OeuvreRepository;
import fr.miage.Bibliotheque.Component.UserRepository;
import fr.miage.Bibliotheque.Entity.Emprunt;
import fr.miage.Bibliotheque.Entity.Exemplaire;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import fr.miage.Bibliotheque.Entity.User;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(value = "/emprunts")
@ExposesResourceFor(Emprunt.class)
public class EmpruntController {

    private final EmpruntRepository er;
    private final OeuvreRepository or;
    private final ExemplaireRepository exr;
    private final UserRepository ur;

    public EmpruntController(EmpruntRepository er, OeuvreRepository or, ExemplaireRepository exr, UserRepository ur) {
        this.er = er;
        this.or = or;
        this.exr = exr;
        this.ur = ur;
    }

    @GetMapping
    public String getAllEmprunts(Model model){
        model.addAttribute("emprunt", new Emprunt());

        Iterable<Emprunt> allEmprunts = er.getAllByEnCoursTrue();
        model.addAttribute("emprunts", allEmprunts);

        return "emprunts";
    }

    @PostMapping("/create")
    public String createEmprunt(@RequestParam(value = "nomUser") String nomUser, @RequestParam(value = "nomOeuvre") String nomOeuvre, Model model){
        Oeuvre oeuvre = or.findByNom(nomOeuvre).stream().findFirst().orElse(null);
        if(oeuvre != null) {
            Exemplaire exemplaire = exr.findByOeuvreAndAndDispo(oeuvre).stream().findFirst().orElse(null);

            User user = ur.findByNom(nomUser);

            if (user != null && exemplaire != null) {
                Emprunt emprunt = new Emprunt(new Date(), user, exemplaire);
                er.save(emprunt);

                exemplaire.setDispo(false);
                exr.save(exemplaire);
            }
        }

        Iterable<Emprunt> allEmprunts = er.getAllByEnCoursTrue();
        model.addAttribute("emprunts", allEmprunts);

        return "emprunts";
    }

    @PostMapping("/rendre")
    public String rendreExemplaire(@RequestParam(value = "idExemplaire") Long idExemplaire, @RequestParam(value = "idUser") Long idUser, Model model ){

        User user = ur.findByIdUser(idUser);
        Exemplaire exemplaire = exr.findById(idExemplaire).stream().findFirst().orElse(null);

        if(user != null && exemplaire != null){
            Emprunt emprunt = er.getEmpruntByUserAndExemplaire(user, exemplaire).stream().findFirst().orElse(null);

            if(emprunt != null){
                emprunt.setEnCours(false);
                er.save(emprunt);

                exemplaire.setDispo(true);
                exr.save(exemplaire);
            }
        }

        Iterable<Emprunt> allEmprunts = er.getAllByEnCoursTrue();
        model.addAttribute("emprunts", allEmprunts);

        return "emprunts";
    }
}
