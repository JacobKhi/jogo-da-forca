package br.edu.iff.forca.factory.grafico;

import br.edu.iff.forca.domain.letra.Letra;
import br.edu.iff.forca.domain.letra.LetraImagem;

public class LetraImagemFactory extends LetraFactoryImpl {

    private static LetraImagemFactory soleInstance = null;

    private LetraImagemFactory() {}

    public static LetraImagemFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new LetraImagemFactory();
        }

        return soleInstance;
    }

    @Override
    protected Letra criarLetra(char codigo) {
        return new LetraImagem(codigo);
    }
    
}