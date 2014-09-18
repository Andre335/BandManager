package Testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Fonte.Banda;
import Fonte.Usuario;

public class TestaUsuario {

	private Usuario usuario;
	private Banda banda1;
	private Banda banda2;

	@Before
	public void iniciaUsuario() throws Exception {
		banda1 = new Banda("Muse");
		banda2 = new Banda("Paralamas");
		usuario = new Usuario("Andre", "andre335@gmail.com", "123456");
	}
	
	@Test
	public void testaNomeInvalido() {
		try {
			new Usuario("", "andre335@gmail.com", "123456");
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Digite seu nome!", e.getMessage());
		}
		
		try {
			new Usuario(null, "andre335@gmail.com", "123456");
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Digite seu nome!", e.getMessage());
		}
	}
	
	@Test
	public void testaEmailInvalido() {
		try {
			new Usuario("Andre", "", "123456");
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Digite seu email!", e.getMessage());
		}
		
		try {
			new Usuario("Andre", null, "123456");
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Digite seu email!", e.getMessage());
		}
	}
	
	@Test
	public void testaSenhaInvalida() {
		try {
			new Usuario("Andre", "andre335@gmail.com", "");
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Digite uma senha!", e.getMessage());
		}
		
		try {
			new Usuario("Andre", "andre335@gmail.com", null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Digite uma senha!", e.getMessage());
		}
		
		try {
			new Usuario("Andre", "andre335@gmail.com", "123456789123456");
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Senha deve ter entre 4 e 10 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void testaGetNome() {
		assertEquals("Andre" ,usuario.getNome());
	}
	
	@Test
	public void testaGetEmail() {
		assertEquals("andre335@gmail.com", usuario.getEmail());
	}
	
	@Test
	public void testaSetNomeValido() throws Exception {
		usuario.setNome("Joao");
		assertEquals("Joao", usuario.getNome());
	}
	
	@Test
	public void testaSetNomeInvalido() {
		try {
			usuario.setNome("");
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Digite seu nome!", e.getMessage());
		}
		
		try {
			usuario.setNome(null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Digite seu nome!", e.getMessage());
		}
	}
	
	@Test
	public void testaSetEmailValido() throws Exception {
		usuario.setEmail("andrerox6@gmail.com");
		assertEquals("andrerox6@gmail.com", usuario.getEmail());
	}
	
	@Test
	public void testaSetEmailInvalido() {
		try {
			usuario.setEmail("");
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Digite seu email!", e.getMessage());
		}
		
		try {
			usuario.setEmail(null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Digite seu email!", e.getMessage());
		}
	}
	
	@Test
	public void testaAddBandaValida() {
		usuario.addBanda(banda1);
		assertEquals(1, usuario.getBandas().size());
		
		usuario.addBanda(banda2);
		assertEquals(2, usuario.getBandas().size());
	}
	
	@Test
	public void testaRemoveBandaExistente() throws Exception {
		usuario.addBanda(banda1);
		assertEquals(1, usuario.getBandas().size());
		
		usuario.removeBanda(banda1);
		assertEquals(0, usuario.getBandas().size());
	}
	
	@Test
	public void testaRomoveBandaNaoExistente() {
		try {
			usuario.removeBanda(banda1);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Banda nao encontrada!", e.getMessage());
		}
	}
	
	@Test
	public void testaGetBandas() {
		usuario.addBanda(banda1);
		usuario.addBanda(banda2);
		
		assertEquals(2, usuario.getBandas().size());
	}
	
	@Test
	public void testaPesquisaBandaExistente() throws Exception {
		usuario.addBanda(banda2);
		assertEquals(banda2, usuario.pesquisaBanda(banda2));
	}
	
	@Test
	public void testaPesquisaBandaInexistente() {
		usuario.addBanda(banda2);
		try {
			usuario.pesquisaBanda(banda1);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Banda nao encontrada!", e.getMessage());
		}
	}
	
	@Test
	public void testaGetBandaFavoritaEscolhida() throws Exception {
		usuario.setBandaFavorita(banda1);
		assertEquals(banda1, usuario.getBandaFavorita());
	}
	
	@Test
	public void testaGetBandaFavoritaNaoEscolhida() {
		try {
			usuario.getBandaFavorita();
			fail("Esperava Excecao!");
		} catch (Exception e) {
			assertEquals("Voce ainda nao escolheu uma banda preferida!", e.getMessage());
		}
	}
	
	@Test
	public void testaSetBandaFavoritaValida() throws Exception {
		usuario.setBandaFavorita(banda1);
		assertEquals(banda1, usuario.getBandaFavorita());
	}
	
	@Test
	public void testaBandaFavoritaInvalida() {
		try {	
			usuario.setBandaFavorita(null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Escolha uma banda!", e.getMessage());
		}
	}
	
	@Test
	public void testaEquals() throws Exception {
		Usuario usuario1 = new Usuario("Andre", "andre335@gmail.com", "123456");
		assertTrue(usuario.equals(usuario1));
		
		usuario1 = new Usuario("Joao", "andre335@gmail.com", "123456");
		assertFalse(usuario.equals(usuario1));
		
		usuario1 = new Usuario("Andre", "joao@gmail.com", "123456");
		assertFalse(usuario.equals(usuario1));
		
		usuario1 = new Usuario("Andre", "andre335@gmail.com", "123456");
		usuario1.addBanda(banda1);
		assertFalse(usuario.equals(usuario1));
		
		usuario.addBanda(banda1);
		usuario1.setBandaFavorita(banda1);
		assertFalse(usuario.equals(usuario1));
	}
	
	@Test
	public void testaToString() throws Exception {
		assertEquals("Nome: Andre\nEmail: andre335@gmail.com"
				+ "\nBandas: Nenhuma\nBanda Favorita: Nenhuma", usuario.toString());
		
		usuario.addBanda(banda1);
		usuario.addBanda(banda2);
		usuario.setBandaFavorita(banda1);
		assertEquals("Nome: Andre\nEmail: andre335@gmail.com"
				+ "\nBandas: Muse\nParalamas\n" + "\nBanda Favorita: Muse", 
				usuario.toString());
	}
}
