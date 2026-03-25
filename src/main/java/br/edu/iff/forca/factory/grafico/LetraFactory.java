package br.edu.iff.forca.factory.grafico;

import br.edu.iff.forca.domain.letra.Letra;

public interface LetraFactory {
    public Letra getLetra(char codigo);
    public Letra getLetraEncoberta();
}