import {Tile, TileType} from "./tile";
import {LAND} from "@/core/constants";


export class GameMap {

    constructor(width, height) {
        this.width = width;
        this.height = height;
        this.tiles = [];
    }

    generateRandomMap(landRatio = 0.2) {
        for (let y = 0; y < this.height; y++) {
            for (let x = 0; x < this.width; x++) {
                const isLand = Math.random() < landRatio;
                const type = isLand ? TileType.LAND : TileType.WATER;
                this.tiles.push(new Tile(x, y, type));
            }
        }
    }

    generateMap(){
        const landSet = new Set(
            LAND.map(coord => coord.x + ',' + coord.y)
        );

        for(let y =0; y<this.height; y++) {
            for(let x=0; x<this.width; x++) {
                const key = x + ',' + y;
                const type = landSet.has(key) ? TileType.LAND : TileType.WATER;

                this.tiles.push(new Tile(x,y,type));
            }
        }
    }
    getTile(x, y) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) {
            return null;
        }
        return this.tiles[y * this.width + x];
    }
}
