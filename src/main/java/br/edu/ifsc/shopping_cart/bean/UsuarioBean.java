package br.edu.ifsc.shopping_cart.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsc.shopping_cart.dao.ProductDAO;
import br.edu.ifsc.shopping_cart.dao.UsuarioDAO;
import br.edu.ifsc.shopping_cart.modelo.Product;
import br.edu.ifsc.shopping_cart.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {
	
	private List<Usuario> usuarios;
	
	public UsuarioBean() {
		usuarios = UsuarioDAO.getInstance().listaTodos();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
}
