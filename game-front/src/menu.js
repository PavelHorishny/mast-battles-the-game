document.getElementById('start').onclick = () => {
    window.api.start();
};

document.getElementById('quit').onclick = () => {
    window.api.quit();
};
document.getElementById('back').onclick = () => {
    window.api.restart();
};
