import React from 'react';
import './App.css';
import SockJs from 'sockjs-client'
import {Stomp} from '@stomp/stompjs'
import {Pane} from "evergreen-ui";

/* Begin Setup */
const socket = new SockJs("http://localhost:8080/chatroom", {}, {CheckOrigin: () => false});
socket.onopen = () => {
    console.log('Connected to server');
};
socket.onclose = () => {
    console.log('Disconnected from server');
};

const stompClient = Stomp.over(socket);
stompClient.connect({}, () => {
    console.log('Connected to chat server');
});
/* End Setup */

function App() {
    return (
        <Pane width="100%"  display="flex" alignItems="center" justifyContent="center" className="margin-top">
            <div>
                KRYPTOTRACKER
            </div>
        </Pane>
    );
}

export default App;
