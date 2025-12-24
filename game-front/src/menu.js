document.getElementById('start').onclick = () => {
    window.api.start();
};

document.getElementById('quit').onclick = () => {
    window.api.quit();
};
document.getElementById('back').onclick = () => {
    window.api.restart();
};
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
