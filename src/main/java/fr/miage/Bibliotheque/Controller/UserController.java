package fr.miage.Bibliotheque.Controller;

import fr.miage.Bibliotheque.Component.UserRepository;
import fr.miage.Bibliotheque.Entity.User;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping(value = "/users")
@ExposesResourceFor(User.class)
public class UserController {

    private final UserRepository ur;

    public UserController(UserRepository ur) {
        this.ur = ur;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        //Model model;
        model.addAttribute("user", new User());
        Iterable<User> allUsers = ur.findAll();

        model.addAttribute("users", allUsers);

        return "users";
    }

    @GetMapping(value="/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") long id) {
        return Optional.ofNullable(ur.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(i.get()))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/create")
    public String createUser(@ModelAttribute User user,  Model model){
        model.addAttribute("user", new User());


        ur.save(user);

        Iterable<User> allUsers = ur.findAll();

        model.addAttribute("users", allUsers);

        return "users";
    }
}
