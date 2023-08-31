package experis.filmapi.Repository;

import experis.filmapi.models.Character;
import experis.filmapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT c FROM Movie c WHERE c.title LIKE %?1%")
    Set<Character> findAllByTitle(String title);
}
