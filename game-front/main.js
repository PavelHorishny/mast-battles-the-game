const { app, BrowserWindow, ipcMain } = require('electron');
const path = require('path');

let mainWindow;
let gameWindow;

const isDev = !app.isPackaged;
const VITE_URL = 'http://localhost:5173';

async function createWindow() {
    mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            preload: path.join(__dirname, 'preload.js'),
            contextIsolation: true,
            nodeIntegration: false
        }
    });

    gameWindow = new BrowserWindow({
        width: 1325,
        height: 1070,
        show: false,
        webPreferences: {
            preload: path.join(__dirname, 'preload.js'),
            contextIsolation: true,
            nodeIntegration: false
        }
    });

    if (isDev) {
        await mainWindow.loadURL(VITE_URL + '/windows/main/index.html');
        await gameWindow.loadURL(VITE_URL + '/windows/game/index.html');
    }else{
        await mainWindow.loadFile(path.join(__dirname, 'dist/windows/main/index.html'));
        await gameWindow.loadFile(path.join(__dirname,'dist/windows/game/index.html'));
    }
    gameWindow.webContents.openDevTools();
}

ipcMain.on('start', () => {
    console.log('start game');
    mainWindow.hide();
    gameWindow.show();
});

ipcMain.on('start-game-with-map', (event,mapData) => {
    console.log('start game with map data');
    gameWindow.webContents.send('init-map',mapData);

    mainWindow.hide();
    gameWindow.show();
})

ipcMain.on('quit', () => {
    console.log('quit');
    app.quit();
});

ipcMain.on('restart', () => {
    console.log('restart');
    gameWindow.hide();
    mainWindow.show();
});

app.whenReady().then(createWindow);

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit();
    }
});

app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) {
        createWindow();
    }
});

