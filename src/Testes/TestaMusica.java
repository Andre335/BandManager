package Testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Fonte.Musica;

public class TestaMusica {

	private Musica musica1;

	@Before
	public void iniciaMusica() throws Exception {
		musica1 = new Musica("Repitillia", "Strokes");
	}
	
	@Test
	public void testaNomeInvalido() {
		try {
			new Musica("", "Strokes");
			fail("Esperava Excecao!");
		} catch (Exception e) {
			assertEquals("Nome invalido!", e.getMessage());
		}
		
		try {
			new Musica(null, "Strokes");
			fail("Esperava Excecao!");
		} catch (Exception e) {
			assertEquals("Nome invalido!", e.getMessage());
		}
	}
	
	@Test
	public void testaAutorInvalido() {
		try {
			new Musica("Someday", "");
			fail("Esperava Excecao!");
		} catch (Exception e) {
			assertEquals("Autor invalido!", e.getMessage());
		}
		
		try {
			new Musica("Someday", null);
			fail("Esperava Excecao!");
		} catch (Exception e) {
			assertEquals("Autor invalido!", e.getMessage());
		}
	}
	
	@Test
	public void testaGetNome() {
		assertEquals("Repitillia", musica1.getNome());
	}
	
	@Test
	public void testaGetAutor() {
		assertEquals("Strokes", musica1.getAutor());
	}
	
	@Test
	public void testaEquals() throws Exception {
		Musica musica2 = new Musica("Someday", "Strokes");
		assertFalse(musica1.equals(musica2));
		
		Musica musica3 = new Musica("Repitillia", "Strokes");
		assertTrue(musica1.equals(musica3));
		
		Musica musica4 = new Musica("Repitillia", "Macacos");
		assertFalse(musica1.equals(musica4));
		
		Musica musica5 = new Musica("Someday", "Macacos");
		assertFalse(musica1.equals(musica5));
	}
	
	@Test
	public void testaToString() {
		assertEquals("Strokes - Repitillia", musica1.toString());
	}
}
