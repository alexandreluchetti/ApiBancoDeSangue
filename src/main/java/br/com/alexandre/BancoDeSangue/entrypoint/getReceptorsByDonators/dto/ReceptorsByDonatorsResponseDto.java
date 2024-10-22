package br.com.alexandre.BancoDeSangue.entrypoint.getReceptorsByDonators.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record ReceptorsByDonatorsResponseDto(

        @JsonProperty("receptoresParaCadaTipoSanguineoDoador")
        Map<String, Integer> map

) {
}
