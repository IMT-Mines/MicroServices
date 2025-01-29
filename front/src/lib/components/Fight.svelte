<script lang="ts">
    import {onMount} from "svelte";
    import type {Dungeon} from "../models/dungeon.model";
    import {gameState} from "../../stores/game-state.store";
    import type {Hero} from "../models/hero.model";
    import type {Monster} from "../models/monster.model";

    let currentDungeon: Dungeon | null = $state(null);
    let currentHero: Hero | null = $state(null);
    let monster: Monster | null = $state(null);

    onMount(async () => {
        gameState.subscribe(state => {
            currentDungeon = state.dungeon;
            currentHero = state.hero;
            monster = state.monster;
        });

        monster = await getMonster();
        gameState.update(state => ({...state, monster}));
    });

    function getRoom() {
        if (!currentDungeon || !currentHero) return null;

        return currentDungeon.rooms.find(room => room.id === currentHero!.roomId);
    }

    function onAttack() {
        console.log('Attaque');
    }

    function onHeal() {
        console.log('Soigner');
    }

    async function getMonster() {
        return new Promise<Monster>((resolve) => {
            setTimeout(() => {
                resolve({
                    id: 1,
                    name: 'Goblin',
                    health: 100,
                    gold: 10,
                    itemDrop: "caillou"
                });
            }, 1000);
        });

        // const response = await fetch('http://localhost:8082/api/monsters/' + getRoom()?.monsterId);
        // if (!response.ok) return null;
        // return await response.json() as Monster;
    }

</script>

{#if currentDungeon && currentHero && monster}
    <div class="dungeon">
        <h2>Donjon : {currentDungeon.name}</h2>
        <p>{getRoom()?.name}</p>
    </div>
    <div class="player"></div>

    <div class="monster">
        <p class="monster-health">{monster.health}</p>
        <p class="monster-name">{monster.name}</p>
    </div>

    <div class="spell-box">
        <button class="attack" on:click={onAttack}>Attaquer</button>
        <button class="heal" on:click={onHeal}>Soigner</button>
    </div>
{:else}
    <p>Chargement...</p>
{/if}

<style>
    h2 {
        margin: 0;
    }

    .dungeon {
        background-color: #1c1c1c;
        width: fit-content;
        padding: 10px 20px;
        border-radius: 0 0 10px 0;
    }

    .player {
        background-color: #ededed;
        width: 100px;
        height: 100px;
        border-radius: 50%;
        position: absolute;
        top: 70%;
        left: 20%;
    }

    .monster {
        background-color: #ff0000;
        width: 100px;
        height: 100px;
        border-radius: 50%;
        position: absolute;
        top: 40%;
        right: 20%;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    .monster-name {
        position: relative;
        top: -80%;
    }

    .monster-health {
        margin: 0;
        font-size: 20px;
        font-weight: bold;
    }

    .spell-box {
        display: flex;
        gap: 10px;
        position: absolute;
        bottom: 20px;
        left: 50%;
        transform: translateX(-50%);
    }

    .attack {
        background-color: #af4c4c;
    }

    .heal {
        background-color: #5871b1;
    }
</style>