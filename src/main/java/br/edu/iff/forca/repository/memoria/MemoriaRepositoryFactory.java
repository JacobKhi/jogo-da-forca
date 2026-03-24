package main.java.br.edu.iff.forca.repository.memoria;

import main.java.br.edu.iff.forca.repository.JogadorRepository;
import main.java.br.edu.iff.forca.repository.PalavraRepository;
import main.java.br.edu.iff.forca.repository.RepositoryFactory;
import main.java.br.edu.iff.forca.repository.RodadaRepository;
import main.java.br.edu.iff.forca.repository.TemaRepository;

public class MemoriaRepositoryFactory implements RepositoryFactory{

    private static MemoriaRepositoryFactory soleInstance = null;

    public static MemoriaRepositoryFactory getSoleInstance() {
        if (MemoriaRepositoryFactory.soleInstance == null)
            MemoriaRepositoryFactory.soleInstance = new MemoriaRepositoryFactory();

        return MemoriaRepositoryFactory.soleInstance;
    }

    private MemoriaRepositoryFactory() {}

    @Override
    public TemaRepository getTemaRepository() {
        return MemoriaTemaRepository.getSoleInstance();
    }

    @Override
    public JogadorRepository getJogadorRepository() {
        return MemoriaJogadorRepository.getSoleInstance();
    }

    @Override
    public PalavraRepository getPalavraRepository() {
        return MemoriaPalavraRepository.getSoleInstance();
    }

    @Override
    public RodadaRepository getRodadaRepository() {
        return MemoriaRodadaRepository.getSoleInstance();
    }
    
}
