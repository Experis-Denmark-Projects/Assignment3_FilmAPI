package experis.filmapi.services.interfaces;

import experis.filmapi.models.Character;

import java.util.Collection;

public interface ICharacterService extends ICrudService<Character, Integer>{
    @Override
    Character create(Character character);

    @Override
    Character findById(Integer id);

    @Override
    Collection<Character> findAll();

    @Override
    Character update(Character character);
}
