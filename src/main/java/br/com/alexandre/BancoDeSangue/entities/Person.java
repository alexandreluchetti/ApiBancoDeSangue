package br.com.alexandre.BancoDeSangue.entities;

import br.com.alexandre.BancoDeSangue.controller.dto.PersonDto;
import br.com.alexandre.BancoDeSangue.exceptions.FormatException;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

@Setter
@Getter
@AllArgsConstructor
public class Person {

    @JsonAlias({"name"})
    private String name;

    private String cpf;

    private String rg;

    @JsonAlias({"data_nasc"})
    private String birthdate;

    @JsonAlias({"sex"})
    private SexEnum sex;

    @JsonAlias({"mother"})
    private String mother;

    @JsonAlias({"father"})
    private String father;

    private String email;

    @JsonAlias({"address"})
    private Address address;

    @JsonAlias({"telefone_fixo"})
    private String homePhone;

    @JsonAlias({"cellphone"})
    private String cellphone;

    @JsonAlias({"height"})
    private double height;

    @JsonAlias({"weight"})
    private double weight;

    @JsonAlias({"tipo_sanguineo"})
    private BloodTypeEnum bloodType;

//    public Person(String name, String cpf, String rg, String birthdate, SexEnum sex, String mother, String father, String email, Endereco address, String homePhone, String cellphone, double height, double weight, TipoSanguineoEnum bloodType) {
//        this.name = name;
//        this.cpf = cpf;
//        this.rg = rg;
//        this.birthdate = birthdate;
//        this.sex = sex;
//        this.mother = mother;
//        this.father = father;
//        this.email = email;
//        this.address = address;
//        this.homePhone = homePhone;
//        this.cellphone = cellphone;
//        this.height = height;
//        this.weight = weight;
//        this.bloodType = bloodType;
//    }

    public Person() {
        this.name = "";
        this.cpf = "";
        this.rg = "";
        this.birthdate = "";
        this.sex = SexEnum.OTHER;
        this.mother = "";
        this.father = "";
        this.email = "";
        this.address = new Address();
        this.homePhone = "";
        this.cellphone = "";
        this.height = 0.0;
        this.weight = 0.0;
        this.bloodType = BloodTypeEnum.VAZIO;
    }

//    public String getName() {
//        return name;
//    }
//
//    public String getCpf() {
//        return cpf;
//    }
//
//    public String getRg() {
//        return rg;
//    }

    public Date getFormattedDate() {
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = outputFormat.parse(birthdate); // Converte a string para java.util.Date
            String formattedDate = outputFormat.format(utilDate); // Formata a data no formato "yyyy-MM-dd"
            return Date.valueOf(formattedDate);
        } catch (ParseException exception) {
            throw FormatException.stringDateFormat();
        }
    }

//    public SexEnum getSex() {
//        return sex;
//    }
//
//    public String getMother() {
//        return mother;
//    }
//
//    public String getFather() {
//        return father;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public Endereco getAddress() {
//        return address;
//    }
//
//    public String getHomePhone() {
//        return homePhone;
//    }
//
//    public String getCellphone() {
//        return cellphone;
//    }
//
//    public double getHeight() {
//        return height;
//    }
//
//    public double getWeight() {
//        return weight;
//    }
//
//    public TipoSanguineoEnum getBloodType() {
//        return bloodType;
//    }

    public Integer getAge() {
        LocalDate now = LocalDate.now();
        LocalDate birthdate = getFormattedDate().toLocalDate();
        Period period = Period.between(birthdate, now);
        return period.getYears();
    }

    public Double getImc() {
        return (this.weight / (this.height * this.height));
    }

    public PersonDto toDto() {
        return new PersonDto(
                name,
                cpf,
                rg,
                formatDate(),
                sex.getValue(),
                mother,
                father,
                email,
                address.getCep(),
                address.getAddress(),
                address.getNumber(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                homePhone,
                cellphone,
                height,
                weight,
                bloodType.getValue()
        );
    }

    public String formatDate() {
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = inputFormat.parse(birthdate);
            return outputFormat.format(date);
        } catch (ParseException exception) {
            throw FormatException.stringDateFormat();
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", sex=" + sex +
                ", mother='" + mother + '\'' +
                ", father='" + father + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", homePhone='" + homePhone + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", bloodType=" + bloodType +
                '}';
    }
}
