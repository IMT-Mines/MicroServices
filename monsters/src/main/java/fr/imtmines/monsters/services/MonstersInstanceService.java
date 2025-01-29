package fr.imtmines.monsters.services;


import fr.imtmines.monsters.entity.MonsterInstance;
import fr.imtmines.monsters.repository.MonstersInstanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<MonsterInstance> getMonstersInstanceById(Long id) {
        return monstersInstanceRepository.findById(id);
    }

    public MonsterInstance saveMonsterInstance(MonsterInstance monsterInstance) {
        return monstersInstanceRepository.save(monsterInstance);
    }

    public Optional<MonsterInstance> getMonsterInstanceByUserId(Long heroId) {
        return monstersInstanceRepository.findByHeroId(heroId);
    }

}

