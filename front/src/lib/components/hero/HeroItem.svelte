<script lang="ts">
    import type {Hero} from "../../models/hero.model";
    import {gameState} from "../../../stores/game-state.store";

    export let hero: Hero;

    function selectHero() {
        gameState.update(state => ({ ...state, hero }));
    }
</script>

<div class="hero-item" on:click={selectHero}>
    <div class="hero-image">
        <div class="avatar">{hero.name.charAt(0).toUpperCase()}</div>
    </div>
    <div class="hero-info">
        <h3>{hero.name}</h3>
        <p><strong>Or :</strong> {hero.gold}</p>
        <p><strong>Santé :</strong> {hero.health} / {hero.maxHealth}</p>
        <p><strong>Dégâts :</strong> {hero.damage}</p>
        <p><strong>Niveau :</strong> {hero.level}</p>
    </div>
</div>

<style>
    .hero-item {
        display: flex;
        align-items: center;
        background-color: #222222;
        border: 1px solid #ddd;
        border-radius: 8px;
        padding: 10px;
        gap: 15px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        transition: transform 0.2s, box-shadow 0.2s;
        cursor: pointer;
    }

    .hero-item:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

    .hero-image {
        flex-shrink: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        background-color: #4800ff;
        color: white;
        font-size: 20px;
        font-weight: bold;
        text-transform: uppercase;
    }

    .hero-info {
        display: flex;
        flex-direction: column;
        gap: 5px;
    }

    .hero-info h3 {
        margin: 0;
        font-size: 18px;
        color: #ffffff;
    }

    .hero-info p {
        margin: 0;
        font-size: 14px;
        color: #d5d5d5;
    }

    .hero-info p strong {
        color: #ffffff;
    }
</style>
