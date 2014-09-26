package Fonte;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Banda implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private List<Repertorio> repertorios;
	private Repertorio repertorioFavorito;

	public Banda(String nome) throws Exception {
		validaNome(nome);
		
		this.nome = nome;
		repertorios = new ArrayList<>();
		repertorioFavorito = null;
	}

	private void validaNome(String nome) throws Exception {
		if (nome == null || nome.equals(""))
			throw new Exception("Nome de banda invalido!");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		validaNome(nome);
		this.nome = nome;
	}

	public List<Repertorio> getRepertorios() {
		return repertorios;
	}

	public void addRepertorio(Repertorio repertorio) {
		repertorios.add(repertorio);
	}

	public boolean removeRepertorio(Repertorio repertorio) throws Exception {
		if (pesquisaRepertorio(repertorio) != null)
			repertorios.remove(repertorio);
		return true;
	}

	public Repertorio pesquisaRepertorio(Repertorio repertorio) throws Exception {
		for (Repertorio repertorioDaBanda : repertorios) {
			if (repertorio.equals(repertorioDaBanda))
				return repertorio;
		} throw new Exception("Repertorio nao encontrado!");
	}

	public Repertorio getRepertorioFavorito() throws Exception {
		if (repertorioFavorito == null)
			throw new Exception("Voce ainda nao escolheu um reperotorio favorito para essa banda!");
		
		return repertorioFavorito;
	}

	public void setRepertorioFavorito(Repertorio repertorio) throws Exception {
		if (repertorio == null)
			throw new Exception("Escolha um repertorio!");
		
		repertorioFavorito = repertorio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime
				* result
				+ ((repertorioFavorito == null) ? 0 : repertorioFavorito
						.hashCode());
		result = prime * result
				+ ((repertorios == null) ? 0 : repertorios.hashCode());
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
		Banda other = (Banda) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (repertorioFavorito == null) {
			if (other.repertorioFavorito != null)
				return false;
		} else if (!repertorioFavorito.equals(other.repertorioFavorito))
			return false;
		if (repertorios == null) {
			if (other.repertorios != null)
				return false;
		} else if (!repertorios.equals(other.repertorios))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String repertoriosS = "";
		String repertFavS = "";
		
		if (repertorios.size() == 0)
			repertoriosS += "Nenhum";
		else {
			for (Repertorio repertorio : repertorios) {
				repertoriosS += repertorio.getNome() + "\n";
			}
		} 
		
		if (repertorioFavorito == null)
			repertFavS += "Nenhum";
		else 
			repertFavS += repertorioFavorito.getNome();
		
		return "Nome: " + this.getNome() + "\nRepertorios: " + repertoriosS 
				+ "\nRepertorio Favorito: " + repertFavS;
	}
}
