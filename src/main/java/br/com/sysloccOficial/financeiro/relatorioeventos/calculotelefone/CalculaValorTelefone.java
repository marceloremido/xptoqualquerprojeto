package br.com.sysloccOficial.financeiro.relatorioeventos.calculotelefone;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualDAO;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;


public class CalculaValorTelefone implements CalculoValorTelefone {
	
	private AnaliticoIndividualDAO analiticoDAO;
	private RelatorioEventoDAO relatorioDAO;
	
	public CalculaValorTelefone(AnaliticoIndividualDAO analiticoDAO,RelatorioEventoDAO relatorioDAO) {
		this.analiticoDAO = analiticoDAO;
		this.relatorioDAO = relatorioDAO;
	}
	
	@Override
	public BigDecimal calculoValorTelefone(BigDecimal giroSemTelefoneEvento,Integer idRelatorioAtual, String mes, String ano) {
		
		BigDecimal razaoCalculoTelefone = new BigDecimal("0.00");
		BigDecimal validador = new BigDecimal("0.00");
		
		/**
		 * Posso pegar o soma dos outros giros que estiverem cadastrados no banco e somar com o giro atual
		 * assim teremos a soma de todos os giros do mes
		 */
		BigDecimal somaGirosEventosMes = relatorioDAO.somaGirosPorAnoMes(ano, mes, idRelatorioAtual);
		
		BigDecimal valorTelefone = analiticoDAO.somaValorTelefonePorMesAno(mes,ano);
		
		if(valorTelefone.equals(new BigDecimal("0.00"))){
			return valorTelefone;
		}else{
			// Pegar o valor do giro sem telefone
			BigDecimal valorGiroDesseEvento = giroSemTelefoneEvento;
			
			// Dividir o giro desse evento pela soma de todos os eventos
			/**
			 * Rever esses dois ifs, porque o primeiro faz a mesma coisa que o segundo
			 */
			if(somaGirosEventosMes.equals(validador) || somaGirosEventosMes == null || somaGirosEventosMes.equals(valorGiroDesseEvento)){
				razaoCalculoTelefone = new BigDecimal("1");
			}

			else{
				razaoCalculoTelefone = valorGiroDesseEvento.divide( somaGirosEventosMes,12,RoundingMode.UP);
			}
			
			// Pegar o resultado e multiplicar pelo valor mensal do telefone
			BigDecimal valorTelefoneEvento = valorTelefone.multiply(razaoCalculoTelefone);
			
			return valorTelefoneEvento;
		}
	}

}
