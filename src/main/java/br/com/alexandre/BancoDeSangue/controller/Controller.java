package br.com.alexandre.BancoDeSangue.controller;

import br.com.alexandre.BancoDeSangue.controller.dto.PessoaDto;
import br.com.alexandre.BancoDeSangue.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping(path = "/envia/pessoas")
    @Operation(summary = "Operacao para enviar uma lista de pesoas para o banco de sangue")
    public void salvaPessoas(@RequestBody List<PessoaDto> pessoaDtoList) {
        for (PessoaDto pessoaDto : pessoaDtoList) {
            service.registraPessoa(pessoaDto.toPessoa());
        }
    }

    @GetMapping(path = "/pessoas")
    @Operation(summary = "Operacao para buscar uma lista de pessoas no banco de dados")
    public List<PessoaDto> buscaPessoas() {
        return service.buscaPessoaDto("");
    }

    @GetMapping(path = "/pessoas/estados")
    @Operation(summary = "Operacao para buscar a quantidade de candidatos em cada estado do Brasil")
    public Map<String, Integer> candidatosPorEstado() {
        return service.candidatosPorEstado();
    }

    @GetMapping(path = "/percentual/obesos/sexo")
    @Operation(summary = "Operacao para buscar o percentual de obesos por sexo")
    public Map<String, Double> percentualDeObesosPorSexo() {
        return service.percentualDeObesosPorSexo();
    }

    @GetMapping(path = "/media/idade/tiposanguineo")
    @Operation(summary = "Operacao para buscar idade media por cada tipo sanguineo")
    public Map<String, Double> mediaIdadePorTipoSanguineo() {
        return service.mediaIdadePorTipoSanguineo();
    }

}
