package br.com.alexandre.BancoDeSangue.service;

import br.com.alexandre.BancoDeSangue.controller.dto.PessoaDto;
import br.com.alexandre.BancoDeSangue.entities.Pessoa;
import br.com.alexandre.BancoDeSangue.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    private static List<Pessoa> pessoasStatic;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void registraPessoa(Pessoa pessoa) {
        pessoaRepository.registrarPessoa(pessoa);
    }

    public List<Pessoa> buscaPessoa(String cpf) {
        pessoasStatic = pessoaRepository.buscaPessoas(cpf);
        return pessoasStatic;
    }

    public List<PessoaDto> buscaPessoaDto(String cpf) {
        List<Pessoa> pessoas = (pessoasStatic.isEmpty()) ? buscaPessoa(cpf) : pessoasStatic;
        List<PessoaDto> pessoaDtos = new ArrayList<>();
        pessoas.forEach( pessoa -> {
            pessoaDtos.add(pessoa.toDto());
        });
        return pessoaDtos;
    }

    public Map<String, Integer> candidatosPorEstado() {
        List<Pessoa> pessoas = (pessoasStatic.isEmpty()) ? buscaPessoa("") : pessoasStatic;
    }

}
