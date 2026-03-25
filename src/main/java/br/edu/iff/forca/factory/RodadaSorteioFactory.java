package br.edu.iff.forca.factory;

import java.util.Random;

import br.edu.iff.forca.domain.Jogador;
import br.edu.iff.forca.domain.Palavra;
import br.edu.iff.forca.domain.Rodada;
import br.edu.iff.forca.domain.Tema;
import br.edu.iff.forca.repository.PalavraRepository;
import br.edu.iff.forca.repository.RepositoryException;
import br.edu.iff.forca.repository.RodadaRepository;
import br.edu.iff.forca.repository.TemaRepository;

public class RodadaSorteioFactory extends RodadaFactoryImpl{

    private static RodadaSorteioFactory soleInstance = null;

    public static void createSoleInstance(RodadaRepository rodadaRepository, TemaRepository temaRepository,
            PalavraRepository palavraRepository) {
        if (RodadaSorteioFactory.soleInstance == null)
            RodadaSorteioFactory.soleInstance = new RodadaSorteioFactory(rodadaRepository, temaRepository, palavraRepository);
    }

    public static RodadaSorteioFactory getSoleInstance() {
        return RodadaSorteioFactory.soleInstance;
    }

    private RodadaSorteioFactory(RodadaRepository rodadaRepository, TemaRepository temaRepository,
            PalavraRepository palavraRepository) {
        super(rodadaRepository, temaRepository, palavraRepository);
    }

    @Override
    public Rodada getRodada(Jogador jogador) {

        Random rnd = new Random();

        Tema[] temas = getTemaRepository().getTodos();
        if (temas.length == 0)
            throw new IllegalStateException("Nao possui temas cadatrados");

        int idx = rnd.nextInt(temas.length);

        Tema temaSorteado = temas[idx];

        Palavra[] palavras = getPalavraRepository().getPorTema(temaSorteado);
        if (palavras.length == 0)
            throw new IllegalStateException("Nao possui palavras cadastradas");

        int numeroMaxPalavras = Math.min(palavras.length, Rodada.getMaxPalavras());
        int qtde = rnd.nextInt(numeroMaxPalavras) + 1;

        Palavra[] palavrasSorteadas = new Palavra[qtde];
        for (int i = 0; i < qtde; i++) {
            int aleatorio = rnd.nextInt(palavras.length);
            boolean jaFoiSorteada = false;

            for (int j = 0; j < i; j++) {
                if (palavrasSorteadas[j] == palavras[aleatorio]) {
                    jaFoiSorteada = true;
                    break;
                }
            }

            if (jaFoiSorteada) i--;
            else palavrasSorteadas[i] = palavras[aleatorio];
        }

        Rodada rodada = Rodada.criar(getProximoId(), palavrasSorteadas, jogador);
        try {
            getRodadaRepository().inserir(rodada);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        return rodada;
    }


}