package br.edu.iff.forca.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import br.edu.iff.forca.factory.grafico.LetraTextoFactory;

public class ItemTest {

    private Palavra palavra;

    @BeforeEach
    public void setUp() {
        Palavra.setLetraFactory(LetraTextoFactory.getSoleInstance());
        Tema tema = Tema.criar(1, "Animais");
        palavra = Palavra.criar(1, "gato", tema);
    }

    @Test
    public void testCriar() {
        Item item = Item.criar(1, palavra);
        assertEquals(palavra, item.getPalavra());
        assertFalse(item.arriscou());
        assertFalse(item.acertou());
        assertFalse(item.descobriu());
        assertEquals(4, item.qtdeLetrasEncobertas());
    }

    @Test
    public void testPalavraNula() {
        assertThrows(IllegalArgumentException.class, () -> Item.criar(1, null));
    }

    @Test
    public void testTentar() {
        Item item = Item.criar(1, palavra);
        boolean acertou = item.tentar('a');
        assertTrue(acertou);
        assertEquals(3, item.qtdeLetrasEncobertas());
    }

    @Test
    public void testTentarLetraErrada() {
        Item item = Item.criar(1, palavra);
        boolean acertou = item.tentar('z');
        assertFalse(acertou);
        assertEquals(4, item.qtdeLetrasEncobertas());
    }

    @Test
    public void testArriscar() {
        Item item = Item.criar(1, palavra);
        item.arriscar("gato");
        assertTrue(item.arriscou());
        assertTrue(item.acertou());
        assertTrue(item.descobriu());
    }

    @Test
    public void testArriscarErrado() {
        Item item = Item.criar(1, palavra);
        item.arriscar("cachorro");
        assertTrue(item.arriscou());
        assertFalse(item.acertou());
        assertFalse(item.descobriu());
    }

    @Test
    public void testArriscarDuasVezes() {
        Item item = Item.criar(1, palavra);
        item.arriscar("gato");
        assertThrows(IllegalStateException.class, () -> item.arriscar("gato"));
    }

    @Test
    public void testDescobriu() {
        Item item = Item.criar(1, palavra);
        item.tentar('g');
        item.tentar('a');
        item.tentar('t');
        item.tentar('o');
        assertTrue(item.descobriu());
    }

    @Test
    public void testCalcularPontos() {
        Item item = Item.criar(1, palavra);
        item.tentar('g');
        // 3 letras encobertas * 15 = 45
        assertEquals(45, item.calcularPontosLetrasEncobertas(15));
    }
}
