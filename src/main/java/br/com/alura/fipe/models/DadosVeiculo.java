package br.com.alura.fipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Armazenando dados do veículo escolhido e utilizando @JsonAlias para marcar onde deve buscar o parâmetro
 * e @JsonIgnoreProperties para ignorar outros parâmetros não utilizados
 * @param valor
 * @param marca
 * @param modelo
 * @param anoModelo
 * @param combustivel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(@JsonAlias("Valor") String valor, @JsonAlias("Marca") String marca,
                           @JsonAlias("Modelo") String modelo,
                           @JsonAlias("AnoModelo") Integer anoModelo,
                           @JsonAlias("Combustivel") String combustivel) {

    @Override
    public String toString() {
        return "Valor: " + valor +
                ", Marca: " + marca +
                ", Modelo: " + modelo +
                ", Ano do modelo: " + anoModelo +
                ", Combustível: " + combustivel;
    }
}
