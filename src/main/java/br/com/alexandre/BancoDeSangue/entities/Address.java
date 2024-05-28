package br.com.alexandre.BancoDeSangue.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Address {

    private String cep;
    private String address;
    private Integer number;
    private String neighborhood;
    private String city;
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
