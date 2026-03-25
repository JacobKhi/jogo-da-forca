package br.edu.iff.forca.factory.grafico;

public class BonecoImagemFactory implements BonecoFactory {

    private static BonecoImagemFactory soleInstance = null;

    private BonecoImagemFactory () {}

    public static BonecoImagemFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoImagemFactory();
        }

        return soleInstance;
    }

    @Override
    public Boneco getBoneco() {
        return BonecoImagem.getSoleInstance();
    }
    
}