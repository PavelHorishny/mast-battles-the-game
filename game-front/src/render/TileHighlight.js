import {Graphics} from "pixi.js";

export class TileHighlight extends Graphics {
    constructor(tileSize) {
        super();
        this.tileSize = tileSize;
        this.visible = false;

        this.lightRect();
    }

    lightRect(){
        this.clear();
        this.rect(0,0,this.tileSize, this.tileSize).stroke({
            width:2,
            color:0xffff00
        });
    }

    show(tileX, tileY){
        this.x = tileX * this.tileSize;
        this.y = tileY * this.tileSize;
        this.visible = true;
    }

    hide(){
        this.visible = false;
    }
}