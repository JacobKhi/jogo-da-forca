package br.edu.iff.forca.factory.grafico;

public class BonecoTexto implements Boneco{

    private static BonecoTexto soleInstance = null;

    private BonecoTexto() {}

    public static BonecoTexto getSoleInstance() {
        if (soleInstance == null) { 
            soleInstance = new BonecoTexto();
        }

        return soleInstance;
    }

    @Override
    public void exibir(Object contexto, int partes) {
        System.out.println("Boneco com " + partes + " partes");
    }
    
}