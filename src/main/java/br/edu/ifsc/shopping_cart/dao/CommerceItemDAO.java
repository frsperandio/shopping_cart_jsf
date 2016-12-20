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

public class CommerceItemDAO extends DAO<CommerceItem> {
	public CommerceItemDAO() {
		super(CommerceItem.class);
	}
	
	public CommerceItem getByProduct(Product product, ShoppingCart shoppingCart) {
		TypedQuery<CommerceItem> query = em.createQuery("SELECT x FROM CommerceItem x WHERE x.shoppingCart = :shoppingCart AND x.product = :product", CommerceItem.class);
		query.setParameter("shoppingCart", shoppingCart);
		query.setParameter("product", product);
		List<CommerceItem> results = query.getResultList();
		if (!results.isEmpty()) 
			return results.get(0);
		else
			return null;
	}
}
