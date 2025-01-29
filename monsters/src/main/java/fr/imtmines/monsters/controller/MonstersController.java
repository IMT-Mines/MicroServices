package fr.imtmines.monsters.controller;

import fr.imtmines.monsters.dto.MonsterDto;
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
    public ResponseEntity<MonsterInstance> createMonsterInstance(@RequestBody MonsterDto monsterDto) {
        if (monstersInstanceService.existsByHeroIdAndRoomIdAndDungeonId(
                monsterDto.heroId(), monsterDto.roomId(), monsterDto.dungeonId())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        final MonsterInstance createdMonster = monstersService.createMonsterInstance(monsterDto);
        return new ResponseEntity<>(createdMonster, HttpStatus.CREATED);
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