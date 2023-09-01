package experis.filmapi.models.dtos.character;

import experis.filmapi.models.enums.Gender;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for adding a character. Doesn't contain Id
 * Since this is being auto incremented when adding a new character to the postgres database.
 */

@Getter
@Setter
public class AddCharacterDTO {
    private String name;
    private int franchise;
    private String gender;
}
