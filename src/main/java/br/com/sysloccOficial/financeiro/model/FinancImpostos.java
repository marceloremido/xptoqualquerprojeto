package br.com.sysloccOficial.financeiro.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class FinancImpostos {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idFinancImpostos;
	private BigDecimal valor;
	private String descricao;
	private boolean fixo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
// ------------------------------------------------------------------ //	
	@OneToOne @JoinColumn(name="analitico") private FinancAnalitico analitico;
// ------------------------------------------------------------------ //	

	public Integer getIdFinancImpostos() {
		return idFinancImpostos;
	}

	public boolean isFixo() {
		return fixo;
	}

	public void setFixo(boolean fixo) {
		this.fixo = fixo;
	}

	public void setIdFinancImpostos(Integer idFinancImpostos) {
		this.idFinancImpostos = idFinancImpostos;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public FinancAnalitico getAnalitico() {
		return analitico;
	}

	public void setAnalitico(FinancAnalitico analitico) {
		this.analitico = analitico;
	}
}
