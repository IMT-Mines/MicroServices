package fr.imtmines.monsters.services;


import fr.imtmines.monsters.entity.Monster;
import fr.imtmines.monsters.entity.MonsterInstance;
import fr.imtmines.monsters.repository.MonstersInstanceRepository;
import fr.imtmines.monsters.repository.MonstersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<MonsterInstance> getMonstersInstance() {
        return monstersInstanceRepository.findAll();
    }

    public MonsterInstance getMonstersInstanceById(Long id) {
        return monstersInstanceRepository.findById(id).orElse(null);
    }

    public MonsterInstance saveMonsterInstance(MonsterInstance monsterInstance) {
        return monstersInstanceRepository.save(monsterInstance);
    }

    public MonsterInstance getMonsterInstanceByUserId(Long userId) {
        return (MonsterInstance) monstersInstanceRepository.findByUserId(String.valueOf(userId)).orElse(null);
    }

}

