package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.ExcelBordas;
import br.com.sysloccOficial.Excel.ExcelCelulaBackground;
import br.com.sysloccOficial.Excel.ExcelCelulaEspecial;
import br.com.sysloccOficial.Excel.ExcelFonts;
import br.com.sysloccOficial.Excel.ExcelMerge;

@Component
public class CalculoRodapeCenario {
	
	private static XSSFCellStyle estilo;
	
	
	
	
	
	
	public static void calculosRodapePlanilha(XSSFWorkbook excelGalderma,XSSFSheet cenario,int ultimaLinhaCorpo,int[] linhasSubtotais){
		
		
		for (int i : linhasSubtotais) {
			System.out.println(i);
		}		
		
		String formulaInicial = "E26+E32+E38+E46+E52";
		String formulaNegociado = "G26+G32+G38+G46+G52";
		
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+1, "Subtotal Geral",new int[]{0,176,240},formulaInicial,formulaNegociado);
		
		
		
		
	/*	CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+2, "Investimento - Serviços Terceiros - PGTO VIA NOTA DE DÉBITO",new int[]{219,219,219});
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+3, "Investimento - Serviços Agência",new int[]{219,219,219});
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+4, "FEE Agência",new int[]{219,219,219},5.2);
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+5, "Impostos Emissão NF - Serviços Agência",new int[]{219,219,219},22.9);
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+6, "TOTAL PREVISTO",new int[]{0,176,240});
		*/
		
	}
	
	
	
	
	
	
	
	/**
	 * Método que faz a linha de 
	 * SubTotal Geral
	 * Investimentos de Terceiros
	 * Investimentos de Agência
	 * 
	 * @param excelGalderma
	 * @param cenario
	 */
	public static XSSFRow montaCelulas(XSSFWorkbook excelGalderma,XSSFSheet cenario,int numLinha,String texto,int[] corFundo,String formulaInicial, String formulaNegociado){
		
		XSSFRow linha = cenario.createRow(numLinha);
		XSSFCell cell = linha.createCell(0);
		
		estilo = excelGalderma.createCellStyle();
		estilo.setFont(ExcelFonts.fontBold(excelGalderma, 12, "Tahoma"));
		estilo = ExcelBordas.borda(estilo);
		cell.setCellStyle(ExcelCelulaBackground.background(estilo, corFundo));
		cell.setCellValue(texto);
		
		ExcelMerge.merge(excelGalderma, cenario, cell, linha, 0, 2);
		ExcelCelulaEspecial.formatoFormula(excelGalderma, estilo, linha, 4, formulaInicial);
		ExcelCelulaEspecial.formatoFormula(excelGalderma, estilo, linha, 6, formulaNegociado);
		
		return linha;
		
	}

	
	public static void calculoRodapeCenario(XSSFWorkbook excelGalderma,XSSFSheet cenario,int numLinha,String texto,int[] corFundo,String formulaInicial, String formulaNegociado){
			
		XSSFRow linha = montaCelulas(excelGalderma, cenario, numLinha, texto, corFundo,formulaInicial,formulaNegociado);
		XSSFCell cell2 = linha.createCell(3);cell2.setCellStyle(estilo);
		XSSFCell cell3 = linha.createCell(5);cell3.setCellStyle(estilo);
		
	}

	public static void calculoRodapeCenario(XSSFWorkbook excelGalderma,XSSFSheet cenario,int numLinha,String texto,int[] corFundo,double porcentagem,String formulaInicial, String formulaNegociado){
		
		XSSFRow linha = montaCelulas(excelGalderma, cenario, numLinha, texto, corFundo,formulaInicial,formulaNegociado);
		
		XSSFCellStyle estiloPorcentagem = excelGalderma.createCellStyle();
		estiloPorcentagem.setFont(ExcelFonts.fontBold(excelGalderma, 12, "Tahoma"));
		estiloPorcentagem = ExcelBordas.borda(estiloPorcentagem);
		estiloPorcentagem = ExcelCelulaBackground.background(estiloPorcentagem, corFundo);
		
        ExcelCelulaEspecial.formatoPorcentagem(excelGalderma, estiloPorcentagem, porcentagem, linha, 3);
        ExcelCelulaEspecial.formatoPorcentagem(excelGalderma, estiloPorcentagem, porcentagem, linha, 5);
		
	}
}
