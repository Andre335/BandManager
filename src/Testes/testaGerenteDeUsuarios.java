package Testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Fonte.GerenteDeUsuarios;
import Fonte.Usuario;

public class testaGerenteDeUsuarios {
	
	private GerenteDeUsuarios gerente;
	private Usuario usuario;

	@Before
	public void iniciaGerente() throws Exception {
		usuario = new Usuario("Andre", "andre335@gmail.com", "123456");
		gerente = new GerenteDeUsuarios();
	}
	
	@After
	public void fechaGerente() {
		gerente.limpaArquivo();
	}
	
	@Test
	public void testaAddUsuarioValido() throws Exception {
		gerente.addUsuario(usuario);
		assertEquals(1, gerente.getUsuarios().size());
	}
	
	@Test
	public void testaAddUsuarioInvalido() throws Exception {
		try {
			gerente.addUsuario(null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Usuario nao pode ser adicionado!", e.getMessage());
		}
	}
	
	@Test
	public void testaDeletaUsuarioExistente() throws Exception {
		gerente.addUsuario(usuario);
		assertEquals(1, gerente.getUsuarios().size());
		gerente.deletaUsuario(usuario);
		assertEquals(0, gerente.getUsuarios().size());
	}
	
	@Test
	public void testaDeletaUsuarioInexistente() {
		try {
			gerente.deletaUsuario(usuario);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Usuario não existe!", e.getMessage());
		}
	}
	
	@Test
	public void testaPesquisaUsuarioExistente() throws Exception {
		gerente.addUsuario(usuario);
		assertEquals(usuario, gerente.pesquisaUsuario(usuario));
	}
	
	@Test
	public void testaPesquisaUsuarioInexistente() {
		try {
			gerente.pesquisaUsuario(usuario);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Usuario não existe!", e.getMessage());
		}
	}
	
	@Test
	public void testaLoginValido() throws Exception {
		gerente.addUsuario(usuario);
		gerente.login(usuario);
		assertEquals(usuario, gerente.getUserLogado());
	}
	
	@Test
	public void testaLoginInvalido() {
		try {
			gerente.login(null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Usuario não existe!", e.getMessage());
		}
	}
}
