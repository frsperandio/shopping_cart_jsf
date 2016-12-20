package br.edu.ifsc.shopping_cart.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsc.shopping_cart.dao.ProductDAO;
import br.edu.ifsc.shopping_cart.modelo.Product;

@ManagedBean
@ViewScoped
public class ProdutoBean {
	private List<Product> produtos;
	
	public ProdutoBean() {
		produtos = new ProductDAO().listaTodos();
	}
	
	public List<Product> getProdutos() {
		return produtos;
	}
	
}
