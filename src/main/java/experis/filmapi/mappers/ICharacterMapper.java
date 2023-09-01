package experis.filmapi.mappers;

import experis.filmapi.models.Character;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.character.CharacterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for mapping characters to characterDTOs and collections of the same.
 * A class called "ICharacterMapperImpl" that implements this interface is generated.
 */

@Mapper(componentModel = "spring")
public interface ICharacterMapper {

    // target refers to CharacterDTO and source is the character parameter.
    // qualifiedByName refers to the map method which converts a set of movies to a set of Integer movie ids.
    @Mapping(target = "franchise", source = "franchise.id")
    @Mapping(target = "movies", qualifiedByName = "moviesToMovieIds")
    CharacterDTO characterToCharacterDTO(Character character);

    Collection<CharacterDTO> characterToCharacterDTO(Collection<Character> characters);

    @Named(value = "moviesToMovieIds")
    default Set<Integer> map(Set<Movie> value){
        if(value == null) return null;
        return value.stream().map(Movie::getId).collect(Collectors.toSet());
    }
}
