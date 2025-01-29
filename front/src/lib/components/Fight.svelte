<script lang="ts">
    import {onMount} from "svelte";
    import type {Dungeon} from "../models/dungeon.model";
    import {gameState} from "../../stores/game-state.store";
    import type {Hero} from "../models/hero.model";
    import type {Monster} from "../models/monster.model";
    import LifeBox from "./LifeBox.svelte";
    import {attackMonster, deleteMonster, getMonster} from "../utils/monsters-utils";
    import {createMonsterFight} from "../utils/dungeons-utils";
    import {setHeroGold, setHeroHealth, setHeroInventory, setHeroLevel, setHeroPosition} from "../utils/heroes-utils";

    let currentDungeon: Dungeon | null = $state(null);
    let currentHero: Hero | null = $state(null);
    let currentMonster: Monster | null = $state(null);

    onMount(async () => {
        gameState.subscribe(state => {
            currentDungeon = state.dungeon;
            currentHero = state.hero;
            currentMonster = state.monster;
        });
        if (!currentDungeon || !currentHero) return null;

        await initRoomFight();
    });

    async function initRoomFight() {
        if (!currentHero || !currentDungeon) return;

        const response = await createMonsterFight(currentHero.dungeonId!, currentHero.roomId!, currentHero!.id!);
        currentMonster = await getMonster(currentHero!.id!);
        if (!response || !currentMonster) return;

        gameState.update(state => ({...state, monster: currentMonster}));
    }

    function getRoom() {
        if (!currentDungeon || !currentHero) return null;

        return currentDungeon.rooms.find(room => room.id === currentHero!.roomId);
    }

    async function attack(isHealth = false) {
        if (!currentHero || !currentMonster) return;
        const damage = Math.floor(Math.random() * (currentHero?.damage / 2) + currentHero?.damage);

        const fightResult = await attackMonster(currentHero.id!, damage, isHealth);
        if (!fightResult) return;

        currentHero.health -= fightResult.damage;
        currentMonster.health = fightResult.health;

        await updateHeroHealth(currentHero.health);
    }

    async function onAttack() {
        await attack();
        await verifyFight();
    }

    async function onHeal() {
        if (!currentHero || !currentMonster) return;

        const randomHealthBonus = Math.floor(Math.random() * 10);
        const mappedHealthBonus = currentHero.health + randomHealthBonus > currentHero.maxHealth ? 0 : randomHealthBonus;

        await updateHeroHealth(currentHero.health + mappedHealthBonus);

        await attack(true);
        await verifyFight();
    }

    async function verifyFight() {
        if (!currentHero || !currentMonster) return;

        if (currentHero.health <= 0) {
            alert("Vous avez perdu !");
            await leftDonjon();
        } else if (currentMonster.health <= 0) {
            await deleteMonster(currentHero.id!);
            console.log(currentDungeon);
            const nextRoom = currentDungeon!.rooms.find(room => room.id === currentHero!.roomId! + 1);
            if (!nextRoom) {
                alert("Vous avez fini le donjon !");
                await updateHeroLevel(currentHero.level + 1);
                await updateHeroGold(currentMonster.gold + currentHero.gold);
                currentHero.inventory.push(currentMonster.itemDrop)
                await updateHeroInventory(currentHero.inventory);
                await leftDonjon();
                return;
            } else {
                alert("Vous avez gagné, vous passez à la salle suivante !");
                await updateHeroGold(currentMonster.gold + currentHero.gold);
                currentHero.inventory.push(currentMonster.itemDrop)
                await updateHeroInventory(currentHero.inventory);
                await updateHeroPosition(currentHero.dungeonId, nextRoom.id);
                await initRoomFight();
            }
        }
    }

    async function updateHeroLevel(level: number) {
        if (!currentHero) return;
        const response = await setHeroLevel(currentHero.id!, level);
        if (!response) return;
        currentHero.level = level;
        gameState.update(state => ({...state, hero: currentHero}));
    }

    async function updateHeroInventory(inventory: string[]) {
        if (!currentHero) return;
        const response = await setHeroInventory(currentHero.id!, inventory);
        if (!response) return;
        currentHero.inventory = inventory;
        gameState.update(state => ({...state, hero: currentHero}));
    }

    async function updateHeroGold(gold: number) {
        if (!currentHero) return;
        const response = await setHeroGold(currentHero.id!, gold);
        if (!response) return;
        currentHero.gold = gold;
        gameState.update(state => ({...state, hero: currentHero}));
    }

    async function updateHeroPosition(dungeonId: number, roomId: number) {
        if (!currentHero) return;
        const response = await setHeroPosition(currentHero.id!, dungeonId, roomId);
        if (!response) return;
        currentHero.dungeonId = dungeonId;
        currentHero.roomId = roomId;
        gameState.update(state => ({...state, hero: currentHero}));
    }

    async function updateHeroHealth(health: number) {
        if (!currentHero) return;
        const response = await setHeroHealth(currentHero.id!, health);
        if (!response) return;
        currentHero.health = health;
        gameState.update(state => ({...state, hero: currentHero}));
    }

    async function leftDonjon() {
        if (!currentHero) return;

        await updateHeroHealth(currentHero.maxHealth);
        await updateHeroPosition(-1, -1);
        await deleteMonster(currentHero.id!);
        gameState.update(state => ({...state, monster: null, dungeon: null}));
    }

</script>

<div class="fight">
    {#if currentDungeon && currentHero && currentMonster}
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
                <LifeBox life="{currentMonster.health ?? currentMonster.maxHealth }"
                         maxLife="{currentMonster.maxHealth}"
                         name="{currentMonster.name}"></LifeBox>
            </div>
            <img class="monster-sprite" src={`src/assets/${currentMonster.name}.png`} alt="monster">
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