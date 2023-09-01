package experis.filmapi.models.dtos.character;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class CharacterDTO {
    private int id;
    private String name;
    private Set<Integer> movies;
}
