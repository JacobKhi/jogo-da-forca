package main.java.br.edu.iff.forca.domain.letra;

public class LetraTexto extends Letra {

    public LetraTexto(char codigo) {
        super(codigo);
    }

    @Override
    public void exibir(Object contexto) {
        System.out.println(getCodigo());
    }
}
