package br.edu.iff.forca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import br.edu.iff.forca.domain.Tema;
import br.edu.iff.forca.factory.TemaFactory;
import br.edu.iff.forca.repository.RepositoryException;
import br.edu.iff.forca.service.PalavraAppService;

public class CsvLoader {

    public static void carregar(String caminhoArquivo, TemaFactory temaFactory, PalavraAppService palavraService) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            
            reader.readLine(); // pula o cabeçalho

            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");

                if (partes.length != 2) continue;

                String nomeTema = partes[0].trim();
                String nomePalavra = partes[1].trim();

                if (nomeTema.isEmpty() || nomePalavra.isEmpty()) continue;

                try {
                    Tema tema = temaFactory.getTema(nomeTema);
                    palavraService.novaPalavra(nomePalavra, tema.getId());
                } catch (RepositoryException e) {
                    System.err.println("Erro ao inserir palavra '" + nomePalavra + "': " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}