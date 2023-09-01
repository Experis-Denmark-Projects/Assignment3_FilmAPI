package experis.filmapi.repositories;

import experis.filmapi.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface ICharacterRepository extends JpaRepository<Character, Integer> {

    @Query("SELECT c FROM Character c WHERE c.name LIKE %?1%")
    Set<Character> findAllByName(String name);
}
