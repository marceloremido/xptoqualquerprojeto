package br.com.sysloccOficial.financeiro.analitico.individual;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualDAO;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualItauDAO;

@Controller
public class AnaliticoIndividualMovimentacaoFinanceira {

	@Autowired private AnaliticoIndividualItauDAO analiticoItauDAO;
	@Autowired private AnaliticoIndividualDAO analiticoIndDAO;
	
	
	@RequestMapping("salvaNovaEntrada")
	@ResponseBody
	private ModelAndView salvaNovaEntrada(Integer idAnalitico,String DataPgto, String valor,String descricao,String ndnf,Integer idBanco) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro/itau/itauEntradaAjax");
		
		analiticoItauDAO.salvaNovaEntrada(idAnalitico,DataPgto,valor,descricao,ndnf,idBanco);
		MV.addObject("idAnalitico",idAnalitico);
		MV.addObject("entradasItau", analiticoIndDAO.carregaAnaliticoItauEntrada(idAnalitico));
		return MV;
	}
	
	
	@RequestMapping("editaMovimentacaoFinanceira")
	@ResponseBody
	private ModelAndView editaMovimentacaoFinanceira(Integer idTabela,String valor,String tipoCampo) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/telefonesAjax");
		
		Integer idAnalitico = analiticoItauDAO.editaMovFinanceiro(idTabela,valor,tipoCampo);
		MV.addObject("idAnalitico",idAnalitico);

		/*List<FinancTelefone> analitico2 = analiticoIndDAO.carregaAnaliticoTelefone(idAnalitico);
		MV.addObject("telefone",analitico2);*/
		return MV;
	}
	
	
	
}
