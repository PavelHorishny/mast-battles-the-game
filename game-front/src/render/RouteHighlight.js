import {Container, Graphics} from "pixi.js";

export class RouteHighlight {
    constructor(tileSize) {
        this.tileSize = tileSize;
        this.container = new Container();
    }
    show(path){
        this.container.removeChildren();

        for(const tile of path){
            const g = new Graphics();
            g.rect(0,0,this.tileSize,this.tileSize).fill(0x0B2C3D);
            g.x = tile.x * this.tileSize;
            g.y = tile.y * this.tileSize;
            this.container.addChild(g);
        }
    }

    hide(){
        this.container.removeChildren();
    }
}