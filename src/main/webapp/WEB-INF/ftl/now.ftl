<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/noVNC/1.2.0/novnc.min.js"></script>
</head>
<body>
<!-- 创建一个canvas元素用于显示VNC连接的图像 -->
<canvas id="noVNC_canvas"></canvas>

<script type="text/javascript">
    // 创建noVNC连接
    var rfb = new window.noVNC.RFB(document.getElementById('noVNC_canvas'), "ws://your-server-ip:your-server-port", { shared: true });

    // 连接成功后的回调函数
    rfb.addEventListener("connect", function () {
        console.log("noVNC连接成功");
    });

    // 连接断开后的回调函数
    rfb.addEventListener("disconnect", function () {
        console.log("noVNC连接断开");
    });

    // 启动noVNC连接
    rfb.connect();
</script>
</body>
</html>