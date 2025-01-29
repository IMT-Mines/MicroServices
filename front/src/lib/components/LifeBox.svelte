<script lang="ts">
    export let name: string = "";
    export let life: number = 100;
    export let maxLife: number = 100;
    export let level: number = 1;

    $: lifePercentage = Math.max(0, Math.min(100, (life / maxLife) * 100));

    $: lifeColor =
        lifePercentage > 50 ? "#1ab71a" :
            lifePercentage > 20 ? "#e5c100" :
                "#d62828";
</script>

<div class="box">
    <div class="header">
        <span class="name">{name}</span>
        <span class="level">Lv {level}</span>
    </div>

    <div class="hp-bar-container">
        <div class="hp-bar" style="width: {lifePercentage}%; background: {lifeColor};"></div>
    </div>

    <div class="hp-text">HP {life} / {maxLife}</div>
</div>

<style>
    .box {
        width: 220px;
        background: #ebe9c3;
        padding: 10px;
        border: 4px solid #000;
        font-family: "PixelFont", sans-serif;
        text-transform: uppercase;
        display: flex;
        flex-direction: column;
        gap: 5px;
        box-shadow: 4px 4px 0px #000;
        color: #000;
        border-radius: 5px;
    }

    .header {
        display: flex;
        justify-content: space-between;
        font-size: 14px;
    }

    .name {
        font-weight: bold;
        font-size: 22px;
    }

    .level {
        font-size: 16px;
    }

    .hp-bar-container {
        width: 100%;
        height: 12px;
        background: #000;
        border: 2px solid #000;
        display: flex;
        align-items: center;
    }

    .hp-bar {
        height: 100%;
        transition: width 0.3s ease-in-out;
    }

    .hp-text {
        text-align: right;
        font-size: 16px;
    }
</style>
