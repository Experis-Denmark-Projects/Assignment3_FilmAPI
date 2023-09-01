package experis.filmapi.services.interfaces;

import experis.filmapi.models.Character;
import experis.filmapi.models.Movie;

import java.util.Collection;
/**
 * This is interface for movies that inherit from the CRUD. It has the specific
 * methods getCharacters and getMoviesByFranchise method which are specific to movies.
 */
public interface IMovieService extends ICrudService<Movie, Integer>{


    Movie updateCharactersInMovie(int movieID, Integer[] charIds);

    Collection<Character> getCharacters(int movieId);

     Collection<Movie> getMoviesByFranchise(int franchiseId);
}
