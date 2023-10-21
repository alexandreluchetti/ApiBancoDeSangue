package br.com.alexandre.BancoDeSangue.entities;

public enum SexoEnum {

    MASCULINO("MASCULINO"),
    FEMININO("FEMININO"),
    VAZIO("VAZIO");

    private String value;

    SexoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
