package fr.imtmines.dungeons.dto;

public class CreateMonsterRequest {
    private Long dungeonId;
    private Long roomId;
    private String monsterId;
    private Long heroId;

    public CreateMonsterRequest(Long dungeonId, Long roomId, String monsterId, Long heroId) {
        this.dungeonId = dungeonId;
        this.roomId = roomId;
        this.monsterId = monsterId;
        this.heroId = heroId;
    }

    public Long getDungeonId() {
        return dungeonId;
    }

    public void setDungeonId(Long dungeonId) {
        this.dungeonId = dungeonId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(String monsterId) {
        this.monsterId = monsterId;
    }

    public Long getHeroId() {
        return heroId;
    }

    public void setHeroId(Long heroId) {
        this.heroId = heroId;
    }
}
