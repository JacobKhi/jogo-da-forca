package br.edu.iff.forca.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import br.edu.iff.forca.domain.letra.Letra;
import br.edu.iff.forca.factory.grafico.LetraTextoFactory;

public class PalavraTest {

    @BeforeEach
    public void setUp() {
        Palavra.setLetraFactory(LetraTextoFactory.getSoleInstance());
    }

    @Test
    public void testCriar() {
        Tema tema = Tema.criar(1, "Animais");
        Palavra p = Palavra.criar(1, "gato", tema);
        assertEquals(1, p.getId());
        assertEquals("gato", p.toString());
        assertEquals(4, p.getTamanho());
        assertEquals(tema, p.getTema());
    }

    @Test
    public void testPalavraVazia() {
        Tema tema = Tema.criar(1, "Animais");
        assertThrows(IllegalArgumentException.class, () -> Palavra.criar(1, "", tema));
    }

    @Test
    public void testPalavraNula() {
        Tema tema = Tema.criar(1, "Animais");
        assertThrows(IllegalArgumentException.class, () -> Palavra.criar(1, null, tema));
    }

    @Test
    public void testTemaNulo() {
        assertThrows(IllegalArgumentException.class, () -> Palavra.criar(1, "gato", null));
    }

    @Test
    public void testLetraFactoryNula() {
        Palavra.setLetraFactory(null);
        Tema tema = Tema.criar(1, "Animais");
        assertThrows(IllegalStateException.class, () -> Palavra.criar(1, "gato", tema));
        // restaura para os outros testes
        Palavra.setLetraFactory(LetraTextoFactory.getSoleInstance());
    }

    @Test
    public void testTentar() {
        Tema tema = Tema.criar(1, "Animais");
        Palavra p = Palavra.criar(1, "gato", tema);
        int[] posicoes = p.tentar('a');
        assertEquals(1, posicoes.length);
        assertEquals(1, posicoes[0]);
    }

    @Test
    public void testTentarLetraAusente() {
        Tema tema = Tema.criar(1, "Animais");
        Palavra p = Palavra.criar(1, "gato", tema);
        int[] posicoes = p.tentar('z');
        assertEquals(0, posicoes.length);
    }

    @Test
    public void testComparar() {
        Tema tema = Tema.criar(1, "Animais");
        Palavra p = Palavra.criar(1, "gato", tema);
        assertTrue(p.comparar("gato"));
        assertTrue(p.comparar("GATO"));
        assertFalse(p.comparar("cachorro"));
        assertFalse(p.comparar(null));
    }

    @Test
    public void testGetLetras() {
        Tema tema = Tema.criar(1, "Animais");
        Palavra p = Palavra.criar(1, "gato", tema);
        Letra[] letras = p.getLetras();
        assertEquals(4, letras.length);
        assertEquals('g', letras[0].getCodigo());
        assertEquals('a', letras[1].getCodigo());
        assertEquals('t', letras[2].getCodigo());
        assertEquals('o', letras[3].getCodigo());
    }
}
