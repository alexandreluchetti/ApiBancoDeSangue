package br.com.alexandre.BancoDeSangue.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static final List<TipoSanguineoEnum> tipos = List.of(
            A_POSITIVO, A_NEGATIVO, B_POSITIVO, B_NEGATIVO, AB_POSITIVO, AB_NEGATIVO, O_POSITIVO, O_NEGATIVO);

    private String value;

    TipoSanguineoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TipoSanguineoEnum getEnum(String value) {
        for (TipoSanguineoEnum tsEnum : values()) {
            if (value.toUpperCase().contains(tsEnum.value))
                return tsEnum;
        }
        return VAZIO;
    }

}
