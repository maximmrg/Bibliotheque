package fr.miage.Bibliotheque.Controller;

import fr.miage.Bibliotheque.Component.ExemplaireRepository;
import fr.miage.Bibliotheque.Component.OeuvreRepository;
import fr.miage.Bibliotheque.Entity.Exemplaire;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/exemplaires")
@ExposesResourceFor(Exemplaire.class)
public class ExemplaireController {

    private final ExemplaireRepository er;
    private final OeuvreRepository or;

    public ExemplaireController(ExemplaireRepository er, OeuvreRepository or) {
        this.er = er;
        this.or = or;
    }

    public void init(Model model){
        Iterable<Exemplaire> exemplairesNeufs = er.findAllByEtatIsNeuf();
        model.addAttribute("exemplaires", exemplairesNeufs);

        Iterable<Exemplaire> exemplairesUses = er.findAllByEtatIsUse();
        model.addAttribute("exemplairesUses", exemplairesUses);

        Iterable<Oeuvre> allOeuvres = or.findAll();
        model.addAttribute("oeuvres", allOeuvres);

        Iterable<Oeuvre> allOeuvresDispo = or.findAllByEstPerimeeFalse();
        model.addAttribute("oeuvresDispo", allOeuvresDispo);
    }

    @GetMapping
    public String getAllExemplaires(Model model){
        //model.addAttribute("exemplaire", new Exemplaire());

        init(model);

        return "exemplaires";
    }

    @PostMapping("/create")
    public String ajouterExemplaire(@RequestParam(value = "nomOeuvre") String nomOeuvre, Model model){
        Oeuvre oeuvre = or.findByNom(nomOeuvre).stream().findFirst().orElse(null);

        if(oeuvre != null) {
            Exemplaire exemplaire = new Exemplaire(oeuvre);

            er.save(exemplaire);
        }

        init(model);

        return "exemplaires";
    }

    @PostMapping("/update")
    public String modifierExemplaire(@RequestParam(value = "idExemplaire") Long idExemplaire, Model model){

        Exemplaire exemplaire = er.findById(idExemplaire).orElse(null);

        exemplaire.setEtat("Usé");
        er.save(exemplaire);

        init(model);

        return "exemplaires";
    }
}
