package br.edu.ifsc.shopping_cart.util;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.edu.ifsc.shopping_cart.modelo.Product;
import br.edu.ifsc.shopping_cart.modelo.Usuario;

public class PopulaBanco {

	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();
		
		manager.persist(new Product("Produto 1", "resources/img/165457.jpg", new BigDecimal(1000.0)));
		manager.persist(new Product("Produto 2", "resources/img/165459.jpg", new BigDecimal(750.0)));
		manager.persist(new Product("Produto 3", "resources/img/165460.jpg", new BigDecimal(500.0)));
		
		manager.persist(new Usuario("professor@sematecsolucoes.com.br", "professor"));
		manager.persist(new Usuario("diretor@sematecsolucoes.com.br", "diretor"));
		manager.persist(new Usuario("admin@admin.com", "admin"));
		manager.persist(new Usuario("teste@teste.com", "teste"));
		
		manager.getTransaction().commit();

		manager.close();
	}

}
