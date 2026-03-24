package main.java.br.edu.iff.forca.repository;

public interface RepositoryFactory {
    public TemaRepository getTemaRepository();
    
    public JogadorRepository getJogadorRepository();

    public PalavraRepository getPalavraRepository();

    public RodadaRepository getRodadaRepository();
}
