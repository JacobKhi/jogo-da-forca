package br.edu.iff.forca.factory.grafico;

import br.edu.iff.forca.domain.letra.Letra;

public abstract class LetraFactoryImpl implements LetraFactory {

    private char simboloEncoberto = '*';
    private Letra[] pool = new Letra[26];
    private Letra encoberta = null;

    protected LetraFactoryImpl() {}

    protected abstract Letra criarLetra(char codigo);

    @Override
    public final Letra getLetra(char codigo) {

        /*
        calcula os indices se baseando nos valores ascii
        'b' - 'a' = 98 - 97 = 1
        'c' - 'a' = 99 - 97 = 2
        'z' - 'a' = 122 - 97 = 25
        */
        int idx = Character.toLowerCase(codigo) - 'a';

        if (pool[idx] == null) {
            pool[idx] = criarLetra(codigo);
        }

        return pool[idx];
    }

    @Override
    public Letra getLetraEncoberta() {
        if (encoberta == null){
            this.encoberta = criarLetra(simboloEncoberto);
        }

        return this.encoberta;
    }

}