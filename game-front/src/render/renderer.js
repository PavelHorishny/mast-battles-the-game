import {Application, Assets} from 'pixi.js';
import {MapRenderer} from "./MapRenderer";
import {GameMap} from "@/core/gameMap";


const app = new Application();


await Assets.load([
    { alias: 'water', src: '/water.png' },
    { alias: 'land',  src: '/land.png' }
]);

await app.init({
    width: 1020,
    height: 960,
    backgroundColor: 0x333333,
    resolution: window.devicePixelRatio,
    autoDensity: true
});

document.getElementById('mini-map').appendChild(app.canvas);

const map = new GameMap(34, 32);
map.generateMap();

const mapRenderer = new MapRenderer(map, 30);
mapRenderer.draw();

app.stage.addChild(mapRenderer.container);