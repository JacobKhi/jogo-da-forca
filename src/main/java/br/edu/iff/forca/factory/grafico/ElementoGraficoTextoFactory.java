package br.edu.iff.forca.factory.grafico;

import br.edu.iff.forca.domain.letra.Letra;

public class ElementoGraficoTextoFactory implements ElementoGraficoFactory{

    private LetraTextoFactory letraFactory;
    private BonecoTextoFactory bonecoFactory;
    private static ElementoGraficoTextoFactory soleInstance = null;

    private ElementoGraficoTextoFactory() {
        this.letraFactory = LetraTextoFactory.getSoleInstance();
        this.bonecoFactory = BonecoTextoFactory.getSoleInstance();
    }

    public static ElementoGraficoTextoFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new ElementoGraficoTextoFactory();
        }

        return soleInstance;
    }

    @Override
    public Letra getLetra(char codigo) {
        return letraFactory.getLetra(codigo);
    }

    @Override
    public Letra getLetraEncoberta() {
        return letraFactory.getLetraEncoberta();
    }

    @Override
    public Boneco getBoneco() {
        return bonecoFactory.getBoneco();
    }

}