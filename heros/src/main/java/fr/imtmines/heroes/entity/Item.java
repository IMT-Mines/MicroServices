package fr.imtmines.heroes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "hero")
    private Hero hero;

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Hero getHero() {
        return hero;
    }

    public Item setHero(Hero hero) {
        this.hero = hero;
        return this;
    }
}
