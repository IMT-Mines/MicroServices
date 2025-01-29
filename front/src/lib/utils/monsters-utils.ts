import type { Monster } from "../models/monster.model";
import type {FightResult} from "../models/fight-result.model";

const API_MONSTERS = 'http://localhost:8081/api/monsters';

export async function getMonster(heroId: number) {
    const response = await fetch(`${API_MONSTERS}/heroes/${heroId}`);
    if (!response.ok) return null;
    return await response.json() as Monster;
}

export async function deleteMonster(heroId: number): Promise<boolean> {
    const response = await fetch(`${API_MONSTERS}/heroes/${heroId}`, {method: 'DELETE'});
    return response.ok;
}

export async function attackMonster(heroId: number, damage: number, isHealth: boolean = false) {

    const response = await fetch(`${API_MONSTERS}/attack`,
        {
            method: 'PUT',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({heroId, damage: isHealth ? 0 : damage})
        }
    );
    if (!response.ok) return null
    return await response.json() as FightResult ;
}