package experis.filmapi.models;

import jakarta.persistence.*;

@Entity
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="franchise_id")
    private int id;

    @Column(length = 50,nullable = false)
    private String name;

    @Column(columnDefinition = "Text")
    private String description;


    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;


}
