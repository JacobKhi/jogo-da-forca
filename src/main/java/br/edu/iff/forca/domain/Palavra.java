package main.java.br.edu.iff.forca.domain;

import java.util.Arrays;

import main.java.br.edu.iff.forca.domain.letra.Letra;
import main.java.br.edu.iff.forca.factory.grafico.LetraFactory;

public class Palavra extends ObjetoDominioImpl {
    
    private String palavra;
    private Tema tema;
    private Letra[] letras;
    private static LetraFactory letraFactory;

    public static void setLetraFactory(LetraFactory letraFactory) {
        Palavra.letraFactory = letraFactory;
    }

    public static LetraFactory getLetraFactory() {
        return Palavra.letraFactory;
    }

    public static Palavra criar(long id, String palavra, Tema tema) {
        return new Palavra(id, palavra, tema);
    }

    public static Palavra reconstituir(long id, String palavra, Tema tema) {
        return new Palavra(id, palavra, tema);
    }

    private Palavra(long id, String palavra, Tema tema) {
        super(id);

        setPalavra(palavra);
        setTema(tema);
        setLetras(palavra.length());
    }

    public Letra[] getLetras() {
        return Arrays.copyOf(letras, letras.length);
    }

    public Letra getLetra(int posicao) {
        if (posicao < 0 || posicao >= letras.length)
            throw new IllegalArgumentException("a posicao precisa estar entre 0 e " + letras.length);

        return letras[posicao];
    }

    public void exibir(Object contexto) {
        System.out.println(palavra);
    }

    public void exibir(Object contexto, boolean[] posicoes) {
        System.out.println(palavra);
    }

    public int[] tentar(char codigo) {
        
        // Conta quantas vezes o codigo aparace na palavra
        int contador = 0;
        for(Letra l : letras) {
            if (l.getCodigo() == codigo) {
                contador++;
            }
        }

        // Cria um vetor com o tamanho e retorna as posicoes
        int[] posicoes = new int[contador];
        int idxPosicoes = 0; 
        for (int i = 0; i < letras.length; i++){
            if (letras[i].getCodigo() == codigo) {
                posicoes[idxPosicoes] = i;
                idxPosicoes++;
            }
        }

        return posicoes;
    }

    public Tema getTema() {
        return this.tema;
    }

    public boolean comparar(String palavra) {
        if (palavra == null || palavra.isEmpty()) return false;

        return this.palavra.equalsIgnoreCase(palavra);
    }

    public int getTamanho() {
        return palavra.length();
    }

    @Override
    public String toString() {
        return palavra;
    }

    private void setPalavra (String palavra) {
        if (palavra == null || palavra.isEmpty())
            throw new IllegalArgumentException("A palavra nao pode estar vazia");

        this.palavra = palavra;
    }

    private void setTema(Tema tema) {
        if (tema == null)
            throw new IllegalArgumentException("O tema nao pode ser nulo");
        
        this.tema = tema;
    }

    private void setLetras(int tamanho) {
        if (tamanho < 0)
            throw new IllegalArgumentException("O tamanho nao pode ser negativo");

        if (letraFactory == null) {
            throw new IllegalStateException("O letra factory precisa ser fornecido"); 
        }

        this.letras = new Letra[tamanho];

        for (int i = 0; i < this.letras.length; i++) {
            this.letras[i] = Palavra.letraFactory.getLetra(palavra.charAt(i));
        }
    }
}