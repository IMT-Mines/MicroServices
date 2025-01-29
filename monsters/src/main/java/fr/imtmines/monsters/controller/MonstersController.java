package fr.imtmines.monsters.controller;

import fr.imtmines.monsters.entity.Monster;
import fr.imtmines.monsters.entity.MonsterDamageResponseDto;
import fr.imtmines.monsters.entity.MonsterInstance;
import fr.imtmines.monsters.exception.MissingParameterException;
import fr.imtmines.monsters.services.MonstersInstanceService;
import fr.imtmines.monsters.services.MonstersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/monsters")
public class MonstersController {

    private final MonstersService monstersService;
    private final MonstersInstanceService monstersInstanceService;

    public MonstersController(MonstersService monstersService, MonstersInstanceService monstersInstanceService) {
        this.monstersService = monstersService;
        this.monstersInstanceService = monstersInstanceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monster> getMonsterById(@PathVariable Long id) {
        return ResponseEntity.ok(monstersService.getMonsterById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<MonsterInstance> createMonsterInstance(@RequestBody MonsterInstance monsterInstance) {
        if (monstersInstanceService.existsByHeroIdAndRoomIdAndDungeonId(
                monsterInstance.getHeroId(), monsterInstance.getRoomId(), monsterInstance.getDungeonId())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        MonsterInstance createdMonster = monstersService.createMonsterInstance(monsterInstance);
        return new ResponseEntity<>(createdMonster, HttpStatus.CREATED);
    }

    @PutMapping("/attack")
    public ResponseEntity<MonsterDamageResponseDto> attackMonster(@RequestBody Map<String, Long> requestBody) {
        Long heroId = requestBody.get("heroId");
        Long damage = requestBody.get("damage");

        if (heroId == null || damage == null)
            throw new MissingParameterException("heroId", "damage");

        return ResponseEntity.ok(monstersService.attackMonster(heroId, damage.intValue()));
    }
}