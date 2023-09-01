package experis.filmapi.repositories;

import experis.filmapi.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Set;
/**
 * Repository interface for managing character data. Inherits from jpaRepositories
 * This repository allows for interacting with the postgres database by inheriting generic methods from its parent
 * And specifying the generic types to be Character & Integer. Thus, Character is the object used and Integer the id.
 */
@Repository
public interface ICharacterRepository extends JpaRepository<Character, Integer> {

    @Query("SELECT c FROM Character c WHERE c.name LIKE %?1%")
    Set<Character> findAllByName(String name);
}
