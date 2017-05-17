$(function () {
  log('Requesting Capability Token...');
  $.getJSON('../twilioWebApp/ligacao/token')
    .done(function (data) {
      log('Got a token.');
      console.log('Token: ' + data.token);

      // Setup Twilio.Device
      Twilio.Device.setup(data.token);

      Twilio.Device.ready(function (device) {
        log('Twilio.Device Ready!');
        document.getElementById('call-controls').style.display = 'block';
      });

      Twilio.Device.error(function (error) {
        log('Twilio.Device Error: ' + error.message);
      });

      Twilio.Device.connect(function (conn) {
        log('Successfully established call!');
        document.getElementById('button-call').style.display = 'none';
        document.getElementById('button-hangup').style.display = 'inline';
        // HULLO ADDED - COUNTER
        clearInterval(x);
        now = 0;
        x = setInterval(function(){ callTimer() },1000);
        document.getElementById("timer").style.display = 'inline';
        // end hullo added - counter
      });

      Twilio.Device.disconnect(function (conn) {
        log('Call ended.');
        document.getElementById('button-call').style.display = 'inline';
        document.getElementById('button-hangup').style.display = 'none';
        document.getElementById("timer").style.display = 'none';
        clearInterval(x);
        

        
      //ADDED HULLO - Toggle student grade
        var e = document.getElementById('grade');
        if(e.style.display == 'none')
            e.style.display = 'block';  
      });

      Twilio.Device.incoming(function (conn) {
        log('Incoming connection from ' + conn.parameters.From);
        var archEnemyPhoneNumber = '+12099517118';

        if (conn.parameters.From === archEnemyPhoneNumber) {
          conn.reject();
          log('It\'s your nemesis. Rejected call.');
        } else {
          // accept the incoming connection and start two-way audio
          conn.accept();
        }
      });

      setClientNameUI(data.identity);
    })
    .fail(function () {
      log('Could not get a token from server!');
    });

  // Bind button to make call
  document.getElementById('button-call').onclick = function () {
    // get the phone number to connect the call to
    var params = {
      To: document.getElementById('phone-number').value,
   // HULLO: adicionado parâmatro com ID da aula realizada
      IdAula: document.getElementById('aulaRealizadaAtual.id_aula_realizada').value
    };

    console.log('Calling ' + params.To + '...');
    Twilio.Device.connect(params);
  };

  // Bind button to hangup call
  document.getElementById('button-hangup').onclick = function () {
    log('Hanging up...');
    Twilio.Device.disconnectAll();
  };

});

// Activity log
function log(message) {
  var logDiv = document.getElementById('log');
  logDiv.innerHTML += '<p>&gt;&nbsp;' + message + '</p>';
  logDiv.scrollTop = logDiv.scrollHeight;
}

// Set the client name in the UI
function setClientNameUI(clientName) {
  var div = document.getElementById('client-name');
  div.innerHTML = 'Your client name: <strong>' + clientName +
    '</strong>';
}

// HULLO ADDED - CALL TIMER
var now = 0;
function callTimer(){
	var distance = 300 - now;
	var minutes = Math.floor(distance/60);
	var seconds = Math.floor(distance%60);
	//output result to element with id="timer"
	document.getElementById("timer").innerHTML = minutes + "min " + seconds + "s ";
	//when countdown is over
	if (distance < 0) {
		clearInterval(x);
		document.getElementById("timer").innerHTML = "Encerrar";
	}	
	now++;
}
var x = setInterval(function(){ callTimer() },1000);
clearInterval(x);