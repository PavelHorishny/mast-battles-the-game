import { Application, Graphics, Text, Container } from 'pixi.js';

const app = new Application();
const windData = [
    { dir: 'N',  value: 30 },
    { dir: 'NE', value: 15 },
    { dir: 'E',  value: 10 },
    { dir: 'SE', value: 5 },
    { dir: 'S',  value: 20 },
    { dir: 'SW', value: 25 },
    { dir: 'W',  value: 40 },
    { dir: 'NW', value: 18 }
];

const container = document.getElementById('rose-area');
await app.init({
    resizeTo: container,
    //background: '#1e1e1e',
    antialias: true
});

document.getElementById('rose-area').appendChild(app.canvas);

// -----------------------------

const centerX = 260 / 2;
const centerY = 260 / 2;
const maxRadius = 75;

const rose = new Container();
rose.x = centerX;
rose.y = centerY;
app.stage.addChild(rose);

// tooltip
const tooltip = new Text('', {
    fill: '#ffffff',
    fontSize: 10
});
tooltip.visible = false;
app.stage.addChild(tooltip);

// шкала
const maxValue = Math.max(...windData.map(d => d.value));

// -----------------------------
// оси
const axes = new Graphics();
axes.stroke({ width: 1, color: 0x555555 });

for (let i = 0; i < 8; i++) {
    const angle = (Math.PI * 2 / 8) * i - Math.PI / 2;
    axes.moveTo(0, 0);
    axes.lineTo(
        Math.cos(angle) * maxRadius,
        Math.sin(angle) * maxRadius
    );
}

rose.addChild(axes);

// -----------------------------
// лучи
windData.forEach((d, i) => {
    const angle = (Math.PI * 2 / windData.length) * i - Math.PI / 2;
    const length = (d.value / maxValue) * maxRadius;

    const ray = new Graphics();
    ray.stroke({ width: 18, color: 0x4fc3f7, alpha: 0.8 });
    ray.moveTo(0, 0);
    ray.lineTo(
        Math.cos(angle) * length,
        Math.sin(angle) * length
    );

    ray.eventMode = 'static';
    ray.cursor = 'pointer';

    ray.on('pointerover', () => {
        ray.tint = 0xffc107;
        tooltip.text = `${d.dir}: ${d.value}`;
        tooltip.visible = true;
    });

    ray.on('pointerout', () => {
        ray.tint = 0xffffff;
        tooltip.visible = false;
    });

    ray.on('pointermove', (e) => {
        tooltip.x = e.global.x + 10;
        tooltip.y = e.global.y + 10;
    });

    rose.addChild(ray);

    // подпись направления
    const label = new Text(d.dir, {
        fill: '#aaaaaa',
        fontSize: 10
    });

    label.x = Math.cos(angle) * (maxRadius + 20) - label.width / 2;
    label.y = Math.sin(angle) * (maxRadius + 20) - label.height / 2;
    rose.addChild(label);
});
