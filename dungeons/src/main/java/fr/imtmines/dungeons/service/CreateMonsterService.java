package fr.imtmines.dungeons.service;

import fr.imtmines.dungeons.dto.MonsterDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CreateMonsterService {

    @Value("${monster.api.url}")
    private String monsterApiUrl;

    private final WebClient webClient;

    public CreateMonsterService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void createMonster(MonsterDto monsterDto) {
        webClient.post()
                .uri(monsterApiUrl + "api/monsters/dungeon")
                .bodyValue(monsterDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}