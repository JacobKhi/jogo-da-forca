package br.edu.iff.forca.factory;

import br.edu.iff.forca.domain.Tema;

public interface TemaFactory {
    public Tema getTema(String nome);
}