package br.edu.ifsc.shopping_cart.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.edu.ifsc.shopping_cart.dao.ProductDAO;

public class CommerceItem {
	//id: String The commerce item id, unique per commerce item, used to identify the commerce item inside the shopping cart.
	private String id;
	
	//product_id: String The product id inside this commerce item.
	private String productId;
	
	//Quantity: Integer The quantity added to the shopping cart.
	private Integer quantity;
	
	//Amount: BigDecimal Item amount, calculated mulitplying quantity by the product price.
	private BigDecimal amount;
	
	public CommerceItem() {
		this.id = "";
		this.productId = "";
		this.quantity = 0;
		this.amount = new BigDecimal(0.0);
	}
	
	public String getId() {
		return id;
	}

	public String getProductId() {
		return productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Product getProduct() {
		return ProductDAO.getProductById(this.productId);
	}
}
