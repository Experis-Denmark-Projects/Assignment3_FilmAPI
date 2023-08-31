package experis.filmapi.services.interfaces;

import experis.filmapi.models.Franchise;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IFranchiseService extends ICrudService<Franchise, Integer> {

    @Override
    Franchise create(Franchise franchise);

    @Override
    Franchise findById(Integer id);

    @Override
    Collection<Franchise> findAll();

    @Override
    Franchise update(Franchise franchise);

    Franchise updateMoviesInFranchise(int franchiseId, Integer[] arrayOfMovieIds);
}
