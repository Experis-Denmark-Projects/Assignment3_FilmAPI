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

    /* ICharacterService & IMovieService are used in the process of converting
     * a set of Movie or Character ids to a set of Movies or Characters.*/
    @Autowired
    protected ICharacterService characterService;
    @Autowired
    protected IMovieService movieService;

    // target refers to the franchise object being mapped to and source is the addFranchiseDTO object in the parameter.
    @Mapping(target ="name", source="name")
    @Mapping(target = "movies", qualifiedByName = "movieIdsToMovies")
    @Mapping(target = "characters", qualifiedByName = "characterIdsToCharacters")
    public abstract Franchise addFranchiseDTOToFranchise(AddFranchiseDTO addFranchiseDTO);

    // This method uses the character service to get the character by using findById and passing the character id as an argument.
    @Named("characterIdsToCharacters")
    public Set<Character> mapCharacterIdsToCharacters(Set<Integer> characterIds){
        return characterIds.stream().map(characterService::findById).collect(Collectors.toSet());
    }

    // This method uses the movie service to get the movie by using findById and passing the movie id as an argument.
    @Named("movieIdsToMovies")
    public Set<Movie> mapMovieIdsToMovies(Set<Integer> movieIDs){
        return movieIDs.stream().map(movieService::findById).collect(Collectors.toSet());
    }
}