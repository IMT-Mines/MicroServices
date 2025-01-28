package fr.imtmines.heroes.service;

import fr.imtmines.heroes.entity.Hero;
import fr.imtmines.heroes.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        logService.log("Creating hero " + hero.getName());
        heroRepository.save(hero);
    }

}
