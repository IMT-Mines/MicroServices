package fr.imtmines.heroes.service;

import fr.imtmines.heroes.entity.LogEntry;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LogService {

    public static final String APP_NAME = "heroes";

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.log.queue}")
    private String logQueue;

    public LogService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void log(String message) {
        final LogEntry logEntry = new LogEntry(UUID.randomUUID().toString(), System.currentTimeMillis(), APP_NAME, message);
        amqpTemplate.convertAndSend(logQueue, logEntry);
        System.out.println(logEntry);
    }

}
