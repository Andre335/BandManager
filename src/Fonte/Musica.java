package Fonte;

import java.io.Serializable;

public class Musica implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String autor;

	public Musica(String nome, String autor) throws Exception {
		validaNome(nome);
		validaAutor(autor);
		
		this.nome = nome;
		this.autor = autor;
	}

	private void validaAutor(String autor) throws Exception {
		if (autor == null || autor.equals(""))
			throw new Exception("Autor invalido!");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
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
		Musica other = (Musica) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	private void validaNome(String nome) throws Exception {
		if (nome == null || nome.equals(""))
			throw new Exception("Nome invalido!");
	}

	public String getNome() {
		return nome;
	}

	public String getAutor() {
		return autor;
	}
	
	@Override
	public String toString() {
		return getAutor() + " - " + getNome();
	}
}
