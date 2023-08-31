package experis.filmapi.services.interfaces;

import experis.filmapi.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface IMovieService extends ICrudService<Movie, Integer>{

    @Override
    Movie create(Movie movie);

    @Override
    Movie findById(Integer id);

    @Override
    Collection<Movie> findAll();

    @Override
    Movie update(Movie movie);


    Movie updateCharactersInMovie(int movieID, Integer[] arrayOfCharIds);
}
