package br.com.alexandre.BancoDeSangue.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity(name = "Endereco")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "endereco")
    private String address;

    @Column(name = "numero")
    private Integer number;

    @Column(name = "bairro")
    private String neighborhood;

    @Column(name = "cidade")
    private String city;

    @Column(name = "estado")
    private String state;

    public Address() {
        this.cep = "";
        this.address = "";
        this.number = -1;
        this.neighborhood = "";
        this.city = "";
        this.state = "";
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipcode='" + cep + '\'' +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
