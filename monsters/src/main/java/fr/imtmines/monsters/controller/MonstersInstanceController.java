package fr.imtmines.monsters.controller;

import fr.imtmines.monsters.entity.MonsterInstance;
import fr.imtmines.monsters.services.MonstersInstanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/monsters/dungeon")
public class MonstersInstanceController {


    private final MonstersInstanceService monstersInstanceService;

    public MonstersInstanceController(MonstersInstanceService monstersInstanceService) {
        this.monstersInstanceService = monstersInstanceService;
    }

    @PostMapping
    public ResponseEntity<MonsterInstance> createMonsterInstance(@RequestBody MonsterInstance monsterInstance) {
        MonsterInstance createdMonsterInstance = monstersInstanceService.createMonsterInstance(monsterInstance);
        return new ResponseEntity<>(createdMonsterInstance, HttpStatus.CREATED);
    }

}
