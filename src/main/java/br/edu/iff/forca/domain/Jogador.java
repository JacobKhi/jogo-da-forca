package br.edu.iff.forca.domain;

public class Jogador extends ObjetoDominioImpl {

    private String nome;
    private int pontuacao = 0;

    private Jogador(long id, String nome) {
        super(id);
        setNome(nome);
    }

    private Jogador(long id, String nome, int pontuacao) {
        super(id);
        setNome(nome);
        atualizarPontuacao(pontuacao);
    }
    
    public static Jogador criar(long id, String nome){
        return new Jogador(id, nome);
    }
    
    public static Jogador reconstituir(long id, String nome, int pontuacao){
        return new Jogador(id, nome, pontuacao);
    }

    public String getNome() {
        return new String(nome);
    }

    public void setNome(String novoNome) {
        if (novoNome == null || novoNome.isEmpty())
            throw new IllegalArgumentException("O nome nao pode ser vazio nem nulo");

        this.nome = novoNome;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void atualizarPontuacao(int novaPontuacao) {
        if (novaPontuacao < 0) throw new IllegalArgumentException("Pontuacao nao pode ser negativa");
        this.pontuacao += novaPontuacao;
    }
}