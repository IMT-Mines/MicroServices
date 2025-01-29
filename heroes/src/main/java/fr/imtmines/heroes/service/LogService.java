package fr.imtmines.heroes.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    public static final String APP_NAME = "heros";

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.log.queue}")
    private String logQueue;

    public LogService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void log(String message) {
        final String now = java.time.LocalDateTime.now().toString().replace("T", " ");
        final String finalMessage = String.format("[%s] [%s] %s", now, APP_NAME, message);
        System.out.println(finalMessage);
        amqpTemplate.convertAndSend(logQueue, finalMessage);
    }

}
