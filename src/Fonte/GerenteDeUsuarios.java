package Fonte;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import excecao.BandManagerException;
import auxiliar.ArquivadorUsuarios;

/**
 * Classe para gerenciamento de usuários.
 */
public class GerenteDeUsuarios {

	private List<Usuario> usuariosDoSistema;
	private ArquivadorUsuarios arquivador;

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
			"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * Construtor da classe GerenteDeUsuarios, que não tem parâmetros.
	 */
	public GerenteDeUsuarios() {
		try {
			arquivador = new ArquivadorUsuarios("recursos/data.bma");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (arquivador.leUsuarios() == null) {
			usuariosDoSistema = new ArrayList<>();
		} else {
			usuariosDoSistema = new ArrayList<>(arquivador.leUsuarios());
		}
	}

	/**
	 * Método para adicionar um usuário ao sistema.
	 * 
	 * @param nome
	 *            Nome do usuário.
	 * @param email
	 *            E-mail do usuário.
	 * @param senha
	 *            Senha desejada do usuário.
	 * @param confirmacaoDeSenha
	 *            Confirmação da senha inserida pelo usuário.
	 * @param dicaDeSenha
	 *            Dica de senha do usuário.
	 * @param nomeDaConta
	 *            Nome da conta desejada.
	 * @throws BandManagerException
	 *             Lança exceção caso algum parâmetro seja inválido ou se o
	 *             usuário já estiver cadastrado no sistema.
	 * @throws GeneralSecurityException
	 *             Lança exceção se houver problemas com a criptografia da
	 *             senha.
	 * @throws UnsupportedEncodingException
	 *             Lança exceção se houver problemas com a criptografia da
	 *             senha.
	 */
	public void adicionaUsuario(String nome, String email, String senha)
			throws BandManagerException, UnsupportedEncodingException,
			GeneralSecurityException, Exception {

		usuarioValido(nome, email, senha);

		Usuario novoUsuario = new Usuario(nome, email, senha);
		usuariosDoSistema.add(novoUsuario);

		arquivador.escreveUsuarios(usuariosDoSistema);
	}

	/**
	 * Método para pesquisar um usuário no sistema.
	 * 
	 * @param email
	 *            E-mail do usuário a ser procurado.
	 * @return Retorna o usuário caso ele exista; caso contrário, retorna null.
	 */
	public Usuario pesquisaUsuario(String email) {
		for (Usuario usuario : usuariosDoSistema) {
			if (usuario.getEmail().equals(email))
				return usuario;
		}
		return null;
	}

	/**
	 * Método para fazer login do usuário.
	 * 
	 * @param login
	 *            E-mail do usuário.
	 * @param senha
	 *            Senha do usuário.
	 * @return Retorna o usuário que está logando.
	 * @throws BandManagerException
	 *             Lança exceção caso o usuário não esteja cadastrado, ou a
	 *             senha estiver errada.
	 * @throws IOException
	 *             Lança exceção se houver problemas com a criptografia da
	 *             senha.
	 * @throws GeneralSecurityException
	 *             Lança exceção se houver problemas com a criptografia da
	 *             senha.
	 */
	public Usuario login(String login, String senha)
			throws BandManagerException, GeneralSecurityException, IOException {
		Usuario usuario = pesquisaUsuario(login);

		if (usuario == null)
			throw new BandManagerException(
					"Usuário não existe. Cadastre-se primeiro.");

		if (!usuario.checaLogin(senha))
			throw new BandManagerException("Senha incorreta!");

		return usuario;
	}

	/**
	 * Método que serve para atualizar a movimentação de um usuário em um
	 * arquivo.
	 * 
	 * @param usuario
	 *            O usuário que estava movimentando a conta.
	 * @throws BandManagerException
	 *             Lança exceção se houver problema com o arquivador.
	 */
	public void atualizaSistema(Usuario usuario) throws BandManagerException {
		usuariosDoSistema.remove(usuario);
		usuariosDoSistema.add(usuario);
		arquivador.escreveUsuarios(usuariosDoSistema);
	}

	/**
	 * Método para checar se o usuário é um usuário válido.
	 * 
	 * @param nome
	 *            Nome do usuário.
	 * @param email
	 *            E-mail do usuário.
	 * @param senha
	 *            Senha do usuário.
	 * @throws BandManagerException
	 *             Lança exceção se o usuário não for valido, ou seja, pelo
	 *             menos um dos parâmetros estiver incorreto ou usuário já
	 *             existir.
	 */
	private void usuarioValido(String nome, String email, String senha)
			throws BandManagerException {

		if (!nomeValido(nome))
			throw new BandManagerException("Nome inválido.");
		if (!emailValido(email))
			throw new BandManagerException("E-mail inválido ou já existe.");
		if (!senhaValida(senha))
			throw new BandManagerException(
					"Senha inválida ou não confere com confirmação.");
	}

	/**
	 * Método para verificar se um nome é válido.
	 * 
	 * @param nome
	 *            Um nome.
	 * @return Retorna true se for válido, e false caso contrário.
	 */
	private boolean nomeValido(String nome) {
		if (nome == null || nome.trim().isEmpty())
			return false;
		return true;
	}

	/**
	 * Método para verificar se o e-mail do usuário é válido.
	 * 
	 * @param email
	 *            E-mail do usuário.
	 * @return Retorna true se for válido, e false caso contrário.
	 */
	private boolean emailValido(String email) {
		if (email == null || email.trim().isEmpty() || emailJaExiste(email))
			return false;

		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}

	/**
	 * Método para verificar se o e-mail do usuário já existe.
	 * 
	 * @param email
	 *            E-mail do usuário.
	 * @return Retorna true se o e-mail já existe, e false caso contrário.
	 */
	private boolean emailJaExiste(String email) {
		if (pesquisaUsuario(email) != null)
			return true;
		return false;
	}

	/**
	 * Método para verificar se a senha do usuário é igual a confirmação de
	 * senha.
	 * 
	 * @param senha
	 *            Senha do usuário.
	 * @return Retorna true se a senha for igual a confirmação de senha, e false
	 *         caso contrário.
	 */
	private boolean senhaValida(String senha) {
		if (senha == null || senha.trim().isEmpty())
			return false;
		return true;
	}
}
