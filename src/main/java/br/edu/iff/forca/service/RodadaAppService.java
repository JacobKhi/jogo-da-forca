package main.java.br.edu.iff.forca.service;

import java.util.Objects;

import main.java.br.edu.iff.forca.domain.Jogador;
import main.java.br.edu.iff.forca.domain.Rodada;
import main.java.br.edu.iff.forca.factory.RodadaFactory;
import main.java.br.edu.iff.forca.repository.JogadorRepository;
import main.java.br.edu.iff.forca.repository.RepositoryException;
import main.java.br.edu.iff.forca.repository.RodadaRepository;

public class RodadaAppService {
    
    private RodadaFactory rodadaFactory;
    private RodadaRepository rodadaRepository;
    private JogadorRepository jogadorRepository;
    private static RodadaAppService soleInstance = null;

    public static void createSoleInstance(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {
        if (RodadaAppService.soleInstance == null) 
            RodadaAppService.soleInstance = new RodadaAppService(rodadaFactory, rodadaRepository, jogadorRepository);
    }

    public static RodadaAppService getSoleInstance() {
        return RodadaAppService.soleInstance;
    }

    private RodadaAppService(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {
        Objects.requireNonNull(rodadaFactory);
        Objects.requireNonNull(rodadaRepository);
        Objects.requireNonNull(jogadorRepository);

        this.rodadaFactory = rodadaFactory;
        this.rodadaRepository = rodadaRepository;
        this.jogadorRepository = jogadorRepository;
    }

    public Rodada novaRodada(long idJogador) {
        Jogador jogador = jogadorRepository.getPorId(idJogador);

        Rodada rodada = rodadaFactory.getRodada(jogador);

        return rodada;
    }

    public Rodada novaRodada(String nomeJogador) throws JogadorNaoEncontradoException {
        Jogador jogador = jogadorRepository.getPorNome(nomeJogador);

        if (jogador == null)
            throw new JogadorNaoEncontradoException(nomeJogador);

        Rodada rodada = rodadaFactory.getRodada(jogador);

        return rodada;
    }

    public boolean salvarRodada(Rodada rodada) {
        try {
            rodadaRepository.inserir(rodada);
            return true;
        } catch (RepositoryException e) {
            return false;
        }
    }

}