const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('api', {
    start: () => ipcRenderer.send('start'),
    quit: () => ipcRenderer.send('quit'),
    restart: () => ipcRenderer.send('restart')
});
