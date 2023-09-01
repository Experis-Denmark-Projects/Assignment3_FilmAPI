package experis.filmapi.services;

import experis.filmapi.repositories.ICharacterRepository;
import experis.filmapi.repositories.IMovieRepository;
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

    private final IMovieRepository movieRepository;
    private final ICharacterRepository characterRepository;

    public MovieService(IMovieRepository IMovieRepository, ICharacterRepository ICharacterRepository) {
        this.movieRepository = IMovieRepository;
        this.characterRepository = ICharacterRepository;
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
    public void deleteById(Integer id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateCharactersInMovie(int movieID, Integer[] charIds) {
        Movie movie = findById(movieID);
        Set<Character> charactersToUpdate = new HashSet<>();

        for (Integer charId : charIds) {
            Character character = characterRepository.findById(charId).orElseThrow(() -> new CharacterNotFoundException(charId));
            if(character != null) {
                charactersToUpdate.add(character);
            }
        }

        movie.setCharacters(charactersToUpdate);

        return movieRepository.save(movie);
    }

    @Override
    public Collection<Character> getCharacters(int movieId){
        return findById(movieId).getCharacters();
    }

    @Override
    public Collection<Movie> getMoviesByFranchise(int franchiseId){
        return findById(franchiseId).getFranchise().getMovies();
    }
}
