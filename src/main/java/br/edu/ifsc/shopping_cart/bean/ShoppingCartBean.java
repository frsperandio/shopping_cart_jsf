package br.edu.ifsc.shopping_cart.bean;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsc.shopping_cart.dao.CommerceItemDAO;
import br.edu.ifsc.shopping_cart.dao.ProductDAO;
import br.edu.ifsc.shopping_cart.dao.ShoppingCartDAO;
import br.edu.ifsc.shopping_cart.dao.UsuarioDAO;
import br.edu.ifsc.shopping_cart.modelo.CommerceItem;
import br.edu.ifsc.shopping_cart.modelo.Product;
import br.edu.ifsc.shopping_cart.modelo.ShoppingCart;
import br.edu.ifsc.shopping_cart.modelo.Usuario;

@ManagedBean
@ViewScoped
public class ShoppingCartBean {
	
	private Usuario usuarioLogado;
	
	public ShoppingCartBean() {
		FacesContext context = FacesContext.getCurrentInstance();
		usuarioLogado = (Usuario)context.getExternalContext().getSessionMap().get("usuarioLogado");
		ShoppingCart shoppingCart = new ShoppingCartDAO().getByUser(usuarioLogado);
		if(shoppingCart == null) {
			shoppingCart = new ShoppingCart(new BigDecimal(0.0), usuarioLogado);
			new ShoppingCartDAO().adiciona(shoppingCart);
			shoppingCart = new ShoppingCartDAO().getByUser(usuarioLogado);
		}
	}

	public List<CommerceItem> getItems() {
		ShoppingCart shoppingCart = new ShoppingCartDAO().getByUser(usuarioLogado);
		return new ShoppingCartDAO().buscaPorId(shoppingCart.getId()).getCommerceItems();
	}
	
	public void addItem(Product product) {
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
		CommerceItemDAO commerceItemDAO = new CommerceItemDAO();
		ShoppingCart shoppingCart = shoppingCartDAO.getByUser(usuarioLogado);
		CommerceItem commerceItem = commerceItemDAO.getByProduct(product, shoppingCart);
		if(commerceItem != null) {
			commerceItem.setQuantity(commerceItem.getQuantity()+1);
			commerceItem.setAmount(product.getPrice().multiply(new BigDecimal(commerceItem.getQuantity())));
			commerceItemDAO.atualiza(commerceItem);
		}
		else {
			commerceItem = new CommerceItem(product, 1, product.getPrice(), shoppingCart);
			commerceItemDAO.adiciona(commerceItem);
		}
		shoppingCart.setAmount(shoppingCart.getAmount().add(product.getPrice()));
		shoppingCartDAO.atualiza(shoppingCart);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Informação",  "Item adicionado com sucesso!"));
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(shoppingCart.getAmount());
		context.addMessage(null, new FacesMessage("Informação", "Total do carrinho: " + moneyString));
		context.getExternalContext().getSessionMap().put("shoppingCart", shoppingCart);
    }
	
	public void removeItem(CommerceItem commerceItem) {
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
		CommerceItemDAO commerceItemDAO = new CommerceItemDAO();
		ProductDAO productDAO = new ProductDAO();
		ShoppingCart shoppingCart = shoppingCartDAO.getByUser(usuarioLogado);
		if(commerceItem != null) {
			shoppingCart.setAmount(shoppingCart.getAmount().subtract(commerceItem.getProduct().getPrice()));
			if(commerceItem.getQuantity() > 1) {
				commerceItem.setQuantity(commerceItem.getQuantity()-1);
				commerceItem.setAmount(commerceItem.getProduct().getPrice().multiply(new BigDecimal(commerceItem.getQuantity())));
				commerceItemDAO.atualiza(commerceItem);
			}
			else {
				commerceItemDAO.remove(commerceItem);
			}
			shoppingCartDAO.atualiza(shoppingCart);
		}
		else {
			throw new RuntimeException("Registro não encontrado.");
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Informação",  "Item removido com sucesso!"));
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(shoppingCart.getAmount());
		context.addMessage(null, new FacesMessage("Informação", "Total do carrinho: " + moneyString));
		context.getExternalContext().getSessionMap().put("shoppingCart", shoppingCart);
    }
	
	public BigDecimal getAmount() {
		ShoppingCart shoppingCart = new ShoppingCartDAO().getByUser(usuarioLogado);
		return new ShoppingCartDAO().buscaPorId(shoppingCart.getId()).getAmount();
    }
}
