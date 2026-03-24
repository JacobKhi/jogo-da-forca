package main.java.br.edu.iff.forca.factory;

import main.java.br.edu.iff.forca.domain.Palavra;
import main.java.br.edu.iff.forca.domain.Tema;

public interface PalavraFactory {
    public Palavra getPalavra(String palavra, Tema tema);
}