package experis.filmapi.controllers;

import experis.filmapi.mappers.CharacterDTOMapper;
import experis.filmapi.mappers.ICharacterMapper;
import experis.filmapi.models.Character;
import experis.filmapi.models.dtos.character.AddCharacterDTO;
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

/**
 * Controller class for managing movie characters.
 */
@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    private final ICharacterService characterService;
    private final ICharacterMapper characterMapper;
    private final CharacterDTOMapper characterDTOMapper;

    public CharacterController(ICharacterService characterService, ICharacterMapper characterMapper,
                               CharacterDTOMapper characterDTOMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
        this.characterDTOMapper = characterDTOMapper;
    }

    /**
     * Retrieve all characters.
     *
     * @return A collection of character DTOs.
     */
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

    /**
     * Retrieve a character by their ID.
     *
     * @param id The ID of the character to retrieve.
     * @return The character DTO if found, or a 404 response if not found.
     */
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

    /**
     * Create a new character.
     *
     * @param addCharacterDTO The DTO containing information for the new character.
     * @return A 201 response with the URI to the newly created character.
     * @throws URISyntaxException if the URI is malformed.
     */
    @PostMapping
    @Operation(summary = "Adds a new character")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created new character",
                    content = @Content
            )
    })
    public ResponseEntity<AddCharacterDTO> create(@RequestBody AddCharacterDTO addCharacterDTO) throws URISyntaxException {

        Character id  = characterService.create(characterDTOMapper.addCharacterDTOToCharacter(addCharacterDTO));

        URI uri = new URI(String.format("api/v1/characters/%s", id.getId()));

        return ResponseEntity.created(uri).build();
    }

    /**
     * Update a character.
     *
     * @param character The updated character information.
     * @param id The ID of the character to update.
     * @return The updated character DTO if successful, or a 404 response if not found.
     */
    @PutMapping(path = "{id}")
    @Operation(summary = "Update a character")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Character successfully retrieved",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Page Not Found",
                    content = @Content),
            @ApiResponse(
                    responseCode = "500",
                    description = "DID YOU ASSUME MY GENDER?!",
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
