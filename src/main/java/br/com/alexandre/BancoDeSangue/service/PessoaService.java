package br.com.alexandre.BancoDeSangue.service;

import br.com.alexandre.BancoDeSangue.controller.dto.PessoaDto;
import br.com.alexandre.BancoDeSangue.entities.Pessoa;
import br.com.alexandre.BancoDeSangue.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void registraPessoa(Pessoa pessoa) {
        pessoaRepository.registrarPessoa(pessoa);
    }

    public List<PessoaDto> buscaPessoa(String cpf) {
        List<Pessoa> pessoas = pessoaRepository.buscaPessoas(cpf);
        List<PessoaDto> pessoaDtos = new ArrayList<>();
        pessoas.forEach( pessoa -> {
            pessoaDtos.add(pessoa.toDto());
        });
        return pessoaDtos;
    }

}
