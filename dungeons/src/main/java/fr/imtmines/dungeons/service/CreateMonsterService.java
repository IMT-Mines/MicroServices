package fr.imtmines.dungeons.service;

import fr.imtmines.dungeons.dto.MonsterDto;
import fr.imtmines.dungeons.exception.MonsterNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class CreateMonsterService {

    @Value("${monster.api.url}")
    private String monsterApiUrl;

    private final WebClient webClient;

    public CreateMonsterService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void createMonster(MonsterDto monsterDto) {
        try {
            webClient.post()
                    .uri(monsterApiUrl + "api/monsters/create")
                    .bodyValue(monsterDto)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new MonsterNotFoundException("No monster found with id " + monsterDto.monsterId());
        }
    }
}