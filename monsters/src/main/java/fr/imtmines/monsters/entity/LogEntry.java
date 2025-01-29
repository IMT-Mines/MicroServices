package fr.imtmines.monsters.entity;

import java.io.Serializable;

public record LogEntry(
        String id,
        long timestamp,
        String applicationName,
        String message
) implements Serializable {

    @Override
    public String toString() {
        return "[" + timestamp + "] [" + applicationName + "] " + message;
    }
}
