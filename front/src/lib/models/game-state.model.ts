import type {Hero} from "./hero.model";

export interface GameState {
    hero: Hero | null;
}