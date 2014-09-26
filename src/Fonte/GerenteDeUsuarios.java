package Fonte;

import java.util.ArrayList;
import java.util.List;

import projetop2.utils.ArquivoDeDados;
import projetop2.utils.ProjetoHelperExceptions;

public class GerenteDeUsuarios {

	private List<Usuario> usuarios;
	private Usuario userLogado;
	private ArquivoDeDados arquivo;
	private GerenteDeUsuarios uniqueInstance = null;
	
	public GerenteDeUsuarios() {
		userLogado = null;
		usuarios = new ArrayList<>();
		arquivo = new ArquivoDeDados<List<Usuario>>("usuarios.txt");
	}
	
	public void addUsuario(Usuario usuario) throws Exception {
		if (usuario == null)
			throw new Exception("Usuario nao pode ser adicionado!");
		
		usuarios.add(usuario);
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void login(Usuario usuario) throws Exception {
		userLogado = pesquisaUsuario(usuario); 
	}

	public Usuario getUserLogado() {
		return userLogado;
	}

	public Usuario pesquisaUsuario(Usuario usuario) throws Exception {
		for (Usuario usuarioNoSistema : usuarios) {
			if (usuario.equals(usuarioNoSistema)) 
				return usuario;
		} throw new Exception("Usuario não existe!");
	}

	public void deletaUsuario(Usuario usuario) throws Exception {
		pesquisaUsuario(usuario);
		usuarios.remove(usuario);
	}

	public void limpaArquivo() {
		arquivo.limpaArquivo();
	}
	
	public GerenteDeUsuarios getInstance() {
		if(uniqueInstance == null){
			uniqueInstance = new GerenteDeUsuarios();
		} return uniqueInstance;
	}
	
	public void salvarArquivo() throws ProjetoHelperExceptions {
		arquivo.salvaObjeto(getUsuarios());
	}
	
	public void carregaArquivo() throws ProjetoHelperExceptions {
		usuarios = (List<Usuario>) arquivo.carregaObjetos();
	}
	
	public void atualizaArquivo() throws ProjetoHelperExceptions {
		limpaArquivo();
		salvarArquivo();
	}
}
