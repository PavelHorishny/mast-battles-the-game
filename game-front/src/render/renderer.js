import {Application, Assets} from 'pixi.js';
import {MapRenderer} from "./MapRenderer";
import {GameMap} from "@/core/gameMap";
import { GridRenderer } from "@/render/GridRenderer";


const app = new Application();


await Assets.load([
    { alias: 'water', src: '/water.png' },
    { alias: 'land',  src: '/land.png' },
    { alias: 'port',  src: '/port.png' },
    { alias: 'route', src: '/route.png' }
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

const cols = map.width;
const rows = map.height;
const tileSize = mapRenderer.tileSize;

const grid = new GridRenderer(cols,rows, tileSize);
grid.x = mapRenderer.container.x;
grid.y = mapRenderer.container.y;
grid.visible = false;
app.stage.addChild(mapRenderer.container);
app.stage.addChild(grid);

document.getElementById('Grid')?.addEventListener('click', () => {
    grid.visible = !grid.visible;
});