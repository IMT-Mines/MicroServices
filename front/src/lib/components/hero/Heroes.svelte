<script>
    import HeroesList from "./HeroesList.svelte";
    import HeroCreation from "./HeroCreation.svelte";
</script>

<div class="hero-container">
    <div class="hero-selection">
       <HeroesList></HeroesList>
    </div>

    <div class="hero-creation">
        <HeroCreation></HeroCreation>
    </div>
</div>

<style>
    .hero-container {
        display: flex;
        justify-content: space-around;
        width: 100%;
    }

    .hero-selection {
        width: 50%;
        display: flex;
        flex-direction: column;
        gap: 10px;
        padding: 20px;
    }

    .hero-creation {
        width: 50%;
        display: flex;
        flex-direction: column;
        gap: 20px;
        padding: 20px;
    }
</style>