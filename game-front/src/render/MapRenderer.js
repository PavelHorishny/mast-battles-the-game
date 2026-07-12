import {Container, Sprite, Texture} from "pixi.js";
import {TileType} from "@/core/tile";

export class MapRenderer{
    constructor(gameMap, tileSize) {
        this.map = gameMap;
        this.tileSize = tileSize;
        this.container = new Container();

        this.texture = {
            [TileType.WATER]: Texture.from('water'),
            [TileType.LAND]: Texture.from('land'),
            [TileType.PORT]: Texture.from('port'),
            [TileType.ROUTE]: Texture.from('route')
        }
    }

    draw(){
        for(const tile of this.map.tiles){
            const sprite = new Sprite(this.texture[tile.type]);

            sprite.x = tile.x * this.tileSize;
            sprite.y = tile.y * this.tileSize;

            sprite.tile = tile;
            sprite.tileX = tile.x;
            sprite.tileY = tile.y;

            sprite.eventMode = 'static';

            this.container.addChild(sprite);

            sprite.on('pointerdown', ()=>{
                console.log(sprite.tileX, sprite.tileY, sprite.tile.type);
                this.onTileClicked?.(sprite.tile);
            })
        }
    }

    setMap (gameMap){
        this.map = gameMap;
        this.container.removeChildren();
        this.draw();
    }
    setTileClickHandler(fn) {
        this.onTileClicked = fn;
    }
}
