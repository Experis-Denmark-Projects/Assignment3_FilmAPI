package experis.filmapi.controllers;

import experis.filmapi.mappers.FranchiseDTOMapper;
import experis.filmapi.mappers.ICharacterMapper;
import experis.filmapi.mappers.IFranchiseMapper;
import experis.filmapi.mappers.IMovieMapper;
import experis.filmapi.models.Character;
import experis.filmapi.models.Franchise;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.character.CharacterDTO;
import experis.filmapi.models.dtos.franchise.AddFranchiseDTO;
import experis.filmapi.models.dtos.franchise.FranchiseDTO;
import experis.filmapi.models.dtos.movie.AddMovieDTO;
import experis.filmapi.models.dtos.movie.MovieDTO;
import experis.filmapi.services.interfaces.IFranchiseService;
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
 * Controller class for managing movie franchises.
 */
@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {
    private final IFranchiseService franchiseService;
    private final IFranchiseMapper franchiseMapper;
    private final ICharacterMapper characterMapper;
    private final IMovieMapper movieMapper;
    private final FranchiseDTOMapper franchiseDTOMapper;

    public FranchiseController(IFranchiseService franchiseService, IFranchiseMapper franchiseMapper, ICharacterMapper characterMapper, IMovieMapper movieMapper, FranchiseDTOMapper franchiseDTOMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
        this.characterMapper = characterMapper;
        this.movieMapper = movieMapper;
        this.franchiseDTOMapper = franchiseDTOMapper;
    }

    /**
     * Retrieve all movie franchises.
     *
     * @return A collection of franchise DTOs.
     */
    @GetMapping
    @Operation(summary = "Get all the movie franchises")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Franchises successfully retrieved",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FranchiseDTO.class)))
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
    public ResponseEntity<Collection<FranchiseDTO>> findAll(){
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDTO(franchiseService.findAll()));
    }

    /**
     * Retrieve a movie franchise by its ID.
     *
     * @param id The ID of the franchise to retrieve.
     * @return The franchise DTO if found, or a 404 response if not found.
     */
    @GetMapping("{id}")
    @Operation(summary = "Get a franchise by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Character successfully retrieved",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FranchiseDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Page Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class)))
    })
    public ResponseEntity<FranchiseDTO> findById(@PathVariable int id){
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDTO(franchiseService.findById(id)));
    }

    /**
     * Retrieve all movies from a franchise.
     *
     * @param franchiseId The ID of the franchise to get movies from.
     * @return A collection of movie DTOs from the franchise.
     */
    @GetMapping("{id}/movies")
    @Operation(summary = "Get all movies from a franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movies successfully retrieved",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Page Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class)))
    })
    public ResponseEntity<Collection<MovieDTO>> getMovies(@PathVariable int id){
        // Get Franchise from id.
        Franchise franchise = franchiseService.findById(id);
        // Get Movies from Franchise.
        Collection<Movie> movies = franchise.getMovies();
        // Convert Movies to MovieDTOs via Movie Mapper.
        Collection<MovieDTO> movieDTOS = movieMapper.movieToMovieDTO(movies);
        return ResponseEntity.ok(movieDTOS);
    }

    /**
     * Retrieve all characters from a franchise.
     *
     * @param id The ID of the franchise to retrieve characters from.
     * @return A collection of character DTOs from the franchise.
     */
    @GetMapping("{id}/characters")
    @Operation(summary = "Get all characters by franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Characters successfully retrieved",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class)))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Page Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class)))
    })
    public ResponseEntity<Collection<CharacterDTO>> findAllCharactersInFranchise(@PathVariable int id){
        Franchise franchise = franchiseService.findById(id);
        Collection<Character> characters = franchise.getCharacters();
        Collection<CharacterDTO> characterDTOS = characterMapper.characterToCharacterDTO(characters);
        return ResponseEntity.ok(characterDTOS);
    }

    /**
     * Update a franchise.
     *
     * @param franchise The updated franchise information.
     * @param id The ID of the franchise to update.
     * @return The updated franchise DTO if successful, or a 404 response if not found.
     */
    @PutMapping("{id}")
    @Operation(summary = "Update franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Franchise successfully retrieved",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FranchiseDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Page Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class)))
    })
    public ResponseEntity<FranchiseDTO> update(@RequestBody Franchise franchise, @PathVariable int id){
        if(id != franchise.getId()){
            return ResponseEntity.badRequest().build();
        }
        franchiseService.update(franchise);
        return ResponseEntity.noContent().build();
    }

    /**
     * Create a new franchise.
     *
     * @param addFranchiseDTO The DTO containing information for the new franchise.
     * @return A 201 response with the URI to the newly created franchise.
     * @throws URISyntaxException if the URI is malformed.
     */
    @PostMapping
    @Operation(summary = "Adds a new franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created new franchise",
                    content = @Content
            )
    })
    public ResponseEntity<AddFranchiseDTO> create(@RequestBody AddFranchiseDTO addFranchiseDTO) throws URISyntaxException {

        Franchise id  = franchiseService.create(franchiseDTOMapper.addFranchiseDTOToFranchise(addFranchiseDTO));

        URI uri = new URI(String.format("api/v1/characters/%s", id.getId()));

        return ResponseEntity.created(uri).build();
    }
}
