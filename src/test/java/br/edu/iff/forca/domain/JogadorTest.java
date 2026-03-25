package br.edu.iff.forca.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JogadorTest {

    @Test
    public void testCriar() {
        Jogador j = Jogador.criar(1, "Maria");
        assertEquals(1, j.getId());
        assertEquals("Maria", j.getNome());
        assertEquals(0, j.getPontuacao());
    }

    @Test
    public void testReconstituir() {
        Jogador j = Jogador.reconstituir(2, "Joao", 100);
        assertEquals(2, j.getId());
        assertEquals("Joao", j.getNome());
        assertEquals(100, j.getPontuacao());
    }

    @Test
    public void testNomeNulo() {
        assertThrows(IllegalArgumentException.class, () -> Jogador.criar(1, null));
    }

    @Test
    public void testNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> Jogador.criar(1, ""));
    }

    @Test
    public void testAtualizarPontuacao() {
        Jogador j = Jogador.criar(1, "Maria");
        j.atualizarPontuacao(50);
        assertEquals(50, j.getPontuacao());
        j.atualizarPontuacao(50);
        assertEquals(100, j.getPontuacao());
    }

    @Test
    public void testAtualizarPontuacaoNegativa() {
        Jogador j = Jogador.criar(1, "Maria");
        assertThrows(IllegalArgumentException.class, () -> j.atualizarPontuacao(-1));
    }
}
