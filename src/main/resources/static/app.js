const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8585/converter-websocket' //would be good to take this variable from configuration
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
    console.log('sending text to conversion');
    stompClient.publish({
        destination: "/app/convert",
        body: JSON.stringify({'content': $("#text").val()})
    });
}

function pidor() {
    console.log('pidor');
    stompClient.publish({
        destination: "/app/pidor",
        body: JSON.stringify({'content': $("#text").val()})
    });
}

function cancelConversion() {
    console.log('cancelling conversion');
    stompClient.publish({
        destination: "/app/cancelConversion"
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
    $( "#cancel" ).click(() => cancelConversion());

    // Call disconnect() when the page is closed
    $(window).on('beforeunload', () => {
        disconnect();
    });
});

