package experis.filmapi.models.dtos.character;

import experis.filmapi.models.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCharacterDTO {
    private String name;
    private int franchise;
    private String gender;
}
