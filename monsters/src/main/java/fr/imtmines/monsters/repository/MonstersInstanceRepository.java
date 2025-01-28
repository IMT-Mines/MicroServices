package fr.imtmines.monsters.repository;

import fr.imtmines.monsters.entity.MonsterInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonstersInstanceRepository extends JpaRepository<MonsterInstance, Long> {
}
