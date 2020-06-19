package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

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

}
