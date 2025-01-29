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
    private final LogService logService;

    public DungeonService(DungeonRepository dungeonRepository, RoomRepository roomRepository, LogService logService) {
        this.dungeonRepository = dungeonRepository;
        this.roomRepository = roomRepository;
        this.logService = logService;
    }

    public List<Dungeon> getAllDungeons() {
        logService.log("Getting all dungeons");
        return dungeonRepository.findAll();
    }

    public Optional<Dungeon> getDungeonById(Long id) {
        logService.log("Getting dungeon by id " + id);
        return dungeonRepository.findById(id);
    }

    public Optional<Room> getRoomById(Long dungeonId, Long roomId) {
        logService.log("Getting room by id " + roomId + " in dungeon " + dungeonId);
        return Optional.ofNullable(roomRepository.findRoomByIdAndDungeonId(roomId, dungeonId));
    }
}