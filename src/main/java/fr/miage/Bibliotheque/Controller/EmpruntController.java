package fr.miage.Bibliotheque.Controller;

import fr.miage.Bibliotheque.Component.EmpruntRepository;
import fr.miage.Bibliotheque.Component.ExemplaireRepository;
import fr.miage.Bibliotheque.Component.OeuvreRepository;
import fr.miage.Bibliotheque.Component.UsagerRepository;
import fr.miage.Bibliotheque.Entity.Emprunt;
import fr.miage.Bibliotheque.Entity.Exemplaire;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import fr.miage.Bibliotheque.Entity.Usager;
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
    private final UsagerRepository ur;

    public EmpruntController(EmpruntRepository er, OeuvreRepository or, ExemplaireRepository exr, UsagerRepository ur) {
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

        Iterable<Oeuvre> allOeuvres = or.findAll();
        model.addAttribute("oeuvres", allOeuvres);

        Iterable<Oeuvre> allOeuvresDispo = or.findAllByEstPerimeeFalse();
        model.addAttribute("oeuvresDispo", allOeuvresDispo);

        Iterable<Usager> allUsagers = ur.findAll();
        model.addAttribute("usagers", allUsagers);

        return "emprunts";
    }

    @PostMapping("/create")
    public String creerEmprunt(@RequestParam(value = "nomUsager") String nomUsager, @RequestParam(value = "nomOeuvre") String nomOeuvre, Model model){
        Oeuvre oeuvre = or.findByNom(nomOeuvre).stream().findFirst().orElse(null);
        if(oeuvre != null) {
            Exemplaire exemplaire = exr.findByOeuvreAndDispoAndAndEtatIsNeuf(oeuvre).stream().findFirst().orElse(null);

            Usager Usager = ur.findByNom(nomUsager);

            if (Usager != null && exemplaire != null) {
                Emprunt emprunt = new Emprunt(new Date(), Usager, exemplaire);
                er.save(emprunt);

                exemplaire.setEstDispo(false);
                exr.save(exemplaire);
            }
        }

        Iterable<Emprunt> allEmprunts = er.getAllByEnCoursTrue();
        model.addAttribute("emprunts", allEmprunts);

        Iterable<Oeuvre> allOeuvres = or.findAll();
        model.addAttribute("oeuvres", allOeuvres);

        Iterable<Oeuvre> allOeuvresDispo = or.findAllByEstPerimeeFalse();
        model.addAttribute("oeuvresDispo", allOeuvresDispo);

        Iterable<Usager> allUsagers = ur.findAll();
        model.addAttribute("usagers", allUsagers);

        return "emprunts";
    }

    @PostMapping("/rendre")
    public String rendreExemplaire(@RequestParam(value = "idExemplaire") Long idExemplaire, @RequestParam(value = "nomUsager") String nomUsager, Model model ){

        Usager Usager = ur.findByIdUsager(nomUsager);
        Exemplaire exemplaire = exr.findById(idExemplaire).stream().findFirst().orElse(null);

        if(Usager != null && exemplaire != null){
            Emprunt emprunt = er.getEmpruntByUsagerAndExemplaire(Usager, exemplaire).stream().findFirst().orElse(null);

            if(emprunt != null){
                emprunt.setEnCours(false);
                er.save(emprunt);

                exemplaire.setEstDispo(true);
                exr.save(exemplaire);
            }
        }

        Iterable<Emprunt> allEmprunts = er.getAllByEnCoursTrue();
        model.addAttribute("emprunts", allEmprunts);

        Iterable<Oeuvre> allOeuvres = or.findAll();
        model.addAttribute("oeuvres", allOeuvres);

        Iterable<Oeuvre> allOeuvresDispo = or.findAllByEstPerimeeFalse();
        model.addAttribute("oeuvresDispo", allOeuvresDispo);

        Iterable<Usager> allUsagers = ur.findAll();
        model.addAttribute("usagers", allUsagers);

        return "emprunts";
    }
}
