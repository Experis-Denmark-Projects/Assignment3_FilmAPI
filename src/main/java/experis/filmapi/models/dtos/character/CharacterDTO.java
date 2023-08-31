package experis.filmapi.models.dtos.character;

import experis.filmapi.models.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CharacterDTO {
    private int id;
    private String name;
    private Gender gender;
    private Set<Integer> movies;
}
