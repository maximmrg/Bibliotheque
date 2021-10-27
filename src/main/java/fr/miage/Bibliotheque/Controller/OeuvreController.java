package fr.miage.Bibliotheque.Controller;

import fr.miage.Bibliotheque.Component.OeuvreRepository;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import fr.miage.Bibliotheque.Entity.Usager;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/oeuvres")
@ExposesResourceFor(Oeuvre.class)
public class OeuvreController {
    private final OeuvreRepository or;

    public OeuvreController(OeuvreRepository or) {
        this.or = or;
    }

    @GetMapping
    public String getAllOeuvres(Model model){
        model.addAttribute("oeuvre", new Oeuvre());

        Iterable<Oeuvre> allOeuvres = or.findAll();
        model.addAttribute("oeuvres", allOeuvres);

        return "oeuvres";
    }

    @PostMapping("/create")
    public String creerOeuvre(@ModelAttribute Oeuvre oeuvre, Model model){
        model.addAttribute("oeuvre", new Oeuvre());

        if(!oeuvre.getNomOeuvre().isEmpty())
            or.save(oeuvre);

        Iterable<Oeuvre> allOeuvres = or.findAll();

        model.addAttribute("oeuvres", allOeuvres);

        return "oeuvres";
    }

    @PostMapping("/retirer")
    public String retirerOeuvre(@RequestParam(value = "nomOeuvre") String nomOeuvre, Model model){
        model.addAttribute("oeuvre", new Oeuvre());

        Oeuvre oeuvre2delete = or.findByNom(nomOeuvre).stream().findFirst().orElse(null);

        if(oeuvre2delete != null){
            oeuvre2delete.setEstPerimee(true);
            or.save(oeuvre2delete);
        }

        Iterable<Oeuvre> allOeuvres = or.findAll();
        model.addAttribute("oeuvres", allOeuvres);

        return "oeuvres";
    }
}
