package experis.filmapi.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class FranchiseNotFoundException extends EntityNotFoundException {

    public FranchiseNotFoundException(int id){
        super(String.format("Franchise does not exists with ID: %s", id));
    }

}
