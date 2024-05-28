package br.com.alura.fipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Armazena dados dos modelos escolhido pelo usuário em uma lista de DadosMarcas
 * @param modelos
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos(List<DadosMarcas> modelos) {

    @Override
    public String toString() {
        return "Modelos: " + modelos;
    }
}
