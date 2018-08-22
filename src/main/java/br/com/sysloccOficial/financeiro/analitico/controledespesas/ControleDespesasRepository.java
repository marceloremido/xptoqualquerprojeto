package br.com.sysloccOficial.financeiro.analitico.controledespesas;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.financeiro.model.ControleDespesas;


@Repository
public class ControleDespesasRepository {

	
	@PersistenceContext	private EntityManager manager;
	
	
	public List<ControleDespesas> listaControleDespesas() throws NullPointerException {
		TypedQuery<ControleDespesas> query = manager.createQuery("FROM ControleDespesas",ControleDespesas.class);
		
		if(query.getResultList().isEmpty() || query.getResultList().equals(null)){
			throw new NullPointerException("Não existem dados com os parâmetros informados.");
		}
		return query.getResultList();
	}
	
}