package fr.imtmines.heroes.controller;

import fr.imtmines.heroes.entity.Hero;
import fr.imtmines.heroes.exception.HeroNotFoundException;
import fr.imtmines.heroes.exception.MissingParameterException;
import fr.imtmines.heroes.service.HeroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping
    public List<Hero> getHeroes() {
        return heroService.getHeroes();
    }

    @PostMapping
    public ResponseEntity<String> addHero(@RequestBody Hero hero) {
        heroService.createHero(hero);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hero created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHero(@PathVariable String id) {
        return heroService.getHero(Long.parseLong(id))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new HeroNotFoundException("Hero not found with id " + id));
    }

    @PutMapping("/{id}/gold")
    public ResponseEntity<String> updateHeroGold(@PathVariable String id, @RequestBody Map<String, Integer> body) {
        final Integer newGold = body.get("gold");
        if (newGold == null)
            throw new MissingParameterException("gold");
        final Hero hero = heroService.getHero(Long.parseLong(id))
                .orElseThrow(() -> new HeroNotFoundException("Hero not found with id " + id));
        heroService.updateHeroGold(hero, newGold);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/health")
    public ResponseEntity<String> updateHeroHealth(@PathVariable String id, @RequestBody Map<String, Integer> body) {
        final Integer newHealth = body.get("health");
        if (newHealth == null)
            throw new MissingParameterException("health");
        final Hero hero = heroService.getHero(Long.parseLong(id))
                .orElseThrow(() -> new HeroNotFoundException("Hero not found with id " + id));
        heroService.updateHeroHealth(hero, newHealth);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/inventory")
    public ResponseEntity<String> updateHeroInventory(@PathVariable String id, @RequestBody Map<String, List<String>> body) {
        final List<String> newInventory = body.get("inventory");
        if (newInventory == null)
            throw new MissingParameterException("inventory");
        final Hero hero = heroService.getHero(Long.parseLong(id))
                .orElseThrow(() -> new HeroNotFoundException("Hero not found with id " + id));
        heroService.updateHeroInventory(hero, newInventory);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/level")
    public ResponseEntity<String> updateHeroLevel(@PathVariable String id, @RequestBody Map<String, Integer> body) {
        final Integer level = body.get("level");
        if (level == null)
            throw new MissingParameterException("level");
        final Hero hero = heroService.getHero(Long.parseLong(id))
                .orElseThrow(() -> new HeroNotFoundException("Hero not found with id " + id));
        heroService.updateHeroLevel(hero, level);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{id}/position")
    public ResponseEntity<String> updateHeroPosition(@PathVariable String id, @RequestBody Map<String, Long> body) {
        final Long newDungeonId = body.get("dungeonId");
        final Long newRoomId = body.get("roomId");
        if (newDungeonId == null || newRoomId == null)
            throw new MissingParameterException("dungeonId", "roomId");
        final Hero hero = heroService.getHero(Long.parseLong(id))
                .orElseThrow(() -> new HeroNotFoundException("Hero not found with id " + id));
        heroService.updateHeroPosition(hero, newDungeonId, newRoomId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
