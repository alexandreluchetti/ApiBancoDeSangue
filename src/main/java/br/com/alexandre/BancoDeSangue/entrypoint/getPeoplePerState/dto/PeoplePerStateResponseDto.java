package br.com.alexandre.BancoDeSangue.entrypoint.getPeoplePerState.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record PeoplePerStateResponseDto(

        @JsonProperty("pessoasPorEstado")
        Map<String, Integer> map

) {
}
