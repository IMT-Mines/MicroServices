package fr.imtmines.dungeons.component;

import fr.imtmines.dungeons.entity.Dungeon;
import fr.imtmines.dungeons.entity.Room;
import fr.imtmines.dungeons.repository.DungeonRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseInitializer {
    private final DungeonRepository dungeonRepository;

    private static final int DUNGEONS = 5;
    private static final int ROOMS = 5;

    public DatabaseInitializer(DungeonRepository dungeonRepository) {
        this.dungeonRepository = dungeonRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDatabase() {
        Random random = new Random();

        for (int i = 0; i < DUNGEONS; i++) {
            Dungeon dungeon = new Dungeon();
            dungeon.setName("Donjon " + i);

            List<Room> rooms = new ArrayList<>();

            for (long j = 0; j < ROOMS; j++) {
                Room room = new Room();
                room.setName("Salle " + j);
                room.setMonsterId((long) random.nextInt(3) + (j == ROOMS - 1 ? 4 : 1));
                room.setDungeon(dungeon);
                rooms.add(room);
            }

            dungeon.setRooms(rooms);

            dungeonRepository.save(dungeon);
        }
    }
}
