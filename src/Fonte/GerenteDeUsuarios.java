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
 * Classe para gerenciamento de usu�rios.
 */
public class GerenteDeUsuarios {

	private List<Usuario> usuariosDoSistema;
	private ArquivadorUsuarios arquivador;

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
			"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * Construtor da classe GerenteDeUsuarios, que n�o tem par�metros.
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
	 * M�todo para adicionar um usu�rio ao sistema.
	 * 
	 * @param nome
	 *            Nome do usu�rio.
	 * @param email
	 *            E-mail do usu�rio.
	 * @param senha
	 *            Senha desejada do usu�rio.
	 * @param confirmacaoDeSenha
	 *            Confirma��o da senha inserida pelo usu�rio.
	 * @param dicaDeSenha
	 *            Dica de senha do usu�rio.
	 * @param nomeDaConta
	 *            Nome da conta desejada.
	 * @throws BandManagerException
	 *             Lan�a exce��o caso algum par�metro seja inv�lido ou se o
	 *             usu�rio j� estiver cadastrado no sistema.
	 * @throws GeneralSecurityException
	 *             Lan�a exce��o se houver problemas com a criptografia da
	 *             senha.
	 * @throws UnsupportedEncodingException
	 *             Lan�a exce��o se houver problemas com a criptografia da
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
	 * M�todo para pesquisar um usu�rio no sistema.
	 * 
	 * @param email
	 *            E-mail do usu�rio a ser procurado.
	 * @return Retorna o usu�rio caso ele exista; caso contr�rio, retorna null.
	 */
	public Usuario pesquisaUsuario(String email) {
		for (Usuario usuario : usuariosDoSistema) {
			if (usuario.getEmail().equals(email))
				return usuario;
		}
		return null;
	}

	/**
	 * M�todo para fazer login do usu�rio.
	 * 
	 * @param login
	 *            E-mail do usu�rio.
	 * @param senha
	 *            Senha do usu�rio.
	 * @return Retorna o usu�rio que est� logando.
	 * @throws BandManagerException
	 *             Lan�a exce��o caso o usu�rio n�o esteja cadastrado, ou a
	 *             senha estiver errada.
	 * @throws IOException
	 *             Lan�a exce��o se houver problemas com a criptografia da
	 *             senha.
	 * @throws GeneralSecurityException
	 *             Lan�a exce��o se houver problemas com a criptografia da
	 *             senha.
	 */
	public Usuario login(String login, String senha)
			throws BandManagerException, GeneralSecurityException, IOException {
		Usuario usuario = pesquisaUsuario(login);

		if (usuario == null)
			throw new BandManagerException(
					"Usu�rio n�o existe. Cadastre-se primeiro.");

		if (!usuario.checaLogin(senha))
			throw new BandManagerException("Senha incorreta!");

		return usuario;
	}

	/**
	 * M�todo que serve para atualizar a movimenta��o de um usu�rio em um
	 * arquivo.
	 * 
	 * @param usuario
	 *            O usu�rio que estava movimentando a conta.
	 * @throws BandManagerException
	 *             Lan�a exce��o se houver problema com o arquivador.
	 */
	public void atualizaSistema(Usuario usuario) throws BandManagerException {
		usuariosDoSistema.remove(usuario);
		usuariosDoSistema.add(usuario);
		arquivador.escreveUsuarios(usuariosDoSistema);
	}

	/**
	 * M�todo para checar se o usu�rio � um usu�rio v�lido.
	 * 
	 * @param nome
	 *            Nome do usu�rio.
	 * @param email
	 *            E-mail do usu�rio.
	 * @param senha
	 *            Senha do usu�rio.
	 * @throws BandManagerException
	 *             Lan�a exce��o se o usu�rio n�o for valido, ou seja, pelo
	 *             menos um dos par�metros estiver incorreto ou usu�rio j�
	 *             existir.
	 */
	private void usuarioValido(String nome, String email, String senha)
			throws BandManagerException {

		if (!nomeValido(nome))
			throw new BandManagerException("Nome inv�lido.");
		if (!emailValido(email))
			throw new BandManagerException("E-mail inv�lido ou j� existe.");
		if (!senhaValida(senha))
			throw new BandManagerException(
					"Senha inv�lida ou n�o confere com confirma��o.");
	}

	/**
	 * M�todo para verificar se um nome � v�lido.
	 * 
	 * @param nome
	 *            Um nome.
	 * @return Retorna true se for v�lido, e false caso contr�rio.
	 */
	private boolean nomeValido(String nome) {
		if (nome == null || nome.trim().isEmpty())
			return false;
		return true;
	}

	/**
	 * M�todo para verificar se o e-mail do usu�rio � v�lido.
	 * 
	 * @param email
	 *            E-mail do usu�rio.
	 * @return Retorna true se for v�lido, e false caso contr�rio.
	 */
	private boolean emailValido(String email) {
		if (email == null || email.trim().isEmpty() || emailJaExiste(email))
			return false;

		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}

	/**
	 * M�todo para verificar se o e-mail do usu�rio j� existe.
	 * 
	 * @param email
	 *            E-mail do usu�rio.
	 * @return Retorna true se o e-mail j� existe, e false caso contr�rio.
	 */
	private boolean emailJaExiste(String email) {
		if (pesquisaUsuario(email) != null)
			return true;
		return false;
	}

	/**
	 * M�todo para verificar se a senha do usu�rio � igual a confirma��o de
	 * senha.
	 * 
	 * @param senha
	 *            Senha do usu�rio.
	 * @return Retorna true se a senha for igual a confirma��o de senha, e false
	 *         caso contr�rio.
	 */
	private boolean senhaValida(String senha) {
		if (senha == null || senha.trim().isEmpty())
			return false;
		return true;
	}
}
