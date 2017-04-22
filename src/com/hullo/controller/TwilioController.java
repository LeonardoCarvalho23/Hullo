package com.hullo.controller;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.type.PhoneNumber;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.net.URI;


@Controller
@RequestMapping("/twilio")
public class TwilioController extends HttpServlet {
	
	// Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC8963db92d979cf31fbdb8df728e70966";
    public static final String AUTH_TOKEN = "03cf87dad37d23d8f45472b33f6d977a";

	
	@RequestMapping("/twiml")
	@Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Create a TwiML response and add our friendly message.
        VoiceResponse voiceResponse = new VoiceResponse.Builder()
                .say(new Say.Builder("Hello Pangossauro, I love you.").build())
                .build();

        response.setContentType("application/xml");
        try {
            response.getWriter().print(voiceResponse.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
	
	@RequestMapping("/makecall")
	public String makeCall(Model model){
		
		TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();

        PhoneNumber to = new PhoneNumber("5511987720698"); // Replace with your phone number
        PhoneNumber from = new PhoneNumber("551149507002"); // Replace with a Twilio number
        URI uri = URI.create("http://demo.twilio.com/welcome/voice/");

        // Make the call
        Call call = Call.creator(to, from, uri).create(client);
        // Print the call SID (a 32 digit hex like CA123..)
        //System.out.println(call.getSid());
        String mensagem = call.getSid();
        model.addAttribute("chamada", mensagem);
        return "makecall";
	}
    
}
