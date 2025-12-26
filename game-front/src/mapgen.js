import {TILE} from "./core/tile";

export function generateMap(width, height, landRatio = 0.2) {
    const tiles = new Array(width * height).fill(TILE.WATER);

    const landTarget = Math.floor(width * height * landRatio);
    let landCount = 0;

    while (landCount < landTarget) {
        const x = Math.floor(Math.random() * width);
        const y = Math.floor(Math.random() * height);

        const index = y * width + x;
        if (tiles[index] === TILE.LAND) continue;

        // небольшие острова
        const radius = 1 + Math.floor(Math.random() * 3);

        for (let dy = -radius; dy <= radius; dy++) {
            for (let dx = -radius; dx <= radius; dx++) {
                const nx = x + dx;
                const ny = y + dy;
                if (nx < 0 || ny < 0 || nx >= width || ny >= height) continue;

                const i = ny * width + nx;
                if (tiles[i] === TILE.WATER) {
                    tiles[i] = TILE.LAND;
                    landCount++;
                }
            }
        }
    }

    return tiles;
}