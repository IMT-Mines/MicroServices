import {writable} from "svelte/store";
import type {GameState} from "../lib/models/game-state.model";

export const gameState = writable<GameState>({
    hero: null,
    dungeon: null,
});