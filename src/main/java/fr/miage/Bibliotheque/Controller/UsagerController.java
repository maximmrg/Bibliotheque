package fr.miage.Bibliotheque.Controller;

import fr.miage.Bibliotheque.Component.UsagerRepository;
import fr.miage.Bibliotheque.Entity.Usager;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/usagers")
@ExposesResourceFor(Usager.class)
public class UsagerController {

    private final UsagerRepository ur;

    public UsagerController(UsagerRepository ur) {
        this.ur = ur;
    }

    @GetMapping
    public String getAllUsagers(Model model) {
        //Model model;
        model.addAttribute("usager", new Usager());
        Iterable<Usager> allUsagers = ur.findAll();

        model.addAttribute("usagers", allUsagers);

        return "usagers";
    }


    @PostMapping("/create")
    public String creerUsager(@ModelAttribute Usager usager,  Model model){
        model.addAttribute("usager", new Usager());

        Usager existUsager = ur.findByIdUsager(usager.getNom());

        if (existUsager == null)
            ur.save(usager);
        else
            model.addAttribute("errorMsg", "Erreur, ce nom d'utilisateur est déjà enregistré.");

        Iterable<Usager> allUsagers = ur.findAll();

        model.addAttribute("usagers", allUsagers);

        return "usagers";
    }

    @PostMapping("/toUpdate")
    public String toUpdateUsager(@RequestParam(value = "nomUsager") String nomUsager, Model model){

        Usager Usager = ur.findByIdUsager(nomUsager);


        if(Usager != null) {
            model.addAttribute("usager", Usager);
        }

        return "updateUsager";
    }

    @PostMapping("/update")
    public String modifierUsager(@ModelAttribute Usager usager, Model model){

        Usager Usager2save = ur.findByIdUsager(usager.getNom());

        Usager2save.setNom(usager.getNom());
        Usager2save.setPrenom(usager.getPrenom());
        ur.save(Usager2save);

        Iterable<Usager> allUsagers = ur.findAll();
        model.addAttribute("usager", new Usager());
        model.addAttribute("usagers", allUsagers);
        return "usagers";
    }
}
