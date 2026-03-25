package br.edu.iff.forca.factory.grafico;

import br.edu.iff.forca.domain.letra.Letra;
import br.edu.iff.forca.domain.letra.LetraTexto;

public class LetraTextoFactory extends LetraFactoryImpl {

    private static LetraTextoFactory soleInstance = null;

    private LetraTextoFactory() {}

    public static LetraTextoFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new LetraTextoFactory();
        }

        return soleInstance;
    }

    @Override
    protected Letra criarLetra(char codigo) {
        return new LetraTexto(codigo);
    }
    
}