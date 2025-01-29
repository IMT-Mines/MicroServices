package fr.imtmines.logs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "logs")
public record LogEntry(
        @Id String id,
        long timestamp,
        String applicationName,
        String message
) {

    @Override
    public String toString() {
        return "[" + timestamp + "] [" + applicationName + "] " + message;
    }
}
