package main.java.br.edu.iff.forca.factory.grafico;

import main.java.br.edu.iff.forca.domain.letra.Letra;

public interface LetraFactory {
    public Letra getLetra(char codigo);
    public Letra getLetraEncoberta();
}