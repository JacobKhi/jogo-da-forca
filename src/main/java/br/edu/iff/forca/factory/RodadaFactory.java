package main.java.br.edu.iff.forca.factory;

import main.java.br.edu.iff.forca.domain.Jogador;
import main.java.br.edu.iff.forca.domain.Rodada;

public interface RodadaFactory {
    public Rodada getRodada(Jogador jogador);
}