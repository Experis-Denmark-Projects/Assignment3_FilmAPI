package experis.filmapi.services;

import experis.filmapi.Repository.FranchiseRepository;
import experis.filmapi.Repository.MovieRepository;
import experis.filmapi.exceptions.CharacterNotFoundException;
import experis.filmapi.exceptions.FranchiseNotFoundException;
import experis.filmapi.exceptions.MovieNotFoundException;
import experis.filmapi.models.Character;
import experis.filmapi.models.Franchise;
import experis.filmapi.models.Movie;
import experis.filmapi.services.interfaces.IFranchiseService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class FranchiseService implements IFranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final MovieRepository movieRepository;

    public FranchiseService(FranchiseRepository franchiseRepository, MovieRepository movieRepository) {
        this.franchiseRepository = franchiseRepository;
        this.movieRepository = movieRepository;
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
    public Franchise updateMoviesInFranchise(int franchiseId, Integer[] arrayOfMovieIds) {
        Franchise franchise = findById(franchiseId);

        Set<Movie> moviesToUpdate = new HashSet<>();

        for (Integer charID : arrayOfMovieIds) {
            Movie movie = movieRepository.findById(charID).orElseThrow(() -> new MovieNotFoundException(charID));
            //characterRepository.save(character);
            moviesToUpdate.add(movie);
        }


        franchise.setMovies(moviesToUpdate);

        return franchiseRepository.save(franchise);
    }
}
