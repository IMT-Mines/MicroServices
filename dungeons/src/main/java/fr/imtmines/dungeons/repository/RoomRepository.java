package fr.imtmines.dungeons.repository;

import fr.imtmines.dungeons.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findRoomByIdAndDungeonId(Long roomId, Long dungeonId);
}