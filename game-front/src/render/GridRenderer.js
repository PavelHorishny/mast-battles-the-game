import {Graphics} from "pixi.js";

export class GridRenderer extends Graphics {
    constructor(cols, rows, tileSize, color=0xffffff, alpha = 0.2) {
        super();

        this.cols = cols;
        this.rows = rows;
        this.tileSize = tileSize;

        this.strokeStyle = {
            width: 1,
            color,
            alpha
        };

        this.drawGrid();
    }

    drawGrid(){
        const width = this.cols * this.tileSize;
        const height = this.rows * this.tileSize;

        for(let x = 0; x<=this.cols; x++){
            this.moveTo(x * this.tileSize, 0);
            this.lineTo(x * this.tileSize, height);
        }
        for(let y = 0; y<=this.rows; y++){
            this.moveTo(0, y * this.tileSize);
            this.lineTo(width, y * this.tileSize);
        }

        this.stroke();
    }
}