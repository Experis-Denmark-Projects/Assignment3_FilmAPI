package experis.filmapi.models.dtos.character;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;
/**
 * DTO for  characters. Used for finding characters and have selected info to show back
 * The set of Integers property called movies refer to the ids of the movies which the character relates to.
 */
@Getter
@Setter
public class CharacterDTO {
    private int id;
    private String name;
    private int franchise;
    private Set<Integer> movies;
}
