package fr.imtmines.heroes.service;

import fr.imtmines.heroes.entity.Hero;
import fr.imtmines.heroes.entity.Item;
import fr.imtmines.heroes.entity.Items;
import fr.imtmines.heroes.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private final LogService logService;
    private final HeroRepository heroRepository;

    public HeroService(LogService logService, HeroRepository heroRepository) {
        this.logService = logService;
        this.heroRepository = heroRepository;
    }

    public List<Hero> getHeroes() {
        logService.log("Getting heroes");
        return heroRepository.findAll();
    }

    public void createHero(Hero hero) {
        logService.log("Creating hero " + hero.getName() + " with " + hero.getDamage() + " damage");
        heroRepository.save(hero);
    }

    public Optional<Hero> getHero(long id) {
        logService.log("Getting hero with id " + id);
        return heroRepository.findById(id);
    }

    public void updateHeroGold(Hero hero, Integer newGold) {
        logService.log("Updating hero with id " + hero.getId() + "(" + hero.getName() + ") gold to " + newGold);
        hero.setGold(newGold);
        heroRepository.save(hero);
    }

    public void updateHeroHealth(Hero hero, Integer newHealth) {
        logService.log("Updating hero with id " + hero.getId() + "(" + hero.getName() + ") health to " + newHealth);
        hero.setHealth(newHealth);
        heroRepository.save(hero);
    }

    public void updateHeroInventory(Hero hero, List<String> newInventory) {
        logService.log("Updating hero with id " + hero.getId() + "(" + hero.getName() + ") inventory to " + newInventory);
        final List<Item> items = newInventory.stream().map(Items.ITEMS::get).toList();
        hero.setItems(items);
        heroRepository.save(hero);
    }

    public void updateHeroPosition(Hero hero, long dungeonId, long roomId) {
        logService.log("Updating hero with id " + hero.getId() + "(" + hero.getName() + ") position to dungeon id " + dungeonId + " and room id " + roomId);
        hero.setDungeonId(dungeonId);
        hero.setRoomId(roomId);
        heroRepository.save(hero);
    }

}
