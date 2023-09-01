package experis.filmapi.services.interfaces;

import experis.filmapi.models.Character;
import experis.filmapi.models.Movie;

import java.util.Collection;

public interface IMovieService extends ICrudService<Movie, Integer>{

    Movie updateCharactersInMovie(int movieID, Integer[] charIds);

    Collection<Character> getCharacters(int movieId);

     Collection<Movie> getMoviesByFranchise(int franchiseId);
}
