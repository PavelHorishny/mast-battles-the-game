import {Application, Assets, Texture} from 'pixi.js';
import {MapRenderer} from "./MapRenderer";
import {GameMap} from "./map";


const app = new Application();


await Assets.load([
    { alias: 'water', src: '/water.png' },
    { alias: 'land',  src: '/land.png' }
]);

const textures = {
    0: Texture.from('water'),
    1: Texture.from('land')
};

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
/*
const map = {
    width: 34,
    height: 32,
    tileSize: 30,
    tiles: generateMap(34, 32)
};


const world = new Container();
app.stage.addChild(world);

function drawMap(map) {
    for (let y = 0; y < map.height; y++) {
        for (let x = 0; x < map.width; x++) {
            const id = map.tiles[y * map.width + x];
            const tile = new Sprite(textures[id]);

            tile.x = x * map.tileSize;
            tile.y = y * map.tileSize;

            world.addChild(tile);
        }
    }
}
drawMap(map);*/
