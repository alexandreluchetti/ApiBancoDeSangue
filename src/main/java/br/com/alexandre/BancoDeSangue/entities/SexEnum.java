package br.com.alexandre.BancoDeSangue.entities;

public enum SexEnum {

    MALE("MASCULINO", "M"),
    FEMALE("FEMININO", "F"),
    OTHER("OUTRO", "OUTRO");

    private String value;
    private String valueChar;

    SexEnum(String value, String valueChar) {
        this.value = value;
        this.valueChar = valueChar;
    }

    public String getValue() {
        return value;
    }

    public String getValueChar() {
        return valueChar;
    }

    public static SexEnum getEnum(String value) {
        for (SexEnum sexEnum : values()) {
            if (value.toUpperCase().contains(sexEnum.value) ||
            value.toUpperCase().contains(sexEnum.valueChar))
                return sexEnum;
        }
        return OTHER;
    }
}
