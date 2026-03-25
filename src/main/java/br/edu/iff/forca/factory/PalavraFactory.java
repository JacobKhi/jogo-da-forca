package br.edu.iff.forca.factory;

import br.edu.iff.forca.domain.Palavra;
import br.edu.iff.forca.domain.Tema;

public interface PalavraFactory {
    public Palavra getPalavra(String palavra, Tema tema);
}