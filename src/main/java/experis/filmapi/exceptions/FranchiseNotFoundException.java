package experis.filmapi.exceptions;

import jakarta.persistence.EntityNotFoundException;

// The purpose of this class is to provide the user with information about a franchise not existsing with the given id.
// Therefore, the class calls its parents constructor and passes the messages as a string argument.

public class FranchiseNotFoundException extends EntityNotFoundException {

    public FranchiseNotFoundException(int id){
        super(String.format("Franchise does not exists with ID: %s", id));
    }

}
