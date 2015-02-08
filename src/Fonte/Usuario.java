package Fonte;

import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String email;
	private String senha;
	private List<Banda> bandas;
	private Banda bandaFavorita;

	public Usuario(String nome, String email, String senha) throws Exception {
		validaNome(nome);
		validaEmail(email);
		validaSenha(senha);
		
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		bandas = new ArrayList<>();
		bandaFavorita = null;
	}

	private void validaSenha(String senha) throws Exception {
		if (senha == null || senha.equals(""))
			throw new Exception("Digite uma senha!");
		if (senha.length() < 4 || senha.length() > 10)
			throw new Exception("Senha deve ter entre 4 e 10 caracteres!");
	}

	private void validaEmail(String email) throws Exception {
		if (email == null || email.equals(""))
			throw new Exception("Digite seu email!");
	}

	private void validaNome(String nome) throws Exception {
		if (nome == null || nome.equals(""))
			throw new Exception("Digite seu nome!");
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public List<Banda> getBandas() {
		return bandas;
	}

	public void addBanda(Banda banda) {
		bandas.add(banda);
	}

	public void setNome(String nome) throws Exception {
		validaNome(nome);
		this.nome = nome;
	}

	public void setEmail(String email) throws Exception {
		validaEmail(email);
		this.email = email;
	}

	public Banda pesquisaBanda(Banda banda) throws Exception {
		for (Banda bandaDoUsuario : bandas) {
			if (banda.equals(bandaDoUsuario))
				return banda;
		} throw new Exception("Banda nao encontrada!");
	}

	public Banda getBandaFavorita() throws Exception {
		if (bandaFavorita == null)
			throw new Exception("Voce ainda nao escolheu uma banda preferida!");
		
		return bandaFavorita;
	}

	public void setBandaFavorita(Banda banda) throws Exception {
		if (banda == null)
			throw new Exception("Escolha uma banda!");
		
		this.bandaFavorita = banda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bandaFavorita == null) ? 0 : bandaFavorita.hashCode());
		result = prime * result + ((bandas == null) ? 0 : bandas.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (bandaFavorita == null) {
			if (other.bandaFavorita != null)
				return false;
		} else if (!bandaFavorita.equals(other.bandaFavorita))
			return false;
		if (bandas == null) {
			if (other.bandas != null)
				return false;
		} else if (!bandas.equals(other.bandas))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String bandasS = "";
		String bandaFavS = "";
		
		if (bandas.size() == 0) {
			bandasS += "Nenhuma";
		} else {
			for (Banda banda : bandas) {
				bandasS += banda.getNome() + "\n";
			}
		}
		
		if (bandaFavorita == null)
			bandaFavS += "Nenhuma";
		else
			bandaFavS += bandaFavorita.getNome();
		
		return "Nome: " + this.getNome() + "\nEmail: " + this.getEmail() 
				+ "\nBandas: " + bandasS + "\nBanda Favorita: " + bandaFavS;
	}

	public void removeBanda(Banda banda) throws Exception {
		if (pesquisaBanda(banda) != null)
			bandas.remove(banda);
	}
	
	public boolean checaLogin(String senhaParaChecar)
			throws GeneralSecurityException, IOException {
//		if (senhaParaChecar.equals(Criptografia.decrypt(senha)))
		if (senhaParaChecar.equals(senha))
			return true;
		return false;
	}
	
	public String getSenha() {
		return senha;
	}
}
