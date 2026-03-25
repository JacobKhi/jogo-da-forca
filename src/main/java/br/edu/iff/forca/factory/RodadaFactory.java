package br.edu.iff.forca.factory;

import br.edu.iff.forca.domain.Jogador;
import br.edu.iff.forca.domain.Rodada;

public interface RodadaFactory {
    public Rodada getRodada(Jogador jogador);
}