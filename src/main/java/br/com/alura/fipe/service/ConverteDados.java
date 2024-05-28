package br.com.alura.fipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {

    /**
     * Está classe converte os dados do arquivo json obtido atráves da API para String.
     * Ambos os métodos são genéricos
     */

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T obterDados(String json, Class<T> classe) {

        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *Esse método genérico converte o arquivo .json em uma lista de Strings
     * @param json
     * @param classe
     * @return
     * @param <T>
     */
    @Override
    public <T> List<T> converterParaLista(String json, Class<T> classe) {

        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);

        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
