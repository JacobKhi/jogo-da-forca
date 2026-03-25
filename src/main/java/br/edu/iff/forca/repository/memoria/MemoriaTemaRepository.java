package br.edu.iff.forca.repository.memoria;

import java.util.ArrayList;

import br.edu.iff.forca.domain.Tema;
import br.edu.iff.forca.repository.RepositoryException;
import br.edu.iff.forca.repository.TemaRepository;

public class MemoriaTemaRepository implements TemaRepository {
    
    private ArrayList<Tema> pool = new ArrayList<>();
    private static MemoriaTemaRepository soleInstance = null;

    public static MemoriaTemaRepository getSoleInstance() {
        if (MemoriaTemaRepository.soleInstance == null) 
            MemoriaTemaRepository.soleInstance = new MemoriaTemaRepository();

        return MemoriaTemaRepository.soleInstance;
    }

    private MemoriaTemaRepository() {}

    @Override
    public long getProximoId() {
        return this.pool.size() + 1;
    }

    @Override
    public Tema getPorId(long id) {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getId() == id) {
                return this.pool.get(i);
            }
        }
        return null;
    }

    @Override
    public Tema[] getPorNome(String nome) {
        ArrayList<Tema> nomes = new ArrayList<>();

        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getNome().equals(nome)) {
                nomes.add(this.pool.get(i));
            }
        }

        return nomes.toArray(new Tema[0]);
    }

    @Override
    public Tema[] getTodos() {
        return this.pool.toArray(new Tema[0]);
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {
        this.pool.add(tema);
    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getId() == tema.getId()) {
                this.pool.set(i, tema);
            }
        }
    }

    @Override
    public void remover(Tema tema) throws RepositoryException {
        this.pool.remove(tema);
    }

}
