package experis.filmapi.controllers;

import experis.filmapi.mappers.ICharacterMapper;
import experis.filmapi.models.Character;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.character.CharacterDTO;
import experis.filmapi.services.interfaces.ICharacterService;
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
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    private final ICharacterService characterService;
    private final ICharacterMapper characterMapper;

    public CharacterController(ICharacterService characterService, ICharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @GetMapping
    @Operation(summary = "Gets all the characters")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Characters successfully retrieved",
                    content = {
                            @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class)))
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
    public ResponseEntity<Collection<CharacterDTO>> findAll(){
        return ResponseEntity.ok(characterMapper.characterToCharacterDTO(characterService.findAll()));
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a character by their ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Character successfully retrieved",
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
    public ResponseEntity<CharacterDTO> findById(@PathVariable int id){
        return ResponseEntity.ok(characterMapper.characterToCharacterDTO(characterService.findById(id)));
    }

    @GetMapping("{id}/movies")
    public ResponseEntity<Collection<Movie>> getMovies(@PathVariable int id){
        return ResponseEntity.ok(characterService.getMovies(id));
    }

    @PostMapping
    @Operation(summary = "Adds a new character")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "201",
                description = "Created new character",
                content = @Content
        )
    })
    public ResponseEntity<CharacterDTO> create(@RequestBody CharacterDTO characterDTO) throws URISyntaxException {
        URI uri = new URI(String.format("api/v1/characters/%s", 1));
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(path = "{id}")
    @Operation(summary = "Update a character")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Characters successfully retrieved",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Page Not Found",
                    content = @Content)
    })
    public ResponseEntity<CharacterDTO> update(@RequestBody Character character, @PathVariable int id){
        if(id != character.getId()){
            return ResponseEntity.badRequest().build();
        }
        characterService.update(character);
        return ResponseEntity.noContent().build();
    }
}
