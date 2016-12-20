package br.edu.ifsc.shopping_cart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import br.edu.ifsc.shopping_cart.modelo.Product;
import br.edu.ifsc.shopping_cart.modelo.ShoppingCart;
import br.edu.ifsc.shopping_cart.modelo.Usuario;
import br.edu.ifsc.shopping_cart.util.JPAUtil;

public class UsuarioDAO extends DAO<Usuario> {
	public UsuarioDAO() {
		super(Usuario.class);
	}
	
	public Usuario getByEmail(String email) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE lower(email) like :email", Usuario.class);
		query.setParameter("email", email.toLowerCase());
		List<Usuario> results = query.getResultList();
		if (!results.isEmpty()) 
			return results.get(0);
		else
			return null;
	}
	
	public Usuario getByEmailESenha(String email, String senha) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE lower(email) like :email AND senha like :senha", Usuario.class);
		query.setParameter("email", email.toLowerCase());
		query.setParameter("senha", senha.toLowerCase());
		List<Usuario> results = query.getResultList();
		if (!results.isEmpty()) 
			return results.get(0);
		else
			return null;
	}
}
