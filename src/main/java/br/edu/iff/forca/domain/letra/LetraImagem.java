package main.java.br.edu.iff.forca.domain.letra;

public class LetraImagem extends Letra {

    protected LetraImagem(char codigo) {
        super(codigo);
    }

    @Override
    public void exibir(Object contexto) {
        System.out.println(getCodigo());
    }
    
}