package br.com.alexandre.BancoDeSangue.entrypoint.getObesityPercentagePerSex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record ObesityPercentagePerSexResponseDto(

        @JsonProperty("percentualObesidadePorSexo")
        Map<String, Double> map
        
) {
}
