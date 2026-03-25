package br.edu.iff.forca.factory.grafico;

public class BonecoTextoFactory implements BonecoFactory {

    private static BonecoTextoFactory soleInstance = null;

    private BonecoTextoFactory () {}

    public static BonecoTextoFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoTextoFactory();
        }

        return soleInstance;
    }

    @Override
    public Boneco getBoneco() {
        return BonecoTexto.getSoleInstance();
    }
    
}