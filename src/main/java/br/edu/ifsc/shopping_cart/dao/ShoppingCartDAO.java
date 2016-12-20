package br.edu.ifsc.shopping_cart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import br.edu.ifsc.shopping_cart.modelo.CommerceItem;
import br.edu.ifsc.shopping_cart.modelo.Product;
import br.edu.ifsc.shopping_cart.modelo.ShoppingCart;
import br.edu.ifsc.shopping_cart.modelo.Usuario;
import br.edu.ifsc.shopping_cart.util.JPAUtil;

public class ShoppingCartDAO extends DAO<ShoppingCart> {
	public ShoppingCartDAO() {
		super(ShoppingCart.class);
	}
	
	public ShoppingCart getByUser(Usuario usuarioLogado) {
		TypedQuery<ShoppingCart> query = em.createQuery("SELECT x FROM ShoppingCart x WHERE x.usuario.id = :usuario_id", ShoppingCart.class);
		query.setParameter("usuario_id", usuarioLogado.getId());
		List<ShoppingCart> results = query.getResultList();
		if (!results.isEmpty()) 
			return results.get(0);
		else
			return null;
	}
}
