package experis.filmapi.repositories;

import experis.filmapi.models.Character;
import experis.filmapi.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
/**
 * Repository interface for managing franchise data. Inherites from jpaRepositories
 */
@Repository
public interface IFranchiseRepository extends JpaRepository<Franchise, Integer> {

    @Query("SELECT c FROM Franchise c WHERE c.name LIKE %?1%")
    Set<Character> findAllByName(String name);

}
