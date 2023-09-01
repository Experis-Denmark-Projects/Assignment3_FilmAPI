package experis.filmapi.exceptions.controllers;

import experis.filmapi.mappers.IMovieMapper;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.character.CharacterDTO;
import experis.filmapi.models.dtos.movie.MovieDTO;
import experis.filmapi.services.interfaces.IMovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

// Go to this url: http://localhost:8080/swagger-ui/index.html#/

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    private final IMovieService movieService;

    private final IMovieMapper movieMapper;

    public MovieController(IMovieService movieService, IMovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    @Operation(summary = "Gets all the characters")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movies successfully retrieved",
                    content = {
                            @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Page Not Found",
                    content = @Content)
    })
    public ResponseEntity<Collection<MovieDTO>> findAll(){
        return ResponseEntity.ok(movieMapper.movieToMovieDTO(movieService.findAll()));
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a movie by their ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Character successfully retrieved",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MovieDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Page Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)))
    })
    public ResponseEntity<Movie> findById(@PathVariable int id){
        return ResponseEntity.ok(movieService.findById(id));
    }
}
