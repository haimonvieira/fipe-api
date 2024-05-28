package br.com.alura.fipe.models;

/**
 * Armazena dados da marca do veículo
 * @param codigo
 * @param nome
 */
public record DadosMarcas(String codigo, String nome){

    @Override
    public String toString() {
        return "Código: " + codigo +
                ", Nome: " + nome;
    }
}
