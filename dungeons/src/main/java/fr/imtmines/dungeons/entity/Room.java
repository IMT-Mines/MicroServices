package fr.imtmines.dungeons.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Room")
public class Room {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "monsterId")
    private String monsterId;

    @ManyToOne
    private Dungeon dungeon;

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

    public String getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(String monsterId) {
        this.monsterId = monsterId;
    }
}