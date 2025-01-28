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

    @Column(nullable = false)
    private int level = 0;

    @Column(nullable = false)
    private int gold = 0;

    @Column(nullable = false)
    private int maxHealth;

    @Column(nullable = false)
    private int health;

    @Column(nullable = false)
    private int damage;

    @Column
    long dungeonId;

    @Column
    long roomId;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public long getDungeonId() {
        return dungeonId;
    }

    public void setDungeonId(long dungeonId) {
        this.dungeonId = dungeonId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items.clear();
        items.forEach(item -> item.setHero(this));
        this.items.addAll(items);
    }

}
