package experis.filmapi.controllers;

import experis.filmapi.mappers.IFranchiseMapper;
import experis.filmapi.models.Franchise;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.character.CharacterDTO;
import experis.filmapi.models.dtos.franchise.FranchiseDTO;
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

// Go to this url: http://localhost:8080/swagger-ui/index.html#/

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {
    private final IFranchiseService franchiseService;
    private final IFranchiseMapper franchiseMapper;

    public FranchiseController(IFranchiseService franchiseService, IFranchiseMapper franchiseMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
    }

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

    public ResponseEntity<FranchiseDTO> getMovies(int franchiseId){
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDTO(franchiseService.findById(franchiseId)));
    }

    @PostMapping
    @Operation(summary = "Add a new franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created a new franchise",
                    content = @Content
            )
    })
    public ResponseEntity<FranchiseDTO> create (@RequestBody FranchiseDTO franchiseDTO) throws URISyntaxException{
        URI uri = new URI(String.format("api/v1/franchise/%s", 1));
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("{id}")
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
}
