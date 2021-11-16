package fr.miage.Bibliotheque.Component;

import fr.miage.Bibliotheque.Entity.Exemplaire;
import fr.miage.Bibliotheque.Entity.Oeuvre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {

    @Query("select e from Exemplaire e, Oeuvre  o where e.estDispo = true and e.oeuvre = o and o.nomOeuvre = ?1")
    public List<Exemplaire> findByOeuvre_NomAndAndDispo(String name);

    @Query("select e from Exemplaire e where e.estDispo = true and e.oeuvre = ?1")
    public List<Exemplaire> findByOeuvreAndAndDispo(Oeuvre oeuvre);

    @Query("select e from Exemplaire e where e.estDispo = true and e.etat ='Neuf' and e.oeuvre = ?1")
    public List<Exemplaire> findByOeuvreAndDispoAndAndEtatIsNeuf(Oeuvre oeuvre);

    @Query("select  e from  Exemplaire e where e.etat = 'Neuf'")
    public List<Exemplaire> findAllByEtatIsNeuf();

    @Query("select  e from  Exemplaire e where e.etat = 'Usé'")
    public List<Exemplaire> findAllByEtatIsUse();
}
