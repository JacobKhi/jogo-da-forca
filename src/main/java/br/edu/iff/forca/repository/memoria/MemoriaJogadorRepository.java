package br.edu.iff.forca.repository.memoria;

import java.util.ArrayList;

import br.edu.iff.forca.domain.Jogador;
import br.edu.iff.forca.repository.JogadorRepository;
import br.edu.iff.forca.repository.RepositoryException;

public class MemoriaJogadorRepository implements JogadorRepository{

    private ArrayList<Jogador> pool = new ArrayList<>();
    private static MemoriaJogadorRepository soleInstance = null;

    public static MemoriaJogadorRepository getSoleInstance() {
        if (MemoriaJogadorRepository.soleInstance == null) 
            MemoriaJogadorRepository.soleInstance = new MemoriaJogadorRepository();

        return MemoriaJogadorRepository.soleInstance;
    }

    private MemoriaJogadorRepository () {}

    @Override
    public long getProximoId() {
        return this.pool.size() + 1;
    }

    @Override
    public Jogador getPorId(long id) {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getId() == id) {
                return this.pool.get(i);
            }
        }
        return null;
    }

    @Override
    public Jogador getPorNome(String nome) {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getNome().equals(nome)) {
                return this.pool.get(i);
            }
        }
        return null;        
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {
        this.pool.add(jogador);
    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getId() == jogador.getId()) {
                this.pool.set(i, jogador);
            }
        }
    }

    @Override
    public void remover(Jogador jogador) throws RepositoryException {
        this.pool.remove(jogador);
    }
    
}
