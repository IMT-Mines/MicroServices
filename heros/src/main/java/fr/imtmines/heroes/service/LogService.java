package fr.imtmines.heroes.service;

import org.springframework.stereotype.Service;

@Service
public class LogService {

    public static final String APP_NAME = "heros";

    public void log(String message) {
        final String now = java.time.LocalDateTime.now().toString().replace("T", " ");
        final String finalMessage = String.format("[%s] [%s] %s", now, APP_NAME, message);
        System.out.println(finalMessage);
        // TODO: send the message through rabbitmq
    }

}
