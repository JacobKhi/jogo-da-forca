package br.edu.iff.forca.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import br.edu.iff.forca.factory.grafico.BonecoTextoFactory;
import br.edu.iff.forca.factory.grafico.LetraTextoFactory;

public class RodadaTest {

    private Palavra[] palavras;
    private Jogador jogador;

    @BeforeEach
    public void setUp() {
        Palavra.setLetraFactory(LetraTextoFactory.getSoleInstance());
        Rodada.setBonecoFactory(BonecoTextoFactory.getSoleInstance());

        Tema tema = Tema.criar(1, "Animais");
        palavras = new Palavra[]{
            Palavra.criar(1, "gato", tema),
            Palavra.criar(2, "rato", tema)
        };
        jogador = Jogador.criar(1, "Maria");
    }

    @Test
    public void testCriar() {
        Rodada r = Rodada.criar(1, palavras, jogador);
        assertEquals(jogador, r.getJogador());
        assertEquals(2, r.getNumPalavras());
        assertFalse(r.encerrou());
        assertFalse(r.arriscou());
        assertFalse(r.descobriu());
    }

    @Test
    public void testJogadorNulo() {
        assertThrows(IllegalArgumentException.class, () -> Rodada.criar(1, palavras, null));
    }

    @Test
    public void testPalavrasNulas() {
        assertThrows(IllegalArgumentException.class, () -> Rodada.criar(1, null, jogador));
    }

    @Test
    public void testPalavrasDeTemasDistintos() {
        Tema outroTema = Tema.criar(2, "Frutas");
        Palavra[] palavrasMistas = new Palavra[]{
            Palavra.criar(1, "gato", Tema.criar(1, "Animais")),
            Palavra.criar(2, "banana", outroTema)
        };
        assertThrows(IllegalStateException.class, () -> Rodada.criar(1, palavrasMistas, jogador));
    }

    @Test
    public void testTentar() {
        Rodada r = Rodada.criar(1, palavras, jogador);
        r.tentar('a');
        assertEquals(1, r.getQtdeAcertos());
        assertEquals(0, r.getQtdeErros());
    }

    @Test
    public void testTentarErro() {
        Rodada r = Rodada.criar(1, palavras, jogador);
        r.tentar('z');
        assertEquals(0, r.getQtdeAcertos());
        assertEquals(1, r.getQtdeErros());
    }

    @Test
    public void testEncerrarPorMaxErros() {
        Rodada r = Rodada.criar(1, palavras, jogador);
        r.tentar('b');
        r.tentar('c');
        r.tentar('d');
        r.tentar('e');
        r.tentar('f');
        r.tentar('h');
        r.tentar('i');
        r.tentar('j');
        r.tentar('k');
        r.tentar('l');
        assertTrue(r.encerrou());
        assertFalse(r.descobriu());
    }

    @Test
    public void testArriscar() {
        Rodada r = Rodada.criar(1, palavras, jogador);
        r.arriscar(new String[]{"gato", "rato"});
        assertTrue(r.arriscou());
        assertTrue(r.descobriu());
        assertTrue(r.encerrou());
    }

    @Test
    public void testCalcularPontos() {
        Rodada r = Rodada.criar(1, palavras, jogador);
        r.tentar('g');
        r.tentar('a');
        r.tentar('t');
        r.tentar('o');
        r.tentar('r');
        // descobriu todas as palavras por letras
        assertTrue(r.descobriu());
        // 100 + (0 letras encobertas * 15) = 100
        assertEquals(100, r.calcularPontos());
    }

    @Test
    public void testTentarAposEncerrar() {
        Rodada r = Rodada.criar(1, palavras, jogador);
        r.arriscar(new String[]{"gato", "rato"});
        assertThrows(IllegalStateException.class, () -> r.tentar('a'));
    }

    @Test
    public void testAtualizarPontuacaoJogador() {
        Rodada r = Rodada.criar(1, palavras, jogador);
        r.tentar('g');
        r.tentar('a');
        r.tentar('t');
        r.tentar('o');
        r.tentar('r');
        // jogador deve ter pontuacao atualizada ao encerrar
        assertTrue(jogador.getPontuacao() > 0);
    }
}
