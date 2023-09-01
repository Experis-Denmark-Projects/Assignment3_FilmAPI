package experis.filmapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/* This Franchise model class is a representation of the Franchise column in the Film database
* The class is decorated with an @Entity annotation such that the Spring Application knows it is a JPA entity.
* Furthermore, the class is also decorated with @Setter & @Getter annotations.
* Thus, the Lombok library generates getter & setter methods for each class property.
* This is also why each property is a private field.
* The @Column annotation specifies constraints for the respective property.
* In this case the nullable parameter determines whether the field can be null or not.
* The @OneToMany annotation is used to establish a relationship with the Franchise class.
* Then each class referencing the Franchise class should have a Franchise property.
* This means that the Franchise class be referenced by multiple characters or movies.
* However, each character or movie can only reference one franchise.
* */

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

    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movies;

    @OneToMany(mappedBy = "franchise")
    private Set<Character> characters;
}
