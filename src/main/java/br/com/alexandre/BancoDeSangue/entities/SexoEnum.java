package br.com.alexandre.BancoDeSangue.entities;

import java.util.Arrays;
import java.util.List;

public enum SexoEnum {

    MASCULINO("MASCULINO", "M"),
    FEMININO("FEMININO", "F"),
    VAZIO("VAZIO", "");

    private String value;
    private String valueChar;

    SexoEnum(String value, String valueChar) {
        this.value = value;
        this.valueChar = valueChar;
    }

    public String getValue() {
        return value;
    }

    public String getValueChar() {
        return valueChar;
    }

    public static SexoEnum getEnum(String value) {
        for (SexoEnum sexoEnum : values()) {
            if (value.toUpperCase().contains(sexoEnum.value))
                return sexoEnum;
        }
        return VAZIO;
    }
}
