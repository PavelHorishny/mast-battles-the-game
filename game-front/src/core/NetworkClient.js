export class NetworkClient {
    constructor() {
        this.socket = null;
    }

    connect(onOpen, onMessage, onClose) {
        console.log('Подключаемся к ws://localhost:8080/events...');
        this.socket = new WebSocket('ws://localhost:8080/events');

        this.socket.onopen = () => {
            console.log('✅ Хендшейк успешен!');
            if (onOpen) onOpen();
        };

        this.socket.onmessage = (event) => {
            console.log('📩 Данные от сервера получены!');
            if (onMessage) onMessage(event.data);
        };

        this.socket.onclose = () => {
            console.warn('❌ Соединение закрыто');
            if (onClose) onClose();
        };

        this.socket.onerror = (err) => {
            console.error('⚠️ Ошибка сокета (Бэкенд выключен или CORS блокирует):', err);
        };
    }

    send(message) {
        if (this.socket && this.socket.readyState === WebSocket.OPEN) {
            this.socket.send(message);
        }
    }

}