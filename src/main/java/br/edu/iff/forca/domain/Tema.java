package main.java.br.edu.iff.forca.domain;

public class Tema extends ObjetoDominioImpl{

    private String nome;

    private Tema(long id, String nome) {
        super(id);
        setNome(nome);
    }

    public static Tema criar(long id, String nome) {
        return new Tema(id, nome);
    }

    public static Tema recontruir(long id, String nome) {
        return new Tema(id, nome);
    }

    public String getNome() {
        return new String(nome);
    }

    public void setNome(String novoNome) {
        if (novoNome == null || novoNome.isEmpty())
            throw new IllegalArgumentException("O nome nao pode ser vazio nem nulo");

        this.nome = novoNome;
    }
}