package main.java.br.edu.iff.forca.factory;

import main.java.br.edu.iff.forca.domain.Tema;

public interface TemaFactory {
    public Tema getTema(String nome);
}