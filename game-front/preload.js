const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('electronAPI', {
    start: () => ipcRenderer.send('start'),
    startGameWithMap: (mapData) => ipcRenderer.send('start-game-with-map', mapData),
    quit: () => ipcRenderer.send('quit'),
    restart: () => ipcRenderer.send('restart'),
    onInitMap: (callback) => ipcRenderer.on('init-map', (event, mapData) => callback(mapData)),
});
