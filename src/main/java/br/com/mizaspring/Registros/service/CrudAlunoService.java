package br.com.mizaspring.Registros.service;

import br.com.mizaspring.Registros.orm.Aluno;
import br.com.mizaspring.Registros.orm.Disciplina;
import br.com.mizaspring.Registros.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Service //precisa colocar essas anotações para o spring criar os objetos e injetalos aonde necessario
public class CrudAlunoService {

    private AlunoRepository alunoRepository;

    public CrudAlunoService(AlunoRepository alunoRepository{
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void menu(Scanner scanner){
        Boolean isTrue = true;


        while (isTrue){
            System.out.println("Qual ação voce quer executar");
            System.out.println("0 - voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo Aluno");
            System.out.println("2 - Atualizar um Aluno");
            System.out.println("3 - Deletar um Aluno");
            System.out.println("4 - Visualizar um Aluno");

            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.visualizar(scanner);
                    break;
                case 4:
                    this.deletar(scanner);
                    break;
                case 5:
                    this.visualizarAluno(scanner);
                    break;
                default:
                    break;
            }
            System.out.println();
        }

        private void cadastrar(Scanner scanner){
            System.out.println("Nome: ");
            String nome = scanner.next();

            System.out.println("Idade: ");
            Integer idade = scanner.nextInt();

            Aluno aluno = new Aluno();
            aluno.setNome(nome);
            aluno.setIdade(idade);
            this.alunoRepository.save(aluno);
            System.out.println("Salvo\n");
        }

        private void atualizar(Scanner scanner){
            System.out.println("Digite o Id do Aluno a ser atualizado: ");
            Long id = scanner.nextLong();

            Optional<Aluno> optional = this.alunoRepository.findById(id);

            if(optional.isPresent()){
                Aluno aluno = optional.get();

                System.out.println("Nome: ");
                String nome = scanner.next();

                System.out.println("Idade");
                Integer idade = scanner.nextInt();

                aluno.setNome(nome);
                aluno.setIdade(idade);
                this.alunoRepository.save(aluno);
                System.out.println("Atualizado!\n");
            }
            else {
                System.out.println("O id do aluno informado: " + id + " é inválido\n");
            }
        }

        private void visualizar(){
            Iterable<Aluno>alunos = this.alunoRepository.findAll();
            for(Aluno aluno : alunos){
                System.out.println(aluno);
            }
            System.out.println();
        }

        private void deletar(Scanner scanner){
            System.out.println("Id: ");
            Long id = scanner.nextLong();
            this.alunoRepository.deleteById(id);
            System.out.println("Aluno Deletado!");
        }

        @Transactional
        private void visualizarAluno(Scanner scanner){
            System.out.println("Digite o Id do Aluno a ser visualizado: ");
            Long id = scanner.nextLong();

            Optional<Aluno>optional = this.alunoRepository.findById(id);

            if(optional.isPresent()){
               Aluno aluno = optional.get();

               System.out.println("- ID " + aluno.getId());
               System.out.println("- Nome: " + aluno.getNome());
               System.out.println("- Idade: " + aluno.getIdade());
               System.out.println("- Disciplinas: [:");

               if(aluno.getDisciplinas() != null){
                   for(Disciplina disciplina : aluno.getDisciplinas()){
                       System.out.println("\t- Disciplina: " + disciplina.getNome());
                       System.out.println("\t- Semestre> " + disciplina.getSemestre());
                       System.out.println();
                   }
               }
               System.out.println("]");
            }
            else {
                System.out.println("O id do aluno informado: " + id + " é inválido");
            }
        }



    }
}
