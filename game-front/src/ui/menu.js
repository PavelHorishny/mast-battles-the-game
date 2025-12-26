/*document.getElementById('start').onclick = () => {
    window.api.start();
};

document.getElementById('quit').onclick = () => {
    window.api.quit();
};*/
document.addEventListener('DOMContentLoaded', () => {
    const startBtn = document.getElementById('start');
    const quitBtn = document.getElementById('quit');
    //const restartBtn = document.getElementById('restart');


    startBtn.onclick = () => {
        window.api.start();
    };

    quitBtn.onclick = () => {
        window.api.quit();
    };
 /*   restartBtn.onclick = () => {
        window.api.restart();
    };*/
});
console.log('menu loaded');
console.log('api:', window.api);
/*
function bind(id, handler) {
    const el = document.getElementById(id);
    if (!el) {
        console.warn(`Кнопка #${id} не найдена`);
        return;
    }
    el.addEventListener('click', handler);
}

document.addEventListener('DOMContentLoaded', () => {
    bind('start', () => window.api.start());
    bind('quit', () => window.api.quit());
    bind('back', () => window.api.restart());
});*/
/*const ACTIONS = {
    start: () => window.api?.start(),
    quit: () => window.api?.quit(),
    restart: () => window.api?.restart(),
};

function bindButton(id, action) {
    const el = document.getElementById(id);

    if (!el) {
        console.info(`[menu] кнопка #${id} не найдена`);
        return;
    }

    el.addEventListener('click', (e) => {
        e.preventDefault();
        action();
    });

    console.info(`[menu] кнопка #${id} подключена`);
}

function initMenu() {
    Object.entries(ACTIONS).forEach(([id, action]) => {
        bindButton(id, action);
    });
}

// гарантируем DOM
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initMenu);
} else {
    initMenu();
}*/
