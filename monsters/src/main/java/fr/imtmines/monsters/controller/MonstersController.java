package fr.imtmines.monsters.controller;

import fr.imtmines.monsters.dto.MonsterDto;
import fr.imtmines.monsters.entity.MonsterDamageResponseDto;
import fr.imtmines.monsters.entity.MonsterInstance;
import fr.imtmines.monsters.exception.MissingParameterException;
import fr.imtmines.monsters.exception.MonsterNotFoundException;
import fr.imtmines.monsters.services.MonstersInstanceService;
import fr.imtmines.monsters.services.MonstersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/monsters")
public class MonstersController {

    private final MonstersService monstersService;
    private final MonstersInstanceService monstersInstanceService;

    public MonstersController(MonstersService monstersService, MonstersInstanceService monstersInstanceService) {
        this.monstersService = monstersService;
        this.monstersInstanceService = monstersInstanceService;
    }

    @GetMapping("/heroes/{heroId}")
    public ResponseEntity<MonsterInstance> getMonsterById(@PathVariable Long heroId) {
        return monstersInstanceService.getMonsterInstanceById(heroId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new MonsterNotFoundException("No monster found for heroId " + heroId));
    }

    @PostMapping("/create")
    public ResponseEntity<MonsterInstance> createMonsterInstance(@RequestBody MonsterDto monsterDto) {
        if (monstersInstanceService.existsById(monsterDto.heroId(), monsterDto.roomId(), monsterDto.dungeonId())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        final MonsterInstance createdMonster = monstersService.createMonsterInstance(monsterDto);
        return new ResponseEntity<>(createdMonster, HttpStatus.CREATED);
    }

    @DeleteMapping("/heroes/{heroId}")
    public ResponseEntity<Void> deleteMonsterInstance(@PathVariable Long heroId) {
        monstersInstanceService.deleteMonsterInstance(heroId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/attack")
    public ResponseEntity<MonsterDamageResponseDto> attackMonster(@RequestBody Map<String, Long> requestBody) {
        final Long heroId = requestBody.get("heroId");
        final Long damage = requestBody.get("damage");

        if (heroId == null || damage == null)
            throw new MissingParameterException("heroId", "damage");

        return ResponseEntity.ok(monstersService.attackMonster(heroId, damage.intValue()));
    }
}