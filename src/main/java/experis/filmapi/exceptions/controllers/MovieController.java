package experis.filmapi.exceptions.controllers;

import experis.filmapi.mappers.IMovieMapper;
import experis.filmapi.services.interfaces.IMovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    private final IMovieService movieService;

    private final IMovieMapper movieMapper;

    public MovieController(IMovieService movieService, IMovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }
}
