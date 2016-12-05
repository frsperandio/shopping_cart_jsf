package br.edu.ifsc.shopping_cart.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.shopping_cart.dao.ProductDAO;

public class ShoppingCart {
	//items: array[CommerceItem] All the commerce items on the current shopping cart.
	private List<CommerceItem> items;
	
	//Amount: BigDecimal Order total value, calculated summing the amount of all commerce items.
	private BigDecimal amount;
	
	public ShoppingCart() {
		this.items = new ArrayList<CommerceItem>();
		this.amount = new BigDecimal(0.0);
	}
	
	public List<CommerceItem> getItems() {
		return items;
	}
	
	public void setItems(List<CommerceItem> items) {
		this.items = items;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public void addItem(String productId) {
		int id = this.items.size()+1;
		CommerceItem commerceItem = null;
		for (int i = 0; i < this.items.size(); i++) {
			if(this.items.get(i).getProductId().equals(productId)) {
				commerceItem = this.items.get(i);
				break;
			}
		}
		if(commerceItem != null) {
			commerceItem.setQuantity(commerceItem.getQuantity()+1);
		}
		else {
			commerceItem = new CommerceItem();
			commerceItem.setId(Integer.toString(id));
			commerceItem.setProductId(productId);
			commerceItem.setQuantity(1);
			items.add(commerceItem);
		}
		commerceItem.setAmount(commerceItem.getProduct().getPrice().multiply(new BigDecimal(commerceItem.getQuantity())));
		this.amount = calculateAmount();
	}

	public void removeItem(String commerceItemId) {
		int index = getListIndex(commerceItemId);
		CommerceItem commerceItem = this.items.get(index);
		if(commerceItem != null && commerceItem.getQuantity() > 1) {
			commerceItem.setQuantity(commerceItem.getQuantity()-1);
			commerceItem.setAmount(commerceItem.getProduct().getPrice().multiply(new BigDecimal(commerceItem.getQuantity())));
		}
		else {
			this.items.remove(index);
		}
		this.amount = calculateAmount();
	}
	
	public int getListIndex(String commerceItemId) {
		int index = -1;
		for (int i = 0; i < this.items.size(); i++) {
			if(this.items.get(i).getProductId().equals(commerceItemId)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public BigDecimal calculateAmount() {
		BigDecimal totalAmount = new BigDecimal(0.0);
		for (int i = 0; i < this.items.size(); i++) {
			totalAmount = totalAmount.add(this.items.get(i).getAmount());
		}
		return totalAmount;
	}
}
