package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Component
public class RelatorioEventoIndividualApoio {
	
	@Autowired RelatorioEventoDAO relatorioEventoDAO;
	
	
	public RelatorioEventoIndividualApoio(){}
	
	public List<RelatorioBVS> relatorioBVS(Integer idLista){
		
		/**
		 * Aqui posso pegar quem tem fat direto e usar para ND
		 */
		List<Integer> idsFornecedoresPorList = relatorioEventoDAO.idsFornecedoresPorLista(idLista);

		
		List<ProducaoP> listaProducaoPPorIdLista = relatorioEventoDAO.listaProducaoPPorIdLista(idLista);	
		
		List<RelatorioBVS> listaRelatorioBVS = new ArrayList<RelatorioBVS>();
		
		for (Integer ids : idsFornecedoresPorList) {
			
			BigDecimal valor = new BigDecimal("0");
			BigDecimal valorParaPgar = new BigDecimal("0");
			BigDecimal diferenca = new BigDecimal("0");
			
			RelatorioBVS bvs = new RelatorioBVS();
			for (int i = 0; i < listaProducaoPPorIdLista.size(); i++) {
				Integer idsForn = listaProducaoPPorIdLista.get(i).getIdEmpFornecedor().getIdEmpresa();
				if(ids.equals(idsForn)){
					bvs.setIdFornecedor(idsForn);
					bvs.setNomeFornecedor(listaProducaoPPorIdLista.get(i).getIdEmpFornecedor().getEmpresa());
					valor = valor.add(listaProducaoPPorIdLista.get(i).getValorItem());
					valorParaPgar = valorParaPgar.add(listaProducaoPPorIdLista.get(i).getValorDePagamentoContratacao());
					diferenca = diferenca.add(listaProducaoPPorIdLista.get(i).getDiferenca());
				}
			}
			bvs.setValorFornecedor(valor);
			bvs.setValorParaPagar(valorParaPgar);
			bvs.setDiferenca(diferenca);
			
			listaRelatorioBVS.add(bvs);
		}
		
		return listaRelatorioBVS;
	}

	public List<RelatorioBVS> relatorioBVSParaND(Integer idLista){
		
		List<Integer> idsFornecedoresPorList = relatorioEventoDAO.idsFornecedoresPorListaParaND(idLista);
		
		
		/**
		 * Encurtar essa lista abaixo pegando apenas os que tem imposto = 0
		 */
		List<ProducaoP> listaProducaoPPorIdLista = relatorioEventoDAO.listaProducaoPPorIdLista(idLista);	
		
		List<RelatorioBVS> listaRelatorioBVS = new ArrayList<RelatorioBVS>();
		
		for (Integer ids : idsFornecedoresPorList) {
			
			BigDecimal valor = new BigDecimal("0");
			BigDecimal valorParaPgar = new BigDecimal("0");
			BigDecimal diferenca = new BigDecimal("0");
			
			RelatorioBVS bvs = new RelatorioBVS();
			for (int i = 0; i < listaProducaoPPorIdLista.size(); i++) {
				Integer idsForn = listaProducaoPPorIdLista.get(i).getIdEmpFornecedor().getIdEmpresa();
				if(ids.equals(idsForn)){
					bvs.setIdFornecedor(idsForn);
					bvs.setNomeFornecedor(listaProducaoPPorIdLista.get(i).getIdEmpFornecedor().getEmpresa());
					valor = valor.add(listaProducaoPPorIdLista.get(i).getValorItem());
					//Essa linha abaixo eu mudei porque estava dando errado o valor de contratação
					valorParaPgar = valorParaPgar.add(listaProducaoPPorIdLista.get(i).getValorContratacao());
					diferenca = diferenca.add(listaProducaoPPorIdLista.get(i).getDiferenca());
				}
			}
			bvs.setValorFornecedor(valor);
			bvs.setValorParaPagar(valorParaPgar);
			bvs.setDiferenca(diferenca);
			
			listaRelatorioBVS.add(bvs);
		}
		
		return listaRelatorioBVS;
	}
	
}
