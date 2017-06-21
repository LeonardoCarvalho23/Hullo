package com.hullo.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Classe utilitaria para conversao de data/hullo
 * 
 * @author Hullo Team
 * @version 1.0
 */

public class DataConversion {

	/**
	 * Transformar data em hora BR para exibir para o usuario
	 * 
	 * @param data_banco
	 * @return
	 */
	public LocalDateTime banco2br(LocalDateTime data_banco) {

		
		ZonedDateTime data_local = data_banco.atZone(ZoneId.of("America/Sao_Paulo"));
		
//		so para testar com outros fusos
//		ZonedDateTime data_local2 = data_local.withZoneSameInstant(ZoneId.of("Europe/Paris"));
//		System.out.println("Tokyo : " + data_local2);
		
		return data_local.toLocalDateTime();
	}

}
