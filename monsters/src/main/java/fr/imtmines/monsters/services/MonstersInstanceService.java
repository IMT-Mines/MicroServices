package fr.imtmines.monsters.services;


import fr.imtmines.monsters.entity.MonsterInstance;
import fr.imtmines.monsters.repository.MonstersInstanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonstersInstanceService {


    private final MonstersInstanceRepository monstersInstanceRepository;

    public MonstersInstanceService(MonstersInstanceRepository monstersInstanceRepository) {
        this.monstersInstanceRepository = monstersInstanceRepository;
    }

    public void deleteMonsterInstance(Long id) {
        monstersInstanceRepository.deleteById(id);
    }

    public List<MonsterInstance> getMonstersInstance() {
        return monstersInstanceRepository.findAll();
    }

    public MonsterInstance getMonstersInstanceById(Long id) {
        return monstersInstanceRepository.findById(id).orElse(null);
    }

    public MonsterInstance saveMonsterInstance(MonsterInstance monsterInstance) {
        return monstersInstanceRepository.save(monsterInstance);
    }

    public MonsterInstance getMonsterInstanceByUserId(Long heroId) {
        return (MonsterInstance) monstersInstanceRepository.findByHeroId(heroId).orElse(null);
    }

}

