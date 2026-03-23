package main.java.br.edu.iff.forca.domain;

import main.java.br.edu.iff.forca.domain.letra.Letra;
import main.java.br.edu.iff.forca.factory.grafico.BonecoFactory;

public class Rodada extends ObjetoDominioImpl {
    
    private Item[] itens;
    private Letra[] letrasErradas;
    private Jogador jogador;
    private static BonecoFactory bonecoFactory = null;

    private static int PONTOS_POR_LETRA_ENCOBERTA = 15;
    private static int PONTOS_QUANDO_DESCOBRE_TODAS_AS_PALAVRAS = 100;
    private static int MAX_ERROS = 10;
    private static int MAX_PALAVRAS = 3;
    
    public static BonecoFactory getBonecoFactory() {
        return bonecoFactory;
    }

    public static void setBonecoFactory(BonecoFactory bonecoFactory) {
        Rodada.bonecoFactory = bonecoFactory;
    }

    public static int getPontosPorLetraEncoberta() {
        return PONTOS_POR_LETRA_ENCOBERTA;
    }

    public static void setPontosPorLetraEncoberta(int pontosPorLetraEncoberta) {
        PONTOS_POR_LETRA_ENCOBERTA = pontosPorLetraEncoberta;
    }

    public static int getPontosQuandoDescobreTodasAsPalavras() {
        return PONTOS_QUANDO_DESCOBRE_TODAS_AS_PALAVRAS;
    }

    public static void setPontosQuandoDescobreTodasAsPalavras(int pontosQuandoDescobreTodasAsPalavras) {
        PONTOS_QUANDO_DESCOBRE_TODAS_AS_PALAVRAS = pontosQuandoDescobreTodasAsPalavras;
    }

    public static int getMaxErros() {
        return MAX_ERROS;
    }

    public static void setMaxErros(int maxErros) {
        MAX_ERROS = maxErros;
    }

    public static int getMaxPalavras() {
        return MAX_PALAVRAS;
    }

    public static void setMaxPalavras(int maxPalavras) {
        MAX_PALAVRAS = maxPalavras;
    }
    
    public static Rodada criar(long id, Palavra[] palavras, Jogador jogador) {
        return new Rodada(id, palavras, jogador);
    }

    public static Rodada reconstituir(long id, Item[] itens, Letra[] letrasErradas, Jogador jogador) {
        return new Rodada(id, itens, letrasErradas, jogador);
    }

    private Rodada(long id, Palavra[] palavras, Jogador jogador) {
        super(id);


    }

    private Rodada(long id, Item[] itens, Letra[] letrasErradas, Jogador jogador) {
        super(id);

        
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public Tema getTema() {}

    public Palavra[] getPalavras() {}

    public int getNumPalavras() {}

    public void tentar(char codigo) {}

    public void arriscar(String[] palavras) {}

    public void exibirItens(Object contexto) {}

    public void exibirBoneco(Object contexto) {}
    
    public void exibirPalavras(Object contexto) {}
    
    public void exibirLetrasErradas(Object contexto) {}

    public Letra[] getTentativas() {}

    public Letra[] getCertas() {}

    public Letra[] getErradas() {}

    public int calcularPontos() {}

    public boolean encerrou() {}

    public boolean descobriu() {}

    public boolean arriscou() {}

    public int getQtdeTentativasRestantes() {}

    public int getQtdeErros() {}

    public int getQtdeAcertos() {}

    public int getQtdeTentativas() {}

    private void setJogador(Jogador jogador) {
        if (jogador == null) 
            throw new IllegalArgumentException("O jogador nao pode ser nulo");

        this.jogador = jogador;
    }

    private void setMaxPalavras(Palavra[] palavras) {
        if (palavras == null || palavras.length == 0)
            throw new IllegalArgumentException("A array nao pode ser nula nem vazia");

        // Verificacao de temas iguais
        for (int i = 1; i < palavras.length; i++) {
            if (!palavras[i].getTema().equals(palavras[0].getTema())) {
                throw new IllegalStateException("Os temas tem que ser iguais");
            }
        }

        
    }
}
