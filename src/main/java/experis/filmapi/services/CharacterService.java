package experis.filmapi.services;

import experis.filmapi.models.Franchise;
import experis.filmapi.models.Movie;
import experis.filmapi.models.dtos.character.AddCharacterDTO;
import experis.filmapi.repositories.ICharacterRepository;
import experis.filmapi.exceptions.CharacterNotFoundException;
import experis.filmapi.models.Character;
import experis.filmapi.services.interfaces.ICharacterService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Service class responsible for handling operations related to movie characters.
 */
@Service
public class CharacterService implements ICharacterService {

    private final ICharacterRepository characterRepository;

    /**
     * Constructor to initialize the CharacterService with an instance of ICharacterRepository.
     *
     * @param characterRepository An implementation of the character repository.
     */
    public CharacterService(ICharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    /**
     * Creates a new character record.
     *
     * @param character The character object to be created.
     * @return The created character object.
     */
    @Override
    public Character create(Character character) {
        return characterRepository.save(character);
    }

    /**
     * Finds a character by its unique identifier.
     *
     * @param id The unique identifier of the character to find.
     * @return The character object if found, or throws a CharacterNotFoundException.
     * @throws CharacterNotFoundException If no character with the given ID is found.
     */

    @Override
    public Character findById(Integer id) {
        return characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id));
    }

    /**
     * Retrieves a collection of all character records.
     *
     * @return A collection of character objects.
     */
    @Override
    public Collection<Character> findAll() {
        return characterRepository.findAll();
    }

    /**
     * Updates an existing character record.
     *
     * @param character The character object to be updated.
     * @return The updated character object.
     */
    @Override
    public Character update(Character character) {
        return characterRepository.save(character);
    }

    /**
     * Deletes a character by its unique identifier.
     *
     * @param id The unique identifier of the character to delete.
     */
    @Override
    public void deleteById(Integer id) {
        characterRepository.deleteById(id);
    }
}
