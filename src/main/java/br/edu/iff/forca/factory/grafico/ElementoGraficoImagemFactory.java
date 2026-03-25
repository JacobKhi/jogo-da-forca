package br.edu.iff.forca.factory.grafico;

import br.edu.iff.forca.domain.letra.Letra;

public class ElementoGraficoImagemFactory implements ElementoGraficoFactory{

    private LetraImagemFactory letraFactory;
    private BonecoImagemFactory bonecoFactory;
    private static ElementoGraficoImagemFactory soleInstance = null;

    private ElementoGraficoImagemFactory() {
        this.letraFactory = LetraImagemFactory.getSoleInstance();
        this.bonecoFactory = BonecoImagemFactory.getSoleInstance();
    }

    public static ElementoGraficoImagemFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new ElementoGraficoImagemFactory();
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