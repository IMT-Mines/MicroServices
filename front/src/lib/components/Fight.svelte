<script lang="ts">
    import {onMount} from "svelte";
    import type {Dungeon} from "../models/dungeon.model";
    import {gameState} from "../../stores/game-state.store";
    import type {Hero} from "../models/hero.model";
    import type {Monster} from "../models/monster.model";
    import type {FightResult} from "../models/fight-result.model";
    import LifeBox from "./LifeBox.svelte";

    let currentDungeon: Dungeon | null = $state(null);
    let currentHero: Hero | null = $state(null);
    let monster: Monster | null = $state(null);

    const API_MONSTERS = 'http://localhost:8081/api/monsters';
    const API_DUNGEONS = 'http://localhost:8082/api/dungeons';
    const API_HEROES = 'http://localhost:8080/api/heroes';

    onMount(async () => {
        gameState.subscribe(state => {
            currentDungeon = state.dungeon;
            currentHero = state.hero;
            monster = state.monster;
        });

        await createMonsterFight();

        monster = await getMonster();
        gameState.update(state => ({...state, monster}));
    });

    async function createMonsterFight() {
        const response = await fetch(`${API_DUNGEONS}/${currentDungeon?.id}/rooms/${currentHero?.roomId}?heroId=${currentHero?.id}`);
        const monster = await response.json();
        console.log(monster);
    }

    function getRoom() {
        if (!currentDungeon || !currentHero) return null;

        return currentDungeon.rooms.find(room => room.id === currentHero!.roomId);
    }

    function onAttack() {
        attack();
    }

    async function attack(isHealth = false) {
        if (!currentHero || !monster) return;

        const damage = Math.floor(Math.random() * (currentHero?.damage / 2) + currentHero?.damage);
        const response = await fetch(`${API_MONSTERS}/attack`,
            {
                method: 'PUT',
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({heroId: currentHero?.id, damage: isHealth ? 0 : damage})
            }
        );
        if (!response.ok) return;
        const fightResult = await response.json() as FightResult;



        // TODO UPDATE MONSTER HEALTH AND PLAYER IN DB


    }

    async function onHeal() {
        if (!currentHero || !monster) return;

        const randomHealthBonus = Math.floor(Math.random() * 10);
        const mappedHealthBonus = currentHero.health + randomHealthBonus > currentHero.maxHealth ? 0 : randomHealthBonus;

        const response = await fetch(`${API_HEROES}/${currentHero.id}/health`,
            {
                method: 'PUT',
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({health: currentHero.health + mappedHealthBonus})
            }
        );
        currentHero.health += mappedHealthBonus;

        if (!response.ok) return;

        await attack(true);
    }

    async function getMonster() {
        const response = await fetch(`${API_MONSTERS}/${getRoom()?.monsterId}`);
        if (!response.ok) return null;
        return await response.json() as Monster;
    }

</script>

<div class="fight">
    {#if currentDungeon && currentHero && monster}
        <div class="dungeon-container">
            <div class="dungeon">
                <h2>Donjon : {currentDungeon.name}</h2>
                <p>{getRoom()?.name}</p>
            </div>
        </div>
        <div class="player">
            <img class="back player-back" src="src/assets/back2.png" alt="back">
            <img class="player-sprite" src="src/assets/hero.png" alt="hero">
            <div class="player-lifebox">
                <LifeBox life="{currentHero.health}" maxLife="{currentHero.maxHealth}" name="{currentHero.name}"
                         level="{currentHero.level}"></LifeBox>
            </div>
        </div>

        <div class="monster">
            <img class="back monster-back" src="src/assets/back2.png" alt="back">
            <img class="monster-sprite" src={`src/assets/${monster.name}.png`} alt="monster">
            <LifeBox life="{monster.health ?? monster.maxHealth }" maxLife="{monster.maxHealth}"
                     name="{monster.name}"></LifeBox>
        </div>

        <div class="spell-box">
            <button class="attack" onclick={onAttack}>Attaquer</button>
            <button class="heal" onclick={onHeal}>Soigner</button>
        </div>
    {:else}
        <p>Chargement...</p>
    {/if}
</div>

<style>

    .fight {
        overflow: hidden;
        height: 94vh;
        position: relative;
    }

    h2 {
        margin: 0;
    }

    .dungeon-container {
        display: flex;
        justify-content: center;
    }

    .dungeon {
        background-color: #ebe9c3;
        color: #000;
        border: 4px solid #000;
        border-top: none;
        width: fit-content;
        padding: 10px 20px;
        border-radius: 0 0 10px 0;
    }

    .player {
        position: relative;
        top: 70%;
        left: 20%;
    }

    .player-lifebox {
        position: absolute;
    }

    .player-sprite {
        position: absolute;
        bottom: 0;
        left: 10%;
    }

    .monster {
        position: absolute;
        top: 40%;
        right: 20%;
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
        background-color: #f7a7af;
        border: 4px solid #fff;
        font-size: 25px;
        padding: 8px;
    }

    .heal {
        background-color: #308abe;
        border: 4px solid #fff;
        font-size: 25px;
        padding: 8px;
    }

    .back {
        position: absolute;
        z-index: -1;
        width: 500px;
        filter: drop-shadow(0 0 10px rgba(0, 0, 0, 0.5));
    }

    .player-back {
        bottom: -80%;
        transform: scale(1.5);
    }


    .attack:hover {
        background-color: #f697a1;
    }

    .heal:hover {
        background-color: #3080a8;
    }
</style>