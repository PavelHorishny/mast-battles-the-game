document.addEventListener('DOMContentLoaded', () => {
    const startBtn = document.getElementById('start');
    const quitBtn = document.getElementById('quit');



    startBtn.onclick = () => {
        window.api.start();
    };

    quitBtn.onclick = () => {
        window.api.quit();
    };
});
console.log('menu loaded');
console.log('api:', window.api);
