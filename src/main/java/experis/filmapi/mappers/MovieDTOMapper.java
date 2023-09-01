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

    @Autowired
    protected ICharacterService characterService;

    @Mapping(target = "title", source = "title")
    @Mapping(target = "characters", qualifiedByName = "characterIdsToCharacters")
    @Mapping(target = "franchise.id", source = "franchise")
    public abstract Movie addMovieDTOToMovie(AddMovieDTO addMovieDTO);

    @Named("characterIdsToCharacters")
    public Set<Character> mapMovieIdsToMovies(Set<Integer> characterIds){
        return characterIds.stream().map(characterService::findById).collect(Collectors.toSet());
    }
}
