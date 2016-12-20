package br.edu.ifsc.shopping_cart.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsc.shopping_cart.dao.UsuarioDAO;
import br.edu.ifsc.shopping_cart.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {
	private String email;
	private String senha;
	
	public LoginBean() {
	
	}
	
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		context.getExternalContext().getSessionMap().remove("shoppingCart");
		return "login?faces-redirect=true";
	}

	public String efetuaLogin() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = new UsuarioDAO().getByEmailESenha(this.email, this.senha);
		if (usuario != null) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			return "produtos?faces-redirect=true";
		}
		else {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Usuário não encontrado"));
			return "login?faces-redirect=true";			
		}
	}
	
	public String signOut() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().clear();
		return "login?faces-redirect=true";
	}
	
	public String novoUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = new UsuarioDAO().getByEmail(this.email);
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha(senha);
			new UsuarioDAO().adiciona(usuario);
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			return "produtos?faces-redirect=true";
		}
		else {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("E-mail já cadastrado"));
			return "login?faces-redirect=true";			
		}
	}

	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean getIsLoggedIn() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getExternalContext().getSessionMap().get("usuarioLogado") != null;
	}
}
