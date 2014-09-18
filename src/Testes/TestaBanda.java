package Testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Fonte.Banda;
import Fonte.Repertorio;

public class TestaBanda {

	private Banda banda;
	private Repertorio repertorio1;

	@Before
	public void iniciaBanda() throws Exception {
		repertorio1 = new Repertorio("Show Alto Falante");
		banda = new Banda("KS");
	}
	
	@Test
	public void testaNomeInvalido() {
		try {
			new Banda("");
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Nome de banda invalido!", e.getMessage());
		}
		
		try {
			new Banda(null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Nome de banda invalido!", e.getMessage());
		}
	}
	
	@Test
	public void testaGetNome() {
		assertEquals("KS", banda.getNome());
	}
	
	@Test
	public void testaSetNomeValido() throws Exception {
		banda.setNome("Macacos do Artico");
		assertEquals("Macacos do Artico", banda.getNome());
	}
	
	@Test
	public void testaSetNomeInvalido() {
		try {
			banda.setNome("");
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Nome de banda invalido!", e.getMessage());
		}
		
		try {
			banda.setNome(null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Nome de banda invalido!", e.getMessage());
		}
	}
	
	@Test
	public void testaAddRepertorio() {
		assertEquals(0, banda.getRepertorios().size());
		banda.addRepertorio(repertorio1);
		assertEquals(1, banda.getRepertorios().size());
	}
	
	@Test
	public void testaRemoveRepertorioValido() throws Exception {
		banda.addRepertorio(repertorio1);
		assertEquals(1, banda.getRepertorios().size());
		
		banda.removeRepertorio(repertorio1);
		assertEquals(0, banda.getRepertorios().size());
	}
	
	@Test
	public void testaRemoveRepertorioInvalido() {
		try {
			banda.removeRepertorio(repertorio1);
			fail("Esperava Excecao!");
		} catch (Exception e) {
			assertEquals("Repertorio nao encontrado!", e.getMessage());
		}
	}
	
	@Test
	public void testaGetRepertorios() {
		assertEquals(0, banda.getRepertorios().size());
		banda.addRepertorio(repertorio1);
		assertEquals(1, banda.getRepertorios().size());
	}
	
	@Test
	public void testaPesquisaRepertorioExistente() throws Exception {
		banda.addRepertorio(repertorio1);
		assertEquals(repertorio1, banda.pesquisaRepertorio(repertorio1));
	}
	
	@Test
	public void testaPesquisaRepertorioInexistente() {
		try {
			banda.pesquisaRepertorio(repertorio1);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Repertorio nao encontrado!", e.getMessage());
		}
	}
	
	@Test
	public void testaSetRepertorioFavoritoValido() {
		try {
			banda.setRepertorioFavorito(repertorio1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testaSetRepertorioFavoritoInvalido() {
		try {
			banda.setRepertorioFavorito(null);
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Escolha um repertorio!", e.getMessage());
		}
	}
	
	@Test
	public void testaGetRepertorioFavoritoExistente() throws Exception {
		banda.setRepertorioFavorito(repertorio1);
		assertEquals(repertorio1, banda.getRepertorioFavorito());
	}
	
	@Test
	public void testaGetRepertorioFavoritoInexistente() {
		try {
			banda.getRepertorioFavorito();
			fail("Esperava excecao!");
		} catch (Exception e) {
			assertEquals("Voce ainda nao escolheu um reperotorio favorito para essa banda!", e.getMessage());
		}
	}
	
	@Test
	public void testaEquals() throws Exception {
		Banda banda1 = new Banda("Macacos do artico");
		Banda banda2 = new Banda("KS");
		
		assertTrue(banda.equals(banda2));
		
		banda2.addRepertorio(repertorio1);
		assertFalse(banda.equals(banda2));
		assertFalse(banda.equals(banda1));
		
		banda.addRepertorio(repertorio1);
		assertTrue(banda.equals(banda2));
		
		banda.setRepertorioFavorito(repertorio1);
		assertFalse(banda.equals(banda2));
	}
	
	@Test
	public void testaToString() throws Exception {
		assertEquals("Nome: KS\nRepertorios: Nenhum\nRepertorio Favorito: Nenhum"
				, banda.toString());
		
		banda.addRepertorio(repertorio1);
		
		assertEquals("Nome: KS\nRepertorios: Show Alto Falante\n\nRepertorio Favorito: Nenhum"
				, banda.toString());
		
		banda.setRepertorioFavorito(repertorio1);
		
		assertEquals("Nome: KS\nRepertorios: Show Alto Falante\n\nRepertorio Favorito: Show Alto Falante"
				, banda.toString());
	}
}
