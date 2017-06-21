package com.hullo.testClasses;

import java.time.LocalDateTime;

import com.hullo.utility.DataConversion;

public class TesteDataConversion {

	public static void main(String[] args) {
		
		DataConversion conversor = new DataConversion();
		LocalDateTime ldt = LocalDateTime.now();
        
        System.out.println(conversor.banco2br(ldt));

	}

}
