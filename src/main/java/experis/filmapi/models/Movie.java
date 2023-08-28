package experis.filmapi.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
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

    @OneToMany(mappedBy = "movie")
    private Set<Character> characters;

    @OneToMany(mappedBy = "movie")
    private Set<Franchise> franchises;




}
