package br.edu.ifsc.shopping_cart.modelo;

public class Usuario extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private String senha;

	public Usuario() {
		this("", "");
	}

	public Usuario(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getSenha() {
		return senha;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
