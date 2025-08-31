<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<video id="vid" autoplay></video>
</body>
<script src="opencv.js" onload="onOpenCvReady();" type="text/javascript"></script>
<script>
	navigator.mediaDevices.getUserMedia({
		video: {
			width:{
				min:640,
				max: 1280
			},
			height: {
				min:480,
				max:720
			},
			frameRate:1
		}
	})
	.then(stream => {
			document.getElementById("vid").srcObject = stream;
			
			var socket = io();

			while(streaming) {
			   socket.emit(stream);
			}
	});
</script>
</html>