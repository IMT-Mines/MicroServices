package fr.imtmines.dungeons.service;

import fr.imtmines.dungeons.dto.MonsterDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CreateMonsterService {

    private final LogService logService;
    @Value("${monster.api.url}")
    private String monsterApiUrl;

    private final WebClient webClient;

    public CreateMonsterService(WebClient webClient, LogService logService) {
        this.webClient = webClient;
        this.logService = logService;
    }

    public void createMonsters(MonsterDto monsterDto) {
        logService.log("Creating monster with body: " + monsterDto);
        webClient.post()
            .uri(monsterApiUrl + "api/monsters/dungeon")
            .bodyValue(monsterDto)
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}