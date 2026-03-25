package br.edu.iff.forca.factory.grafico;

public class BonecoImagem implements Boneco{

    private static BonecoImagem soleInstance = null;

    private BonecoImagem() {}

    public static BonecoImagem getSoleInstance() {
        if (soleInstance == null) { 
            soleInstance = new BonecoImagem();
        }

        return soleInstance;
    }

    @Override
    public void exibir(Object contexto, int partes) {
        System.out.println("Boneco com " + partes + " partes");
    }
    
}