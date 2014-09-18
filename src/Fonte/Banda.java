package Fonte;

public class Banda {

	private String nome;

	public Banda(String nome) throws Exception {
		if (nome == null)
			throw new Exception("Nome invalido!");
		
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
