package br.com.alexandre.BancoDeSangue.entities;

public enum TipoSanguineoEnum {

    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-"),
    AB_POSITIVO("AB+"),
    AB_NEGATIVO("AB-"),
    O_POSITIVO("O+"),
    O_NEGATIVO("O-"),
    VAZIO("VAZIO");

    private String value;

    TipoSanguineoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
