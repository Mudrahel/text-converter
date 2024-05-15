const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/converter-websocket'
});

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/conversion', (portion) => {
        showResult(portion.body);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    console.log("Disconnected");
}

function sendTextToConversion() {
    stompClient.publish({
        destination: "/app/convert",
        body: JSON.stringify({'content': $("#text").val()})
    });
}

function showResult(message) {
     $("#result").val($("#result").val() + message);
}

$(function () {
    // Call connect() on page load
    connect();

    $("form").on('submit', (e) => e.preventDefault());
    $( "#sendText" ).click(() => sendTextToConversion());

    // Call disconnect() when the page is closed
    $(window).on('beforeunload', () => {
        disconnect();
    });
});

