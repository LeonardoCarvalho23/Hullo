package com.hullo.utility;

import java.util.Date;

/**
 * Classe utilitaria para conversao de data/hullo
 * 
 * @author Hullo Team
 * @version 1.0
 */

public class DataConversion {

	/**
	 * Transformar data local em UTC para guardar no banco
	 * @param data
	 * @return data em utc
	 */
	public Date local2utc(Date dataLocal) {

		Date date_utc = new Date();

		return date_utc;
	}

	/**
	 * retornar data atual me formato UTC
	 * 
	 * @return
	 */
	public Date atualUt() {

		Date date_utc = new Date();

		return date_utc;
	}

	/**
	 * Transformar data UTC em local para exibir para o usuario
	 * @param data_utc
	 * @return
	 */
	public Date utc2local(Date data_utc){
		Date date_local = new Date();
		
		return date_local;
	}

}
