package br.com.alexandre.BancoDeSangue.repositories.rowMapper;

import br.com.alexandre.BancoDeSangue.entities.Endereco;
import br.com.alexandre.BancoDeSangue.entities.Pessoa;
import br.com.alexandre.BancoDeSangue.entities.SexoEnum;
import br.com.alexandre.BancoDeSangue.entities.TipoSanguineoEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaRowMapper implements RowMapper<Pessoa> {

    @Override
    public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
        Endereco endereco = new Endereco(
                rs.getString("cep"),
                rs.getString("endereco"),
                rs.getInt("numero"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getString("estado")
        );

        return new Pessoa(
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("rg"),
                rs.getString("dataNascimento"),
                SexoEnum.getEnum(rs.getString("sexo")),
                rs.getString("mae"),
                rs.getString("pai"),
                rs.getString("email"),
                endereco,
                rs.getString("telefoneFixo"),
                rs.getString("celular"),
                rs.getDouble("altura"),
                rs.getDouble("peso"),
                TipoSanguineoEnum.getEnum(rs.getString("tipoSanguineo"))
        );
    }

}
