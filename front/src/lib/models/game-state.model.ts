import type {Hero} from "./hero.model";
import type {Dungeon} from "./dungeon.model";
import type {Monster} from "./monster.model";

export interface GameState {
    hero: Hero | null;
    dungeon: Dungeon | null;
    monster: Monster | null;
}