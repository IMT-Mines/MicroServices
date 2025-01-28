package fr.imtmines.heroes.controller;

import fr.imtmines.heroes.entity.Hero;
import fr.imtmines.heroes.service.HeroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public void addHero(Hero hero) {
        heroService.createHero(hero);
    }

}
