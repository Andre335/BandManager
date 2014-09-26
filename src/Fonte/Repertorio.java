package Fonte;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repertorio implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private List<Musica> musicas;
	private List<Musica> futurasMusicas;

	public Repertorio(String nome) throws Exception {
		validaNome(nome);
		
		this.nome = nome;
		musicas = new ArrayList<>();
		futurasMusicas = new ArrayList<>();
	}

	private void validaNome(String nome) throws Exception {
		if (nome == null || nome.equals(""))
			throw new Exception("Nome de repertorio invalido!");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		validaNome(nome);
		this.nome = nome;
	}

	public void addMusica(Musica musica) throws Exception {
		validaMusica(musica);
		musicas.add(musica);
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public int getNumDeMusicas() {
		return musicas.size();
	}

	public void removeMusica(Musica musica) throws Exception {
		if (pesquisaMusica(musica) != null) {
			musicas.remove(musica);
		}
	}

	public Musica pesquisaMusica(Musica musica) throws Exception {
		for (Musica musicaNoRepertorio : musicas) {
			if (musica.equals(musicaNoRepertorio)) {
				return musica;
			}
		} throw new Exception("Essa musica nao esta no repertorio!");
	}

	public void addMusicaFutura(Musica musica) throws Exception {
		validaMusica(musica);
		futurasMusicas.add(musica);
	}

	private void validaMusica(Musica musica) throws Exception {
		if (musica == null)
			throw new Exception("Escolha uma musica para adicionar!");
	}

	public int getNumDeMusicasFuturas() {
		return futurasMusicas.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((futurasMusicas == null) ? 0 : futurasMusicas.hashCode());
		result = prime * result + ((musicas == null) ? 0 : musicas.hashCode());
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
		Repertorio other = (Repertorio) obj;
		if (futurasMusicas == null) {
			if (other.futurasMusicas != null)
				return false;
		} else if (!futurasMusicas.equals(other.futurasMusicas))
			return false;
		if (musicas == null) {
			if (other.musicas != null)
				return false;
		} else if (!musicas.equals(other.musicas))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void removeMusicaFutura(Musica musicaFutura) throws Exception {
		if (pesquisaMusicaFutura(musicaFutura) != null) {
			futurasMusicas.remove(musicaFutura);
		}
	}

	private Musica pesquisaMusicaFutura(Musica musicaFutura) throws Exception {
		for (Musica musicaAEntrar : futurasMusicas) {
			if (musicaFutura.equals(musicaAEntrar)) {
				return musicaFutura;
			}
		} throw new Exception("Essa musica nao esta para entrar!");
	}

	public List<Musica> getMusicasFuturas() {
		return futurasMusicas;
	}
	
	@Override
	public String toString() {
		String musicasS = "";
		String futurasMusicasS = "";
		
		if (musicas.size() == 0)
			musicasS = "Nenhuma";
		else {	
			for (Musica musica : musicas) {
				musicasS += musica.getAutor() + " - " + musica.getNome();
			}
		}
		
		if (futurasMusicas.size() == 0)
			futurasMusicasS = "Nenhuma";
		else {	
			for (Musica musicaFutura : futurasMusicas) {
				futurasMusicasS += musicaFutura.getAutor() + " - " + musicaFutura.getNome();
			}
		}
		
		return "Nome: " + nome + "\nMusicas: " + musicasS + "\nMusicas a Entrar: " 
			+ futurasMusicasS;
	}
}
