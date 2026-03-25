package br.edu.iff.forca.domain;

import java.util.Arrays;

import br.edu.iff.forca.domain.letra.Letra;
import br.edu.iff.forca.factory.grafico.BonecoFactory;

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

        if (bonecoFactory == null)
            throw new IllegalStateException("O factory do jogador nao foi definido");

        validarPalavras(palavras);
        criarItens(palavras);
        iniciarLetrasErradas();
        setJogador(jogador);
    }

    private Rodada(long id, Item[] itens, Letra[] letrasErradas, Jogador jogador) {
        super(id);

        if (bonecoFactory == null)
            throw new IllegalStateException("O factory do jogador nao foi definido");

        setItens(itens);
        setLetrasErradas(letrasErradas);
        setJogador(jogador);
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public Tema getTema() {
        return itens[0].getPalavra().getTema();
    }

    public Palavra[] getPalavras() {
        Palavra[] palavras = new Palavra[itens.length];

        for (int i = 0; i < palavras.length; i++) {
            palavras[i] = itens[i].getPalavra();
        }

        return palavras;
    }

    public int getNumPalavras() {
        return itens.length;
    }

    public void tentar(char codigo) {
        if (encerrou())
            throw new IllegalStateException("O Jogo ja encerrou, voce nao pode mais tentar");

        boolean acertou = false;

        for (Item i : itens) {
            if (i.tentar(codigo))
                acertou = true;
        }

        if (!acertou) {
            Letra[] novo = Arrays.copyOf(letrasErradas, letrasErradas.length + 1);
            novo[novo.length - 1] = Palavra.getLetraFactory().getLetra(codigo);
            this.letrasErradas = novo;
        }

        if (encerrou()) {
            jogador.atualizarPontuacao(calcularPontos());
        }
    }

    public void arriscar(String[] palavras) {
        if (encerrou())
            throw new IllegalStateException("O Jogo ja encerrou, voce nao pode mais arriscar");

        for (int i = 0; i < itens.length; i++) {
            itens[i].arriscar(palavras[i]);
        }

        if (encerrou()) {
            jogador.atualizarPontuacao(calcularPontos());
        }
    }

    public void exibirItens(Object contexto) {
        for (Item i : itens) {
            i.exibir(contexto);
        }
    }

    public void exibirBoneco(Object contexto) {
        bonecoFactory.getBoneco().exibir(contexto, getQtdeErros());
    }

    public void exibirPalavras(Object contexto) {
        for (Item i : itens) {
            i.getPalavra().exibir(contexto);
        }
    }

    public void exibirLetrasErradas(Object contexto) {
        for (Letra l : letrasErradas) {
            l.exibir(contexto);
        }
    }

    public Letra[] getTentativas() {
        Letra[] tentativas = new Letra[getQtdeTentativas()];

        for (int i = 0; i < getCertas().length; i++) {
            tentativas[i] = getCertas()[i];
        }

        for (int i = 0; i < getErradas().length; i++) {
            tentativas[getCertas().length + i] = getErradas()[i];
        }

        return tentativas;
    }

    public Letra[] getCertas() {
        Letra[] letras = new Letra[26];
        int idx = 0;

        for (int i = 0; i < itens.length; i++) {
            for (int j = 0; j < itens[i].getLetrasDescobertas().length; j++) {

                Letra letraAtual = itens[i].getLetrasDescobertas()[j];
                boolean jaExiste = false;

                for (int k = 0; k < idx; k++) {
                    if (letras[k].equals(letraAtual)) {
                        jaExiste = true;
                        break;
                    }

                }
                if (!jaExiste) {
                    letras[idx] = letraAtual;
                    idx++;
                }
            }
        }

        return Arrays.copyOf(letras, idx);
    }

    public Letra[] getErradas() {
        return Arrays.copyOf(letrasErradas, letrasErradas.length);
    }

    public int calcularPontos() {
        if (!descobriu())
            return 0;

        int pontos = PONTOS_QUANDO_DESCOBRE_TODAS_AS_PALAVRAS;

        for (Item i : itens) {
            pontos += i.calcularPontosLetrasEncobertas(PONTOS_POR_LETRA_ENCOBERTA);
        }

        return pontos;
    }

    public boolean encerrou() {
        return arriscou() || descobriu() || getQtdeTentativasRestantes() == 0;
    }

    public boolean descobriu() {
        for (Item i : itens) {
            if (!i.descobriu())
                return false;
        }

        return true;
    }

    public boolean arriscou() {
        for (Item i : itens) {
            if (i.arriscou())
                return true;
        }

        return false;
    }

    public int getQtdeTentativasRestantes() {
        return MAX_ERROS - getQtdeErros();
    }

    public int getQtdeErros() {
        return letrasErradas.length;
    }

    public int getQtdeAcertos() {
        return getCertas().length;
    }

    public int getQtdeTentativas() {
        return getQtdeAcertos() + getQtdeErros();
    }

    private void setJogador(Jogador jogador) {
        if (jogador == null)
            throw new IllegalArgumentException("O jogador nao pode ser nulo");

        this.jogador = jogador;
    }

    private void validarPalavras(Palavra[] palavras) {
        if (palavras == null)
            throw new IllegalArgumentException("A array nao pode ser nula");

        if (palavras.length == 0 || palavras.length > MAX_PALAVRAS)
            throw new IllegalArgumentException("Tamanho invalido, ele precisa ser maior que 0 e " + MAX_PALAVRAS);

        // Verificacao de temas iguais
        for (int i = 1; i < palavras.length; i++) {
            if (!(palavras[i].getTema().getId() == palavras[0].getTema().getId())) {
                throw new IllegalStateException("Os temas tem que ser iguais");
            }
        }
    }

    private void criarItens(Palavra[] palavras) {
        this.itens = new Item[palavras.length];

        for (int i = 0; i < this.itens.length; i++) {
            this.itens[i] = Item.criar(i, palavras[i]);
        }
    }

    private void iniciarLetrasErradas() {
        this.letrasErradas = new Letra[0];
    }

    private void setItens(Item[] itens) {
        if (itens == null || itens.length == 0)
            throw new IllegalArgumentException("A array nao pode ser nula nem vazia");

        this.itens = itens;
    }

    public Item[] getItens() {
        return Arrays.copyOf(itens, itens.length);
    }

    private void setLetrasErradas(Letra[] letrasErradas) {
        if (letrasErradas == null)
            throw new IllegalArgumentException("O vetor de letras nao pode ser nulo");

        this.letrasErradas = letrasErradas;
    }
}
