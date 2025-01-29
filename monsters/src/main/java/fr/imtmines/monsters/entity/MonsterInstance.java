package fr.imtmines.monsters.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class MonsterInstance {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int maxHealth;

    @Column(nullable = false)
    private int gold;

    @Column(nullable = false)
    private String itemDrop;
    private int damage;

    @Column(nullable = false)
    private int health;

    @Column(nullable = false)
    private Long heroId;

    @Column(nullable = false)
    private Long roomId;

    @Column(nullable = false)
    private Long dungeonId;

    @Column(nullable = false)
    private String image;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int Health) {
        this.health = Health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public String getItemDrop() {
        return itemDrop;
    }

    public void setItemDrop(String itemDrop) {
        this.itemDrop = itemDrop;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setHeroId(Long userId) {
        this.heroId = userId;
    }

    public Long getHeroId() {
        return heroId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setDungeonId(Long dungeonId) {
        this.dungeonId = dungeonId;
    }

    public Long getDungeonId() {
        return dungeonId;
    }

}
