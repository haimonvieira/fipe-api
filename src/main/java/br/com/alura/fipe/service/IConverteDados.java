package br.com.alura.fipe.service;

import java.util.List;

/**
 * Interface para implementar métodos genéricos para
 * converter os dados em Strings e em uma lista de Strings
 */
public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);
    <T> List<T> converterParaLista(String json, Class<T> classe);
}
