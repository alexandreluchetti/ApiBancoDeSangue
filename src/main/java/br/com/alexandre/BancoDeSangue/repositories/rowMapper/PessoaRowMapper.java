package br.com.alexandre.BancoDeSangue.repositories.rowMapper;

import br.com.alexandre.BancoDeSangue.entities.Address;
import br.com.alexandre.BancoDeSangue.entities.Person;
import br.com.alexandre.BancoDeSangue.entities.SexEnum;
import br.com.alexandre.BancoDeSangue.entities.BloodTypeEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address(
                rs.getString("cep"),
                rs.getString("endereco"),
                rs.getInt("numero"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getString("estado")
        );

        return new Person(
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("rg"),
                rs.getString("dataNascimento"),
                SexEnum.getEnum(rs.getString("sexo")),
                rs.getString("mae"),
                rs.getString("pai"),
                rs.getString("email"),
                address,
                rs.getString("telefoneFixo"),
                rs.getString("celular"),
                rs.getDouble("altura"),
                rs.getDouble("peso"),
                BloodTypeEnum.getEnum(rs.getString("tipoSanguineo"))
        );
    }

}
