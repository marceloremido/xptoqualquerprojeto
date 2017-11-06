package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.ExcelImagem;
import br.com.sysloccOficial.model.GrupoCategoriaGalderma;
import br.com.sysloccOficial.model.Job;

@Component
public class GeraCorpoCenarios {
	
	/**
	 * Método para gerar o cabeçalho da aba
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ParseException 
	 * 
	 */
	/*public static void geraCabecalhoAbaCenarios(XSSFSheet cenario,XSSFWorkbook excelGalderma,String nomeAba){

	}*/
	
	public static int geraCorpoAbaCenarios(XSSFSheet cenario,XSSFWorkbook excelGalderma,String nomeAba,
											List<CorpoGrupoCategoriaGalderma> gruposParaExcel,List<GrupoCategoriaGalderma> categoriasGalderma,Job job)
											throws FileNotFoundException, IOException, ParseException{
		
		cenario = excelGalderma.createSheet(nomeAba); /** Cria Aba Cenarios da planilha */
		cenario.setZoom(80);
		
		ExcelImagem.InsereImagem(excelGalderma, cenario, "C:/SYSLOC/upload/logoEmpresas/logoExcelAgencia2.png",0.67);
	
		GeraCabecalhoExcelGalderma.geraCabecalhoFormula(cenario, excelGalderma, nomeAba, job);

		
		//Não precisa mexer mais
		CorpoCenarioGaldermaTopo.geraTopoEstatico(excelGalderma, cenario, 17);
		
//		int linhasParaConsolidado = passaInformacoesCorpoExcel(cenario, excelGalderma, gruposParaExcel, categoriasGalderma);
		int linhasParaConsolidado = passaInformacoesGerarCorpoExcelNovo(cenario, excelGalderma, gruposParaExcel, categoriasGalderma);
		
		//Não mexer mais
		if(nomeAba.equals("Opcionais")){
			GeraTextoRodapeCenarios.geraTextoRodapeOpcionais(excelGalderma, cenario,linhasParaConsolidado+7);
		}else{
			GeraTextoRodapeCenarios.geraTextoRodape(excelGalderma, cenario,linhasParaConsolidado+7);
		}
	
		return linhasParaConsolidado;
		
	}

	/**
	 * Separa por Categoria 
	 * 
	 * Método que passa as informações para gerar o corpo do
	 * Excel com as informações da planilha do cliente
	 * Info de quantidade, diaria, valor unitario inicial, negociado
	 */
	private static int passaInformacoesCorpoExcel(XSSFSheet cenario, XSSFWorkbook excelGalderma, List<CorpoGrupoCategoriaGalderma> gruposParaExcel,List<GrupoCategoriaGalderma> categoriasGalderma) {
		
		int linhaComecoCategorias = 20;
		int linhaComecoInfoCategorias = 21;
		int linhasConsolidado = 0;
		
		List<Integer[]> linhasSubtotais = new ArrayList<Integer[]>();
		
		

		int qtdInfoGrupo2 = 0;
		int qtdInfoGrupo3 = 0;
		int ultimaLinhaGrupoCategoria = 0;
		int primeiraLinhaGrupoCategoria = 0;
		
		//Para cada categoria Galderma
		for (int i = 0; i < categoriasGalderma.size(); i++) {
			
			if(qtdInfoGrupo2 == 0){
				
				qtdInfoGrupo3 = linhaComecoCategorias;
				primeiraLinhaGrupoCategoria = linhaComecoInfoCategorias;
			}else {
				qtdInfoGrupo3 = qtdInfoGrupo2;
				linhaComecoInfoCategorias = qtdInfoGrupo2+1;
				primeiraLinhaGrupoCategoria = qtdInfoGrupo2+1;
				
			
				if(qtdInfoGrupo2 == 52){
					System.out.println(qtdInfoGrupo2);
				}

			
			}
			
			if(cenario.getSheetName().equals("Opcionais")){
				
			}else{
				GeraTextoCategorias.geratextoCategorias(excelGalderma, cenario, qtdInfoGrupo3,categoriasGalderma.get(i).getCategoria()); // ok
			}
			
			
			// Tx da categoria
			double taxaISS = 0;
			double taxaServico = 0;
			
			for (int j = 0; j < gruposParaExcel.size(); j++) {
				
						if(categoriasGalderma.get(i).getIdCategoriaGalderma() == gruposParaExcel.get(j).getIdCategoriaGalderma()){
						
							CorpoCenarioGalderma.corpoCenario(excelGalderma, cenario,linhaComecoInfoCategorias,gruposParaExcel.get(j)); //Chama método para gerar o corpo
	
							qtdInfoGrupo2 = linhaComecoInfoCategorias + 1;

							

							linhaComecoInfoCategorias = linhaComecoInfoCategorias + 1;
							taxaISS = gruposParaExcel.get(j).getTxISS();
							taxaServico = gruposParaExcel.get(j).getTxServico();
							
					}
			}
			
			
			
			ultimaLinhaGrupoCategoria = qtdInfoGrupo2;
			
			Integer[] linhas = new Integer[2];

				if(categoriasGalderma.get(i).getIdCategoriaGalderma() == 8){
					linhas[0] = ultimaLinhaGrupoCategoria+4;
					linhas[1] = 8;
					linhasSubtotais.add(linhas);
				}else{
					linhas[0] = ultimaLinhaGrupoCategoria+4;
					linhas[1] = 0;
					linhasSubtotais.add(linhas);
				}
				
				if(cenario.getSheetName().equals("Opcionais")){

				}else{
				
					
				 CorpoCenarioGalderma.geraSubTotalCadaCategoria(excelGalderma, cenario,primeiraLinhaGrupoCategoria, ultimaLinhaGrupoCategoria,taxaServico,taxaISS);
				 
					
					
					
					qtdInfoGrupo2 = qtdInfoGrupo2+4;
				}
		}
		linhasConsolidado = qtdInfoGrupo2;
		
		CalculoRodapeCenario.calculosRodapePlanilha(excelGalderma, cenario, qtdInfoGrupo2,linhasSubtotais);
		return linhasConsolidado;
	}

	private static int passaInformacoesGerarCorpoExcelNovo(XSSFSheet cenario, XSSFWorkbook excelGalderma, List<CorpoGrupoCategoriaGalderma> gruposParaExcel,List<GrupoCategoriaGalderma> categoriasGalderma) {
		
		int linhaComecoCategorias = 20;
		int linhaComecoInfoCategorias = 21;
		int linhasConsolidado = 0;
		
		List<Integer[]> linhasSubtotais = new ArrayList<Integer[]>();
		
		
		
		int qtdInfoGrupo2 = 0;
		int qtdInfoGrupo3 = 0;
		int ultimaLinhaGrupoCategoria = 0;
		int primeiraLinhaGrupoCategoria = 0;
		
		//Para cada categoria Galderma
		for (int i = 0; i < gruposParaExcel.size(); i++) {
			
			if(qtdInfoGrupo2 == 0){
				
				qtdInfoGrupo3 = linhaComecoCategorias;
				primeiraLinhaGrupoCategoria = linhaComecoInfoCategorias;
			}else {
				qtdInfoGrupo3 = qtdInfoGrupo2;
				linhaComecoInfoCategorias = qtdInfoGrupo2+1;
				primeiraLinhaGrupoCategoria = qtdInfoGrupo2+1;
				
				
				if(qtdInfoGrupo2 == 52){
					System.out.println(qtdInfoGrupo2);
				}
				
				
			}
			
			if(cenario.getSheetName().equals("Opcionais")){
				
			}else{
				GeraTextoCategorias.geratextoCategorias(excelGalderma, cenario, qtdInfoGrupo3,gruposParaExcel.get(i).getInfoGrupo()); // ok
			}
			
			
			// Tx da categoria
			double taxaISS = 0;
			double taxaServico = 0;
			
			
			CorpoCenarioGalderma.corpoCenario(excelGalderma, cenario,linhaComecoInfoCategorias,gruposParaExcel.get(i)); //Chama método para gerar o corpo
			
			qtdInfoGrupo2 = linhaComecoInfoCategorias + 1;
			
			
			
			linhaComecoInfoCategorias = linhaComecoInfoCategorias + 1;
			taxaISS = gruposParaExcel.get(i).getTxISS();
			taxaServico = gruposParaExcel.get(i).getTxServico();
					
			
			
			
			
			ultimaLinhaGrupoCategoria = qtdInfoGrupo2;
			
			Integer[] linhas = new Integer[2];
			
			if(categoriasGalderma.get(i).getIdCategoriaGalderma() == 8){
				linhas[0] = ultimaLinhaGrupoCategoria+4;
				linhas[1] = 8;
				linhasSubtotais.add(linhas);
			}else{
				linhas[0] = ultimaLinhaGrupoCategoria+4;
				linhas[1] = 0;
				linhasSubtotais.add(linhas);
			}
			
			if(cenario.getSheetName().equals("Opcionais")){
				
			}else{
				
				
				CorpoCenarioGalderma.geraSubTotalCadaCategoria(excelGalderma, cenario,primeiraLinhaGrupoCategoria, ultimaLinhaGrupoCategoria,taxaServico,taxaISS);
				
				
				
				
				qtdInfoGrupo2 = qtdInfoGrupo2+4;
			}
		}
		linhasConsolidado = qtdInfoGrupo2;
		
		CalculoRodapeCenario.calculosRodapePlanilha(excelGalderma, cenario, qtdInfoGrupo2,linhasSubtotais);
		return linhasConsolidado;
	}

	
	/**
	 * Método para gerar rodapé da aba
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	/*public static void geraCorpoAbaCenariosOpcionais(XSSFSheet cenario,XSSFWorkbook excelGalderma,String nomeAba,String infoGrupo) throws FileNotFoundException, IOException{
	  cenario = excelGalderma.createSheet(nomeAba); *//** Cria Aba Cenarios da planilha *//*
	  cenario.setZoom(80);
	  	ExcelImagem.InsereImagem(excelGalderma, cenario, "C:/SYSLOC/upload/logoEmpresas/logoExcelAgencia2.png",0.35);
		GeraCabecalhoExcelGalderma.geraCabecalho(cenario, excelGalderma, nomeAba);
		CorpoCenarioGalderma.corpoCenarioOpcionais(excelGalderma, cenario,infoGrupo,250,450, new BigDecimal("3596.00"), new BigDecimal("3596.00"));
		GeraTextoRodapeCenarios.geraTextoRodapeOpcionais(excelGalderma,18,cenario);
	}*/
}
