function formatDate(now) {
    const nowTime = dayjs();
    return nowTime.format('YYYY-MM-DD HH:mm:ss.SSS');
}

var websocket;

function loginSuccess(token, userId) {
    writeToScreen('<span style="color:green">登录成功,userId=' + userId + ',token=' + token + '</span><br/>');
    addsocket(token, userId);
}

function addsocket(token, userId) {
    var wsaddr = "ws://127.0.0.1:10100/websocket?token=" + token + "&userId=" + userId;
    StartWebSocket(wsaddr);
}


function StartWebSocket(wsUri) {
    websocket = new WebSocket(wsUri);
    websocket.binaryType = 'arraybuffer';

    websocket.onopen = function (evt) {
        onOpen(evt, wsUri)
    };
    websocket.onclose = function (evt) {
        onClose(evt)
    };
    websocket.onmessage = function (evt) {
        onMessage(evt)
    };
    websocket.onerror = function (evt) {
        onError(evt)
    };
}

function onOpen(evt, wsUri) {
    let data = '{"name":"LiaoXiang Link Success"}';
    writeToScreen("<span style='color:red'>连接成功，现在你可以发送信息进行测试了！</span>");
    writeToScreen(wsUri);
    sendMsgByInput(1, 1, data);

    startAsyncSendPosition();
}

function onClose(evt) {
    writeToScreen("<span style='color:red'>Websocket连接已断开！</span>");
    websocket.close();
}

function binaryData(data) {
    return JSON.parse(new TextDecoder("utf-8").decode(new Uint8Array(data)))
}

function onMessage(evt) {
    debugger
    let externalMessage = binaryData(evt.data);
    let bizData = externalMessage.data;
    let bizDataJson = binaryData(bizData);
    externalMessage.data = bizDataJson;
    console.log(bizDataJson);
    let json = JSON.stringify(externalMessage);
    if(externalMessage.cmdMerge === 65537){
        // hello
        writeToScreen('<span style="color:blue">服务端回应&nbsp;' + formatDate(new Date()) + '</span><br/><span>' + json + '</span>');
    }else if(externalMessage.cmdMerge === 131073){
        // near
        nearUserRefresh(bizDataJson);
    }
}

function onError(evt) {
    writeToScreen('<span style="color: red;">发生错误:</span> ' + evt.data);
}

function sendMsgByInput(cmd, subCmd, data) {
    var dataJson = JSON.parse(data)
    var message = {
        cmdCode: 1,
        cmdMerge: merge(cmd, subCmd),
        data: dataJson
    }
    var json = JSON.stringify(message);
    if(cmd !== 2){
        writeToScreen('<span style="color:green">你发送的信息&nbsp;' + formatDate(new Date()) + '</span><br/>' + json);
    }
    var textEncoder = new TextEncoder();
    var dataArray = textEncoder.encode(JSON.stringify(dataJson));
    message.data = Array.from(dataArray);
    json = JSON.stringify(message);
    let uint8Array = textEncoder.encode(json);
    websocket.send(uint8Array);
}

function merge(cmd, subCmd) {
    return Number((cmd << 16)) + Number(subCmd);
}

function writeToScreen(message) {
    var div = "<div>" + message + "</div>";
    var d = $("#output");
    var d = d[0];
    var doScroll = d.scrollTop == d.scrollHeight - d.clientHeight;
    $("#output").append(div);
    if (doScroll) {
        d.scrollTop = d.scrollHeight - d.clientHeight;
    }
}