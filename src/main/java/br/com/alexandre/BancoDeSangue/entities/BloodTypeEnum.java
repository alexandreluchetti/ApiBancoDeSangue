package br.com.alexandre.BancoDeSangue.entities;

import java.util.ArrayList;
import java.util.List;

public enum BloodTypeEnum {

    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    VAZIO("EMPTY");

    public static final List<BloodTypeEnum> tipos = List.of(
            A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE);

    public static final List<BloodTypeEnum> donateTo(BloodTypeEnum tipoSanguineo) {
        return switch (tipoSanguineo) {
            case A_POSITIVE -> List.of(AB_POSITIVE, A_POSITIVE);
            case A_NEGATIVE -> List.of(A_POSITIVE, A_NEGATIVE, AB_POSITIVE, AB_NEGATIVE);
            case B_POSITIVE -> List.of(B_POSITIVE, AB_POSITIVE);
            case B_NEGATIVE -> List.of(B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE);
            case AB_POSITIVE -> List.of(AB_POSITIVE);
            case AB_NEGATIVE -> List.of(AB_POSITIVE, AB_NEGATIVE);
            case O_POSITIVE -> List.of(A_POSITIVE, B_POSITIVE, AB_POSITIVE, O_POSITIVE);
            case O_NEGATIVE -> tipos;
            default -> new ArrayList<>();
        };
    }

    public static final List<BloodTypeEnum> recieveFrom(BloodTypeEnum tipoSanguineo) {
        return switch (tipoSanguineo) {
            case A_POSITIVE -> List.of(A_POSITIVE, A_NEGATIVE, O_POSITIVE, O_NEGATIVE);
            case A_NEGATIVE -> List.of(A_NEGATIVE, O_NEGATIVE);
            case B_POSITIVE -> List.of(B_POSITIVE, B_NEGATIVE, O_POSITIVE, O_NEGATIVE);
            case B_NEGATIVE -> List.of(B_NEGATIVE, O_NEGATIVE);
            case AB_POSITIVE -> tipos;
            case AB_NEGATIVE -> List.of(A_NEGATIVE, B_NEGATIVE, O_NEGATIVE, AB_NEGATIVE);
            case O_POSITIVE -> List.of(O_POSITIVE, O_NEGATIVE);
            case O_NEGATIVE -> List.of(O_NEGATIVE);
            default -> new ArrayList<>();
        };
    }

    private String value;

    BloodTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BloodTypeEnum getEnum(String value) {
        for (BloodTypeEnum tsEnum : values()) {
            if (value.toUpperCase().equals(tsEnum.value))
                return tsEnum;
        }
        return VAZIO;
    }

}
