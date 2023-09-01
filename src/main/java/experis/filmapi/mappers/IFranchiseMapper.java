package experis.filmapi.mappers;

import experis.filmapi.models.Character;
import experis.filmapi.models.Franchise;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.franchise.FranchiseDTO;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for mapping franchises to franchiseDTOs and collections of the same.
 */

@Mapper(componentModel = "spring")
public interface IFranchiseMapper {

    // target refers to FranchiseDTO and source is the franchise parameter.
    // qualifiedByName refers to the map and mapCharacters methods.
    @Mapping(target = "characters", qualifiedByName = "charactersToCharacterIds")
    @Mapping(target = "movies", qualifiedByName = "moviesToMovieIds")
    FranchiseDTO franchiseToFranchiseDTO(Franchise franchise);

    Collection<FranchiseDTO> franchiseToFranchiseDTO(Collection<Franchise> franchises);

    // This method maps a set of movies to a set of Integer movie ids.
    @Named(value = "moviesToMovieIds")
    default Set<Integer> map(Set<Movie> value){
        if(value == null) return null;
        return value.stream().map(Movie::getId).collect(Collectors.toSet());
    }

    // This method maps a set of characters to a set of Integer character ids.
    @Named(value = "charactersToCharacterIds")
    default Set<Integer> mapCharacters(Set<Character> value){
        if(value == null) return null;
        return value.stream().map(Character::getId).collect(Collectors.toSet());
    }
}
