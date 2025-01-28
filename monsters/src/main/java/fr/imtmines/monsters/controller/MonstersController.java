package fr.imtmines.monsters.controller;

import fr.imtmines.monsters.services.MonstersService;
import fr.imtmines.monsters.entity.Monster;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/monsters")
public class MonstersController {

    private final MonstersService monstersService;

    public MonstersController(MonstersService monstersService) {
        this.monstersService = monstersService;
    }

    @GetMapping
    public List<Monster> getMonsters() {
        return monstersService.getMonsters();
    }


    @GetMapping("/{id}")
    public Monster getMonstersById(@PathVariable Long id) {
        return monstersService.getMonstersById(id);
    }

    @PostMapping
    public ResponseEntity<Monster> createMonster(@RequestBody Monster monster) {
        Monster createdMonster = monstersService.createMonster(monster);
        return new ResponseEntity<>(createdMonster, HttpStatus.CREATED);
    }

}
