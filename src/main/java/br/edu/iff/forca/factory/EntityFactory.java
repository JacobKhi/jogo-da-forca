package main.java.br.edu.iff.forca.factory;

import main.java.br.edu.iff.forca.repository.Repository;

public abstract class EntityFactory {
    
    private Repository repository;

    protected EntityFactory(Repository repository) {
        if (repository == null) 
            throw new IllegalArgumentException("O repository nao pode ser null");

        this.repository = repository;
    }

    protected Repository getRepository() {
        return this.repository;
    }

    protected long getProximoId() {
        return this.repository.getProximoId();
    }

}