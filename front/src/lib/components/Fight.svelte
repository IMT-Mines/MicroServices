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

        currentHero.health -= fightResult.damage;
        monster.health = fightResult.health;

        await setHealth(currentHero.health);

    }

    function onAttack() {
        attack();
        checkLife();
    }

    async function onHeal() {
        if (!currentHero || !monster) return;

        const randomHealthBonus = Math.floor(Math.random() * 10);
        const mappedHealthBonus = currentHero.health + randomHealthBonus > currentHero.maxHealth ? 0 : randomHealthBonus;

        await setHealth(currentHero.health + mappedHealthBonus);

        await attack(true);
        checkLife();
    }

    async function setHealth(health: number) {
        if (!currentHero) return;

        const response = await fetch(`${API_HEROES}/${currentHero.id}/health`,
            {
                method: 'PUT',
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({health})
            }
        );

        if (!response.ok) return;

        currentHero.health = health;
    }

    async function setPosition(dungeonId: number | undefined, roomId: number | undefined) {
        if (!currentHero) return;

        const response = await fetch(`${API_HEROES}/${currentHero.id}/position`,
            {
                method: 'PUT',
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({dungeonId, roomId})
            }
        );

        if (!response.ok) return;

        gameState.update(state => ({...state, dungeon: null}));
    }

    async function getMonster() {
        const response = await fetch(`${API_MONSTERS}/${getRoom()?.monsterId}`);
        if (!response.ok) return null;
        return await response.json() as Monster;
    }

    function checkLife() {
        if (!currentHero || !monster) return;

        if (currentHero.health <= 0) {
            alert("Vous avez perdu !");
            setHealth(currentHero.maxHealth);
            setPosition(undefined, undefined);
        }
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
            <div class="lifebox-player">
                <LifeBox life="{currentHero.health}" maxLife="{currentHero.maxHealth}" name="{currentHero.name}"
                         level="{currentHero.level}"></LifeBox>
            </div>
            <img class="player-sprite" src="src/assets/hero.png" alt="hero">
        </div>

        <div class="monster">
            <img class="back monster-back" src="src/assets/back2.png" alt="back">
            <div class="lifebox-monster">
                <LifeBox life="{monster.health ?? monster.maxHealth }" maxLife="{monster.maxHealth}"
                         name="{monster.name}"></LifeBox>
            </div>
            <img class="monster-sprite" src={`src/assets/${monster.name}.png`} alt="monster">
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
        position: absolute;
        bottom: 10%;
        left: 10%;
    }

    .monster {
        position: absolute;
        top: 10%;
        right: 20%;
    }


    .player-back {
        bottom: 0;
    }

    .lifebox-player {
        transform: translateX(-25%);
    }

    .player-sprite {
        transform: translateX(50%) translateY(-20%);
    }

    .monster-back {
        bottom: 0;
    }

    .lifebox-monster {
        transform: translateX(-25%);
    }

    .monster-sprite {
        transform: translateX(70%) translateY(-20%);
        width: 100%;
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

    .attack:hover {
        background-color: #f697a1;
    }

    .heal:hover {
        background-color: #3080a8;
    }
</style>