package fr.miage.Bibliotheque.Controller;

import fr.miage.Bibliotheque.Component.ExemplaireRepository;
import fr.miage.Bibliotheque.Component.OeuvreRepository;
import fr.miage.Bibliotheque.Entity.Exemplaire;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    public String getAllExemplaires(Model model){
        model.addAttribute("exemplaire", new Exemplaire());

        Iterable<Exemplaire> allExemplaires = er.findAll();
        model.addAttribute("exemplaires", allExemplaires);

        model.addAttribute("oeuvre", new Oeuvre());

        Iterable<Oeuvre> allOeuvres = or.findAll();
        model.addAttribute("oeuvres", allOeuvres);

        return "exemplaires";
    }

    @PostMapping("/create")
    public String cerateExemplaire(@ModelAttribute Exemplaire exemplaire, Model model){
        model.addAttribute("exemplaire", new Exemplaire());
        if(exemplaire.getOeuvre() != null && !exemplaire.getEtat().isEmpty()){
            if(exemplaire.getOeuvre().getIdOeuvre()!=-1)
            er.save(exemplaire);

        }

        Iterable<Exemplaire> allExemplaires = er.findAll();

        model.addAttribute("exemplaires", allExemplaires);
        Iterable<Oeuvre> allOeuvres = or.findAll();
        model.addAttribute("oeuvres", allOeuvres);

        return "exemplaires";
    }
}
