package experis.filmapi.services.interfaces;

import java.util.Collection;
/** This generic interface serves the purpose of being the super repository interface which follows the repository pattern.
 * Therefore, the interface defines the four methods findAll, findById, insert and delete that all use the generic types
 * that the interface specifies.
 * These generic types refer to object, identifier and value respectively and must be non-primitive data types.
 * **/
public interface ICrudService<T,ID> {

    // Create, Read, Update & Delete

    T create(T object);

    T findById(ID id);

    Collection<T> findAll();

    T update(T object);

    void deleteById(ID id);
}
