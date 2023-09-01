package experis.filmapi.models.dtos.franchise;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

/**
 * DTO for  franchises. Used for finding franchises and have selected info to show back
 */

@Getter
@Setter
public class FranchiseDTO {
    private int id;
    private String name;
    private Set<Integer> movies;
    private Set<Integer> characters;
}
