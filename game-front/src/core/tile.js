export const TileType = {
    WATER: 'water',
    LAND: 'land',
    PORT: 'port',
    ROUTE: 'route'
}

export class Tile {
    constructor(x,y,type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    isWater() {
        return this.type === TileType.WATER;
    }
    isLand() {
        return this.type === TileType.LAND;
    }
}