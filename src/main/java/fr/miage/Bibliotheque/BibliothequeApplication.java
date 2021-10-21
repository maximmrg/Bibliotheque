package fr.miage.Bibliotheque;

import fr.miage.Bibliotheque.Component.OeuvreRepository;
import fr.miage.Bibliotheque.Component.UserRepository;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import fr.miage.Bibliotheque.Entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BibliothequeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliothequeApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(OeuvreRepository or, UserRepository ur) {
		return (args) -> {
			Oeuvre oeuvre = new Oeuvre("Harry Potter");
			or.save(oeuvre);

			User user = new User("Tom", "Gobillard");
			ur.save(user);
		};
	}
}
