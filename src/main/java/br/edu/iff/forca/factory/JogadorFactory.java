package main.java.br.edu.iff.forca.factory;

import main.java.br.edu.iff.forca.domain.Jogador;

public interface JogadorFactory {
    public Jogador getJogador(String nome);
}