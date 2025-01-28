<script lang="ts">
    import {gameState} from "../../stores/game-state.store";
    import type {Hero} from "../models/hero.model";
    import {onMount} from "svelte";
    import {faDollarSign, faHeart, faStar} from "@fortawesome/free-solid-svg-icons";
    import Fa from "svelte-fa";

    let currentHero: Hero | null;

    onMount(() => {
        gameState.subscribe(state => {
            currentHero = state.hero;
        });
    });
</script>

<div class="navbar">
    {#if currentHero}
        <span class="player-name">{currentHero.name}</span>

        <div class="player-stats">
            <div class="health-box">
                <Fa icon="{faHeart}"></Fa>
                <span>{currentHero.health} / {currentHero.maxHealth}</span>
            </div>

            <div class="gold-box">
                <Fa icon="{faDollarSign}"></Fa>
                <span>{currentHero.gold}</span>
            </div>

            <div class="xp-box">
                <Fa icon="{faStar}"></Fa>
                <span>{currentHero.level}</span>
            </div>
        </div>
    {:else}
        <span>Aucun héros choisi</span>
    {/if}
</div>

<style>
    .navbar {
        height: 60px;
        background-color: #1c1c1c;
        color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 20px;
        font-family: Arial, sans-serif;
    }

    .player-name {
        font-size: 20px;
        font-weight: bold;
    }

    .player-stats {
        display: flex;
        gap: 10px;
    }

    .xp-box, .gold-box, .health-box {
        display: flex;
        align-items: center;
        gap: 5px;
        border-radius: 10px;
        padding: 5px 10px;

    }

    .xp-box {
        background-color: #f1c40f;
    }

    .gold-box {
        background-color: #f39c12;
    }

    .health-box {
        background-color: #e74c3c;
    }
</style>