<!DOCTYPE html>
<html lang="pt">
<head>
<script src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	
<script
	src="${pageContext.request.contextPath}/resources/js/stopwatch.js"></script>
</head>

<body>
<div>
    <h1>Stopwatch</h1>
    <span id="sw_h">00</span>:
    <span id="sw_m">00</span>:
    <span id="sw_s">00</span>:
    <span id="sw_ms">00</span>
    <br/>
    <br/>
    <input type="button" value="Start" id="sw_start" />
    <input type="button" value="Pause" id="sw_pause" />
    <input type="button" value="Stop"  id="sw_stop" />
    <input type="button" value="Reset" id="sw_reset" />
    <br/>
    <br/>
    <span id="sw_status">Idle</span>
</div>
<div>
    Don't run them at the same time ... it's a demo :-)
</div>
<div>
    <h1>Countdown</h1>
    <span id="cd_h">00</span>:
    <span id="cd_m">00</span>:
    <span id="cd_s">00</span>:
    <span id="cd_ms">00</span>
    <br/>
    <br/>
    <input type="button" value="Start" id="cd_start" />
    <input type="button" value="Pause" id="cd_pause" />
    <input type="button" value="Stop"  id="cd_stop" />
    <input type="button" value="Reset" id="cd_reset" />
    <br/>
    <br/>
    <input type="text" value="15" id="cd_seconds" />
    secs
    <br/>
    <br/>
    <span id="cd_status">Idle</span>
</div>
</body>
</html>