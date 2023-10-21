package br.com.alexandre.BancoDeSangue.controller;

import br.com.alexandre.BancoDeSangue.controller.dto.PessoaDto;
import br.com.alexandre.BancoDeSangue.repositories.PessoaRepository;
import br.com.alexandre.BancoDeSangue.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private PessoaService pessoaService;

    @Autowired
    public Controller(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping(path = "/envia/pessoas")
    @Operation(summary = "Operacao para enviar uma lista de pesoas para o banco de sangue")
    public void salvaPessoas(@RequestBody List<PessoaDto> pessoaDtoList) {
        for (PessoaDto pessoaDto : pessoaDtoList) {
            pessoaService.registraPessoa(pessoaDto.toPessoa());
        }
    }

    @GetMapping(path = "/busca/pessoas")
    @Operation(summary = "Operacao para buscar uma lista de pessoas no banco de dados")
    public List<PessoaDto> buscaPessoas() {
        return pessoaService.buscaPessoaDto("");
    }

    @GetMapping(path = "/busca/pessoas/estados")
    @Operation(summary = "")
    public Map<String, Integer> candidatosPorEstado() {

    }

}
