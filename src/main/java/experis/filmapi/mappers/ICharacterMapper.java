package experis.filmapi.mappers;

import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.character.CharacterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ICharacterMapper {

   // @Mapping(target = "id", source ="character.id")
    @Mapping(target = "CharacterDTO.movies", qualifiedByName = "moviesToMovieIds")
    CharacterDTO characterToCharacterDTO(Character character);

    Collection<CharacterDTO> characterToCharacterDTO(Collection<Character> characters);


    @Named("moviesToMovieIds")
    default Set<Integer> map(Set<Movie> value){
        if(value == null) return null;
        return value.stream().map(Movie::getId).collect(Collectors.toSet());
    }
}
