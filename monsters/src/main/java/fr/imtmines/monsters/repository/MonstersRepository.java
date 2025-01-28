package fr.imtmines.monsters.repository;

import fr.imtmines.monsters.entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonstersRepository extends JpaRepository<Monster, Long> {
    Optional<Monster> findByName(String name);
}
