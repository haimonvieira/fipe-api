package br.com.alura.fipe.models;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    /**
     * Está classe obtem os dados do arquivo .json se comunicando atráves da URL da API e retornando em formato
     * de .json
     * @param enderecoApi
     * @return
     */

    public String obterDados(String enderecoApi){

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(enderecoApi))
                .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Retorna o json
        return response.body();

    }

}
