package fr.miage.Bibliotheque.Component;

import fr.miage.Bibliotheque.Entity.Exemplaire;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {

    @Query("select e from Exemplaire e, Oeuvre  o where e.isDispo = true and e.oeuvre = o and o.nom = ?1")
    public Exemplaire findByOeuvre_NomAndAndDispo(String name);

    @Query("select e from Exemplaire e where e.isDispo = true and e.oeuvre = ?1")
    public Exemplaire findByOeuvreAndAndDispo(Oeuvre oeuvre);
}
