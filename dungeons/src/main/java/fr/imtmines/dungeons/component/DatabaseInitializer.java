package fr.imtmines.dungeons.component;

import fr.imtmines.dungeons.entity.Dungeon;
import fr.imtmines.dungeons.entity.Room;
import fr.imtmines.dungeons.repository.DungeonRepository;
import fr.imtmines.dungeons.repository.RoomRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer {
    private final DungeonRepository dungeonRepository;
    private final RoomRepository roomRepository;

    public DatabaseInitializer(DungeonRepository dungeonRepository, RoomRepository roomRepository) {
        this.dungeonRepository = dungeonRepository;
        this.roomRepository = roomRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDatabase() {
        for (int i = 0; i < 5; i++) {
            Dungeon dungeon = new Dungeon();
            dungeon.setName("Donjon " + i);

            List<Room> rooms = new ArrayList<>();

            for (int j = 0; j < 5; j++) {
                Room room = new Room();
                room.setName("Salle " + j);
                room.setMonsterId("Monstre " + j);
                room.setDungeon(dungeon);

                rooms.add(room);
            }

            dungeon.setRooms(rooms);

            dungeonRepository.save(dungeon);
        }
    }
}
