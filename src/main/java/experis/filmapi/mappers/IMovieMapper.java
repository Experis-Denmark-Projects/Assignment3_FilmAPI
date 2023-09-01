package experis.filmapi.mappers;

import experis.filmapi.models.Movie;
import experis.filmapi.models.Character;
import experis.filmapi.models.dtos.character.AddCharacterDTO;
import experis.filmapi.models.dtos.movie.AddMovieDTO;
import experis.filmapi.models.dtos.movie.MovieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for mapping movies to movieDTOs and collections of the same.
 */

@Mapper(componentModel = "spring")
public interface IMovieMapper {



    @Mapping(target = "franchise", source = "franchise.id")
    @Mapping(target = "characters", qualifiedByName = "charactersToCharacterIds")
    MovieDTO movieToMovieDTO(Movie movie);

    Collection<MovieDTO> movieToMovieDTO(Collection<Movie> movies);

    @Named(value = "charactersToCharacterIds")
    default Set<Integer> map(Set<Character> value){
        if(value == null) return null;
        return value.stream().map(Character::getId).collect(Collectors.toSet());
    }

}
