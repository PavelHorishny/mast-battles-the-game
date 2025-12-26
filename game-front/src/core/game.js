import { Application, Graphics } from 'pixi.js';

const app = new Application();
await app.init({ resizeTo: window, background: '#222' });

document.body.appendChild(app.canvas);

const g = new Graphics().rect(50, 50, 200, 200).fill(0xff0000);
app.stage.addChild(g);

document.getElementById('restart').onclick = () => {
    window.api.restart();
};
