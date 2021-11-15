package fr.miage.Bibliotheque;

import fr.miage.Bibliotheque.Component.ExemplaireRepository;
import fr.miage.Bibliotheque.Component.OeuvreRepository;
import fr.miage.Bibliotheque.Component.UsagerRepository;
import fr.miage.Bibliotheque.Entity.Exemplaire;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import fr.miage.Bibliotheque.Entity.Usager;
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

	/*@Bean
	public CommandLineRunner demo(OeuvreRepository or, UsagerRepository ur, ExemplaireRepository exR) {
		return (args) -> {
			Oeuvre oeuvre = new Oeuvre("Harry Potter");
			or.save(oeuvre);

			oeuvre = or.findByNom("Harry Potter").stream().findFirst().orElse(null);

			Usager Usager = new Usager("Gobillard", "Tom");
			ur.save(Usager);

			Exemplaire exemplaire = new Exemplaire("Neuf", oeuvre);
			Exemplaire exemplaire2 = new Exemplaire("Neuf", oeuvre);

			exR.save(exemplaire);
			exR.save(exemplaire2);
		};
	}*/
}
