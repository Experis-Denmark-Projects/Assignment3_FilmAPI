package experis.filmapi.models.dtos.franchise;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class AddFranchiseDTO {
    private String name;
    private Set<Integer> movies;
    private Set<Integer> characters;
}
