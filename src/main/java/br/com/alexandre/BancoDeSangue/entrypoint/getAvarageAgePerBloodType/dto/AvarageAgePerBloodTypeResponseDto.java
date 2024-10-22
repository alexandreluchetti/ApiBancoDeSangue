package br.com.alexandre.BancoDeSangue.entrypoint.getAvarageAgePerBloodType.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record AvarageAgePerBloodTypeResponseDto(

        @JsonProperty("mediaIdadePorTipoSanguineo")
        Map<String, Double> map

) {
}
