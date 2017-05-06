package com.hullo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//import static spark.Spark.get;
//import static spark.Spark.post;
//import static spark.Spark.staticFileLocation;
//import static spark.Spark.afterAfter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

import com.google.gson.Gson;
import com.hullo.service.AulaRealizadaServiceImpl;
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
import com.twilio.twiml.TwiMLException;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/twilioWebApp")
public class TwilioController extends HttpServlet {
	
	//Adiciona as credenciais geradas para nossa conta no Twilio
	public static final String ACCOUNT_SID = "AC8963db92d979cf31fbdb8df728e70966";
	public static final String AUTH_TOKEN = "03cf87dad37d23d8f45472b33f6d977a";
	public static final String APPLICATION_SID = "AP5f8a739c058a2ee4874b0122957bab73";

	@Autowired
	private AulaRealizadaServiceImpl aulaRealizadaService;
	
	// Generate token in JSON format
	@GetMapping("/ligacao/token")
	public void getToken(HttpServletRequest request, HttpServletResponse response) throws IOException, TwiMLException{
        
	// O JS (quickstart.js) do Twilio gera um nome aleatório de cliente. Aqui, estou setando como "Aluno".
	// Podemos eliminar essa exigência no futuro.
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
	// TwiML é a linguagem do Twilio, com a qual nos comunicamos com ele via XML
	@PostMapping("/voice")
	public void postVoice(
			// este paramatro IdAula foi acrescentado ao JS do Twilio (quickstart.js)
			@RequestParam("IdAula") int id_aula,
			@RequestParam("To") String to,
			@RequestParam("CallSid") String callSid,
			@RequestParam("CallStatus") String callStatus,
			HttpServletRequest request, HttpServletResponse response) throws IOException, TwiMLException{

		//Atualiza a aulaRealizada para incluir o CallSid
		try {
			aulaRealizadaService.updateAulaRealizada(id_aula, callSid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//cria o objeto number
		Number number = new Number.Builder(to).build();
		//cria a String callerId
		String callerId = "551149507002";
		// Use <Record> to record the caller's message
		// Não estamos setando isso ainda, tá dando pau
	    //Record record = new Record.Builder().build();
		
		//Cria o objeto dialBuilder e define seus parâmetros
		Dial.Builder dialBuilder = new Dial.Builder();
		dialBuilder.callerId(callerId);
		dialBuilder.number(number);
		dialBuilder.timeLimit(15); // duração de 5min da chamada
		dialBuilder.timeout(15); // desliga se não atender após 15s
		
		
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
			HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		//chama os demais dados da chamada
		// inicia conexão com twilio
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
		Call call = Call.fetcher(callSid).fetch();
		DateTime startTime = call.getStartTime();
		DateTime endTime = call.getEndTime();
		BigDecimal price = call.getPrice();
		Status status = call.getStatus();
		
		// Converte as datas que chegam em RFC 2822 para o datetime do MySQL
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse(startTime.toString());
		String startTimeConv = output.format(d);
		d = sdf.parse(endTime.toString());
		String endTimeConv = output.format(d);
	
		//Atualiza as informações da chamada pós ligação
		aulaRealizadaService.updateAulaRealizada(callSid, callDuration, status, startTimeConv,
				endTimeConv, price);
	}
	
	
	//Exibe a página com os controles para a chamada
	@RequestMapping("/ligacao")
	public String fazerLigacao(){
		return "twiliowebapp";
	}
}
