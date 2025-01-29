package fr.imtmines.heroes.service;

import fr.imtmines.heroes.entity.Hero;
import fr.imtmines.heroes.repository.HeroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class HeroService {

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public List<Hero> getHeroes() {
        return heroRepository.findAll();
    }

    public void createHero(Hero hero) {
        hero.setId(null);
        heroRepository.save(hero);
    }

    public Optional<Hero> getHero(long id) {
        return heroRepository.findById(id);
    }

    public void updateHeroGold(Hero hero, Integer newGold) {
        hero.setGold(newGold);
        heroRepository.save(hero);
    }

    public void updateHeroHealth(Hero hero, Integer newHealth) {
        hero.setHealth(newHealth);
        heroRepository.save(hero);
    }

    public void updateHeroInventory(Hero hero, List<String> newInventory) {
        hero.setInventory(newInventory);
        heroRepository.save(hero);
    }

    public void updateHeroPosition(Hero hero, long dungeonId, long roomId) {
        hero.setDungeonId(dungeonId);
        hero.setRoomId(roomId);
        heroRepository.save(hero);
    }

    public void updateHeroLevel(Hero hero, Integer level) {
        hero.setLevel(level);
        heroRepository.save(hero);
    }
}
