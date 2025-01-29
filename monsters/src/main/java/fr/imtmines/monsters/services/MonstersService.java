package fr.imtmines.monsters.services;

import fr.imtmines.monsters.entity.Monster;
import fr.imtmines.monsters.entity.MonsterDamageResponseDto;
import fr.imtmines.monsters.entity.MonsterInstance;
import fr.imtmines.monsters.exception.MonsterNotFoundException;
import fr.imtmines.monsters.repository.MonstersInstanceRepository;
import fr.imtmines.monsters.repository.MonstersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class MonstersService {

    private final MonstersRepository monstersRepository;
    private final MonstersInstanceRepository monstersInstanceRepository;

    public MonstersService(MonstersRepository monstersRepository, MonstersInstanceRepository monstersInstanceRepository) {
        this.monstersRepository = monstersRepository;
        this.monstersInstanceRepository = monstersInstanceRepository;
    }

    public List<MonsterInstance> getAllMonstersInstances() {
        return monstersInstanceRepository.findAll();
    }

    public Monster getMonsterById(Long id) {
        return monstersRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException("Monster not found with id " + id));
    }

    public MonsterInstance createMonsterInstance(MonsterInstance monsterInstance) {
        Monster monsterTemplate = getMonsterById(monsterInstance.getId());

        monsterInstance.setId(null);
        monsterInstance.setMaxHealth(monsterTemplate.getMaxHealth());
        monsterInstance.setGold(monsterTemplate.getGold());
        monsterInstance.setItemDrop(monsterTemplate.getItemDrop());
        monsterInstance.setDamage(monsterTemplate.getDamage());
        monsterInstance.setHealth(monsterTemplate.getMaxHealth());
        monsterInstance.setName(monsterTemplate.getName());
        monsterInstance.setImage(monsterTemplate.getImage());

        return monstersInstanceRepository.save(monsterInstance);
    }

    public MonsterDamageResponseDto attackMonster(long heroId, int damage) {
        MonsterInstance monsterInstance = monstersInstanceRepository.findByHeroId(heroId)
                .orElseThrow(() -> new MonsterNotFoundException("No monster found for heroId " + heroId));

        monsterInstance.setHealth(Math.min(0, monsterInstance.getHealth() - damage));
        MonsterDamageResponseDto response;

        if (monsterInstance.getHealth() <= 0) {
            int monsterDamage = monsterInstance.getDamage();
            int randomDamage = monsterDamage / 2 + new Random().nextInt(monsterDamage / 2 + 1);

            monstersInstanceRepository.deleteById(monsterInstance.getId());
            response = new MonsterDamageResponseDto(0, randomDamage);
        } else {
            MonsterInstance updatedMonster = monstersInstanceRepository.save(monsterInstance);
            response = new MonsterDamageResponseDto(updatedMonster.getHealth(), updatedMonster.getDamage());
        }
        return response;
    }
}
