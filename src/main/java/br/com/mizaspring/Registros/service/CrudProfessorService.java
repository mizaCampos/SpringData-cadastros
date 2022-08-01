package br.com.mizaspring.Registros.service;

import br.com.mizaspring.Registros.orm.Disciplina;
import br.com.mizaspring.Registros.orm.Professor;
import br.com.mizaspring.Registros.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Service
@Transactional
public class CrudProfessorService {
    private ProfessorRepository professorRepository; //dependencia da classe CrudProfessorService

    //o spring automaticamente cria um objeto com a interface ProfesseorRepository,
    // e o injeta para nós no construtor da classe atual ==> Injeção de Dependencia
    public CrudProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    //@Transactional
    public void menu(Scanner scanner){
        Boolean isTrue = true;

        while(isTrue){
            System.out.println("Qual ação voce quer executar");
            System.out.println("0 - voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo Professor");
            System.out.println("2 - Atualizar um Professor");
            System.out.println("3 - Visualizar todos os professores");
            System.out.println("4 - deletar um professor");
            System.out.println("5 - visualizar um professor");

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
                case 4:
                    this.deletar(scanner);
                case 5:
                    this.visualizarProfessor(scanner);
                default:
                    isTrue = false;
                     break;

            }
        }

        System.out.println();
    }

    private void cadastrar(Scanner scanner){
        System.out.println("Digite o nome do professor: ");
        String nome = scanner.next();

        System.out.println("Digite o prontuario do professor: ");
        String prontuario = scanner.next();

        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor salvo  no banco");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Digite o Id do Professor a ser atualizado: ");
        Long id = scanner.nextLong();

        Optional<Professor> Optional =  this.professorRepository.findById(id);

        //se o hibernate conseguiu achar uma tupla/registro na tabela de professores com id igual ao passado pelo usuario
        if(Optional.isPresent()){

            System.out.println("Digite o nome do professor: ");
            String nome = scanner.next();

            System.out.println("Digite o prontuario do professor: ");
            String prontuario = scanner.next();

            Professor professor = Optional.get(); //recuperando do banco de dados
            professor.setNome(nome);
            professor.setProntuario(prontuario);
            professorRepository.save(professor);  //atualiza (persiste) o objeto / registro/tupla no banco de dados
        }
        else{
            System.out.println("O id do professor informado: " + id + " é inválido\n");
        }

    }

    private void atualizaSemFindById(Scanner scanner){
        System.out.println("Digite o Id do Professor a ser atualizado: ");
        Long id = scanner.nextLong();

        System.out.println("Digite o nome do Professor ");
        String nome = scanner.next();

        System.out.println("Digite o prontuario do Professor: ");
        String prontuario = scanner.next();

        Professor professor = new Professor();
        professor.setId(id);
        professor.setNome(nome);
        professor.setProntuario(prontuario);

        professorRepository.save(professor);
        System.out.println("Professor atualizado com sucesso!!!\n");
    }

    private void visualizar(){
        Iterable<Professor> professores = this.professorRepository.findAll();
        for (Professor professor : professores){
            System.out.println(professor);
        }

        //lambda function
    //    professores.forEach(professor -> {
    //        System.out.println(professor);
    //    });
        System.out.println();
    }

    private void deletar(Scanner scanner){
        System.out.println("Digite o Id do Professor a ser deletado: ");
        Long id = scanner.nextLong();
        try {
            this.professorRepository.deleteById(id); //lançara uma exception se não achar o ID passado na tabela
        }catch (Exception e){
            System.out.println("Não foi possivel encontrar ou deletar o ID informado!");
        }
        System.out.println("Professor Deletado!\n");

    }

    //@Transactional
    private void visualizarProfessor(Scanner scanner){
        System.out.println("Id do Professor: ");
        Long id = scanner.nextLong();

        Optional<Professor> optional = professorRepository.findById(id);
        if(optional.isPresent()){
            Professor professor = optional.get();

            System.out.println("Professor: {");
            System.out.println("ID: " + professor.getId());
            System.out.println("Nome: " + professor.getNome());
            System.out.println("Prontuario: " + professor.getProntuario());
            System.out.println("Disciplinas:[");
            for(Disciplina disciplina : professor.getDisciplinas()) {
                System.out.println("\tId: " + disciplina.getId());
                System.out.println("\tNome: " + disciplina.getNome());
                System.out.println("\tSemestre: " + disciplina.getSemestre());
                System.out.println();
            }
            System.out.println("]\n}");
        }
    }




}
