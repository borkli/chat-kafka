let stompClient = null;
let username = 'noname';

function connect() {
    let socket = new SockJS('/ws-connect');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        username = frame.headers['user-name'];
        stompClient.subscribe('/user/chat_out/receive_message', function (payload) {
            console.log('Received new message: ' + payload);
            let msg = JSON.parse(payload.body);
            $('#chatMessages').append(msg.sender + ':\n' + msg.message + '\n');
        });
        loadChatHistory();
    });
}

//function loadChatHistory() {
//    $.get("/history", function (history) {
//        history.forEach(function (message) {
//            $('#chatMessages').append(message + '\n');
//        });
//    });
//}

function sendMessage() {
    let receiver = $('#userName').val().trim();
    let textInput = $('#message').val().trim();
    if (receiver.length === 0 || textInput.length === 0) {
        return;
    }
    $.ajax({
        url: "/send",
        data: JSON.stringify({
            'sender': username,
            'receiver': receiver,
            'message': textInput
        }),
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    });
    $('#chatMessages').append(username + ':\n' + textInput + '\n');
    $('#message').val('');
}

$('#message').keyup(function (event) {
    if (event.keyCode === 13) {
        $('#sendButton').click();
    }
});

$('#sendButton').click(function () {
    sendMessage();
});

connect();