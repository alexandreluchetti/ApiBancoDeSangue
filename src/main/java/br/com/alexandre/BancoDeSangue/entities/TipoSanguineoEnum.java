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

    public static final List<TipoSanguineoEnum> doarPara(TipoSanguineoEnum tipoSanguineo) {
        return switch (tipoSanguineo) {
            case A_POSITIVO -> List.of(AB_POSITIVO, A_POSITIVO);
            case A_NEGATIVO -> List.of(A_POSITIVO, A_NEGATIVO, AB_POSITIVO, AB_NEGATIVO);
            case B_POSITIVO -> List.of(B_POSITIVO, AB_POSITIVO);
            case B_NEGATIVO -> List.of(B_POSITIVO, B_NEGATIVO, AB_POSITIVO, AB_NEGATIVO);
            case AB_POSITIVO -> List.of(AB_POSITIVO);
            case AB_NEGATIVO -> List.of(AB_POSITIVO, AB_NEGATIVO);
            case O_POSITIVO -> List.of(A_POSITIVO, B_POSITIVO, AB_POSITIVO, O_POSITIVO);
            case O_NEGATIVO -> tipos;
            default -> new ArrayList<>();
        };
    }

    public static final List<TipoSanguineoEnum> receberDe(TipoSanguineoEnum tipoSanguineo) {
        return switch (tipoSanguineo) {
            case A_POSITIVO -> List.of(A_POSITIVO, A_NEGATIVO, O_POSITIVO, O_NEGATIVO);
            case A_NEGATIVO -> List.of(A_NEGATIVO, O_NEGATIVO);
            case B_POSITIVO -> List.of(B_POSITIVO, B_NEGATIVO, O_POSITIVO, O_NEGATIVO);
            case B_NEGATIVO -> List.of(B_NEGATIVO, O_NEGATIVO);
            case AB_POSITIVO -> tipos;
            case AB_NEGATIVO -> List.of(A_NEGATIVO, B_NEGATIVO, O_NEGATIVO, AB_NEGATIVO);
            case O_POSITIVO -> List.of(O_POSITIVO, O_NEGATIVO);
            case O_NEGATIVO -> List.of(O_NEGATIVO);
            default -> new ArrayList<>();
        };
    }

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
