package br.com.alexandre.BancoDeSangue.entrypoint.getAvarageBMIPerDecade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record AvarageBMIPerDecadeResponseDto(

        @JsonProperty("mediaIMCPorDecada")
        Map<String, Double> map

) {
}
