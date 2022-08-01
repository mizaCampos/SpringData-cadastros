package br.com.mizaspring.Registros.repository;

import br.com.mizaspring.Registros.orm.Disciplina;
import org.springframework.data.repository.CrudRepository;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {
}
