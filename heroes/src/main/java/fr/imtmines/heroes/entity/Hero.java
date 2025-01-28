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
    private long dungeonId;

    @Column
    private long roomId;

    @ElementCollection
    @CollectionTable(name = "HeroItems", joinColumns = @JoinColumn(name = "hero_id"))
    @Column(name = "item")
    private List<String> inventory;

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

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", gold=" + gold +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                ", damage=" + damage +
                ", dungeonId=" + dungeonId +
                ", roomId=" + roomId +
                ", items=" + inventory +
                '}';
    }
}
