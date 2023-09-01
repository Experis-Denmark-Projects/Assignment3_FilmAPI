package experis.filmapi.models.dtos.movie;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {
    private int id;
    private String title;
    private Set<Integer> characters;
    private int franchise;
}
