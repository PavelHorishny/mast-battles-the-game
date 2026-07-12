import {initMenu} from '@/ui/menu.js';
import {initRenderer} from '@/render/renderer.js';
//import '@/ui/buttons';
import {initWindRose} from '@/render/windrose';

import { NetworkClient } from './core/NetworkClient.js';

// Строго определяем, в каком окне мы находимся
const isMenuWindow = document.getElementById('start') !== null;
const isGameWindow = document.getElementById('restart') !== null;

// ==========================================
// ЛОГИКА ТОЛЬКО ДЛЯ ГЛАВНОГО МЕНЮ
// ==========================================
if (isMenuWindow) {
    initMenu();
    const startBtn = document.getElementById('start');
    const quitBtn = document.getElementById('quit');
    const client = new NetworkClient();

    startBtn.disabled = true;
    startBtn.innerText = 'Connecting...';

    // Инициализируем подключение к Spring
    client.connect(
        // Callback на успешный коннект
        () => {
            startBtn.disabled = false;
            startBtn.innerText = 'Start';
        },
        // Callback на получение сообщения
        (data) => {
            try {
                const mapData = JSON.parse(data);
                if (Array.isArray(mapData)) { // Если это массив, значит пришла карта
                    console.log('Карта распарсена. Отправляем в Electron!');
                    // Вызов не будет подчеркиваться красным, если preload.js обновлен
                    window.electronAPI.startGameWithMap(mapData);
                }
            } catch (e) {
                console.log('Получен текст:', data);
            }
        },
        // Callback на отключение
        () => {
            startBtn.disabled = true;
            startBtn.innerText = 'Disconnected';
        }
    );

    startBtn.addEventListener('click', () => {
        startBtn.disabled = true;
        startBtn.innerText = 'Generating...';
        // Отправляем команду в бэкенд (Engine.java)
        client.send('GENERATE_MAP');
    });

    if (quitBtn) {
        quitBtn.addEventListener('click', () => window.electronAPI.quit());
    }
}

// ==========================================
// ЛОГИКА ТОЛЬКО ДЛЯ ОКНА ИГРЫ
// ==========================================
let rendererHandle = null;
let pendingMatrix = null;
if (isGameWindow) {
    initRenderer().then(handle => {
        rendererHandle = handle;
        if(pendingMatrix) renderGameMap(pendingMatrix);
    });
    initWindRose();
    const restartBtn = document.getElementById('restart');

    if (restartBtn) {
        restartBtn.addEventListener('click', () => window.electronAPI.restart());
    }

    // Слушаем Электрон. Сработает только тогда, когда Электрон перешлет карту
    if (window.electronAPI && window.electronAPI.onInitMap) {
        window.electronAPI.onInitMap((mapData) => {
            console.log('Окно игры: Карта получена! Начинаем рендер.');
            renderGameMap(mapData);
        });
    }
}

// Функция рендера
function renderGameMap(matrix) {
    if (!matrix || matrix.length === 0) return;
    console.log(`Рендерим матрицу: Y=${matrix.length}, X=${matrix[0].length}`);
    if(!rendererHandle){
        console.warn("renderer not ready");
        pendingMatrix = matrix;
        return;
    }
    rendererHandle.updateMap(matrix);
    // Здесь твой код отрисовки...
}
