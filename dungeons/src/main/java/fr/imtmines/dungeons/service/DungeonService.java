package fr.imtmines.dungeons.service;

import fr.imtmines.dungeons.entity.Dungeon;
import fr.imtmines.dungeons.entity.Room;
import fr.imtmines.dungeons.repository.DungeonRepository;
import fr.imtmines.dungeons.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DungeonService {
    private final DungeonRepository dungeonRepository;
    private final RoomRepository roomRepository;

    public DungeonService(DungeonRepository dungeonRepository, RoomRepository roomRepository) {
        this.dungeonRepository = dungeonRepository;
        this.roomRepository = roomRepository;
    }

    public List<Dungeon> getAllDungeons() {
        return dungeonRepository.findAll();
    }

    public Optional<Dungeon> getDungeonById(Long id) {
        return dungeonRepository.findById(id);
    }

    public Optional<Room> getRoomById(Long dungeonId, Long roomId) {
        return roomRepository.findRoomByIdAndDungeonId(roomId, dungeonId);
    }
}