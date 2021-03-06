package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class FornecedorNaoTemNegociacaoComContratacaoIgualZero implements CalculoFornecedorInterna {

	private CalculoFornecedorInterna proximo;
	
	@Override
	public BigDecimal calculaValorFornecedor(ProducaoP producaoP) {

		boolean verificaContratacao = producaoP.getValorContratacao().equals(new BigDecimal("0.00"));
		boolean diferencaIgualZero = producaoP.getDiferenca().equals(new BigDecimal("0.00"));
		
		
		
		if(verificaContratacao == true && diferencaIgualZero == true && producaoP.isTemContratacao() == false){
			
			BigDecimal valorFinal = producaoP.getValorItem();
			producaoP.setValorFornecedor(valorFinal);
			
			producaoP.setDiferencaParaLocco(calculaDiferenca(producaoP));
			
			return valorFinal;
		}else{
			return proximo.calculaValorFornecedor(producaoP);
		}
	}

	@Override
	public BigDecimal calculaDiferenca(ProducaoP producaoP) {
		BigDecimal valorDiferencaCalculado = new BigDecimal("0.00");
		return valorDiferencaCalculado;
	}

	@Override
	public void setProximo(CalculoFornecedorInterna proximo) {
		this.proximo = proximo;
	}

}
