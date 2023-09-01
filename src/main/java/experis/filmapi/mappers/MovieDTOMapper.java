package experis.filmapi.mappers;

import experis.filmapi.models.Character;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.movie.AddMovieDTO;
import experis.filmapi.services.interfaces.ICharacterService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper file for mapping the DTO used for adding movies. It maps from a movie to a AddMovieDTO
 */

@Mapper(componentModel = "spring")
public abstract class MovieDTOMapper {

    // This ICharacterService is used in the process of converting a set of Movie ids to a set of Movies.
    @Autowired
    protected ICharacterService characterService;

    // target refers to the Movie object being mapped to and source is the addMovieDTO object in the parameter.
    // qualifiedByName refers to the method mapCharacterIdsToCharacters which converts a set of CharacterIds to a set of Characters.
    @Mapping(target = "title", source = "title")
    @Mapping(target = "characters", qualifiedByName = "characterIdsToCharacters")
    @Mapping(target = "franchise.id", source = "franchise")
    public abstract Movie addMovieDTOToMovie(AddMovieDTO addMovieDTO);

    // This method uses the character service to get the character by using findById and passing the character id as an argument.
    @Named("characterIdsToCharacters")
    public Set<Character> mapCharacterIdsToCharacters(Set<Integer> characterIds){
        return characterIds.stream().map(characterService::findById).collect(Collectors.toSet());
    }
}
