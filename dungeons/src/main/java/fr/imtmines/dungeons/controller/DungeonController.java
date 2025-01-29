package fr.imtmines.dungeons.controller;

import fr.imtmines.dungeons.dto.MonsterDto;
import fr.imtmines.dungeons.entity.Dungeon;
import fr.imtmines.dungeons.entity.Room;
import fr.imtmines.dungeons.exception.DungeonNotFoundException;
import fr.imtmines.dungeons.service.CreateMonsterService;
import fr.imtmines.dungeons.service.DungeonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/dungeons")
public class DungeonController {
    private final DungeonService dungeonService;
    private final CreateMonsterService createMonsterService;

    public DungeonController(DungeonService dungeonService, CreateMonsterService createMonsterService) {
        this.dungeonService = dungeonService;
        this.createMonsterService = createMonsterService;
    }

    @GetMapping
    public List<Dungeon> getDungeons() {
        return dungeonService.getAllDungeons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dungeon> getDungeonsById(@PathVariable Long id) {
        return dungeonService.getDungeonById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new DungeonNotFoundException("Dungeon not found with id " + id));
    }

    @GetMapping("/{dungeonId}/rooms/{roomId}")
    public ResponseEntity<Room> getDungeonRoomById(@PathVariable Long dungeonId, @PathVariable Long roomId, @RequestParam Long heroId) {
        return dungeonService.getRoomById(dungeonId, roomId)
                .map(room -> {
                    createMonsterService.createMonster(new MonsterDto(dungeonId, roomId, room.getMonsterId(), heroId));
                    return ResponseEntity.ok(room);
                })
                .orElseThrow(() -> new DungeonNotFoundException("Room not found with id " + roomId + " in dungeon " + dungeonId));
    }
}
