package br.com.alexandre.BancoDeSangue.controller.dto;

import br.com.alexandre.BancoDeSangue.entities.Endereco;
import br.com.alexandre.BancoDeSangue.entities.Pessoa;
import br.com.alexandre.BancoDeSangue.entities.SexoEnum;
import br.com.alexandre.BancoDeSangue.entities.TipoSanguineoEnum;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record PessoaDto(

        @Schema(example = "Juca Bala", required = true)
        String nome,
        @Schema(example = "00011122233", required = true)
        String cpf,
        @Schema(example = "001112223", required = true)
        String rg,
        @JsonAlias({"data_nasc"})
        @Schema(example = "10/07/1992", required = true)
        String dataNascimento,
        @Schema(example = "Masculino", required = true)
        String sexo,
        @Schema(example = "Bia Bala", required = true)
        String mae,
        @Schema(example = "Paulo Bala", required = true)
        String pai,
        @Schema(example = "jucabala@gmail.com", required = true)
        String email,
        @Schema(example = "00111-222", required = true)
        String cep,
        @Schema(example = "Rua dos pilares", required = true)
        String endereco,
        @Schema(example = "135", required = true)
        Integer numero,
        @Schema(example = "Jardim das couves", required = true)
        String bairro,
        @Schema(example = "Araraquara", required = true)
        String cidade,
        @Schema(example = "SP", required = true)
        String estado,
        @Schema(example = "11 35874545", required = true)
        @JsonAlias({"telefone_fixo"})
        String telefoneFixo, //telefone_fixo
        @Schema(example = "11 988571245", required = true)
        String celular,
        @Schema(example = "1.80", required = true)
        double altura,
        @Schema(example = "80.2", required = true)
        double peso,
        @JsonAlias({"tipo_sanguineo"})
        @Schema(example = "A+", required = true)
        String tipoSanguineo //tipo_sanguineo

) {

        public Pessoa toPessoa() {
                SexoEnum sexoEnum = SexoEnum.getEnum(sexo);
                TipoSanguineoEnum tipoSanguineoEnum = TipoSanguineoEnum.getEnum(tipoSanguineo);
                Endereco enderecoObj = new Endereco(cep, endereco, numero, bairro, cidade, estado);
                return new Pessoa(nome, cpf, rg, getFormatedDate(), sexoEnum, mae, pai, email, enderecoObj, telefoneFixo, celular, altura, peso, tipoSanguineoEnum);
        }

        public String getFormatedDate() {
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = "";
                try {
                        Date date = inputFormat.parse(dataNascimento);
                        formattedDate = outputFormat.format(date);
                } catch (ParseException exception) {
                        System.out.println(exception.getMessage());
                }
                return formattedDate;
        }

}
