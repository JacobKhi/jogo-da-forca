package main.java.br.edu.iff.forca.domain;

public abstract class ObjetoDominioImpl implements ObjetoDominio {

    private long id;

    protected ObjetoDominioImpl(long id){
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;        
    }
    
}
