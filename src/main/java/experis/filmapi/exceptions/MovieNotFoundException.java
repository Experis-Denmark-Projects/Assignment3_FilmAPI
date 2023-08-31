package experis.filmapi.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class MovieNotFoundException extends EntityNotFoundException {

    public MovieNotFoundException(int id){
        super(String.format("Movie does not exists with ID: %s", id));
    }
}
