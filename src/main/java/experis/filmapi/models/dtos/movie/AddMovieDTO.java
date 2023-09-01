package experis.filmapi.models.dtos.movie;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
/**
 * DTO for adding a movie. Doesn't contain Id
 * The Id is automatically being incremented when a movie instance is added to the database.
 * Therefore, this DTO contains the fields that user will pass when sending an HTTP request.
 */
@Getter
@Setter
public class AddMovieDTO {
    private String title;
    private Set<Integer> characters;
    private int franchise;
}
