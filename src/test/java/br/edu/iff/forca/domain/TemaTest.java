package br.edu.iff.forca.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TemaTest {

    @Test
    public void testCriar() {
        Tema t = Tema.criar(1, "Animais");
        assertEquals(1, t.getId());
        assertEquals("Animais", t.getNome());
    }

    @Test
    public void testReconstituir() {
        Tema t = Tema.reconstituir(2, "Frutas");
        assertEquals(2, t.getId());
        assertEquals("Frutas", t.getNome());
    }

    @Test
    public void testSetNomeNulo() {
        assertThrows(IllegalArgumentException.class, () -> Tema.criar(1, null));
    }

    @Test
    public void testSetNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> Tema.criar(1, ""));
    }

    @Test
    public void testSetNome() {
        Tema t = Tema.criar(1, "Animais");
        t.setNome("Frutas");
        assertEquals("Frutas", t.getNome());
    }
}
