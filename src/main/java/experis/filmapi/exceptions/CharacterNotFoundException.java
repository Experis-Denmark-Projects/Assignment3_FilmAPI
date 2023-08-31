package experis.filmapi.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CharacterNotFoundException extends EntityNotFoundException {
    public CharacterNotFoundException(int id){
        super(String.format("Character does not exists with ID: %s", id));
    }
}
