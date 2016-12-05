package br.edu.ifsc.shopping_cart.modelo;

import java.math.BigDecimal;

public class Product {
	//id: String A unique identifier for the product
	private String id;
	
	//name: String Display name of product.
	private String name;
	
	//Image: String Image URL representing the product.
	private String image;
	
	//Price: BigDecimal The price of the product.
	private BigDecimal price;
	
	public Product(String id, String name, String image, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public BigDecimal getPrice() {
		return price;
	}
}
