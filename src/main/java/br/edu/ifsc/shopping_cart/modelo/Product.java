package br.edu.ifsc.shopping_cart.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product extends BaseEntity {
	private static final long serialVersionUID = -6876583329482289529L;

	//id: String A unique identifier for the product
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//name: String Display name of product.
	private String name;
	
	//Image: String Image URL representing the product.
	private String image;
	
	//Price: BigDecimal The price of the product.
	private BigDecimal price;
	
	public Product() {
	
	}
	
	public Product(String name, String image, BigDecimal price) {
		this.name = name;
		this.image = image;
		this.price = price;
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

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
