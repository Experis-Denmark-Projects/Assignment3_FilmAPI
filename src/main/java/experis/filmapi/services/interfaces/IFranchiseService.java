package experis.filmapi.services.interfaces;

import experis.filmapi.models.Franchise;

/**
 * This is interface for franchise that inherit from the CRUD
 */
public interface IFranchiseService extends ICrudService<Franchise, Integer> {

   Franchise updateMoviesInFranchise(int franchiseId, Integer[] movieIds);
}
