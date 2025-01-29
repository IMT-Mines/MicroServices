package fr.imtmines.dungeons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Dungeon not found")
public class DungeonNotFoundException extends RuntimeException {
    public DungeonNotFoundException(String message) {
        super(message);
    }
}