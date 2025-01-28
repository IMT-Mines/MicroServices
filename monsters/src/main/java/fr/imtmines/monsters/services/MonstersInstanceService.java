package fr.imtmines.monsters.services;


import fr.imtmines.monsters.entity.Monster;
import fr.imtmines.monsters.entity.MonsterInstance;
import fr.imtmines.monsters.repository.MonstersInstanceRepository;
import fr.imtmines.monsters.repository.MonstersRepository;
import org.springframework.stereotype.Service;

@Service
public class MonstersInstanceService {


    private final MonstersInstanceRepository monstersInstanceRepository;
    private final MonstersRepository monstersRepository;

    public MonstersInstanceService(MonstersInstanceRepository monstersInstanceRepository, MonstersRepository monstersRepository) {
        this.monstersInstanceRepository = monstersInstanceRepository;
        this.monstersRepository = monstersRepository;
    }

    public void deleteMonsterInstance(Long id) {
        monstersInstanceRepository.deleteById(id);
    }

    public MonsterInstance createMonsterInstance(MonsterInstance monsterInstance) {

        Monster monsterTemplate = monstersRepository.findByName(monsterInstance.getName())
                .orElseThrow(() -> new IllegalArgumentException("No monster template found with the name: " + monsterInstance.getName()));


        monsterInstance.setMaxHealth(monsterTemplate.getMaxHealth());
        monsterInstance.setGold(monsterTemplate.getGold());
        monsterInstance.setItemDrop(monsterTemplate.getItemDrop());
        monsterInstance.setDamage(monsterTemplate.getDamage());
        monsterInstance.setHealth(monsterTemplate.getMaxHealth());


        return monstersInstanceRepository.save(monsterInstance);
    }

}

