package br.edu.ifsc.shopping_cart.modelo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ShoppingCart extends BaseEntity {
	private static final long serialVersionUID = -3981926681740040046L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private BigDecimal amount;
	
	@OneToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "shoppingCart")
	private List<CommerceItem> commerceItems;
	
	public ShoppingCart() {
	
	}
	
	public ShoppingCart(BigDecimal amount, Usuario usuario) {
		this.amount = amount;
		this.usuario = usuario;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<CommerceItem> getCommerceItems() {
		return commerceItems;
	}

	public void setItems(List<CommerceItem> commerceItems) {
		this.commerceItems = commerceItems;
	}
}
