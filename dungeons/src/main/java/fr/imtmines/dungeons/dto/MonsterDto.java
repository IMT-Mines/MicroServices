package fr.imtmines.dungeons.dto;

public record MonsterDto(
        long dungeonId,
        long roomId,
        long id,
        long heroId
) {
}
