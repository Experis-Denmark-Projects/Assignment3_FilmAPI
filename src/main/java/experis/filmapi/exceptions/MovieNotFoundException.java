package experis.filmapi.exceptions;

import jakarta.persistence.EntityNotFoundException;

// The prupose of this class is to provide the user with information about a movie not existsing with the given id.
// Therefore, the class calls its parents constructor and passes the messages as a string argument.
public class MovieNotFoundException extends EntityNotFoundException {

    public MovieNotFoundException(int id){
        super(String.format("Movie does not exists with ID: %s", id));
    }
}
