package br.com.alexandre.BancoDeSangue.controller;

import br.com.alexandre.BancoDeSangue.controller.dto.PessoaDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
//@Tag(name = "Banco de Sangue", description = "Controller das operações referentes ao Banco de Sangue")
public class Controller {

    @PostMapping(path = "/envia/pessoas")
    @Operation(summary = "Operacao para enviar uma lista de pesoas para o banco de sangue")
    public void enviaPessoas(@RequestBody List<PessoaDto> pessoaDtoList) {//receber varios de uma vez
        pessoaDtoList.forEach( pessoaDto -> {
            System.out.println("\n" + pessoaDto.toString());
        });
    }

}
