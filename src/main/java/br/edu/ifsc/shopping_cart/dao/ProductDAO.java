package br.edu.ifsc.shopping_cart.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifsc.shopping_cart.modelo.Product;

public class ProductDAO {
	private final static Map<String, Product> PRODUCTS = new HashMap<>();
	static {
		PRODUCTS.put("1", new Product("1", "Produto 1", "resources/img/165457.jpg", new BigDecimal(1000.0)));
		PRODUCTS.put("2", new Product("2", "Produto 2", "resources/img/165459.jpg", new BigDecimal(750.0)));
		PRODUCTS.put("3", new Product("3", "Produto 3", "resources/img/165460.jpg", new BigDecimal(500.0)));
		PRODUCTS.put("3", new Product("3", "Produto 3", "resources/img/165475.jpg", new BigDecimal(500.0)));
		PRODUCTS.put("3", new Product("3", "Produto 3", "resources/img/165476.jpg", new BigDecimal(500.0)));
		PRODUCTS.put("3", new Product("3", "Produto 3", "resources/img/165478.jpg", new BigDecimal(500.0)));
		PRODUCTS.put("3", new Product("3", "Produto 3", "resources/img/165480.jpg", new BigDecimal(500.0)));
		PRODUCTS.put("3", new Product("3", "Produto 3", "resources/img/165482.jpg", new BigDecimal(500.0)));
		PRODUCTS.put("3", new Product("3", "Produto 3", "resources/img/165483.jpg", new BigDecimal(500.0)));
	}

	public static List<Product> getProducts() {
		return new ArrayList<Product>(PRODUCTS.values());
	}
	
	public static Product getProductById(String productId) {
		return PRODUCTS.get(productId);
	}
}
