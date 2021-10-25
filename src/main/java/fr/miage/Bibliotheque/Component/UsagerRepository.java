package fr.miage.Bibliotheque.Component;

import fr.miage.Bibliotheque.Entity.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsagerRepository extends JpaRepository<Usager, Long> {

    @Query("select u from Usager u where u.nom = ?1")
    public Usager findByNom(String name);

    @Query("select u from Usager u where u.nom = ?1 ")
    public Usager findByIdUsager(String nomUsager);
}
