package fr.imtmines.monsters.dto;

public record MonsterDto(
        long monsterId,
        long heroId,
        long dungeonId,
        long roomId
) {
}
