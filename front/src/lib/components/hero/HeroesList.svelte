<script lang="ts">
    import HeroItem from "./HeroItem.svelte";
    import {onMount} from "svelte";
    import type {Hero} from "../../models/hero.model";

    const API_HEROES = 'http://localhost:8080/api/heroes';

    let heroes: Hero[] = $state([]);

    onMount(async () => {
        heroes = await getHeroes();
    });

    async function getHeroes() {
        const response = await fetch(API_HEROES);
        if (!response.ok) return [];
        return await response.json() as Hero[];
    }
</script>

<h2>Sélection du héro</h2>
<div class="heroes-list">
    {#each heroes as hero}
        <HeroItem {hero}></HeroItem>
    {/each}
</div>

<style>

    .heroes-list {
        display: flex;
        flex-direction: column;
        gap: 10px;
        height:  500px;
        overflow-y: auto;
    }
</style>