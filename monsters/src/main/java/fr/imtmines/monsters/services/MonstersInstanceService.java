package fr.imtmines.monsters.services;


import fr.imtmines.monsters.entity.MonsterInstance;
import fr.imtmines.monsters.repository.MonstersInstanceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonstersInstanceService {

    private final MonstersInstanceRepository monstersInstanceRepository;

    public MonstersInstanceService(MonstersInstanceRepository monstersInstanceRepository) {
        this.monstersInstanceRepository = monstersInstanceRepository;
    }

    public boolean existsById(long heroId, long roomId, long dungeonId) {
        return monstersInstanceRepository.existsByHeroIdAndRoomIdAndDungeonId(heroId, roomId, dungeonId);
    }

    public Optional<MonsterInstance> getMonsterInstanceById(long heroId) {
        return monstersInstanceRepository.findByHeroId(heroId);
    }

    public void deleteMonsterInstance(long heroId) {
        monstersInstanceRepository.deleteMonsterInstanceByHeroId(heroId);
    }
}

