import { Application, Graphics } from 'pixi.js';

const app = new Application();

await app.init({
    width: 260,
    height: 260,
    backgroundColor: 0x333333,
    resolution: window.devicePixelRatio,
    autoDensity: true
});

document.getElementById('mini-map').appendChild(app.canvas);

// test
const g = new Graphics()
    .rect(10, 10, 50, 50)
    .fill(0xff0000);

app.stage.addChild(g);