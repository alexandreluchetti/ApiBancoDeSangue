package br.com.alexandre.BancoDeSangue.dataprovider;

import br.com.alexandre.BancoDeSangue.core.entities.PersonDB;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BancoDeSangueRepository extends JpaRepository<PersonDB, Long> {

    @Transactional
    @Procedure(value = "banco_de_sangue.busca_pessoa_por_cpf")
    List<PersonDB> getPeople(String cpf);

    PersonDB findPeopleByCpf(String cpf);

    @Procedure(value = "banco_de_sangue.registra_pessoa_prc")
    void register(
            String name,
            String cpf,
            String rg,
            Date birthdate,
            String sex,
            String mother,
            String father,
            String email,
            String cep,
            String address,
            Integer number,
            String neighborhood,
            String city,
            String state,
            String homephone,
            String cellphone,
            Double height,
            Double weight,
            String bloodType
    );

}
