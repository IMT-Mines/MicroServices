import type {Dungeon} from "../models/dungeon.model";

const API_DUNGEONS = 'http://localhost:8082/api/dungeons';

export async function createMonsterFight(dungeonId: number, roomId: number, heroId: number): Promise<boolean> {
    const response = await fetch(`${API_DUNGEONS}/${dungeonId}/rooms/${roomId}?heroId=${heroId}`);
    return response.ok;
}

export async function getDungeons() {
    const response = await fetch(API_DUNGEONS);
    if (!response.ok) return null;
    return await response.json() as Dungeon[];
}