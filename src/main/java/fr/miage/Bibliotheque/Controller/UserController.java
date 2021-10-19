package fr.miage.Bibliotheque.Controller;

import fr.miage.Bibliotheque.Component.UserRepository;
import fr.miage.Bibliotheque.Entity.User;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(User.class)
public class UserController {

    private final UserRepository ur;

    public UserController(UserRepository ur) {
        this.ur = ur;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        Iterable<User> allIntervenants = ur.findAll();
        return ResponseEntity.ok(allIntervenants);
    }

    @GetMapping(value="/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") long id) {
        return Optional.ofNullable(ur.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(i.get()))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        User user2Save = new User(user.getNom(), user.getPrenom());

        User saved = ur.save(user2Save);

        URI location = linkTo(UserRepository.class).slash(saved.getIdUser()).toUri();
        return ResponseEntity.created(location).build();
    }
}
