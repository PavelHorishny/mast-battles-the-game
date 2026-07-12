import {Container, Sprite,Graphics, RenderTexture} from "pixi.js";

const DIRECTIONS = {
    N: -90,
    NE: -45,
    E: 0,
    SE: 45,
    S: 90,
    SW: 135,
    W: 180,
    NW: -135
};

const COLORS = {
    calm: 0x88ccff,
    breeze: 0x55ff55,
    storm: 0xff3333
};

export class WindRoseCompass extends Container {
    constructor(texture, size = 260) {
        super();
        this.size = size;
        this.radius = size / 2;

        //base
        this.base = new Sprite(texture);
        this.base.anchor.set(0.5);
        this.base.width = size;
        this.base.height = size;
        this.addChild(this.base);

        //highlight
        this.highlight = new Sprite(texture);
        this.highlight.anchor.set(0.5);
        this.highlight.width = size;
        this.highlight.height = size;
        this.addChild(this.highlight);

        //mask containers
        this.maskContainer = new Container();
        this.petalMask = new Graphics();

        this.maskContainer.addChild(this.petalMask);
        this.addChild(this.maskContainer);

        //render texture for final mask
        this.finalMaskRT = RenderTexture.create({
            width: size,
            height: size
        });

        this.highlight.mask = this.maskContainer;
        this.highlight.visible = false;
    }

    setWind(direction,strength) {
        const angleDeg = DIRECTIONS[direction];
        if(angleDeg === undefined) return;

        this.highlight.tint = COLORS[strength] ?? 0xffffff;
        this.highlight.visible = true;

        this._drawPetalMask(angleDeg);
        //this._updateFinalMask();
    }

    clear() {
        this.highlight.visible = false;
    }

    _drawPetalMask(angleDeg) {
        const angle = angleDeg * (Math.PI / 180);
        const spread = Math.PI / 10; //22.5 degrees

        this.petalMask.clear();
        //this.petalMask.beginFill(0xffffff);
        this.petalMask.moveTo(0,0);
        this.petalMask.lineTo(
            Math.cos(angle - spread) * this.radius,
            Math.sin(angle - spread) * this.radius
        );
        this.petalMask.lineTo(
            Math.cos(angle + spread) * this.radius,
            Math.sin(angle + spread) * this.radius
        );
        this.petalMask.closePath();
        this.petalMask.fill(0xffffff);
    }
}