package br.com.mizaspring.Registros.service;

import br.com.mizaspring.Registros.orm.Disciplina;
import br.com.mizaspring.Registros.orm.Professor;
import br.com.mizaspring.Registros.repository.DisciplinaRepository;
import br.com.mizaspring.Registros.repository.ProfessorRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.persistence.PreRemove;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudDisciplinaService {

    private DisciplinaRepository disciplinaRepository;
    private ProfessorRepository professorRepository;

    //injeção de dependencia
    //contrutor de serviço
    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository){
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;

    }

    public void menu(Scanner scanner){
        Boolean isTrue = true;

        while(isTrue){
            System.out.println("Qual ação voce quer executar");
            System.out.println("0 - voltar ao menu anterior");
            System.out.println("1 - Cadastrar nova Disciplina");
            System.out.println("2 - Atualizar uma Disciplina");
            System.out.println("3 - Visualizar todas Disciplinas");
            System.out.println("4 - deletar uma Disciplina");

            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.visualizar();
                    break;
                case 4:
                    this.deletar(scanner);
                    break;
                default:
                    isTrue = false;
                    break;

            }
        }

        System.out.println();
   }

   private void cadastrar(Scanner scanner){
       System.out.println("Digite o nome da disciplina: ");
       String nome = scanner.next();

       System.out.println("Digite o Semestre: ");
       Integer semestre = scanner.nextInt();

       System.out.println("Informe o ID do Professor: ");
       Long professorId = scanner.nextLong();

       Optional<Professor>optional = professorRepository.findById(professorId);
       if(optional.isPresent()){
           Professor professor = optional.get();
           Disciplina disciplina = new Disciplina(nome, semestre,professor);
           disciplinaRepository.save(disciplina);
           System.out.println("Dados Cadastrados");
       }

   }

   private void atualizar(Scanner scanner){
       System.out.println("Digite o Id da Disciplina: ");
       Long id = scanner.nextLong();

       Optional<Disciplina> optional = this.disciplinaRepository.findById(id);

       if(optional.isPresent()){

           System.out.println("Digite o nome da Disciplina: ");
           String nome = scanner.next();

           System.out.println("Digite o semestre: ");
           Integer semestre = scanner.nextInt();

           Disciplina disciplina = optional.get();
           disciplina.setNome(nome);
           disciplina.setSemestre(semestre);
           disciplinaRepository.save(disciplina);
       }

   }

    private void visualizar(){
        Iterable<Disciplina> disciplinas = this.disciplinaRepository.findAll();
        for (Disciplina disciplina : disciplinas ){
            System.out.println(disciplina);
        }

        //lambda function
        //    professores.forEach(professor -> {
        //        System.out.println(professor);
        //    });
        System.out.println();
    }

    private void deletar(Scanner scanner){
        System.out.println("Digite o Id da Disciplina a ser deletada: ");
        Long id = scanner.nextLong();
        try {
            this.disciplinaRepository.deleteById(id); //lançara uma exception se não achar o ID passado na tabela
        }catch (Exception e){
            System.out.println("Não foi possivel encontrar ou deletar o ID informado!");
        }
        System.out.println("Disciplina Deletada!\n");

    }

    //ON REMOVE SET NULL

   // public void atualizarDisciplinasOnDelete(){
     //   System.out.println("***** AtualizarDisciplinasOnDelete");
      //  for(Disciplina disciplina : this.getDisciplinas()){
          //  disciplina.setProfessor(null);
       // }
    //}
}
