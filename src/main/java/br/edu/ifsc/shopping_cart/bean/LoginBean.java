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
	private Usuario usuario = new Usuario();

	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		context.getExternalContext().getSessionMap().remove("shoppingCart");
		return "login?faces-redirect=true";
	}

	public String efetuaLogin() {
		FacesContext context = FacesContext.getCurrentInstance();
		boolean existe = UsuarioDAO.getInstance().existe(this.usuario);
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
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
		boolean existe = UsuarioDAO.getInstance().existe(this.usuario);
		if (!existe) {
			UsuarioDAO.getInstance().adiciona(this.usuario);
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "produtos?faces-redirect=true";
		}
		else {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("E-mail já cadastrado"));
			return "login?faces-redirect=true";			
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public boolean getIsLoggedIn() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getExternalContext().getSessionMap().get("usuarioLogado") != null;
	}
}
