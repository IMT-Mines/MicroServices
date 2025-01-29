package fr.imtmines.monsters.repository;

import fr.imtmines.monsters.entity.MonsterInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonstersInstanceRepository extends JpaRepository<MonsterInstance, Long> {
    Optional<MonsterInstance> findByHeroId(Long heroId);

    boolean existsByHeroIdAndRoomIdAndDungeonId(long heroId, long roomId, long dungeonId);

    void deleteMonsterInstanceByHeroId(long heroId);
}
