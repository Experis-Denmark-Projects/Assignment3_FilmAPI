package experis.filmapi.services;

import experis.filmapi.models.Character;
import experis.filmapi.repositories.IFranchiseRepository;
import experis.filmapi.repositories.IMovieRepository;
import experis.filmapi.exceptions.FranchiseNotFoundException;
import experis.filmapi.exceptions.MovieNotFoundException;
import experis.filmapi.models.Franchise;
import experis.filmapi.models.Movie;
import experis.filmapi.services.interfaces.IFranchiseService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Service class responsible for handling operations related to movie franchises.
 */
@Service
public class FranchiseService implements IFranchiseService {

    private final IFranchiseRepository franchiseRepository;
    private final IMovieRepository movieRepository;

    /**
     * Constructor to initialize the FranchiseService with instances of IFranchiseRepository and IMovieRepository.
     *
     * @param franchiseRepository An implementation of the franchise repository.
     * @param movieRepository     An implementation of the movie repository.
     */
    public FranchiseService(IFranchiseRepository franchiseRepository, IMovieRepository movieRepository) {
        this.franchiseRepository = franchiseRepository;
        this.movieRepository = movieRepository;
    }

    /**
     * Creates a new franchise record.
     *
     * @param franchise The franchise object to be created.
     * @return The created franchise object.
     */
    @Override
    public Franchise create(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    /**
     * Finds a franchise by its unique identifier.
     *
     * @param id The unique identifier of the franchise to find.
     * @return The franchise object if found, or throws a FranchiseNotFoundException.
     * @throws FranchiseNotFoundException If no franchise with the given ID is found.
     */
    @Override
    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).orElseThrow(() -> new FranchiseNotFoundException(id));
    }

    /**
     * Retrieves a collection of all franchise records.
     *
     * @return A collection of franchise objects.
     */
    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    /**
     * Updates an existing franchise record.
     *
     * @param franchise The franchise object to be updated.
     * @return The updated franchise object.
     */
    @Override
    public Franchise update(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    /**
     * Deletes a franchise by its unique identifier.
     *
     * @param id The unique identifier of the franchise to delete.
     */
    @Override
    public void deleteById(Integer id) {
        franchiseRepository.deleteById(id);
    }

    /**
     * Updates the list of movies associated with a franchise.
     *
     * @param franchiseId The unique identifier of the franchise to update.
     * @param movieIds    An array of movie IDs to associate with the franchise.
     * @return The updated franchise object.
     * @throws MovieNotFoundException If any of the provided movie IDs is not found.
     */
    @Override
    public Franchise updateMoviesInFranchise(int franchiseId, Integer[] movieIds) {
        Franchise franchise = findById(franchiseId);

        Set<Movie> moviesToUpdate = new HashSet<>();

        for (Integer movieId : movieIds) {
            Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
            if (movie != null) {
                moviesToUpdate.add(movie);
            }
        }

        franchise.setMovies(moviesToUpdate);

        return franchiseRepository.save(franchise);
    }
}
