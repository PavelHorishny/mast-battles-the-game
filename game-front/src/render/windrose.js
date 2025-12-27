import {Application, Assets} from 'pixi.js';
import {WindRoseCompass} from "@/render/WindRoseCompass";

const SIZE = 260;

const app = new Application();
await app.init({
    width: SIZE,
    height: SIZE,
    backgroundColor: 0x3b9ad9,
    antialias: true
});

document.getElementById('rose-area').appendChild(app.canvas);

const compassTexture = await Assets.load('/windRose.png');
const rose = new WindRoseCompass(compassTexture, 260);
rose.position.set(130,130);

app.stage.addChild(rose);
rose.setWind('W',"storm");
