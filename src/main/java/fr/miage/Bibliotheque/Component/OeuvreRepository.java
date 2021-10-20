package fr.miage.Bibliotheque.Component;

import fr.miage.Bibliotheque.Entity.Oeuvre;
import fr.miage.Bibliotheque.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OeuvreRepository extends JpaRepository<Oeuvre, Long> {
}
