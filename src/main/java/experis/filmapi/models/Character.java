package experis.filmapi.models;

import experis.filmapi.models.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="char_id")
    private int id;

    @Column(name="char_name", length=50, nullable = false)
    private String name;
    @Column(length=50)
    private String alias;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    //default length of 255
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "character_movie",
            joinColumns = {@JoinColumn(name = "char_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    private Set<Movie> movies;
}