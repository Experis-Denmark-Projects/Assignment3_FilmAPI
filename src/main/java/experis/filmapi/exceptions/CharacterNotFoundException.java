package experis.filmapi.exceptions;

import jakarta.persistence.EntityNotFoundException;

// The purpose of this class is to provide the user with information about a character not existsing with the given id.
// Therefore, the class calls its parents constructor and passes the messages as a string argument.
public class CharacterNotFoundException extends EntityNotFoundException {
    public CharacterNotFoundException(int id){
        super(String.format("Character does not exists with ID: %s", id));
    }
}
