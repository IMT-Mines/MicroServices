<script>
    import TopBar from "./TopBar.svelte";
    import Heroes from "./hero/Heroes.svelte";

    function startGame() {
        console.log("Game started");
    }
</script>

<TopBar></TopBar>

<div class="play-container">
    <Heroes></Heroes>

    <button class="play-button" on:click={startGame}>Jouer</button>
</div>

<style>

    .play-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 50px;
    }

    .play-button {
        padding: 10px 20px;
        font-size: 30px;
        background-color: #4800ff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
</style>