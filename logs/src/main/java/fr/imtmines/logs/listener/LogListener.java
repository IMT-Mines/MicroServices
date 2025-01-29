package fr.imtmines.logs.listener;

import fr.imtmines.logs.entity.LogEntry;
import fr.imtmines.logs.service.LogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LogListener {

    private final LogService logService;

    public LogListener(LogService logService) {
        this.logService = logService;
    }

    @RabbitListener(queues = "${rabbitmq.log.queue}")
    public void receiveLogMessage(LogEntry logEntry) {
        System.out.println("Received log message: " + logEntry);
        logService.log(logEntry);
    }
}
