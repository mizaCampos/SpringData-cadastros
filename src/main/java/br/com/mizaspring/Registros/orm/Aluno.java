package br.com.mizaspring.Registros.orm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    private Integer idade;

    @ManyToMany(mappedBy = "alunos")
    List<Disciplina>disciplinas;

    public Aluno(){ }

    public Aluno(long id, String nome, Integer idade, List<Disciplina> disciplinas) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.disciplinas = disciplinas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade='" + idade + '\'' +
                ", disciplinas=" + disciplinas +
                '}';
    }
}
