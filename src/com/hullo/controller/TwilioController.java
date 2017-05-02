package com.hullo.controller;

import java.io.IOException;
import java.math.BigDecimal;

//import static spark.Spark.get;
//import static spark.Spark.post;
//import static spark.Spark.staticFileLocation;
//import static spark.Spark.afterAfter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

import com.google.gson.Gson;
import com.twilio.Twilio;
// Token generation imports
import com.twilio.jwt.Jwt;
import com.twilio.jwt.client.ClientCapability;
import com.twilio.jwt.client.IncomingClientScope;
import com.twilio.jwt.client.OutgoingClientScope;
import com.twilio.jwt.client.Scope;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Call.Status;
// TwiML generation imports
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.Dial;
import com.twilio.twiml.Number;
import com.twilio.twiml.Record;
import com.twilio.twiml.Client;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;

@Controller
@RequestMapping("/twilioWebApp")
public class TwilioController extends HttpServlet {
	
	//Adicionando as credenciais
	public static final String ACCOUNT_SID = "AC8963db92d979cf31fbdb8df728e70966";
	public static final String AUTH_TOKEN = "03cf87dad37d23d8f45472b33f6d977a";
	public static final String APPLICATION_SID = "AP5f8a739c058a2ee4874b0122957bab73";


	// Generate token in JSON format
	@GetMapping("/ligacao/token")
	public void getToken(HttpServletRequest request, HttpServletResponse response) throws IOException, TwiMLException{
        
		// Log all requests and responses
        //afterAfter(new LoggingFilter());
           
		// Generate a random username for the connecting client
		
            String identity = "Aluno";

        // Generate capability token
            List<Scope> scopes = new ArrayList<>();
            scopes.add(new IncomingClientScope(identity));
            scopes.add(new OutgoingClientScope.Builder(APPLICATION_SID).build());
            Jwt jwt = new ClientCapability.Builder(ACCOUNT_SID, AUTH_TOKEN).scopes(scopes).build();
            String token = jwt.toJwt();

            // create JSON response payload
            HashMap<String, String> json = new HashMap<>();
            json.put("identity", identity);
            json.put("token", token);

            // Render JSON response
            response.setContentType("application/json");
            Gson gson = new Gson();
            response.getWriter().print(gson.toJson(json));
        }

        // Generate voice TwiML
	@PostMapping("/voice")
	public void postVoice(
			@RequestParam("To") String to,
			@RequestParam("CallSid") String callSid,
			@RequestParam("CallStatus") String callStatus,
			
			HttpServletRequest request, HttpServletResponse response) throws IOException, TwiMLException{

		
		System.out.println("To: "+ to +"\nCallSid: " + callSid + "\nCallStatus: " + callStatus);
		//cria a String to (para)
		//String to = "5511987720698";
		//cria o objeto number
		Number number = new Number.Builder(to).build();
		//cria a String callerId
		String callerId = "551149507002";
		// Use <Record> to record the caller's message
	    Record record = new Record.Builder().build();
		
		//Cria o objeto dialBuilder e define seus parâmetros
		Dial.Builder dialBuilder = new Dial.Builder();
		dialBuilder.callerId(callerId);
		dialBuilder.number(number);
		dialBuilder.timeLimit(315);
		dialBuilder.timeout(15);
		
		
		//cria o objeto VoiceResponse
		VoiceResponse voiceTwimlResponse = new VoiceResponse.Builder()
				//.record(record)
				.dial(dialBuilder.build()).build();
            response.setContentType("text/xml");
            response.getWriter().print(voiceTwimlResponse.toXml());      
    } 
	
	// Recebe os dados finais da ligacao
	@PostMapping("/callback")
	@ResponseStatus(value=HttpStatus.OK)
	public void statusCallback(
			@RequestParam("CallDuration") String callDuration,
			@RequestParam("CallSid") String callSid,
			HttpServletRequest request, HttpServletResponse response) {
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Call call = Call.fetcher(callSid).fetch();
		DateTime startTime = call.getStartTime();
		DateTime endTime = call.getEndTime();
		BigDecimal price = call.getPrice();
		Status status = call.getStatus();
		
		
		System.out.println(
				"\nPós ligação"+
				"\nDuração: "+callDuration+
				"\nSid: "+callSid+
				"\nStatus chamada: "+status+
				"\nGravação: " /*+recordingUrl*/+
				"\nInício: "+startTime+
				"\nTérmino: "+endTime+
				"\nCusto: "+price);		
	}
	
	
	//Exibe a página com os controles para a chamada
	@RequestMapping("/ligacao")
	public String fazerLigacao(){
		return "twiliowebapp";
	}
}
