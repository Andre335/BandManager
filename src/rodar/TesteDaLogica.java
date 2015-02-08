package rodar;

import Fonte.GerenteDeUsuarios;
import Fonte.Usuario;

public class TesteDaLogica {

	public static void main(String[] args) {
		GerenteDeUsuarios gerenciador = new GerenteDeUsuarios();
		
		try {
			gerenciador.adicionaUsuario("Andre", "andre335@gmail.com", "123456789");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		Usuario user = gerenciador.pesquisaUsuario("andre335@gmail.com");
		System.out.println(user);
	}

}
