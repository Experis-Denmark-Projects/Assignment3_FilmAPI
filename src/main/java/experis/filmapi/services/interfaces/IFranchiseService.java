package experis.filmapi.services.interfaces;

import experis.filmapi.models.Franchise;

public interface IFranchiseService extends ICrudService<Franchise, Integer> {

    Franchise updateMoviesInFranchise(int franchiseId, Integer[] movieIds);
}
