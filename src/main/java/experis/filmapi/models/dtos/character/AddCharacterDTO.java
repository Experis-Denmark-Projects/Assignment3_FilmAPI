package experis.filmapi.models.dtos.character;

import experis.filmapi.models.enums.Gender;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for adding a character. Doesn't contain Id
 */

@Getter
@Setter
public class AddCharacterDTO {
    private String name;
    private int franchise;
    private String gender;
}
