<script lang="ts">
    import TopBar from "./TopBar.svelte";
    import Heroes from "./hero/Heroes.svelte";
    import { gameState } from "../../stores/game-state.store";
    import type { Dungeon } from "../models/dungeon.model.js";
    import Fight from "./Fight.svelte";
    import type { Hero } from "../models/hero.model";

    const API_HERO = "http://localhost:8080/api/hero";
    const API_DUNGEONS = "http://localhost:8082/api/dungeons";

    async function startGame() {
        const hero = $gameState.hero;
        if (!hero) return;

        try {
            let dungeon: Dungeon | null = null;

            if (hero.dungeonId && hero.roomId) {
                dungeon = await getHeroDungeon(hero);
            } else {
                dungeon = await selectRandomDungeon();
            }

            if (!dungeon) {
                console.error("Aucun donjon disponible.");
                return;
            }

            hero.roomId = dungeon.rooms[0]?.id;
            hero.dungeonId = dungeon.id;

            const isUpdated = await setHeroDungeon(hero);
            if (!isUpdated) {
                console.error("Échec de la mise à jour du donjon du héros.");
                return;
            }

            gameState.update(state => ({ ...state, dungeon, hero }));
        } catch (error) {
            console.error("Erreur lors du démarrage du jeu :", error);
        }
    }

    async function setHeroDungeon(hero: Hero) {
        try {
            const response = await fetch(`${API_HERO}/${hero.id}/position`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ dungeonId: hero.dungeonId, roomId: hero.roomId }),
            });

            return response.ok;
        } catch (error) {
            console.error("Erreur lors de la mise à jour du héros :", error);
            return false;
        }
    }

    async function getHeroDungeon(hero: Hero) {
        const dungeons = await getDungeons();
        return dungeons?.find(dungeon => dungeon.id === hero.dungeonId) ?? null;
    }

    async function selectRandomDungeon() {
        const dungeons = await getDungeons();
        if (!dungeons || dungeons.length === 0) {
            console.warn("Aucun donjon disponible.");
            return null;
        }
        const randomIndex = Math.floor(Math.random() * dungeons.length);
        return dungeons[randomIndex];
    }

    async function getDungeons() {
        try {
            const response = await fetch(API_DUNGEONS);
            if (!response.ok) {
                console.error("Erreur lors de la récupération des donjons :", response.statusText);
                return null;
            }
            return await response.json() as Dungeon[];
        } catch (error) {
            console.error("Erreur lors de la récupération des donjons :", error);
            return null;
        }
    }
</script>

<TopBar></TopBar>

{#if $gameState.dungeon}
    <Fight></Fight>
{:else}
    <div class="play-container">
        <Heroes></Heroes>
        <button disabled={!$gameState.hero} class="play-button" on:click={startGame}>Jouer</button>
    </div>
{/if}

<style>

    .play-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 50px;
    }

    .play-button {
        padding: 10px 20px;
        font-size: 30px;
        background-color: #bd9549;
        color: #fff;
        border: 4px solid #ffff;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .play-button:hover:not(:disabled) {
        background-color: #b78b38;
    }

    .play-button:disabled {
        background-color: #ccc;
        cursor: not-allowed;
    }
</style>
