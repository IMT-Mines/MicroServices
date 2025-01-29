package fr.imtmines.monsters.repository;

import fr.imtmines.monsters.entity.MonsterInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonstersInstanceRepository extends JpaRepository<MonsterInstance, Long> {
    Optional<Object> findByHeroId(Long heroId);
}
