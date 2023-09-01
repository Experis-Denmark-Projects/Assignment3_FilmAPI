package experis.filmapi.services.interfaces;

import experis.filmapi.models.Movie;

public interface IMovieService extends ICrudService<Movie, Integer>{

    Movie updateCharactersInMovie(int movieID, Integer[] charIds);
}
