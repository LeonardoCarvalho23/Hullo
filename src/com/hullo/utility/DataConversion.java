package com.hullo.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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

		//banco esta em UTC
		ZonedDateTime data_local = data_banco.atZone(ZoneId.of("UTC"));
		
//		so transforma em BR
		ZonedDateTime data_local2 = data_local.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
		
		return data_local2.toLocalDateTime();
	}

}
