package br.com.alexandre.BancoDeSangue.controller.dto;

import br.com.alexandre.BancoDeSangue.entities.Address;
import br.com.alexandre.BancoDeSangue.entities.Person;
import br.com.alexandre.BancoDeSangue.entities.SexEnum;
import br.com.alexandre.BancoDeSangue.entities.BloodTypeEnum;
import br.com.alexandre.BancoDeSangue.exceptions.PersonException;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record PersonDto(

        @JsonProperty("nome")
        @Schema(example = "Juca Bala", required = true)
        String name,

        @Schema(example = "00011122233", required = true)
        String cpf,

        @Schema(example = "001112223", required = true)
        String rg,

        @JsonProperty("data_nasc")
        @JsonAlias({"data_nasc"})
        @Schema(example = "10/07/1992", required = true)
        String birthdate,

        @JsonProperty("sexo")
        @Schema(example = "Masculino", required = true)
        String sex,

        @JsonProperty("mae")
        @Schema(example = "Bia Bala", required = true)
        String mother,

        @JsonProperty("pai")
        @Schema(example = "Paulo Bala", required = true)
        String father,

        @Schema(example = "jucabala@gmail.com", required = true)
        String email,

        @JsonProperty("cep")
        @Schema(example = "00111-222", required = true)
        String zipcode,

        @JsonProperty("endereco")
        @Schema(example = "Rua dos pilares", required = true)
        String address,

        @JsonProperty("numero")
        @Schema(example = "135", required = true)
        Integer number,

        @JsonProperty("bairro")
        @Schema(example = "Jardim das couves", required = true)
        String neighborhood,

        @JsonProperty("cidade")
        @Schema(example = "Araraquara", required = true)
        String city,

        @JsonProperty("estado")
        @Schema(example = "SP", required = true)
        String state,

        @JsonProperty("telefone_fixo")
        @Schema(example = "11 35874545", required = true)
        @JsonAlias({"telefone_fixo"})
        String homePhone, //telefone_fixo

        @JsonProperty("celular")
        @Schema(example = "11 988571245", required = true)
        String cellphone,

        @JsonProperty("altura")
        @Schema(example = "1.80", required = true)
        double height,

        @JsonProperty("peso")
        @Schema(example = "80.2", required = true)
        double weight,

        @JsonProperty("tipo_sanguineo")
        @JsonAlias({"tipo_sanguineo"})
        @Schema(example = "A+", required = true)
        String bloodType //tipo_sanguineo

) {

    public Person toPerson() {
        try {
            SexEnum sexEnum = SexEnum.getEnum(sex);
            BloodTypeEnum bloodTypeEnum = BloodTypeEnum.getEnum(bloodType);
            Address addressObj = new Address(null, zipcode, address, number, neighborhood, city, state);
            return new Person(name, cpf, rg, getFormattedDate(), sexEnum, mother, father, email, addressObj, homePhone, cellphone, height, weight, bloodTypeEnum);
        } catch (Exception exception) {
            throw PersonException.convert(this);
        }
    }

    @JsonIgnore
    public String getFormattedDate() {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = "";
        try {
            Date date = inputFormat.parse(birthdate);
            formattedDate = outputFormat.format(date);
        } catch (ParseException exception) {
            System.out.println(exception.getMessage());
        }
        return formattedDate;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", sex='" + sex + '\'' +
                ", mother='" + mother + '\'' +
                ", father='" + father + '\'' +
                ", email='" + email + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", bloodType='" + bloodType + '\'' +
                '}';
    }
}
