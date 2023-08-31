package experis.filmapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_id")
    private int id;

    @Column(length = 50,nullable = false)
    private String title;

    @Column(length = 50,nullable = false)
    private String genre;

    @Column(length = 4,nullable = false)
    private int releaseYear;

    @Column(length = 50,nullable = false)
    private String director;

    private String imageUrl;

    private String trailerUrl;

    @ManyToMany(mappedBy = "movies")
    private Set<Character> characters;

    //@OneToMany(mappedBy = "movie")
    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;




}
