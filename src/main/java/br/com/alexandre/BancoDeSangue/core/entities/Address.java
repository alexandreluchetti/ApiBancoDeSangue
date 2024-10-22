package br.com.alexandre.BancoDeSangue.core.entities;

public class Address {

    private String cep;
    private String address;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;

    public Address(String cep, String address, Integer number, String neighborhood, String city, String state) {
        this.cep = cep;
        this.address = address;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public Address() {
    }

    public String getCep() {
        return cep;
    }

    public String getAddress() {
        return address;
    }

    public Integer getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
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
