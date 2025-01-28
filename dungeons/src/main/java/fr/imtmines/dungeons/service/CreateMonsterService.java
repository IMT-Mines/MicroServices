package fr.imtmines.dungeons.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CreateMonsterService {
    private final WebClient webClient;

    public CreateMonsterService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void createMonsters(Object body) {
        webClient.post()
            .uri("http://localhost:8081/api/monsters/dungeon")
            .bodyValue(body)
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}