package main.java.br.edu.iff.forca.repository.memoria;

import java.util.ArrayList;

import main.java.br.edu.iff.forca.domain.Palavra;
import main.java.br.edu.iff.forca.domain.Tema;
import main.java.br.edu.iff.forca.repository.PalavraRepository;
import main.java.br.edu.iff.forca.repository.RepositoryException;

public class MemoriaPalavraRepository implements PalavraRepository {

    private ArrayList<Palavra> pool = new ArrayList<>();
    private static MemoriaPalavraRepository soleInstance = null;

    public static MemoriaPalavraRepository getSoleInstance() {
        if (MemoriaPalavraRepository.soleInstance == null) 
            MemoriaPalavraRepository.soleInstance = new MemoriaPalavraRepository();
        
        return MemoriaPalavraRepository.soleInstance;
    }

    private MemoriaPalavraRepository () {}

    @Override
    public long getProximoId() {
        return pool.size() + 1;
    }

    @Override
    public Palavra getPorId(long id) {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getId() == id) {
                return this.pool.get(i);
            }
        }

        return null;
    }   

    @Override
    public Palavra[] getPorTema(Tema tema) {
        ArrayList<Palavra> palavras = new ArrayList<>();

        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getTema().getId() == tema.getId()) {
                palavras.add(this.pool.get(i));
            }
        }

        return palavras.toArray(new Palavra[0]);
    }

    @Override
    public Palavra[] getTodas() {
        return pool.toArray(new Palavra[0]);
    }

    @Override
    public Palavra getPalavra(String palavra) {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).comparar(palavra)) {
                return this.pool.get(i);
            }
        }

        return null;
    }

    @Override
    public void inserir(Palavra palavra) throws RepositoryException {
        pool.add(palavra);
    }

    @Override
    public void atualizar(Palavra palavra) throws RepositoryException {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getId() == palavra.getId()) {
                this.pool.set(i, palavra);
            }
        }
    }

    @Override
    public void remover(Palavra palavra) throws RepositoryException {
        pool.remove(palavra);
    }
    
}
