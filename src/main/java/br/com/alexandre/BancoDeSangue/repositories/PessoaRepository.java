package br.com.alexandre.BancoDeSangue.repositories;

import br.com.alexandre.BancoDeSangue.entities.Pessoa;
import br.com.alexandre.BancoDeSangue.repositories.rowMapper.PessoaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PessoaRepository {

    private static final Integer TIMEOUT = 60;

    private final String CALL_REGISTRA_PESSOA_PRC = "CALL banco_de_sangue.registra_pessoa_prc(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String CALL_BUSCA_PESSOA_POR_CPF = "CALL banco_de_sangue.busca_pessoa_por_cpf(?)";

    private JdbcTemplate jdbc;

    @Autowired
    public PessoaRepository(@Qualifier("bancoDeSangueJdbc") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static void closeJdbcConnection(JdbcTemplate jdbc) {
        try {
            jdbc.getDataSource().getConnection().close();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public void registrarPessoa(Pessoa pessoa) {
        String query = CALL_REGISTRA_PESSOA_PRC;

        try {
            jdbc.setQueryTimeout(TIMEOUT);
            jdbc.execute(query, (CallableStatementCallback<Object>) cs -> {
                System.out.println("QUERY: " + query + " - NOME DA PESSOA: " + pessoa.getNome());

                int index = 1;
                cs.setString(index++, pessoa.getNome());
                cs.setString(index++, pessoa.getCpf());
                cs.setString(index++, pessoa.getRg());
                cs.setDate(index++, pessoa.getFormatedDate());
                cs.setString(index++, pessoa.getSexo().getValueChar());
                cs.setString(index++, pessoa.getMae());
                cs.setString(index++, pessoa.getPai());
                cs.setString(index++, pessoa.getEmail());
                cs.setString(index++, pessoa.getEndereco().getCep());
                cs.setString(index++, pessoa.getEndereco().getEndereco());
                cs.setInt(index++, pessoa.getEndereco().getNumero());
                cs.setString(index++, pessoa.getEndereco().getBairro());
                cs.setString(index++, pessoa.getEndereco().getCidade());
                cs.setString(index++, pessoa.getEndereco().getEstado());
                cs.setString(index++, pessoa.getTelefoneFixo());
                cs.setString(index++, pessoa.getCelular());
                cs.setDouble(index++, pessoa.getAltura());
                cs.setDouble(index++, pessoa.getPeso());
                cs.setString(index++, pessoa.getTipoSanguineo().getValue());

                var startTime = System.currentTimeMillis();
                cs.execute();
                var time = System.currentTimeMillis() - startTime;
                System.out.println("TEMPO DE RESPOSTA DO BANCO DE DADOS: " + time + " MILISSEGUNDOS");

                return "";
            });
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            closeJdbcConnection(jdbc);
        }
    }

    public List<Pessoa> buscaPessoas(String cpf) {
        String query = CALL_BUSCA_PESSOA_POR_CPF;
        List<Pessoa> pessoas = new ArrayList<>();
        boolean isCpf = !cpf.isEmpty();

        try {
            jdbc.setQueryTimeout(TIMEOUT);
            pessoas = jdbc.execute(query, (CallableStatementCallback<List<Pessoa>>) cs -> {
                if (isCpf) System.out.println("QUERY: " + query.replace("?", cpf));
                else System.out.println("QUERY: " + query);

                cs.setString(1, (isCpf) ? cpf : null);

                var startTime = System.currentTimeMillis();
                cs.execute();
                var time = System.currentTimeMillis() - startTime;
                System.out.println("TEMPO DE RESPOSTA DO BANCO DE DADOS: " + time + " MILISSEGUNDOS");

                List<Pessoa> pessoaList = new ArrayList<>();
                try (ResultSet resultSet = cs.getResultSet()) {
                    if (resultSet != null) pessoaList = new RowMapperResultSetExtractor<>(
                            new PessoaRowMapper())
                            .extractData(resultSet).stream().toList();
                }

                System.out.println("QUANTIDADE RETORNADA DA PROCEDURE: " + pessoaList.size());

                return pessoaList;
            });
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            closeJdbcConnection(jdbc);
        }

        return pessoas;
    }


}
