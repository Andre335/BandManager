package Testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Fonte.Musica;
import Fonte.Repertorio;

public class TestaRepertorio {

	private Repertorio repertorio1;
	private Musica musica1;
	private Musica musica2;

	@Before
	public void test() throws Exception {
		musica1 = new Musica("Someday", "Strokes");
		musica2 = new Musica("Last Nite", "Strokes");
		repertorio1 = new Repertorio("Show1"); 
	}
	
	@Test
	public void testaNomeInvalido() {
		try {
			new Repertorio("");
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Nome de repertorio invalido!", e.getMessage());
		}
		
		try {
			new Repertorio(null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Nome de repertorio invalido!", e.getMessage());
		}
	}
	
	@Test
	public void testaGetNome() {
		assertEquals("Show1", repertorio1.getNome());
	}
	
	@Test
	public void testaSetNomeValido() throws Exception {
		repertorio1.setNome("Show2");
		assertEquals("Show2", repertorio1.getNome());
	}
	
	@Test
	public void testaSetNomeInvalido() {
		try {
			repertorio1.setNome("");
			fail("Esperava Excecao!");
		} catch (Exception e) {
			assertEquals("Nome de repertorio invalido!", e.getMessage());
		}
		
		try {
			repertorio1.setNome(null);
			fail("Esperava Excecao!");
		} catch (Exception e) {
			assertEquals("Nome de repertorio invalido!", e.getMessage());
		}
	}
	
	@Test
	public void testaAddMusicaInvalida() {
		try {
			repertorio1.addMusica(null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Escolha uma musica para adicionar!", e.getMessage());
		}
	}
	
	@Test
	public void testaAddMusicaValida() throws Exception {
		assertEquals(0, repertorio1.getNumDeMusicas());
		
		repertorio1.addMusica(musica1);
		assertEquals(1, repertorio1.getNumDeMusicas());
		
		repertorio1.addMusica(musica2);
		assertEquals(2, repertorio1.getNumDeMusicas());
	}
	
	@Test
	public void testaRemoveMusicaExistente() throws Exception {
		repertorio1.addMusica(musica1);
		repertorio1.addMusica(musica2);
		assertEquals(2, repertorio1.getNumDeMusicas());
		
		repertorio1.removeMusica(musica1);
		assertEquals(1, repertorio1.getNumDeMusicas());
		
		repertorio1.removeMusica(musica2);
		assertEquals(0, repertorio1.getNumDeMusicas());
	}
	
	@Test
	public void testaRemoveMusicaInexistente() {
		try {
			repertorio1.removeMusica(musica1);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Essa musica nao esta no repertorio!", e.getMessage());
		}
		
		try {
			repertorio1.removeMusica(musica2);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Essa musica nao esta no repertorio!", e.getMessage());
		}
	}
	
	@Test
	public void testaGetMusicas() throws Exception {
		repertorio1.addMusica(musica1);
		assertEquals(1, repertorio1.getMusicas().size());
		
		repertorio1.addMusica(musica2);
		assertEquals(2, repertorio1.getMusicas().size());
	}
	
	@Test
	public void testaPesquisaMusicaExistente() throws Exception {
		repertorio1.addMusica(musica1);
		assertEquals(musica1, repertorio1.pesquisaMusica(musica1));
		
		repertorio1.addMusica(musica2);
		assertEquals(musica2, repertorio1.pesquisaMusica(musica2));
	}
	
	@Test
	public void testaPesquisaMusicaInexistente() {
		try {
			repertorio1.pesquisaMusica(musica1);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Essa musica nao esta no repertorio!", e.getMessage());
		}
		
		try {
			repertorio1.pesquisaMusica(musica2);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Essa musica nao esta no repertorio!", e.getMessage());
		}
	}
	
	@Test
	public void testaAddMusicaFuturaValida() throws Exception {
		assertEquals(0, repertorio1.getNumDeMusicasFuturas());
		
		repertorio1.addMusicaFutura(musica1);
		assertEquals(1, repertorio1.getNumDeMusicasFuturas());
		
		repertorio1.addMusicaFutura(musica2);
		assertEquals(2, repertorio1.getNumDeMusicasFuturas());
	}
	
	@Test
	public void testaAddMusicaFuturaInvalida() {
		try {
			repertorio1.addMusicaFutura(null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Escolha uma musica para adicionar!", e.getMessage());
		}
	}
	
	@Test
	public void testaRemoveMusicaFuturaExistente() throws Exception {
		repertorio1.addMusicaFutura(musica1);
		repertorio1.addMusicaFutura(musica2);
		assertEquals(2, repertorio1.getNumDeMusicasFuturas());
		
		repertorio1.removeMusicaFutura(musica1);
		assertEquals(1, repertorio1.getNumDeMusicasFuturas());
		
		repertorio1.removeMusicaFutura(musica2);
		assertEquals(0, repertorio1.getNumDeMusicasFuturas());
	}
	
	@Test
	public void testaRemoveMusicaFuturaInexistente() {
		try {
			repertorio1.removeMusicaFutura(musica1);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Essa musica nao esta para entrar!", e.getMessage());
		}
		
		try {
			repertorio1.removeMusicaFutura(musica2);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Essa musica nao esta para entrar!", e.getMessage());
		}
	}
	
	@Test
	public void testaGetMusicasFuturas() throws Exception {
		repertorio1.addMusicaFutura(musica1);
		assertEquals(1, repertorio1.getMusicasFuturas().size());
		
		repertorio1.addMusicaFutura(musica2);
		assertEquals(2, repertorio1.getMusicasFuturas().size());
	}
	
	@Test
	public void testaEquals() throws Exception {
		Repertorio repertorio2 = new Repertorio("Show1");
		Repertorio repertorio3 = new Repertorio("Show2");
		
		assertTrue(repertorio1.equals(repertorio2));
		assertFalse(repertorio1.equals(repertorio3));
		
		repertorio2.addMusica(musica1);
		assertFalse(repertorio1.equals(repertorio2));
		
		repertorio1.addMusica(musica1);
		assertTrue(repertorio1.equals(repertorio2));
		
		repertorio3.addMusica(musica1);
		assertFalse(repertorio1.equals(repertorio3));
		
		repertorio2.addMusicaFutura(musica2);
		assertFalse(repertorio1.equals(repertorio2));
		
		repertorio1.addMusicaFutura(musica2);
		assertTrue(repertorio1.equals(repertorio2));
	}
	
	@Test
	public void testaToString() throws Exception {
		assertEquals("Nome: Show1\nMusicas: Nenhuma\nMusicas a Entrar: Nenhuma"
				, repertorio1.toString());
		
		repertorio1.addMusica(musica1);
		assertEquals("Nome: Show1\nMusicas: Strokes - Someday\nMusicas a Entrar: Nenhuma"
				, repertorio1.toString());
		
		repertorio1.addMusicaFutura(musica2);
		assertEquals("Nome: Show1\nMusicas: Strokes - Someday\nMusicas a Entrar: Strokes - Last Nite"
				, repertorio1.toString());
	}
}
