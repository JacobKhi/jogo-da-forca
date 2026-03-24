package main.java.br.edu.iff.forca.repository;

import main.java.br.edu.iff.forca.domain.Palavra;
import main.java.br.edu.iff.forca.domain.Tema;

public interface PalavraRepository extends Repository {
    public Palavra getPorId(long id);
    
    public Palavra[] getPorTema(Tema tema);

    public Palavra[] getTodas();

    public Palavra getPalavra(String palaString);

    public void inserir(Palavra palavra) throws RepositoryException;

    public void atualizar(Palavra palavra) throws RepositoryException;

    public void remover(Palavra palavra) throws RepositoryException;
}
