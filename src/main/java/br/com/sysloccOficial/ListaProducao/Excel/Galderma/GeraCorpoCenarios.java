package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.ExcelImagem;
import br.com.sysloccOficial.model.Categoria;
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
											List<CorpoGrupoCategoriaGalderma> gruposParaExcel,List<Categoria> categoriasDaLista,Job job)
											throws FileNotFoundException, IOException, ParseException{
		
		cenario = excelGalderma.createSheet(nomeAba); /** Cria Aba Cenarios da planilha */
		cenario.setZoom(80);
		
		ExcelImagem.InsereImagem(excelGalderma, cenario, "C:/SYSLOC/upload/logoEmpresas/logoExcelAgencia2.png",0.67);
	
		GeraCabecalhoExcelGalderma.geraCabecalhoFormula(cenario, excelGalderma, nomeAba, job);

		
		//Não precisa mexer mais
		CorpoCenarioGaldermaTopo.geraTopoEstatico(excelGalderma, cenario, 17);
		
		int linhasParaConsolidado = passaInformacoesGerarCorpoExcelNovo(cenario, excelGalderma, gruposParaExcel, categoriasDaLista);
		
		//Não mexer mais
		if(nomeAba.equals("Opcionais")){
			GeraTextoRodapeCenarios.geraTextoRodapeOpcionais(excelGalderma, cenario,linhasParaConsolidado+2);
		}else{
			GeraTextoRodapeCenarios.geraTextoRodape(excelGalderma, cenario,linhasParaConsolidado+2);
		}
	
		return linhasParaConsolidado;
		
	}
	
	
	////////////////////////////////////////////////
	
	public static int geraCorpoAbaCenariosTESTE(XSSFSheet cenario, XSSFWorkbook excelGalderma, String nomeAba,
													List<CorpoGrupoCategoriaGalderma> gruposParaExcel,
											List<GrupoCategoriaGalderma> categoriasDaLista, Job job)throws FileNotFoundException, IOException, ParseException {

		cenario = excelGalderma.createSheet(nomeAba);
		/** Cria Aba Cenarios da planilha */
		cenario.setZoom(80);

		ExcelImagem.InsereImagem(excelGalderma, cenario,
				"C:/SYSLOC/upload/logoEmpresas/logoExcelAgencia2.png", 0.67);

		GeraCabecalhoExcelGalderma.geraCabecalhoFormula(cenario, excelGalderma,
				nomeAba, job);

		// Não precisa mexer mais
		CorpoCenarioGaldermaTopo.geraTopoEstatico(excelGalderma, cenario, 17);

		int linhasParaConsolidado = passaInformacoesCorpoExcel( cenario, excelGalderma, gruposParaExcel, categoriasDaLista);

		// Não mexer mais
		if (nomeAba.equals("Opcionais")) {
			GeraTextoRodapeCenarios.geraTextoRodapeOpcionais(excelGalderma, cenario, linhasParaConsolidado + 2);
		} else {
			GeraTextoRodapeCenarios.geraTextoRodape(excelGalderma, cenario, linhasParaConsolidado + 2);
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
				
						/*if(categoriasGalderma.get(i).getIdCategoriaGalderma() == gruposParaExcel.get(j).getIdCategoriaGalderma()){*/
						
							CorpoCenarioGalderma.corpoCenario(excelGalderma, cenario,linhaComecoInfoCategorias,gruposParaExcel.get(j)); //Chama método para gerar o corpo
	
							qtdInfoGrupo2 = linhaComecoInfoCategorias + 1;

							

							linhaComecoInfoCategorias = linhaComecoInfoCategorias + 1;
							taxaISS = gruposParaExcel.get(j).getTxISS();
							taxaServico = gruposParaExcel.get(j).getTxServico();
							
					/*}*/
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

	private static int passaInformacoesGerarCorpoExcelNovo(XSSFSheet cenario, XSSFWorkbook excelGalderma, List<CorpoGrupoCategoriaGalderma> gruposParaExcel,List<Categoria> categoriasDaLista) {
		
		int linhaComecoCategorias = 20;
		int linhaComecoInfoCategorias = 21;
		int linhasConsolidado = 0;
		
		List<Integer[]> linhasSubtotais = new ArrayList<Integer[]>();
		
		
		
		int qtdInfoGrupo2 = 0;
		int qtdInfoGrupo3 = 0;
		int ultimaLinhaGrupoCategoria = 0;
		int primeiraLinhaGrupoCategoria = 0;
		
		//Para cada categoria Galderma
		for (int i = 0; i < categoriasDaLista.size(); i++) {
			
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
				//Gera a linha com o nome da Categoria
				GeraTextoCategorias.geratextoCategorias(excelGalderma, cenario, qtdInfoGrupo3,categoriasDaLista.get(i).getCategoria()); // ok
			}
			
			
			// Tx da categoria
			double taxaISS = 0;
			double taxaServico = 0;
			
			for (int j = 0; j < gruposParaExcel.size(); j++) {
				
				int idcategoria = categoriasDaLista.get(i).getIdcategoria();
				int idcategoriaNoGrupo = gruposParaExcel.get(j).getIdCategoriaGalderma();
				
				if(idcategoria == idcategoriaNoGrupo){
					
					CorpoCenarioGalderma.corpoCenario(excelGalderma, cenario,linhaComecoInfoCategorias,gruposParaExcel.get(j)); //Chama método para gerar o corpo
					
					qtdInfoGrupo2 = linhaComecoInfoCategorias + 1;
					
					
					
					linhaComecoInfoCategorias = linhaComecoInfoCategorias + 1;
					taxaISS = gruposParaExcel.get(j).getTxISS();
					taxaServico = gruposParaExcel.get(j).getTxServico();
					
				}
			}
			
			
			
			ultimaLinhaGrupoCategoria = qtdInfoGrupo2;
			
			Integer[] linhas = new Integer[2];
			
			if(categoriasDaLista.get(i).getIdcategoria() == 8){
				//linhas[0] = ultimaLinhaGrupoCategoria+4; //Alterado
				linhas[0] = ultimaLinhaGrupoCategoria+1; //Alterado
				linhas[1] = 8;
				linhasSubtotais.add(linhas);
			}else{
				// linhas[0] = ultimaLinhaGrupoCategoria+4; //Alterado
				linhas[0] = ultimaLinhaGrupoCategoria+1;
				linhas[1] = 0;
				linhasSubtotais.add(linhas);
			}
			
			if(cenario.getSheetName().equals("Opcionais")){
				
			}else{
				
				
				CorpoCenarioGalderma.geraSubTotalCadaCategoria(excelGalderma, cenario,primeiraLinhaGrupoCategoria, ultimaLinhaGrupoCategoria,taxaServico,taxaISS);
				
				
				
				
				// qtdInfoGrupo2 = qtdInfoGrupo2+4; Alterado
				qtdInfoGrupo2 = qtdInfoGrupo2+1;
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
