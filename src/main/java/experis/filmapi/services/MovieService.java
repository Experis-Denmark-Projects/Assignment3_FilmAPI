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

/**
 * Service class responsible for handling operations related to movies and movie characters.
 */
@Service
public class MovieService implements IMovieService {

    private final IMovieRepository movieRepository;
    private final ICharacterRepository characterRepository;

    /**
     * Constructor to initialize the MovieService with instances of IMovieRepository and ICharacterRepository.
     *
     * @param movieRepository    An implementation of the movie repository.
     * @param characterRepository An implementation of the character repository.
     */
    public MovieService(IMovieRepository movieRepository, ICharacterRepository characterRepository) {
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
    }

    /**
     * Creates a new movie record.
     *
     * @param movie The movie object to be created.
     * @return The created movie object.
     */
    @Override
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * Finds a movie by its unique identifier.
     *
     * @param id The unique identifier of the movie to find.
     * @return The movie object if found, or throws a MovieNotFoundException.
     * @throws MovieNotFoundException If no movie with the given ID is found.
     */
    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    /**
     * Retrieves a collection of all movie records.
     *
     * @return A collection of movie objects.
     */
    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * Updates an existing movie record.
     *
     * @param movie The movie object to be updated.
     * @return The updated movie object.
     */
    @Override
    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * Deletes a movie by its unique identifier.
     *
     * @param id The unique identifier of the movie to delete.
     */
    @Override
    public void deleteById(Integer id) {
        movieRepository.deleteById(id);
    }

    /**
     * Updates the list of characters associated with a movie.
     *
     * @param movieID The unique identifier of the movie to update.
     * @param charIds An array of character IDs to associate with the movie.
     * @return The updated movie object.
     * @throws CharacterNotFoundException If any of the provided character IDs is not found.
     */
    @Override
    public Movie updateCharactersInMovie(int movieID, Integer[] charIds) {
        Movie movie = findById(movieID);
        Set<Character> charactersToUpdate = new HashSet<>();

        for (Integer charId : charIds) {
            Character character = characterRepository.findById(charId).orElseThrow(() -> new CharacterNotFoundException(charId));
            if (character != null) {
                charactersToUpdate.add(character);
            }
        }

        movie.setCharacters(charactersToUpdate);

        return movieRepository.save(movie);
    }

    /**
     * Retrieves a collection of characters associated with a movie.
     *
     * @param movieId The unique identifier of the movie.
     * @return A collection of character objects associated with the movie.
     */
    @Override
    public Collection<Character> getCharacters(int movieId) {
        return findById(movieId).getCharacters();
    }

    /**
     * Retrieves a collection of movies belonging to a franchise.
     *
     * @param franchiseId The unique identifier of the franchise.
     * @return A collection of movie objects belonging to the franchise.
     */
    @Override
    public Collection<Movie> getMoviesByFranchise(int franchiseId) {
        return findById(franchiseId).getFranchise().getMovies();
    }
}
