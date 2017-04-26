var stompClient = null;

function connect() {
    var socket = new SockJS('/pokesgi');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/chat', function (chat) {
            showGreeting(JSON.parse(chat.body).content);
        });
        sendMessage("join the chat.");
    });
}

function disconnect() {
    if (stompClient != null) {
        sendMessage("quit the chat.");
        stompClient.disconnect();
    }
    window.location.href = "http://localhost:8080/deconnexion";
}

function sendMessage(message) {
    stompClient.send("/app/chat", {}, JSON.stringify({'message': message}));
}

function showGreeting(message) {
    $("#chat").append("<tr><td>" + message + "</td></tr>");
    $("#divChat").scrollTop($("#divChat")[0].scrollHeight);
}

$(function () {
    connect();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(": " + $("#name").val()); });
});