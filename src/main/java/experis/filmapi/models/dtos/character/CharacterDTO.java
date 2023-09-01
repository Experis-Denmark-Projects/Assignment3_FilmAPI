package experis.filmapi.models.dtos.character;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;
/**
 * DTO for  characters. Used for finding characters and have selected info to show back
 */
@Getter
@Setter
public class CharacterDTO {
    private int id;
    private String name;
    private int franchise;
    private Set<Integer> movies;
}
