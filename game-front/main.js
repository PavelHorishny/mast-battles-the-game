const { app, BrowserWindow, ipcMain } = require('electron');
const path = require('path');

let mainWindow;
let gameWindow;

const isDev = !app.isPackaged;
const VITE_URL = 'http://localhost:5173';

function createWindow() {
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
        mainWindow.loadURL(VITE_URL + '/main-win/index.html');
        //gameWindow.loadURL(VITE_URL + '/.game.html');
        gameWindow.loadURL(VITE_URL + '/game-win/index.html');
    } else {
        mainWindow.loadFile('dist/index.html');
        gameWindow.loadFile('dist/.game.html');
    }
    gameWindow.webContents.openDevTools();
}

ipcMain.on('start', () => {
    console.log('start game');
    mainWindow.hide();
    gameWindow.show();
});

ipcMain.on('quit', () => {
    console.log('quit');
    app.quit();
});

ipcMain.on('restart', () => {
    console.log('restart');
    gameWindow.hide();
    mainWindow.show();
});

app.whenReady().then(createWindow)/*on('ready', createWindow)*/;

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
/*
mainWindow.on('closed', () => (mainWindow = null));*/
