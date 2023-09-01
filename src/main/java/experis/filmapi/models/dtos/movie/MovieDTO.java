package experis.filmapi.models.dtos.movie;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

/**
 * DTO for  movies. Used for finding movies and have selected info to show back.
 * The characters property refers to the ids of the characters that refers to this movie object.
 */

@Getter
@Setter
public class MovieDTO {
    private int id;
    private String title;
    private Set<Integer> characters;
    private int franchise;
}
