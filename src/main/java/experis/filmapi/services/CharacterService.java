package experis.filmapi.services;

import experis.filmapi.Repository.CharacterRepository;
import experis.filmapi.exceptions.CharacterNotFoundException;
import experis.filmapi.models.Character;
import experis.filmapi.services.interfaces.ICharacterService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CharacterService implements ICharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    @Override
    public Character create(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public Character findById(Integer id) {
        return characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id));
    }

    @Override
    public Collection<Character> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character update(Character character) {
        return characterRepository.save(character);
    }
}
