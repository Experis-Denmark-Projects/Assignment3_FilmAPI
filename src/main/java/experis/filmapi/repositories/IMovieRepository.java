package experis.filmapi.repositories;

import experis.filmapi.models.Character;
import experis.filmapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repository interface for managing movie data. Inherits from jpaRepositories
 * This repository allows for interacting with the postgres database by inheriting generic methods from its parent
 *  * And specifying the generic types to be Movie & Integer. Thus, Movie is the object used and Integer the id.
 */
@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer> {

    /**
     * Custom query to find all movies by a partial match of their title.
     *
     * @param title The partial title to search for.
     * @return A set of character objects matching the search criteria.
     */
    @Query("SELECT c FROM Movie c WHERE c.title LIKE %?1%")
    Set<Character> findAllByTitle(String title);
}
