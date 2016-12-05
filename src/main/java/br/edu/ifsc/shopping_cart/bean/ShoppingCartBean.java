package br.edu.ifsc.shopping_cart.bean;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsc.shopping_cart.dao.ProductDAO;
import br.edu.ifsc.shopping_cart.dao.UsuarioDAO;
import br.edu.ifsc.shopping_cart.modelo.CommerceItem;
import br.edu.ifsc.shopping_cart.modelo.Product;
import br.edu.ifsc.shopping_cart.modelo.ShoppingCart;
import br.edu.ifsc.shopping_cart.modelo.Usuario;

@ManagedBean
@ViewScoped
public class ShoppingCartBean {
	
	private ShoppingCart shoppingCart;
	
	public ShoppingCartBean() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(context.getExternalContext().getSessionMap().containsKey("shoppingCart")) {
			shoppingCart = (ShoppingCart)context.getExternalContext().getSessionMap().get("shoppingCart");
		}
		else {
			shoppingCart = new ShoppingCart();
		}
	}

	public List<CommerceItem> getItems() {
		return shoppingCart.getItems();
	}
	
	public void addItem(String productId) {
		shoppingCart.addItem(productId);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Informação",  "Item adicionado com sucesso!"));
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(shoppingCart.getAmount());
		context.addMessage(null, new FacesMessage("Informação", "Total do carrinho: " + moneyString));
		context.getExternalContext().getSessionMap().put("shoppingCart", shoppingCart);
    }
	
	public void removeItem(String commerceItemId) {
		shoppingCart.removeItem(commerceItemId);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Informação",  "Item removido com sucesso!"));
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(shoppingCart.getAmount());
		context.addMessage(null, new FacesMessage("Informação", "Total do carrinho: " + moneyString));
		context.getExternalContext().getSessionMap().put("shoppingCart", shoppingCart);
    }
	
	public BigDecimal getAmount() {
		return shoppingCart.getAmount();
    }
}
