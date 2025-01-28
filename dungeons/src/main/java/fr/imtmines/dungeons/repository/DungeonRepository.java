package fr.imtmines.dungeons.repository;

import fr.imtmines.dungeons.entity.Dungeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DungeonRepository extends JpaRepository<Dungeon, Long> {
}
