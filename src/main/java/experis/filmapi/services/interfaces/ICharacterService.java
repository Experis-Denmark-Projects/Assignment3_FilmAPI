package experis.filmapi.services.interfaces;

import experis.filmapi.models.Character;
import experis.filmapi.models.Movie;

import java.util.Collection;

public interface ICharacterService extends ICrudService<Character, Integer>{
    Collection<Movie> getMovies(int charId);
}
