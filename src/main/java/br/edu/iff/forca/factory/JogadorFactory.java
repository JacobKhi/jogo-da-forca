package br.edu.iff.forca.factory;

import br.edu.iff.forca.domain.Jogador;

public interface JogadorFactory {
    public Jogador getJogador(String nome);
}