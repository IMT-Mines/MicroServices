package fr.imtmines.monsters.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class MonsterInstance {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private String name;
    private int maxHealth;
    private int gold;
    private String itemDrop;
    private int damage;

    private int health;
    private String userId;
    private int roomId;
    private int dungeonId;


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

    public String getItemDrop () {
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setDungeonId(int dungeonId) {
        this.dungeonId = dungeonId;
    }

    public int getDungeonId() {
        return dungeonId;
    }

}
