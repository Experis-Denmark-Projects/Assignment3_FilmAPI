package experis.filmapi.exceptions.controllers;

import experis.filmapi.mappers.ICharacterMapper;
import experis.filmapi.models.Character;
import experis.filmapi.models.dtos.character.CharacterDTO;
import experis.filmapi.services.interfaces.ICharacterService;
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
    public ResponseEntity<Character> findById(@PathVariable int id){
        return ResponseEntity.ok(characterService.findById(id));
    }
}
