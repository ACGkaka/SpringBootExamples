# springboot-websocket

## 1. 简介

> **WebSocket**：是一种`网络传输协议`，可在单个TCP连接上进行`全双工通信`，位于OSI模型的应用层。
>
> * WebSocket 使得客户端和服务端之间的数据交换变得更加简单，<font color="red">允许服务端主动向客户端推送数据</font>。
> * 在 WebSocket API 中，浏览器和服务器只需一次握手，就可以创建持久性的连接，并<font color="red">进行双向数据传输</font>。

## 2. 项目结构

<img src="https://img-blog.csdnimg.cn/f161f1558aa444ddb15a18257d25796a.png" width="40%" />


## 3. 项目地址

**github：**[https://github.com/ACGkaka/SpringBootExamples/tree/main/springboot-websocket](https://github.com/ACGkaka/SpringBootExamples/tree/main/springboot-websocket)

**本地测试：** http:\\\\localhost:8080/websocket/socketTest

## 4. 配置

### 4.1 Maven

```xml
<!-- Websocket -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>

<!-- thymeleaf -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

### 4.2 WebSocketConfig

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <p> @Title WebSocketConfig
 * <p> @Description Websocket 配置类
 *
 * @author ACGkaka
 * @date 2022/1/10 10:00
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndPointExporter() {
        return new ServerEndpointExporter();
    }
}
```

## 5. Java 代码

### 5.1 WebSocketController.java

```java
import com.demo.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> @Title WebSocketController
 * <p> @Description websocket controller
 *
 * @author ACGkaka
 * @date 2022/1/10 10:13
 */
@Slf4j
@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    /**
     * 本地socket测试
     */
    @GetMapping("/socketTest")
    public String socketTest() {
        return "socketTest";
    }

    /**
     * 页面请求
     */
    @GetMapping("/view/{socketId}")
    public String socketView(@PathVariable String socketId, Model model) {
        if (socketId == null || socketId.trim().length() == 0) {
            return "socketFail";
        }
        model.addAttribute("socketId", socketId);
        return "socketView";
    }

    /**
     * 推送数据接口
     */
    @GetMapping("/push/{socketId}")
    public Map<String, Object> pushToMap(@PathVariable String socketId, String message) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 推送数据
            WebSocketServer.sendInfo(message, socketId);
            result.put("code", 200);
            result.put("msg", "success");
        } catch (IOException e) {
            // 抛出异常
            log.error(e.getMessage(), e);
            result.put("code", 500);
            result.put("msg", "推送数据失败：" + e.getMessage());
        }
        return result;
    }
}
```

### 5.2 WebSocketServer.java

```java
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p> @Title WebSocketServer
 * <p> @Description Websocket 服务器
 *
 * @author ACGkaka
 * @date 2022/1/10 10:02
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServer {

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收sid
     */
    private String sid = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
        this.sid = sid;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        // 从set中删除
        webSocketSet.remove(this);
        // 在线数减1
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + sid + "的信息:" + message);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}
```

## 6. 本地socket测试

<img src="https://img-blog.csdnimg.cn/616a62f4521948e899458c5297243ef1.png" width="80%" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>本地websocket测试</title>
    <meta name="robots" content="all"/>
    <meta name="keywords" content="本地,websocket,测试工具"/>
    <meta name="description" content="本地,websocket,测试工具"/>
    <style>
        .btn-group {
            display: inline-block;
        }
    </style>
</head>
<body>
<input type='text' value='ws://localhost:8080/websocket/1' class="form-control" style='width:390px;display:inline'
       id='wsaddr'/>
<div class="btn-group">
    <button type="button" class="btn btn-default" onclick='addsocket();'>连接</button>
    <button type="button" class="btn btn-default" onclick='closesocket();'>断开</button>
    <button type="button" class="btn btn-default" onclick='$("#wsaddr").val("")'>清空</button>
</div>
<div class="row">
    <div id="output" style="border:1px solid #ccc;height:365px;overflow: auto;margin: 20px 0;"></div>
    <input type="text" id='message' class="form-control" style='width:810px' placeholder="待发信息" onkeydown="en(event);">
    <span class="input-group-btn">
				<button class="btn btn-default" type="button" onclick="doSend();">发送</button>
			</span>
</div>
</div>
</body>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script language="javascript" type="text/javascript">
    function formatDate(now) {
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var date = now.getDate();
        var hour = now.getHours();
        var minute = now.getMinutes();
        var second = now.getSeconds();
        return year + "-" + (month = month < 10 ? ("0" + month) : month) + "-" + (date = date < 10 ? ("0" + date) : date) +
            " " + (hour = hour < 10 ? ("0" + hour) : hour) + ":" + (minute = minute < 10 ? ("0" + minute) : minute) + ":" + (
                second = second < 10 ? ("0" + second) : second);
    }

    var output;
    var websocket;

    function init() {
        output = document.getElementById("output");
        testWebSocket();
    }

    function addsocket() {
        var wsaddr = $("#wsaddr").val();
        if (wsaddr == '') {
            alert("请填写websocket的地址");
            return false;
        }
        StartWebSocket(wsaddr);
    }

    function closesocket() {
        websocket.close();
    }

    function StartWebSocket(wsUri) {
        websocket = new WebSocket(wsUri);
        websocket.onopen = function (evt) {
            onOpen(evt)
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

    function onOpen(evt) {
        writeToScreen("<span style='color:red'>连接成功，现在你可以发送信息啦！！！</span>");
    }

    function onClose(evt) {
        writeToScreen("<span style='color:red'>websocket连接已断开!!!</span>");
        websocket.close();
    }

    function onMessage(evt) {
        writeToScreen('<span style="color:blue">服务端回应&nbsp;' + formatDate(new Date()) + '</span><br/><span class="bubble">' +
            evt.data + '</span>');
    }

    function onError(evt) {
        writeToScreen('<span style="color: red;">发生错误:</span> ' + evt.data);
    }

    function doSend() {
        var message = $("#message").val();
        if (message == '') {
            alert("请先填写发送信息");
            $("#message").focus();
            return false;
        }
        if (typeof websocket === "undefined") {
            alert("websocket还没有连接，或者连接失败，请检测");
            return false;
        }
        if (websocket.readyState == 3) {
            alert("websocket已经关闭，请重新连接");
            return false;
        }
        console.log(websocket);
        $("#message").val('');
        writeToScreen('<span style="color:green">你发送的信息&nbsp;' + formatDate(new Date()) + '</span><br/>' + message);
        websocket.send(message);
    }

    function writeToScreen(message) {
        var div = "<div class='newmessage'>" + message + "</div>";
        var d = $("#output");
        var d = d[0];
        var doScroll = d.scrollTop == d.scrollHeight - d.clientHeight;
        $("#output").append(div);
        if (doScroll) {
            d.scrollTop = d.scrollHeight - d.clientHeight;
        }
    }


    function en(event) {
        var evt = evt ? evt : (window.event ? window.event : null);
        if (evt.keyCode == 13) {
            doSend()
        }
    }
</script>

</html>
```

<br/>

整理完毕，完结撒花~ :sunflower: