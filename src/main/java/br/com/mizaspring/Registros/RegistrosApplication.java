package br.com.mizaspring.Registros;

import br.com.mizaspring.Registros.orm.Professor;
import br.com.mizaspring.Registros.repository.ProfessorRepository;
import br.com.mizaspring.Registros.service.CrudAlunoService;
import br.com.mizaspring.Registros.service.CrudDisciplinaService;
import br.com.mizaspring.Registros.service.CrudProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication

public class RegistrosApplication implements CommandLineRunner {
	private CrudProfessorService professorService;
	private  CrudDisciplinaService disciplinaService;
	private CrudAlunoService alunoService;

	// os objetos passado por parametro sao injetados automaticamente pelo spring
	// porque suas classes possuem a anotação @Service
	public  RegistrosApplication(CrudProfessorService professorService, CrudDisciplinaService disciplinaService, CrudAlunoService alunoService){
		this.professorService = professorService;
		this.disciplinaService = disciplinaService;
		this.alunoService = alunoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RegistrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);

		while(isTrue){
			System.out.println("\nQual entidade voce deseja interagir?");
			System.out.println("0 - sair");
			System.out.println("1 - Professor");
			System.out.println("2 - Disciplina");
			System.out.println("3 - Aluno")
			int opcao = scanner.nextInt();

			switch (opcao){
				case 1:
					this.professorService.menu(scanner);
					break;
				case 2:
					this.disciplinaService.menu(scanner);
				case 3:
					this.alunoService.menu(scanner);
				default:
					isTrue = false;
					break;
			}
		}

	}
}
