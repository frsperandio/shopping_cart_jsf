package br.edu.ifsc.shopping_cart.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shopping_cart");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}