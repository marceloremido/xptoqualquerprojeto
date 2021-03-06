package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.ExcelBordas;
import br.com.sysloccOficial.Excel.ExcelCelulaEspecial;
import br.com.sysloccOficial.Excel.ExcelEstiloPadrao;
import br.com.sysloccOficial.Excel.ExcelFormatoCelulaComum;


@Component
public class GeraConteudoConsolidado {
	
	public static int[] corFundoCelulaPadrao = {255,255,254};
	
	public static int geraConteudo(XSSFWorkbook excel, XSSFSheet cenario,String nomeAba,int linhaComeco,int linhasConsolidado,int numCenario,int qtdPessoasDias,String infoLista, String deadline){
		
		int linhaParaFormula = linhaComeco+1;
		
		XSSFRow linha2 = cenario.createRow(linhaComeco); linha2.setHeightInPoints(35);

		XSSFCell cell = linha2.createCell(0);
		
		if(nomeAba.equals("Opcionais")){
			
			System.out.println();
			
		}else{
			nomeAba = nomeAba+"0"+numCenario;
		}
	
		cell.setCellValue(nomeAba);
		
		cell.setCellStyle(ExcelEstiloPadrao.estiloPadraoBoldCentralizado(excel,estiloConteudo(excel)));
		
		ExcelCelulaEspecial.formatoFormula(excel, ExcelEstiloPadrao.estiloPadraoBoldCentralizado(excel,estiloConteudo(excel)), linha2, 1, "'"+nomeAba+"'!E"+(linhasConsolidado+2));
		ExcelCelulaEspecial.formatoFormula(excel, ExcelEstiloPadrao.estiloPadraoBoldCentralizado(excel,estiloConteudo(excel)), linha2, 2, "'"+nomeAba+"'!G"+(linhasConsolidado+2));
		ExcelFormatoCelulaComum.numeroCentralizadoBold(excel, linha2, qtdPessoasDias, 3, corFundoCelulaPadrao);
		ExcelCelulaEspecial.formatoFormula(excel, ExcelEstiloPadrao.estiloPadraoBoldCentralizado(excel,estiloConteudo(excel)), linha2, 4, "C"+(linhaComeco+1)+"/D"+(linhaComeco+1)+"");
		ExcelCelulaEspecial.formatoFormula(excel, ExcelEstiloPadrao.estiloPadraoBoldCentralizado(excel,estiloConteudo(excel)), linha2, 5, "B"+(linhaComeco+1)+"-C"+(linhaComeco+1)+"");
		ExcelFormatoCelulaComum.textoSimplesCentralizadoBold(excel, cell, linha2, deadline, 6, corFundoCelulaPadrao);
		ExcelFormatoCelulaComum.textoSimplesCentralizadoBold(excel, cell, linha2, infoLista, 7, corFundoCelulaPadrao);
		
		
		return linhaParaFormula;
	}
	
	
	
	
	
	
	private static XSSFFont estiloConteudo(XSSFWorkbook excel){
		
		XSSFFont font = excel.createFont();
		font.setBold(true);

		XSSFCellStyle estilo = excel.createCellStyle();
		estilo.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		estilo.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		estilo = ExcelBordas.borda(estilo);		
		estilo.setFont(font);
		
		return font;
		
	}
	
	
	
	
	

}
