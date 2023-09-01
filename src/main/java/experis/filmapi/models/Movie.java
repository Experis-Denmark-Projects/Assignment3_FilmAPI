package experis.filmapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/* This Movie model class is a representation of the Movie column in the Film database
 * The class is decorated with an @Entity annotation such that the Spring Application knows it is a JPA entity.
 * Furthermore, the class is also decorated with @Setter & @Getter annotations.
 * Thus, the Lombok library generates getter & setter methods for each class property.
 * This is also why each property is a private field.
 * The @Column annotation specifies constraints for the respective property.
 * In this case the nullable parameter determines whether the field can be null or not.
 * The @ManyToOne annotation is used to establish a relationship with the Franchise class.
 * This means that each instance of the Movie class can reference one Franchise instance.
 * However, the Franchise class can be referenced by multiple instances of the Movie class.
 * The @ManyToMany annotation specifies that multiple instances of the Movie class can reference the Character class
 * and vice versa.
 * */

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

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;
}
