package experis.filmapi.services;

import experis.filmapi.Repository.CharacterRepository;
import experis.filmapi.Repository.MovieRepository;
import experis.filmapi.exceptions.CharacterNotFoundException;
import experis.filmapi.exceptions.MovieNotFoundException;
import experis.filmapi.models.Character;
import experis.filmapi.models.Movie;
import experis.filmapi.services.interfaces.IMovieService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;
    private final CharacterRepository characterRepository;

    public MovieService(MovieRepository movieRepository, CharacterRepository characterRepository) {
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
    }

    @Override
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateCharactersInMovie(int movieID, Integer[] arrayOfCharIds) {
        Movie movie = findById(movieID);

        Set<Character> charactersToUpdate = new HashSet<>();

        for (Integer charID : arrayOfCharIds) {
            Character character = characterRepository.findById(charID).orElseThrow(() -> new CharacterNotFoundException(charID));
            //characterRepository.save(character);
            charactersToUpdate.add(character);
        }

        movie.setCharacters(charactersToUpdate);

        return movieRepository.save(movie);
    }
}
