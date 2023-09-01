package experis.filmapi.controllers;

import experis.filmapi.mappers.ICharacterMapper;
import experis.filmapi.mappers.IMovieMapper;
import experis.filmapi.mappers.MovieDTOMapper;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.character.CharacterDTO;
import experis.filmapi.models.dtos.movie.AddMovieDTO;
import experis.filmapi.models.dtos.movie.MovieDTO;
import experis.filmapi.services.interfaces.IMovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * Controller class for managing movies and their associated characters.
 */
@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    private final IMovieService movieService;
    private final IMovieMapper movieMapper;
    private final MovieDTOMapper movieDTOMapper;
    private final ICharacterMapper characterMapper;

    public MovieController(IMovieService movieService, IMovieMapper movieMapper, ICharacterMapper characterMapper, MovieDTOMapper movieDTOMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
        this.movieDTOMapper = movieDTOMapper;
    }

    /**
     * Retrieve all movies.
     *
     * @return A collection of movie DTOs.
     */
    @GetMapping
    @Operation(summary = "Gets all the movies")
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

    /**
     * Retrieve a movie by its ID.
     *
     * @param id The ID of the movie to retrieve.
     * @return The movie DTO if found, or a 404 response if not found.
     */
    @GetMapping("{id}")
    @Operation(summary = "Get a movie by their ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie successfully retrieved",
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
    public ResponseEntity<MovieDTO> findById(@PathVariable int id){
        return ResponseEntity.ok(movieMapper.movieToMovieDTO(movieService.findById(id)));
    }

    /**
     * Retrieve all characters from a movie by its ID.
     *
     * @param id The ID of the movie to retrieve characters from.
     * @return A collection of character DTOs associated with the movie.
     */
    @GetMapping("{id}/characters")
    @Operation(summary="Get all characters from a movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Characters successfully retrieved",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CharacterDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Page Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class)))
    })
    public ResponseEntity<Collection<CharacterDTO>> getCharacters(@PathVariable int id){
        return ResponseEntity.ok(characterMapper.characterToCharacterDTO(movieService.getCharacters(id)));
    }

    /**
     * Retrieve all movies in a franchise by the franchise's ID.
     *
     * @param id The ID of the franchise to retrieve movies from.
     * @return A collection of movie DTOs in the franchise.
     */
    @GetMapping("{id}/franchise")
    @Operation(summary = "Get all movies in a franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movies successfully retrieved",
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
    public ResponseEntity<Collection<MovieDTO>> getMoviesInFranchise(@PathVariable int id){
        Collection<MovieDTO> movies = movieMapper.movieToMovieDTO(movieService.getMoviesByFranchise(id));
        return ResponseEntity.ok(movies);
    }

    /**
     * Create a new movie.
     *
     * @param addMovieDTO The DTO containing information for the new movie.
     * @return A 201 response with the URI to the newly created movie.
     * @throws URISyntaxException if the URI is malformed.
     */
    @PostMapping
    @Operation(summary = "Adds a new movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created new movie",
                    content = @Content
            )
    })
    public ResponseEntity<AddMovieDTO> create(@RequestBody AddMovieDTO addMovieDTO) throws URISyntaxException {

        Movie id  = movieService.create(movieDTOMapper.addMovieDTOToMovie(addMovieDTO));

        URI uri = new URI(String.format("api/v1/characters/%s", id.getId()));

        return ResponseEntity.created(uri).build();
    }
}
