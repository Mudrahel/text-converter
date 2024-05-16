const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8585/converter-websocket' //would be good to take this variable from configuration
});

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/conversion', (portion) => {
        showResult(portion.body);
    });
    stompClient.subscribe('/topic/conversion-complete', () => {
            endProcessing();
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
    var textValue = $("#text").val();
    if (textValue.length === 0) {
        console.log('There is nothing to process');
        return; // Do nothing if there is no text to process
    }

    startProcessing();
    console.log('Sending text to conversion');
    $("#result").val('');
    stompClient.publish({
        destination: "/app/convert",
        body: JSON.stringify({'content': textValue})
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

const sendTextButton = document.getElementById('sendText');
const processingPopup = document.getElementById('processingPopup');

function startProcessing() {
    console.log('start processing UI');
    // Processing started, disable button and show popup
    $("#sendText").prop('disabled',true);
    $("#cancel").prop('disabled',false);
    $("#processingPopup").css('display', 'block');
}

function endProcessing() {
    console.log('end processing UI');
    // Processing completed, enable button and hide popup
    $("#sendText").prop('disabled',false);
    $("#cancel").prop('disabled',true);
    $("#processingPopup").css('display', 'none');
}

function copyToClipBoard() {
    // Select the text in the textarea
    var textarea = $("#result");
    textarea.select();
    // Copy the selected text to clipboard
    document.execCommand("copy");
}

$(function () {
    // Call connect() on page load
    connect();

    $("form").on('submit', (e) => e.preventDefault());
    $( "#sendText" ).click(() => sendTextToConversion());
    $( "#cancel" ).click(() => {
        cancelConversion();
        endProcessing();
    });
    $( "#copyButton" ).click(() => copyToClipBoard());

    // Call disconnect() when the page is closed
    $(window).on('beforeunload', () => {
        disconnect();
    });
});

