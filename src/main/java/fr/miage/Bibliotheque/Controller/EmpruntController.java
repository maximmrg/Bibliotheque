package fr.miage.Bibliotheque.Controller;

import fr.miage.Bibliotheque.Component.EmpruntRepository;
import fr.miage.Bibliotheque.Component.ExemplaireRepository;
import fr.miage.Bibliotheque.Component.UserRepository;
import fr.miage.Bibliotheque.Entity.Emprunt;
import fr.miage.Bibliotheque.Entity.Exemplaire;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import fr.miage.Bibliotheque.Entity.User;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/emprunts")
@ExposesResourceFor(Emprunt.class)
public class EmpruntController {

    private final EmpruntRepository er;
    private final ExemplaireRepository exr;
    private final UserRepository ur;

    public EmpruntController(EmpruntRepository er, ExemplaireRepository exr, UserRepository ur) {
        this.er = er;
        this.exr = exr;
        this.ur = ur;
    }

    @GetMapping
    public String getAllEmprunts(Model model){
        model.addAttribute("emprunt", new Emprunt());

        Iterable<Emprunt> allEmprunts = er.findAll();
        model.addAttribute("emprunts", allEmprunts);

        return "emprunts";
    }

    @PostMapping("/create")
    public String createEmprunt(@RequestParam(value = "nomUser") String nomUser, @RequestParam(value = "nomOeuvre") String nomOeuvre, Model model){
        User user = ur.findByNom(nomUser);
        Exemplaire exemplaire = exr.findByOeuvre_NomAndAndDispo(nomOeuvre);

        return "emprunts";
    }
}
