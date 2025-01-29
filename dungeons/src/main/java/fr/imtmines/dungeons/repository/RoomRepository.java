package fr.imtmines.dungeons.repository;

import fr.imtmines.dungeons.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findRoomByIdAndDungeonId(Long roomId, Long dungeonId);
}