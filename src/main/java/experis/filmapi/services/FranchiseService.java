package experis.filmapi.services;

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

@Service
public class FranchiseService implements IFranchiseService {

    private final IFranchiseRepository franchiseRepository;
    private final IMovieRepository movieRepository;

    public FranchiseService(IFranchiseRepository IFranchiseRepository, IMovieRepository IMovieRepository) {
        this.franchiseRepository = IFranchiseRepository;
        this.movieRepository = IMovieRepository;
    }


    @Override
    public Franchise create(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    @Override
    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).orElseThrow(() -> new FranchiseNotFoundException(id));
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise update(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    @Override
    public void deleteById(Integer id) {
        franchiseRepository.deleteById(id);
    }

    @Override
    public Franchise updateMoviesInFranchise(int franchiseId, Integer[] movieIds) {
        Franchise franchise = findById(franchiseId);

        Set<Movie> moviesToUpdate = franchise.getMovies();

        for (Integer movieId : movieIds) {
            Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
            if(movie != null) {
                moviesToUpdate.add(movie);
            }
        }

        franchise.setMovies(moviesToUpdate);

        return franchiseRepository.save(franchise);
    }
}
