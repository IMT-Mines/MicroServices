package fr.imtmines.dungeons.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Dungeons")
public class Dungeon {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "dungeon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;

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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
