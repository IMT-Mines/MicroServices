<script lang="ts">
    import TopBar from "./TopBar.svelte";
    import Heroes from "./hero/Heroes.svelte";
    import {gameState} from "../../stores/game-state.store";
    import type {Dungeon} from "../models/dungeon.model.js";
    import Fight from "./Fight.svelte";
    import type {Hero} from "../models/hero.model";

    async function startGame() {
        const hero = $gameState.hero;
        if (!hero) return;

        let dungeon: Dungeon | null = null;
        if (hero.dungeonId && hero.roomId) {
            dungeon = await getHeroDungeon(hero);
        } else {
            dungeon = await selectRandomDungeon();
        }

        if (!dungeon) return;
        hero.roomId = dungeon.rooms[0].id;
        hero.dungeonId = dungeon.id;

        const isUpdated = await setHeroDungeon(hero);
        if (!isUpdated) return;
        gameState.update(state => ({...state, dungeon, hero}));
    }

    async function setHeroDungeon(hero: Hero) {
        const response = await fetch('http://localhost:8080/api/hero/' + hero.id + '/position', {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({dungeonId: hero.dungeonId, roomId: hero.roomId})
        });

        return response.ok;
    }

    async function getHeroDungeon(hero: Hero) {
        const dungeons = await getDungeons();
        if (!dungeons) return null;

        return dungeons.find(dungeon => dungeon.id === hero.dungeonId) ?? null;
    }

    async function selectRandomDungeon() {
        const dungeons = await getDungeons();
        const randomIndex = Math.floor(Math.random() * dungeons.length);
        return dungeons[randomIndex];
    }

    async function getDungeons() {
        const response = await fetch('http://localhost:8082/api/dungeons');
        if (!response.ok) return [];
        return await response.json() as Dungeon[];
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
        background-color: #4800ff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .play-button:disabled {
        background-color: #ccc;
        cursor: not-allowed;
    }
</style>