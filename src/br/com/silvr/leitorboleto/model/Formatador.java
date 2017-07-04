package br.com.silvr.leitorboleto.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

class Formatador {
	
	private static final Pattern VALOR_ZERADO = Pattern.compile("^0+$");
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Calendar getDataVencimento(String representacao) {
		
		int dia = Integer.parseInt(representacao.substring(6));
		int mes = Integer.parseInt(representacao.substring(4, 6));
		int ano = Integer.parseInt(representacao.substring(0, 4));
		
		Calendar dataVencimento = Calendar.getInstance();
		dataVencimento.setLenient(false);
		
		try {
			dataVencimento.set(ano, mes - 1, dia);
			DATE_FORMAT.format(dataVencimento.getTime());
			return dataVencimento;
		} catch(Exception e) {
			return null;
		}
		
	}
	
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
