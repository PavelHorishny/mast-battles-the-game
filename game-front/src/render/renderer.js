import {Application, Assets, Container} from 'pixi.js';
import {MapRenderer} from "./MapRenderer";
import {GameMap} from "@/core/gameMap";
import { GridRenderer } from "@/render/GridRenderer";
import {UnitRenderer} from "@/render/UnitRenderer";
import {testAnchor, testShip} from "@/core/constants";
import {SelectionManager} from "@/core/SelectionManager";
import {TileHighlight} from "@/render/TileHighlight";


const app = new Application();


await Assets.load([
    { alias: 'water', src: '/water.png' },
    { alias: 'land',  src: '/land.png' },
    { alias: 'port',  src: '/port.png' },
    { alias: 'route', src: '/route.png' },
    { alias: 'brig', src: '/sprites/1_Brig.png' },
    { alias: 'galleon', src: '/sprites/1_Galleon.png' },
    { alias: 'battery', src: '/sprites/1_Battery.png' },
    { alias: 'corvette', src: '/sprites/1_Corvette.png' },
    { alias: 'frigate', src: '/sprites/1_Frigate.png' },
    { alias: 'galley', src: '/sprites/1_Galley.png' },
    { alias: 'monitor', src: '/sprites/1_Monitor.png' },
    { alias: 'steamer', src: '/sprites/1_Steamer.png' },
    { alias: 'steamFrigate', src: '/sprites/1_SteamFrigate.png' },
    { alias: 'tender', src: '/sprites/1_Tender.png' },
    { alias: 'twoDeckerShipOfLine', src: '/sprites/1_2DSoL.png' },
    { alias: 'threeDeckerShipOfLine', src: '/sprites/1_3DSoL.png' },
    { alias: '_brig', src: '/sprites/2_Brig.png' },
    { alias: '_galleon', src: '/sprites/2_Galleon.png' },
    { alias: '_battery', src: '/sprites/2_Battery.png' },
    { alias: '_corvette', src: '/sprites/2_Corvette.png' },
    { alias: '_frigate', src: '/sprites/2_Frigate.png' },
    { alias: '_galley', src: '/sprites/2_Galley.png' },
    { alias: '_monitor', src: '/sprites/2_Monitor.png' },
    { alias: '_steamer', src: '/sprites/2_Steamer.png' },
    { alias: '_steamFrigate', src: '/sprites/2_SteamFrigate.png' },
    { alias: '_tender', src: '/sprites/2_Tender.png' },
    { alias: '_twoDeckerShipOfLine', src: '/sprites/2_2DSoL.png' },
    { alias: '_threeDeckerShipOfLine', src: '/sprites/2_3DSoL.png' },
    { alias: 'royalPort', src: '/sprites/1_Royal_Port.png' },
    { alias: '_royalPort', src: '/sprites/2_Royal_Port.png' },
    { alias: 'firstLineFort', src: '/sprites/1_FLFort.png' },
    { alias: '_firstLineFort', src: '/sprites/2_FLFort.png' },
    { alias: 'secondLineFort', src: '/sprites/1_SLFort.png' },
    { alias: '_secondLineFort', src: '/sprites/2_SLFort.png' },
    { alias: '__firstLineFort', src: '/sprites/0_FLFort.png' },
    { alias: '__secondLineFort', src: '/sprites/0_SLFort.png'},
    { alias: 'anchor', src: '/sprites/Anchor.png' }
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

const unitRenderer = new UnitRenderer(tileSize);
const highlightLayer = new Container();
const selection = new SelectionManager();
const highlight = new TileHighlight(tileSize);
grid.x = mapRenderer.container.x;
grid.y = mapRenderer.container.y;
grid.visible = false;

unitRenderer.addUnit(testShip);
unitRenderer.addUnit(testAnchor);

highlightLayer.addChild(highlight);

unitRenderer.setClickHandler((unit)=>{
    selection.select(unit);
    highlight.show(unit.x, unit.y);
});
mapRenderer.setTileClickHandler(()=>{
    selection.clear();
    highlight.hide();
});

app.stage.addChild(mapRenderer.container);
app.stage.addChild(highlightLayer);
app.stage.addChild(unitRenderer.container);
/*
app.ticker.add(() => {
    unitRenderer.update();
});
*/

app.stage.addChild(grid);

document.getElementById('Grid')?.addEventListener('click', () => {
    grid.visible = !grid.visible;
});