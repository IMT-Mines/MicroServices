<script lang="ts">
    import type { Hero } from "../../models/hero.model";

    const API_HERO = 'http://localhost:8080/api/heroes';

    let newHero: Partial<Hero> = {
        name: "",
        level: 1,
        gold: 0,
        maxHealth: 100,
        health: 100,
        damage: 10,
        inventory: []
    };

    async function addHero() {
        if (!newHero.name || !newHero.level || !newHero.maxHealth || !newHero.damage) {
            return;
        }

        const createdHero: Hero = {
            name: newHero.name,
            level: newHero.level,
            gold: newHero.gold || 0,
            maxHealth: newHero.maxHealth,
            health: newHero.maxHealth,
            damage: newHero.damage,
            inventory: newHero.inventory || []
        };

        await fetch(API_HERO, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(createdHero)
        });
    }

    function handleInventoryChange(e: Event) {
        const target = e.target as HTMLInputElement;
        newHero.inventory = target?.value.split(",").map((item: string) => item.trim());
    }
</script>

<h2>Création du héro</h2>
<form on:submit|preventDefault={addHero} class="hero-form">
    <div>
        <label for="name">Nom</label>
        <input id="name" type="text" bind:value={newHero.name} placeholder="Nom du héro" required />
    </div>
    <div>
        <label for="level">Niveau</label>
        <input id="level" type="number" bind:value={newHero.level} min="1" required />
    </div>
    <div>
        <label for="gold">Or</label>
        <input id="gold" type="number" bind:value={newHero.gold} min="0" />
    </div>
    <div>
        <label for="maxHealth">Santé max</label>
        <input id="maxHealth" type="number" bind:value={newHero.maxHealth} min="1" required />
    </div>
    <div>
        <label for="damage">Dégâts</label>
        <input id="damage" type="number" bind:value={newHero.damage} min="1" required />
    </div>
    <div>
        <label for="inventory">Inventaire</label>
        <input
            id="inventory"
            type="text"
            bind:value={newHero.inventory}
            placeholder="Objets séparés par des virgules"
            on:change={handleInventoryChange}
        />
    </div>
    <button type="submit">Ajouter un héros</button>
</form>

<style>
    .hero-form {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }

    .hero-form div {
        display: flex;
        flex-direction: column;
    }

    .hero-form label {
        font-weight: bold;
        margin-bottom: 5px;
    }

    .hero-form input {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        background-color: #ebe9c3;
    }
</style>