package br.com.alexandre.BancoDeSangue.entrypoint.getDonatorsByReceptors.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record DonatorsByReceptorsResponseDto(

        @JsonProperty("doadoresParaCadaTipoSanguineoReceptor")
        Map<String, Integer> map

) {
}
