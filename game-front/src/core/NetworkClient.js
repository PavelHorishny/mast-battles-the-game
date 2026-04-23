export class NetworkClient {
    constructor(url) {
        this.url = url;
        this.socket = null;
    }

    connect() {
        this.socket = new WebSocket(this.url);
        this.socket.onopen = () => {
            console.log('[WS] Connected to server');
        }

        this.socket.onmessage = (event) => {
            const data = JSON.parse(event.data);
            this.handleMessage(data);
        }

        this.socket.onclose = () => {
            console.log('[WS] Disconnected from server');
            setTimeout(()=>this.connect(), 3000);
        }
        this.socket.onerror = (error) => {
            console.log('[WS] Socket Error:', error);
        }
    }

    handleMessage(data) {
        console.log('[WS] Message:', data);

        const event = new CustomEvent('message', {detail: data});
        window.dispatchEvent(event);
    }

    send(type, payload) {
        if(this.socket && this.socket.readyState === WebSocket.OPEN) {
            this.socket.send(JSON.stringify({type, payload}));
        }
    }
}