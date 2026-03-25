package main.java.br.edu.iff.forca;

import java.util.Scanner;

import main.java.br.edu.iff.forca.domain.Item;
import main.java.br.edu.iff.forca.domain.Jogador;
import main.java.br.edu.iff.forca.domain.Rodada;
import main.java.br.edu.iff.forca.domain.Tema;
import main.java.br.edu.iff.forca.domain.letra.Letra;
import main.java.br.edu.iff.forca.factory.JogadorFactory;
import main.java.br.edu.iff.forca.factory.TemaFactory;
import main.java.br.edu.iff.forca.repository.RepositoryException;
import main.java.br.edu.iff.forca.service.PalavraAppService;
import main.java.br.edu.iff.forca.service.RodadaAppService;

public class Main {

    public static void main(String[] args) {

        Aplicacao app = Aplicacao.getSoleInstance();
        app.configurar();

        TemaFactory temaFactory = app.getTemaFactory();
        Tema t1 = temaFactory.getTema("Animais");
        Tema t2 = temaFactory.getTema("Frutas");

        PalavraAppService palavraService = PalavraAppService.getSoleInstance();
        try {
            palavraService.novaPalavra("gato", t1.getId());
            palavraService.novaPalavra("cachorro", t1.getId());
            palavraService.novaPalavra("papagaio", t1.getId());
            palavraService.novaPalavra("banana", t2.getId());
            palavraService.novaPalavra("manga", t2.getId());
            palavraService.novaPalavra("abacaxi", t2.getId());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        JogadorFactory jogadorFactory = app.getJogadorFactory();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        String nomeJogador = scanner.nextLine();
        Jogador jogador = jogadorFactory.getJogador(nomeJogador);

        RodadaAppService rodadaService = RodadaAppService.getSoleInstance();

        do {
            Rodada rodada = rodadaService.novaRodada(jogador.getId());

            System.out.println("\n========== NOVA RODADA ==========");
            System.out.println("Tema: " + rodada.getTema().getNome());

            while (!rodada.encerrou()) {

                exibirBoneco(rodada.getQtdeErros());

                exibirPalavras(rodada);

                System.out.print("Letras erradas: ");
                for (Letra l : rodada.getErradas()) {
                    System.out.print(l.getCodigo() + " ");
                }
                System.out.println();
                System.out.println("Tentativas restantes: " + rodada.getQtdeTentativasRestantes());
                System.out.println("---------------------------------");
                System.out.print("Digite uma letra (ou ! para arriscar todas as palavras): ");
                String entrada = scanner.nextLine().trim().toLowerCase();

                if (entrada.equals("!")) {
                    String[] palavras = new String[rodada.getNumPalavras()];
                    for (int i = 0; i < palavras.length; i++) {
                        System.out.print("Digite a palavra " + (i + 1) + ": ");
                        palavras[i] = scanner.nextLine().trim();
                    }
                    rodada.arriscar(palavras);
                } else if (entrada.length() == 1) {
                    rodada.tentar(entrada.charAt(0));
                } else {
                    System.out.println("Entrada invalida! Digite apenas uma letra ou '!'");
                }
            }

            System.out.println("\n========== FIM DA RODADA ==========");
            exibirBoneco(rodada.getQtdeErros());
            exibirPalavras(rodada);

            if (rodada.descobriu()) {
                System.out.println("Parabens! Voce descobriu todas as palavras!");
            } else {
                System.out.println("Que pena! Voce nao descobriu as palavras.");
                System.out.print("As palavras eram: ");
                for (main.java.br.edu.iff.forca.domain.Palavra p : rodada.getPalavras()) {
                    System.out.print(p.toString() + " ");
                }
                System.out.println();
            }

            System.out.println("Pontos desta rodada: " + rodada.calcularPontos());
            System.out.println("Pontuacao total: " + jogador.getPontuacao());

            rodadaService.salvarRodada(rodada);

            System.out.print("\nJogar novamente? (s/n): ");

        } while (scanner.nextLine().trim().equalsIgnoreCase("s"));

        System.out.println("\nObrigado por jogar, " + jogador.getNome() + "!");
        System.out.println("Pontuacao final: " + jogador.getPontuacao());
        scanner.close();
    }
    
    private static void exibirPalavras(Rodada rodada) {
        Item[] itens = rodada.getItens();
        System.out.println();
        for (int i = 0; i < itens.length; i++) {
            System.out.print("Palavra " + (i + 1) + ": [ ");
            for (int j = 0; j < itens[i].getPalavra().getTamanho(); j++) {
                Letra letraNaPosicao = itens[i].getPalavra().getLetra(j);
                boolean descoberta = false;
                for (Letra l : itens[i].getLetrasDescobertas()) {
                    if (l.getCodigo() == letraNaPosicao.getCodigo()) {
                        descoberta = true;
                        break;
                    }
                }
                if (descoberta) {
                    System.out.print(letraNaPosicao.getCodigo() + " ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println("]");
        }
    }

    private static void exibirBoneco(int erros) {
        System.out.println();
        System.out.println("  +---+");
        System.out.println("  |   |");
        System.out.println("  |   " + (erros >= 1 ? "O" : " "));
        System.out.println("  |  " + (erros >= 7 ? "/" : " ") + (erros >= 6 ? "|" : " ") + (erros >= 8 ? "\\" : " "));
        System.out.println("  |  " + (erros >= 9 ? "/" : " ") + " " + (erros >= 10 ? "\\" : " "));
        System.out.println("  |");
        System.out.println("=====");
        System.out.println("Erros: " + erros + "/10");
    }
}