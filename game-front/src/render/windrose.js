import { Application, Graphics, Text, Container } from 'pixi.js';

const SIZE = 260;
const CENTER = SIZE / 2;
const MAX_RADIUS = 80;

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

// --------------------
// APP
const app = new Application();
await app.init({
    width: SIZE,
    height: SIZE,
    backgroundColor: 0x3b9ad9,
    antialias: true
});

document.getElementById('rose-area').appendChild(app.canvas);

// --------------------
// MAIN CONTAINER
const rose = new Container();
rose.position.set(CENTER, CENTER);
app.stage.addChild(rose);

// --------------------
// TOOLTIP
const tooltip = new Text({
    text: '',
    style: {
        fill: '#ffffff',
        fontSize: 11
    }
});
tooltip.visible = false;
app.stage.addChild(tooltip);

// --------------------
// SCALE
const maxValue = Math.max(...windData.map(d => d.value));

// --------------------
// AXES
const axes = new Graphics();
axes.strokeStyle = { width: 1, color: 0x555555 };

for (let i = 0; i < 8; i++) {
    const angle = (Math.PI * 2 / 8) * i - Math.PI / 2;
    axes.moveTo(0, 0);
    axes.lineTo(
        Math.cos(angle) * MAX_RADIUS,
        Math.sin(angle) * MAX_RADIUS
    );
}

axes.stroke(); // 🔴 ОБЯЗАТЕЛЬНО в Pixi 8
rose.addChild(axes);

// --------------------
// RAYS + LABELS
windData.forEach((d, i) => {
    const angle = (Math.PI * 2 / windData.length) * i - Math.PI / 2;
    const length = (d.value / maxValue) * MAX_RADIUS;

    // RAY
    const ray = new Graphics();
    ray.strokeStyle = {
        width: 16,
        color: 0x4fc3f7,
        alpha: 0.85,
        cap: 'round'
    };

    ray.moveTo(0, 0);
    ray.lineTo(
        Math.cos(angle) * length,
        Math.sin(angle) * length
    );

    ray.stroke(); // 🔴 ОБЯЗАТЕЛЬНО
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
        tooltip.position.set(e.global.x + 8, e.global.y + 8);
    });

    rose.addChild(ray);

    // LABEL
    const label = new Text({
        text: d.dir,
        style: {
            fill: '#dddddd',
            fontSize: 11
        }
    });

    label.anchor.set(0.5);
    label.position.set(
        Math.cos(angle) * (MAX_RADIUS + 18),
        Math.sin(angle) * (MAX_RADIUS + 18)
    );

    rose.addChild(label);
});
