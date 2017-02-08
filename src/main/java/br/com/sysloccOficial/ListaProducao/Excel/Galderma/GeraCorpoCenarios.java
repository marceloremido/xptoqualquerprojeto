package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import br.com.sysloccOficial.Excel.ExcelImagem;
import br.com.sysloccOficial.model.GrupoCategoriaGalderma;

@Component
public class GeraCorpoCenarios {
	
	/**
	 * Método para gerar o cabeçalho da aba
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	/*public static void geraCabecalhoAbaCenarios(XSSFSheet cenario,XSSFWorkbook excelGalderma,String nomeAba){

	}*/
	
	public static void geraCorpoAbaCenarios(XSSFSheet cenario,XSSFWorkbook excelGalderma,String nomeAba,
											List<CorpoGrupoCategoriaGalderma> gruposParaExcel,List<GrupoCategoriaGalderma> categoriasGalderma)
											throws FileNotFoundException, IOException{
		
		cenario = excelGalderma.createSheet(nomeAba); /** Cria Aba Cenarios da planilha */
		cenario.setZoom(80);
		ExcelImagem.InsereImagem(excelGalderma, cenario, "C:/SYSLOC/upload/logoEmpresas/logoExcelAgencia2.png",0.35);
		GeraCabecalhoExcelGalderma.geraCabecalho(cenario, excelGalderma, nomeAba);

		
		//Não precisa mexer mais
		CorpoCenarioGaldermaTopo.geraTopoEstatico(excelGalderma, cenario, 17);
		
		
		passaInformacoesCorpoExcel(cenario, excelGalderma, gruposParaExcel, categoriasGalderma);
		
		//Não mexer mais
		GeraTextoRodapeCenarios.geraTextoRodape(excelGalderma, cenario);
	}

	/**
	 * Separa por Categoria 
	 * 
	 * Método que passa as informações para gerar o corpo do
	 * Excel com as informações da planilha do cliente
	 * Info de quantidade, diaria, valor unitario inicial, negociado
	 */
	private static void passaInformacoesCorpoExcel(XSSFSheet cenario, XSSFWorkbook excelGalderma, List<CorpoGrupoCategoriaGalderma> gruposParaExcel,List<GrupoCategoriaGalderma> categoriasGalderma) {
		
		int linhaComecoCategorias = 20;
		int linhaComecoInfoCategorias = 21;
		int qtdInfoGrupo = 0;
		
		for (int i = 0; i < categoriasGalderma.size(); i++) {
			
			GeraTextoCategorias.geratextoCategorias(excelGalderma, cenario, linhaComecoCategorias + qtdInfoGrupo,categoriasGalderma.get(i).getCategoria()); // ok
			linhaComecoCategorias = linhaComecoCategorias+1;
			for (int j = 0; j < gruposParaExcel.size(); j++) {
						if(categoriasGalderma.get(i).getIdCategoriaGalderma().equals(gruposParaExcel.get(j).getIdCategoriaGalderma())){
					    //Chama método para gerar o corpo
						CorpoCenarioGalderma.corpoCenario(excelGalderma, cenario,linhaComecoInfoCategorias,gruposParaExcel.get(j));
						linhaComecoInfoCategorias=linhaComecoInfoCategorias+1;
						linhaComecoCategorias = linhaComecoCategorias+1;
						qtdInfoGrupo = qtdInfoGrupo + 1;
					}
			}

			
		}
		//System.out.println(""+linhaComecoInfoCategorias);
	}
	
	/**
	 * Método para gerar rodapé da aba
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public static void geraCorpoAbaCenariosOpcionais(XSSFSheet cenario,XSSFWorkbook excelGalderma,String nomeAba,String infoGrupo) throws FileNotFoundException, IOException{
	  cenario = excelGalderma.createSheet(nomeAba); /** Cria Aba Cenarios da planilha */
	  cenario.setZoom(80);
	  	ExcelImagem.InsereImagem(excelGalderma, cenario, "C:/SYSLOC/upload/logoEmpresas/logoExcelAgencia2.png",0.35);
		GeraCabecalhoExcelGalderma.geraCabecalho(cenario, excelGalderma, nomeAba);
		CorpoCenarioGalderma.corpoCenarioOpcionais(excelGalderma, cenario,infoGrupo,250,450, new BigDecimal("3596.00"), new BigDecimal("3596.00"));
		GeraTextoRodapeCenarios.geraTextoRodapeOpcionais(excelGalderma, cenario);
	}
}
