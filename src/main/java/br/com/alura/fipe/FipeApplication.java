package br.com.alura.fipe;

import br.com.alura.fipe.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class FipeApplication implements CommandLineRunner {

	public static void main(String[] args){
		SpringApplication.run(FipeApplication.class, args);
	}


	@Override
	public void run(String... args) throws IOException {
		Scanner scanner = new Scanner(System.in);

		Main main = new Main();
		main.exibirMenu();
		System.out.println("\nDeseja sair? (S/N)");
		System.out.printf("> ");
		String op = scanner.nextLine().toLowerCase();

		while (op.contains("n")){

			main.exibirMenu();
			System.out.println("\nDeseja sair? (S/N)");
			System.out.printf("> ");
			op = scanner.nextLine();

		}

		System.out.println("\nSaindo...");



	}
}
