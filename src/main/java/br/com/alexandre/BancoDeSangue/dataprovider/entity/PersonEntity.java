package br.com.alexandre.BancoDeSangue.dataprovider.entity;

import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.core.entities.enums.BloodTypeEnum;
import br.com.alexandre.BancoDeSangue.core.entities.enums.SexEnum;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity(name = "Pessoa")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome")
    @JsonAlias({"name"})
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "dataNascimento")
    @JsonAlias({"data_nasc"})
    private String birthdate;

    @Column(name = "sexo")
    @JsonAlias({"sex"})
    private String sex;

    @Column(name = "mae")
    @JsonAlias({"mother"})
    private String mother;

    @Column(name = "pai")
    @JsonAlias({"father"})
    private String father;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id")
    @JsonAlias({"address"})
    private AddressEntity addressEntity;

    @Column(name = "telefoneFixo")
    @JsonAlias({"telefone_fixo"})
    private String homePhone;

    @Column(name = "celular")
    @JsonAlias({"cellphone"})
    private String cellphone;

    @Column(name = "altura")
    @JsonAlias({"height"})
    private double height;

    @Column(name = "peso")
    @JsonAlias({"weight"})
    private double weight;

    @Column(name = "tipoSanguineo")
    @JsonAlias({"tipo_sanguineo"})
    private String bloodType;

    public PersonEntity() {
        this.name = "";
        this.cpf = "";
        this.rg = "";
        this.birthdate = "";
        this.sex = "";
        this.mother = "";
        this.father = "";
        this.email = "";
        this.addressEntity = new AddressEntity();
        this.homePhone = "";
        this.cellphone = "";
        this.height = 0.0;
        this.weight = 0.0;
        this.bloodType = "";
    }

    public Person toObject() {
        return new Person(
                this.name,
                this.cpf,
                this.rg,
                this.birthdate,
                SexEnum.getEnumFromChar(this.sex),
                this.mother,
                this.father,
                this.email,
                this.addressEntity.toObject(),
                this.homePhone,
                this.cellphone,
                this.height,
                this.weight,
                BloodTypeEnum.getEnum(this.bloodType)
        );
    }

}
