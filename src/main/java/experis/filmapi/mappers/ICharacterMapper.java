package experis.filmapi.mappers;

import experis.filmapi.models.Character;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.character.CharacterDTO;
import experis.filmapi.models.dtos.movie.MovieDTO;
import experis.filmapi.services.MovieService;
import experis.filmapi.services.interfaces.IMovieService;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for mapping characters to characterDTOs and collections of the same.
 */

@Mapper(componentModel = "spring")
public interface ICharacterMapper {

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
