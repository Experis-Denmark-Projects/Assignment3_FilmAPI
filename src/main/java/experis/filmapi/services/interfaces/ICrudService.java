package experis.filmapi.services.interfaces;

import java.util.Collection;

public interface ICrudService<T,ID> {

    // Create, Read, Update & Delete

    T create(T object);

    T findById(ID id);

    Collection<T> findAll();

    T update(T object);

    void deleteById(ID id);
}
