package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Repository /*Notação para mapear o ProdutoDAO*/
@Transactional /*Notação para o Spring fazer a transação*/
public class ProdutoDAO {
	
	@PersistenceContext /* Notação para injetar o EntityManager */
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() { 
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto find(Integer id) {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos where "
				+ "p.id = :id", Produto.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	public void remover(Integer produtoId) {
		
		Produto produto = null;
		
		produto = manager.find(Produto.class, produtoId);
		manager.remove(produto);
		System.out.println("Produto removido.");
	}
	
	public BigDecimal somaPrecoPorTipo(TipoPreco tipoPreco) {
		TypedQuery<BigDecimal> query = manager.createQuery("select sum(preco.valor) from Produto p join p.precos preco where preco.tipo = :tipoPreco", BigDecimal.class);
	    query.setParameter("tipoPreco", tipoPreco);
	    return query.getSingleResult();
	}

}
