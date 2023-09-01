package experis.filmapi.models.dtos.movie;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
/**
 * DTO for adding a movie. Doesn't contain Id
 */
@Getter
@Setter
public class AddMovieDTO {
    private String title;
    private Set<Integer> characters;
    private int franchise;
}
