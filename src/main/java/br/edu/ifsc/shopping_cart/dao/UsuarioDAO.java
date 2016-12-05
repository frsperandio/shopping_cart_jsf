package br.edu.ifsc.shopping_cart.dao;

import org.apache.commons.lang3.StringUtils;

import br.edu.ifsc.shopping_cart.modelo.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
	public static synchronized UsuarioDAO getInstance() {
		if (instancia == null) {
			instancia = new UsuarioDAO();
		}
		return instancia;
	}

	private static UsuarioDAO instancia;

	private UsuarioDAO() {
		super(Usuario.class);
		geraDados();
	}

	public boolean existe(Usuario usuario) {
		Long i = 0l;
		boolean retorno = false;
		while (!retorno && i < LISTA.size()) {
			if (StringUtils.equalsIgnoreCase(LISTA.get(i).getEmail(), usuario.getEmail()) && StringUtils.equalsIgnoreCase(LISTA.get(i).getSenha(), usuario.getSenha())) {
				return true;
			}
			i++;
		}
		return retorno;
	}

	@Override
	void geraDados() {
		geraIdEAdiciona(new Usuario("professor@sematecsolucoes.com.br", "professor"));
		geraIdEAdiciona(new Usuario("diretor@sematecsolucoes.com.br", "diretor"));
		geraIdEAdiciona(new Usuario("admin@admin.com", "admin"));
		geraIdEAdiciona(new Usuario("teste@teste.com", "teste"));
	}
}
