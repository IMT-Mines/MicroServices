package fr.imtmines.dungeons.dto;

public record MonsterDto(
        long monsterId,
        long heroId,
        long dungeonId,
        long roomId
) {
}
