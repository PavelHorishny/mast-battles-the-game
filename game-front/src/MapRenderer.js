import {Container, Sprite, Texture} from "pixi.js";
import {TileType} from "./tile.js";

export class MapRenderer{
    constructor(gameMap, tileSize) {
        this.map = gameMap;
        this.tileSize = tileSize;
        this.container = new Container();

        this.texture = {
            [TileType.WATER]: Texture.from('water'),
            [TileType.LAND]: Texture.from('land')
        }
    }

    draw(){
        for(const tile of this.map.tiles){
            const sprite = new Sprite(this.texture[tile.type]);

            sprite.x = tile.x * this.tileSize;
            sprite.y = tile.y * this.tileSize;

            this.container.addChild(sprite);
        }
    }
}
