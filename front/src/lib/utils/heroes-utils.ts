const API_HEROES = 'http://localhost:8080/api/heroes';


export async function setHealth(heroId: number, health: number): Promise<boolean> {
    const response = await fetch(`${API_HEROES}/${heroId}/health`,
        {
            method: 'PUT',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({health})
        }
    );

    return response.ok;

}

export async function setPosition(heroId: number, dungeonId: number | undefined, roomId: number | undefined): Promise<boolean> {
    const response = await fetch(`${API_HEROES}/${heroId}/position`,
        {
            method: 'PUT',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({dungeonId, roomId})
        }
    );

    return response.ok;

}