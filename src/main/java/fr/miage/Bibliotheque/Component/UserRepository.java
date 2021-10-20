package fr.miage.Bibliotheque.Component;

import fr.miage.Bibliotheque.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.nom = ?1")
    public User findByNom(String name);
}
