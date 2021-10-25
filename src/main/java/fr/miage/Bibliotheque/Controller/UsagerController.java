package fr.miage.Bibliotheque.Controller;

import com.sun.xml.bind.v2.model.core.ID;
import fr.miage.Bibliotheque.Component.UsagerRepository;
import fr.miage.Bibliotheque.Entity.Usager;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


import java.util.Optional;

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
    public String creerUsager(@ModelAttribute Usager Usager,  Model model){
        model.addAttribute("usager", new Usager());


        ur.save(Usager);

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
    public String modifierUsager(@ModelAttribute Usager Usager, Model model){

        /*Usager Usager = ur.findByIdUsager(idUsager);

        if(Usager != null) {
            Usager.setNom(nomUsager);

            ur.save(Usager2);


        }*/
        Usager Usager2save = ur.findByIdUsager(Usager.getNom());
        Usager2save.setNom(Usager.getNom());
        ur.save(Usager2save);

        Iterable<Usager> allUsagers = ur.findAll();
        model.addAttribute("usager", new Usager());
        model.addAttribute("usagers", allUsagers);
        return "usagers";
    }
}
