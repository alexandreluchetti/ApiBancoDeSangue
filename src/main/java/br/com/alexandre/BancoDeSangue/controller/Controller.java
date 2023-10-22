package br.com.alexandre.BancoDeSangue.controller;

import br.com.alexandre.BancoDeSangue.controller.dto.PessoaDto;
import br.com.alexandre.BancoDeSangue.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
        try {
            for (PessoaDto pessoaDto : pessoaDtoList) {
                service.registraPessoa(pessoaDto.toPessoa());
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @GetMapping(path = "/pessoas")
    @Operation(summary = "Operacao para buscar uma lista de pessoas no banco de dados")
    public List<PessoaDto> buscaPessoas() {
        List<PessoaDto> pessoaDtos = new ArrayList<>();
        try {
            pessoaDtos = service.buscaPessoaDto("");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return pessoaDtos;
    }

    @GetMapping(path = "/pessoas/estados")
    @Operation(summary = "Operacao para buscar a quantidade de candidatos em cada estado do Brasil")
    public Map<String, Integer> candidatosPorEstado() {
        Map<String, Integer> canditosPorEstado = new HashMap<>();
        try {
            canditosPorEstado = service.candidatosPorEstado();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return canditosPorEstado;
    }

    @GetMapping(path = "/percentual/obesos/sexo")
    @Operation(summary = "Operacao para buscar o percentual de obesos por sexo")
    public Map<String, Double> percentualDeObesosPorSexo() {
        Map<String, Double> percentualDeObesosPorSexo = new HashMap<>();
        try {
            percentualDeObesosPorSexo = service.percentualDeObesosPorSexo();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return percentualDeObesosPorSexo;
    }

    @GetMapping(path = "/media/idade/tiposanguineo")
    @Operation(summary = "Operacao para buscar idade media por cada tipo sanguineo")
    public Map<String, Double> mediaIdadePorTipoSanguineo() {
        Map<String, Double> mediaIdadePorTipoSanguineo = new HashMap<>();
        try {
            mediaIdadePorTipoSanguineo = service.mediaIdadePorTipoSanguineo();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mediaIdadePorTipoSanguineo;
    }

    @GetMapping(path = "/quantidade/doadores/tiposanguineo/receptor")
    @Operation(summary = "Operacao para buscar a quantidade de doadores para cada tipo sanguineo receptor")
    public Map<String, Integer> quantidadeDoadoresParaCadaTipoSanguineoReceptor() {
        Map<String, Integer> doadoresPorReceptor = new HashMap<>();
        try {
            doadoresPorReceptor = service.quantidadeDoadoresParaCadaTipoSanguineoReceptor();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return doadoresPorReceptor;
    }

    @GetMapping(path = "/quantidade/receptores/tiposanguineo/doador")
    @Operation(summary = "Operacao para buscar a quantidade de receptores para cada tipo sanguineo doador")
    public Map<String, Integer> quantidadeReceptoresParaCadaTipoSanguineoDoador() {
        Map<String, Integer> receptoresPorDoador = new HashMap<>();
        try {
            receptoresPorDoador = service.quantidadeReceptoresParaCadaTipoSanguineoDoador();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return receptoresPorDoador;
    }

    @GetMapping(path = "/media/imc/decada")
    @Operation(summary = "Operacao para buscar a media de IMC por cada decada")
    public Map<String, Double> imcMedioPorDecada() {
        Map<String, Double> imcMedioPorDecada = new HashMap<>();
        try {
            imcMedioPorDecada = service.imcMedioPorDecada();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return imcMedioPorDecada;
    }

}
