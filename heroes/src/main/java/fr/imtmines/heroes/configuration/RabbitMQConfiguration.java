package fr.imtmines.heroes.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String LOG_QUEUE = "logQueue";

    @Bean
    public Queue logQueue() {
        return new Queue(LOG_QUEUE, true);
    }
}