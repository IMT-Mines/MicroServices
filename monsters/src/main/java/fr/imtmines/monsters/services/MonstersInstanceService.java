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

    public boolean existsByHeroIdAndRoomIdAndDungeonId(long heroId, int roomId, int dungeonId) {
        return monstersInstanceRepository.existsByHeroIdAndRoomIdAndDungeonId(heroId, roomId, dungeonId);
    }
}

