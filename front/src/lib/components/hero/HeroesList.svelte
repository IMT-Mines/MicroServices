<script lang="ts">
    import HeroItem from "./HeroItem.svelte";
    import {onMount} from "svelte";
    import type {Hero} from "../../models/hero.model";

    let heroes: Hero[] = $state([]);


    onMount(async () => {
        heroes = await getHeroes();
    });

    async function getHeroes() {
        const response = await fetch('http://localhost:8080/heroes');
        if (!response.ok) return [];
        return await response.json() as Hero[];
        // return new Promise<Hero[]>((resolve) => {
        //     resolve(stubHeroes);
        // });
    }
</script>

<h2>Selection du héro</h2>
<div class="heroes-list">
    {#each heroes as hero}
        <HeroItem {hero}></HeroItem>
    {/each}
</div>

<style>

</style>
