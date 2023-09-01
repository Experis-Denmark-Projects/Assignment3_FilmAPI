package experis.filmapi.mappers;

import experis.filmapi.models.Character;
import experis.filmapi.models.Franchise;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.franchise.AddFranchiseDTO;
import experis.filmapi.services.interfaces.ICharacterService;
import experis.filmapi.services.interfaces.IMovieService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;
/**
 * Mapper file for mapping the DTO used for adding franchises. It maps from a franchise to a AddFranchiseDTO
 */
@Mapper(componentModel = "spring")
public abstract class FranchiseDTOMapper {

    @Autowired
    protected ICharacterService characterService;
    @Autowired
    protected IMovieService movieService;

    @Mapping(target ="name", source="name")
    @Mapping(target = "movies", qualifiedByName = "movieIdsToMovies")
    @Mapping(target = "characters", qualifiedByName = "characterIdsToCharacters")
    public abstract Franchise addFranchiseDTOToFranchise(AddFranchiseDTO addFranchiseDTO);

    @Named("characterIdsToCharacters")
    public Set<Character> mapCharacterIdsToCharacters(Set<Integer> characterIds){
        return characterIds.stream().map(characterService::findById).collect(Collectors.toSet());
    }

    @Named("movieIdsToMovies")
    public Set<Movie> mapMovieIdsToMovies(Set<Integer> movieIDs){
        return movieIDs.stream().map(movieService::findById).collect(Collectors.toSet());
    }
}