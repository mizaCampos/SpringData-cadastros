package br.com.mizaspring.Registros.repository;

import br.com.mizaspring.Registros.orm.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
// repositorio de interações entre a entidade professor cujo a chave primaria e do tipo long com a tabela no banco de dados

    

}
