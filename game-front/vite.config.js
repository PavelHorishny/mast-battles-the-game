import { defineConfig } from 'vite';
import path from 'path';

export default defineConfig({
    root: '.',
    base: './',
    resolve: {
        alias: {
            '@': path.resolve(__dirname, 'src')
        }
    },
    build: {
        outDir: 'dist',
        emptyOutDir: true,
        rollupOptions: {
            input: {
                main: path.resolve(__dirname, 'windows/main/index.html'),
                game: path.resolve(__dirname, 'windows/game/index.html')
            }
        }
    }
});