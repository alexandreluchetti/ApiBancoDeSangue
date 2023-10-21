package br.com.alexandre.BancoDeSangue.controller.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record PessoaDto(

//        @Schema(example = "Juca Bala", required = true)
        String nome,
//        @Schema(example = "00011122233", required = true)
        String cpf,
//        @Schema(example = "001112223", required = true)
        String rg,
//        @Schema(example = "dd/mm/aaaa", required = true)
        @JsonAlias({"data_nasc"})
        String dataNascimento, //data_nasc
//        @Schema(example = "Masculino ou Feminino", required = true)
        String sexo,
//        @Schema(example = "Bia Bala", required = true)
        String mae,
//        @Schema(example = "Paulo Bala", required = true)
        String pai,
//        @Schema(example = "jucabala@gmail.com", required = true)
        String email,
//        @Schema(example = "00111-222", required = true)
        String cep,
//        @Schema(example = "Rua dos pilares", required = true)
        String endereco,
//        @Schema(example = "135", required = true)
        Integer numero,
//        @Schema(example = "Jardim das couves", required = true)
        String bairro,
//        @Schema(example = "Araraquara", required = true)
        String cidade,
//        @Schema(example = "SP", required = true)
        String estado,
//        @Schema(example = "11 35874545", required = true)
        @JsonAlias({"telefone_fixo"})
        String telefoneFixo, //telefone_fixo
//        @Schema(example = "11 988571245", required = true)
        String celular,
//        @Schema(example = "1.80", required = true)
        double altura,
//        @Schema(example = "80.2", required = true)
        double peso,
//        @Schema(example = "A+, A-, B+, B-, AB+, AB-, O+ ou O-", required = true)
        @JsonAlias({"tipo_sanguineo"})
        String tipoSanguineo //tipo_sanguineo

) {
}
