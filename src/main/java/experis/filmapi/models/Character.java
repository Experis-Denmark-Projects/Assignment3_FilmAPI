package experis.filmapi.models;

import experis.filmapi.models.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/* This Character model class is a representation of the Character column in the Film database
 * The class is decorated with an @Entity annotation such that the Spring Application knows it is a JPA entity.
 * Furthermore, the class is also decorated with @Setter & @Getter annotations.
 * Thus, the Lombok library generates getter & setter methods for each class property.
 * This is also why each property is a private field.
 * The @Column annotation specifies constraints for the respective property.
 * In this case the nullable parameter determines whether the field can be null or not.
 * The @ManyToOne annotation is used to establish a relationship with the Franchise class.
 * This means that each instance of the Character class can reference one Franchise instance.
 * However, the Franchise class can be referenced by multiple instances of the Character class.
 * The @ManyToMany annotation specifies that multiple instances of the Movie class can reference the Character class
 * and vice versa.
 * */


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

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;
}