package main.java.br.edu.iff.forca;

import main.java.br.edu.iff.forca.domain.Palavra;
import main.java.br.edu.iff.forca.domain.Rodada;
import main.java.br.edu.iff.forca.factory.JogadorFactory;
import main.java.br.edu.iff.forca.factory.JogadorFactoryImpl;
import main.java.br.edu.iff.forca.factory.PalavraFactory;
import main.java.br.edu.iff.forca.factory.PalavraFactoryImpl;
import main.java.br.edu.iff.forca.factory.RodadaFactory;
import main.java.br.edu.iff.forca.factory.RodadaSorteioFactory;
import main.java.br.edu.iff.forca.factory.TemaFactory;
import main.java.br.edu.iff.forca.factory.TemaFactoryImpl;
import main.java.br.edu.iff.forca.factory.grafico.BonecoFactory;
import main.java.br.edu.iff.forca.factory.grafico.ElementoGraficoFactory;
import main.java.br.edu.iff.forca.factory.grafico.ElementoGraficoTextoFactory;
import main.java.br.edu.iff.forca.factory.grafico.LetraFactory;
import main.java.br.edu.iff.forca.repository.RepositoryFactory;
import main.java.br.edu.iff.forca.repository.memoria.MemoriaRepositoryFactory;
import main.java.br.edu.iff.forca.service.PalavraAppService;
import main.java.br.edu.iff.forca.service.RodadaAppService;

public class Aplicacao {
    
    private final String[] TIPOS_REPOSITORY_FACTORY = {"memoria", "relacional"};
    private final String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = {"texto", "imagem"};
    private final String[] TIPOS_RODADA_FACTORY = {"sorteio"};

    private static Aplicacao soleInstance = null;
    private String tipoRepositoryFactory = TIPOS_REPOSITORY_FACTORY[0];
    private String tipoElementoGraficoFactory = TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
    private String tipoRodadaFactory = TIPOS_RODADA_FACTORY[0];

    public static Aplicacao getSoleInstance() {
        if (Aplicacao.soleInstance == null) 
            Aplicacao.soleInstance = new Aplicacao();

        return Aplicacao.soleInstance;
    }

    private Aplicacao() {}

    public void configurar() {

        RepositoryFactory repoFac = getRepositoryFactory();

        TemaFactoryImpl.createSoleInstance(repoFac.getTemaRepository());
        PalavraFactoryImpl.createSoleInstance(repoFac.getPalavraRepository());
        JogadorFactoryImpl.createSoleInstance(repoFac.getJogadorRepository());
        RodadaSorteioFactory.createSoleInstance(
            repoFac.getRodadaRepository(), 
            repoFac.getTemaRepository(), 
            repoFac.getPalavraRepository()
        );

        Palavra.setLetraFactory(getLetraFactory());
        Rodada.setBonecoFactory(getBonecoFactory());

        PalavraAppService.createSoleInstance(
            repoFac.getTemaRepository(), 
            repoFac.getPalavraRepository(), 
            getPalavraFactory()
        );

        RodadaAppService.createSoleInstance(
            getRodadaFactory(),
            repoFac.getRodadaRepository(),
            repoFac.getJogadorRepository()
        );

    }

    public RepositoryFactory getRepositoryFactory() {
        if (tipoRepositoryFactory.equals(TIPOS_REPOSITORY_FACTORY[0])) {
            return MemoriaRepositoryFactory.getSoleInstance();
        }
        return null;
    }

    private ElementoGraficoFactory getElementoGraficoFactory() {
        if (tipoElementoGraficoFactory.equals(TIPOS_ELEMENTO_GRAFICO_FACTORY[0])) {
            return ElementoGraficoTextoFactory.getSoleInstance();
        }
        return null;
    }

    public RodadaFactory getRodadaFactory() {
        if (tipoRodadaFactory.equals(TIPOS_RODADA_FACTORY[0])) {
            return RodadaSorteioFactory.getSoleInstance();
        }

        return null;
    }

    public BonecoFactory getBonecoFactory() {
        return getElementoGraficoFactory();
    }

    public LetraFactory getLetraFactory() {
        return getElementoGraficoFactory();
    }

    public TemaFactory getTemaFactory() {
    return TemaFactoryImpl.getSoleInstance();
}

    public PalavraFactory getPalavraFactory() {
        return PalavraFactoryImpl.getSoleInstance();
    }

    public JogadorFactory getJogadorFactory() {
        return JogadorFactoryImpl.getSoleInstance();
    }

    public String[] getTiposRepositoryFactory() {
        return TIPOS_REPOSITORY_FACTORY;
    }

    public void setTipoRepositoryFactory(String tipo) {
        this.tipoRepositoryFactory = tipo;
    }

    public String[] getTiposElementoGraficoFactory() {
        return TIPOS_ELEMENTO_GRAFICO_FACTORY;
    }

    public void setTipoElementoGraficoFactory(String tipo) {
        this.tipoElementoGraficoFactory = tipo;
    }

    public String[] getTiposRodadaFactory() {
        return TIPOS_RODADA_FACTORY;
    }

    public void setTipoRodadaFactory(String tipo) {
        this.tipoRodadaFactory = tipo;
    }
}
