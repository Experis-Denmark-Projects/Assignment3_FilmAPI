package experis.filmapi.mappers;

import experis.filmapi.models.Character;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.character.AddCharacterDTO;
import experis.filmapi.models.dtos.character.CharacterDTO;
import experis.filmapi.services.interfaces.ICharacterService;
import experis.filmapi.services.interfaces.IMovieService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper file for mapping the DTO used for adding characters. It maps from a character to a AddCharacterDTO
 */

@Mapper(componentModel = "spring")
public abstract class CharacterDTOMapper {
    @Autowired
    protected IMovieService movieService;
    @Autowired
    ICharacterService characterService;

    @Named("characterIdToCharacter")
    public Character mapCharacterDTOTOCharacter(int id){
        return characterService.findById(id);
    }

    @Mapping(target = "name", source = "name")
    @Mapping(target = "franchise.id", source = "franchise")
    @Mapping(target = "gender", source = "gender")
    public abstract Character addCharacterDTOToCharacter(AddCharacterDTO addCharacterDTO);

    @Named("movieIdsToMovies")
    public Set<Movie> mapMovieIdsToMovies(Set<Integer> movieIds){
        return movieIds.stream().map(i -> movieService.findById(i)).collect(Collectors.toSet());
    }
}
