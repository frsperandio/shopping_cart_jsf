package br.edu.ifsc.shopping_cart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifsc.shopping_cart.modelo.Product;
import br.edu.ifsc.shopping_cart.modelo.ShoppingCart;
import br.edu.ifsc.shopping_cart.util.JPAUtil;

public class ProductDAO extends DAO<Product> {
	public ProductDAO() {
		super(Product.class);
	}
}
