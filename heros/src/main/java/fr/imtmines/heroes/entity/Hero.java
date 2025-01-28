package fr.imtmines.heroes.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Heroes")
public class Hero {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private int level;

    @Column
    private int gold;

    @Column
    private int maxHealth;

    @Column
    private int health;

    @Column
    private int damage;

    @OneToMany(mappedBy = "hero")
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public Hero setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Hero setName(String name) {
        this.name = name;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public Hero setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getGold() {
        return gold;
    }

    public Hero setGold(int gold) {
        this.gold = gold;
        return this;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Hero setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        return this;
    }

    public int getHealth() {
        return health;
    }

    public Hero setHealth(int health) {
        this.health = health;
        return this;
    }

    public int getDamage() {
        return damage;
    }

    public Hero setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public Hero setItems(List<Item> items) {
        this.items = items;
        return this;
    }
}
