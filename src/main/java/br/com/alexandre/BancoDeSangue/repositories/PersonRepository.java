package br.com.alexandre.BancoDeSangue.repositories;

import br.com.alexandre.BancoDeSangue.entities.Address;
import br.com.alexandre.BancoDeSangue.entities.Person;
import br.com.alexandre.BancoDeSangue.exceptions.EmptyListException;
import br.com.alexandre.BancoDeSangue.repositories.rowMapper.PessoaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private static final Integer TIMEOUT = 60;

    private final String CALL_PERSON_REGISTRATION_PRC = "CALL banco_de_sangue.registra_pessoa_prc(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String CALL_GET_PERSON_BY_CPF = "CALL banco_de_sangue.busca_pessoa_por_cpf(?)";

    private JdbcTemplate jdbc;

    @Autowired
    public PersonRepository(@Qualifier("bancoDeSangueJdbc") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static void closeJdbcConnection(JdbcTemplate jdbc) {
        try {
            jdbc.getDataSource().getConnection().close();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public void personRegistration(Person person) {
        String query = CALL_PERSON_REGISTRATION_PRC;

        try {
            jdbc.setQueryTimeout(TIMEOUT);
            jdbc.execute(query, (CallableStatementCallback<Object>) cs -> {
                System.out.println("QUERY: " + query + " - NOME DA PESSOA: " + person.getName());

                Address address = person.getAddress();
                
                int index = 1;
                cs.setString(index++, person.getName());
                cs.setString(index++, person.getCpf());
                cs.setString(index++, person.getRg());
                cs.setDate(index++, person.getFormattedDate());
                cs.setString(index++, person.getSex().getValueChar());
                cs.setString(index++, person.getMother());
                cs.setString(index++, person.getFather());
                cs.setString(index++, person.getEmail());
                cs.setString(index++, address.getCep());
                cs.setString(index++, address.getAddress());
                cs.setInt(index++, address.getNumber());
                cs.setString(index++, address.getNeighborhood());
                cs.setString(index++, address.getCity());
                cs.setString(index++, address.getState());
                cs.setString(index++, person.getHomePhone());
                cs.setString(index++, person.getCellphone());
                cs.setDouble(index++, person.getHeight());
                cs.setDouble(index++, person.getWeight());
                cs.setString(index, person.getBloodType().getValue());
                
                executeJdbc(cs);
                return "";
            });
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            closeJdbcConnection(jdbc);
        }
    }

    public List<Person> getPeopleByCpf(String cpf) {
        String query = CALL_GET_PERSON_BY_CPF;

        try {
            jdbc.setQueryTimeout(TIMEOUT);
            return jdbc.execute(query, (CallableStatementCallback<List<Person>>) cs -> {
                System.out.println("QUERY: " + query.replace("?", (cpf == null) ? "null" : cpf));

                if (cpf == null) {
                    cs.setNull(1, Types.NULL);
                } else {
                    cs.setString(1, cpf);
                }
                executeJdbc(cs);

                List<Person> personList = new ArrayList<>();
                try (ResultSet resultSet = cs.getResultSet()) {
                    if (resultSet != null) personList = new RowMapperResultSetExtractor<>(
                            new PessoaRowMapper())
                            .extractData(resultSet).stream().toList();
                }

                System.out.println("QUANTIDADE RETORNADA DA PROCEDURE: " + personList.size());

                return personList;
            });
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            closeJdbcConnection(jdbc);
        }

        throw EmptyListException.noPersonFound();
    }

    private void executeJdbc(PreparedStatement cs) throws SQLException {
        var startTime = System.currentTimeMillis();
        cs.execute();
        var time = System.currentTimeMillis() - startTime;
        System.out.println("TEMPO DE RESPOSTA DO BANCO DE DADOS: " + time + " MILISSEGUNDOS");
    }
    
}
