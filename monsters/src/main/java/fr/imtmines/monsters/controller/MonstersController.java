package fr.imtmines.monsters.controller;

import fr.imtmines.monsters.entity.MonsterInstance;
import fr.imtmines.monsters.repository.MonstersRepository;
import fr.imtmines.monsters.services.MonstersInstanceService;
import fr.imtmines.monsters.services.MonstersService;
import fr.imtmines.monsters.entity.Monster;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @GetMapping
    public List<MonsterInstance> getMonstersInstance() {
        return monstersInstanceService.getMonstersInstance();
    }

    @GetMapping("/{id}")
    public MonsterInstance getMonstersInstanceById(@PathVariable Long id) {
        return monstersInstanceService.getMonstersInstanceById(id);
    }

    @PostMapping("/dungeon")
    public ResponseEntity<MonsterInstance> createMonsterInstance(@RequestBody MonsterInstance monsterInstance) {

        Monster monsterTemplate = monstersService.getMonstersById(monsterInstance.getId());

        monsterInstance.setId(null);
        monsterInstance.setMaxHealth(monsterTemplate.getMaxHealth());
        monsterInstance.setGold(monsterTemplate.getGold());
        monsterInstance.setItemDrop(monsterTemplate.getItemDrop());
        monsterInstance.setDamage(monsterTemplate.getDamage());
        monsterInstance.setHealth(monsterTemplate.getMaxHealth());
        monsterInstance.setName(monsterTemplate.getName());
        monsterInstance.setImage(monsterTemplate.getImage());

        MonsterInstance createdMonsterInstance = monstersInstanceService.saveMonsterInstance(monsterInstance);
        return new ResponseEntity<>(createdMonsterInstance, HttpStatus.CREATED);

    }

    @GetMapping("/template/{id}")
    public Monster getMonstersById(@PathVariable Long id) {
        return monstersService.getMonstersById(id);
    }

    @PutMapping("/attack")
    public ResponseEntity<Map<String, Integer>> attackMonster(@RequestBody Map<String, Long> requestBody) {

        Long userId = requestBody.get("hero_id");
        MonsterInstance monsterInstance = monstersInstanceService.getMonsterInstanceByUserId(userId);

        if (monsterInstance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        monsterInstance.setHealth(monsterInstance.getHealth() - 5);

        if (monsterInstance.getHealth() <= 0) {

            Integer health = monsterInstance.getHealth();
            Integer damage = monsterInstance.getDamage();

            monstersInstanceService.deleteMonsterInstance(monsterInstance.getId());

            Map<String, Integer> response = new HashMap<>();
            response.put("health", health);
            response.put("damage", damage);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        MonsterInstance updatedMonsterInstance = monstersInstanceService.saveMonsterInstance(monsterInstance);

        Map<String, Integer> response = new HashMap<>();
        response.put("health", updatedMonsterInstance.getHealth());
        response.put("damage", updatedMonsterInstance.getDamage());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

