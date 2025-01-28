<script>
    import HeroesList from "./HeroesList.svelte";
    import HeroCreation from "./HeroCreation.svelte";
</script>

<div class="hero-container">
    <div class="box">
        <HeroesList></HeroesList>
    </div>

    <div class=" box">
        <HeroCreation></HeroCreation>
    </div>
</div>

<style>
    .hero-container {
        display: flex;
        justify-content: space-around;
        width: 100%;
    }

    .box {
        background-color: #1c1c1c;
        border-radius: 10px;
        margin: 20px;
        display: flex;
        flex-direction: column;
        gap: 10px;
        padding: 20px;
        width: 600px;
    }

</style>