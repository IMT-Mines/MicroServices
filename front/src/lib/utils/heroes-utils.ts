const API_HEROES = 'http://localhost:8080/api/heroes';


export async function setHeroHealth(heroId: number, health: number): Promise<boolean> {
    const response = await fetch(`${API_HEROES}/${heroId}/health`,
        {
            method: 'PUT',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({health})
        }
    );

    return response.ok;
}

export async function setHeroPosition(heroId: number, dungeonId: number, roomId: number): Promise<boolean> {
    const response = await fetch(`${API_HEROES}/${heroId}/position`,
        {
            method: 'PUT',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({dungeonId, roomId})
        }
    );

    return response.ok;
}

export async function setHeroGold(heroId: number, gold: number): Promise<boolean> {
    const response = await fetch(`${API_HEROES}/${heroId}/gold`,
        {
            method: 'PUT',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({gold})
        }
    );

    return response.ok;
}

export async function setHeroInventory(heroId: number, inventory: string[]): Promise<boolean> {
    const response = await fetch(`${API_HEROES}/${heroId}/inventory`,
        {
            method: 'PUT',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({inventory})
        }
    );

    return response.ok;
}

export async function setHeroLevel(heroId: number, level: number): Promise<boolean> {
    const response = await fetch(`${API_HEROES}/${heroId}/level`,
        {
            method: 'PUT',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({level})
        }
    );

    return response.ok;
}