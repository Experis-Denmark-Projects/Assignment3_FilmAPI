package experis.filmapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="franchise_id")
    private int id;

    @Column(length = 50,nullable = false)
    private String name;

    @Column(columnDefinition = "Text")
    private String description;

    @OneToMany(mappedBy = "movie")
    @JoinColumn(name = "movie_id")
    private Set<Movie> movies;
}
