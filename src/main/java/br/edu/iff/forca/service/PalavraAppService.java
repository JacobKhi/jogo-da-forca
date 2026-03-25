package br.edu.iff.forca.service;

import java.util.Objects;

import br.edu.iff.forca.domain.Palavra;
import br.edu.iff.forca.domain.Tema;
import br.edu.iff.forca.factory.PalavraFactory;
import br.edu.iff.forca.repository.PalavraRepository;
import br.edu.iff.forca.repository.RepositoryException;
import br.edu.iff.forca.repository.TemaRepository;

public class PalavraAppService {
    
    private TemaRepository temaRepository;
    private PalavraRepository palavraRepository;
    private PalavraFactory palavraFactory;
    private static PalavraAppService soleInstance = null;

    public static void createSoleInstance(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory palavraFactory) {
        if (PalavraAppService.soleInstance == null)
            PalavraAppService.soleInstance = new PalavraAppService(temaRepository, palavraRepository, palavraFactory);
    }

    public static PalavraAppService getSoleInstance() {
        return PalavraAppService.soleInstance;
    }

    private PalavraAppService(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory palavraFactory) {
        Objects.requireNonNull(temaRepository);
        Objects.requireNonNull(palavraRepository);
        Objects.requireNonNull(palavraFactory);

        this.temaRepository = temaRepository;
        this.palavraRepository = palavraRepository;
        this.palavraFactory = palavraFactory;
    }

    public boolean novaPalavra(String palavra, long idTema) throws RepositoryException {
        Palavra p = palavraRepository.getPalavra(palavra);
        
        if (p != null) return true;

        Tema tema = temaRepository.getPorId(idTema);
        
        palavraFactory.getPalavra(palavra, tema);
        
        return true;
    }
}