package fr.miage.Bibliotheque.Component;

import fr.miage.Bibliotheque.Entity.Oeuvre;
import fr.miage.Bibliotheque.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OeuvreRepository extends JpaRepository<Oeuvre, Long>{

    @Query("select o from Oeuvre o where o.nom = ?1")
    public List<Oeuvre> findByNom(String name);


}
