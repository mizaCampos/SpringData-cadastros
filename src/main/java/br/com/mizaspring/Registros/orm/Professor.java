package br.com.mizaspring.Registros.orm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professores")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String nome;
    @Column(nullable = false, unique = true)
    private String prontuario;

    //fetching
    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas;

    @Deprecated // só para indiciar que nós não a usaremos muito, ou ela sera usada por outras bibliotecas externas
    public Professor(){ }

    public Professor(String nome, String prontuario) {
        this.nome = nome;
        this.prontuario = prontuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    //ON REMOVE SET NULL
    @PreRemove
    public void atualizarDisciplinasOnDelete(){
        System.out.println("***** AtualizarDisciplinasOnDelete");
        for(Disciplina disciplina : this.getDisciplinas()){
            disciplina.setProfessor(null);
        }
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", prontuario='" + prontuario + '\'' +
                '}';
    }
}
