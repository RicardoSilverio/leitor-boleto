package br.com.silvr.leitorboleto.model;

import java.math.BigDecimal;
import java.util.regex.Pattern;

class Formatador {
	
	private static final Pattern VALOR_ZERADO = Pattern.compile("^0+$");
	
	public static BigDecimal getValor(String representacao) {
		
		if(VALOR_ZERADO.matcher(representacao).matches()) {
			return null;
		}
		
		return new BigDecimal(converteRepresentacaoEmDecimal(representacao));		
	}
	
	private static String converteRepresentacaoEmDecimal(String representacao) {
		return new StringBuilder(representacao.substring(0, representacao.length() - 2))
			.append(".").append(representacao.substring(representacao.length() - 2))
			.toString();
	}

}
