package br.com.alura.fipe.main;

import br.com.alura.fipe.models.ConsumoApi;
import br.com.alura.fipe.models.DadosModelos;
import br.com.alura.fipe.models.DadosVeiculo;
import br.com.alura.fipe.service.ConverteDados;
import br.com.alura.fipe.models.DadosMarcas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    /**
     * Classe principal onde é feita toda a lógica usando o Spring Framework e os dados do .json
     */
    private Scanner scanner = new Scanner(System.in);
    private ConverteDados converteDados = new ConverteDados();
    private final String URL = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumoApi = new ConsumoApi();

    /**
     * Método para exibir o menu e executar as operações
     */
    public void exibirMenu() throws IOException {

        System.out.println("\nInsira qual veículo deseja pesquisar");
        System.out.println("""
                Carros
                Motos
                Caminhoes
                """);
        System.out.printf("> ");
        String pesquisa = scanner.nextLine().toLowerCase();
        var endereco = "";

        if (pesquisa.contains("carros")) {
            endereco = URL + pesquisa + "/marcas/";
        } else if (pesquisa.contains("motos")) {
            endereco = URL + pesquisa + "/marcas/";
        } else if (pesquisa.contains("caminhoes")) {
            endereco = URL + pesquisa + "/marcas/";
        }

        //Armazena os dados em formato de texto
        String json = consumoApi.obterDados(endereco);

        //Transforma os dados obtidos em uma lista de marcas
        var marcas = converteDados.converterParaLista(json, DadosMarcas.class);
        marcas.stream()
                .sorted(Comparator.comparing(DadosMarcas::nome))
                .forEach(System.out::println);


        System.out.println("\nInsira o código do modelo");
        System.out.printf("> ");
        int codigoModelo = scanner.nextInt();
        scanner.nextLine();

        endereco = endereco + codigoModelo + "/modelos/";
        json = consumoApi.obterDados(endereco);

        //Armazeno os modelos em uma lista, convertendo os dados do json para o modelo da classe
        //DadosModelos
        var listaDeModelos = converteDados.obterDados(json, DadosModelos.class);

        System.out.println("\nModelos da marca: ");
        listaDeModelos.modelos().stream()
                .sorted(Comparator.comparing(DadosMarcas::nome))
                .forEach(m -> System.out.println(m.nome()));

        System.out.println("\nInsira um trecho do modelo para filtrar");
        System.out.printf("> ");
        var veiculo = scanner.nextLine();

        List<DadosMarcas> listaDeModelosFiltrados = listaDeModelos.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(veiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.printf("\nModelos filtrados\n");
        listaDeModelosFiltrados.forEach(System.out::println);

        System.out.println("\nInsira o código do modelo para mostrar os valores");
        System.out.printf("> ");
        var codigoModeloFiltrado = scanner.nextLine();

        endereco = endereco + codigoModeloFiltrado + "/anos/";
        json = consumoApi.obterDados(endereco);

        //Cria lista de anos que o modelo do veículo tem
        List<DadosMarcas> listaDeAnos = converteDados.converterParaLista(json, DadosMarcas.class);
        List<DadosVeiculo> listaDeVeiculos = new ArrayList<>();
        for (int i = 0; i < listaDeAnos.size(); i++) {

            //Inserir os dados do modelo para cada ano
            var enderecoAno = endereco + listaDeAnos.get(i).codigo();
            json = consumoApi.obterDados(enderecoAno);
            DadosVeiculo dadosVeiculo = converteDados.obterDados(json, DadosVeiculo.class);
            listaDeVeiculos.add(dadosVeiculo);

        }

        //Listando por ano do veículo
        System.out.println("\nTodos os veiculos filtrados");
        listaDeVeiculos.stream()
                .sorted(Comparator.comparing(DadosVeiculo::anoModelo))
                .forEach(System.out::println);

        File file = new File("veiculos.txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(listaDeVeiculos.toString());
        fileWriter.close();



    }


}
