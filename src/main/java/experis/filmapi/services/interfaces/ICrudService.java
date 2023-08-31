package experis.filmapi.services.interfaces;

import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface ICrudService<T,ID> {

    // Create, Read, Update & Delete

    T create(T object);

    T findById(ID id);

    Collection<T> findAll();

    T update(T object);

    //TODO delete



}
