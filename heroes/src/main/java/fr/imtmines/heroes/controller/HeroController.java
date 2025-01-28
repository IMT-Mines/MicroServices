package fr.imtmines.heroes.controller;

import fr.imtmines.heroes.entity.Hero;
import fr.imtmines.heroes.excepetion.HeroNotFoundException;
import fr.imtmines.heroes.service.HeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/heroes")
    public List<Hero> getHeroes() {
        return heroService.getHeroes();
    }

    @PostMapping("/heroes")
    public ResponseEntity<String> addHero(Hero hero) {
        heroService.createHero(hero);
        return ResponseEntity.ok().body("Hero created");
    }

    @GetMapping("/hero/{id}")
    public ResponseEntity<Hero> getHero(@PathVariable String id) {
        final Hero hero = heroService.getHero(Long.parseLong(id))
                .orElseThrow(() -> new HeroNotFoundException("Hero not found with id " + id));
        return ResponseEntity.ok(hero);
    }

    @PutMapping("/hero/{id}/gold")
    public ResponseEntity<String> updateHeroGold(@PathVariable String id, @RequestBody Map<String, Integer> body) {
        final Integer newGold = body.get("gold");
        if (newGold == null)
            return ResponseEntity.badRequest().body("Request body must contain a 'gold' field");
        final Hero hero = heroService.getHero(Long.parseLong(id))
                .orElseThrow(() -> new HeroNotFoundException("Hero not found with id " + id));
        heroService.updateHeroGold(hero, newGold);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/hero/{id}/health")
    public ResponseEntity<String> updateHeroHealth(@PathVariable String id, @RequestBody Map<String, Integer> body) {
        final Integer newHealth = body.get("health");
        if (newHealth == null)
            return ResponseEntity.badRequest().body("Request body must contain a 'health' field");
        final Hero hero = heroService.getHero(Long.parseLong(id))
                .orElseThrow(() -> new HeroNotFoundException("Hero not found with id " + id));
        heroService.updateHeroHealth(hero, newHealth);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/hero/{id}/inventory")
    public ResponseEntity<String> updateHeroInventory(@PathVariable String id, @RequestBody Map<String, List<String>> body) {
        final List<String> newInventory = body.get("inventory");
        if (newInventory == null)
            return ResponseEntity.badRequest().body("Request body must contain a 'inventory' field");
        final Hero hero = heroService.getHero(Long.parseLong(id))
                .orElseThrow(() -> new HeroNotFoundException("Hero not found with id " + id));
        heroService.updateHeroInventory(hero, newInventory);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/hero/{id}/position")
    public ResponseEntity<String> updateHeroPosition(@PathVariable String id, @RequestBody Map<String, Long> body) {
        final Long newDungeonId = body.get("dungeonId");
        final Long newRoomId = body.get("roomId");
        if (newDungeonId == null || newRoomId == null)
            return ResponseEntity.badRequest().body("Request body must contain both 'dungeonId' and 'roomId' fields");
        final Hero hero = heroService.getHero(Long.parseLong(id))
                .orElseThrow(() -> new HeroNotFoundException("Hero not found with id " + id));
        heroService.updateHeroPosition(hero, newDungeonId, newRoomId);
        return ResponseEntity.ok().build();
    }

}
