package fr.miage.Bibliotheque.Component;

import fr.miage.Bibliotheque.Entity.Emprunt;
import fr.miage.Bibliotheque.Entity.Exemplaire;
import fr.miage.Bibliotheque.Entity.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    @Query("select e from Emprunt e where e.enCours=true")
    public List<Emprunt> getAllByEnCoursTrue();

    @Query("select e from Emprunt e where e.usager = ?1 and e.exemplaire = ?2")
    public List<Emprunt> getEmpruntByUsagerAndExemplaire(Usager Usager, Exemplaire exemplaire);

}

