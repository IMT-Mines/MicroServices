package fr.imtmines.dungeons.controller;

import fr.imtmines.dungeons.dto.CreateMonsterRequest;
import fr.imtmines.dungeons.entity.Dungeon;
import fr.imtmines.dungeons.entity.Room;
import fr.imtmines.dungeons.repository.DungeonRepository;
import fr.imtmines.dungeons.repository.RoomRepository;
import fr.imtmines.dungeons.service.CreateMonsterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class DungeonController {
    private final DungeonRepository dungeonRepository;
    private final RoomRepository roomRepository;

    private final CreateMonsterService createMonsterService;

    public DungeonController(DungeonRepository dungeonRepository, RoomRepository roomRepository, CreateMonsterService createMonsterService) {
        this.dungeonRepository = dungeonRepository;
        this.roomRepository = roomRepository;

        this.createMonsterService = createMonsterService;
    }

    @GetMapping("/dungeons")
    public List<Dungeon> getDungeons() {
        return dungeonRepository.findAll();
    }

    @GetMapping("/dungeons/{id}")
    public ResponseEntity<Dungeon> getDungeonsById(@PathVariable Long id) {
        Dungeon dungeon = dungeonRepository.findById(id).orElse(null);

        if (dungeon == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(dungeon);
    }

    @GetMapping("/dungeons/{dungeonId}/rooms/{roomId}")
    public ResponseEntity<Room> getDungeonRoomById(@PathVariable Long dungeonId, @PathVariable Long roomId, @RequestParam Long heroId) {
        Room room = roomRepository.findRoomByIdAndDungeonId(roomId, dungeonId);

        if (room == null)
            return ResponseEntity.notFound().build();

        createMonsterService.createMonsters(new CreateMonsterRequest(dungeonId, roomId, room.getMonsterId(), heroId));

        return ResponseEntity.ok(room);
    }
}