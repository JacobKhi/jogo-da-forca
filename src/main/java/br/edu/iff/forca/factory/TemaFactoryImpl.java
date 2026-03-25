package br.edu.iff.forca.factory;

import br.edu.iff.forca.domain.Tema;
import br.edu.iff.forca.repository.RepositoryException;
import br.edu.iff.forca.repository.TemaRepository;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory {

    private static TemaFactoryImpl soleInstance = null;

    public static void createSoleInstance(TemaRepository repository) {
        if (TemaFactoryImpl.soleInstance == null) 
            TemaFactoryImpl.soleInstance = new TemaFactoryImpl(repository);
    }

    public static TemaFactoryImpl getSoleInstance() {
        return TemaFactoryImpl.soleInstance;
    }

    private TemaFactoryImpl(TemaRepository repository) {
        super(repository);
    }
    
    @Override
    public Tema getTema(String nome) {
        Tema[] temas = getTemaRepository().getPorNome(nome);

        if (temas.length == 0) {
            Tema novo = Tema.criar(getProximoId(), nome);
            try {
                getTemaRepository().inserir(novo);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            return novo;
        }

        return temas[0];
    }

    private TemaRepository getTemaRepository() {
        return (TemaRepository) getRepository();
    }

}   