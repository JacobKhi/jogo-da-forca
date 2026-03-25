package br.edu.iff.forca.factory;

import br.edu.iff.forca.domain.Palavra;
import br.edu.iff.forca.domain.Tema;
import br.edu.iff.forca.repository.PalavraRepository;
import br.edu.iff.forca.repository.Repository;
import br.edu.iff.forca.repository.RepositoryException;

public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {

    private static PalavraFactoryImpl soleInstance = null;

    public static void createSoleInstance(PalavraRepository repository) {
        if (PalavraFactoryImpl.soleInstance == null) 
            PalavraFactoryImpl.soleInstance = new PalavraFactoryImpl(repository);
    }

    public static PalavraFactoryImpl getSoleInstance() {
        return PalavraFactoryImpl.soleInstance;
    }

    private PalavraFactoryImpl(Repository repository) {
        super(repository);
    }

    @Override
    public Palavra getPalavra(String palavra, Tema tema) {
        Palavra p = getPalavraRepository().getPalavra(palavra);

        if (p == null) {
            Palavra nova = Palavra.criar(getProximoId(), palavra, tema);
            try {
                getPalavraRepository().inserir(nova);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            return nova;
        }
        return p;
    }

    private PalavraRepository getPalavraRepository() {
        return (PalavraRepository) getRepository();
    }
}