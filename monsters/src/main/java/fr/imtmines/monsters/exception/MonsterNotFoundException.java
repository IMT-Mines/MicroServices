package fr.imtmines.monsters.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Monster not found")
public class MonsterNotFoundException extends RuntimeException {
    public MonsterNotFoundException(String message) {
        super(message);
    }
}