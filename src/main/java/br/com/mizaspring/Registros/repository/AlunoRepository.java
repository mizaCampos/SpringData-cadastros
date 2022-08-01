package br.com.mizaspring.Registros.repository;

import br.com.mizaspring.Registros.orm.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
}
