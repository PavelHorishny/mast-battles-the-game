import {Sprite} from "pixi.js";

export class ShipSprite extends Sprite {
    constructor(texture,tileX, tileY, tileSize) {
        super(texture);

        this.anchor.set(0.5);
        const maxSize = tileSize;

        const scaleX = maxSize / this.texture.width;
        const scaleY = maxSize / this.texture.height;
        const scale = Math.min(scaleX, scaleY);
        this.scale.set(scale);

        this.setTilePosition(tileX, tileY,tileSize);
    }

    setTilePosition(x,y, tileSize) {
        this.x = x * tileSize + tileSize / 2;
        this.y = y * tileSize + tileSize / 2;
    }

    setDirection(degree) {
        this.rotation = degree * (Math.PI / 180);
    }
}